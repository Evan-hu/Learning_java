package com.ga.erp.biz.activity.regactivity;

import java.util.List;
import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.GaConstant;
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
import com.ga.erp.biz.store.StoreListView;

@SuppressWarnings("serial")
public class RegActivityMTPage extends MTablePage {
	
  private StoreListView storeListView;
  private StoreListView storeListView1;
  private MemberRegActivityListView memberRegListView;
  
	@Override
	public void initController() throws Exception {
		super.initController();
		
		this.storeListView1 = new StoreListView("storeListView1", this.modelMap);
		this.storeListView1.setQueryRows(2);
    PageEvent event = this.regListViewLoadEvent(this.storeListView1, "reloadStoreList");
    event.addEventParam(this.detailView, PageEvent.PARAMTYPE_QUERYVALUE,"REG_ACTIVITY_ID");
    event.addEventParam(this.storeListView1, PageEvent.PARAMTYPE_PAGEQUERY);
		
		this.memberRegListView = new MemberRegActivityListView("memberRegListView",this.modelMap);    
		this.memberRegListView.setQueryRows(2);
    event = this.regListViewLoadEvent(this.memberRegListView, "reloadRegList");
    event.addEventParam(this.detailView, PageEvent.PARAMTYPE_QUERYVALUE,"REG_ACTIVITY_ID");
    event.addEventParam(this.memberRegListView, PageEvent.PARAMTYPE_PAGEQUERY);
	}
	
	@Override
	public void pageLoad(Map<String,Object> formQuery,QueryPageData pageQuery) {
		super.pageLoad(formQuery, pageQuery);
		RegActivityBiz biz = new RegActivityBiz(this.getUserACL());
    this.memberRegListView.bindData(biz.queryRegMemberList(pageQuery, this.memberRegListView.getFieldList(), (Long) formQuery.get("REG_ACTIVITY_ID")));
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
      this.storeListView1.bindData(biz.queryRegStoreList(new QueryPageData(), this.storeListView1.getFieldList(), formQuery.get("REG_ACTIVITY_ID") + ""));
    }
	}
	
	@Override
	public Layout initLayout() {    
		FormLayout formLayOut = new FormLayout("",this.detailView.getDataForm(),2);
//		formLayOut.mergeCellField("auto", 8, 0, 1, "NOTE");
		this.detailView.getDataForm().setLayout(formLayOut);
		ViewPageLayout layout = new ViewPageLayout(this);				
		layout.addControl("基本信息","",this.detailView);
		layout.addControl("基本信息","",this.storeListView);
		if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		  layout.addControl("参与活动会员","",this.memberRegListView);
		  layout.addControl("参与活动门店","",this.storeListView1);
		}
	    return layout;
	}
	
	@Override
	public ListView initListView() {
		// TODO Auto-generated method stub
	  storeListView = new StoreListView("storeListView",modelMap);
	  storeListView.showQuery(false);
	  storeListView.setMultiSelect(true);
	  storeListView.setViewEditMode(GaConstant.EDITMODE_DISP);
		return storeListView;
	}

	@Override
	public FormView initDetailView() {
		RegActivityFormView formView = new RegActivityFormView("regActivityFormView",modelMap);
		return formView;
	}

	@Override
	public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
		// TODO Auto-generated method stub
	  Boolean isEdit = false;
	  RegActivityBiz biz = new RegActivityBiz(this.getUserACL());
	  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
	    isEdit = true;
	  }
	  PageResult pr = biz.queryStoreList(pageParam, this.storeListView.getFieldList(), formParam.get("REG_ACTIVITY_ID") + "", isEdit);
		return pr;
	}

	@Override
	public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
		RegActivityBiz biz = new RegActivityBiz(this.getUserACL());
		return biz.queryRegActivityDetail((Long)paramMap.get("REG_ACTIVITY_ID"), this.detailView.getFieldList());
	}
  
  public ActionResult reloadStoreList(Long id, QueryPageData pageData) {
    try {
      RegActivityBiz biz = new RegActivityBiz(this.getUserACL());
        this.storeListView1.bindData(biz.queryRegStoreList(pageData, this.storeListView1.getFieldList(), id + ""));
        ClickUtil.setControlLayoutH(this.storeListView1.getViewControl(), 170);
      return this.linkView(this.storeListView1);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
	
	 public ActionResult reloadRegList(Long id, QueryPageData pageData) {
     try {
       RegActivityBiz biz = new RegActivityBiz(this.getUserACL());
       this.memberRegListView.bindData(biz.queryRegMemberList(pageData, this.memberRegListView.getFieldList(), id));
       ClickUtil.setControlLayoutH(this.memberRegListView.getViewControl(), 170);
       return this.linkView(this.memberRegListView);
     } catch (BizException e) {
       throw e;
     } catch (Exception ex) {
       throw new BizException("加载页面异常");
     }
   }
	 
	@Override
	public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {
	  RegActivityBiz biz = new RegActivityBiz(this.getUserACL());
	  biz.save(formData, listData);
	}
	
//	public ActionResult select(String ids) {
//	  
//	  
//    return this.createReturnJSON(true, "该活动已经应用到所选门店", true, false, this.storeListView.getViewID(), "");
//	}
}
