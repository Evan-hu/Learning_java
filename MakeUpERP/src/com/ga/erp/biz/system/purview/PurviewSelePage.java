package com.ga.erp.biz.system.purview;

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
import com.ga.erp.util.SystemUtil;

/**
 * ������Ҵ���ҳ��
 *
 */
@SuppressWarnings("serial")
public class PurviewSelePage extends UnitPage {

  private View purviewTreeView;
  @Override
  public void initController() throws Exception {
    // TODO Auto-generated method stub
    this.purviewTreeView = new PurviewTreeView("purviewTree",this.modelMap);
    //ע������¼�
    PageEvent loadEvent = this.regPageLoadEvent("pageLoad");
    loadEvent.addEventRequestParam(this.purviewTreeView,"SortTypeCode");
  }
  
  @Override
  public Layout initLayout() {
    // TODO Auto-generated method stub
      // TODO Auto-generated method stub
      ViewPageLayout layout = new ViewPageLayout(this);
      layout.addControl(this.purviewTreeView);
      return layout;
  }
  /**
   * ҳ���ʼ������
   * @param modelMap
   */
  public void pageLoad(String sortTypeCode)  {
    try {
      if (GaUtil.isNullStr(sortTypeCode)) {
        sortTypeCode = String.valueOf(SystemUtil.SYSCODE_NORMALSORT);
      }
      //��ز�ѯ
      PurviewBiz biz = new PurviewBiz(this.getUserACL());
      ResultSet  dataList = biz.queryPurviewList(1L, false);
      //����ѯ���תΪnode;     
      TreeNode treeNode = ClickUtil.getTreeNode(dataList, "PURVIEW_ID", "P_ID", "PURVIEW_NAME", "PURVIEW_ID", "PURVIEW_ID,PURVIEW_NAME", 1L);
      //����ͼ����
      this.purviewTreeView.bindData(treeNode,"",false);     
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"��ѯ�����б��쳣");
    }
  }
}
