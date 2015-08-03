package com.ga.click.mvc;

import java.util.HashMap;
import java.util.Map;

import org.apache.click.Control;
import org.apache.click.control.HiddenField;

import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.form.ListForm;
import com.ga.click.control.table.QueryTable;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.StateBiz;
import com.ga.erp.biz.system.audits.AuditsBiz;

public class ListView extends View {
  private boolean showPage = true;
  private boolean showQuery = true;
  private boolean multiSelect = false;
  private ActionButton submitAction = null;
  private boolean dispDeleRow = true;
  private String mouseOverJs = "";
  private String mouseOutJS = "";
  private int queryRows = 1;
  private String queryTip = "";
  private boolean isMTableMode = false;
  
  private boolean isExport = false; 
  /**
   * ��ʼ���б���ͼ
   * @param viewID
   * @param modelMap
   */
  public ListView(String viewID,ModelMap modelMap) {
    super(viewID,GaConstant.VIEWTYPE_LIST,modelMap);
  }
  
  /**
   * ��ʼ���б���ͼ
   * (�Բ�ͬ��page�ı༭ģʽ����������ͼ)
   * @param viewID
   * @param modelMap
   * @param editMOde
   */
  public ListView(String viewID,ModelMap modelMap,int editMode) {
    super(viewID,GaConstant.VIEWTYPE_LIST,modelMap,editMode);
  }
  
  /**
   * �����Ƿ��������ѡ
   * @param isMultiSelect
   */
  public void setMultiSelect(boolean isMultiSelect) {
    multiSelect = isMultiSelect;
  }
  
  public void setMouseOverJS(String in ,String out) {
    this.mouseOverJs = in;
    this.mouseOutJS = out;
  }
  
  /**
   * �����ؼ�
   * (Ӧ�ӷ���ֽ⵽������,�Ժ��Ż�)
   */
  @Override
  protected Control createControl() throws Exception {
    Control control =  super.createControl();
    if (control != null) {
      ((QueryTable)control).setMultiSelect(this.multiSelect);
    }
    return control;
  }
   
  /**
   * ע���½���ť
   * @param actionID ��ťID
   * @param actionTitle ��ť����
   * @param winUrl �򿪵�ַ
   * @return
   */
  public ActionButton regAddAction(String actionID,String actionTitle,String winUrl) {
    try {
      //�½���չ�����¼�
      Map<String,Object>paramMap = new HashMap<String,Object>();
      paramMap.put(GaConstant.FIXPARAM_EDITMODE, GaConstant.EDITMODE_NEW);
      ActionButton action = new ActionButton(this.getClass(),actionID,actionTitle,winUrl,paramMap);
      this.regAction(action);
      
      return action;
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ע���½��򿪰�ťʧ��");
    }
  }
  
  /**
   * ע��༭��ť(���¼�)
   * @param actionID ��ť����
   * @param actionTitle ��ť����
   * @param winUrl �򿪵�ַ
   * @return
   */
  public ActionButton regEditAction(String actionID,String actionTitle,String winUrl) {
    try { 
      //�༭��չ�����¼�
      Map<String,Object> paramMap = new HashMap<String,Object>();
      paramMap.put(GaConstant.FIXPARAM_EDITMODE, GaConstant.EDITMODE_EDIT);
      ActionButton action = new ActionButton(this.getClass(),actionID,actionTitle,winUrl,paramMap);
      action.bindTableRowData(this.viewID);
      this.regAction(action);
      return action;
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ע���½��򿪰�ťʧ��");
    }
  }
  
