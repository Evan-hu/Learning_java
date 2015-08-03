package com.ga.erp.biz.system.audits.config;

import java.util.List;
import java.util.Map;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.MTablePage;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.system.audits.role.AuditsRoleBiz;
import com.ga.erp.biz.system.audits.role.AuditsRoleListView;

public class MTableRolePage extends MTablePage {
	
  private static final long serialVersionUID = 1L;

  @Override
	public void initController() throws Exception {
		super.initController();

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
		layout.addControl("基本信息","",this.detailView);
    layout.addControl("基本信息","",this.listView);
	  return layout;
	}
	
	@Override
	public ListView initListView() {
	  AuditsRoleListView listView = new AuditsRoleListView("auditsRoleList",modelMap);
		listView.showQuery(false);
		ActionButton action = listView.regAddAction("addListView", "新建", "/system/AuditsRole_detail.htm");
		SubmitTool.submitToDialog(action, "newRole", "新建", 800, 270,this.modelMap.getPreNavInfoStr());
		action = listView.regEditAction("addListView", "修改", "/system/AuditsRole_detail.htm");
		SubmitTool.submitToDialog(action, "editRole", "修改", 800, 270,this.modelMap.getPreNavInfoStr());
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
	  AudConfigFormView formView = new AudConfigFormView("audConfigFormView",modelMap);
		return formView;
	}

	@Override
	public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
	  AuditsRoleBiz biz = new AuditsRoleBiz(this.getUserACL());
		PageResult pr = biz.queryAuditsRoleList(pageParam,this.listView.getFieldList(),(Long)formParam.get("AUDITING_CONFIG_ID"));
		return pr;
	}

	@Override
	public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
	  AudConfigBiz biz = new AudConfigBiz(this.getUserACL());
		return biz.queryAudConfigDetail((Long)paramMap.get("AUDITING_CONFIG_ID"), this.detailView.getFieldList());
	}

	@Override
	public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {
		try {
			//执行保存处理
		  DbUtils.begin();
		  
		} catch(Exception ex) {
			
		}
	}	
	
}
