package com.ga.click.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.click.control.Field;
import org.apache.click.control.Form;
import com.ga.click.control.DbField;
import com.ga.click.control.GaConstant;
import com.ga.click.dbutil.PageParam;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.View;
import com.ga.click.util.GaUtil;

public class QueryPageData {
	
  public Map<String,String> queryExpression = new HashMap<String,String>();
  public Map<String,List<String>> queryValues = new HashMap<String,List<String>>();
  public PageParam pageParam = new PageParam();
  public List<DbField> dbFieldList = null;
  
  /**
   * �������ݶ�����ύ�ı����ݽ�������ѯҳ������
   * @param fieldList 
   * @param dataForm
   */
  public void putData(List<DbField> fieldList,Form dataForm) {
      try {
        this.dbFieldList = fieldList;
        if (fieldList != null && dataForm != null) {
          //������ѯ����
          for(DbField field : fieldList) {
            if (field.isQueryParam() && field.isAutoWire()) {
              StringBuffer expression = new StringBuffer();
              List<String> valueList = new ArrayList<String>();
              if (field.getQueryOpera() == GaConstant.QUERY_BETWEEN) {
                //��Χ�Ƚϲ�����
                String minV = "";
                String maxV = "";
                Field minField = dataForm.getField(field.getFieldCode()+"_min_");
                Field maxField = dataForm.getField(field.getFieldCode()+"_max_");
                boolean haveMin = false;
                if (minField != null) {
                  minV = minField.getValue();
                }
                if (maxField != null) {
                  maxV = maxField.getValue();
                }
                if (!GaUtil.isNullStr(minV)) {
                  expression.append(field.getFieldCode().toUpperCase());
                  expression.append(">= ");
                  expression.append(field.getValueExpression());
                  expression.append(" ");
                  valueList.add(minV);
                  haveMin = true;
                }
                if (!GaUtil.isNullStr(maxV)) {
                  if (haveMin) expression.append(" and ");
                  expression.append(field.getFieldCode().toUpperCase());
                  expression.append("<= ");
                  expression.append(field.getValueExpression());
                  expression.append(" ");
                  valueList.add(maxV);
                }
                if (valueList.size() > 0) {
                  this.queryValues.put(field.getFieldCode(), valueList);
                  this.queryExpression.put(field.getFieldCode(),expression.toString());
                }                                                                
              } else if (field.getQueryOpera() == GaConstant.QUERY_CUSTOM) {
                //�Զ��������
                Field valueField = dataForm.getField(field.getFieldCode());                
                Field operaField = dataForm.getField(field.getFieldCode()+"_opera_");                
                if (operaField !=null && valueField != null) {
                  if (!GaUtil.isNullStr(valueField.getValue()) && !GaUtil.isNullStr(operaField.getValue())) {
                    valueList.add(valueField.getValue());
                    expression.append(field.getFieldCode().toUpperCase());
                    expression.append(" ");
                    expression.append(operaField.getValue());
                    expression.append(" ");
                    expression.append(field.getValueExpression());
                    expression.append(" ");
                    this.queryValues.put(field.getFieldCode(), valueList);
                    this.queryExpression.put(field.getFieldCode(),expression.toString());
                  }
                }                 
              } else {
                //ͨ������
                Field valueField = dataForm.getField(field.getFieldCode());
                if (valueField != null && !GaUtil.isNullStr(valueField.getValue())) {
                  String opera = GaConstant.QUERY_OPERA[field.getQueryOpera()];
                  valueList.add(valueField.getValue());
                  this.queryValues.put(field.getFieldCode(), valueList);
                  expression.append(field.getFieldCode());
                  expression.append(" ");
                  expression.append(opera);
                  expression.append(" ");
                  expression.append(field.getValueExpression());
                  expression.append(" ");
                  this.queryExpression.put(field.getFieldCode(),expression.toString());
                }                        
              }
            }
          }
        //��������������
          //����ҳ��Ĺ̶�����
          try {
            Field field = dataForm.getField(GaConstant.FIXPARAM_PAGENO);
            if (field != null && !GaUtil.isNullStr(field.getValue())) {      
              this.pageParam.setPageNumber(Integer.parseInt(field.getValue()));
            }
            field = dataForm.getField(GaConstant.FIXPARAM_PAGESIZE);
            if (field != null && !GaUtil.isNullStr(field.getValue())) {      
              this.pageParam.setPageSize(Integer.parseInt(field.getValue()));
            }
            field = dataForm.getField(GaConstant.FIXPARAM_PAGE_ORDERF);
            if (field != null && !GaUtil.isNullStr(field.getValue())) {
              this.pageParam.setOrderFiledName(field.getValue());
            }
            field = dataForm.getField(GaConstant.FIXPARAM_PAGE_ORDERT);
            if (field != null && !GaUtil.isNullStr(field.getValue())) {
              this.pageParam.setOrderFileOrder(field.getValue());
            }
          } catch (NumberFormatException e) {
          }
       }
    } catch(Exception ex) {
      throw new BizException(BizException.SYSTEM,"���������쳣");
    }
  }
 
