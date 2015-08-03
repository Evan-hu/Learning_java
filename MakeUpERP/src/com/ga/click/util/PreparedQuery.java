package com.ga.click.util;

import java.util.*;

import java.sql.*;
import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;

public class PreparedQuery {
  private Connection conn;
  private String sql;
  private int limit = 100;

  private List<Param> params = new ArrayList<Param>();
  private List<String> orders = new ArrayList<String>();

  public PreparedQuery(Connection conn, String sql) {
    this.conn = conn;
    this.sql = sql;
  }

  public CachedRowSet query() throws Exception {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try {
      StringBuilder where = new StringBuilder();
      for (Param p : params) {
        where.append(where.length() > 0 ? " and " : " where ").append(p.field);
      }
      if (orders.size() <= 0) {
        where.append(where.length() > 0 ? " and rownum<=?" : " where rownum<=?");
        pstmt = conn.prepareStatement(sql + where);
      }
      else {
        StringBuilder order = new StringBuilder(" order by ");
        for (String field : orders) {
          if (order.length() > 0) {
            order.append(",");
          }
          order.append(field);
        }
        pstmt = conn.prepareStatement("select * from (" + sql + where + order + ") where rownum<=?");
      }
      int index = 1;
      for (Param p : params) {
        if (p.value.getClass() == Integer.class) {
          pstmt.setInt(index++, (Integer)p.value);
        }
        else if (p.value.getClass() == String.class) {
          pstmt.setString(index++, (String)p.value);
        }
      }
      pstmt.setInt(index++, limit);
      rs = pstmt.executeQuery();
      CachedRowSet crs = new CachedRowSetImpl();
      crs.populate(rs);
      return crs;
    }
    finally {
      if (rs != null) rs.close();
      if (pstmt != null) pstmt.close();
      params.clear();
    }
  }

  public PreparedQuery setParam(String field, Object value) {
    params.add(new Param(field, value));
    return this;
  }

  public PreparedQuery setOrder(String field) {
    orders.add(field);
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

}
