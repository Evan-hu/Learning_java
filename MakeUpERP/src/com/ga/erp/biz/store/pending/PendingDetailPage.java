package com.ga.erp.biz.store.pending;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.ModelMap;
import com.ga.click.mvc.UnitPage;

public class PendingDetailPage extends UnitPage {

  private static final long serialVersionUID = 1L;
  private PendingFormView pendingForm;
  
  @Override
  public void initController() throws Exception {
    this.modelMap.setPageEditMode(GaConstant.EDITMODE_EDIT);
    pendingForm = new PendingFormView("pendingForm", this.modelMap);
    this.regPageLoadEvent("pageLoad");
  }

  @Override
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    
    FormLayout formLayout = new FormLayout("",this.pendingForm.getDataForm(),2);
    formLayout.setGroupFields("提示信息", "STORE_ALERT,STORE_MINUS_ALERT,STOCK_ALERT,STOCK_MINUS_ALERT,STORE_DIRECT_APPLY_ORDER,STORE_DIRECT_RECIVE_ORDER", 2);

    formLayout.setGroupFields("采购订单概况", "ORDER_WAIT_AUDIT,ORDER_AUDIT_NO_PASS,ORDER_PENDING_ACCEPT,ORDER_REFUSE_ACCEPT,ORDER_PENDING_STOCK," +
    		"ORDER_SENDING,ORDER_COMPLETE,ORDER_CANCEL,ORDER_TODAY", 2);

    formLayout.setGroupFields("待办事项", "COMMODITY_RETURN_APPLY,WAIT_AUDIT_BUSINESS", 2);
    this.pendingForm.getDataForm().setLayout(formLayout);
    
    layout.addControl("每日提示",this.pendingForm);
    return layout;

  }

  /**
   * 执行页面加载处理
   * @param
   */
  public void pageLoad(ModelMap map) {
    try {
    	PendingBiz biz = new PendingBiz(getUserACL());
    	List<Map<String, Object>> lists = biz.queryCountInfo();
    	Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
      for (Map<String, Object> m : lists) {
        Long typeObj = (Long) m.get("type");
        int type = typeObj.intValue();
        switch (type) {
          case 1 :
            resultMap.put("STORE_ALERT", m.get("cnt"));
            break;
          case 2 :
            resultMap.put("STORE_MINUS_ALERT", m.get("cnt"));
            break;
          case 3 :
            resultMap.put("STOCK_ALERT", m.get("cnt"));
            break;
          case 4 :
            resultMap.put("STOCK_MINUS_ALERT", m.get("cnt"));
            break;
          case 5 :
            resultMap.put("STORE_DIRECT_APPLY_ORDER", m.get("cnt"));
            break;
          case 6 :
            resultMap.put("STORE_DIRECT_RECIVE_ORDER", m.get("cnt"));
            break;
        }
      }
      
      List<Map<String, Object>> orderLists = biz.queryOrderCountInfo();
      for (Map<String, Object> m : orderLists) {
        int state = Integer.parseInt(m.get("STATE") + "");
        switch (state) {
          case 4 :
            resultMap.put("ORDER_COMPLETE", m.get("cnt"));
            break;
          case 3 :
            resultMap.put("ORDER_SENDING", m.get("cnt"));
            break;
          case 2 :
            resultMap.put("ORDER_PENDING_STOCK", m.get("cnt"));
            break;
          case 1 :
            resultMap.put("ORDER_PENDING_ACCEPT", m.get("cnt"));
            break;
          case 0 :
            resultMap.put("ORDER_CANCEL", m.get("cnt"));
            break;
          case -1 :
            resultMap.put("ORDER_WAIT_AUDIT", m.get("cnt"));
            break;
          case -2 :
            resultMap.put("ORDER_AUDIT_NO_PASS", m.get("cnt"));
            break;
          case -3 :
            resultMap.put("ORDER_REFUSE_ACCEPT", m.get("cnt"));
            break;
        }
        while(m.get("type")=="1"){
          resultMap.put("ORDER_TODAY", m.get("cnt"));
        }
      }
    
      List<Map<String, Object>> pendingLists1 = biz.queryDetail();
      for (Map<String, Object> m : pendingLists1) {
        int state = Integer.parseInt(m.get("STATE") + "");
        switch (state) {
          case 1 :
            resultMap.put("COMMODITY_RETURN_APPLY", m.get("cnt"));
            break;
        }
      this.pendingForm.bindData(resultMap);
      }
      
      List<Map<String, Object>> pendingLists2 = biz.queryWaitAuditInfo();
      for (Map<String, Object> m : pendingLists2) {
      resultMap.put("WAIT_AUDIT_BUSINESS", m.get("cnt"));
      this.pendingForm.bindData(resultMap);
      }
      
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      ex.printStackTrace();
      throw new BizException(BizException.SYSTEM,"加载页面异常");
    }
  }
}
