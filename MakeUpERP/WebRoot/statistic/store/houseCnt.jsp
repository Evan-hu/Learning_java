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
  
  //ѡ����ŵ�
  String storeIDs="";
  storeIDs =request.getParameter("HCSTORESELECT.STORE_ID");
  String storeName=request.getParameter("HCSTORESELECT.STORE_NAME");
  storeName=GaUtil.isNullStr(storeName)?"":storeName;
  //Ȩ��
   UserACL userACL=(UserACL)session.getAttribute("userAcl");
   
  if(GaUtil.isNullStr(storeIDs)&&!userACL.isAllOp()){
    storeIDs=userACL.getStoreID();
  }
  //�ۿ۲�ѯ
  Integer ratio = userACL.getDiscRatio();
  
  //��ͼ
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
	    m.put("MONEY_PRO",ActivityUtil.cutPriceStr(Double.parseDouble(m.get("ALL_MONEY") + "")* 100,allMoney.longValue()) +"%");//ռ��
	  }
  }
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0"> 
    <title>�������</title>
  <script type="text/javascript">
var oldScope = <%=timeScope%>;     //����ԭʼʱ�䷶Χ����
function setTimeScope(type){
  $("#beginTime1").val("");    //��ʼʱ��
  $("#endTime1").val("");      //����ʱ��
  $("#timeScope").val(type);   //��ѯʱ��    
  $("#daySelect").val(type);
  $("#ListForm_qrybutton").click();
}
function checkScope(o) {
  if(o.checked) {
    $("#timeScope").val("7");      //��ѯʱ��Ϊ�Զ���ʱ��
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
              <li><label onclick="setTimeScope(1)" style="cursor: pointer; <%=timeScope == 1 ? "color:blue;" : "" %>">����</label></li>
              <li><label onclick="setTimeScope(2)" style="cursor: pointer; <%=timeScope == 2 ? "color:blue;" : "" %>">����</label></li>
              <li><label onclick="setTimeScope(3)" style="cursor: pointer; <%=timeScope == 3 ? "color:blue;" : "" %>">���7��</label></li>
              <li><label onclick="setTimeScope(4)" style="cursor: pointer; <%=timeScope == 4 ? "color:blue;" : "" %>">���30��</label></li>
              <li><label onclick="setTimeScope(5)" style="cursor: pointer; <%=timeScope == 5 ? "color:blue;" : "" %>">����</label></li>
              <li><label onclick="setTimeScope(6)" style="cursor: pointer; <%=timeScope == 6? "color:blue;" : "" %>">����</label></li>
              <li>
                �Զ���ʱ��:<input type="checkbox" name="isTime" id="isTime" value="1" <%=timeScope == 7 ? "checked=\"checked\"" : ""%> onchange="checkScope(this)"/>
              </li>
              <li id="defTime" style="<%=timeScope != 7 ? "display:none;" : ""%>">
                <input class="date textInput" type="text" hide="false" size="10" value="<%=begDate == null ? "" : begDate  %>" name="beginDate" id="beginTime1">
                -
                <input class="date textInput" type="text" hide="false" size="10" value="<%=endDate == null ? "" : endDate  %> "name="endDate" id="endTime1">
              </li>
               <li>
            <label>
            ѡ���ŵ�
            </label>
            <input type="hidden" value="<%=storeIDs %>" lookupgroup="HCSTORESELECT" name="HCSTORESELECT.STORE_ID">
            <input type="text" class="readonly textInput" readonly="readonly" value="<%=storeName%>" lookupgroup="HCSTORESELECT" 
            name="HCSTORESELECT.STORE_NAME" style="width:80px;">
            <a id="HCSTORESELECTLink" class="btnLook" width="800" height="450" 
            href="/store/store_main.htm?lookupMode=STORE_ID,STORE_NAME,cid_storeList&amp;popsize=800,450&amp;poptarget=HCSTORESELECTLink" 
            lookupgroup="HCSTORESELECT">ѡ��</a>
            </li>
              
            </ul>
      <div class="subBar" style="float: right;">
        <ul>
          <li>
            <div class="buttonActive">
              <div class="buttonContent">
                <input class="searchButton" type="button" value="��ѯ"  _windownav="navTab,m_11031" _prewindownav="navTab,main" _actionid="ListForm.qrybutton" onclick="doFormPost(this,'storeForm')" url="" id="ListForm_qrybutton" name="ListForm_qrybutton"/>
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
          <th width="100px" defaultval="">�ֵ�</th>
          <th width="100px" defaultval="">���۶�</th>
          <th width="100px" defaultval="">������</th>
          <th width="100px" defaultval="">���۶�ռ��</th>
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
        <td>�ϼ�</td>
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
