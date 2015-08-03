package com.ga.click.control.table;

import java.util.ArrayList;
import java.util.List;
import org.apache.click.Context;
import org.apache.click.control.Field;
import org.apache.click.util.HtmlStringBuffer;
import com.ga.click.control.SubPage;
import com.ga.click.exception.BizException;
import com.ga.click.util.GaUtil;

public class SubPageList extends Field{

    // Constants --------------------------------------------------------------

    private static final long serialVersionUID = 1L;

    
    private List<SubPage> subPageList = new ArrayList<SubPage>();
    
    
    // Constructors -----------------------------------------------------------

    public int selectedPageIndex = 1;
    
    public SubPageList(String name) {
        super(name);
    }
    
    public SubPageList(String name,String Lable) {
        super(name,Lable);
    }
    


    public int getSelectedPageIndex() {
        return selectedPageIndex;
    }



    public void setSelectedPageIndex(int selectedPageIndex) {
        this.selectedPageIndex = selectedPageIndex;
    }



    public List<SubPage> getSubPageList() {
        return subPageList;
    }

    public void addSubPageList(SubPage subPaget) {
        this.subPageList.add(subPaget);
    }

    public SubPageList() {
    }

    @Override
    public String getTag() {
        return "input";
    }

    public String getType() {
        return "button";
    }

    @Override
    public boolean onProcess() {
        Context context = getContext();

        if (context.isAjaxRequest()) {

            if (isDisabled()) {
                
                if (context.hasRequestParameter(getName())) {
                    setDisabled(false);
                } else {
                    // If field is disabled skip process event
                    return true;
                }
            }

            if (context.hasRequestParameter(getName())) {
                dispatchActionEvent();
            }
        }

        return true;
    }

  
    @Override
    public int getControlSizeEst() {
        return 40;
    }

  
    @Override
    public void render(HtmlStringBuffer buffer) {
        
        if(this.getSubPageList().size() == 0){
            throw new BizException(BizException.SYSTEM, "未定义子页面！");
        }
        if(!GaUtil.isNullStr(this.getLabel())){
           // buffer.append("<h3 class=\"contentTitle\">").append(this.getLabel()).append("</h3>");
        }
        
        buffer.append("<div class=\"tabs\" name=\"").append(this.getName()).append("\">");
        buffer.append("<div class=\"tabsHeader\">");
        buffer.append("<div class=\"tabsHeaderContent\">");
        buffer.append("<ul>");
        int i = 1;
        for(SubPage page : this.getSubPageList()){
            
            buffer.append("<li ");
            if(this.getSelectedPageIndex() == i){
                buffer.append(" class=\"selected\"");
            }
            buffer.append(" ><a href=\"javascript:void(0)\"><span>").append(page.getLable()).append("</span></a></li>");
            ++i;      
        }
        buffer.append("</ul>");
        buffer.append("</div>");
        buffer.append("</div>");
        buffer.append("<div class=\"tabsContent\" style=\"height: 150px;\">");
        for(SubPage page : this.getSubPageList()){
            buffer.append("<div id=\"").append(page.getId()).append("\"></div>");
            buffer.append("<script>$(\"#").append(page.getId()).append("\").loadUrl(\"").append(page.getUrl()).append("\",\"\");</script>");
        }
        buffer.append("<div class=\"tabsFooter\">");
        buffer.append("<div class=\"tabsFooterContent\"></div>");
        buffer.append("</div>");
        buffer.append("</div>");
        buffer.append("</div>");
    }
}
