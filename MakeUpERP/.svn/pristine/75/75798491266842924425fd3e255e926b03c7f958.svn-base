package com.ga.click.util;

import java.util.*;
import java.sql.*;

public class PreparedUpdate {
  private Connection conn;
  private String sql;
  private List<String> updateSqlList = new ArrayList<String>();

  private List<Object> params = new ArrayList<Object>();
  private Map<String, Object> mapParms = new HashMap<String, Object>();

  public PreparedUpdate(Connection conn, String sql) {
    this.conn = conn;
    this.sql = sql;
  }
  public PreparedUpdate(Connection conn, List<String> updateSqlList) {
    this.conn = conn;
    this.updateSqlList = updateSqlList;
  }

  //单表编辑
  public int update() throws Exception {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(sql);
      int index = 1;
      List<String> parmsKey = new ArrayList<String>();
      if (!sql.contains("insert") && sql.contains("update")) {
        parmsKey = this.getUpdateParmsKey(sql);
      }
      if (sql.contains("insert") && !sql.contains("update")) {
        parmsKey = this.getInsertParmsKey(sql);
      }
      for (int i = 0; i < parmsKey.size(); i ++) {
        Object p = mapParms.get(parmsKey.get(i));
        if (p == null) {
          pstmt.setNull(index++, java.sql.Types.NULL);
        }
        else if (p.getClass() == Integer.class) {
          pstmt.setInt(index++, (Integer)p);
        }
        else if (p.getClass() == String.class) {
          pstmt.setString(index++, (String)p);
        }
      }
      return pstmt.executeUpdate();
    }
    finally {
      if (pstmt != null) pstmt.close();
      if (updateSqlList.size() <= 0)
        params.clear();
    }
  }
  
  //多表编辑
  public boolean updateTables() throws Exception {
    try {
      boolean isOk = true;
      for (String updateSql : updateSqlList) {
        this.sql = updateSql;
        if (this.update() != 1) {
          isOk = false;
          throw new Exception("此sql执行错误" + updateSql);
        }
      }
      return isOk;
    }
    finally {
      params.clear();
    }
  }

  public List<String> getInsertParmsKey(String insertSql) {
    String parmStr = insertSql.substring(insertSql.indexOf("(")+1, insertSql.indexOf(")"));
    String str = insertSql.substring(insertSql.lastIndexOf("(")+1, insertSql.lastIndexOf(")"));
    String[] strs = str.split(",");
    List<String> resultStrs = new ArrayList<String>();
    for (int i = 0; i < strs.length; i ++) {
      if (strs[i].equals("?")) {
        resultStrs.add(parmStr.split(",")[i].trim());
      }
    }
    return resultStrs;
  }
  
  public List<String> getUpdateParmsKey(String updateSql) {
    List<String> resultStrs = new ArrayList<String>();
    String[] ss = updateSql.split("set")[1].split("\\?");
    int length = ss.length;
    if (!updateSql.split("where")[1].contains("?")) {
      length = length - 1;
    }
    for (int i = 0; i < length; i++) {
      String str=ss[i].substring(ss[i].lastIndexOf(".") == -1 ? 0 : (ss[i].lastIndexOf(".")+1),ss[i].length());
      str = (str.contains(",") == true ? str.substring(str.lastIndexOf(",") + 1, str.lastIndexOf("=")) : str.substring(0, str.lastIndexOf("="))).trim();
      resultStrs.add(str.contains(" ") == true ? str.substring(str.lastIndexOf(" ")+ 1, str.length()) : str);
    }
    return resultStrs;
  }
  
  public PreparedUpdate setParam(Object value) {
    params.add(value);
    return this;
  }
  
  public PreparedUpdate setParam(String key, Object value) {
    mapParms.put(key, value);
    return this;
  }
}
