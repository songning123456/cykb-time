package com.sn.time.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author: songning
 * @date: 2020/2/19 22:49
 */
@Configuration
public class ThreadConfig {
    private Integer corePoolSize = 100;
    private Integer maxPoolSize = 1000;
    private Integer queueCapacity = 500;
    private Integer keepAliveSecond = 60;
    private String threadNamePrefix = "TimeAsync_";
    private Integer awaitTerminationSeconds = 60;

    @Bean(name = "TimeExecutor")
    public Executor theftExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(this.corePoolSize);
        executor.setMaxPoolSize(this.maxPoolSize);
        executor.setQueueCapacity(this.queueCapacity);
        executor.setKeepAliveSeconds(this.keepAliveSecond);
        executor.setThreadNamePrefix(this.threadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(this.awaitTerminationSeconds);
        return executor;
    }
}
