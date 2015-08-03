package com.ga.erp.biz.statistic.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.ga.click.util.GaUtil;
import com.sun.jmx.snmp.UserAcl;

/**
 * 
 * @author ���� ��Ӫͳ�Ʋ�������
 */
public class NStatisticUtil {

  // ״̬
  private int timeType = 0; // 0:���� 1:���� 2:����
  private int timeScope = 0; // 0������ 1������ 2��������� 3������ 4������ 5�����30�� 6���Զ���ʱ��
  private int isCompare = 0; // 0: ���Ա� 1:�Ա�
  private int sourceType = 0; // 0: ������ 1:������� 2:ƽ���������
  private String sequence = "desc"; // desc:���� asc:����
  private int shop = 2; // 2��˫� 4������ 5������ 0:������
  private int commodityType = 0; // 0������� 1������ 2�����۶� 3��������
  private int top = 10; // 10�� ��ʾ10�� 50�� ��ʾ50�� 100����ʾ100��
  // ʱ��
  private String beginTime1 = "";
  private String endTime1 = "";
  private String beginTime2 = "";
  private String endTime2 = "";
  // �۸�
  private Long minPrice = 0L;
  private Long maxPrice = 0L;
  //����
  int num = 0;
  int num1 = 0;
  int isBoolean = 0;

  public NStatisticUtil() {
    this.timeType = 0;
    this.timeScope = 0;
    this.isCompare = 0;
    this.sourceType = 0;
    this.sequence = "desc";
    this.shop = 2;
    this.commodityType = 0;
    this.top = 10;
    this.beginTime1 = "";
    this.endTime1 = "";
    this.beginTime2 = "";
    this.endTime2 = "";
    this.maxPrice = 0L;
    this.minPrice = 0L;
    this.isBoolean = 0;
    this.num = 0;
    this.num1 = 0 ;
  }

