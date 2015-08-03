<%@page import="com.ga.click.util.GaUtil"%>
<%@page import="com.ga.click.mvc.UserACL"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.ga.click.util.charts.ChartsContainer"%>
<%@ page import="com.ga.erp.biz.statistic.util.NStatisticUtil"%>
<%@ page import="com.ga.erp.biz.statistic.member.MemGroupBiz"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme() + "://"
      + request.getServerName() + ":" + request.getServerPort()
      + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%
      //状态
      int timeScope = 0; //0：今日 1：昨日  2：最近七天 3：上周  4：上月   5：最近30天  6：自定义时间
      int sourceType = 0; //0:性别 1 年龄 
      String sequence = "desc"; //desc:降序 asc:升序
      int commodityType = 0; //0：销量 2：销售额 
      int top = 10; //10： 显示10条  50： 显示50条  100：显示100条
      int count = 0; //小计
      int count1 = 0;
      String countStr = "0"; //小计
      String count1Str = "0";

      //时间
      String beginTime1 = "";
      String endTime1 = "";
      String beginTime2 = "";
      String endTime2 = "";
      //查询时间
      String beginTimeSelect1 = "";
      String endTimeSelect1 = "";
      String beginTimeSelect2 = "";
      String endTimeSelect2 = "";
      //价格
      String minPrice = "";
      String maxPrice = "";
      //查询价格
      Long minPriceSelect = 0L;
      Long maxPriceSelect = 0L;

      NStatisticUtil s = new NStatisticUtil();
      Map<String, Object> paras = s.ProcessCondition(request);
      //获取处理后的值
      beginTime1 = String.valueOf(paras.get("beginTimeStr1"));
      endTime1 = String.valueOf(paras.get("endTimeStr1"));
      beginTime2 = String.valueOf(paras.get("beginTimeStr2"));
      endTime2 = String.valueOf(paras.get("endTimeStr2"));
      timeScope = Integer
          .parseInt(String.valueOf(paras.get("timeScope")));
      sourceType = Integer.parseInt(String.valueOf(paras
          .get("sourceType")));
      sequence = String.valueOf(paras.get("sequence"));
      commodityType = Integer.parseInt(String.valueOf(paras
          .get("commodityType")));
      top = Integer.parseInt(String.valueOf(paras.get("top")));
      minPrice = String.valueOf(paras.get("minPriceStr"));
      maxPrice = String.valueOf(paras.get("maxPriceStr"));

      minPriceSelect = Long.parseLong(String.valueOf(paras
          .get("minPrice")));
      maxPriceSelect = Long.parseLong(String.valueOf(paras
          .get("maxPrice")));
      beginTimeSelect1 = String.valueOf(paras.get("beginTime1"));
      endTimeSelect1 = String.valueOf(paras.get("endTime1"));
      
    //选择的门店
      String storeIDs="";
      storeIDs =request.getParameter("MGSTORESELECT.STORE_ID");
      String storeName=request.getParameter("MGSTORESELECT.STORE_NAME");
      storeName=GaUtil.isNullStr(storeName)?"":storeName;
      //权限
       UserACL userACL=(UserACL)session.getAttribute("userAcl");
       
      if(GaUtil.isNullStr(storeIDs)&&!userACL.isAllOp()){
        storeIDs=userACL.getStoreID();
      }
      
      //折扣查询
      Integer ratio = userACL.getDiscRatio();
      
      //执行查询
      List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
      
      MemGroupBiz biz = new MemGroupBiz(beginTimeSelect1,endTimeSelect1,sequence, top, commodityType, sourceType,mapList,storeIDs,ratio);
    
      ChartsContainer charts =biz.queryMemberGroup();
    %>
    <script type="text/javascript">
