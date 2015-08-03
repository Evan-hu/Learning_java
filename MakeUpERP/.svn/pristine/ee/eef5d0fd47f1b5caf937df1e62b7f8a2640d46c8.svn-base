package com.ga.erp.biz.stock.inventorybill;

import java.util.List;
import java.util.Map;

import javax.management.monitor.GaugeMonitor;

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
import com.ga.erp.biz.stock.inventorybill.comm.CommBiz;
import com.ga.erp.biz.stock.inventorybill.comm.CommListView;

public class TablePage extends MTablePage {
  
  private static final long serialVersionUID = 1L;

@Override
  public void initController() throws Exception {
    super.initController();
    
    PageEvent event = this.regListViewLoadEvent(this.listView, "reloadInventoryCommList");
    event.addEventParam(this.detailView, PageEvent.PARAMTYPE_QUERYVALUE,"INVENTORY_BILL_ID");
    event.addEventParam(this.listView, PageEvent.PARAMTYPE_PAGEQUERY);
    
    
  }
  
  @Override
  public void pageLoad(Map<String,Object> formQuery,QueryPageData pageQuery) {
    super.pageLoad(formQuery, pageQuery);
    
    CommBiz comBiz = new CommBiz(this.getUserACL());
    this.listView.bindData(comBiz.queryICommList(pageQuery,this.listView.getFieldList(),(Long) formQuery.get("INVENTORY_BILL_ID")));
    
  }
  
  @Override
  public Layout initLayout() {    
    FormLayout formLayOut = new FormLayout("",this.detailView.getDataForm(),2);
    this.detailView.getDataForm().setLayout(formLayOut);
    ViewPageLayout layout = new ViewPageLayout(this);       
    layout.addControl("盘点单","",this.detailView);
    layout.addControl("盘点单","",this.listView);
      return layout;
  }
  
//    WalletLogBiz walletBiz = new WalletLogBiz(this.getUserACL());
//this.walletLogList.bindData(walletBiz.queryWalletLogList(new QueryPageData(),this.walletLogList.getFieldList(), storeId));

  
  @Override
  public ListView initListView() {
      // TODO Auto-generated method stub
        CommListView listView = new CommListView("tableICommList",modelMap);
        listView.showQuery(false);
  //    listView.setViewEditMode(GaConstant.EDITMODE_EDIT);
  //    listView.regCreateRowAction("asdfasd","adfasdf");
        ActionButton action = listView.regAddAction("addListView", "新增", "/stock/billcomm_detail.htm");
        if(this.modelMap.getPageEditMode()==GaConstant.EDITMODE_NEW){
          action.setHidden(true);
          }
        SubmitTool.submitToDialog(action, "newinventoryComm", "新增", 800, 170,this.modelMap.getPreNavInfoStr());
        
        
        action = listView.regEditAction("editListView", "修改", "/stock/billcomm_detail.htm");
        SubmitTool.submitToDialog(action, "editinventoryComm", "修改", 800, 270,this.modelMap.getPreNavInfoStr());
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
    
    BillFormView formView = new BillFormView("inventoryBillForm",modelMap);
    return formView;
  }

  @Override
  public PageResult loadListData(Map<String,Object> formParam,QueryPageData pageParam) {
    // TODO Auto-generated method stub
    CommBiz biz = new CommBiz(this.getUserACL());
    return   biz.queryICommList(pageParam,this.listView.getFieldList(), (Long)formParam.get("INVENTORY_BILL_ID"));
  
  }

  @Override
  public Map<String, Object> loadDetailData(Map<String, Object> paramMap) {
    // TODO Auto-generated method stub
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
      List<DbField>   filedList=this.detailView.getFieldList();
      for(int i=1;i<filedList.size()-1;i++){
        
        filedList.get(i).setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
      }
    }
    BillBiz biz = new BillBiz(this.getUserACL());
    Map<String, Object> map = biz.queryInventoryBillDetail((Long)paramMap.get("INVENTORY_BILL_ID"));
    if(map!=null){
    String type = map.get("TYPE") + "";
   
    String inBatchID=map.get("INVENTORY_BATCH_ID")+"";
    this.listView.getActionByID("addListView").addParam("inBatchId", inBatchID);
    this.listView.getActionByID("addListView").addParam("batchType", type);
    this.listView.getActionByID("editListView").addParam("inBatchId", inBatchID);
    this.listView.getActionByID("editListView").addParam("batchType", type);
    }
    return map;
  }

  @Override
  public void saveData(int editMode, Map<String, Object> formData, List<Map<String, Object>> listData, String delListID) {
    try {
      //新建 和编辑  增加判断存储类型。
      
      DbUtils.begin();
      CommBiz commbiz = new CommBiz(this.getUserACL());
      BillBiz billbiz = new BillBiz(this.getUserACL());
    //如果为单品盘点，则listDate(盘点商品)中只能存在一个值  
     if("2".equals(formData.get("SCOPE"))){
       if(listData.size()>1){
         throw new BizException("该盘点批次为单品盘点，只能盘点一个商品!"); 
       }
     }
      
      if(GaUtil.isNullStr(formData.get("INVENTORY_BILL_ID")+"")){// TODO 执行添加保存处理
        
      billbiz.saveInventoryBill(formData, true);
      
      Long billId=billbiz.queryId(formData.get("INVENTORY_FLOW_NUM")+"");
      
//      for(Map<String ,Object> map:listData){
//        map.put("INVENTORY_BILL_ID",billId);
//        commbiz.saveIComm(map, true);
//        }
      }else{//执行更新操作
 
      billbiz.saveInventoryBill(formData, false);
      Long billId=billbiz.queryId(formData.get("INVENTORY_FLOW_NUM")+"");
//      for(Map<String ,Object> map:listData){
//        map.put("INVENTORY_BILL_ID",billId);
//        if(GaUtil.isNullStr(map.get("INVENTORY_COMM_ID")+"")){
//          commbiz.saveIComm(map, true);
//        }else{
//          commbiz.saveIComm(map, false);
//        }
//      }
      }
      //  delId 的存储方式？
      if(!GaUtil.isNullStr(delListID)){
     String[] delId =delListID.split(",");
     for(String id:delId){
     DbUtils.del("INVENTORY_COMM", "INVENTORY_COMM_ID", Long.parseLong(id));
     }
      }
      DbUtils.commit(); 
    } catch(Exception ex) { 
      DbUtils.rollback(); 
     
    }
  } 

  public ActionResult reloadInventoryCommList(Long billId, QueryPageData pageData) {
      try {
        CommBiz commbiz = new CommBiz(this.getUserACL());
        this.listView.bindData(commbiz.queryICommList(pageData,this.listView.getFieldList(),billId));
        ClickUtil.setControlLayoutH(this.listView.getViewControl(), 120);
        return this.linkView(this.listView);
      } catch (BizException e) {
        throw e;
      } catch (Exception ex) {
        throw new BizException("加载页面异常");
      }
    }
  
}
