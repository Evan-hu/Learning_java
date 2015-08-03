<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ga.click.util.charts.ChartsContainer" %>
<%@ page import="com.ga.erp.biz.statistic.util.NStatisticUtil" %>
<%@ page import="com.ga.erp.biz.statistic.store.OrderBiz" %>
<%@page import="com.ga.click.util.GaUtil"%>
<%@page import="com.ga.click.mvc.UserACL"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <%
  	//状态
  int timeType = 0;          //0:按日 1:按周 2:按月
  int orTimeScope = 0;         //0：今日 1：昨日  2：最近七天 3：上周  4：上月   5：最近30天  6：自定义时间
  int isCompare = 0;         //0: 不对比 1:对比 
  int sourceType = 0;        //0: 订单量 1:订单金额 2:平均订单金额
	String sequence = "desc";  //desc:降序 asc:升序
	int shop  =  2;            //2：双楠  4：建设  5：高新 0:不区分
	int commodityType = 0;     //0：浏览量 1：销量 2：销售额 3：评论数
	int top = 10;              //10： 显示10条  50： 显示50条  100：显示100条
	int count = 0;             //小计
	int count1 = 0;
	String countStr = "0";     //小计
	String count1Str = "0";
	
  	//时间
	String beginTime1 = "";
	String endTime1 = "";
	String beginTime2 ="";
	String endTime2 = "";
	//查询时间
	String beginTimeSelect1 = "";
	String endTimeSelect1 = "";
	String beginTimeSelect2 ="";
	String endTimeSelect2 = "";
	//价格
	String minPrice = "";
	String maxPrice = "";
	//查询价格
	Long minPriceSelect = 0L;
	Long maxPriceSelect = 0L;
	
	NStatisticUtil s = new NStatisticUtil();
	Map<String,Object> paras = s.ProcessCondition(request);
	//获取处理后的值
	beginTime1 = String.valueOf(paras.get("beginTime1"));
	endTime1 = String.valueOf(paras.get("endTime1"));
	beginTime2 = String.valueOf(paras.get("beginTime2"));
	endTime2 = String.valueOf(paras.get("endTime2"));
	orTimeScope = Integer.parseInt(String.valueOf(paras.get("timeScope")));
	isCompare = Integer.parseInt(String.valueOf(paras.get("isCompare")));
	sourceType = Integer.parseInt(String.valueOf(paras.get("sourceType")));
	sequence = String.valueOf(paras.get("sequence"));
	shop = Integer.parseInt(String.valueOf(paras.get("shop")));
	commodityType = Integer.parseInt(String.valueOf(paras.get("commodityType")));
	top = Integer.parseInt(String.valueOf(paras.get("top")));
	minPrice = String.valueOf(paras.get("minPriceStr"));
	maxPrice = String.valueOf(paras.get("maxPriceStr"));
	timeType = Integer.parseInt(String.valueOf(paras.get("timeType")));
	minPriceSelect = Long.parseLong(String.valueOf(paras.get("minPrice")));
	maxPriceSelect = Long.parseLong(String.valueOf(paras.get("maxPrice")));
	beginTimeSelect1 = String.valueOf(paras.get("beginTime1"));
	endTimeSelect1 = String.valueOf(paras.get("endTime1"));
	beginTimeSelect2 = String.valueOf(paras.get("beginTime2"));
	endTimeSelect2 = String.valueOf(paras.get("endTime2"));
	
	 //选择的门店
  String storeIDs="";
  storeIDs =request.getParameter("OSTORESELECT.STORE_ID");
  String storeName=request.getParameter("OSTORESELECT.STORE_NAME");
  storeName=GaUtil.isNullStr(storeName)?"":storeName;
  //权限
   UserACL userACL=(UserACL)session.getAttribute("userAcl");
   
  if(GaUtil.isNullStr(storeIDs)&&!userACL.isAllOp()){
    storeIDs=userACL.getStoreID();
  }
  
  //折扣查询
  Integer ratio = userACL.getDiscRatio();
  
	//执行查询
	OrderBiz oab = new OrderBiz();
	oab.setOrderFacts(sourceType,timeType, beginTimeSelect1, endTimeSelect1, beginTimeSelect2,
	    endTimeSelect2, orTimeScope, isCompare,maxPriceSelect,minPriceSelect,storeIDs,ratio);
	ChartsContainer orderS = oab.queryOrder();
 
  %>
  <script type="text/javascript">
  	var oldScope = <%=orTimeScope%>;     //保持原始时间范围数据
  	function setorTimeScope(type){
  	  $("#beginTime1").val("");    //开始时间
		  $("#endTime1").val("");      //结束时间
		  $("#beginTime2").val("");    //对比开始时间
		  $("#endTime2").val("");	   //对比结束时间
		  $("#orTimeScope").val(type);   //查询时间    
		  document.getElementById("isCompare").value = 0;	
		  $("#order_qrybutton").click();
  	}
  	function checkScope(o) {
      if(o.checked) {
        $("#orTimeScope").val("6");      //查询时间为自定义时间
        $("#ordefTime").css("display","block");
      } else {
        $("#ordefTime").css("display","none");
        $("#orTimeScope").val(oldScope);
      }
    }
    function checkCompare(o) {
      if(o.checked) {
        $("#goCompare").css("display","block");
        document.getElementById("isCompare").value = 1; 
      } else {
        $("#goCompare").css("display","none");
      }
    }
  </script>
  </head>
  
  <body>
  	
    <form action="/statistic/store/order.jsp?m_11032" id="pagerForm" name="pagerForm" method="post">
    	<input type="hidden" value="<%=orTimeScope%>" name="timeScope" id="orTimeScope" />
				<div class="pageHeader" >
					<div class="searchBar">
						<ul class="searchContent">
							<li><label onclick="setorTimeScope(0)" style="cursor: pointer; <%=orTimeScope == 0 ? "color:blue;" : "" %>">今日</label></li>
							<li><label onclick="setorTimeScope(1)" style="cursor: pointer; <%=orTimeScope == 1 ? "color:blue;" : "" %>">昨日</label></li>
							<li><label onclick="setorTimeScope(2)" style="cursor: pointer; <%=orTimeScope == 2 ? "color:blue;" : "" %>">最近7天</label></li>
							<li><label onclick="setorTimeScope(3)" style="cursor: pointer; <%=orTimeScope == 3 ? "color:blue;" : "" %>">上周</label></li>
							<li><label onclick="setorTimeScope(4)" style="cursor: pointer; <%=orTimeScope == 4 ? "color:blue;" : "" %>">最近30天</label></li>
							<li><label onclick="setorTimeScope(5)" style="cursor: pointer; <%=orTimeScope == 5 ? "color:blue;" : "" %>">上月</label></li>
							<li>
                自定义时间(下单时间):<input type="checkbox" name="isTime" id="isTime" value="1" <%=orTimeScope == 6 ? "checked=\"checked\"" : ""%> onchange="checkScope(this)"/>
              </li>
              <li id="ordefTime" style="<%=orTimeScope != 6 ? "display:none;" : ""%>">
								<input class="date textInput" type="text" hide="false" size="10" value="<%=beginTime1 %>" name="beginTime1" id="orbeginTime">
								-
								<input class="date textInput" type="text" hide="false" size="10" value="<%=endTime1 %>" name="endTime1" id="orendTime1">
              </li>
              <li>&nbsp;&nbsp;&nbsp;对比:<input type="checkbox" name="isCompare" id="isCompare" value="1" <%=isCompare == 1 ? "checked=\"checked\"" : ""  %> onchange="checkCompare(this)" /></li>
              <li id="goCompare" style="<%=isCompare != 1 ? "display:none;" : ""%>"> 
								<input class="date textInput" type="text" hide="false" size="10" value="<%=beginTime2 %>" name="beginTime2" id="orbeginTime2">
								-
								<input class="date textInput" type="text" hide="false" size="10" value="<%=endTime2 %>" name="endTime2" id="orendTime2">
							</li>
							<li>
  							<label>查询方式</label>
  							<select name="timeType">
  							  <option value="0" <%=timeType == 0 ? "selected='selected'" : "" %>>按日</option>
  							  <option value="1" <%=timeType == 1 ? "selected='selected'" : "" %>>按周</option>
  							  <option value="2" <%=timeType == 2 ? "selected='selected'" : "" %>>按月</option>
  							</select>
					  	</li>
              			
						<!-- <li><input type="radio" name="commodityType" value="0" <%= commodityType == 0 ? "checked=\"checked\"" : "" %>/>浏览量&nbsp;&nbsp; </li>
              			<li><input type="radio" name="commodityType" value="1" <%= commodityType == 1 ? "checked=\"checked\"" : "" %>/>销量&nbsp;&nbsp; </li>
              			<li><input type="radio" name="commodityType" value="2" <%= commodityType == 2 ? "checked=\"checked\"" : "" %>/>销售额&nbsp;&nbsp; </li>
              			<li><input type="radio" name="commodityType" value="3" <%= commodityType == 3 ? "checked=\"checked\"" : "" %>/>评论数&nbsp;&nbsp; </li>-->
						
						
						</ul>
					</div>
							<div class="searchBar">
						<ul class="searchContent">
