package com.ga.erp.biz.comm;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class CommFormView extends FormView {

  public CommFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

     List<DbField> dbFields = new ArrayList<DbField>();
    
     DbField field = new DbField("ID", "COMMODITY_ID", GaConstant.DT_LONG);
     field.setInput(GaConstant.INPUT_HIDDEN);
     field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "COMMODITY_ID");
     dbFields.add(field);

     field = new DbField("商品名称<font color='red'>*</font>", "COMMODITY_NAME",GaConstant.DT_STRING);
     dbFields.add(field);

     field = new DbField("类型","TYPE",GaConstant.DT_INT);
  	 field.setInput(GaConstant.INPUT_SELECT);
     Map<String, Object> option = new LinkedHashMap<String, Object>();
     option.put("1", "普通商品");
     option.put("2", "赠品");
     option.put("3", "试用品");
     field.setLookupData(new LookupDataSet(option));
     field.setVerifyFormula("", true);
     dbFields.add(field);
     
     field = new DbField("商品简称", "COMMODITY_SNAME", GaConstant.DT_STRING);
	   dbFields.add(field);
	
  	 field = new DbField("货号<font color='red'>*</font>", "CODE", GaConstant.DT_STRING);
  	 dbFields.add(field);
  	 
  	 field = new DbField("自编码", "CUSTOM_CODE", GaConstant.DT_STRING);
  	 dbFields.add(field);
  	 
  	 field = new DbField("助记码", "MNEMONIC_CODE", GaConstant.DT_STRING);
  	 dbFields.add(field);
  	 
  	 field = new DbField("分类ID", "SORT_ID", GaConstant.DT_LONG);
     field.setPopSelect("NEWSORTSELECT", "SORT_ID", false);
     dbFields.add(field);
    
     field = new DbField("分类<font color='red'>*</font>", "SORT_NAME", GaConstant.DT_STRING);
     field.setPopSelect("NEWSORTSELECT","SORT_NAME",true,
            "/comm/sort_sele.htm",
            "SORT_ID,SORT_NAME,cid_sortTree",200,300);
     dbFields.add(field);
    
     field = new DbField("品牌ID","BRAND_ID", GaConstant.DT_LONG);
     field.setPopSelect("NEWBRANDSELECT", "BRAND_ID", false);
     dbFields.add(field); 
    
     field = new DbField("品牌<font color='red'>*</font>","NAME",GaConstant.DT_STRING);
     field.setPopSelect("NEWBRANDSELECT","NAME",true,
        "/comm/brand_main.htm",
        "BRAND_ID,NAME,cid_brandList", 800,300);
     dbFields.add(field);

     field = new DbField("单位","UNIT",GaConstant.DT_STRING);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("产地","ADDR",GaConstant.DT_STRING);
     field.setInputCustomStyle("width:100px;");
     dbFields.add(field);
     
     field = new DbField("商品规格","COMMODITY_SPEC",GaConstant.DT_STRING);
     dbFields.add(field);
     
     field = new DbField("进货规格<font color='red'>*</font>","STOCK_SPEC",GaConstant.DT_STRING);
     dbFields.add(field);

     field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
     field.setInput(GaConstant.INPUT_TEXTAREA);
     field.addInputAttributeMap("rows", "6");
     field.addInputAttributeMap("cols", "120");
  	 dbFields.add(field);
	
     /**********************************价格**************************************/
     field = new DbField("参考批发价<font color='red'>*</font>", "TRADE_PRICE", GaConstant.DT_MONEY);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("参考采购价<font color='red'>*</font>", "PURCHASE_PRICE", GaConstant.DT_MONEY);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);       
     
     field = new DbField("参考配送价<font color='red'>*</font>", "SEND_PRICE", GaConstant.DT_MONEY);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("参考零售价<font color='red'>*</font>", "SELL_PRICE", GaConstant.DT_MONEY);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("参考会员价<font color='red'>*</font>", "MEMBER_PRICE", GaConstant.DT_MONEY);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     /**********************************价格**************************************/
     
     
     /**********************************数量**************************************/
     field = new DbField("累计采购数", "PURCHASE_AMOUNT", GaConstant.DT_INT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setDefaultValue(0);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("累计配送数", "SEND_AMOUNT", GaConstant.DT_INT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setDefaultValue(0);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("累计销售数", "SELL_AMOUNT", GaConstant.DT_INT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setDefaultValue(0);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("累计退货数", "RETURN_AMOUNT", GaConstant.DT_INT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setDefaultValue(0);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     
     field = new DbField("累计货损数", "LOSS_AMOUNT", GaConstant.DT_INT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     field.setDefaultValue(0);
     field.setInputCustomStyle("width:50px;");
     dbFields.add(field);
     /**********************************数量**************************************/
    
     
     /**********************************状态**************************************/
     field = new DbField("创建人", "TRUENAME", GaConstant.DT_STRING);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     dbFields.add(field);
 
     field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
     field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     dbFields.add(field);
    
     field = new DbField("最后修改人", "LAST_OP_NAME", GaConstant.DT_STRING);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     dbFields.add(field);
 
     field = new DbField("最后修改时间","LAST_TIME",GaConstant.DT_DATETIME);
     field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
     dbFields.add(field);

     field = new DbField("状态","STATE",GaConstant.DT_INT);
     field.setInput(GaConstant.INPUT_SELECT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	 field.setVerifyFormula("", true);
  	 field.setLookupData(SystemUtil.getStatusMap());
     dbFields.add(field);
     
     field = new DbField("采购状态","PURCHASE_STATE",GaConstant.DT_INT);
     field.setInput(GaConstant.INPUT_SELECT);
     field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
     field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
  	 option = new LinkedHashMap<String, Object>();
  	 option.put("0", "停购");
  	 option.put("1", "正常");
  	 field.setLookupData(new LookupDataSet(option));
  	 field.setVerifyFormula("", true);
     dbFields.add(field);
     /**********************************状态**************************************/
     
     this.fieldList = dbFields;
  }

}
