package com.ga.erp.biz.supplier;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

public class SupplierMainPage extends UnitPage {

  private static final long serialVersionUID = 1L;
  private ListView supplierListView;
  String suppType = "";
  
  @Override
  public void initController() throws Exception {
    suppType = this.modelMap.getRequest().getParameter("suppliertype");
    supplierListView = new SupplierListView("supplierList",this.modelMap);
    this.supplierListView.setQueryRows(2);
    supplierListView.setMultiSelect(true);
    PageEvent event  = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.supplierListView,PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.supplierListView.regAddAction("addSupplier","�½�","/supplier/supplier_detail.htm");
    SubmitTool.submitToDialog(action, "addSupplier","�½�",800, 500,this.modelMap.getPreNavInfoStr());
    action.addParam("suppType", suppType);
    
    action = this.supplierListView.regEditAction("editSupplier", "�鿴 /�༭", "/supplier/supplier_detail.htm");
    SubmitTool.submitToDialog(action, "editSupplier","�鿴/�༭",860, 560,this.modelMap.getPreNavInfoStr());
    action.addParam("suppType", suppType);
    
//    action = this.supplierListView.regEditAction("startSupplier","����",this.getSelfUrl());
//    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
//    action.bindTableRowData(this.supplierListView.getViewID());
//    action.setConfirm("ȷ������?");
//    event = this.regPageEvent(action, "startSupplier");
//    event.addEventParam(this.supplierListView, PageEvent.PARAMTYPE_QUERYVALUE,"SUPPLIER_ID");
//    
//    action = this.supplierListView.regEditAction("stopSupplier","����",this.getSelfUrl());
//    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
//    action.bindTableRowData(this.supplierListView.getViewID());
//    action.setConfirm("ȷ�Ͻ���?");
//    event = this.regPageEvent(action, "stopSupplier");
//    event.addEventParam(this.supplierListView, PageEvent.PARAMTYPE_QUERYVALUE,"SUPPLIER_ID");
    
    this.supplierListView.regStateAction(this.getSelfUrl(), this, "SUPPLIER");
    
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.supplierListView);
    
    return layout;	
  }
  
  public void pageLoad(QueryPageData queryPageData) {
    try {      
      SupplierBiz biz  = new SupplierBiz(this.getUserACL());
      this.supplierListView.bindData(biz.querySupplierList(queryPageData,this.supplierListView.getFieldList(),null, suppType));      
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"ҳ������쳣");
    }
  }
  
//  
//  /**
//   * ����
//   * @param id
//   * @return
//   */
//  public  ActionResult startSupplier(Long id){
//    return startOrStopSupplier(id, "����");
//  }
//  
//  /**
//   *  ����
//   * @param id
//   * @return
//   */
//  public  ActionResult stopSupplier(Long id){
//    return startOrStopSupplier(id, "����");
//  }
//  
//  /**
//   * ��������ù�Ӧ��
//   * @param ids
//   * @param message
//   * @return
//   */
//  private  ActionResult startOrStopSupplier(Long id,String message){
//    try{
//      SupplierBiz biz  = new SupplierBiz(this.getUserACL());
//      if(message.equals("����")){
//        biz.stopSupplier(id);
//      }else{
//        biz.startSupplier(id);
//      }
//      return this.createReturnJSON(true, message + "��Ӧ�̳ɹ�", false, false, this.supplierListView.getViewID(), "");
//    }catch (BizException e) {
//      throw e;
//    }catch(Exception ex) {
//      ex.printStackTrace();
//      throw new BizException(BizException.SYSTEM,message + "��Ӧ��ʧ��");
//    }
//  }
  
 
}
