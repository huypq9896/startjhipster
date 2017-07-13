package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.HscnService;
import com.mycompany.myapp.domain.Hscn;
import com.mycompany.myapp.repository.HscnRepository;
import com.mycompany.myapp.repository.search.HscnSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Hscn.
 */
@Service
@Transactional
public class HscnServiceImpl implements HscnService{

    private final Logger log = LoggerFactory.getLogger(HscnServiceImpl.class);

    private final HscnRepository hscnRepository;

    private final HscnSearchRepository hscnSearchRepository;

    public HscnServiceImpl(HscnRepository hscnRepository, HscnSearchRepository hscnSearchRepository) {
        this.hscnRepository = hscnRepository;
        this.hscnSearchRepository = hscnSearchRepository;
    }

    /**
     * Save a hscn.
     *
     * @param hscn the entity to save
     * @return the persisted entity
     */
    @Override
    public Hscn save(Hscn hscn) {
        log.debug("Request to save Hscn : {}", hscn);
        Hscn result = hscnRepository.save(hscn);
        hscnSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the hscns.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Hscn> findAll(Pageable pageable) {
        log.debug("Request to get all Hscns");
        return hscnRepository.findAll(pageable);
    }

    /**
     *  Get one hscn by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Hscn findOne(Long id) {
        log.debug("Request to get Hscn : {}", id);
        return hscnRepository.findOne(id);
    }

    /**
     *  Delete the  hscn by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Hscn : {}", id);
        hscnRepository.delete(id);
        hscnSearchRepository.delete(id);
    }

    /**
     * Search for the hscn corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Hscn> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Hscns for query {}", query);
        Page<Hscn> result = hscnSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
