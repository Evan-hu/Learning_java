package com.ga.erp.biz.stock.inventorydiffbill;

import java.util.List;
import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.dbutil.DbUtils;
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
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.stock.inventorybill.BillBiz;
import com.ga.erp.biz.stock.inventorydiffbill.comm.CommBiz;
import com.ga.erp.biz.stock.inventorydiffbill.comm.CommListView;
import com.ga.erp.biz.store.StoreBiz;
import com.ga.erp.biz.store.storepos.PosBiz;
import com.ga.erp.biz.store.storepos.PosListView;
import com.ga.erp.biz.store.storewalletlog.WalletLogBiz;
import com.ga.erp.biz.store.storewalletlog.WalletLogListView;

public class TablePage extends MTablePage {
  
  @Override
  public void initController() throws Exception {
    super.initController();
    PageEvent event = this.regListViewLoadEvent(this.listView, "reloadDBCommList");
    event.addEventParam(this.detailView, PageEvent.PARAMTYPE_QUERYVALUE,"INVENTORY_DIFF_BILL_ID");
    event.addEventParam(this.listView, PageEvent.PARAMTYPE_PAGEQUERY);
  }
  
  @Override
  public void pageLoad(Map<String,Object> formQuery,QueryPageData pageQuery) {
    super.pageLoad(formQuery, pageQuery);
    CommBiz biz = new CommBiz(this.getUserACL());
    this.listView.bindData(biz.queryDBCommList(pageQuery,this.listView.getFieldList(),(Long) formQuery.get("INVENTORY_DIFF_BILL_ID")));
    
  }
  
  @Override
  public Layout initLayout() {    
    FormLayout formLayOut = new FormLayout("",this.detailView.getDataForm(),2);
    this.detailView.getDataForm().setLayout(formLayOut);
    ViewPageLayout layout = new ViewPageLayout(this);       
    layout.addControl("盘点差异单","",this.detailView);
    layout.addControl("盘点差异单","",this.listView);
      return layout;
  }
  
//    WalletLogBiz walletBiz = new WalletLogBiz(this.getUserACL());
//this.walletLogList.bindData(walletBiz.queryWalletLogList(new QueryPageData(),this.walletLogList.getFieldList(), storeId));

  
  @Override
  public ListView initListView() {
    // TODO Auto-generated method stub
    CommListView listView = new CommListView("tableDiFiComm",modelMap);
    listView.showQuery(false);
    ActionButton  action = listView.regEditAction("addListView", "查看", "/stock/diffcomm_detail.htm");
    SubmitTool.submitToDialog(action, "editinventoryComm", "查看", 800, 330,this.modelMap.getPreNavInfoStr());
    return listView;
  }

  @Override
  public FormView initDetailView() {
    // TODO Auto-generated method stub
    DiffBillFormView formView = new DiffBillFormView("DiBiForm",modelMap);
    List<DbField> fielList=formView.getFieldList();
    fielList.get(1).setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
    for(int i=2 ;i<fielList.size()-1;i++){
      fielList.get(i).setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    }
    return formView;
  }

  @Override
  public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
    // TODO Auto-generated method stub
    CommBiz biz = new CommBiz(this.getUserACL());
    return biz.queryDBCommList(pageParam, this.listView.getFieldList(),(Long)formParam.get("INVENTORY_DIFF_BILL_ID"));
//      return new PageResult();
  }
  @Override
  public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
    // TODO Auto-generated method stub
    DiffBillBiz biz = new DiffBillBiz(this.getUserACL());
    return biz.queryIDiffBillDetail((Long)paramMap.get("INVENTORY_DIFF_BILL_ID"));
  }

  @Override
  public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {
    try {
//      DbUtils.begin();
      // TODO 执行保存处理
      DiffBillBiz billbiz = new DiffBillBiz(this.getUserACL());
         billbiz.saveIDiffBill(formData, true);
         //TODO 保存list 
    } catch(Exception ex) {
//      DbUtils.rollback();
    }
    
  }

  public ActionResult reloadDBCommList(Long storeId, QueryPageData pageData) {
      try {
        CommBiz biz = new CommBiz(this.getUserACL());
        this.listView.bindData(biz.queryDBCommList(pageData, this.listView.getFieldList(), storeId));
        ClickUtil.setControlLayoutH(this.listView.getViewControl(), 120);
        return this.linkView(this.listView);
      } catch (BizException e) {
        throw e;
      } catch (Exception ex) {
        throw new BizException("加载页面异常");
      }
    }
  
}
