package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppsmysqlApp;

import com.mycompany.myapp.domain.Hscn;
import com.mycompany.myapp.repository.HscnRepository;
import com.mycompany.myapp.service.HscnService;
import com.mycompany.myapp.repository.search.HscnSearchRepository;
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
 * Test class for the HscnResource REST controller.
 *
 * @see HscnResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppsmysqlApp.class)
public class HscnResourceIntTest {

    private static final String DEFAULT_SO_BHXH = "AAAAAAAAAA";
    private static final String UPDATED_SO_BHXH = "BBBBBBBBBB";

    private static final String DEFAULT_HO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN = "BBBBBBBBBB";

    private static final String DEFAULT_GIOI_TINH = "AAAAAAAAAA";
    private static final String UPDATED_GIOI_TINH = "BBBBBBBBBB";

    private static final Long DEFAULT_NGAY_SINH = 1L;
    private static final Long UPDATED_NGAY_SINH = 2L;

    private static final String DEFAULT_SO_CMND = "AAAAAAAAAA";
    private static final String UPDATED_SO_CMND = "BBBBBBBBBB";

    private static final String DEFAULT_DIA_CHI_LH = "AAAAAAAAAA";
    private static final String UPDATED_DIA_CHI_LH = "BBBBBBBBBB";

    private static final Long DEFAULT_NGAY_CAP = 1L;
    private static final Long UPDATED_NGAY_CAP = 2L;

    private static final String DEFAULT_NOI_CAP = "AAAAAAAAAA";
    private static final String UPDATED_NOI_CAP = "BBBBBBBBBB";

    private static final String DEFAULT_DAN_TOC = "AAAAAAAAAA";
    private static final String UPDATED_DAN_TOC = "BBBBBBBBBB";

    private static final String DEFAULT_QUOC_TICH = "AAAAAAAAAA";
    private static final String UPDATED_QUOC_TICH = "BBBBBBBBBB";

    private static final String DEFAULT_NOI_KHAI = "AAAAAAAAAA";
    private static final String UPDATED_NOI_KHAI = "BBBBBBBBBB";

    private static final String DEFAULT_DIACHI_HK = "AAAAAAAAAA";
    private static final String UPDATED_DIACHI_HK = "BBBBBBBBBB";

    private static final String DEFAULT_DIEN_THOAI = "AAAAAAAAAA";
    private static final String UPDATED_DIEN_THOAI = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CHUC_VU = "AAAAAAAAAA";
    private static final String UPDATED_CHUC_VU = "BBBBBBBBBB";

    private static final String DEFAULT_MA_PB = "AAAAAAAAAA";
    private static final String UPDATED_MA_PB = "BBBBBBBBBB";

    private static final String DEFAULT_PHONG_BAN = "AAAAAAAAAA";
    private static final String UPDATED_PHONG_BAN = "BBBBBBBBBB";

    private static final String DEFAULT_MA_CV = "AAAAAAAAAA";
    private static final String UPDATED_MA_CV = "BBBBBBBBBB";

    private static final String DEFAULT_MA_NV = "AAAAAAAAAA";
    private static final String UPDATED_MA_NV = "BBBBBBBBBB";

    private static final String DEFAULT_NOICAP_BHXH = "AAAAAAAAAA";
    private static final String UPDATED_NOICAP_BHXH = "BBBBBBBBBB";

    private static final String DEFAULT_MA_DT = "AAAAAAAAAA";
    private static final String UPDATED_MA_DT = "BBBBBBBBBB";

    private static final String DEFAULT_MA_BV = "AAAAAAAAAA";
    private static final String UPDATED_MA_BV = "BBBBBBBBBB";

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    @Autowired
    private HscnRepository hscnRepository;

    @Autowired
    private HscnService hscnService;

    @Autowired
    private HscnSearchRepository hscnSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restHscnMockMvc;

