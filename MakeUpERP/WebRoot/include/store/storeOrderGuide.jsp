<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>

<script type="text/javascript">
function ajaxLoad(url){
   $("#auto2").load(url);
}

</script>
<form id="formView" class="pageForm required-validate" action="/activity/activity_param_edit.htm" name="formView" method="post" novalidate="novalidate">

<div class="tabsContent" style="border-width: 0 1px 1px 1px;">
    <div id="auto2">
        <jsp:include page="/include/store/storeOrderGuideSetp1.jsp"></jsp:include>
    </div>
</div>
</form>