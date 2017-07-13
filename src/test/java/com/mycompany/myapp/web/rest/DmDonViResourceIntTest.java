package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppsmysqlApp;

import com.mycompany.myapp.domain.DmDonVi;
import com.mycompany.myapp.repository.DmDonViRepository;
import com.mycompany.myapp.repository.search.DmDonViSearchRepository;
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
 * Test class for the DmDonViResource REST controller.
 *
 * @see DmDonViResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppsmysqlApp.class)
public class DmDonViResourceIntTest {

    private static final String DEFAULT_MA = "AAAAAAAAAA";
    private static final String UPDATED_MA = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    @Autowired
    private DmDonViRepository dmDonViRepository;

    @Autowired
    private DmDonViSearchRepository dmDonViSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDmDonViMockMvc;

    private DmDonVi dmDonVi;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DmDonViResource dmDonViResource = new DmDonViResource(dmDonViRepository, dmDonViSearchRepository);
        this.restDmDonViMockMvc = MockMvcBuilders.standaloneSetup(dmDonViResource)
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
    public static DmDonVi createEntity(EntityManager em) {
        DmDonVi dmDonVi = new DmDonVi()
            .ma(DEFAULT_MA)
            .name(DEFAULT_NAME)
            .diaChi(DEFAULT_DIA_CHI)
            .ghiChu(DEFAULT_GHI_CHU);
        return dmDonVi;
    }

    @Before
    public void initTest() {
        dmDonViSearchRepository.deleteAll();
        dmDonVi = createEntity(em);
    }

