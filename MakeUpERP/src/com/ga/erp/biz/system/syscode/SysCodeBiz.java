package com.ga.erp.biz.system.syscode;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class SysCodeBiz extends ACLBiz {
  public SysCodeBiz(UserACL userACL) {
    super(userACL);
  }

    public ResultSet querySyscodeList(Long pID,boolean isAll) {
        try {   
        	return DbUtils.query("select * from SYS_CODE order by NLEVEL,SYS_CODE_ID");	
        } catch (Exception e) {
          throw new BizException(BizException.SYSTEM,"查询系统编码列表失败");
        }
    }
    
    public Map<String, Object> querySyscodeDetail(List<DbField> fieldList,Long sysCodeId) throws BizException {
      try {
    	 String sql=
    		  "select s.*, ps.SYS_CODE_NAME as P_NAME from SYS_CODE s LEFT JOIN\n" +
    		  " SYS_CODE ps on s.P_CODE = ps.SYS_CODE_ID\n" + 
    		  "   where s.SYS_CODE_ID = ?";
        return DbUtils.queryMap(fieldList, sql, sysCodeId);
      } catch (BizException e) {
          throw e;
      } catch (Exception ex) {
          ex.printStackTrace();
          throw new BizException(BizException.SYSTEM, "查询系统编码失败", ex);
      }
  }
    
    /**
     * 判断所选中是否自己的子编码
     * @param sortID  本分类
     * @param chkSortID 所选中的分类
     */
    public boolean isSelfChild(Long sysCodeId,Long chkSyscodeID) {
    	return DbUtils.queryLong("select * from SYS_CODE where SYS_CODE_ID = ?" +
    		" and ID_PATH like '%/"+sysCodeId+"/%'", chkSyscodeID) != null;
    }
    
    /**
     * 新建编码
     * @param valueMap
     * @param funcMap
     */
    public void saveNewSyscode(Map<String,Object> valueMap) {
      try {      
        DbUtils.begin();
        DbUtils.add("SYS_CODE",valueMap,null,"SYS_CODE_ID,P_CODE,SYS_CODE_NAME,SYS_CODE_VALUE,STATE,NOTE");
        GaUtil.updateTreeLevel(DbUtils.getConnection(), "SYS_CODE", GaUtil.getLastId(), valueMap.get("P_CODE"), valueMap.get("STATE"));
        DbUtils.commit();
      } catch(Exception ex) {
        throw new BizException(BizException.SYSTEM,"执行新建失败",ex);
      }
      finally {
    	  DbUtils.rollback();
      }
    }
    
    /**
     * 更新编码信息
     * @param valueMap
     * @param funcMap
     * @param pkFieldName
     */
    public void updateSyscode(Map<String,Object> valueMap) {
      try {      
        //执行查询
        if (valueMap.get("SYS_CODE_ID") == null) {
          throw new BizException("没有指定要更新的系统编码");
        }
        DbUtils.begin();
        DbUtils.update("SYS_CODE",valueMap,null,"SYS_CODE_ID,P_CODE,SYS_CODE_NAME,SYS_CODE_VALUE,STATE,NOTE", "SYS_CODE_ID");
        GaUtil.updateTreeLevel(DbUtils.getConnection(), "SYS_CODE", valueMap.get("SYS_CODE_ID"), valueMap.get("P_CODE"), valueMap.get("STATE"));
        DbUtils.commit();
      } catch (BizException e) {
        throw e;
      } catch(Exception ex) {
        throw new BizException(BizException.SYSTEM,"执行保存失败",ex);
      } finally {
        DbUtils.rollback();
      }
    }

}
