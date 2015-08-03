package com.ga.erp.biz.system.audits;

import java.util.ArrayList;
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
import com.ga.erp.biz.ACLBiz;

public class AuditsBiz extends ACLBiz {

  public AuditsBiz(UserACL userACL) {
    super(userACL);
  }

  public static final int AUDIT_GROUPBUY = 1;  
  public static final int AUDIT_COMSUPPLIER = 2;  
  public static final int AUDIT_PERSUPPLIER = 3;  
  public static final int AUDIT_COMM = 4;  
  public static final int AUDIT_AD = 5;  
  
  public PageResult queryAuditList(QueryPageData queryPageData,List<DbField> dbFieldList, String bizCode, String bizId,String state) {
    String where = "a.RESULT_CODE=" + state;
    return BizUtil.queryListBySQL("select a.*,o.TRUENAME,ae.SYS_CODE_NAME " +
	  		"from AUDITING a JOIN OP o on a.OP_ID = o.OP_ID join AUDITING_EXAMPLE ae on ae.AUDITING_EXAMPLE_ID = a.AUDITING_EXAMPLE_ID", 
	  		where,"AUDITING_ID desc", dbFieldList, queryPageData, this.userACL);
  }

  public Map<String, Object> queryAuditDetail(Long id, List<DbField> dbFieldList) {
    return DbUtils.queryMap(dbFieldList, "select a.*,o.TRUENAME " +
	  		"from AUDITING a JOIN OP o on a.OP_ID = o.OP_ID where a.AUDITING_ID = ?", id);
  }

  public void saveAudit(Map<String, Object> valueMap) {
    try {
      Map<String, String> funcMap = new HashMap<String, String>();
      funcMap.put("CREATE_TIME", "now()");
      DbUtils.begin();
      //if((Integer)valueMap.get("RESULT_CODE")==1){//���״̬��ͨ��
      
      valueMap.put("OP_ID", userACL.getUserID());
        DbUtils.update("AUDITING", valueMap, funcMap, "CREATE_TIME,RESULT_CODE,RESULT_NOTE", "AUDITING_ID");//���ȸ���ԭ�����Ϣ
        int step = (Integer) valueMap.get("STEP");
        Map<String, Object> stepMap = DbUtils.queryMap(DbUtils.getConnection(),"select ALL_STEP from AUDITING_EXAMPLE where AUDITING_EXAMPLE_ID=?",valueMap.get("AUDITING_EXAMPLE_ID"));
        int allStep = (Integer) stepMap.get("ALL_STEP");
        String objectName =  (String)valueMap.get("OBJECT_NAME");
        Long objectId =  (Long)valueMap.get("BIZ_ID");
        String objectType = "";
        Integer resultCode = (Integer)valueMap.get("RESULT_CODE");
    	long sysCodeId =(Long)valueMap.get("SYS_CODE_ID");
        Map<String,Object>  map = DbUtils.queryMap(DbUtils.getConnection(), "select SYS_CODE_VALUE from SYS_CODE where SYS_CODE_ID = ?", sysCodeId);
    	if(map.get("SYS_CODE_VALUE") != null){
    		objectType = (String)map.get("SYS_CODE_VALUE");
    	}else{
    		throw new BizException("�Բ���,���ʧ��!(��������)");
    	}
        if(resultCode.equals(-1)){//��˲�ͨ����ֻҪ��ͨ������ı�ʵ��״̬�Ͷ�������״̬��
        	DbUtils.update("update AUDITING_EXAMPLE set STATE = 2 where AUDITING_EXAMPLE_ID = ?", valueMap.get("AUDITING_EXAMPLE_ID"));
        	DbUtils.update("update  "+ objectType +" set CHECK_STATE = -1 where " + objectType + "_ID = ?" ,objectId);
            DbUtils.execute("update AUDITING_EXAMPLE set NOW_STEP=? ,LAST_TIME=now()", step);
        }else{//ͨ��
	        if(step < allStep){ //����������һ��
	          //���²���һ�������Ϣ
	          valueMap.put("RESULT_CODE", 0);
	          valueMap.remove("OBJECT_NAME");
	          valueMap.remove("BIZ_ID");
	          valueMap.remove("BIZ_CODE");
	          valueMap.remove("SYS_CODE_ID");
	          valueMap.remove("OP_ID");
	          valueMap.remove("RESULT_NOTE");
	          valueMap.remove("TRUENAME");
	          String sql = "select ar.ROLE_ID,STEP from AUDITING_ROLE ar  where ar.STEP > ? order by  STEP limit 0,1";
	          Map<String, Object> roleMap = DbUtils.queryMap(DbUtils.getConnection(),sql, step);
	          int roleId = (Integer) roleMap.get("ROLE_ID");//��ѯ��һ����˽�ɫID
	          valueMap.put("ROLE_ID", roleId);
	          valueMap.put("STEP", roleMap.get("STEP"));
	          DbUtils.add("AUDITING", valueMap, funcMap);
	          //�������ʵ����
	        }else {//������һ��
	        	//�������״̬
	        	DbUtils.update("update AUDITING_EXAMPLE set STATE = 2 where AUDITING_EXAMPLE_ID = ?", valueMap.get("AUDITING_EXAMPLE_ID"));
	        	map = DbUtils.queryMap(DbUtils.getConnection(), "select SYS_CODE_VALUE from SYS_CODE where SYS_CODE_ID = ?", sysCodeId);
	            objectType = (String)map.get("SYS_CODE_VALUE");
	    	    auditSuccess(objectType, objectName, objectId);//���ͨ��ҵ������
	    	    DbUtils.update("update  "+ objectType +" set CHECK_STATE = 1 where " + objectType + "_ID = ?" , objectId);
	            DbUtils.execute("update AUDITING_EXAMPLE set NOW_STEP=? ,LAST_TIME=now()", allStep);
	        }
        }
        
  //    } 
      DbUtils.commit();
    } catch (BizException ex) {
    	ex.printStackTrace();
      throw ex;
    } catch (Exception e) {
    	e.printStackTrace();
      throw new BizException(BizException.SYSTEM, "�����쳣");
    }finally{
    	DbUtils.rollback();
    }
  }

