package com.ga.erp.biz.stock.shelf;

import java.sql.ResultSet;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ModelMap;
import com.ga.click.mvc.UnitPage;
import com.ga.click.mvc.View;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

public class ShelfSelePage extends UnitPage {

  private static final long serialVersionUID = -1L;
  private View shelfTreeView;
  
  @Override
  public void initController() throws Exception {
	  
    this.shelfTreeView = new ShelfSeleView("shelfTree",this.modelMap);
    this.regPageLoadEvent("pageLoad");
  }
  
  @Override
  public Layout initLayout() {
      ViewPageLayout layout = new ViewPageLayout(this);
      layout.addControl(this.shelfTreeView);
      return layout;
  }
  
  public void pageLoad(ModelMap modelMap)  {
    try {
      String rootShelfIdStr = this.modelMap.getRequest().getParameter("ROOT_SORT");
      if(GaUtil.isNullStr(rootShelfIdStr)){
        rootShelfIdStr = "1";
      }
      Long rootShelfId = Long.parseLong(rootShelfIdStr);
      ShelfBiz biz = new ShelfBiz(this.getUserACL());
      ResultSet dataList = biz.querySortList(rootShelfId);
      TreeNode treeNode = ClickUtil.getTreeNode(dataList, "SHELF_ID", "P_SHELF_ID", "SHELF_NUM", 
    		  "SHELF_ID", "SHELF_ID,SHELF_NUM", rootShelfId);
      this.shelfTreeView.bindData(treeNode, "", false);     
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询仓库商品明细异常");
    }
  }

}
