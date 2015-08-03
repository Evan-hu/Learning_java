package com.ga.erp.biz.system.area;

import java.sql.ResultSet;
import org.apache.click.ActionResult;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.TreeView;
import com.ga.click.mvc.UnitPage;
import com.ga.click.mvc.View;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;
import com.ga.erp.util.SystemUtil;

/**
 * 分类查找带回页面
 *
 */
@SuppressWarnings("serial")
public class AreaSelePage extends UnitPage {

  private View treeView;
  @Override
  public void initController() throws Exception {
    this.treeView = new TreeView("areaTree",this.modelMap);

    PageEvent loadEvent = this.regPageLoadEvent("pageLoad");
    loadEvent.addEventRequestParam(this.treeView,"SortTypeCode");
  }
  
  @Override
  public Layout initLayout() {
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
      AreaBiz biz = new AreaBiz(this.getUserACL());
      ResultSet dataList = biz.queryAreaList(SystemUtil.ID_TREEROOT);
      //将查询结果转为node;     
      TreeNode treeNode = ClickUtil.getTreeNode(dataList, "AREA_ID", "P_ID", "AREA_NAME", "AREA_ID", "AREA_ID,AREA_NAME", SystemUtil.ID_TREEROOT);
      //绑定视图数据
      this.treeView.bindData(treeNode,"",false);     
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"查询明细异常");
    }
  }
  
  public ActionResult suggestList()  {
    try {
      String param = this.getContext().getRequestParameter("inputValue");
      if (!GaUtil.isNullStr(param)) {
        param = param.trim();
      }
      AreaBiz biz  = new AreaBiz(this.getUserACL());       
     return new ActionResult(biz.querySuggestList(param).toString().toString(),ActionResult.JSON);
    } catch(Exception e) {
      throw new BizException("查询异常");
    }
 }
}
