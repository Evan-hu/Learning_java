package com.ga.erp.biz.system.log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.CustomFormat;

public class LogListView extends ListView {
  public LogListView(String viewID,ModelMap modelMap) {
    super(viewID,modelMap,GaConstant.EDITMODE_DISP);
  }
  
  @Override
  public void initField() throws Exception {
    // TODO Auto-generated method stub
    List<DbField> fields = new ArrayList<DbField>();
    DbField field = new DbField("OP_LOG_ID","OP_LOG_ID",GaConstant.DT_LONG);
    field.setColumnDisplay(false, 0, true);
    fields.add(field);
    
    field = new DbField("OP_ID","OP_ID",GaConstant.DT_STRING);
    field.setColumnDisplay(false, 0, true);
    fields.add(field);

    field = new DbField("执行业务","TYPE",GaConstant.DT_INT);    
    Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "管理员登录");
    field.setLookupData(new LookupDataSet(option));
    field.setColumnDisplay(true, 100,false);
    fields.add(field);
        
    field = new DbField("执行操作","ACTION",GaConstant.DT_STRING);
    field.setColumnDisplay(true, 100,false);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0,false);
    fields.add(field);  
    
    field = new DbField("操作描述","NOTE",GaConstant.DT_STRING);
    field.setColumnDisplay(true, 0,false);
    field.setMaxLength(100);
    fields.add(field);
    
    field = new DbField("执行人","TRUENAME",GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 0, true);
    field.setColumnDisplay(true, 100,false);
    fields.add(field);
    
    field = new DbField("执行时间","CREATE_TIME",GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm",false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, 0, true);
    field.setAliasCode("a.");
    field.setColumnDisplay(true, 100,false);
    fields.add(field);
    
    this.fieldList = fields;
  }
  
  /**
   * 绑定所有日志
   * @param pageData
   * @return
   */
  public void bindAllLog(QueryPageData pageData) {
    try {
      this.showPage(true);
      this.showQuery(true);
      String sql = " select A.*,B.TRUENAME from OP_LOG A,OP B";
      String where = "A.OPERATOR_TARGET = B.OP_ID ";
      this.bindData(BizUtil.queryListBySQL(sql, where,"A.create_time desc",this.fieldList, pageData,this.getUserACL()));
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw new BizException("查询业务日志异常");
    }
  }
  
  /**
   * 绑定指定业务的日志
   * @param bizCode
   * @param bizID
   */
  public void bindBizLog(Long bizCode,Long bizID) {
    try {
      this.showPage(false);
      this.showQuery(false);
      String sql = 
       "select A.*,B.TRUENAME from OP_LOG A,OP B";
      String where = "A.OPERATOR_TARGET = B.OP_ID ";
      where = where + " AND A.OPERATOR_TYPE= " + bizCode + 
                      " AND A.OPERATOR_TARGET like '%"+bizID+"%'";
      sql = sql + " where " + where + " order by A.CREATE_TIME desc";      
      this.bindData(DbUtils.queryMapList(this.fieldList,sql));
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw new BizException("查询业务日志异常");
    }
  }
}
