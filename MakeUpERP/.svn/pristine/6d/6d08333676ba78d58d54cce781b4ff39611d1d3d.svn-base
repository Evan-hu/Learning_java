package com.ga.erp.biz.system;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

@SuppressWarnings("serial")
public class OpDetailPage extends UnitPage {
  
  private OpFormView opForm;
  private OpRoleListView opRoleList;
  private String type;
  
  @Override
  public void initController() throws Exception {
    
    opForm = new OpFormView("opFrom",this.modelMap);
    opRoleList = new OpRoleListView("opRoleListView",this.modelMap);
    this.opRoleList.showPage(false);
    this.opRoleList.showQuery(false);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.opForm, PageEvent.PARAMTYPE_QUERYVALUE,"OP_ID");
    
    this.opForm.regAddSaveEvent("add","addOp",this, true);
    this.opForm.regEditSaveEvent("save", "updateOp",this, true);
    ActionButton action = this.opRoleList.regAddAction("addOpRoles","新增", "/system/role_main.htm?type=2");
    SubmitTool.submitToDialog(action,"新增", "新增",600,500,this.modelMap.getPreNavInfoStr());
    
    action  = new ActionButton(this.getClass(),"delOpRole","删除",this.getSelfUrl(),null);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    action.bindTableRowData(this.opRoleList.getViewID());
    action.setConfirm("确认删除? 删除后将无法恢复!");
    event = this.regPageEvent(action,"delOpRole");
    event.addEventParam(this.opRoleList,PageEvent.PARAMTYPE_QUERYVALUE,"OP_ROLE_ID");
    this.opRoleList.regAction(action);
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("",this.opForm.getDataForm(),2);
    formLayout.mergeCellField("auto", 6, 0, 2, "NOTE");
    this.opForm.getDataForm().setLayout(formLayout);
    if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
      layout.addControl(opForm);
    }else{
      layout.addControl("基本信息",opForm);
      layout.setLeftPanel("", "基本信息");
      layout.addControl("", "所属角色", "所属角色", "", this.opRoleList);
      layout.setLeftWidth("",80);
      layout.setControlLayoutH(this.opForm.getViewControl(),50);
    }
    return layout;
  }
  
  /**
   * 页面加载
   * @param id
   */
  public void pageLoad(Long id) {
    try {
      type = this.modelMap.getRequest().getParameter("TYPE");
      if(type == null){
        type = "1";
      }
      
      if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW) {
        this.opForm.bindData(this.opForm.getViewParam());
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        OpBiz biz  = new OpBiz(this.getUserACL());
        this.opForm.bindData(biz.queryOpDetail(id, this.opForm.getFieldList(), type));   
        this.opRoleList.bindData(biz.queryOpRoleList(id));
        this.opRoleList.getActionByID("addOpRoles").addParam("OP_ID", id);
      }      
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询详细异常");
    }
  }
  
  /**
   * 添加
   */
  public ActionResult addOp(Map<String,Object> valueMap){
    try {
      OpBiz biz = new OpBiz(this.getUserACL());
      biz.addOp(valueMap, this.opForm.getFuncMap());
     return this.createReturnJSON(true, "新建操作员成功,重新加载本页面!",true,false,"opList","");
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"新建操作员异常");
    }
  }
  
  /**
   * 修改
   */
  public ActionResult updateOp(Map<String,Object> valueMap){
    try {
      OpBiz biz = new OpBiz(this.getUserACL());
      biz.updateOp(valueMap, this.opForm.getFuncMap());
    return this.createReturnJSON(true, "修改操作员成功,重新加载本页面!",true,false,"opList","");
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"修改操作员异常");
    }
  }
  /**
   * 删除操作员角色
   * @param opRoleId
   * @return
   */
  public ActionResult delOpRole(Long opRoleId){
    try {  
      OpBiz biz = new OpBiz(this.getUserACL());
      biz.delOpRole(opRoleId);
      return this.createReturnJSON(true, "删除成功,重新加载本页面!",true,false,this.opForm.getViewID(),"");
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"删除异常");
    }
  }
  
}
