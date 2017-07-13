package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.Luong;
import com.mycompany.myapp.service.LuongService;
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
 * REST controller for managing Luong.
 */
@RestController
@RequestMapping("/api")
public class LuongResource {

    private final Logger log = LoggerFactory.getLogger(LuongResource.class);

    private static final String ENTITY_NAME = "luong";

    private final LuongService luongService;

    public LuongResource(LuongService luongService) {
        this.luongService = luongService;
    }

    /**
     * POST  /luongs : Create a new luong.
     *
     * @param luong the luong to create
     * @return the ResponseEntity with status 201 (Created) and with body the new luong, or with status 400 (Bad Request) if the luong has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/luongs")
    @Timed
    public ResponseEntity<Luong> createLuong(@RequestBody Luong luong) throws URISyntaxException {
        log.debug("REST request to save Luong : {}", luong);
        if (luong.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new luong cannot already have an ID")).body(null);
        }
        Luong result = luongService.save(luong);
        return ResponseEntity.created(new URI("/api/luongs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /luongs : Updates an existing luong.
     *
     * @param luong the luong to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated luong,
     * or with status 400 (Bad Request) if the luong is not valid,
     * or with status 500 (Internal Server Error) if the luong couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/luongs")
    @Timed
    public ResponseEntity<Luong> updateLuong(@RequestBody Luong luong) throws URISyntaxException {
        log.debug("REST request to update Luong : {}", luong);
        if (luong.getId() == null) {
            return createLuong(luong);
        }
        Luong result = luongService.save(luong);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, luong.getId().toString()))
            .body(result);
    }

    /**
     * GET  /luongs : get all the luongs.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of luongs in body
     */
    @GetMapping("/luongs")
    @Timed
    public ResponseEntity<List<Luong>> getAllLuongs(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Luongs");
        Page<Luong> page = luongService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/luongs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /luongs/:id : get the "id" luong.
     *
     * @param id the id of the luong to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the luong, or with status 404 (Not Found)
     */
    @GetMapping("/luongs/{id}")
    @Timed
    public ResponseEntity<Luong> getLuong(@PathVariable Long id) {
        log.debug("REST request to get Luong : {}", id);
        Luong luong = luongService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(luong));
    }

    /**
     * DELETE  /luongs/:id : delete the "id" luong.
     *
     * @param id the id of the luong to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/luongs/{id}")
    @Timed
    public ResponseEntity<Void> deleteLuong(@PathVariable Long id) {
        log.debug("REST request to delete Luong : {}", id);
        luongService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/luongs?query=:query : search for the luong corresponding
     * to the query.
     *
     * @param query the query of the luong search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/luongs")
    @Timed
    public ResponseEntity<List<Luong>> searchLuongs(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Luongs for query {}", query);
        Page<Luong> page = luongService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/luongs");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
