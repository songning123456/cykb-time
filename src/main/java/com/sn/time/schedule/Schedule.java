package com.sn.time.schedule;

import com.sn.time.repository.NovelsRepository;
import com.sn.time.thread.TimeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author: songning
 * @date: 2020/4/6 15:38
 */
@Component
@EnableScheduling
@Slf4j
public class Schedule {

    @Autowired
    private NovelsRepository novelsRepository;
    @Autowired
    private TimeProcessor timeProcessor;

    /**
     * 每天凌晨执行
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateLatest() {
        List<Map<String, Object>> novelsList = novelsRepository.findNative();
        for (Map<String, Object> novels : novelsList) {
            timeProcessor.timeExecutor(novels);
        }
    }
}
