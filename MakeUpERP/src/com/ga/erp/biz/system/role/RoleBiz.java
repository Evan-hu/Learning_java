package com.ga.erp.biz.system.role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class RoleBiz extends ACLBiz {

  public RoleBiz(UserACL userACL) {
    super(userACL);
  }

  /**
   * ͨ����ɫ��ҳ����
   * @param pageData ��ҳ����
   * @param fieldList �ֶ��б�
   * @return
   */
  public PageResult queryRoleList(QueryPageData pageData,List<DbField> fieldList) {    
      String sql = "select R.*,O.USERNAME,R.ROLE_NAME NAME,R.ROLE_ID ID from ROLE R join OP O on R.CREATOR_ID = O.OP_ID";
      return BizUtil.queryListBySQL(sql, "1=1","ROLE_ID DESC", fieldList, pageData,this.userACL);
  }
  
  /**
   * ͨ����ѯ����Ա�ķ�ҳ����
   * @param pageData ��ҳ����
   * @param fieldList �ֶ��б�
   * @return
   */
  public PageResult queryOpRoleList(QueryPageData pageData,List<DbField> fieldList, Long opId) {    
      String sql = "select R.*,O.USERNAME,R.ROLE_NAME NAME,R.ROLE_ID ID from ROLE R join OP O on R.CREATOR_ID = O.OP_ID";
      return BizUtil.queryListBySQL(sql, "R.STATE =1 and R.ROLE_ID NOT IN(select ROLE_ID from OP_ROLE where OP_ID = "+opId+")","ROLE_ID DESC", fieldList, pageData,this.userACL);
  }
  
  /**
   * ��ѯ��ϸ
   * @param id
   * @param fieldList
   * @return
   */
  public Map<String, Object> queryRoleDetail(Long id,List<DbField> fieldList){
    String sql ="select R.*,O.USERNAME from ROLE R join OP O on R.CREATOR_ID = O.OP_ID where R.ROLE_ID = ?";
    return DbUtils.queryMap(fieldList, sql, id);
  }
  
  /**
   * ��ѯ������
   * @return
   */
  public ResultSet queryShopTree() {
    try {        
        return DbUtils.query("select SHOP_ID,NAME,P_SHOP_ID from shop start with shop_id =? connect by prior shop_id = P_SHOP_ID and STATE = 1 order by shop_id ASC", 1);
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM,"��ѯ������ʧ��");
    }
  }
  
  /**
   * ����½�ɫ
   * @param valueMap
   * @param funcMap
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void addRole(Map<String,Object> valueMap,Map<String,String> funcMap) {
    try {  
      if(valueMap.get("ROLE_NAME") == null || GaUtil.isNullStr(valueMap.get("ROLE_NAME") + "")){
    	  throw new BizException("�������ɫ��");
      }	
      DbUtils.begin(); 
      funcMap.put("CREATE_TIME", "now()");
      funcMap.put("CREATOR_ID", String.valueOf(this.userACL.getUserID()));
      DbUtils.add("ROLE",valueMap,funcMap,"CREATE_TIME,ROLE_NAME,STATE,CREATOR_ID,NOTE");
      
      String treeValue = (String)valueMap.get("purviewTreeUpdate");
      if(!GaUtil.isNullStr(treeValue)){
        JSONArray json = new JSONArray(treeValue);
        List<Long> rankNumList = new ArrayList<Long>();
        for( int i = 0 ; i <  json.length();i ++){
          rankNumList.add(json.getJSONObject(i).getLong("rankNum"));
        }
        Collections.sort(rankNumList,new Comparator(){
          @Override
          public int compare(Object o1, Object o2) {
            return (Long)o1 > (Long)o2 ? 0 : 1;
          }
        });
        
        List<Long> ids = null;
        List<Map<String,Object>> leafIdStrs = new ArrayList<Map<String,Object>>();//��ײ��Ȩ��Id        
        leafIdStrs = DbUtils.queryMapList(DbUtils.getConnection(),"select purview_id from PURVIEW where NLEVEL = 4");

        HashSet<Long> leafIds = new HashSet<Long>();
        for(Map<String,Object> leaf : leafIdStrs ){
          leafIds.add(((Integer)leaf.get("purview_id")).longValue());
        }
        Long roleId = GaUtil.getLastId();
        for(int i = 0 ; i <  json.length();i ++){
          for(Long id : rankNumList){
            if(json.getJSONObject(i).getLong("rankNum") == id){
              ids = new ArrayList<Long>();
              ids.add(json.getJSONObject(i).getLong("id"));
              getSubPurviewId(json.getJSONObject(i).getLong("id"), ids);
              boolean isAdd = json.getJSONObject(i).getLong("type") == 2L;
              editAccredit(ids, isAdd, roleId,leafIds);
            }
          }
        }

      }
      
      DbUtils.commit();
    } catch (BizException e) {
		throw e;
	}catch(Exception ex) {
    	ex.printStackTrace();
      throw new BizException(BizException.SYSTEM,"ִ���½���ɫʧ��",ex);
    }
    finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * �޸�     
   * @param valueMap
   */
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public void updateRole(Map<String,Object> valueMap,Map<String,String> funcMap){ try {      
      //ִ�в�ѯ
      DbUtils.begin();
      DbUtils.update("ROLE", valueMap, funcMap, "ROLE_NAME,STATE,NOTE","ROLE_ID");
      long roleId = (Long)valueMap.get("ROLE_ID");
      String treeValue = (String)valueMap.get("purviewTreeUpdate");
      if(!GaUtil.isNullStr(treeValue)){
        JSONArray json = new JSONArray(treeValue);
        List<Long> rankNumList = new ArrayList<Long>();
        for( int i = 0 ; i <  json.length();i ++){
          rankNumList.add(json.getJSONObject(i).getLong("rankNum"));
        }
        Collections.sort(rankNumList,new Comparator(){
          public int compare(Object o1, Object o2) {
            return (Long)o1 > (Long)o2 ? 0 : 1;
          }
        });
        List<Long> ids = null;
        List<Map<String,Object>> leafIdStrs = new ArrayList<Map<String,Object>>();//��ײ��Ȩ��Id
        leafIdStrs = DbUtils.queryMapList(DbUtils.getConnection(),"select purview_id from PURVIEW where NLEVEL = 4");
        HashSet<Long> leafIds = new HashSet<Long>();
        for(Map<String,Object> leaf : leafIdStrs ){
          leafIds.add(((Integer)leaf.get("purview_id")).longValue());
        }
        for(int i = 0 ; i <  json.length();i ++){
          for(Long id : rankNumList){
            if(json.getJSONObject(i).getLong("rankNum") == id){
              ids = new ArrayList<Long>();
              ids.add(json.getJSONObject(i).getLong("id"));
              getSubPurviewId(json.getJSONObject(i).getLong("id"), ids);
              boolean isAdd = json.getJSONObject(i).getLong("type") == 2L;
              editAccredit(ids, isAdd, roleId,leafIds);
            }
          }
        }

      } 
      DbUtils.commit();
    } catch(Exception ex) {
      ex.printStackTrace();
      throw new BizException(BizException.SYSTEM,"ִ���޸�ʧ��",ex);
    }
    finally {
      DbUtils.rollback();
    }}
  
  public void editAccredit(List<Long> ids,boolean isAdd,long roleId,HashSet<Long> leafIds){
    if(isAdd){
      for(Long id : ids){
        if(leafIds.contains(id)){
          Long cnt = DbUtils.queryLong("select count(*) from ACCREDIT where PURVIEW_ID = ? and ROLE_ID = ?", id,roleId);
          if(cnt < 1){
            DbUtils.update(DbUtils.getConnection(), "insert into ACCREDIT(PURVIEW_ID,ROLE_ID) values(?,?)", id,roleId);
          }
        }
      }
    } else{
      for(Long id : ids){
        if(leafIds.contains(id)){
          DbUtils.update(DbUtils.getConnection(),"delete from ACCREDIT where PURVIEW_ID = ? and ROLE_ID = ?",id,roleId);
        }
      }
    }
  }
  
  /**
   * ��ӹ���Ա��ɫ
   * @param valueMap
   * @param funcMap
   */
  public void addOpRole(String opId, String roleIds) {
    Connection conn= DbUtils.getConnection();
    try {      
      String [] roleIdArr = roleIds.split(",");
      conn.setAutoCommit(false); 
      for(String roleId : roleIdArr){
        DbUtils.update(conn, "insert into OP_ROLE( ROLE_ID, OP_ID) values(?,?)", roleId, opId);
      }
      conn.commit();
    } catch(Exception ex) {
      try {
        conn.rollback();
      } catch (Exception e) {
        throw new BizException(BizException.SYSTEM,"�ع��쳣��",ex);
      }
      throw new BizException(BizException.SYSTEM,"��ӽ�ɫʧ��",ex);
    }
  }
  
  /**
   * ��ѯ�ý�ɫ�����е�Ȩ��
   * @param id
   * @return
   */
  public List<String> queryAccredit(Long id){
    List<String> listAccredit = new ArrayList<String>();
    List<Map<String,Object>> list = DbUtils.queryMapList(DbUtils.getConnection(), "select * from accredit t where ROLE_ID = ?", id);
    if(list != null && list.size() > 0){
      for (Map<String, Object> m : list) {
        listAccredit.add(String.valueOf(m.get("PURVIEW_ID")));
      }
    }
    return listAccredit;   
  }
  
  public void getSubPurviewId(long pid,List<Long> subIds){
    List<Map<String,Object>> subPurviewIds = DbUtils.queryMapList(DbUtils.getConnection(), "select PURVIEW_ID from PURVIEW where p_id=?", pid);
    if(subPurviewIds == null || subPurviewIds.size() == 0){
      return;
    }
    for(Map<String,Object> s : subPurviewIds){
      long purviewId = ((Integer)s.get("PURVIEW_ID")).longValue(); 
      subIds.add(purviewId);
      getSubPurviewId(purviewId, subIds);
    }
  }
  
}
