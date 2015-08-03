package com.ga.erp.biz.settlement.suppliersettlement;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SupplierJob implements Job {
  private static Logger logger = Logger.getLogger(SupplierJob.class);
  
  @Override
  public void execute(JobExecutionContext arg0) throws JobExecutionException {
    try {
      SuppSettlementBiz biz = new SuppSettlementBiz(null);
      logger.info("供应商自动结算中，请稍后....");
      biz.autoSettlement();
      logger.info("供应商自动结算成功！");
    } catch(Exception e) {
      e.printStackTrace();
      logger.info("供应商自动结算处理异常！",e);
    }
  }
}
