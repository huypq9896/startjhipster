package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.TuyenDt;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the TuyenDt entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TuyenDtRepository extends JpaRepository<TuyenDt,Long> {
    
}
