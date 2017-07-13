package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DotXhService;
import com.mycompany.myapp.domain.DotXh;
import com.mycompany.myapp.repository.DotXhRepository;
import com.mycompany.myapp.repository.search.DotXhSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing DotXh.
 */
@Service
@Transactional
public class DotXhServiceImpl implements DotXhService{

    private final Logger log = LoggerFactory.getLogger(DotXhServiceImpl.class);

    private final DotXhRepository dotXhRepository;

    private final DotXhSearchRepository dotXhSearchRepository;

    public DotXhServiceImpl(DotXhRepository dotXhRepository, DotXhSearchRepository dotXhSearchRepository) {
        this.dotXhRepository = dotXhRepository;
        this.dotXhSearchRepository = dotXhSearchRepository;
    }

    /**
     * Save a dotXh.
     *
     * @param dotXh the entity to save
     * @return the persisted entity
     */
    @Override
    public DotXh save(DotXh dotXh) {
        log.debug("Request to save DotXh : {}", dotXh);
        DotXh result = dotXhRepository.save(dotXh);
        dotXhSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the dotXhs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DotXh> findAll(Pageable pageable) {
        log.debug("Request to get all DotXhs");
        return dotXhRepository.findAll(pageable);
    }

    /**
     *  Get one dotXh by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DotXh findOne(Long id) {
        log.debug("Request to get DotXh : {}", id);
        return dotXhRepository.findOne(id);
    }

    /**
     *  Delete the  dotXh by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DotXh : {}", id);
        dotXhRepository.delete(id);
        dotXhSearchRepository.delete(id);
    }

    /**
     * Search for the dotXh corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DotXh> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of DotXhs for query {}", query);
        Page<DotXh> result = dotXhSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
