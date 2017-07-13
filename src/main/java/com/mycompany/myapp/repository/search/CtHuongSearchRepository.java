package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.CtHuong;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CtHuong entity.
 */
public interface CtHuongSearchRepository extends ElasticsearchRepository<CtHuong, Long> {
}
