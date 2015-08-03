package com.ga.erp.biz.comm.sort;

import java.sql.ResultSet;
import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.TreeView;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;
import com.ga.erp.biz.comm.CommBiz;
import com.ga.erp.biz.comm.CommListView;

@SuppressWarnings("serial")
public class SortMainPage extends UnitPage{
	
  private TreeView sortSelectView;
  private FormView sortForm;
  private ListView commList;
  
  @Override
  public void initController() throws Exception {
	  
  	this.sortSelectView = new SortSeleView("sortTree",this.modelMap);
  	this.sortForm = new SortFormView("sortForm",this.modelMap);  
  	this.regPageLoadEvent("pageLoad");
  	
  	this.commList = new CommListView("commList", this.modelMap);
  	this.commList.showPage(false);
  	this.commList.showQuery(false);

  	PageEvent loadMethod = this.regPageLoadEvent("pageLoadSort");
  	
    ActionButton action = sortSelectView.regClickEvent(this.getSelfUrl(), "getSortDetail", this);
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr(),this.sortForm,this.commList);
    action.getEvent().addEventParam(this.sortForm,PageEvent.PARAMTYPE_QUERYVALUE,"SORT_ID");
    
    action  = this.sortForm.regAddEventByOpenDialog("newArticleSort","新建分类","/comm/sort_detail.htm", this);
    SubmitTool.submitToDialog(action, "newSortDlg","新建子分类",750, 300,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.sortForm,"pID","SORT_ID",true);
    action.bindViewParam(this.sortForm,"pName","SORT_NAME",true);
    
    this.sortForm.regEditSaveEvent("saveSort", "saveSort", this,false);
    
    action = this.commList.regAddAction("addComm", "新建",
        "/comm/comm_detail.htm");
    SubmitTool.submitToDialog(action, "addComm", "新建", 800, 230,
        this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.sortForm, "SORT_ID", "SORT_ID", false);
    action.setHidden(true);

    action = this.commList.regEditAction("editComm",
        "查看 /编辑", "/comm/comm_detail.htm");
    SubmitTool.submitToDialog(action, "editComm", "查看/编辑", 800, 290,
        this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.sortForm, "SORT_ID", "SORT_ID", false);
    action.setHidden(true);
    
    loadMethod = this.regListViewLoadEvent(this.commList,"reloadCommList");
    loadMethod.addEventParam(this.sortForm,PageEvent.PARAMTYPE_QUERYVALUE, "SORT_ID");
    loadMethod.addEventParam(this.commList, PageEvent.PARAMTYPE_PAGEQUERY);
    
  }
  
  @Override
  public Layout initLayout() {
  	ViewPageLayout treeLayout = new ViewPageLayout(this);
  	treeLayout.addControl("分类列表", this.sortSelectView);
  	FormLayout formLayout = new FormLayout("", this.sortForm.getDataForm(), 2);
  	this.sortForm.getDataForm().setLayout(formLayout);
  	treeLayout.setLeftPanel("", "分类列表");
  	treeLayout.addControl("","分类明细", "分类明细","", this.sortForm);
  	treeLayout.addControl("","分类明细", "分类商品","", this.commList);
  	treeLayout.setLeftWidth("", 20);
  	treeLayout.setControlLayoutH(this.sortSelectView.getViewControl(),50);
  	treeLayout.setControlLayoutH(this.sortForm.getViewControl(), 50);
    return treeLayout;
  }
  
  public void pageLoadSort(ModelMap modelMap) {
    try {
      SortBiz biz = new SortBiz(this.getUserACL());
      ResultSet dataList = biz.querySortList(1L);	
      TreeNode treeNode = ClickUtil.getTreeNode(dataList, "SORT_ID", "P_ID", "SORT_NAME", 
    		  "SORT_ID", "SORT_ID,SORT_NAME", 1L);
      this.sortSelectView.bindData(treeNode,this.getSelfUrl(),false);
      this.sortSelectView.getTreeControl().setExpandLv(1); 
      
      this.sortForm.bindNull();
      this.commList.bindNull();
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
  public ActionResult getSortDetail(Long sortId) {
      try {
      	SortBiz biz = new SortBiz(this.getUserACL());
      	CommBiz cBiz = new CommBiz(this.getUserACL());
      	
      	Map<String,Object> sortDetailMap = biz.querySortDetail(this.sortForm.getFieldList(),sortId);
        this.sortForm.bindData(sortDetailMap);

        FormLayout formLayout = new FormLayout("", this.sortForm.getDataForm(), 2);
        this.sortForm.getDataForm().setLayout(formLayout);
        
        this.commList.bindData(cBiz.querySortCommList(new QueryPageData(),this.commList.getFieldList(),sortId));
        return this.linkView(this.sortForm,this.commList);
      } catch (BizException ex) {
          throw ex;
      } catch (Exception e) {
          e.printStackTrace();
          throw new BizException("获取分类详情失败");
      }
  }
  
  public ActionResult saveSort(Map<String, Object> sortMap){
    try {
      SortBiz biz = new SortBiz(this.getUserACL());
      String sortId = sortMap.get("SORT_ID") + "";
      String chkSortId = sortMap.get("P_ID") + "";
      
      if(sortId.equals("")){
        throw new BizException("请选择要更新的分类");
      }
      if(biz.isSelfChild(sortId, chkSortId)){
        throw new BizException("不能选择自己以及子类作为父类");
      }
      biz.saveSort(sortMap, sortMap.get("SORT_ID") == null ? true : false);
      return this.createReturnJSON(true, "修改分类成功,重新加载本页面", true, true, "sortForm","");
    } catch (BizException ex) {
       throw ex;
    }catch (Exception e) {
      e.printStackTrace();;
      throw new BizException("保存分类异常");
    }
  }
  
  public ActionResult reloadCommList(Long sortId,QueryPageData pageData) {
    try {
      CommBiz cBiz = new CommBiz(this.getUserACL());
      this.commList.bindData(cBiz.querySortCommList(pageData,this.commList.getFieldList(),sortId));
      return this.linkView(this.commList);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
  
}
