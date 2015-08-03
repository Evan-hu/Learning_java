package com.company.shop.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.company.shop.exception.SysException;



/**
 * 数据访问对象.提供静�?的数据访问方�?
 */
public class BaseDAO {

  /**
   * 查询单条记录,将ResultSet中第�?��的数据映射成Map.Map的key和value对应结果集的字段名以及�?.
   * @param sql 查询语句
   * @param params 查询条件
   * @return 映射成的map.key为字段的名字,value�?
   * @throws DbUtilException 
   */
  @SuppressWarnings("unchecked")
  public static Map<String, Object> queryMap(Connection conn, String sql, Object... params) {
    ResultSetHandler rsHandler = new MapHandler();
    QueryRunner queryRunner = new QueryRunner();
    Map<String, Object> result = null;
    try {
      result = (Map<String, Object>)queryRunner.query(conn, sql, rsHandler, params);
    } catch (SQLException e) {
      throw new SysException(e);
    }
    return result;
  }
  
  /**
   * 查询多条记录,将ResultSet中每�?��的数据映射成Map并装入List.
   * Map的key和value对应结果集的字段名以及�?.
   * @param sql 查询语句
   * @param params 查询条件
   * @return List<Map<字段�? 字段�?>
   * @throws DbUtilException
   */
  @SuppressWarnings("unchecked")
  public static List<Map<String, Object>> queryMapList(Connection conn, String sql, Object... params) {
    ResultSetHandler  rsHandler = new MapListHandler();
    QueryRunner queryRunner = new QueryRunner();
    List<Map<String, Object>> result = null;
    try {
      result = (List<Map<String, Object>>)queryRunner.query(conn, sql, rsHandler, params);
    } catch (SQLException e) {
    	throw new SysException(e);
    }
    return result;
  }
  
  
  /**
   * 查询多条记录,将ResultSet中每�?��的数据映射成Map然后以某�?��段进行分�?
   * Map的key和value对应结果集的字段名以及�?.
   * @param sql 查询语句
   * @param groupByField 分组字段
   * @param params 查询条件
   * @return Map<分组字段�? (分组字段值对应的�?���?List<Map<字段�? 字段�?>>
   * @throws DbUtilException
   */
  public static Map<Object, List<Map<String, Object>>> queryGroupMapList(Connection conn, String sql, String groupByField, Object... params) {
    return convertToGroupMapList(queryMapList(conn, sql, params), groupByField);
  }
  
  /**
   * 将MapList转换成分组的MapList.
   * @param srcMapList �?��转换的ListMap
   * @param groupByField 分组字段
   * @return Map<分组字段�? (分组字段值对应的�?���?List<Map<字段�? 字段�?>>
   */
  public static Map<Object, List<Map<String, Object>>> convertToGroupMapList(List<Map<String, Object>> srcMapList, String groupByField) {
    Map<Object, List<Map<String, Object>>> destGroupMapList = null;
    
    if (srcMapList != null) {
      
      destGroupMapList = new HashMap<Object, List<Map<String, Object>>>();
      
      for (Map<String, Object> map : srcMapList) {
        Object groupByObject = map.get(groupByField);
        
        if (groupByObject != null) {
          List<Map<String, Object>> mapList = destGroupMapList.get(groupByObject);
          if (mapList == null) {
            mapList = new ArrayList<Map<String,Object>>();
            destGroupMapList.put(groupByObject, mapList);
          }
          mapList.add(map);
        }
        
      }
    }
    return destGroupMapList;
  }
  
  /**
   * 将分组的MapList转换为MapList.
   * @param srcGroupMapList �?��转换的分组的MapList
   * @return List<Map<字段�? 字段�?>
   */
  public static List<Map<String, Object>> convertToMapList(Map<Object, List<Map<String, Object>>> srcGroupMapList) {
    List<Map<String, Object>> destMapList = null;
    
    if (srcGroupMapList != null) {
      destMapList = new ArrayList<Map<String,Object>>();
      for (Map.Entry<Object, List<Map<String, Object>>> entry : srcGroupMapList.entrySet()) {
        destMapList.addAll(entry.getValue());
      }
    }
    return destMapList;
  }
  

  
  /**
   * 执行INSERT, UPDATE, �?DELETE. �?��提供连接. 事务�?��手动处理.
   * @param conn
   * @param sql 执行的sql
   * @param params sql的参�?
   * @return 更新的行�?
   * @throws DbUtilException
   */
  public static int update(Connection conn, String sql, Object... params) {
    QueryRunner queryRunner = new QueryRunner();
    try {
      return queryRunner.update(conn, sql, params);
    } catch (SQLException e) {
      throw new SysException(e);
    }
  }
 
}
