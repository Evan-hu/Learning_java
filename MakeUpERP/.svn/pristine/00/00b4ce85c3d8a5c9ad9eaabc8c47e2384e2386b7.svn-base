/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.ga.erp.biz.member.viplog;

import java.util.ArrayList;
import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.mvc.ListView;
import com.ga.click.mvc.ModelMap;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.util.SystemUtil;

/**
 * 类描述
 * @author jzk
 * @create_time 2014-5-2 上午11:47:39
 * @project MakeUpERP
 */
public class ConsumeLogListView extends ListView {

  /**
   * @param viewID
   * @param modelMap
   */
  public ConsumeLogListView(String viewID, ModelMap modelMap) {
    super(viewID, modelMap);
  }


  public void initField() throws Exception {
    List<DbField> dbFields = new ArrayList<DbField>();
    
    DbField field = new DbField("会员编号", "MEMBER_NUM", GaConstant.DT_INT);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 1, false);
    dbFields.add(field);
    
    field = new DbField("合计金额", "TOTAL_MONEY", GaConstant.DT_MONEY);
    dbFields.add(field);
    
    field = new DbField("优惠金额", "DISCOUNT_MONEY", GaConstant.DT_MONEY);
    dbFields.add(field);
    
    field = new DbField("应付金额", "PAY_MONEY", GaConstant.DT_MONEY);
    dbFields.add(field);
    
    field = new DbField("门店名", "STORE_NAME", GaConstant.DT_STRING);
    field.setQueryOpera(GaConstant.QUERY_LIKE, 2, false);
    dbFields.add(field);
    
    field = new DbField("营业员", "TRUENAME", GaConstant.DT_STRING);
    fieldList.add(field);
    
    field = new DbField("交易时间", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss", false));
    field.setQueryOpera(GaConstant.QUERY_BETWEEN, GaConstant.INPUT_DATETIME, 3, false);
    field.setAliasCode("sso");
    dbFields.add(field);
//    
//    field = new DbField("类型", "TYPE", GaConstant.DT_INT);
//    field.setLookupData(SystemUtil.getOrderTypeMap());
//    dbFields.add(field);
//    
    this.fieldList = dbFields;
  }
}
