package com.ga.click.control;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import com.ga.click.control.tree.TreeControl;
import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;

/* 0: NUMBER
 1: String
 2: Date
 */

public class DbField {

    // 基本数据结构定义
    private String tableCode = "";
    private String fieldCode = "";
    private String aliasCode = "";
    private String fieldName = "";
    private int dataType = 0;
    private int dataLength = 0;
    private Object defaultValue = "";
    private int columnWidth = 0;
    private LookupDataSet lookupData;
    //下来框重点标红设置
    private Set<String> lookupMarkData;
    private boolean bindRowData = false;
    private boolean display = true;
    private String paramSoureName = "";
    private String inputSourceName = "";
    private boolean isPK = false;
    private String tipInfo; //提示信息
    private FieldVerify fieldVerify = new FieldVerify(); //校验信息

    // 查询参数定义
    private int queryOpera = 0; // 查询比较操作
    private int queryInputType = GaConstant.INPUT_TEXT; // 查询输入类型
    private int queryOrder = 0; // 查询序号
    private boolean autoWire = true; //是否自动组装查询条件
//    private boolean advQuery = false; // 是否高级查询条件
    private String queryTitle = ""; //查询条件名称
    
    //列显示定义
    private String columnMergeCode = ""; //合并的列信息
    private StringDecorator columnDecorator = null; //列装饰器

    // 格式化信息
    private DbFieldFormat format = null;

    // 编辑控件定义
    private int inputType = 0; // 输入方式
    private Map<Integer,Integer> inputMode = new  HashMap<Integer,Integer>();    
    private boolean isRequire = false;//指定模式下是否只读
    private String inputCustomStyle = "";
    private Map<String,String> inputAttributeMap = new HashMap<String,String>();
    private String inputJSName = "";

    private int maxLength = 0;// 最大长度
    private int minLength = 0;// 最短长度    
    
    private String verifyFormula = null;// 验证方式
    private String confirmFieldCode = null;//需要与指定控件值一致
    
    ///弹出选择相关参数
    private String popID;
    private String popSelectFields;
    private String popPageUrl;
    private int popWidth = 400;
    private int popHeight = 300;
    private String popBindField;
    private boolean popDisplay = true;
    private String popConfirm = "";
    private boolean popEdit = false;
    private int popSubInput = -1;
    
    private String suggestID;
    private String suggestBindField;
    private String suggestSelectFields;
    private String suggestDispFields;
    private String suggestUrl;
    //关联下拉框
    private String refDataUrl;
    private String refDataMethod;
    private String refComboxName;

    //设置列表控件
    private int selectListSize = 1;
    private boolean multiSelect = false;
    //文件上传空间
    private boolean filePreview = false;
    private int fileWidth = 0;
    private int fileHeight = 0;
    private String fileSelectExt = "";
    private TreeControl treeControl = null;
    
    //复选框
    private Map<String,String> checkBoxsData = null;
    //设置复选框的选中值以","号隔开
    private String selectedCheckBoxsValue = "";
    
    private Boolean isFilterCode = false; //过滤字段  SHOP_ID
    
    
    public TreeControl getTreeControl() {
        return treeControl;
    }

