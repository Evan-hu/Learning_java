package com.ga.erp.biz.comm;

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

public class CommBiz extends ACLBiz {

  public CommBiz(UserACL userACL) {
    super(userACL);
  }
  
  public static String publicSql = "SELECT\n" +
			"  c.*,s.SORT_NAME,\n" + 
			"  b.NAME,\n" + 
			"  o.TRUENAME\n" + 
			"  FROM  COMMODITY C \n" + 
			"  JOIN SORT s\n" + 
			"    ON c.SORT_ID = s.SORT_ID\n" +
			"  JOIN BRAND b ON c.BRAND_ID = b.BRAND_ID\n" +
            "  JOIN OP o ON o.OP_ID = c.OP_ID\n";
  
  public PageResult queryCommList(QueryPageData pageData,List<DbField> fieldList) {
	  return BizUtil.queryListBySQL(publicSql, "", "c.COMMODITY_ID desc", fieldList, pageData, this.userACL);
  }
  
  public PageResult queryBrandCommList(QueryPageData pageData,List<DbField> fieldList,Long brandId) {
    return BizUtil.queryListBySQL(publicSql, "c.BRAND_ID = " + brandId, "c.COMMODITY_ID desc", fieldList, pageData, this.userACL);
  }
  
  public PageResult querySortCommList(QueryPageData pageData,List<DbField> fieldList,Long sortId) {
    return BizUtil.queryListBySQL(publicSql, "c.SORT_ID = " + sortId, "c.COMMODITY_ID desc", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryCommDetail(List<DbField> fieldList, Long id) throws BizException {
	  return DbUtils.queryMap(fieldList, publicSql + " where c.COMMODITY_ID = ? ", id);
  }
  
  private void chkValue(Map<String,Object> valueMap){
    StringBuffer buffer = new StringBuffer("");
    int count = 1;
  	if(GaUtil.isNullStr(valueMap.get("COMMODITY_NAME") + "")){
  	  buffer.append("<br>"+count+"，请输入商品名称；");
      count++; 
      }
  	if(GaUtil.isNullStr(valueMap.get("CODE") + "")){
  	  buffer.append("<br>"+count+"，请输入货号；");
      count++;
  	}
  	if(GaUtil.isNullStr(valueMap.get("SORT_ID") + "")){
  		buffer.append("<br>"+count+"，请选择分类；");
  		count++; 
  	}
  	if(GaUtil.isNullStr(valueMap.get("BRAND_ID") + "")){
  	  buffer.append("<br>"+count+"，请选择品牌；");
      count++;
  	}
  	if(GaUtil.isNullStr(valueMap.get("STOCK_SPEC") + "")){
        buffer.append("<br>"+count+"，请输入进货规格；");
        count++;
    }
  	if((valueMap.get("TRADE_PRICE") + "").equals("0")){
        buffer.append("<br>"+count+"，请输入参考批发价；");
        count++;
    }
  	if((valueMap.get("PURCHASE_PRICE") + "").equals("0")){
        buffer.append("<br>"+count+"，请输入参考采购价；");
        count++;
    }
  	if((valueMap.get("SEND_PRICE") + "").equals("0")){
        buffer.append("<br>"+count+"，请输入参考配送价；");
        count++;
    }
  	if((valueMap.get("SELL_PRICE") + "").equals("0")){
        buffer.append("<br>"+count+"，请输入参考零售价；");
        count++;
    }
  	if((valueMap.get("MEMBER_PRICE") + "").equals("0")){
        buffer.append("<br>"+count+"，请输入参考会员价；");
        count++;
    }
  	if(buffer.length() > 0){
       throw new BizException(buffer.toString());
    }
  }
  
  public void saveComm(Map<String,Object> valueMap, /*Map<String,Object> extendsMap,*/ Boolean isAdd) {
    try {      
      this.chkValue(valueMap);	
      String updateField = "COMMODITY_NAME,TYPE,COMMODITY_SNAME,CODE," +
    		"CUSTOM_CODE,MNEMONIC_CODE,SORT_ID,BRAND_ID,UNIT,ADDR," +
    		"COMMODITY_SPEC,STOCK_SPEC,LAST_OP_ID,LAST_OP_NAME,LAST_TIME,NOTE";
      
      String newUpdateField = ",TRADE_PRICE,PURCHASE_PRICE,SEND_PRICE," +
          "SELL_PRICE,MEMBER_PRICE,OP_ID,CREATE_TIME";
      
      valueMap.put("OP_ID", this.userACL.getUserID());
	    valueMap.put("LAST_OP_ID", this.userACL.getUserID());
	    valueMap.put("LAST_OP_NAME", this.userACL.getTrueName());
	  
	    Map<String,String> funcMap = new HashMap<String, String>();	
	    funcMap.put("LAST_TIME", "NOW()");
	  
      DbUtils.begin();
      if(isAdd){
        funcMap.put("CREATE_TIME", "NOW()");
      	DbUtils.add("COMMODITY",valueMap,funcMap,updateField + newUpdateField);
      }else{
        DbUtils.update("COMMODITY", valueMap, funcMap, updateField, "COMMODITY_ID");
      }
      DbUtils.commit();
	} catch (BizException e) {
		throw e;
	} catch (Exception ex) {
		ex.printStackTrace();
		throw new BizException(BizException.SYSTEM, "保存失败", ex);
	} finally {
		DbUtils.rollback();
	 }
  }

  public void stopCommodity(String ids) {
    try {
      //检查所选的数据是否都是未审核数据
      String sql = "select count(*) from COMMODITY where COMMODITY_ID in ("+ids+") and STATE = ?";
      Long cnt = DbUtils.queryLong(sql,0);
      if (cnt > 0) {
        throw new BizException("所选商品含有已废除的商品");
      }
      sql = "update COMMODITY set STATE = 0 where COMMODITY_ID in  ("+ids+")";
      DbUtils.begin();
      DbUtils.execute(sql);
      DbUtils.commit();
    } catch (BizException e) {
       throw e;
    } catch(Exception ex) {
       ex.printStackTrace();
       throw new BizException(BizException.SYSTEM,"废除商品失败",ex);
    }
    finally {
      DbUtils.rollback();
    }  
  }
  
//  /**
//   * 启用商品
//   * @param ids
//   */
//  public void startCommodity(String ids) {
//    try {
//      //检查所选的数据是否都是未审核数据
//      String sql = "select count(*) from COMMODITY where COMMODITY_ID in ("+ids+") and STATE != ?";
//      Long cnt = DbUtils.queryLong(sql,-1);
//      if (cnt >0) {
//        throw new BizException("所选商品含有上架的商品,请只选中下架的商品后执行");
//      }
//      sql = "update COMMODITY set STATE = 1 where COMMODITY_ID in  ("+ids+")" +  (userACL.getShopID() > 0 ? " and SHOP_ID = "+userACL.getShopID()+" " : "");
//      DbUtils.begin();
//      DbUtils.execute(sql);
//      String[] idList = ids.split(",");
//      if (idList != null) {
//        for(int i=0;i<idList.length;i++) {
//          this.updateCommodityChangeLog(Long.parseLong(idList[i]));
//        }
//      }
//      SystemUtil.opLog(this.userACL,"启用商品","", ids);
//      DbUtils.commit();
//    }  catch (BizException e) {
//      throw e;
//    }  catch(Exception ex) {
//      ex.printStackTrace();
//      throw new BizException(BizException.SYSTEM,"执行查询失败",ex);
//    }
//    finally {
//      DbUtils.rollback();
//    }  
//  }
  

}
