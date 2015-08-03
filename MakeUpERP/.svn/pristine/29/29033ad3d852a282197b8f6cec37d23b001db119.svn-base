package com.ga.erp.biz.purchase.order;

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
import com.ga.erp.util.SystemUtil;

public class PurOrderBiz extends ACLBiz{

	public PurOrderBiz(UserACL userACL) {
		super(userACL);
	}
  String publicSql = "SELECT po.*,o.TRUENAME,s.SUPPLIER_NAME,ppo.PURCHASE_ORDER_NUM as P_PURCHASE_ORDER_NUM ,d.DELIVERY_NAME,st.NAME,sm.CODE " +
      "FROM purchase_order po JOIN SUPPLIER s ON po.SUPPLIER_ID =s.SUPPLIER_ID " +
      " LEFT  JOIN purchase_order ppo ON po.P_PURCHASE_ORDER_ID=ppo.PURCHASE_ORDER_ID " +
      " JOIN delivery_org d ON d.DELIVERY_ORG_ID= po.DELIVERY_ORG_ID " +
      " JOIN stock st ON po.STOCK_ID=st.STOCK_ID " +
      " left JOIN settlement sm ON sm.SETTLEMENT_ID=po.SETTLEMENT_ID"+
      " JOIN OP o ON o.OP_ID=po.OP_ID";
  
	public PageResult queryStockList(QueryPageData queryPageData,List<DbField> dbFieldList, Long supplierId,String type,String state) {
		String whereSql = " s.DELETE_STATE = 1 ";
		if(!GaUtil.isNullStr(supplierId + "")) {
			whereSql += " AND po.SUPPLIER_ID = " + supplierId;
		}
		if(!GaUtil.isNullStr(type)){
		  whereSql += " and po.TYPE="+type;
		}
		if(GaUtil.isValidStr(state)){
		  if(state.equals("5")){
		    whereSql += "and TO_DAYS(po.CREATE_TIME) = TO_DAYS(NOW())";
		  }else {
	        whereSql += "and po.STATE=" + state;
	      }
		}
		return BizUtil.queryListBySQL(publicSql, whereSql, " po.PURCHASE_ORDER_ID desc", dbFieldList, queryPageData, this.userACL);
	}
	
	public Map<String, Object> queryStockDetail(Long id) {
		String sql = publicSql+" where po.PURCHASE_ORDER_ID=? ";
		return DbUtils.queryMap(DbUtils.getConnection(),sql, id);
	}

	public void savePurOrder(Map<String, Object> valuesMap, boolean isAdd) {
	  String updateFiled="PURCHASE_ORDER_NUM,P_PURCHASE_ORDER_ID,SETTLEMENT_ID,SUPPLIER_ID,STOCK_ID," +
        "DILIVERY_ADDR_ID,DELIVERY_TIME,ORDER_CNT,REALITY_PRICE,COMM_SORT_CNT,COMM_CNT,DISTRIBUTE_MONEY,DISTRIBUTE_NUM" +
        ",IS_DIFF,DIFF_IS_SETTLED,STATE,NOTE,DIFF_NOTE,TYPE,DELIVERY_ORG_ID";
		if (isAdd) {
			checkMap(valuesMap);
			Map<String, String> funcFieldMap =new HashMap<String, String>();
			funcFieldMap.put("CREATE_TIME", "NOW()");
			valuesMap.put("OP_ID", this.userACL.getUserID());
			valuesMap.put("IS_DIFF", -1);
			valuesMap.put("STATE", -1);
			DbUtils.add("PURCHASE_ORDER", valuesMap,funcFieldMap,updateFiled + ",OP_ID,CREATE_TIME");
		} else {
			DbUtils.update("PURCHASE_ORDER", valuesMap, null,updateFiled,"PURCHASE_ORDER_ID");
		}	
		
	}

	private void checkMap(Map<String, Object> valuesMap) {
		 StringBuffer buffer = new StringBuffer("");
		    int count = 1;
		if (GaUtil.isNullStr((valuesMap.get("PURCHASE_ORDER_NUM") + ""))) {
			buffer.append("<br>"+count+"，请选择清单编号；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("SETTLEMENT_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择商品；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("SUPPLIER_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择供应商；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("STOCK_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择仓库；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("SETTLEMENT_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择结算流水号；");
	        count++;
		}
		
		if (GaUtil.isNullStr((valuesMap.get("DILIVERY_ADDR_ID") + ""))) {
			buffer.append("<br>"+count+"，请填写配送地址；");
	        count++;
		}
		
		if (GaUtil.isNullStr((valuesMap.get("DELIVERY_ORG_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择配送机构；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("DELIVERY_TIME") + ""))) {
			buffer.append("<br>"+count+"，请选择配送机构；");
	        count++;
		}
		if(buffer.length() > 0){
		       throw new BizException(buffer.toString());
		}

	}
	  /**
	  * 查询供应商订单物流记录
	  * @param orderFormID 订单ID
	  * @param dbFieldList 字段列表
	  * @return
	  */
	  public List<Map<String,Object>> queryOrderDeliveryRecord(Long id,List<DbField> dbFieldList) {
	   Map<String,Object> deliveryMap=DbUtils.queryMap(DbUtils.getConnection(), "select D.DELIVERY_NAME,D.DELIVERY_CODE,S.DISTRIBUTE_NUM from DELIVERY_ORG D join PURCHASE_ORDER S " +
	   		" on D.DELIVERY_ORG_ID = S.DELIVERY_ORG_ID where S.PURCHASE_ORDER_ID = ?", id);
	   return SystemUtil.resolveDeliveryRecord(deliveryMap);
	  }
}
