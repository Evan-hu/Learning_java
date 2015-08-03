package com.ga.erp.biz.system.purview;

import java.sql.ResultSet;
import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.TreeView;
import com.ga.click.mvc.UnitPage;
import com.ga.click.util.ClickUtil;

@SuppressWarnings("serial")
public class PurviewMainPage extends UnitPage{
  private TreeView purviewTreeView;
  private FormView purviewFormView;
  
  @SuppressWarnings("unused")
  @Override
  public void initController() throws Exception {
    // TODO Auto-generated method stub
    this.purviewTreeView = new PurviewTreeView("purviewTree",this.modelMap);
    this.purviewFormView = new PurviewFormView("purviewFormView", this.modelMap);
    //注册加载事件(不需任何参数)
    PageEvent loadMethod = this.regPageLoadEvent("pageLoad");
    
    //树点击事件(将树节点点击事件以按钮方式定义,id必须同树视图ID一致)
    ActionButton action = purviewTreeView.regClickEvent(this.getSelfUrl(), "getSortDetail", this);
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr(),this.purviewFormView);
    action.getEvent().addEventParam(this.purviewFormView,PageEvent.PARAMTYPE_QUERYVALUE,"PURVIEW_ID");
    
    //新建子分类按钮(不触发事件)
    action  = this.purviewFormView.regAddEventByOpenDialog("newPurview","新建子权限","/system/purview_new.htm", this);
    SubmitTool.submitToDialog(action, "newPurviewDialog","新建子权限",640, 450,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.purviewFormView,"P_ID","PURVIEW_ID",false);
    action.bindViewParam(this.purviewFormView,"P_NAME","PURVIEW_NAME",false);    
    //分类保存事件
    this.purviewFormView.regEditSaveEvent("savePurview", "savePurview", this,false);
  }
  
  /**
   * 页面初始化加载
   * @param modelMap
   */
  public void pageLoad(ModelMap modelMap) {
    try {
      PurviewBiz biz =new PurviewBiz(this.getUserACL());
      ResultSet  dataList = biz.queryPurviewList(1L,true);
      // 将查询结果转为node;
      TreeNode treeNode = ClickUtil.getTreeNode(dataList, "PURVIEW_ID", "P_ID", "PURVIEW_NAME", "PURVIEW_ID", "PURVIEW_ID,PURVIEW_NAME", 1L);
      //绑定视图数据
      this.purviewTreeView.bindData(treeNode, this.getSelfUrl(), false);
      this.purviewTreeView.getTreeControl().setExpandLv(1);   
//      Map<String,Object> detailInfo = biz.queryPurviewDetail(this.purviewFormView.getFieldList(),1L);
//      this.purviewFormView.bindData(detailInfo);
      this.purviewFormView.bindNull();
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "查询系统编码明细异常");
    }
  }
  
  /**
   * 获取指定的明细信息
   * (刷新所有明细显示区域)
   * @param id :id
   * @return
   */
  public ActionResult getSortDetail(Long id) {
    try {      
      //为视图添加按钮
      //相关查询
      PurviewBiz biz = new PurviewBiz(this.getUserACL());
      Map<String,Object> detailInfo = biz.queryPurviewDetail(this.purviewFormView.getFieldList(),id);
      //绑定视图
      this.purviewFormView.bindData(detailInfo);
      //设置视图控件高度
      ClickUtil.setControlLayoutH(this.purviewFormView.getViewControl(),50);
      //因为此事件只初始化了一个sortFormView视图,因此只有sortFormView解析了参数，而其他列表视图的单独刷新需要相关参数，因此通过下面语句获取参数并进行传递 
      //返回视图解析结
      return this.linkView(this.purviewFormView);
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询详细异常");
    }
  }
  
  /**
   * 权限保存处理
   * @param viewMap 视图的参数
   * @return
   */
  public ActionResult savePurview(Map<String,Object> viewMap) {
    try {
      //相关查询
      PurviewBiz biz = new PurviewBiz(this.getUserACL());
      Long purviewId = (Long)viewMap.get("PURVIEW_ID");
      Long chkID = (Long)viewMap.get("P_ID");
      if (biz.isSelfChild(purviewId, chkID)) {
        throw new BizException("不能选中自己下级作为上级");
      }
      if(viewMap.get("PURVIEW_ID") == null){
        biz.saveNewPurview(viewMap, this.purviewFormView.getFuncMap());
      } else {
        biz.updatePurview(viewMap, this.purviewFormView.getFuncMap(),this.purviewFormView.getPKField().getFieldCode());
      }
      return this.createReturnJSON(true, "保存权限信息成功,重新加载本页面！",true,true,"","");
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"保存权限信息异常！");
    }
  }
  
  @Override
  public Layout initLayout() {
    // TODO Auto-generated method stub
    ViewPageLayout treeLayout = new ViewPageLayout(this);
    treeLayout.addControl("管理权限列表", this.purviewTreeView);
    treeLayout.setLeftPanel("", "管理权限列表");
    treeLayout.addControl("","详细", "权限信息", "", this.purviewFormView);
    
    treeLayout.setLeftWidth("", 20);
    treeLayout.setControlLayoutH(this.purviewTreeView.getViewControl(),50);
    treeLayout.setControlLayoutH(this.purviewFormView.getViewControl(),50);
    
    return treeLayout;
  }

}
