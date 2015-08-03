package com.ga.erp.biz.supplier;

import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;
import com.ga.erp.biz.purchase.order.PurOrderBiz;
import com.ga.erp.biz.purchase.order.PurOrderListView;
import com.ga.erp.biz.supplier.commodity.SupplierCommodityBiz;
import com.ga.erp.biz.supplier.commodity.SupplierCommodityListView;
import com.ga.erp.biz.supplier.contract.ContractBiz;
import com.ga.erp.biz.supplier.contract.ContractListView;
import com.ga.erp.biz.system.OpBiz;
import com.ga.erp.biz.system.OpListView;
import com.ga.erp.biz.system.OpNoEditDetailPage;

public class SupplierDetailPage extends UnitPage {

  private static final long serialVersionUID = 1L;
  private FormView formView;
  private ListView supplierCommodityListView;
  private ListView contractListView;
  private ListView purOrderListView;
  private ListView opList;
  String suppType = "";

  @Override
  public void initController() throws Exception {
    suppType = this.modelMap.getRequest().getParameter("suppType");
    this.formView = new SupplierFormView("supplierDetail", this.modelMap);
    this.supplierCommodityListView = new SupplierCommodityListView("supplierCommList", this.modelMap);
    this.supplierCommodityListView.setQueryRows(3);
    this.contractListView = new ContractListView("contractListView", this.modelMap);
    this.contractListView.setQueryRows(2);
    this.contractListView.getFieldList().remove(1);
    this.purOrderListView = new PurOrderListView("purOrderListView", this.modelMap);
    this.purOrderListView.setQueryRows(2);
    this.opList = new OpListView("opList", this.modelMap);
    this.opList.showQuery(false);
    this.opList.showPage(false);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "SUPPLIER_ID");
    event.addEventParam(this.supplierCommodityListView,PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = new ActionButton(this.getClass(), "saveSupplier", "����", this.getSelfUrl(), null);
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    event = this.regPageEvent(action, "saveSupplier");
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_DATAMAP);
    action.bindForm(this.formView.getViewID());
    action.addParam("suppType", suppType);
    this.formView.regAction(action);
    
    action = this.supplierCommodityListView.regAddAction("addSupplierCommodity","�½�", "/supplier/commodity/suppliercommodity_detail.htm");
    SubmitTool.submitToDialog(action, "addSupplierCommodity","�½�",800, 350,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.formView,"SUPPLIER_ID","SUPPLIER_ID",false);
    
    action = this.supplierCommodityListView.regEditAction("editSupplierCommodity", "�鿴 /�༭", "/supplier/commodity/suppliercommodity_detail.htm");
    SubmitTool.submitToDialog(action, "editSupplierCommodity","�鿴/�༭",800, 350,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.formView, "SUPPLIER_ID", "SUPPLIER_ID", false);
    
