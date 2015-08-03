package com.ga.click.mvc;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.click.Control;
import org.apache.click.control.Field;
import org.apache.click.control.Form;
import org.apache.click.util.ClickUtils;
import org.apache.click.util.HtmlStringBuffer;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.form.DataForm;
import com.ga.click.control.form.ListForm;
import com.ga.click.control.table.QueryTable;
import com.ga.click.control.tree.TreeControl;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

/**
 * ��ͼ����
 * ��ͼ�Ƕ�(List/Form/Tree)�������ؼ��İ�װ,���ؼ�ͬ���ݰ󶨵�һ��
 * ������ͼ���õķ���
 * @author Administrator
 *
 */
public class View {

  protected int viewEditMode = 0;
  protected String viewID = "";
  protected int viewType = 0;
  protected List<DbField> fieldList = new ArrayList<DbField>();
  protected List<ActionButton> actionList = new ArrayList<ActionButton>();
  
  protected Control viewControl = null;  
  protected Map<String,Object> viewData = null; 
  protected List<Map<String,Object>> viewListData = null; 
  
  protected Map<String,Object> viewParam = new HashMap<String,Object>(); //���в����ֶ�ֵ
  protected QueryPageData viewPageQuery = null;  //��ѯ��������,���Զ���ϱ��ʽ

  protected Map<String,String> funcMap = new HashMap<String,String>();
  protected ModelMap modelMap = null;
  
  protected Map<String,String[]> requestValueMap; 
  
  protected JSONObject rowDataObj = null;
  protected boolean exportMark = false;
  protected String exportFields = "";
  

