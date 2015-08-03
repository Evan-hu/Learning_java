package com.ga.click.util;

import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Field;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Label;
import org.apache.click.control.Option;
import org.apache.click.control.PasswordField;
import org.apache.click.control.Select;
import org.apache.click.control.TextArea;
import org.apache.click.util.ClickUtils;
import org.json.JSONObject;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.extra.CheckboxList;
import com.ga.click.control.extra.FileUploadField;
import com.ga.click.control.extra.GaOption;
import com.ga.click.control.extra.GaText;
import com.ga.click.control.extra.InputMoney;
import com.ga.click.control.extra.MultiSelect;
import com.ga.click.control.extra.PKHiddenField;
import com.ga.click.control.extra.PopSelect;
import com.ga.click.control.extra.RefCombox;
import com.ga.click.control.form.DataForm;
import com.ga.click.control.table.QueryTable;
import com.ga.click.control.tree.TreeControl;
import com.ga.click.control.tree.TreeField;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.exception.BizException;
import com.ga.click.layout.FormLayout;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ListLayout;

public class ClickUtil {
  

  public static List<Field> createQueryControl(DbField dbField, String formName) {
    List<Field> rtnList = null;
    if (dbField != null && dbField.getQueryOpera() > 0) {
      rtnList = new ArrayList<Field>();
      if (dbField.getQueryInputType() == GaConstant.INPUT_LABLE) {
        Label label = new Label(dbField.getFieldCode());
        rtnList.add(label);
      } else {
        if (dbField.getQueryOpera() == GaConstant.QUERY_BETWEEN) {
          //范围查询
          DbField tmpField = new DbField(dbField.getQueryTitle(),dbField.getFieldCode()+"_min_",dbField.getDataType());
          tmpField.setLookupData(dbField.getLookupData());
          tmpField.setFormat(dbField.getFormat());
          tmpField.setInput(dbField.getQueryInputType());
          tmpField.setLookupData(dbField.getLookupData());
          tmpField.setLookupMarkData(dbField.getLookupMarkData());
          tmpField.setInputCustomStyle(dbField.getInputCustomStyle());
          Field f = createControl(tmpField);  
          rtnList.add(f);
          Label lblSpace = new Label("lblSpace","-");
          rtnList.add(lblSpace);    
          tmpField = new DbField("",dbField.getFieldCode()+"_max_",dbField.getQueryInputType());
          tmpField.setFormat(dbField.getFormat());
          tmpField.setInput(dbField.getQueryInputType());
          tmpField.setLookupData(dbField.getLookupData());
          tmpField.setLookupMarkData(dbField.getLookupMarkData());
          tmpField.setInputCustomStyle(dbField.getInputCustomStyle());
          f = createControl(tmpField);  
          rtnList.add(f);
        }  else if (dbField.getQueryOpera() == GaConstant.QUERY_CUSTOM) {
          //自定义查询
        } else {
          //普通查询   
          DbField tmpField = new DbField(dbField.getQueryTitle(),dbField.getFieldCode(),dbField.getQueryInputType());
          tmpField.setLookupData(dbField.getLookupData());
          tmpField.setLookupMarkData(dbField.getLookupMarkData());
          tmpField.setFormat(dbField.getFormat());
          tmpField.setInput(dbField.getQueryInputType());
          if (!GaUtil.isNullStr(dbField.getPopID())) { 
            if (!GaUtil.isNullStr(dbField.getPopPageUrl())) {
              tmpField.setPopSelect(dbField.getPopID(),dbField.getPopBindField(),dbField.isPopDisplay(),
                  dbField.getPopPageUrl(),dbField.getPopSelectFields(),
                  dbField.getPopWidth(),dbField.getPopHeight());
           } else {
             tmpField.setPopSelect(dbField.getPopID(),dbField.getPopBindField(),dbField.isPopDisplay());
           }
         }
          if (!GaUtil.isNullStr(dbField.getSuggestID())) { 
              tmpField.setSuggest(dbField.getSuggestID(),dbField.getSuggestBindField(),dbField.getSuggestUrl(),dbField.getSuggestSelectFields());         
         }
          tmpField.setInputCustomStyle(dbField.getInputCustomStyle());
         
          Field f = createControl(tmpField);
         
          f.setAttribute("onkeydown", "clickInputOnEnter(event, this, '" + formName + "')");
          rtnList.add(f);
        }
      }
    }
    return rtnList;
  }
  
