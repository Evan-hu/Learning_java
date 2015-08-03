package com.ga.erp.biz.settlement.suppliersettlement.costordersettlement;

import java.util.List;
import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.dbutil.PageResult;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.MTablePage;
import com.ga.click.mvc.PageEvent;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.settlement.costorder.CostOrderBiz;
import com.ga.erp.biz.settlement.costorder.CostOrderListView;
import com.ga.erp.biz.store.StoreBiz;
import com.ga.erp.biz.store.StoreFormView;
import com.ga.erp.biz.supplier.SupplierBiz;
import com.ga.erp.biz.supplier.SupplierFormView;
import com.ga.erp.biz.system.deliveryorg.DeliveryBiz;
import com.ga.erp.biz.system.deliveryorg.DeliveryFormView;

@SuppressWarnings("serial")
public class CostOrderSettlementMTPage extends MTablePage {
	
  CostOrderListView costOrderListView;
  private String type;
	@Override
	public void initController() throws Exception {
		super.initController();
    type = this.modelMap.getRequest().getParameter("type");
    if(GaUtil.isNullStr(this.modelMap.getSession(false).getAttribute("type") + "")) {
      this.modelMap.getSession(false).setAttribute("type", type);
    }
	}
	
	@Override
	public void pageLoad(Map<String,Object> formQuery,QueryPageData pageQuery) {
		super.pageLoad(formQuery, pageQuery);
	}
	
	@Override
	public Layout initLayout() {    
		FormLayout formLayOut = new FormLayout("",this.detailView.getDataForm(),2);
		formLayOut.mergeCellField("auto", 8, 0, 1, "NOTE");
		this.detailView.getDataForm().setLayout(formLayOut);
		ViewPageLayout layout = new ViewPageLayout(this);				
		layout.addControl("基本信息","",this.detailView);
		layout.addControl("基本信息","",this.costOrderListView);
	  return layout;
	}
	
	@Override
	public ListView initListView() {
		// TODO Auto-generated method stub
	  costOrderListView = new CostOrderListView("costOrderListView",modelMap);
	  costOrderListView.showQuery(false);
	  costOrderListView.setMultiSelect(true);
	  costOrderListView.setViewEditMode(GaConstant.EDITMODE_DISP);  
	  
	  ActionButton action = new ActionButton(this.getClass(),"settle","结算",this.getSelfUrl(),null);
    action.bindTableMultiSelect(this.costOrderListView.getViewID());
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    PageEvent event = this.regPageEvent(action,"settle");
    event.addEventRequestParam(this.costOrderListView,GaConstant.FIXPARAM_MULTISELECT);
    this.costOrderListView.regAction(action);

    return costOrderListView;
	}

	@Override
	public FormView initDetailView() {
	  type = this.modelMap.getRequest().getParameter("type");
	  //new SupplierFormView("supplierFormView",modelMap
	  return (!GaUtil.isNullStr(type)) && "2".equals(type) ? new StoreFormView("storeFormView",modelMap) : (!GaUtil.isNullStr(type)) && "3".equals(type) || "1".equals(type) ? new SupplierFormView("supplierFormView",modelMap) : new DeliveryFormView("deliveryFormView",modelMap);
	}

	@Override
	public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
		// TODO Auto-generated method stub
    CostOrderBiz biz = new CostOrderBiz(this.getUserACL());
    PageResult pr = biz.queryCostOrderList(pageParam, this.costOrderListView.getFieldList(), type, (!GaUtil.isNullStr(type)) && "2".equals(type) ? formParam.get("STORE_ID") + "" : (!GaUtil.isNullStr(type)) && "3".equals(type) ? formParam.get("SUPPLIER_ID") + "" :formParam.get("DELIVERY_ORG_ID") + "");
    return pr;
	}

	@Override
	public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
	  if((!GaUtil.isNullStr(type)) && "2".equals(type)) {
	    StoreBiz biz = new StoreBiz(this.getUserACL());
	    return biz.queryStoreDetail((Long)paramMap.get("STORE_ID"),this.detailView.getFieldList());
	  }else if((!GaUtil.isNullStr(type)) && "3".equals(type) || "1".equals(type)) {
	    SupplierBiz biz = new SupplierBiz(this.getUserACL());
	    return biz.querySupplierMap((Long)paramMap.get("SUPPLIER_ID"));
    }else {
      DeliveryBiz biz = new DeliveryBiz(this.getUserACL());
      return biz.queryDeliveryDetail(this.detailView.getFieldList(), (Long)paramMap.get("DELIVERY_ORG_ID"));
    }
	}

	@Override
	public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {

	}
	
	public ActionResult settle(String ids) {
	    CostOrderSettlementBiz biz = new CostOrderSettlementBiz(this.getUserACL());
      biz.settle(ids, this.modelMap.getSession(false).getAttribute("type") + "");
      return this.createReturnJSON(true, "结算账单已生成", true, false, this.costOrderListView.getViewID(), "");
	}
}
