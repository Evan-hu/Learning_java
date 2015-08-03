package com.ga.erp.biz.stock;

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

public class StockBiz extends ACLBiz{

	public StockBiz(UserACL userACL) {
		super(userACL);
	}

	public PageResult queryStockList(QueryPageData queryPageData,List<DbField> dbFieldList) {
		String sql = "SELECT s.*,a.AREA_NAME FROM  "
				+ "	STOCK s JOIN AREA a on s.AREA_ID= a.AREA_ID";
		return BizUtil.queryListBySQL(sql, "", "s.STOCK_ID desc",
				dbFieldList, queryPageData, this.userACL);
	}
	
	public Map<String, Object> queryStockDetail(Long id) {
		return DbUtils.queryMap(DbUtils.getConnection(),"SELECT s.*,a.AREA_NAME FROM  "
								+ "	STOCK s JOIN AREA a on s.AREA_ID= a.AREA_ID where s.STOCK_ID=?", id);
	}

	public void saveStock(Map<String, Object> valuesMap, boolean isAdd) {

		if (isAdd) {
			checkMap(valuesMap);
			if (isExists(valuesMap.get("NAME") + "")) {
				throw new BizException("仓库名已存在");
			}
			DbUtils.add("STOCK", valuesMap,null,
					"NAME,ACREAGE,AREA_ID");
		} else {
			DbUtils.update("STOCK", valuesMap, null,"NAME,ACREAGE,AREA_ID","STOCK_ID");
		}	
		
	}

	private void checkMap(Map<String, Object> valuesMap) {
		 StringBuffer buffer = new StringBuffer("");
		    int count = 1;
		if (GaUtil.isNullStr((valuesMap.get("NAME") + ""))) {
			buffer.append("<br>"+count+"，请填写仓库名；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("ACREAGE") + ""))) {
			buffer.append("<br>"+count+"，请填写仓库面积；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("AREA_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择所在地区；");
	        count++;
		}
		if(buffer.length() > 0){
		       throw new BizException(buffer.toString());
		}

	}

	private boolean isExists(String name) {
		 return DbUtils.queryLong("SELECT COUNT(*) FROM STOCK WHERE NAME=?", name) > 0; 
	}

}
