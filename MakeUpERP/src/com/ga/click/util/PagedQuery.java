/**
 * page页面查询类提供分页数据和查询数据
 * @create_time 2011-11-24 13:40:41
 */
package com.ga.click.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageParam;
import com.ga.click.exception.BizException;
import com.sun.rowset.CachedRowSetImpl;

public class PagedQuery {
	
  private Connection conn;
  private String selectSQL;
  private String wheres;
  private List<Object> values;
  private String orders;
  private PageParam pageParam;
  
  private List<Param> paramList = new ArrayList<Param>();
  private List<String> orderList = new ArrayList<String>();

  public PagedQuery(Connection conn, PageParam pageParam, String selectSQL) {
    this.conn = conn;
    this.pageParam = pageParam;
    this.selectSQL = selectSQL;
  }

  public PagedQuery(Connection conn, PageParam pageParam, String selectSQL, String orders) {
    this.conn = conn;
    this.pageParam = pageParam;
    this.selectSQL = selectSQL;
    this.orders = orders;
  }

  public PagedQuery(Connection conn, PageParam pageParam, String selectSQL, String orders, String wheres, List<Object> values) {
    this.conn = conn;
    this.pageParam = pageParam;
    this.selectSQL = selectSQL;
    this.orders = orders;
    this.wheres = wheres;
    this.values = values;
  }
  
  /**
   * 执行附加SQL，如分组统计等处理
   * SQL中使用#QUERY_SQL# 表示真正的查询SQL
   * @param sql 包含查询SQL的附加SQL语句
   * @return
   * @throws Exception
   */
  public List<Map<String,Object>> queryOther(String otherSql)  throws Exception {
    StringBuilder where = new StringBuilder();
    if (wheres != null) {
      where.append(wheres);
    }
    for (Param p : paramList) {
      where.append(where.length() > 0 ? " and " : "").append(p.field);
    }
    String sql =  this.selectSQL + (where.length() > 0 ? "  where " + where : "");    
    sql = otherSql.replace("#QUERY_SQL#", sql);
 
    List<Object> setList = new ArrayList<Object>();
    if (values != null) {
      setList.addAll(values);
    }
    if (paramList != null) {
      setList.addAll(paramList);
    }
    if (setList.size()> 0) {
      return DbUtils.queryMapList(conn, sql, setList.toArray());
    }  else {
      return DbUtils.queryMapList(conn, sql);
    }   
  }
  
