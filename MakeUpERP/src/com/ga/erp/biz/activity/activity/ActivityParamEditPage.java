/**
 * Copyright (c) 2011 Noah
 * All right reserved.
 */
package com.ga.erp.biz.activity.activity;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.click.ActionResult;

import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.util.GaUtil;

/**
 * ������
 * @author SPE
 * @create_time 2012-7-5 ����04:20:28
 * @project ShopMgr
 */
public class ActivityParamEditPage extends UnitPage {
  
  private static final long serialVersionUID = 1L;
  ActivityParamEditView formView;
  
  public void initController() throws Exception {
    
    formView = new ActivityParamEditView("formView", this.modelMap);
    
    PageEvent pageEvent = this.regPageLoadEvent("pageLoad");
    pageEvent.addEventParam(this.formView, PageEvent.PARAMTYPE_QUERYVALUE, "DISCOUNT_PARAM_ID"); 
    
    ActionButton action = new ActionButton(this.getClass(), "save", "����", this.getSelfUrl(), null);
    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
    action.addParam("isSave", true);
    if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW) {
      SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());  
    } else {
      SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
    } 
    pageEvent = this.regPageEvent(action, "save");
    pageEvent.addEventParam(this.formView, PageEvent.PARAMTYPE_DATAMAP);
    action.bindForm(this.formView.getViewID());
    this.formView.regAction(action);
    
  }
  
  public Layout initLayout() {
    ViewPageLayout layout = new ViewPageLayout(this);
    FormLayout formLayout = new FormLayout("", this.formView.getDataForm(), 2);

    formView.getDataForm().setLayout(formLayout);
    layout.addControl("","������Ϣ","������Ϣ","",this.formView);
  
    return layout;
  }
  
  public void pageLoad(Long activityID) {
	  
  }
  
  public ActionResult save(Map<String,Object> detailForm) {
	  long type = (Long)detailForm.get("DISCOUNT_TYPE");
	  long commodityType = (Long)detailForm.get("COMMODITY_RANGE_TYPE");
	  
	  if(commodityType == 2 ){
		  if(detailForm.get("NEWBRANDSELECT.SORT_ID") == null){
		    return this.createReturnJSON(false, "��ѡ����࣡", false, false, "", "");
		  }
		  detailForm.put("TARGET_ID", detailForm.get("NEWBRANDSELECT.SORT_ID"));
		  detailForm.put("TARGET_NAME", detailForm.get("NEWSORTSELECT.SORT_NAME"));
	  }
	  if(commodityType == 3 ){
		  if(detailForm.get("NEWBRANDSELECT.BRAND_ID") == null){
		    return this.createReturnJSON(false, "��ѡ��Ʒ�ƣ�", false, false, "", "");
		  }
		  detailForm.put("TARGET_ID", detailForm.get("NEWBRANDSELECT.BRAND_ID"));
		  detailForm.put("TARGET_NAME", detailForm.get("NEWBRANDSELECT.NAME"));
	  }
	  if(commodityType == 4 ){
		  if(detailForm.get("selectCommodity.COMMODITY_ID") == null){
		    return this.createReturnJSON(false, "��ѡ����Ʒ��", false, false, "", "");
		  }
		  detailForm.put("TARGET_ID", detailForm.get("selectCommodity.COMMODITY_ID"));
		  detailForm.put("TARGET_NAME", detailForm.get("selectCommodity.COMMODITY_NAME"));
	  }
	  if(type == 7){
		  if(detailForm.get("selectCardBatch.CARD_BATCH_ID") == null){
			    return this.createReturnJSON(false, "��ѡ�����ȯ���Σ�", false, false, "", "");
			  }
			  detailForm.put("TARGET_ID", detailForm.get("selectCardBatch.CARD_BATCH_ID"));
			  detailForm.put("TARGET_NAME", detailForm.get("selectCardBatch.NAME"));
	  }
	  String errorMsg = "";
	  BigDecimal minMoney = (BigDecimal)detailForm.get("MIN_MONEY");
	  BigDecimal value = (BigDecimal)detailForm.get("VALUE");
	  BigDecimal minCount = (BigDecimal)detailForm.get("MIN_COUNT");

	  if(type == 1){//������Ǯ������Ʒ
			if(minMoney == null || minMoney.longValue() < 0){
				errorMsg =  "��������Ϊ�ջ�С��0�������ý������д0����";
			}
			else if(value == null || value.longValue() <= 0){
				errorMsg =  "��������Ϊ�ջ�С�ڣ������ý������д0����";
			}else if( detailForm.get("selectCommodity.COMMODITY_ID") == null){
				errorMsg =  "��ѡ�����͵���Ʒ��";
			}
	  }else if(type == 2){//����̶����
		
		if(minMoney == null || minMoney.longValue() < 0){
			errorMsg =  "��������Ϊ�ջ�С��0�������ý������д0����";
		}
		else if(value == null || value.longValue() <= 0){
			errorMsg =  "�������Ϊ�ջ�С�ڵ���0��";
		}
	  }else if(type == 3){//ָ����Ʒ�ؼ�
		  if(value == null || value.longValue() <= 0){
			errorMsg =  "�������Ϊ�ջ�С�ڵ���0��";
		  }
	  }else if(type == 4){//�������ۣ�ֱ���ۿ۽������Ϊ0���ɣ�
		
		if(minMoney == null || minMoney.longValue() < 0){
			errorMsg =  "��������Ϊ�ջ�С��0�������ý������д0����";
		}
		else if(value == null || value.longValue() <= 0){
			errorMsg =  "�ۿ۽���Ϊ�ջ�С�ڵ���0��";
		}
	  }else if(type == 5){//����������
		 if(minCount == null || minCount.longValue() < 0){
			errorMsg =  "������������Ϊ�ջ�С��0����������������д0����";
		 }
		 else if(value == null || value.longValue() <= 0){
			errorMsg =  "�ۿ۽���Ϊ�ջ�С�ڵ���0��";
		 }
	  }else if(type == 7){//����������Ż�ȯ
		  if(minCount == null || minCount.longValue() < 0){
				errorMsg =  "������������Ϊ�ջ�С��0�������ý������д0����";
		  }
		  if(detailForm.get("selectCardBatch.CARD_BATCH_ID") == null){
				errorMsg =  "��ѡ�����ȯ���Σ�";
		  }
	  }else if(type == 8){//�����Ʒ
		  
	  }else if(type == 9){//��������Ǯ������Ʒ
			if(minCount == null || minCount.longValue() < 0){
				errorMsg =  "������������Ϊ�ջ�С��0�������ý������д0����";
			}
			else if(value == null || value.longValue() <= 0){
				errorMsg =  "��������Ϊ�ջ�С�ڣ������ý������д0����";
			}else if( detailForm.get("selectCommodity.COMMODITY_ID") == null){
				errorMsg =  "��ѡ�����͵���Ʒ��";
			}
	  }
	  if(!GaUtil.isNullStr(errorMsg)){
		return this.createReturnJSON(false, errorMsg, false, false, "", "");
	  }
	  
	  ActivityBiz biz = new ActivityBiz(getUserACL());
	  if(biz.editActivityParam(detailForm)){
        return this.createReturnJSON(true, "�༭�ɹ�!", true, false, "paramListView", "");
	  }else{
		return this.createReturnJSON(false, "�༭ʧ��!", false, false, "", "");  
	  }
  }
}