  /**
   * ������ͼ����
   * @param viewID ��ͼID
   * @param viewType ��ͼ����
   * @param viewEditMode ��ͼ�༭ģʽ
   */
  public View(String viewID,int viewType,ModelMap modelMap) {
    try {
      if (GaUtil.isNullStr(viewID)) {
        throw new BizException(BizException.SYSTEM,"δָ����ͼID");
      }
      this.viewID = viewID;
      this.viewType = viewType;
      this.viewEditMode = modelMap.getPageEditMode();
      this.modelMap = modelMap;
      this.initRequestMap(this.modelMap);
      this.initField();
      
      /**************Ф�����޸�2014-07-9�������Զ���FORMVIEW�ĵ���ѡ����д����������Ա�ǵ��̹���Ա������ֻ��һ�ҵ��̵Ĺ���Ȩ����Ĭ�����Լ��ĵ��̣�����Ҫѡ��*******************************/
      if(this.viewType == GaConstant.VIEWTYPE_FORM){
        if(this.getFieldByCode("STORE_ID") != null ){
        	DbField storeField = this.getFieldByCode("STORE_ID");
        	DbField storeNameField = this.getFieldByCode("STORE_NAME");
        	if(!GaUtil.isNullStr(storeField.getPopID())){
        		LookupDataSet dataSet =  getUserACL().getStoreName();
        		if(dataSet != null && dataSet.getLookupDataMap() != null && dataSet.getLookupDataMap().size()  == 1){
        			storeNameField.clearPopSelect();//ȡ��
        			for(String storeId : dataSet.getLookupDataMap().keySet()){
        				storeField.setDefaultValue(storeId);
            			storeNameField.setDefaultValue(dataSet.getLookupDataMap().get(storeId));	
        			}
        		}	
        	}
        }
      }
      
      /*********************************************/
      
      this.initAction();
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"������ͼ�����쳣");
    }
  }
  
  public View(String viewID,int viewType,ModelMap modelMap,int editMode) {
    try {
      this.viewEditMode = editMode;
      if (GaUtil.isNullStr(viewID)) {
        throw new BizException(BizException.SYSTEM,"δָ����ͼID");
      }
      this.viewID = viewID;
      this.viewType = viewType;
      this.modelMap = modelMap;
      this.initRequestMap(this.modelMap);
      this.initField();
      this.initAction();
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"������ͼ�����쳣");
    }
  }
  /**
   * ��ʼ����ǰ��ͼ�Ĳ���ֵ
   * (���ֶ���͵���ģʽ)
   * @param modelMap
   */
  private void initRequestMap(ModelMap modelMap) {
    String multiForm = this.modelMap.getRequest().getParameter(GaConstant.FIXPARAM_MULTIFORM);
    if (GaUtil.isNullStr(multiForm)) {
        this.requestValueMap = this.modelMap.getRequest().getParameterMap();
    } else {
      String paramJson;
      if (this instanceof ListView) {
        paramJson = this.modelMap.getRequest().getParameter(this.viewID+"List");
      } else {
        paramJson = this.modelMap.getRequest().getParameter(this.viewID);
      }

      try {
        this.requestValueMap = new HashMap<String,String[]>();
        if (GaUtil.isNullStr(paramJson)) {
          
        } else {
          if (paramJson.startsWith("[")) {
            JSONArray jsonArray = new JSONArray(paramJson);
            for (int i=0;i<jsonArray.length();i++) {
              Object getObj = jsonArray.get(i);
              if (getObj instanceof JSONObject) {
                JSONObject jsonObj = (JSONObject)getObj;
                Iterator it = jsonObj.keys();
                String name = "";
                String[] values = null;
                while (it.hasNext()) {
                  String key = (String)it.next();
                  String v = jsonObj.getString(key);
                  values = null;
                  if (key.toLowerCase().equals("name")) {
                    name = v;
                  } else if (key.toLowerCase().endsWith("value")) {      
                    if (v != null) {       
                      if (this.requestValueMap.get(name) == null) {
                        values = new String[1];
                        values[0] = v;
                      } else {
                        values = this.requestValueMap.get(name);
                        List<String> vList = new ArrayList<String>();
                        vList.addAll(Arrays.asList(values));
                        vList.add(v);
                        values = vList.toArray(new String[vList.size()]);
                      }
                    } else {
                      this.requestValueMap.put(name, null);
                    }
                  }
                }
                if (!GaUtil.isNullStr(name)) {
                  this.requestValueMap.put(name, values);
                }
              }
            }
          }
          
        }       
      } catch(Exception ex) {
        ex.printStackTrace();
      }
    }
    //��ʼ��rowobj��Ϣ
    String rowJson = this.getRequestValue(GaConstant.FIXPARAM_ROWDATA);
    if (!GaUtil.isNullStr(rowJson)) {
      try {
        this.rowDataObj = new JSONObject(rowJson);
      } catch(Exception e) {
        
      }
    }
    //����ѡ��Ϣ����rowdata��
    String ids = this.getRequestValue(GaConstant.FIXPARAM_MULTISELECT);
    if (!GaUtil.isNullStr(ids)) {
      try {
        if (this.rowDataObj == null) this.rowDataObj = new JSONObject("{}");
        this.rowDataObj.put(GaConstant.FIXPARAM_MULTISELECT, ids);
      } catch(Exception e) {       
      }
    }
    //�����������
    String exportStr = this.getRequestValue(GaConstant.FIXPARAM_EXPORTMARK);
    if (!GaUtil.isNullStr(exportStr)) {
      this.exportMark = true;
      this.exportFields = exportStr;
    }
  }

  /**
   * ���Ĭ�϶����ActionButton
   */
  public void clearAction() {
    this.actionList.clear();
  }
  
  public List<ActionButton> getActionList() {
    return this.actionList;
  }
  /**
   * ���б�ؼ�����
   * @param result
   */
  public void bindData(List<Map<String,Object>> result) {
    try {
      this.viewListData = result;
      QueryTable table = this.getQueryTable();
      PageResult pageResult = new PageResult();
      pageResult.setDataList(result);
      table.setDataSource(pageResult);
      this.modelMap.putBindDataView(this);
    }
    catch (BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"�������쳣");
    }
  }

  /**
   * ���б�ؼ�����
   * @param result
   */
  public void bindData(PageResult result) {
    try {
      QueryTable table = this.getQueryTable();
      table.setDataSource(result); 
      
      this.bindListQueryForm(this.getViewParam());
      this.modelMap.putBindDataView(this);
    }
    catch (BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"�������쳣");
    }
  }
  
  /**
   * ��DataForm�ؼ�����
   * @param formData
   */
  public void bindData(Map<String,Object> formData) {
    try {
      this.viewData = formData;
      DataForm form = this.getDataForm();
      this.putFormValue(form, formData, this.fieldList,false);
      this.modelMap.putBindDataView(this);
    }
    catch (BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"�������쳣");
    }
  }
  
  /**
   * ���б��ѯ��
   * @param queryData ��ѯ����
   */
  public void bindListQueryForm(Map<String,Object> queryData) {
    try {
      QueryTable table = this.getQueryTable();
      if (table.getQueryForm() != null) {
        List<DbField> dbFieldList = new ArrayList<DbField>();
        for (DbField field : this.fieldList) {
          if (field.isQueryParam()) {
            dbFieldList.add(field);            
          }
        }
        table.getQueryForm().setActionURL(this.modelMap.getRequestURL());
        this.putFormValue(table.getQueryForm(), queryData, dbFieldList,true);  
      }      
    }
    catch (BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"�������쳣");
    }
  }
  
  /**
   * �����ؼ�����
   * @param treeData
   */
  public void bindData(TreeNode treeData,String url,boolean isExpand) {
    try {
      if (this.viewType != GaConstant.VIEWTYPE_TREE) {
        throw new BizException(BizException.SYSTEM,"��ͼ���Ͳ��������ͣ��޷�ִ�д˰�");
      }
      TreeControl treeControl = new TreeControl(this.viewID,treeData,url,isExpand,this.getUserACL());
      if (this.actionList != null && this.actionList.size() > 0) {
        ActionButton action = this.actionList.get(0);
        if (action != null) {
          //��������Ϣ
          treeControl.setAttribute(GaConstant.FIXPARAM_WINDOWNAV, action.getAttributeMap().get(GaConstant.FIXPARAM_WINDOWNAV));
          treeControl.setAttribute(GaConstant.FIXPARAM_PREWINDOWNAV, action.getAttributeMap().get(GaConstant.FIXPARAM_PREWINDOWNAV));
        }
      }
      this.viewControl = treeControl;
      this.modelMap.putBindDataView(this);
    }
    catch (BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"�������쳣");
    }
  }
  
  public void bindNull() {
    
  }
  
  public void parseRequest() {
    try {
      //��������Map
      for (DbField field : this.fieldList) {
        if (field.isQueryParam()) {
          //��Ϊ��������
          Object value = this.parseDbFieldQueryParam(field);
          this.viewParam.put(field.getFieldCode(), value);
        }
      }
      //������ҳ��ѯ����
      if (this.viewType == GaConstant.VIEWTYPE_LIST  || this.viewType == GaConstant.VIEWTYPE_QUERY) {
        this.viewPageQuery = new QueryPageData();
        this.viewPageQuery.putData(fieldList,this);        
      } 
      //������ͼ��������
      if (this.viewType == GaConstant.VIEWTYPE_LIST && this.viewEditMode != GaConstant.EDITMODE_DISP) {
        this.viewListData = this.parseListValue(this.fieldList);
      } else if (this.viewType == GaConstant.VIEWTYPE_FORM) {
        this.viewData = new HashMap<String,Object>();
        for (DbField field : this.fieldList) {
          if (field.getInputType() > 0) {
            //��Ϊ�������ݷ���
            Object value = this.parseDbFieldInputData(field);
            this.viewData.put(field.getFieldCode(), value);
          }
        }
      }
      //�жϵ�ǰ��ͼ�Ƿ���Ҵ���ģʽ
      if ( this.modelMap.isLookupMode() 
          &&this.viewID.equals(modelMap.getLookupControlID()) 
          &&this.viewType == GaConstant.VIEWTYPE_LIST ) {
        for(DbField field : this.fieldList) {
          if (modelMap.getLookupFieldSet().contains(field.getFieldCode(false))) {
            field.setBindRowData(true);
          } else {
            field.setBindRowData(false);
          }
        }
      }
    }
    catch (BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"������ͼ�ύ����ʱ�쳣");
    }
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
        this.viewControl = this.createControl();
      } 
      return this.viewControl;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"��ȡ��ͼ�ؼ�ʱ�쳣");
    }
  }
  
  /**
   * ��ȡ��ͼ�ؼ�(��ѯ��)
   * @return
   */
  public ListForm getQueryForm() {
    try {
      if (this.viewType != GaConstant.VIEWTYPE_QUERY) {
        throw new BizException(BizException.SYSTEM,"��ͼ���Ͳ��ǲ�ѯ���ͣ��޷����ز�ѯ���ؼ�");
      }
      return (ListForm)this.getViewControl();    
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw  new BizException(BizException.SYSTEM,"���ز�ѯ���ؼ�ʱ�쳣");
    }   
  }
  /**
   * ��ȡ��ͼ�ؼ�(��ϸ��)
   * @return
   */
  public DataForm getDataForm() {
    try {
      if (this.viewType != GaConstant.VIEWTYPE_FORM) {
        throw new BizException(BizException.SYSTEM,"��ͼ���Ͳ������ݱ����ͣ��޷��������ݱ��ؼ�");
      }
      return (DataForm)this.getViewControl();    
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw  new BizException(BizException.SYSTEM,"�������ݱ��ؼ�ʱ�쳣");
    }
  }
  /**
   * ��ȡ��ͼ�ؼ�(�б�ؼ�)
   * @return
   */
  public QueryTable getQueryTable() {
    try {
      if (this.viewType != GaConstant.VIEWTYPE_LIST) {
        throw new BizException(BizException.SYSTEM,"��ͼ���Ͳ����б����ͣ��޷������б�ؼ�");
      }
      return (QueryTable)this.getViewControl();    
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw  new BizException(BizException.SYSTEM,"�����б�ؼ�ʱ�쳣");
    }
  }
  
  /**
   * ��ȡ��ͼ�ؼ�(���ؼ�)
   * @return
   */
  public TreeControl getTreeControl() {
    try {
      if (this.viewType != GaConstant.VIEWTYPE_TREE) {
        throw new BizException(BizException.SYSTEM,"��ͼ���Ͳ��������ͣ��޷��������ؼ�");
      }
      return (TreeControl)this.getViewControl();    
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw  new BizException(BizException.SYSTEM,"�������Ϳؼ�ʱ�쳣");
    }
  }
  
  /**
   * ���ݶ��崴����ͼ�ؼ�
   * @return
   * @throws Exception
   */
  protected Control createControl() throws Exception {
    switch (this.viewType) {
    case GaConstant.VIEWTYPE_FORM:
      DataForm form = new DataForm(this.viewID,this.fieldList,this.viewEditMode,this.getUserACL());
      form.setActionURL(this.modelMap.getRequestURL());
      if (this.actionList != null) {
        for(ActionButton action : this.actionList) {
          form.regFormButton(action);
        }
      }
      return form;
    case GaConstant.VIEWTYPE_LIST:
      QueryTable list = new QueryTable(this.viewID,this.fieldList,this.viewEditMode,this.getUserACL());
      if (this.actionList != null && !this.modelMap.isLookupMode()) {
        for(ActionButton action : this.actionList) {
          list.regToolBar(action);
        }
      }
      return list;
    case GaConstant.VIEWTYPE_QUERY:
      ListForm listForm = new ListForm(this.fieldList,this.getUserACL(), viewID);
      if (this.modelMap != null && listForm != null) {
        listForm.setQueryNavInfo(this.modelMap.getNavInfoStr(),this.modelMap.getPreNavInfoStr());
      }
      return listForm;
    case GaConstant.VIEWTYPE_TREE:
      throw new BizException(BizException.SYSTEM,"�������ؼ�������ִ��BindData����");
      default:
        throw new BizException(BizException.SYSTEM,"ָ����ͼ������Ч,�޷�������ͼ�ؼ�");
    }    
  }
  
  /**
   * ����ͼ�ؼ�������Ⱦ
   * @param buff
   */
  public void render(HtmlStringBuffer buff) {
    try {
      Control control = this.getViewControl();
      //����ť
      for (ActionButton action : this.actionList) {
        if (!this.getUserACL().checkAction(action.getId(),action.getTitle())) {
          this.actionList.remove(action);
        }
      }
      control.render(buff);
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw  new BizException(BizException.SYSTEM,"��Ⱦ��ͼ�ؼ�ʱ�쳣");
    }
  }
  
  
  /**
   * ��ͼ��ʼ������,�����ش˷�������dbField��ʼ��,�Ա���ͼ����
   * ע��:��ʼ���ֶ�ʱ�ɸ��ݲ�ͬģʽ���пؼ���ʼ��
   * �˷����ɲ������쳣����
   * @throws Exception
   */
  public void initField() throws Exception{    
  }
  
  /**
   * ��ʼ����ͼ��ť
   * @throws Exception
   */
  public void initAction() throws Exception {    
  }
  
  
  /**
   * ����code����Field
   * @param fieldCode
   * @return
   */
  public DbField getFieldByCode(String fieldCode) {
    for(DbField field : this.fieldList) {
      if (field.getFieldCode().equals(fieldCode)) {
        return field;
      }
    }
    return null;
  }
  
  /**
   * ��ȡ��ť����
   * @param actionID ��ťID(��������ťǰ��class)
   * @return ��ť����
   */
  public ActionButton getActionByID(String actionID) {
    for (ActionButton action : this.actionList) {
      if (action.getId().equals(actionID)) {
        return action;
      }
    }
    return null;
  }
  
  /**
   * ��ȡָ���ֶε�����
   * @param fieldCode
   * @return
   */
  public Class getFieldClass(String fieldCode) {
    DbField field = this.getFieldByCode(fieldCode);
    switch (field.getDataType()) {
    case GaConstant.DT_DATETIME:
      return Date.class;
    case GaConstant.DT_DOUBLE:
      return Double.class;
    case GaConstant.DT_INT:
      return Integer.class;
    case GaConstant.DT_LONG:
      return Long.class;
    case GaConstant.DT_MONEY:
      return BigDecimal.class;
    case GaConstant.DT_PASSWORD:
    case GaConstant.DT_STRING:
     default:
       return String.class;
    }
  }
  /**
   * ע��dbField�ֶ�
   * @param dbField
   */
  public void regField(DbField dbField) {
    this.fieldList.add(dbField);
  }
  
  /**
   * ע����ͼ��ذ�ť
   * @param action
   */
  public void regAction(ActionButton action) {
    this.actionList.add(action);
  }
  
  public String getViewID() {
    return viewID;
  }
  
  
  /**
   * ����DBFIELD�ֶε�����ֵ,��������:
   * 1.���ֵ����������ؼ�,����ݶ���ȡֵ��ת��
   * 2.��ֻ�����˲�ѯ�󶨲���(REQUEST/SESSION/ROWOBJ),��Ҳ�᷵�����󶨵�ֵ
   * 3.��ͬʱ����������ؼ��Ͳ�ѯ�󶨣��򷵻�ֵ�Բ�ѯ��Ϊ����
   * @param field
   * @param modelMap
   * @return
   */
  protected Object parseDbFieldInputData(DbField field) {
    try {
      Object valueObject = null;
      //��ֵͨ,ֱ�Ӵ�request�ж�ȡ
      String[] values = null;
      String value = null;
      //������ϸ���ݲ���
      if (field.getInputType() > 0) {
        switch (field.getInputType()) {
          case GaConstant.INPUT_TREE:
            values = this.getRequestValues(field.getFieldCode());
            List<String> valueList = new ArrayList<String>();
            if(values != null && values.length > 0){
                for(String v : values){
                    valueList.add(v);
                }
            }
            valueObject = valueList;
            break;
          case GaConstant.INPUT_POPSELECT:
            value = this.getRequestValue(field.getPopID()+"."+field.getPopBindField());
            valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
            break;
          case GaConstant.INPUT_MONEY:
            value = this.getRequestValue(field.getFieldCode());
            try {
              if (GaUtil.isNullStr(value)) {
                value = "0";
              }
              valueObject =  GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
            } catch(Exception e) {
               throw new BizException(BizException.COMMBIZ,"�����Ϣ�����ʽ����ȷ");
            }
            break;
          case GaConstant.INPUT_SELECTLIST:
            if (field.isMultiSelect()) {
              //�б�
              values = this.getRequestValues(field.getFieldCode());
              List<Object> setV = null;
              if (values != null) {
                setV = new ArrayList<Object>();
                for (int i=0;i<values.length;i++) {
                  setV.add(GaUtil.convertData(values[i],field.getDataType(),field.getFormatPattern()));
                }
              }
              valueObject = setV;
            } else {
              value = this.getRequestValue(field.getFieldCode());
              valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
            }
            break;          
          case GaConstant.INPUT_REQUEST:
            value = this.getRequestValue(field.getInputSourceName());
            if (GaUtil.isNullStr(value)) {
            	value = this.getRowDataValue(field.getInputSourceName());
            } 
            valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
            break;
          case GaConstant.INPUT_ROWOBJ:
            value = this.getRowDataValue(field.getInputSourceName());
            if (GaUtil.isNullStr(value)) {
            	value = this.getRequestValue(field.getInputSourceName());
            }
            valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
            break;
          case GaConstant.INPUT_SESSION:
            valueObject = modelMap.getSession(true).getAttribute(field.getInputSourceName());
            break;
          case GaConstant.INPUT_CHECKLIST:
            values = this.getRequestValues(field.getFieldCode());
            valueObject = StringUtils.join(values,",");
            break;
          default:
            value = this.getRequestValue(field.getFieldCode());
            valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
        }       
      }
      return valueObject;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"����DBField�����쳣");
    }
  }
  
  
  /**
   * �����ֶεĲ�ѯ����ֵ,ֻҪ������getQueryInputType�Ķ��ܻ�ȡֵ,��δ����ֵ��Ϊnull
   * ������Ҫ����ֵ��queryopera,�򷵻�List����,��һ��Ϊ���ޣ��ڶ���Ϊ������
   * @param field �ֶ�
   * @param modelMap ҳ�����
   * @return
   */
  protected Object parseDbFieldQueryParam(DbField field) {
    try {
      Object valueObject = null;
   //   String[] values = null;
      String value = null;
      if (field.getQueryInputType() > 0) {
        switch (field.getQueryOpera()) {    
          case GaConstant.QUERY_BETWEEN:
            //���ǱȽϲ���,��������ֵ
            String minV =  this.getRequestValue(field.getFieldCode()+"_min_");
            String maxV =  this.getRequestValue(field.getFieldCode()+"_max_");
            List<Object> setV = new ArrayList<Object>();
            setV.add(GaUtil.convertData(minV,field.getDataType(),field.getFormatPattern()));
            setV.add(GaUtil.convertData(maxV,field.getDataType(),field.getFormatPattern()));            
            valueObject = setV;
            break;
          default:
            if (field.getQueryInputType() == GaConstant.INPUT_POPSELECT) {
              value = this.getRequestValue(field.getPopID()+"."+field.getPopBindField());
              valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
            }
            else {
              value = this.getRequestValue(field.getFieldCode());
              valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
            }
            break;
        }
      }      
      if (field.getQueryInputType() == GaConstant.INPUT_SESSION) {
        //��session�ж�ȡ
        valueObject = this.modelMap.getSession(true).getAttribute(field.getParamSoureName());          
      } else {
        if (field.getQueryInputType() == GaConstant.INPUT_REQUEST) {
          //��request�ж�ȡ
          value = this.getRequestValue(field.getParamSoureName());
          if (GaUtil.isNullStr(value)) {
            value = this.getRowDataValue(field.getParamSoureName());
          }
          valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
        }  else if (field.getQueryInputType() == GaConstant.INPUT_ROWOBJ)  {
          //��rowobj�ж�ȡ    
          value = this.getRowDataValue(field.getParamSoureName());
          if (GaUtil.isNullStr(value)) {
            value = this.getRequestValue(field.getParamSoureName());
          }
          valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
       }  
     }      
      return valueObject;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"����DBField�����쳣");
    }
  }
  
  
  /**
   * ����dbField���ж����ȡ�ύֵ(ֻ�б���ύ����ô˺�������)
   * Map�ṹΪ:string-�ֶ���,Object-�ֶ�ֵ
   * @param request
   * @param dbFieldList
   * @return
   */
  public  List<Map<String,Object>> parseListValue(List<DbField> dbFieldList) {
    try {
      //�����ύ������
    Map<String,String[]> valueMap   = this.requestValueMap;
      Map<Integer,Map<String,Object>> getValue = new TreeMap<Integer,Map<String,Object>>();
      Map<String,String> popFieldMap = new HashMap<String,String>();
      for (DbField field : dbFieldList) {
        if (field.getInputType() == GaConstant.INPUT_POPSELECT) {
          String popName = field.getPopID()+"."+field.getPopBindField();
          popFieldMap.put(popName,field.getFieldCode());
        }
      }
      for(String name : valueMap.keySet()) {
        if (name.startsWith("items[")) {         
          int end1 = name.indexOf("[");
          int end2  = name.indexOf("]");
          int start  = name.lastIndexOf("."); //���һ����
          if (start == -1 || end1 == -1 || end2 == -1) {
            continue;
          }
          String[] value = valueMap.get(name);
          String posIndexStr = name.substring(end1+1,end2);
          String fieldName = name.substring(start + 1);
          String popName = name.substring(end2+2);          
          Integer posIndex  = 0;
          try {
            posIndex = Integer.parseInt(posIndexStr);  
          } catch(Exception x) {
            continue;
          }
          //���ж��Ƿ���ֶ�
          String popFieldName = popFieldMap.get(popName);
          DbField field = null;
          if (GaUtil.isNullStr(popFieldName)) {
            field = getFieldByCode(fieldName);
          } else {
            fieldName = popFieldName;
            field =  getFieldByCode(fieldName);
          }
          if (field == null) {
            continue;
          }
          Object valueObject = null;        
          if (value != null && value.length >0) {
            String getV = value[0];
            valueObject = GaUtil.convertData(getV,field.getDataType(),field.getFormatPattern());
          }   
          //��ֵ�����ֶ�
          Map<String,Object> fieldValue = getValue.get(posIndex);
          if (fieldValue == null) {
            fieldValue = new HashMap<String,Object>();
            getValue.put(posIndex, fieldValue);
          }
          fieldValue.put(fieldName, valueObject);
        }
      }
      //ִ������,תΪList
      List<Map<String,Object>> rtnList = new ArrayList<Map<String,Object>>();
      Iterator<Map<String,Object>> it = getValue.values().iterator();      
      while(it.hasNext()){
        Map<String,Object> rowV = (Map<String,Object>)it.next();
        rtnList.add(rowV);
      }
      return rtnList;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"");
    }
  }
  
  /**
   * ��map�ṹ�е�ֵ����dbField������䵽���ؼ���
   * @param form ���ؼ�
   * @param dataMap ����
   */
  protected  void putFormValue(Form form,Map<String,Object> dataMap,List<DbField> dbFieldList,boolean isQueryInput) {
   try {       
        //�������ֶε�ֵתΪstring
     HashMap setMap  = new HashMap<String,Object>();
     if (dataMap != null) {
      for(DbField field : dbFieldList) {
        //���⴦��clob��������
        Object value = dataMap.get(field.getFieldCode());

        setMap.put(field.getFieldCode(), value);
        if (value != null && value instanceof Clob) {
          setMap.put(field.getFieldCode(),GaUtil.clobToString((Clob)value));
        } else if (value != null && value instanceof Timestamp) {
          value = ClickUtil.getDisplayValue(field, value);
          setMap.put(field.getFieldCode(), value);
        }
        
        if (isQueryInput) {
          //��ѯ��������
          if (field.getQueryInputType() == GaConstant.INPUT_HIDDEN || 
              field.getQueryInputType() == GaConstant.INPUT_REQUEST ||
              field.getQueryInputType() == GaConstant.INPUT_ROWOBJ
              ) {
            //���ȴ������������������Ϊstring
            if (value != null) {
              setMap.put(field.getFieldCode(), value.toString());
            } else {
              //����Ĭ��ֵ
              if (dataMap.get(field.getFieldCode()) == null) {
                setMap.put(field.getFieldCode(), field.getDefaultValue().toString());
              }
            }
          }
          else if (dataMap.get(field.getFieldCode()) == null) {
            //�����ѡĬ��ֵ
            setMap.put(field.getFieldCode(), field.getDefaultValue());
          } else if (field.getQueryOpera() == GaConstant.QUERY_BETWEEN) {
            String fieldCode = field.getFieldCode();
            List v = (List)value;
            if (v != null) {      
              if (field.getDataType() == GaConstant.DT_MONEY) {
                  setMap.put(fieldCode+"_min_", v.get(0));
                  setMap.put(fieldCode+"_max_", v.get(1));
              } else {
                 setMap.put(fieldCode+"_min_", ClickUtil.getDisplayValue(field, v.get(0)));
                 setMap.put(fieldCode+"_max_", ClickUtil.getDisplayValue(field, v.get(1)));
              }
            }
          }
        } else {
          //��ֵ������ȡ
          if (field.getInputType() == GaConstant.INPUT_HIDDEN || 
              field.getInputType() == GaConstant.INPUT_REQUEST ||
              field.getInputType() == GaConstant.INPUT_ROWOBJ
              ) {
            //���ȴ������������������Ϊstring
            if (value != null) {
              setMap.put(field.getFieldCode(), value.toString());
            } else {
              //����Ĭ��ֵ
              if (dataMap.get(field.getFieldCode()) == null) {
                setMap.put(field.getFieldCode(), field.getDefaultValue().toString());
              }
            }
          }
          else if (dataMap.get(field.getFieldCode()) == null) {
            Field control = form.getField(field.getFieldCode());
            if (control != null) {
              control.setValue("");
              control.setValueObject(null);
            }
            //����Ĭ��ֵ
            setMap.put(field.getFieldCode(), field.getDefaultValue());
          } 
        }
      }
     }
      //���⴦��pop���Ϳؼ�(���Ʋ�һ���޷�pop)      
      ClickUtils.copyObjectToForm(setMap, form,false);
      //�ж��Ƿ��ж�ѡ�ؼ�      
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"����ת��ʧ��",ex);
    }    
  }


  public List<DbField> getFieldList() {
    return fieldList;
  }
  
  /**
   * �����ֶ��б�
   * @param fieldList
   */
  public void setFiledList(List<DbField> fieldList) {
    this.fieldList = fieldList;
  }

  public Map<String, Object> getViewData() {
    return viewData;
  }


  public List<Map<String, Object>> getViewListData() {
    return viewListData;
  }


  public Map<String, Object> getViewParam() {
    return viewParam;
  }


  public QueryPageData getViewPageQuery() {
    return viewPageQuery;
  }
  
  public Object getFieldParamValue(String fieldCode) {
    return this.viewParam.get(fieldCode);
  }
  
  public Object getFieldInputValue(String fieldCode) {
    return this.viewData.get(fieldCode);
  }
  
  public String getDivID() {
   return this.viewID+"Div"; 
  }


  public Map<String, String> getFuncMap() {
    return funcMap;
  }
  
  public DbField getPKField() {
    if (this.fieldList != null) {
      for(DbField field : this.fieldList) {
        if (field.isPK()) return field;
      }
    }
    return null;
  }  
  
 
  /**
   * ��ȡrequest�е�ֵ
   * @param paramName
   * @return
   */
  public String getRequestValue(String paramName) {
    if (this.requestValueMap == null ) return null;
    String[] v = this.requestValueMap.get(paramName);
    if (v == null || v.length != 1) {
      return null;
    }
    return v[0];
  }
  
  /**
   * ��ȡrequest��ֵ
   * @param paramName
   * @return
   */
  public String[] getRequestValues(String paramName) {
    if (this.requestValueMap == null ) return null;
    String[] v = this.requestValueMap.get(paramName);
    return v;
  }
  
  public JSONObject getRowDataObj() {
    return rowDataObj;
  }
  
  
  public void setViewEditMode(int viewEditMode) {
	this.viewEditMode = viewEditMode;
}

public String getRowDataValue(String param) {
    if (this.rowDataObj == null) return null;
    try {
      return this.rowDataObj.getString(param);  
    } catch(Exception e) {
      return null;
    }    
  }
  /**
   * ��Ⱦ֮ǰ��Ԥ���¼�����ͨ�����ظ�������ؼ���ֻ��״̬��
   */
  public void beforeRender() {   
  }
  
  
  public UserACL getUserACL() {
    return this.modelMap.getUserACL();
  }
  
//  public void removeDbField(String fieldCode){
//    for(DbField filed : this.fieldList){
//      if(filed.getFieldCode().equals(fieldCode)){
//        fieldList.remove(filed);
//        break;
//      }
//    }
//  }
  public void removeDbField(String... fieldCode){
	    for(String str : fieldCode){
	    	for(DbField filed : this.fieldList){
		      if(filed.getFieldCode().equals(str)){
		        fieldList.remove(filed);
		        break;
		      }
	      }
	    }
	  }
  
}
