package com.ga.click.control.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.click.control.AbstractControl;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.form.ListForm;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ListLayout;
import com.ga.click.mvc.UserACL;
import com.ga.click.util.GaUtil;

public class QueryTable extends AbstractControl {

  private PageResult rs = null;
  private List<DbField> dbFieldList = null;

  private ListTable listTable = null;
  private ListPagination listPagination = null;  
  private ListForm queryForm = null;
  private ToolBar toolBar = null; 
  private Layout layout = null;
  private ActionButton selfLink = null;
  private boolean multiSelect = false;
  private boolean dispDeleRow = true;
  private String moueOverJs = "";
  private UserACL userACL = null;
  private boolean isMTableMode = false;
  
  
  public void setLayout(Layout layout) {
    this.layout = layout;
  }
  
  public Layout getLayout() {
    return this.layout;
  }
  
  
  public void setMultiSelect(boolean isMultiSelect) {
    multiSelect = isMultiSelect;
    this.listTable.setMultiSelect(isMultiSelect);
  }
  
  
  public void setSelfLink(ActionButton selfLink) {
    this.selfLink = selfLink;
  }

  /**
   * 设置是否显示删除行
   * @param dispDeleRow
   */
  public void setDispDeleRow(boolean dispDeleRow) {
    this.dispDeleRow = dispDeleRow;
    this.listTable.setDispDeleRow(dispDeleRow);
  }
  /**
   * 创建查询列表对象
   * @param tableName
   * @param dbFieldList
   */
  public QueryTable(String tableID,List<DbField> dbFieldList,UserACL acl) {
    this(tableID,dbFieldList,GaConstant.EDITMODE_DISP,acl);  
  }
  
  
  /**
   * 创建查询列表对象
   * @param tableName
   * @param dbFieldList
   */
  public QueryTable(String tableID,List<DbField> dbFieldList,int editMode,UserACL acl) {
    this.userACL = acl;
    this.setName(tableID);
    this.dbFieldList = dbFieldList;
    this.listTable = new ListTable(tableID+"List",this.dbFieldList,editMode);
    this.listTable.setMTableMode(this.isMTableMode);
    this.listTable.setMultiSelect(this.multiSelect);
    
  }
  
  /**
   * 设置行选择触发的关联按钮
   * @param button
   */
  public void setRowSelect(ActionButton button) {
    Map<String, Object> paramMap = new HashMap<String, Object>();
    try {
        button.setHidden(true);
        this.regToolBar(button);
        this.getListTable().setRowSelect(button.getId().replace(".","_"));
    } catch (BizException e) {
        throw e;
    } catch (Exception ex) {
        throw new BizException(BizException.SYSTEM, "设置编辑操作失败", ex);
    }
  }

  /**
   * 设置显示的结果记录表格数据
   * @param rs 查询结果集
   */
  public void setDataSource(PageResult rs) {
    this.rs = rs;
    this.listTable.setDataSource(rs);    
    if (this.listPagination != null) {
      this.listPagination.setPageInfo(rs.getPageParam());
    }
  }
  
  /**
   * 设置显示分页控件
   */
  public void setListPagination() {
    this.listPagination = new ListPagination();
  }
  
  public void clearListPaginate() {
    this.listPagination = null;
  }
  /**
   * 设置显示查询表单
   * @param dispQueryForm
   */
  public void setQueryForm() {
    this.queryForm = new ListForm(this.dbFieldList,this.userACL, this.name);
  }
  
  public void clearQueryForm() {
    this.queryForm = null;
  }
  
  /**
   * 使用regToolBar方法替代
   * @deprecated
   * @param actionList
   */
  public void setToolBar(List<ActionButton> actionList) {
    this.toolBar = new ToolBar(this.name+"Toolbar",actionList);
  }
  
  public void regToolBar(ActionButton button) {
    if (this.userACL == null || !this.userACL.checkAction(button.getId(),button.getTitle())) {
      return;
    }
    if (this.toolBar == null) {
      this.toolBar = new ToolBar(this.name+"Toolbar");
    }
    if (GaUtil.isNullStr(button.getAttribute("class"))) {
      button.setAttribute("class", "icon");
    }
    this.toolBar.regToolBar(button);
  }
  
  public ListTable getListTable() {
    return listTable;
  }
  
  public ListForm getQueryForm() {
    return queryForm;
  }
  
  public ToolBar getToolBar() {
    return toolBar;
  }
  
  public ListPagination getListPagination() {    
    return listPagination;
  }  
  
  
  @Override
  public void render(HtmlStringBuffer buffer) {
    if (this.layout == null) {
      //默认初始化布局控件
      this.layout = new ListLayout(this);
    } 
    this.layout.render(buffer);
    //判断是否需要悬浮事件
    String overInfo = 
      "$(\"#word_select_value_list\").mouseover(function() {$(\".listtitle-cont2\").show();}).mouseout(function(){$(\".listtitle-cont2\").hide();});";
  }


  @Override
  public String getTag() {
    // TODO Auto-generated method stub
    return "div";
  }
  
  public boolean havePage() {
    if (this.listPagination == null) return false;
    return true;
  }
  
  public boolean haveQuery() {
    if (this.queryForm == null) return false;
    return true;
  }
  
  public boolean haveToolbar() {
    if (this.toolBar == null) return false;
    return true;
  }
  
  public void setMouseOver(String in,String out) {
    this.moueOverJs = in+","+out;
    if (this.listTable !=null) {
      this.listTable.setMouseOverJS(this.moueOverJs);
    }
  }

public void setMTableMode(boolean isMTableMode) {
	this.isMTableMode = isMTableMode;
	this.listTable.setMTableMode(this.isMTableMode);
}
  
  
}
