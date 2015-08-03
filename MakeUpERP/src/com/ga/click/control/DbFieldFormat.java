package com.ga.click.control;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;

public class DbFieldFormat {
  protected int dataType;
  protected String formatPattern;
  protected boolean isCustom;
  protected DbField defineField;
  
  public String getFormatPattern() {
    return formatPattern;
  }

  public boolean isCustom() {
    return isCustom;
  }

  public DbFieldFormat(int dataType,String formatPattern,boolean isCustom) {
    this.dataType = dataType;
    this.formatPattern = formatPattern;
    this.isCustom = isCustom;
  }
  
  public String getFormatString(Object value) {
    try {
      if (!this.isCustom) {
        if (value != null && !GaUtil.isNullStr(formatPattern)) {
            switch (dataType) {
            case GaConstant.DT_DOUBLE: {
              DecimalFormat fmt = new DecimalFormat(formatPattern);
              MessageFormat msgFmt = new MessageFormat("{0}");
              msgFmt.setFormat(0, fmt);
              return msgFmt.format(value);
            }
            case GaConstant.DT_DATETIME: {
              SimpleDateFormat fmt = new SimpleDateFormat(formatPattern);
              MessageFormat msgFmt = new MessageFormat("{0}");
              msgFmt.setFormat(0, fmt);
              Object[] args;
              if (value instanceof  Timestamp) {                 
                Date date = new Date(((Timestamp)value).getTime());
                args = new Object[] {date};
              } else if (value instanceof String) {
               return value.toString();
              } else {
                args = new Object[] {value};
              }
              return msgFmt.format(args);
            }            
            default:
              return value.toString();
            }
        } else if (value != null){
          return  value.toString();
        } else {
          return "";
        }
      } else {
       return this.getCustomFormatString(value);
      }
    }
    catch(BizException e) {
      throw e;
    }
    catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"格式化处理异常"+ex.getMessage());
    }
  }
  
  protected String getCustomFormatString(Object value) {
    //根据自定义类型进行解析
    throw new BizException(BizException.SYSTEM,"未实现此自定义解析");
  }

  public void setDefineField(DbField field) {
    this.defineField = field;
  }
}
