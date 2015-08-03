package com.ga.erp.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.sql.rowset.CachedRowSet;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.erp.biz.CustomFormat;

public class SystemUtil {
	
  public static final long ID_TREEROOT = 1L;
  public static final long SYSCODE_NORMALSORT = 102001L;  //普通商品分类编码
  public static final int STORE_TYPE_STORE = 1;//店间直调相关业务
  public static final int STORE_TYPE_DC = 2;//DC相关业务
  
  public static final int STORE_ORDER_DIRECT_APPLY = 1;//店间直调申请单
  public static final int STORE_ORDER_DIRECT_SEND = 2;//店间直调发货单
  public static final int STORE_ORDER_DIRECT_RECEIPT = 3;//店间直调发货单
  public static final int STORE_ORDER_DC_APPLY  = 4;//门店采购申请单
  public static final int STORE_ORDER_DC_SEND = 5;//DC发货单
  public static final int STORE_ORDER_DC_RECEIPT = 6;//门店采购收货单
  public static final int STORE_ORDER_DC_RETURN = 7;//门店采购退货收货单
  public static final int STORE_ORDER_DC_RETURN_RECEIPT =8;//门店采购退货收货单（DC收货）
  public static final int STORE_ORDER_DIRECT_RETURN = 9;//店间直调退货申请单
  public static final int STORE_ORDER_DIRECT_RETURN_RECEIPT = 10;//店间直调退货收货单
  
  
  public static LookupDataSet getSysCodeMap(Long pCode) {
    return getSysCodeMap(pCode,false);
  }
  
  public static LookupDataSet getSysCodeMap(Long pCode,boolean includeChild) {
    try {
      List<Map<String, Object>>  list;
      if (!includeChild) {
        list = DbUtils.queryMapList(DbUtils.getConnection(),"SELECT SYS_CODE_ID,SYS_CODE_NAME FROM SYS_CODE WHERE P_CODE = ? and STATE= 1",pCode);
      } else {
        list = DbUtils.queryMapList(DbUtils.getConnection(),"SELECT SYS_CODE_ID,SYS_CODE_NAME FROM SYS_CODE WHERE  STATE= 1 START WITH P_CODE = ? CONNECT BY PRIOR SYS_CODE_ID =  P_CODE",pCode);  
      }
      
      if (list == null) {
        return new LookupDataSet(new HashMap<String,Object>());
      } else {
        Map<String,Object> vMap = new HashMap<String,Object>();
        for (Map<String,Object> row : list) {
          vMap.put(row.get("SYS_CODE_ID").toString(),row.get("SYS_CODE_NAME"));
        }
        return new LookupDataSet(vMap);
      }      
    } catch (Exception ex) {
      throw new BizException(BizException.SYSTEM,"获取系统编码异常");
    }
  }
  
  public static LookupDataSet getSettleTypeMap() {
	 try {
	     CachedRowSet rs = DbUtils.query("select SETTLEMENT_TYPE_ID,TYPE from SETTLEMENT_TYPE");
	  	 return new LookupDataSet(rs, "SETTLEMENT_TYPE_ID", "TYPE");
	   } catch (Exception ex) {
	     throw new BizException(BizException.SYSTEM,"获取费用类型异常");
	   }
	 }
  
