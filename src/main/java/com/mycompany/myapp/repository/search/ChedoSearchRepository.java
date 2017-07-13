package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.Chedo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Chedo entity.
 */
public interface ChedoSearchRepository extends ElasticsearchRepository<Chedo, Long> {
}
