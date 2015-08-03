package com.ga.erp.biz.content.stationmsg;

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

public class StationMsgBiz extends ACLBiz {

  public StationMsgBiz(UserACL userACL) {
    super(userACL);
  }

  private String publicSql = "select s.*,o.TRUENAME from STATION_MSG s JOIN OP o on s.CREATOR_ID = o.OP_ID";
//  private final String memMsg = "1";    //会员站内信
//  private final String storeMsg = "2"; //门店站内信
//  private final String supplierMsg = "3"; //供应商站内信
  public PageResult queryMsgList(QueryPageData pageData,List<DbField> fieldList, String type) {
    if(GaUtil.isNullStr(type)){
      type = "1";
    }
    if(this.userACL.isAllOp()) {
      String sql = "SELECT sm.*,o.TRUENAME FROM STATION_MSG sm JOIN OP o ON sm.CREATOR_ID = o.OP_ID WHERE sm.OBJECT_TYPE = " + type;
      return BizUtil.queryListBySQL(sql, "", "sm.STATION_MSG_ID desc", fieldList, pageData, this.userACL);
    }else{
      String ids = "";
      if(this.userACL.isStoreOp()) {
        ids = this.userACL.getStoreID();
      }else {
        ids = this.userACL.getSupplierID() + "";
      }
      String sql = "SELECT s.*,o.TRUENAME FROM MESSAGE_SEND ms  JOIN STATION_MSG s ON s.STATION_MSG_ID = ms.STATION_MSG_ID  JOIN OP o ON o.OP_ID = s.CREATOR_ID  WHERE ms.OBJECT_TYPE = "+type+" AND ms.OBJECT_ID IN ("+ids+")";
      return BizUtil.queryListBySQL(sql, "", "s.STATION_MSG_ID desc", fieldList, pageData, this.userACL);
    }
  }
  
  public PageResult queryMsgSendList(QueryPageData pageData,Map<String, Object> valueMap, List<DbField> fieldList, Long msgId) {
	  String sql1 = "SELECT TYPE FROM STATION_MSG SM  WHERE STATION_MSG_ID = ?";
	  Map<String, Object> map = DbUtils.queryMap(DbUtils.getConnection(), sql1, msgId);
	  
	  Integer type = (Integer) map.get("TYPE");
	  String sql = "";
	  String whereSql = "MS.STATION_MSG_ID = " + msgId;
	  if(1 == type) {
		  sql += "SELECT MS.*, T.TRUENAME OBJECT_NAME, SM.TITLE  FROM MESSAGE_SEND MS  JOIN MEMBER T ON T.MEMBER_ID = MS.OBJECT_ID JOIN STATION_MSG SM ON SM.STATION_MSG_ID = MS.STATION_MSG_ID";
		  if(null != valueMap && !"".equals(valueMap.get("OBJECT_NAME"))){
			  whereSql += " AND T.TRUENAME like '" + valueMap.get("OBJECT_NAME") + "' ";
		  }
	  }else if(2 == type) {
		  sql += "SELECT MS.*, T.STORE_NAME OBJECT_NAME, SM.TITLE FROM MESSAGE_SEND MS JOIN STORE T ON T.STORE_ID = MS.OBJECT_ID JOIN STATION_MSG SM ON SM.STATION_MSG_ID = MS.STATION_MSG_ID";
		  if(null != valueMap && !"".equals(valueMap.get("OBJECT_NAME"))){
			  whereSql += " AND T.STORE_NAME like '" + valueMap.get("OBJECT_NAME") + "' ";
		  }
	  }else if(3 == type) {
	    whereSql += " AND S.DELETE_STATE = 1 ";
		  sql += "SELECT MS.*, S.SUPPLIER_NAME OBJECT_NAME, SM.TITLE  FROM MESSAGE_SEND MS JOIN SUPPLIER S ON S.SUPPLIER_ID = MS.OBJECT_ID JOIN STATION_MSG SM ON SM.STATION_MSG_ID = MS.STATION_MSG_ID";
		  if(null != valueMap && !"".equals(valueMap.get("OBJECT_NAME"))){
			  whereSql += " AND S.SUPPLIER_NAME like '" + valueMap.get("OBJECT_NAME") + "' ";
		  }
	  }
	  return BizUtil.queryListBySQL(sql, whereSql, "MS.MESSAGE_SEND_ID desc", fieldList, pageData, this.userACL);
  }
  
  public Map<String, Object> queryMsgDetail(Long id) {
	  return DbUtils.queryMap(DbUtils.getConnection(), publicSql + " where s.STATION_MSG_ID = ?", id);
  }
  
