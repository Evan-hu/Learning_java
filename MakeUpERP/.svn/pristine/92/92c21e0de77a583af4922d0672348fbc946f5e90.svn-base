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
 * page相当于传统MVC的control层，主要实现以下功能:
 * 1.请求到控制器方法的分发
 * 2.控制器方法的自动调用和参数自动装配
 * 3.整合VIEW和model
 * 4.所有控制器都需继承此类
 * 5.todo 效率优化
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
  
  //未绑定事件，又需要初始化view
  protected Set<View> initView = new HashSet<View>();

  
  /**
   * 注册控制器方法
   * 1.注册控制器方法后可以针对方法注册输入参数，否则控制器方法输入参数必须为modelmap类型,
   * 并且此modelmap未同任何视图参数绑定
   * 2.注册事件的参数对应类型和顺序必须同对应函数的入参一致。
   * @param button 执行的按钮对象
   * @param controllerMethod 控制器方法名称
   */
  public PageEvent regPageEvent(ActionButton button,String controllerMethod) {
    try {      
      if (methodNameSet.contains(controllerMethod)) {
        throw new BizException(BizException.SYSTEM,"指定控制器方法已注册,不允许重复注册");
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
      throw new BizException(BizException.SYSTEM,"注册控制器方法时异常");
    }    
  }
  
  /**
   * 注册页面加载事件,load事件同其他按钮触发事件区别在于
   * 1.访问本页面时，不带actionid参数默认执行load事件
   * 2.load事件返回页面内容区的HTML
   * @param controllerMethod
   * @return
   */
  public PageEvent regPageLoadEvent(String methodName) {
    try {
      if (methodNameSet.contains(methodName)) {
        throw new BizException(BizException.SYSTEM,"指定方法已注册,不允许重复注册");
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
      throw new BizException(BizException.SYSTEM,"注册控制器方法时异常");
    }    
  }
  
  /**
   * 注册列表视图加载事件
   * 列表视图因为可能需要执行查询、翻页、刷新等操作，在一个页面有多个视图时，就必须为列表视图
   * 指定加载事件,否则是执行的整个页面LOAD事件
   * @param view
   * @param methodName
   * @return
   */
  public PageEvent regListViewLoadEvent(ListView view,String methodName) {
    try {      
      if (methodNameSet.contains(methodName)) {
        throw new BizException(BizException.SYSTEM,"指定控制器方法已注册,不允许重复注册");
      }
      String actionID = view.getViewID()+"LoadAction";
      ActionButton actionButton = new ActionButton(this.getClass(),actionID,"查询",this.getSelfUrl(),null);
      
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
      throw new BizException(BizException.SYSTEM,"注册控制器方法时异常");
    } 
  }
  
  /**
   * 注册页面级按钮,通过本方法注册的页面按钮触发后会自动提交所指定的视图数据。
   * 页面级按钮同视图内按钮的区别在于:
   * 1.页面级按钮显示在底部，不会随滚动条滚动。
   * 2.页面级按钮在load时加载一次，刷新视图时不会刷新页面按钮。
   * 3.页面级按钮可同时提交多个视图。
   * @param action 按钮对象
   * @param views 按钮提交的视图对象
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
        navInfo =  navInfo + ",0"; //不需要刷新
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
   * 根据事件注册信息进行参数初始化
   * 参数初始化是以视图为核心进行，在注册参数时需指定视图，初始化会自动
   * 将相关视图的参数初始化,如不指定任何参数,则不会初始化任何视图参数
   */
  private void initModelMap() {
    try {
      //构建相关视图参数      
      String actionID = this.modelMap.getActionID();
      this.isPageInit = this.checkIsInit(actionID);
      if ("_dataExport".equals(actionID)) {
        this.isExport = true;
      }
      String pageAction = getContext().getRequest().getParameter("pageAction");
      if (GaUtil.isNullStr(actionID) || !"doController".equals(pageAction)) {
       //页面加载处理
        actionID = LOAD_ACTION;
      }
      PageEvent method = this.controllerMap.get(actionID);
      if (method == null) {
        throw new BizException(BizException.SYSTEM,"未注册此按钮,无法执行参数初始化");
      }
      List<View> viewList = method.getViewList();
      if (viewList == null || viewList.size() ==0) {
        //throw new BizException(BizException.SYSTEM,"未注册控制器方法对应视图,无法执行参数初始化");
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
      throw new BizException(BizException.SYSTEM,"初始化参数处理异常");
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
   * 执行方法分发处理和参数校验等,控制器的核心处理
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
        //普通控制器方法必须执行onInit;
        this.onInit();
      }
      PageEvent method = this.controllerMap.get(actionID);
      if (method == null) {
        throw new BizException(BizException.SYSTEM,"未注册此按钮,无法执行操作");
      }
      //执行控制器方法调用
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
      throw new BizException(BizException.SYSTEM,"执行控制器分发异常.");
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
   * 页面构建函数
   */
  public UnitPage() {
    //构建默认参数
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
      throw new BizException(BizException.SYSTEM,"创建页面对象异常.");
    }
     
  }
    
  /**
   * 执行非按钮动作时(第一次页面加载)执行
   */
  @Override
  public void onRender() {
    // TODO Auto-generated method stub
    try {
      this.doController(); 
      //根据权限判断视图按钮
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
        //布局处理,获取到具体所选的控件
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
              //无分页
              autoH = autoH - 24;
            }
            if (!table.haveToolbar()) {
              //无工具条
              autoH = autoH - 24;
            }
            if (!table.haveQuery()) {
              //无查询
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
        //查找返回模式
        this.actionList.clear();
        //增加查找返回操作
        ActionButton newLink = new ActionButton(this.getClass(),"lookup","确认选择","",null);
        newLink.setOnClick("javascript:doLookupSelectDiv('"+this.modelMap.getLookupControlID()
            +"Div','"+this.modelMap.getLookupCallback()+"','"+this.modelMap.getLookupTarget()+"',"+this.modelMap.isLookupMultiSele()+")");
        newLink.setAttribute("class","icon");
        this.regPageActionButton(newLink);
        newLink = new ActionButton(this.getClass(),"cancellookup","清除选择","",null);
        newLink.setOnClick("javascript:clearLookupSelectDiv('"+this.modelMap.getLookupTarget()+"')");
        newLink.setAttribute("class","icon");
        this.regPageActionButton(newLink);
      }
    } 
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"页面加载异常");
    }
  }

  /**
   * 获取当前页面URL地址
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
   * 组合多个视图的HTML信息并返回
   * @param viewList 视图列表
   * @return
   */
  public ActionResult linkView(View...views) {
   return this.linkView(false,"", views); 
  }
  
  
  /**
   * 组合多个视图的HTML信息并返回
   * (可设置是否重新渲染按钮区域)
   * @param viewList
   * @param includePageButton
   * @param buff
   * @return
   */
  public ActionResult linkView(boolean includePageButton,String callBackJS,View...views) {
    try  {
      if (views == null || views.length == 0) {
        throw new BizException("返回HTML片段时,必须指定一个视图");
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
      throw new BizException(BizException.SYSTEM,"组合视图时异常");
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
      throw new BizException(BizException.SYSTEM,"页面构建异常");
    }
  }
  
  /**
   * 初始化控制器,初始化动作必须完成以下动作
   * 1.注册pageload事件
   * 2.注册各actionbutton的相关事件和参数
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
   * 注册非事件按钮
   * @param action
   */
  public void regInitView(View view) {
    this.initView.add(view);
  }
  
  /**
   * 获取当前登录用户的访问控制对象
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
      throw new BizException(BizException.SYSTEM,"创建返回信息时异常");
    }
  }

  public boolean isPageLoad() {
    return this.isPageInit;
  }

  public ModelMap getModelMap() {
    return modelMap;
  }
  
  
  /**
   * 复选删除
   */
  public  ActionResult delObjects(String id,String tableName,String viewId){
    return startOrStopObject(id, "删除",tableName,viewId);
  }
  /**
   * 单选删除
   */
  public  ActionResult delObject(Long id,String tableName,String viewId){
    return startOrStopObject(id, "删除",tableName,viewId);
  }
  /**
   * 复选废止
   */
  public  ActionResult stopObjects(String id,String tableName,String viewId){
    return startOrStopObject(id, "废止",tableName,viewId);
  }
  
  /**
   * 单选废止
   */
  public  ActionResult stopObject(Long id,String tableName,String viewId){
    return startOrStopObject(id, "废止",tableName,viewId);
  }
  /**
   *  复选启用
   */
  public  ActionResult startObjects(String id,String tableName,String viewId){
    return startOrStopObject(id, "启用",tableName,viewId);
  }
  
  /**
   *  单选启用
   * @param id
   * @return
   */
  public  ActionResult startObject(Long id,String tableName,String viewId){
    return startOrStopObject(id, "启用",tableName,viewId);
  }
  
  /**
   * 启用与废止
   * @param ids
   * @param message
   * @return
   */
  public  ActionResult startOrStopObject(Object id,String message,String tableName,String viewId){
    try{
      StateBiz biz = new StateBiz(getUserACL());
      if(message.equals("废止")){
        biz.stopObject(id,tableName);
      }else if(message.equals("启用")){
        biz.startObject(id,tableName);
      }else {
          biz.delObject(id,tableName);
        }
      return createReturnJSON(true, message + "成功", false, false, viewId, "");
    }catch (BizException e) {
      throw e;
    }catch(Exception ex) {
      ex.printStackTrace();
      throw new BizException(BizException.SYSTEM,message + "失败");
    }
  }
  
}
