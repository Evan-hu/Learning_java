package com.ga.erp.biz.content.stationmsg;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.PageEvent;
import com.ga.click.mvc.UnitPage;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;
import com.ga.erp.util.SystemUtil;

public class StationMsgDetailPage extends UnitPage {
	
  private static final long serialVersionUID = 1L;
  private FormView msgForm;
  private ListView listView;
  private String type;
  
  @Override
  public void initController() throws Exception {
	  msgForm = new StationMsgFormView("msgForm",this.modelMap);
	  listView = new MsgSendListView("msgList", this.modelMap);
	  type = this.modelMap.getRequest().getParameter("TYPE");
	  if(!GaUtil.isNullStr(type)) {
		  this.modelMap.getSession(false).setAttribute("type", type);
	  }else{
		  type = (String) this.modelMap.getSession(false).getAttribute("type");
	  }
	   if(!GaUtil.isNullStr(type) && "1".equals(type)) {
		   
		   	DbField field = new DbField("�Ա�", "SEX", GaConstant.DT_INT);
  			field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
  			field.setInput(GaConstant.INPUT_SELECT);
		    field.setLookupData(SystemUtil.getSexMap());
		    field.setColumnDisplay(true, 50, false);
		    this.msgForm.getFieldList().add(2, field);
		    
  			field = new DbField("ɸѡ������ϵ", "OA", GaConstant.DT_INT);
  			field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
  			field.setInput(GaConstant.INPUT_SELECT);
  			Map<String, Object>  lookup = new LinkedHashMap<String, Object>();
		    lookup.put("1", "��");
		    lookup.put("0", "��");
		    field.setLookupData(new LookupDataSet(lookup));
		    this.msgForm.getFieldList().add(3, field);
		    
  			field = new DbField("�����", "AGE", GaConstant.DT_INT);
  			field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
  			field.setInput(GaConstant.INPUT_SELECT);
		    lookup = new LinkedHashMap<String, Object>();
		    lookup.put("4", "50������");
		    lookup.put("3", "30-50��");
		    lookup.put("2", "20-30��");
		    lookup.put("1", "20������");
		    field.setLookupData(new LookupDataSet(lookup));
		    field.setColumnDisplay(true, 50, false);
		    this.msgForm.getFieldList().add(4, field);
		    
		    field = new DbField("����", "TYPE", GaConstant.DT_INT);
		    field.setInput(GaConstant.INPUT_HIDDEN);
		    field.setDefaultValue(type);
		    this.msgForm.getFieldList().add(field);

	   }else if(!GaUtil.isNullStr(type) && "2".equals(type)) {
		   
		    DbField field = new DbField("����ID","AREA_ID", GaConstant.DT_LONG);
  			field.setInput(GaConstant.INPUT_HIDDEN);
  			field.setPopSelect("selectArea", "AREA_ID", false);
  			this.msgForm.getFieldList().add(field);
  			
  		     
  	    field = new DbField("��������<font color='red'>*</font>","AREA_NAME",GaConstant.DT_STRING);
  	    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
  	    field.setPopSelect("selectArea","AREA_NAME",true, "/system/area_sele.htm", "AREA_ID,AREA_NAME,cid_areaTree",300,400);
  	    this.msgForm.getFieldList().add(2, field);
  		    
  			field = new DbField("ɸѡ������ϵ", "OA", GaConstant.DT_INT);
  			field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
  			field.setInput(GaConstant.INPUT_SELECT);
  			Map<String, Object>  lookup = new LinkedHashMap<String, Object>();
  			lookup.put("1", "��");
        lookup.put("0", "��");
        field.setLookupData(new LookupDataSet(lookup));
        this.msgForm.getFieldList().add(3, field);
        
        
        field = new DbField("����", "TYPE", GaConstant.DT_INT);
        field.setInput(GaConstant.INPUT_HIDDEN);
        field.setDefaultValue(type);
        this.msgForm.getFieldList().add(field);

	   }else if(!GaUtil.isNullStr(type) && "3".equals(type)) {
		   
  		  DbField field = new DbField("����ID","AREA_ID", GaConstant.DT_LONG);
  			field.setInput(GaConstant.INPUT_HIDDEN);
  			field.setPopSelect("selectArea", "AREA_ID", false);
  			this.msgForm.getFieldList().add(field);
  			 
  			field = new DbField("��������<font color='red'>*</font>","AREA_NAME",GaConstant.DT_STRING);
        field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
        field.setPopSelect("selectArea","AREA_NAME",true, "/system/area_sele.htm", "AREA_ID,AREA_NAME,cid_areaTree",300,400);
        this.msgForm.getFieldList().add(2, field);
  	   		
  	    field = new DbField("����", "TYPE", GaConstant.DT_INT);
  	    field.setInput(GaConstant.INPUT_HIDDEN);
  	    field.setDefaultValue(type);
  	    this.msgForm.getFieldList().add(field);
  		    
  			field = new DbField("ɸѡ������ϵ", "OA", GaConstant.DT_INT);
  			field.setInput(GaConstant.INPUT_SELECT);
   		  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_HIDDEN);
  			Map<String, Object>  lookup = new LinkedHashMap<String, Object>();
  		  lookup.put("1", "��");
  		  lookup.put("0", "��");
  		  field.setLookupData(new LookupDataSet(lookup));
  		  this.msgForm.getFieldList().add(3, field);
	   }
	
