package com.ga.click.util;

import org.apache.click.Context;

public interface SqlDecorator {
  public Object render(Object value, Context context);
}
