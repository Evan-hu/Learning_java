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

  private int pageEditMode = GaConstant.EDITMODE_DISP;; //页面编辑模式
  private String actionID;  //执行请求的actionID
  private Map<String,Object> modelAddPram = new HashMap<String,Object>();
  //导航方式控制

  protected String divID = "";
  protected String windowNavType = GaConstant.NAVTYPE_TAB; //窗口导航方式
  protected String windowNavID = "main";  //当前窗口导航目标ID
  protected String navInfoStr = "";
  protected String preNavInfoStr = "";
  protected HttpServletRequest request = null;
  protected Map<String,View> viewMap = new HashMap<String,View>();
  
  //查找返回模式
  protected boolean lookupMode = false; //是否查询选择模式
  protected Set<String> lookupFieldSet= null; //查询选择返回字段列表    
  protected String lookupCallback = ""; //查找返回回调口子
  protected String lookupControlID = ""; //查找带回的控件ID
  protected String lookupSize = ""; //弹出窗口的尺寸
  protected String lookupUrl = ""; //查询返回的URL
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
   * 构建函数,根据request初始化固定默认参数
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
      throw new BizException(BizException.SYSTEM,"构建页面参数对象异常");
    }
  }
  
  /**
   * 注册绑定了数据的视图
   * @param view
   */
  public void putBindDataView(View view) {
    this.bindDataViewMap.put(view.getViewID(), view);
  }
  
  public Map<String, View> getBindDataViewMap() {
    return bindDataViewMap;
  }

  /**
   * 执行视图参数解析
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
      throw new BizException(BizException.SYSTEM,"解析视图参数异常");
    } 
  }
  
  /**
   * 获取指定视图的所提交的数据(除list外的所偶视图)
   * (数据同参数的区别,参数是通过setQuery方法进行设置的dbField,如未调用则为数据字段)
   * @param view 视图对象
   * @return
   */
  public View getView(String viewID) {
    View view = this.viewMap.get(viewID);
    if (view == null) {
      throw new BizException(BizException.SYSTEM,"指定视图未注册,无法解析");
    }
    return view;
  }  
  
  /**
   * 解析固定参数
   */
  private final void initFixParams() {      
    //当前页面打开方式(以,分隔的字符)只解析不设置回写到页面
    navInfoStr = request.getParameter(GaConstant.FIXPARAM_WINDOWNAV);
    if (!GaUtil.isNullStr(navInfoStr)) {
      String[] navInfo = navInfoStr.split(",");
      windowNavType = navInfo[0];
      if (navInfo.length > 2) {  
        windowNavID = navInfo[1];
      }
    } else {    	
      windowNavType = GaConstant.NAVTYPE_TAB;
    //lt-20140506 多窗口模式修改
      //windowNavID = "main";
      windowNavID = "m_"+this.userACL.getSeleMenuID();
      navInfoStr = GaConstant.NAVTYPE_TAB+","+windowNavID;
    }       
    //判断当前是否div提交
    if (windowNavType.equals(GaConstant.NAVTYPE_DIV) ||
        (navInfoStr.indexOf("mvc,") == 0))
  //      ((navInfoStr.indexOf("mvc,") == 0) && (!navInfoStr.equals("mvc,0"))) 
    {
      //如是div提交,则获取前一页面打开方式,并将此方式设置为div刷新的页面打开方式
      preNavInfoStr = request.getParameter(GaConstant.FIXPARAM_PREWINDOWNAV);
      if (GaUtil.isNullStr(preNavInfoStr)) {
    	  //lt-20140506
    	  windowNavID = "m_"+this.userACL.getSeleMenuID(); 
    	  preNavInfoStr = GaConstant.NAVTYPE_TAB+","+windowNavID;
      }            
    } else {
      //如是其他方式提交,则将当前导航信息设置为本页面导航信息
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
    //当前刷新的divid
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
   * 初始化查询返回
   */
  private void initLookup() {
  //获取lookup参数
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
   * 返回用户控制信息
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
