package com.sn.time.service.impl;

import com.sn.time.entity.Chapters;
import com.sn.time.repository.ChaptersRepository;
import com.sn.time.service.ChaptersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: songning
 * @date: 2020/4/6 11:09
 */
@Service
public class ChaptersServiceImpl implements ChaptersService {

    @Autowired
    private ChaptersRepository chaptersRepository;

    @Override
    public List<Map<String, Object>> findByNovelsId(String novelsId) {
        return chaptersRepository.findByNovelsIdNative(novelsId);
    }

    @Override
    public void save(Chapters chapters) {
        chaptersRepository.save(chapters);
    }
}