  public static Field createControl(DbField dbField) {
    Field f = null;
    switch (dbField.getInputType()) {
    case GaConstant.INPUT_MONEY:
        f = new InputMoney(dbField.getFieldCode(), dbField.getFieldName(), dbField.isRequire());
        ((InputMoney)f).setDbField(dbField);
        break;
    case GaConstant.INPUT_TEXT :           
    case GaConstant.INPUT_NUMTEXT:   
       GaText txt = new GaText(dbField.getFieldCode(), dbField.getFieldName(), dbField.isRequire());      
       txt.setDbField(dbField);
       f = txt;
       break;
    case GaConstant.INPUT_PASSWORD:
      f =  new PasswordField(dbField.getFieldCode(), dbField.getFieldName(), dbField.isRequire());    
      break;
    case GaConstant.INPUT_CHECKLIST: 
      CheckboxList check = new CheckboxList(dbField.getFieldCode(), dbField.getFieldName(), dbField.isRequire());
      if (dbField.getLookupData()!= null) {      
        
        for(String key: dbField.getLookupData().getKeyList()) {
          if (dbField.getLookupMarkData() != null && dbField.getLookupMarkData().contains(key)) {
            GaOption option = new GaOption(key, dbField.getLookupData().find(key));
            option.setMark(true);
            check.add(option);
          } else {
            check.add(new GaOption(key, dbField.getLookupData().find(key)));
          }  
        }   
      }
      f = check;
      if (dbField.getDefaultValue() != null) {
        f.setValueObject(dbField.getDefaultValue());
      }
      break;
    case GaConstant.INPUT_SELECT: 
      Select select = new Select(dbField.getFieldCode(), dbField.getFieldName(), dbField.isRequire());
      //增加option选项
      //select.setAttribute("class","combox");
      if(!select.isRequired()){
        select.add(new Option("",""));
      }
      if (dbField.getLookupData()  != null) {      
        for(String key: dbField.getLookupData().getKeyList()) {  
          if (dbField.getLookupMarkData() != null && dbField.getLookupMarkData().contains(key)) {
            GaOption option = new GaOption(key, dbField.getLookupData().find(key));
            option.setMark(true);
            select.add(option);
          } else {
            select.add(new GaOption(key, dbField.getLookupData().find(key)));
          }  
        }        
      }          
      f = select;
      if (dbField.getDefaultValue() != null) {
        f.setValueObject(dbField.getDefaultValue());
      }
      break;
    case GaConstant.INPUT_DATETIME: 
      GaText dtText = new GaText(dbField.getFieldCode(), dbField.getFieldName(), dbField.isRequire());
      dtText.setDbField(dbField);
      dtText.setAttribute("hide", "false");
      dtText.addStyleClass("date readonly");
      dtText.setAttribute("readonly", "true"); 
      if (dbField.getFormat() == null) {
        dtText.setAttribute("format","yyyy-MM-dd");
      } else {
        dtText.setAttribute("format",dbField.getFormat().getFormatPattern());  
      }      
      //dtText.setAttribute("readonly", "readonly");
      f = dtText;
      break;
    case GaConstant.INPUT_TEXTTREE: //树状文本
      GaText treeTxt = new GaText(dbField.getFieldCode(), dbField.getFieldName(), dbField.isRequire());
      treeTxt.setDbField(dbField);
      f = treeTxt;
      break;
    case GaConstant.INPUT_TEXTAREA://文本区域
      TextArea textArea = new TextArea(dbField.getFieldCode(), dbField.getFieldName(), dbField.isRequire());
      if(dbField.getInputAttributeMap()!=null){
          if(GaUtil.isNumber(dbField.getInputAttributeMap().get("cols"))){
              textArea.setCols(Integer.parseInt(dbField.getInputAttributeMap().get("cols")));
              dbField.getInputAttributeMap().remove("cols");
          }
          if(GaUtil.isNumber(dbField.getInputAttributeMap().get("rows"))){
              textArea.setRows(Integer.parseInt(dbField.getInputAttributeMap().get("rows")));
              dbField.getInputAttributeMap().remove("rows");
          }
      }
      f = textArea;
      break;
    
    case GaConstant.INPUT_REQUEST:
    case GaConstant.INPUT_SESSION:
    case GaConstant.INPUT_ROWOBJ:
    case GaConstant.INPUT_HIDDEN://隐藏文本
    	HiddenField hidden;
        if (dbField.isPK()) {
        	hidden = new PKHiddenField(dbField.getFieldCode(),"");
        } else {
        	hidden = new HiddenField(dbField.getFieldCode(),"");
        }
      f = hidden;
      if (dbField.getDefaultValue() != null) {
        f.setValueObject(dbField.getDefaultValue().toString());
      }
      break;
    case GaConstant.INPUT_REFCOMBOX://关联下拉
      RefCombox refCombox = new RefCombox(dbField.getFieldCode(),
          dbField.getFieldName(),dbField.getRefComboxName(),
          dbField.getRefDataUrl(),dbField.getRefDataMethod(),
          dbField.getLookupData());
      f = refCombox;
      break;
    case GaConstant.INPUT_POPSELECT: //选择弹出
      PopSelect popSelect = null;
      String widthStr = dbField.getInputCustomStyle();
      int width = 0;
      if (!GaUtil.isNullStr(widthStr)) {
        int pos = widthStr.indexOf("width");
        int pos2 = widthStr.indexOf("px", pos);
        if (pos > -1 && pos2 > -1) {
          widthStr =widthStr.substring(pos +6,pos2);
          width = Integer.parseInt(widthStr);
        }
      }
      if (!GaUtil.isNullStr(dbField.getPopPageUrl())) {
        //主选择控件
        popSelect = new PopSelect(dbField.getFieldName(),dbField.getFieldCode(),dbField.getPopID(),dbField.getPopBindField(),
            dbField.isPopDisplay(),dbField.getPopPageUrl(),
            dbField.getPopSelectFields(),dbField.getPopConfirm(),dbField.getPopWidth(),
            dbField.getPopHeight());
        if (width > 0) {
          popSelect.setAllInputWidth(width);  
        }        
      } else {        
        //显示选择结果控件
        popSelect = new PopSelect(dbField.getFieldName(),dbField.getFieldCode(),dbField.getPopID(),dbField.getPopBindField(),dbField.isPopDisplay());
        if (width > 0) {
          popSelect.setAllInputWidth(width);  
        }
      } 
      popSelect.setInputJS(dbField.getInputJSName());
      popSelect.setDbField(dbField);
      popSelect.setPopEdit(dbField.isPopEdit());
      f = popSelect;
      break;
    case GaConstant.INPUT_SELECTLIST: //列表框
      MultiSelect selectList = new MultiSelect(dbField.getFieldCode(), dbField.getFieldName(), dbField.isRequire());
      selectList.setSize(dbField.getSelectListSize());
      selectList.setMultiple(dbField.isMultiSelect());
      //增加option选项
      if (dbField.getLookupData()  != null) {      
        for(String key: dbField.getLookupData().getKeyList()) {    
          selectList.add(new Option(key, dbField.getLookupData().find(key)));
        }
      }
      //处理默认值
      if (dbField.getDefaultValue() != null) {
        if (dbField.isMultiSelect()) {
          if (dbField.getDefaultValue() instanceof List) {
            List<Object> defV = (List<Object>) dbField.getDefaultValue() ;
            List<String> setV = new ArrayList<String>();
            for (Object v : defV) {
              setV.add(v.toString());              
            }
            selectList.setSelectedValues(setV);
          }          
        } else {
          selectList.setValueObject(dbField.getDefaultValue());
        }
      }
      f = selectList;
      break;
    case GaConstant.INPUT_UPLOAD: //文件上传控件
      FileUploadField file = new FileUploadField(dbField.getFieldCode(),dbField.getFieldName());
      file.setSelectExt(dbField.getFileSelectExt());
      file.setPreview(dbField.isFilePreview(),dbField.getFileWidth(),dbField.getFileHeight());
      f = file;
      break;
    case GaConstant.INPUT_TREE: //树控件
        TreeField treeField = new TreeField(dbField.getFieldCode(),dbField.getFieldName());
        treeField.setTreeControl(dbField.getTreeControl());
        f = treeField;
        break;
    }
    if (f != null) {   
      //设置自动提示
      if (dbField.getSuggestID() != null) {
        f.setAttribute("lookupGroup",dbField.getSuggestID());
        if (!GaUtil.isNullStr(dbField.getSuggestUrl())) {
          f.setAttribute("suggestUrl",dbField.getSuggestUrl());
          f.setAttribute("suggestFields",dbField.getSuggestSelectFields());
        }     
        f.setAttribute("dispFields", dbField.getSuggestDispFields());
        f.setAttribute("suggestName",dbField.getSuggestID() + "." + dbField.getSuggestBindField());
      }
      //设置默认值
      if(!(f instanceof Label)){
          if (dbField.getDefaultValue() != null && !(f instanceof Select)) {
            f.setValue(dbField.getDefaultValue().toString());
          }
         // f.addStyleClass(dbField.getValidationEngine());
          //设置定制样式
          if (!GaUtil.isNullStr(dbField.getInputCustomStyle())) {
            String styleString = dbField.getInputCustomStyle();
            if (styleString.startsWith(";")) styleString = styleString.substring(1);
            String[] custom = styleString.split(";");
            for (String styleStr : custom) {
              if (styleStr.indexOf(":") > 0) {
                f.setStyle(styleStr.split(":")[0], styleStr.split(":")[1]);  
              }              
            }
          }
          //设置扩展属性
          for(String attrName : dbField.getInputAttributeMap().keySet()) {
            f.setAttribute(attrName, dbField.getInputAttributeMap().get(attrName));
          }
      }
    }
    return f;
  }

