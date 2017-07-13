package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.TuyenDt;
import com.mycompany.myapp.service.TuyenDtService;
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
 * REST controller for managing TuyenDt.
 */
@RestController
@RequestMapping("/api")
public class TuyenDtResource {

    private final Logger log = LoggerFactory.getLogger(TuyenDtResource.class);

    private static final String ENTITY_NAME = "tuyenDt";

    private final TuyenDtService tuyenDtService;

    public TuyenDtResource(TuyenDtService tuyenDtService) {
        this.tuyenDtService = tuyenDtService;
    }

    /**
     * POST  /tuyen-dts : Create a new tuyenDt.
     *
     * @param tuyenDt the tuyenDt to create
     * @return the ResponseEntity with status 201 (Created) and with body the new tuyenDt, or with status 400 (Bad Request) if the tuyenDt has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/tuyen-dts")
    @Timed
    public ResponseEntity<TuyenDt> createTuyenDt(@RequestBody TuyenDt tuyenDt) throws URISyntaxException {
        log.debug("REST request to save TuyenDt : {}", tuyenDt);
        if (tuyenDt.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new tuyenDt cannot already have an ID")).body(null);
        }
        TuyenDt result = tuyenDtService.save(tuyenDt);
        return ResponseEntity.created(new URI("/api/tuyen-dts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /tuyen-dts : Updates an existing tuyenDt.
     *
     * @param tuyenDt the tuyenDt to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated tuyenDt,
     * or with status 400 (Bad Request) if the tuyenDt is not valid,
     * or with status 500 (Internal Server Error) if the tuyenDt couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/tuyen-dts")
    @Timed
    public ResponseEntity<TuyenDt> updateTuyenDt(@RequestBody TuyenDt tuyenDt) throws URISyntaxException {
        log.debug("REST request to update TuyenDt : {}", tuyenDt);
        if (tuyenDt.getId() == null) {
            return createTuyenDt(tuyenDt);
        }
        TuyenDt result = tuyenDtService.save(tuyenDt);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, tuyenDt.getId().toString()))
            .body(result);
    }

    /**
     * GET  /tuyen-dts : get all the tuyenDts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of tuyenDts in body
     */
    @GetMapping("/tuyen-dts")
    @Timed
    public ResponseEntity<List<TuyenDt>> getAllTuyenDts(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of TuyenDts");
        Page<TuyenDt> page = tuyenDtService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/tuyen-dts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /tuyen-dts/:id : get the "id" tuyenDt.
     *
     * @param id the id of the tuyenDt to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the tuyenDt, or with status 404 (Not Found)
     */
    @GetMapping("/tuyen-dts/{id}")
    @Timed
    public ResponseEntity<TuyenDt> getTuyenDt(@PathVariable Long id) {
        log.debug("REST request to get TuyenDt : {}", id);
        TuyenDt tuyenDt = tuyenDtService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(tuyenDt));
    }

    /**
     * DELETE  /tuyen-dts/:id : delete the "id" tuyenDt.
     *
     * @param id the id of the tuyenDt to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/tuyen-dts/{id}")
    @Timed
    public ResponseEntity<Void> deleteTuyenDt(@PathVariable Long id) {
        log.debug("REST request to delete TuyenDt : {}", id);
        tuyenDtService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/tuyen-dts?query=:query : search for the tuyenDt corresponding
     * to the query.
     *
     * @param query the query of the tuyenDt search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/tuyen-dts")
    @Timed
    public ResponseEntity<List<TuyenDt>> searchTuyenDts(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of TuyenDts for query {}", query);
        Page<TuyenDt> page = tuyenDtService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/tuyen-dts");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