	PageEvent event = this.regPageLoadEvent("pageLoad");   
    event.addEventParam(this.msgForm, PageEvent.PARAMTYPE_QUERYVALUE, "STATION_MSG_ID");
    event.addEventParam(this.listView, PageEvent.PARAMTYPE_PAGEQUERY);
    
	if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_NEW) {
	    ActionButton action = new ActionButton(this.getClass(), "saveMsg", "����", this.getSelfUrl(), null);
	    action.addParam(GaConstant.FIXPARAM_EDITMODE, this.modelMap.getPageEditMode());
	    SubmitTool.submitMvc(action,this.modelMap.getPreNavInfoStr());
	    event = this.regPageEvent(action, "saveMsg");
	    event.addEventParam(this.msgForm, PageEvent.PARAMTYPE_DATAMAP);
	    action.bindForm(this.msgForm.getViewID());
	    this.msgForm.regAction(action);
	}
    
    event = this.regListViewLoadEvent(this.listView, "reloadMsgSend");
    event.addEventParam(this.listView,PageEvent.PARAMTYPE_PAGEQUERY); 
    event.addEventParam(this.listView,PageEvent.PARAMTYPE_QUERYMAP); 
    event.addEventParam(this.msgForm, PageEvent.PARAMTYPE_QUERYVALUE, "STATION_MSG_ID");
  }

  @Override
  public Layout initLayout() {
	FormLayout formLayOut = new FormLayout("",this.msgForm.getDataForm(),1);
	this.msgForm.getDataForm().setLayout(formLayOut);
	ViewPageLayout layout = new ViewPageLayout(this);
	if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		layout.addControl("������Ϣ", "" ,this.msgForm);
		if(this.modelMap.getUserACL().isAllOp()){ 
		  layout.addControl("������־", "", this.listView);
		}
	}else {
		layout.addControl(this.msgForm);
	}
	return layout;
  }

  public void pageLoad(Long msgId, QueryPageData pageData) {
	try {   
	  if (this.modelMap.getPageEditMode() == GaConstant.EDITMODE_EDIT) {
		  StationMsgBiz biz = new StationMsgBiz(this.getUserACL()); 
		  this.msgForm.bindData(biz.queryMsgDetail(msgId));
		  this.listView.bindData(biz.queryMsgSendList(pageData, null, this.listView.getFieldList(), msgId));
       }else {
    	   this.msgForm.bindData(this.msgForm.getViewParam());
       }
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"ҳ������쳣");
    }
  }
  
  public ActionResult saveMsg(Map<String, Object> valuesMap) {
	  StationMsgBiz biz = new StationMsgBiz(this.getUserACL());
	  biz.saveStationMsg(valuesMap);
	  return this.createReturnJSON(true, "����վ���ųɹ�", true, false, "msgList", "");
  }
  
  public ActionResult reloadMsgSend(QueryPageData pageData, Map<String, Object> querymap, Long msgId) {
	try {
		 StationMsgBiz biz = new StationMsgBiz(this.getUserACL()); 
		 this.listView.bindData(biz.queryMsgSendList(pageData, querymap, this.listView.getFieldList(), msgId));
		 ClickUtil.setControlLayoutH(this.listView.getViewControl(), 120);
	   return this.linkView(this.listView);
	  } catch(BizException e) {
	     throw e;
	  } catch(Exception ex) {
	     throw new BizException("����ҳ���쳣");	
	 }
  }
}
