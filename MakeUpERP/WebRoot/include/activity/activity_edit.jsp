<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<div class="tableform1">
    <div id="auto" class="division">
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tbody>          
          <tr>
            <th>活动名称</th>
            <td><input type="text" class="validate[nulllength[0,10]] textInput" maxlength="20" size="20" value="123" id="formView_NAME" name="NAME"></td>
            <th>活动类型</th>
            <td><select disabled="disabled" class="required" size="1" id="formView_TYPE" name="TYPE"><option value="1" selected="selected">折扣</option><option value="2">特价</option><option value="3">买满送</option><option value="4">组合商品</option></select><input type="hidden" value="1" name="TYPE"></td>
          </tr>
          <tr>
            <th>开始时间</th>
            <td><input type="text" readonly="true" format="yyyy-MM-dd HH:ss" class="date readonly textInput" hide="false" size="20" value="2014-05-02 15:00" id="formView_START_TIME" name="START_TIME"></td>
            <th>结束时间</th>
            <td><input type="text" readonly="true" format="yyyy-MM-dd HH:ss" class="date readonly textInput valid" hide="false" size="20" value="2014-06-07 00:00" id="formView_END_TIME" name="END_TIME"></td>
          </tr>
          <tr>
            <th>创建人</th>
            <td><input type="text" readonly="readonly" maxlength="20" size="20" value="superadmin" id="formView_USERNAME" name="USERNAME" class="textInput readonly"></td>
            <th>创建时间</th>
            <td><input type="text" disabled="disabled" readonly="true" format="yyyy-MM-dd" class="date readonly disabled textInput" hide="false" size="20" value="2014-05-02 15:43:28.0" id="formView_CREATE_TIME" name="CREATE_TIME"></td>
          </tr>
          <tr>
            <th>状态</th>
            <td><select class="required" size="1" id="formView_ACTIVITY_STATE" name="ACTIVITY_STATE"><option value="1" selected="selected">正常</option><option value="0">无效</option></select></td>
          </tr>
          <tr>
            <th>活动描述</th>
            <td colspan="3"><textarea upimgext="jpg,jpeg,gif,png" uplinkurl="" upmediaurl="&amp;type=activity" uplinkext="zip,rar,txt" class="editor textInput" upmediaext="avi" tools="simple" upflashext="swf" upimgurl="/UploadServlet?flag=img&amp;type=activity" upflashurl="/UploadServlet?flag=flash&amp;type=activity" cols="70" rows="10" id="formView_CONTENT" name="CONTENT" style="display: none;">123</textarea><input type="text" style="position:absolute;display:none;" id="xhe0_fixffcursor" class="textInput"><span style="" class="xhe_vista" id="xhe0_container"><table cellspacing="0" cellpadding="0" role="presentation" style="width:595px;height:223px;" class="xheLayout"><tbody><tr><td role="presentation" style="height:1px;" unselectable="on" class="xheTool" id="xhe0_Tool"><span class="xheGStart"></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Blocktag" title="段落标签" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnBlocktag">段落标签</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Fontface" title="字体" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnFontface">字体</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="FontSize" title="字体大小" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnFontSize">字体大小</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Bold" title="加粗 (Ctrl+B)" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnBold">加粗 (Ctrl+B)</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Italic" title="斜体 (Ctrl+I)" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnItalic">斜体 (Ctrl+I)</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Underline" title="下划线 (Ctrl+U)" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnUnderline">下划线 (Ctrl+U)</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Strikethrough" title="删除线" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnStrikethrough">删除线</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="FontColor" title="字体颜色" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnFontColor">字体颜色</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="BackColor" title="背景颜色" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnBackColor">背景颜色</span></a></span><span class="xheGEnd"></span><span class="xheSeparator"></span><span class="xheGStart"></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Align" title="对齐" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnAlign">对齐</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="List" title="列表" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnList">列表</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Outdent" title="减少缩进" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnOutdent">减少缩进</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Indent" title="增加缩进" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnIndent">增加缩进</span></a></span><span class="xheGEnd"></span><span class="xheSeparator"></span><span class="xheGStart"></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Link" title="超链接 (Ctrl+L)" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnLink">超链接 (Ctrl+L)</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Img" title="图片" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnImg">图片</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="Emot" title="表情" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnEmot">表情</span></a></span><span><a role="button" tabindex="-1" class="xheButton xheEnabled" cmd="About" title="关于 xhEditor" href="#"><span style="font-size:0;color:transparent;text-indent:-999px;" unselectable="on" class="xheIcon xheBtnAbout">关于 xhEditor</span></a></span><span class="xheGEnd"></span><br></td></tr><tr><td role="presentation" class="xheIframeArea" id="xhe0_iframearea" style="height: 210px;"><iframe frameborder="0" style="width:100%;" src="javascript:;" id="xhe0_iframe"></iframe></td></tr></tbody></table></span></td>
          </tr>
          <tr>
            <th>备注</th>
            <td colspan="3"><textarea style="width:590px;" cols="20" rows="3" id="formView_NOTE" name="NOTE" class="textInput">123</textarea></td>
          </tr>
          <tr>
          </tr>
         </tbody><tbody>
      </tbody></table>
    </div>
  </div>