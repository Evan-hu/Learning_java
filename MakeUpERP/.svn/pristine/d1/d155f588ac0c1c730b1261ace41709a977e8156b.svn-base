package com.ga.erp.biz.store.storeorder.toOrder;

import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.mvc.UserACL;
import com.ga.erp.biz.ACLBiz;

public class ToOrderBiz extends ACLBiz {
	  public ToOrderBiz(UserACL userACL) {
		super(userACL);
	}
	  
	private String stockSql ="select s.*, c.COMMODITY_NAME, sk.NAME, sf.SHELF_NUM,C.CODE\n" +
		      "  from STOCK_COMM s\n" + 
		      "  JOIN COMMODITY c\n" + 
		      "    on s.COMMODITY_ID = c.COMMODITY_ID\n" + 
		      "  JOIN SHELF sf\n" + 
		      "    ON s.SHELF_ID = sf.SHELF_ID\n" + 
		      "  JOIN STOCK sk\n" + 
		      "    ON s.STOCK_ID = sk.STOCK_ID";
	
	/**
	 * 按库存量指标自动补货 （算法：[库存下限]商品，按[库存上限]补货 ） 
	 */
	public List<Map<String, Object>> queryLimitStockList(List<DbField> dbFieldList) {
	  return DbUtils.queryMapList(dbFieldList, stockSql);
	}
	
	/**
	 * 按安全库存量自动补货（算法：一般采用最近四周平均销量*采购周期得到。 ） 
	 * @return
	 */
	public List<Map<String, Object>> querySafeStockList(List<DbField> dbFieldList) {
	   return DbUtils.queryMapList(dbFieldList, stockSql);
	}
	
	 /**
   * 销量
   * @return
   */
  public List<Map<String, Object>> queryStockList(List<DbField> dbFieldList) {
    return DbUtils.queryMapList(dbFieldList, stockSql);
  }

}
