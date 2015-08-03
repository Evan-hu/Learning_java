package com.ga.click.mvc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.click.ActionResult;
import org.apache.click.Page;
import org.apache.click.util.HtmlStringBuffer;
import org.json.JSONObject;

import com.ga.click.control.GaConstant;
import com.ga.click.control.SubmitTool;
import com.ga.click.control.button.ActionButton;
import com.ga.click.control.table.QueryTable;
import com.ga.click.exception.BizException;
import com.ga.click.layout.Layout;
import com.ga.click.layout.ViewPageLayout;
import com.ga.click.util.ClickUtil;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.StateBiz;

/**
 * page�൱�ڴ�ͳMVC��control�㣬��Ҫʵ�����¹���:
 * 1.���󵽿����������ķַ�
 * 2.�������������Զ����úͲ����Զ�װ��
 * 3.����VIEW��model
 * 4.���п���������̳д���
 * 5.todo Ч���Ż�
 *
 */
public abstract class UnitPage extends Page {
	
  protected static String LOAD_ACTION = "_PAGE_LOAD_ACTION";
  
  protected Map<String,PageEvent> controllerMap = new HashMap<String,PageEvent>(); //key:actionid,
  protected Set<String> methodNameSet = new HashSet<String>(); 
  protected ModelMap modelMap;
  protected List<ActionButton> actionList = new ArrayList<ActionButton>();
  protected Layout layout;
  protected boolean  isPageInit;
  protected boolean isExport = false;
  
  //δ���¼�������Ҫ��ʼ��view
  protected Set<View> initView = new HashSet<View>();

  
  /**
   * ע�����������
   * 1.ע������������������Է���ע�����������������������������������Ϊmodelmap����,
   * ���Ҵ�modelmapδͬ�κ���ͼ������
   * 2.ע���¼��Ĳ�����Ӧ���ͺ�˳�����ͬ��Ӧ���������һ�¡�
   * @param button ִ�еİ�ť����
   * @param controllerMethod ��������������
   */
  public PageEvent regPageEvent(ActionButton button,String controllerMethod) {
    try {      
      if (methodNameSet.contains(controllerMethod)) {
        throw new BizException(BizException.SYSTEM,"ָ��������������ע��,�������ظ�ע��");
      }
      PageEvent method = new PageEvent(controllerMethod,this);     
      controllerMap.put(button.getId(), method);
      methodNameSet.add(controllerMethod);
      button.setEvent(method);
      return method;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ע�����������ʱ�쳣");
    }    
  }
  
  /**
   * ע��ҳ������¼�,load�¼�ͬ������ť�����¼���������
   * 1.���ʱ�ҳ��ʱ������actionid����Ĭ��ִ��load�¼�
   * 2.load�¼�����ҳ����������HTML
   * @param controllerMethod
   * @return
   */
  public PageEvent regPageLoadEvent(String methodName) {
    try {
      if (methodNameSet.contains(methodName)) {
        throw new BizException(BizException.SYSTEM,"ָ��������ע��,�������ظ�ע��");
      }
      PageEvent method = new PageEvent(methodName,this);
      controllerMap.put(LOAD_ACTION, method);
      methodNameSet.add(methodName);
      return method;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ע�����������ʱ�쳣");
    }    
  }
  
  /**
   * ע���б���ͼ�����¼�
   * �б���ͼ��Ϊ������Ҫִ�в�ѯ����ҳ��ˢ�µȲ�������һ��ҳ���ж����ͼʱ���ͱ���Ϊ�б���ͼ
   * ָ�������¼�,������ִ�е�����ҳ��LOAD�¼�
   * @param view
   * @param methodName
   * @return
   */
  public PageEvent regListViewLoadEvent(ListView view,String methodName) {
    try {      
      if (methodNameSet.contains(methodName)) {
        throw new BizException(BizException.SYSTEM,"ָ��������������ע��,�������ظ�ע��");
      }
      String actionID = view.getViewID()+"LoadAction";
      ActionButton actionButton = new ActionButton(this.getClass(),actionID,"��ѯ",this.getSelfUrl(),null);
      
      actionButton.setAttribute("_mvccallback", "setListDescShow(\'" + view.getViewID() + "\', null);");
      SubmitTool.submitMvc(actionButton, this.modelMap.getPreNavInfoStr(), view);      
      PageEvent method = new PageEvent(methodName,this);     
      controllerMap.put(actionButton.getId(), method);
      methodNameSet.add(methodName);
      actionButton.setEvent(method);
      view.setSubmitAction(actionButton);
      return method;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ע�����������ʱ�쳣");
    } 
  }
  
