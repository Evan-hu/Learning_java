package com.ga.erp.biz.system.audits;

import java.util.Iterator;
import java.util.Map;

import org.apache.click.ActionResult;
import org.json.JSONObject;

import com.ga.click.control.GaConstant;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.util.GaUtil;

@SuppressWarnings("serial")
public class AuditsDetailPage extends UnitPage {

  private FormView auditForm;

  @Override
  public void initController() throws Exception {

    this.auditForm = new AuditsFormView("auditDetail", this.modelMap);
    PageEvent event = this.regPageLoadEvent("pageLoad");
//    System.out.println(checkType);
    event.addEventParam(this.auditForm, PageEvent.PARAMTYPE_QUERYVALUE,"AUDITING_ID");
    
   
  }

  @Override
  public Layout initLayout() {
	FormLayout formLayout = new FormLayout("", this.auditForm.getDataForm(), 2);
	formLayout.mergeCellField("auto", this.modelMap.getPageEditMode() == 1 ? 2 : 3, 0, 1, "RESULT_NOTE");
    this.auditForm.getDataForm().setLayout(formLayout);
    ViewPageLayout layout = new ViewPageLayout(this);
    layout.addControl("审核信息","",auditForm);
    return layout;
  }

  public void pageLoad(Long auditId) {
    try {   
    	   if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
    		if(auditId == null){
    			this.auditForm.regAddSaveEvent("saveAudit", "saveAudit", this, false);
    		    String checkType = this.modelMap.getRequest().getParameter("CHECK_TYPE");
    			String bizId = this.auditForm.getRowDataValue(checkType+"_ID");
    			Map<String,Object> map = DbUtils.queryMap(DbUtils.getConnection()," select AUDITING_ID from AUDITING where AUDITING_EXAMPLE_ID = (select AUDITING_EXAMPLE_ID from AUDITING_EXAMPLE where state < 2 and BIZ_ID = ? and  SYS_CODE_ID = (select SYS_CODE_ID from SYS_CODE where SYS_CODE_VALUE = ? limit 0,1) limit 0,1 ) and ROLE_ID in (?) ",bizId,checkType,getUserACL().getRoleIDStr());
    			if(map != null && !map.isEmpty()){
       			  auditId = (Long)map.get("AUDITING_ID");
    			}
    		}
    		AuditsBiz biz = new AuditsBiz(this.getUserACL());
         	this.auditForm.bindData(biz.queryAuditDetail(auditId));
          }
      } catch(BizException ex) {
        throw ex;
      } catch(Exception e) {
    	  e.printStackTrace();
          throw new BizException(BizException.SYSTEM,"页面加载异常");
      }
  }

  public ActionResult saveAudit(Map<String, Object> valueMap) {
      AuditsBiz biz = new AuditsBiz(this.getUserACL());
      biz.saveAudit(valueMap);
      return this.createReturnJSON(true, "审核成功", true, true, "", "");
  }
  
}
