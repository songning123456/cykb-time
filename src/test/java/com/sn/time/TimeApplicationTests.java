package com.sn.time;

import com.sn.time.repository.NovelsRepository;
import com.sn.time.thread.TimeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    void contextLoads() {
    }

    @Test
    public void testBiquge() {
        List<Map<String, Object>> novelsList = novelsRepository.findNative();
        for (Map<String, Object> novels : novelsList) {
            timeProcessor.timeExecutor(novels);
        }
    }

}
