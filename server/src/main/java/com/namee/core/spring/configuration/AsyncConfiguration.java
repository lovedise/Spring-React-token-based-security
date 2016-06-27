package com.namee.core.spring.configuration;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
@EnableScheduling
public class AsyncConfiguration implements AsyncConfigurer, SchedulingConfigurer {

	@Override
	@Bean
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(50);
		executor.setMaxPoolSize(200);//쓰레드풀갯수 (default = 1-Integer.MAX_VALUE)
		executor.setQueueCapacity(1000);//작업이 저장되는 큐의 최대크기 , 풀에 쓰레드가 모두 작업중인 경우 큐에서 대기(default = Integer.MAX_VALUE)
		executor.setThreadNamePrefix("MyExecutor-");
		//executor.setKeepAliveSeconds(60);//풀에 들어있는 쓰레드의 최대 유휴 시간 (default = 60seconds)
		executor.setWaitForTasksToCompleteOnShutdown(true);
		/*****************************************************************
		ABORT	작업을 거부하고 예외 발생
		CALLER_RUNS 호출한 쓰레드를 이용해서 실행
		DISCARD	작업을 실행하지 않고 무시
		DISCARD_OLDEST	큐의 헤드에서 하나를 제거하고 작업을 추가한다.
		******************************************************************/
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
      	executor.initialize();
      	return executor;
	}

	@Bean
	public ThreadPoolTaskScheduler taskScheduler()
	{
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(20);
		scheduler.setThreadNamePrefix("task-");
		scheduler.setAwaitTerminationSeconds(60);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		TaskScheduler scheduler = this.taskScheduler();
		taskRegistrar.setTaskScheduler(scheduler);
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return null;
	}

}