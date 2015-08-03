<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<script type="text/javascript"  src="/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="/js/jquery.linkagesel-min.js"></script>
<select id="area"></select>
<input type="hidden" name="areaId" id="areaId" value=""/>
<script type="text/javascript">
jQuery(document).ready(function() {
var opts = {
            head:'请选择',
			autoLink:false,
            ajax: '/queryArea.do',
            selStyle: 'margin-left: 3px;',
            select: '#area'
    };
             
    var linkageSel3 = new LinkageSel(opts);
		linkageSel3.changeValues([${areaId}],true);
      linkageSel3.onChange(function() {
        var areaId = linkageSel3.getSelectedValue();
        $("#areaId").val(areaId);
    });
  	
    
    });

</script>