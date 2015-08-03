package com.ga.click.control.extra;

import org.apache.click.control.TextField;
import org.apache.click.util.HtmlStringBuffer;

import com.ga.click.control.DbField;

public class GaText extends TextField {
	
  private DbField defiField;
  
  public GaText(String code,String name,boolean isRequire) {
    super(code,name,isRequire);
  }
  
  public void setDbField(DbField defiField) {
    this.defiField = defiField;
  }
  
  @Override
  public void render(HtmlStringBuffer buffer) {

      buffer.elementStart(getTag());

      buffer.appendAttribute("type", getType());
      buffer.appendAttribute("name", getName());
      buffer.appendAttribute("id", getId());
      buffer.appendAttributeEscaped("value", getValue());
      buffer.appendAttribute("size", getSize());
      buffer.appendAttribute("title", getTitle());
      if (getTabIndex() > 0) {
          buffer.appendAttribute("tabindex", getTabIndex());
      }


      if (isValid()) {
          removeStyleClass("error");
          if (isDisabled()) {
              addStyleClass("disabled");
          } else {
              removeStyleClass("disabled");
          }
      } else {
          addStyleClass("error");
      }
     

      if (isDisabled()) {
          buffer.appendAttributeDisabled();
      }
      if (isReadonly()) {
          buffer.appendAttributeReadonly();
      }
      if (this.defiField != null && this.defiField.getFieldVerify() != null) {
	      if (this.defiField.getFieldVerify().isRequire()) {
	    	  if (this.hasAttribute("class")) {
	    		  addStyleClass("required");
	    	  } else {
	    		  buffer.appendAttribute("class","required");
	    	  }	    	  
	      }	      
	      if (this.defiField.getFieldVerify().getMaxLen() > 0) {
	          buffer.appendAttribute("maxlength", this.defiField.getFieldVerify().getMaxLen());
	      }
      }
      appendAttributes(buffer);
      buffer.elementEnd();

      if (getHelp() != null) {
          buffer.append(getHelp());
      }
  }
}
