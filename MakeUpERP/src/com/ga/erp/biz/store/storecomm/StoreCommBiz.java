package com.ga.erp.biz.store.storecomm;

import java.util.List;
import javax.sql.rowset.CachedRowSet;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.ACLBiz;

public class StoreCommBiz extends ACLBiz {

  public StoreCommBiz(UserACL userACL) {
    super(userACL);
  }
  String publicSql = "select sc.*,sc.INNAGE as CNT,s.STORE_NAME,c.COMMODITY_NAME from STORE_COMM sc " +
  		"JOIN STORE s on sc.STORE_ID = s.STORE_ID " +
  		"join COMMODITY c on c.COMMODITY_ID=sc.COMMODITY_ID";
  public PageResult queryStoreCommList(QueryPageData pageData,List<DbField> fieldList, Long storeId,Long inBatchID,Long batchStoreId) {
    String where="";
    try{
      if(batchStoreId!=null){
        
        where =  "sc.STORE_ID = " + batchStoreId;
        return BizUtil.queryListBySQL(publicSql, where,"sc.STORE_COMM_ID desc",
            fieldList, pageData, this.userACL);
        
      }
      if(inBatchID!=null){
      
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
     where="sc.STORE_ID="+typeId;
     //为1 代表全场盘点
     if(scopeType==1||scopeType==2){
    
       
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
     
     return BizUtil.queryListBySQL(publicSql, where, "sc.STORE_COMM_ID desc",fieldList, pageData, this.userACL);
    }else{
    
	  where = storeId == null ? "" : "sc.STORE_ID = " + storeId;
	  return BizUtil.queryListBySQL(publicSql, where,"sc.STORE_COMM_ID desc",
	  		fieldList, pageData, this.userACL);
        }
    }catch (Exception e) {
      return null;
    }
  
  }
}
