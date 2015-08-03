package com.ga.click.control.extra;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import org.apache.click.control.AbstractControl;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.exception.BizException;
import com.ga.click.util.FilePath;
import com.ga.click.util.FileUploadUtil;
import com.ga.click.util.GaUtil;

public class PhotoUpload extends AbstractControl {
  
  private String jsMethod = "";
  private boolean isPreview = false;
  private List<String> checkFileType = new ArrayList<String>();
  private int imgWidth = 210;
  private int imgHeight = 0;
  private String preNavInfo = "";
  private String name = "";

  private Long commodityId;
  
  private String mainPicUrl = "";
  private List<Long> picList = new ArrayList<Long>();
  private String filePath = "";
  
  
  public PhotoUpload(Class callClass,String name,Long id) {
    try {
      String className = callClass.getName();
      if (className != "") {
        className = className.substring(className.lastIndexOf(".") + 1);
      }
      this.name = name;
      this.commodityId = id;
      this.setName(className+"."+id);
      this.initPicList();      
    } catch(Exception e) {
      throw new BizException(BizException.SYSTEM,"图库控件初始化失败");
    }    
  }
  
  public void setPreNavInfo(String preNavInfo) {
    this.preNavInfo = preNavInfo;
  }

  private void initPicList() throws Exception{    
    CachedRowSet ocrs = DbUtils.query("select * from COMMODITY_PICTURE cp where cp.commodity_Id=? order by ISMAIN DESC, RANK_NUM", commodityId);
    filePath = FilePath.idToPathBase8(commodityId);
    boolean fileExist = (new File(FileUploadUtil.getFileRoot()+ "/commodity/" + FilePath.idToPathBase8(commodityId) + commodityId + "_6.jpg")).exists(); //主图
    if (fileExist) {
      mainPicUrl = FileUploadUtil.getFileRoot() + "/commodity/" + FilePath.idToPathBase8(commodityId) + commodityId + "_6.jpg";
    }
    //mainPicUrl = FileUploadUtil.getFileRoot() + FilePath.idToPathBase8(commodityId) + commodityId + "_6.jpg";
    while (ocrs.next()) {
      this.picList.add(ocrs.getLong("PICTURE_ID"));
    }
  }
  
  public void addCheckType(String type) {
    if(!checkFileType.contains(type)){
      checkFileType.add(type);
    }
  }
  
  public void bindJs(String methodName) {
    this.jsMethod = methodName;
  }
  
  public void setPreview(boolean isPreview, int width, int height) {
    this.isPreview = isPreview;
    this.imgWidth = width;
    this.imgHeight = height;
  }
  
