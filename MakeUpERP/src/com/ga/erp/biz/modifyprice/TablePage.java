package com.ga.erp.biz.modifyprice;

import java.util.List;
import java.util.Map;

import org.apache.click.ActionResult;

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
import com.ga.erp.biz.modifyprice.comm.CommBiz;
import com.ga.erp.biz.modifyprice.comm.CommListView;
import com.ga.erp.biz.stock.inventorybill.BillBiz;
public class TablePage extends MTablePage {
  
  private static final long serialVersionUID = 1L;
  private String objType;
@Override
  public void initController() throws Exception {
    super.initController();
    
    PageEvent event = this.regListViewLoadEvent(this.listView, "modifyPriceComm");
   
    event.addEventParam(this.detailView, PageEvent.PARAMTYPE_QUERYVALUE,"MODIFY_PRICE_ID");
    event.addEventParam(this.listView, PageEvent.PARAMTYPE_PAGEQUERY);
   
   
  }
  
  @Override
  public void pageLoad(Map<String,Object> formQuery,QueryPageData pageQuery) {
    
    super.pageLoad(formQuery, pageQuery);
    CommBiz comBiz = new CommBiz(this.getUserACL());
    this.listView.bindData(comBiz.queryMoPricCommList(pageQuery,this.listView.getFieldList(),(Long) formQuery.get("MODIFY_PRICE_ID")));
    
  }
  
  @Override
  public Layout initLayout() {    
    FormLayout formLayOut = new FormLayout("",this.detailView.getDataForm(),2);
    this.detailView.getDataForm().setLayout(formLayOut);
    ViewPageLayout layout = new ViewPageLayout(this);       
    layout.addControl("价格调整单","",this.detailView);
    layout.addControl("价格调整单","",this.listView);
      return layout;
  }
  
//    WalletLogBiz walletBiz = new WalletLogBiz(this.getUserACL());
//this.walletLogList.bindData(walletBiz.queryWalletLogList(new QueryPageData(),this.walletLogList.getFieldList(), storeId));

  
  @Override
  public ListView initListView() {
      // TODO Auto-generated method stub
        CommListView listView = new CommListView("tabledbCommList",modelMap);
        objType=listView.getRequestValue("objType");
        removeDbfiel(listView);
        listView.showQuery(false);
  //    listView.setViewEditMode(GaConstant.EDITMODE_EDIT);
  //    listView.regCreateRowAction("asdfasd","adfasdf");
        ActionButton action = listView.regAddAction("addListView", "新增", "/modifyprice/comm_detal.htm");
        if(this.modelMap.getPageEditMode()==GaConstant.EDITMODE_NEW){
        action.setHidden(true);
        }
//        action.setClickJS("removeMPObj()");
        SubmitTool.submitToDialog(action, "newmdComm", "新增", 800, 300,this.modelMap.getPreNavInfoStr());
        
        action = listView.regEditAction("editListView", "修改", "/modifyprice/comm_detal.htm?billType");
        SubmitTool.submitToDialog(action, "editmdComm", "修改", 800, 300,this.modelMap.getPreNavInfoStr());
        //删除处理
        ActionButton button = new ActionButton(this.getClass(),"delList","删除","",null); //       
        button.setOnClick("delMTableRow('"+listView.getViewID()+"List')");
        button.bindTableRowData(listView.getViewID());
        SubmitTool.submitToNavtab(button,"",this.modelMap.getPreNavInfoStr());
        listView.regAction(button);
        return listView;
  }

