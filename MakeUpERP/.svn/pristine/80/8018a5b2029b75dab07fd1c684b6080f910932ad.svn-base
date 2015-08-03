package com.ga.erp.biz.system.role;

import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;

@SuppressWarnings("serial")
public class RoleMainPage extends UnitPage {
  
 private RoleListView listView;
 int type = 1;
  @Override
  public void initController() throws Exception {
    // TODO Auto-generated method stub
    String typeStr = this.modelMap.getRequest().getParameter("type");
    if (!GaUtil.isNullStr(typeStr) ) {
      this.type = Integer.parseInt(typeStr);
    }
    
    this.listView = new RoleListView("roleList",this.modelMap);
    PageEvent event = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.listView, PageEvent.PARAMTYPE_PAGEQUERY);
    event.addEventParam(this.listView, PageEvent.PARAMTYPE_QUERYMAP);
    
    if(this.type == 1){
      ActionButton action = this.listView.regAddAction("newRole","新建","/system/role_detail.htm");
      SubmitTool.submitToDialog(action,"newRole", "新建角色",500,600,this.modelMap.getPreNavInfoStr());
      
      action = this.listView.regEditAction("editRole","查看/编辑", "/system/role_detail.htm");
      SubmitTool.submitToDialog(action,"editRole", "查看/编辑",500,600,this.modelMap.getPreNavInfoStr());  
    }else if(this.type == 2){
      this.listView.setMultiSelect(true);
      ActionButton action  = new ActionButton(this.getClass(),"addOpRole","选择角色",this.getSelfUrl(),null);
      SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
      action.bindTableMultiSelect(this.listView.getViewID());
      action.setConfirm("确认增加选中角色吗?");
      event = this.regPageEvent(action,"addOpRole");
      event.addEventParam(this.listView,PageEvent.PARAMTYPE_REQUEST,GaConstant.FIXPARAM_MULTISELECT);      
      event.addEventRequestParam(listView,"OP_ID");
      this.listView.regAction(action);
    }
  }
  
  /**
   * 执行页面加载查询
   * @param queryData 查询数据
   */
  public void pageLoad(QueryPageData queryData,Map<String,Object> queryMap) {
    try {      
      RoleBiz biz = new RoleBiz(this.getUserACL());
      if(this.type == 1){
        this.listView.bindData(biz.queryRoleList(queryData, this.listView.getFieldList()));
      }
      if(this.type == 2){
        Long opID = (Long)queryMap.get("OP_ID");
        this.listView.bindData(biz.queryOpRoleList(queryData, this.listView.getFieldList(), opID));
        this.listView.addHiddenParam("OP_ID", opID);
        if (this.listView.getActionByID("addOpRole") != null) {
          this.listView.getActionByID("addOpRole").addParam("OP_ID", opID);  
        } 
      }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询角色链接列表异常");
    }
  }
  /**
   * 新增操作员角色
   * @param idS
   * @return
   */
  public ActionResult addOpRole(String ids,String opID){
    if(GaUtil.isNullStr(ids)){
      throw new BizException("未选中数据!");
    }
    if(!GaUtil.isNumber(opID)){
      throw new BizException("操作员ID有误！");
    }
    try {
      RoleBiz biz = new RoleBiz(this.getUserACL());
      biz.addOpRole(opID, ids);
      return this.createReturnJSON(true, "添加管理员角色成功,请重新加载本页面!",true,false,"opRoleListView","");
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"添加管理员角色异常！");
    }
  }
  
  @Override
  public Layout initLayout() {
    // TODO Auto-generated method stub
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.listView);
    layout.setControlLayoutH(listView.getViewControl(), 0);
    return layout;
  }
  

}
