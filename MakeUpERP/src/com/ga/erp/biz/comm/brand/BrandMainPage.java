package com.ga.erp.biz.comm.brand;

import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;

@SuppressWarnings("serial")
public class BrandMainPage extends UnitPage{
	
  private ListView brandList;
  
  @Override
  public void initController() throws Exception {
	  
    this.brandList = new BrandListView("brandList", this.modelMap);

    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.brandList, PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.brandList.regAddAction("newBrand","新建","/comm/brand_detail.htm");
    SubmitTool.submitToDialog(action,"newBrand", "新建",910,410,this.modelMap.getPreNavInfoStr());
    
    action = this.brandList.regEditAction("editBrand","查看/编辑", "/comm/brand_detail.htm");
    SubmitTool.submitToDialog(action, "editBrand", "查看/编辑",910,410,this.modelMap.getPreNavInfoStr());
    
    this.brandList.regStateAction(this.getSelfUrl(), this, "BRAND");
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.brandList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPagedata) {
    try {
      BrandBiz biz = new BrandBiz(this.getUserACL());
      this.brandList.bindData(biz.queryBrandList(queryPagedata, this.brandList.getFieldList()));
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "页面加载异常");
    }
  }
  
}