  /**
   * 根据调整单类型移除不需要的列
   * 
   */
  private void removeDbfiel(CommListView listView) {
    
//1 供应商 2、dc  3 门店
   if("1".equals(objType)){

     listView.removeDbField("OLD_MEMBER_PRICE");
     listView.removeDbField("MEMBER_PRICE");
     listView.removeDbField("OLD_SELL_PRICE");
     listView.removeDbField("SELL_PRICE");
     listView.removeDbField("OLD_SEND_PRICE");
     listView.removeDbField("SEND_PRICE");
     listView.removeDbField("OLD_TRADE_PRICE");
     listView.removeDbField("TRADE_PRICE");
    
   }
   else if("2".equals(objType)){
     
   }else{
     
   
     listView.removeDbField("OLD_PURCHASE_PRICE");
     listView.removeDbField("PURCHASE_PRICE");
     listView.removeDbField("OLD_SEND_PRICE");
     listView.removeDbField("SEND_PRICE");
     listView.removeDbField("OLD_TRADE_PRICE");
     listView.removeDbField("TRADE_PRICE");
   }
  }

  @Override
  public FormView initDetailView() {
    // TODO Auto-generated method stub
    ModifyPriceFormView formView = new ModifyPriceFormView("modPriceForm",modelMap);
    objType=listView.getRequestValue("objType");
    return formView;
  }

  @Override
  public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
    // TODO Auto-generated method stub
    CommBiz biz = new CommBiz(this.getUserACL());
  //根据条件单类型 remove列
    return   biz.queryMoPricCommList(pageParam,this.listView.getFieldList(), (Long)formParam.get("MODIFY_PRICE_ID"));
  
  }

  @Override
  public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
  
    Map<String, Object> map =null;
    //根据价格调整单的类型 改变 对象的选择
    if (this.getModelMap().getPageEditMode() == GaConstant.EDITMODE_NEW) {
    if("1".equals(objType)){
      
      this.detailView.getFieldByCode("OBJECT_ID").setPopSelect("commSelect", "SUPPLIER_ID", false);
      this.detailView.getFieldByCode("OBJECT_NAME").setPopSelect("commSelect","SUPPLIER_NAME",true,
        "/supplier/supplier_main.htm",
        "SUPPLIER_ID,SUPPLIER_NAME,cid_supplierList,callback_setmpObjId",800,400);
    }else if("2".equals(objType)){
      this.detailView.getFieldByCode("OBJECT_ID").setPopSelect("commSelect", "STOCK_ID", false);
      this.detailView.getFieldByCode("OBJECT_NAME").setPopSelect("commSelect","NAME",true,
          "/stock/stock_main.htm",
          "STOCK_ID,NAME,cid_stockList,callback_setmpObjId",800,400);
    }else{
      this.detailView.getFieldByCode("OBJECT_ID").setPopSelect("commSelect", "STORE_ID", false);
      this.detailView.getFieldByCode("OBJECT_NAME").setPopSelect("commSelect","STORE_NAME",true,
          "/store/store_main.htm",
          "STORE_ID,STORE_NAME,cid_storeList,callback_setmpObjId",800,400); 
    }
    }
    
    //如果是修改 则绑定数据
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
      ModifyPriceBiz biz = new ModifyPriceBiz(this.getUserACL());
     map = biz.queryModifyPriceDetail((Long)paramMap.get("MODIFY_PRICE_ID"));
 
    if(map!=null){
     
   
    String objectId=map.get("OBJECT_ID")+"";
    this.listView.getActionByID("addListView").addParam("objectID", objectId);
    this.listView.getActionByID("addListView").addParam("billType", objType);
    this.listView.getActionByID("editListView").addParam("objectID", objectId);
    this.listView.getActionByID("editListView").addParam("billType", objType);
    }
    }
    return map;
  }

  @Override
  public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {
    try {
          
      DbUtils.begin();
    //保存
      
      DbUtils.commit(); 
    } catch(Exception ex) { 
      DbUtils.rollback(); 
     
    }
  } 

  public ActionResult reloadInventoryCommList(Long MoPriceId, QueryPageData pageData) {
      try {
        CommBiz commbiz = new CommBiz(this.getUserACL());
        this.listView.bindData(commbiz.queryMoPricCommList(pageData,this.listView.getFieldList(),MoPriceId));
        ClickUtil.setControlLayoutH(this.listView.getViewControl(), 120);
        return this.linkView(this.listView);
      } catch (BizException e) {
        throw e;
      } catch (Exception ex) {
        throw new BizException("加载页面异常");
      }
    }
  
}
