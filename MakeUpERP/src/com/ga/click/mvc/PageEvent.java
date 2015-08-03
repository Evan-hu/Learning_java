package com.ga.click.mvc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.click.ActionResult;
import com.ga.click.exception.BizException;
import com.ga.click.page.QueryPageData;

public class PageEvent {
  
  public static final int PARAMTYPE_QUERYVALUE = 1; //ȡ�����Ĳ�ѯ�����ֶ�
  public static final int PARAMTYPE_INPUTVALUE = 2; //ȡ��������������ֶ�
  public static final int PARAMTYPE_QUERYMAP = 3;   //ȡ���еĲ�ѯ����MAP
  public static final int PARAMTYPE_PAGEQUERY = 4;  //ȡ��ҳ��ѯ����
  public static final int PARAMTYPE_DATAMAP = 5;    //ȡDATAFORM��Ӧ�����е��������MAP
  public static final int PARAMTYPE_LISTDATAMAP = 6; //ȡ�༭ģʽ�µ��б���������
  public static final int PARAMTYPE_REQUEST = 7;   //ֱ�Ӵ�REQUEST�ı����л�ȡ
  public static final int PARAMTYPE_FIXVALUE = 8;  //���ù̶�ֵ
  public static final int PARAMTYPE_REQUESTMAP = 9;  //ֱ�ӻ�ȡrequest��MAP
  
  private String methodName;

  private UnitPage page;
  
  private List<View> methodView = new ArrayList<View>();
  private List<Object[]> methodParam = new ArrayList<Object[]>(); //0:����;1:viewID 2:FIELD ID;3:value class
  
  /*****************************************************
   * ����ҳ���¼�
   * @param methodName �¼���Ӧ��ִ�з���
   * @param page �¼�����ҳ�����
   */
  public PageEvent(String methodName,UnitPage page) {
    this.methodName = methodName;
    this.page = page;
  }
  
  
  public void addEventParam(View view,int paramType) {
    this.addEventParam(view, paramType,"");
  }
  
  /**
   * ��request�еĲ�����Ϊ�¼�����(����Ϊstring)
   * @param requestParamName
   */
  public void addEventRequestParam(View view,String requestParamName) {
    Object[] paramInfo = new Object[4];
    paramInfo[0] = PARAMTYPE_REQUEST;
    paramInfo[1] = view.getViewID();
    paramInfo[2] = requestParamName;
    paramInfo[3] = String.class;
    this.methodParam.add(paramInfo);
    if (!this.methodView.contains(view)) {
      this.methodView.add(view);
    }
  }
  
  /**
   * Ϊ�¼���ӹ̶�ֵ����
   * @param value
   */
  public void addEventFixValueParam(Object value) {
    Object[] paramInfo = new Object[4];
    paramInfo[0] = PARAMTYPE_FIXVALUE;
    paramInfo[1] = "";
    paramInfo[2] = value;
    paramInfo[3] = value.getClass();
    this.methodParam.add(paramInfo);
  }
  
  /**
   * �����¼�����,���ƶ���ͼ��ָ��DBFIELD�ֶ���Ϊ����
   * @param view �¼���������ͼ
   * @param dbFields ��ͼ��DBField�ֶ�
   */
  public void addEventParam(View view,int paramType,String dbFieldCode) {
    Object[] paramInfo = new Object[4];
    paramInfo[0] = paramType;
    paramInfo[1] = view.getViewID();
    paramInfo[2] = dbFieldCode;
    if (paramType == PARAMTYPE_QUERYVALUE) {
      paramInfo[3] = view.getFieldClass(dbFieldCode);
    } else  if (paramType == PARAMTYPE_INPUTVALUE) {
      paramInfo[3] = view.getFieldClass(dbFieldCode);
    } else if (paramType == PARAMTYPE_QUERYMAP) {
      paramInfo[3] = Map.class;            
    } else if (paramType == PARAMTYPE_PAGEQUERY) {
      paramInfo[3] = QueryPageData.class;
    } else if (paramType == PARAMTYPE_DATAMAP) {
      paramInfo[3] = Map.class;
    } else if (paramType == PARAMTYPE_LISTDATAMAP) {
      paramInfo[3] = List.class;
    } else if (paramType == PARAMTYPE_REQUEST) {
    	paramInfo[3] = String.class;
    } else if (paramType == PARAMTYPE_REQUESTMAP) {
    	paramInfo[3] = Map.class;
    }
    this.methodParam.add(paramInfo);
    if (!this.methodView.contains(view)) {
      this.methodView.add(view);
    }
  }
  
  
  
  public List<View> getViewList() {
    return methodView;
  }
  
  /**
   * ��ȡ�¼�����
   * @return
   */
  private Method getMethod() {
    try {
      Method execMethod = null;      
      if (this.methodParam.size() == 0) {
        execMethod = this.page.getClass().getMethod(this.methodName, new java.lang.Class[]{ModelMap.class});
      } else {
        Class[] paramClass = new Class[ this.methodParam.size()];
        int i=0;
        for(Object[] paramInfo : this.methodParam) {
          paramClass[i] = (Class)paramInfo[3];
          i ++;
        }
        execMethod = this.page.getClass().getMethod(this.methodName, paramClass);
      }
      return execMethod;
    } catch(NoSuchMethodException ex) {
    	ex.printStackTrace();
      throw new BizException(BizException.SYSTEM, "��ע����¼�������ƥ�䣬���鷽�����Ͳ���");
    } catch (SecurityException e) {
      throw new BizException(BizException.SYSTEM, "��ע����¼���������public,�޷�����");
    } catch (Exception ex) {
      throw new BizException(BizException.SYSTEM, "��ȡ��ע����¼������쳣");
    }

  }
  
