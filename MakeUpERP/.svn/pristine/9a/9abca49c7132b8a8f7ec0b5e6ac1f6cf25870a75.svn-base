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
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.erp.biz.store.StoreListView;

public class OpNoEditDetailPage extends UnitPage {
	
  private static final long serialVersionUID = 1L;
  private OpFormView opForm;
  private ListView storeList;
  private String type;
  
  public final static String storeOP = "2"; 
  public final static String supplierOP = "3"; 
  
  @Override
  public void initController() throws Exception {
    
    opForm = new OpFormView("opFrom",this.modelMap);
    storeList = new StoreListView("opStoreList", this.modelMap);
    storeList.showQuery(false);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.opForm, PageEvent.PARAMTYPE_QUERYVALUE,"OP_ID");
    
    ActionButton action = this.storeList.regAddAction("newOpStore","新建","/system/opstore_detail.htm");
    SubmitTool.submitToDialog(action,"newOp", "新建操作员",800,200,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.opForm, "OP_ID", "OP_ID", false);
    
    action  = new ActionButton(this.getClass(),"delStoreOp","删除",this.getSelfUrl(),null);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    action.bindTableRowData(this.storeList.getViewID());
    action.setConfirm("确认删除(只会删除管理员本门店管理权限)?");
    event = this.regPageEvent(action,"delStoreOp");
    event.addEventParam(this.storeList,PageEvent.PARAMTYPE_QUERYVALUE,"OP_STORE_ID");
    this.storeList.regAction(action);
    
    this.opForm.regAddSaveEvent("add","addOp",this, true);
    this.opForm.regEditSaveEvent("save", "updateOp",this, true);
    
    event = this.regListViewLoadEvent(this.storeList, "reloadStoreOp");
    event.addEventParam(this.opForm, PageEvent.PARAMTYPE_QUERYVALUE,"OP_ID");
    
  }

  @Override
  public Layout initLayout() {
    
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("",this.opForm.getDataForm(),2);
    formLayout.mergeCellField("auto", 6, 0, 2, "NOTE");
    this.opForm.getDataForm().setLayout(formLayout);
    if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
      layout.addControl(opForm);
    } else {
      layout.addControl("基本信息","",opForm);
      layout.addControl("门店列表","",storeList);
    }
    return layout;
  }
  
  public void pageLoad(Long opId) {
    try {
      
      type = this.modelMap.getRequest().getParameter("TYPE");
      if(type == null){
        type = "1";
      }
      if(!type.equals("2")){
        this.opForm.removeDbField("STORE_NAME");
      }
      if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW) {
  		  this.opForm.bindData(this.opForm.getViewParam());
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        OpBiz biz  = new OpBiz(this.getUserACL());
        this.opForm.bindData(biz.queryOpDetail(opId, this.opForm.getFieldList(), type));  
        this.storeList.bindData(biz.queryOpStoreList(opId));
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
      return this.createReturnJSON(true, "新建操作员成功!",true,false,"opList","");
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
      return this.createReturnJSON(true, "修改操作员成功!",true,false,"opList","");
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"修改操作员异常");
    }
  }
  
  public ActionResult delStoreOp(Long opStoreId){
    try {
      OpBiz biz = new OpBiz(this.getUserACL());
      biz.delOpStore(opStoreId);
      return this.createReturnJSON(true, "删除门店管理员成功!", false, false, this.storeList.getViewID(),"");
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"删除门店管理员异常");
    }
  }
  
  public ActionResult reloadStoreOp(Long opId) {
    try {
      OpBiz biz = new OpBiz(this.getUserACL());
      this.storeList.bindData(biz.queryOpStoreList(opId));
      return this.linkView(this.storeList);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
 
}
