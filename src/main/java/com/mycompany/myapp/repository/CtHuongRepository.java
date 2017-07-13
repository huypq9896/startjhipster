package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.CtHuong;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CtHuong entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CtHuongRepository extends JpaRepository<CtHuong,Long> {
    
}
