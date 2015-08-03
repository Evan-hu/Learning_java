package com.ga.click.page;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageParam;
import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;
import com.ga.click.util.PagedQuery;

/**
 * 本类是用于处理自动SQL构建工具，实现以下功能.
 * 1.通过DBFiled定义和提交的QueryFormData自动组合SQL语句和条件字段
 *   注意自动组合的条件字段都是统一附加在where后面，如遇子查询有条件则需自己手动处理
 * @author Administrator
 */
public class AutoSQLUtil {
  
  /**
   * 自动创建查询对象
   * @param tableName 表名
   * @param orderString 排序子语句
   * @param fieldsList 字段定义列表
   * @param pageData 查询页面数据
   * @return
   */
  public static PagedQuery createSqlForTable(String tableName,String whereString,String orderString,List<DbField> fieldsList,QueryPageData pageData) {    
    if (GaUtil.isNullStr(tableName) || fieldsList == null) {
      throw new BizException(BizException.SYSTEM,"无法自动解析SQL");
    } 
    StringBuffer selectSb = new StringBuffer();
    selectSb.append(" select ");
    //遍历select字段
    for(DbField field : fieldsList) {
      //if (field.isDisplay()) {
        selectSb.append(field.getFieldCode().toUpperCase());
        selectSb.append(",");
      //}
    }
    String selectSql = selectSb.toString();
    if (selectSql.endsWith(",")) {
      selectSql = selectSql.substring(0,selectSql.length() - 1);
    }
    selectSql = selectSql + " from " + tableName.toUpperCase();
    return createSql(selectSql,whereString,orderString,fieldsList,pageData);
  }
  
  /**
   * 自动创建查询(自定义sql,条件全部自动组装)
   * @param selectSql
   * @param orderString
   * @param fieldsList
   * @param pageData
   * @return
   */
  public static PagedQuery createSql(String selectSql,String whereString,String orderString,List<DbField> fieldsList,QueryPageData pageData) {
    if (GaUtil.isNullStr(selectSql) || fieldsList == null) {
      throw new BizException(BizException.SYSTEM,"无法自动解析SQL");
    } 
    //组合where语句
    PagedQuery pagedQuery = null;
    StringBuffer whereSb = new StringBuffer();
    List<Object> whereParams = new ArrayList<Object>();
    if (pageData == null) { 
      //表示无分页,无参数
      if (GaUtil.isNullStr(whereString)) {
        pagedQuery = new PagedQuery(DbUtils.getConnection(),null,selectSql,orderString);        
      } else {
        pagedQuery = new PagedQuery(DbUtils.getConnection(),null,selectSql + " where " + whereString,orderString);
      }
      
    } else {
      //表示有查询信息,组合where子句
      whereSb.append(whereString);
      boolean haveExp = false;
      if (!GaUtil.isNullStr(whereString)) {
        haveExp = true;
      }
      for (DbField field : fieldsList) {
        if (field.isQueryParam()) {
          //获取查询表达式
          String queryExp = pageData.GetQueryExpression(field.getFieldCode());
          List<String> queryValue = pageData.GetQueryValues(field.getFieldCode());
          if (!GaUtil.isNullStr(queryExp) && queryValue != null && queryValue.size() > 0) {            
            if (haveExp) {
              whereSb.append(" and ");
            }
            whereSb.append(queryExp);
            haveExp = true;
            //获取查询值,转为相应对象  
            for(String value : queryValue) {
              if (field.getQueryOpera() == GaConstant.QUERY_LIKE) {
                whereParams.add("%" + GaUtil.convertData(value, field.getDataType(),field.getFormatPattern()) + "%"); 
              } else {
                whereParams.add(GaUtil.convertData(value, field.getDataType(),field.getFormatPattern()));
              }
              //whereParams.add(e)
            }
          }
        }
      }
      pagedQuery = new PagedQuery(DbUtils.getConnection(),pageData.GetPageParam(),selectSql,orderString,whereSb.toString(),whereParams);
    }
    return pagedQuery;
  }
  
  /**
   * 自动创建查询(自定义完整sql,条件字段手动指定,在写条件表达式用#1#来表示条件,从1开始)
   * @param selectSql
   * @param orderString
   * @param paramName
   * @param fieldsList
   * @param pageData
   */
  public static PagedQuery createCustomSql(String allSql,String orderString,String paramsName,List<DbField> fieldsList,QueryPageData pageData) {
    if (GaUtil.isNullStr(allSql) || fieldsList == null) {
      throw new BizException(BizException.SYSTEM,"无法自动解析SQL");
    } 
    //组合where语句
    PagedQuery pagedQuery = null;
    List<Object> whereParams = new ArrayList<Object>();
    if (pageData == null) { 
      //表示无分页,无参数
      pagedQuery = new PagedQuery(DbUtils.getConnection(),null,allSql);
    } else {
      //表示有查询信息,组合where子句
      boolean haveExp = false;
      String[] paramsList = paramsName.split(",");
      int paramIndex = 0;
      for (String params : paramsList) {
        paramIndex ++;
        for(DbField field :  fieldsList) {
          if (field.getFieldCode().equals(params)) {
            //获取查询表达式
            String queryExp = pageData.GetQueryExpression(field.getFieldCode());
            List<String> queryValue = pageData.GetQueryValues(field.getFieldCode());
            if (!GaUtil.isNullStr(queryExp) && queryValue != null && queryValue.size() > 0) {  
              for(String value : queryValue) {
                if (field.getQueryOpera() == GaConstant.QUERY_LIKE) {
                  whereParams.add("%" + GaUtil.convertData(value, field.getDataType(),field.getFormatPattern()) + "%"); 
                } else {
                  whereParams.add(GaUtil.convertData(value, field.getDataType(),field.getFormatPattern()));
                }
              }
              allSql = allSql.replace("@"+paramIndex, queryExp); 
              break;
            } else {
              //没有输入,取消条件
              allSql = allSql.replace("@"+paramIndex, "1=1"); 
              break;
            }
          }
        }
      }
      pagedQuery = new PagedQuery(DbUtils.getConnection(),pageData.GetPageParam(),allSql,orderString,"",whereParams);
    }
    return pagedQuery;
  }
  
  /**
   * 直接将将SQL语句封装为分页查询对象
   * @param fieldsList 字段列表
   * @param pageInfo 分页信息
   * @param sql 查询SQL
   * @param orderStr 排序语句
   * @param param 查询参数
   * @return
   */
  public static PagedQuery createSqlNoAuto(List<DbField> fieldsList,PageParam pageInfo,String sql,String orderStr,Object...param) {
    if (GaUtil.isNullStr(sql) || fieldsList == null) {
      throw new BizException(BizException.SYSTEM,"无法自动解析SQL");
    } 
    PagedQuery pagedQuery = new PagedQuery(DbUtils.getConnection(),pageInfo,sql,orderStr,"",Arrays.asList(param));
    return pagedQuery;
  }
  
  /**
   * 直接将将SQL语句封装为分页查询对象
   * @param fieldsList
   * @param pageInfo
   * @param sql
   * @param whereStr
   * @param orderStr
   * @param param
   * @return
   */
  public static PagedQuery createSqlNoAuto(List<DbField> fieldsList,PageParam pageInfo,String sql,String whereStr,String orderStr,Object...param) {
    if (GaUtil.isNullStr(sql) || fieldsList == null) {
      throw new BizException(BizException.SYSTEM,"无法自动解析SQL");
    } 
    PagedQuery pagedQuery = new PagedQuery(DbUtils.getConnection(),pageInfo,sql,orderStr,whereStr,Arrays.asList(param));
    return pagedQuery;
  }
}
