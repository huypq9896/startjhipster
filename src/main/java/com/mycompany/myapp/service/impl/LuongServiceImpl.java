package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.LuongService;
import com.mycompany.myapp.domain.Luong;
import com.mycompany.myapp.repository.LuongRepository;
import com.mycompany.myapp.repository.search.LuongSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Luong.
 */
@Service
@Transactional
public class LuongServiceImpl implements LuongService{

    private final Logger log = LoggerFactory.getLogger(LuongServiceImpl.class);

    private final LuongRepository luongRepository;

    private final LuongSearchRepository luongSearchRepository;

    public LuongServiceImpl(LuongRepository luongRepository, LuongSearchRepository luongSearchRepository) {
        this.luongRepository = luongRepository;
        this.luongSearchRepository = luongSearchRepository;
    }

    /**
     * Save a luong.
     *
     * @param luong the entity to save
     * @return the persisted entity
     */
    @Override
    public Luong save(Luong luong) {
        log.debug("Request to save Luong : {}", luong);
        Luong result = luongRepository.save(luong);
        luongSearchRepository.save(result);
        return result;
    }

    /**
     *  Get all the luongs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Luong> findAll(Pageable pageable) {
        log.debug("Request to get all Luongs");
        return luongRepository.findAll(pageable);
    }

    /**
     *  Get one luong by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Luong findOne(Long id) {
        log.debug("Request to get Luong : {}", id);
        return luongRepository.findOne(id);
    }

    /**
     *  Delete the  luong by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Luong : {}", id);
        luongRepository.delete(id);
        luongSearchRepository.delete(id);
    }

    /**
     * Search for the luong corresponding to the query.
     *
     *  @param query the query of the search
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Luong> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Luongs for query {}", query);
        Page<Luong> result = luongSearchRepository.search(queryStringQuery(query), pageable);
        return result;
    }
}
