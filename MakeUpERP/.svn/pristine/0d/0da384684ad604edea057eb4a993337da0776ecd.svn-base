package com.ga.click.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.click.ActionResult;
import org.apache.click.control.HiddenField;
import org.apache.click.util.Bindable;
import org.json.JSONObject;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.table.QueryTable;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;

public abstract class ListPage extends BasePage {
	
  public static int DEFAULT_PAGESIZE = 10;

  @Bindable
  protected QueryTable queryTable = null;// 显示列表结果集
  
 
  //提交的参数
  protected boolean isLookupMode = false; //是否查询选择模式
  protected Map lookupFieldMap = null; //查询选择返回字段列表    
  protected String lookupCallback = ""; //查找返回回调口子

  /**
   * 构建函数,主要用于提供默认设置 1.设置页面展示模板 2.设置查询表单的默认展示属性 3.设置数据表格的默认展示属性
   */  
  public ListPage() {
      super();
  }

  /**
   * 初始化控件\表单数据\工具栏等
   */
  public void initControl() {
    try {          
      // 处理控件
      this.queryTable = new QueryTable("queryTable",this.dbFieldList,this.pageEditMode,null);
      this.queryTable.setQueryForm();      //允许显示查询表单
      this.queryTable.setListPagination(); //允许显示分页 
      this.queryTable.setToolBar(this.toolbarList);
      this.addControl(this.queryTable.getToolBar());
      this.addControl(this.queryTable.getQueryForm());
      this.addControl(this.queryTable.getListPagination());
      //设置表格控件的所有操作同窗口模式一直
      SubmitTool.submitToWindow(this.queryTable, windowNavType, windowNavID,this.preNavInfoStr);
    } catch (BizException ex) {
        throw ex;
    } catch (Exception ex) {
        throw new BizException(BizException.SYSTEM, "初始化失败", ex);
    }
  }
  

