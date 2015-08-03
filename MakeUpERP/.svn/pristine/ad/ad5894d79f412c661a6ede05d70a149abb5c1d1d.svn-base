package com.ga.erp.biz.system.role;

import java.sql.ResultSet;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.mvc.View;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.system.purview.PurviewTreeView;
import com.ga.erp.util.SystemUtil;

@SuppressWarnings("serial")
public class OpSelePage extends UnitPage{
  private View treeView;
  @Override
  public void initController() throws Exception {
    // TODO Auto-generated method stub
    this.treeView = new PurviewTreeView("treeView",this.modelMap);
    //注册加载事件
    PageEvent loadEvent = this.regPageLoadEvent("pageLoad");
    loadEvent.addEventRequestParam(this.treeView,"SortTypeCode");
  }
  
  @Override
  public Layout initLayout() {
    // TODO Auto-generated method stub
      // TODO Auto-generated method stub
      ViewPageLayout layout = new ViewPageLayout(this);
      layout.addControl(this.treeView);
      return layout;
  }
  /**
   * 页面初始化加载
   * @param modelMap
   */
  public void pageLoad(String sortTypeCode)  {
    try {
      if (GaUtil.isNullStr(sortTypeCode)) {
        sortTypeCode = String.valueOf(SystemUtil.SYSCODE_NORMALSORT);
      }
      //相关查询
      RoleBiz biz = new RoleBiz(this.getUserACL());
      ResultSet  dataList = biz.queryShopTree();
      //将查询结果转为node;     
      TreeNode treeNode = ClickUtil.getTreeNode(dataList, "SHOP_ID", "P_SHOP_ID", "NAME", "SHOP_ID", "SHOP_ID,NAME", 1L);
      //绑定视图数据
      this.treeView.bindData(treeNode,"",false);     
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询树型列表异常");
    }
  }
}
