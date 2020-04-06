package com.sn.time.strategy;

import java.util.Map;

/**
 * @author: songning
 * @date: 2020/4/5 15:20
 */
public class SourceContent {
    public static void doSource(Map<String, Object> map) {
        String sourceName = String.valueOf(map.get("sourceName"));
        Class sourceClazz = SourceEnum.currentSource(sourceName);
        try {
            assert sourceClazz != null;
            SourceService sourceService = (SourceService) sourceClazz.newInstance();
            sourceService.updateChapter(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
