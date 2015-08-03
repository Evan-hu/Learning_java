package com.ga.click.control.extra;

import java.util.Arrays;
import java.util.List;
import org.apache.click.control.Option;
import org.apache.click.extras.control.CheckList;
import org.apache.click.util.HtmlStringBuffer;

public class CheckboxList extends CheckList{
  /**
   * 构建复选框对象
   * @param code
   * @param name
   * @param isRequire
   */
	public CheckboxList(String code,String name,boolean isRequire) {
	  super(code,name,isRequire);
	}
	
	public void render(HtmlStringBuffer buffer) {
    // the options
    List<Option> optionsList = getOptionList();
    if (!optionsList.isEmpty()) {
        int i = -1;
        for (Option option : optionsList) {
            i++;         
            buffer.append("<input type=\"checkbox\" ");
            buffer.appendAttributeEscaped("value", option.getValue());
            buffer.append(" id=\"").append(getName()).append('_').append(i).append("\"");
            buffer.appendAttribute("name", getName());


            // set checked status
            boolean checked = false;
            List<String> values = getSelectedValues();
            for (int k = 0; k < values.size(); k++) {
                if (String.valueOf(values.get(k)).equals(option.getValue())) {
                    checked = true;
                }
            }

            if (checked) {
                buffer.appendAttribute("checked", "checked");
            }
            if (isReadonly() || isDisabled()) {
                buffer.appendAttributeDisabled();
            }
            buffer.append(">");
            buffer.append(option.getLabel());
        }
    }
  }

  @Override
  public void setValueObject(Object object) {
    // TODO Auto-generated method stub
    if (object == null) {
      this.setSelectedValues(null);
      return;
    }
   String value = object.toString();
   this.setSelectedValues(Arrays.asList(value.split(",")));
  }
	
	 
	 
}
