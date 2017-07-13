package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.DmDonVi;

import com.mycompany.myapp.repository.DmDonViRepository;
import com.mycompany.myapp.repository.search.DmDonViSearchRepository;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing DmDonVi.
 */
@RestController
@RequestMapping("/api")
public class DmDonViResource {

    private final Logger log = LoggerFactory.getLogger(DmDonViResource.class);

    private static final String ENTITY_NAME = "dmDonVi";

    private final DmDonViRepository dmDonViRepository;

    private final DmDonViSearchRepository dmDonViSearchRepository;

    public DmDonViResource(DmDonViRepository dmDonViRepository, DmDonViSearchRepository dmDonViSearchRepository) {
        this.dmDonViRepository = dmDonViRepository;
        this.dmDonViSearchRepository = dmDonViSearchRepository;
    }

    /**
     * POST  /dm-don-vis : Create a new dmDonVi.
     *
     * @param dmDonVi the dmDonVi to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dmDonVi, or with status 400 (Bad Request) if the dmDonVi has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dm-don-vis")
    @Timed
    public ResponseEntity<DmDonVi> createDmDonVi(@Valid @RequestBody DmDonVi dmDonVi) throws URISyntaxException {
        log.debug("REST request to save DmDonVi : {}", dmDonVi);
        if (dmDonVi.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new dmDonVi cannot already have an ID")).body(null);
        }
        DmDonVi result = dmDonViRepository.save(dmDonVi);
        dmDonViSearchRepository.save(result);
        return ResponseEntity.created(new URI("/api/dm-don-vis/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dm-don-vis : Updates an existing dmDonVi.
     *
     * @param dmDonVi the dmDonVi to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dmDonVi,
     * or with status 400 (Bad Request) if the dmDonVi is not valid,
     * or with status 500 (Internal Server Error) if the dmDonVi couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dm-don-vis")
    @Timed
    public ResponseEntity<DmDonVi> updateDmDonVi(@Valid @RequestBody DmDonVi dmDonVi) throws URISyntaxException {
        log.debug("REST request to update DmDonVi : {}", dmDonVi);
        if (dmDonVi.getId() == null) {
            return createDmDonVi(dmDonVi);
        }
        DmDonVi result = dmDonViRepository.save(dmDonVi);
        dmDonViSearchRepository.save(result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dmDonVi.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dm-don-vis : get all the dmDonVis.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dmDonVis in body
     */
    @GetMapping("/dm-don-vis")
    @Timed
    public ResponseEntity<List<DmDonVi>> getAllDmDonVis(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DmDonVis");
        Page<DmDonVi> page = dmDonViRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dm-don-vis");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /dm-don-vis/:id : get the "id" dmDonVi.
     *
     * @param id the id of the dmDonVi to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dmDonVi, or with status 404 (Not Found)
     */
    @GetMapping("/dm-don-vis/{id}")
    @Timed
    public ResponseEntity<DmDonVi> getDmDonVi(@PathVariable Long id) {
        log.debug("REST request to get DmDonVi : {}", id);
        DmDonVi dmDonVi = dmDonViRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dmDonVi));
    }

    /**
     * DELETE  /dm-don-vis/:id : delete the "id" dmDonVi.
     *
     * @param id the id of the dmDonVi to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dm-don-vis/{id}")
    @Timed
    public ResponseEntity<Void> deleteDmDonVi(@PathVariable Long id) {
        log.debug("REST request to delete DmDonVi : {}", id);
        dmDonViRepository.delete(id);
        dmDonViSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/dm-don-vis?query=:query : search for the dmDonVi corresponding
     * to the query.
     *
     * @param query the query of the dmDonVi search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/dm-don-vis")
    @Timed
    public ResponseEntity<List<DmDonVi>> searchDmDonVis(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of DmDonVis for query {}", query);
        Page<DmDonVi> page = dmDonViSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/dm-don-vis");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
