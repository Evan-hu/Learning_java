package com.ga.click.util;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.click.util.ClickUtils;
import java.sql.ResultSet;

@SuppressWarnings("unchecked")
public class DataPropertyUtils {
  /** Provides a synchronized cache of OGNL expressions. */
  private static final Map<String, Object> OGNL_EXPRESSION_CACHE = new ConcurrentHashMap<String, Object>();

  /** Provides a synchronized cache of get value reflection methods. */
  private static final Map<String, Object> GET_METHOD_CACHE = new ConcurrentHashMap<String, Object>();

  public static Object getValue(Object source, String name) {
    String basePart = name;
    String remainingPart = null;

    if (source instanceof Map) {
      return ((Map) source).get(name);
    }

    int baseIndex = name.indexOf(".");
    if (baseIndex != -1) {
      basePart = name.substring(0, baseIndex);
      remainingPart = name.substring(baseIndex + 1);
    }

    Object value = getObjectPropertyValue(source, basePart, GET_METHOD_CACHE);

    if (remainingPart == null || value == null) {
      return value;
    }
    else {
      return getValue(value, remainingPart, GET_METHOD_CACHE);
    }
  }

  /**
   * Return the property value for the given object and property name. This
   * method uses reflection internally to get the property value.
   * <p/>
   * This method caches the reflected property methods in the given Map cache.
   * You must NOT modify the cache. Also note cache is ONLY valid for the
   * current thread, as access to the cache is not synchronized. If you need
   * multi-threaded access to shared cache use a thread-safe Map object, such
   * as <tt>Collections.synchronizedMap(new HashMap())</tt>.
   * <p/>
   * If the given source object is a <tt>Map</tt> this method will simply
   * return the value for the given key name.
   *
   * @param source the source object
   * @param name the name of the property
   * @param cache the cache of reflected property Method objects, do NOT modify
   * this cache
   * @return the property value for the given source object and property name
   */
  public static Object getValue(Object source, String name, Map cache) {
    String basePart = name;
    String remainingPart = null;

    if (source instanceof Map) {
      return ((Map) source).get(name);
    }
    else if (source instanceof ResultSet) {
      try {
        return ((ResultSet)source).getObject(name);
      }
      catch (Exception ex) {
        return null;
      }
    }
    int baseIndex = name.indexOf(".");
    if (baseIndex != -1) {
      basePart = name.substring(0, baseIndex);
      remainingPart = name.substring(baseIndex + 1);
    }
    Object value = getObjectPropertyValue(source, basePart, cache);

    if (remainingPart == null || value == null) {
      return value;
    }
    else {
      return getValue(value, remainingPart, cache);
    }
  }

  /**
   * Return the property value for the given object and property name using
   * the OGNL library.
   * <p/>
   * This method is thread-safe, and caches parsed OGNL expressions in an
   * internal synchronized cache.
   *
   * @param source the source object
   * @param name the name of the property
   * @param context the OGNL context, do NOT modify this object
   * @return the property value for the given source object and property name
   * @throws OgnlException if an OGN error occurs
   */
  public static Object getValueOgnl(Object source, String name, Map context) throws
      OgnlException {

    Object expression = OGNL_EXPRESSION_CACHE.get(name);
    if (expression == null) {
      expression = Ognl.parseExpression(name);
      OGNL_EXPRESSION_CACHE.put(name, expression);
    }

    return Ognl.getValue(expression, context, source);
  }

  /**
   * Return the property value for the given object and property name using
   * the OGNL library.
   * <p/>
   * This method is thread-safe, and caches parsed OGNL expressions in an
   * internal synchronized cache.
   *
   * @param target the target object to set the property of
   * @param name the name of the property to set
   * @param value the property value to set
   * @param context the OGNL context, do NOT modify this object
   * @throws OgnlException if an OGN error occurs
   */
  public static void setValueOgnl(Object target, String name, Object value,
                                  Map context) throws OgnlException {

    Object expression = OGNL_EXPRESSION_CACHE.get(name);
    if (expression == null) {
      expression = Ognl.parseExpression(name);
      OGNL_EXPRESSION_CACHE.put(name, expression);
    }

    Ognl.setValue(expression,
                  context,
                  target,
                  value);
  }

  // -------------------------------------------------------- Private Methods

  /**
   * Return the property value for the given object and property name. This
   * method uses reflection internally to get the property value.
   *
   * @param source the source object
   * @param name the name of the property
   * @param cache the cache of reflected property Method objects
   * @return the property value for the given source object and property name
   */
  private static Object getObjectPropertyValue(Object source, String name, Map cache) {
    DataPropertyUtils.CacheKey methodNameKey = new DataPropertyUtils.CacheKey(
        source, name);

    Method method = null;
    try {
      method = (Method) cache.get(methodNameKey);

      if (method == null) {

        method = source.getClass().getMethod(ClickUtils.toGetterName(name));
        cache.put(methodNameKey, method);
      }

      return method.invoke(source);

    } catch (NoSuchMethodException nsme) {

      try {
        method = source.getClass().getMethod(ClickUtils.toIsGetterName(name));
        cache.put(methodNameKey, method);

        return method.invoke(source);

      } catch (NoSuchMethodException nsme2) {

        try {
          method = source.getClass().getMethod(name);
          cache.put(methodNameKey, method);

          return method.invoke(source);

        } catch (NoSuchMethodException nsme3) {
          String msg = "No matching getter method found for property '"
                       + name + "' on class " + source.getClass().getName();

          throw new RuntimeException(msg);

        } catch (Exception e) {
          String msg = "Error getting property '" + name + "' from " +
                       source.getClass();
          throw new RuntimeException(msg, e);
        }

      } catch (Exception e) {
        String msg = "Error getting property '" + name + "' from " +
                     source.getClass();
        throw new RuntimeException(msg, e);
      }

    } catch (Exception e) {
      String msg = "Error getting property '" + name + "' from " +
                   source.getClass();
      throw new RuntimeException(msg, e);
    }
  }

  // ---------------------------------------------------------- Inner Classes

  /**
   * See DRY Performance article by Kirk Pepperdine.
   * <p/>
   * http://www.javaspecialists.eu/archive/Issue134.html
   */
  private static class CacheKey {

    /** Class to encapsulate in cache key. */
    private final Class sourceClass;

    /** Property to encapsulate in cache key. */
    private final String property;

    /**
     * Constructs a new CacheKey for the given object and property.
     *
     * @param source the object to build the cache key for
     * @param property the property to build the cache key for
     */
    public CacheKey(Object source, String property) {
      if (source == null) {
        throw new IllegalArgumentException("Null source parameter");
      }
      if (property == null) {
        throw new IllegalArgumentException("Null property parameter");
      }
      this.sourceClass = source.getClass();
      this.property = property;
    }

    @Override
    public final boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof CacheKey)) {
        return false;
      }

      CacheKey that = (CacheKey) o;

      if (!sourceClass.equals(that.sourceClass)) {
        return false;
      }

      if (!property.equals(that.property)) {
        return false;
      }

      return true;
    }

    @Override
    public final int hashCode() {
      return sourceClass.hashCode() * 31 + property.hashCode();
    }
  }

}
