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
 * 视图基类
 * 视图是对(List/Form/Tree)等容器控件的包装,将控件同数据绑定到一起
 * 定义视图公用的方法
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
  
  protected Map<String,Object> viewParam = new HashMap<String,Object>(); //所有参数字段值
  protected QueryPageData viewPageQuery = null;  //查询表单的数据,能自动组合表达式

  protected Map<String,String> funcMap = new HashMap<String,String>();
  protected ModelMap modelMap = null;
  
  protected Map<String,String[]> requestValueMap; 
  
  protected JSONObject rowDataObj = null;
  protected boolean exportMark = false;
  protected String exportFields = "";
  

  /**
   * 构建视图对象
   * @param viewID 视图ID
   * @param viewType 视图类型
   * @param viewEditMode 视图编辑模式
   */
  public View(String viewID,int viewType,ModelMap modelMap) {
    try {
      if (GaUtil.isNullStr(viewID)) {
        throw new BizException(BizException.SYSTEM,"未指定视图ID");
      }
      this.viewID = viewID;
      this.viewType = viewType;
      this.viewEditMode = modelMap.getPageEditMode();
      this.modelMap = modelMap;
      this.initRequestMap(this.modelMap);
      this.initField();
      
      /**************肖宇舟修改2014-07-9（增加自动给FORMVIEW的店铺选择进行处理，如果管理员是店铺管理员，并且只有一家店铺的管理权，则默认是自己的店铺，不需要选择）*******************************/
      if(this.viewType == GaConstant.VIEWTYPE_FORM){
        if(this.getFieldByCode("STORE_ID") != null ){
        	DbField storeField = this.getFieldByCode("STORE_ID");
        	DbField storeNameField = this.getFieldByCode("STORE_NAME");
        	if(!GaUtil.isNullStr(storeField.getPopID())){
        		LookupDataSet dataSet =  getUserACL().getStoreName();
        		if(dataSet != null && dataSet.getLookupDataMap() != null && dataSet.getLookupDataMap().size()  == 1){
        			storeNameField.clearPopSelect();//取消
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
      throw new BizException(BizException.SYSTEM,"创建视图对象异常");
    }
  }
  
  public View(String viewID,int viewType,ModelMap modelMap,int editMode) {
    try {
      this.viewEditMode = editMode;
      if (GaUtil.isNullStr(viewID)) {
        throw new BizException(BizException.SYSTEM,"未指定视图ID");
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
      throw new BizException(BizException.SYSTEM,"创建视图对象异常");
    }
  }
  /**
   * 初始化当前视图的参数值
   * (区分多表单和单表单模式)
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
    //初始化rowobj信息
    String rowJson = this.getRequestValue(GaConstant.FIXPARAM_ROWDATA);
    if (!GaUtil.isNullStr(rowJson)) {
      try {
        this.rowDataObj = new JSONObject(rowJson);
      } catch(Exception e) {
        
      }
    }
    //将多选信息放入rowdata中
    String ids = this.getRequestValue(GaConstant.FIXPARAM_MULTISELECT);
    if (!GaUtil.isNullStr(ids)) {
      try {
        if (this.rowDataObj == null) this.rowDataObj = new JSONObject("{}");
        this.rowDataObj.put(GaConstant.FIXPARAM_MULTISELECT, ids);
      } catch(Exception e) {       
      }
    }
    //解析导出标记
    String exportStr = this.getRequestValue(GaConstant.FIXPARAM_EXPORTMARK);
    if (!GaUtil.isNullStr(exportStr)) {
      this.exportMark = true;
      this.exportFields = exportStr;
    }
  }

  /**
   * 清楚默认定义的ActionButton
   */
  public void clearAction() {
    this.actionList.clear();
  }
  
  public List<ActionButton> getActionList() {
    return this.actionList;
  }
  /**
   * 绑定列表控件数据
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
      throw new BizException(BizException.SYSTEM,"绑定数据异常");
    }
  }

  /**
   * 绑定列表控件数据
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
      throw new BizException(BizException.SYSTEM,"绑定数据异常");
    }
  }
  
  /**
   * 绑定DataForm控件数据
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
      throw new BizException(BizException.SYSTEM,"绑定数据异常");
    }
  }
  
  /**
   * 绑定列表查询表单
   * @param queryData 查询数据
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
      throw new BizException(BizException.SYSTEM,"绑定数据异常");
    }
  }
  
  /**
   * 绑定树控件数据
   * @param treeData
   */
  public void bindData(TreeNode treeData,String url,boolean isExpand) {
    try {
      if (this.viewType != GaConstant.VIEWTYPE_TREE) {
        throw new BizException(BizException.SYSTEM,"视图类型不是树类型，无法执行此绑定");
      }
      TreeControl treeControl = new TreeControl(this.viewID,treeData,url,isExpand,this.getUserACL());
      if (this.actionList != null && this.actionList.size() > 0) {
        ActionButton action = this.actionList.get(0);
        if (action != null) {
          //处理导航信息
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
      throw new BizException(BizException.SYSTEM,"绑定数据异常");
    }
  }
  
  public void bindNull() {
    
  }
  
  public void parseRequest() {
    try {
      //解析参数Map
      for (DbField field : this.fieldList) {
        if (field.isQueryParam()) {
          //作为参数放入
          Object value = this.parseDbFieldQueryParam(field);
          this.viewParam.put(field.getFieldCode(), value);
        }
      }
      //解析分页查询对象
      if (this.viewType == GaConstant.VIEWTYPE_LIST  || this.viewType == GaConstant.VIEWTYPE_QUERY) {
        this.viewPageQuery = new QueryPageData();
        this.viewPageQuery.putData(fieldList,this);        
      } 
      //解析视图输入数据
      if (this.viewType == GaConstant.VIEWTYPE_LIST && this.viewEditMode != GaConstant.EDITMODE_DISP) {
        this.viewListData = this.parseListValue(this.fieldList);
      } else if (this.viewType == GaConstant.VIEWTYPE_FORM) {
        this.viewData = new HashMap<String,Object>();
        for (DbField field : this.fieldList) {
          if (field.getInputType() > 0) {
            //作为输入数据放入
            Object value = this.parseDbFieldInputData(field);
            this.viewData.put(field.getFieldCode(), value);
          }
        }
      }
      //判断当前视图是否查找带回模式
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
      throw new BizException(BizException.SYSTEM,"解析视图提交参数时异常");
    }
  }

  /**
   * 获取视图核心控件
   * @return
   */
  public Control getViewControl() {
    try {
      if (this.viewControl == null) {
        //重新构建
        this.beforeRender();
        this.viewControl = this.createControl();
      } 
      return this.viewControl;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"获取视图控件时异常");
    }
  }
  
  /**
   * 获取视图控件(查询表单)
   * @return
   */
  public ListForm getQueryForm() {
    try {
      if (this.viewType != GaConstant.VIEWTYPE_QUERY) {
        throw new BizException(BizException.SYSTEM,"视图类型不是查询类型，无法返回查询表单控件");
      }
      return (ListForm)this.getViewControl();    
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw  new BizException(BizException.SYSTEM,"返回查询表单控件时异常");
    }   
  }
  /**
   * 获取视图控件(明细表单)
   * @return
   */
  public DataForm getDataForm() {
    try {
      if (this.viewType != GaConstant.VIEWTYPE_FORM) {
        throw new BizException(BizException.SYSTEM,"视图类型不是数据表单类型，无法返回数据表单控件");
      }
      return (DataForm)this.getViewControl();    
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw  new BizException(BizException.SYSTEM,"返回数据表单控件时异常");
    }
  }
  /**
   * 获取视图控件(列表控件)
   * @return
   */
  public QueryTable getQueryTable() {
    try {
      if (this.viewType != GaConstant.VIEWTYPE_LIST) {
        throw new BizException(BizException.SYSTEM,"视图类型不是列表类型，无法返回列表控件");
      }
      return (QueryTable)this.getViewControl();    
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw  new BizException(BizException.SYSTEM,"返回列表控件时异常");
    }
  }
  
  /**
   * 获取视图控件(树控件)
   * @return
   */
  public TreeControl getTreeControl() {
    try {
      if (this.viewType != GaConstant.VIEWTYPE_TREE) {
        throw new BizException(BizException.SYSTEM,"视图类型不是树类型，无法返回树控件");
      }
      return (TreeControl)this.getViewControl();    
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      throw  new BizException(BizException.SYSTEM,"返回树型控件时异常");
    }
  }
  
  /**
   * 根据定义创建视图控件
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
      throw new BizException(BizException.SYSTEM,"创建树控件必须先执行BindData方法");
      default:
        throw new BizException(BizException.SYSTEM,"指定视图类型无效,无法创建视图控件");
    }    
  }
  
  /**
   * 对视图控件进行渲染
   * @param buff
   */
  public void render(HtmlStringBuffer buff) {
    try {
      Control control = this.getViewControl();
      //处理按钮
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
      throw  new BizException(BizException.SYSTEM,"渲染视图控件时异常");
    }
  }
  
  
  /**
   * 视图初始化方法,可重载此方法进行dbField初始化,以便视图重用
   * 注意:初始化字段时可根据不同模式进行控件初始化
   * 此方法可不进行异常捕获
   * @throws Exception
   */
  public void initField() throws Exception{    
  }
  
  /**
   * 初始化视图按钮
   * @throws Exception
   */
  public void initAction() throws Exception {    
  }
  
  
  /**
   * 根据code查找Field
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
   * 获取按钮对象
   * @param actionID 按钮ID(不包含按钮前的class)
   * @return 按钮对象
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
   * 获取指定字段的类型
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
   * 注册dbField字段
   * @param dbField
   */
  public void regField(DbField dbField) {
    this.fieldList.add(dbField);
  }
  
  /**
   * 注册视图相关按钮
   * @param action
   */
  public void regAction(ActionButton action) {
    this.actionList.add(action);
  }
  
  public String getViewID() {
    return viewID;
  }
  
  
  /**
   * 解析DBFIELD字段的输入值,解析规则:
   * 1.如果值设置了输入控件,则根据定义取值和转换
   * 2.如只设置了查询绑定参数(REQUEST/SESSION/ROWOBJ),则也会返回所绑定的值
   * 3.如同时设置了输入控件和查询绑定，则返回值以查询绑定为优先
   * @param field
   * @param modelMap
   * @return
   */
  protected Object parseDbFieldInputData(DbField field) {
    try {
      Object valueObject = null;
      //普通值,直接从request中读取
      String[] values = null;
      String value = null;
      //解析明细数据参数
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
               throw new BizException(BizException.COMMBIZ,"金额信息输入格式不正确");
            }
            break;
          case GaConstant.INPUT_SELECTLIST:
            if (field.isMultiSelect()) {
              //列表
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
      throw new BizException(BizException.SYSTEM,"解析DBField参数异常");
    }
  }
  
  
  /**
   * 解析字段的查询参数值,只要设置了getQueryInputType的都能获取值,如未输入值则为null
   * 对于需要两个值的queryopera,则返回List类型,第一个为下限，第二个为上限制
   * @param field 字段
   * @param modelMap 页面参数
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
            //如是比较操作,则是两个值
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
        //从session中读取
        valueObject = this.modelMap.getSession(true).getAttribute(field.getParamSoureName());          
      } else {
        if (field.getQueryInputType() == GaConstant.INPUT_REQUEST) {
          //从request中读取
          value = this.getRequestValue(field.getParamSoureName());
          if (GaUtil.isNullStr(value)) {
            value = this.getRowDataValue(field.getParamSoureName());
          }
          valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
        }  else if (field.getQueryInputType() == GaConstant.INPUT_ROWOBJ)  {
          //从rowobj中读取    
          value = this.getRowDataValue(field.getParamSoureName());
          if (GaUtil.isNullStr(value)) {
            value = this.getRequestValue(field.getParamSoureName());
          }
          valueObject = GaUtil.convertData(value,field.getDataType(),field.getFormatPattern());
       }  
     }      
      return valueObject;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"解析DBField参数异常");
    }
  }
  
  
  /**
   * 根据dbField的列定义获取提交值(只有表格提交会采用此函数解析)
   * Map结构为:string-字段名,Object-字段值
   * @param request
   * @param dbFieldList
   * @return
   */
  public  List<Map<String,Object>> parseListValue(List<DbField> dbFieldList) {
    try {
      //解析提交参数名
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
   * 将map结构中的值根据dbField设置填充到表单控件中
   * @param form 表单控件
   * @param dataMap 数据
   */
  protected  void putFormValue(Form form,Map<String,Object> dataMap,List<DbField> dbFieldList,boolean isQueryInput) {
   try {       
        //将隐藏字段的值转为string
     HashMap setMap  = new HashMap<String,Object>();
     if (dataMap != null) {
      for(DbField field : dbFieldList) {
        //特殊处理clob类型数据
        Object value = dataMap.get(field.getFieldCode());

        setMap.put(field.getFieldCode(), value);
        if (value != null && value instanceof Clob) {
          setMap.put(field.getFieldCode(),GaUtil.clobToString((Clob)value));
        } else if (value != null && value instanceof Timestamp) {
          value = ClickUtil.getDisplayValue(field, value);
          setMap.put(field.getFieldCode(), value);
        }
        
        if (isQueryInput) {
          //查询参数处理
          if (field.getQueryInputType() == GaConstant.INPUT_HIDDEN || 
              field.getQueryInputType() == GaConstant.INPUT_REQUEST ||
              field.getQueryInputType() == GaConstant.INPUT_ROWOBJ
              ) {
            //优先处理隐藏域，隐藏域必须为string
            if (value != null) {
              setMap.put(field.getFieldCode(), value.toString());
            } else {
              //隐藏默认值
              if (dataMap.get(field.getFieldCode()) == null) {
                setMap.put(field.getFieldCode(), field.getDefaultValue().toString());
              }
            }
          }
          else if (dataMap.get(field.getFieldCode()) == null) {
            //处理多选默认值
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
          //从值输入中取
          if (field.getInputType() == GaConstant.INPUT_HIDDEN || 
              field.getInputType() == GaConstant.INPUT_REQUEST ||
              field.getInputType() == GaConstant.INPUT_ROWOBJ
              ) {
            //优先处理隐藏域，隐藏域必须为string
            if (value != null) {
              setMap.put(field.getFieldCode(), value.toString());
            } else {
              //隐藏默认值
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
            //处理默认值
            setMap.put(field.getFieldCode(), field.getDefaultValue());
          } 
        }
      }
     }
      //特殊处理pop类型控件(名称不一致无法pop)      
      ClickUtils.copyObjectToForm(setMap, form,false);
      //判断是否有多选控件      
    } catch(BizException e) {
      throw e;
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"数据转换失败",ex);
    }    
  }


  public List<DbField> getFieldList() {
    return fieldList;
  }
  
  /**
   * 设置字段列表
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
   * 获取request中的值
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
   * 获取request的值
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
   * 渲染之前的预置事件，可通过重载更改输入控件，只读状态等
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
