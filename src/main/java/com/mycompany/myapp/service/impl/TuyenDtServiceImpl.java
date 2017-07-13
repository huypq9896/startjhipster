package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TuyenDtService;
import com.mycompany.myapp.domain.TuyenDt;
import com.mycompany.myapp.repository.TuyenDtRepository;
import com.mycompany.myapp.repository.search.TuyenDtSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing TuyenDt.
 */
@Service
@Transactional
public class TuyenDtServiceImpl implements TuyenDtService{

    private final Logger log = LoggerFactory.getLogger(TuyenDtServiceImpl.class);

    private final TuyenDtRepository tuyenDtRepository;

    private final TuyenDtSearchRepository tuyenDtSearchRepository;

    public TuyenDtServiceImpl(TuyenDtRepository tuyenDtRepository, TuyenDtSearchRepository tuyenDtSearchRepository) {
        this.tuyenDtRepository = tuyenDtRepository;
        this.tuyenDtSearchRepository = tuyenDtSearchRepository;
    }

    /**
     * Save a tuyenDt.
     *
     * @param tuyenDt the entity to save
     * @return the persisted entity
     */
    @Override
    public TuyenDt save(TuyenDt tuyenDt) {
        log.debug("Request to save TuyenDt : {}", tuyenDt);
        TuyenDt result = tuyenDtRepository.save(tuyenDt);
        tuyenDtSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the tuyenDts.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TuyenDt> findAll(Pageable pageable) {
        log.debug("Request to get all TuyenDts");
        return tuyenDtRepository.findAll(pageable);
    }

    /**
     *  Get one tuyenDt by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TuyenDt findOne(Long id) {
        log.debug("Request to get TuyenDt : {}", id);
        return tuyenDtRepository.findOne(id);
    }

    /**
     *  Delete the  tuyenDt by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TuyenDt : {}", id);
        tuyenDtRepository.delete(id);
        tuyenDtSearchRepository.delete(id);
    }

    /**
     * Search for the tuyenDt corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TuyenDt> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TuyenDts for query {}", query);
        Page<TuyenDt> result = tuyenDtSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
