package com.ga.erp.biz;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import com.ga.click.control.DbFieldFormat;
import com.ga.click.control.GaConstant;
import com.ga.click.dbutil.DbUtils;

public class CustomFormat extends DbFieldFormat {
  
  public CustomFormat(int dataType,String formatPattern,boolean isCustom) {
    super(dataType,formatPattern,isCustom);
  }

  @Override
  protected String getCustomFormatString(Object value) {
    String fmtStr = this.getFormatPattern();
    if (this.dataType == GaConstant.DT_STRING) {
      if (fmtStr.indexOf("nvl.") == 0) {
        //显示空值为特定字符
        if (value == null || ((String)value).equals("")) {
          return fmtStr.replace("nvl.", "");
        }
      } else if (fmtStr.indexOf("nvlSpec.") == 0) {
        if(value != null){
          return formatSpecValue(value.toString(), fmtStr.substring(fmtStr.indexOf(".") + 1));
        }
      } else if (fmtStr.indexOf("nvlSort.") == 0) {
        if(value != null){
          return formatSortName(value.toString());
        }
      } else if (fmtStr.indexOf("nvlBrand.") == 0) {
        if(value != null){
          return formatBrandName(value.toString());
        } 
      } else if (fmtStr.indexOf("mulDouble.") == 0) {//乘以100
        return mulDouble(value);
      } else if (fmtStr.indexOf("divideDouble.") == 0) {
        return divideDouble(value);
      } else if (fmtStr.indexOf("divideDouble.") == 0) {
        //计算折扣价格
      } else if (fmtStr.indexOf("checkValue.") == 0) {
        //处理CHECKVALUE值显示
        return checkValue(value,fmtStr);
      } else if (fmtStr.indexOf("multiCode.") ==0) {
        return multiCode(value);
      }
    } else if (this.dataType == GaConstant.DT_INT || 
        this.dataType == GaConstant.DT_LONG ||
        this.dataType == GaConstant.DT_MONEY){
      if (fmtStr.indexOf("zero.") == 0) {
        //显示空值和0为特定字符
        if (value == null || value.toString().equals("0")) {
          return fmtStr.replace("zero.", "");
        }
      } else if (fmtStr.indexOf("display.") ==0) {
         String fmt = fmtStr.replace("display.", "");  
         DecimalFormat   df   =   new   DecimalFormat(fmt);
         if (this.dataType == GaConstant.DT_MONEY) {
           if (value != null) {
             return df.format(new BigDecimal((String)value));
           }
         } else {
           if (value != null) {
             return df.format(value);
           }
         }
         return "";
      }     
    }
    return value.toString();
  }
  
  /**
   * 格式化规格值显示 (已废弃?)
   * @param specValues 规格值
   * @param index
   * @return
   */
  String formatSpecValue(String specValues, String index){
    return specValues.split(",")[Integer.parseInt(index)];
  }
  
  /**
   * 格式化分类名显示 (已废弃?)
   * @param id
   * @return
   */
  String formatSortName(String id){
    try {
      if(id != null){
        ResultSet rs = DbUtils.query("select SORT_NAME  from SORT WHERE SORT_ID = ? ", true, id);
        return  rs.next()? rs.getString("SORT_NAME"): "未知" ;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "未知";
  }
  
  /**
   * 格式化品牌名(已废弃?)
   * @param id
   * @return
   */
  String formatBrandName(String id){
    try {
      if(id != null){
        ResultSet rs = DbUtils.query("select (case when cname is null then ename else cname end) as name  from COMMODITY_BRAND WHERE BRAND_ID = ? ", true, id);
        return  rs.next()? rs.getString("NAME"): "未知" ;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "未知";
  }
  
  String mulDouble(Object value) {
    return value == null ? "0" : new BigDecimal(value.toString()).multiply(new BigDecimal(100)).toString();
  }
  
  String divideDouble(Object value) {
    return value == null ? "0" : new BigDecimal(value.toString()).divide(new BigDecimal(100)).toString();
  }
  
  /**
   * 多code转换显示(多code用逗号分隔)
   * @param value 值
   * @return
   */
  String multiCode(Object value) {
    String rtnStr = "";
    if (value != null && value instanceof String &&  this.defineField.getLookupData() != null) {
      String[] place = value.toString().split(",");
      for (int i=0;i<place.length;i++) {
        String oneInfo = "";
        for (int chkLen = 6 ;chkLen <= place[i].length(); chkLen = chkLen + 3) {
          String code = place[i].substring(0,chkLen);
          Object v = this.defineField.getLookupData().find(code);
          if (v != null) {
            oneInfo = oneInfo +  v;  
          }          
        }
        rtnStr = rtnStr + "," +oneInfo;
      }
    }
    if (rtnStr.startsWith(",")) rtnStr = rtnStr.substring(1);
    return rtnStr;
  }
  
  String checkValue(Object value,String fmtStr) {
    if (value == null || this.defineField.getLookupData() == null) {
      return "";
    }
    String[] values = value.toString().split(",");
    if (values.length == this.defineField.getLookupData().getKeyList().size()) {
      if (fmtStr.length() > "checkValue.".length()) {
        return fmtStr.substring("checkValue.".length());
      }      
    }
    String rtnInfo = "";
    for(int i =0;i<values.length;i++) {
      Object v = this.defineField.getLookupData().find(values[i]);
      if (v != null) {
        rtnInfo = rtnInfo + "," + v;
      }
    }
    if (rtnInfo.startsWith(",")) {
      return rtnInfo.substring(1);
    } else {
      return "";
    }    
  }
}
