package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.SolieuktService;
import com.mycompany.myapp.domain.Solieukt;
import com.mycompany.myapp.repository.SolieuktRepository;
import com.mycompany.myapp.repository.search.SolieuktSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Solieukt.
 */
@Service
@Transactional
public class SolieuktServiceImpl implements SolieuktService{

    private final Logger log = LoggerFactory.getLogger(SolieuktServiceImpl.class);

    private final SolieuktRepository solieuktRepository;

    private final SolieuktSearchRepository solieuktSearchRepository;

    public SolieuktServiceImpl(SolieuktRepository solieuktRepository, SolieuktSearchRepository solieuktSearchRepository) {
        this.solieuktRepository = solieuktRepository;
        this.solieuktSearchRepository = solieuktSearchRepository;
    }

    /**
     * Save a solieukt.
     *
     * @param solieukt the entity to save
     * @return the persisted entity
     */
    @Override
    public Solieukt save(Solieukt solieukt) {
        log.debug("Request to save Solieukt : {}", solieukt);
        Solieukt result = solieuktRepository.save(solieukt);
        solieuktSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the solieukts.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Solieukt> findAll(Pageable pageable) {
        log.debug("Request to get all Solieukts");
        return solieuktRepository.findAll(pageable);
    }

    /**
     *  Get one solieukt by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Solieukt findOne(Long id) {
        log.debug("Request to get Solieukt : {}", id);
        return solieuktRepository.findOne(id);
    }

    /**
     *  Delete the  solieukt by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Solieukt : {}", id);
        solieuktRepository.delete(id);
        solieuktSearchRepository.delete(id);
    }

    /**
     * Search for the solieukt corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Solieukt> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Solieukts for query {}", query);
        Page<Solieukt> result = solieuktSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
