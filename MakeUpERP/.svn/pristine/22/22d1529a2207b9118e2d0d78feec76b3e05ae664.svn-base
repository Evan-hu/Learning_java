/**
 * @author 
 */
 jQuery.extend(
 {
  /**
   * @see  将javascript数据类型转换为json字符串
   * @param 待转换对象,支持object,array,string,function,number,boolean,regexp
   * @return 返回json字符串
   */
  toJSON : function (object)
  {
   var type = typeof object;
   if ('object' == type)
   {
    if (Array == object.constructor)
     type = 'array';
    else if (RegExp == object.constructor)
     type = 'regexp';
    else
     type = 'object';
   }
   switch(type)
   {
   case 'undefined':
   case 'unknown':
     return;
     break;
   case 'function':
   case 'boolean':
   case 'regexp':
     return object.toString();
     break;
   case 'number':
     return isFinite(object) ? object.toString() : 'null';
     break;
   case 'string':
     return '"' + object.replace(/(\\|\")/g,"\\$1").replace(/\n|\r|\t/g,
       function(){  
                 var a = arguments[0];                   
        return  (a == '\n') ? '\\n':  
                       (a == '\r') ? '\\r':  
                       (a == '\t') ? '\\t': "" 
             }) + '"';
     break;
   case 'object':
     if (object === null) return 'null';
        var results = [];
        for (var property in object) {
          var value = jQuery.toJSON(object[property]);
          if (value !== undefined)
            results.push(jQuery.toJSON(property) + ':' + value);
        }
        return '{' + results.join(',') + '}';
     break;
   case 'array':
     var results = [];
     for(var i = 0; i < object.length; i++)
     {
        var value = jQuery.toJSON(object[i]);
        if (value !== undefined) results.push(value);
     }
     return '[' + results.join(',') + ']';
     break;
   }
  }
 });
 
document.onkeydown = function(event) {  
	var target, code, tag;  
	if (!event) {  
	    event = window.event; //针对ie浏览器  
	    target = event.srcElement;  
	    code = event.keyCode;  
	    if (code == 13) {  
	        tag = target.tagName;  
	        if (tag == "TEXTAREA") { return true; }  
	        else { return false; }  
	    }  
	}  
	else {  
	    target = event.target; //针对遵循w3c标准的浏览器，如Firefox  
	    code = event.keyCode;  
	    if (code == 13) {  
	        tag = target.tagName;  
	        if (tag == "INPUT") { return false; }  
	        else { return true; }   
	    }  
	}  
 };
 
 function createHidePara(name,value) {
  var paramObj = document.createElement("input"); 
  paramObj.type = "hidden";
  paramObj.name = name;
  paramObj.id = name;
  paramObj.value = value; 
  return paramObj;
}
/**lookup按钮的js**/
function doLookupSelect(callback,target) {   
  var pv = ("{row_data}").replaceTmById($.pdialog.getCurrent());
  if (pv == "{row_data}") {
  	alertMsg.error("请选择信息");
  }  
  pv = pv.replace(/\'/g,"\"");
  var pvobj = eval('('+pv+')');
  if (callback) {
    pvobj.callback = callback.replace("callback_","");
  }
  if (target) {
    pvobj.target = target;
  }
  $.bringBackPop(pvobj);
}

function getMemberAdd(value) {
	//获取JSON格式对象中的会员ID
	var json = DWZ.jsonEval(value);
	document.getElementById("ADDSELECTLink").href = "/order/member_addr.htm?mid="+json.MEMBER_ID+"&lookupMode=ADDR,DELIVERY_ADDR_ID";
}

//多div查找返回
function doLookupSelectDiv(divID,callback,target,ismulti) {
  var $box = $("#" + divID,$.pdialog.getCurrent());
  var pv;
  var pvobj;
  if (!ismulti) {
	//单选
    pv = "{row_data}".replaceTmById($box); //替换表格选中参数
    if (pv == "{row_data}") {
      
  	  alertMsg.error("请选择信息");
  	  return;
    } 
    pv = pv.replace(/\'/g,"\"");
    pv = pv.replace(/\\u2018/g,"'");
    pvobj = eval('('+pv+')');
  } else {
	//多选
  	pvobj = eval('({})');
    $(":checkbox").each(function(){
       if($(this).attr("checked") && $(this).parent().parent().is("tr")) {
    	var tmpv = $(this).parent().parent().attr("rel");
    	if (tmpv) {
	    	tmpv = tmpv.replace(/\'/g,"\"");
	    	tmpv = tmpv.replace(/\\u2018/g,"'");
	    	var vobj = eval('('+tmpv+')');
	    	for(var attr in vobj){
	    	  if (pvobj[attr]) {
	    		  pvobj[attr] = pvobj[attr] + "," + vobj[attr]; 
	    	  } else {
	    		  pvobj[attr] = vobj[attr]; 
	    	  }
    	 }
        }
      }
	});
  }
  if (callback) {
    pvobj.callback = callback.replace("callback_","");
  }
  if (target) {
    pvobj.target = target;
  }
  $.bringBackPop(pvobj);
}

//清除查找选择
function clearLookupSelectDiv(target) {
  $.clearBackSuggestPop(target);
}


function setFormFixV(src,formObj) {
 	formObj.method="post";
	var str = src.getAttribute('param');
	var actionID = src.getAttribute('_actionid');
	var pageAction = src.getAttribute('_pageAction');
	var winNav = src.getAttribute('_windownav');
	var preWinNav = src.getAttribute('_prewindownav');
	var divMode = src.getAttribute('divMode');
	var mvcCallback = src.getAttribute('_mvccallback');
	var json,navType,navTitle,isAjax;
	if (str != null)
	{
	  str.replace(/\'/g,"\"")
	  json = eval("("+str+")");
	  for(var key in json)
  	  {
  	    var pn = key;
  	    var pv = json[pn];
    	//添加表单参数
    	if (pn =="_rowdata") {   
		    var table = pv.replace("{row_data}","");
	   		    if (preWinNav.indexOf("dialog") ==0) {
	   		      if (table) {
	   		        $box = $("#" + table,$.pdialog.getCurrent());
	   		        pv = "{row_data}".replaceTmById($box); //替换表格选中参数
	   		      } else {
	   		        pv = pv.replaceTmById($($.pdialog.getCurrent())); //替换表格选中参数
	   		      }    		  
	    		  } else {
		    		  if (table) {
		   		      $box = $("#" + table,navTab.getCurrentPanel());
		   		      pv = "{row_data}".replaceTmById($box); //替换表格选中参数
		   		    } else {
		   		      pv = pv.replaceTmById($(navTab.getCurrentPanel())); //替换表格选中参数
		   		    }    		  
			  		} 	  	
	   		    
    		if (pv == "{row_data}") {
    			//提示未选中信息
    			if(src.getAttribute('paramValue')!= null && src.getAttribute('paramValue').indexOf("paramValue") != 1){
    			  pv = src.getAttribute('paramValue');
    			  if(pv == "1"){
    				  alertMsg.error("对不起，本条审核信息记录有误，请联系管理员！");
    				  return false;
    			  }
    			}else{
    			  alertMsg.error(DWZ.msg("alertSelectMsg"));
    			  return false;
    			}
    		}
    		pv.replace(/\'/g,"\"");
    	} else if (pn =="_ids") {
    	    //处理多选,获取对应tableid
          var table = pv.replace("{select_ids}","");
          if (table) {
            var str = "";
	   	    if (preWinNav.indexOf("dialog") ==0) {   
	   	      $box = $("#" + table,$.pdialog.getCurrent());		        
	   		  $("[name='_ids']:checked",$box).each(function(){
                 str= str + ","+$(this).val();
              }) 
	        } else {
	          $box = $("#" + table,navTab.getCurrentPanel());
	          $("[name='_ids']:checked",$box).each(function(){
                 str = str + ","+$(this).val();
              }) 		  
	        }
	        if (!str) {
    			//提示未选中信息
    			alertMsg.error("请选中表格中的复选框");
    			return false;
    		} else {
    		  if (str.indexOf(",") == 0) {
    		    str = str.substring(1);
    		  }
    		  pv = str;
    		}
          }
    	}
    	if (formObj.elements && formObj.elements[pn]) {
    	   formObj.elements[pn].value = pv;
    	} else {
    	   formObj.appendChild(createHidePara(pn,pv));
    	}
  	}
	}
 	if (formObj._actionid){
    //如表单有actionid信息
  		formObj._actionid.value = actionID;
  	} else {
  		formObj.appendChild(createHidePara("_actionid",actionID));
  	}
  	if (formObj.divMode){
    	//如表单有divMode信息
  		formObj.divMode.value = divMode;
  	} else {
  		formObj.appendChild(createHidePara("divMode",divMode));
  	}
  	if (formObj._mvccallback){
    	//如表单有mvc回调
  		formObj._mvccallback.value = mvcCallback;
  	} else {
  		formObj.appendChild(createHidePara("_mvccallback",mvcCallback));
  	}
  	if (formObj._windownav) {
    	//有导航信息
   		if (!winNav) {
      	  winNav = formObj._windownav.value;
    	}
  	} else {
    	//无导航信息
    	formObj.appendChild(createHidePara("_windownav",winNav));
  	}
  	if (formObj._prewindownav) {
    	//有导航信息
      	formObj._prewindownav.value = preWinNav;    
  	} else {
    	//无导航信息
    	formObj.appendChild(createHidePara("_prewindownav",preWinNav));
  	}
  	if (pageAction != null && pageAction != "") {
	  formObj.appendChild(createHidePara("pageAction",pageAction));
	  if(winNav.indexOf("custom") < 0){
		  winNav = "ajax,"+divMode;	  	  
	  }else{
		  winNav = src.getAttribute('_windownav');
		  formObj._windownav.value = winNav;
	  }
  	}
  	return true;
}

/**执行表单提交,附加按钮上的固定参数到表单中**/
function doFormPost(src,formName) {
	
  var formObj;
  var preWinNav = src.getAttribute('_prewindownav');
  if (src.getAttribute('beforeClickJS')) {
     eval(src.getAttribute('beforeClickJS')+"()");
  }
  if (formName == "pagerForm") {
     //查询表单必须同名，因此必须通过src获取
     formObj = $(src).parents('#pagerForm')[0];
  } else {
	  if (preWinNav.indexOf("dialog") == 0) {
	     var dlgid = preWinNav.replace("dialog,","");
	     var dialog = $("body").data(dlgid);
	     if (dialog && dialog[0]) {
	       formObj = $("#"+formName,dialog)[0];
	     } else {
	       formObj = $("#"+formName,$.pdialog.getCurrent())[0];
	     }
	  } else {
	     formObj=$("#"+formName,navTab.getCurrentPanel())[0];
	  }
  }
  if (!formObj) {
     formObj = $("#"+formName)[0];
  }  
  if (!formObj.action) {
  	 formObj.action = src.getAttribute('url');
  }
  //校验表单对象
  if (formName != 'pagerForm') {  
	  var result = validateCallback(formObj);
	  if (!result) {
		  return false;
	  }
  }
  
  if (!setFormFixV(src,formObj)) {
  	 return false;
  }
  //校验
  var confirmMsg = src.getAttribute('confirm');
  if (confirmMsg) {
      if (!chkForm(confirmMsg,formObj)) {
        return false;
      } 
  }
  var pTable = src.getAttribute('_parenttable');
  if (pTable) {
	  //不提交,直接将form转为JSON并设置到父表格中
	  postToTable(formObj,pTable,src);
  } else {
	  //提交*/
	  submitActionForm(formObj._actionid.value,formObj,formObj._windownav.value,formObj._prewindownav.value);	  
  } 
  return false;
}

function delMTableRow(tableViewID) {
	//校验是否选择
	var sele = $("#"+tableViewID).find(" tr.selected");
	if (sele.length == 0)  {
		alert("请选中要删除的行");
		return;
	} 
	var pkf = $(sele).find(" input[pk='1']");
	if (pkf.length >0) {		
		//记录
		var pkv = $(pkf).val();
		//设置数据
		var delf = $("#"+tableViewID).find("#mtable_del");
		var oldv = $(delf).val();
		if (oldv && oldv != "") {					
			pkv = pkv + "," + oldv;
		}
		$(delf).val(pkv);
	}
	//记录删除id
	$(sele).remove();
}

function postToTable(formObj,tableViewID,src) {
	var $formObj=$(formObj);
	var data = $formObj.serializeArray();
	//获取指定tableViewID的头定义,获取其对应的列
	var allth =$("#"+tableViewID).find("th");
	var allRow = $("#"+tableViewID).find("tr").length;
	if (!allRow) allRow = 0;
	var trHtml = "<tr  class=\"trbg\">";
	//设置PK的值
	var pkfield = $("#"+tableViewID).find("#pkfield");
	if (!pkfield || pkfield.length == 0) 
	{
		pkfield = "id";
	} else {
		pkfield = pkfield.attr("value");
	}
	//获取PK值
	var pkvalue = getFieldValue(data,pkfield);
	var tbody;
	if (pkvalue != "")  {
		//修改
		var pklist =$("#"+tableViewID).find(" input[pk='1']");		
		pklist.each(function(index,element){ 
			 if ($(this).val() == pkvalue) {
				//获取其索引
				 var itemname = $(this).attr("name");
				 var spos = itemname.indexOf("[");
				 var epos = itemname.indexOf("]");
				 allRow = itemname.substring(spos + 1,epos);
				 //获取其tr对象				 				 
				 tbody = $(this).parent();				 
				 return false;
			 }
		 });
	} else {
		tbody =$("#"+tableViewID).find(" tr:eq(1)");		
	}
	trHtml = trHtml + 	"<input type='hidden' pk='1'  name='items["+allRow+"]."+pkfield+"' value='"+pkvalue+"' />";	  
	//获取formObj的pk字段名称和值
	allth.each(function(index,element){ 
		 var thid = $(this).attr("id");
		 var  fix = "_th_";
		 if (thid) {
			 thid = thid.substr(thid.indexOf(fix)+4);
			 var find = false;
			 var datav = getFieldValue(data,thid);
			 var hctrl = "<input type='hidden' name='items["+allRow+"]."+thid+"' value='"+datav+"'>";
			 if (datav =="") datav ="&nbsp;";
			 trHtml = trHtml +"<td>" +datav+hctrl+"</td>";
		 }
	 });

	//刷新HTML
	if (pkvalue != "")  {
		tbody[0].innerHTML = trHtml;
	} else {		
		if (tbody.length == 0) {
			$("#"+tableViewID).find(" tbody")[0].innerHTML = trHtml;
		} else {
			$("#"+tableViewID+" tr:eq(1)").before(trHtml);
		}
	}

	//获取父窗口ID
	var diav = $(src).attr("_prewindownav");
	if (diav && diav.indexOf("dialog,") == 0) {
		$.pdialog.close(diav.substr(7));
	} else {
		$.pdialog.closeCurrent();
	}
		
}

function getFieldValue(data,name) {
	var fv = "";
	 $.each(data, function(i,val){      			      
	      if (val.name == name) {
	    	  fv = val.value;
	    	  return true;
	      }
	  });
	 return fv;
}

function chkForm(confirmInfo,form) {
  confirmInfo.replace(/\'/g,"\"")
  var json = eval("("+confirmInfo+")");
  if (json) {
     var chk = json["chk"];
     var pass = json["pass"];
     var err = json["err"];
     if (chk) {
       var isrowdata = false;
       var ispass = false;
       if (chk.substring(0,8) == "_rowdata") {
         chk = chk.substring(8);
         isrowdata = true;
       }
       var param = chk.substring(chk.indexOf("[")+1,chk.indexOf("]"));
	   var chkExp = "";
       //执行校验       
       if (isrowdata) {
       	   if (form._rowdata) {
       	      var rowdata = form._rowdata.value;
       	      rowdata.replace(/\'/g,"\"")
       	      var row = eval("("+rowdata+")");
       	      chkExp = row[param];       	  
       	      var regS = new RegExp("\\["+param+"]","g");
       	      chkExp = chk.replace(regS,chkExp);
	          ispass = eval(chkExp);
       	   }
       } else {
	       if (form.param) {
	         chkExp = form.param.value;
	         chkExp = chk.replace("["+param+"]",chkExp);
	         ispass = eval(chkExp);
	       }
       }
       //错误提示
       if (!ispass) {
         if (err) {
         	alertMsg.error(err);
         }
         return false;
       }
     }
     if (pass) {
       //显示提示选择
      if (!confirm(pass)) {
        return false;
      }
     }
     return true;
  }    
}

/***
*以ajax方法提交多表单
*/
function PostMultiForm(src,formName) {
  var confirmMsg = src.getAttribute('confirm');
  if (confirmMsg) {
      if (!confirm(confirmMsg)) {
        return false;
      } 
  }
  var submitForm = document.createElement("form");
  if (!setFormFixV(src,submitForm)) {
    return false;
  }
  submitForm.method="post";
  submitForm.action = src.getAttribute('url');
  submitForm.appendChild(createHidePara("_multiform",src.getAttribute('_multiform'))); 
  
  var preWinNav = src.getAttribute('_prewindownav');
  var formList = formName.split(",");
  //组合各form的输入值
  var valueList = new Array();
  for (var index in formList) {
    var formName = formList[index];
   if (preWinNav.indexOf("dialog") == 0) {
    //formObj = $("#"+formName,$.pdialog.getCurrent())[0];
    var dlgid = preWinNav.replace("dialog,","");
    var dialog = $("body").data(dlgid);
    var formObj;
    if (dialog[0]) {
      formObj = $("#"+formName,dialog)[0];
    }
   } else {
     formObj=$("#"+formName,navTab.getCurrentPanel())[0];
   }
    var json =  $(formObj).serializeArray();  
    submitForm.appendChild(createHidePara(formName,$.toJSON(json)));
  }
    
  winNav = src.getAttribute('_windownav');
  var navInfo = winNav.split(",");
  var callbackjs = "";
  //判断是否多div刷新,如是则执行多div loadurl函数
  if (navInfo[1] == "0") {
	  $.ajax({
		type: 'POST',
		url:submitForm.action,
		data:$(submitForm).serializeArray(),
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		dataType:"json",
		cache: false,
		success: AjaxDone
	   });
  } else  {
	  $.ajax({
		type: 'POST',
		url:submitForm.action,
		data:$(submitForm).serializeArray(),
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		dataType:"html",
		cache: false,
		success: function(response){
		  try {		  
			  var json = DWZ.jsonEval(response);
			  if (json.statusCode==DWZ.statusCode.error){
				 if (json.message) alertMsg.error(json.message);
			   } else {
			     LoadMultiDiv(response,navInfo[1],preWinNav,callbackjs);
			   }
		   } catch(err) {
		   }
		  },
		error:function(){
		  alert("调用失败");		   
        }
	 });
  }
  return false;
}

/**将连接转为表单对象提交,读取连接的属性附加到表单中**/
function doLinkPost(src) {
	
     var link = $(src);
     if (link.parent().parent()) {        
     	if (link.parent().parent().is("tr")) {
     	  link.parent().parent().trigger('click')
     	} else if (link.parent().parent().parent().is("tr")) {
     	  link.parent().parent().parent().trigger('click')
     	}
    }
    if (src.getAttribute('beforeClickJS')) {
     eval(src.getAttribute('beforeClickJS')+"()");
    }
    var formObj =document.createElement("form");
 	formObj.action = src.getAttribute('url');
 	var str = src.getAttribute('param');
 	while(str.indexOf("\'") != -1){
 		str = str.replace("\'","\""); 		
 	}

 //	if(str.indexOf("noChk") == 1){//肖宇舟 2014-7-2 增加，审核中可以直接d 
 	  if (!setFormFixV(src,formObj)) {
 	    return false;
  	  }
 	//}
 	  //校验
	  var confirmMsg = src.getAttribute('confirm');
	  if (confirmMsg) {
	      if (!chkForm(confirmMsg,formObj)) {
	        return false;
	      } 
	  }  
	//提交*/
	submitActionForm(formObj._actionid.value,formObj,formObj._windownav.value,formObj._prewindownav.value);
	return false;
}


/*以ajax的方式提交表单,并将返回的html填充到一指定对话框*/
function BindDialog(formObj,dlgid,title,dialogWidth,dialogHeight) {
	var $formObj=$(formObj)
//	if (!$formObj.valid()) {
//		return false;
//	}
	$.pdialog._open(formObj.action,$formObj.serializeArray(), dlgid, title,{mask:true,width:dialogWidth,height:dialogHeight}); 	
}

/*以ajax的方式提交表单,并将返回的html填充到一指定navtab*/
function BindNavTab(formObj,navID,title) {
  //formObj.onsubmit = function() {return navTabSearch(this,"main");}
  var $formObj=$(formObj);
//  	if (!$formObj.valid()) {
//		return false;
//	}
  if (!navID) navID = "main";
  navTab.openTab(navID, formObj.action, {title:title,data:$formObj.serializeArray()});
}

/*以ajax的方式提交指定表单,并将返回html填充到一指定div中*/
function BindDiv(formObj,divID,preNavInfo) {
  var $formObj=$(formObj);
//  	if (!$formObj.valid()) {
//		return false;
//	}
  if (formObj.divMode){
    //如表单有divMode信息
  	formObj.divMode.value = divID;
  } else {
  	formObj.appendChild(createHidePara("divMode",divID));
  }
  if (formObj.pageAction){
    //如表单有actionid信息
  	formObj.pageAction.value = "loadDiv";
  } else {
  	formObj.appendChild(createHidePara("pageAction","loadDiv"));
  }
  //判断是否多div刷新,如是则执行多div loadurl函数
  if (divID.indexOf("|")>0) {
	  $.ajax({
		type: 'POST',
		url:formObj.action,
		data:$formObj.serializeArray(),
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		dataType:"html",
		cache: false,
		success: function(response){
		  try {		  
			  var json = DWZ.jsonEval(response);
			  if (json.statusCode==DWZ.statusCode.error){
				 if (json.message) alertMsg.error(json.message);
			   }
			   LoadMultiDiv(response,divID,preNavInfo);
		   } catch(err) {
		   }
		  },
		error:function(){
		  alert("调用失败");		   
        }
	   });
  } else {
	  var $box;
	  if (preNavInfo.indexOf("dialog") ==0) {
	    $box = $("#" + divID,$.pdialog.getCurrent());
	  } else {
	    $box = $("#" + divID,navTab.getCurrentPanel());
	  }
	  $box.loadUrl(formObj.action,$formObj.serializeArray(),function(){$box.find("[layoutH]").layoutH();});
  }
}

/*以ajax的方式提交指定表单,并将返回html填充到一指定div中*/
function BindMvc(formObj,divID,preNavInfo) {
  var $formObj=$(formObj);  
  var callbackjs;
  if (formObj._mvccallback) {
    callbackjs = formObj._mvccallback.value;
  }
  if (formObj.pageAction){
    //如表单有actionid信息
  	formObj.pageAction.value = "doController";
  } else {
  	formObj.appendChild(createHidePara("pageAction","doController"));
  }
  //判断是否多div刷新,如是则执行多div loadurl函数
  if (divID == "0") {
    $.ajax({
	 type: 'POST',
	 url:formObj.action,
	 data:$formObj.serializeArray(),
	 contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	 dataType:"json",
	 cache: false,
	 success: AjaxDone
    });
  } else  {
	  $.ajax({
		type: 'POST',
		url:formObj.action,
		data:$formObj.serializeArray(),
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		dataType:"html",
		cache: false,
		success: function(response){
		  try {		  
			  var json = DWZ.jsonEval(response);
			  if (json.statusCode==DWZ.statusCode.error){
				 if (json.message) alertMsg.error(json.message);
			   } else {
			     LoadMultiDiv(response,divID,preNavInfo,callbackjs);
			   }
		   } catch(err) {
		   }
		  },
		error:function(){
		  alert("调用失败");		   
        }
	 });
  }
}


/**根据返回区域加载多div**/
function LoadMultiDiv(response,divID,preNavInfo,callbackjs) {
   var divList = divID.split("|");
   var $res = $(response); 
   for (var i in divList) {
     //获取返回div的html
      var $box;
	  if (preNavInfo.indexOf("dialog") ==0) {
	    $box = $("#" + divList[i],$.pdialog.getCurrent());
	  } else {
	    $box = $("#" + divList[i],navTab.getCurrentPanel());
	  }
     var $div = $("#" + divList[i],$res);
     $box.html($div.html()).initUI();
     $box.find("[layoutH]").layoutH();
   }
   if (callbackjs) {
	 eval(callbackjs);
   }
   var pos = response.indexOf("<div>");
   if ( pos !=0) {
      var callfunc = response.substring(0,pos);
      eval(callfunc);
   }
}


/**绑定表单以ajax方式提交,并执行固定的AjaxDone回调函数**/
function BindAjax(formObj,divID) {
  var $formObj=$(formObj);
   if (formObj.divMode){
    //如表单有divMode信息
  	formObj.divMode.value = divID;
  } else {
  	formObj.appendChild(createHidePara("divMode",divID));
  }
  $.ajax({
	type: 'POST',
	url:formObj.action,
	data:$formObj.serializeArray(),
	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	dataType:"json",
	cache: false,
	success: AjaxDone
   });
}

/**加载指定连接到div中**/
function LoadDivPage(divid,url,param) {
    var formObj =document.createElement("form");
 	formObj.method="post";
	if (param && param != "")
	{
	  param.replace(/\'/g,"\"")
	  json = eval("("+param+")");
	  for(var key in json)
  	  {
  	    var pn = key;
  	    var pv = json[pn];
    	//添加表单参数    	
    	formObj.appendChild(createHidePara(pn,pv));
  	  }
	}   
 	formObj.action = url;
	formObj.onsubmit = function() {return divSearch(this, divid);}
	formObj.onsubmit();
}

  //ajax提交
  //formObj.onsubmit = return validateCallback(this,dialogAjaxDone); //回调为dialog处理
  //formObj.onsubmit = return validateCallback(this,navTabAjaxDone); //回调为main处理
  //form提交
  //formObj.onsubmit = return dialogSearch(this,"maindlg");
  //formObj.onsubmit = return navTabSearch(this,"main");
  //局部div提交
  //formObj.onsubmit = return divSearch(this,"divid");


/**提交表单的处理入口**/
function submitActionForm(actionID,formObj,winNav,preWinNav) {
  var actionIDInfo = actionID.split(".");
  var pageName = actionIDInfo[0];
  var action =  actionIDInfo[1];
  if (actionID == "_dataExport") {
	  var navInfo = winNav.split(",");
	  var $formObj=$(formObj);
	  $.ajax({
		type: 'POST',
		url:formObj.action,
		data:$formObj.serializeArray(),
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		dataType:'text',
		cache: false,
		success: function(data){
		  if (data.indexOf('<') == 0) {
			 alert("导出异常");
		  } else {
			window.open(data);
		  }
	    }
	  });
	  return;
  } else if (actionID == "exportExcel" || actionID == "exportCsv") {
    var navInfo = winNav.split(",");
    var $formObj=$(formObj);
    $.ajax({
    type: 'POST',
    url:formObj.action,
    data:$formObj.serializeArray(),
    contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
    dataType:'json',
    cache: false,
    success: function(data){
      var message = data.message;
      if(message == "导出异常"){
        alert(message); 
      }else{
        window.open(message);
        }
      }
    });
    return;
  } else if (winNav.indexOf("custom") < 0) {
    //已在服务端指定提交方式,则进行解析后提交
    var navInfo = winNav.split(",");
    if (navInfo[0] == "dialog") {
      //对话框
      BindDialog(formObj,navInfo[1],navInfo[2],navInfo[3],navInfo[4]);      
    } else if (navInfo[0] == "navTab") {
      //主窗口
      BindNavTab(formObj,navInfo[1],navInfo[2]);
    } else if (navInfo[0] == "div") {     
      BindDiv(formObj,navInfo[1],preWinNav);
    }else if (navInfo[0] == "mvc") {     
      BindMvc(formObj,navInfo[1],preWinNav);
    } else if (navInfo[0] == "ajax") {
      BindAjax(formObj,navInfo[1]);
    }
    return;
  }else{
  }

  
  //根据执行按钮进行特殊处理
  if (pageName == "DemoList") {
  	 DemoListAction(action,formObj);
  } else if (pageName == "DemoEdit") {
  	 DemoEditAction(action,formObj);
  } 
  else {
    alert("未指定此操作");
  }
}

function activityJumpUrl(divId, url, navInfo, param,type,activityId){	
	var para = '{"_prewindownav":"'+param+'","":""}';
	$.pdialog.reload(url + "?DISCOUNT_ACTIVITY_ID=" + activityId + "&_prewindownav="+ param + "&_windownav="+navInfo + "&editMode=2&type=" + type, JSON.parse(para) , 'addNew');
}

/**纯ajax请求的回调处理入口,实现默认的处理逻辑:关闭对话框,并重新提交父列表页面的查询表单**/
function AjaxDone(json) {
  //判断成功标示和返回信息
  try {
    if (json["success"] == "1") {
		//提示后刷新
		alertMsg.info(json["message"]);
	} else {
		//错误提示
		alertMsg.error(json["message"]);
	}
	//关闭对话框
	if (json["isclose"] == "1") {
		if ($.pdialog.getCurrent()) {
			$.pdialog.closeCurrent();
		}
	}
	//刷新page
	if (json["reloadpage"] == "1") {
		//获取页面左侧菜单选中的连接并处罚点击
	  $butt = $("#sidebar div .selected a");
	  if ($butt) {
		 $butt.trigger("click");
	  }	
	  return;
	}
	//刷新视图
	var view = json["reloadview"];
	var prenav = json["prenavinfo"];
	if (view) {
	  $butt = $("#"+view+"LoadAction",$("#" + view + "Div"));
	 if ($butt[0]) {
		 $butt.trigger("click");
	  } else {
	  	$butt = $("#qrybutton",$("#" + view + "Div"));	 
	  	if ($butt[0]) {
		 $butt.trigger("click");
		} 
	  }
	  return;
	}
	//跳转页面
	var forward = json["forward"];
	if (forward) {
		if (json["prenavinfo"]) {
		  if (json["prenavinfo"].indexOf("dialog") == 0) {
    	    var dlgid = json["prenavinfo"].replace("dialog,","");
    	    //var param = "{\"_prewindownav\":\""+json["prenavinfo"]+"\",\"_windownav\":\""+json["navinfo"]+"\"}";
    	    var param = "_prewindownav="+json["prenavinfo"]+"&_windownav="+json["navinfo"];
    	    forward.replace(param,"");
    	    if (forward.indexOf("?") > 0) {
    	      forward = forward + "&" + param;
    	    } else {
    	      forward = forward + "?" + param;
    	    }
		    $.pdialog.reload(forward,"{}",dlgid)
		  } else {
		    navTab.reload(forward);
		  }
		} else { 
		  navTab.reload(forward);
		}
	}
  }catch(err) {
    alert(err.description);
  }
}

//  function AjaxDone(json) {
//    //判断成功标示和返回信息
//    try {
//      if (json["success"] == "1") {
//      //提示后刷新
//      alertMsg.info(json["message"]);
//      if (!json["custom"]) {
//        //关闭当前窗口后刷新pageform
//        if ($.pdialog.getCurrent()) {
//          $.pdialog.closeCurrent();
//        }     
//        if ($("#pagerForm") && $("#pagerForm")[0]) {
//            //模拟查询按钮点击 ListForm.qrybutton         
//            var butt = $("#ListForm_qrybutton",$("#pagerForm")[0]);
//            if (butt[0]) {
//               $butt.trigger("click");
//            }
//        }
//      } else {
//        alert("未指定操作");
//      }
//    } else {
//      //错误提示
//      alertMsg.error(json["message"]);
//    } 
//    }catch(err) {
//      alert(err.description);
//    }
//  }

//////////////////////商品图库管理
function updObjUrl(objSelect,limitType){	
	if(objSelect == "objSelect"){
		if(limitType == 1){
			$("a[lookupgroup='"+objSelect+"']").attr("href","/commodity/commodity_list.htm?lookupMode=NAME,ID");
		}else if(limitType == 2){
			$("a[lookupgroup='"+objSelect+"']").attr("href","/commodity/sort_list.htm?lookupMode=NAME,ID");
		}
	}else if(objSelect == "paramSelect"){	
		if(limitType == 4){
			$("input[name='NAME']").show();
			$("a[lookupgroup='"+objSelect+"']").attr("href","/commodity/commodity_list.htm?lookupMode=NAME,ID");
		}else if(limitType == 7){
			$("dl[name='dl_NAME']").show();
			$("a[lookupgroup='"+objSelect+"']").attr("href","/commodity/commodity_list.htm?lookupMode=NAME,ID");
		}else{
			
			if(limitType == 11){
			  $("input[name='COMMODITY_AMOUNT']").show();
			}else{
			  $("input[name='COMMODITY_AMOUNT']").hide();
			}
			$("input[name='NAME']").hide();
		}
	}else if(objSelect == "ruleParamSelect"){				
		if(limitType == 1){
			$("a[lookupgroup='"+objSelect+"']").attr("href","/commodity/commodity_list.htm?lookupMode=NAME,ID");
		}else if(limitType == 2){
			$("a[lookupgroup='"+objSelect+"']").attr("href","/commodity/sort_list.htm?lookupMode=NAME,ID");
		}else if(limitType == 3){
			$("a[lookupgroup='"+objSelect+"']").attr("href","/commodity/sort_list.htm?lookupMode=NAME,ID");
		}else if(limitType == 4){
			$("a[lookupgroup='"+objSelect+"']").attr("href","/commodity/sort_list.htm?lookupMode=NAME,ID");
		}
	}
}


function resizeImgX(img, iwidth, iheight){
  var _img = new Image();
  _img.src = img.src;
  if (_img.width > _img.height) {
    img.width = (_img.width > iwidth) ? iwidth : _img.width;
    img.height = (_img.height / _img.width) * img.width;
  }
  else {
    if (_img.width < _img.height) {
      img.height = (_img.height > iheight) ? iheight : _img.height;
      img.width = (_img.width / _img.height) * img.height;
    }
    else {
      img.height = (_img.height > iheight) ? iheight : _img.height;
      img.width = (_img.width > iwidth) ? iwidth : _img.width;
    }
  }
}

function setPhoto(cpId, cId, type,name) {
  if (type == "del") {
    if(confirm("确定要删除此商品图片?不可恢复!")) {
      $.ajax({
	    url: "/UploadServlet?m=del&cId="+cId+"&pId="+cpId,
	    type: 'POST',
	    timeout: 20000,
	    error: function(){
	      alert('未知错误!');
	    },
	    success: function(data){
		  reloadPhoto(data,name+"commodityUpload");
	    }
	  });
      return true;
    }
    return false;
  } else if (type=="delMain") {
    if (confirm("确实要删除商品主图?不可恢复")) {
	$.ajax({
		    url: "/UploadServlet?m=delMain&cId="+cId,
		    type: 'POST',
		    timeout: 20000,
		    error: function(){
		      alert('未知错误!');
		    },
		    success: function(data){
			  reloadPhoto(data,name+"commodityUpload");		  
		    }
		});
	 }
	 return true;
  } else {
	  $.ajax({
	    url: "/UploadServlet?m="+type+"&cId="+cId+"&pId="+cpId,
	    type: 'POST',
	    timeout: 20000,
	    error: function(){
	      alert('未知错误!');
	    },
	    success: function(data){
		  reloadPhoto(data,name+"commodityUpload");
	    }
	  });
  }
}

  /*最后一个文件刷新*/  
  function uploadEnd(event, queueId, fileObj, response, data){  
  	if (data.fileCount == 0){
  	  //设置文件控制值和预览信息
  	  var getID = event.currentTarget.attributes.getNamedItem("id").nodeValue;    	  
  	  reloadPhoto(response,getID);
  	}
  }
  function uploadSelected (event, queueID, fileObj) {
	 alert("ttt");
  }
  
 function uploadAllEnd(event, data,response) {	
 }

  function showBig(o) {
    //取消current的元素
    $(o).parent().parent().parent().parent().parent().parent().find("input").uploadifySettings('scriptData',{'name':'liudong'});
    $(".current").attr("class","");
    o.parentNode.className  = "current";
    var $bpic = $("#commodity_bigpic",$(o).parent().parent().parent().parent())
    var pos = o.src.lastIndexOf("_");
    var lastSrc = o.src.substring(0, pos);
    var pos2 = lastSrc.lastIndexOf("_");
    if (pos2 >0 ) {
    	var subid = lastSrc.substring(pos2+1,pos);
    	$(o).parent().parent().parent().parent().parent().parent().find("input").uploadifySettings('scriptData',{'subid':subid});
    }else{
    	$(o).parent().parent().parent().parent().parent().parent().find("input").uploadifySettings('scriptData',{'subid':''});
    }
    var newSrc = lastSrc + "_5.jpg?r="+Math.random();
    $bpic.attr("src",newSrc);
  }
  

	function reloadPhoto(data,getID){
	 try {	 	 
	 	  var preNavInfo = $("#"+getID).parent().parent().attr("_prewindownav");	 		  
		  var formObj =document.createElement("form");	  
		  var $formObj=$(formObj);
		  formObj.method="post";
		  formObj.appendChild(createHidePara("divMode","photoUpload"));
		  formObj.appendChild(createHidePara("pageAction","reloadPhoto"));
		  formObj.appendChild(createHidePara("_prewindownav",preNavInfo));
		  formObj.appendChild(createHidePara("commodityID",data));
		  formObj.appendChild(createHidePara("inputid",getID));
		  var $box;
		  if (preNavInfo.indexOf("dialog") ==0) {
		    var dlgid = preNavInfo.substring(preNavInfo.indexOf(",")+1);
		    $box = $("#photoUpload",$.pdialog.getCurrent());
		  } else {
		    $box = $("#photoUpload",navTab.getCurrentPanel());
		  }
		  $box.loadUrl("/ncommodity/commodity_detail.htm",
		     $formObj.serializeArray(),
		     function(){});	 
	  } catch(err) {
	    alert("err");
	  }
	}

  /**普通文件开始上传*****/
  function tmpUploadStart(event,ID,fileObj) {  
  }
  
  /*****普通文件上传结束*****/  
  function tmpUploadEnd(event, queueId, fileObj, response, data){
  	if (data.fileCount == 0){
  	  //设置文件控制值和预览信息
  	  var getID = event.currentTarget.attributes.getNamedItem("id").nodeValue;
  	  getID = getID.replace("_file","");  	  
  	  tmpPreview(getID,response);
  	  var valueObj = document.getElementById(getID);
  	  if (valueObj) {
  	    valueObj.value = response;
  	  }
  	}
  }
  
  function tmpPreview(id,file) {
    //取消current的元素
    var showImg = document.getElementById(id+"tmpPreview");
    if (showImg) {
        var newSrc = file + "?r="+Math.random();
    	showImg.src = newSrc;
    } else {
    	showImg = document.getElementById(id+"tmpFileInfo");
    	showImg.innerHTML = "文件上传成功,路径: "+file;
    }
    //设置空间值
    showImg = document.getElementById(id+"tmpPreview");
  }
  
    
  function uploadStart(event,ID,fileObj) {    
    var getID = event.currentTarget.attributes.getNamedItem("id").nodeValue;
    getID = getID.replace("commodityUpload","");
    //var getID =""; 
    $("#fileQueue"+getID,$(this).parent().parent()).attr("style","");
    $("#commodity_bigpic",$(this).parent().parent()).attr("style","display:none");    
  }
  
function confirmEcoupon(selectId,timeId,btn){
	var i = $("#dataForm_"+selectId).val();
	var time = $("#dataForm_"+timeId).val();
	if(i != 2 && (time != null || time != "")){
		if(confirm("将已使用状态修改为其他状态,将会清除该电子码和订单的关联，是否继续？")){
			doFormPost(btn,'dataForm');
		}else{
			return false;
		}
	}else{
		doFormPost(btn,'dataForm');
	}
}

function loadChart(data) {

}

function displayLayout(value,type){
	if(type == 1){
       if(value == 3){
           $("#auto").attr("style","display:block");
       }else{
     	   $("#auto").attr("style","display:none");
      }
   }
}

function delTableRow(src) {
  $(src).parents("tr:first").remove();
  initSuffix($tbody);
  return false;
}

function addTableRow(tableID) {
 var form = $("#"+tableID);
 if (form) {
 	$("#addRowBtn",form).trigger("click");
 }
}


//调用页面-CommodityDetailPage  
//选择启用规格按钮时将目前页面上的数量\价格\货号提交
function submitToSpec() {
    var param;
	if ($("#setSpec")) {
	  var paramstr = $("#setSpec").attr("param");
	  paramstr.replace(/\'/g,"\"")
	  param = eval("("+paramstr+")");
	  param["PRICE"] = $("#commodityDetail_SELL_PRICE").val();
	  param["INNAGE"] = $("#commodityDetail_SALE_ENABLE_COUNT").val();
	  param["CODE"] = $("#commodityDetail_CODE").val();
	  paramstr = $.toJSON(param);
	  paramstr.replace(/\'/g,"\"");
	  $("#setSpec").attr("param",paramstr);
	} 
}


//调用页面-CommodityDetailPage  
//选择分类后执行的回调函数(触发隐藏的重置扩展属性按钮)
function eloadextends(json) {
    //重新加载扩展属性区域
    $butt = $("#reloadExtends");    
    $butt.trigger("click");
}
//调用页面-CommodityDetailPage  
//选择分类后设置扩展属性面板隐藏还是显示
function dispExtendsPanel(exdisp,specdisp) {
  if (exdisp == 1) {
  	$panel = $("#扩展属性");
  	$panel.css("display", "block");
  } else if (exdisp ==0) {
  	$panel = $("#扩展属性");
  	$panel.css("display", "none");
  }
   if (specdisp == 1) {
  	$panel = $("#多规格商品Head");
  	$panel.css("display", "block");
  } else if (specdisp ==0) {
  	$panel = $("#多规格商品Head");
  	$panel.css("display", "none");
  }
}

//调用页面-CommodityDetailPage  
//选择品牌后执行的回调函数(触发价格计算)
function cbdispdiscount(json) {
   //执行ajax请求
   var dis;
   var price;
   if ($("#commodityDetail_DISCOUNT_PRICE")) 
   {
	  $("#commodityDetail_DISCOUNT_PRICE").val("");
   }
   if (json["DISCOUNT_INFO"]) {
     dis = json["DISCOUNT_INFO"];
     $price = $("#commodityDetail_SELL_PRICE");     
     if ($price) {
        price = $price.val();
        //计算折扣
        var urlinfo = "/commodity/commodity_detail.htm?pageAction=countBrandPrice";
        urlinfo = urlinfo + "&price="+price+"&discount="+dis;
        $.ajax({
		  url: urlinfo,
		  type: 'POST',
		  timeout: 2000,
		  dataType: 'text',
		  error: function(){
		      alert('计算品牌折扣价格失败!');
		  },
		  success: function(data){
		   if ($("#commodityDetail_DISCOUNT_PRICE")) 
		   {
		   		$("#commodityDetail_DISCOUNT_PRICE").val(data);
		   }
		  }
		 });
     }
  }
}
//调用页面-SubCommodityPage 
//重载子商品选择页面
function cbreloadselesub(json) {
  var subid = json["COMMODITY_ID"];
  //获取子商品选择窗口的表单目标地址
  var url = $("#subCommodityView").attr("action");
  var pos = url.indexOf("MAIN_COMMODITY_ID=");
  var pos2 = url.indexOf("&",pos);
  var mainid;
  if (pos2 == -1) {
    mainid = url.substring(pos+ 18);
  } else {
    mainid = url.substring(pos+ 18,pos2);
  }
  url = "/commodity/subcommodity_detail.htm?type=sele&_prewindownav=dialog,seleSubCommoditydlg&_windownav=mvc,0&MAIN_COMMODITY_ID="+mainid+"&SUB_COMMODITY_ID="+subid;
  //$.pdialog.reload(url,{},"seleSubCommoditydlg")
  //$.pdialog.open(url,"seleSubCommoditydlg", "选择子商品");
  $.pdialog._open(url,{}, "seleSubCommoditydlg", "选择子商品",{mask:true}); 
}


//调用页面-CommodityDetailPage  
//选择分类后执行的回调函数(触发隐藏的重置扩展属性按钮)
function cbreloadextends(json) {
    //重新加载扩展属性区域
    $butt = $("#reloadExtends");    
    $butt.trigger("click");
}
//调用页面-CommodityDetailPage  
//选择分类后设置扩展属性面板隐藏还是显示
function dispExtendsPanel(exdisp,specdisp) {
  if (exdisp == 1) {
  	$panel = $("#扩展属性");
  	$panel.css("display", "block");
  } else if (exdisp ==0) {
  	$panel = $("#扩展属性");
  	$panel.css("display", "none");
  }
   if (specdisp == 1) {
  	$panel = $("#多规格商品Head");
  	$panel.css("display", "block");
  } else if (exdisp ==0) {
  	$panel = $("#多规格商品Head");
  	$panel.css("display", "none");
  }
}

//调用页面-CommodityDetailPage  
//选择品牌后执行的回调函数(触发价格计算)
function cbdispdiscount(json) {
   //执行ajax请求
   var dis;
   var price;
   if ($("#commodityDetail_DISCOUNT_PRICE")) 
   {
	  $("#commodityDetail_DISCOUNT_PRICE").val("");
   }
   if (json["DISCOUNT_INFO"]) {
     dis = json["DISCOUNT_INFO"];
     $price = $("#commodityDetail_SELL_PRICE");     
     if ($price) {
        price = $price.val();
        //计算折扣
        var urlinfo = "/commodity/commodity_detail.htm?pageAction=countBrandPrice";
        urlinfo = urlinfo + "&price="+price+"&discount="+dis;
        $.ajax({
		  url: urlinfo,
		  type: 'POST',
		  timeout: 2000,
		  dataType: 'text',
		  error: function(){
		      alert('计算品牌折扣价格失败!');
		  },
		  success: function(data){
		   if ($("#commodityDetail_DISCOUNT_PRICE")) 
		   {
		   		$("#commodityDetail_DISCOUNT_PRICE").val(data);
		   }
		  }
		 });
     }
  }
}

//调用页面-OrderDetailPage
//收件人地址点击查找带回时的触发函数,更改连接的参数
function receiversele(src) {   
   var member = $("input[name='membersele.MID']").val();
   if (!member) {
   }
   var link = src.href;
   var pos =link.indexOf("?MEMBER_ID=");
   if (pos > 0) {
     var pos2 = link.indexOf("&",pos);
     link = link.substring(0,pos) + "?MEMBER_ID="+member+"&"+link.substring(pos2+1);   
   } else {
   	  pos =link.indexOf("?");
   	  link = link.substring(0,pos) + "?MEMBER_ID="+member+"&"+link.substring(pos+1); 
   }   
   src.href = link;   
}
//调用页面-OrderDetailPage
//商品选择时的触发函数,更改连接的参数
function commoditysele(src) {   
   var lv = $("input[name='membersele.VIP_LV']").val();
   if (!lv) {
     lv = 0;
   }
   var pay = $("input[name='paysele.PAY_CHANNEL_ID']").val();
   if (!pay) {
     pay = 0;
   }  
   lv = lv +","+pay;
   var link = src.href;
   var pos =link.indexOf("?DISCOUNT=");
   if (pos > 0) {
     var pos2 = link.indexOf("&",pos);
     link = link.substring(0,pos) + "?DISCOUNT="+lv+"&"+link.substring(pos2+1);   
   } else {
   	  pos =link.indexOf("?");
   	  link = link.substring(0,pos) + "?DISCOUNT="+lv+"&"+link.substring(pos+1); 
   }
   src.href = link;   
}
//调用页面-orderdetailpage
//选取下单用户后，清除用户相关信息
function clearMember(info) {
  $("input[name='recsele.DELIVERY_ADDR_ID']").val("");
  $("input[name='recsele.RECEIVER_NAME']").val("");
  $("input[name='recsele.RECEIVER_TEL']").val("");
  $("input[name='recsele.RECEIVER_MOBILE']").val("");
  $("input[name='recsele.AREA_NAME']").val("");
  $("input[name='recsele.AREA_ID']").val("");
  $("textarea[name='recsele.ADDR']").val("");
  //填充默认收件人信息
  //执行ajax调用
  $.ajax({url: '/member/member_main.htm?pageAction=qryDeliveryInfo', 
	type: 'POST', 
	data:{MID:info.MID}, 
	dataType: 'json', 
	timeout: 1000, 	 
	 success: function(result){
	 	if (result.DELIVERY_ADDR_ID) {
	 		//直接填充	 		
	 		$("input[name='recsele.DELIVERY_ADDR_ID']").val(result.DELIVERY_ADDR_ID);
	 		$("input[name='recsele.RECEIVER_NAME']").val(result.RECEIVER_NAME);
	 		if (result.RECEIVER_TEL) $("input[name='recsele.RECEIVER_TEL']").val(result.RECEIVER_TEL);
	 		if (result.RECEIVER_MOBILE) $("input[name='recsele.RECEIVER_MOBILE']").val(result.RECEIVER_MOBILE);
	 		if (result.ADDR) $("textarea[name='recsele.ADDR']").val(result.ADDR);
	 		if (result.AREA_ID) $("input[name='recsele.AREA_ID']").val(result.AREA_ID);
	 		if (result.AREA_NAME) $("input[name='recsele.AREA_NAME']").val(result.AREA_NAME);	 		
	 	} else {
	 	   //设置会员信息
	 	   if (result.TRUENAME)  {
	 	      $("input[name='recsele.RECEIVER_NAME']").val(result.TRUENAME);
	 	   } else {
	 	      $("input[name='recsele.RECEIVER_NAME']").val(result.USERNAME);
	 	   }
	 	   if (result.PHONE) $("input[name='recsele.RECEIVER_MOBILE']").val(result.PHONE);
	 	   if (result.HOME_ADDR) $("textarea[name='recsele.ADDR']").val(result.HOME_ADDR);
	 	   if (result.AREA_ID) $("input[name='recsele.AREA_ID']").val(result.AREA_ID);	
	 	   if (result.AREA_PATH) $("input[name='recsele.AREA_NAME']").val(result.AREA_PATH);	 	 	   
	 	}
	 } 
   }
  ); 
}
//调用页面-orderdetailpage
//保存订单后提示保存成功
function saveOrderOk() {
  alertMsg.info("订单修改成功");
}

//如有表格有鼠标移入移出效果，则可在listview中进行设置
//所有设置了移入移出效果的listview都调用本函数，其中target是行对象，
//通过行对象上的in和out属性来分别执行不同的函数
var floatTimer;
function showFloatDiv(target) {
  var $this = $(target);
  if ($this.attr("in") && $this.attr("in") == "dispOrderDetail") {
	floatTimer = setTimeout(
	function(){
	    var param={};
	    if ($this.attr("rel")) {
	      var str = $this.attr("rel").replace(/\'/g,"\"")
		  param = eval("("+str+")");
	    }
	    $.ajaxSettings.global=false;
		$("#TableFloatDiv").loadUrl(
		   "/order/order_detail.htm?pageAction=showOrderDetail&order_id="+param.ORDER_FORM_ID,
		   param,
		   function(){  
		    $("#TableFloatDiv").css('position','absolute');
		   	$("#TableFloatDiv").css('left','350px');
		   	if (($this.offset().top + 50) > 400) {
		   		$("#TableFloatDiv").css('top',$this.offset().top - 120);
		   	} else {
	  			$("#TableFloatDiv").css('top',$this.offset().top + 50);
	  		}
	  		$("#TableFloatDiv").css('display','block');
	 	   }
		); 
		$.ajaxSettings.global=true;    
	},600);
  }
}

function hideFloatDiv(target) {
  var $this = $(target);
  $("#TableFloatDiv").css('display','none');
  clearTimeout(floatTimer);
}

function changeSpecPreview(item, id){
	$("#subCommodityView_SEDFPIC_" + id + "tmpPreview").attr("src", '/photos/spec/' + id+ "_" + item.value + ".jpg");
}

function reoladPicSpec() {
   $("#reloadPicSpec").trigger("click");
}

//打开订单商品弹出选择
function openOrderCommoditySele(src) {
   var mid = $("input[name='membersele.MID']").val();
   if (!mid) {
   	alert("请先选择下单用户");
   	return;
   }
   var link = $(src).attr("url");   
   if (link.indexOf('MID') == -1) {
     link = link + "?MID=" + mid;
   } else {
      link = link.substring(0,link.indexOf('MID')) + "MID=" + mid;
   }
   $(src).attr("url",link);
   doLinkPost(src);
}

function cbreloadorder() {
   
}
function exportPhone() {
	window.open('/export.html','导出', 'height=100, width=200, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no');
}
function printOrder(src, type) {
	var str = src.getAttribute('param');
	var actionID = src.getAttribute('_actionid');
	var pageAction = src.getAttribute('_pageAction');
	var winNav = src.getAttribute('_windownav');
	var preWinNav = src.getAttribute('_prewindownav');
	var divMode = src.getAttribute('divMode');
	var mvcCallback = src.getAttribute('_mvccallback');
	var json,navType,navTitle,isAjax;
	if (str != null)
	{
	  str.replace(/\'/g,"\"")
	  json = eval("("+str+")");
	  for(var key in json)
  	  {
  	    var pn = key;
  	    var pv = json[pn];
    	//添加表单参数
    	if (pn =="_rowdata") {   
		    var table = pv.replace("{row_data}","");
   		  if (false) {
   		    if (table) {
   		      //$box = $("#" + table,$.pdialog.getCurrent());
   		      $box = $("#" + table,navTab.getCurrentPanel());
   		      pv = "{row_data}".replaceTmById($box); //替换表格选中参数
   		    } else {
   		      pv = pv.replaceTmById($($.pdialog.getCurrent())); //替换表格选中参数
   		    }    		  
          } else {
	    	  if (table) {
	   		    $box = $("#" + table,navTab.getCurrentPanel());
	   		    pv = "{row_data}".replaceTmById($box); //替换表格选中参数
	   		  } else {
	   		    pv = pv.replaceTmById($(navTab.getCurrentPanel())); //替换表格选中参数
	   		  }    		  
		  } 	  	
    		//alert(pv);
    		if (pv == "{row_data}") {
    			//提示未选中信息
    			alertMsg.error(DWZ.msg("alertSelectMsg"));
    			return false;
    		}
    		pv.replace(/\'/g,"\"");
    	} else if (pn =="_ids") {
    	    //处理多选,获取对应tableid
          var table = pv.replace("{select_ids}","");
          if (table) {
            var str = "";
	   	    if (preWinNav.indexOf("dialog") ==0) {   
	   	      $box = $("#" + table,$.pdialog.getCurrent());		        
	   		  $("[name='_ids']:checked",$box).each(function(){
                 str= str + ","+$(this).val();
              }) 
	        } else {
	          $box = $("#" + table,navTab.getCurrentPanel());
	          $("[name='_ids']:checked",$box).each(function(){
                 str = str + ","+$(this).val();
              }) 		  
	        }
	        if (!str) {
    			//提示未选中信息
    			alertMsg.error("请选中表格中的复选框");
    			return false;
    		} else {
    		  if (str.indexOf(",") == 0) {
    		    str = str.substring(1);
    		  }
    		  pv = str;
    		}
          }
    	}
			if(pn =="_rowdata" && type == 1)    	
    		window.open("/order/orderPrint.jsp?" + pn + "=" + pv);
			else if(pn =="_rowdata" && type == 2)
				window.open("/order/deliveryPrint.jsp?" + pn + "=" + pv);
  	}
  }
  return true;
}

function dataExport(view) {  
   if (!confirm('如导出数据量太多,会等待较长时间,确认导出?')) {
	  return;
   }
  if (view) {
	  $butt = $("#"+view+"LoadAction",$("#" + view + "Div"));
	 if ($butt[0]) {
		 $butt.attr("id","_dataExport");
		 $butt.attr("_actionid","_dataExport");		 
		 $butt.trigger("click");
		 $butt.attr("id",view+"LoadAction");
		 $butt.attr("_actionid",view+"LoadAction");	
	  } else {
	  	$butt = $("#qrybutton",$("#" + view + "Div"));	 
	  	if ($butt[0]) {
	  	 $butt.attr("id","_dataExport");
		 $butt.attr("_actionid","_dataExport");
		 $butt.trigger("click");
		 $butt.attr("id","qrybutton");
		 $butt.attr("_actionid","qrybutton");	
		}
	  }
   }
}
function changeAccountSure(arg){
	var words = arg.SELE_MONEY.split(',');
	var m = 0;
	for(var i = 0 ; i < words.length ; i++){
      m += parseFloat(words[i]);
	}
	$("#statusForm_PAY_MONEY").val(returnFloat1(m));
}

function returnFloat1(value) { //保留一位小数点
    value = Math.round(parseFloat(value) * 10) / 10;
    if (value.toString().indexOf(".") < 0)
      value = value.toString() + ".0";
    return value;
  }



var checkTreeValueArray = new Array();
var selectTreeRankNum = 0;
function treeSelect(arg,tname,tvalue){
	++selectTreeRankNum;
	var isChecked = !arg.hasClass("checked");
	var checkTreeValue = new Object();
	var checkTreeFlag = true;
	if(isChecked){
		
		for(var i in checkTreeValueArray){
		  if(checkTreeValueArray[i].id == tvalue){
			  checkTreeValueArray[i].type = 2;
			  checkTreeValueArray[i].rankNum = selectTreeRankNum;
			  checkTreeFlag = false;
		  }
		}
		if(checkTreeFlag){
			checkTreeValue.id = tvalue;
			checkTreeValue.type = 2;
			checkTreeValue.rankNum = selectTreeRankNum;
			checkTreeValueArray.push(checkTreeValue);	
		}
		
	}else{//取消
		for(var i in checkTreeValueArray){
		 if(checkTreeValueArray[i].id == tvalue){
			checkTreeValueArray[i].type = 1;
			checkTreeValueArray[i].rankNum = selectTreeRankNum;
			checkTreeFlag = false;
		 }
		}
		if(checkTreeFlag){
			checkTreeValue.id = tvalue;
			checkTreeValue.type = 1;
			checkTreeValue.rankNum = selectTreeRankNum;
			checkTreeValueArray.push(checkTreeValue);	
		}
	}
	
	$("input[name='"+tname+"Update']").val($.toJSON(checkTreeValueArray));
	
}

/**
 * 用于更新字段排序图标
 * @return
 */
function rankByColumn(target,rankColumn, state){
	$("input[name='targetField']").val(target);
	$("input[name='orderByColumn']").val(rankColumn);
	$("input[name='orderByMethod']").val(state);
	doFormPost(document.getElementById("qrybutton"),'pagerForm');
}

/**
*获取浏览器的信息
*/
function getExplorerInfo(){
	if(navigator.appName == "Microsoft Internet Explorer"){
		return "Microsoft Internet Explorer";
	}else{
		return "Netscape";
	}
}
/**
用于快捷提交表单
*/
function shortcutSubmitForm(e){
	var keyNum = null;
	if(getExplorerInfo() == "Microsoft Internet Explorer"){
		keyNum = e.keyCode;
	}else{
		keyNum = e.which;
	}
	
	if(keyNum == 13){
		doFormPost(document.getElementById("qrybutton"),'pagerForm');
	}
}

function activityChangeType(t){
	var selectId = parseInt($(t).val());
	if(selectId == 2 || selectId == 4){
		$("#formView_COMMODITY_RANGE_TYPE").empty();
    	$("#formView_COMMODITY_RANGE_TYPE").append("<option value='4' selected='selected'>商品</option>");
	}else{
		$("#formView_COMMODITY_RANGE_TYPE").empty();
    	$("#formView_COMMODITY_RANGE_TYPE").append("<option selected='selected' value='1'>全场</option><option value='2'>分类</option><option value='3'>品牌</option><option value='4'>商品</option>");
	}
}
	
function changePopUrl(objSelect,limitType){	
	if(objSelect == "inventory"){
		if(limitType == 1){
			
				
	      $("input[name='typeSelect.STORE_ID']").val("");
		  $("input[name='typeSelect.STORE_NAME']").val("");
	      $("input[name='typeSelect.STORE_ID']").attr("name","typeSelect.STOCK_ID");
	      $("input[name='typeSelect.STORE_NAME']").attr("name","typeSelect.NAME");
	      
	      $("#typeSelectLink").attr("href","/stock/stock_main.htm?lookupMode=STOCK_ID,NAME," +
	       "cid_stockList&popsize=600,300&poptarget=typeSelectLink");
				
		}else{
		
		    $("input[name='typeSelect.STOCK_ID']").val("");
			$("input[name='typeSelect.NAME']").val("");
			$("input[name='typeSelect.STOCK_ID']").attr("name","typeSelect.STORE_ID");
		    $("input[name='typeSelect.NAME']").attr("name","typeSelect.STORE_NAME");
		    
		    $("#typeSelectLink").attr("href","/store/store_main.htm?lookupMode=STORE_ID,STORE_NAME," +
		     "cid_storeList&popsize=800,400&poptarget=typeSelectLink");
				
		}
	
	}else{
       alert(objSelect);
	    }
}	

function changeScopeUrl(objSelect,limitType){	
	
	
	$("input[name='scopeSelect.SORT_ID']").val("");
	$("input[name='scopeSelect.SORT_NAME']").val("");
	$("input[name='scopeSelect.BRAND_ID']").val("");
	$("input[name='scopeSelect.NAME']").val("");
	
		
	if(objSelect == "scope"){
		
		$("#scopeSelectLink").addClass('btnLook');
		$("#scopeSelectLink").html("选择");
		
		if(limitType==4){
		
	     	 $("input[name='scopeSelect.SORT_ID']").attr("name","scopeSelect.BRAND_ID");
	     	 $("input[name='scopeSelect.SORT_NAME']").attr("name","scopeSelect.NAME");
	     	 
	     	$("#scopeSelectLink").attr("href","/comm/brand_main.htm?lookupMode=BRAND_ID,NAME," +
		       "cid_brandList,_ids&popsize=600,400&poptarget=scopeSelectLink");
			
		}else if(limitType==3){
			
			$("input[name='scopeSelect.BRAND_ID']").attr("name","scopeSelect.SORT_ID");
	     	$("input[name='scopeSelect.NAME']").attr("name","scopeSelect.SORT_NAME");
			
			$("#scopeSelectLink").attr("href","/comm/sort_sele.htm?lookupMode=SORT_ID,SORT_NAME," +
		       "cid_sortTree&popsize=600,400&poptarget=scopeSelectLink");
			
		}else{
			$("#scopeSelectLink").removeAttr('href');
			$("#scopeSelectLink").removeClass('btnLook');
			$("#scopeSelectLink").html("");
		}
			
	}else{
       alert(objSelect);
	    }
}

//排序表单提交
function doFormDescPost(divId, descField, descType) {
	
	/*var index = 0;
	$("ul[class='navTab-tab'] li").each(function(){
	   if($(this).attr("class") == "selected"){
	      return;
	   }
	   index++;
    });*/
	div = $("div[class='page unitBox']:visible");
	var src =div.find("#qrybutton")[0];
	src = src == null ? $(div).find("#" + divId + "LoadAction")[0] : src;
//$("#"+divId).find("#" + divId + "LoadAction").each(function(){
//	 alert($(this).attr("id")); 
//});
//alert(src.id);
	descType = descType == "" ? "DESC" : (descType == "DESC" ? "ASC" : "DESC");
	$(div).find("#pagerForm_pageOrderFiled").val(descField);
	$(div).find("#pagerForm_pageOrderType").val(descType);
	
  doFormPost(src, "pagerForm") ;
	//alert(divId);
	//doLinkPost(src);
   //setListDescShow(divId, ""); 
}

function setListDescShow(divId, tableId) {
	//alert("set");
	 var div = $("div[class='page unitBox']:visible");
	 var hidOrderFiled = div.find("#pagerForm_pageOrderFiled").val();
	 var hidOrderType = div.find("#pagerForm_pageOrderType").val();
	 
	 var choiceTh = div.find("#"+divId+"_th_"+hidOrderFiled);
	 var img = choiceTh.find("img");
	 hidOrderType = (hidOrderType == "" || hidOrderType == null) ? "DESC" :  hidOrderType;
	 if (hidOrderFiled != "" && choiceTh != null) {
	 	  img.attr("src", hidOrderType == "DESC" ? "/click/column-ascending-dark.gif" : "/click/column-descending-dark.gif" );
	 	 $(choiceTh).click(function(){
	 		 doFormDescPost(divId,hidOrderFiled,hidOrderType);
	        }); 
	 	$(choiceTh).css("background-color","#E4F5FF");
	 }
}
function setAddValue(args){
	
	var inBatchId=args.INVENTORY_BATCH_ID;
	var batchType= args.TYPE;
	$("#addListView").attr("style","display:block;");
	$("#addListView").attr("url","/stock/billcomm_detail.htm?inBatchId="+inBatchId+"&batchType="+batchType);
	$("#editListView").attr("url","/stock/billcomm_detail.htm?inBatchId="+inBatchId+"&batchType="+batchType);
}

function setmpObjId(args){
	var storeId=args.STORE_ID;
	var supId=args.SUPPLIER_ID;
	var dcId=args.STOCK_ID;
//	var dcId=
	$("#addListView").attr("style","display:block;");
	if(storeId){//存在Storeid 则类型为store
		
		$("#addListView").attr("url","/modifyprice/comm_detal.htm?billType=3&objectID="+storeId);
		$("#editListView").attr("url","/modifyprice/comm_detal.htm?billType=3&objectID="+storeId);
		
	}else if(supId){
		$("#addListView").attr("url","/modifyprice/comm_detal.htm?billType=1&objectID="+supId);
		$("#editListView").attr("url","/modifyprice/comm_detal.htm?billType=1&objectID="+supId);
		
	}else if(dcId){
		$("#addListView").attr("url","/modifyprice/comm_detal.htm?billType=2&objectID="+dcId);
		$("#editListView").attr("url","/modifyprice/comm_detal.htm?billType=2&objectID="+dcId);
		
		
	}else{
		alert("未知错误");
	}
	
function removeMPObj(){
	
	if($("input[name='items[0].MODIFY_PRICE_COMMODITY_ID']"))
//	if()如果该调整单已经添加商品 则不可修改调整对象
	$("#commSelectLink").removeAttr('href');
	$("#commSelectLink").removeClass('btnLook');
	$("#commSelectLink").html("");
	
}
	
	
}