    this.supplierCommodityListView.regStateAction(this.getSelfUrl(), this, "SUPPLIER_COMMODITY");
    event = this.regListViewLoadEvent(this.supplierCommodityListView, "reloadSupplierCommList");
    event.addEventParam(this.supplierCommodityListView,PageEvent.PARAMTYPE_PAGEQUERY); 
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "SUPPLIER_ID");
    
    action = this.contractListView.regAddAction("newContract","�½���ͬ","/supplier/contract_detail.htm");
    SubmitTool.submitToDialog(action,"newContract", "�½���ͬ",800,300,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.formView,"SUPPLIER_ID","SUPPLIER_ID",false);
    action.bindViewParam(this.formView,"SUPPLIER_NAME","SUPPLIER_NAME",false);
    
    action = this.contractListView.regEditAction("editContract","�鿴/�༭", "/supplier/contract_detail.htm");
    SubmitTool.submitToDialog(action, "editContract", "�鿴/�༭",800,350,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.formView,"SUPPLIER_ID","SUPPLIER_ID",false);
    
    action = this.opList.regAddAction("newOp","�½�","/system/op_noedit_detail.htm");
    SubmitTool.submitToDialog(action,"newOp", "�½�",800,400,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.formView, "SUPPLIER_ID", "SUPPLIER_ID", false);
    action.addParam("TYPE", OpNoEditDetailPage.supplierOP);
    
    action = this.opList.regEditAction("editOp","�鿴/�༭", "/system/op_noedit_detail.htm");
    SubmitTool.submitToDialog(action,"editOp", "�鿴/�༭",800,400,this.modelMap.getPreNavInfoStr());
    
    action = this.opList.regEditAction("editPassword","�޸�����", "/system/op_pass_detail.htm");
    SubmitTool.submitToDialog(action,"editPassword", "�޸�����",400,230,this.modelMap.getPreNavInfoStr());
    
    action  = new ActionButton(this.getClass(),"delSupplierOp","ɾ��",this.getSelfUrl(),null);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    action.bindTableRowData(this.opList.getViewID());
    action.setConfirm("ȷ��ɾ��(ֻ��ɾ������Ա����Ӧ�̹���Ȩ��)?");
    event = this.regPageEvent(action,"delSupplierOp");
    event.addEventParam(this.opList,PageEvent.PARAMTYPE_QUERYVALUE,"OP_SUPPLIER_ID");
    this.opList.regAction(action);
    
    event = this.regListViewLoadEvent(this.contractListView, "reloadContractList");
    event.addEventParam(this.contractListView,PageEvent.PARAMTYPE_PAGEQUERY); 
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "SUPPLIER_ID");
    
    event = this.regListViewLoadEvent(this.purOrderListView, "reloadPurOrder");
    event.addEventParam(this.purOrderListView,PageEvent.PARAMTYPE_PAGEQUERY); 
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "SUPPLIER_ID");
    
    event = this.regListViewLoadEvent(this.opList, "reloadSupplierOp");
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "SUPPLIER_ID");
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("",this.formView.getDataForm(), 2);
    formLayout.mergeCellField("auto", 8, 0, 1, "ADDR");
    formLayout.mergeCellField("auto", 9, 0, 1, "NOTE");
    this.formView.getDataForm().setLayout(formLayout);
    layout.addControl("������Ϣ","",formView);
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
      if("1".equals(suppType)) {
        layout.addControl("��Ӧ����Ʒ", "", this.supplierCommodityListView);
      }
      layout.addControl("��ͬ", "" , this.contractListView);
      layout.addControl("����ҵ��", "" , this.purOrderListView);
      layout.addControl("����Ա��Ϣ","",this.opList);
    }
	  ClickUtil.setControlLayoutH(this.purOrderListView.getViewControl(),120);

    return layout;
  }

  public void pageLoad(Long supplierId, QueryPageData queryPageData) {
    try {   
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
      SupplierBiz biz = new SupplierBiz(this.getUserACL());
      SupplierCommodityBiz scBiz = new SupplierCommodityBiz(this.getUserACL());
      ContractBiz conBiz = new ContractBiz(this.getUserACL());
      PurOrderBiz purBiz = new PurOrderBiz(this.getUserACL());
      OpBiz opBiz = new OpBiz(this.getUserACL());
      this.formView.bindData(biz.querySupplierMap(supplierId));
      this.supplierCommodityListView.bindData(scBiz.querySupplierCommList(queryPageData, this.supplierCommodityListView.getFieldList(), supplierId));
      this.contractListView.bindData(conBiz.queryContractList(queryPageData, this.contractListView.getFieldList(), supplierId));
      this.purOrderListView.bindData(purBiz.queryStockList(queryPageData, this.purOrderListView.getFieldList(), supplierId,"",""));
      this.opList.bindData(opBiz.querySupplierOpList(supplierId));
      }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"ҳ������쳣");
    }
  }

  public ActionResult saveSupplier(Map<String, Object> valuesMap) {
    
    String type = this.formView.getActionList().get(0).getParamMap().get("suppType") + "";
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        SupplierBiz biz = new SupplierBiz(this.getUserACL());
        biz.saveSupplier(valuesMap, type, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        SupplierBiz biz = new SupplierBiz(this.getUserACL());
        biz.saveSupplier(valuesMap, type, false);
      }
      return this.createReturnJSON(true, "���湩Ӧ�̳ɹ�", true, false, "supplierList", "");
  }
  
  public ActionResult delSupplierOp(Long opSupplierId){
    try {
      OpBiz biz = new OpBiz(this.getUserACL());
      biz.delOpSupplier(opSupplierId);
      return this.createReturnJSON(true, "ɾ����Ӧ�̹���Ա�ɹ�!", false, false, this.opList.getViewID(),"");
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"�޸Ĳ���Ա�쳣");
    }
  }
  
  public ActionResult reloadContractList(QueryPageData pageData, Long supplierId) {
	try {
	  ContractBiz biz = new ContractBiz(this.getUserACL());
	  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
	      this.contractListView.bindData(biz.queryContractList(pageData, this.contractListView.getFieldList(), supplierId));
	  }
	  ClickUtil.setControlLayoutH(this.contractListView.getViewControl(), 160);
	    return this.linkView(this.contractListView);
	  } catch(BizException e) {
	     throw e;
	  } catch(Exception ex) {
	     throw new BizException("����ҳ���쳣");
	 }
  }
  
  public ActionResult reloadSupplierCommList(QueryPageData pageData, Long supplierId) {
	try {
	  SupplierCommodityBiz biz = new SupplierCommodityBiz(this.getUserACL());
	  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
	      this.supplierCommodityListView.bindData(biz.querySupplierCommList(pageData, this.supplierCommodityListView.getFieldList(), supplierId));
	  }
	  ClickUtil.setControlLayoutH(this.supplierCommodityListView.getViewControl(), 190);
	    return this.linkView(this.supplierCommodityListView);
	  } catch(BizException e) {
	     throw e;
	  } catch(Exception ex) {
	     throw new BizException("����ҳ���쳣");
	 }
  }
  
  public ActionResult reloadPurOrder(QueryPageData pageData, Long supplierId) {
	try {
	  PurOrderBiz biz = new PurOrderBiz(this.getUserACL());
	  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
	      this.purOrderListView.bindData(biz.queryStockList(pageData, this.purOrderListView.getFieldList(), supplierId,"",""));
	  }
	  ClickUtil.setControlLayoutH(this.purOrderListView.getViewControl(), 160);
	    return this.linkView(this.purOrderListView);
	  } catch(BizException e) {
	     throw e;
	  } catch(Exception ex) {
	     throw new BizException("����ҳ���쳣");
	 }
  }
  
  public ActionResult reloadSupplierOp(Long supplierId) {
    try {
      SupplierBiz biz = new SupplierBiz(this.getUserACL());
      this.opList.bindData(biz.querySupplierOpList(supplierId));
      return this.linkView(this.opList);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("����ҳ���쳣");
    }
  }
  
}