  public static ActionLink createLookupSelectLink(String linkID,String linkTitle) {   
    ActionLink newLink = new ActionLink(linkID) { 
      //重载Label
      public String getLabel() {
        if (label == null) {
            label = getMessage(getName() + ".label");
        }
        if (label == null) {
            label = ClickUtils.toLabel(getName());
        }
        label = "<span>"+label+"</span>";
        return label;
      }
      //重载设置链接
      public String getHref(Object value) {
        return "javascript:doLookupSelect()";
      }
    };
    if (!GaUtil.isNullStr(linkTitle)) {
      newLink.setLabel(linkTitle);
    } else {
      newLink.setLabel(linkID);
    }
    newLink.setAttribute("external", "false");
    newLink.setAttribute("class","icon");
    newLink.setAttribute("actionLink","#");
    //处理参数
  //  newLink.setAttribute("onclick","return doLookupSelect();");
    //处理提交        
    return newLink;
  }
  
  /**
   * 
   * @param linkID
   * @param linkTitle
   * @param targetUrl
   * @param navTtype
   * @param navID
   * @param navTitle
   * @param isPost
   * @param bindForm
   * @param isBindRowData
   * @param paramMap
   */
  public static void createColumnLink(String linkID,String linkTitle,String targetUrl,
                                      String navTtype,String navID,String navTitle,
                                      boolean isPost,String bindForm,boolean isBindRowData,
                                      Map<String,String> paramMap) {
    
  }
  
 
  /**
   * 将异常转为返回前端框架的json字符串
   * {"statusCode":"200", "message":"操作成功", "navTabId":"navNewsLi", "forwardUrl":"", "callbackType":"closeCurrent"}
   * @param biz
   * @return
   */
  public static String creaReturnJSON(BizException biz) {
    JSONObject json = new JSONObject();
    try {
      if (biz != null) {
        if (biz.getCode() == BizException.AJAXEND) {
          json.put("statusCode", "200");
        } else {
          json.put("statusCode", "300");
        }               
        json.put("message", biz.getMessage());
        if (GaConstant.NAVTYPE_DIALOG.equals(biz.getNavType())) {          
          json.put("navTabId", "main");
        } 
        if (!GaUtil.isNullStr(biz.getCallBackType())) {
          json.put("callbackType", biz.getCallBackType());
        }
      }
    } catch(Exception e) {
      return "{\"statusCode\":\"300\", \"message\":\"处理返回异常\"}";
    }
    return json.toString();
  }
  
