package com.ga.click.page;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import org.json.JSONException;
import org.json.JSONObject;

import com.ga.click.control.DbField;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageParam;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;
import com.ga.click.util.PagedQuery;

/**
 * 实现单表的增删改查处理
 * @author Administrator
 *
 */
public class BaseBiz implements IBiz {
  protected String tableListName = "";//单表查询的表名或视图名
  protected String selectSql = "";//查询的SQL语句
  protected String customSelectSql = ""; //自定义SQL语句
  protected String whereSql = "";//查询的SQL语句  
  protected String orderStr = ""; //排序字段(以,号分隔,不加order by 关键字)
  protected String selectParamStr = ""; //自定义参数(以,号分隔多参数)
  
  protected String tableEditName = ""; //编辑的表名
  protected DbField pkField = null;
  protected Map<String,String> editFuncFieldMap = null; //编辑的函数字段
  protected String editSelectSql = ""; //查询明细的select语句
  protected String editSelectParamStr = ""; //自定义参数列表(以,号分隔)
  protected String delMarkField = ""; //假删除标记
  protected Object delMarkValue = null;   //假删除值
  
  protected Map<String,Object> valueMap = null;
  protected PageParam pageParam = null;
  protected List<DbField> dbFieldList = null;
  protected JSONObject rowData = null;
  protected Map<String,Object> addParamValue = new HashMap<String,Object>();

  
  /**
   * 添加数据
   */
  public void add() throws BizException {
    try {      
      //指定表名则自动根据字段设置进行构建
      if (!GaUtil.isNullStr(this.tableEditName)) {
        DbUtils.begin();
        DbUtils.add(this.tableEditName,this.valueMap,this.editFuncFieldMap);
        
        DbUtils.commit();
      } else {
        throw new BizException(BizException.SYSTEM,"未绑定表");        
      }
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"执行新建失败",ex);
    }
    finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * 修改数据
   */
  public void save() throws BizException {
    try {      
      //指定表名则自动根据字段设置进行构建
      if (!GaUtil.isNullStr(this.tableEditName)) {
        DbUtils.begin();
        DbUtils.update(this.tableEditName,this.valueMap,this.editFuncFieldMap,this.pkField.getFieldCode());
        DbUtils.commit();
      } else {
        throw new BizException(BizException.SYSTEM,"未绑定表");        
      }
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"执行保存失败",ex);
    }
    finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * 根据ROWDATA的ID字段删除表
   */
  public void del() throws BizException {
    try {
      //判断提交的行数据是否有ID字段设置和值
      if (this.pkField == null || rowData == null) {
        throw new BizException(BizException.COMMBIZ,"未选中要删除的行");
      }
      String idStr = "";
      try {
        idStr = rowData.getString(this.pkField.getFieldCode());
      } catch(JSONException e) {        
      }
      if (GaUtil.isNullStr(idStr)) {
        throw new BizException(BizException.SYSTEM,"未设置主键信息");
      }
      if (this.dbFieldList == null || this.dbFieldList.size() == 0) {
        throw new BizException(BizException.SYSTEM,"未定义数据字段");
      }
      Object value = GaUtil.convertData(idStr, 
                      pkField.getDataType(), 
                      pkField.getFormatPattern());
      //执行删除
      DbUtils.begin();
      if (GaUtil.isNullStr(this.delMarkField)) {
        DbUtils.del(this.tableEditName, this.pkField.getFieldCode(), value);
      } else {
        Map<String,Object> vMap = new HashMap<String,Object>();
        vMap.put(this.delMarkField,this.delMarkValue);
        vMap.put(this.pkField.getFieldCode(),value);
        DbUtils.update(this.tableEditName, vMap, null, this.pkField.getFieldCode());
      }      
      DbUtils.commit();
    }
    catch(BizException e) {  
      throw e;
    }
    catch(Exception ex) {    
      throw new BizException(BizException.SYSTEM,"执行删除失败",ex); 
    }
    finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * 自动构建的列表查询
   */
  public PageResult queryList(QueryPageData pageData) throws BizException{
    try {      
      if (this.dbFieldList == null) throw new BizException(BizException.COMMBIZ,"未定义数据字段"); 
      PagedQuery query;
      if (!GaUtil.isNullStr(this.tableListName) ) {
        query = AutoSQLUtil.createSqlForTable(this.tableListName,this.whereSql,this.orderStr,this.dbFieldList,pageData);
      } else if (!GaUtil.isNullStr(this.selectSql)) {
          query = AutoSQLUtil.createSql(this.selectSql,this.whereSql,this.orderStr,this.dbFieldList,pageData);
      } else if (!GaUtil.isNullStr(this.customSelectSql)) {
          query = AutoSQLUtil.createCustomSql(this.customSelectSql, this.orderStr, this.selectParamStr, this.dbFieldList, pageData);
      } else {
        throw new BizException(BizException.SYSTEM,"页面未绑定查询");
      }
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
  
  /**
   * 查询明细信息
   */
  public Map<String,Object> queryDetail() throws BizException {
    try {
      //判断是否有ID字段设置和值
      if (this.pkField == null|| rowData == null) {
        throw new BizException(BizException.COMMBIZ,"未选中要编辑的行");
      }
      String idStr = "";
      try {
        idStr = rowData.getString(this.pkField.getFieldCode());
      } catch(JSONException e) {        
      }
      if (GaUtil.isNullStr(idStr)) {
        throw new BizException(BizException.SYSTEM,"未设置主键信息");
      }
      if (this.dbFieldList == null || this.dbFieldList.size() == 0) {
        return null;
      }      
      StringBuffer sql = new StringBuffer();
      if (!GaUtil.isNullStr(this.tableEditName) ) {
        //指定了表名的查询
        sql.append("select ");
        boolean haveField = false;
        for(DbField field :this.dbFieldList) {
          if (haveField) {
            sql.append(",");
          }
          sql.append(field.getFieldCode().toUpperCase());        
          haveField = true;
        }
        sql.append(" from ");
        sql.append(this.tableEditName.toUpperCase());
        sql.append(" where ");
        sql.append(this.pkField.getFieldCode().toUpperCase());
        sql.append("= ? ");        
      } else if (!GaUtil.isNullStr(this.selectSql)) {
        //to-do:指定了语句的查询展未实现
        sql.append(this.selectSql);
        if (!GaUtil.isNullStr(this.whereSql)) {
          sql.append(" where ").append(this.whereSql);
          sql.append(" and ");
          sql.append(this.pkField.getFieldCode().toUpperCase());
          sql.append("= ? ");     
        } else {
          sql.append(" where ");
          sql.append(this.pkField.getFieldCode().toUpperCase());
          sql.append("= ? "); 
        }
      } else {
        throw new BizException(BizException.SYSTEM,"页面未绑定查询");
      }
      //执行查询      
      Object value = GaUtil.convertData(idStr, 
          this.pkField.getDataType(), 
          this.pkField.getFormatPattern());
      CachedRowSet rs = DbUtils.query(sql.toString(),value);
      //获取结果数据
      return DbUtils.getDataMap(this.dbFieldList,rs);
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"查询明细信息失败",ex);
    }
  }
  
  
  /**
   * 查询树控件方法样例(所有绑定到树控件查询的方法都需按此结构)
   * @return
   * @throws BizException
   */
  public TreeNode queryTreeDemo(String bindFields) throws BizException {
    try {
      
      //执行查询      
      String sql = "SELECT * FROM SYS_CODE";
      ResultSet rs = DbUtils.query(sql);
      TreeNode node = ClickUtil.getTreeNode(rs,"SYS_CODE_ID","P_CODE", 
          "SYS_CODE_NAME", "", 
          bindFields,1L);
      return node;
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"查询明细信息失败",ex);
    }
  }
  
  /**
   * 设置值MAP
   */
  public void setValueMap(Map<String,Object> valueMap){
    this.valueMap = valueMap;
  }
  
  public Map<String, Object> getValueMap() {
    return valueMap;
}

/**
   * 设置提交的行数据
   */
  public void setRowData(JSONObject jsonObj) {
    this.rowData = jsonObj;
  }
  
  
  /////////////////单表处理扩展方法/////////////
  /**
   * 
   * @param table
   * @param whereSql
   * @param orderStr
   */
  public void setTableQuery(String table,String whereSql,String orderStr) {
    this.tableListName = table;
    this.whereSql = whereSql;
    this.orderStr = orderStr;    
  }
  
  /**
   * 
   * @param sql
   * @param whereSql
   * @param orderStr
   */
  public void setListQuery(String sql,String whereSql,String orderStr) {
    this.selectSql = sql;
    this.whereSql = whereSql;
    this.orderStr = orderStr;
  }
  
  /**
   * 
   * @param customSql
   * @param paramCodeList
   * @param orderStr
   */
  public void setCustomListQuery(String customSql,String paramCodeList,String orderStr) {
    this.customSelectSql = customSql;
    this.selectParamStr = paramCodeList;
    this.orderStr = orderStr;
  }
  
  /**
   * 
   * @param tableName
   * @param idFieldCode
   * @param funcFieldMap
   */
  public void SetTableEdit(String tableName,Map<String,String> funcFieldMap) {
    this.tableEditName = tableName;
      this.editFuncFieldMap = funcFieldMap;
  }
  
  public void SetTableDelete(String tableName,String delMarkField,Object delMarkValue) {
    this.tableEditName = tableName;
    this.delMarkField = delMarkField;
    this.delMarkValue = delMarkValue;
  }
  /**
   * 
   * @param sql
   * @param paramString
   */
  public void SetDetailQuery(String sql,String paramString) {
    this.editSelectSql = sql;
    this.editSelectParamStr = paramString;
  }
  /**
   * 初始化字段列表
   * @return
   * @throws BizException
   */
  public void setDbFields(List<DbField> dbList){    
    this.dbFieldList = dbList;
    for(DbField field:dbList) {
      if (field.isPK()) {
        this.pkField = field;
        break;
      }
    }
  }

  @Override
  public void addParam(String paramName, Object value) {
    // TODO Auto-generated method stub
    this.addParamValue.put(paramName, value);
  }

  public JSONObject getRowData() {
	return rowData;
  }
  
  
  
  
 
  /****扩展方法****/ 
//  public PageResult extendQueryList(String methodName,QueryPageData pageData) throws BizException {
//    throw new BizException(BizException.SYSTEM,"未实现此扩展方法");
//  }
//  public Map<String,Object> extendQueryDetail(String methodName) throws BizException {
//    throw new BizException(BizException.SYSTEM,"未实现此扩展方法");
//  }
//  
//  public void extendAction(String methodName) throws BizException {
//    throw new BizException(BizException.SYSTEM,"未实现此扩展方法");
//  }
}
