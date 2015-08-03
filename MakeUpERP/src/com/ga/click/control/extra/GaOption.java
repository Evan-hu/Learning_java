package com.ga.click.control.extra;

import java.util.List;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.util.HtmlStringBuffer;

public class GaOption extends Option {
	
  private boolean isMark = false;
  
  public GaOption(Object value, Object label) {
    super(value,label);
  }
  
  public void setMark(boolean isMark) {
    this.isMark = isMark;
  }
  
  public void render(Select select, HtmlStringBuffer buffer) {
    buffer.elementStart(getTag());
    if (isMark) {
      buffer.appendAttribute("style", "color:#CC0000;");
    }
    if (select.isMultiple()) {
        if (!select.getSelectedValues().isEmpty()) {
            // Search through selection list for matching value
            List values = select.getSelectedValues();
            for (int i = 0, size = values.size(); i < size; i++) {
                String value = values.get(i).toString();
                if (getValue().equals(value)) {
                    buffer.appendAttribute("selected", "selected");
                    break;
                }
            }
        }

    } else {
        if (getValue().equals(select.getValue())) {
            buffer.appendAttribute("selected", "selected");
        }
    }

    buffer.appendAttributeEscaped("value", getValue());
    buffer.closeTag();

    buffer.appendEscaped(getLabel());

    buffer.elementEnd(getTag());
}
}
