package com.ga.erp.biz.settlement.storesettlement;

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
import com.ga.erp.biz.settlement.OrderListView;
import com.ga.erp.biz.store.StoreBiz;
import com.ga.erp.biz.store.StoreFormView;

@SuppressWarnings("serial")
public class StoreSettlementMTPage extends MTablePage {
	
  OrderListView orderListView;
  String type = "";
  @Override
	public void initController() throws Exception {
		super.initController();
	  type = this.modelMap.getRequest().getParameter("type");
	}
	
	@Override
	public void pageLoad(Map<String,Object> formQuery,QueryPageData pageQuery) {
		super.pageLoad(formQuery, pageQuery);
	}
	
	@Override
	public Layout initLayout() {    
		FormLayout formLayOut = new FormLayout("",this.detailView.getDataForm(),2);
//		formLayOut.mergeCellField("auto", 8, 0, 1, "NOTE");
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
//	  storeOrderListView.getFieldList().remove(20);
//	  storeOrderListView.getFieldList().remove(19);
//	  storeOrderListView.getFieldList().remove(18);
//	  storeOrderListView.getFieldList().remove(17);  
	  
//	  ActionButton action = new ActionButton(this.getClass(),"settle","结算",this.getSelfUrl(),null);
//    action.bindTableMultiSelect(this.storeOrderListView.getViewID());
//    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
//    PageEvent event = this.regPageEvent(action,"settle");
//    event.addEventRequestParam(this.storeOrderListView,GaConstant.FIXPARAM_MULTISELECT);
//    this.storeOrderListView.regAction(action);
    
		return orderListView;
	}

	@Override
	public FormView initDetailView() {
	  StoreFormView formView = new StoreFormView("storeFormView",modelMap);
		return formView;
	}

	@Override
	public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
    StoreSettlementBiz biz = new StoreSettlementBiz(this.getUserACL());
    PageResult pr = new PageResult();
    pr.setDataList(biz.querySettList(pageParam, this.orderListView.getFieldList(), type, formParam.get("STORE_ID")+ ""));
    return pr;
	}

	@Override
	public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
		StoreBiz biz = new StoreBiz(this.getUserACL());
		return biz.queryStoreDetail((Long)paramMap.get("STORE_ID"), this.detailView.getFieldList());
	}

	@Override
	public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {
	  StoreSettlementBiz biz = new StoreSettlementBiz(this.getUserACL());
	  biz.settle(listData);
	}
}
