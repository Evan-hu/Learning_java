package com.ga.click.page;

import java.util.List;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.PageParam;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.GaUtil;
import com.ga.click.util.PagedQuery;

public class BizUtil {  
  public static PageResult queryListByTable(String tableName,String whereSql,String orderStr,List<DbField> dbFieldList,QueryPageData pageData,UserACL acl) {
    try {      
      if (dbFieldList == null) throw new BizException(BizException.COMMBIZ,"未定义数据字段"); 
      PagedQuery query;
      orderStr = gerOrderByStr(orderStr, pageData);
      if (acl != null && acl.isExportAction()) {
        pageData.setPageParam(null);
      }       //执行查询
      query = AutoSQLUtil.createSqlForTable(tableName,whereSql,orderStr,dbFieldList,pageData);
      CachedRowSet rowset = query.query();
      PageResult result = new PageResult();
      result.setPageParam(query.getPageParam());
      result.setData(rowset);
      return result;
    }
    catch(BizException e) {
      //提示处理
      throw e;
    }
    catch(Exception ex) {
      //提示处理
      throw new BizException(BizException.COMMBIZ,"查询列表失败"+ex.getMessage());
    }
  }
   
  
  public static PageResult queryListBySQL(String selectSql,String whereSql,String orderStr,List<DbField> dbFieldList,QueryPageData pageData,UserACL acl) {
    try {      
      if (dbFieldList == null) throw new BizException(BizException.COMMBIZ,"未定义数据字段"); 
      PagedQuery query;
      orderStr = gerOrderByStr(orderStr, pageData);
      if (acl != null && acl.isExportAction()) {
        pageData.setPageParam(null);
      }
      /************************数据过滤**************************/
      StringBuffer where = new StringBuffer(" 1 = 1 ");
      for (DbField field : dbFieldList) {
  		if(field.isFilterCode()){
  			if(field.getFieldCode().contains("STORE_ID") && GaUtil.isValidStr(acl.getStoreID())){
  				where.append(" AND " + field.getFieldCode() + " IN( " + acl.getStoreID() + ")");
  			}
  			if(field.getFieldCode().contains("SUPPLIER_ID") && acl.getSupplierID() > 0){
  				where.append(" AND " + field.getFieldCode() + " = " + acl.getSupplierID());
  			}
  			if(field.getFieldCode().contains("DELETE_STATE")){
  				where.append(" AND " + field.getFieldCode() + " <> -1");
  			}
  		  }
  	   }
      if(where.length() > 7){
    	  whereSql += (whereSql.equals("") ? "" : " AND ") + where;  
      }
      /************************数据过滤**************************/
      query = AutoSQLUtil.createSql(selectSql,whereSql,orderStr,dbFieldList,pageData);
      List<Map<String,Object>>  dataList = query.queryToList(dbFieldList);
      PageResult result = new PageResult();
      result.setPageParam(query.getPageParam());
      result.setDataList(dataList);
      return result;
      
    }
    catch(BizException e) {
      //提示处理
      throw e;
    }
    catch(Exception ex) {
      //提示处理
      throw new BizException(BizException.COMMBIZ,"查询列表失败"+ex.getMessage());
    }
  }
  
  public static PageResult queryListByCustomSQL(String customSQL,String orderStr,String selectParamStr,List<DbField> dbFieldList,QueryPageData pageData,UserACL acl) {
    try {      
      if (dbFieldList == null) throw new BizException(BizException.COMMBIZ,"未定义数据字段"); 
      PagedQuery query;
      orderStr = gerOrderByStr(orderStr, pageData);
      if (acl != null && acl.isExportAction()) {
        pageData.setPageParam(null);
      }       
      //执行查询
      query = AutoSQLUtil.createCustomSql(customSQL,orderStr,selectParamStr,dbFieldList,pageData);  
      CachedRowSet rowset = query.query();
      PageResult result = new PageResult();
      result.setPageParam(query.getPageParam());
      result.setData(rowset);
      return result;
    }
    catch(BizException e) {
      //提示处理
      throw e;
    }
    catch(Exception ex) {
      //提示处理
      throw new BizException(BizException.COMMBIZ,"查询列表失败"+ex.getMessage());
    }
  }
  
  /**
   * 执行分页查询
   * (不使用querypagedata的查询字段组装条件,直接执行sql语句并进行分页)
   * @param pageData 分页参数
   * @param dbFieldList 绑定的字段
   * @param selectSQL  查询语句
   * @param orderStr 排序语句
   * @param param 查询参数
   * @return
   */
  public static PageResult queryListNoAuto(PageParam pageData,List<DbField> dbFieldList,String selectSQL,String orderStr,UserACL acl,Object...param) {
    try {      
      if (dbFieldList == null) throw new BizException(BizException.COMMBIZ,"未定义数据字段"); 
      PagedQuery query  = AutoSQLUtil.createSqlNoAuto(dbFieldList, pageData, selectSQL, orderStr, param);     
      //执行查询
      CachedRowSet rowset = query.query();
      PageResult result = new PageResult();
      result.setPageParam(query.getPageParam());
      result.setData(rowset);
      return result;
    }
    catch(BizException e) {
      //提示处理
      throw e;
    }
    catch(Exception ex) {
      //提示处理
      throw new BizException(BizException.COMMBIZ,"查询列表失败"+ex.getMessage());
    }
  }
  
  public static String gerOrderByStr(String orderStr, QueryPageData pageData) {
    if (pageData == null || pageData.GetPageParam() == null) {
      return orderStr;
    }
    String orderNameFiled = pageData.GetPageParam().getOrderFiledName();
    String orderNameOrder = pageData.GetPageParam().getOrderFileOrder();
    if (orderNameFiled != null && !"".equals(orderNameFiled)) {
      return orderNameFiled + " " + orderNameOrder + "";
    }
    return orderStr;
  }
}
