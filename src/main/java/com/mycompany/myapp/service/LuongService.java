package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Luong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Luong.
 */
public interface LuongService {

    /**
     * Save a luong.
     *
     * @param luong the entity to save
     * @return the persisted entity
     */
    Luong save(Luong luong);

    /**
     *  Get all the luongs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Luong> findAll(Pageable pageable);

    /**
     *  Get the "id" luong.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    Luong findOne(Long id);

    /**
     *  Delete the "id" luong.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the luong corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<Luong> search(String query, Pageable pageable);
}
