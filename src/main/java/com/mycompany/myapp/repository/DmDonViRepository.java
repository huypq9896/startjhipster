package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DmDonVi;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DmDonVi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DmDonViRepository extends JpaRepository<DmDonVi,Long> {
    
}