  /**
   * 初始化表单
   */
  @Override
  public void initForm() {
    // TODO Auto-generated method stub
    //绑定数据
    this.bindForm(this.queryTable.getQueryForm()); 
    //初始化必须的参数    
    //设置默认分页值
    if (GaUtil.isNullStr(this.queryTable.getQueryForm().getFieldValue(GaConstant.FIXPARAM_PAGENO))) {
      HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGENO,1);
      this.queryTable.getQueryForm().add(field);
    }
    if (GaUtil.isNullStr(this.queryTable.getQueryForm().getFieldValue(GaConstant.FIXPARAM_PAGESIZE))) {
      HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGESIZE,DEFAULT_PAGESIZE);
      this.queryTable.getQueryForm().add(field);
    }
    if (GaUtil.isNullStr(this.queryTable.getQueryForm().getFieldValue(GaConstant.FIXPARAM_PAGE_ORDERF))) {
      HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGE_ORDERF,"");
      this.queryTable.getQueryForm().add(field);
    }
    if (GaUtil.isNullStr(this.queryTable.getQueryForm().getFieldValue(GaConstant.FIXPARAM_PAGE_ORDERT))) {
      HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGE_ORDERT,"DESC");
      this.queryTable.getQueryForm().add(field);
    }
    
    //获取lookup参数
    String lookupParam = this.queryTable.getQueryForm().getFieldValue(GaConstant.FIXPARAM_LOOKUPMODE);
    if (!GaUtil.isNullStr(lookupParam)) {
      this.isLookupMode = true;
      this.lookupFieldMap = new HashMap();
      String[] list =lookupParam.split(",");
      for (int i=0;i<list.length;i++) {     
        if (list[i].indexOf(GaConstant.FIXPARAM_LOOKUPCALLBACK) == 0) {
          this.lookupCallback =list[i]; 
        } else {
          this.lookupFieldMap.put(list[i],i);        
        }
      }
    }
    //设置rowselect事件
    String rowSelect =  this.queryTable.getQueryForm().getFieldValue(GaConstant.FIXPARAM_ROWSELECT);
    if (!GaUtil.isNullStr(rowSelect)) {
      this.queryTable.getListTable().setRowSelect(rowSelect);
    }
  }


  @Override
  public void onRender() {
      try {
          //设置相关操作模式
          if (this.isLookupMode) {  
            //查找返回模式
            this.toolbarList.clear();
            //增加查找返回操作
            ActionButton newLink = new ActionButton(this.getClass(),"lookup","确认选择","",null);
            newLink.setOnClick("javascript:doLookupSelect('"+this.lookupCallback+"')");
            newLink.setAttribute("class","icon");            
            this.regToolBar(newLink);
            //初始化行绑定数据未查询选择字段
            for(DbField field : this.dbFieldList) {
              if (this.lookupFieldMap.containsKey(field.getFieldCode(false))) {
                field.setBindRowData(true);
              } else {
                field.setBindRowData(false);
              }
            }
            //设置列表查询表单按钮的导航信息
            if (this.queryTable.getQueryForm() != null) {
              this.queryTable.getQueryForm().setQueryNavInfo("dialog,_blank", "dialog,_blank");
            }
            if (this.queryTable.getListPagination() != null) {
              this.queryTable.getListPagination().setNavInfo("dialog","_blank","dialog,_blank");
            }
          }
          // 设置数据结果集,显示字段等
          PageResult ds = this.bizClass.queryList(this.getQueryData(this.queryTable.getQueryForm()));
          queryTable.setDataSource(ds);
          super.onRender();
      } catch (BizException e) {
          throw e;
      } catch (Exception ex) {
          throw new BizException(BizException.SYSTEM, "渲染页面失败", ex);
      }
  }

  /**
   * 执行数据删除的方法
   * 
   * @param rowData
   *            当前选中行数据
   * @return
   */
   public ActionResult onDel() {
      try {
          this.onInit();
          this.bizClass.del();
          JSONObject obj = new JSONObject();
          obj.put("actionid", this.actionID);
          obj.put("success", "1");
          obj.put("message","删除成功");
         return new ActionResult(obj.toString(),ActionResult.JSON);
      } catch (BizException e) {
          return ClickUtil.createErrorResult(this.actionID,e);
      } catch (Exception ex) {
          return ClickUtil.createErrorResult(this.actionID,ex);
      }
  }

  /**
   * 设置删除操作
   * 
   * @param delFunctionName
   * @param selectInfo
   * @param isToolBar
   */
   public ActionButton setDelAction(boolean isToolBar) {
     Map<String, Object> paramMap = new HashMap<String, Object>();
     try {
         ActionButton actionButton = new ActionButton(this.getClass(),"del","删除",this.getContext().getRequest().getRequestURI(),paramMap);
         actionButton.setLinkButton("onDel");
         actionButton.setAttribute("class", "delete");
         paramMap.put(GaConstant.FIXPARAM_ROWDATA, "{row_data}");
         this.toolbarList.add(actionButton);
         return actionButton;
     } catch (BizException e) {
         throw e;
     } catch (Exception ex) {
         throw new BizException(BizException.SYSTEM, "设置新建操作失败", ex);
     }
 }

  /**
   * 设置新增操作
   * 
   * @param newPage
   * @param isToolBar
   */
  public ActionButton setNewAction(String newPageUrl,String title,int width,int height) {
      Map<String, Object> paramMap = new HashMap<String, Object>();
      try {
          paramMap.put(GaConstant.FIXPARAM_EDITMODE, String.valueOf(GaConstant.EDITMODE_NEW));
          ActionButton actionButton = new ActionButton(this.getClass(),"new","新建",newPageUrl,paramMap);
          actionButton.setAttribute("class", "add");
          this.toolbarList.add(actionButton);
          SubmitTool.submitToDialog(actionButton, "maindlg", title, width, height,this.preNavInfoStr);
          return actionButton;
      } catch (BizException e) {
          throw e;
      } catch (Exception ex) {
          throw new BizException(BizException.SYSTEM, "设置新建操作失败", ex);
      }
  }

  /**
   * 设置编辑操作
   * 
   * @param editPage
   * @param selectInfo
   * @param isToolBar
   */
  public ActionButton setEditAction(String editPageUrl,String title,int width,int height) {
      Map<String, Object> paramMap = new HashMap<String, Object>();
      try {          
          paramMap.put(GaConstant.FIXPARAM_EDITMODE, String.valueOf(GaConstant.EDITMODE_EDIT));
          ActionButton actionButton = new ActionButton(this.getClass(),"edit","编辑",editPageUrl,paramMap);
          actionButton.bindTableRowData(this.queryTable.getId());
          actionButton.setAttribute("class", "edit");
          SubmitTool.submitToDialog(actionButton, "maindlg", title, width, height,this.preNavInfoStr);
          this.toolbarList.add(actionButton);
          return actionButton;
      } catch (BizException e) {
          throw e;
      } catch (Exception ex) {
          throw new BizException(BizException.SYSTEM, "设置编辑操作失败", ex);
      }
  }
  

  /**
   * 注册工具栏按钮链接
   * 
   * @param actionName
   * @param actionFunction
   * @param targetWin
   */
  public void regToolBar(ActionButton button) {
      try {
        //记录当前页面打开方式
        if (GaUtil.isNullStr(button.getAttribute("class"))) {
          button.setAttribute("class", "icon");
        }
        this.toolbarList.add(button); 
      } catch (BizException e) {
          throw e;
      } catch (Exception ex) {
          throw new BizException(BizException.SYSTEM, "注册工具栏操作失败", ex);         
      }

  };
  
  
  /**
   * 如将本页作为查询选择窗口的初始化处理.
   * (如检测到提交的_lookup参数不为空，则进入查询返回模式,可通过此函数进行一些初始化工作
   * 主要是对显示信息的一些局部修改，如隐藏一些不必要的字段不进行显示等
   */
  public void initLookup() {  
  }
  
  @Override
  public String getTemplate() {
    // TODO Auto-generated method stub
    return  "/clicktemplate/list_page.htm";// 设置模板路径
  }

  /**
   * 初始化数据字段定义
   * 
   * @return
   */
  public abstract List<DbField> initDbField();

  /**
   * 绑定业务处理类
   * 
   * @return
   */
  public abstract IBiz initBizClass();
}
