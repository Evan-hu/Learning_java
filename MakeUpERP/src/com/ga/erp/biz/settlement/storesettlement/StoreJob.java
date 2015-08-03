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
      logger.info("门店自动结算中,请稍后....");
      biz.autoSettlement();
      logger.info("门店自动结算成功！");
    } catch(Exception e) {
      e.printStackTrace();
      logger.info("门店自动结算处理异常！",e);
    }
  }
}
