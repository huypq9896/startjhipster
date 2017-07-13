package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.CtHuong;
import com.mycompany.myapp.service.CtHuongService;
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
 * REST controller for managing CtHuong.
 */
@RestController
@RequestMapping("/api")
public class CtHuongResource {

    private final Logger log = LoggerFactory.getLogger(CtHuongResource.class);

    private static final String ENTITY_NAME = "ctHuong";

    private final CtHuongService ctHuongService;

    public CtHuongResource(CtHuongService ctHuongService) {
        this.ctHuongService = ctHuongService;
    }

    /**
     * POST  /ct-huongs : Create a new ctHuong.
     *
     * @param ctHuong the ctHuong to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ctHuong, or with status 400 (Bad Request) if the ctHuong has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ct-huongs")
    @Timed
    public ResponseEntity<CtHuong> createCtHuong(@RequestBody CtHuong ctHuong) throws URISyntaxException {
        log.debug("REST request to save CtHuong : {}", ctHuong);
        if (ctHuong.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new ctHuong cannot already have an ID")).body(null);
        }
        CtHuong result = ctHuongService.save(ctHuong);
        return ResponseEntity.created(new URI("/api/ct-huongs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ct-huongs : Updates an existing ctHuong.
     *
     * @param ctHuong the ctHuong to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ctHuong,
     * or with status 400 (Bad Request) if the ctHuong is not valid,
     * or with status 500 (Internal Server Error) if the ctHuong couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ct-huongs")
    @Timed
    public ResponseEntity<CtHuong> updateCtHuong(@RequestBody CtHuong ctHuong) throws URISyntaxException {
        log.debug("REST request to update CtHuong : {}", ctHuong);
        if (ctHuong.getId() == null) {
            return createCtHuong(ctHuong);
        }
        CtHuong result = ctHuongService.save(ctHuong);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ctHuong.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ct-huongs : get all the ctHuongs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ctHuongs in body
     */
    @GetMapping("/ct-huongs")
    @Timed
    public ResponseEntity<List<CtHuong>> getAllCtHuongs(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of CtHuongs");
        Page<CtHuong> page = ctHuongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ct-huongs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /ct-huongs/:id : get the "id" ctHuong.
     *
     * @param id the id of the ctHuong to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ctHuong, or with status 404 (Not Found)
     */
    @GetMapping("/ct-huongs/{id}")
    @Timed
    public ResponseEntity<CtHuong> getCtHuong(@PathVariable Long id) {
        log.debug("REST request to get CtHuong : {}", id);
        CtHuong ctHuong = ctHuongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(ctHuong));
    }

    /**
     * DELETE  /ct-huongs/:id : delete the "id" ctHuong.
     *
     * @param id the id of the ctHuong to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ct-huongs/{id}")
    @Timed
    public ResponseEntity<Void> deleteCtHuong(@PathVariable Long id) {
        log.debug("REST request to delete CtHuong : {}", id);
        ctHuongService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/ct-huongs?query=:query : search for the ctHuong corresponding
     * to the query.
     *
     * @param query the query of the ctHuong search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/ct-huongs")
    @Timed
    public ResponseEntity<List<CtHuong>> searchCtHuongs(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of CtHuongs for query {}", query);
        Page<CtHuong> page = ctHuongService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/ct-huongs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
