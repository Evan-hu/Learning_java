package com.ga.erp.biz;

import com.ga.click.mvc.UserACL;

public class ACLBiz {
  protected UserACL userACL = null;
  
  public ACLBiz(UserACL userACL) {
    this.userACL = userACL;
  }
  
}