  public List<Map<String,Object>> queryToList(List<DbField> fieldList) throws Exception {
    ResultSet rs = null;
    try {
      rs = this.queryToResultSet();
      return DbUtils.getDataMapList(fieldList, rs);
    } catch(Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
    finally {
      if (rs != null) rs.close();
    }
  }
  
  public CachedRowSet query() throws Exception {
    ResultSet rs = null;
    try {
      rs = this.queryToResultSet();
      CachedRowSet crs = new CachedRowSetImpl();
      crs.populate(rs);
      return crs;
    } catch(Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
    finally {
      if (rs != null) rs.close();
    }
  }
  
  public ResultSet queryToResultSet() throws Exception {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    if (this.pageParam == null) {
      this.pageParam = new PageParam();
      this.pageParam.setHavePage(false);
    }
    try {
      // 求符合条件的记录数
      StringBuilder where = new StringBuilder();
      if (wheres != null) {
        where.append(wheres);
      }
      for (Param p : paramList) {
        where.append(where.length() > 0 ? " and " : "").append(p.field);
      }
      CachedRowSet crs;
      if (this.pageParam.isHavePage()) {
        //处理分页统计
        String sql = "select count(*) CNT from ( " + this.selectSQL + (where.length() > 0 ? "  where " + where +")" : ")") + " as TOTAL_MYSQL_ALIAS ";
        pstmt = conn.prepareStatement(sql.toUpperCase());
        int index = 1;
        if (values != null) {
          for (Object v : values) {
            if (v.getClass() == Integer.class) {
              pstmt.setInt(index++, (Integer)  v);
            } else if (v.getClass() == String.class) {
              pstmt.setString(index++, (String)v);
            } else if (v.getClass() == Double.class) {
              pstmt.setDouble(index++, (Double) v);
            } else if (v.getClass() == Long.class) {
              pstmt.setLong(index++, (Long) v);
            } else if (v.getClass() == BigDecimal.class) {
              pstmt.setBigDecimal(index++, (BigDecimal) v);
            }
          }
        }
        for (Param p : paramList) {
          if (p.value.getClass() == Integer.class) {
            pstmt.setInt(index++, (Integer)p.value);
          }
          else if (p.value.getClass() == String.class) {
            pstmt.setString(index++, (String)p.value);
          }
        }
        rs = pstmt.executeQuery();
        rs.next();
        this.pageParam.setRowCount(rs.getInt("CNT"));
        rs.close();
        pstmt.close();
        // 计算页数
        int pageCount = (this.pageParam.getRowCount() + this.pageParam.getPageSize() - 1) / this.pageParam.getPageSize();
        this.pageParam.setPageCount(pageCount);
        if (this.pageParam.getPageNumber() > pageCount) {
          this.pageParam.setPageNumber(pageCount);
        }
        if (this.pageParam.getPageNumber() < 1) {
          this.pageParam.setPageNumber(1);
        }
        int startRow = (this.pageParam.getPageNumber() - 1) * this.pageParam.getPageSize() + 1;
        int endRow = this.pageParam.getPageNumber() * this.pageParam.getPageSize();
        // 查询数据
        int selectPos = this.selectSQL.trim().toLowerCase().indexOf("select");
        if (selectPos != 0) {
          throw new BizException(BizException.DB,"查询语句不合法");
        }
        String querySQL = " select " + this.selectSQL.trim().substring(6);
        if ((orders == null || orders.length() <=0) && orderList.size() <= 0) {
          //where.append(where.length() > 0 ? " and rownum<=?" : "rownum<=?");
          sql = "select * from (" +querySQL+ ") limit ?,?";
        }
        else {
          StringBuilder order = new StringBuilder();
          if (orders != null) {
            order.append(orders);
          }
          for (String field : orderList) {
            if (order.length() > 0) {
              order.append(",");
            }
            order.append(field);
          }
          //sql = "select " + columns + " from (select " + columns + ",rownum RN from (select " + columns + " from " + tables + (where.length() > 0 ? " where " + where : "") + " order by " + order + ") where rownum<=?) where RN>=?";
          sql = "select * from (select t.* from ("+ selectSQL + (where.length() > 0 ? " where " + where : "") + " order by " + order + ") t) as TOTAL_MYSQL_ALIAS1 limit ?,?";
        }
        pstmt = conn.prepareStatement(sql.toUpperCase());
        index = 1;
        if (values != null) {
          for (Object v : values) {
            if (v.getClass() == Integer.class) {
              pstmt.setInt(index++, (Integer) v);
            } else if (v.getClass() == String.class) {
              pstmt.setString(index++, (String)v);
            } else if (v.getClass() == Long.class) {
              pstmt.setLong(index++, (Long) v);
            } else if (v.getClass() == BigDecimal.class) {
              pstmt.setBigDecimal(index++, (BigDecimal) v);
            }
          }
        }
        for (Param p : paramList) {
          if (p.value.getClass() == Integer.class) {
            pstmt.setInt(index++, (Integer)p.value);
          }
          else if (p.value.getClass() == String.class) {
            pstmt.setString(index++, (String)p.value);
          }else if (p.value.getClass() == Long.class) {
              pstmt.setLong(index++, (Long) p.value);
          }
        }
        pstmt.setInt(index++, startRow-1);
        pstmt.setInt(index++, this.pageParam.getPageSize());
        rs = pstmt.executeQuery();
      } else {
        //直接查询
        StringBuilder order = new StringBuilder();
        if (orders != null) {
          order.append(orders);
        }
        for (String field : orderList) {
          if (order.length() > 0) {
            order.append(",");
          }
          order.append(field);
        }
        String sql = selectSQL + (where.length() > 0 ? " where " + where : "") +  (order.toString().length() > 0 ? " order by " + order : "");
        pstmt = conn.prepareStatement(sql.toUpperCase());
        int index = 1;
        if (values != null) {
          for (Object v : values) {
            if (v.getClass() == Integer.class) {
              pstmt.setInt(index++, (Integer) v);
            } else if (v.getClass() == String.class) {
              pstmt.setString(index++, (String)v);
            } else if (v.getClass() == Long.class) {
              pstmt.setLong(index++, (Long) v);
            } else if (v.getClass() == BigDecimal.class) {
              pstmt.setBigDecimal(index++, (BigDecimal) v);
            }
          }
        }
        for (Param p : paramList) {
          if (p.value.getClass() == Integer.class) {
            pstmt.setInt(index++, (Integer)p.value);
          }
          else if (p.value.getClass() == String.class) {
            pstmt.setString(index++, (String)p.value);
          }
        }
        rs = pstmt.executeQuery();
      }
      return rs;
    } catch(Exception ex) {
      ex.printStackTrace();
      throw ex;
    }
    finally {
      //if (pstmt != null) pstmt.close();
      paramList.clear();
      orderList.clear();
    }
  }

  public PagedQuery setParam(String field, Object value) {
    paramList.add(new Param(field, value));
    return this;
  }

  public PagedQuery setOrder(String field) {
    orderList.add(field);
    return this;
  }

  class Param {
    public String field;
    public Object value;
    public Param(String field, Object value) {
      this.field = field;
      this.value = value;
    }
  }

  public PageParam getPageParam() {
    return pageParam;
  }

//  public static void main(String[] args) {
//    try {
//      Class.forName("oracle.jdbc.driver.OracleDriver");
//      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "iy", "mainkey");
//      PagedQuery pq = new PagedQuery(con, 10, 5, "COMMODITY", "COMMODITY_ID, COMMODITY_NAME");
//      pq.setParam("COMMODITY_ID>?", 1000);
//      pq.setParam("COMMODITY_ID<?", 10000);
//      pq.setOrder("PRICE desc");
//      CachedRowSet rs = pq.query();
//      while (rs.next()) {
//        System.out.println(rs.getString("COMMODITY_ID"));
//      }
//      con.close();
//    }
//    catch (SQLException e) {
//      e.printStackTrace();
////      System.out.println("SQLState:" + e.getSQLState());
////      System.out.println("Message:" + e.getMessage());
////      System.out.println("Vendor:" + e.getErrorCode());
//    }
//    catch (Exception e) {
//      e.printStackTrace();
//    }
//  }
}

