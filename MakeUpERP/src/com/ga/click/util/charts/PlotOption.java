package com.ga.click.util.charts;

public class PlotOption {
    boolean isSelect;
    boolean isShow;
    String font;
    boolean showInLegend = true;

    public PlotOption(boolean isSelect, boolean isShow, String font) {
      this.isSelect = isSelect;
      this.isShow = isShow;
      this.font = font;
    }
    
    public boolean getIsSelect() {
      return this.isSelect;
    }
    public boolean getIsShow() {
      return this.isShow;
    }
    
    public String getFont() {
      return this.font;
    }
    
    public void setIsSelect(boolean isSelect) {
      this.isSelect = isSelect;
    }
    
    public void setIsShow(boolean isShow) {
      this.isShow = isShow;
    }
    
    public void setFont(String font) {
      this.font = font;
    }

    public boolean isShowInLegend() {
      return showInLegend;
    }

    public void setShowInLegend(boolean showInLegend) {
      this.showInLegend = showInLegend;
    }
    
    
}
