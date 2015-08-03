package com.company.shop.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbutils.DbUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.company.shop.exception.SysException;



/**
 * db辅助工具�?
 */
public class DbUtil {
  
  private static Logger logger = Logger.getLogger(DbUtil.class.getName());
  
  public static Connection getConnection(){
	  WebApplicationContext webApplicationContext =ContextLoader.getCurrentWebApplicationContext();  
	  SqlSessionFactory sqlSessionFactory = (SqlSessionFactory )webApplicationContext.getBean("sqlSessionFactory");  
	  SqlSession sqlSession = sqlSessionFactory.openSession();  
	  return sqlSession.getConnection();
  }
  
  /**
   * 关闭连接
   */
  public static void close(Connection conn) {
    try {
      DbUtils.close(conn);
    } catch (SQLException e) {
      throw new SysException(e);
    }
  }

  /**
   * 关闭ResultSet
   */
  public static void close(ResultSet rs) {
    try {
      DbUtils.close(rs);
    } catch (SQLException e) {
    	throw new SysException(e);
    }
  }

  /**
   * 关闭Statement
   */
  public static void close(Statement stmt) {
    try {
      DbUtils.close(stmt);
    } catch (SQLException e) {
    	throw new SysException(e);
    }
  }

  
  /**
   * 关闭连接,只记录错误日�?不抛出SQL异常.
   */
  public static void closeQuietly(Connection conn) {
    try {
      DbUtils.close(conn);
    } catch (SQLException e) {
      logger.error("", e);
    }
  }

  /**
   * 关闭ResultSet,只记录错误日�?不抛出SQL异常.
   */
  public static void closeQuietly(ResultSet rs) {
    try {
      DbUtils.close(rs);
    } catch (SQLException e) {
      logger.error("", e);
    }
  }

  /**
   * 关闭Statement,只记录错误日�?不抛出SQL异常.
   */
  public static void closeQuietly(Statement stmt) {
    try {
      DbUtils.close(stmt);
    } catch (SQLException e) {
      logger.error("", e);
    }
  }
  
  /**
   * 关闭连接,ResultSet,Statement,只记录错误日�?不抛出SQL异常.
   */
  public static void closeQuietly(Connection conn, Statement stmt, ResultSet rs) {
    try {
      DbUtils.close(rs);
      DbUtils.close(stmt);
      DbUtils.close(conn);
    } catch (SQLException e) {
      logger.error("", e);
    }
  }

  /**
   * 提交事务并关闭连�?
   */
  public static void commitAndClose(Connection conn) {
    try {
      DbUtils.commitAndClose(conn);
    } catch (SQLException e) {
    	throw new SysException(e);
    }
  }

  /**
   * 提交事务并关闭连�?只记录错误日�?不抛出SQL异常.
   */
  public static void commitAndCloseQuietly(Connection conn) {
    try {
      DbUtils.commitAndClose(conn);
    } catch (SQLException e) {
      logger.error("", e);
    }
  }

  /**
   * 回滚事务.
   */
  public static void rollback(Connection conn) {
    try {
      DbUtils.rollback(conn);
    } catch (SQLException e) {
    	throw new SysException(e);
    }
  }
  
  /**
   * 回滚事务并关闭连�?
   */
  public static void rollbackAndClose(Connection conn) {
    try {
      DbUtils.rollbackAndClose(conn);
    } catch (SQLException e) {
    	throw new SysException(e);
    }
  }

  /**
   * 回滚事务并关闭连�?只记录错误日�?不抛出SQL异常.
   */
  public static void rollbackAndCloseQuietly(Connection conn) {
    try {
      DbUtils.rollbackAndClose(conn);
    } catch (SQLException e) {
      logger.error("", e);
    }
  }
  /**
   * 回滚事务.如果回滚异常便将传入的msg�?��包装成SysException.
   */
  public static void rollbackAndWrap(Connection conn, String msg) {
    try {
      DbUtils.rollback(conn);
    } catch (Exception e) {
      throw new SysException(msg, e);
    }
  }
  
}