  public static ActionResult createErrorResult(String actionid,Exception biz) {
    JSONObject json = new JSONObject();
    try {
      if (biz != null) {            
        json.put("actionid", actionid);
        json.put("message", biz.getMessage());
        json.put("success","0");
      }
    } catch(Exception e) {
      json.optJSONObject("{\"success\":\"0\", \"message\":\"处理返回异常\"}");
    }
    return new ActionResult(json.toString(),ActionResult.JSON);
  }

  
  
 
  /**
   * 根据记录集创建TreeNode对象
   * @param rs 记录集
   * @param idFieldCode 树ID字段
   * @param pidFieldCode 树父ID字段
   * @param nodeName 节点名称
   * @param nodeLink 节点连接
   * @param bindField 节点连接绑定参数
   * @return
   */
  public static TreeNode getTreeNode(ResultSet rs,String idField,String pidField,
      String nodeNameField,String nodeLinkField,String bindField,Long rootNodeID) {
     try {
       if (rs == null) return null;
       //rs.first();
       Map<Long,List<TreeNode>> allPNode = new HashMap<Long,List<TreeNode>>();
       TreeNode rootNode = null;
       Map<Long,TreeNode> allNode = new HashMap<Long,TreeNode>();
       while(rs.next()) {
         Long pid = rs.getLong(pidField);
         Long id = rs.getLong(idField);
         String nodeName = rs.getString(nodeNameField);
         String nodeLink = "";
         if (!GaUtil.isNullStr(nodeLinkField)) {
           nodeLink = rs.getString(nodeLinkField);
         }
         Map<String,Object> paramMap = null;
         if (bindField != null) {
           paramMap = new HashMap<String,Object>();
           String[] bindFields = bindField.split(",");
           for(String bind : bindFields) {    
             paramMap.put(bind,rs.getObject(bind));
           }
         }
         TreeNode node = new TreeNode(id, nodeName, nodeLink);
         if (paramMap != null) {
           node.setNodeParam(paramMap);
         }
         if (id.equals(rootNodeID)) {
           rootNode = node;
         }  else {
           allNode.put(id,node);
           List<TreeNode> listNode = null;
           if (allPNode.get(pid) == null) {
             listNode = new ArrayList<TreeNode>();
             allPNode.put(pid,listNode);
           } else {
             listNode = allPNode.get(pid);
           }
           listNode.add(node);  
         }
       }
       //根据map构建treenode层次
       if (rootNode != null) {
         rootNode.setChildNodes(allPNode.get(rootNodeID));
         for (Long id :allNode.keySet()) {
           allNode.get(id).setChildNodes(allPNode.get(id));
         }
       }
       return rootNode;
     } catch(Exception ex) {
       ex.printStackTrace();
       throw new BizException(BizException.SYSTEM,"构建树型结构异常"+ex.getMessage(),ex);
     }
  }

