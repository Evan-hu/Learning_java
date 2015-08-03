package com.ga.erp.biz.supplier.commodity;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;

public class SupplierCommodityMainPage extends UnitPage {

  private static final long serialVersionUID = 1L;
  private ListView supplierCommList;
  private String  supplierId;
  
  @Override
  public void initController() throws Exception {
    
    supplierCommList = new SupplierCommodityListView("supplierCommList",this.modelMap);
    this.supplierCommList.setQueryRows(2);
    
    PageEvent event  = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.supplierCommList,PageEvent.PARAMTYPE_PAGEQUERY);
    
    supplierId=this.supplierCommList.getRequestValue("supplierId");
    
    ActionButton action = this.supplierCommList.regAddAction("addSupplierCommodity","新建", "/supplier/commodity/suppliercommodity_detail.htm");
    SubmitTool.submitToDialog(action, "addSupplierCommodity","新建",800, 350,this.modelMap.getPreNavInfoStr());
    
    action = this.supplierCommList.regEditAction("editSupplierCommodity", "查看 /编辑", "/supplier/commodity/suppliercommodity_detail.htm");
    SubmitTool.submitToDialog(action, "editSupplierCommodity","查看/编辑",800, 350,this.modelMap.getPreNavInfoStr());

    
    this.supplierCommList.regStateAction(this.getSelfUrl(), this, "SUPPLIER_COMMODITY");
    

  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.supplierCommList);
    return layout;	
  }
  
  public void pageLoad(QueryPageData queryPageData) {
    try {      
      SupplierCommodityBiz biz  = new SupplierCommodityBiz(this.getUserACL());
      this.supplierCommList.bindData(biz.querySupplierCommList(queryPageData,this.supplierCommList.getFieldList(),
          GaUtil.isNullStr(supplierId)?null:Long.parseLong(supplierId)));      
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }

  
}
