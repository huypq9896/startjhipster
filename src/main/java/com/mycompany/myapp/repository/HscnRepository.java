package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Hscn;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Hscn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HscnRepository extends JpaRepository<Hscn,Long> {
    
}
