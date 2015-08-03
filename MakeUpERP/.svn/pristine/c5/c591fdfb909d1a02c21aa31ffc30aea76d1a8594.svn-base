package com.ga.erp.biz.stock.shelf;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class ShelfBiz extends ACLBiz {

  public ShelfBiz(UserACL userACL) {
		super(userACL);
	}

  public ResultSet querySortList(Long pId) {
	  try {    
	    return DbUtils.query("SELECT * FROM SHELF s WHERE s.DELETE_STATE = 1 ORDER BY NLEVEL,SHELF_ID");
	  } catch (Exception e) {
		e.printStackTrace();  
	    throw new BizException(BizException.SYSTEM,"查询货架列表失败");
	  }  
  }
  
  public Map<String,Object> queryShelfDetail(List<DbField> fieldList, Long shelfId) {
    try {      
      String sql="SELECT s.*,ps.shelf_num as p_shelf_num,sk.name FROM shelf s  " +
      		"LEFT JOIN shelf ps  ON s.p_shelf_id = ps.shelf_id " +
      		" LEFT JOIN stock sk ON sk.stock_id = s.stocks_id  WHERE s.shelf_id = ? and s.DELETE_STATE = 1"; 
      return DbUtils.queryMap(fieldList, sql, shelfId);
    }catch (BizException e) {
      throw e;
    }catch(Exception ex) {
      ex.printStackTrace();
      throw new BizException(BizException.SYSTEM,"查询货架详情失败",ex);
    }
  }

  /**
   * 判断所选中分类是否自己的子分类
   * @param shelfId  本分类
   * @param chkshelfId 所选中的分类
   */
  public boolean isSelfChild(String shelfId, String chkshelfId) {
	Long cnt =  DbUtils.queryLong("select * from SHELF where DELETE_STATE = 1 AND SHELF_ID = ? and ID_PATH like '%/"+shelfId+"/%'", chkshelfId);
	return cnt != null;
  }
  
  public void chkValue(Map<String, Object> sortMap){
    StringBuffer buffer=new StringBuffer("");
    int count=1;
    if(null==sortMap.get("SHELF_NUM")||(""+sortMap.get("SHELF_NUM")).trim().length()==0){
      buffer.append("<br>"+count+"，请输入货架编号；");
      count++;
    }
    if(null == sortMap.get("P_SHELF_ID")||(""+sortMap.get("P_SHELF_ID")).trim().length()==0){
      buffer.append("<br>"+count+"，请选择上级货架编号；");
      count++;
    }
    if(null==sortMap.get("STOCKS_ID")||(""+sortMap.get("STOCKS_ID")).trim().length()==0){
        buffer.append("<br>"+count+"，请选择仓库；");
        count++;
      }
    if(0 != buffer.length()){
      throw new BizException(buffer.toString());
    }
  }
  
  public void saveShelf(Map<String,Object> shelfMap, Boolean isAdd) {
    try {      
      chkValue(shelfMap);
      DbUtils.begin();
      String updateFields = "SHELF_NUM,P_SHELF_ID,STOCKS_ID,TYPE,NOTE,STATE";
      Object shelfId = null;
      if(isAdd){
    	  if(isExist(shelfMap.get("SHELF_NUM")+"")){
    		  throw new BizException("货架编号已存在");
    	  }
    	  DbUtils.add("SHELF", shelfMap, null,updateFields);
    	  shelfId = GaUtil.getLastId();
      }else{
    	  shelfId = shelfMap.get("SHELF_ID");
    	  DbUtils.update("SHELF", shelfMap, null, updateFields, "SHELF_ID");
      }
      GaUtil.updateTreeLevel(DbUtils.getConnection(), "SHELF", shelfId, shelfMap.get("P_SHELF_ID"), shelfMap.get("STATE"));
      DbUtils.commit();
    } catch (BizException e) {
    	DbUtils.rollback();
       throw e;
    } catch(Exception ex) {
    	DbUtils.rollback();
       ex.printStackTrace();
       throw new BizException(BizException.SYSTEM, "保存失败", ex);
    }  
  }
  
  private boolean isExist(String num) {
	return DbUtils.queryLong("SELECT COUNT(*) FROM SHELF WHERE SHELF_NUM = ?", num) > 0; 
  }

public Map<String, Object> queryShelfComm(List<DbField> fieldList, Long shelfId) {
	String sql ="select s.*, c.COMMODITY_NAME, sk.NAME, sf.SHELF_NUM\n" +
			"  from STOCK_COMM s\n" + 
			"  JOIN COMMODITY c\n" + 
			"    on s.COMMODITY_ID = c.COMMODITY_ID\n" + 
			"  JOIN SHELF sf\n" + 
			"    ON s.SHELF_ID = sf.SHELF_ID\n" + 
			"  JOIN STOCK sk\n" + 
			"    ON s.STOCK_ID = sk.STOCK_ID where s.SHELF_ID=? AND sf.DELETE_STATE = 1";
	return DbUtils.queryMap(fieldList, sql , shelfId);
	
}
  
}
