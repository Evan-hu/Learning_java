package com.ga.click.page;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.click.ActionResult;
import org.apache.click.Control;
import org.apache.click.control.AbstractControl;
import org.apache.click.control.HiddenField;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.form.DataForm;
import com.ga.click.control.table.QueryTable;
import com.ga.click.control.tree.TreeControl;
import com.ga.click.control.tree.TreeNode;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;

public abstract class MultiDivPage extends MultiBasePage{
	
  protected Map<String,Control> divMap = new HashMap<String,Control>();
  protected List<ActionButton> pageActionList = new ArrayList<ActionButton>();
  
  //提交的参数
  protected boolean isLookupMode = false; //是否查询选择模式
  protected Set<String> lookupFieldSet= null; //查询选择返回字段列表    
  protected String lookupCallback = ""; //查找返回回调口子
  protected String lookupControlID = ""; //查找带回的控件ID
  
  public MultiDivPage() {
    super();
    this.initLookup();
  }
  /**
   * 设置指定DIV的控件
   * @param divID divid
   * @param control 控件对象
   */
  public void setDivControl(String divID,AbstractControl control) {
    if (control == null) {
      return;
    } else {
      //实现控件的碎片化处理
      SubmitTool.submitToDiv(control, divID,this.preNavInfoStr);
      this.divMap.put(divID,control);
    }
    divMap.put(divID, control);
  }
  
  /**
   * 创建树控件
   * @param treeID 树ID
   * @param url 节点连接
   * @param isExpand 是否展开
   * @param queryMethod 树控件查询方法
   * @param queryCheckValueMethod 树控件选中值查询方法
   * @param addMap 附加参数
   * @param istreeCheck 是否为treeCheck
   * @param checkFun 绑定前台JS点击事件
   * @return
   */
  public TreeControl loadTreeControl(String treeID,String url,boolean isExpand,String queryMethod,String queryCheckValueMethod,String bindFields,Map<String,Object> addMap,boolean isTreeCheck,String checkFun) {
      try {
          IBiz biz = this.getBizClass(treeID);
          if (biz == null) {
            throw new BizException(BizException.SYSTEM,"业务类不合法");
          } else {
            biz.setRowData(this.rowDataObj);
          }
          TreeControl treeControl = null;
          Method execMethod = biz.getClass().getMethod(queryMethod, new java.lang.Class[]{String.class});
          Object getList;
          if (this.isLookupMode && this.lookupControlID.equals(treeID)) {
            String fields = "";
            for(String field : this.lookupFieldSet) {
              fields = fields + ","+ field ;
            }
            if (fields.indexOf(",") == 0) fields = fields.substring(1);
            getList = execMethod.invoke(biz,fields);
          } else {
            getList = execMethod.invoke(biz,bindFields);
          }
          Object checkValues = null;
          if (!GaUtil.isNullStr(queryCheckValueMethod)){
            execMethod = biz.getClass().getMethod(queryCheckValueMethod, new java.lang.Class[]{});   
            checkValues = execMethod.invoke(biz);
          }
          if (getList != null) {
            treeControl = new TreeControl(treeID, (TreeNode) getList,url,isExpand,null);
            treeControl.SetCheck(isTreeCheck, checkFun);
            if(checkValues != null){
                treeControl.setCheckValues((List<String>) checkValues);
            }
          } 
          return treeControl;
        }
        catch(BizException e) {
          throw e;
        }
        catch(Exception ex) {
          ex.printStackTrace();
          throw new BizException(BizException.SYSTEM,"动态加载列表控件失败");
        }
  }
  /**
   * 创建树控件
   * @param treeID 树ID
   * @param url 节点连接
   * @param isExpand 是否展开
   * @param queryMethod 树控件查询方法
   * @param addMap 附加参数
   * @return
   */
  public TreeControl loadTreeControl(String treeID,String url,boolean isExpand,String queryMethod,String bindFields,Map<String,Object> addMap) {
     return this.loadTreeControl(treeID, url, isExpand, queryMethod,null, bindFields, addMap, false,null);
  }
  
