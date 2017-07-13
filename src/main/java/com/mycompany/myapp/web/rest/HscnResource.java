package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Hscn;
import com.mycompany.myapp.service.HscnService;
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
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Hscn.
 */
@RestController
@RequestMapping("/api")
public class HscnResource {

    private final Logger log = LoggerFactory.getLogger(HscnResource.class);

    private static final String ENTITY_NAME = "hscn";

    private final HscnService hscnService;

    public HscnResource(HscnService hscnService) {
        this.hscnService = hscnService;
    }

    /**
     * POST  /hscns : Create a new hscn.
     *
     * @param hscn the hscn to create
     * @return the ResponseEntity with status 201 (Created) and with body the new hscn, or with status 400 (Bad Request) if the hscn has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/hscns")
    @Timed
    public ResponseEntity<Hscn> createHscn(@Valid @RequestBody Hscn hscn) throws URISyntaxException {
        log.debug("REST request to save Hscn : {}", hscn);
        if (hscn.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new hscn cannot already have an ID")).body(null);
        }
        Hscn result = hscnService.save(hscn);
        return ResponseEntity.created(new URI("/api/hscns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /hscns : Updates an existing hscn.
     *
     * @param hscn the hscn to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated hscn,
     * or with status 400 (Bad Request) if the hscn is not valid,
     * or with status 500 (Internal Server Error) if the hscn couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/hscns")
    @Timed
    public ResponseEntity<Hscn> updateHscn(@Valid @RequestBody Hscn hscn) throws URISyntaxException {
        log.debug("REST request to update Hscn : {}", hscn);
        if (hscn.getId() == null) {
            return createHscn(hscn);
        }
        Hscn result = hscnService.save(hscn);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, hscn.getId().toString()))
            .body(result);
    }

    /**
     * GET  /hscns : get all the hscns.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of hscns in body
     */
    @GetMapping("/hscns")
    @Timed
    public ResponseEntity<List<Hscn>> getAllHscns(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Hscns");
        Page<Hscn> page = hscnService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/hscns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /hscns/:id : get the "id" hscn.
     *
     * @param id the id of the hscn to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the hscn, or with status 404 (Not Found)
     */
    @GetMapping("/hscns/{id}")
    @Timed
    public ResponseEntity<Hscn> getHscn(@PathVariable Long id) {
        log.debug("REST request to get Hscn : {}", id);
        Hscn hscn = hscnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(hscn));
    }

    /**
     * DELETE  /hscns/:id : delete the "id" hscn.
     *
     * @param id the id of the hscn to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/hscns/{id}")
    @Timed
    public ResponseEntity<Void> deleteHscn(@PathVariable Long id) {
        log.debug("REST request to delete Hscn : {}", id);
        hscnService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/hscns?query=:query : search for the hscn corresponding
     * to the query.
     *
     * @param query the query of the hscn search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/hscns")
    @Timed
    public ResponseEntity<List<Hscn>> searchHscns(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Hscns for query {}", query);
        Page<Hscn> page = hscnService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/hscns");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
