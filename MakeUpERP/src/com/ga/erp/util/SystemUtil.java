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
  public static final long SYSCODE_NORMALSORT = 102001L;  //��ͨ��Ʒ�������
  public static final int STORE_TYPE_STORE = 1;//���ֱ�����ҵ��
  public static final int STORE_TYPE_DC = 2;//DC���ҵ��
  
  public static final int STORE_ORDER_DIRECT_APPLY = 1;//���ֱ�����뵥
  public static final int STORE_ORDER_DIRECT_SEND = 2;//���ֱ��������
  public static final int STORE_ORDER_DIRECT_RECEIPT = 3;//���ֱ��������
  public static final int STORE_ORDER_DC_APPLY  = 4;//�ŵ�ɹ����뵥
  public static final int STORE_ORDER_DC_SEND = 5;//DC������
  public static final int STORE_ORDER_DC_RECEIPT = 6;//�ŵ�ɹ��ջ���
  public static final int STORE_ORDER_DC_RETURN = 7;//�ŵ�ɹ��˻��ջ���
  public static final int STORE_ORDER_DC_RETURN_RECEIPT =8;//�ŵ�ɹ��˻��ջ�����DC�ջ���
  public static final int STORE_ORDER_DIRECT_RETURN = 9;//���ֱ���˻����뵥
  public static final int STORE_ORDER_DIRECT_RETURN_RECEIPT = 10;//���ֱ���˻��ջ���
  
  
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
      throw new BizException(BizException.SYSTEM,"��ȡϵͳ�����쳣");
    }
  }
  
  public static LookupDataSet getSettleTypeMap() {
	 try {
	     CachedRowSet rs = DbUtils.query("select SETTLEMENT_TYPE_ID,TYPE from SETTLEMENT_TYPE");
	  	 return new LookupDataSet(rs, "SETTLEMENT_TYPE_ID", "TYPE");
	   } catch (Exception ex) {
	     throw new BizException(BizException.SYSTEM,"��ȡ���������쳣");
	   }
	 }
  
  /**
   * �����Ƿ�MAP
   * @return
   */
  public static LookupDataSet getYesNoMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "��");
    lookup.put("0", "��");
    return new LookupDataSet(lookup);
  }
  
  /**
   * ����״̬����MAP
   * @return
   */
  public static LookupDataSet getStatusMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "����");
    lookup.put("0", "��Ч");
    return new LookupDataSet(lookup);
  }
  
  /**
   * վ�����Ķ�״̬
   * @return
   */
  public static LookupDataSet getMessageSendStateMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("2", "��ɾ��");
    lookup.put("1", "�Ѷ�");
    lookup.put("0", "δ��");
    return new LookupDataSet(lookup);
	 }
  
  
  /**
   * վ����type
   * @return
   */
  public static LookupDataSet getStationMsgTypeMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("3", "��Ӧ��");
    lookup.put("2", "�ŵ�");
    lookup.put("1", "��Ա");
    return new LookupDataSet(lookup);
	  }
  
  /**
   * վ����״̬
   * @return
   */
  public static LookupDataSet getStationMsgStateMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("2", "�ѷ���");
    lookup.put("1", "δ����");
    lookup.put("0", "��Ч");
    return new LookupDataSet(lookup);
	  }
  
  /**
   * �Ա�
   * @return
   */
  public static LookupDataSet getSexMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "��");
    lookup.put("0", "Ů");
    return new LookupDataSet(lookup);
	 }
  
  
  /**
   * ���ض�������map
   * @return
   */
  public static LookupDataSet getObjectTypeMap() {
    Map<String, Object> lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "�ŵ�ɹ�");
    lookup.put("2", "�ŵ��˻�");
    lookup.put("3", "�ŵ�ɹ�����");
    lookup.put("4", "������������");
    return new LookupDataSet(lookup);
  }
 
	  /**
	   * �������״̬map(List���༭״̬)
	   * @return
	   */
	  public static LookupDataSet getAuditsMap() {
	    try {
	      Map<String, Object> auditsMap = new LinkedHashMap<String, Object>();
	      auditsMap.put("-1", "��ͨ��");
	      auditsMap.put("0", "�����");
	      auditsMap.put("1", "ͨ��");
	      return new LookupDataSet(auditsMap);
	    } catch (Exception ex) {
	      throw new BizException(BizException.SYSTEM,"��ȡ���״̬�쳣");
	    }  
	  }
	  
	  /**
	   * �������״̬map(List���༭״̬)
	   * @return
	   */
	  public static LookupDataSet getOrderTypeMap() {
	    try {
	      Map<String, Object> auditsMap = new LinkedHashMap<String, Object>();
	      auditsMap.put("1", "��������");
	      auditsMap.put("2", "�ݹҶ���");
	      
	      return new LookupDataSet(auditsMap);
	    } catch (Exception ex) {
	      throw new BizException(BizException.SYSTEM,"��ȡ���������쳣");
	    }  
	  }
	  
	  public static List<DbField> getNormalFieldList(){
		  
		  List<DbField> dbFields = new ArrayList<DbField>(); 
		  
		  DbField field = new DbField("������","TRUENAME",GaConstant.DT_STRING);
		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		  field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		  dbFields.add(field);

		  field = new DbField("����ʱ��","CREATE_TIME",GaConstant.DT_DATETIME);
		  field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		  field.setInputMode(GaConstant.EDITMODE_EDIT,GaConstant.INPUTMODE_READONLY);
		  dbFields.add(field);

		  field = new DbField("״̬","STATE",GaConstant.DT_INT);
		  field.setInput(GaConstant.INPUT_SELECT);
		  field.setLookupData(SystemUtil.getStatusMap());
		  field.setVerifyFormula("", true);
		  field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
		  field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
		  dbFields.add(field);
		  
		  field = new DbField("��ע", "NOTE", GaConstant.DT_STRING);
		  field.setInput(GaConstant.INPUT_TEXTAREA);
		  field.addInputAttributeMap("rows", "3");
		  field.addInputAttributeMap("cols", "25");
		  dbFields.add(field);
		  
		  return dbFields;
	  }
	  
	  public static List<DbField> getNormalListFieldList(String aliasCode, int beginOrder){
	    
	    List<DbField> dbFields = new ArrayList<DbField>(); 
	    
	    DbField field = new DbField("������","TRUENAME",GaConstant.DT_STRING);
	    field.setQueryOpera(GaConstant.QUERY_LIKE, beginOrder, true);
	    field.setInputCustomStyle("width:80px;");
	    dbFields.add(field);
	  
	    field = new DbField("����ʱ��","CREATE_TIME",GaConstant.DT_DATETIME);
	    field.setAliasCode(aliasCode);
	    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm:ss",false));
	    field.setQueryOpera(GaConstant.QUERY_BETWEEN,GaConstant.INPUT_DATETIME, beginOrder + 1, true);
	    dbFields.add(field);
	    
	    field = new DbField("״̬","STATE",GaConstant.DT_INT);
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
	   * ��¼ҵ����־,��BIZ�����
	   * @param userAcl �û���Ϣ
	   * @param bizCode ҵ�����(��Ʒ/����/��)
	   * @param actionName ִ�ж���
	   * @param target Ŀ��ID����(Long���� List���� String[]����)
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
	   * ������;״̬��ѯ
	   * @param deliveryMap
	   * @return
	   */
	  public static List<Map<String,Object>> resolveDeliveryRecord(Map<String,Object> deliveryMap){
		    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		    if(deliveryMap != null){
		      if("1".equals(String.valueOf(deliveryMap.get("delivery_org_id")))){
		        Map<String, Object> jsonDataMap = new HashMap<String, Object>();
		        jsonDataMap.put("DEAL_MESSAGE", "����");
		        jsonDataMap.put("DEAL_ADMIN", "����");
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
		          if(returnObject!=null){ //�������ٵĴ���
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
		          Logger.getLogger(SystemUtil.class.getName()).info("��������,��������Э��"); 
		        } catch (IOException e) {
		          Logger.getLogger(SystemUtil.class.getName()).info("��������,��������"); 
		        } catch (JSONException e) {
		          Logger.getLogger(SystemUtil.class.getName()).info("JSONת������,����"); 
		        } catch (Exception ex) {
		          Logger.getLogger(SystemUtil.class.getName()).info("JSONת������,����"); 
		        }
		        finally {
		          if (getMethod != null) getMethod.releaseConnection();
		        }
		      }
		    }
		    return list;
		  }
	  
	  /**
	   * ���ݶ������ͻ�ȡ���ö���
	   * @return
	   */
	  public static int getStoreOrderType(int orderType){
		  if(orderType > 3 && orderType < 9){
			  return STORE_TYPE_DC;
		  }
		  return STORE_TYPE_STORE;
	  }
	
}