    private Hscn hscn;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HscnResource hscnResource = new HscnResource(hscnService);
        this.restHscnMockMvc = MockMvcBuilders.standaloneSetup(hscnResource)
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
    public static Hscn createEntity(EntityManager em) {
        Hscn hscn = new Hscn()
            .soBhxh(DEFAULT_SO_BHXH)
            .hoTen(DEFAULT_HO_TEN)
            .gioiTinh(DEFAULT_GIOI_TINH)
            .ngaySinh(DEFAULT_NGAY_SINH)
            .soCmnd(DEFAULT_SO_CMND)
            .diaChiLh(DEFAULT_DIA_CHI_LH)
            .ngayCap(DEFAULT_NGAY_CAP)
            .noiCap(DEFAULT_NOI_CAP)
            .dan_toc(DEFAULT_DAN_TOC)
            .quoc_tich(DEFAULT_QUOC_TICH)
            .noi_khai(DEFAULT_NOI_KHAI)
            .diachiHK(DEFAULT_DIACHI_HK)
            .dien_thoai(DEFAULT_DIEN_THOAI)
            .email(DEFAULT_EMAIL)
            .chuc_vu(DEFAULT_CHUC_VU)
            .maPB(DEFAULT_MA_PB)
            .phong_ban(DEFAULT_PHONG_BAN)
            .maCV(DEFAULT_MA_CV)
            .maNV(DEFAULT_MA_NV)
            .noicapBHXH(DEFAULT_NOICAP_BHXH)
            .maDT(DEFAULT_MA_DT)
            .maBV(DEFAULT_MA_BV)
            .ghi_chu(DEFAULT_GHI_CHU);
        return hscn;
    }

    @Before
    public void initTest() {
        hscnSearchRepository.deleteAll();
        hscn = createEntity(em);
    }

