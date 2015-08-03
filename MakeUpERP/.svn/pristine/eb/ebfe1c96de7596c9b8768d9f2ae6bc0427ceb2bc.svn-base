package com.ga.click.util.charts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChartsContainer {
  
  class XAxisOption {
     boolean isShow = true;         //是否显示X轴
     String font = "13px Trebuchet MS, Verdana, sans-serif";            //X轴字体
     String xAxisText;       //Y轴说明
     String xAxisAlign;      //Y轴说明位置
     int rotation = 0;
  }
  
  class YAxisOption {
    boolean isShow = true;         
    String font = "13px Trebuchet MS, Verdana, sans-serif";           
    String yAxisText; 
    String yAxisAlign; 
    int rotation = 0;
  }
  
  class ExtendInfo {
    Integer extendWidth;
    Integer extendHeight;
    String extendTitle;
    Map<String, Object> extendInfo;
  }
  
  public static final String TYPE_PIE = "pie";
  public static final String TYPE_COLUMN = "column";
  public static final String TYPE_BAR = "bar";
  public static final String TYPE_AREA = "area";
  public static final String TYPE_LINE = "line";
  
  String title;//标题
  String subTitle;//副标题
  
  String formatName;//格式化标题头
  String formatRadix;//图表数据单位
  String formatFunction; //格式化JS方法
  
  String minValue;//图表最小值
  String chartsType;//图表类型
  String containerName;//容器ID
  int containerWidth = 800;
  int containerHeight = 600;
  String legendStyle;
  
  
  PlotOption plotOption;
  XAxisOption xAxisOption;
  YAxisOption yAxisOption;
  ExtendInfo extendInfo;
  Map<String, List<Charts>> xaxisMap;
  
  
  Map<String, Object> formatMap;
  
  void initContainer(){
    this.plotOption = new PlotOption(true, true, "13px Trebuchet MS, Verdana, sans-serif");
    this.xAxisOption = new XAxisOption(); 
    this.yAxisOption = new YAxisOption();
    this.extendInfo = new ExtendInfo();
    formatMap = new LinkedHashMap<String, Object>();
  }
  
  /**
   * 无X轴多元素构造方法
   * @param containerName
   * @param list
   * @param minValue
   * @param type
   */
  public ChartsContainer(String formatName, String containerName, String minValue, String type){
//    if(list == null)
//      throw new NullPointerException("传入LIST为空");
    if(!java.util.regex.Pattern.matches("^(-?\\d+)(\\.\\d+)?", minValue))
      throw new NumberFormatException("传入最小值非数字");
    if(containerName == null || containerName.length() == 0 || !Character.isLetter(containerName.toCharArray()[0]))
      throw new RuntimeException("传入容器名首位必须为字母");
    if( !type.equals(TYPE_AREA) && !type.equals(TYPE_BAR) && !type.equals(TYPE_COLUMN) && !type.equals(TYPE_LINE) && !type.equals(TYPE_PIE))
      throw new RuntimeException("无此类型图表,请检查");
    this.containerName  =   containerName;
    this.minValue       =   minValue;
    this.chartsType     =   type;
    this.formatName    =   formatName;
    initContainer();
  }
  /**
   * 带X轴多元素构造方法
   * @param containerName
   * @param minValue
   * @param type
   */
  public ChartsContainer(String containerName, String minValue, String type){
    if(!java.util.regex.Pattern.matches("^(-?\\d+)(\\.\\d+)?", minValue))
      throw new NumberFormatException("传入最小值非数字");
    if(containerName == null || containerName.length() == 0 || !Character.isLetter(containerName.toCharArray()[0]))
      throw new RuntimeException("传入容器名首位必须为字母");
    if( !type.equals(TYPE_AREA) && !type.equals(TYPE_BAR) && !type.equals(TYPE_COLUMN) && !type.equals(TYPE_LINE) && !type.equals(TYPE_PIE))
      throw new RuntimeException("无此类型图表,请检查");
    this.containerName  =   containerName;
    this.minValue       =   minValue;
    this.chartsType     =   type;
    initContainer();
  }
  /**
   * 设置容器大小
   * @param width
   * @param height
   */
  public void setContainerSize(int width, int height) {
    this.containerWidth = width;
    this.containerHeight = height;
  }
  /**
   * 设置图表主标题,副标题,以及Y轴说明,格式化显示题头
   * @param title 主标题
   * @param subTitle 副标题
   * @param yAxisText Y轴说明
   * @param formatName 格式化显示题头
   */
  public void setTitle(String mainTitle, String subTitle) {
    if(mainTitle == null)
      mainTitle = "";
    if(subTitle == null)
      subTitle = "";
    this.extendInfo.extendTitle = this.title = mainTitle;
    this.subTitle = subTitle;
  }
  
  @Deprecated
  public void setTitle(String title, String subTitle, String yAxisText, String formatName) {
    if(title == null )
      title = "";
    if(subTitle == null)
      subTitle = "";
    this.title = title;
    this.subTitle = subTitle;
    this.yAxisOption.yAxisText = yAxisText;
    this.formatName = formatName;
  }
  /**
   * 绑定格式化显示JS方法;
   * @param jsMethod
   */
  public void bindFormatFunctionItem(String jsMethod){
    this.formatFunction = jsMethod;
  }
  /**
   * 设置Y轴样式
   * @param align Y轴说明显示位置
   */
  public void setYAxisStyle(String align, String yAxisText) {
    this.yAxisOption.yAxisText = yAxisText;
    this.yAxisOption.yAxisAlign = align;
  }
  public void setYAxisStyle(boolean isShow, int rotation) {
    this.yAxisOption.isShow = isShow;
    this.yAxisOption.rotation = rotation;
  }
  /**
   * 设置X轴样式
   * @param align
   * @param xAxisText
   */
  public void setXAxisStyle(String align, String xAxisText) {
    this.xAxisOption.xAxisText = xAxisText;
    this.xAxisOption.xAxisAlign = align;
  }
  
  public void setXAxisStyle(boolean isShow, int rotation) {
    this.xAxisOption.isShow = isShow;
    this.xAxisOption.rotation = rotation;
  }
  /**
   * 设置帮助栏样式
   * @param layout 布局格式
   * @param align 说明垂直位置
   * @param verticalAlign 说明水平位置
   * @param x 说明的LEFT值
   * @param y 说明的TOP值
   * @param borderWith 说明 borderWidth
   * @param backgroudColor 说明背景色
   */
  public void setLegendStyle(String layout, String align, String verticalAlign,
      String x, String y, String borderWith, String backgroudColor){
    StringBuilder sb = new StringBuilder();
    appendPropertyHtml(sb, "layout", layout).append(",");
    appendPropertyHtml(sb, "align", align).append(",");
    appendPropertyHtml(sb, "verticalAlign", verticalAlign).append(",");
    appendPropertyHtml(sb, "x", x).append(",");
    appendPropertyHtml(sb, "y", y).append(",");
    appendPropertyHtml(sb, "borderWith", borderWith).append(",");
    appendPropertyHtml(sb, "backgroudColor", backgroudColor);
    this.legendStyle = sb.toString();
  }
  /**
   * 设置图表type
   * @param type 图表类型
   */
 @Deprecated
  public void setDefaultSeriesType(String type) {
    this.chartsType = type;
  }
  /**
   * 设置图表单位
   * @param radix
   */
  public void setRadix(String radix) {
    this.formatRadix = radix;
  }
  /**
   * 加入X轴元素, 如TITLE重复则组合LIST
   * @param title 数据所属类型
   * @param cs X轴子元素
   */
  public void setXaxisElements(String title, List<Charts> cs){
    if(this.chartsType.toUpperCase().equals("PIE"))
      throw new RuntimeException("不可在饼状统计图中加入X轴元素");
    if(xaxisMap == null)
      xaxisMap = new LinkedHashMap<String, List<Charts>>();
    for(String key : xaxisMap.keySet()){
      if(title.equals(key)){
        xaxisMap.get(key).addAll(cs);
        return ;
      }
    } 
    xaxisMap.put(title, cs);
  }
  /**
   * 加入X轴元素, 如TITLE重复则加入LIST
   * @param title 数据所属类型
   * @param cs X轴子元素
   */
  public void addXaxisElement(String title, Charts cs){
//    if(this.chartsType.toUpperCase().equals("PIE"))
//      throw new RuntimeException("不可在饼状统计图中加入X轴元素");
    if(xaxisMap == null)
      xaxisMap = new LinkedHashMap<String, List<Charts>>();
    for(String key : xaxisMap.keySet()){
      if(title.equals(key)){
        xaxisMap.get(key).add(cs);
        return ;
      }
    }
    xaxisMap.put(title, new ArrayList<Charts>());
    xaxisMap.get(title).add(cs);
  }
  /**
   * 加入扩展信息
   * @param name 
   * @param value 
   */
  public void addExtendInfo(String name, Object value) {
    if(this.extendInfo.extendInfo == null)
      this.extendInfo.extendInfo = new LinkedHashMap<String, Object>();
    if(value instanceof Map){
      if(!(value instanceof LinkedHashMap)){
        System.err.println("传入MAP非LinkedHashMap, 可能会出现乱序");
      }
    }
    extendInfo.extendInfo.put(name, value);
  }  
  
  public void setExtendInfo(Map<String, Object> map) {
    this.extendInfo.extendInfo = map;
  }
  
  public void setExtendFormStyle(Integer width, Integer height, String title) {
    this.extendInfo.extendWidth = width;
    this.extendInfo.extendHeight = height;
    if(title == null)
      throw new NullPointerException("TITLE不能为空");
    this.extendInfo.extendTitle = title;
  }
  
  public PlotOption getPlotOption() {
    return this.plotOption;
  }
  
  
  String getCategories() {
    StringBuffer sb = new StringBuffer();
    if(xaxisMap != null){
      int i  = 0;
      for(String key : xaxisMap.keySet()){
        List<Charts> list = xaxisMap.get(key);
        for (Charts charts : list) {
          sb.append("\""  + charts.getName().toString() + "\"");
          if(i++ != list.size() - 1)
            sb.append(",");
          else
            return sb.toString();
        }
      }
    } else {
//      for(int i = 0; i < charses.size(); i++ ){
//        Charts charts = charses.get(i);
//        sb.append("'" + charts.getName().toString() + "'");
//        if(i != charses.size() - 1)
//          sb.append(",");
//      }
//      sb.append("null");
    }
    
    
    return sb.toString();
  }
//  String getCategoriesTitle() {
//    StringBuffer sb = new StringBuffer();
//    for(int i = 0; i < charses.size(); i++ ){
//      if(charses.get(i).getTitle() != null ){
//        sb.append("'" + charses.get(i).getTitle() + "'");
//        if(i != charses.size() - 1)
//          sb.append(",");
//      } else {
//        sb.append("null");
//        if(i != charses.size() - 1)
//          sb.append(",");
//      }
//      
//    }
//    return sb.toString();
//  } 
  StringBuilder appendPropertyHtml(StringBuilder sb, String name, String value){
    return sb.append(name).append(":'").append(value).append("'");
  }
  StringBuilder appendPropertyHtmlWithoutQuotes(StringBuilder sb, String name, String value){
    return sb.append(name).append(":").append(value).append("");
  }
  
  StringBuilder getHtmlContainer(StringBuilder sb){
    return sb.append("<div id=\"")
             .append(this.containerName)
             .append("\" style=\"width: ").append(containerWidth - 10).append("px; height: ")
                .append(containerHeight).append("px; margin: 0 auto; float:left; \"></div>");
    
  }
  String getMinValue() {
    return minValue == null || minValue.length() == 0 ? "0" : minValue ;
  }
  
  String getFormatFunction(){
    if(formatFunction == null || formatFunction.length() == 0){
      if(this.chartsType.toUpperCase().equals("PIE")){
        formatFunction = "return '<b>'+ this.point.name +'</b>: '+ this.y +' " + this.formatRadix + "';";
      } else {
        formatFunction = "return '' + this.series.name +': '+ this.x + ':'+ this.y +' " + this.formatRadix + "';";
      }
    }
    return formatFunction;
  }
  
  String getChartsType(StringBuilder sb){
    if(this.chartsType.toUpperCase().equals("PIE"))
      return "";
    return appendPropertyHtml(sb, "defaultSeriesType", this.chartsType).toString();
  }
  
  StringBuilder getChartsTitleHtml(StringBuilder sb){
    //初始化标题
    sb.append("title: {\n");
      appendPropertyHtml(sb, "text", this.title);
    sb.append("},\n");
    //初始化副标题
    if(subTitle != null){
      sb.append("subtitle: {\n");
        appendPropertyHtml(sb, "text", this.subTitle);
      sb.append("},\n");
    }
    return sb;
  }
  StringBuilder getXAxisHtml(StringBuilder sb){
//    if(this.chartsType.toUpperCase().equals("PIE")){
//      sb.append("plotArea: {\n\t");
//        appendPropertyHtml(sb, "shadow", null).append(",\n\t");
//        appendPropertyHtml(sb, "borderWidth", null).append(",\n\t");
//        appendPropertyHtml(sb, "backgroundColor", null).append("\n\t");
//      sb.append("},\n");
//      return sb;
//    }
      
    //初始化X轴参数
    
    sb.append("xAxis: {\n");
      sb.append("categories:[\n");
        sb.append(getCategories());
      sb.append("],\n");
      
      //(this.charses != null && charses.size() > 9) ||
      int size = 0;
      for(String key : xaxisMap.keySet()){
        size += xaxisMap.get(key) != null ? xaxisMap.get(key).size() : 0;
      }
      sb.append("labels: {");
        sb.append("enabled: " + this.xAxisOption.isShow + ",");
        sb.append("rotation: " + this.xAxisOption.rotation + ",");
        sb.append("align: 'center',");
        if(size >= 10){
          sb.append("rotation:-30,");  
        }
        sb.append("style: {");
          sb.append("font: '" + this.xAxisOption.font + "'");
        sb.append("}");
      sb.append("},");
      
      sb.append("title: {\n");
      sb.append("enabled:false,");
        sb.append("text: \n'");
          sb.append(this.xAxisOption.xAxisText == null ?  "" : this.xAxisOption.xAxisText);
        sb.append("'\n");
      sb.append("\n");
      sb.append("}\n");
    sb.append("},\n");
    
    return sb;
  }
  StringBuilder getYAxisHtml(StringBuilder sb){
    if(this.chartsType.toUpperCase().equals("PIE"))
      return sb;
    sb.append("yAxis: {\n");
      appendPropertyHtml(sb, "min", getMinValue()).append(",");
      sb.append("title: {\n");
        appendPropertyHtml(sb, "text", yAxisOption.yAxisText).append(",");
        appendPropertyHtml(sb, "align", yAxisOption.yAxisAlign);
      sb.append("},\n");
      
      sb.append("labels: {");
        sb.append("enabled: " + this.yAxisOption.isShow + ",");
        sb.append("rotation: " + this.yAxisOption.rotation + ",");
        sb.append("align: 'right',");
        sb.append("style: {");
          sb.append("font: '" + this.yAxisOption.font + "'");
        sb.append("}");
      sb.append("},");
      
    sb.append("},\n");
    return sb;
  }
  StringBuilder getLegendHtml(StringBuilder sb){
    sb.append("legend: {\n");
      appendPropertyHtml(sb, "layout", "vertical").append(",");
      appendPropertyHtml(sb, "align", "right").append(",");
      appendPropertyHtml(sb, "verticalAlign", "top").append(",");
      appendPropertyHtmlWithoutQuotes(sb, "x", "-50").append(",");
      appendPropertyHtmlWithoutQuotes(sb, "y", "0").append(",");
      appendPropertyHtml(sb, "borderWidth", "1").append(",");
      appendPropertyHtmlWithoutQuotes(sb, "floating", "true").append(",");
      appendPropertyHtmlWithoutQuotes(sb, "shadow", "true").append(",");
      appendPropertyHtml(sb, "backgroundColor", "#FFFFFF");
    sb.append("},\n"); 
    return sb;
  }
  StringBuilder getDataHtml(StringBuilder sb){
    sb.append("series:[");
      sb.append(getData());
    sb.append("]\n");
    return sb;
  }
  String getData() {
    StringBuilder sb = new StringBuilder();
    
    Double AllCount = 0d;
//    DecimalFormat nf = new DecimalFormat("##.00");
    
    if(this.chartsType.toUpperCase().equals("PIE")){
      sb.append("{");
      appendPropertyHtml(sb, "type", "pie").append(",");
      for (String key : this.xaxisMap.keySet()) {
        List<Charts> temp = xaxisMap.get(key);
        for (Charts charts : temp) {
          AllCount += Double.valueOf(charts.getValue().toString());
        }
      }
    }
    
    if(this.xaxisMap == null || xaxisMap.size() == 0){
//      if( !this.chartsType.toUpperCase().equals("PIE") )
//        sb.append("{");
//      sb.append("name: '" + this.formatName + "',");
//      sb.append("data : [");
//      
//      sb.append("]\n");
//      if( !this.chartsType.toUpperCase().equals("PIE"))
//        sb.append("}\n");
    } else {
      int xaxisCount = 0;
      for(String key : xaxisMap.keySet()){
        if( !this.chartsType.toUpperCase().equals("PIE") )
          sb.append("{");
        
          appendPropertyHtml(sb, "name", key).append(",");
          sb.append("data:[");
            List<Charts> temp = xaxisMap.get(key);
            for (int i = 0; i < temp.size() ; i++) {
              sb.append("\n");
              if(this.chartsType.toUpperCase().equals("PIE")){
                sb.append("['")
                  .append(temp.get(i).getName())
                  .append("',")
                  .append(Double.valueOf(temp.get(i).getValue().toString()))
                  .append("]");
              } else {
                sb.append(temp.get(i).getValue());
              }
              if(i != temp.size() - 1)
                sb.append(",");
            }
          sb.append("]");
        if( !this.chartsType.toUpperCase().equals("PIE"))
          sb.append("}\n");
        if(++xaxisCount != xaxisMap.size())
          sb.append(",");
      }
    }
    if(this.chartsType.toUpperCase().equals("PIE")){
      sb.append("}");
    }
    return sb.toString();
  }
  StringBuilder getPlotOptionHtml(StringBuilder sb){
    sb.append("plotOptions: {\n");
      if(chartsType.toUpperCase().equals("PIE")){
        sb.append("pie:{\n");
          sb.append("allowPointSelect").append(":").append(this.plotOption.getIsSelect()).append(",");
          appendPropertyHtml(sb, "cursor", "pointer").append(",");
          sb.append("dataLabels: {\n");
            sb.append("enabled").append(":").append(this.plotOption.getIsShow()).append(",");
            sb.append("formatter:function(){");
              sb.append(" return  '<b>'+ this.point.name +'</b>: '+ twoDecimal(this.percentage) +' %';");
            sb.append("},");
            appendPropertyHtml(sb, "color", "black").append(",");
            sb.append("style:{");
              sb.append("font: '" + this.plotOption.getFont() + "'");
            sb.append("},");
          sb.append("},\n");
          appendPropertyHtmlWithoutQuotes(sb, "showInLegend", plotOption.isShowInLegend()+"");
        sb.append("}\n");
      } else {
        sb.append("bar: {\n");
          sb.append("dataLabels: {\n");
            appendPropertyHtml(sb, "enabled", "true");
          sb.append("}\n");
        sb.append("}\n");
      }
      
    sb.append("},\n");
    return sb;
  }
  StringBuilder initBaseCharts(StringBuilder sb){
    title = title == null ? "" : title ;
    getHtmlContainer(sb);
    //(charses == null && this.xaxisMap != null ) || (charses != null && this.charses.size() > 0)
    if(this.xaxisMap != null && xaxisMap.size() > 0  ){
      sb.append("<script type='text/javascript' language='javascript'>\n");
        sb.append("var").append(" ").append(this.containerName).append(";\n");
        sb.append("$(function() {\n");
          sb.append(this.containerName);  
          sb.append(" = new Highcharts.Chart({\n");
            //初始化基础属性
            sb.append("chart: {\n");
              appendPropertyHtml(sb, "renderTo", this.containerName).append(",");
              getChartsType(sb);
            sb.append("},\n");
            
            //初始化标题及副标题
            getChartsTitleHtml(sb);
            //初始化X轴
            getXAxisHtml(sb);
            //初始化Y轴参数
            getYAxisHtml(sb);
            
            //初始化工具栏及格式化参数
            sb.append("tooltip: {\n");
              sb.append("formatter: function(){\n");
                sb.append(getFormatFunction());
              sb.append("}\n");
            sb.append("},\n");
            
            //初始化NeedToFill
            getPlotOptionHtml(sb);
            
            //初始化说明/图例
            getLegendHtml(sb);
            
            //是否显示JS源代码开发组
            sb.append("credits: {\n");
              sb.append("enabled").append(":").append(false);
            sb.append("},\n");
            
            //导出工具
            sb.append("exporting: {\n");
              sb.append("enabled").append(":").append(false);
            sb.append("},\n");
            
            //初始化数据
            getDataHtml(sb);
            
          sb.append("});\n");
        sb.append("});\n");
      sb.append("</script>");
    } else {
      sb.append("<script>document.getElementById('" + this.containerName + "').innerHTML = '暂无任何数据'</script>");
    }
    return sb;
  }
  StringBuilder getExtendHeaderHtml(StringBuilder sb , boolean b){
    sb.append("<div  style=\"float: left; display: block; overflow: visible; ");
    if(b){
      if(this.extendInfo.extendWidth != null){
        sb.append("width:").append(this.extendInfo.extendWidth + this.containerWidth + 32).append("px;");
      }
      if(this.extendInfo.extendHeight != null){
        sb.append("height:").append(this.extendInfo.extendHeight + this.containerHeight ).append("px;");
      }
    } else {
      sb.append("width:").append(this.containerWidth + 32).append("px; height:").append(this.containerHeight ).append("px;");
    }
    sb.append("\">");
    return sb;
  }
  StringBuilder getExtendTitle(StringBuilder sb){
        sb.append("\n<h1>");
          sb.append(this.extendInfo.extendTitle);
        sb.append("</h1>");
    return sb;
  }
  StringBuilder getExtendFormHeader(StringBuilder sb){
    int width = this.extendInfo == null ? 310 : this.extendInfo.extendWidth == null ? 310 : this.extendInfo.extendWidth ;
    sb.append("<div  style='float: left; display: block; overflow: visible; width:" + width + "px;'>");
    sb.append("  <div class='panel'>");
    sb.append("    <h1>摘要信息</h1>");
    return sb;
  }
  @SuppressWarnings("unchecked")
  StringBuilder getExtendFormContent(StringBuilder sb){
    sb.append("\n<div class=\"\">");
    sb.append("\n <div class=\"tableform division\">");
    sb.append("\n   <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">");
    sb.append("\n     <tbody>");
    
    int i = 1;
    boolean b = false;
    for(String s :this.extendInfo.extendInfo.keySet()){
      
      if( this.extendInfo.extendInfo.get(s) instanceof Map){
        if(i % 2 == 0 && b){
          sb.append("\n</tr>");
          b = false;
          i++;
        }
        sb.append("\n<tr>");
        sb.append("\n <th style=\"text-align: left;\" colspan=\"4\">");
        sb.append("\n   <b style=\"color: red;\">").append(s).append("\n</b>");        
        sb.append("\n </th>");
        sb.append("\n</tr>");
        
        Map<String, Object> map = (Map<String, Object>) this.extendInfo.extendInfo.get(s);
        int j = 1;
        for(String key : map.keySet()){
//          if(key != null && !key.trim().equals("")){
          if(j % 2 == 1)
            sb.append("\n<tr>");
          
          sb.append("\n  <th style='text-align: left;'>").append(key).append("\n</th>");
          sb.append("\n  <td>").append(map.get(key)).append("\n</td>");
          
          if(j % 2 == 0)
            sb.append("\n</tr>");
          
          j = 1 - j % 2; 
        }
//        }
        if(j % 2 == 0)
          sb.append("\n</tr>");
        
      } else {   
        if(i % 2 == 1)
          sb.append("\n<tr>");
        if(s != null && !s.trim().equals("")){
        sb.append("\n  <th style='text-align: left;'>").append(s).append("\n</th>");
        sb.append("\n  <td>").append(this.extendInfo.extendInfo.get(s)).append("\n</td>");
        }
        
        if(i % 2 == 0)
          sb.append("\n</tr>");
        else 
          b = b == false;
        i = 1 - i % 2; 
      }
    }
    sb.append("\n     </tbody>");
    sb.append("\n   </table>");
    sb.append("\n </div>");
    sb.append("\n</div>");
    return sb;
  }
  StringBuilder getExtendFormFooter(StringBuilder sb){
    sb.append(" </div>");  
    sb.append("</div>");
    return sb;
  }
  
  public String getScript() {
    StringBuilder sb = new StringBuilder();
    if(this.extendInfo.extendInfo != null && this.extendInfo.extendInfo.size() > 0){
      getExtendHeaderHtml(sb , true);
        sb.append("<div class=\"panel\">");
        
          getExtendTitle(sb);
          
          sb.append("<div id=\"" + this.containerName + "Div\"  style=\"\">");
            initBaseCharts(sb);
            
            getExtendFormHeader(sb);
              getExtendFormContent(sb);
            getExtendFormFooter(sb);
            
          sb.append("</div>");
          
        sb.append("</div>");  
      sb.append("</div>");    
    } else {
      getExtendHeaderHtml(sb, false);
        sb.append("<div class=\"panel\">");
          getExtendTitle(sb);
          
          sb.append("<div id=\"" + this.containerName + "Div\"  style=\";\">");
            initBaseCharts(sb);
          sb.append("</div>");
          
        sb.append("</div>");  
      sb.append("</div>");  
    }
    
    return sb.toString();
  }
  
  public String toString() {
    return getScript();
  }
  
  public void setShowInLegend(boolean showInLegend) {
    plotOption.setShowInLegend(showInLegend);
  }
 
}
