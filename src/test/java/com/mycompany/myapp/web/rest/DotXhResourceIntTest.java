package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppsmysqlApp;

import com.mycompany.myapp.domain.DotXh;
import com.mycompany.myapp.repository.DotXhRepository;
import com.mycompany.myapp.service.DotXhService;
import com.mycompany.myapp.repository.search.DotXhSearchRepository;
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
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.sameInstant;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the DotXhResource REST controller.
 *
 * @see DotXhResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppsmysqlApp.class)
public class DotXhResourceIntTest {

    private static final String DEFAULT_MA_DV = "AAAAAAAAAA";
    private static final String UPDATED_MA_DV = "BBBBBBBBBB";

    private static final String DEFAULT_MA_QT = "AAAAAAAAAA";
    private static final String UPDATED_MA_QT = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI_DT = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_DT = "BBBBBBBBBB";

    private static final String DEFAULT_MA_NT = "AAAAAAAAAA";
    private static final String UPDATED_MA_NT = "BBBBBBBBBB";

    private static final Integer DEFAULT_TY_GIA = 1;
    private static final Integer UPDATED_TY_GIA = 2;

    private static final ZonedDateTime DEFAULT_NGAY_CT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_CT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_SO_PHIEU = "AAAAAAAAAA";
    private static final String UPDATED_SO_PHIEU = "BBBBBBBBBB";

    private static final Integer DEFAULT_SO_LD = 1;
    private static final Integer UPDATED_SO_LD = 2;

    private static final Float DEFAULT_TQL = 1F;
    private static final Float UPDATED_TQL = 2F;

    private static final String DEFAULT_USER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_USER_CODE = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_LAST_CODE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_LAST_CODE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_SLD_NU = 1;
    private static final Integer UPDATED_SLD_NU = 2;

    private static final String DEFAULT_MA_QL = "AAAAAAAAAA";
    private static final String UPDATED_MA_QL = "BBBBBBBBBB";

    private static final String DEFAULT_MA_TINH = "AAAAAAAAAA";
    private static final String UPDATED_MA_TINH = "BBBBBBBBBB";

    private static final String DEFAULT_LIST_NGAYNGHI = "AAAAAAAAAA";
    private static final String UPDATED_LIST_NGAYNGHI = "BBBBBBBBBB";

    private static final Boolean DEFAULT_KHOA_SO = false;
    private static final Boolean UPDATED_KHOA_SO = true;

    private static final ZonedDateTime DEFAULT_NGAY_KHOA = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_KHOA = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_THONG_TIN_1 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_1 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_2 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_2 = "BBBBBBBBBB";

    private static final String DEFAULT_THONG_TIN_3 = "AAAAAAAAAA";
    private static final String UPDATED_THONG_TIN_3 = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NGAY_2 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_2 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_NGAY_3 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_3 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_NGAY_DUYET = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_DUYET = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NGUOI_DUYET = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_DUYET = "BBBBBBBBBB";

    private static final String DEFAULT_KEY_SL = "AAAAAAAAAA";
    private static final String UPDATED_KEY_SL = "BBBBBBBBBB";

    @Autowired
    private DotXhRepository dotXhRepository;

    @Autowired
    private DotXhService dotXhService;

    @Autowired
    private DotXhSearchRepository dotXhSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDotXhMockMvc;

    private DotXh dotXh;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DotXhResource dotXhResource = new DotXhResource(dotXhService);
        this.restDotXhMockMvc = MockMvcBuilders.standaloneSetup(dotXhResource)
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
    public static DotXh createEntity(EntityManager em) {
        DotXh dotXh = new DotXh()
            .maDv(DEFAULT_MA_DV)
            .maQt(DEFAULT_MA_QT)
            .loaiDt(DEFAULT_LOAI_DT)
            .maNt(DEFAULT_MA_NT)
            .tyGia(DEFAULT_TY_GIA)
            .ngayCt(DEFAULT_NGAY_CT)
            .soPhieu(DEFAULT_SO_PHIEU)
            .soLd(DEFAULT_SO_LD)
            .tql(DEFAULT_TQL)
            .userCode(DEFAULT_USER_CODE)
            .lastCode(DEFAULT_LAST_CODE)
            .sldNu(DEFAULT_SLD_NU)
            .maQl(DEFAULT_MA_QL)
            .maTinh(DEFAULT_MA_TINH)
            .listNgaynghi(DEFAULT_LIST_NGAYNGHI)
            .khoaSo(DEFAULT_KHOA_SO)
            .ngayKhoa(DEFAULT_NGAY_KHOA)
            .thongTin1(DEFAULT_THONG_TIN_1)
            .thongTin2(DEFAULT_THONG_TIN_2)
            .thongTin3(DEFAULT_THONG_TIN_3)
            .ngay1(DEFAULT_NGAY_1)
            .ngay2(DEFAULT_NGAY_2)
            .ngay3(DEFAULT_NGAY_3)
            .ghiChu(DEFAULT_GHI_CHU)
            .ngayDuyet(DEFAULT_NGAY_DUYET)
            .nguoiDuyet(DEFAULT_NGUOI_DUYET);
        return dotXh;
    }

