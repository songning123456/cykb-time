package com.sn.time.strategy;

/**
 * @author: songning
 * @date: 2020/4/5 15:20
 */
public class SourceContent {
    public static void doSource(String sourceName, String sourceUrl) {
        Class sourceClazz = SourceEnum.currentSource(sourceName);
        try {
            assert sourceClazz != null;
            SourceService sourceService = (SourceService) sourceClazz.newInstance();
            sourceService.updateChapter(sourceName, sourceUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
