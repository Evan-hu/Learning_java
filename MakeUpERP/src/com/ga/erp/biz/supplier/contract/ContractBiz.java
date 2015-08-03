package com.ga.erp.biz.supplier.contract;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class ContractBiz extends ACLBiz {

	public ContractBiz(UserACL userACL) {
		super(userACL);
	}

	public PageResult queryContractList(QueryPageData queryPageData,List<DbField> dbFieldList, Long supplierId) {
		String sql = "select c.*,s.SUPPLIER_NAME,o.TRUENAME from  "
				+ "CONTRACT c JOIN SUPPLIER s on c.SUPPLIER_ID = s.SUPPLIER_ID " +
				"join OP o on o.OP_ID=c.OP_ID";
		String whereSql = " s.DELETE_STATE = 1 ";
		if(!GaUtil.isNullStr(supplierId + "")) {
			whereSql += " AND C.SUPPLIER_ID = " + supplierId;
		}
		return BizUtil.queryListBySQL(sql, whereSql, "c.CONTRACT_ID desc",dbFieldList, queryPageData, this.userACL);
	}

	public Map<String, Object> queryContractDetail(Long id) {
		return DbUtils.queryMap(DbUtils.getConnection(),"select c.*,s.SUPPLIER_NAME,o.TRUENAME from "
								+ "CONTRACT c JOIN SUPPLIER s on c.SUPPLIER_ID = s.SUPPLIER_ID JOIN OP o ON o.OP_ID=c.OP_ID where c.CONTRACT_ID = ?", id);
	}

	public void saveContract(Map<String, Object> valuesMap, boolean isAdd) {
	
		if (isAdd) {
			checkMap(valuesMap);
			if (isExists(valuesMap.get("CONTRACT_NUM") + "")) {
				throw new BizException("合同编号已存在");
			}
			Map<String, String> funcMap = new HashMap<String, String>();
			funcMap.put("CREATE_TIME", "now()");
			valuesMap.put("OP_ID", this.userACL.getUserID());
			valuesMap.put("STATE", 1);
			DbUtils.add("CONTRACT", valuesMap, funcMap,
					"SUPPLIER_ID,CONTRACT_NUM,BEG_TIME,END_TIME,CONTENT,OP_ID,CREATE_TIME,STATE");
		} else {
			if(GaUtil.isNullStr(valuesMap.get("SUPPLIER_ID")+"")){
				throw new BizException("请选择供应商");
			}
			if(GaUtil.isNullStr(valuesMap.get("CONTRACT_NUM")+"")){
				throw new BizException("请选择合同编号");
			}
			if(GaUtil.isNullStr(valuesMap.get("CONTENT")+"")){
				throw new BizException("请填写合同内容");
			}
			DbUtils.update("CONTRACT", valuesMap, null,"SUPPLIER_ID,CONTRACT_NUM,CONTENT,STATE","CONTRACT_ID");
		}
		
	}

	private void checkMap(Map<String, Object> valuesMap) {
		
		if(GaUtil.isNullStr(valuesMap.get("SUPPLIER_ID")+"")){
			throw new BizException("请选择供应商");
		}
		if(GaUtil.isNullStr(valuesMap.get("CONTRACT_NUM")+"")){
			throw new BizException("请选择合同编号");
		}
		if(GaUtil.isNullStr(valuesMap.get("BEG_TIME")+"")){
			throw new BizException("请选择合同开始时间");
		}
		if(GaUtil.isNullStr(valuesMap.get("END_TIME")+"")){
			throw new BizException("请选择合同结束时间");
		}
		if(GaUtil.isNullStr(valuesMap.get("CONTENT")+"")){
			throw new BizException("请填写合同内容");
		}
		if(!GaUtil.compareTimeStr(valuesMap.get("BEG_TIME")+"", valuesMap.get("END_TIME")+"", "yyyy-MM-dd HH:mm:ss")){
			throw new BizException("开始时间大于结束时间，请选择正确的时间段");
		}
		
		
	}

	private boolean isExists(String num) {
		//方法如何调用
		 return DbUtils.queryLong("SELECT COUNT(*) FROM CONTRACT WHERE CONTRACT_NUM=?", num) > 0; 
//		if (count > 0) {
//			return true;
//		} else {
//			return false;
//		}

	}

}