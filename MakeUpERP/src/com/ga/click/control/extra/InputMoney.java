package com.ga.click.control.extra;

import java.math.BigDecimal;

import org.apache.click.control.TextField;
import org.apache.click.util.HtmlStringBuffer;

import com.ga.click.control.DbField;
import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;

public class InputMoney extends TextField {
	  private DbField defiField;
  //private BigDecimal moneyValue;
  private BigDecimal moneyValue;
  private String displayValue = "";
  
  public InputMoney(String name, String label, boolean required) {
    super(name,label,required);
  }
  public void setDbField(DbField defiField) {
	    this.defiField = defiField;
	  }
  /**
   * 设置值(分)
   */
  @Override
  public void setValue(String value) {
    // TODO Auto-generated method stub
    try {
      this.moneyValue = new BigDecimal(value);
      this.moneyValue.setScale(2,BigDecimal.ROUND_HALF_UP);
      this.displayValue = String.valueOf(moneyValue.divide(new BigDecimal("100")).doubleValue());
    } catch (Exception e) {
      
    }
  }

  /**
   * 设置值(分)
   */
  @Override
  public void setValueObject(Object object) {
    if (object == null) {
      this.moneyValue = null;
      this.displayValue = "";
      return;
    }
    // TODO Auto-generated method stub
    if (object instanceof Long) {
      this.moneyValue = new BigDecimal(object.toString());
    } else if (object instanceof BigDecimal) {
      this.moneyValue = (BigDecimal)object;
    } else {
      this.moneyValue = null;
      this.displayValue = "";
      return;
      //throw new BizException(BizException.SYSTEM,"货币控件设置值类型不符");
    }
    this.moneyValue.setScale(2,BigDecimal.ROUND_HALF_UP);
    this.displayValue = String.valueOf(moneyValue.divide(new BigDecimal("100")).doubleValue());
  }

  
  

  /**
   * 获取值(分)
   */
  @Override
  public String getValue() {
    // TODO Auto-generated method stub
    if (this.moneyValue == null) return "";
    return this.moneyValue.toString();
  }

  /**
   * 获取值(分)
   */
  @Override
  public Object getValueObject() {
    // TODO Auto-generated method stub
    return this.moneyValue;
  }

  /**
   * 从request中读取值(自动*100)
   */
  @Override
  protected String getRequestValue() {
    String requestValue = getContext().getRequestParameter(getName());
    if (GaUtil.isNullStr(requestValue)) return "";
    try {
      BigDecimal money = new BigDecimal(requestValue);
      money = money.multiply(new BigDecimal("100"));
      return String.valueOf(money.longValue());
    } catch(Exception e) {
       throw new BizException(BizException.COMMBIZ,"金额信息输入格式不正确");
    }
  }
  
  
  @Override
  public void render(HtmlStringBuffer buffer) {

      buffer.elementStart(getTag());

      buffer.appendAttribute("type", getType());
      buffer.appendAttribute("name", getName());
      buffer.appendAttribute("id", getId());
      buffer.appendAttributeEscaped("value", displayValue);
      buffer.appendAttribute("size", getSize());
      buffer.appendAttribute("title", getTitle());
      if (getTabIndex() > 0) {
          buffer.appendAttribute("tabindex", getTabIndex());
      }
      if (getMaxLength() > 0) {
          buffer.appendAttribute("maxlength", getMaxLength());
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

      appendAttributes(buffer);

      if (isDisabled()) {
          buffer.appendAttributeDisabled();
      }
      if (isReadonly()) {
          buffer.appendAttributeReadonly();
      }

      buffer.elementEnd();

      if (getHelp() != null) {
          buffer.append(getHelp());
      }
  }

  
  
}