  @Override
  public void render(HtmlStringBuffer buffer) {
    try {
      if(this.commodityId == 0L){
        
      } else {        
        buffer.append("<div id=\"commodityPicDiv\" class=\"GoodsPicArea\"  style=\"margin:4px 0 0 0;padding:0\"");
        if (!GaUtil.isNullStr(this.preNavInfo) ) {
          buffer.append(" _prewindownav=\"").append(this.preNavInfo).append("\"");
        }
        buffer.append("> \r\n");
        //输出按钮区
        buffer.append("  <div class=\"actionBar\">");
        buffer.append("<input id='").append(name).append("commodityUpload' type='file' name='image'  value='上传'");
        buffer.append("\n uploader='/clicktemplate/uploadify/scripts/uploadify.swf'") ;
        buffer.append("\n cancelImg=''/clicktemplate/uploadify/cancel.png'");
        buffer.append("\n script='/UploadServlet'");
        buffer.append("\n scriptData='{ajax:1,m:\"upload\",commodityId:" + this.commodityId + "}'");
        buffer.append("\n fileQueue='fileQueue").append(name).append("'");
        buffer.append("\n onComplete='uploadEnd'");
        buffer.append("\n onOpen='uploadStart'");
        buffer.append("\n onAllComplete='uploadAllEnd'");
        buffer.append("\n fileExt='*.jpg;*.jpeg;'");
        buffer.append("\n fileDesc='*.jpg;*.jpeg;'/>");
        buffer.append("  </div>");
        //动态显示;               
        buffer.append("  <br style=\"clear:both\">");
        //图片预览区
        buffer.append("  <div class=\"pic-area\">");
        //大图
        buffer.append("    <div id=\"x-main-pic\" class=\"\">"); 
        buffer.append("    <div id=\"fileQueue").append(name).append("\" class=\"fileQueue\" style=\"display:none\"></div>");
        if (GaUtil.isNullStr(mainPicUrl)) {
          //没有图片
          buffer.append("<p align=\"center\"><br><br>此处显示商品页默认图片<br>[您还未上传商品图片！]</p>");          
        } else {
          //显示主图
          buffer.append("<img id=\"commodity_bigpic\" src='/photos/" +  this.commodityId + "_6.jpg?r=").append(Calendar.getInstance().getTimeInMillis()).append("' width=\"200\" height=\"300\"/>");
        }
        buffer.append("    </div>");
        //图片列表
        buffer.append("    <div id=\"all-pics\" style=\"width:").append(this.imgWidth + 30).append("px\">");           
        if (!GaUtil.isNullStr(mainPicUrl)) {
          //主图缩略图
          buffer.append("       <div class=\"gpic-box\">"); 
          buffer.append("         <span class=\"current\">");
          buffer.append("           <img  src='/photos/" + this.commodityId + "_2.jpg?r=").append(Calendar.getInstance().getTimeInMillis()).append("'  style='cursor: pointer;' onclick='showBig(this)' width='40' height='60'/>");
          buffer.append("         </span>");
          //图片操作区
          buffer.append("         <div class=\"picdelete\">");
          //删除按钮
          buffer.append("           <image border=\"0\"  src=\"/js/del.png\" alt=\"删除\" title=\"删除\" onclick='setPhoto(0," + this.commodityId + ", \"delMain\",\"").append(name).append("\"); return false;' />");
          buffer.append("         </div>"); 
          buffer.append("       </div>"); 
          //其他缩略图
          for (Long picID : this.picList) {
            //缩略图
            buffer.append("       <div class=\"gpic-box\">"); 
            buffer.append("         <span>");
            buffer.append("           <img src='/photos/" + this.commodityId + "_" + picID + "_2.jpg?r=").append(Calendar.getInstance().getTimeInMillis()).append("'  style='cursor: pointer;' onclick='showBig(this)' width='40' height='60'/>");
            buffer.append("         </span>");
            //图片操作区
            buffer.append("         <div class=\"picdelete\">");
            //删除按钮
            buffer.append("           <image border=\"0\"  style=\"wdith:12px;height:12px\"  src=\"/js/top.gif\" alt=\"设为主图\" title=\"设为主图\" onclick=\"setPhoto(" + picID + "," + this.commodityId + ", 'setMain','").append(name).append("')\" />");
            buffer.append("           <image border=\"0\"  src=\"/js/pos.png\" alt=\"置顶\" title=\"置顶\" onclick=\"setPhoto(" + picID + "," + this.commodityId + ",'topImg','").append(this.name).append("')\"/>");
            buffer.append("           <image border=\"0\"  src=\"/js/del.png\" alt=\"删除\" title=\"删除\" onclick='setPhoto(" + picID + "," + this.commodityId + ", \"del\",\"").append(this.name).append("\"); return false;' />");

            buffer.append("         </div>");
            buffer.append("       </div>"); 
          }
        }
        buffer.append("    </div>");
        buffer.append("  </div>");   
        buffer.append("</div>");
      }     
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"渲染上传异常." + ex.getMessage(), ex);
    }  
  }

  /* @see org.apache.click.control.AbstractControl#getTag()
   */
  @Override
  public String getTag() {
    return "a";
  }  
}
