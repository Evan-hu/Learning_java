package com.ga.click.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ga.click.control.LookupDataSet;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;

public class UserACL {

  private static String ADMIN_USERNAME = "superadmin";
  private  Map<String,Object> userInfo;
  private boolean isExportAction;
  private Long seleMenuID = 0L;
  
  public Long getSeleMenuID() {
	return seleMenuID;
  }

private Map<Long,Set<String>> actionMap;
  private TreeNode myRootNode;

  public UserACL(Map<String,Object> userInfo) {
    this.userInfo = userInfo;
  }
  
  /**
   * 获取当前登录用户ID
   * @return
   */
  public Long getUserID() {
    Integer id =(Integer) this.userInfo.get("OP_ID");
    return Long.valueOf(id);
  }
  
  public List<String> getRoleID() {
    List<String> roleId = (List)this.userInfo.get("ROLE_ID");
    roleId = new ArrayList<String>();
    roleId.add("");
    return roleId;
  }
  public String getRoleIDStr() {
	    String ids = "";
	    if(getRoleID() != null){
		    for(String  id : getRoleID()){
		    	ids += id + ",";
		    }
		    if(ids.length() > 0){
		    	ids = ids.substring(0,ids.length());
		    }
	    }
	    return ids;
  }
  
  public Long getType(){
	 Integer id =(Integer) this.userInfo.get("TYPE");
	 return id == null ? null : Long.valueOf(id); 
  }
  
  public String getUserName() {
    return (String)this.userInfo.get("USERNAME");
  }
  
  public String getTrueName() {
    return (String)this.userInfo.get("TRUENAME");
  }
  
  public Integer getDiscRatio() {
    Integer radio = (Integer)this.userInfo.get("DISCOUNT_RATIO");
    if(radio == null){
      return 1;
    }
    return radio;
  }
  
  /**
   * 返回对应店铺ID
   * @return
   */
  public String getStoreID() {
    if(userInfo.get("STORE_ID") == null){
      return "";
    }
    return userInfo.get("STORE_ID") + "";
  }
  
  public LookupDataSet getStoreName() {
    Map<String, Object> optionMap = new LinkedHashMap<String, Object>();
    if(userInfo.get("STORE_NAME") == null){
      return new LookupDataSet(optionMap);
    }
    optionMap = (LinkedHashMap)this.userInfo.get("STORE_NAME");
    return new LookupDataSet(optionMap);
  }
  
  public Long getSupplierID() {
    Integer id =(Integer) this.userInfo.get("SUPPLIER_ID");
    return id == null ? 0L : Long.valueOf(id);
  }
  
  public String getSupplierName() {
    if(userInfo.get("SUPPLIER_NAME") == null){
      return "";
    }
    return userInfo.get("SUPPLIER_NAME") + "";
  }
  
  public boolean checkUserAction (UnitPage page,String actionID) {
    return true;
  }
  
  /**
   * 获取登录用户信息
   * @return
   */
    public Map<String,Object> getUserInfo() {
      return this.userInfo;
    }
 
	public void setExportAction(boolean isExportAction) {
      this.isExportAction = isExportAction;
    }
	
	public void setMenuID(Long menuID) {
	  this.seleMenuID = menuID;
	}
	
  public boolean isExportAction() {
	  return isExportAction;
	}
  
  public boolean isStoreOp() { 
    return getType() == 2L;
  }
  
  public boolean isSupplierOp() {
    return getType() == 3L;
  }
  
  public boolean isAllOp() {
    return getStoreID().equals("") && getSupplierID() == 0L;
  }

