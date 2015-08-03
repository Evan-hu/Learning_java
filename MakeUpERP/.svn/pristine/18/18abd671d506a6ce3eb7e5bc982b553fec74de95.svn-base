package com.ga.click.control;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.ga.click.control.button.ActionButton;
import com.ga.click.util.GaUtil;

public class StringDecorator {
  protected boolean isRed = false;
  protected boolean isNewLine = false;
  protected ActionButton button = null;
  protected String imgUrl = "";
  protected int imgWidth = 0;
  protected int imgHeight = 0;
  protected String stringFmt = null;
  protected Map<String,String> linkValue = new HashMap<String,String>();
  protected Map<String,DbField> linkField = new HashMap<String,DbField>();
  protected String oldActionUrl = "";
  protected boolean isAdd = false;
   
  /**
   * 设置关联字段值
   * @param fieldCode
   * @param value
   */
  public void setLinkFieldValue(String fieldCode,String value,DbField dbField) {
    this.linkValue.put(fieldCode, value);
    this.linkField.put(fieldCode, dbField);
  }
  
  /**
   * 获取关联字段列表
   * @return
   */
  public Set<String> getLinkeFieldCode() {
    return linkValue.keySet();
  }
  /**
   * 设置为标红装饰
   */
  public void setRedDecorator() {
    isRed = true;
  }
  
  /**
   * 设置换行装饰
   */
  public void setNewLineDecorator() {
    isNewLine = true;
  }
  
  public boolean isImageDecorator() {
    if (GaUtil.isNullStr(imgUrl)) {
      return false;
    } else {
      return true;
    }
  }
  
  
  public int getImgWidth() {
    return imgWidth;
  }

  public int getImgHeight() {
    return imgHeight;
  }

  /*
   * 字符格式化,可同时显示多个dbfield的值,可在原值基础上加入固定前后缀,""表示不输出
   * {value}表示原值
   * {xxxx} 表示相应fieldCode的值
   * @param stringFmt
   */
  public void setStringFormat(String stringFmt) {
    this.stringFmt = stringFmt;
    //依次解析字符串
    this.parseExpress(stringFmt);
  }
  
  protected void parseExpress(String express) {
    int pos =0,pos1 = 0;
    while (pos > -1 && pos1 > -1) {
      pos = express.indexOf("{",pos1);
      pos1 = express.indexOf("}",pos);
      if (pos > -1 && pos1 > -1) {
        String field = express.substring(pos + 1,pos1);
        if (!field.equals("value")) {
          this.linkValue.put(field, "");
        }
      }
    }
  }
  
  private String renderExpress(String expresss,String value) {
    return this.renderExpress(expresss, value,false);
  }
  
  private String renderExpress(String expresss,String value,boolean setDecorde) {
    String rtnValue = expresss.replace("{value}",value);
    //替换其他字段值
    for(String fieldCode : this.linkValue.keySet()) {
      String getV = this.linkValue.get(fieldCode);
      if (setDecorde) {
          DbField field = this.linkField.get(fieldCode);
          if (field != null && field.getColumnDecorator() != null) {
            getV = field.getColumnDecorator().decorator(getV);
          }
      }
      rtnValue = rtnValue.replace("{"+fieldCode+"}",getV);
    }
    return rtnValue;
  }
  /**
   * 设置为可点击连接装饰
   * @param button
   */
  public void setLinkDecorator(ActionButton button) {
    this.button = button;
    this.oldActionUrl = button.getUrl();
    this.parseExpress(button.getUrl());
  }
  
  
  public ActionButton getButton() {
	return button;
}

public void setImgDecorator(String link,int width,int height) {
    this.imgUrl = link;
    this.imgWidth = width;
    this.imgHeight = height;
    this.parseExpress(imgUrl);
  }
  
  public String decorator(String value) {
    String oldValue = value;
    if (this.stringFmt != null && !GaUtil.isNullStr(value)) {
      value = renderExpress(this.stringFmt,oldValue,true);
    }
    if (isRed) {
      value = "<font color=\"red\">" + value + "<font>";
    }
    if (this.button != null) {
      if (this.isAdd) {
        //额外按钮可以自由设置
        this.button.setTitle(this.button.getTitle().replace("{value}", oldValue)); 
      } else {
        //非额外按钮必须使用本字段的值
        this.button.setTitle(value.replace("{value}", oldValue));  
      }          
      this.button.setAttribute("class", "trbtn");
      String url = renderExpress(this.oldActionUrl,oldValue);
      this.button.setUrl(url);
      value = this.button.toString();
      value = this.renderExpress(value,oldValue );
    }
    if (this.isNewLine) {
      value = "<br>"+value;
    }    
    if (!GaUtil.isNullStr(this.imgUrl)) {
      String currUrl = this.imgUrl;
      currUrl = renderExpress(currUrl,oldValue);
      String rnd = GaUtil.getRandomString(3);
      if (currUrl.endsWith("_2.jpg")) {
        //商品缩略图
        value = "<img src='"+currUrl+"?rnd="+rnd+"' onclick=\"window.open('"+currUrl.replace("_2", "_5")+"?rnd="+rnd+"');\" width='"+this.imgWidth+"px' height='"+this.imgHeight+"'></a>";
      } else {
        value = "<img src='"+currUrl+"?rnd="+rnd+"' onclick=\"window.open('"+currUrl+"?rnd="+rnd+"');\" width='"+this.imgWidth+"px' height='"+this.imgHeight+"'></a>";
      }
    }
    return value;
  }

  public boolean isAdd() {
    return isAdd;
  }

  public void setAdd(boolean isAdd) {
    this.isAdd = isAdd;
  }
}
