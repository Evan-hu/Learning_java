package com.company.shop.util.jstl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ShowDateTag extends SimpleTagSupport {
	
	private Date date;
	private SimpleDateFormat simpleDateFormat;
	
//	private Date nowDate
	
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		if(date !=null ){
			String showDataString=null;//显示结果
			long timeLag = (System.currentTimeMillis() - date.getTime())/1000;// �?
			if(timeLag > 4*24*3600){
				simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				showDataString=simpleDateFormat.format(date);
			}else if(timeLag >= 24*3600){
				showDataString = (int)Math.floor(timeLag/(24*3600))+"天前";
			}else if(timeLag >= 12*3600){
				showDataString = "半天前";
			}else if(timeLag >= 3600){
				showDataString = (int)Math.floor(timeLag/3600)+"小时前";
			}else if(timeLag >= 30*60){
				showDataString = "半小时前";
			}else if(timeLag >= 60){
				showDataString = (int)Math.floor(timeLag/60)+"分钟前";
			}else{
				showDataString = (int)Math.floor(timeLag)+"秒前";
			}
			getJspContext().getOut().write(showDataString);
		}
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
