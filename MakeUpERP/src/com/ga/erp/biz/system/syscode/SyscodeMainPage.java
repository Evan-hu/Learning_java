package com.ga.erp.biz.system.syscode;

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

public class SyscodeMainPage extends UnitPage{
	
  private static final long serialVersionUID = 1L;
  private TreeView syscodeTreeView;
  private FormView syscodeForm;
  
  @Override
  public void initController() throws Exception {

	this.syscodeTreeView = new SyscodeTreeView("syscodeTree",this.modelMap);
    this.syscodeForm = new SyscodeFormView("syscodeForm", this.modelMap);
    this.regPageLoadEvent("pageLoad");
    
    //树点击事件(将树节点点击事件以按钮方式定义,id必须同树视图ID一致)
    ActionButton action = syscodeTreeView.regClickEvent(this.getSelfUrl(), "getSortDetail", this);
    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr(), this.syscodeForm);
    action.getEvent().addEventParam(this.syscodeForm,PageEvent.PARAMTYPE_QUERYVALUE,"SYS_CODE_ID");
    
    //新建子分类按钮(不触发事件)
    action  = this.syscodeForm.regAddEventByOpenDialog("newSort","新建子编码","/system/syscode_detail.htm", this);
    SubmitTool.submitToDialog(action, "newSyscode","新建子编码",540, 340,this.modelMap.getPreNavInfoStr());
    action.bindViewParam(this.syscodeForm,"P_CODE","SYS_CODE_ID",false);
    action.bindViewParam(this.syscodeForm,"P_NAME","SYS_CODE_NAME",false);    
    //分类保存事件
    this.syscodeForm.regEditSaveEvent("saveSyscode", "saveSyscode", this,false);
  }
  
  /**
   * 页面初始化加载
   * @param modelMap
   */
  public void pageLoad(ModelMap modelMap) {
    try {
      SysCodeBiz biz =new SysCodeBiz(this.getUserACL());
      ResultSet  dataList = biz.querySyscodeList(1L,true);   
       // 将查询结果转为node;
      TreeNode treeNode = ClickUtil.getTreeNode(dataList, "SYS_CODE_ID", "P_CODE", "SYS_CODE_NAME", "SYS_CODE_ID", "SYS_CODE_ID,SYS_CODE_NAME", 1L);
      //绑定视图数据
      this.syscodeTreeView.bindData(treeNode, this.getSelfUrl(), false);
      this.syscodeTreeView.getTreeControl().setExpandLv(1);
      
      this.syscodeForm.bindNull();
      
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM, "查询系统编码明细异常");
    }
  }
  
  /**
   * 获取指定系统的明细信息
   * (刷新所有明细显示区域)
   * @param syscodeID :id
   * @return
   */
  public ActionResult getSortDetail(Long sysCodeID) {
    try {      
      SysCodeBiz biz = new SysCodeBiz(this.getUserACL());
      
      Map<String,Object> detailInfo = biz.querySyscodeDetail(this.syscodeForm.getFieldList(),sysCodeID);
      //绑定视图
      this.syscodeForm.bindData(detailInfo);
      //设置视图控件高度
      ClickUtil.setControlLayoutH(this.syscodeForm.getViewControl(),50);
      
      //因为此事件只初始化了一个sortFormView视图,因此只有sortFormView解析了参数，而其他列表视图的单独刷新需要相关参数，因此通过下面语句获取参数并进行传递
      
      //返回视图解析结
      return this.linkView(this.syscodeForm);
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询分类明细异常");
    }
  }
  
  /**
   * 系统编码保存处理
   * @param viewMap 视图的参数
   * @return
   */
  public ActionResult saveSyscode(Map<String,Object> valueMap) {
    try {
      //相关查询
      SysCodeBiz biz = new SysCodeBiz(this.getUserACL());
      Long sysCodeIdID = (Long)valueMap.get("SYS_CODE_ID");
      Long chkID = (Long)valueMap.get("P_CODE");
      if (biz.isSelfChild(sysCodeIdID, chkID)) {
        throw new BizException("不能选中自己及子类作为父类");
      }
      if(sysCodeIdID == null){
        biz.saveNewSyscode(valueMap);
      } else {
        biz.updateSyscode(valueMap);
      }
      return this.createReturnJSON(true, "修改分类成功,重新加载本页面",true,false,this.syscodeForm.getViewID(),"");
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"保存系统编码明细异常");
    }
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout treeLayout = new ViewPageLayout(this);
    treeLayout.addControl("站点列表", this.syscodeTreeView);
    treeLayout.setLeftPanel("", "站点列表");
    treeLayout.addControl("","详细", "明细", "", this.syscodeForm);
    
    treeLayout.setLeftWidth("", 20);
    treeLayout.setControlLayoutH(this.syscodeTreeView.getViewControl(),50);
    treeLayout.setControlLayoutH(this.syscodeForm.getViewControl(),50);
    
    return treeLayout;
  }

}
