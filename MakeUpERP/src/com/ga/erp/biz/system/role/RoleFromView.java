package com.ga.erp.biz.system.role;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.LookupDataSet;
import com.ga.click.control.tree.TreeControl;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.mvc.FormView;
import com.ga.click.mvc.ModelMap;
import com.ga.click.util.ClickUtil;
import com.ga.erp.biz.CustomFormat;
import com.ga.erp.biz.system.purview.PurviewBiz;

public class RoleFromView extends FormView{
  
  private  TreeControl treeControl;
  
  @SuppressWarnings("unused")
  private long roleId =0L;

  public RoleFromView(String viewID, ModelMap modelMap,Long roleId) {
    super(viewID, modelMap);
    
    this.roleId = roleId;
    // TODO Auto-generated constructor stub
  }

  @Override
  public void initField() throws Exception {
    List<DbField> dbField = new ArrayList<DbField>();
    DbField field = new DbField("ID", "ROLE_ID", GaConstant.DT_LONG);
    field.setQueryParamFormSource(GaConstant.INPUT_ROWOBJ, "ROLE_ID");
    field.setInput(GaConstant.INPUT_HIDDEN);
    field.setPK(true);
    dbField.add(field);
     
    field = new DbField("角色名称", "ROLE_NAME", GaConstant.DT_STRING);
    dbField.add(field);
    
    field = new DbField("创建人", "USERNAME", GaConstant.DT_STRING);
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbField.add(field);

    field = new DbField("创建时间", "CREATE_TIME", GaConstant.DT_DATETIME);
    field.setFormat(new CustomFormat(GaConstant.DT_DATETIME,"yyyy-MM-dd HH:mm",false));
    field.setInputMode(GaConstant.EDITMODE_NEW, GaConstant.INPUTMODE_HIDDEN);
    field.setInputMode(GaConstant.EDITMODE_EDIT, GaConstant.INPUTMODE_READONLY);
    dbField.add(field);

    
    field = new DbField("状态","STATE",GaConstant.DT_INT);
    field.setInput(GaConstant.INPUT_SELECT);
    field.setVerifyFormula("", true);
    field.setQueryOpera(GaConstant.QUERY_EQUALS, GaConstant.INPUT_SELECT,0,false);
    Map<String, Object>lookup = new LinkedHashMap<String, Object>();
    lookup.put("1", "正常");
    lookup.put("0", "无效");
    field.setLookupData(new LookupDataSet(lookup));
    dbField.add(field);
    
    
    PurviewBiz biz1 =new PurviewBiz(this.getUserACL());
    ResultSet  dataList = biz1.queryPurviewList(1L,false);
    TreeNode treeNode = ClickUtil.getTreeNode(dataList, "PURVIEW_ID", "P_ID", "PURVIEW_NAME", "PURVIEW_ID", "PURVIEW_ID,PURVIEW_NAME", 1L);
    //绑定视图数据
    treeControl = new TreeControl("purviewTree", treeNode, "", true,this.getUserACL());
    //treeControl.  
    treeControl.SetCheck(true, "");
    
    field = new DbField("对应权限", treeControl.getName(), GaConstant.DT_STRING);
    field.setTreeControl(treeControl);
    field.setInput(GaConstant.INPUT_TREE);
    dbField.add(field);
    
    field = new DbField("对应权限", "purviewTreeUpdate", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_HIDDEN);
    dbField.add(field);
    
    field = new DbField("备注", "NOTE", GaConstant.DT_STRING);
    field.setInput(GaConstant.INPUT_TEXTAREA);
    field.addInputAttributeMap("rows", "3");
    field.addInputAttributeMap("cols", "50");
    dbField.add(field);
     
    this.fieldList = dbField;
    
  }
}
