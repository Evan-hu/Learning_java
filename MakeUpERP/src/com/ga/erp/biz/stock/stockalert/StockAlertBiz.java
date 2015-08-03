package com.ga.erp.biz.stock.stockalert;

import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.ACLBiz;

public class StockAlertBiz extends ACLBiz {


	public StockAlertBiz(UserACL userACL) {
		super(userACL);
	}
	

	public PageResult queryStockCommList(QueryPageData queryPageData, List<DbField> dbFieldList, String type) {
	  String where ="";
	  String sql ="select s.*, c.COMMODITY_NAME, sk.NAME, sf.SHELF_NUM\n" +
	      "  from STOCK_COMM s\n" + 
	      "  JOIN COMMODITY c\n" + 
	      "  on s.COMMODITY_ID = c.COMMODITY_ID\n" + 
	      "  JOIN SHELF sf\n" + 
	      "  ON s.SHELF_ID = sf.SHELF_ID\n" + 
	      "  JOIN STOCK sk\n" + 
	      "  ON s.STOCK_ID = sk.STOCK_ID";
	  if(type.equals("1")){
	    where = "INNAGE <0 ";
	  }else {
	    where = "INNAGE <= LOWER_LIMIT and INNAGE >= 0 ";
    }
		return BizUtil.queryListBySQL(sql, where, "s.STOCK_COMM_ID desc",dbFieldList, queryPageData, this.userACL);
	}
}