var oldScope= <%=timeScope%>;     //保持原始时间范围数据
    function setTimeScope(type){
      $("#mgbeginTime").val("");    //开始时间
      $("#mgendTime").val("");      //结束时间
      $("#mgScope").val(type);   //查询时间    
      $("#mg_qbutton").click();
    }
    function checkSaleScope(o) {
      if(o.checked) {
        $("#mgScope").val("6");      //查询时间为自定义时间
        $("#mgdefTime").css("display","block");
      } else {
        $("#mgdefTime").css("display","none");
        $("#mgScope").val(oldScope);
      }
    }
    function checkCompare(o) {
      if(o.checked) {
        $("#salgoCompare").css("display","block");
      } else {
        $("#salgoCompare").css("display","none");
      }
    }
    function check() {
      $("#saldef").css("display","block");
    }
     function check1() {
      $("#saldef").css("display","none");
    }
  </script>
  </head>

  <body>

    <form action="/statistic/member/MemGroup.jsp?m_11013"
      id="memberGroupForm" name="pagerForm" method="post">
      <input type="hidden" value="<%=timeScope%>" name="timeScope"
        id="mgScope" />
      <div class="pageHeader">
        <div class="searchBar">
           <ul class="searchContent">
            <li>
              <label onclick="setTimeScope(0)"
                style="cursor: pointer; <%=timeScope == 0 ? "color:blue;" : ""%>">
                今日
              </label>
            </li>
            <li>
              <label onclick="setTimeScope(1)"
                style="cursor: pointer; <%=timeScope == 1 ? "color:blue;" : ""%>">
                昨日
              </label>
            </li>
            <li>
              <label onclick="setTimeScope(2)"
                style="cursor: pointer; <%=timeScope == 2 ? "color:blue;" : ""%>">
                最近7天
              </label>
            </li>
            <li>
              <label onclick="setTimeScope(3)"
                style="cursor: pointer; <%=timeScope == 3 ? "color:blue;" : ""%>">
                上周
              </label>
            </li>
            <li>
              <label onclick="setTimeScope(4)"
                style="cursor: pointer; <%=timeScope == 4 ? "color:blue;" : ""%>">
                上月
              </label>
            </li>
            <li>
              <label onclick="setTimeScope(5)"
                style="cursor: pointer; <%=timeScope == 5 ? "color:blue;" : ""%>">
                最近30天
              </label>
            </li>
            <li>
              自定义时间:
              <input type="checkbox" name="isTime" id="isTimeSale" value="1"
                <%=timeScope == 6 ? "checked=\"checked\"" : ""%>
                onchange="checkSaleScope(this)" />
            </li>
            <li id="mgdefTime"style="<%=timeScope != 6 ? "display:none;" : ""%>">
              <input class="date textInput" type="text" hide="false" size="10"
                value="<%=beginTime1%>" name="beginTime1" id="mgbeginTime">
              -
              <input class="date textInput" type="text" hide="false" size="10"
                value="<%=endTime1%>" name="endTime1" id="mgendTime">
            </li>

          </ul>
        </div>
        <div class="searchBar">
          <ul class="searchContent">
          
          <li>
              <label>
                选择查询类型:
              </label>
              <select name="sourceType">
               
                <option value=0 <%=sourceType == 0 ? "selected" : ""%>
                  onclick="check()">
                 性别
                </option>
                <option value=1 <%=sourceType == 1 ? "selected" : ""%>
                  onclick="check1()">
                 年龄段
                </option>
                
              </select>
            </li>
          
             <li>
              <label>
                选择查询方式:
              </label>
              <select name="commodityType">
               
                <option value=0 <%=commodityType == 0 ? "selected" : ""%>
                  onclick="check()">
                 客单量
                </option>
                <option value=1 <%=commodityType == 1 ? "selected" : ""%>
                  onclick="check1()">
                客单价
                </option>
                
              </select>
            </li>
            
            <li>
              <label>
                排序方式
              </label>
              <select name="orderType">
                <option value="desc"
                  <%="desc".equals(sequence) ? "selected='selected'" : ""%>>
                  降序
                </option>
                <option value="asc"
                  <%="asc".equals(sequence) ? "selected='selected'" : ""%>>
                  升序
                </option>
              </select>
            </li>
            <li>
              <label>
                显示条数
              </label>
              <select name="topType">
                <option value="10" <%=top == 10 ? "selected='selected'" : ""%>>
                  5条
                </option>
                <option value="15" <%=top == 15 ? "selected='selected'" : ""%>>
                  10条
                </option>
                <option value="20" <%=top == 20 ? "selected='selected'" : ""%>>
                  15条
                </option>
              </select>
            </li>
            <li>
            <label>
            选择门店
            </label>
            <input type="hidden" value="<%=storeIDs %>" lookupgroup="MGSTORESELECT" name="MGSTORESELECT.STORE_ID">
            <input type="text" class="readonly textInput" readonly="readonly" value="<%=storeName%>" lookupgroup="MGSTORESELECT" 
            name="MGSTORESELECT.STORE_NAME" style="width:80px;">
            <a id="MGSTORESELECTLink" class="btnLook" width="800" height="450" 
            href="/store/store_main.htm?lookupMode=STORE_ID,STORE_NAME,cid_storeList&amp;popsize=800,450&amp;poptarget=MGSTORESELECTLink" 
            lookupgroup="MGSTORESELECT">选择</a>
            </li>
          </ul>
          
        </div>
        <div class="subBar">
          <ul style="float: right;">
            <li>
              <div class="buttonActive">
                <div class="buttonContent">
                  <input type="button" value="查询" class="searchButton"
                    _windownav="navTab,m_11013" _prewindownav="navTab,main"
                    _actionid="ListForm.qrybutton"
                    onclick="doFormPost(this,'memberGroupForm')" url=""
                    id="mg_qbutton" name="ListForm_qrybutton" />
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div class="pageContent" style="overflow: auto;" layouth="90">
      <%=charts %>
        <div layouth="400" style=" width:1000px; overflow: auto; height: 354px;padding-top: 60px; padding-left: 10px;">
          <table class="list" style="width: 718px; float: left;">
          <thead align="center">
            <tr target="row_data" rel="{}" class="">
              <td>查询类型</td>
              <td>累计人数</td>
              <td>订单总量</td>
              <td>累计消费金额</td>
              <td>客单量</td>
              <td>客单价</td>
            </tr>
          </thead>
          <tbody>
          <% //for()循环map list 获取值
          
          for(Map<String ,Object> map:mapList){
          %>
            <tr target="row_data" rel="{}" class="">
              <td><%=map.get("source")%></td>
               <td><%=map.get("TOTAL_MEMBER")%></td>
               <td><%=map.get("TOTAL_ORDER")%></td>
              <td><%=map.get("TOTAL_MONEY")%></td>
               <td><%=map.get("AVG_ORDER")%></td>
              <td><%=map.get("AVG_MONEY")%></td>
            </tr>
            <%} %>
          </tbody>
          
        </table>
        </div>
      </div>
    </form>
  </body>
</html>
