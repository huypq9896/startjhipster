package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TuyenDt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TuyenDt.
 */
public interface TuyenDtService {

    /**
     * Save a tuyenDt.
     *
     * @param tuyenDt the entity to save
     * @return the persisted entity
     */
    TuyenDt save(TuyenDt tuyenDt);

    /**
     *  Get all the tuyenDts.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<TuyenDt> findAll(Pageable pageable);

    /**
     *  Get the "id" tuyenDt.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    TuyenDt findOne(Long id);

    /**
     *  Delete the "id" tuyenDt.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the tuyenDt corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<TuyenDt> search(String query, Pageable pageable);
}
