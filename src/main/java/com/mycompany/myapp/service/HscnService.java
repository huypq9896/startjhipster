package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Hscn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Hscn.
 */
public interface HscnService {

    /**
     * Save a hscn.
     *
     * @param hscn the entity to save
     * @return the persisted entity
     */
    Hscn save(Hscn hscn);

    /**
     *  Get all the hscns.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Hscn> findAll(Pageable pageable);

    /**
     *  Get the "id" hscn.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Hscn findOne(Long id);

    /**
     *  Delete the "id" hscn.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the hscn corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Hscn> search(String query, Pageable pageable);
}