  /**
   * 初始化查询返回
   */
  private void initLookup() {
  //获取lookup参数
    String lookupParam = this.getContext().getRequest().getParameter(GaConstant.FIXPARAM_LOOKUPMODE);
    if (!GaUtil.isNullStr(lookupParam)) {
      this.isLookupMode = true;
      this.preNavInfoStr = "dialog,selewin";
      this.lookupFieldSet = new HashSet<String>();
      String[] list =lookupParam.split(",");
      for (int i=0;i<list.length;i++) {     
        if (list[i].indexOf(GaConstant.FIXPARAM_LOOKUPCALLBACK) == 0) {
          this.lookupCallback =list[i]; 
        } else if (list[i].indexOf(GaConstant.FIXPARAM_LOOKUPCID) == 0) {
          this.lookupControlID = list[i].replace(GaConstant.FIXPARAM_LOOKUPCID, "");
        } else {
          this.lookupFieldSet.add(list[i]);   
        }
      }
    }
  }
  
  
  @Override
  public void initAction() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void initControl() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onRender() {
    // TODO Auto-generated method stub
    try {
      if (this.isLookupMode) {  
        //查找返回模式
        this.pageActionList.clear();
        String divID ="";
        for(String key : this.divMap.keySet()) {
          Control control = this.divMap.get(key);
          if (control.getName().equals(this.lookupControlID)) {
            divID = key;
            break;
          }
        }
        //增加查找返回操作
        ActionButton newLink = new ActionButton(this.getClass(),"lookup","确认选择","",null);
        newLink.setOnClick("javascript:doLookupSelectDiv('"+divID+"','"+this.lookupCallback+"')");
        newLink.setAttribute("class","icon");            
        this.regPageButton(newLink);
      }
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException(BizException.SYSTEM, "渲染页面失败", ex);
    }
  }

  public QueryTable loadListControl(String listID,
      String method,
      Map<String,Object> addParam,boolean haveQueryForm) {
    return this.loadListControl(listID, method, addParam, haveQueryForm,false);
  }
  
  public QueryTable loadListControl(String listID,
      String method,
      Map<String,Object> addParam,boolean haveQueryForm,boolean havePagination) {
    return this.loadListControl(listID,GaConstant.EDITMODE_DISP, method, addParam, haveQueryForm,false);
  }
  /**
   * 加载列表控件
   * @param listID
   * @param dbFieldList
   * @param biz
   * @param method
   * @return
   */
  public QueryTable loadListControl(String listID,int eidtMode,
      String method,
      Map<String,Object> addParam,boolean haveQueryForm,boolean havePagination) {
    try {
      List<DbField> dbFieldList = this.initDbField(listID);
      IBiz biz = this.getBizClass(listID);
      if (biz == null) {
        throw new BizException(BizException.SYSTEM,"业务类不合法");
      } else {
        biz.setRowData(this.rowDataObj);
        biz.setDbFields(dbFieldList);
      }
      //创建控件实例
      QueryTable queryTable = new QueryTable(listID,dbFieldList,eidtMode,null);
      if (haveQueryForm) {
        queryTable.setQueryForm();
        queryTable.getQueryForm().setActionURL(this.getContext().getRequest().getRequestURI());
      } 
      if (havePagination) {
        queryTable.setListPagination();
      }
      QueryPageData queryData = null;
      if (queryTable.getQueryForm() != null) {
        //利用request填充查询表单内容        
        this.bindForm(queryTable.getQueryForm()); 
        //初始分页信息
        //设置默认分页值
        if (GaUtil.isNullStr(queryTable.getQueryForm().getFieldValue(GaConstant.FIXPARAM_PAGENO))) {
          HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGENO,1);
          queryTable.getQueryForm().add(field);
        }
        if (GaUtil.isNullStr(queryTable.getQueryForm().getFieldValue(GaConstant.FIXPARAM_PAGESIZE))) {
          HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGESIZE,ListPage.DEFAULT_PAGESIZE);
          queryTable.getQueryForm().add(field);
        }
        if (GaUtil.isNullStr(queryTable.getQueryForm().getFieldValue(GaConstant.FIXPARAM_PAGE_ORDERF))) {
          HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGE_ORDERF,"");
          queryTable.getQueryForm().add(field);
        }
        if (GaUtil.isNullStr(queryTable.getQueryForm().getFieldValue(GaConstant.FIXPARAM_PAGE_ORDERT))) {
          HiddenField field  = new HiddenField(GaConstant.FIXPARAM_PAGE_ORDERT,"");
          queryTable.getQueryForm().add(field);
        }
        //获取查询数据
        queryData = this.getQueryData(queryTable.getQueryForm(), dbFieldList);        
      } else {
        //附加参数如何处理?
      }            
      //设置查找返回状态下的绑定信息
      if (this.isLookupMode && this.lookupControlID.equals(listID)) {
        for(DbField field : dbFieldList) {
          if (this.lookupFieldSet.contains(field.getFieldCode())) {
            field.setBindRowData(true);
          } else {
            field.setBindRowData(false);
          }
        }
      }
      //调用查询方法
      Method  execMethod   =   biz.getClass().getMethod(method,new java.lang.Class[]{QueryPageData.class});
      Object getList = execMethod.invoke(biz,queryData);
      //获取查询列表对象
      if (getList != null) {
        //将查询结果绑定奥控件
        queryTable.setDataSource((PageResult)getList);
      }
      //返回
      return queryTable;
    }
    catch(BizException e) {
      e.printStackTrace();
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"动态加载列表控件失败");
    }
  }
  
  
  
  
