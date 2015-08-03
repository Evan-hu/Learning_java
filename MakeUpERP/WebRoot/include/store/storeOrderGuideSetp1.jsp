<%@page import="com.ga.erp.util.SystemUtil"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.ga.click.dbutil.DbUtils"%>
<%@page import="com.ga.erp.biz.activity.activity.ActivityParamEditView"%>
<%@page import="com.ga.click.util.GaUtil"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<script>
  function subBtn(){
	  var btime = $("#btime").val();
	  var etime = $("#etime").val();
	  ajaxLoad("/store/toOrder_detail.htm?type="+$('input[name="type"]:checked').val()+"&sellType="+$('input[name="sellType"]:checked').val()+"&btime="+btime+"&etime="+etime);
  }
</script>
<style>
td {
  line-height: 30px;
}
</style>

<form id="formView" class="pageForm required-validate" action="/activity/activity_param_edit.htm" name="formView" method="post" novalidate="novalidate">
<fieldset >
<div class="tableform1">
     <div id="auto" class="division">
  		<table style="width: 100%;" >
  		<tr>
  		  <td >
 		    <input type="radio" name="type" id="radio1" checked="checked" value="1"/><label for="radio1"><b>按库存量指标自动补货</b></label>
 		    <br/>
		 	对当前库存量低于库存指标中设置的[库存下限]商品，按[库存上限]补货
     		<br/>
                                          备注：减去在途商品数
  		  </td>
  		</tr>
  		<tr>
  		  <td>
	         <input type="radio" name="type" id="radio2" value="2"/><label for="radio2"><b>按安全库存自动补货</b></label>
	        <br/>
	                           安全库存是一个动态指标，一般采用最近四周平均销量*采购周期得到。
	        <br/>
	                            使用该方法补货至少要有四周的销售数据
	        <br/>
  		  </td>
  		</tr>
      <tr>
  		  <td>
 		    <input type="radio" name="type" id="radio3" value="3"/><label for="radio3"><b>手工输入补货商品</b></label>
	        <br/>
	                          人工录入需要补货的商品品种、数量，系统自动按供应商生成采购订单。
	        <br/>
  		  </td>
  		</tr>
       <tr>
  		  <td>
  		     <input type="radio"  name="type" id="radio4" value="1"/><label for="radio4"><b>按销量补货</b></label><br><input type="radio" value="2" name="sellType" checked="checked">补货数量=销量</input>  <input type="radio" value="3" name="sellType">补货数量=销量-当前库存</input><input 

type="radio" value="" name="sellType">补货数量=销量-当前库存+库存下限</input>
        	<br/>
	       	指定日期范围的销售数量为补货数量
	        <input type="text" id="btime" class="date textInput readonly" readonly="true">
	        -
	        <input type="text" readonly="true" format="yyyy-MM-dd HH:mm:ss" class="date readonly textInput" 
	        hide="false" size="20" value="" id="etime">
        <br/>
  		</tr>
         </table>              
    </div>
  </div>
  </fieldset>
  <div  style="width: 100%;text-align: center;">
  <table cellspacing="0" cellpadding="0" border="0" align="center" class="tableAction">
<tbody><tr>
<td style="text-align: center;">
<b class="submitBtn"><input type="button" value="下一步"  onclick="subBtn();"></b></td></tr></tbody>
</table>
</div>
</form>