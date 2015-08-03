package com.ga.click.control.extra;

import org.apache.click.control.Field;
import org.apache.click.util.HtmlStringBuffer;

import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;

public class FileUploadField extends Field{

  private boolean preview = false;
  private String selectExt = "";
  private int width = 50;
  private int height = 50;
  
  public FileUploadField(String name,String lable) {
    super(name,lable);
  }
  
  public void setPreview(boolean preview,int width,int height) {
    this.preview = preview;
    if (width > 0) {
      this.width = width;
    }
    if (height > 0) {
      this.height = height;  
    }
    
  }


  public void setSelectExt(String selectExt) {
    if (!GaUtil.isNullStr(selectExt)) {
      this.selectExt = selectExt;  
    }    
  }


  @Override
  public void render(HtmlStringBuffer buffer) {
    try {     
        buffer.append("<div id=\"commodityPicDiv\" class=\"GoodsPicArea\"  style=\"margin:4px 0 0 0;padding:0\"");
        buffer.append("> \r\n");
        //输出按钮区
        buffer.append("  <div class=\"actionBar\">");
        buffer.append("<input name='").append(this.getName()).append("_file' id='").append(this.getId()+"_file").append("' type='file' name='image'  value='上传1'");
        buffer.append("\n uploader='/clicktemplate/uploadify/scripts/uploadify.swf'") ;
        buffer.append("\n cancelImg='/clicktemplate/uploadify/cancel.png'");
        buffer.append("\n script='/UploadServlet?flag=tmpfile'");
        buffer.append("\n scriptData='{ajax:1,m:\"upload\"}'");
        buffer.append("\n fileQueue='fileQueue'");
        buffer.append("\n onComplete='tmpUploadEnd'");
        buffer.append("\n onOpen='tmpUploadStart'");
        buffer.append("\n multi=false");      
        buffer.append("\n onAllComplete='uploadAllEnd'");
        if (!GaUtil.isNullStr(this.selectExt)) {
          buffer.append("\n fileExt='").append(this.selectExt).append("'");
          buffer.append("\n fileDesc='").append(this.selectExt).append("'/>");
        } else {
          buffer.append("\n fileExt='*.jpg;*.jpeg;*.gif;*.png;'");
          buffer.append("\n fileDesc='*.jpg;*.jpeg;*.gif;*.png;'/>");
        }

        buffer.append("  </div>");
        //动态显示;               
        buffer.append("  <br style=\"clear:both\">");
        //图片预览区
        if (this.preview) {
          //显示图片
          buffer.append("  <div class=\'pic-area\' style='width:").append(this.width).append("px;height:").append(this.height).append("px'>");
            //主图缩略图
          buffer.append("       <div class=\'gpic-box\' style='width:").append(this.width).append("px;height:").append(this.height).append("px'>"); 
          buffer.append("         <span class='current' style='width:").append(this.width).append("px;height:").append(this.height).append("px'>");
          if (GaUtil.isNullStr(this.getValue()))  {
            buffer.append("           <img  id='").append(getId()).append("tmpPreview' src=''  style='cursor: pointer;width:").append(this.width).append("px;height:").append(this.height).append("px'/>");
          } else {
            buffer.append("           <img  id='").append(getId()).append("tmpPreview' src='").append(this.getValue())
            .append("?rnd=").append(GaUtil.getRandomString(3))
            .append("'  style='cursor: pointer;width:")
            .append(this.width)
            .append("px;height:").append(this.height)
            .append("px'/>");
          }          
          buffer.append("         </span>");
          buffer.append("         </div>");
          buffer.append("  </div>");
        } else {
          //显示文字
          buffer.append("  <div class=\"pic-area\">");
          //主图缩略图
          buffer.append("         <span id='").append(getId()).append("tmpFileInfo' style='text-align:center;'>"); 
          if (GaUtil.isNullStr(this.getValue()))  {
            buffer.append("未选择文件");
          } else {
            buffer.append("已上传文件,文件名:"+this.getValue());
          }
          buffer.append("         </span>");
          buffer.append("  </div>");
        }
        buffer.append(" </div>");
        buffer.append(" <input type=\"hidden\" value='").append(this.getValue()).append("' name='").append(this.getName()).append("' id=\"").append(this.getId()).append("\"");
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"渲染上传异常." + ex.getMessage(), ex);
    }   
  }

  
  
  @Override
  public String getValue() {
    // TODO Auto-generated method stub
    return super.getValue();
  }

  
}