  public Map<String, Object> ProcessCondition(HttpServletRequest request) {

    // ��ȡҳ�����
    String timeTypeStr = (String) request.getParameter("timeType");
    String beginTimeStr1 = (String) request.getParameter("beginTime1");
    String endTimeStr1 = (String) request.getParameter("endTime1");
    String beginTimeStr2 = (String) request.getParameter("beginTime2");
    String endTimeStr2 = (String) request.getParameter("endTime2");
    String timeScopeStr = (String) request.getParameter("timeScope");
    String isCompareStr = (String) request.getParameter("isCompare");
    String sourceTypeStr = (String) request.getParameter("sourceType");
    String sequenceStr = (String) request.getParameter("orderType");
    String shopStr = (String) request.getParameter("shopType");
    String minPriceStr = (String) request.getParameter("minPrice");
    String maxPriceStr = (String) request.getParameter("maxPrice");
    String commodityTypeStr = (String) request.getParameter("commodityType");
    String topStr = (String) request.getParameter("topType");
    String isBooleanStr = (String) request.getParameter("isBoolean");
    String numStr = (String) request.getParameter("num");
    String numStr1 = (String)request.getParameter("num1");  

    
    // ����ʱ��
    beginTimeStr1 = beginTimeStr1 == null ? "" : beginTimeStr1;
    endTimeStr1 = endTimeStr1 == null ? "" : endTimeStr1;
    beginTimeStr2 = beginTimeStr2 == null ? "" : beginTimeStr2;
    endTimeStr2 = endTimeStr2 == null ? "" : endTimeStr2;
    sequenceStr = sequenceStr == null ? "" : sequenceStr;
    minPriceStr = minPriceStr == null ? "" : minPriceStr;
    maxPriceStr = maxPriceStr == null ? "" : maxPriceStr;

    // ������ֵ
    if (GaUtil.isNumber(isBooleanStr)) {
      isBoolean = Integer.parseInt(isBooleanStr);
    }
    if (!GaUtil.isNullStr(beginTimeStr1)) {
      beginTime1 = beginTimeStr1;
    }
    if (!GaUtil.isNullStr(endTimeStr1)) {
      endTime1 = endTimeStr1;
    }
    if (!GaUtil.isNullStr(beginTimeStr2)) {
      beginTime2 = beginTimeStr2;
    }
    if (!GaUtil.isNullStr(endTimeStr2)) {
      endTime2 = endTimeStr2;
    }
    if (GaUtil.isNumber(timeScopeStr)) {
      timeScope = Integer.parseInt(timeScopeStr);
    }
    if (GaUtil.isNumber(isCompareStr)) {
      isCompare = Integer.parseInt(isCompareStr);
    }
    if (GaUtil.isNumber(timeTypeStr)) {
      timeType = Integer.parseInt(timeTypeStr);
    }
    if (GaUtil.isNumber(sourceTypeStr)) {
      sourceType = Integer.parseInt(sourceTypeStr);
    }
    if (!GaUtil.isNullStr(sequenceStr)) {
      sequence = sequenceStr;
    }
    if (GaUtil.isNumber(shopStr)) {
      shop = Integer.parseInt(shopStr);
    }
    if (GaUtil.isNumber(minPriceStr)) {
      minPrice = Long.parseLong(minPriceStr) * 100;
    }
    if (GaUtil.isNumber(maxPriceStr)) {
      maxPrice = Long.parseLong(maxPriceStr) * 100;
    }
    if (GaUtil.isNumber(commodityTypeStr)) {
      commodityType = Integer.parseInt(commodityTypeStr);
    }
    if (GaUtil.isNumber(topStr)) {
      top = Integer.parseInt(topStr);
    }
    if(GaUtil.isNumber(numStr)){
      num = Integer.parseInt(numStr);
    }
    if(GaUtil.isNumber(numStr1)){
      num1= Integer.parseInt(numStr1);
    }

    // ִ�����ݴ���
    Calendar ca = Calendar.getInstance();
    switch (timeScope) {
    case 0: // ����
      beginTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime())
          + " 00:00:00";
      endTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime()) + " 23:59:59";
      break;
    case 1: // ����
      ca.add(Calendar.DAY_OF_YEAR, -1);
      endTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime()) + " 23:59:59";
      beginTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime())
          + " 00:00:00";
      break;
    case 2: // �������
      endTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime()) + " 23:59:59";
      ca.add(Calendar.DAY_OF_YEAR, -6);
      beginTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime())
          + " 00:00:00";
      break;
    case 3: // ����
      ca.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
      ca.add(Calendar.DAY_OF_YEAR, -1);
      endTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime()) + " 23:59:59";
      ca.add(Calendar.DAY_OF_YEAR, -6);
      beginTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime())
          + " 00:00:00";
      break;
    case 4: // ����
      ca.set(Calendar.DAY_OF_MONTH, 1);
      ca.add(Calendar.DAY_OF_YEAR, -1);
      endTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime()) + " 23:59:59";
      ca.add(Calendar.DAY_OF_YEAR, 1);
      ca.add(Calendar.MONTH, -1);
      beginTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime())
          + " 00:00:00";
      break;
    case 5: // ���30��
      endTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime()) + " 23:59:59";
      ca.add(Calendar.DAY_OF_YEAR, -30);
      beginTime1 = GaUtil.formatDate("yyyy-MM-dd", ca.getTime())
          + " 00:00:00";
      break;
    case 6: // �Զ���ʱ��
      if (GaUtil.isNullStr(beginTimeStr1)) {
        beginTime1 = GaUtil.formatDate("yyyy-MM-dd", new Date())
            + " 00:00:00";
      } else {
        beginTime1 = beginTimeStr1 + " 00:00:00";
      }
      if (GaUtil.isNullStr(endTimeStr1)) {
        endTime1 = GaUtil.formatDate("yyyy-MM-dd", new Date()) + " 23:59:59";
      } else {
        endTime1 = endTimeStr1 + " 23:59:59";
      }
      break;
      default:
        break;
    }
    switch (isCompare) {
    case 0: // ���Ա�

      break;
    case 1: // �Ա�
      if (GaUtil.isNullStr(beginTimeStr2)) {
        beginTime2 = GaUtil.formatDate("yyyy-MM-dd", new Date())
            + " 00:00:00";
      } else {
        beginTime2 = beginTime2 + " 00:00:00";
      }
      if (GaUtil.isNullStr(endTimeStr2)) {
        endTime2 = GaUtil.formatDate("yyyy-MM-dd", new Date()) + " 23:59:59";
      } else {
        endTime2 = endTime2 + " 23:59:59";
      }
      break;
      default:
        break;
    }
    switch (timeType) {
      case 1:
        break;
      case 2:
        break;
      case 3:
        break;
      default:
        break;
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("timeScope", timeScope);
    map.put("isCompare", isCompare);
    map.put("sourceType", sourceType);
    map.put("sequence", sequence);
    map.put("shop", shop);
    map.put("beginTime1", beginTime1);
    map.put("endTime1", endTime1);
    map.put("beginTime2", beginTime2);
    map.put("endTime2", endTime2);
    map.put("minPrice", minPrice);
    map.put("maxPrice", maxPrice);
    map.put("commodityType", commodityType);
    map.put("top", top);
    map.put("timeType", timeType);
    map.put("beginTimeStr1", beginTimeStr1);
    map.put("endTimeStr1", endTimeStr1);
    map.put("beginTimeStr2", beginTimeStr2);
    map.put("endTimeStr2", endTimeStr2);
    map.put("minPriceStr", minPriceStr);
    map.put("maxPriceStr", maxPriceStr);
    map.put("isBoolean", isBoolean);

    return map;

  }
}