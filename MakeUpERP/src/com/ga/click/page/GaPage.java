package com.ga.click.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.click.Page;
import org.apache.click.control.Field;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Select;
import org.apache.click.util.ClickUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.tree.TreeField;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.util.GaUtil;

public abstract class GaPage extends Page{
	
  protected Layout layout;
  
  //������ʽ����
  protected JSONObject rowDataObj = null;
  protected String actionID = "";
  protected String divID = "";
  protected String windowNavType = GaConstant.NAVTYPE_TAB; //���ڵ�����ʽ
  protected String windowNavID = "main";  //��ǰ���ڵ���Ŀ��ID
  protected String navInfoStr = "";
  protected String preNavInfoStr = "";
  protected int pageEditMode = GaConstant.EDITMODE_DISP;
  
  public Layout getLayout() {
    return layout;
  }
  
  /**
   * ��ɳ�ʼ������
   */
  public void onInit() {
    try {
      this.initControl();
      this.initForm();
      this.initAction();
      this.layout = this.initLayout();
    } catch(BizException ex) {
      throw ex;
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"ִ�г�ʼ��ʧ��",e);
    }    
  }
  
  protected final void initNavInfo() {      
    //��ǰҳ��򿪷�ʽ(��,�ָ����ַ�)ֻ���������û�д��ҳ��
    navInfoStr = getContext().getRequest().getParameter(GaConstant.FIXPARAM_WINDOWNAV);
    if (!GaUtil.isNullStr(navInfoStr)) {
      String[] navInfo = navInfoStr.split(",");
      windowNavType = navInfo[0];
      if (navInfo.length > 2) {  
        windowNavID = navInfo[1];
      }
    } else {
      windowNavType = GaConstant.NAVTYPE_TAB;
      windowNavID = "main";
      navInfoStr = GaConstant.NAVTYPE_TAB+",main";
    }       
    //�жϵ�ǰ�Ƿ�div�ύ
    if (windowNavType .equals(GaConstant.NAVTYPE_DIV) || windowNavType.equals(GaConstant.NAVTYPE_CUSTOM)) {
      //����div�ύ,���ȡǰһҳ��򿪷�ʽ,�����˷�ʽ����Ϊdivˢ�µ�ҳ��򿪷�ʽ
      preNavInfoStr = getContext().getRequest().getParameter(GaConstant.FIXPARAM_PREWINDOWNAV);
      if (GaUtil.isNullStr(preNavInfoStr)) {
        preNavInfoStr = GaConstant.NAVTYPE_TAB+",main";
      }            
    } else {
      //����������ʽ�ύ,�򽫵�ǰ������Ϣ����Ϊ��ҳ�浼����Ϣ
      preNavInfoStr = windowNavType+","+windowNavID;
    }
    String tmpV = getContext().getRequest().getParameter(GaConstant.FIXPARAM_ROWDATA);
    if (!GaUtil.isNullStr(tmpV)) {
      //��ʼҳ���row_data����
      if ("{row_data}".equals(tmpV)) {
        rowDataObj = null;
      } else {
        try {
          rowDataObj = new JSONObject(tmpV);
        } catch (JSONException e) {
          rowDataObj = null;
        }
      }
    }
    tmpV = getContext().getRequest().getParameter(GaConstant.FIXPARAM_EDITMODE);
    if (!GaUtil.isNullStr(tmpV)) {
      try {        
        this.pageEditMode = Integer.parseInt(tmpV);
      } catch(Exception e) {
        this.pageEditMode = GaConstant.EDITMODE_DISP;
      }
    }
    //��ǰˢ�µ�divid
    this.divID = getContext().getRequest().getParameter(GaConstant.FIXPARAM_DIVMODE);        
  }
  /**
   * �����󶨱��ؼ�ֵ
   * ���Զ���ҳ����������Ĭ�ϲ���
   * @param form
   */
  public void bindForm(Form form) {
    try {
      ClickUtils.bind(form);  
      HttpServletRequest request = getContext().getRequest();
      String tmpV = request.getParameter(GaConstant.FIXPARAM_PAGENO);
      if (!GaUtil.isNullStr(tmpV)) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGENO,tmpV);
        form.add(field);
      }
      tmpV = request.getParameter(GaConstant.FIXPARAM_PAGESIZE);
      if (!GaUtil.isNullStr(request.getParameter(GaConstant.FIXPARAM_PAGESIZE))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGESIZE,tmpV);
        form.add(field);
      }
      tmpV = request.getParameter(GaConstant.FIXPARAM_EDITMODE);
      if (!GaUtil.isNullStr(request.getParameter(GaConstant.FIXPARAM_EDITMODE))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_EDITMODE,tmpV);
        form.add(field);
      }         
      tmpV = request.getParameter(GaConstant.FIXPARAM_ROWDATA);
      if (!GaUtil.isNullStr(request.getParameter(GaConstant.FIXPARAM_ROWDATA))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_ROWDATA,tmpV);
        form.add(field);
      }
      tmpV = request.getParameter(GaConstant.FIXPARAM_LOOKUPMODE);
      if (!GaUtil.isNullStr(request.getParameter(GaConstant.FIXPARAM_LOOKUPMODE))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_LOOKUPMODE,tmpV);
        form.add(field);
      }
      tmpV = request.getParameter(GaConstant.FIXPARAM_DIVMODE);
      if (!GaUtil.isNullStr(request.getParameter(GaConstant.FIXPARAM_DIVMODE))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_DIVMODE,tmpV);
        form.add(field);
        //��ȡdivID
        this.divID = tmpV;
      }
      tmpV = request.getParameter(GaConstant.FIXPARAM_ACTIONID);
      if (!GaUtil.isNullStr(request.getParameter(GaConstant.FIXPARAM_ACTIONID))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_ACTIONID,tmpV);
        form.add(field);
        //��ȡ�ύactionid
        this.actionID = tmpV;
      }
      tmpV = request.getParameter(GaConstant.FIXPARAM_ROWSELECT);
      if (!GaUtil.isNullStr(request.getParameter(GaConstant.FIXPARAM_ROWSELECT))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_ROWSELECT,tmpV);
        form.add(field);
      }      
      //�̶�д�뵼����Ϣ
      HiddenField field  = new HiddenField(GaConstant.FIXPARAM_WINDOWNAV,this.navInfoStr);
      form.add(field);
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM, "������ʧ��", ex);
    }    
  }
  
  
  /**
   * ��map�ṹ�е�ֵ����dbField������䵽���ؼ���
   * @param form
   * @param queryResultMap
   */
  public  void putFormValue(Form form,Map<String,Object> queryResultMap,List<DbField> dbFieldList) {
   try {
        //�������ֶε�ֵתΪstring
     if (queryResultMap == null) queryResultMap = new HashMap<String,Object>();
      for(DbField field : dbFieldList) {
        if (field.getInputType() == GaConstant.INPUT_HIDDEN) {
          //���ȴ������������������Ϊstring
          Object value = queryResultMap.get(field.getFieldCode());
          if (value != null) {
            queryResultMap.put(field.getFieldCode(), value.toString());
          } else {
            //����Ĭ��ֵ
            if (queryResultMap.get(field.getFieldCode()) == null) {
              queryResultMap.put(field.getFieldCode(), field.getDefaultValue().toString());
            }
          }
        }
        else if (queryResultMap.get(field.getFieldCode()) == null) {
          //�����ѡĬ��ֵ
          queryResultMap.put(field.getFieldCode(), field.getDefaultValue());
        }
      }
      //���⴦��pop���Ϳؼ�(���Ʋ�һ���޷�pop)      
      ClickUtils.copyObjectToForm(queryResultMap, form,false);
      //�ж��Ƿ��ж�ѡ�ؼ�      
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"����ת��ʧ��",ex);
    }    
  }
  
  /**
   * ��form�ؼ��ж�ȡ����dbField�����ֵMAP
   * @param form
   * @return
   */
  public Map<String,Object> getFormValueMap(Form form,List<DbField> dbFieldList) {
    try {
      Map<String,Object> returnValueMap = new  HashMap<String,Object>();
      if (dbFieldList != null && form != null) {               
        //������ϸ���ݲ���
        for(DbField field : dbFieldList) {
          if (!GaUtil.isNullStr(field.getSessionName())) {
            //����sessionbindֵ
            if (getContext().getRequest().getSession(true) != null) {
              returnValueMap.put(field.getFieldCode(),
                  getContext().getRequest().getSession(true).getAttribute(field.getSessionName()));
            } 
//          } else if (!GaUtil.isNullStr(field.getRequestName())){
//            String paramName = field.getRequestName();
//            if (getContext().getRequest().getParameter(paramName) != null) {
//              String paramValue = getContext().getRequest().getParameter(paramName);              
//              returnValueMap.put(field.getFieldCode(),GaUtil.ConvertData(paramValue,
//                  field.getDataType(), 
//                  field.getFormatPattern()));
//            }
          } else if (field.getInputType() > 0) {
            Field inputField =  form.getField(field.getFieldCode());
            Object valueObject = null;
            if (inputField != null) {
              switch (field.getInputType())
              {
              case GaConstant.INPUT_TREE:
                  valueObject = ((TreeField)inputField).getValues();
              break;
              case GaConstant.INPUT_SELECTLIST:
                Select selectField = (Select)inputField;
                if (selectField.isMultiple()) {
                  //�б�
                  List getV = selectField.getSelectedValues();
                  valueObject = getV;
                  List<Object> setV = null;
                  if (getV != null) {
                    setV = new ArrayList<Object>();
                    for (int i=0;i<getV.size();i++) {
                      setV.add(GaUtil.convertData((String)getV.get(i),
                          field.getDataType(), 
                          field.getFormatPattern()));
                    }
                  }
                  valueObject = setV;
                } else {
                  valueObject = GaUtil.convertData(inputField.getValue(),
                      field.getDataType(), 
                      field.getFormatPattern());
                }
                break;
              default:
                 valueObject = GaUtil.convertData(inputField.getValue(),
                    field.getDataType(), 
                    field.getFormatPattern());
              }
            }
            returnValueMap.put(field.getFieldCode(),valueObject);
          }
        }
      }
      return returnValueMap;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"���������쳣");
    }
  }
  
  
  /**
   * ��form�ؼ��ж�ȡ����dbField�����ֵMAP
   * @param form
   * @return
   */
  private Object getFieldValueObject(DbField field,String fieldValue) {
    try {
      if (!GaUtil.isNullStr(field.getSessionName())) {
        //����sessionbindֵ
        if (getContext().getRequest().getSession(true) != null) {
          return  getContext().getRequest().getSession(true).getAttribute(field.getSessionName());
        } 
      } 
//      else if (!GaUtil.isNullStr(field.getRequestName())){
//        String paramName = field.getRequestName();
//        if (getContext().getRequest().getParameter(paramName) != null) {
//          String paramValue = getContext().getRequest().getParameter(paramName);              
//          return GaUtil.ConvertData(paramValue,field.getDataType(),field.getFormatPattern());
//        }
//      }
      if (fieldValue != null) {
        return GaUtil.convertData(fieldValue,
            field.getDataType(), 
            field.getFormatPattern());
      } else {
        return null;
      }
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"���������쳣");
    }
  }
  
  private DbField getDbField(String fieldCode,List<DbField> dbFieldList) {
    for(DbField field : dbFieldList) {
      if (field.getFieldCode().equals(fieldCode)) {
        return field;
      }
    }
    return null;
  }
  
  /**
   * ����dbField���ж����ȡ�ύֵ
   * Map�ṹΪ:string-�ֶ���,Object-�ֶ�ֵ
   * @param request
   * @param dbFieldList
   * @return
   */
  public  List<Map<String,Object>> getFormValueMapList(Form form,List<DbField> dbFieldList) {
    try {
      //�����ύ������
      Map<String,String[]> valueMap = getContext().getRequest().getParameterMap();
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
            field = getDbField(fieldName,dbFieldList);
          } else {
            fieldName = popFieldName;
            field =  getDbField(fieldName,dbFieldList);
          }
          if (field == null) {
            continue;
          }
          Object valueObject = null;        
          if (value != null && value.length >0) {
            String getV = value[0];
            if (field.getDataType() == GaConstant.DT_MONEY) {
              valueObject = GaUtil.getInputMoney(getV);
            } else {
              valueObject = getFieldValueObject(field,getV);
            }
          } else {
            valueObject = getFieldValueObject(field,null);
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
   * ���ݲ�ѯ����ȡ��ѯ���ݶ���
   * @param queryForm
   * @return
   */
  public QueryPageData getQueryData(Form queryForm,List<DbField> dbFieldList) {
    QueryPageData pageData = new QueryPageData();
    pageData.putData(dbFieldList, queryForm);
    return pageData;
  }
  
  /**
   * ��ʼ���ؼ�
   */
  public abstract void initControl();
  
  /**
   * ��ʼ������
   */
  public abstract void  initForm();
  
  /**
   * ��ʼ����ť
   */
  public abstract void initAction();
  
  /**
   * ��ʼ��layout
   */
  public abstract Layout initLayout();
  
}