  /**
   * 加载用户权限
   * @param userID 用户ID
   * @return
   */
  public void loadUserPurview(Long userID) {
	    try {
	        //初始化按钮列表
	        this.actionMap = new HashMap<Long,Set<String>>();
	        String sql = "SELECT C.* FROM OP_ROLE A,ACCREDIT B,PURVIEW C WHERE A.ROLE_ID = B.ROLE_ID AND B.PURVIEW_ID = C.PURVIEW_ID AND A.OP_ID = ? AND C.TYPE = 2 and c.STATE = 1";
	        List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
	        
	        if (!this.getUserName().equals(ADMIN_USERNAME)) {
	          dataList =  DbUtils.queryMapList(DbUtils.getConnection(), sql, userID);
	        }
	        
	        for (Map<String,Object> data : dataList) {
	      	Integer pID = (Integer)data.get("P_ID");
	          String mark = (String)data.get("MARK");
	          if (pID == null || mark == null) { //不合法定义,跳过
	            continue;
	          }
	          Long menuID = pID.longValue();
	          Set<String> actionSet = this.actionMap.get(menuID);
	          if (actionSet == null) {
	            actionSet = new HashSet<String>();
	            this.actionMap.put(menuID, actionSet);
	          }
	          actionSet.add(mark);
	        }
	        
	        //初始化菜单对象
	        if (this.getUserName().equals(ADMIN_USERNAME)) {
	      	  sql = "SELECT * FROM PURVIEW WHERE TYPE = 1 AND STATE=1 order by RANK_NUM,PURVIEW_ID ";
	          dataList =  DbUtils.queryMapList(DbUtils.getConnection(), sql);
	        } else {
	      	  sql = "select * frOM PURVIEW PU join ("+
	      	    		 " select PURVIEW_ID from PURVIEW  P join ("+
	      	    				"  select ID_PATH from PURVIEW where PURVIEW_ID in (SELECT C.PURVIEW_ID"+
	      	    				                       "  FROM OP_ROLE A, ACCREDIT B, PURVIEW C, ROLE D"+
	      	    				                      "  WHERE A.ROLE_ID = B.ROLE_ID"+
	      	    				                       "   AND B.PURVIEW_ID = C.PURVIEW_ID"+
	      	    				                       "   AND D.ROLE_ID = A.ROLE_ID"+
	      	    				                       "   AND A.OP_ID = ?"+
	      	    				                       "   AND D.STATE = 1"+
	      	    				                       "   and C.STATE = 1)  order by NLEVEL )"+
	      	    				 " D on D.ID_PATH  like CONCAT('%/',P.PURVIEW_ID,'/%') "+
	      	    				 " group by P.PURVIEW_ID"+
	      	    				 " ) a on a.PURVIEW_ID = PU.PURVIEW_ID where TYPE = 1";
	          dataList =  DbUtils.queryMapList(DbUtils.getConnection(), sql, userID);
	        }
	        
	        Map<Long,TreeNode> nodeMap = new HashMap<Long,TreeNode>();
	        Map<Long,List<Long>> childNodeMap = new HashMap<Long,List<Long>>();
	        
	        for (Map<String,Object> data : dataList) {
	      	  Integer pID = (Integer)data.get("P_ID");
	      	  Integer nodeID = (Integer)data.get("PURVIEW_ID");
	          String nodeName = (String)data.get("PURVIEW_NAME");
	          String nodeUrl  = (String) data.get("FORM_URL");       
	          TreeNode node = new TreeNode(nodeID.longValue(),nodeName,nodeUrl);
	          nodeMap.put(nodeID.longValue(), node);
	          if (pID == null) {
	            myRootNode = node;
	            continue;
	          }
	          List<Long> nodeIDList = childNodeMap.get(pID.longValue());
	          if (nodeIDList == null) {
	            nodeIDList = new ArrayList<Long>();
	            childNodeMap.put(pID.longValue(), nodeIDList);
	          }
	          if (!nodeIDList.contains(nodeID.longValue())) {
	            nodeIDList.add(nodeID.longValue());
	          }
	        }
	        //组织treenode关系
	        for (Long key : childNodeMap.keySet()) {
	          List<Long> childIDList = childNodeMap.get(key);
	          TreeNode pNode = nodeMap.get(key);
	          if (pNode == null) {
	            System.out.println("不合法的父节点");
	            continue;
	          }
	          for (Long childID : childIDList) {
	            TreeNode childNode = nodeMap.get(childID);
	            pNode.addChildNode(childNode);
	          }
	        }
	      } catch(Exception e) {
	      	e.printStackTrace();
	       throw new BizException("初始化用户权限时错误"); 
	      }
	    }
  
  /**
   * 获取菜单根节点
   * @return
   */
  public TreeNode getMyRootNode() {
    return myRootNode;
  }
  
  /**
   * 校验点击的按钮
   * @param nodeID
   * @param actionID
   * @return
   */
  public boolean checkAction(String actionID,String actionTitle) {
    if (this.getUserName().equals(ADMIN_USERNAME)){
//    	addButton(this.seleMenuID, "[" + actionTitle + "]", actionID);
    	return true;
    } 
    Set actionList = this.actionMap.get(this.seleMenuID);
    if (actionList == null) return false;
    return (actionList.contains(actionID));
  }
  

  /**
   * 添加按钮   不存在就添加
   * @param pId
   * @param buttonName
   * @param mark
   */
  private void addButton(Long pId, String buttonName, String mark){
    String sql = "select 1 from PURVIEW where P_ID = ? and MARK = ?";
    if(DbUtils.queryLong(sql, pId, mark) != null){
      return;
    }
    try{
	    DbUtils.begin();
	    sql = "insert into PURVIEW( P_ID, PURVIEW_NAME, MARK, TYPE, STATE, RANK_NUM, CREATE_TIME)  values ( ?, ?, ?, 2, 1, 0, now())";
	    DbUtils.update(DbUtils.getConnection(),sql, pId, buttonName, mark);
	    GaUtil.updateTreeLevel(DbUtils.getConnection(), "PURVIEW", GaUtil.getLastId(), pId, 1);
	    DbUtils.commit();
    }catch(Exception e){
    	e.printStackTrace();
    	DbUtils.rollback();
    }
  }
}