<li><input type="radio" name="sourceType" value="0" <%= sourceType == 0 ? "checked=\"checked\"" : "" %>/>订单量&nbsp;&nbsp; </li>
              			<li><input type="radio" name="sourceType" value="1" <%= sourceType == 1 ? "checked=\"checked\"" : "" %>/>订单金额&nbsp;&nbsp; </li>
              			<li><input type="radio" name="sourceType" value="2" <%= sourceType == 2 ? "checked=\"checked\"" : "" %>/>平均订单金额&nbsp;&nbsp; </li>
						</ul>
						</div>
					<div class="searchBar">
						<ul class="searchContent">
							<li>
							价格范围：
							<input type="text" value="<%=minPrice %>" name="minPrice" id="minPrice"/>-
							<input type="text" value="<%=maxPrice %>" name="maxPrice" id="maxPrice"/>
						<font color="#3071e1">(请填写有效数字)</font> </li>
						<li>
            <label>
            选择门店
            </label>
            <input type="hidden" value="<%=storeIDs %>" lookupgroup="OSTORESELECT" name="OSTORESELECT.STORE_ID">
            <input type="text" class="readonly textInput" readonly="readonly" value="<%=storeName%>" lookupgroup="OSTORESELECT" 
            name="OSTORESELECT.STORE_NAME" style="width:80px;">
            <a id="OSTORESELECTLink" class="btnLook" width="800" height="450" 
            href="/store/store_main.htm?lookupMode=STORE_ID,STORE_NAME,cid_storeList&amp;popsize=800,450&amp;poptarget=OSTORESELECTLink" 
            lookupgroup="OSTORESELECT">选择</a>
            </li>
						</ul>
					</div>
					<div class="searchBar">
						<ul class="searchContent">
						<li>
						</li>
						</ul>
					</div>	
					<div class="subBar">
						<ul style="float: right;">
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<input type="button" value="查询" class="searchButton" _windownav="navTab,m_11032" _prewindownav="navTab,main" _actionid="ListForm.qrybutton" onclick="doFormPost(this,'pagerForm')" url="" id="order_qrybutton" name="ListForm_qrybutton"/>
									</div>
								</div>
							</li>
						</ul>
					</div>
					
					</div>
					
					<div class="pageContent" layouth="110">
					<%=orderS %>
         
          <div layouth="400" style=" width:1000px; overflow: auto; height: 354px;padding-top: 60px; padding-left: 10px;">
         
        </div>
        </div>
        
    </form>
  </body>
</html>
	