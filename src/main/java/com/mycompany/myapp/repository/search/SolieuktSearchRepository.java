package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.Solieukt;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Solieukt entity.
 */
public interface SolieuktSearchRepository extends ElasticsearchRepository<Solieukt, Long> {
}
