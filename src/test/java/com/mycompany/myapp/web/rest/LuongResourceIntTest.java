package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppsmysqlApp;

import com.mycompany.myapp.domain.Luong;
import com.mycompany.myapp.repository.LuongRepository;
import com.mycompany.myapp.service.LuongService;
import com.mycompany.myapp.repository.search.LuongSearchRepository;
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
 * Test class for the LuongResource REST controller.
 *
 * @see LuongResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppsmysqlApp.class)
public class LuongResourceIntTest {

    private static final String DEFAULT_MUC_LUONG = "AAAAAAAAAA";
    private static final String UPDATED_MUC_LUONG = "BBBBBBBBBB";

    private static final Integer DEFAULT_THANG = 1;
    private static final Integer UPDATED_THANG = 2;

    @Autowired
    private LuongRepository luongRepository;

    @Autowired
    private LuongService luongService;

    @Autowired
    private LuongSearchRepository luongSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restLuongMockMvc;

    private Luong luong;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        LuongResource luongResource = new LuongResource(luongService);
        this.restLuongMockMvc = MockMvcBuilders.standaloneSetup(luongResource)
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
    public static Luong createEntity(EntityManager em) {
        Luong luong = new Luong()
            .mucLuong(DEFAULT_MUC_LUONG)
            .thang(DEFAULT_THANG);
        return luong;
    }

    @Before
    public void initTest() {
        luongSearchRepository.deleteAll();
        luong = createEntity(em);
    }

    @Test
    @Transactional
    public void createLuong() throws Exception {
        int databaseSizeBeforeCreate = luongRepository.findAll().size();

        // Create the Luong
        restLuongMockMvc.perform(post("/api/luongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(luong)))
            .andExpect(status().isCreated());

        // Validate the Luong in the database
        List<Luong> luongList = luongRepository.findAll();
        assertThat(luongList).hasSize(databaseSizeBeforeCreate + 1);
        Luong testLuong = luongList.get(luongList.size() - 1);
        assertThat(testLuong.getMucLuong()).isEqualTo(DEFAULT_MUC_LUONG);
        assertThat(testLuong.getThang()).isEqualTo(DEFAULT_THANG);

        // Validate the Luong in Elasticsearch
        Luong luongEs = luongSearchRepository.findOne(testLuong.getId());
        assertThat(luongEs).isEqualToComparingFieldByField(testLuong);
    }

    @Test
    @Transactional
    public void createLuongWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = luongRepository.findAll().size();

        // Create the Luong with an existing ID
        luong.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLuongMockMvc.perform(post("/api/luongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(luong)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Luong> luongList = luongRepository.findAll();
        assertThat(luongList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllLuongs() throws Exception {
        // Initialize the database
        luongRepository.saveAndFlush(luong);

        // Get all the luongList
        restLuongMockMvc.perform(get("/api/luongs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(luong.getId().intValue())))
            .andExpect(jsonPath("$.[*].mucLuong").value(hasItem(DEFAULT_MUC_LUONG.toString())))
            .andExpect(jsonPath("$.[*].thang").value(hasItem(DEFAULT_THANG)));
    }

    @Test
    @Transactional
    public void getLuong() throws Exception {
        // Initialize the database
        luongRepository.saveAndFlush(luong);

        // Get the luong
        restLuongMockMvc.perform(get("/api/luongs/{id}", luong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(luong.getId().intValue()))
            .andExpect(jsonPath("$.mucLuong").value(DEFAULT_MUC_LUONG.toString()))
            .andExpect(jsonPath("$.thang").value(DEFAULT_THANG));
    }

    @Test
    @Transactional
    public void getNonExistingLuong() throws Exception {
        // Get the luong
        restLuongMockMvc.perform(get("/api/luongs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLuong() throws Exception {
        // Initialize the database
        luongService.save(luong);

        int databaseSizeBeforeUpdate = luongRepository.findAll().size();

        // Update the luong
        Luong updatedLuong = luongRepository.findOne(luong.getId());
        updatedLuong
            .mucLuong(UPDATED_MUC_LUONG)
            .thang(UPDATED_THANG);

        restLuongMockMvc.perform(put("/api/luongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLuong)))
            .andExpect(status().isOk());

        // Validate the Luong in the database
        List<Luong> luongList = luongRepository.findAll();
        assertThat(luongList).hasSize(databaseSizeBeforeUpdate);
        Luong testLuong = luongList.get(luongList.size() - 1);
        assertThat(testLuong.getMucLuong()).isEqualTo(UPDATED_MUC_LUONG);
        assertThat(testLuong.getThang()).isEqualTo(UPDATED_THANG);

        // Validate the Luong in Elasticsearch
        Luong luongEs = luongSearchRepository.findOne(testLuong.getId());
        assertThat(luongEs).isEqualToComparingFieldByField(testLuong);
    }

    @Test
    @Transactional
    public void updateNonExistingLuong() throws Exception {
        int databaseSizeBeforeUpdate = luongRepository.findAll().size();

        // Create the Luong

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restLuongMockMvc.perform(put("/api/luongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(luong)))
            .andExpect(status().isCreated());

        // Validate the Luong in the database
        List<Luong> luongList = luongRepository.findAll();
        assertThat(luongList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteLuong() throws Exception {
        // Initialize the database
        luongService.save(luong);

        int databaseSizeBeforeDelete = luongRepository.findAll().size();

        // Get the luong
        restLuongMockMvc.perform(delete("/api/luongs/{id}", luong.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean luongExistsInEs = luongSearchRepository.exists(luong.getId());
        assertThat(luongExistsInEs).isFalse();

        // Validate the database is empty
        List<Luong> luongList = luongRepository.findAll();
        assertThat(luongList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchLuong() throws Exception {
        // Initialize the database
        luongService.save(luong);

        // Search the luong
        restLuongMockMvc.perform(get("/api/_search/luongs?query=id:" + luong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(luong.getId().intValue())))
            .andExpect(jsonPath("$.[*].mucLuong").value(hasItem(DEFAULT_MUC_LUONG.toString())))
            .andExpect(jsonPath("$.[*].thang").value(hasItem(DEFAULT_THANG)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Luong.class);
        Luong luong1 = new Luong();
        luong1.setId(1L);
        Luong luong2 = new Luong();
        luong2.setId(luong1.getId());
        assertThat(luong1).isEqualTo(luong2);
        luong2.setId(2L);
        assertThat(luong1).isNotEqualTo(luong2);
        luong1.setId(null);
        assertThat(luong1).isNotEqualTo(luong2);
    }
}
