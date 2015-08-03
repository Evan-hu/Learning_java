package com.ga.click.mvc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ga.click.control.GaConstant;
import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;

public class ModelMap {

  private int pageEditMode = GaConstant.EDITMODE_DISP;; //ҳ��༭ģʽ
  private String actionID;  //ִ�������actionID
  private Map<String,Object> modelAddPram = new HashMap<String,Object>();
  //������ʽ����

  protected String divID = "";
  protected String windowNavType = GaConstant.NAVTYPE_TAB; //���ڵ�����ʽ
  protected String windowNavID = "main";  //��ǰ���ڵ���Ŀ��ID
  protected String navInfoStr = "";
  protected String preNavInfoStr = "";
  protected HttpServletRequest request = null;
  protected Map<String,View> viewMap = new HashMap<String,View>();
  
  //���ҷ���ģʽ
  protected boolean lookupMode = false; //�Ƿ��ѯѡ��ģʽ
  protected Set<String> lookupFieldSet= null; //��ѯѡ�񷵻��ֶ��б�    
  protected String lookupCallback = ""; //���ҷ��ػص�����
  protected String lookupControlID = ""; //���Ҵ��صĿؼ�ID
  protected String lookupSize = ""; //�������ڵĳߴ�
  protected String lookupUrl = ""; //��ѯ���ص�URL
  protected String lookupTarget = "";
  protected boolean lookupReload = false;
  protected boolean lookupMultiSele =false;
  
  protected UserACL userACL = null;
  protected Map<String,View> bindDataViewMap = new HashMap<String,View>();
  
  
  public int getPageEditMode() {
    return pageEditMode;
  }
  
  public void setPageEditMode(int pageEditMode) {
    this.pageEditMode = pageEditMode;
  }
  
  public String getActionID() {
    return actionID;
  }
  
  public void setActionID(String actionID) {
    this.actionID = actionID;
  }
  
  public HttpServletRequest getRequest() {
    return this.request;
  }
  
  public String getRequestURL() {
    String url = request.getRequestURI();
    String param = request.getQueryString();
    if (!GaUtil.isNullStr(param)) {
      url = url + "?" + param;
    }
    return url;
  }
  
  public HttpSession getSession(boolean create) {
    return this.request.getSession(create);
  }
  