  public void saveStationMsg(Map<String, Object> valuesMap) {
	  
		String type = valuesMap.get("TYPE") + "";
		if(!GaUtil.isNullStr(type) && "1".equals(type)){
			boolean oa = GaUtil.isNullStr(valuesMap.get("OA") + "");
			boolean sex = GaUtil.isNullStr(valuesMap.get("SEX") + "");
			boolean age = GaUtil.isNullStr(valuesMap.get("AGE") + "");
			String memberSql = "SELECT MEMBER_ID FROM MEMBER M WHERE 1 = 1 ";
			String oaStr = " AND";
			String note = "发送群体对象：";
			
			if((!oa) && "1".equals(valuesMap.get("OA") + "")) {
				oaStr = " OR";
				note += "[条件或]";
			}else {
				note += "[条件与]";
			}
			if(!sex) {
				memberSql += oaStr + " SEX = " + valuesMap.get("SEX");
			}
			note += "1".equals(valuesMap.get("SEX") + "") ? "[性别：男]" : "[性别：女]";
			
			if(!age) {
				if("1".equals(valuesMap.get("AGE") +"")) {
					memberSql += oaStr + "   now() - birth <= 630720000000";
					note += "[年龄小于20岁]";
				}else if("2".equals(valuesMap.get("AGE")  + "")) {
					note += "[年龄大于20小于30岁]";
					memberSql += oaStr + " now() - birth  between 630720000000 and 946080000000";
				}else if("3".equals(valuesMap.get("AGE")  + "")){
					note += "[年龄大于30小于50岁]";
					memberSql += oaStr + " now() - birth  between 946080000000 and 1576800000000";
				}else if("4".equals(valuesMap.get("AGE")  + "")){
					note += "[年龄大于50岁]";
					memberSql += oaStr + " now() - birth > 1576800000000";
				}
			}
			sendMsg(type, memberSql, note, valuesMap);
			  
		}else if(!GaUtil.isNullStr(type) && "2".equals(type)){
			boolean oa = GaUtil.isNullStr(valuesMap.get("OA") + "");
			boolean area = GaUtil.isNullStr(valuesMap.get("AREA_ID") + "");
			String note = "发送群体对象：";
			
			String sql = "SELECT STORE_ID FROM STORE , AREA WHERE 1 = 1 ";
			String oaStr = "AND";
			if(!oa && "1".equals(valuesMap.get("OA") + "")) {
				oaStr = " OR";
				note += "[条件或]";
			}else {
				note += "[条件与]";
			}
			if(!area) {
				sql += oaStr + " ID_PATH like '%/"+valuesMap.get("AREA_ID")+"/%' ";
				note += "[地区："+ valuesMap.get("AREA_NAME")+ "]";
			}
			sendMsg(type, sql, note, valuesMap);
			
		}else if(!GaUtil.isNullStr(type) && "3".equals(type)) {

			boolean oa = GaUtil.isNullStr(valuesMap.get("OA") + "");
			boolean area = GaUtil.isNullStr(valuesMap.get("AREA_ID") + "");
			String sql = "SELECT SUPPLIER_ID FROM SUPPLIER , AREA WHERE 1 = 1 AND DELETE_STATE = 1 ";
			String note = "发送群体对象：";
			String oaStr = " AND";
			if(!oa && "1".equals(valuesMap.get("OA") + "")) {
				oaStr = " OR";
				note += "[条件或]";
			}else {
				note += "[条件与]";
			}
			if(!area) {
				sql += oaStr + " ID_PATH like '%/"+valuesMap.get("AREA_ID")+"/%' ";
				note += "[地区："+ valuesMap.get("AREA_NAME")+ "]";
			}
			sendMsg(type, sql, note, valuesMap);
		}
  }
  
  private void sendMsg(String type ,String sql, String note, Map<String, Object> valuesMap) {
		
	  List<Map<String,Object>> list = DbUtils.queryMapList(DbUtils.getConnection(), sql);
	  if(0 == list.size()) {
		  throw new BizException("发送对象为空，请重新筛选条件！");
	  }else {
		  try {
			  DbUtils.begin();
			  Map<String, String> funcMap = new HashMap<String, String>();
			  funcMap.put("CREATE_TIME", "NOW()");
			  valuesMap.put("CREATOR_ID", this.userACL.getUserID());
			  valuesMap.put("STATE", 2);
			  valuesMap.put("NOTE", note);
			  DbUtils.add("STATION_MSG", valuesMap, funcMap, "TYPE,TITLE,CREATE_TIME,STATE,CONTENT,CREATOR_ID,NOTE");
			  
			  Map<String, Object> valueMap = new HashMap<String, Object>();
			  valueMap.put("STATION_MSG_ID", GaUtil.getLastId());
			  valueMap.put("OBJECT_TYPE", type);
			  valueMap.put("STATE", 2);
			  for (Map<String, Object> obj : list) {
				  valueMap.put("OBJECT_ID", obj.get(obj.keySet().iterator().next()));
				  DbUtils.add("MESSAGE_SEND", valueMap, null);
			  }
			  DbUtils.commit();
		} catch (Exception e) {
			e.printStackTrace();
			DbUtils.rollback();
		}
	  }
  }
}