package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.Luong;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Luong entity.
 */
public interface LuongSearchRepository extends ElasticsearchRepository<Luong, Long> {
}