  /**
   * ע��ҳ�漶��ť,ͨ��������ע���ҳ�水ť��������Զ��ύ��ָ������ͼ���ݡ�
   * ҳ�漶��ťͬ��ͼ�ڰ�ť����������:
   * 1.ҳ�漶��ť��ʾ�ڵײ��������������������
   * 2.ҳ�漶��ť��loadʱ����һ�Σ�ˢ����ͼʱ����ˢ��ҳ�水ť��
   * 3.ҳ�漶��ť��ͬʱ�ύ�����ͼ��
   * @param action ��ť����
   * @param views ��ť�ύ����ͼ����
   */
  public void regPageActionButton(ActionButton action,View...views) {
    if (views != null && views.length > 0) {
      String multiForm = "";
      for(View view : views){
        if (view instanceof ListView) {
          multiForm = multiForm + "," + view.getViewID()+"List";
        } else {
          multiForm = multiForm + "," + view.getViewID();
        }
      }
      
      String navInfo = GaConstant.NAVTYPE_MVC;
      if (views == null || views.length == 0) {
        navInfo =  navInfo + ",0"; //����Ҫˢ��
      } else {
        String divID = "";
        for (View view : views) {
          divID = divID + "|" + view.getDivID();
        } 
        if (divID.startsWith("|")) divID = divID.substring(1);
        navInfo = navInfo + "," + divID;
      }
      action.setAttribute(GaConstant.FIXPARAM_WINDOWNAV,navInfo);
      
      if (multiForm.startsWith(",")) multiForm = multiForm.substring(1);
      action.setAttribute(GaConstant.FIXPARAM_MULTIFORM, multiForm);
      action.setClickJS("PostMultiForm(this,'"+multiForm+"');");
      action.bindAjaxMethod("doController");
    }
    if (this.getUserACL() == null || !this.getUserACL().checkAction(action.getId(),action.getTitle())) {
      return;
    }
    this.actionList.add(action);
  }
  
