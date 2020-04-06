package com.sn.time.repository;

import com.sn.time.entity.Chapters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: songning
 * @date: 2020/3/9 23:00
 */
public interface ChaptersRepository extends JpaRepository<Chapters, String> {

    @Query(value = "select chapter, update_time as updateTime from chapters where novels_id = ?1 order by update_time asc", nativeQuery = true)
    List<Map<String, Object>> findByNovelsIdNative(String novelsId);
}