  /**
   * 返回是否MAP
   * @return
   */
  public static LookupDataSet getYesNoMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "是");
    lookup.put("0", "否");
    return new LookupDataSet(lookup);
  }
  
  /**
   * 返回状态编码MAP
   * @return
   */
  public static LookupDataSet getStatusMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "正常");
    lookup.put("0", "无效");
    return new LookupDataSet(lookup);
  }
  
  /**
   * 站内信阅读状态
   * @return
   */
  public static LookupDataSet getMessageSendStateMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("2", "已删除");
    lookup.put("1", "已读");
    lookup.put("0", "未读");
    return new LookupDataSet(lookup);
	 }
  
  
  /**
   * 站内信type
   * @return
   */
  public static LookupDataSet getStationMsgTypeMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("3", "供应商");
    lookup.put("2", "门店");
    lookup.put("1", "会员");
    return new LookupDataSet(lookup);
	  }
  
  /**
   * 站内信状态
   * @return
   */
  public static LookupDataSet getStationMsgStateMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("2", "已发送");
    lookup.put("1", "未发送");
    lookup.put("0", "无效");
    return new LookupDataSet(lookup);
	  }
  
  /**
   * 性别
   * @return
   */
  public static LookupDataSet getSexMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "男");
    lookup.put("0", "女");
    return new LookupDataSet(lookup);
	 }
  
  
  /**
   * 返回对象类型map
   * @return
   */
  public static LookupDataSet getObjectTypeMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "门店采购");
    lookup.put("2", "门店退货");
    lookup.put("3", "门店采购差异");
    lookup.put("4", "其它费用类型");
    return new LookupDataSet(lookup);
  }
 
	  /**
	   * 返回审核状态map(List及编辑状态)
	   * @return
	   */
	  public static LookupDataSet getAuditsMap() {
	    try {
	      Map<String, Object> auditsMap = new LinkedHashMap<String, Object>();
	      auditsMap.put("-1", "不通过");
	      auditsMap.put("0", "待审核");
	      auditsMap.put("1", "通过");
	      return new LookupDataSet(auditsMap);
	    } catch (Exception ex) {
	      throw new BizException(BizException.SYSTEM,"获取审核状态异常");
	    }  
	  }
	  
	  /**
	   * 返回审核状态map(List及编辑状态)
	   * @return
	   */
	  public static LookupDataSet getOrderTypeMap() {
	    try {
	      Map<String, Object> auditsMap = new LinkedHashMap<String, Object>();
	      auditsMap.put("1", "正常订单");
	      auditsMap.put("2", "暂挂订单");
	      
	      return new LookupDataSet(auditsMap);
	    } catch (Exception ex) {
	      throw new BizException(BizException.SYSTEM,"获取订单类型异常");
	    }  
	  }
	  
	  public static List<DbField> getNormalFieldList(){
		  
		  List<DbField> dbFields = new ArrayList<DbField>(); 
		  
		  DbField field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		  field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		  dbFields.add(field);

		  field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
		  field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		  field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		  dbFields.add(field);

		  field = new DbField("状态","STATE",GaConstant.DT_INT);
		  field.setInput(GaConstant.INPUT_SELECT);
		  field.setLookupData(SystemUtil.getStatusMap());
		  field.setVerifyFormula("", true);
		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		  dbFields.add(field);
		  
		  field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
		  field.setInput(GaConstant.INPUT_TEXTAREA);
		  field.addInputAttributeMap("rows", "3");
		  field.addInputAttributeMap("cols", "25");
		  dbFields.add(field);
		  
		  return dbFields;
	  }
	  
	  public static List<DbField> getNormalListFieldList(String aliasCode, int beginOrder){
	    
	    List<DbField> dbFields = new ArrayList<DbField>(); 
	    
	    DbField field = new DbField("创建人","TRUENAME",GaConstant.DT_STRING);
	    field.setQueryOpera(GaConstant.QUERY_LIKE, beginOrder, true);
	    field.setInputCustomStyle("width:80px;");
	    dbFields.add(field);
	  
	    field = new DbField("创建时间","CREATE_TIME",GaConstant.DT_DATETIME);
	    field.setAliasCode(aliasCode);
	    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
	    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, beginOrder + 1, true);
	    dbFields.add(field);
	    
	    field = new DbField("状态","STATE",GaConstant.DT_INT);
	    field.setAliasCode(aliasCode);
	    field.setQueryOpera(GaConstant.QUERY_EQUALS,GaConstant.INPUT_SELECT, beginOrder + 2, true);
	    field.setLookupData(SystemUtil.getStatusMap());
	    dbFields.add(field);
	    
	    return dbFields;
	  }
	  
	  public static String formatStr(String str){
		  return str == null ? "" : str;
	  }
	  
	  /**
	   * 记录业务日志,在BIZ层调用
	   * @param userAcl 用户信息
	   * @param bizCode 业务代码(商品/订单/等)
	   * @param actionName 执行动作
	   * @param target 目标ID对象(Long类型 List类型 String[]类型)
	   */
	  @SuppressWarnings("rawtypes")
	public static void opLog(UserACL userAcl,Long bizCode,String actionName,String actionDesc,Object target) {
	    String targetInfo = "";
	    if (target instanceof Long) {
	      targetInfo = target.toString();
	    } else if (target instanceof List) {
	      StringUtils.join(((List)target),",");
	    } else if (target instanceof Object[]) {      
	      StringUtils.join(((Object[])target),",");
	    } else if (target instanceof String) {      
	      targetInfo = (String)target;
	    }
	    String sql = "insert into OP_LOG(OP_LOG_ID,OPERATOR_ID,OPERATOR_TYPE,CREATE_TIME,OPERATOR_TARGET,NOTE) values(SEQ_OP_LOG.nextval,?,?,sysdate,?,?)";
	    DbUtils.execute(sql,userAcl.getUserID(),bizCode,targetInfo,actionDesc);
	  }
	  
	  public static String MapValuetoString(Map map,Object value){
		  return map.get(value) == null ? "" : map.get(value).toString();
	  }
	  public static String MapValuetoMoney(Map map,Object value){
		  return map.get(value) == null ? "" : (((Integer)map.get(value)) / 100.00)+"";
	  }
	  /**
	   * 物流在途状态查询
	   * @param deliveryMap
	   * @return
	   */
	  public static List<Map<String,Object>> resolveDeliveryRecord(Map<String,Object> deliveryMap){
		    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		    if(deliveryMap != null){
		      if("1".equals(String.valueOf(deliveryMap.get("delivery_org_id")))){
		        Map<String, Object> jsonDataMap = new HashMap<String, Object>();
		        jsonDataMap.put("DEAL_MESSAGE", "自配");
		        jsonDataMap.put("DEAL_ADMIN", "自配");
		        list.add(jsonDataMap);
		      }else {
		        String deliveryNo="";
		        String deliveryCode="";
		        if(deliveryMap!=null){
		          deliveryNo=String.valueOf(deliveryMap.get("DISTRIBUTE_NUM"));
		          deliveryCode=String.valueOf(deliveryMap.get("DELIVERY_CODE"));
		        }
		        JSONObject returnObject = new JSONObject();
		        StringBuffer sb = new StringBuffer();
		        sb.append("http://www.kuaidi100.com/query?type=").append(deliveryCode.trim())
		        .append("&postid=").append(deliveryNo.trim()).append("&order=asc"); 
		        int statusCode;
		        GetMethod getMethod = null;
		        try {
		          HttpClient httpClient = new HttpClient();
		          getMethod = new GetMethod(sb.toString());
		          statusCode = httpClient.executeMethod(getMethod);
		          if (statusCode != HttpStatus.SC_OK) {
		            System.err.println("Method failed: " + getMethod.getStatusLine());
		          }
		          byte[] responseBody = getMethod.getResponseBody();
		          JSONObject jsonObject = new JSONObject(new String(responseBody, "UTF-8"));
		          if( !jsonObject.isNull("data")){
		            returnObject.put("message", jsonObject.getString("message"));
		            returnObject.put("status", 1);
		            List<JSONObject> jsons = new ArrayList<JSONObject>();
		            for( int i = 0 ; i <  jsonObject.getJSONArray("data").length(); i ++){
		              if(! (jsonObject.getJSONArray("data").getJSONObject(i).getString("time").length() <= 0)){
		                jsons.add(jsonObject.getJSONArray("data").getJSONObject(i));
		              }
		            }
		            Collections.reverse(jsons);
		            returnObject.put("data", jsons);
		          } else {
		            returnObject.put("message", jsonObject.getString("message"));
		            returnObject.put("status", 0);
		          }
		          if(returnObject!=null){ //订单跟踪的处理
		            int deliveryStaus = returnObject.getInt("status");
		            if(deliveryStaus==1){
		              JSONArray jsonArray = returnObject.getJSONArray("data");
		              for( int i = 0 ; i < jsonArray.length() ; i++){
		                Map<String, Object> jsonDataMap = new HashMap<String, Object>();
		                jsonDataMap.put("DEAL_TIME", jsonArray.getJSONObject(i).getString("time"));
		                jsonDataMap.put("DEAL_MESSAGE", jsonArray.getJSONObject(i).getString("context"));
		                jsonDataMap.put("DEAL_ADMIN", deliveryMap.get("DELIVERY_NAME"));
		                list.add(jsonDataMap);
		              }
		            }
		          }
		        } catch (HttpException e) {
		          Logger.getLogger(SystemUtil.class.getName()).info("发生错误,请检查网络协议"); 
		        } catch (IOException e) {
		          Logger.getLogger(SystemUtil.class.getName()).info("发生错误,请检查网络"); 
		        } catch (JSONException e) {
		          Logger.getLogger(SystemUtil.class.getName()).info("JSON转换错误,请检查"); 
		        } catch (Exception ex) {
		          Logger.getLogger(SystemUtil.class.getName()).info("JSON转换错误,请检查"); 
		        }
		        finally {
		          if (getMethod != null) getMethod.releaseConnection();
		        }
		      }
		    }
		    return list;
		  }
	  
	  /**
	   * 根据订单类型获取作用对象
	   * @return
	   */
	  public static int getStoreOrderType(int orderType){
		  if(orderType > 3 && orderType < 9){
			  return STORE_TYPE_DC;
		  }
		  return STORE_TYPE_STORE;
	  }
	
}


