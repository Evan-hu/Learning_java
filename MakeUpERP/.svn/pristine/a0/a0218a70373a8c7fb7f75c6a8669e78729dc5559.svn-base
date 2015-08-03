package com.ga.erp.biz.store.storealert;

import java.util.List;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.PageResult;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.erp.biz.ACLBiz;

public class StoreAlertBiz extends ACLBiz {

  public StoreAlertBiz(UserACL userACL) {
    super(userACL);
  }
  
  public PageResult queryStoreAlertList(QueryPageData pageData,List<DbField> fieldList, String type) {
    String sql = "select sc.*,s.STORE_NAME,c.COMMODITY_NAME from STORE_COMM sc " +
        "join STORE s on sc.STORE_ID = s.STORE_ID " +
        "join COMMODITY c on c.COMMODITY_ID=sc.COMMODITY_ID";
    String where = "";
    if(type.equals("1")){//¸º¿â´æ
      where = "INNAGE_BALANCE < 0 ";
    }else {//È±»õ¸æ¾¯
      where = "INNAGE_BALANCE <= INNAGE_LOWER_LIMIT and INNAGE_BALANCE >= 0 ";
    }
    return BizUtil.queryListBySQL(sql, where,"sc.STORE_COMM_ID desc",fieldList, pageData, this.userACL);
  }
}
