<%@page import="com.ga.erp.util.SystemUtil"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.ga.click.dbutil.DbUtils"%>
<%@page import="com.ga.erp.biz.activity.activity.ActivityParamEditView"%>
<%@page import="com.ga.click.util.GaUtil"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%
   long activityId = Long.parseLong(request.getParameter("DISCOUNT_ACTIVITY_ID"));
   long paramId = 0;
   if(!GaUtil.isNullStr(request.getParameter("_rowdata"))){
	   JSONObject json = new JSONObject(request.getParameter("_rowdata"));
	   if(json != null ){  
	     paramId = Long.parseLong(String.valueOf(json.get("DISCOUNT_PARAM_ID")));
	   }
   }
   
   boolean isAdd = paramId == 0;
   Integer type = 0;
   Integer commodityType = 0;
   Integer discountType = 0;
   Map<String,Object> paramMap = new HashMap<String,Object>();
   Map<String,Object> map = DbUtils.queryMap(DbUtils.getConnection(),"select TYPE,COMMODITY_RANGE_TYPE from DISCOUNT_ACTIVITY where DISCOUNT_ACTIVITY_ID = ?",activityId);
   type = (Integer)map.get("TYPE");
   commodityType = (Integer)map.get("COMMODITY_RANGE_TYPE");
   if(!isAdd){
     paramMap = DbUtils.queryMap(DbUtils.getConnection(),"select * from DISCOUNT_PARAM where DISCOUNT_PARAM_ID = ?",paramId);
     discountType =  (Integer)paramMap.get("DISCOUNT_TYPE");
   }
   
   Map<Integer,String> typeHtmlMap = new HashMap<Integer,String>();
   typeHtmlMap.put(1, "<option value='4'>满金额打折</option><option value='5'>满数量打折</option>");//活动类型为折扣
   typeHtmlMap.put(2, "<option value='3'>指定商品特价</option>");//活动类型为特价
   typeHtmlMap.put(3, "<option value='1'>满金额加钱赠送商品</option><option value='9'>满数量加钱赠送商品</option><option value='2'>满金额减免固定金额</option><option value='7'>满金额赠送优惠券</option>");//活动类型为买满送
   typeHtmlMap.put(4, "<option value='9'>组合商品</option>");//活动类型为组合商品
   
%>
<script type="text/javascript">
    function allHide(){
     $("#MIN_MONEY").hide();
     $("#MIN_COUNT").hide();
     $("#VALUE").hide();
     $("#ADD_MONEY").hide();
     $("#COMMODITY_AMOUNT").hide(); 
     $("#cardBatch").hide();
     $("#commodity").hide();
    }
	function typeChange(t){
	  var type = parseInt(t.val());
	  allHide();
	  switch(type){
	    case 1:
	    
	        $("#MIN_MONEY").show();
	        $("#ADD_MONEY").show();
	         $("#commodity").show();
	    break;
	    case 2:
	       $("#MIN_MONEY").show();
	       $("#VALUE").show();
	       $("#VALUE_TEXT").text("减免金额");
	    break;
	    case 3:
	   	   $("#VALUE").show();
	       $("#VALUE_TEXT").text("商品特价");
	       
	    break;
	    case 4:
	     	$("#MIN_MONEY").show();
	    	$("#VALUE").show();
	     	$("#VALUE_TEXT").text("折扣");
	    break;
	    case 5:
	    	$("#MIN_COUNT").show();
	    	$("#VALUE").show();
	     	$("#VALUE_TEXT").text("折扣");
	    break;
	    case 6:
	    	$("#VALUE").show();
	     	$("#VALUE_TEXT").text("折扣数");
	    break;
	    case 7:
	    	 $("#MIN_MONEY").show();
	    	 $("#cardBatch").show();
	    break;
	    case 8:
	    	$("#VALUE").show();
	     	$("#VALUE_TEXT").text("折扣数");
	    break;
	    case 9:
	    	$("#MIN_COUNT").show();
	        $("#ADD_MONEY").show();
	        $("#commodity").show();
	    break;
	  }
	}
</script>
<form id="formView" class="pageForm required-validate" action="/activity/activity_param_edit.htm" name="formView" method="post" novalidate="novalidate">

