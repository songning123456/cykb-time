package com.sn.time.strategy.impl;

import com.sn.time.strategy.SourceService;
import org.springframework.stereotype.Service;

/**
 * @author: songning
 * @date: 2020/4/5 15:10
 */
@Service
public class FeikuSourceServiceImpl implements SourceService {
    @Override
    public void updateChapter(String sourceName, String sourceUrl) {
        System.out.println(sourceName);
    }
}
