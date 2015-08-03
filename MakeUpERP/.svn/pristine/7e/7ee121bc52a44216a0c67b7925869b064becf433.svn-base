package com.ga.click.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.click.control.Form;
import com.ga.click.control.DbField;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;

public abstract class BasePage  extends GaPage {
  
  protected List<DbField> dbFieldList = null;// 查询参数字段List
  protected IBiz bizClass = null;
  protected List<ActionButton> toolbarList = new ArrayList<ActionButton>(); //页面操作
  
  /**
   * 默认构建函数
   * 实现dbfield和bizclass的绑定和初始化
   */
  public BasePage() {
      try {
        //初始化导航
        this.initNavInfo();
         // 初始化    
        dbFieldList = this.initDbField();        
        // 初始化业务处理类
        this.bizClass = this.initBizClass();
        this.bizClass.setDbFields(this.dbFieldList);
        this.bizClass.setRowData(this.rowDataObj);
      } catch (BizException ex) {
          throw ex;
      } catch (Exception ex) {
          throw new BizException(BizException.SYSTEM, "初始化失败", ex);
      }
  }

  /**
   * 从form控件中读取符合dbField定义的值MAP
   * @param form
   * @return
   */
  public Map<String,Object> getFormValueMap(Form form) {
    return this.getFormValueMap(form,this.dbFieldList);
  }
  
  /**
   * 将map结构中的值根据dbField设置填充到表单控件中
   * @param form
   * @param queryResultMap
   */
  public  void putFormValue(Form form,Map<String,Object> queryResultMap) {
    this.putFormValue(form, queryResultMap,this.dbFieldList);
  }
  
  /**
   * 根据查询表单获取查询数据对象
   * @param queryForm
   * @return
   */
  public QueryPageData getQueryData(Form queryForm) {
    return this.getQueryData(queryForm,this.dbFieldList);
  }
 
  public Layout initLayout() {
    return null;
  }
  
  /**
   * 绑定业务处理类
   * 
   * @return
   */
  public abstract IBiz initBizClass();
  
  /**
   * 初始化数据字段定义
   * @return
   */
  public abstract List<DbField> initDbField();

}
