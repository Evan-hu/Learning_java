package com.ga.erp.biz.comm.brand;

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
import com.ga.click.util.ClickUtil;
import com.ga.erp.biz.comm.CommBiz;
import com.ga.erp.biz.comm.CommListView;

public class BrandDetailPage extends UnitPage {
	
  private static final long serialVersionUID = 1L;
  private FormView brandForm;
  private ListView commList;
  
  @Override
  public void initController() throws Exception {
	  
    this.brandForm = new BrandFormView("brandForm",this.modelMap);  
    this.commList = new CommListView("commList", this.modelMap);
    this.commList.showPage(false);
    this.commList.showQuery(false);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.brandForm, PageEvent.PARAMTYPE_QUERYVALUE, "BRAND_ID");
    
    ActionButton action  = new ActionButton(this.getClass(),"saveBrand","保存",this.getSelfUrl(),null);
    action.bindForm(this.brandForm.getViewID(),true);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    this.brandForm.regAction(action);
    
    event = this.regPageEvent(action, "saveBrand");
    event.addEventParam(this.brandForm, PageEvent.PARAMTYPE_DATAMAP);
    
    event = this.regListViewLoadEvent(this.commList, "reloadCommList");
    event.addEventParam(this.brandForm, PageEvent.PARAMTYPE_QUERYVALUE,"BRAND_ID");
    event.addEventParam(this.commList, PageEvent.PARAMTYPE_PAGEQUERY);
  }

  @Override
  public Layout initLayout() {
  	FormLayout formLayOut = new FormLayout("",this.brandForm.getDataForm(),2);
  	this.brandForm.getDataForm().setLayout(formLayOut);
  	ViewPageLayout layout = new ViewPageLayout(this);
  	layout.addControl("基本信息", "", this.brandForm);
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
      layout.addControl("品牌商品", "", this.commList);
    }
    return layout;
  }

  public void pageLoad(Long brandId) {
	try {   
	  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		  BrandBiz biz = new BrandBiz(this.getUserACL()); 
		  this.brandForm.bindData(biz.queryBrandDetail(brandId));
		  
		  CommBiz cBiz = new CommBiz(this.getUserACL());
      this.commList.bindData(cBiz.queryBrandCommList(new QueryPageData(),this.commList.getFieldList(), brandId));
       }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
  
  public ActionResult saveBrand(Map<String, Object> valueMap) {
	    BrandBiz biz = new BrandBiz(this.getUserACL());
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        biz.saveBrand(valueMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        biz.saveBrand(valueMap, false);
      }
      return this.createReturnJSON(true, "保存成功", true, false, "brandList", "");
  }
  
  public ActionResult reloadCommList(Long brandId, QueryPageData pageData) {
    try {
      CommBiz cBiz = new CommBiz(this.getUserACL());
      this.commList.bindData(cBiz.queryBrandCommList(pageData, this.commList.getFieldList(), brandId));
      ClickUtil.setControlLayoutH(this.commList.getViewControl(), 120);
      return this.linkView(this.commList);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
  
}
