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
  
  //�ύ�Ĳ���
  protected boolean isLookupMode = false; //�Ƿ��ѯѡ��ģʽ
  protected Set<String> lookupFieldSet= null; //��ѯѡ�񷵻��ֶ��б�    
  protected String lookupCallback = ""; //���ҷ��ػص�����
  protected String lookupControlID = ""; //���Ҵ��صĿؼ�ID
  
  public MultiDivPage() {
    super();
    this.initLookup();
  }
  /**
   * ����ָ��DIV�Ŀؼ�
   * @param divID divid
   * @param control �ؼ�����
   */
  public void setDivControl(String divID,AbstractControl control) {
    if (control == null) {
      return;
    } else {
      //ʵ�ֿؼ�����Ƭ������
      SubmitTool.submitToDiv(control, divID,this.preNavInfoStr);
      this.divMap.put(divID,control);
    }
    divMap.put(divID, control);
  }
  
  /**
   * �������ؼ�
   * @param treeID ��ID
   * @param url �ڵ�����
   * @param isExpand �Ƿ�չ��
   * @param queryMethod ���ؼ���ѯ����
   * @param queryCheckValueMethod ���ؼ�ѡ��ֵ��ѯ����
   * @param addMap ���Ӳ���
   * @param istreeCheck �Ƿ�ΪtreeCheck
   * @param checkFun ��ǰ̨JS����¼�
   * @return
   */
  public TreeControl loadTreeControl(String treeID,String url,boolean isExpand,String queryMethod,String queryCheckValueMethod,String bindFields,Map<String,Object> addMap,boolean isTreeCheck,String checkFun) {
      try {
          IBiz biz = this.getBizClass(treeID);
          if (biz == null) {
            throw new BizException(BizException.SYSTEM,"ҵ���಻�Ϸ�");
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
          throw new BizException(BizException.SYSTEM,"��̬�����б�ؼ�ʧ��");
        }
  }
  /**
   * �������ؼ�
   * @param treeID ��ID
   * @param url �ڵ�����
   * @param isExpand �Ƿ�չ��
   * @param queryMethod ���ؼ���ѯ����
   * @param addMap ���Ӳ���
   * @return
   */
  public TreeControl loadTreeControl(String treeID,String url,boolean isExpand,String queryMethod,String bindFields,Map<String,Object> addMap) {
     return this.loadTreeControl(treeID, url, isExpand, queryMethod,null, bindFields, addMap, false,null);
  }
  
  /**
   * ��ʼ����ѯ����
   */
  private void initLookup() {
  //��ȡlookup����
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
        //���ҷ���ģʽ
        this.pageActionList.clear();
        String divID ="";
        for(String key : this.divMap.keySet()) {
          Control control = this.divMap.get(key);
          if (control.getName().equals(this.lookupControlID)) {
            divID = key;
            break;
          }
        }
        //���Ӳ��ҷ��ز���
        ActionButton newLink = new ActionButton(this.getClass(),"lookup","ȷ��ѡ��","",null);
        newLink.setOnClick("javascript:doLookupSelectDiv('"+divID+"','"+this.lookupCallback+"')");
        newLink.setAttribute("class","icon");            
        this.regPageButton(newLink);
      }
    } catch (BizException e) {
      throw e;
    } catch (Exception ex) {
      throw new BizException(BizException.SYSTEM, "��Ⱦҳ��ʧ��", ex);
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
   * �����б�ؼ�
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
        throw new BizException(BizException.SYSTEM,"ҵ���಻�Ϸ�");
      } else {
        biz.setRowData(this.rowDataObj);
        biz.setDbFields(dbFieldList);
      }
      //�����ؼ�ʵ��
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
        //����request����ѯ������        
        this.bindForm(queryTable.getQueryForm()); 
        //��ʼ��ҳ��Ϣ
        //����Ĭ�Ϸ�ҳֵ
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
        //��ȡ��ѯ����
        queryData = this.getQueryData(queryTable.getQueryForm(), dbFieldList);        
      } else {
        //���Ӳ�����δ���?
      }            
      //���ò��ҷ���״̬�µİ���Ϣ
      if (this.isLookupMode && this.lookupControlID.equals(listID)) {
        for(DbField field : dbFieldList) {
          if (this.lookupFieldSet.contains(field.getFieldCode())) {
            field.setBindRowData(true);
          } else {
            field.setBindRowData(false);
          }
        }
      }
      //���ò�ѯ����
      Method  execMethod   =   biz.getClass().getMethod(method,new java.lang.Class[]{QueryPageData.class});
      Object getList = execMethod.invoke(biz,queryData);
      //��ȡ��ѯ�б����
      if (getList != null) {
        //����ѯ����󶨰¿ؼ�
        queryTable.setDataSource((PageResult)getList);
      }
      //����
      return queryTable;
    }
    catch(BizException e) {
      e.printStackTrace();
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"��̬�����б�ؼ�ʧ��");
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
   * ����form�ؼ�
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
        throw new BizException(BizException.SYSTEM,"ҵ���಻�Ϸ�");
      } else {
        biz.setRowData(this.rowDataObj);
        biz.setDbFields(dbFieldList);
      }
      //�����ؼ�ʵ��
      DataForm dataForm = new DataForm(formID,dbFieldList,editMode,null);
      if (editMode != GaConstant.EDITMODE_NEW) {
        //���ò�ѯ����
        //�󶨱�(��ϸ��ѯ����ͨ��_rowdata��������,������ͨ������
        this.bindForm(dataForm);
        Map<String,Object> getMap = this.getFormValueMap(dataForm, dbFieldList);
        if (addMap != null) {
          getMap.putAll(addMap);
        }
        ((IBiz)biz).setValueMap(getMap);
        Method  execMethod = biz.getClass().getMethod(method,null);
        Object getList = execMethod.invoke(biz);
        //��ȡ��ѯ�б����
        if (getList != null) {
          //����ѯ����󶨰¿ؼ�
          this.putFormValue(dataForm,(Map<String,Object>)getList,dbFieldList);
        } 
      } else {
        //����Ĭ��ֵ
        this.bindForm(dataForm);
        Map<String,Object> getMap =this.getFormValueMap(dataForm, dbFieldList);
        if (addMap !=null) {
          getMap.putAll(addMap);
        }
        ((IBiz)biz).setValueMap(getMap);
        this.putFormValue(dataForm,null,dbFieldList);
      }
      //����
      return dataForm;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"��̬�����б�ؼ�ʧ��");
    }
  } 
  /**
   * ִ��ָ��div�����ˢ��
   * @return
   */
  public ActionResult loadDiv() {
    try {
      //����Ĭ�ϲ���ִ�г�ʼ��
      ActionResult returnDivInfo = null;
      if (this.divID.indexOf("|") > 0) {
        //��divˢ��
        String[] divList  = this.divID.split("\\|");
        //��ʼ������div
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
          throw new BizException(BizException.SYSTEM,"ָ��ˢ�����򲻴���");
        }
      }
      return returnDivInfo;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"��̬�����б�ؼ�ʧ��");
    }
  }
  
  /**
   * ע��ҳ�漶��ť
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
