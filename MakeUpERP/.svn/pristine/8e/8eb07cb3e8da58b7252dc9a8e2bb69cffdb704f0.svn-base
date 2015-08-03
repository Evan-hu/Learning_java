<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.ga.click.util.GaUtil"%>
<%@page import="com.ga.click.mvc.UserACL"%>
<%@ page import="com.ga.click.util.charts.ChartsContainer"%>
<%@ page import="com.ga.erp.biz.statistic.util.NStatisticUtil"%>
<%@ page import="com.ga.erp.biz.statistic.store.SaleBiz"%>
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
      String sequence = "desc"; //desc:降序 asc:升序
      int shop = 2; //2：双楠  4：建设  5：高新 0:不区分
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
      sequence = String.valueOf(paras.get("sequence"));
      shop = Integer.parseInt(String.valueOf(paras.get("shop")));
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
      beginTimeSelect2 = String.valueOf(paras.get("beginTime2"));
      endTimeSelect2 = String.valueOf(paras.get("endTime2"));

      //选择的门店
      String storeIDs="";
      storeIDs =request.getParameter("HTSTORESELECT.STORE_ID");
      String storeName=request.getParameter("HTSTORESELECT.STORE_NAME");
      storeName=GaUtil.isNullStr(storeName)?"":storeName;
      //权限
      UserACL userACL=(UserACL)session.getAttribute("userAcl");
       
      if(GaUtil.isNullStr(storeIDs)&&!userACL.isAllOp()){
        storeIDs=userACL.getStoreID();
      }
    //折扣查询
      Integer ratio = userACL.getDiscRatio();
    
      //执行查询
      SaleBiz biz = new SaleBiz(beginTimeSelect1, endTimeSelect1,
          sequence, top, commodityType,storeIDs,ratio);
    ChartsContainer charts =biz.queryHotSaleComm();
    %>
    <script type="text/javascript">
var oldScope= <%=timeScope%>;     //保持原始时间范围数据
    function setTimeScope(type){
        $("#beginTime1").val("");    //开始时间
      $("#endTime1").val("");      //结束时间
      $("#beginTime2").val("");    //对比开始时间
      $("#endTime2").val("");    //对比结束时间
      $("#saleTimeScope").val(type);   //查询时间    
      $("#popular_qrybutton").click();
    }
    function checkSaleScope(o) {
      if(o.checked) {
        $("#saleTimeScope").val("6");      //查询时间为自定义时间
        $("#saldefTime").css("display","block");
      } else {
        $("#saldefTime").css("display","none");
        $("#saleTimeScope").val(oldScope);
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

    <form action="/statistic/store/HotComm.jsp?m_11033"
      id="popularForm" name="pagerForm" method="post">
      <input type="hidden" value="<%=timeScope%>" name="timeScope"
        id="saleTimeScope" />
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
            <li id="saldefTime"style="<%=timeScope != 6 ? "display:none;" : ""%>">
              <input class="date textInput" type="text" hide="false" size="10"
                value="<%=beginTime1%>" name="beginTime1" id="salebeginTime1">
              -
              <input class="date textInput" type="text" hide="false" size="10"
                value="<%=endTime1%>" name="endTime1" id="salendTime1">
            </li>

          </ul>
        </div>
        <div class="searchBar">
          <ul class="searchContent">
             <li>
              <label>
                请选择:
              </label>
              <select name="commodityType">
               
                <option value=0 <%=commodityType == 0 ? "selected" : ""%>
                  onclick="check()">
                 销售数量
                </option>
                <option value=1 <%=commodityType == 1 ? "selected" : ""%>
                  onclick="check1()">
                 商品销售金额
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
            <input type="hidden" value="<%=storeIDs %>" lookupgroup="HTSTORESELECT" name="HTSTORESELECT.STORE_ID">
            <input type="text" class="readonly textInput" readonly="readonly" value="<%=storeName%>" lookupgroup="HTSTORESELECT" 
            name="HTSTORESELECT.STORE_NAME" style="width:80px;">
            <a id="HTSTORESELECTLink" class="btnLook" width="800" height="450" 
            href="/store/store_main.htm?lookupMode=STORE_ID,STORE_NAME,cid_storeList&amp;popsize=800,450&amp;poptarget=HTSTORESELECTLink" 
            lookupgroup="HTSTORESELECT">选择</a>
            </li>
          </ul>
        </div>
        <div class="subBar">
          <ul style="float: right;">
            <li>
              <div class="buttonActive">
                <div class="buttonContent">
                  <input type="button" value="查询" class="searchButton"
                    _windownav="navTab,m_11033" _prewindownav="navTab,main"
                    _actionid="ListForm.qrybutton"
                    onclick="doFormPost(this,'popularForm')" url=""
                    id="popular_qrybutton" name="ListForm_qrybutton" />
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
      <div class="pageContent" style="overflow: auto;" layouth="90">
       <%=charts %>
      </div>
    </form>
  </body>
</html>