  public Map<String, Object> queryAuditDetail(Long auditId) {
		return DbUtils.queryMap(DbUtils.getConnection(), "select a.*,o.TRUENAME,AE.OBJECT_NAME,AE.BIZ_ID,AE.SYS_CODE_ID from " +
				" AUDITING a left JOIN OP o on a.OP_ID = o.OP_ID join AUDITING_EXAMPLE AE on A.AUDITING_EXAMPLE_ID = AE.AUDITING_EXAMPLE_ID " +
				"where AUDITING_ID = ?", auditId);
	}
  
  public Boolean isNoRecord(Long bizCode, Long bizId) {
	   return DbUtils.queryLong("select count(*) from AUDITING where BIZ_CODE = ? and BIZ_ID = ?", bizCode, bizId) == 0;
   }
  
  public PageResult queryAudExampleList(QueryPageData pageData,List<DbField> fieldList) {
	  String sql = "select ae.* from AUDITING_EXAMPLE ae";
	  return BizUtil.queryListBySQL(sql, "", "ae.LAST_TIME desc",fieldList, pageData, this.userACL);
	}
  public Map<String,Object> queryAudExampleDetail(Long exampleId) {
	  String sql = "select ae.* from AUDITING_EXAMPLE ae where AUDITING_EXAMPLE_ID = ?";
	  return DbUtils.queryMap(DbUtils.getConnection(), sql, exampleId);
	}
  public List<Map<String,Object>> queryAuditngForExampleId(Long exampleId) {
	  return DbUtils.queryMapList(DbUtils.getConnection(), "select A.*,IFNULL(OP.TRUENAME,'����') TRUENAME from AUDITING A left join OP on A.OP_ID = OP.OP_ID  where AUDITING_EXAMPLE_ID = ?", exampleId);
	}
  /**
   * ������˳ɹ����ҵ���߼�
   * @param objectType
   * @param objectName
   * @param objectId
   * @return
   */
  public  boolean auditSuccess(String objectType,String objectName,Long objectId){
	
	  return true;
  }
  
  /***
   * �ж��Ƿ���Ҫ�����ɫ���(�Զ�������˰�ť)
   * @param roleId
   * @param tableName
   * @return
   */
  public Boolean isNeedAudit(String tableName){
    List<String> list = new ArrayList<String>();//��ǰ��ɫ����Ҫ��˵ı�
    String sql = "";
    return list.contains(tableName);
  }
  
}
