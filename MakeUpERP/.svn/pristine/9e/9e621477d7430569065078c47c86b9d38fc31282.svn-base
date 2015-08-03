package com.ga.erp.biz.system.area.catalogue;

import java.util.Map;
import org.apache.click.ActionResult;
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
import com.ga.erp.biz.system.area.cataloguecomm.CatalogueCommBiz;
import com.ga.erp.biz.system.area.cataloguecomm.CatalogueCommListView;

@SuppressWarnings("serial")
public class CatalogueDetailPage extends UnitPage {

  private FormView catalogueFormView;
  private ListView catalogueCommListView;

  @Override
  public void initController() throws Exception {

    this.catalogueFormView = new CatalogueFormView("catalogueFormView", this.modelMap);
    this.catalogueCommListView = new CatalogueCommListView("catalogueCommListView", this.modelMap);
    this.catalogueCommListView.setQueryRows(2);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.catalogueFormView, PageEvent.PARAMTYPE_QUERYVALUE, "CATALOGUE_ID");
    event.addEventParam(this.catalogueCommListView,PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = new ActionButton(this.getClass(), "saveCatalogue", "保存", this.getSelfUrl(), null);
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    
    event = this.regPageEvent(action, "saveCatalogue");
    event.addEventParam(this.catalogueFormView, PageEvent.PARAMTYPE_DATAMAP);
    
    action.bindForm(this.catalogueFormView.getViewID());
    this.catalogueFormView.regAction(action);
    
    action = this.catalogueCommListView.regAddAction("addCatalogueComm","新建", "/system/catalogue_comm_detail.htm");
    SubmitTool.submitToDialog(action, "addCatalogueComm","新建",700, 180,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.catalogueFormView,"CATALOGUE_ID","CATALOGUE_ID",false);
    
    action = this.catalogueCommListView.regEditAction("editCatalogueComm", "查看 /编辑", "/system/catalogue_comm_detail.htm");
    SubmitTool.submitToDialog(action, "editCatalogueComm","查看/编辑",800, 200,this.modelMap.getPreNavInfoStr());
  
    event = this.regListViewLoadEvent(this.catalogueCommListView, "reloadCatalogueCommList");
    event.addEventParam(this.catalogueCommListView,PageEvent.PARAMTYPE_PAGEQUERY);
    event.addEventParam(this.catalogueFormView, PageEvent.PARAMTYPE_QUERYVALUE, "CATALOGUE_ID");
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("",this.catalogueFormView.getDataForm(), 2);
    this.catalogueFormView.getDataForm().setLayout(formLayout);
    layout.addControl(catalogueFormView);
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        formLayout.mergeCellField("auto", 2, 0, 1, "NOTE");
        layout.addControl("基本信息","",catalogueFormView);
        layout.addControl("采购目录商品", "", catalogueCommListView);
    }else{
        formLayout.mergeCellField("auto", 1, 0, 1, "NOTE");
    }
    return layout;
  }

  public void pageLoad(Long catalogueId, QueryPageData pageData) {
    try {   
    if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
    	this.catalogueFormView.bindData(this.catalogueFormView.getViewParam());
    }else {
      CatalogueBiz biz = new CatalogueBiz(this.getUserACL());
      CatalogueCommBiz cBiz = new CatalogueCommBiz(this.getUserACL());
      this.catalogueFormView.bindData(biz.querySupplierMap(catalogueId));
      this.catalogueCommListView.bindData(cBiz.queryCatalogueCommList(pageData, this.catalogueCommListView.getFieldList(), catalogueId));
      }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }

  public ActionResult saveCatalogue(Map<String, Object> valuesMap) {
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        CatalogueBiz biz = new CatalogueBiz(this.getUserACL());
        biz.saveCatalogue(valuesMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        CatalogueBiz biz = new CatalogueBiz(this.getUserACL());
        biz.saveCatalogue(valuesMap, false);
      }
      return this.createReturnJSON(true, "保存采购目录成功", true, false, "catalogueListView", "");
  }
  
  public ActionResult reloadCatalogueCommList(QueryPageData pageData, Long catalogueId) {
		
	  try {
			CatalogueCommBiz biz = new CatalogueCommBiz(this.getUserACL());
		    this.catalogueCommListView.bindData(biz.queryCatalogueCommList(pageData, this.catalogueCommListView.getFieldList(), catalogueId));
		    return this.linkView(this.catalogueCommListView);
		  } catch(BizException e) {
		     throw e;
		  } catch(Exception ex) {
		     throw new BizException("加载页面异常");
		 }
	  }
}
