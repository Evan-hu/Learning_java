package com.ga.erp.biz.system.syscode;

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
 */
public class SyscodeSelePage extends UnitPage {

  private static final long serialVersionUID = 1L;
  private View syscodeTreeView;
  @Override
  public void initController() throws Exception {

	this.syscodeTreeView = new SyscodeTreeView("syscodeTree",this.modelMap);
    //ע������¼�
    PageEvent loadEvent = this.regPageLoadEvent("pageLoad");
    loadEvent.addEventRequestParam(this.syscodeTreeView,"SortTypeCode");
  }
  
  @Override
  public Layout initLayout() {
    // TODO Auto-generated method stub
      // TODO Auto-generated method stub
      ViewPageLayout layout = new ViewPageLayout(this);
      layout.addControl(this.syscodeTreeView);
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
      SysCodeBiz biz = new SysCodeBiz(this.getUserACL());
      ResultSet  dataList = biz.querySyscodeList(1L, false);
      //����ѯ���תΪnode;     
      TreeNode treeNode = ClickUtil.getTreeNode(dataList, "SYS_CODE_ID", "P_CODE", "SYS_CODE_NAME", "SYS_CODE_ID", "SYS_CODE_ID,SYS_CODE_NAME", 1L);
      //����ͼ����
      this.syscodeTreeView.bindData(treeNode,"",false);     
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"��ѯϵͳ������ϸ�쳣");
    }
  }


}
