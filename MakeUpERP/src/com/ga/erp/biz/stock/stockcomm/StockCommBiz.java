package com.ga.erp.biz.stock.stockcomm;

import java.util.List;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class StockCommBiz extends ACLBiz {

	public StockCommBiz(UserACL userACL) {
		super(userACL);
	}
  private String pubSql ="select s.*, s.INNAGE CNT, c.COMMODITY_NAME, sk.NAME, sf.SHELF_NUM\n" +
      "  from STOCK_COMM s\n" + 
      "  JOIN COMMODITY c\n" + 
      "    on s.COMMODITY_ID = c.COMMODITY_ID\n" + 
      "  JOIN SHELF sf\n" + 
      "    ON s.SHELF_ID = sf.SHELF_ID\n" + 
      "  JOIN STOCK sk\n" + 
      "    ON s.STOCK_ID = sk.STOCK_ID";
  /**
   * 
   * @param queryPageData
   * @param dbFieldList
   * @param shelfId
   * @param inBatchID
   * @param stockId
   * @return
   */
	public PageResult queryStockCommList(QueryPageData queryPageData, List<DbField> dbFieldList, Long shelfId,Long inBatchID,Long stockId) {
	  String where="";
	try{
	  
	  //盘点批次 选择盘点对象
	  if(stockId!=null){
	    /**
	     * 1.全场盘点
         2.单品盘点
         3.分类盘点
         4.品牌盘点
	     */
	    where+="s.STOCK_ID="+stockId;
	   
	      return BizUtil.queryListBySQL(pubSql, where, "s.STOCK_COMM_ID desc",dbFieldList, queryPageData, this.userACL);
	    
//	    else if(batchscope==3){
//	      pubSql="";
//	      return BizUtil.queryListBySQL(pubSql, where, "s.STOCK_COMM_ID desc",dbFieldList, queryPageData, this.userACL);
//      }
//	    else if(batchscope==4){
//	      return BizUtil.queryListBySQL("select ", where, "s.STOCK_COMM_ID desc",dbFieldList, queryPageData, this.userACL);
//	    }
 
	  }
	  
	  //盘点单 选择商品
	  if(!GaUtil.isNullStr(inBatchID+"")){
	  
	 CachedRowSet  batch= DbUtils.query("select type_id, scope from inventory_batch where inventory_batch_id=?", inBatchID);
	 CachedRowSet scope=DbUtils.query("select  OBJECT_ID from INVENTORY_SCOPE where inventory_batch_id=?", inBatchID);
	 batch.next();
	 int scopeType = batch.getInt("scope");
	 int typeId=batch.getInt("type_id");
	 int[] objectId=new int[scope.size()];
	 int i=0;
	 while(scope.next()){
	   objectId[i]=scope.getInt("OBJECT_ID");
	   i++;
	 }
	 where="s.stock_id="+typeId;
	 //为1 代表全场盘点
	 if(scopeType==1){
	
	   
	   // 2.单品盘点
	 }else if(scopeType==2){
	   where+=" and s.COMMODITY_ID in(";
	   for(int id:objectId){
	     where+=id+",";
	   }
	   where+="-1)";
	 }//3.分类盘点
	 else if(scopeType==3){
     where+=" and c.SORT_ID in(";
     for(int id:objectId){
       where+=id+",";
     }
     where+="-1)";
   } // 4.品牌盘点
	 else if(scopeType==4){
	   where+=" and c.BRAND_ID in(";
     for(int id:objectId){
       where+=id+",";
     }
     where+="-1)";
  
	 }
	 return BizUtil.queryListBySQL(pubSql, where, "s.STOCK_COMM_ID desc",dbFieldList, queryPageData, this.userACL);
	}else{	
	 where = shelfId == null ? "" : "s.SHELF_ID = " + shelfId;
		return BizUtil.queryListBySQL(pubSql, where, "s.STOCK_COMM_ID desc",dbFieldList, queryPageData, this.userACL);
	  }
	}
	catch (Exception e) {
    return null;
  }
	}

	public Map<String, Object> queryStockCommDetail(Long id, List<DbField> fieldList) {
		return DbUtils.queryMap(fieldList, pubSql+" where s.STOCK_COMM_ID=?" , id);
	}
	
  public List<Map<String, Object>> queryStockCommDetail(List<DbField> fieldList, Long storeId) {
    return DbUtils.queryMapList(fieldList, pubSql + " where s.STOCK_ID = ?", storeId);
  }
	public void saveStockComm(Map<String, Object> valuesMap, boolean isAdd) {

		String dbFile = "COMMODITY_ID,STOCK_ID,SHELF_ID,STOCK_PRICE,COST_PRICE,WHOLESALE_PRICE,RETAIL_PRICE,MEM_PRICE,"
				+ "DISTRIBUT_PRICE,INNAGE,UPPER_LIMIT,LOWER_LIMIT,STATE";
		checkMap(valuesMap);
		if (isAdd) {
//			if (isExists(valuesMap.get("COMMODITY_ID") + "")) {
//				throw new BizException("已存在该商品");
//			}
			valuesMap.put("STATE", 1);
			DbUtils.add("STOCK_COMM", valuesMap, null, dbFile);
		} else {
			DbUtils.update("STOCK_COMM", valuesMap, null, dbFile,
					"STOCK_COMM_ID");
		}

	}

	private void checkMap(Map<String, Object> valuesMap) {
		 StringBuffer buffer = new StringBuffer("");
		    int count = 1;
		if (GaUtil.isNullStr((valuesMap.get("COMMODITY_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择商品；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("STOCK_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择仓库；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("SHELF_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择货架；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("STOCK_PRICE") + ""))) {
			buffer.append("<br>"+count+"，请填写进货价；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("COST_PRICE") + ""))) {
			buffer.append("<br>"+count+"，请填写成本价；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("WHOLESALE_PRICE") + ""))) {
			buffer.append("<br>"+count+"，请填写批发价；");
	        count++;
		}
		if (GaUtil.isNullStr((valuesMap.get("RETAIL_PRICE") + ""))) {
			buffer.append("<br>"+count+"，请填写零售价；");
	        count++;
		}
		if(buffer.length() > 0){
		       throw new BizException(buffer.toString());
		}
	}

}
