package com.ga.erp.biz.settlement.suppliersettlement;

import java.util.List;
import java.util.Map;
import com.ga.click.control.GaConstant;
import com.ga.click.dbutil.PageResult;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.MTablePage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.settlement.OrderListView;
import com.ga.erp.biz.supplier.SupplierBiz;
import com.ga.erp.biz.supplier.SupplierFormView;

@SuppressWarnings("serial")
public class SuppSettlementMTPage extends MTablePage {
	
  OrderListView orderListView;
  private String type = "";
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
		layout.addControl("基本信息","",this.orderListView);
	    return layout;
	}
	
	@Override
	public ListView initListView() {
		// TODO Auto-generated method stub
	  orderListView = new OrderListView("orderListView",modelMap);
	  orderListView.showQuery(false);
	  orderListView.setMultiSelect(true);
	  orderListView.setViewEditMode(GaConstant.EDITMODE_DISP);
	  
//	  ActionButton action = new ActionButton(this.getClass(),"settle","结算",this.getSelfUrl(),null);
//    action.bindTableMultiSelect(this.orderListView.getViewID());
//    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
//    PageEvent event = this.regPageEvent(action,"settle");
//    event.addEventRequestParam(this.orderListView,GaConstant.FIXPARAM_MULTISELECT);
//    this.orderListView.regAction(action);
    
		return orderListView;
	}

	@Override
	public FormView initDetailView() {
		SupplierFormView formView = new SupplierFormView("supplierFormView",modelMap);
		return formView;
	}

	@Override
	public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
		// TODO Auto-generated method stub
    SuppSettlementBiz suBiz = new SuppSettlementBiz(this.getUserACL());
    PageResult pr = new PageResult();
    pr.setDataList(suBiz.querySettList(pageParam, this.orderListView.getFieldList(), type, formParam.get("SUPPLIER_ID") + ""));
    return pr;
	}

	@Override
	public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
		SupplierBiz biz = new SupplierBiz(this.getUserACL());
		return biz.querySupplierMap((Long)paramMap.get("SUPPLIER_ID"));
	}

	@Override
	public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {
	  SuppSettlementBiz biz = new SuppSettlementBiz(this.getUserACL());
	  biz.settle(listData);
	}
	
//	public ActionResult settle(String ids) {
//	    SuppSettlementBiz biz = new SuppSettlementBiz(this.getUserACL());
//      biz.save(ids, this.modelMap.getSession(false).getAttribute("type") + "", "[供应商手动结算]");
//      return this.createReturnJSON(true, "结算账单已生成", true, false, this.orderListView.getViewID(), "");
//	}
}