    @Test
    @Transactional
    public void createDmDonVi() throws Exception {
        int databaseSizeBeforeCreate = dmDonViRepository.findAll().size();

        // Create the DmDonVi
        restDmDonViMockMvc.perform(post("/api/dm-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmDonVi)))
            .andExpect(status().isCreated());

        // Validate the DmDonVi in the database
        List<DmDonVi> dmDonViList = dmDonViRepository.findAll();
        assertThat(dmDonViList).hasSize(databaseSizeBeforeCreate + 1);
        DmDonVi testDmDonVi = dmDonViList.get(dmDonViList.size() - 1);
        assertThat(testDmDonVi.getMa()).isEqualTo(DEFAULT_MA);
        assertThat(testDmDonVi.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDmDonVi.getDiaChi()).isEqualTo(DEFAULT_DIA_CHI);
        assertThat(testDmDonVi.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);

        // Validate the DmDonVi in Elasticsearch
        DmDonVi dmDonViEs = dmDonViSearchRepository.findOne(testDmDonVi.getId());
        assertThat(dmDonViEs).isEqualToComparingFieldByField(testDmDonVi);
    }

    @Test
    @Transactional
    public void createDmDonViWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dmDonViRepository.findAll().size();

        // Create the DmDonVi with an existing ID
        dmDonVi.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDmDonViMockMvc.perform(post("/api/dm-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmDonVi)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DmDonVi> dmDonViList = dmDonViRepository.findAll();
        assertThat(dmDonViList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkMaIsRequired() throws Exception {
        int databaseSizeBeforeTest = dmDonViRepository.findAll().size();
        // set the field null
        dmDonVi.setMa(null);

        // Create the DmDonVi, which fails.

        restDmDonViMockMvc.perform(post("/api/dm-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmDonVi)))
            .andExpect(status().isBadRequest());

        List<DmDonVi> dmDonViList = dmDonViRepository.findAll();
        assertThat(dmDonViList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDmDonVis() throws Exception {
        // Initialize the database
        dmDonViRepository.saveAndFlush(dmDonVi);

        // Get all the dmDonViList
        restDmDonViMockMvc.perform(get("/api/dm-don-vis?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmDonVi.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())));
    }

    @Test
    @Transactional
    public void getDmDonVi() throws Exception {
        // Initialize the database
        dmDonViRepository.saveAndFlush(dmDonVi);

        // Get the dmDonVi
        restDmDonViMockMvc.perform(get("/api/dm-don-vis/{id}", dmDonVi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dmDonVi.getId().intValue()))
            .andExpect(jsonPath("$.ma").value(DEFAULT_MA.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.diaChi").value(DEFAULT_DIA_CHI.toString()))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDmDonVi() throws Exception {
        // Get the dmDonVi
        restDmDonViMockMvc.perform(get("/api/dm-don-vis/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDmDonVi() throws Exception {
        // Initialize the database
        dmDonViRepository.saveAndFlush(dmDonVi);
        dmDonViSearchRepository.save(dmDonVi);
        int databaseSizeBeforeUpdate = dmDonViRepository.findAll().size();

        // Update the dmDonVi
        DmDonVi updatedDmDonVi = dmDonViRepository.findOne(dmDonVi.getId());
        updatedDmDonVi
            .ma(UPDATED_MA)
            .name(UPDATED_NAME)
            .diaChi(UPDATED_DIA_CHI)
            .ghiChu(UPDATED_GHI_CHU);

        restDmDonViMockMvc.perform(put("/api/dm-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDmDonVi)))
            .andExpect(status().isOk());

        // Validate the DmDonVi in the database
        List<DmDonVi> dmDonViList = dmDonViRepository.findAll();
        assertThat(dmDonViList).hasSize(databaseSizeBeforeUpdate);
        DmDonVi testDmDonVi = dmDonViList.get(dmDonViList.size() - 1);
        assertThat(testDmDonVi.getMa()).isEqualTo(UPDATED_MA);
        assertThat(testDmDonVi.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDmDonVi.getDiaChi()).isEqualTo(UPDATED_DIA_CHI);
        assertThat(testDmDonVi.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);

        // Validate the DmDonVi in Elasticsearch
        DmDonVi dmDonViEs = dmDonViSearchRepository.findOne(testDmDonVi.getId());
        assertThat(dmDonViEs).isEqualToComparingFieldByField(testDmDonVi);
    }

    @Test
    @Transactional
    public void updateNonExistingDmDonVi() throws Exception {
        int databaseSizeBeforeUpdate = dmDonViRepository.findAll().size();

        // Create the DmDonVi

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDmDonViMockMvc.perform(put("/api/dm-don-vis")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dmDonVi)))
            .andExpect(status().isCreated());

        // Validate the DmDonVi in the database
        List<DmDonVi> dmDonViList = dmDonViRepository.findAll();
        assertThat(dmDonViList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDmDonVi() throws Exception {
        // Initialize the database
        dmDonViRepository.saveAndFlush(dmDonVi);
        dmDonViSearchRepository.save(dmDonVi);
        int databaseSizeBeforeDelete = dmDonViRepository.findAll().size();

        // Get the dmDonVi
        restDmDonViMockMvc.perform(delete("/api/dm-don-vis/{id}", dmDonVi.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean dmDonViExistsInEs = dmDonViSearchRepository.exists(dmDonVi.getId());
        assertThat(dmDonViExistsInEs).isFalse();

        // Validate the database is empty
        List<DmDonVi> dmDonViList = dmDonViRepository.findAll();
        assertThat(dmDonViList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchDmDonVi() throws Exception {
        // Initialize the database
        dmDonViRepository.saveAndFlush(dmDonVi);
        dmDonViSearchRepository.save(dmDonVi);

        // Search the dmDonVi
        restDmDonViMockMvc.perform(get("/api/_search/dm-don-vis?query=id:" + dmDonVi.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dmDonVi.getId().intValue())))
            .andExpect(jsonPath("$.[*].ma").value(hasItem(DEFAULT_MA.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].diaChi").value(hasItem(DEFAULT_DIA_CHI.toString())))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DmDonVi.class);
        DmDonVi dmDonVi1 = new DmDonVi();
        dmDonVi1.setId(1L);
        DmDonVi dmDonVi2 = new DmDonVi();
        dmDonVi2.setId(dmDonVi1.getId());
        assertThat(dmDonVi1).isEqualTo(dmDonVi2);
        dmDonVi2.setId(2L);
        assertThat(dmDonVi1).isNotEqualTo(dmDonVi2);
        dmDonVi1.setId(null);
        assertThat(dmDonVi1).isNotEqualTo(dmDonVi2);
    }
}
