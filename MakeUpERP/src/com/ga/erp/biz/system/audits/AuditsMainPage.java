package com.ga.erp.biz.system.audits;

import java.util.Iterator;
import org.json.JSONObject;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;


@SuppressWarnings("serial")
public class AuditsMainPage extends UnitPage {

  private ListView auditsList;
  
  @Override
  public void initController() throws Exception {
    
	this.auditsList = new AuditsListView("auditsList",this.modelMap);

    PageEvent event  = this.regPageLoadEvent("pageLoad");
    event.addEventParam(this.auditsList,PageEvent.PARAMTYPE_PAGEQUERY);
    
    ActionButton action = this.auditsList.regEditAction("eidtAudit", "审核/查看", "/system/audits_detail.htm");
    SubmitTool.submitToDialog(action, "eidtAudit","审核/查看",700, 320,this.modelMap.getPreNavInfoStr());
    action.bindTableRowData("");
  }
  
  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl(this.auditsList);
    return layout;
  }
  
  public void pageLoad(QueryPageData queryPageData) {
    try {      
      String bizCode = this.modelMap.getRequest().getParameter("BIZ_CODE");
      String rowData = this.modelMap.getRequest().getParameter("_rowdata");
      String bizId = "";
      if(rowData != null){
        JSONObject json = new JSONObject(rowData);
        @SuppressWarnings("unchecked")
		Iterator<String> it = json.keys();
        while (it.hasNext()) {
          String key = it.next();
          if(key.contains("_ID")){
            bizId = json.getString(key);
          }
         }  
      }
      AuditsBiz biz  = new AuditsBiz(this.getUserACL());
      String state = this.modelMap.getRequest().getParameter("state");
      this.auditsList.bindData(biz.queryAuditList(queryPageData,this.auditsList.getFieldList(), bizCode, bizId,state));
    } catch(BizException ex) {
        throw ex;
    } catch(Exception e) {
        throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }
  
}
