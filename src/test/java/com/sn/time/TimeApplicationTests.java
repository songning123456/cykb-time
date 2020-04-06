package com.sn.time;

import com.sn.time.repository.ChaptersRepository;
import com.sn.time.repository.NovelsRepository;
import com.sn.time.thread.TimeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class TimeApplicationTests {

    @Autowired
    private TimeProcessor timeProcessor;
    @Autowired
    private NovelsRepository novelsRepository;
    @Autowired
    private ChaptersRepository chaptersRepository;
    @Resource(name = "SourceExecutor")
    private Executor sourceExecutor;

    @Test
    void contextLoads() {
        String novelsId = "402880ea714a6c0401714a71de6f000d";
        List<Map<String, Object>> result = chaptersRepository.findByNovelsIdNative(novelsId);
        System.out.println("");
    }

    @Test
    public void testBiquge() {
        List<String> sourceList = Arrays.asList("笔趣阁", "147小说", "天天书吧", "飞库小说", "趣书吧");
        for (String sourceName : sourceList) {
            sourceExecutor.execute(() -> {
                List<Map<String, Object>> novelsList = novelsRepository.findBySourceNameNative(sourceName);
                // 排除最后一个正在新增的小说
                for (int i = 0, length = novelsList.size(); i < length - 1; i++) {
                    timeProcessor.timeExecutor(novelsList.get(i));
                }
            });
        }
    }
}
