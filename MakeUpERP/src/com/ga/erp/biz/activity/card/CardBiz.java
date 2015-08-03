package com.ga.erp.biz.activity.card;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.ga.click.control.DbField;
import com.ga.click.dbutil.DbUtils;
import com.ga.click.dbutil.PageResult;
import com.ga.click.exception.BizException;
import com.ga.click.mvc.UserACL;
import com.ga.click.page.BizUtil;
import com.ga.click.page.QueryPageData;
import com.ga.click.util.GaUtil;
import com.ga.erp.biz.ACLBiz;

public class CardBiz extends ACLBiz {

  public CardBiz(UserACL userACL) {
    super(userACL);
  }
  
  /**
   * ��ѯ���еĵ��Ӿ������Ϣ
   * @param pageData
   * @param map
   * @param dbFields
   * @return
   */
  public PageResult queryCardLists(QueryPageData pageData,List<DbField> dbFields){
    try {
      String sql=      
                  "select c.CARD_ID,\n" +
                  "       c.CARD_NUM,\n" + 
                  "       cb.CARD_BATCH_ID,\n" + 
                  "       m.MEMBER_NUM,\n" + 
                  "       c.GET_TIME,\n" + 
                  "       c.USE_TIME,\n" + 
                  "       c.STATE,\n" + 
                  "       c.CREATE_TIME\n" + 
                  "  FROM CARD c\n" + 
                  "  LEFT JOIN MEMBER m ON c.MEMBER_ID = m.MEMBER_ID\n" + 
                  "  JOIN CARD_BATCH cb ON cb.CARD_BATCH_ID = c.CARD_BATCH_ID";
      return BizUtil.queryListBySQL(sql, "", "c.CREATE_TIME desc", dbFields, pageData, this.userACL);
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"��ѯ�Ż�ȯ��Ϣʧ��");
    }
  }
  
  /**
   * ��ѯָ�����Ӿ�Ĳ��������Ϣ
   * @param CardID
   * @return
   */
  public Map<String, Object> queryCardInfo(Long CardID){
    try {
      String sql="select c.*,m.TRUENAME,m.TEL from CARD c LEFT JOIN MEMBER m on c.MEMBER_ID = m.MEMBER_ID where c.CARD_ID = ? ";
      
      Map<String, Object> result = DbUtils.queryMap(DbUtils.getConnection(), sql, CardID);
      result.put("NEWSTATE", result.get("STATE"));
      result.put("EXPLAIN", "�޸ĺ��ȯ�Ų����ٴ�ʹ��");
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"��ѯ��Ϣʧ��");
    }
  }
  
  public void addCardsInfo(Map<String, Object> vMap,Map<String, String>funcMap){
    try {
      Long batchPK=Long.valueOf(""+vMap.get("CARD_BATCH_ID"));
      int autoNums=Integer.parseInt(""+vMap.get("AUTO_COUNT"));
      long opPK=this.userACL.getUserID();
      String autoCardFields="CARD_BATCH_ID,OP_ID,CARD_NUM,CREATE_TIME,STATE,GET_TYPE";
      Map<String, Object> autoCardMap = new HashMap<String, Object>();
      autoCardMap.put("CARD_BATCH_ID", batchPK);
      autoCardMap.put("OP_ID", opPK);
      autoCardMap.put("STATE", 1);
      autoCardMap.put("GET_TYPE", 0);
      //**********�Զ����ɵ��Ӿ�
      DbUtils.begin();
      for (int i = 0; i < autoNums; i++) {
        StringBuffer cardNum = new StringBuffer(GaUtil.encodeToMD5(GaUtil.getDateStr(new Date())));
        cardNum.delete(0, 16);
        cardNum.insert(4, "-");
        cardNum.insert(9, "-");
        cardNum.insert(14, "-");
        autoCardMap.put("CARD_NUM", cardNum.toString().toLowerCase());
        DbUtils.add("CARD", autoCardMap, funcMap, autoCardFields);
      }
      DbUtils.commit();
    } catch (Exception e) {
      DbUtils.rollback();
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"���ʧ��");
    }
  }
  
  /**
   * ����ָ�����Ӿ�Ĳ�����Ϣ,����¼����ȯ������־
   * @param valueMap
   */
  public void updateCardState(Map<String, Object> valueMap,Map<String, String>funcMap){
    try {
      if(valueMap.get("CARD_ID") == null){
        throw new BizException("��ѡ��Ҫ�޸ĵ�ȯ");
      }
//      if(valueMap.get("TEL") == null || GaUtil.isNullStr(valueMap.get("TEL") + "")){
//        throw new BizException("����д��Ա�յ����ŵĵ绰����");
//      }
//      Boolean isMobileNoExist = DbUtils.queryLong("SELECT count(*) FROM card c JOIN MEMBER m ON c.MEMBER_ID = " +
//      		"m.MEMBER_ID WHERE c.CARD_ID = ? and m.MOBILE = ?", valueMap.get("CARD_ID"), valueMap.get("TEL")) == 0L;
//      if(isMobileNoExist){
//        throw new BizException("�绰���������ȷ�Ϻ���Ϊ�û��յ����ŵĺ���");
//      }
      DbUtils.begin();
      DbUtils.update("UPDATE CARD set STATE = 2,USE_TIME = NOW() where CARD_ID = ?", valueMap.get("CARD_ID"));
      DbUtils.commit();
    } catch (BizException ex) {
      throw ex;
    } catch (Exception e) {
      throw new BizException(BizException.SYSTEM,"������Ϣʧ��");
    } finally {
      DbUtils.rollback();
    }
  }
  
  /**
   * ����ȯ��־�����б��ѯ
   * @param pageData
   * @param queryMap
   * @param dbFields
   * @return
   */
  public PageResult queryCardOpLogLists(QueryPageData pageData,Map<String, Object>queryMap,List<DbField>dbFields){
    try {
      String sql="select CARD_BATCH.CARD_BATCH_ID,CARD.CARD_NUM,OP.USERNAME,OP.TRUENAME,"+
              "CARD_OP_LOG.CREATE_TIME,CARD_OP_LOG.NOTE,CARD_OP_LOG.ORDER_MASTER_ID "+
              "FROM OP JOIN CARD_OP_LOG JOIN "+
              "CARD JOIN CARD_BATCH "+
              "ON CARD_BATCH.CARD_BATCH_ID=CARD.CARD_BATCH_ID "+
              "ON CARD.CARD_ID=CARD_OP_LOG.CARD_ID "+
              "ON CARD_OP_LOG.OP_ID=OP.OP_ID ";
      
      PageResult result=BizUtil.queryListBySQL(sql, "", "CARD_OP_LOG.CREATE_TIME desc", dbFields, pageData, this.userACL);
      List<Map<String, Object>> datas=result.getListData();
      for (Map<String, Object> m : datas) {
        if(null!=m.get("ORDER_MASTER_ID")&&(!"".equalsIgnoreCase(""+m.get("ORDER_MASTER_ID")))){
          String orders=""+m.get("ORDER_MASTER_ID")+" -> "+queryOrderInforOFOrderMaster(Long.valueOf(""+m.get("ORDER_MASTER_ID")));
          m.put("ORDERS", orders);
        }
      }
      result.setDataList(datas);
      return result;
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"��ѯ��Ϣʧ��");
    }
  }
  
  
  /**
   * ���ⷽ���������ݶ����ܱ�õ����еĶ�����
   * @param orderMasterID
   * @return
   */
  public String queryOrderInforOFOrderMaster(Long orderMasterID){
    try {
      if(orderMasterID==-1L){
        return null;
      }
      String sql="select ORDER_FORM_ID from order_form where ORDER_MASTER_ID= ? ";
      List<Map<String, Object>> orderLists=DbUtils.queryMapList(DbUtils.getConnection(), sql, orderMasterID);
      StringBuffer htmlStr = new StringBuffer();
      if(orderLists!=null){
        for (Map<String, Object> map : orderLists) {
            htmlStr.append("<a id=\"openOrder\" class=\"trbtn\" _windownav=\"dialog,OrderInfo,������Ϣ,1000,600\" " +
            		"_prewindownav=\"navTab,main\" _actionid=\"openOrder\" onclick=\"doLinkPost(this)\" href=\"javascript:void(0);\" " +
            		"url=\"/order/order_detail.htm?ORDER_FORM_ID="+ map.get("ORDER_FORM_ID") +"\">" +
            		"<span>" + map.get("ORDER_FORM_ID") + "(<font color='green'>" + this.getStockHouseName(map.get("ORDER_FORM_ID").toString()) + "</font>)</span></a>" );
            htmlStr.append(";"); 
          }
      }
      return htmlStr.toString();
    } catch (Exception e) {
      e.printStackTrace();
      throw new BizException(BizException.SYSTEM,"��ѯ������Ϣʧ��");
    }
  }
  
  public String getStockHouseName(String orderID){
    String sql=
      "select stockhouse_name\n" +
      "  from stockhouse sh, \n"+
      "order_form o\n" + 
      " where sh.stockhouse_id = o.stockhouse_id\n" + 
      "   and o.order_form_id = ?";
    Map<String, Object> data = DbUtils.queryMap(DbUtils.getConnection(), sql, orderID);
   return data != null?data.get("STOCKHOUSE_NAME").toString():"";
  } 

  public static void main(String[] args) {
	System.out.println(new StringBuffer(GaUtil.encodeToMD5(GaUtil.getDateStr(new Date()))).toString());
  }
  
}
