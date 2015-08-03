package com.ga.erp.biz.comm.sort;

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

public class SortSelePage extends UnitPage {

  private static final long serialVersionUID = -1L;
  private View sortTreeView;
  
  @Override
  public void initController() throws Exception {
	  
    this.sortTreeView = new SortSeleView("sortTree",this.modelMap);
    
    this.regPageLoadEvent("pageLoad");
  }
  
  @Override
  public Layout initLayout() {
      ViewPageLayout layout = new ViewPageLayout(this);
      layout.addControl(this.sortTreeView);
      return layout;
  }
  
  public void pageLoad(ModelMap modelMap)  {
    try {
      String rootSortIdStr = this.modelMap.getRequest().getParameter("ROOT_SORT");
      if(GaUtil.isNullStr(rootSortIdStr)){
        rootSortIdStr = "1";
      }
      Long rootSortId = Long.parseLong(rootSortIdStr);
      SortBiz biz = new SortBiz(this.getUserACL());
      ResultSet dataList = biz.querySortList(rootSortId);
      TreeNode treeNode = ClickUtil.getTreeNode(dataList, "SORT_ID", "P_ID", "SORT_NAME", 
    		  "SORT_ID", "SORT_ID,SORT_NAME", rootSortId);
      this.sortTreeView.bindData(treeNode, "", false);     
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询分类明细异常");
    }
  }

}
