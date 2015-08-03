package com.ga.erp.biz.stock.inventorybatch;

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

public class BatchBiz extends ACLBiz {

	public BatchBiz(UserACL userACL) {
		super(userACL);
	}
	
	public PageResult queryInventoryBatchList(QueryPageData queryPageData,List<DbField> dbFieldList,int type) {
		String sql ="SELECT i.*,o.TRUENAME FROM INVENTORY_BATCH i JOIN OP o ON o.OP_ID=i.OP_ID ";
		String where = " i.DELETE_STATE = 1 and I.TYPE = " + type;
		if(GaUtil.isValidStr(userACL.getStoreID())){
		  where += " AND i.TYPE = 2 AND i.TYPE_ID in (" + userACL.getStoreID() + ")";
		}
		return BizUtil.queryListBySQL(sql, where, "i.INVENTORY_BATCH_ID desc",
				dbFieldList, queryPageData, this.userACL);
	}
	
	public Map<String, Object> queryInventoryBatchDetail(Long id) {
		String sql ="SELECT i.*,o.TRUENAME FROM INVENTORY_BATCH i JOIN OP o ON o.OP_ID=i.OP_ID where i.INVENTORY_BATCH_ID=? AND i.DELETE_STATE = 1";
		Map<String, Object> map = DbUtils.queryMap(DbUtils.getConnection(),sql, id);
		String sqlstore = "select * FROM STORE where STORE_ID = ?";
		String sqlstock = "select * FROM STOCK where STOCK_ID = ?";
		
		if("2".equals(map.get("TYPE")+"")){
			Map<String, Object> resultMap=DbUtils.queryMap(DbUtils.getConnection(),sqlstore, map.get("TYPE_ID"));
			map.put("TYPE_NAME", (resultMap == null || resultMap.get("STORE_NAME") == null) ? "未知" : resultMap.get("STORE_NAME"));
		}else{
			Map<String, Object> mapStore=DbUtils.queryMap(DbUtils.getConnection(),sqlstock, map.get("TYPE_ID"));
			map.put("TYPE_NAME", (mapStore == null || mapStore.get("NAME") == null)  ? "未知" : mapStore.get("NAME"));
		}	
		//如果为品牌或者分类 那么具有品牌或分类名
		int scope=(Integer)map.get("SCOPE");
		if(scope==3||scope==4){
		  sql="select OBJECT_NAME from inventory_scope where INVENTORY_BATCH_ID=? ";
		  List<Map<String, Object>> resultMap= DbUtils.queryMapList(DbUtils.getConnection(), sql, id);
		  String names ="";
		  for(Map<String, Object> rmap:resultMap){
		   names += rmap.get("OBJECT_NAME")+";";
		  }
		  map.put("OBJECT_NAMES", names);
		}
		return map;
	}
	

	public void saveInventoryBatch(Map<String, Object> valuesMap, boolean isAdd) {
		
		checkMap(valuesMap);
		DbUtils.begin();
		try{
			String updateFile = "INVENTORY_BATCH_NUM,TYPE,SCOPE,TYPE_ID,OP_ID,CREATE_TIME,STATE,NOTE,TYPE_NAME";
			Map<String, String> funcMap = new HashMap<String, String>();
			funcMap.put("CREATE_TIME", "now()");
			valuesMap.put("OP_ID", this.userACL.getUserID());
			valuesMap.put("STATE", 1);
			DbUtils.add("INVENTORY_BATCH", valuesMap, funcMap, updateFile);

			
			Map<String, Object>  idMap=DbUtils.queryMap(DbUtils.getConnection(),"SELECT INVENTORY_BATCH_ID FROM INVENTORY_BATCH WHERE INVENTORY_BATCH_NUM=?",
					valuesMap.get("INVENTORY_BATCH_NUM"));
			if("3".equals(valuesMap.get("SCOPE")+"")||"4".equals(valuesMap.get("SCOPE")+"")){
			/**
			 * 如果是品牌或者分类时
			 * 盘点范围表的更新
			 * 
			 */
			String ids=valuesMap.get("OBJECT_IDS")+"";
			String names=valuesMap.get("OBJECT_NAMES")+"";
			String[] id=ids.split(",");
			String[]  name=names.split(",");
			valuesMap.put("INVENTORY_BATCH_ID",idMap.get("INVENTORY_BATCH_ID")+"");
			
			for(int i=0;i<id.length;i++){
			  
			Map<String, Object> scopeMap=new HashMap<String, Object>();
			scopeMap.put("INVENTORY_BATCH_ID", valuesMap.get("INVENTORY_BATCH_ID"));
			   scopeMap.put("OBJECT_ID",  id[i]);
			   scopeMap.put("OBJECT_NAME", name[i]);
			DbUtils.add("INVENTORY_SCOPE", scopeMap, null, "INVENTORY_BATCH_ID,OBJECT_ID,OBJECT_NAME");
			}
			}
		
		DbUtils.commit();
		}catch (Exception e) {
			DbUtils.rollback();
			e.printStackTrace();
			throw new BizException("数据库繁忙请稍后再试");
		}finally{
			DbUtils.close();
		}
	}

	private void checkMap(Map<String, Object> valuesMap) {
		 StringBuffer buffer = new StringBuffer("");
		    int count = 1;
		if (GaUtil.isNullStr((valuesMap.get("INVENTORY_BATCH_NUM") + ""))) {
				buffer.append("<br>"+count+"，请填写盘点批次；");
		        count++;
			}
		if (GaUtil.isNullStr((valuesMap.get("TYPE_ID") + ""))) {
			buffer.append("<br>"+count+"，请选择盘点对象；");
	        count++;
		}
	//当为品牌分类盘点时 进行判断是否添加具分类或品牌对象
	  if (("3".equals(valuesMap.get("SCOPE")+"")||"4".equals(valuesMap.get("SCOPE")+""))&&GaUtil.isNullStr(valuesMap.get("OBJECT_IDS")+"")) {
      buffer.append("<br>"+count+"，请选择盘点品牌或分类；");
          count++;
    }
    
		if(buffer.length() > 0){
		       throw new BizException(buffer.toString());
		}
	}


}
