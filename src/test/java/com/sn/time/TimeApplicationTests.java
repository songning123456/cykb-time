package com.sn.time;

import com.sn.time.repository.ChaptersRepository;
import com.sn.time.repository.NovelsRepository;
import com.sn.time.thread.TimeProcessor;
import com.sn.time.util.BeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

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

    @Test
    void contextLoads() {
        String novelsId = "402880ea714a6c0401714a71de6f000d";
        List<Map<String, Object>> result = chaptersRepository.findByNovelsIdNative(novelsId);
        System.out.println("");
    }

    @Test
    public void testBiquge() {
        List<Map<String, Object>> novelsList = novelsRepository.findNative();
        for (Map<String, Object> novels : novelsList) {
            timeProcessor.timeExecutor(novels);
        }
    }

}
