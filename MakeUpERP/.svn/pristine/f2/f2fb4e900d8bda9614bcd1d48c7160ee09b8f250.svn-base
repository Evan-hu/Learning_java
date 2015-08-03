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
  
  protected Map<String,Object> fieldValues = new HashMap<String,Object>(); //明细数据
  protected int editMode = GaConstant.EDITMODE_NEW;
  
  
  public EditPage() {
    super(); 
  }
  
  /**
   * 初始化处理
   */
  public void initControl() {
    try {
      //处理控件
      String tmpV = getContext().getRequest().getParameter(GaConstant.FIXPARAM_EDITMODE);
      if(!GaUtil.isNullStr(tmpV)){
          this.editMode = Integer.parseInt(tmpV);
      }
      this.editForm = new DataForm(this.getDataFormName(),this.dbFieldList,this.editMode,null);
      this.addModel("editForm", this.editForm);
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"编辑页面初始化失败",ex);
    }
 }
  
  @Override
  public void initForm() {
    // TODO Auto-generated method stub
    //绑定数据
    this.bindForm(this.editForm);
    this.fieldValues = this.getFormValueMap(this.editForm);
    this.bizClass.setValueMap(this.fieldValues);
    //解析固定参数
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
   * 参数获取,获取POST参数
   */
  public void onPost() {
    //获取参数,将参数PUT到数据对象中
    onGet();
  }

  /**
   * 执行数据保存的方法
   * 
   * @param rowData
   *            当前选中行数据
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
          obj.put("message", "保存成功");
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
   * 根据编辑模式设置表单数据
   */
  @Override
  public void onRender() {
    try {
      if (this.editMode != GaConstant.EDITMODE_NEW) {
        Map<String,Object> queryResultMap = this.bizClass.queryDetail();
        this.putFormValue(this.editForm, queryResultMap);
      } else {
        //设置默认值
        this.putFormValue(this.editForm,null);
      }
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"数据转换失败",ex);
    }    
  }
  
  /**
   * 初始化表单按钮
   */
  public void initAction() {
    //设置工具栏
    ActionButton button = new ActionButton(this.getClass(),"save","保存",
        getContext().getRequest().getRequestURI(),null);
    button.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV, this.preNavInfoStr);
    button.setFormButton(this.editForm.getName(),"onSave");
    this.editForm.regFormButton(button);
  }
  
  public String getDataFormName() {
    return "dataForm";  
  }

  /**
   * 初始化数据字段定义
   * @return
   */
  public abstract List<DbField> initDbField();
  

  /**
   * 绑定业务处理类
   * @return
   */
  public abstract IBiz initBizClass();
}
