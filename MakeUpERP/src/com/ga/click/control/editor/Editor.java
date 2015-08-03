package com.ga.click.control.editor;

import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;

/**
 * 
 * @author xyz
 * 在线编辑器控件，默认模式只有图片可以上传
 */
public class Editor extends DbField{
    
    public static final String UP_LOAD_SERVLET_PATH = "/UploadServlet?flag=";
    public static final String UP_LOAD_OBJ_COMMODITY = "commodity";
    public static final String UP_LOAD_OBJ_ACTIVITY = "activity";
    public static final String UP_LOAD_OBJ_SECKILL = "seckill";
    
    private int rows = 6;
    private int cols = 100;
    private String upLinkUrl = "";
    private String upLinkExt = "zip,rar,txt";
    private String upImgUrl = UP_LOAD_SERVLET_PATH+"img";
    private String upImgExt = "jpg,jpeg,gif,png";
    private String upFlashUrl =  UP_LOAD_SERVLET_PATH+"flash";
    private String upFlashExt = "swf";
    private String upMediaUrl = "";
    private String upMediaExt = "avi";
    private String type = UP_LOAD_OBJ_COMMODITY;
    private String toolsMode = "";
    
    public Editor(String fieldName, String fieldCode, int dataType,String type) {
        super(fieldName, fieldCode, dataType);
        setInput(GaConstant.INPUT_TEXTAREA);
        this.addInputAttributeMap("class", "editor");
        this.type = type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public Editor(String fieldName, String fieldCode, int dataType) {
        super(fieldName, fieldCode, dataType);
        setInput(GaConstant.INPUT_TEXTAREA);
        this.addInputAttributeMap("class", "editor");
    }

    public void setToolsMode(String toolsMode) {
        this.toolsMode = toolsMode;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    public void setCols(int cols) {
        this.cols = cols;
    }
    public void setUpLinkUrl(String upLinkUrl) {
        this.upLinkUrl = upLinkUrl;
    }

    public void setUpLinkExt(String upLinkExt) {
        this.upLinkExt = upLinkExt;
       
    }

    public void setUpImgUrl(String upImgUrl) {
        this.upImgUrl = upImgUrl;
        
    }

    public void setUpImgExt(String upImgExt) {
        this.upImgExt = upImgExt;
        
    }
    public void setUpFlashUrl(String upFlashUrl) {
        this.upFlashUrl = upFlashUrl;
    }
    public void setUpFlashExt(String upFlashExt) {
        this.upFlashExt = upFlashExt;
    }
    public void setUpMediaUrl(String upMediaUrl) {
        this.upMediaUrl = upMediaUrl;
    }
    public void setUpMediaExt(String upMediaExt) {
        this.upMediaExt = upMediaExt;
    }
    public void bindAddAttribute(){
        this.addInputAttributeMap("upMediaUrl", upMediaUrl+"&type="+type);
        this.addInputAttributeMap("upFlashUrl", upFlashUrl+"&type="+type);
        this.addInputAttributeMap("upImgUrl", upImgUrl+"&type="+type);
        this.addInputAttributeMap("upMediaExt", upMediaExt);
        this.addInputAttributeMap("upFlashExt", upFlashExt);
        this.addInputAttributeMap("upImgExt", upImgExt);
        this.addInputAttributeMap("upLinkExt", upLinkExt);
        this.addInputAttributeMap("upLinkUrl", upLinkUrl);
        this.addInputAttributeMap("rows", rows+"");
        this.addInputAttributeMap("cols", cols+"");
        this.addInputAttributeMap("tools", toolsMode);
    }
}
