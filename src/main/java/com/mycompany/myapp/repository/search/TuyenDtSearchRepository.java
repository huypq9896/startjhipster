package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.TuyenDt;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TuyenDt entity.
 */
public interface TuyenDtSearchRepository extends ElasticsearchRepository<TuyenDt, Long> {
}
