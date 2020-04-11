package com.sn.time;

import com.sn.time.repository.ChaptersRepository;
import com.sn.time.repository.NovelsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class TimeApplicationTests {

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
}
