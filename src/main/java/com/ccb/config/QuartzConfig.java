package com.ccb.config;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;


import com.ccb.quartz.QuartzDemo;
import com.ccb.quartz.QuartzDemo2;

/**
 * Quartz配置类
 *
 *
 */
@Configuration
public class QuartzConfig {
	
	// 配置触发器1
	  @Bean(name = "firstTrigger")
	  public SimpleTriggerFactoryBean firstTrigger(JobDetail firstJobDetail) {
	    SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
	    trigger.setJobDetail(firstJobDetail);
	    // 设置任务启动延迟
	    trigger.setStartDelay(0);
	    // 每5秒执行一次
	    trigger.setRepeatInterval(5000);
	    return trigger;
	  }
	 
	  // 配置定时任务1
	  @Bean(name = "firstJobDetail")
	  public MethodInvokingJobDetailFactoryBean firstJobDetail() {
		QuartzDemo firstJob = (QuartzDemo) SpringUtil.getBean(QuartzDemo.class);
	    MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
	    // 是否并发执行
	    jobDetail.setConcurrent(false);
	    // 为需要执行的实体类对应的对象
	    jobDetail.setTargetObject(firstJob);
	    // 需要执行的方法
	    jobDetail.setTargetMethod("task");
	    return jobDetail;
	  }
	 
	  // 配置触发器2
	  @Bean(name = "secondTrigger")
	  public CronTriggerFactoryBean secondTrigger(JobDetail secondJobDetail) {
	    CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
	    trigger.setJobDetail(secondJobDetail);
	    // cron表达式
	    trigger.setCronExpression("*/5 * * * * ?");
	    return trigger;
	  }
	 
	  // 配置定时任务2
	  @Bean(name = "secondJobDetail")
	  public MethodInvokingJobDetailFactoryBean secondJobDetail() {
		QuartzDemo2 secondJob = (QuartzDemo2) SpringUtil.getBean(QuartzDemo2.class);
	    MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
	    // 是否并发执行
	    jobDetail.setConcurrent(false);
	    // 为需要执行的实体类对应的对象
	    jobDetail.setTargetObject(secondJob);
	    // 需要执行的方法
	    jobDetail.setTargetMethod("task");
	    return jobDetail;
	  }
	 
	  // 配置Scheduler
	  @Bean(name = "scheduler")
	  public SchedulerFactoryBean schedulerFactory(Trigger firstTrigger, Trigger secondTrigger) {
	    SchedulerFactoryBean bean = new SchedulerFactoryBean();
	    // 延时启动，应用启动1秒后
	    bean.setStartupDelay(1);
	    // 注册触发器
	    bean.setTriggers(firstTrigger, secondTrigger);
	    return bean;
	  }
}
