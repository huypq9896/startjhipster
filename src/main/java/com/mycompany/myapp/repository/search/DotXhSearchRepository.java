package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.DotXh;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DotXh entity.
 */
public interface DotXhSearchRepository extends ElasticsearchRepository<DotXh, Long> {
}