 /**
  * ע������/��ֹ/ɾ����ť(�Զ����¼������账��)
  * @param actionID
  * @param winUrl
  * @param view
  * @param page
  */
  public void regStateAction(String winUrl,UnitPage page,String tableName) {
    try { 
      Map<String,Object> paramMap = new HashMap<String,Object>();
      paramMap.put(GaConstant.FIXPARAM_EDITMODE, GaConstant.EDITMODE_EDIT);
      ActionButton action = new ActionButton(this.getClass(),this.viewID+"_start","����",winUrl,paramMap);
      if(isMultiSelect()){
    	action.bindTableMultiSelect(this.viewID);
      }else{
        action.bindTableRowData(this.viewID);
      }
      this.regAction(action);
      SubmitTool.submitMvc(action, this.modelMap.getPreNavInfoStr());
     
      action.setConfirm("ȷ������?");
      PageEvent pageEvent = page.regPageEvent(action, isMultiSelect() ? "startObjects" : "startObject" );
      if(isMultiSelect()){
    	  pageEvent.addEventParam(this,PageEvent.PARAMTYPE_REQUEST,GaConstant.FIXPARAM_MULTISELECT);
      }else{
    	  pageEvent.addEventParam(this, PageEvent.PARAMTYPE_QUERYVALUE,tableName+"_ID");
      }
      pageEvent.addEventFixValueParam(tableName);
      pageEvent.addEventFixValueParam(this.viewID);
      
      ActionButton stopAction = new ActionButton(this.getClass(),this.viewID+"_stop","��ֹ",winUrl,paramMap);
      if(isMultiSelect()){
    	stopAction.bindTableMultiSelect(this.viewID);
      }else{
        stopAction.bindTableRowData(this.viewID);
      }
    
      this.regAction(stopAction);
      SubmitTool.submitMvc(stopAction, this.modelMap.getPreNavInfoStr());
      stopAction.setConfirm("ȷ�Ϸ�ֹ?");
      PageEvent p = page.regPageEvent(stopAction, isMultiSelect() ? "stopObjects" : "stopObject");
      if(isMultiSelect()){
    	  p.addEventParam(this,PageEvent.PARAMTYPE_REQUEST,GaConstant.FIXPARAM_MULTISELECT);
      }else{
         p.addEventParam(this, PageEvent.PARAMTYPE_QUERYVALUE,tableName+"_ID");
      }
        
      p.addEventFixValueParam(tableName);
      p.addEventFixValueParam(this.viewID);
      
      if(!GaUtil.haveCheckTables.contains(tableName)){
  	    StateBiz biz = new StateBiz(getUserACL());
  	    if(biz.haveColumn(tableName,"check_state")){
  	      GaUtil.haveCheckTables.add(tableName); 	  
  	    }
       }
      if(GaUtil.haveCheckTables.contains(tableName)){
//    	  Map<String,Object> map = DbUtils.queryMap(DbUtils.getConnection()," select AUDITING_ID from AUDITING where AUDITING_EXAMPLE_ID = (select AUDITING_EXAMPLE_ID from AUDITING_EXAMPLE where state < 2 and  SYS_CODE_ID = (select SYS_CODE_ID from SYS_CODE where SYS_CODE_VALUE = ? limit 0,1) limit 0,1 ) and ROLE_ID in (?) ",tableName,getUserACL().getRoleIDStr());
//    	  if(map != null && !map.isEmpty()){
	    	  action = regEditAction(this.viewID + "_audit","���","/system/audits_detail.htm");
	    	  action.addParam("CHECK_TYPE",tableName);
	          SubmitTool.submitToDialog(action,this.viewID+ "_audit", "���",730,350,this.modelMap.getPreNavInfoStr());
//    	  }
      }
      if(!GaUtil.haveDelTables.contains(tableName)){
	    StateBiz biz = new StateBiz(getUserACL());
	    if(biz.haveColumn(tableName,"delete_state")){
	      GaUtil.haveDelTables.add(tableName); 	  
	    }
      }
      
      if(GaUtil.haveDelTables.contains(tableName)){
    	  ActionButton delAction = new ActionButton(this.getClass(),this.viewID+"_del","ɾ��",winUrl,paramMap);
          if(isMultiSelect()){
        	  delAction.bindTableMultiSelect(this.viewID);
          }else{
        	  delAction.bindTableRowData(this.viewID);
          }
        
          this.regAction(delAction);
          SubmitTool.submitMvc(delAction, this.modelMap.getPreNavInfoStr());
          delAction.setConfirm("ȷ��ɾ��?���ò��������棩");
          PageEvent delEvent = page.regPageEvent(delAction, isMultiSelect() ? "delObjects" : "delObject");
          if(isMultiSelect()){
        	  delEvent.addEventParam(this,PageEvent.PARAMTYPE_REQUEST,GaConstant.FIXPARAM_MULTISELECT);
          }else{
        	  delEvent.addEventParam(this, PageEvent.PARAMTYPE_QUERYVALUE,tableName+"_ID");
          }
          delEvent.addEventFixValueParam(tableName);
          delEvent.addEventFixValueParam(this.viewID);
      }
     }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
    	ex.printStackTrace();
      throw new BizException(BizException.SYSTEM,"ע�ᰴťʧ��");
    }
  }
  
  public void regAuditAction(ListView listView,String tableName) {
    if(new AuditsBiz(this.modelMap.userACL).isNeedAudit(tableName)){
      ActionButton action = listView.regEditAction(listView.getViewID() + "_audit","���","/system/audexample_detail.htm");
      SubmitTool.submitToDialog(action,listView.getViewID() + "_audit", "���",730,350,this.modelMap.getPreNavInfoStr());
    }
  }
  
  /**
   * ע���¼��а�ť
   * (ֻ���ڱ��༭ģʽ�Ż���ִ˰�ť)
   * @return
   */
  public ActionButton regCreateRowAction(String actionID,String title) {
    if ((
        this.viewEditMode == GaConstant.EDITMODE_NEW || 
        this.viewEditMode == GaConstant.EDITMODE_EDIT)) {
      ActionButton button = new ActionButton(this.getClass(),actionID,title,"",null); //������������
      button.setAttribute("class", "icon");
      button.setOnClick("addTableRow('"+this.viewID+"List')");
      SubmitTool.submitToNavtab(button,"",this.modelMap.getPreNavInfoStr());
      this.regAction(button);
      return button;      
    }
    return null;
  }
  
  /**
   * ע�ᱣ�����¼�
   * Ĭ�������List<Map<String,Object>>
   * @param actionID
   * @param saveMethod
   * @param page
   * @param autoHidden
   * @return
   */
  public ActionButton regSaveTableEvent(String actionID,String title,String saveMethod,UnitPage page) {
    try {
      if (this.viewEditMode ==  GaConstant.EDITMODE_EDIT || 
          this.viewEditMode == GaConstant.EDITMODE_NEW) {
        ActionButton action = new ActionButton(this.getClass(),actionID,title,page.getSelfUrl(),null);
        action.bindForm(this.viewID + "List");
        this.regAction(action);    
        SubmitTool.submitMvc(action,page.getModelMap().getPreNavInfoStr());
        PageEvent event  = page.regPageEvent(action,saveMethod);
        event.addEventParam(this,PageEvent.PARAMTYPE_LISTDATAMAP);
        return action;
      } else {
        return null;
      }
    }
    catch (BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ע�ᱣ�水ťʧ��");
    }
  }
  
  
  
  public void showPage(boolean isShow) {
    this.showPage = isShow;
    if (this.viewControl != null) {
      if (isShow) {
        this.getQueryTable().clearListPaginate();  
      }      
    }
    
  }
  
  public void showQuery(boolean isShow) {
    this.showQuery = isShow;
    if (this.viewControl != null) {
      if (isShow) {
        this.getQueryTable().clearQueryForm();
      }
    }
  }
  
  /**
   * �����Ƿ���ʾɾ����
   * @param dispDeleRow
   */
  public void setDispDeleRow(boolean dispDeleRow) {
    this.dispDeleRow = dispDeleRow;
  }

  /**
   * ��ȡ��ͼ���Ŀؼ�
   * @return
   */
  public Control getViewControl() {
    try {
      if (this.viewControl == null) {
        //���¹���
        this.beforeRender();
        //���õ�����ť
        if (this.isExport) {
          String url = this.modelMap.getRequestURL();
         // url =  GaUtil.addUrlParam(url, "pageAction", "doExport");
          ActionButton action = new ActionButton(this.getClass(),"_dataExport","���ݵ���",url,null); 
          //action.setConfirm("����������ݹ���,�ȴ�ʱ���ϳ�,�Ƿ�ȷ������?");
          action.setClickJS("dataExport('"+this.getViewID()+"')");
          this.regAction(action);
        }
        this.viewControl = this.createControl();
        if (this.viewControl != null) {
          this.getQueryTable().getListTable().setExport(this.getUserACL().isExportAction());
          if (this.showPage) {
            this.getQueryTable().setListPagination();  
          }
          //���÷�ҳĿ��
          if (this.getQueryTable().getListPagination() != null) {
            this.getQueryTable().getListPagination().setNavInfo(GaConstant.NAVTYPE_DIV,this.getDivID(), this.modelMap.getPreNavInfoStr());            
          }
          if (!GaUtil.isNullStr(this.mouseOverJs) && !GaUtil.isNullStr(this.mouseOutJS)) {
            this.getQueryTable().setMouseOver(this.mouseOverJs,this.mouseOutJS);  
          }        
          //���ò�ѯ��
          this.getQueryTable().setQueryForm();
          bindQueryFormFixParam();
          if (this.showQuery) {            
            this.getQueryTable().getQueryForm().setHidden(false);
            this.getQueryTable().getQueryForm().setQueryRows(this.queryRows);
            this.getQueryTable().getQueryForm().setTipInfo(this.queryTip);
          } else {
            this.getQueryTable().getQueryForm().setHidden(true);           
          }   
          this.getQueryTable().setDispDeleRow(this.dispDeleRow);
          //���ò�ѯ��ť
          if (submitAction != null) {
            //�����˵����ύ�¼�(mvc�ύ),���ύ��ť�Ĳ������Ƶ�����
            Map<String,Object> vMap = submitAction.getParamMap();
            if (vMap != null) {
              for(String key: vMap.keySet()) {
                Object v = vMap.get(key);
                HiddenField field  = new HiddenField(key,v.toString());
                this.getQueryTable().getQueryForm().add(field);
              }
            }
            HiddenField field  = new HiddenField("pageAction","doController");
            submitAction.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV, this.modelMap.getPreNavInfoStr());
            this.getQueryTable().getQueryForm().add(field);
            this.getQueryTable().getQueryForm().setSubmitButton(submitAction);            
          } else {
            this.getQueryTable().getQueryForm().setQueryNavInfo(this.modelMap.getNavInfoStr(),this.modelMap.getPreNavInfoStr());
          }
        }
      } 
      return this.viewControl;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
    	ex.printStackTrace();
    	
      throw new BizException(BizException.SYSTEM,"��ȡ��ͼ�ؼ�ʱ�쳣");
    }
  }
  
  private void bindQueryFormFixParam() {
    try {
      ListForm form  = this.getQueryTable().getQueryForm();
      boolean havePage = this.showPage;
      if (havePage) {   
        if (this.viewPageQuery == null || this.viewPageQuery.GetPageParam() == null) {
          this.viewPageQuery = new QueryPageData();
        }
        String tmpV = String.valueOf(this.viewPageQuery.GetPageParam().getPageNumber());
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGENO,tmpV);
        form.add(field);
        
        tmpV = String.valueOf(this.viewPageQuery.GetPageParam().getPageSize());
        field  = new HiddenField(GaConstant.FIXPARAM_PAGESIZE,tmpV);
        form.add(field);
        
        tmpV = String.valueOf(this.viewPageQuery.GetPageParam().getOrderFiledName());
        field  = new HiddenField(GaConstant.FIXPARAM_PAGE_ORDERF,tmpV);
        form.add(field);
        
        tmpV = String.valueOf(this.viewPageQuery.GetPageParam().getOrderFileOrder());
        field  = new HiddenField(GaConstant.FIXPARAM_PAGE_ORDERT,tmpV);
        form.add(field); 
      }
      String tmpV = this.getRequestValue(GaConstant.FIXPARAM_EDITMODE);
      if (!GaUtil.isNullStr(getRequestValue(GaConstant.FIXPARAM_EDITMODE))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_EDITMODE,tmpV);
        form.add(field);
      }         
      tmpV = getRequestValue(GaConstant.FIXPARAM_ROWDATA);
      if (!GaUtil.isNullStr(getRequestValue(GaConstant.FIXPARAM_ROWDATA))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_ROWDATA,tmpV);
        form.add(field);
      }
      tmpV = getRequestValue(GaConstant.FIXPARAM_LOOKUPMODE);
      if (!GaUtil.isNullStr(getRequestValue(GaConstant.FIXPARAM_LOOKUPMODE))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_LOOKUPMODE,tmpV);
        form.add(field);
      }
      //�̶�д�뵼����Ϣ
