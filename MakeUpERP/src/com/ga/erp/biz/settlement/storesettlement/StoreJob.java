package com.ga.erp.biz.settlement.storesettlement;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class StoreJob implements Job {
  private static Logger logger = Logger.getLogger(StoreJob.class);
  
  @Override
  public void execute(JobExecutionContext arg0) throws JobExecutionException {
    try {
      StoreSettlementBiz biz = new StoreSettlementBiz(null);
      logger.info("�ŵ��Զ�������,���Ժ�....");
      biz.autoSettlement();
      logger.info("�ŵ��Զ�����ɹ���");
    } catch(Exception e) {
      e.printStackTrace();
      logger.info("�ŵ��Զ����㴦���쳣��",e);
    }
  }
}
