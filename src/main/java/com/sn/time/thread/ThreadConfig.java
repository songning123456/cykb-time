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
    private Integer keepAliveSecond = 60 * 60;
    private String threadNamePrefix = "TimeAsync_";
    private Integer awaitTerminationSeconds = 60 * 60 * 3;

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

    @Bean(name = "SourceExecutor")
    public Executor sourceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.setMaxPoolSize(100);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(this.keepAliveSecond);
        executor.setThreadNamePrefix("SourceAsync_");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(this.awaitTerminationSeconds);
        return executor;
    }
}