    public void setTreeControl(TreeControl treeControl) {
        this.treeControl = treeControl;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public DbField(String fieldName, String fieldCode, int dataType) {
        this.fieldName = fieldName;
        this.fieldCode = fieldCode;
        this.dataType = dataType;
//        this.maxLength = 10;
        this.initDefaultInput();
    }
    
    public DbField(String fieldName, String fieldCode, int dataType,boolean isAutoWire) {
      this.fieldName = fieldName;
      this.fieldCode = fieldCode;
      this.dataType = dataType;
      this.autoWire = isAutoWire;
      this.initDefaultInput();      
  }

    public DbField(String fieldName, String fieldCode, int dataType, int dataLength) {
        this.fieldName = fieldName;
        this.fieldCode = fieldCode;
        this.dataType = dataType;
        this.dataLength = dataLength;
        this.initDefaultInput();
    }
    

    
    /**
     * @deprecated 使用setQueryParam函数代替
     * @param sessionName session参数名
     */
    public void BindSession(String sessionName) {
      this.paramSoureName = sessionName;
    }
    
    /**
     * @deprecated 使用getQuerySourceParam函数代替
     * @return 返回session变量的名称
     */    
    public String getSessionName() {
      return this.paramSoureName;
    }
    
    

    private void initDefaultInput() {
        switch (this.dataType) {
        case GaConstant.DT_MONEY:
          this.inputType = GaConstant.INPUT_MONEY;
          break;
        case GaConstant.DT_DATETIME:
            this.inputType = GaConstant.INPUT_DATETIME;
            break;
        case GaConstant.DT_DOUBLE:
        case GaConstant.DT_INT:
        case GaConstant.DT_LONG:
            this.inputType = GaConstant.INPUT_NUMTEXT;
            break;
        case GaConstant.DT_PASSWORD:
            this.inputType = GaConstant.INPUT_PASSWORD;
            break;
        case GaConstant.DT_STRING:
            this.inputType = GaConstant.INPUT_TEXT;
            break;
        }    
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldCode() {
      return getFieldCode(true);
    }
    
    public String getFieldCode(boolean includeAlias) {
      if (includeAlias) {
        if (GaUtil.isNullStr(this.aliasCode)) {
          return fieldCode;
        } else {
          if (this.aliasCode.indexOf(".")>0) {
            return this.aliasCode;
          } else {
            return this.aliasCode+"."+fieldCode;  
          }
           
        }        
      } else {
        return fieldCode;
      }
    }

    public int getDataType() {
        return dataType;
    }

    public int getDataLength() {
        return dataLength;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public LookupDataSet getLookupData() {
        return lookupData;
    }    

    public void setLookupData(LookupDataSet lookupData) {
        this.lookupData = lookupData;
    }
    
    
    
    public Set<String> getLookupMarkData() {
      return lookupMarkData;
    }

    public void setLookupMarkData(Set<String> lookupMarkData) {
      this.lookupMarkData = lookupMarkData;
    }

    /**
     * 设置复选框
     * @param LookupDataSet 复选框范围值
     */
    public void setCheckBox(Map<String,Object> lookupData) {
      this.lookupData = new LookupDataSet(lookupData);
      this.inputType = GaConstant.INPUT_CHECKLIST;
    }

    /**
     * 格式化显示设置
     * 
     * @param formatType
     *            格式化类型
     * @param formatPattern
     *            格式化模式(如小数格式化为".2",日期格式化为"yyyymmdd")
     */
    public void setFormat(DbFieldFormat format) {
      if (format !=null) {
        this.format = format;      
        this.format.setDefineField(this);
      }
    }

    /**
     * 设置输入控件
     * 
     * @param inputType
     *            输入控件类型
     */
    public void setInput(int inputType) {
        this.inputType = inputType;
    }

    /**
     * 设置输入模式
     * @param editMode 页面模式(新增/修改/通用)
     * @param inputMode 输入模式（只读/隐藏)
     */
    public void setInputMode(int editMode,int inputMode) {
      this.inputMode.put(editMode, inputMode);      
    }
    
    
    public Map<Integer, Integer> getInputMode() {
      return inputMode;
    }
    

    /**
     * 设置弹出选择模式
     * @param selectID 选择ID，一个页面可能存在多个选择操作，用ID区分不同的选择操作
     * @param selectFields 选择页面返回的字段列表,以,分隔
     * @param popPageUrl 选择页面URL
     * @param width 选择页面显示宽度 0为默认
     * @param height 选择页面显示高度 0为默认
     */
    public void setPopSelect(String selectID,String bindField,boolean popDisplay,String popPageUrl,String selectFields,int width,int height) {
      this.setPopSelect(selectID, bindField, popDisplay, popPageUrl, selectFields, "", width, height);
    }
    
    /**
     * 设置查找带回控件是否允许编辑
     * @param popEdit 是否可编辑
     */
    public void setPopEdit(boolean popEdit) {
      this.popEdit = popEdit;
    }
    
    public void setPopSubInput(int subInput) {
      this.popSubInput = subInput;
    }
        
    public int getPopSubInput() {
      return popSubInput;
    }

  
    
    /**
     * 设置弹出选择模式
     * (在弹出选择时可提示选择)
     * @param selectID
     * @param bindField
     * @param popDisplay
     * @param popPageUrl
     * @param selectFields
     * @param confirmInfo
     * @param width
     * @param height
     */
    public void setPopSelect(String selectID,String bindField,boolean popDisplay,String popPageUrl,String selectFields,String confirmInfo,int width,int height) {
      this.popID = selectID;
      this.popSelectFields = selectFields;
      this.popPageUrl = popPageUrl;
      this.popHeight = height;
      this.popWidth = width;
      this.popBindField =  bindField;
      this.popDisplay = popDisplay;
      this.inputType = GaConstant.INPUT_POPSELECT; 
      this.popConfirm = confirmInfo;      
    }
    /**
     * 设置只显示弹出选择的字段
     * @param selectID 选择ID
     * @param displayFieldCode 选择代码
     */
    public void setPopSelect(String selectID,String bindField,boolean popDisplay) {
      this.popID = selectID;
      this.popBindField = bindField;
      this.popDisplay = popDisplay;
      this.inputType = GaConstant.INPUT_POPSELECT;
    }
    
    
    /**
     * 设置自动完成信息
     * @param groupID 分组ID
     * @param bindField 绑定字段
     * @param suggestUrl 实时查询URL
     * @param suggestField 建议列表
     */
    public void setSuggest(String groupID,String bindField,String suggestUrl,String suggestField) {
      this.suggestID = groupID;
      this.suggestBindField = bindField;
      this.suggestUrl = suggestUrl;
      this.suggestSelectFields = suggestField;
    }
    
    public void setSuggest(String groupID,String bindField,String suggestUrl,String suggestField,String displayFields) {
      this.suggestID = groupID;
      this.suggestBindField = bindField;
      this.suggestUrl = suggestUrl;
      this.suggestSelectFields = suggestField;
      this.suggestDispFields = displayFields;
    }
    
    
    public String getSuggestID() {
      return suggestID;
    }

    public String getSuggestBindField() {
      return suggestBindField;
    }

    public String getSuggestSelectFields() {
      return suggestSelectFields;
    }

    public String getSuggestUrl() {      
      return suggestUrl;
    }
    
    
    public String getSuggestDispFields() {
      if (GaUtil.isNullStr(this.suggestDispFields)) {
        return this.suggestSelectFields;
      } else {
        return this.suggestDispFields;
      }
    }

    public void setRefCombox(String refComboxName,String refDataUrl,String refDataMethod) {
      this.inputType = GaConstant.INPUT_REFCOMBOX;
      this.refDataMethod = refDataMethod;
      this.refDataUrl = refDataUrl;
      this.refComboxName = refComboxName;
    }

    public void setSelectList(int selectListSize,boolean multiSelect) {
      this.inputType = GaConstant.INPUT_SELECTLIST;
      this.selectListSize = selectListSize;
      this.multiSelect = multiSelect;
    }
    
    public int getSelectListSize() {
      return selectListSize;
    }   
    
    public boolean isMultiSelect() {
      return multiSelect;
    }

    public void setFileUpload(boolean isPreview,String extInfo) {
      this.inputType = GaConstant.INPUT_UPLOAD;
      this.filePreview = isPreview;
      this.fileSelectExt = extInfo;
    }
    
    public void setFileUpload(boolean isPreview,String extInfo,int width,int height) {
      this.inputType = GaConstant.INPUT_UPLOAD;
      this.filePreview = isPreview;
      this.fileSelectExt = extInfo;
      this.fileWidth = width;
      this.fileHeight = height;      
    }
        
    public boolean isFilePreview() {
      return filePreview;
    }

    public String getFileSelectExt() {
      return fileSelectExt;
    }
    
    
    public int getFileWidth() {
      return fileWidth;
    }

    public int getFileHeight() {
      return fileHeight;
    }

    /**
     * 获取指定编辑模式下的控件模式(正常/只读/隐藏)
     * @param editMode
     * @return 输入控件模式(只读/隐藏)
     */
    public int getInputMode(int editMode) {
        if (this.inputMode.get(editMode) != null) {
          return this.inputMode.get(editMode);
        } else {
          return 0;
        }        
    }
    
    public void setInputFromSource(int inputType,String paramName) {
      if (inputType == GaConstant.INPUT_REQUEST || 
          inputType == GaConstant.INPUT_SESSION || 
          inputType == GaConstant.INPUT_ROWOBJ) {
        this.inputType = inputType;        
        this.inputSourceName = paramName;
      }
      else {
        throw new BizException(BizException.SYSTEM,"设置输入参数错误");
      }   
    }
   
    /**
     * 设置查询字段的控件展示
     * (默认查询字段是绑定到数据库)
     * @param queryOpera 查询操作符
     * @param order 排列序号
     * @param isAdvQuery 是否高级查询
     */
    public void setQueryOpera(int queryOpera, int order, boolean isAdvQuery) {
      this.setQueryOpera(queryOpera, order, isAdvQuery,true);
    }
    
    /**
     * 设置查询字段的控件展示
     * @param queryOpera 查询操作符
     * @param order 排列序号
     * @param isAdvQuery 是否高级查询
     * @param bindDb 是否绑定到数据库(如设置为绑定,则系统自动附加条件,否则不附加条件)
     */
    public void setQueryOpera(int queryOpera, int order, boolean isAdvQuery,boolean bindDb) {
      // 默认输入框，根据类型来定
      this.autoWire = bindDb;
      this.queryOpera = queryOpera;
      this.queryOrder = order;
      switch (this.dataType) {
      case GaConstant.DT_DATETIME:
          this.queryInputType = GaConstant.INPUT_DATETIME;
          break;
      case GaConstant.DT_DOUBLE:
      case GaConstant.DT_INT:
      case GaConstant.DT_LONG:
          this.queryInputType = GaConstant.INPUT_NUMTEXT;
          break;
      case GaConstant.DT_PASSWORD:
          this.queryInputType = GaConstant.INPUT_PASSWORD;
          break;
      case GaConstant.DT_STRING:
          this.queryInputType = GaConstant.INPUT_TEXT;
          break;
      }
    }
              
    /**
     * 返回参数来源的参数名称
     * @return
     */
    public String getParamSoureName() {
      return paramSoureName;
    }
    
    

    public String getInputSourceName() {
      return inputSourceName;
    }

    /**
     * 设置查询操作(默认为自动组装)
     * @param queryOpera
     *            查询比较符
     * @param queryInputType
     *            查询输入控件
     * @param order
     *            查询序号
     * @param isAdvQuery
     */
    public void setQueryOpera(int queryOpera, int queryInputType, int order, boolean isAdvQuery) {
        this.queryOpera = queryOpera;
        this.queryInputType = queryInputType;
        this.queryOrder = order;
        this.autoWire = true;
    }
    
    /**
     * 设置查询输入
     * @param queryOpera 查询操作
     * @param queryInputType 查询输入
     * @param order 是否
     * @param isAdvQuery 是否高级
     * @param isAutoWire 是否自动组装
     */
    public void setQueryOpera(int queryOpera, int queryInputType, int order, boolean isAdvQuery,boolean isAutoWire) {
      this.queryOpera = queryOpera;
      this.queryInputType = queryInputType;
      this.queryOrder = order;
      this.autoWire = isAutoWire;
   }
    
    public void setQueryTitle(String queryTitle) {
      this.queryTitle = queryTitle;
    }
    
    public String getQueryTitle() {
      if (GaUtil.isNullStr(this.queryTitle)) {
        return this.fieldName;
      } else {
        return this.queryTitle;  
      }      
    }
    
    public void setQueryInputType(int queryInputType) {
      this.queryInputType = queryInputType;
    }

    /**
     * 设置查询参数(不显示提供输入的查询字段),此参数均不自动组装
     * @param paramSource 参数源(request,session,rowobj)
     * @param paramName 参数源对应的名称
     * @param bindDb 是否绑定到数据库
     */
    public void setQueryParamFormSource(int inputType,String paramName) {
      if (inputType == GaConstant.INPUT_REQUEST || 
          inputType == GaConstant.INPUT_SESSION || 
          inputType == GaConstant.INPUT_ROWOBJ) {
        this.queryOpera = GaConstant.QUERY_EQUALS;
        this.queryInputType = inputType;        
        this.paramSoureName = paramName;
        this.autoWire = false;
      }
      else {
        throw new BizException(BizException.SYSTEM,"设置查询参数错误");
      }      
    }

    /**
     * 获取字段是否自动组装(同数据库相关)
     * @return
     */
    public boolean isAutoWire() {
      return autoWire;
    }

    /**
     * 设置字段是否自动组装
     * @param autoWire
     */
    public void setAutoWire(boolean autoWire) {
      this.autoWire = autoWire;
    }

    /**
     * 设置表格列显示
     * 
     * @param colWidth
     *            是否显示列
     * @param colWidth
     *            列宽度,0表示自动宽度;
     * @param isRowData
     *            是否行绑定数据
     */
    public void setColumnDisplay(boolean isDisplay, int colWidth, boolean isRowData) {
        this.display = isDisplay;
        this.columnWidth = colWidth;
        this.bindRowData = isRowData;
    }
    
    /**
     * 设置列显示装饰
     * @param mainCode 合并的主列,如未指定,则不合并
     * @param decorator 字符输出装饰对象
     */
    public void setColumnDecorator(String mainCode,StringDecorator decorator) {
      this.columnMergeCode = mainCode;
      this.columnDecorator = decorator;
    }
    
    public String getColumnMergeCode() {
      return columnMergeCode;
    }

    public StringDecorator getColumnDecorator() {
      return columnDecorator;
    }

    public int getQueryOrder() {
        return queryOrder;
    }

    public int getQueryOpera() {
        return this.queryOpera;
    }

    public int getQueryInputType() {
        return this.queryInputType;
    }

   
    public int getInputType() {
        return inputType;
    }

    
    public boolean isBindRowData() {
        return bindRowData;
    }

    public boolean isDisplay() {
        return display;
    }
     
    
    public boolean isRequire() {
      return isRequire;
    }

    public String getTableCode() {
        return tableCode;
    }

    public String getVerifyFormula() {
        return verifyFormula;
    }

    public void setVerifyFormula(String verifyFormula,boolean require) {
        this.verifyFormula = verifyFormula;
        this.isRequire = require;
    }
    
    
    
    public String getConfirmFieldCode() {
        return confirmFieldCode;
    }

    public void setConfirmFieldCode(String confirmFieldCode) {
        this.confirmFieldCode = confirmFieldCode;
    }
 
    public void setBindRowData(boolean bindRowData) {
      this.bindRowData = bindRowData;
    }

    public String getValueExpression() {
      return "?";
//        switch (this.dataType) {
//        case NoahConstant.DT_DATETIME:
//          if (this.format != null) {
//            String rtnFmt = this.format.getFormatPattern().replace("HH", "HH24");
//            rtnFmt = rtnFmt.replace("mm", "mi");
//            return "to_date(?,'" +rtnFmt + "')";
//          } else {
//            return "to_date(?,'yyyy-MM-dd')";
//          }       
//        default:
//            return "?";
//        }
    }

    public String getPopPageUrl() {
      return popPageUrl;
    }

    public int getPopWidth() {
      return popWidth;
    }

    public int getPopHeight() {
      return popHeight;
    }

    public String getPopSelectFields() {
      return popSelectFields;
    }

    public String getPopBindField() {
      return popBindField;
    }

    
    
    public String getPopConfirm() {
      return popConfirm;
    }

    public String getPopID() {
      return popID;
    }

    public boolean isPopDisplay() {
      return popDisplay;
    }

    public boolean isPopEdit() {
      return this.popEdit;
    }
    
    public DbFieldFormat getFormat() {
      return format;
    }
    
    public String getFormatPattern() {
      if (this.format != null) {
        return this.format.getFormatPattern();
      } else {
        return "";
      }
    }
    
     public String getValidationEngine(){
        String str = null;
        if(this.isRequire){
            str = "required,";
            //SPE
            return str = "required";
        }
        switch(this.getInputType()){
           case GaConstant.INPUT_NUMTEXT:
               if(this.getDataType() == GaConstant.DT_INT || this.getDataType() == GaConstant.DT_LONG){
                 str += "custom["+GaConstant.VALIDAT_INTEGER+"],";   
               }else if(this.getDataType() == GaConstant.DT_DOUBLE){
                 str += "custom["+GaConstant.VALIDAT_NUMBER+"],";
               }
           break;
           case GaConstant.INPUT_TEXT:
           case GaConstant.INPUT_TEXTAREA:
               if(!GaUtil.isNullStr(this.getVerifyFormula())){
                  str += "custom["+this.getVerifyFormula()+"],";
               }
           break;
        }
        if(this.getMinLength() > this.getMaxLength()){
            throw new BizException(BizException.SYSTEM,"'"+this.getFieldName()+"'最小长度不能大于最大长度");
        }
        if(this.getMaxLength() > 0){
            str += "length["+this.getMinLength()+","+this.getMaxLength()+"],";
        }
        
       // return "validate[required,custom[onlyLetterSp],length[0,100]]";
        return str == null ? null : "validate["+ str.substring(0,str.length()-1)+"]";
    }
     
   public String getInputCustomStyle() {
       return inputCustomStyle;
   }

   public void setInputCustomStyle(String customStyle) {
       this.inputCustomStyle = this.inputCustomStyle + ";"+customStyle;
   }
   
   public void addInputAttributeMap(String name, String value) {
     this.inputAttributeMap.put(name, value);
   }

   public Map<String, String> getInputAttributeMap() {
     return inputAttributeMap;
   }

  public String getRefDataUrl() {
    return refDataUrl;
  }

  public String getRefDataMethod() {
    return refDataMethod;
  }

  public String getRefComboxName() {
    return refComboxName;
  }

  public boolean isPK() {
    return isPK;
  }

  public void setPK(boolean isPK) {
    this.isPK = isPK;
    this.bindRowData = true;
  }

  public String getAliasCode() {
    return aliasCode;
  }

  public void setAliasCode(String aliasCode) {
    this.aliasCode = aliasCode;
  }
  
  public boolean isQueryParam() {
   if (this.queryInputType > 0 || this.queryOpera > 0) {
     return true;
   } else {
     return false;
   }
 }

  public String getTipInfo() {
    return tipInfo;
  }

  public void setTipInfo(String tipInfo) {
    this.tipInfo = tipInfo;
  }

  public Map<String, String> getCheckBoxsData() {
	return checkBoxsData;
  }
  
  public void putCheckBoxsData(String value,String showName ){
	  if(this.checkBoxsData == null){
		  this.checkBoxsData = new LinkedHashMap<String, String>();
	  }
	  this.checkBoxsData.put(value, showName);
  }

  public String getSelectedCheckBoxsValue() {
	return selectedCheckBoxsValue;
  }
	
  public void setSelectedCheckBoxsValue(String selectedCheckBoxsValue) {
	this.selectedCheckBoxsValue = selectedCheckBoxsValue;
  }

  public String getInputJSName() {
    return inputJSName;
  }

  public void setInputJSName(String inputJSName) {
    this.inputJSName = inputJSName;
  }

  public Boolean isFilterCode() {
	return isFilterCode;
  }

  public void setIsFilterCode(Boolean isFilterCode) {
	this.isFilterCode = isFilterCode;
  }

	public FieldVerify getFieldVerify() {
		return fieldVerify;
	}
	public void clearPopSelect(){
		this.popID = "";
	      this.popSelectFields = "";
	      this.popPageUrl = "";
	      this.popHeight = 0;
	      this.popWidth = 0;
	      this.popBindField =  "";
	      this.inputType = GaConstant.INPUT_TEXT; 
	      this.popConfirm = "";      
	      this.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
	      this.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_READONLY);

	}
}