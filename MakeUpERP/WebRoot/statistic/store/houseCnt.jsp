<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.ga.click.dbutil.DbUtils"%>
<%@page import="com.ga.click.mvc.UserACL"%>
<%@page import="com.ga.click.util.GaUtil"%>
<%@page import="javax.sql.rowset.CachedRowSet"%>
<%@page import="com.ga.click.util.charts.Charts"%>
<%@page import="com.ga.click.util.charts.ChartsContainer"%>
<%@page import="com.ga.erp.biz.statistic.store.SaleAnalysisBiz"%>
<%@page import="com.ga.erp.biz.statistic.util.ActivityUtil"%>
<%@page import="java.math.BigDecimal"%>
<%
//Charts charts = new Charts();
try{
  ChartsContainer container;
 // request.getMethod().equals("POST") || "POST".equals(request.getMethod());
  String daySelect="";
  String typeSelect="";
  String begDate="";
  String endDate="";
  String rtimeScope="" ;  
  
  daySelect = request.getParameter("daySelect");
  begDate = request.getParameter("beginDate");
  endDate = request.getParameter("endDate");
  rtimeScope=request.getParameter("timeScope");
  if(daySelect == null)
    daySelect = "6";
  if(rtimeScope == null)
   rtimeScope="6";  
  
  int daySelectItem = Integer.parseInt(daySelect);
  int timeScope =Integer.parseInt(rtimeScope);
  
  String begDateStr = GaUtil.isNullStr(begDate) ? "" : (begDate + " 00:00:00");
  String endDateStr = GaUtil.isNullStr(endDate) ? "" : (endDate + " 23:59:59");
  
  //选择的门店
  String storeIDs="";
  storeIDs =request.getParameter("HCSTORESELECT.STORE_ID");
  String storeName=request.getParameter("HCSTORESELECT.STORE_NAME");
  storeName=GaUtil.isNullStr(storeName)?"":storeName;
  //权限
   UserACL userACL=(UserACL)session.getAttribute("userAcl");
   
  if(GaUtil.isNullStr(storeIDs)&&!userACL.isAllOp()){
    storeIDs=userACL.getStoreID();
  }
  //折扣查询
  Integer ratio = userACL.getDiscRatio();
  
  //画图
  SaleAnalysisBiz biz = new SaleAnalysisBiz();
  List<Map<String,Object>> activityList = new ArrayList<Map<String,Object>>();
  container = biz.queryHouseCnt(
      GaUtil.formatDate("yyyy-MM-dd HH:mm:ss", GaUtil.getStrDate(begDateStr,"yyyy-MM-dd HH:mm:ss")),
      GaUtil.formatDate("yyyy-MM-dd HH:mm:ss", GaUtil.getStrDate(endDateStr,"yyyy-MM-dd HH:mm:ss")),
      daySelectItem, timeScope, request,activityList,storeIDs,ratio);
  
  BigDecimal allMoney = new BigDecimal(0);
  BigDecimal allOcnt = new BigDecimal(0);
  if(activityList !=null && activityList.size() > 0){
	  for (int i = 0; i < activityList.size(); i++) {
	    Map<String, Object> map = activityList.get(i);
	    allMoney = allMoney.add(new BigDecimal(map.get("ALL_MONEY") + ""));
	    allOcnt  = allOcnt.add(new BigDecimal(map.get("ALL_ORDER") + ""));
	  }
	  for(Map<String,Object> m : activityList){
	    m.put("MONEY_PRO",ActivityUtil.cutPriceStr(Double.parseDouble(m.get("ALL_MONEY") + "")* 100,allMoney.longValue()) +"%");//占比
	  }
  }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0"> 
    <title>单店分析</title>
  <script type="text/javascript">
var oldScope = <%=timeScope%>;     //保持原始时间范围数据
function setTimeScope(type){
  $("#beginTime1").val("");    //开始时间
  $("#endTime1").val("");      //结束时间
  $("#timeScope").val(type);   //查询时间    
  $("#daySelect").val(type);
  $("#ListForm_qrybutton").click();
}
function checkScope(o) {
  if(o.checked) {
    $("#timeScope").val("7");      //查询时间为自定义时间
    $("#defTime").css("display","block");
  } else {
    $("#defTime").css("display","none");
    $("#timeScope").val(oldScope);
  }
}
function checkCompare(o) {
  if(o.checked) {
    $("#goCompare").css("display","block");
  } else {
    $("#goCompare").css("display","none");
  }
}
  </script>
  </head>
  <body layoutH="0">
    <form  action="/statistic/store/houseCnt.jsp?_menuid=11031" method="post" id="storeForm" name="storeForm">
     <input type="hidden" value="<%=timeScope %>" name="timeScope" id="timeScope" />
      <input type="hidden" value="<%=daySelect %>" name="daySelect" id="daySelect" />
    <div class="pageHeader">
    <div class="searchBar">
   <ul class="searchContent">
              <li><label onclick="setTimeScope(1)" style="cursor: pointer; <%=timeScope == 1 ? "color:blue;" : "" %>">今日</label></li>
              <li><label onclick="setTimeScope(2)" style="cursor: pointer; <%=timeScope == 2 ? "color:blue;" : "" %>">昨日</label></li>
              <li><label onclick="setTimeScope(3)" style="cursor: pointer; <%=timeScope == 3 ? "color:blue;" : "" %>">最近7天</label></li>
              <li><label onclick="setTimeScope(4)" style="cursor: pointer; <%=timeScope == 4 ? "color:blue;" : "" %>">最近30天</label></li>
              <li><label onclick="setTimeScope(5)" style="cursor: pointer; <%=timeScope == 5 ? "color:blue;" : "" %>">上周</label></li>
              <li><label onclick="setTimeScope(6)" style="cursor: pointer; <%=timeScope == 6? "color:blue;" : "" %>">上月</label></li>
              <li>
                自定义时间:<input type="checkbox" name="isTime" id="isTime" value="1" <%=timeScope == 7 ? "checked=\"checked\"" : ""%> onchange="checkScope(this)"/>
              </li>
              <li id="defTime" style="<%=timeScope != 7 ? "display:none;" : ""%>">
                <input class="date textInput" type="text" hide="false" size="10" value="<%=begDate == null ? "" : begDate  %>" name="beginDate" id="beginTime1">
                -
                <input class="date textInput" type="text" hide="false" size="10" value="<%=endDate == null ? "" : endDate  %> "name="endDate" id="endTime1">
              </li>
               <li>
            <label>
            选择门店
            </label>
            <input type="hidden" value="<%=storeIDs %>" lookupgroup="HCSTORESELECT" name="HCSTORESELECT.STORE_ID">
            <input type="text" class="readonly textInput" readonly="readonly" value="<%=storeName%>" lookupgroup="HCSTORESELECT" 
            name="HCSTORESELECT.STORE_NAME" style="width:80px;">
            <a id="HCSTORESELECTLink" class="btnLook" width="800" height="450" 
            href="/store/store_main.htm?lookupMode=STORE_ID,STORE_NAME,cid_storeList&amp;popsize=800,450&amp;poptarget=HCSTORESELECTLink" 
            lookupgroup="HCSTORESELECT">选择</a>
            </li>
              
            </ul>
      <div class="subBar" style="float: right;">
        <ul>
          <li>
            <div class="buttonActive">
              <div class="buttonContent">
                <input class="searchButton" type="button" value="查询"  _windownav="navTab,m_11031" _prewindownav="navTab,main" _actionid="ListForm.qrybutton" onclick="doFormPost(this,'storeForm')" url="" id="ListForm_qrybutton" name="ListForm_qrybutton"/>
              </div>
            </div>
          </li>
        </ul>
      </div>
    </div>
    </div>
     <div layouth="40">
      <table width="1000px" class="list" id="brandListList">
      <thead align="center">
        <tr>
          <th width="100px" defaultval="">分店</th>
          <th width="100px" defaultval="">销售额</th>
          <th width="100px" defaultval="">订单数</th>
          <th width="100px" defaultval="">销售额占比</th>
        </tr>
      </thead>
      <%
        for(Map<String,Object> map : activityList){
      %>
      <tr>
        <td><%=map.get("STORE_NAME") %></td>
        <td><%=Double.parseDouble(map.get("ALL_MONEY") + "") / 100.00 %></td>
        <td><%=map.get("ALL_ORDER") %></td>
        <td><%=map.get("MONEY_PRO") %></td>
      </tr>
      <%}if(activityList != null && activityList.size() > 0){ %>
      	<tr>
        <td>合计</td>
        <td><%=allMoney.longValue() / 100.00 %></td>
        <td><%=allOcnt%></td>
        <td>100.0%</td>
     	</tr>
      <%} %>
      <tbody>
      </tbody>
      </table>
 	<br /><br />
        <%=container%>
    </div>
    </form>
  </body>
</html>
<%
} catch(Exception e){
  e.printStackTrace(); 
} finally {
  this.destroy();
}
%>
