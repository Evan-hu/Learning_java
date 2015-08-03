package com.ga.erp.biz.content.msgsend;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.editor.Editor;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

public class MsgSendFormView extends FormView {

  public MsgSendFormView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }

  @Override
  public void initField() throws Exception {

    List<DbField> dbFields = new ArrayList<DbField>();

    DbField field = new DbField("ID", "SHOP_ID", GaConstant.DT_LONG);
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "SHOP_ID");
    dbFields.add(field);

    field = new DbField("商家名称<font color='red'>*</font>", "SHOP_NAME",
        GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("会员ID", "MEMBER_ID", GaConstant.DT_STRING);
    field.setPopSelect("MEMBERSELECT", "MEMBER_ID", false);
    field.setColumnDisplay(false, 0, true);
    dbFields.add(field);

    field = new DbField("会员帐号", "USERNAME", GaConstant.DT_STRING);
    field.setPopSelect("MEMBERSELECT", "USERNAME", true,
        "/member/member_main.htm", "MEMBER_ID,USERNAME,cid_memberList", 1000,
        600);
    dbFields.add(field);

    field = new DbField("企业店铺LOGO", "COMPANY_IMG_URL", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_UPLOAD);
    field.setFileUpload(true, "*.jpg", 195, 160);
    dbFields.add(field);

    field = new DbField("个人店铺LOGO", "PERSONAL_IMG_URL", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_UPLOAD);
    field.setFileUpload(true, "*.jpg", 195, 160);
    dbFields.add(field);

    field = new DbField("类型", "TYPE", GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    Map<String, Object> option = new LinkedHashMap<String, Object>();
    option.put("1", "个体商家");
    option.put("2", "企业商家");
    field.setLookupData(new LookupDataSet(option));
    field.setVerifyFormula("", true);
    dbFields.add(field);

    field = new DbField("营业执照", "SHOP_LICENSE", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("法人代表", "LEGAL", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("联系电话", "TEL", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("联系地址", "ADDR", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("业务范围", "SCOPE", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("法人身份证", "ID_CARD", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("个人支付宝", "ALIPAY", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("银联", "UNIONPAY", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("是否开启个人店铺", "IS_OPEN_PERSONAL", GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getYesNoMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);

    field = new DbField("是否推荐", "IS_RECOMMEND", GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getYesNoMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);

    field = new DbField("推荐指数", "RECOMMEND_NUM", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("是否认证", "IS_CHECK", GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getYesNoMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);

    field = new DbField("认证指数", "CHECK_NUM", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("店铺类型", "SHOP_TYPE", GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    option = new LinkedHashMap<String, Object>();
    option.put("3", "整车厂");
    field.setVerifyFormula("", true);
    field.setLookupData(new LookupDataSet(option));
    dbFields.add(field);

    field = new DbField("百度经度", "LONG_ITUDE", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("百度纬度", "LAT_ITUDE", GaConstant.DT_STRING);
    dbFields.add(field);

    field = new DbField("状态", "STATE", GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setLookupData(SystemUtil.getStatusMap());
    field.setVerifyFormula("", true);
    dbFields.add(field);

    field = new DbField("创建时间", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,
        "yyyy-MM-dd HH:mm:ss", false));
    field
        .setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT,
        GaConstant.INPUTMODE_READONLY);
    dbFields.add(field);

    field = new DbField("综合得分", "SHOP_REVIEW_STAR", GaConstant.DT_LONG);
    dbFields.add(field);

    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "40");
    dbFields.add(field);

    Editor editor = new Editor("详细介绍", "CONTENT", GaConstant.DT_STRING);
    editor.setToolsMode(GaConstant.EDITOR_MODE_MFULL);
    editor.setRows(25);
    editor.setCols(75);
    editor.bindAddAttribute();
    dbFields.add(editor);

    this.fieldList = dbFields;
  }

}
