package com.ga.click.dbutil;

public class PageParam {
    
  private int rowCount = 0;  //总记录条数
  private int pageNumber = 1; //当前页数 
  private int pageSize = 20; //每页记录数 -1表示不分页
  private int pageCount = 1;  //总页数
  private boolean havePage = true;
  private String orderFiledName = "";//排序字段名
  private String orderFileOrder = "";//排序方式,升序,降序
  
  
  public void setHavePage(boolean havePage) {
    this.havePage = havePage;
  }
  public boolean isHavePage() {
    return havePage;
  }
  public int getRowCount() {
    return rowCount;
  }
  public void setRowCount(int rowCount) {
    this.rowCount = rowCount;
  }
  public int getPageNumber() {
    return pageNumber;
  }
  public void setPageNumber(int pageNumber) {
    this.pageNumber = pageNumber;
  }
  public int getPageSize() {
    return pageSize;
  }
  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }
  public int getPageCount() {
    return pageCount;
  }
  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }
  public String getOrderFiledName() {
    return orderFiledName;
  }
  public void setOrderFiledName(String orderFiledName) {
    this.orderFiledName = orderFiledName;
  }
  public String getOrderFileOrder() {
    return orderFileOrder;
  }
  public void setOrderFileOrder(String orderFileOrder) {
    this.orderFileOrder = orderFileOrder;
  }
  
}