  /**
   * ��request����װ��ҳ��ѯ����
   * @param fieldList
   * @param requet
   */
  public void putData(List<DbField> fieldList,View view) {
    try {
      this.dbFieldList = fieldList;
      if (fieldList != null) {               
        //������ѯ����
        for(DbField field : fieldList) {

          if (field.isQueryParam() && field.isAutoWire()) {
            StringBuffer expression = new StringBuffer();
            List<String> valueList = new ArrayList<String>();
            if (field.getQueryOpera() == GaConstant.QUERY_BETWEEN) {
              //��Χ�Ƚϲ�����
              String minV = view.getRequestValue(field.getFieldCode()+"_min_");
              String maxV = view.getRequestValue(field.getFieldCode()+"_max_");
              this.parseQueryField(field, minV, maxV);
            } else if (field.getQueryOpera() == GaConstant.QUERY_CUSTOM) {
              //todo
            } else {
              //ͨ������
              String valueField = "";
              if (view.getViewParam() != null) {
                Object v = view.getViewParam().get(field.getFieldCode());                
                if (v != null) {
                  valueField = v.toString();
                }
              }              
              this.parseQueryField(field, valueField,"");                 
            }
          }
        }
        //��������������
        //����ҳ��Ĺ̶�����
        try {
          String field = view.getRequestValue(GaConstant.FIXPARAM_PAGENO);
          if (!GaUtil.isNullStr(field)) {      
              this.pageParam.setPageNumber(Integer.parseInt(field));
            }
          field = view.getRequestValue(GaConstant.FIXPARAM_PAGESIZE);
          if (!GaUtil.isNullStr(field)) {      
            this.pageParam.setPageSize(Integer.parseInt(field));
          }
          field = view.getRequestValue(GaConstant.FIXPARAM_PAGE_ORDERF);
          if (field != null && !GaUtil.isNullStr(field)) {
            this.pageParam.setOrderFiledName(field);
          }
          field = view.getRequestValue(GaConstant.FIXPARAM_PAGE_ORDERT);
          if (field != null && !GaUtil.isNullStr(field)) {
            this.pageParam.setOrderFileOrder(field);
          }
        } catch (NumberFormatException e) {
        }
     }
   } catch(Exception ex) {
     throw new BizException(BizException.SYSTEM,"���������쳣");
   }
 }
  
  private void parseQueryField(DbField field,String minV,String maxV) {
    if (field.isQueryParam() && field.isAutoWire()) {
      StringBuffer expression = new StringBuffer();
      List<String> valueList = new ArrayList<String>();
      if (field.getQueryOpera() == GaConstant.QUERY_BETWEEN) {
        //��Χ�Ƚϲ�����
        boolean haveMin = false;
        if (!GaUtil.isNullStr(minV)) {
          expression.append(field.getFieldCode().toUpperCase());
          expression.append(">= ");
          expression.append(field.getValueExpression());
          expression.append(" ");
          valueList.add(minV);
          haveMin = true;
        }
        if (!GaUtil.isNullStr(maxV)) {
          if (haveMin) expression.append(" and ");
          expression.append(field.getFieldCode().toUpperCase());
          expression.append("<= ");
          expression.append(field.getValueExpression());
          expression.append(" ");
          valueList.add(maxV);
        }
        if (valueList.size() > 0) {
          this.queryValues.put(field.getFieldCode(), valueList);
          this.queryExpression.put(field.getFieldCode(),expression.toString());
        }                                                                
      } else {
        //ͨ������           
        if (!GaUtil.isNullStr(minV)) {
          String opera = GaConstant.QUERY_OPERA[field.getQueryOpera()];
          valueList.add(minV);
          this.queryValues.put(field.getFieldCode(), valueList);
          expression.append(field.getFieldCode());
          expression.append(" ");
          expression.append(opera);
          expression.append(" ");
          expression.append(field.getValueExpression());
          expression.append(" ");
          this.queryExpression.put(field.getFieldCode(),expression.toString());
        }                        
      }
    }
  }

  
//  /**
//   * ����µĲ�ѯ��ʽ(����ʽֻ��һ��ֵ,����minVֵΪ׼)   
//   * @param fieldCode �ֶ���
//   * @param sqlExpress sqlע����ʽ ��(xx>? and xx<?)
//   * @param minV ��Сֵ
//   * @param maxV ���ֵ
//   */
//  public void addQueryExpression(String fieldCode,String sqlExpress,String minV,String maxV) {
//    List<String> valueList = new ArrayList<String>();
//    this.queryExpression.put(fieldCode,sqlExpress);
//    if (!GaUtil.isNullStr(minV)) {
//      valueList.add(minV);
//    }
//    if (!GaUtil.isNullStr(maxV)) {
//      valueList.add(maxV);
//    }
//    this.queryValues.put(fieldCode, valueList);     
//  }
  
