package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.DotXh;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the DotXh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DotXhRepository extends JpaRepository<DotXh,Long> {
    
}