<div class="tableform1">
    <div id="auto" class="division">
       <table width="100%" cellspacing="0" cellpadding="0" border="0">
          <tbody>          <tr>
            <th>参数名称</th>
            <td><input type="text" size="20" id="formView_PARAM_NAME" name="PARAM_NAME" class="textInput" value="<%=SystemUtil.MapValuetoString(paramMap, "PARAM_NAME") %>"></td>
          </tr>
          <tr>
          <th>优惠方式</th>
            <td>
                <select size="1" id="formView_DISCOUNT_TYPE" name="DISCOUNT_TYPE" class="valid" onchange="typeChange($(this));">
		          <%=typeHtmlMap.get(type) %>
	            </select> 
	            <input type="hidden" size="20" value="<%=activityId %>" id="formView_DISCOUNT_ACTIVITY_ID" name="DISCOUNT_ACTIVITY_ID" class="textInput" >
	            <input type="hidden" size="20" value="<%=type %>" id="formView_TYPE" name="TYPE" class="textInput" >
	            <input type="hidden" size="20" value="<%=paramId %>" id="formView_DISCOUNT_PARAM_ID" name="DISCOUNT_PARAM_ID" class="textInput" >
	            <input type="hidden" size="20" value="<%=commodityType %>" id="formView_COMMODITY_RANGE_TYPE" name="COMMODITY_RANGE_TYPE" class="textInput" >
            </td>
          </tr>
          <% if(commodityType>1){ %>
            <%if(commodityType == 2){ %>
            <tr>
            <th>分类名称</th>
             <td><input type="text" name="NEWSORTSELECT.SORT_NAME" lookupgroup="NEWSORTSELECT" id="SORT_NAME"   value="<%=SystemUtil.MapValuetoString(paramMap, "TARGET_NAME") %>" readonly="readonly" class="readonly textInput"><a lookupgroup="NEWSORTSELECT" href="/comm/sort_sele.htm?lookupMode=SORT_ID,SORT_NAME,cid_sortTree&amp;popsize=200,300&amp;poptarget=NEWSORTSELECTLink" height="300" width="200" class="btnLook" id="NEWSORTSELECTLink">选择</a></td>
             <input type="hidden" size="20"  name="NEWBRANDSELECT.SORT_ID" id="SORT_ID" lookupgroup="NEWBRANDSELECT" class="textInput" value="<%=SystemUtil.MapValuetoString(paramMap, "TARGET_ID") %>">      
			</tr>
			<%} %>
			<%if(commodityType == 3) {%>
			<tr>
            <th>品牌名称</th>
             <td><input type="text" name="NEWBRANDSELECT.NAME" lookupgroup="NEWBRANDSELECT"  id="NAME" value="<%=SystemUtil.MapValuetoString(paramMap, "TARGET_NAME") %>" readonly="readonly" class="readonly textInput"><a lookupgroup="NEWBRANDSELECT" href="/comm/brand_main.htm?lookupMode=BRAND_ID,NAME,cid_brandList&amp;popsize=800,300&amp;poptarget=NEWBRANDSELECTLink" height="300" width="800" class="btnLook" id="NEWBRANDSELECTLink">选择</a>
             <input type="hidden" size="20"  name="NEWBRANDSELECT.BRAND_ID" id="BRAND_ID" lookupgroup="NEWBRANDSELECT" class="textInput" value="<%=SystemUtil.MapValuetoString(paramMap, "TARGET_ID") %>">
			</td>
			</tr>
			<%} %>
			<%if(commodityType == 4){ %>
          <tr>
            <th>商品名称</th>
            <td><input type="text" name="selectCommodity.COMMODITY_NAME" lookupgroup="selectCommodity" id="COMMODITY_NAME"  value="<%=SystemUtil.MapValuetoString(paramMap, "TARGET_NAME") %>"  readonly="readonly" class="readonly textInput"><a lookupgroup="selectCommodity" href="/comm/comm_main.htm?lookupMode=COMMODITY_ID,COMMODITY_NAME,cid_commList&amp;popsize=800,500&amp;poptarget=selectCommodityLink" height="500" width="800" class="btnLook" id="selectCommodityLink">选择</a></td>
            <input type="hidden" size="20"  name="selectCommodity.COMMODITY_ID" id="COMMODITY_ID" lookupgroup="selectCommodity" class="textInput" value="<%=SystemUtil.MapValuetoString(paramMap, "TARGET_ID") %>">
          </tr>
           <%} %>
			
         <%}%>
         
         <tr id="MIN_MONEY">
            <th>买满金额</th>
            <td><input type="text" size="20" value="<%=SystemUtil.MapValuetoMoney(paramMap,"MIN_MONEY") %>" id="formView_MIN_MONEY" name="MIN_MONEY" class="textInput" ></td>
          </tr>
        
          <tr id="MIN_COUNT">
            <th>买满数量</th>
            <td><input type="text" size="20" value="<%=SystemUtil.MapValuetoString(paramMap,"MIN_COUNT") %>" id="formView_MIN_COUNT" name="MIN_COUNT" class="textInput"></td>
          </tr>
         <tr id="VALUE">
           <th id="VALUE_TEXT">优惠值</th>
            <td><input type="text" size="20" value="<%=SystemUtil.MapValuetoMoney(paramMap,"VALUE") %>" id="formView_VALUE" name="VALUE" class="textInput"></td>
         </tr>
          <tr id="ADD_MONEY">
            <th>附加金额</th>
            <td><input type="text" size="20" value="<%=SystemUtil.MapValuetoMoney(paramMap,"ADD_MONEY") %>" id="formView_ADD_MONEY" name="ADD_MONEY" class="textInput"></td>
          </tr>
            <%
          if(type == 3){ %>
            <tr id="cardBatch" style="display: none;">
            <th>电子券批次</th>
            <td><input type="text" name="selectCardBatch.NAME" lookupgroup="selectCardBatch" id="NAME"  value="<%=SystemUtil.MapValuetoString(paramMap, "TARGET_NAME") %>"  readonly="readonly" class="readonly textInput"><a lookupgroup="selectCardBatch" href="/activity/cardbatch_main.htm?lookupMode=CARD_BATCH_ID,NAME,cid_batchListView&amp;popsize=800,500&amp;poptarget=selectCardBatchLink" height="500" width="800" class="btnLook" id="selectCardBatchLink">选择</a></td>
            <input type="hidden" size="20"  name="selectCardBatch.CARD_BATCH_ID" id="CARD_BATCH_ID" lookupgroup="selectCardBatch" class="textInput" value="<%=SystemUtil.MapValuetoString(paramMap, "TARGET_ID") %>">
          </tr>
          <tr id="commodity" style="display: none;">
            <th>赠送商品</th>
            <td><input type="text" name="selectCommodity.COMMODITY_NAME" lookupgroup="selectCommodity" id="COMMODITY_NAME"  value="<%=SystemUtil.MapValuetoString(paramMap, "TARGET_NAME") %>"  readonly="readonly" class="readonly textInput"><a lookupgroup="selectCommodity" href="/comm/comm_main.htm?lookupMode=COMMODITY_ID,COMMODITY_NAME,cid_commList&amp;popsize=800,500&amp;poptarget=selectCommodityLink" height="500" width="800" class="btnLook" id="selectCommodityLink">选择</a></td>
            <input type="hidden" size="20"  name="selectCommodity.COMMODITY_ID" id="COMMODITY_ID" lookupgroup="selectCommodity" class="textInput" value="<%=SystemUtil.MapValuetoString(paramMap, "TARGET_ID") %>">
          </tr>
         <%} %>
          <tr id="COMMODITY_AMOUNT">
            <th>商品折扣件数</th>
            <td><input type="text" size="20" value="<%=SystemUtil.MapValuetoString(paramMap,"COMMODITY_AMOUNT") %>" id="formView_COMMODITY_AMOUNT" name="COMMODITY_AMOUNT" class="textInput"></td>
          </tr>
          <tr >
            <th>状态</th>
            <td><select size="1" id="formView_STATE" name="STATE"><option value="1">正常</option><option value="0">无效</option></select></td>
          </tr>
          
          <tr>
            <th>备注</th>
            <td colspan="3"><textarea style="width:300px;" cols="20" rows="3" id="formView_NOTE" name="NOTE" class="textInput"><%=SystemUtil.MapValuetoString(paramMap,"NOTE") %></textarea></td>
          </tr>
         </tbody><tbody>
      </tbody></table>
    </div>
  </div>
  <table cellspacing="0" cellpadding="0" border="0" align="center" class="tableAction">
<tbody><tr>
<td><b class="submitBtn"><input type="button" value="保存" _windownav="mvc,0" _prewindownav="dialog,paramAdd" param="{'isSave':'true','editMode':'1','_actionid':'save'}" _actionid="save" _pageaction="doController" onclick="doFormPost(this,'formView')" url="/activity/activity_param_edit.htm" id="save"></b></td></tr></tbody>
</table>
<% if(isAdd){%>
<script type="text/javascript">
  typeChange($("#formView_DISCOUNT_TYPE"));
  </script>
<%}else{%>
<script type="text/javascript">
  $("#formView_DISCOUNT_TYPE").val("<%=discountType%>");
  typeChange($("#formView_DISCOUNT_TYPE"));
  </script>
<%} %>
</form>