    @Test
    @Transactional
    public void createHscn() throws Exception {
        int databaseSizeBeforeCreate = hscnRepository.findAll().size();

        // Create the Hscn
        restHscnMockMvc.perform(post("/api/hscns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hscn)))
            .andExpect(status().isCreated());

        // Validate the Hscn in the database
        List<Hscn> hscnList = hscnRepository.findAll();
        assertThat(hscnList).hasSize(databaseSizeBeforeCreate + 1);
        Hscn testHscn = hscnList.get(hscnList.size() - 1);
        assertThat(testHscn.getSoBhxh()).isEqualTo(DEFAULT_SO_BHXH);
        assertThat(testHscn.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testHscn.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testHscn.getNgaySinh()).isEqualTo(DEFAULT_NGAY_SINH);
        assertThat(testHscn.getSoCmnd()).isEqualTo(DEFAULT_SO_CMND);
        assertThat(testHscn.getDiaChiLh()).isEqualTo(DEFAULT_DIA_CHI_LH);
        assertThat(testHscn.getNgayCap()).isEqualTo(DEFAULT_NGAY_CAP);
        assertThat(testHscn.getNoiCap()).isEqualTo(DEFAULT_NOI_CAP);
        assertThat(testHscn.getDan_toc()).isEqualTo(DEFAULT_DAN_TOC);
        assertThat(testHscn.getQuoc_tich()).isEqualTo(DEFAULT_QUOC_TICH);
        assertThat(testHscn.getNoi_khai()).isEqualTo(DEFAULT_NOI_KHAI);
        assertThat(testHscn.getDiachiHK()).isEqualTo(DEFAULT_DIACHI_HK);
        assertThat(testHscn.getDien_thoai()).isEqualTo(DEFAULT_DIEN_THOAI);
        assertThat(testHscn.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testHscn.getChuc_vu()).isEqualTo(DEFAULT_CHUC_VU);
        assertThat(testHscn.getMaPB()).isEqualTo(DEFAULT_MA_PB);
        assertThat(testHscn.getPhong_ban()).isEqualTo(DEFAULT_PHONG_BAN);
        assertThat(testHscn.getMaCV()).isEqualTo(DEFAULT_MA_CV);
        assertThat(testHscn.getMaNV()).isEqualTo(DEFAULT_MA_NV);
        assertThat(testHscn.getNoicapBHXH()).isEqualTo(DEFAULT_NOICAP_BHXH);
        assertThat(testHscn.getMaDT()).isEqualTo(DEFAULT_MA_DT);
        assertThat(testHscn.getMaBV()).isEqualTo(DEFAULT_MA_BV);
        assertThat(testHscn.getGhi_chu()).isEqualTo(DEFAULT_GHI_CHU);

        // Validate the Hscn in Elasticsearch
        Hscn hscnEs = hscnSearchRepository.findOne(testHscn.getId());
        assertThat(hscnEs).isEqualToComparingFieldByField(testHscn);
    }

    @Test
    @Transactional
    public void createHscnWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = hscnRepository.findAll().size();

        // Create the Hscn with an existing ID
        hscn.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHscnMockMvc.perform(post("/api/hscns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hscn)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Hscn> hscnList = hscnRepository.findAll();
        assertThat(hscnList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkSoBhxhIsRequired() throws Exception {
        int databaseSizeBeforeTest = hscnRepository.findAll().size();
        // set the field null
        hscn.setSoBhxh(null);

        // Create the Hscn, which fails.

        restHscnMockMvc.perform(post("/api/hscns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hscn)))
            .andExpect(status().isBadRequest());

        List<Hscn> hscnList = hscnRepository.findAll();
        assertThat(hscnList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllHscns() throws Exception {
        // Initialize the database
        hscnRepository.saveAndFlush(hscn);

        // Get all the hscnList
        restHscnMockMvc.perform(get("/api/hscns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hscn.getId().intValue())))
            .andExpect(jsonPath("$.[*].soBhxh").value(hasItem(DEFAULT_SO_BHXH.toString())))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN.toString())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH.toString())))
            .andExpect(jsonPath("$.[*].ngaySinh").value(hasItem(DEFAULT_NGAY_SINH.intValue())))
            .andExpect(jsonPath("$.[*].soCmnd").value(hasItem(DEFAULT_SO_CMND.toString())))
            .andExpect(jsonPath("$.[*].diaChiLh").value(hasItem(DEFAULT_DIA_CHI_LH.toString())))
            .andExpect(jsonPath("$.[*].ngayCap").value(hasItem(DEFAULT_NGAY_CAP.intValue())))
            .andExpect(jsonPath("$.[*].noiCap").value(hasItem(DEFAULT_NOI_CAP.toString())))
            .andExpect(jsonPath("$.[*].dan_toc").value(hasItem(DEFAULT_DAN_TOC.toString())))
            .andExpect(jsonPath("$.[*].quoc_tich").value(hasItem(DEFAULT_QUOC_TICH.toString())))
            .andExpect(jsonPath("$.[*].noi_khai").value(hasItem(DEFAULT_NOI_KHAI.toString())))
            .andExpect(jsonPath("$.[*].diachiHK").value(hasItem(DEFAULT_DIACHI_HK.toString())))
            .andExpect(jsonPath("$.[*].dien_thoai").value(hasItem(DEFAULT_DIEN_THOAI.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].chuc_vu").value(hasItem(DEFAULT_CHUC_VU.toString())))
            .andExpect(jsonPath("$.[*].maPB").value(hasItem(DEFAULT_MA_PB.toString())))
            .andExpect(jsonPath("$.[*].phong_ban").value(hasItem(DEFAULT_PHONG_BAN.toString())))
            .andExpect(jsonPath("$.[*].maCV").value(hasItem(DEFAULT_MA_CV.toString())))
            .andExpect(jsonPath("$.[*].maNV").value(hasItem(DEFAULT_MA_NV.toString())))
            .andExpect(jsonPath("$.[*].noicapBHXH").value(hasItem(DEFAULT_NOICAP_BHXH.toString())))
            .andExpect(jsonPath("$.[*].maDT").value(hasItem(DEFAULT_MA_DT.toString())))
            .andExpect(jsonPath("$.[*].maBV").value(hasItem(DEFAULT_MA_BV.toString())))
            .andExpect(jsonPath("$.[*].ghi_chu").value(hasItem(DEFAULT_GHI_CHU.toString())));
    }

    @Test
    @Transactional
    public void getHscn() throws Exception {
        // Initialize the database
        hscnRepository.saveAndFlush(hscn);

        // Get the hscn
        restHscnMockMvc.perform(get("/api/hscns/{id}", hscn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(hscn.getId().intValue()))
            .andExpect(jsonPath("$.soBhxh").value(DEFAULT_SO_BHXH.toString()))
            .andExpect(jsonPath("$.hoTen").value(DEFAULT_HO_TEN.toString()))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH.toString()))
            .andExpect(jsonPath("$.ngaySinh").value(DEFAULT_NGAY_SINH.intValue()))
            .andExpect(jsonPath("$.soCmnd").value(DEFAULT_SO_CMND.toString()))
            .andExpect(jsonPath("$.diaChiLh").value(DEFAULT_DIA_CHI_LH.toString()))
            .andExpect(jsonPath("$.ngayCap").value(DEFAULT_NGAY_CAP.intValue()))
            .andExpect(jsonPath("$.noiCap").value(DEFAULT_NOI_CAP.toString()))
            .andExpect(jsonPath("$.dan_toc").value(DEFAULT_DAN_TOC.toString()))
            .andExpect(jsonPath("$.quoc_tich").value(DEFAULT_QUOC_TICH.toString()))
            .andExpect(jsonPath("$.noi_khai").value(DEFAULT_NOI_KHAI.toString()))
            .andExpect(jsonPath("$.diachiHK").value(DEFAULT_DIACHI_HK.toString()))
            .andExpect(jsonPath("$.dien_thoai").value(DEFAULT_DIEN_THOAI.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.chuc_vu").value(DEFAULT_CHUC_VU.toString()))
            .andExpect(jsonPath("$.maPB").value(DEFAULT_MA_PB.toString()))
            .andExpect(jsonPath("$.phong_ban").value(DEFAULT_PHONG_BAN.toString()))
            .andExpect(jsonPath("$.maCV").value(DEFAULT_MA_CV.toString()))
            .andExpect(jsonPath("$.maNV").value(DEFAULT_MA_NV.toString()))
            .andExpect(jsonPath("$.noicapBHXH").value(DEFAULT_NOICAP_BHXH.toString()))
            .andExpect(jsonPath("$.maDT").value(DEFAULT_MA_DT.toString()))
            .andExpect(jsonPath("$.maBV").value(DEFAULT_MA_BV.toString()))
            .andExpect(jsonPath("$.ghi_chu").value(DEFAULT_GHI_CHU.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHscn() throws Exception {
        // Get the hscn
        restHscnMockMvc.perform(get("/api/hscns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHscn() throws Exception {
        // Initialize the database
        hscnService.save(hscn);

        int databaseSizeBeforeUpdate = hscnRepository.findAll().size();

        // Update the hscn
        Hscn updatedHscn = hscnRepository.findOne(hscn.getId());
        updatedHscn
            .soBhxh(UPDATED_SO_BHXH)
            .hoTen(UPDATED_HO_TEN)
            .gioiTinh(UPDATED_GIOI_TINH)
            .ngaySinh(UPDATED_NGAY_SINH)
            .soCmnd(UPDATED_SO_CMND)
            .diaChiLh(UPDATED_DIA_CHI_LH)
            .ngayCap(UPDATED_NGAY_CAP)
            .noiCap(UPDATED_NOI_CAP)
            .dan_toc(UPDATED_DAN_TOC)
            .quoc_tich(UPDATED_QUOC_TICH)
            .noi_khai(UPDATED_NOI_KHAI)
            .diachiHK(UPDATED_DIACHI_HK)
            .dien_thoai(UPDATED_DIEN_THOAI)
            .email(UPDATED_EMAIL)
            .chuc_vu(UPDATED_CHUC_VU)
            .maPB(UPDATED_MA_PB)
            .phong_ban(UPDATED_PHONG_BAN)
            .maCV(UPDATED_MA_CV)
            .maNV(UPDATED_MA_NV)
            .noicapBHXH(UPDATED_NOICAP_BHXH)
            .maDT(UPDATED_MA_DT)
            .maBV(UPDATED_MA_BV)
            .ghi_chu(UPDATED_GHI_CHU);

        restHscnMockMvc.perform(put("/api/hscns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedHscn)))
            .andExpect(status().isOk());

        // Validate the Hscn in the database
        List<Hscn> hscnList = hscnRepository.findAll();
        assertThat(hscnList).hasSize(databaseSizeBeforeUpdate);
        Hscn testHscn = hscnList.get(hscnList.size() - 1);
        assertThat(testHscn.getSoBhxh()).isEqualTo(UPDATED_SO_BHXH);
        assertThat(testHscn.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testHscn.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testHscn.getNgaySinh()).isEqualTo(UPDATED_NGAY_SINH);
        assertThat(testHscn.getSoCmnd()).isEqualTo(UPDATED_SO_CMND);
        assertThat(testHscn.getDiaChiLh()).isEqualTo(UPDATED_DIA_CHI_LH);
        assertThat(testHscn.getNgayCap()).isEqualTo(UPDATED_NGAY_CAP);
        assertThat(testHscn.getNoiCap()).isEqualTo(UPDATED_NOI_CAP);
        assertThat(testHscn.getDan_toc()).isEqualTo(UPDATED_DAN_TOC);
        assertThat(testHscn.getQuoc_tich()).isEqualTo(UPDATED_QUOC_TICH);
        assertThat(testHscn.getNoi_khai()).isEqualTo(UPDATED_NOI_KHAI);
        assertThat(testHscn.getDiachiHK()).isEqualTo(UPDATED_DIACHI_HK);
        assertThat(testHscn.getDien_thoai()).isEqualTo(UPDATED_DIEN_THOAI);
        assertThat(testHscn.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testHscn.getChuc_vu()).isEqualTo(UPDATED_CHUC_VU);
        assertThat(testHscn.getMaPB()).isEqualTo(UPDATED_MA_PB);
        assertThat(testHscn.getPhong_ban()).isEqualTo(UPDATED_PHONG_BAN);
        assertThat(testHscn.getMaCV()).isEqualTo(UPDATED_MA_CV);
        assertThat(testHscn.getMaNV()).isEqualTo(UPDATED_MA_NV);
        assertThat(testHscn.getNoicapBHXH()).isEqualTo(UPDATED_NOICAP_BHXH);
        assertThat(testHscn.getMaDT()).isEqualTo(UPDATED_MA_DT);
        assertThat(testHscn.getMaBV()).isEqualTo(UPDATED_MA_BV);
        assertThat(testHscn.getGhi_chu()).isEqualTo(UPDATED_GHI_CHU);

        // Validate the Hscn in Elasticsearch
        Hscn hscnEs = hscnSearchRepository.findOne(testHscn.getId());
        assertThat(hscnEs).isEqualToComparingFieldByField(testHscn);
    }

    @Test
    @Transactional
    public void updateNonExistingHscn() throws Exception {
        int databaseSizeBeforeUpdate = hscnRepository.findAll().size();

        // Create the Hscn

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restHscnMockMvc.perform(put("/api/hscns")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(hscn)))
            .andExpect(status().isCreated());

        // Validate the Hscn in the database
        List<Hscn> hscnList = hscnRepository.findAll();
        assertThat(hscnList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteHscn() throws Exception {
        // Initialize the database
        hscnService.save(hscn);

        int databaseSizeBeforeDelete = hscnRepository.findAll().size();

        // Get the hscn
        restHscnMockMvc.perform(delete("/api/hscns/{id}", hscn.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean hscnExistsInEs = hscnSearchRepository.exists(hscn.getId());
        assertThat(hscnExistsInEs).isFalse();

        // Validate the database is empty
        List<Hscn> hscnList = hscnRepository.findAll();
        assertThat(hscnList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchHscn() throws Exception {
        // Initialize the database
        hscnService.save(hscn);

        // Search the hscn
        restHscnMockMvc.perform(get("/api/_search/hscns?query=id:" + hscn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hscn.getId().intValue())))
            .andExpect(jsonPath("$.[*].soBhxh").value(hasItem(DEFAULT_SO_BHXH.toString())))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN.toString())))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH.toString())))
            .andExpect(jsonPath("$.[*].ngaySinh").value(hasItem(DEFAULT_NGAY_SINH.intValue())))
            .andExpect(jsonPath("$.[*].soCmnd").value(hasItem(DEFAULT_SO_CMND.toString())))
            .andExpect(jsonPath("$.[*].diaChiLh").value(hasItem(DEFAULT_DIA_CHI_LH.toString())))
            .andExpect(jsonPath("$.[*].ngayCap").value(hasItem(DEFAULT_NGAY_CAP.intValue())))
            .andExpect(jsonPath("$.[*].noiCap").value(hasItem(DEFAULT_NOI_CAP.toString())))
            .andExpect(jsonPath("$.[*].dan_toc").value(hasItem(DEFAULT_DAN_TOC.toString())))
            .andExpect(jsonPath("$.[*].quoc_tich").value(hasItem(DEFAULT_QUOC_TICH.toString())))
            .andExpect(jsonPath("$.[*].noi_khai").value(hasItem(DEFAULT_NOI_KHAI.toString())))
            .andExpect(jsonPath("$.[*].diachiHK").value(hasItem(DEFAULT_DIACHI_HK.toString())))
            .andExpect(jsonPath("$.[*].dien_thoai").value(hasItem(DEFAULT_DIEN_THOAI.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].chuc_vu").value(hasItem(DEFAULT_CHUC_VU.toString())))
            .andExpect(jsonPath("$.[*].maPB").value(hasItem(DEFAULT_MA_PB.toString())))
            .andExpect(jsonPath("$.[*].phong_ban").value(hasItem(DEFAULT_PHONG_BAN.toString())))
            .andExpect(jsonPath("$.[*].maCV").value(hasItem(DEFAULT_MA_CV.toString())))
            .andExpect(jsonPath("$.[*].maNV").value(hasItem(DEFAULT_MA_NV.toString())))
            .andExpect(jsonPath("$.[*].noicapBHXH").value(hasItem(DEFAULT_NOICAP_BHXH.toString())))
            .andExpect(jsonPath("$.[*].maDT").value(hasItem(DEFAULT_MA_DT.toString())))
            .andExpect(jsonPath("$.[*].maBV").value(hasItem(DEFAULT_MA_BV.toString())))
            .andExpect(jsonPath("$.[*].ghi_chu").value(hasItem(DEFAULT_GHI_CHU.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hscn.class);
        Hscn hscn1 = new Hscn();
        hscn1.setId(1L);
        Hscn hscn2 = new Hscn();
        hscn2.setId(hscn1.getId());
        assertThat(hscn1).isEqualTo(hscn2);
        hscn2.setId(2L);
        assertThat(hscn1).isNotEqualTo(hscn2);
        hscn1.setId(null);
        assertThat(hscn1).isNotEqualTo(hscn2);
    }
}
