package com.sn.time.schedule;

import com.sn.time.repository.NovelsRepository;
import com.sn.time.strategy.SourceContent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

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
    @Resource(name = "SourceExecutor")
    private Executor sourceExecutor;

    /**
     * 每天凌晨执行
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateLatest() {
        try {
            List<String> sourceList = Arrays.asList("笔趣阁", "147小说", "天天书吧", "飞库小说", "趣书吧");
            for (String sourceName : sourceList) {
                sourceExecutor.execute(() -> {
                    List<Map<String, Object>> novelsList = novelsRepository.findBySourceNameNative(sourceName);
                    // 排除最后一个正在新增的小说
                    for (int i = 0, length = novelsList.size(); i < length - 1; i++) {
                        SourceContent.doSource(novelsList.get(i));
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("定时更新失败: {}", e.getMessage());
        }
    }
}