  public void setDefaultQeuryValue(String fieldCode,String minV,String maxV,boolean showQuery) {
    DbField defiField = null;
    for(DbField field : this.dbFieldList) {
      if (field.getFieldCode(false).equals(fieldCode)) {
        defiField = field;
        break;
      }
    }
    if (defiField == null) {
      throw new BizException("δ�����ƶ��ֶεĶ���");
    }    
    this.parseQueryField(defiField,minV,maxV);
    if (!showQuery) {
      defiField.setQueryInputType(GaConstant.INPUT_HIDDEN);
    }
  }
  
  public String GetQueryExpression(String paramCode) {
    List<String> queryValue= GetQueryValues(paramCode);
    if(this.queryExpression.get(paramCode) != null){
    String temp = this.queryExpression.get(paramCode).toLowerCase();
    if(queryValue != null && temp.indexOf("'yyyy-mm-dd'") > -1 ){
       if(queryValue.size() == 2){
         String begTime = queryValue.get(0) ;
         String endTime = queryValue.get(1);
         if(!GaUtil.isNullStr(begTime) && begTime.indexOf(" 00:00:00") < 1){
           begTime  += " 00:00:00";
         }
         if(!GaUtil.isNullStr(endTime) && endTime.indexOf(" 23:59:59") < 1){
           endTime  += " 23:59:59";
         }
         queryValue = new ArrayList<String>();
         queryValue.add(0, begTime);
         queryValue.add(1, endTime);
         this.queryValues.put(paramCode, queryValue);
         return this.queryExpression.get(paramCode).replaceAll("yyyy-MM-dd", "yyyy-MM-dd hh24:mi:ss") ;  
       }else if(queryValue.size() == 1){
         if(temp.indexOf(">=") > -1){
           String begTime = queryValue.get(0) ;
           if(!GaUtil.isNullStr(begTime) && begTime.indexOf(" 00:00:00") < 1){
             begTime  += " 00:00:00";
           }
           queryValue = new ArrayList<String>();
           queryValue.add(0, begTime);
           this.queryValues.put(paramCode, queryValue);
           return this.queryExpression.get(paramCode).replaceAll("yyyy-MM-dd", "yyyy-MM-dd hh24:mi:ss") ;
         }else if(temp.indexOf("<=") > -1){
           String endTime = queryValue.get(0) ;
           if(!GaUtil.isNullStr(endTime) && endTime.indexOf(" 23:59:59") < 1){
             endTime  += " 23:59:59";
           }
           queryValue = new ArrayList<String>();
           queryValue.add(0, endTime);
           this.queryValues.put(paramCode, queryValue);
           return this.queryExpression.get(paramCode).replaceAll("yyyy-MM-dd", "yyyy-MM-dd hh24:mi:ss") ;
         }
       }
       
      }else{
        return this.queryExpression.get(paramCode);
      }
    }
    return this.queryExpression.get(paramCode);
  }
  
  public List<String> GetQueryValues(String paramCode) {
    return this.queryValues.get(paramCode);
  }
  
  public void setPageParam(PageParam pageInfo) {
    this.pageParam = pageInfo;
  }
  public PageParam GetPageParam() {
    return this.pageParam;
  }
}
