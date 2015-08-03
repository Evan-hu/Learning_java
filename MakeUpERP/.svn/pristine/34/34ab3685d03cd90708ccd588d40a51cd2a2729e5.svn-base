package com.ga.erp.biz.store.storeorder;

import java.util.HashMap;
import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.DbField;
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
import com.ga.erp.biz.store.storeorder.comm.OrderCommListView;
import com.ga.erp.util.SystemUtil;

public class StoreOrderDetailPage extends UnitPage {
	
  private static final long serialVersionUID = 1L;
  private FormView storeOrderForm;
  private ListView orderCommList;
  private OrderDeliveryRecordListView orderDeliveryRecordListView;
  String type = "";
  
  @Override
  public void initController() throws Exception {
    type = this.modelMap.getRequest().getParameter("type");
    
    this.storeOrderForm = new StoreOrderFormView("storeOrderForm",this.modelMap);
    this.orderCommList = new OrderCommListView("orderCommList", this.modelMap);
    this.orderDeliveryRecordListView = new OrderDeliveryRecordListView("orderDeliveryRecordListView", this.modelMap);
    this.orderDeliveryRecordListView.showPage(false);
    this.orderDeliveryRecordListView.showQuery(false);
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.storeOrderForm, PageEvent.PARAMTYPE_QUERYVALUE, "STORE_ORDER_ID");
    
    ActionButton action = this.orderCommList.regAddAction("addOrderComm","新建", "/store/ordercomm_detail.htm");
    SubmitTool.submitToDialog(action, "editMember", "查看/编辑",800,300,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.storeOrderForm, "STORE_ORDER_ID", "STORE_ORDER_ID", false);
    
    action = this.orderCommList.regEditAction("editOrderComm","查看/编辑", "/store/ordercomm_detail.htm");
    SubmitTool.submitToDialog(action, "editOrderComm", "查看/编辑",800,300,this.modelMap.getPreNavInfoStr());
    
    this.storeOrderForm.regAddSaveEvent("saveStoreOrder", "saveStoreOrder", this, true);
    this.storeOrderForm.regEditSaveEvent("saveStoreOrder", "saveStoreOrder", this, true);
    
    event = this.regListViewLoadEvent(this.orderCommList, "reloadOrderCommList");
    event.addEventParam(this.storeOrderForm, PageEvent.PARAMTYPE_QUERYVALUE,"STORE_ORDER_ID");
    event.addEventParam(this.orderCommList, PageEvent.PARAMTYPE_PAGEQUERY);
    
  }

  @Override
  public Layout initLayout() {
  	FormLayout formLayOut = new FormLayout("",this.storeOrderForm.getDataForm(),2);
  	this.storeOrderForm.getDataForm().setLayout(formLayOut);
  	ViewPageLayout layout = new ViewPageLayout(this);
  	if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
  	  layout.addControl(this.storeOrderForm);
  	} else {
      layout.setControlLayoutH(this.orderDeliveryRecordListView.getViewControl(), 50);
  	  layout.addControl("基本信息","",this.storeOrderForm);
  	  layout.addControl("商品列表","",this.orderCommList);
  	  layout.addControl("物流记录","",this.orderDeliveryRecordListView);
  	}
    return layout;
  }

  public void pageLoad(Long storeOrderId) {
    try {
      Integer orderType =Integer.parseInt(type) ;
//      if(orderType < 5){
//        
//        this.storeOrderForm.removeDbField("RECEIVE_TIME");
//      }
      if(SystemUtil.getStoreOrderType(orderType) ==  SystemUtil.STORE_TYPE_STORE){//店间直调相关单据
          this.storeOrderForm.removeDbField("DISTRIBUTE_NUM","DISTRIBUTE_MONEY");
    	}else{
    	  this.storeOrderForm.removeDbField("SEND_STORE_ID","SEND_STORE_NAME");
      }
      if(orderType != SystemUtil.STORE_ORDER_DC_RECEIPT && orderType != SystemUtil.STORE_ORDER_DC_RETURN){
          this.storeOrderForm.removeDbField("CODE","SETTLEMENT_ID");
        }
      if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        StoreOrderBiz biz = new StoreOrderBiz(this.getUserACL());
        for (DbField field : this.storeOrderForm.getFieldList()) {
          if (!field.getFieldCode().equals("NOTE")) {
            field.setInputMode(GaConstant.EDITMODE_EDIT,
                GaConstant.INPUTMODE_READONLY);
          }
        }
        this.storeOrderForm.bindData(biz.queryStoreOrderDetail(storeOrderId));
        this.orderCommList.bindData(biz.queryOrderCommList(new QueryPageData(),this.orderCommList.getFieldList(), storeOrderId));
        this.orderDeliveryRecordListView.bindData(biz.queryOrderDeliveryRecord(storeOrderId, this.orderDeliveryRecordListView.getFieldList()));

      }else{
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("TYPE", type);
       this.storeOrderForm.bindData(map);
      }
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
  public ActionResult saveStoreOrder(Map<String, Object> valueMap) {
    try {
      StoreOrderBiz biz = new StoreOrderBiz(this.getUserACL());
      if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
        biz.saveStoreOrder(valueMap, true);
      } else {
        biz.saveStoreOrder(valueMap, false);
      }
      return this.createReturnJSON(true, "保存成功", true, true, "", "/store/storeorder_main.htm?type=" + valueMap.get("TYPE"));
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
  
  public ActionResult reloadOrderCommList(Long storeOrderId, QueryPageData pageData) {
    try {
      StoreOrderBiz biz = new StoreOrderBiz(this.getUserACL());
      this.orderCommList.bindData(biz.queryOrderCommList(pageData, this.orderCommList.getFieldList(), storeOrderId));
      ClickUtil.setControlLayoutH(this.orderCommList.getViewControl(), 150);
      return this.linkView(this.orderCommList);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
  
}
