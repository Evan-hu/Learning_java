package com.ga.click.control.extra;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import org.apache.click.control.HiddenField;
import org.apache.click.util.ClickUtils;
import org.apache.click.util.HtmlStringBuffer;

public class PKHiddenField extends HiddenField{
	public PKHiddenField(String name, Object value) {
        super(name,value);
    }
	@Override
    public void render(HtmlStringBuffer buffer) {

        buffer.elementStart(getTag());
        buffer.appendAttribute("type", getType());
        buffer.appendAttribute("name", getName());
        buffer.appendAttribute("id", getId());

        Class<?> valueCls = getValueClass();

        if (valueCls == String.class
            || valueCls == Integer.class
            || valueCls == Boolean.class
            || valueCls == Double.class
            || valueCls == Float.class
            || valueCls == Long.class
            || valueCls == Short.class) {

            buffer.appendAttributeEscaped("value", String.valueOf(getValue()));

        } else if (getValueObject() instanceof Date) {
            String dateStr = String.valueOf(((Date) getValueObject()).getTime());
            buffer.appendAttributeEscaped("value", dateStr);

        } else if (getValueObject() instanceof Serializable) {
            try {
                buffer.appendAttribute("value", ClickUtils.encode(getValueObject()));
            } catch (IOException ioe) {
                String msg =
                    "could not encode value for hidden field: "
                    + getValueObject();
                throw new RuntimeException(msg, ioe);
            }
        } else {
            buffer.appendAttributeEscaped("value", getValue());
        }
        buffer.elementEnd();
        buffer.append("<input type ='hidden' id='pkfield' value='").append(getName()).append("' />");
    }
}
