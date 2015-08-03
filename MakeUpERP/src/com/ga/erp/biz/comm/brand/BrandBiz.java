package com.ga.erp.biz.comm.brand;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class BrandBiz extends ACLBiz {

  public BrandBiz(UserACL userACL) {
    super(userACL);
  }

  public PageResult queryBrandList(QueryPageData pageData,List<DbField> fieldList) {
	  return BizUtil.queryListBySQL("select b.*,o.TRUENAME from BRAND b JOIN OP o on b.OP_ID = o.OP_ID", 
			  "", "b.BRAND_ID desc", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryBrandDetail(Long id) throws BizException {
	  return DbUtils.queryMap(DbUtils.getConnection(), "select b.*,o.TRUENAME from " +
	  		"BRAND b JOIN OP o on b.OP_ID = o.OP_ID where b.BRAND_ID = ?", id);
  }

  public void saveBrand(Map<String,Object> valueMap, Boolean isAdd) {
    try {      
      String updateField = "NAME,CODE,CUT_RATIO,NOTE,RANK_NUM";  
      if(!GaUtil.isNullStr(valueMap.get("CUT_RATIO")+"") && (Double.parseDouble(valueMap.get("CUT_RATIO")+"") > 100 || Double.parseDouble(valueMap.get("CUT_RATIO")+"") < 0)){
        throw new BizException("提成比率不能小于0或大于100%；");
      }
      if(isAdd){
    	  valueMap.put("OP_ID", this.userACL.getUserID());
    	  valueMap.put("STATE", 1);
    	  Map<String,String> funcMap = new HashMap<String, String>();	
    	  funcMap.put("CREATE_TIME", "NOW()");
    	  DbUtils.add("BRAND",valueMap,funcMap,updateField + ",OP_ID,CREATE_TIME,STATE");
      }else{
    	  DbUtils.update("BRAND",valueMap,null,updateField,"BRAND_ID");
      } 
    } catch (BizException e) {
       throw e;
     } catch(Exception ex) {
       ex.printStackTrace(); 
       throw new BizException(BizException.SYSTEM,"保存失败",ex);
    }
  }
  
}
