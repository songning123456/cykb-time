package com.sn.time.repository;

import com.sn.time.entity.Novels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @author: songning
 * @date: 2020/3/9 23:01
 */
public interface NovelsRepository extends JpaRepository<Novels, String> {

    @Query(value = "select id as novelsId, source_name as sourceName, source_url as sourceUrl from novels where source_name = ?1 order by create_time asc", nativeQuery = true)
    List<Map<String, Object>> findBySourceNameNative(String sourceName);
}
