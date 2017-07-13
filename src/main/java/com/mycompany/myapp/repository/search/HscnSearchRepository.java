package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.Hscn;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Hscn entity.
 */
public interface HscnSearchRepository extends ElasticsearchRepository<Hscn, Long> {
}
