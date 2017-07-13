package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.DotXh;
import com.mycompany.myapp.service.DotXhService;
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

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing DotXh.
 */
@RestController
@RequestMapping("/api")
public class DotXhResource {

    private final Logger log = LoggerFactory.getLogger(DotXhResource.class);

    private static final String ENTITY_NAME = "dotXh";

    private final DotXhService dotXhService;

    public DotXhResource(DotXhService dotXhService) {
        this.dotXhService = dotXhService;
    }

    /**
     * POST  /dot-xhs : Create a new dotXh.
     *
     * @param dotXh the dotXh to create
     * @return the ResponseEntity with status 201 (Created) and with body the new dotXh, or with status 400 (Bad Request) if the dotXh has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/dot-xhs")
    @Timed
    public ResponseEntity<DotXh> createDotXh(@RequestBody DotXh dotXh) throws URISyntaxException {
        log.debug("REST request to save DotXh : {}", dotXh);
        if (dotXh.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new dotXh cannot already have an ID")).body(null);
        }
        DotXh result = dotXhService.save(dotXh);
        return ResponseEntity.created(new URI("/api/dot-xhs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /dot-xhs : Updates an existing dotXh.
     *
     * @param dotXh the dotXh to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated dotXh,
     * or with status 400 (Bad Request) if the dotXh is not valid,
     * or with status 500 (Internal Server Error) if the dotXh couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/dot-xhs")
    @Timed
    public ResponseEntity<DotXh> updateDotXh(@RequestBody DotXh dotXh) throws URISyntaxException {
        log.debug("REST request to update DotXh : {}", dotXh);
        if (dotXh.getId() == null) {
            return createDotXh(dotXh);
        }
        DotXh result = dotXhService.save(dotXh);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dotXh.getId().toString()))
            .body(result);
    }

    /**
     * GET  /dot-xhs : get all the dotXhs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of dotXhs in body
     */
    @GetMapping("/dot-xhs")
    @Timed
    public ResponseEntity<List<DotXh>> getAllDotXhs(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of DotXhs");
        Page<DotXh> page = dotXhService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/dot-xhs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /dot-xhs/:id : get the "id" dotXh.
     *
     * @param id the id of the dotXh to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the dotXh, or with status 404 (Not Found)
     */
    @GetMapping("/dot-xhs/{id}")
    @Timed
    public ResponseEntity<DotXh> getDotXh(@PathVariable Long id) {
        log.debug("REST request to get DotXh : {}", id);
        DotXh dotXh = dotXhService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dotXh));
    }

    /**
     * DELETE  /dot-xhs/:id : delete the "id" dotXh.
     *
     * @param id the id of the dotXh to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/dot-xhs/{id}")
    @Timed
    public ResponseEntity<Void> deleteDotXh(@PathVariable Long id) {
        log.debug("REST request to delete DotXh : {}", id);
        dotXhService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/dot-xhs?query=:query : search for the dotXh corresponding
     * to the query.
     *
     * @param query the query of the dotXh search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/dot-xhs")
    @Timed
    public ResponseEntity<List<DotXh>> searchDotXhs(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of DotXhs for query {}", query);
        Page<DotXh> page = dotXhService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/dot-xhs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
