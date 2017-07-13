package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.CtHuong;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing CtHuong.
 */
public interface CtHuongService {

    /**
     * Save a ctHuong.
     *
     * @param ctHuong the entity to save
     * @return the persisted entity
     */
    CtHuong save(CtHuong ctHuong);

    /**
     *  Get all the ctHuongs.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CtHuong> findAll(Pageable pageable);

    /**
     *  Get the "id" ctHuong.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    CtHuong findOne(Long id);

    /**
     *  Delete the "id" ctHuong.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the ctHuong corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<CtHuong> search(String query, Pageable pageable);
}
