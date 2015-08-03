package com.ga.erp.biz.system.area;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class AreaBiz extends ACLBiz{
  public AreaBiz(UserACL acl) {
    super(acl);
  }
  
  public ResultSet queryAreaList(Long pID) {
    try {        
    	return DbUtils.query("SELECT * FROM AREA ORDER BY NLEVEL,AREA_ID"); 
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM,"��ѯ�б�ʧ��");
    }
  }
  
  public Map<String, Object> queryAreaDetail(List<DbField> fieldList,Long id){
    String sql ="select a.*,ar.area_name p_name from area a " +
    		"left join area ar on a.p_id = ar.area_id where a.area_id =?";
    return DbUtils.queryMap(fieldList, sql, id);
  }
  
  /**
   * �ж���ѡ���Ƿ��Լ�����
   * @param sortID  ������
   * @param chkSortID ��ѡ�еķ���
   */
  public boolean isSelfChild(Long sortId,Long chkSortId) {
	Long cnt =  DbUtils.queryLong(" select * from AREA where AREA_ID = ? " +
			"and ID_PATH like '%/"+sortId+"/%'", chkSortId);
    return cnt != null;
  }
  
  /**
   * ���µ�����Ϣ
   * @param valueMap
   * @param funcMap
   * @param pkFieldName
   */
  public void updatePurview(Map<String,Object> valueMap,Map<String,String> funcMap,String pkFieldName) {
    if (valueMap.get("AREA_ID") == null) {
      throw new BizException("û��ָ��Ҫ����ĵ���ID");
    }
    try {      
      //ִ�в�ѯ
      DbUtils.begin();
      DbUtils.update("AREA",valueMap,funcMap,"AREA_ID,P_ID,AREA_NAME,STATE,NOTE",pkFieldName);
      GaUtil.updateTreeLevel(DbUtils.getConnection(), "AREA", valueMap.get("AREA_ID"), valueMap.get("P_ID"), valueMap.get("STATE"));
      DbUtils.commit();
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ִ�б���ʧ��",ex);
    }
    finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * �½�����
   * @param valueMap
   * @param funcMap
   */
  public void saveNewArea(Map<String,Object> valueMap,Map<String,String> funcMap) {
    try {      
      //ִ�в�ѯ
      DbUtils.begin();
      funcMap.put("CREATE_TIME", "now()");
      funcMap.put("CREATOR_ID", String.valueOf(this.userACL.getUserID()));
      DbUtils.add("AREA",valueMap,funcMap,"AREA_ID,P_ID,CREATOR_ID,AREA_NAME,CREATE_TIME,STATE,NOTE");
      GaUtil.updateTreeLevel(DbUtils.getConnection(), "AREA", GaUtil.getLastId(), valueMap.get("P_ID"), valueMap.get("STATE"));
      DbUtils.commit();
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ִ���½�����ʧ�ܣ�",ex);
    }
    finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * ��ѯ�Զ���ʾ�б�
   * @param cname
   * @return
   */
  public JSONArray querySuggestList(String param) {
    List<Map<String,Object>> list;
    if (GaUtil.isNullStr(param)) {
      list = DbUtils.queryMapList(DbUtils.getConnection(),"select AREA_NAME,AREA_ID from AREA where p_id = 1 order by AREA_name desc");      
    } else {
      list = DbUtils.queryMapList(DbUtils.getConnection(),"select AREA_NAME,AREA_ID from  (select * from AREA where AREA_name like ? order by AREA_name) A where  rownum < 20","%"+param+"%");
    }    
    return GaUtil.convertToJSON(list);
  }
  
}
