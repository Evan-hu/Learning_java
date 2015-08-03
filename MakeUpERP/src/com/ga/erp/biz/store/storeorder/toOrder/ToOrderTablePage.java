package com.ga.erp.biz.store.storeorder.toOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.stock.stockcomm.StockCommListView;
import com.ga.erp.util.SystemUtil;
/**
 * 采购单处理类（门店采购及DC采购）
 * @author Administrator
 *
 */
public class ToOrderTablePage extends MTablePage {
	
	private String tagetType = "";//操作方式类型：1：DC采购，2：门店采购
	private String type  = "";//条件类型：1：按库存量指标自动补货 ，2：按安全库存自动补货 ：3，手工输入补货商品
	private String sellType = "";//销量补货方式：1：补货数量=销量 ，2：补货数量=销量-当前库存 ：3，补货数量=销量-当前库存+库存下限 
	private String btime = "";//销售开始时间
	private String etime = "";//销售结束时间
	
	@Override
	public void initController() throws Exception {
		super.initController();
		ActionButton upBtn = null;
		for(ActionButton btn : actionList){
		  if(btn.getId().equals("mtableSaveAction")){
			  upBtn = btn;
			  break;
		  }
		}
		upBtn.setTitle("上一步");
		upBtn.setClickJS("ajaxLoad('/include/store/storeOrderGuideSetp1.jsp')");
		Map<String,Object>paramMap = new HashMap<String,Object>();
	  paramMap.put(GaConstant.FIXPARAM_EDITMODE, GaConstant.EDITMODE_EDIT);
		ActionButton nextBtn = new ActionButton(this.getClass(),"mtableSaveAction","下一步",this.getSelfUrl(),paramMap);
		this.regPageActionButton(nextBtn,this.detailView,this.listView);
		nextBtn.setClickJS("ajaxLoad('/include/store/storeOrderGuideSetp1.jsp')");
		HttpServletRequest request = this.modelMap.getRequest();
		tagetType = SystemUtil.formatStr(request.getParameter("tagetType"));
		type = SystemUtil.formatStr(request.getParameter("type")); 
		sellType =  SystemUtil.formatStr(request.getParameter("sellType"));
		btime = SystemUtil.formatStr(request.getParameter("btime"));
		etime = SystemUtil.formatStr(request.getParameter("etime"));
	}
	
	@Override
	public void pageLoad(Map<String,Object> formQuery,QueryPageData pageQuery) {
		super.pageLoad(formQuery, pageQuery);
	}
	
	@Override
	public Layout initLayout() {    
		FormLayout formLayOut = new FormLayout("",this.detailView.getDataForm(),2);
		this.detailView.getDataForm().setLayout(formLayOut);
		ViewPageLayout layout = new ViewPageLayout(this);				
		layout.addControl("选择商品","",this.listView);
    return layout;
	}
	
	@Override
	public ListView initListView() {
		StockCommListView listView = new StockCommListView("storeCommListView",modelMap);
		listView.removeDbField("NAME");
		listView.removeDbField("SHELF_NUM");
		listView.removeDbField("STATE");
		listView.removeDbField("UPPER_LIMIT");
		listView.removeDbField("LOWER_LIMIT");
		listView.removeDbField("COST_PRICE");
		listView.removeDbField("WHOLESALE_PRICE");
		listView.removeDbField("RETAIL_PRICE");
		listView.removeDbField("DISTRIBUT_PRICE");
		listView.removeDbField("MEM_PRICE");
		
		listView.showQuery(false);
		listView.showPage(false);
		ActionButton action = listView.regAddAction("addListView", "添加", "/store/storepos/pos_detail.htm");
		SubmitTool.submitToDialog(action, "newPos", "新建", 800, 270,this.modelMap.getPreNavInfoStr());
		action = listView.regEditAction("addListView", "修改", "/store/storepos/pos_detail.htm");
		SubmitTool.submitToDialog(action, "editPosP", "修改", 800, 270,this.modelMap.getPreNavInfoStr());
		//删除处理
		 ActionButton button = new ActionButton(this.getClass(),"delList","删除","",null); //	      
	   button.setOnClick("delMTableRow('"+listView.getViewID()+"List')");
	   button.bindTableRowData(listView.getViewID());
	   SubmitTool.submitToNavtab(button,"",this.modelMap.getPreNavInfoStr());
	   listView.regAction(button);
		return listView;
	}
//
	@Override
	public FormView initDetailView() {
		ToOrderFormView formView = new ToOrderFormView("toOrderFormView",modelMap);
		return formView;
	}

	@Override
	public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
		
		ToOrderBiz biz = new ToOrderBiz(this.getUserACL());
		List<Map<String, Object>> mapList = null;
		if("1".equals(type)){//按库存量指标自动补货 
		  mapList = biz.queryLimitStockList(this.listView.getFieldList());
		} else if("2".equals(type)){//按安全库存自动补货 
		  mapList = biz.querySafeStockList(this.listView.getFieldList());
		} else if("4".equals(type)){//按销量补货
		  mapList = biz.queryStockList(this.listView.getFieldList());
		}
		PageResult result = new PageResult();
		result.getPageParam().setPageNumber(1);
		result.getPageParam().setPageSize(999999);
		result.setDataList(mapList);
		return result;
		
	}
//
	@Override
	public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		//StoreBiz biz = new StoreBiz(this.getUserACL());
		return null;
		//return null;
	}

	@Override
	public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {
		try {
			//执行保存处理
			System.out.println("dddf");
		} catch(Exception ex) {
			
		}
	}
}
