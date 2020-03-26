package com.app.net.modules.quartz;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component("expiredCaseCleanJob")
public class ExpiredCaseCleanJob {

	
	public void execute(){
		System.out.println("==========Quartz job======="+new Date());
	}
}
