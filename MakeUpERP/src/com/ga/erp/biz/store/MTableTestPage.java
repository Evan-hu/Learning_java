package com.ga.erp.biz.store;

import java.util.List;
import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.MTablePage;
import com.ga.click.mvc.PageEvent;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;
import com.ga.erp.biz.store.storepos.PosBiz;
import com.ga.erp.biz.store.storepos.PosListView;
import com.ga.erp.biz.store.storewalletlog.WalletLogBiz;
import com.ga.erp.biz.store.storewalletlog.WalletLogListView;

public class MTableTestPage extends MTablePage {
  
  private static final long serialVersionUID = 1L;
  private ListView walletLogList;
	
	@Override
	public void initController() throws Exception {
		super.initController();
		
		this.walletLogList = new WalletLogListView("walletLogList",this.modelMap);		
		PageEvent event = this.regListViewLoadEvent(this.walletLogList, "reloadWalletLogList");
		event.addEventParam(this.detailView, PageEvent.PARAMTYPE_QUERYVALUE,"STORE_ID");
		event.addEventParam(this.walletLogList, PageEvent.PARAMTYPE_PAGEQUERY);
	}
	
	@Override
	public void pageLoad(Map<String,Object> formQuery,QueryPageData pageQuery) {
		super.pageLoad(formQuery, pageQuery);
		WalletLogBiz walletBiz = new WalletLogBiz(this.getUserACL());
		this.walletLogList.bindData(walletBiz.queryWalletLogList(new QueryPageData(),this.walletLogList.getFieldList(),(Long) formQuery.get("STORE_ID")));
		
	}
	
	@Override
	public Layout initLayout() {    
		FormLayout formLayOut = new FormLayout("",this.detailView.getDataForm(),2);
		this.detailView.getDataForm().setLayout(formLayOut);
		ViewPageLayout layout = new ViewPageLayout(this);				
		layout.addControl("基本信息","",this.detailView);
		layout.addControl("基本信息","",this.listView);
		layout.addControl("门店钱包日志","", this.walletLogList);
	    return layout;
	}
	
//		WalletLogBiz walletBiz = new WalletLogBiz(this.getUserACL());
//this.walletLogList.bindData(walletBiz.queryWalletLogList(new QueryPageData(),this.walletLogList.getFieldList(), storeId));

	
	@Override
	public ListView initListView() {
		// TODO Auto-generated method stub
		PosListView listView = new PosListView("posListView",modelMap);
		listView.showQuery(false);
//		listView.setViewEditMode(GaConstant.EDITMODE_EDIT);
//		listView.regCreateRowAction("asdfasd","adfasdf");
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

	@Override
	public FormView initDetailView() {
		// TODO Auto-generated method stub
		StoreFormView formView = new StoreFormView("storeFormView",modelMap);
		return formView;
	}

	@Override
	public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
		// TODO Auto-generated method stub
		PosBiz biz = new PosBiz(this.getUserACL());
		StringBuilder builder = new StringBuilder("");
		PageResult pr = biz.queryPosList((Long)formParam.get("STORE_ID"),pageParam,this.listView.getFieldList(), builder);
		this.listView.setQueryTip(builder.toString());
		return pr;
		  //return biz.queryStoreList(queryPagedata);
	}

	@Override
	public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		StoreBiz biz = new StoreBiz(this.getUserACL());
		return biz.queryStoreDetail((Long)paramMap.get("STORE_ID"), this.detailView.getFieldList());
	}

	@Override
	public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {
		try {
			//执行保存处理
			System.out.println("dddf");
		} catch(Exception ex) {
			
		}
		
	}

	public ActionResult reloadWalletLogList(Long storeId, QueryPageData pageData) {
	    try {
	      WalletLogBiz biz = new WalletLogBiz(this.getUserACL());
	      this.walletLogList.bindData(biz.queryWalletLogList(pageData,this.walletLogList.getFieldList(), storeId));
	      ClickUtil.setControlLayoutH(this.walletLogList.getViewControl(), 120);
	      return this.linkView(this.walletLogList);
	    } catch (BizException e) {
	      throw e;
	    } catch (Exception ex) {
	      throw new BizException("加载页面异常");
	    }
	  }
	
}
