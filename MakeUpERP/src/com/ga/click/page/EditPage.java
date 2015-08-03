package com.ga.click.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.click.ActionResult;
import org.apache.click.util.Bindable;
import org.json.JSONObject;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.form.DataForm;
import com.ga.click.exception.BizException;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

public abstract class EditPage extends BasePage{  
  
  @Bindable 
  protected DataForm editForm = null;
  
  protected Map<String,Object> fieldValues = new HashMap<String,Object>(); //��ϸ����
  protected int editMode = GaConstant.EDITMODE_NEW;
  
  
  public EditPage() {
    super(); 
  }
  
  /**
   * ��ʼ������
   */
  public void initControl() {
    try {
      //����ؼ�
      String tmpV = getContext().getRequest().getParameter(GaConstant.FIXPARAM_EDITMODE);
      if(!GaUtil.isNullStr(tmpV)){
          this.editMode = Integer.parseInt(tmpV);
      }
      this.editForm = new DataForm(this.getDataFormName(),this.dbFieldList,this.editMode,null);
      this.addModel("editForm", this.editForm);
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"�༭ҳ���ʼ��ʧ��",ex);
    }
 }
  
  @Override
  public void initForm() {
    // TODO Auto-generated method stub
    //������
    this.bindForm(this.editForm);
    this.fieldValues = this.getFormValueMap(this.editForm);
    this.bizClass.setValueMap(this.fieldValues);
    //�����̶�����
    String paramV =  editForm.getFieldValue(GaConstant.FIXPARAM_EDITMODE);
    if (!GaUtil.isNullStr(paramV)) {
      if (paramV.equals(String.valueOf(GaConstant.EDITMODE_NEW))) {
        this.editMode = GaConstant.EDITMODE_NEW;
      } else if (paramV.equals(String.valueOf(GaConstant.EDITMODE_EDIT)))  {
        this.editMode = GaConstant.EDITMODE_EDIT;
      } else if (paramV.equals(String.valueOf(GaConstant.EDITMODE_DISP)))  {
        this.editMode = GaConstant.EDITMODE_DISP;
      }
    }
  }
    
  /**
   * ������ȡ,��ȡPOST����
   */
  public void onPost() {
    //��ȡ����,������PUT�����ݶ�����
    onGet();
  }

  /**
   * ִ�����ݱ���ķ���
   * 
   * @param rowData
   *            ��ǰѡ��������
   * @return
   */
   public ActionResult onSave() {
      try {
          this.onInit();
          if (this.editMode == GaConstant.EDITMODE_NEW) {
            this.bizClass.add();
          } else if (this.editMode == GaConstant.EDITMODE_EDIT) {
            this.bizClass.save();
          }
          JSONObject obj = new JSONObject();
          obj.put("actionid", this.actionID);
          obj.put("success", "1");
          obj.put("message", "����ɹ�");
         return new ActionResult(obj.toString(),ActionResult.JSON);
      } catch (BizException e) {
          return ClickUtil.createErrorResult(this.actionID,e);
      } catch (Exception ex) {
          return ClickUtil.createErrorResult(this.actionID,ex);
      }
  }
   
   

  @Override
  public String getTemplate() {
    // TODO Auto-generated method stub
    return "/clicktemplate/edit_page.htm";
  }
  
  /**
   * ���ݱ༭ģʽ���ñ�����
   */
  @Override
  public void onRender() {
    try {
      if (this.editMode != GaConstant.EDITMODE_NEW) {
        Map<String,Object> queryResultMap = this.bizClass.queryDetail();
        this.putFormValue(this.editForm, queryResultMap);
      } else {
        //����Ĭ��ֵ
        this.putFormValue(this.editForm,null);
      }
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"����ת��ʧ��",ex);
    }    
  }
  
  /**
   * ��ʼ������ť
   */
  public void initAction() {
    //���ù�����
    ActionButton button = new ActionButton(this.getClass(),"save","����",
        getContext().getRequest().getRequestURI(),null);
    button.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV, this.preNavInfoStr);
    button.setFormButton(this.editForm.getName(),"onSave");
    this.editForm.regFormButton(button);
  }
  
  public String getDataFormName() {
    return "dataForm";  
  }

  /**
   * ��ʼ�������ֶζ���
   * @return
   */
  public abstract List<DbField> initDbField();
  

  /**
   * ��ҵ������
   * @return
   */
  public abstract IBiz initBizClass();
}
