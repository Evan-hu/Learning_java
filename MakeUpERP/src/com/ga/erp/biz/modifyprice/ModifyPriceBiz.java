/**
 * Copyright (c) 2014 GA
 * All right reserved.
 */
package com.ga.erp.biz.modifyprice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

/**
 * 类描述
 * @author zyq
 * @create_time 2014-7-3 下午2:53:11
 * @project MakeUpERP
 */
public class ModifyPriceBiz extends ACLBiz{
 

    public ModifyPriceBiz(UserACL userACL) {
      super(userACL);
    }

    private String pubSql="select mp.*,o.TRUENAME from MODIFY_PRICE mp JOIN OP o ON o.OP_ID=mp.OP_ID ";
    
    public PageResult queryModifyPriceList(QueryPageData queryPageData,List<DbField> dbFieldList,String objType) {
     //根据不同的入口  查询部同的价格调整单
      String where =" mp.TYPE=" + objType ;
      return BizUtil.queryListBySQL(pubSql, where, "mp.MODIFY_PRICE_ID DESC", dbFieldList, queryPageData, this.userACL);
      
    }
    
    public Map<String, Object> queryModifyPriceDetail(Long id) {
      
      return DbUtils.queryMap(DbUtils.getConnection(),pubSql + " where mp.MODIFY_PRICE_ID = ? ", id);
    }
    
    public List<Map<String, Object>> queryInventoryBillDetail(List<DbField> fieldList, Long id) {
      return DbUtils.queryMapList(fieldList, pubSql + " mp.where MODIFY_PRICE_ID = ?  ", id);
    }

    public void saveModifyPrice(Map<String, Object> valuesMap, boolean isAdd) {
      String dbFile = "BILL_NOTE,NOTE,REASON,OP_ID,STATE,TYPE,OBJECT_NAME,OBJECT_ID,CREATE_TIME";
      checkMap(valuesMap);
      if (isAdd) {
        Map<String, String> funcMap = new HashMap<String, String>();
        funcMap.put("CREATE_TIME", "now()");
        valuesMap.put("OP_ID", this.userACL.getUserID());
        valuesMap.put("STATE", 1);
        DbUtils.add("MODIFY_PRICE", valuesMap, funcMap, dbFile);
        
      } else {
        DbUtils.update("MODIFY_PRICE", valuesMap, null, "REASON,NOTE",
            "MODIFY_PRICE_ID");
      }

    }

    private void checkMap(Map<String, Object> valuesMap) {
       StringBuffer buffer = new StringBuffer("");
          int count = 1;
      if (GaUtil.isNullStr((valuesMap.get("INVENTORY_BATCH_ID") + ""))) {
        buffer.append("<br>"+count+"，请选盘点批次；");
            count++;
      }
      if (GaUtil.isNullStr((valuesMap.get("INVENTORY_FLOW_NUM") + ""))) {
        buffer.append("<br>"+count+"，请填写盘点流水账号；");
            count++;
      }
      
      
      if(buffer.length() > 0){
             throw new BizException(buffer.toString());
      }
    }
  

  
  
}
