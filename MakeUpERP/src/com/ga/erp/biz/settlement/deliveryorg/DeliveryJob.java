package com.ga.erp.biz.settlement.deliveryorg;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DeliveryJob implements Job {
  private static Logger logger = Logger.getLogger(DeliveryJob.class);
  
  @Override
  public void execute(JobExecutionContext arg0) throws JobExecutionException {
    try {
      DeliverySettBiz biz = new DeliverySettBiz(null);
      logger.info("���ͻ����Զ������У����Ժ�....");
      biz.autoSettlement();
      logger.info("���ͻ����Զ�����ɹ���");
    } catch(Exception e) {
      e.printStackTrace();
      logger.info("���ͻ����Զ����㴦���쳣��",e);
    }
  }
}
