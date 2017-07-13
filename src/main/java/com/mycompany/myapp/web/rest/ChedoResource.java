package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Chedo;

import com.mycompany.myapp.repository.ChedoRepository;
import com.mycompany.myapp.repository.search.ChedoSearchRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Chedo.
 */
@RestController
@RequestMapping("/api")
public class ChedoResource {

    private final Logger log = LoggerFactory.getLogger(ChedoResource.class);

    private static final String ENTITY_NAME = "chedo";

    private final ChedoRepository chedoRepository;

    private final ChedoSearchRepository chedoSearchRepository;

    public ChedoResource(ChedoRepository chedoRepository, ChedoSearchRepository chedoSearchRepository) {
        this.chedoRepository = chedoRepository;
        this.chedoSearchRepository = chedoSearchRepository;
    }

    /**
     * POST  /chedos : Create a new chedo.
     *
     * @param chedo the chedo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new chedo, or with status 400 (Bad Request) if the chedo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/chedos")
    @Timed
    public ResponseEntity<Chedo> createChedo(@RequestBody Chedo chedo) throws URISyntaxException {
        log.debug("REST request to save Chedo : {}", chedo);
        if (chedo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new chedo cannot already have an ID")).body(null);
        }
        Chedo result = chedoRepository.save(chedo);
        chedoSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/chedos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /chedos : Updates an existing chedo.
     *
     * @param chedo the chedo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated chedo,
     * or with status 400 (Bad Request) if the chedo is not valid,
     * or with status 500 (Internal Server Error) if the chedo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/chedos")
    @Timed
    public ResponseEntity<Chedo> updateChedo(@RequestBody Chedo chedo) throws URISyntaxException {
        log.debug("REST request to update Chedo : {}", chedo);
        if (chedo.getId() == null) {
            return createChedo(chedo);
        }
        Chedo result = chedoRepository.save(chedo);
        chedoSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, chedo.getId().toString()))
            .body(result);
    }

    /**
     * GET  /chedos : get all the chedos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of chedos in body
     */
    @GetMapping("/chedos")
    @Timed
    public List<Chedo> getAllChedos() {
        log.debug("REST request to get all Chedos");
        return chedoRepository.findAll();
    }

    /**
     * GET  /chedos/:id : get the "id" chedo.
     *
     * @param id the id of the chedo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the chedo, or with status 404 (Not Found)
     */
    @GetMapping("/chedos/{id}")
    @Timed
    public ResponseEntity<Chedo> getChedo(@PathVariable Long id) {
        log.debug("REST request to get Chedo : {}", id);
        Chedo chedo = chedoRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(chedo));
    }

    /**
     * DELETE  /chedos/:id : delete the "id" chedo.
     *
     * @param id the id of the chedo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/chedos/{id}")
    @Timed
    public ResponseEntity<Void> deleteChedo(@PathVariable Long id) {
        log.debug("REST request to delete Chedo : {}", id);
        chedoRepository.delete(id);
        chedoSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/chedos?query=:query : search for the chedo corresponding
     * to the query.
     *
     * @param query the query of the chedo search
     * @return the result of the search
     */
    @GetMapping("/_search/chedos")
    @Timed
    public List<Chedo> searchChedos(@RequestParam String query) {
        log.debug("REST request to search Chedos for query {}", query);
        return StreamSupport
            .stream(chedoSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }

}
