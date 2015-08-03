package com.ga.click.control.extra;

import java.util.ArrayList;
import java.util.List;
import org.apache.click.control.Select;

public class MultiSelect extends Select{
	
  public MultiSelect(String name, String label, boolean required) {
    super(name,label,required);
  }
  @Override
  public void setValueObject(Object object) {
    // TODO Auto-generated method stub
    if (this.isMultiple() && object != null && object instanceof List) {
      List<String> setV = new ArrayList<String>();
      for (Object v : (List<Object>)object) {
        setV.add(v.toString());
      }
      this.setSelectedValues(setV);
    } else {
      super.setValueObject(object);
    }
  }
  

}
