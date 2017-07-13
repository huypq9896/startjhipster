package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppsmysqlApp;

import com.mycompany.myapp.domain.TuyenDt;
import com.mycompany.myapp.repository.TuyenDtRepository;
import com.mycompany.myapp.service.TuyenDtService;
import com.mycompany.myapp.repository.search.TuyenDtSearchRepository;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TuyenDtResource REST controller.
 *
 * @see TuyenDtResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppsmysqlApp.class)
public class TuyenDtResourceIntTest {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_TEN = "AAAAAAAAAA";
    private static final String UPDATED_TEN = "BBBBBBBBBB";

    private static final Integer DEFAULT_SO_NGAY = 1;
    private static final Integer UPDATED_SO_NGAY = 2;

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;

    private static final Long DEFAULT_HSCN_ID = 1L;
    private static final Long UPDATED_HSCN_ID = 2L;

    @Autowired
    private TuyenDtRepository tuyenDtRepository;

    @Autowired
    private TuyenDtService tuyenDtService;

    @Autowired
    private TuyenDtSearchRepository tuyenDtSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTuyenDtMockMvc;

    private TuyenDt tuyenDt;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        TuyenDtResource tuyenDtResource = new TuyenDtResource(tuyenDtService);
        this.restTuyenDtMockMvc = MockMvcBuilders.standaloneSetup(tuyenDtResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TuyenDt createEntity(EntityManager em) {
        TuyenDt tuyenDt = new TuyenDt()
            .ma(DEFAULT_MA)
            .ten(DEFAULT_TEN)
            .soNgay(DEFAULT_SO_NGAY)
            .active(DEFAULT_ACTIVE)
            .hscnId(DEFAULT_HSCN_ID);
        return tuyenDt;
    }

    @Before
    public void initTest() {
        tuyenDtSearchRepository.deleteAll();
        tuyenDt = createEntity(em);
    }

    @Test
    @Transactional
    public void createTuyenDt() throws Exception {
        int databaseSizeBeforeCreate = tuyenDtRepository.findAll().size();

        // Create the TuyenDt
        restTuyenDtMockMvc.perform(post("/api/tuyen-dts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuyenDt)))
            .andExpect(status().isCreated());

        // Validate the TuyenDt in the database
        List<TuyenDt> tuyenDtList = tuyenDtRepository.findAll();
        assertThat(tuyenDtList).hasSize(databaseSizeBeforeCreate + 1);
        TuyenDt testTuyenDt = tuyenDtList.get(tuyenDtList.size() - 1);
        assertThat(testTuyenDt.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testTuyenDt.getTen()).isEqualTo(DEFAULT_TEN);
        assertThat(testTuyenDt.getSoNgay()).isEqualTo(DEFAULT_SO_NGAY);
        assertThat(testTuyenDt.isActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testTuyenDt.getHscnId()).isEqualTo(DEFAULT_HSCN_ID);

        // Validate the TuyenDt in Elasticsearch
        TuyenDt tuyenDtEs = tuyenDtSearchRepository.findOne(testTuyenDt.getId());
        assertThat(tuyenDtEs).isEqualToComparingFieldByField(testTuyenDt);
    }

    @Test
    @Transactional
    public void createTuyenDtWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tuyenDtRepository.findAll().size();

        // Create the TuyenDt with an existing ID
        tuyenDt.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTuyenDtMockMvc.perform(post("/api/tuyen-dts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuyenDt)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<TuyenDt> tuyenDtList = tuyenDtRepository.findAll();
        assertThat(tuyenDtList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTuyenDts() throws Exception {
        // Initialize the database
        tuyenDtRepository.saveAndFlush(tuyenDt);

        // Get all the tuyenDtList
        restTuyenDtMockMvc.perform(get("/api/tuyen-dts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tuyenDt.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA.toString())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN.toString())))
            .andExpect(jsonPath("$.[*].soNgay").value(hasItem(DEFAULT_SO_NGAY)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].hscnId").value(hasItem(DEFAULT_HSCN_ID.intValue())));
    }

    @Test
    @Transactional
    public void getTuyenDt() throws Exception {
        // Initialize the database
        tuyenDtRepository.saveAndFlush(tuyenDt);

        // Get the tuyenDt
        restTuyenDtMockMvc.perform(get("/api/tuyen-dts/{id}", tuyenDt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tuyenDt.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA.toString()))
            .andExpect(jsonPath("$.ten").value(DEFAULT_TEN.toString()))
            .andExpect(jsonPath("$.soNgay").value(DEFAULT_SO_NGAY))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.hscnId").value(DEFAULT_HSCN_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingTuyenDt() throws Exception {
        // Get the tuyenDt
        restTuyenDtMockMvc.perform(get("/api/tuyen-dts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTuyenDt() throws Exception {
        // Initialize the database
        tuyenDtService.save(tuyenDt);

        int databaseSizeBeforeUpdate = tuyenDtRepository.findAll().size();

        // Update the tuyenDt
        TuyenDt updatedTuyenDt = tuyenDtRepository.findOne(tuyenDt.getId());
        updatedTuyenDt
            .ma(UPDATED_MA)
            .ten(UPDATED_TEN)
            .soNgay(UPDATED_SO_NGAY)
            .active(UPDATED_ACTIVE)
            .hscnId(UPDATED_HSCN_ID);

        restTuyenDtMockMvc.perform(put("/api/tuyen-dts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTuyenDt)))
            .andExpect(status().isOk());

        // Validate the TuyenDt in the database
        List<TuyenDt> tuyenDtList = tuyenDtRepository.findAll();
        assertThat(tuyenDtList).hasSize(databaseSizeBeforeUpdate);
        TuyenDt testTuyenDt = tuyenDtList.get(tuyenDtList.size() - 1);
        assertThat(testTuyenDt.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testTuyenDt.getTen()).isEqualTo(UPDATED_TEN);
        assertThat(testTuyenDt.getSoNgay()).isEqualTo(UPDATED_SO_NGAY);
        assertThat(testTuyenDt.isActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testTuyenDt.getHscnId()).isEqualTo(UPDATED_HSCN_ID);

        // Validate the TuyenDt in Elasticsearch
        TuyenDt tuyenDtEs = tuyenDtSearchRepository.findOne(testTuyenDt.getId());
        assertThat(tuyenDtEs).isEqualToComparingFieldByField(testTuyenDt);
    }

    @Test
    @Transactional
    public void updateNonExistingTuyenDt() throws Exception {
        int databaseSizeBeforeUpdate = tuyenDtRepository.findAll().size();

        // Create the TuyenDt

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTuyenDtMockMvc.perform(put("/api/tuyen-dts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tuyenDt)))
            .andExpect(status().isCreated());

        // Validate the TuyenDt in the database
        List<TuyenDt> tuyenDtList = tuyenDtRepository.findAll();
        assertThat(tuyenDtList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTuyenDt() throws Exception {
        // Initialize the database
        tuyenDtService.save(tuyenDt);

        int databaseSizeBeforeDelete = tuyenDtRepository.findAll().size();

        // Get the tuyenDt
        restTuyenDtMockMvc.perform(delete("/api/tuyen-dts/{id}", tuyenDt.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean tuyenDtExistsInEs = tuyenDtSearchRepository.exists(tuyenDt.getId());
        assertThat(tuyenDtExistsInEs).isFalse();

        // Validate the database is empty
        List<TuyenDt> tuyenDtList = tuyenDtRepository.findAll();
        assertThat(tuyenDtList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchTuyenDt() throws Exception {
        // Initialize the database
        tuyenDtService.save(tuyenDt);

        // Search the tuyenDt
        restTuyenDtMockMvc.perform(get("/api/_search/tuyen-dts?query=id:" + tuyenDt.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tuyenDt.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA.toString())))
            .andExpect(jsonPath("$.[*].ten").value(hasItem(DEFAULT_TEN.toString())))
            .andExpect(jsonPath("$.[*].soNgay").value(hasItem(DEFAULT_SO_NGAY)))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].hscnId").value(hasItem(DEFAULT_HSCN_ID.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TuyenDt.class);
        TuyenDt tuyenDt1 = new TuyenDt();
        tuyenDt1.setId(1L);
        TuyenDt tuyenDt2 = new TuyenDt();
        tuyenDt2.setId(tuyenDt1.getId());
        assertThat(tuyenDt1).isEqualTo(tuyenDt2);
        tuyenDt2.setId(2L);
        assertThat(tuyenDt1).isNotEqualTo(tuyenDt2);
        tuyenDt1.setId(null);
        assertThat(tuyenDt1).isNotEqualTo(tuyenDt2);
    }
}
