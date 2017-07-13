package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppsmysqlApp;

import com.mycompany.myapp.domain.Chedo;
import com.mycompany.myapp.repository.ChedoRepository;
import com.mycompany.myapp.repository.search.ChedoSearchRepository;
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
 * Test class for the ChedoResource REST controller.
 *
 * @see ChedoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppsmysqlApp.class)
public class ChedoResourceIntTest {

    private static final String DEFAULT_MA_CD = "AAAAAAAAAA";
    private static final String UPDATED_MA_CD = "BBBBBBBBBB";

    private static final String DEFAULT_TEN_CD = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CD = "BBBBBBBBBB";

    @Autowired
    private ChedoRepository chedoRepository;

    @Autowired
    private ChedoSearchRepository chedoSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restChedoMockMvc;

    private Chedo chedo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ChedoResource chedoResource = new ChedoResource(chedoRepository, chedoSearchRepository);
        this.restChedoMockMvc = MockMvcBuilders.standaloneSetup(chedoResource)
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
    public static Chedo createEntity(EntityManager em) {
        Chedo chedo = new Chedo()
            .maCD(DEFAULT_MA_CD)
            .tenCD(DEFAULT_TEN_CD);
        return chedo;
    }

    @Before
    public void initTest() {
        chedoSearchRepository.deleteAll();
        chedo = createEntity(em);
    }

    @Test
    @Transactional
    public void createChedo() throws Exception {
        int databaseSizeBeforeCreate = chedoRepository.findAll().size();

        // Create the Chedo
        restChedoMockMvc.perform(post("/api/chedos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chedo)))
            .andExpect(status().isCreated());

        // Validate the Chedo in the database
        List<Chedo> chedoList = chedoRepository.findAll();
        assertThat(chedoList).hasSize(databaseSizeBeforeCreate + 1);
        Chedo testChedo = chedoList.get(chedoList.size() - 1);
        assertThat(testChedo.getMaCD()).isEqualTo(DEFAULT_MA_CD);
        assertThat(testChedo.getTenCD()).isEqualTo(DEFAULT_TEN_CD);

        // Validate the Chedo in Elasticsearch
        Chedo chedoEs = chedoSearchRepository.findOne(testChedo.getId());
        assertThat(chedoEs).isEqualToComparingFieldByField(testChedo);
    }

    @Test
    @Transactional
    public void createChedoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chedoRepository.findAll().size();

        // Create the Chedo with an existing ID
        chedo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChedoMockMvc.perform(post("/api/chedos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chedo)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Chedo> chedoList = chedoRepository.findAll();
        assertThat(chedoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllChedos() throws Exception {
        // Initialize the database
        chedoRepository.saveAndFlush(chedo);

        // Get all the chedoList
        restChedoMockMvc.perform(get("/api/chedos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chedo.getId().intValue())))
            .andExpect(jsonPath("$.[*].maCD").value(hasItem(DEFAULT_MA_CD.toString())))
            .andExpect(jsonPath("$.[*].tenCD").value(hasItem(DEFAULT_TEN_CD.toString())));
    }

    @Test
    @Transactional
    public void getChedo() throws Exception {
        // Initialize the database
        chedoRepository.saveAndFlush(chedo);

        // Get the chedo
        restChedoMockMvc.perform(get("/api/chedos/{id}", chedo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chedo.getId().intValue()))
            .andExpect(jsonPath("$.maCD").value(DEFAULT_MA_CD.toString()))
            .andExpect(jsonPath("$.tenCD").value(DEFAULT_TEN_CD.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingChedo() throws Exception {
        // Get the chedo
        restChedoMockMvc.perform(get("/api/chedos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChedo() throws Exception {
        // Initialize the database
        chedoRepository.saveAndFlush(chedo);
        chedoSearchRepository.save(chedo);
        int databaseSizeBeforeUpdate = chedoRepository.findAll().size();

        // Update the chedo
        Chedo updatedChedo = chedoRepository.findOne(chedo.getId());
        updatedChedo
            .maCD(UPDATED_MA_CD)
            .tenCD(UPDATED_TEN_CD);

        restChedoMockMvc.perform(put("/api/chedos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedChedo)))
            .andExpect(status().isOk());

        // Validate the Chedo in the database
        List<Chedo> chedoList = chedoRepository.findAll();
        assertThat(chedoList).hasSize(databaseSizeBeforeUpdate);
        Chedo testChedo = chedoList.get(chedoList.size() - 1);
        assertThat(testChedo.getMaCD()).isEqualTo(UPDATED_MA_CD);
        assertThat(testChedo.getTenCD()).isEqualTo(UPDATED_TEN_CD);

        // Validate the Chedo in Elasticsearch
        Chedo chedoEs = chedoSearchRepository.findOne(testChedo.getId());
        assertThat(chedoEs).isEqualToComparingFieldByField(testChedo);
    }

    @Test
    @Transactional
    public void updateNonExistingChedo() throws Exception {
        int databaseSizeBeforeUpdate = chedoRepository.findAll().size();

        // Create the Chedo

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restChedoMockMvc.perform(put("/api/chedos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chedo)))
            .andExpect(status().isCreated());

        // Validate the Chedo in the database
        List<Chedo> chedoList = chedoRepository.findAll();
        assertThat(chedoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteChedo() throws Exception {
        // Initialize the database
        chedoRepository.saveAndFlush(chedo);
        chedoSearchRepository.save(chedo);
        int databaseSizeBeforeDelete = chedoRepository.findAll().size();

        // Get the chedo
        restChedoMockMvc.perform(delete("/api/chedos/{id}", chedo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean chedoExistsInEs = chedoSearchRepository.exists(chedo.getId());
        assertThat(chedoExistsInEs).isFalse();

        // Validate the database is empty
        List<Chedo> chedoList = chedoRepository.findAll();
        assertThat(chedoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchChedo() throws Exception {
        // Initialize the database
        chedoRepository.saveAndFlush(chedo);
        chedoSearchRepository.save(chedo);

        // Search the chedo
        restChedoMockMvc.perform(get("/api/_search/chedos?query=id:" + chedo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chedo.getId().intValue())))
            .andExpect(jsonPath("$.[*].maCD").value(hasItem(DEFAULT_MA_CD.toString())))
            .andExpect(jsonPath("$.[*].tenCD").value(hasItem(DEFAULT_TEN_CD.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Chedo.class);
        Chedo chedo1 = new Chedo();
        chedo1.setId(1L);
        Chedo chedo2 = new Chedo();
        chedo2.setId(chedo1.getId());
        assertThat(chedo1).isEqualTo(chedo2);
        chedo2.setId(2L);
        assertThat(chedo1).isNotEqualTo(chedo2);
        chedo1.setId(null);
        assertThat(chedo1).isNotEqualTo(chedo2);
    }
}
