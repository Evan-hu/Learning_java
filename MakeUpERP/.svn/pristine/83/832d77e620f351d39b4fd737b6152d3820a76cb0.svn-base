package com.ga.erp.biz.system.role;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.tree.TreeControl;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;

@SuppressWarnings("serial")
public class RoleDetailPage extends UnitPage {
  private  RoleFromView formView;
  //private  PurviewTreeView purviewTreeView;
  
  @Override
  public void initController() throws Exception {

    formView = new RoleFromView("roleFrom",this.modelMap,1L);
    
    PageEvent event = this.regPageLoadEvent("pageLoad");
    formView.getFieldClass("ROLE_ID");
    event.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE,"ROLE_ID");
    
    this.formView.regAddSaveEvent("add","addRole",this, true);
    this.formView.regEditSaveEvent("save", "updateRole",this, true);
  }
  
  /**
   * 页面加载
   * @param id
   */
  public void pageLoad(Long id) {
    try {
      if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
        RoleBiz biz  = new RoleBiz(this.getUserACL());
        this.formView.bindData(biz.queryRoleDetail(id,this.formView.getFieldList()));  
        DbField field = this.formView.getFieldByCode("purviewTree");
        TreeControl tree = field.getTreeControl();
        
        RoleBiz biz1  = new RoleBiz(this.getUserACL());
        tree.setCheckValues(biz1.queryAccredit(id));
      }
    } catch(Exception e) {
    	e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"查询详细异常");
    }
  }
  
  /**
   * 添加新的角色
   */
  public ActionResult addRole(Map<String,Object> valueMap){

    try {
      valueMap.put("SHOP_ID", 1L);
      RoleBiz biz = new RoleBiz(this.getUserACL());
      biz.addRole(valueMap, this.formView.getFuncMap());
      return this.createReturnJSON(true, "新建角色成功,重新加载本页面!",true,false,"roleList","");
    } catch(Exception e) {
    	e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"新建角色异常");
    }
  }
  
  /**
   * 修改角色
   */
  public ActionResult updateRole(Map<String,Object> valueMap){
    try {
      RoleBiz biz = new RoleBiz(this.getUserACL());
      
      valueMap.put("SHOP_ID", 1L);
      biz.updateRole(valueMap, this.formView.getFuncMap());
      return this.createReturnJSON(true, "修改角色成功,重新加载本页面!",true,true,"roleList","");
    } catch(Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"修改角色异常");
    }
  }
  @Override
  public Layout initLayout() {
    // TODO Auto-generated method stub
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("",this.formView.getDataForm(),2);
    
    this.formView.getDataForm().setLayout(formLayout);
    if(this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW){
      formLayout.mergeCellField("auto", 2, 0, 2, "purviewTree");
      formLayout.mergeCellField("auto", 3, 0, 2, "NOTE");
      layout.addControl(this.formView);
    }else if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT){
      formLayout.mergeCellField("auto", 3, 0, 2, "NOTE");
      formLayout.mergeCellField("auto", 2, 0, 2, "purviewTree");
      layout.addControl("基本信息","",this.formView);
    //  layout.addControl("权限设定","",this.purviewTreeView);
    }
    return layout;
  }
}