//  public QueryTable loadListControl(String listID,
//      List<DbField> dbFieldList,String fieldCodeList,boolean isInclude,
//      IBiz biz,String method,Map<String,Object> addParam) {
//    List<DbField> inFieldList =  dbFieldList;
//    if (!NoahUtil.isNullStr(fieldCodeList)) {
//      String inField = fieldCodeList + ",";
//      inFieldList = new ArrayList<DbField>();
//      if (isInclude) {
//        for(DbField field : dbFieldList) {
//          if (inField.indexOf(field.getFieldCode()) > -1) {
//            inFieldList.add(field);
//          }
//        }
//      } else {
//        for(DbField field : dbFieldList) {
//          if (inField.indexOf(field.getFieldCode()) == -1) {
//            inFieldList.add(field);
//          }
//        }
//      }
//    }
//    return this.loadListControl(listID, inFieldList, biz,method,addParam);
//   }
  
  /**
   * 加载form控件
   * @param formID
   * @param dbFieldList
   * @param editMode
   * @param biz
   * @param method
   * @return
   */
  public DataForm loadFormControl(String formID,
      int editMode,String method,Map<String,Object> addMap) {
    try {      
      List<DbField> dbFieldList = this.initDbField(formID);
      IBiz biz = this.getBizClass(formID);
      if (biz == null) {
        throw new BizException(BizException.SYSTEM,"业务类不合法");
      } else {
        biz.setRowData(this.rowDataObj);
        biz.setDbFields(dbFieldList);
      }
      //创建控件实例
      DataForm dataForm = new DataForm(formID,dbFieldList,editMode,null);
      if (editMode != GaConstant.EDITMODE_NEW) {
        //调用查询方法
        //绑定表单(明细查询参数通过_rowdata参数传递,而不是通过表单？
        this.bindForm(dataForm);
        Map<String,Object> getMap = this.getFormValueMap(dataForm, dbFieldList);
        if (addMap != null) {
          getMap.putAll(addMap);
        }
        ((IBiz)biz).setValueMap(getMap);
        Method  execMethod = biz.getClass().getMethod(method,null);
        Object getList = execMethod.invoke(biz);
        //获取查询列表对象
        if (getList != null) {
          //将查询结果绑定奥控件
          this.putFormValue(dataForm,(Map<String,Object>)getList,dbFieldList);
        } 
      } else {
        //设置默认值
        this.bindForm(dataForm);
        Map<String,Object> getMap =this.getFormValueMap(dataForm, dbFieldList);
        if (addMap !=null) {
          getMap.putAll(addMap);
        }
        ((IBiz)biz).setValueMap(getMap);
        this.putFormValue(dataForm,null,dbFieldList);
      }
      //返回
      return dataForm;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"动态加载列表控件失败");
    }
  } 
  /**
   * 执行指定div区域的刷新
   * @return
   */
  public ActionResult loadDiv() {
    try {
      //根据默认参数执行初始化
      ActionResult returnDivInfo = null;
      if (this.divID.indexOf("|") > 0) {
        //多div刷新
        String[] divList  = this.divID.split("\\|");
        //初始化所有div
        this.onInit();
        HtmlStringBuffer buff = new HtmlStringBuffer();
        buff.append("<div>");
        for (String div : divList) {          
          buff.append("<div id=\"").append(div).append("\">");
          this.divMap.get(div).render(buff);
          buff.append("</div>");
        }
        buff.append("</div>");
        returnDivInfo = new ActionResult(buff.toString(),ActionResult.HTML);
      } else {
        this.onInit();      
        if (this.divMap.get(this.divID) != null) {
          returnDivInfo = new ActionResult(this.divMap.get(this.divID).toString(),ActionResult.HTML);
        } else {
          throw new BizException(BizException.SYSTEM,"指定刷新区域不存在");
        }
      }
      return returnDivInfo;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"动态加载列表控件失败");
    }
  }
  
  /**
   * 注册页面级按钮
   * @param button
   */
  public void regPageButton(ActionButton button) {
    this.pageActionList.add(button);
  }
  
  public List<ActionButton> getPageActionList() {
    return pageActionList;
  }

  @Override
  public IBiz initBizClass(String controlID) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<DbField> initDbField(String controlID) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void addModel(String name, Object value) {
    // TODO Auto-generated method stub
    if (value == null) return;
    super.addModel(name, value);
  }
}