  /**
   * ��������,����request��ʼ���̶�Ĭ�ϲ���
   * @param request
   * @throws Exception
   */
  public ModelMap(UnitPage page) throws Exception {
    try {
      this.userACL = page.getUserACL();
      this.request = page.getContext().getRequest();
      this.initFixParams();
      this.initLookup();
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"����ҳ����������쳣");
    }
  }
  
  /**
   * ע��������ݵ���ͼ
   * @param view
   */
  public void putBindDataView(View view) {
    this.bindDataViewMap.put(view.getViewID(), view);
  }
  
  public Map<String, View> getBindDataViewMap() {
    return bindDataViewMap;
  }

  /**
   * ִ����ͼ��������
   * @param view
   */
  public void parseViewParam(View view) {
    try {
      view.parseRequest();
      this.viewMap.put(view.getViewID(),view);
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"������ͼ�����쳣");
    } 
  }
  
  /**
   * ��ȡָ����ͼ�����ύ������(��list�����ż��ͼ)
   * (����ͬ����������,������ͨ��setQuery�����������õ�dbField,��δ������Ϊ�����ֶ�)
   * @param view ��ͼ����
   * @return
   */
  public View getView(String viewID) {
    View view = this.viewMap.get(viewID);
    if (view == null) {
      throw new BizException(BizException.SYSTEM,"ָ����ͼδע��,�޷�����");
    }
    return view;
  }  
  
  /**
   * �����̶�����
   */
  private final void initFixParams() {      
    //��ǰҳ��򿪷�ʽ(��,�ָ����ַ�)ֻ���������û�д��ҳ��
    navInfoStr = request.getParameter(GaConstant.FIXPARAM_WINDOWNAV);
    if (!GaUtil.isNullStr(navInfoStr)) {
      String[] navInfo = navInfoStr.split(",");
      windowNavType = navInfo[0];
      if (navInfo.length > 2) {  
        windowNavID = navInfo[1];
      }
    } else {    	
      windowNavType = GaConstant.NAVTYPE_TAB;
    //lt-20140506 �ര��ģʽ�޸�
      //windowNavID = "main";
      windowNavID = "m_"+this.userACL.getSeleMenuID();
      navInfoStr = GaConstant.NAVTYPE_TAB+","+windowNavID;
    }       
    //�жϵ�ǰ�Ƿ�div�ύ
    if (windowNavType.equals(GaConstant.NAVTYPE_DIV) ||
        (navInfoStr.indexOf("mvc,") == 0))
  //      ((navInfoStr.indexOf("mvc,") == 0) && (!navInfoStr.equals("mvc,0"))) 
    {
      //����div�ύ,���ȡǰһҳ��򿪷�ʽ,�����˷�ʽ����Ϊdivˢ�µ�ҳ��򿪷�ʽ
      preNavInfoStr = request.getParameter(GaConstant.FIXPARAM_PREWINDOWNAV);
      if (GaUtil.isNullStr(preNavInfoStr)) {
    	  //lt-20140506
    	  windowNavID = "m_"+this.userACL.getSeleMenuID(); 
    	  preNavInfoStr = GaConstant.NAVTYPE_TAB+","+windowNavID;
      }            
    } else {
      //����������ʽ�ύ,�򽫵�ǰ������Ϣ����Ϊ��ҳ�浼����Ϣ
      preNavInfoStr = windowNavType+","+windowNavID;
    }
    String tmpV = request.getParameter(GaConstant.FIXPARAM_EDITMODE);
    if (!GaUtil.isNullStr(tmpV)) {
      try {        
        this.pageEditMode = Integer.parseInt(tmpV);
      } catch(Exception e) {
        this.pageEditMode = GaConstant.EDITMODE_DISP;
      }
    }
    //��ǰˢ�µ�divid
    this.divID = request.getParameter(GaConstant.FIXPARAM_DIVMODE);    
    this.actionID = request.getParameter(GaConstant.FIXPARAM_ACTIONID); 
  }

  public String getWindowNavType() {
    return windowNavType;
  }

  public String getWindowNavID() {
    return windowNavID;
  }

  public String getNavInfoStr() {
    return navInfoStr;
  }

  public String getPreNavInfoStr() {
    return preNavInfoStr;
  }
  
  /**
   * ��ʼ����ѯ����
   */
  private void initLookup() {
  //��ȡlookup����
    String lookupParam = this.request.getParameter(GaConstant.FIXPARAM_LOOKUPMODE);
    if (!GaUtil.isNullStr(lookupParam)) {
      this.lookupUrl = this.getRequestURL();
      this.lookupMode = true;
      this.preNavInfoStr = "dialog,selewin";
      this.lookupFieldSet = new HashSet<String>();
      String[] list =lookupParam.split(",");
      for (int i=0;i<list.length;i++) {     
        if (list[i].indexOf(GaConstant.FIXPARAM_LOOKUPCALLBACK) == 0) {
          this.lookupCallback =list[i]; 
        } else if (list[i].equals(GaConstant.FIXPARAM_MULTISELECT)) {
          this.lookupMultiSele = true;
        } else if (list[i].indexOf(GaConstant.FIXPARAM_LOOKUPCID) == 0) {
          this.lookupControlID = list[i].replace(GaConstant.FIXPARAM_LOOKUPCID, "");
        } else {
          this.lookupFieldSet.add(list[i]);   
        }
      }
      this.lookupSize = this.request.getParameter("popsize");
      this.lookupTarget = this.request.getParameter("poptarget");
      if ("1".equals(this.request.getParameter("lookupreload"))) {
        this.lookupReload = true;
      }
    }
  }

  public boolean isLookupMode() {
    return lookupMode;
  }

  public Set<String> getLookupFieldSet() {
    return lookupFieldSet;
  }

  public String getLookupCallback() {
    return lookupCallback;
  }

  public String getLookupControlID() {
    return lookupControlID;
  }
  
  public boolean isLookupMultiSele() {
	return lookupMultiSele;
}

public String getLookupTarget() {
    return lookupTarget;
  }

  public String getLookupSize() {
    return lookupSize;
  }

  
  public String getLookupUrl() {
    return lookupUrl;
  }
  
  

  public boolean isLookupReload() {
    return lookupReload;
  }

  /**
   * �����û�������Ϣ
   * @return
   */
  public UserACL getUserACL() {
    return this.userACL;
  }
  
  public void addOtherModelParam(String param,Object value) {
    this.modelAddPram.put(param, value);
  }
  
  public Object getOtherModelParam(String param) {
    return this.modelAddPram.get(param);
  }
}