  /**
   * ִ���¼���������
   * @param modeMap ��ǰ��ҳ���������
   * @return 
   */
  public ActionResult execMethodAsControl(ModelMap modelMap) {
    try {
      Method method = getMethod();
      //ִ�в���ע��
      Object[] paramValue = null;
      if (this.methodParam.size() == 0) {
        paramValue = new Object[1];
        paramValue[0] = modelMap;
      } else {
        paramValue = new Object[ this.methodParam.size()];
        int i=0;
        for(Object[] paramInfo : this.methodParam) {
          int paramType = (Integer)paramInfo[0];
          String viewID = (String)paramInfo[1];
          if (paramType == PARAMTYPE_QUERYVALUE) {
            String fieldCode = (String)paramInfo[2];
            paramValue[i] = modelMap.getView(viewID).getFieldParamValue(fieldCode);
          } else  if (paramType == PARAMTYPE_INPUTVALUE) {
            String fieldCode = (String)paramInfo[2];
            paramValue[i] = modelMap.getView(viewID).getFieldInputValue(fieldCode);
          } else if (paramType == PARAMTYPE_QUERYMAP) {
            paramValue[i] = modelMap.getView(viewID).getViewParam();
          } else if (paramType == PARAMTYPE_PAGEQUERY) {
            paramValue[i] = modelMap.getView(viewID).getViewPageQuery();
          } else if (paramType == PARAMTYPE_DATAMAP) {
            paramValue[i] = modelMap.getView(viewID).getViewData();
          } else if (paramType == PARAMTYPE_LISTDATAMAP) {
            paramValue[i] = modelMap.getView(viewID).getViewListData();
          } else if (paramType == PARAMTYPE_REQUEST) {
            String fieldCode = (String)paramInfo[2];
            paramValue[i] = modelMap.getView(viewID).getRequestValue(fieldCode);
          } else if (paramType == PARAMTYPE_FIXVALUE) {
            paramValue[i] = paramInfo[2];
          } else if (paramType == PARAMTYPE_REQUESTMAP) {
              paramValue[i] = modelMap.getRequest().getParameterMap();
            } 
          i ++;
        }
      }
      //��¼��־
      return (ActionResult)method.invoke(this.page,paramValue);
    } catch(BizException e) {
      throw e;
    } catch (InvocationTargetException rex) {
      if (rex.getTargetException() != null && rex.getTargetException() instanceof BizException) {
        throw (BizException)rex.getTargetException();
      } else {
        throw new BizException(BizException.SYSTEM, "�����¼�����ʱ�쳣");
      }
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM, "�����¼�����ʱ�쳣");
    }
  }
  
  
  public void execMethodAsLoad(ModelMap modelMap) {
    try {
      Method method = getMethod();
      //ִ�в���ע��
      Object[] paramValue = null;
      if (this.methodParam.size() == 0) {
        paramValue = new Object[1];
        paramValue[0] = modelMap;
      } else {
        paramValue = new Object[ this.methodParam.size()];
        int i=0;
        for(Object[] paramInfo : this.methodParam) {
          int paramType = (Integer)paramInfo[0];
          String viewID = (String)paramInfo[1];
          if (paramType == PARAMTYPE_QUERYVALUE) {
            String fieldCode = (String)paramInfo[2];
            paramValue[i] = modelMap.getView(viewID).getFieldParamValue(fieldCode);
          } else  if (paramType == PARAMTYPE_INPUTVALUE) {
            String fieldCode = (String)paramInfo[2];
            paramValue[i] = modelMap.getView(viewID).getFieldParamValue(fieldCode);
          } else if (paramType == PARAMTYPE_QUERYMAP) {
            paramValue[i] = modelMap.getView(viewID).getViewParam();
          } else if (paramType == PARAMTYPE_PAGEQUERY) {
            paramValue[i] = modelMap.getView(viewID).getViewPageQuery();
          } else if (paramType == PARAMTYPE_DATAMAP) {
            paramValue[i] = modelMap.getView(viewID).getViewData();
          } else if (paramType == PARAMTYPE_LISTDATAMAP) {
            paramValue[i] = modelMap.getView(viewID).getViewListData();
          } else if (paramType == PARAMTYPE_REQUEST) {
            String fieldCode = (String)paramInfo[2];
            paramValue[i] = modelMap.getView(viewID).getRequestValue(fieldCode);
          } else if (paramType == PARAMTYPE_FIXVALUE) {
            paramValue[i] = paramInfo[2];
          } else if (paramType == PARAMTYPE_REQUESTMAP) {
              paramValue[i] = modelMap.getRequest().getParameterMap();
           }
          i ++;
        }
      }
      method.invoke(this.page,paramValue);
    } catch(BizException e) {
      e.printStackTrace();
      throw e;
    } catch (InvocationTargetException rex) {
      if (rex.getTargetException() != null && rex.getTargetException() instanceof BizException) {
        throw (BizException)rex.getTargetException();
      } else {
        throw new BizException(BizException.SYSTEM, "�����¼�����ʱ�쳣");
      }
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM, "�����¼�����ʱ�쳣");
    }
  }
  
}
