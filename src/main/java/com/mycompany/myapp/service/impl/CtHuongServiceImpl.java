package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.CtHuongService;
import com.mycompany.myapp.domain.CtHuong;
import com.mycompany.myapp.repository.CtHuongRepository;
import com.mycompany.myapp.repository.search.CtHuongSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CtHuong.
 */
@Service
@Transactional
public class CtHuongServiceImpl implements CtHuongService{

    private final Logger log = LoggerFactory.getLogger(CtHuongServiceImpl.class);

    private final CtHuongRepository ctHuongRepository;

    private final CtHuongSearchRepository ctHuongSearchRepository;

    public CtHuongServiceImpl(CtHuongRepository ctHuongRepository, CtHuongSearchRepository ctHuongSearchRepository) {
        this.ctHuongRepository = ctHuongRepository;
        this.ctHuongSearchRepository = ctHuongSearchRepository;
    }

    /**
     * Save a ctHuong.
     *
     * @param ctHuong the entity to save
     * @return the persisted entity
     */
    @Override
    public CtHuong save(CtHuong ctHuong) {
        log.debug("Request to save CtHuong : {}", ctHuong);
        CtHuong result = ctHuongRepository.save(ctHuong);
        ctHuongSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the ctHuongs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CtHuong> findAll(Pageable pageable) {
        log.debug("Request to get all CtHuongs");
        return ctHuongRepository.findAll(pageable);
    }

    /**
     *  Get one ctHuong by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CtHuong findOne(Long id) {
        log.debug("Request to get CtHuong : {}", id);
        return ctHuongRepository.findOne(id);
    }

    /**
     *  Delete the  ctHuong by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CtHuong : {}", id);
        ctHuongRepository.delete(id);
        ctHuongSearchRepository.delete(id);
    }

    /**
     * Search for the ctHuong corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CtHuong> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of CtHuongs for query {}", query);
        Page<CtHuong> result = ctHuongSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
