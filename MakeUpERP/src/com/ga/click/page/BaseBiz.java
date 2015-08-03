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
 * ʵ�ֵ������ɾ�Ĳ鴦��
 * @author Administrator
 *
 */
public class BaseBiz implements IBiz {
  protected String tableListName = "";//�����ѯ�ı�������ͼ��
  protected String selectSql = "";//��ѯ��SQL���
  protected String customSelectSql = ""; //�Զ���SQL���
  protected String whereSql = "";//��ѯ��SQL���  
  protected String orderStr = ""; //�����ֶ�(��,�ŷָ�,����order by �ؼ���)
  protected String selectParamStr = ""; //�Զ������(��,�ŷָ������)
  
  protected String tableEditName = ""; //�༭�ı���
  protected DbField pkField = null;
  protected Map<String,String> editFuncFieldMap = null; //�༭�ĺ����ֶ�
  protected String editSelectSql = ""; //��ѯ��ϸ��select���
  protected String editSelectParamStr = ""; //�Զ�������б�(��,�ŷָ�)
  protected String delMarkField = ""; //��ɾ�����
  protected Object delMarkValue = null;   //��ɾ��ֵ
  
  protected Map<String,Object> valueMap = null;
  protected PageParam pageParam = null;
  protected List<DbField> dbFieldList = null;
  protected JSONObject rowData = null;
  protected Map<String,Object> addParamValue = new HashMap<String,Object>();

  
  /**
   * �������
   */
  public void add() throws BizException {
    try {      
      //ָ���������Զ������ֶ����ý��й���
      if (!GaUtil.isNullStr(this.tableEditName)) {
        DbUtils.begin();
        DbUtils.add(this.tableEditName,this.valueMap,this.editFuncFieldMap);
        
        DbUtils.commit();
      } else {
        throw new BizException(BizException.SYSTEM,"δ�󶨱�");        
      }
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ִ���½�ʧ��",ex);
    }
    finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * �޸�����
   */
  public void save() throws BizException {
    try {      
      //ָ���������Զ������ֶ����ý��й���
      if (!GaUtil.isNullStr(this.tableEditName)) {
        DbUtils.begin();
        DbUtils.update(this.tableEditName,this.valueMap,this.editFuncFieldMap,this.pkField.getFieldCode());
        DbUtils.commit();
      } else {
        throw new BizException(BizException.SYSTEM,"δ�󶨱�");        
      }
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ִ�б���ʧ��",ex);
    }
    finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * ����ROWDATA��ID�ֶ�ɾ����
   */
  public void del() throws BizException {
    try {
      //�ж��ύ���������Ƿ���ID�ֶ����ú�ֵ
      if (this.pkField == null || rowData == null) {
        throw new BizException(BizException.COMMBIZ,"δѡ��Ҫɾ������");
      }
      String idStr = "";
      try {
        idStr = rowData.getString(this.pkField.getFieldCode());
      } catch(JSONException e) {        
      }
      if (GaUtil.isNullStr(idStr)) {
        throw new BizException(BizException.SYSTEM,"δ����������Ϣ");
      }
      if (this.dbFieldList == null || this.dbFieldList.size() == 0) {
        throw new BizException(BizException.SYSTEM,"δ���������ֶ�");
      }
      Object value = GaUtil.convertData(idStr, 
                      pkField.getDataType(), 
                      pkField.getFormatPattern());
      //ִ��ɾ��
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
      throw new BizException(BizException.SYSTEM,"ִ��ɾ��ʧ��",ex); 
    }
    finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * �Զ��������б��ѯ
   */
  public PageResult queryList(QueryPageData pageData) throws BizException{
    try {      
      if (this.dbFieldList == null) throw new BizException(BizException.COMMBIZ,"δ���������ֶ�"); 
      PagedQuery query;
      if (!GaUtil.isNullStr(this.tableListName) ) {
        query = AutoSQLUtil.createSqlForTable(this.tableListName,this.whereSql,this.orderStr,this.dbFieldList,pageData);
      } else if (!GaUtil.isNullStr(this.selectSql)) {
          query = AutoSQLUtil.createSql(this.selectSql,this.whereSql,this.orderStr,this.dbFieldList,pageData);
      } else if (!GaUtil.isNullStr(this.customSelectSql)) {
          query = AutoSQLUtil.createCustomSql(this.customSelectSql, this.orderStr, this.selectParamStr, this.dbFieldList, pageData);
      } else {
        throw new BizException(BizException.SYSTEM,"ҳ��δ�󶨲�ѯ");
      }
      //ִ�в�ѯ
      CachedRowSet rowset = query.query();
      PageResult result = new PageResult();
      result.setPageParam(query.getPageParam());
      result.setData(rowset);
      return result;
    }
    catch(BizException e) {
      //��ʾ����
      throw e;
    }
    catch(Exception ex) {
      //��ʾ����
      throw new BizException(BizException.COMMBIZ,"��ѯ�б�ʧ��"+ex.getMessage());
    }
  }
  
  /**
   * ��ѯ��ϸ��Ϣ
   */
  public Map<String,Object> queryDetail() throws BizException {
    try {
      //�ж��Ƿ���ID�ֶ����ú�ֵ
      if (this.pkField == null|| rowData == null) {
        throw new BizException(BizException.COMMBIZ,"δѡ��Ҫ�༭����");
      }
      String idStr = "";
      try {
        idStr = rowData.getString(this.pkField.getFieldCode());
      } catch(JSONException e) {        
      }
      if (GaUtil.isNullStr(idStr)) {
        throw new BizException(BizException.SYSTEM,"δ����������Ϣ");
      }
      if (this.dbFieldList == null || this.dbFieldList.size() == 0) {
        return null;
      }      
      StringBuffer sql = new StringBuffer();
      if (!GaUtil.isNullStr(this.tableEditName) ) {
        //ָ���˱����Ĳ�ѯ
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
        //to-do:ָ�������Ĳ�ѯչδʵ��
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
        throw new BizException(BizException.SYSTEM,"ҳ��δ�󶨲�ѯ");
      }
      //ִ�в�ѯ      
      Object value = GaUtil.convertData(idStr, 
          this.pkField.getDataType(), 
          this.pkField.getFormatPattern());
      CachedRowSet rs = DbUtils.query(sql.toString(),value);
      //��ȡ�������
      return DbUtils.getDataMap(this.dbFieldList,rs);
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"��ѯ��ϸ��Ϣʧ��",ex);
    }
  }
  
  
  /**
   * ��ѯ���ؼ���������(���а󶨵����ؼ���ѯ�ķ������谴�˽ṹ)
   * @return
   * @throws BizException
   */
  public TreeNode queryTreeDemo(String bindFields) throws BizException {
    try {
      
      //ִ�в�ѯ      
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
      throw new BizException(BizException.SYSTEM,"��ѯ��ϸ��Ϣʧ��",ex);
    }
  }
  
  /**
   * ����ֵMAP
   */
  public void setValueMap(Map<String,Object> valueMap){
    this.valueMap = valueMap;
  }
  
  public Map<String, Object> getValueMap() {
    return valueMap;
}

/**
   * �����ύ��������
   */
  public void setRowData(JSONObject jsonObj) {
    this.rowData = jsonObj;
  }
  
  
  /////////////////��������չ����/////////////
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
   * ��ʼ���ֶ��б�
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
  
  
  
  
 
  /****��չ����****/ 
//  public PageResult extendQueryList(String methodName,QueryPageData pageData) throws BizException {
//    throw new BizException(BizException.SYSTEM,"δʵ�ִ���չ����");
//  }
//  public Map<String,Object> extendQueryDetail(String methodName) throws BizException {
//    throw new BizException(BizException.SYSTEM,"δʵ�ִ���չ����");
//  }
//  
//  public void extendAction(String methodName) throws BizException {
//    throw new BizException(BizException.SYSTEM,"δʵ�ִ���չ����");
//  }
}