  /**
   * 将记录集行转为一个JSON对象
   * @param rs
   * @param fieldList
   * @return
   * @throws Exception
   */
  public static JSONObject getRowJSONObject(ResultSet rs,List<String> fieldList) throws Exception {
    if (rs == null) return null;
    JSONObject obj = new JSONObject();
    for(String field: fieldList) {
      Object valueObj = rs.getObject(field);
      obj.put(field, JSONObject.valueToString(valueObj));
    }       
    return obj;
  }
  
  public static JSONObject getJSONObject(Map<String,Object> valueMap) throws Exception {
    if (valueMap == null) return null;
    JSONObject obj = new JSONObject();
    for(String key: valueMap.keySet()) {
      Object valueObj = valueMap.get(key);
      if (valueObj == null) {
        obj.put(key, "");
      } else {
        obj.put(key, valueObj.toString());
      }
    }       
    return obj;
  }
  
  public static void setControlLayoutH(Control control, int autoH) {
    if (control instanceof QueryTable) {
      QueryTable table = (QueryTable) control;
      Layout layout = table.getLayout();
      if (layout == null) {
        layout = new ListLayout(table);
        table.setLayout(layout);
      }
      layout.setLayoutH(autoH);
    } else if (control instanceof DataForm) {
      DataForm form = (DataForm) control;
      Layout layout = form.getLayout();
      if (layout == null) {
        layout = new FormLayout("", form, 1);
        form.setLayout(layout);
      }
      layout.setLayoutH(autoH);
    } else if (control instanceof TreeControl) {
      TreeControl tree = (TreeControl) control;
      tree.setLayoutH(autoH);
    }
  }
  
