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

    @Query(value = "select source_name as sourceName, source_url as sourceUrl from novels", nativeQuery = true)
    List<Map<String, Object>> findNative();
}