  /**
   * �����¼�ע����Ϣ���в�����ʼ��
   * ������ʼ��������ͼΪ���Ľ��У���ע�����ʱ��ָ����ͼ����ʼ�����Զ�
   * �������ͼ�Ĳ�����ʼ��,�粻ָ���κβ���,�򲻻��ʼ���κ���ͼ����
   */
  private void initModelMap() {
    try {
      //���������ͼ����      
      String actionID = this.modelMap.getActionID();
      this.isPageInit = this.checkIsInit(actionID);
      if ("_dataExport".equals(actionID)) {
        this.isExport = true;
      }
      String pageAction = getContext().getRequest().getParameter("pageAction");
      if (GaUtil.isNullStr(actionID) || !"doController".equals(pageAction)) {
       //ҳ����ش���
        actionID = LOAD_ACTION;
      }
      PageEvent method = this.controllerMap.get(actionID);
      if (method == null) {
        throw new BizException(BizException.SYSTEM,"δע��˰�ť,�޷�ִ�в�����ʼ��");
      }
      List<View> viewList = method.getViewList();
      if (viewList == null || viewList.size() ==0) {
        //throw new BizException(BizException.SYSTEM,"δע�������������Ӧ��ͼ,�޷�ִ�в�����ʼ��");
      }
      for(View view : viewList) {
        if (!this.initView.contains(view)) {
          this.initView.add(view);
        }
      }
      for(View view : this.initView) {
        this.modelMap.parseViewParam(view);    
      }    
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"��ʼ�����������쳣");
    }
  }
  
  private boolean checkIsInit(String actionID) {
    if (GaUtil.isNullStr(actionID)) {
      return true;
    } else {
      if ("doController".equals(getContext().getRequest().getParameter("pageAction"))) {
        return false;
      } else {
        if (actionID.equals("qrybutton") || actionID.endsWith("LoadAction")) {
          return false;
        } else {
          return true;
        }
      }
    }
  }
  
  /**
   * ִ�з����ַ�����Ͳ���У���,�������ĺ��Ĵ���
   */
  public ActionResult doController() {
    try {       
      String actionID = this.modelMap.getActionID();    
      this.isPageInit = this.checkIsInit(actionID);
      boolean isLoad = false;
      if ("_dataExport".equals(actionID)) {
        actionID = "";
        this.modelMap.setActionID("");
      }
      if (GaUtil.isNullStr(actionID) || !"doController".equals(getContext().getRequest().getParameter("pageAction"))) {        
        actionID = LOAD_ACTION;
        isLoad = true;
      } else {
        //��ͨ��������������ִ��onInit;
        this.onInit();
      }
      PageEvent method = this.controllerMap.get(actionID);
      if (method == null) {
        throw new BizException(BizException.SYSTEM,"δע��˰�ť,�޷�ִ�в���");
      }
      //ִ�п�������������
      if (isLoad) {
        method.execMethodAsLoad(this.modelMap);
        return null;
      } else {
        return method.execMethodAsControl(this.modelMap);
      }      
    }
    catch(BizException e) {
      e.setAjaxRequest(true);
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ִ�п������ַ��쳣.");
    }
  }
  
 
  private void toDownload() throws Exception {
    HttpServletResponse response = this.getContext().getResponse();
    response.reset();
    response.setContentType("text/plain");
    String fileName = URLEncoder.encode("phone.txt", "UTF-8");
    response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    try {
      bis = new BufferedInputStream(new FileInputStream("d:\test.xls"));
      bos = new BufferedOutputStream(response.getOutputStream());
      byte[] buff = new byte[2048];
      int bytesRead;
      while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
        bos.write(buff, 0, bytesRead);
      }
    } catch (final IOException e) {
      System.out.println("IOException." + e);
    } finally {
      if (bis != null)
        bis.close();
      if (bos != null)
        bos.close();
    }
  }
  
  /**
   * ҳ�湹������
   */
  public UnitPage() {
    //����Ĭ�ϲ���
    try {
      //
//      Map<String,Object> userInfo = DbUtils.queryMap(DbUtils.getConnection(),"select * from OP where USERNAME=?", "superadmin");
//      System.out.println(this.getContext().getRequest().getSession());
//      UserACL userAcl = new UserACL(userInfo);
//      getContext().getRequest().getSession(false).setAttribute("userAcl",userAcl);
      UserACL acl = (UserACL) this.getContext().getRequest().getSession(false).getAttribute("userAcl");
      if (acl != null) {
        //acl.setMenuID(0L);
        acl.setMenuID((Long)this.getContext().getRequest().getSession(false).getAttribute("MENUID"));
       }
      this.modelMap = new ModelMap(this); 
    }
    catch(BizException ex) {
      throw ex;
    }
    catch(Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"����ҳ������쳣.");
    }
     
  }
    
  /**
   * ִ�зǰ�ť����ʱ(��һ��ҳ�����)ִ��
   */
  @Override
  public void onRender() {
    // TODO Auto-generated method stub
    try {
      this.doController(); 
      //����Ȩ���ж���ͼ��ť
//      for(View view : this.modelMap.getBindDataViewMap().values()) {
//        view.clearAction();
////        Iterator<ActionButton> it = view.getActionList().iterator();
////        while(it.hasNext()) {
////          ActionButton getaction = (ActionButton)it.next();
////          if (!this.getUserACL().checkAction(getaction.getId())) {
////            it.remove();
////          }
////        }
//      }
      this.layout = this.initLayout();
      if (this.modelMap.isLookupMode()) {
        //���ִ���,��ȡ��������ѡ�Ŀؼ�
        View view = this.modelMap.getBindDataViewMap().get(this.modelMap.getLookupControlID());
        if (view != null) {
          if (view instanceof ListView) {
            QueryTable table = view.getQueryTable();
            table.getQueryForm().setActionURL(this.modelMap.getLookupUrl()+"&lookupreload=1");
            if (!GaUtil.isNullStr(this.modelMap.getLookupSize())) {
              String[] size = this.modelMap.getLookupSize().split(",");
              int w = 0;
              try {
                w = Integer.parseInt(size[0]);
              } catch(Exception ee) {              
              }
              table.getListTable().setTableMaxWidth(w);
            }      
            if (table.getQueryForm() != null) {
              table.getQueryForm().setQueryNavInfo(this.modelMap.getPreNavInfoStr(),this.modelMap.getPreNavInfoStr());
            }
            if (table.getListPagination() != null) {
              table.getListPagination().setNavInfo(GaConstant.NAVTYPE_DIV, "", this.modelMap.getPreNavInfoStr());
            }
            table.setMultiSelect(this.modelMap.isLookupMultiSele());
            int autoH = 125;
            if (!table.havePage()) {
              //�޷�ҳ
              autoH = autoH - 24;
            }
            if (!table.haveToolbar()) {
              //�޹�����
              autoH = autoH - 24;
            }
            if (!table.haveQuery()) {
              //�޲�ѯ
              autoH = autoH - 38;
            } else {
              if (table.getQueryForm().getQueryRows() > 1) {
                int height = (table.getQueryForm().getQueryRows() - 1) * 25;
                autoH = autoH + height;
              }
            }
            ClickUtil.setControlLayoutH(table, autoH);
          }
        }
        //���ҷ���ģʽ
        this.actionList.clear();
        //���Ӳ��ҷ��ز���
        ActionButton newLink = new ActionButton(this.getClass(),"lookup","ȷ��ѡ��","",null);
        newLink.setOnClick("javascript:doLookupSelectDiv('"+this.modelMap.getLookupControlID()
            +"Div','"+this.modelMap.getLookupCallback()+"','"+this.modelMap.getLookupTarget()+"',"+this.modelMap.isLookupMultiSele()+")");
        newLink.setAttribute("class","icon");
        this.regPageActionButton(newLink);
        newLink = new ActionButton(this.getClass(),"cancellookup","���ѡ��","",null);
        newLink.setOnClick("javascript:clearLookupSelectDiv('"+this.modelMap.getLookupTarget()+"')");
        newLink.setAttribute("class","icon");
        this.regPageActionButton(newLink);
      }
    } 
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ҳ������쳣");
    }
  }

  /**
   * ��ȡ��ǰҳ��URL��ַ
   * @return
   */
  public String getSelfUrl() {
    String url = this.getContext().getRequest().getRequestURI();
    String param = this.getContext().getRequest().getQueryString();
    if (!GaUtil.isNullStr(param)) {
      url = url + "?" + param;
    }
    return url;
  }
  
  /**
   * ��϶����ͼ��HTML��Ϣ������
   * @param viewList ��ͼ�б�
   * @return
   */
  public ActionResult linkView(View...views) {
   return this.linkView(false,"", views); 
  }
  
  
  /**
   * ��϶����ͼ��HTML��Ϣ������
   * (�������Ƿ�������Ⱦ��ť����)
   * @param viewList
   * @param includePageButton
   * @param buff
   * @return
   */
  public ActionResult linkView(boolean includePageButton,String callBackJS,View...views) {
    try  {
      if (views == null || views.length == 0) {
        throw new BizException("����HTMLƬ��ʱ,����ָ��һ����ͼ");
      }
      HtmlStringBuffer buff = new HtmlStringBuffer();
      if (!GaUtil.isNullStr(callBackJS)) {
        buff.append(callBackJS);
      }
      buff.append("<div>");
      for (View view : views) {          
        buff.append("<div id=\"").append(view.getDivID()).append("\">");
        view.render(buff);
        buff.append("</div>\r\n");
      }
      if (includePageButton) {
        buff.append("<div id=\"pageButtonZone\">");
        ViewPageLayout viewLayout = new ViewPageLayout(this);
        viewLayout.renderButtonNoDiv(buff);
        buff.append("</div>");
      }      
      buff.append("</div>");    
      ActionResult returnViewInfo = new ActionResult(buff.toString(),ActionResult.HTML);
      return returnViewInfo;
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"�����ͼʱ�쳣");
    }    
  } 
  
  public void onInit() {   
    try {
      this.initController();
      this.initModelMap();        
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"ҳ�湹���쳣");
    }
  }
  
  /**
   * ��ʼ��������,��ʼ����������������¶���
   * 1.ע��pageload�¼�
   * 2.ע���actionbutton������¼��Ͳ���
   */
  public abstract void initController() throws Exception;
  
  public abstract Layout initLayout();

  public Layout getLayout() {
    return layout;
  }

  public List<ActionButton> getPageActionList() {
    return actionList;
  }
  
  /**
   * ע����¼���ť
   * @param action
   */
  public void regInitView(View view) {
    this.initView.add(view);
  }
  
  /**
   * ��ȡ��ǰ��¼�û��ķ��ʿ��ƶ���
   * @return
   */
  public UserACL getUserACL() {
   UserACL acl = (UserACL) this.getContext().getRequest().getSession(false).getAttribute("userAcl");
   if (acl != null) acl.setExportAction(this.isExport);
   return acl;
 }
  
  public ActionResult createReturnJSON(boolean isSucess,String message,boolean isClose,boolean reloadPage,String reloadView,String forwardUrl) {
    try {
      JSONObject obj = new JSONObject();
      if (isSucess) {
        obj.put("success", "1");
      } else {
        obj.put("success", "0");
      }
      obj.put("message", message);
      if (isClose) {
        obj.put("isclose", "1");
      }
      if (reloadPage) {
        obj.put("reloadpage","1");
      } else {
        obj.put("reloadpage","0");
      }
      if (!GaUtil.isNullStr(reloadView)) {
        obj.put("reloadview", reloadView);
        obj.put("prenavinfo",this.modelMap.getPreNavInfoStr());
      }
      if (!GaUtil.isNullStr(forwardUrl)) {
        obj.put("forward", forwardUrl);
        obj.put("prenavinfo",this.modelMap.getPreNavInfoStr());
        obj.put("navinfo",this.modelMap.getNavInfoStr());
      }
      String rtnJson = obj.toString();
      return new ActionResult(rtnJson,ActionResult.JSON);
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM,"����������Ϣʱ�쳣");
    }
  }

  public boolean isPageLoad() {
    return this.isPageInit;
  }

  public ModelMap getModelMap() {
    return modelMap;
  }
  
  
  /**
   * ��ѡɾ��
   */
  public  ActionResult delObjects(String id,String tableName,String viewId){
    return startOrStopObject(id, "ɾ��",tableName,viewId);
  }
  /**
   * ��ѡɾ��
   */
  public  ActionResult delObject(Long id,String tableName,String viewId){
    return startOrStopObject(id, "ɾ��",tableName,viewId);
  }
  /**
   * ��ѡ��ֹ
   */
  public  ActionResult stopObjects(String id,String tableName,String viewId){
    return startOrStopObject(id, "��ֹ",tableName,viewId);
  }
  
  /**
   * ��ѡ��ֹ
   */
  public  ActionResult stopObject(Long id,String tableName,String viewId){
    return startOrStopObject(id, "��ֹ",tableName,viewId);
  }
  /**
   *  ��ѡ����
   */
  public  ActionResult startObjects(String id,String tableName,String viewId){
    return startOrStopObject(id, "����",tableName,viewId);
  }
  
  /**
   *  ��ѡ����
   * @param id
   * @return
   */
  public  ActionResult startObject(Long id,String tableName,String viewId){
    return startOrStopObject(id, "����",tableName,viewId);
  }
  
  /**
   * �������ֹ
   * @param ids
   * @param message
   * @return
   */
  public  ActionResult startOrStopObject(Object id,String message,String tableName,String viewId){
    try{
      StateBiz biz = new StateBiz(getUserACL());
      if(message.equals("��ֹ")){
        biz.stopObject(id,tableName);
      }else if(message.equals("����")){
        biz.startObject(id,tableName);
      }else {
          biz.delObject(id,tableName);
        }
      return createReturnJSON(true, message + "�ɹ�", false, false, viewId, "");
    }catch (BizException e) {
      throw e;
    }catch(Exception ex) {
      ex.printStackTrace();
      throw new BizException(BizException.SYSTEM,message + "ʧ��");
    }
  }
  
}
