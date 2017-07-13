package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.DotXh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing DotXh.
 */
public interface DotXhService {

    /**
     * Save a dotXh.
     *
     * @param dotXh the entity to save
     * @return the persisted entity
     */
    DotXh save(DotXh dotXh);

    /**
     *  Get all the dotXhs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DotXh> findAll(Pageable pageable);

    /**
     *  Get the "id" dotXh.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    DotXh findOne(Long id);

    /**
     *  Delete the "id" dotXh.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the dotXh corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<DotXh> search(String query, Pageable pageable);
}
