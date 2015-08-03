package com.ga.click.dbutil;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public class PageResult {
 
  //private boolean queryResult;
  //private String errorMessage;
  private int dataType = 0;
  private PageParam pageParam = new PageParam();  
  private ResultSet data;
  private List<Map<String,Object>> listData;
  
  public PageParam getPageParam() {
    return pageParam;
  }
  public void setPageParam(PageParam pageParam) {
    this.pageParam = pageParam;
  }  
  
  /**
   * 建议使用getListData方法进行处理
   * 此方法返回cacherowset在处理大数据时会出现异常
   * @return
   */
  @Deprecated 
  public ResultSet getData() {
    return data;
  }
  public void setData(ResultSet data) {
    this.data = data;
    if (data != null) {
      this.dataType = 0;
    }
  }
  
  public void setDataList(List<Map<String,Object>> data) {
   this.listData = data;
   if (data != null) {
     this.dataType = 1;
   }
  }
  public List<Map<String, Object>> getListData() {
    return listData;
  }
  public int getDataType() {
    return dataType;
  }
  
  
  
//  public boolean isQueryResult() {
//    return queryResult;
//  }
//  public void setQueryResult(boolean queryResult) {
//    this.queryResult = queryResult;
//  }
//  public String getErrorMessage() {
//    return errorMessage;
//  }
//  public void setErrorMessage(String errorMessage) {
//    this.errorMessage = errorMessage;
//  }
}
