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
  
  protected List<DbField> dbFieldList = null;// ��ѯ�����ֶ�List
  protected IBiz bizClass = null;
  protected List<ActionButton> toolbarList = new ArrayList<ActionButton>(); //ҳ�����
  
  /**
   * Ĭ�Ϲ�������
   * ʵ��dbfield��bizclass�İ󶨺ͳ�ʼ��
   */
  public BasePage() {
      try {
        //��ʼ������
        this.initNavInfo();
         // ��ʼ��    
        dbFieldList = this.initDbField();        
        // ��ʼ��ҵ������
        this.bizClass = this.initBizClass();
        this.bizClass.setDbFields(this.dbFieldList);
        this.bizClass.setRowData(this.rowDataObj);
      } catch (BizException ex) {
          throw ex;
      } catch (Exception ex) {
          throw new BizException(BizException.SYSTEM, "��ʼ��ʧ��", ex);
      }
  }

  /**
   * ��form�ؼ��ж�ȡ����dbField�����ֵMAP
   * @param form
   * @return
   */
  public Map<String,Object> getFormValueMap(Form form) {
    return this.getFormValueMap(form,this.dbFieldList);
  }
  
  /**
   * ��map�ṹ�е�ֵ����dbField������䵽���ؼ���
   * @param form
   * @param queryResultMap
   */
  public  void putFormValue(Form form,Map<String,Object> queryResultMap) {
    this.putFormValue(form, queryResultMap,this.dbFieldList);
  }
  
  /**
   * ���ݲ�ѯ����ȡ��ѯ���ݶ���
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
   * ��ҵ������
   * 
   * @return
   */
  public abstract IBiz initBizClass();
  
  /**
   * ��ʼ�������ֶζ���
   * @return
   */
  public abstract List<DbField> initDbField();

}
