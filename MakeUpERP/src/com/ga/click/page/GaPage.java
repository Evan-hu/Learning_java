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
  
  //导航方式控制
  protected JSONObject rowDataObj = null;
  protected String actionID = "";
  protected String divID = "";
  protected String windowNavType = GaConstant.NAVTYPE_TAB; //窗口导航方式
  protected String windowNavID = "main";  //当前窗口导航目标ID
  protected String navInfoStr = "";
  protected String preNavInfoStr = "";
  protected int pageEditMode = GaConstant.EDITMODE_DISP;
  
  public Layout getLayout() {
    return layout;
  }
  
  /**
   * 完成初始化动作
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
      throw new BizException(BizException.SYSTEM,"执行初始化失败",e);
    }    
  }
  
  protected final void initNavInfo() {      
    //当前页面打开方式(以,分隔的字符)只解析不设置回写到页面
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
    //判断当前是否div提交
    if (windowNavType .equals(GaConstant.NAVTYPE_DIV) || windowNavType.equals(GaConstant.NAVTYPE_CUSTOM)) {
      //如是div提交,则获取前一页面打开方式,并将此方式设置为div刷新的页面打开方式
      preNavInfoStr = getContext().getRequest().getParameter(GaConstant.FIXPARAM_PREWINDOWNAV);
      if (GaUtil.isNullStr(preNavInfoStr)) {
        preNavInfoStr = GaConstant.NAVTYPE_TAB+",main";
      }            
    } else {
      //如是其他方式提交,则将当前导航信息设置为本页面导航信息
      preNavInfoStr = windowNavType+","+windowNavID;
    }
    String tmpV = getContext().getRequest().getParameter(GaConstant.FIXPARAM_ROWDATA);
    if (!GaUtil.isNullStr(tmpV)) {
      //初始页面的row_data处理
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
    //当前刷新的divid
    this.divID = getContext().getRequest().getParameter(GaConstant.FIXPARAM_DIVMODE);        
  }
  /**
   * 主动绑定表单控件值
   * 并自动在页面对象中添加默认参数
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
        //获取divID
        this.divID = tmpV;
      }
      tmpV = request.getParameter(GaConstant.FIXPARAM_ACTIONID);
      if (!GaUtil.isNullStr(request.getParameter(GaConstant.FIXPARAM_ACTIONID))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_ACTIONID,tmpV);
        form.add(field);
        //获取提交actionid
        this.actionID = tmpV;
      }
      tmpV = request.getParameter(GaConstant.FIXPARAM_ROWSELECT);
      if (!GaUtil.isNullStr(request.getParameter(GaConstant.FIXPARAM_ROWSELECT))) {
        HiddenField field  = new HiddenField(GaConstant.FIXPARAM_ROWSELECT,tmpV);
        form.add(field);
      }      
      //固定写入导航信息
      HiddenField field  = new HiddenField(GaConstant.FIXPARAM_WINDOWNAV,this.navInfoStr);
      form.add(field);
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM, "绑定数据失败", ex);
    }    
  }
  
  
  /**
   * 将map结构中的值根据dbField设置填充到表单控件中
   * @param form
   * @param queryResultMap
   */
  public  void putFormValue(Form form,Map<String,Object> queryResultMap,List<DbField> dbFieldList) {
   try {
        //将隐藏字段的值转为string
     if (queryResultMap == null) queryResultMap = new HashMap<String,Object>();
      for(DbField field : dbFieldList) {
        if (field.getInputType() == GaConstant.INPUT_HIDDEN) {
          //优先处理隐藏域，隐藏域必须为string
          Object value = queryResultMap.get(field.getFieldCode());
          if (value != null) {
            queryResultMap.put(field.getFieldCode(), value.toString());
          } else {
            //隐藏默认值
            if (queryResultMap.get(field.getFieldCode()) == null) {
              queryResultMap.put(field.getFieldCode(), field.getDefaultValue().toString());
            }
          }
        }
        else if (queryResultMap.get(field.getFieldCode()) == null) {
          //处理多选默认值
          queryResultMap.put(field.getFieldCode(), field.getDefaultValue());
        }
      }
      //特殊处理pop类型控件(名称不一致无法pop)      
      ClickUtils.copyObjectToForm(queryResultMap, form,false);
      //判断是否有多选控件      
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"数据转换失败",ex);
    }    
  }
  
  /**
   * 从form控件中读取符合dbField定义的值MAP
   * @param form
   * @return
   */
  public Map<String,Object> getFormValueMap(Form form,List<DbField> dbFieldList) {
    try {
      Map<String,Object> returnValueMap = new  HashMap<String,Object>();
      if (dbFieldList != null && form != null) {               
        //解析明细数据参数
        for(DbField field : dbFieldList) {
          if (!GaUtil.isNullStr(field.getSessionName())) {
            //处理sessionbind值
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
                  //列表
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
      throw new BizException(BizException.SYSTEM,"解析参数异常");
    }
  }
  
  
  /**
   * 从form控件中读取符合dbField定义的值MAP
   * @param form
   * @return
   */
  private Object getFieldValueObject(DbField field,String fieldValue) {
    try {
      if (!GaUtil.isNullStr(field.getSessionName())) {
        //处理sessionbind值
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
      throw new BizException(BizException.SYSTEM,"解析参数异常");
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
   * 根据dbField的列定义获取提交值
   * Map结构为:string-字段名,Object-字段值
   * @param request
   * @param dbFieldList
   * @return
   */
  public  List<Map<String,Object>> getFormValueMapList(Form form,List<DbField> dbFieldList) {
    try {
      //解析提交参数名
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
          int start  = name.lastIndexOf("."); //最后一个点
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
          //先判断是否绑定字段
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
          //将值放入字段
          Map<String,Object> fieldValue = getValue.get(posIndex);
          if (fieldValue == null) {
            fieldValue = new HashMap<String,Object>();
            getValue.put(posIndex, fieldValue);
          }
          fieldValue.put(fieldName, valueObject);
        }
      }
      //执行排序,转为List
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
   * 根据查询表单获取查询数据对象
   * @param queryForm
   * @return
   */
  public QueryPageData getQueryData(Form queryForm,List<DbField> dbFieldList) {
    QueryPageData pageData = new QueryPageData();
    pageData.putData(dbFieldList, queryForm);
    return pageData;
  }
  
  /**
   * 初始化控件
   */
  public abstract void initControl();
  
  /**
   * 初始化数据
   */
  public abstract void  initForm();
  
  /**
   * 初始化按钮
   */
  public abstract void initAction();
  
  /**
   * 初始化layout
   */
  public abstract Layout initLayout();
  
}
