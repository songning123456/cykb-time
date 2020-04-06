package com.sn.time.thread;

import com.sn.time.strategy.SourceContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: songning
 * @date: 2020/4/5 14:39
 */
@Component
@Slf4j
public class TimeProcessor {

    @Async("TimeExecutor")
    public void timeExecutor(Map<String, Object> map) {
        try {
            SourceContent.doSource(map);
        } catch (Exception e) {
            log.error("定时更新失败: {}", e.getMessage());
        }
    }
}
