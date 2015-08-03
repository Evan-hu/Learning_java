package com.ga.click.control.tree;

import java.util.ArrayList;
import java.util.List;
import org.apache.click.control.Field;
import org.apache.click.util.HtmlStringBuffer;

public class TreeField extends Field{

    private TreeControl treeControl;
    
    public TreeField(String name,String lable) {
        super(name,lable);
    }
    
    public List<String> getValues(){
        String[] values = getContext().getRequest().getParameterValues(this.getName());
        List<String> valueList = new ArrayList<String>();
        if(values != null && values.length > 0){
            for(String value : values){
                valueList.add(value);
            }
        }
        return valueList;
    }
      
    public void setTreeControl(TreeControl treeControl) {
        this.treeControl = treeControl;
    }

    public void render(HtmlStringBuffer buffer) {
        buffer.append(treeControl.toString());
    }

    
}