//      HiddenField field  = new HiddenField(NoahConstant.FIXPARAM_WINDOWNAV,this.modelMap.getNavInfoStr());
//      form.add(field);      
//      field  = new HiddenField(NoahConstant.FIXPARAM_PREWINDOWNAV,this.modelMap.getPreNavInfoStr());
//      form.add(field);
//      field  = new HiddenField("pageAction","doController");
//      form.add(field);
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM, "������ʧ��", ex);
    }    
  }

  public void setSubmitAction(ActionButton submitAction) {
    this.submitAction = submitAction;
  }
  
  /**
   * ��������
   * @param name
   * @param value
   */
  public void addHiddenParam(String name,Object value) {
    if (this.viewControl == null) {
      throw new BizException(BizException.SYSTEM, "�ؼ�δ����,�޷���������ֶ�,�볢���ڰ�ֵ������ֶ�");
    }
    HiddenField field = new HiddenField(name,value);
    this.getQueryTable().getQueryForm().add(field);
  }
  
  public void setQueryRows(int rows) {
    this.queryRows = rows;
  }

  public String getQueryTip() {
    return queryTip;
  }

  public void setQueryTip(String queryTip) {
    this.queryTip = queryTip;
  }
  
  public void setExport(boolean export) {
    this.isExport = export;
  }

  public boolean isExport() {
    return isExport;
  }

public boolean isMultiSelect() {
	return multiSelect;
}

public void setMTableMode(boolean isMTableMode) {
	this.isMTableMode = isMTableMode;
	this.getQueryTable().setMTableMode(this.isMTableMode);
}
   
}