    @Before
    public void initTest() {
        dotXhSearchRepository.deleteAll();
        dotXh = createEntity(em);
    }

    @Test
    @Transactional
    public void createDotXh() throws Exception {
        int databaseSizeBeforeCreate = dotXhRepository.findAll().size();

        // Create the DotXh
        restDotXhMockMvc.perform(post("/api/dot-xhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dotXh)))
            .andExpect(status().isCreated());

        // Validate the DotXh in the database
        List<DotXh> dotXhList = dotXhRepository.findAll();
        assertThat(dotXhList).hasSize(databaseSizeBeforeCreate + 1);
        DotXh testDotXh = dotXhList.get(dotXhList.size() - 1);
        assertThat(testDotXh.getMaDv()).isEqualTo(DEFAULT_MA_DV);
        assertThat(testDotXh.getMaQt()).isEqualTo(DEFAULT_MA_QT);
        assertThat(testDotXh.getLoaiDt()).isEqualTo(DEFAULT_LOAI_DT);
        assertThat(testDotXh.getMaNt()).isEqualTo(DEFAULT_MA_NT);
        assertThat(testDotXh.getTyGia()).isEqualTo(DEFAULT_TY_GIA);
        assertThat(testDotXh.getNgayCt()).isEqualTo(DEFAULT_NGAY_CT);
        assertThat(testDotXh.getSoPhieu()).isEqualTo(DEFAULT_SO_PHIEU);
        assertThat(testDotXh.getSoLd()).isEqualTo(DEFAULT_SO_LD);
        assertThat(testDotXh.getTql()).isEqualTo(DEFAULT_TQL);
        assertThat(testDotXh.getUserCode()).isEqualTo(DEFAULT_USER_CODE);
        assertThat(testDotXh.getLastCode()).isEqualTo(DEFAULT_LAST_CODE);
        assertThat(testDotXh.getSldNu()).isEqualTo(DEFAULT_SLD_NU);
        assertThat(testDotXh.getMaQl()).isEqualTo(DEFAULT_MA_QL);
        assertThat(testDotXh.getMaTinh()).isEqualTo(DEFAULT_MA_TINH);
        assertThat(testDotXh.getListNgaynghi()).isEqualTo(DEFAULT_LIST_NGAYNGHI);
        assertThat(testDotXh.isKhoaSo()).isEqualTo(DEFAULT_KHOA_SO);
        assertThat(testDotXh.getNgayKhoa()).isEqualTo(DEFAULT_NGAY_KHOA);
        assertThat(testDotXh.getThongTin1()).isEqualTo(DEFAULT_THONG_TIN_1);
        assertThat(testDotXh.getThongTin2()).isEqualTo(DEFAULT_THONG_TIN_2);
        assertThat(testDotXh.getThongTin3()).isEqualTo(DEFAULT_THONG_TIN_3);
        assertThat(testDotXh.getNgay1()).isEqualTo(DEFAULT_NGAY_1);
        assertThat(testDotXh.getNgay2()).isEqualTo(DEFAULT_NGAY_2);
        assertThat(testDotXh.getNgay3()).isEqualTo(DEFAULT_NGAY_3);
        assertThat(testDotXh.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testDotXh.getNgayDuyet()).isEqualTo(DEFAULT_NGAY_DUYET);
        assertThat(testDotXh.getNguoiDuyet()).isEqualTo(DEFAULT_NGUOI_DUYET);
        // Validate the DotXh in Elasticsearch
        DotXh dotXhEs = dotXhSearchRepository.findOne(testDotXh.getId());
        assertThat(dotXhEs).isEqualToComparingFieldByField(testDotXh);
    }

    @Test
    @Transactional
    public void createDotXhWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dotXhRepository.findAll().size();

        // Create the DotXh with an existing ID
        dotXh.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDotXhMockMvc.perform(post("/api/dot-xhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dotXh)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<DotXh> dotXhList = dotXhRepository.findAll();
        assertThat(dotXhList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDotXhs() throws Exception {
        // Initialize the database
        dotXhRepository.saveAndFlush(dotXh);

        // Get all the dotXhList
        restDotXhMockMvc.perform(get("/api/dot-xhs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dotXh.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDv").value(hasItem(DEFAULT_MA_DV.toString())))
            .andExpect(jsonPath("$.[*].maQt").value(hasItem(DEFAULT_MA_QT.toString())))
            .andExpect(jsonPath("$.[*].loaiDt").value(hasItem(DEFAULT_LOAI_DT.toString())))
            .andExpect(jsonPath("$.[*].maNt").value(hasItem(DEFAULT_MA_NT.toString())))
            .andExpect(jsonPath("$.[*].tyGia").value(hasItem(DEFAULT_TY_GIA)))
            .andExpect(jsonPath("$.[*].ngayCt").value(hasItem(sameInstant(DEFAULT_NGAY_CT))))
            .andExpect(jsonPath("$.[*].soPhieu").value(hasItem(DEFAULT_SO_PHIEU.toString())))
            .andExpect(jsonPath("$.[*].soLd").value(hasItem(DEFAULT_SO_LD)))
            .andExpect(jsonPath("$.[*].tql").value(hasItem(DEFAULT_TQL.doubleValue())))
            .andExpect(jsonPath("$.[*].userCode").value(hasItem(DEFAULT_USER_CODE.toString())))
            .andExpect(jsonPath("$.[*].lastCode").value(hasItem(sameInstant(DEFAULT_LAST_CODE))))
            .andExpect(jsonPath("$.[*].sldNu").value(hasItem(DEFAULT_SLD_NU)))
            .andExpect(jsonPath("$.[*].maQl").value(hasItem(DEFAULT_MA_QL.toString())))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(DEFAULT_MA_TINH.toString())))
            .andExpect(jsonPath("$.[*].listNgaynghi").value(hasItem(DEFAULT_LIST_NGAYNGHI.toString())))
            .andExpect(jsonPath("$.[*].khoaSo").value(hasItem(DEFAULT_KHOA_SO.booleanValue())))
            .andExpect(jsonPath("$.[*].ngayKhoa").value(hasItem(sameInstant(DEFAULT_NGAY_KHOA))))
            .andExpect(jsonPath("$.[*].thongTin1").value(hasItem(DEFAULT_THONG_TIN_1.toString())))
            .andExpect(jsonPath("$.[*].thongTin2").value(hasItem(DEFAULT_THONG_TIN_2.toString())))
            .andExpect(jsonPath("$.[*].thongTin3").value(hasItem(DEFAULT_THONG_TIN_3.toString())))
            .andExpect(jsonPath("$.[*].ngay1").value(hasItem(sameInstant(DEFAULT_NGAY_1))))
            .andExpect(jsonPath("$.[*].ngay2").value(hasItem(sameInstant(DEFAULT_NGAY_2))))
            .andExpect(jsonPath("$.[*].ngay3").value(hasItem(sameInstant(DEFAULT_NGAY_3))))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].ngayDuyet").value(hasItem(sameInstant(DEFAULT_NGAY_DUYET))))
            .andExpect(jsonPath("$.[*].nguoiDuyet").value(hasItem(DEFAULT_NGUOI_DUYET.toString())))
            ;
    }

    @Test
    @Transactional
    public void getDotXh() throws Exception {
        // Initialize the database
        dotXhRepository.saveAndFlush(dotXh);

        // Get the dotXh
        restDotXhMockMvc.perform(get("/api/dot-xhs/{id}", dotXh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(dotXh.getId().intValue()))
            .andExpect(jsonPath("$.maDv").value(DEFAULT_MA_DV.toString()))
            .andExpect(jsonPath("$.maQt").value(DEFAULT_MA_QT.toString()))
            .andExpect(jsonPath("$.loaiDt").value(DEFAULT_LOAI_DT.toString()))
            .andExpect(jsonPath("$.maNt").value(DEFAULT_MA_NT.toString()))
            .andExpect(jsonPath("$.tyGia").value(DEFAULT_TY_GIA))
            .andExpect(jsonPath("$.ngayCt").value(sameInstant(DEFAULT_NGAY_CT)))
            .andExpect(jsonPath("$.soPhieu").value(DEFAULT_SO_PHIEU.toString()))
            .andExpect(jsonPath("$.soLd").value(DEFAULT_SO_LD))
            .andExpect(jsonPath("$.tql").value(DEFAULT_TQL.doubleValue()))
            .andExpect(jsonPath("$.userCode").value(DEFAULT_USER_CODE.toString()))
            .andExpect(jsonPath("$.lastCode").value(sameInstant(DEFAULT_LAST_CODE)))
            .andExpect(jsonPath("$.sldNu").value(DEFAULT_SLD_NU))
            .andExpect(jsonPath("$.maQl").value(DEFAULT_MA_QL.toString()))
            .andExpect(jsonPath("$.maTinh").value(DEFAULT_MA_TINH.toString()))
            .andExpect(jsonPath("$.listNgaynghi").value(DEFAULT_LIST_NGAYNGHI.toString()))
            .andExpect(jsonPath("$.khoaSo").value(DEFAULT_KHOA_SO.booleanValue()))
            .andExpect(jsonPath("$.ngayKhoa").value(sameInstant(DEFAULT_NGAY_KHOA)))
            .andExpect(jsonPath("$.thongTin1").value(DEFAULT_THONG_TIN_1.toString()))
            .andExpect(jsonPath("$.thongTin2").value(DEFAULT_THONG_TIN_2.toString()))
            .andExpect(jsonPath("$.thongTin3").value(DEFAULT_THONG_TIN_3.toString()))
            .andExpect(jsonPath("$.ngay1").value(sameInstant(DEFAULT_NGAY_1)))
            .andExpect(jsonPath("$.ngay2").value(sameInstant(DEFAULT_NGAY_2)))
            .andExpect(jsonPath("$.ngay3").value(sameInstant(DEFAULT_NGAY_3)))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.ngayDuyet").value(sameInstant(DEFAULT_NGAY_DUYET)))
            .andExpect(jsonPath("$.nguoiDuyet").value(DEFAULT_NGUOI_DUYET.toString()))
            ;
    }

    @Test
    @Transactional
    public void getNonExistingDotXh() throws Exception {
        // Get the dotXh
        restDotXhMockMvc.perform(get("/api/dot-xhs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDotXh() throws Exception {
        // Initialize the database
        dotXhService.save(dotXh);

        int databaseSizeBeforeUpdate = dotXhRepository.findAll().size();

        // Update the dotXh
        DotXh updatedDotXh = dotXhRepository.findOne(dotXh.getId());
        updatedDotXh
            .maDv(UPDATED_MA_DV)
            .maQt(UPDATED_MA_QT)
            .loaiDt(UPDATED_LOAI_DT)
            .maNt(UPDATED_MA_NT)
            .tyGia(UPDATED_TY_GIA)
            .ngayCt(UPDATED_NGAY_CT)
            .soPhieu(UPDATED_SO_PHIEU)
            .soLd(UPDATED_SO_LD)
            .tql(UPDATED_TQL)
            .userCode(UPDATED_USER_CODE)
            .lastCode(UPDATED_LAST_CODE)
            .sldNu(UPDATED_SLD_NU)
            .maQl(UPDATED_MA_QL)
            .maTinh(UPDATED_MA_TINH)
            .listNgaynghi(UPDATED_LIST_NGAYNGHI)
            .khoaSo(UPDATED_KHOA_SO)
            .ngayKhoa(UPDATED_NGAY_KHOA)
            .thongTin1(UPDATED_THONG_TIN_1)
            .thongTin2(UPDATED_THONG_TIN_2)
            .thongTin3(UPDATED_THONG_TIN_3)
            .ngay1(UPDATED_NGAY_1)
            .ngay2(UPDATED_NGAY_2)
            .ngay3(UPDATED_NGAY_3)
            .ghiChu(UPDATED_GHI_CHU)
            .ngayDuyet(UPDATED_NGAY_DUYET)
            .nguoiDuyet(UPDATED_NGUOI_DUYET)
          ;

        restDotXhMockMvc.perform(put("/api/dot-xhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDotXh)))
            .andExpect(status().isOk());

        // Validate the DotXh in the database
        List<DotXh> dotXhList = dotXhRepository.findAll();
        assertThat(dotXhList).hasSize(databaseSizeBeforeUpdate);
        DotXh testDotXh = dotXhList.get(dotXhList.size() - 1);
        assertThat(testDotXh.getMaDv()).isEqualTo(UPDATED_MA_DV);
        assertThat(testDotXh.getMaQt()).isEqualTo(UPDATED_MA_QT);
        assertThat(testDotXh.getLoaiDt()).isEqualTo(UPDATED_LOAI_DT);
        assertThat(testDotXh.getMaNt()).isEqualTo(UPDATED_MA_NT);
        assertThat(testDotXh.getTyGia()).isEqualTo(UPDATED_TY_GIA);
        assertThat(testDotXh.getNgayCt()).isEqualTo(UPDATED_NGAY_CT);
        assertThat(testDotXh.getSoPhieu()).isEqualTo(UPDATED_SO_PHIEU);
        assertThat(testDotXh.getSoLd()).isEqualTo(UPDATED_SO_LD);
        assertThat(testDotXh.getTql()).isEqualTo(UPDATED_TQL);
        assertThat(testDotXh.getUserCode()).isEqualTo(UPDATED_USER_CODE);
        assertThat(testDotXh.getLastCode()).isEqualTo(UPDATED_LAST_CODE);
        assertThat(testDotXh.getSldNu()).isEqualTo(UPDATED_SLD_NU);
        assertThat(testDotXh.getMaQl()).isEqualTo(UPDATED_MA_QL);
        assertThat(testDotXh.getMaTinh()).isEqualTo(UPDATED_MA_TINH);
        assertThat(testDotXh.getListNgaynghi()).isEqualTo(UPDATED_LIST_NGAYNGHI);
        assertThat(testDotXh.isKhoaSo()).isEqualTo(UPDATED_KHOA_SO);
        assertThat(testDotXh.getNgayKhoa()).isEqualTo(UPDATED_NGAY_KHOA);
        assertThat(testDotXh.getThongTin1()).isEqualTo(UPDATED_THONG_TIN_1);
        assertThat(testDotXh.getThongTin2()).isEqualTo(UPDATED_THONG_TIN_2);
        assertThat(testDotXh.getThongTin3()).isEqualTo(UPDATED_THONG_TIN_3);
        assertThat(testDotXh.getNgay1()).isEqualTo(UPDATED_NGAY_1);
        assertThat(testDotXh.getNgay2()).isEqualTo(UPDATED_NGAY_2);
        assertThat(testDotXh.getNgay3()).isEqualTo(UPDATED_NGAY_3);
        assertThat(testDotXh.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testDotXh.getNgayDuyet()).isEqualTo(UPDATED_NGAY_DUYET);
        assertThat(testDotXh.getNguoiDuyet()).isEqualTo(UPDATED_NGUOI_DUYET);
       ;

        // Validate the DotXh in Elasticsearch
        DotXh dotXhEs = dotXhSearchRepository.findOne(testDotXh.getId());
        assertThat(dotXhEs).isEqualToComparingFieldByField(testDotXh);
    }

    @Test
    @Transactional
    public void updateNonExistingDotXh() throws Exception {
        int databaseSizeBeforeUpdate = dotXhRepository.findAll().size();

        // Create the DotXh

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDotXhMockMvc.perform(put("/api/dot-xhs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(dotXh)))
            .andExpect(status().isCreated());

        // Validate the DotXh in the database
        List<DotXh> dotXhList = dotXhRepository.findAll();
        assertThat(dotXhList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDotXh() throws Exception {
        // Initialize the database
        dotXhService.save(dotXh);

        int databaseSizeBeforeDelete = dotXhRepository.findAll().size();

        // Get the dotXh
        restDotXhMockMvc.perform(delete("/api/dot-xhs/{id}", dotXh.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean dotXhExistsInEs = dotXhSearchRepository.exists(dotXh.getId());
        assertThat(dotXhExistsInEs).isFalse();

        // Validate the database is empty
        List<DotXh> dotXhList = dotXhRepository.findAll();
        assertThat(dotXhList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchDotXh() throws Exception {
        // Initialize the database
        dotXhService.save(dotXh);

        // Search the dotXh
        restDotXhMockMvc.perform(get("/api/_search/dot-xhs?query=id:" + dotXh.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dotXh.getId().intValue())))
            .andExpect(jsonPath("$.[*].maDv").value(hasItem(DEFAULT_MA_DV.toString())))
            .andExpect(jsonPath("$.[*].maQt").value(hasItem(DEFAULT_MA_QT.toString())))
            .andExpect(jsonPath("$.[*].loaiDt").value(hasItem(DEFAULT_LOAI_DT.toString())))
            .andExpect(jsonPath("$.[*].maNt").value(hasItem(DEFAULT_MA_NT.toString())))
            .andExpect(jsonPath("$.[*].tyGia").value(hasItem(DEFAULT_TY_GIA)))
            .andExpect(jsonPath("$.[*].ngayCt").value(hasItem(sameInstant(DEFAULT_NGAY_CT))))
            .andExpect(jsonPath("$.[*].soPhieu").value(hasItem(DEFAULT_SO_PHIEU.toString())))
            .andExpect(jsonPath("$.[*].soLd").value(hasItem(DEFAULT_SO_LD)))
            .andExpect(jsonPath("$.[*].tql").value(hasItem(DEFAULT_TQL.doubleValue())))
            .andExpect(jsonPath("$.[*].userCode").value(hasItem(DEFAULT_USER_CODE.toString())))
            .andExpect(jsonPath("$.[*].lastCode").value(hasItem(sameInstant(DEFAULT_LAST_CODE))))
            .andExpect(jsonPath("$.[*].sldNu").value(hasItem(DEFAULT_SLD_NU)))
            .andExpect(jsonPath("$.[*].maQl").value(hasItem(DEFAULT_MA_QL.toString())))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(DEFAULT_MA_TINH.toString())))
            .andExpect(jsonPath("$.[*].listNgaynghi").value(hasItem(DEFAULT_LIST_NGAYNGHI.toString())))
            .andExpect(jsonPath("$.[*].khoaSo").value(hasItem(DEFAULT_KHOA_SO.booleanValue())))
            .andExpect(jsonPath("$.[*].ngayKhoa").value(hasItem(sameInstant(DEFAULT_NGAY_KHOA))))
            .andExpect(jsonPath("$.[*].thongTin1").value(hasItem(DEFAULT_THONG_TIN_1.toString())))
            .andExpect(jsonPath("$.[*].thongTin2").value(hasItem(DEFAULT_THONG_TIN_2.toString())))
            .andExpect(jsonPath("$.[*].thongTin3").value(hasItem(DEFAULT_THONG_TIN_3.toString())))
            .andExpect(jsonPath("$.[*].ngay1").value(hasItem(sameInstant(DEFAULT_NGAY_1))))
            .andExpect(jsonPath("$.[*].ngay2").value(hasItem(sameInstant(DEFAULT_NGAY_2))))
            .andExpect(jsonPath("$.[*].ngay3").value(hasItem(sameInstant(DEFAULT_NGAY_3))))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].ngayDuyet").value(hasItem(sameInstant(DEFAULT_NGAY_DUYET))))
            .andExpect(jsonPath("$.[*].nguoiDuyet").value(hasItem(DEFAULT_NGUOI_DUYET.toString())))
           ;
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DotXh.class);
        DotXh dotXh1 = new DotXh();
        dotXh1.setId(1L);
        DotXh dotXh2 = new DotXh();
        dotXh2.setId(dotXh1.getId());
        assertThat(dotXh1).isEqualTo(dotXh2);
        dotXh2.setId(2L);
        assertThat(dotXh1).isNotEqualTo(dotXh2);
        dotXh1.setId(null);
        assertThat(dotXh1).isNotEqualTo(dotXh2);
    }
}