  /**
   *根据值和定义进行转义输出 
   *(不包括装饰处理的转换)
   * @param value
   * @return
   */
  public static String getDisplayValue(DbField field,Object columnValue) {
    String valueString = "";
    if (field.getDataType() == GaConstant.DT_MONEY) {
      //金额类型值处理
      if (columnValue != null && !columnValue.toString().equals("")) {        
        BigDecimal moneyV = new BigDecimal(columnValue.toString());
        columnValue = String.valueOf(moneyV.divide(new BigDecimal("100")).doubleValue());
      }
    } 
    if (field.getFormat() != null) {
      //进行格式化处理,执行装饰器解析
      valueString =  field.getFormat().getFormatString(columnValue);
    }  else if (field.getLookupData() != null) {
      //进行键值对转换
      if (columnValue != null) {
        Object value = field.getLookupData().find(columnValue.toString());
        if (value != null) {
          return value.toString();
        } else {
          return "";
        }
      } else {
        return "";
      }      
    } else if (columnValue != null) {
      valueString = columnValue.toString();
    }
    if (field.getMaxLength() > 0) {
      valueString = ClickUtils.limitLength(valueString, field.getMaxLength());
    }
    return valueString;
  }
  
  
  /**
   * 从记录集或map对象中获取字段值
   * @param name 字段名
   * @param row 返回值
   * @return
   */
  public static Object getRowData(String name, Object row) {
    if (row instanceof ResultSet) {
      ResultSet rs = (ResultSet)row;
      try {
        Object object = rs.getObject(name);  
        if (object instanceof Clob){
          return GaUtil.clobToString((Clob)object);
        } else {
          return object;  
        }
      }
      catch (Exception e) {
        return null;
      }
    }
    else if (row instanceof Map) {
      Map map = (Map) row;
      Object object = map.get(name);   
      if (object instanceof Clob){
        return GaUtil.clobToString((Clob)object);
      } else {
        return object;  
      }      
    } else {
      return null;
    }
  }
}

