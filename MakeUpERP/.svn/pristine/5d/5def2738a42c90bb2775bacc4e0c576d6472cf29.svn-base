package com.ga.erp.biz.comm;

import java.util.HashMap;
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
import com.ga.erp.biz.store.StoreBiz;
import com.ga.erp.biz.store.StoreListView;
import com.ga.erp.biz.supplier.SupplierBiz;
import com.ga.erp.biz.supplier.SupplierListView;

public class CommDetailPage extends UnitPage {
	
  private static final long serialVersionUID = 1L;
  private FormView commForm;
  private ListView storeList;
  private ListView supplierListView;
  
  @Override
  public void initController() throws Exception {
	  
    this.commForm = new CommFormView("commForm",this.modelMap);
    this.storeList = new StoreListView("storeList", this.modelMap);
    this.supplierListView = new SupplierListView("supplierList",this.modelMap);
    this.supplierListView.showPage(false);
    this.supplierListView.showQuery(false);
    
    
    PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.commForm, PageEvent.PARAMTYPE_QUERYVALUE, "COMMODITY_ID");
    
    ActionButton action = new ActionButton(this.getClass(),"saveComm","保存商品",this.getSelfUrl(),null);
    action.bindForm(this.commForm.getViewID(),true);
    SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
    
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    this.commForm.regAction(action);
    
    event = this.regPageEvent(action,"saveComm");
    event.addEventParam(this.commForm, PageEvent.PARAMTYPE_DATAMAP);
    
    event = this.regListViewLoadEvent(this.storeList, "reloadStoreList");
    event.addEventParam(this.commForm, PageEvent.PARAMTYPE_QUERYVALUE,"COMMODITY_ID");
    event.addEventParam(this.storeList, PageEvent.PARAMTYPE_PAGEQUERY);
    event.addEventParam(this.storeList, PageEvent.PARAMTYPE_QUERYMAP);
    
    event = this.regListViewLoadEvent(this.supplierListView, "reloadSupList");
    event.addEventParam(this.commForm, PageEvent.PARAMTYPE_QUERYVALUE,"COMMODITY_ID");
    event.addEventParam(this.supplierListView, PageEvent.PARAMTYPE_PAGEQUERY);
  }

  @Override
  public Layout initLayout() { 
  	
  	FormLayout formLayout = new FormLayout("",this.commForm.getDataForm(),3);
//  	formLayout.setGroupFields("基本信息", "COMMODITY_NAME,TYPE,COMMODITY_SNAME,CODE," +
//  			"CUSTOM_CODE,MNEMONIC_CODE,SORT_NAME,NAME,UNIT,ADDR,SPEC,STOCK_SPEC", 3);
//  	formLayout.setGroupFields("价钱信息", "TRADE_PRICE,PURCHASE_PRICE,SEND_PRICE,SELL_PRICE,MEMBER_PRICE", 3);
//  	if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT){
//  		formLayout.setGroupFields("数量信息", "PURCHASE_AMOUNT,SEND_AMOUNT,SELL_AMOUNT,RETURN_AMOUNT,LOSS_AMOUNT", 3);
//  		formLayout.setGroupFields("状态信息", "TRUENAME,CREATE_TIME,LAST_OP_NAME,LAST_TIME,STATE,PURCHASE_STATE", 3);
//  	}

   	this.commForm.getDataForm().setLayout(formLayout);

  	ViewPageLayout layout = new ViewPageLayout(this);
  	if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW) {
       layout.addControl(this.commForm);
       formLayout.mergeCellField("auto", 6, 0, 4, "NOTE");
    } else {
      layout.addControl("商品基本信息", "", this.commForm);
      formLayout.mergeCellField("auto", 10, 0, 4, "NOTE");
      layout.addControl("商品销售门店", "", this.storeList);
      layout.addControl("商品供应商", "", this.supplierListView);
    }
  	return layout;
  }

  public void pageLoad(Long commId) {
  	try {
  		CommBiz biz = new CommBiz(this.getUserACL());
      Map<String, Object> valuesMap = biz.queryCommDetail(this.commForm.getFieldList(), commId);
      this.commForm.bindData(valuesMap);
        
      StoreBiz sBiz = new StoreBiz(this.getUserACL());
      this.storeList.bindData(sBiz.queryStoreList(new QueryPageData(), new HashMap<String, Object>(), storeList.getFieldList(), commId));
      
      SupplierBiz supBiz = new SupplierBiz(this.getUserACL());
      this.supplierListView.bindData(supBiz.querySupListMap(this.supplierListView.getFieldList(), commId));
      
  	 } catch(BizException ex) {
        throw ex;
     } catch(Exception e) {
        throw new BizException(BizException.SYSTEM,"页面加载异常");
     }
  }
  
  public ActionResult saveComm(Map<String, Object> commMap /*,Map<String,Object> extendsMap*/) {
	    CommBiz biz = new CommBiz(this.getUserACL());
      if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
        biz.saveComm(commMap, true);
      } else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        biz.saveComm(commMap, false);
      }
      return this.createReturnJSON(true, "保存成功", true, false, "commList", "");
  }
  
  public ActionResult reloadStoreList(Long commId, QueryPageData pageData, Map<String, Object> valueMap) {
    try {
      StoreBiz sBiz = new StoreBiz(this.getUserACL());
      this.storeList.bindData(sBiz.queryStoreList(pageData, valueMap, this.storeList.getFieldList(), commId));
      ClickUtil.setControlLayoutH(this.storeList.getViewControl(), 150);
      return this.linkView(this.storeList);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
  
  public ActionResult reloadSupList(Long commId, QueryPageData pageData) {
    try {
      SupplierBiz supBiz = new SupplierBiz(this.getUserACL());
      this.supplierListView.bindData(supBiz.querySuppList(pageData,this.supplierListView.getFieldList(), commId));
      return this.linkView(this.supplierListView);
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException("加载页面异常");
    }
  }
}
