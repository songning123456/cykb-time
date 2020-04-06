package com.sn.time.strategy.impl;

import com.sn.time.entity.Chapters;
import com.sn.time.service.ChaptersService;
import com.sn.time.service.impl.ChaptersServiceImpl;
import com.sn.time.strategy.SourceService;
import com.sn.time.util.BeanUtil;
import com.sn.time.util.DateUtil;
import com.sn.time.util.HttpUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: songning
 * @date: 2020/4/5 15:09
 */
public class YisiqiSourceServiceImpl implements SourceService {
    @Override
    public void updateChapter(Map<String, Object> map) {
        String sourceUrl = String.valueOf(map.get("sourceUrl"));
        String novelsId = String.valueOf(map.get("novelsId"));
        Document baseDoc = HttpUtil.getHtmlFromUrl(sourceUrl, true);
        String latestChapter = baseDoc.getElementById("info").getElementsByTag("p").get(3).getElementsByTag("a").get(0).html();
        ChaptersService chaptersService = BeanUtil.getBean(ChaptersServiceImpl.class);
        List<Map<String, Object>> isExistList = chaptersService.findByNovelsId(novelsId);
        String lastChapter = String.valueOf(isExistList.get(isExistList.size() - 1).get("chapter"));
        if (!latestChapter.equals(lastChapter)) {
            Elements ddElements = baseDoc.getElementById("list").getElementsByTag("dd");
            Date oldUpdateTime = (Date) isExistList.get(isExistList.size() - 1).get("updateTime");
            String strUpdateTime = baseDoc.getElementById("info").getElementsByTag("p").get(2).html().split("：")[1];
            Date newUpdateTime = DateUtil.strToDate(strUpdateTime, "yyyy-MM-dd HH:mm:ss");
            List<Date> timeList = DateUtil.stepTime(oldUpdateTime, newUpdateTime, ddElements.size() - isExistList.size());
            for (int i = isExistList.size(), iLen = ddElements.size(); i < iLen; i++) {
                Element chapterElement = ddElements.get(i).getElementsByTag("a").get(0);
                String chapter = chapterElement.html();
                boolean isExist = false;
                for (Map<String, Object> item : isExistList) {
                    if (chapter.equals(String.valueOf(item.get("chapter")))) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    String chapterUrl = "http://www.147xiaoshuo.com/" + chapterElement.attr("href");
                    Document chapterDoc = HttpUtil.getHtmlFromUrl(chapterUrl, true);
                    String content = chapterDoc.getElementById("content").html();
                    Chapters chapters = Chapters.builder().chapter(chapter).content(content).novelsId(novelsId).updateTime(timeList.get(i - isExistList.size())).build();
                    chaptersService.save(chapters);
                }
            }
        }
    }
}
