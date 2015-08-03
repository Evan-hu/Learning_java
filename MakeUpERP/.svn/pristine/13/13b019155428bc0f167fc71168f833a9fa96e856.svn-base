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
      logger.info("配送机构自动结算中，请稍后....");
      biz.autoSettlement();
      logger.info("配送机构自动结算成功！");
    } catch(Exception e) {
      e.printStackTrace();
      logger.info("配送机构自动结算处理异常！",e);
    }
  }
}
