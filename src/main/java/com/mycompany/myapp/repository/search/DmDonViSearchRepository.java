package com.mycompany.myapp.repository.search;

import com.mycompany.myapp.domain.DmDonVi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DmDonVi entity.
 */
public interface DmDonViSearchRepository extends ElasticsearchRepository<DmDonVi, Long> {
}
