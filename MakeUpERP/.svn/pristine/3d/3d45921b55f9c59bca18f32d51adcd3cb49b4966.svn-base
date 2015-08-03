package com.ga.erp.biz.settlement.deliveryorg;

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
import com.ga.erp.biz.system.deliveryorg.DeliveryBiz;
import com.ga.erp.biz.system.deliveryorg.DeliveryFormView;

@SuppressWarnings("serial")
public class DeliverySettMTPage extends MTablePage {
	
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
		return orderListView;
	}

	@Override
	public FormView initDetailView() {
		DeliveryFormView formView = new DeliveryFormView("deliveryFormView",modelMap);
		return formView;
	}

	@Override
	public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
	  
	  DeliverySettBiz biz = new DeliverySettBiz(this.getUserACL());
	  PageResult pr = new PageResult();
	  pr.setDataList(biz.querySettList(this.orderListView.getFieldList(), type, formParam.get("DELIVERY_ORG_ID")+""));
    return pr;
	}

	@Override
	public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
		DeliveryBiz biz = new DeliveryBiz(this.getUserACL());
		return biz.queryDeliveryDetail(this.detailView.getFieldList(), (Long)paramMap.get("DELIVERY_ORG_ID"));
	}

	@Override
	public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {
    DeliverySettBiz biz = new DeliverySettBiz(this.getUserACL());
    biz.save(formData, listData, "[物流机构手动结算]");
	}
}
