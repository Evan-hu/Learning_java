package com.ga.click.control;

import java.util.HashMap;
import java.util.Map;

public class SubPage {
    
    private String lable;
    private String url;
    private boolean isDisplayToollBar;
    private String id;
    private Map<String,String> parametrMap = new HashMap<String, String>();
    
    public SubPage(String id,String linkUrl){
        this.id = id;
        this.url = linkUrl;
    }
    public void addParametr(String key,String value){
        parametrMap.put(key, value);
    }
    public String getLable() {
        return lable;
    }
    public void setLable(String lable) {
        this.lable = lable;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public boolean isDisplayToollBar() {
        return isDisplayToollBar;
    }
    public void setDisplayToollBar(boolean isDisplayToollBar) {
        this.isDisplayToollBar = isDisplayToollBar;
    }
    public String getId() {
        return id;
    }
    public Map<String, String> getParametrMap() {
        return parametrMap;
    }
    public void setParametrMap(Map<String, String> parametrMap) {
        this.parametrMap = parametrMap;
    }
    
}
