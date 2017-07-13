package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.AppsmysqlApp;

import com.mycompany.myapp.domain.CtHuong;
import com.mycompany.myapp.repository.CtHuongRepository;
import com.mycompany.myapp.service.CtHuongService;
import com.mycompany.myapp.repository.search.CtHuongSearchRepository;
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
 * Test class for the CtHuongResource REST controller.
 *
 * @see CtHuongResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppsmysqlApp.class)
public class CtHuongResourceIntTest {

    private static final String DEFAULT_KEYSL = "AAAAAAAAAA";
    private static final String UPDATED_KEYSL = "BBBBBBBBBB";

    private static final String DEFAULT_KEY_YP = "AAAAAAAAAA";
    private static final String UPDATED_KEY_YP = "BBBBBBBBBB";

    private static final String DEFAULT_MA_DV = "AAAAAAAAAA";
    private static final String UPDATED_MA_DV = "BBBBBBBBBB";

    private static final String DEFAULT_SO_BHXH = "AAAAAAAAAA";
    private static final String UPDATED_SO_BHXH = "BBBBBBBBBB";

    private static final String DEFAULT_MA_NV = "AAAAAAAAAA";
    private static final String UPDATED_MA_NV = "BBBBBBBBBB";

    private static final String DEFAULT_HO_TEN = "AAAAAAAAAA";
    private static final String UPDATED_HO_TEN = "BBBBBBBBBB";

    private static final Float DEFAULT_MUC_LUONG = 1F;
    private static final Float UPDATED_MUC_LUONG = 2F;

    private static final ZonedDateTime DEFAULT_NGAY_DUYET = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_DUYET = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_NGUOI_DUYET = "AAAAAAAAAA";
    private static final String UPDATED_NGUOI_DUYET = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI_DT = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_DT = "BBBBBBBBBB";

    private static final String DEFAULT_MA_NT = "AAAAAAAAAA";
    private static final String UPDATED_MA_NT = "BBBBBBBBBB";

    private static final Float DEFAULT_ML_TT = 1F;
    private static final Float UPDATED_ML_TT = 2F;

    private static final String DEFAULT_MA_CD = "AAAAAAAAAA";
    private static final String UPDATED_MA_CD = "BBBBBBBBBB";

    private static final String DEFAULT_MA_NH = "AAAAAAAAAA";
    private static final String UPDATED_MA_NH = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TU_NGAY = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TU_NGAY = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEN_NGAY = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEN_NGAY = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_TU_NGAY_H = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TU_NGAY_H = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEN_NGAY_H = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEN_NGAY_H = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_SO_NGAY = 1;
    private static final Integer UPDATED_SO_NGAY = 2;

    private static final Integer DEFAULT_SO_NGAY_H = 1;
    private static final Integer UPDATED_SO_NGAY_H = 2;

    private static final Integer DEFAULT_SO_NGAY_LK = 1;
    private static final Integer UPDATED_SO_NGAY_LK = 2;

    private static final Float DEFAULT_SO_TIEN = 1F;
    private static final Float UPDATED_SO_TIEN = 2F;

    private static final Float DEFAULT_SO_TIEN_H = 1F;
    private static final Float UPDATED_SO_TIEN_H = 2F;

    private static final Integer DEFAULT_SO_NAM_BH = 1;
    private static final Integer UPDATED_SO_NAM_BH = 2;

    private static final Integer DEFAULT_SO_THANG_BH = 1;
    private static final Integer UPDATED_SO_THANG_BH = 2;

    private static final Integer DEFAULT_SO_NGAY_LKDV = 1;
    private static final Integer UPDATED_SO_NGAY_LKDV = 2;

    private static final String DEFAULT_GHI_CHU = "AAAAAAAAAA";
    private static final String UPDATED_GHI_CHU = "BBBBBBBBBB";

    private static final Integer DEFAULT_DK_1 = 1;
    private static final Integer UPDATED_DK_1 = 2;

    private static final Integer DEFAULT_DK_2 = 1;
    private static final Integer UPDATED_DK_2 = 2;

    private static final Integer DEFAULT_DK_5 = 1;
    private static final Integer UPDATED_DK_5 = 2;

    private static final Integer DEFAULT_DK_6 = 1;
    private static final Integer UPDATED_DK_6 = 2;

    private static final Integer DEFAULT_TUOI_CON = 1;
    private static final Integer UPDATED_TUOI_CON = 2;

    private static final Integer DEFAULT_STT_CON = 1;
    private static final Integer UPDATED_STT_CON = 2;

    private static final Integer DEFAULT_GIOI_TINH = 1;
    private static final Integer UPDATED_GIOI_TINH = 2;

    private static final String DEFAULT_MA_QT = "AAAAAAAAAA";
    private static final String UPDATED_MA_QT = "BBBBBBBBBB";

    private static final Long DEFAULT_DK_BENH_DAI = 1L;
    private static final Long UPDATED_DK_BENH_DAI = 2L;

    private static final Integer DEFAULT_DK_PHAUTHUAT = 1;
    private static final Integer UPDATED_DK_PHAUTHUAT = 2;

    private static final Integer DEFAULT_DK_NGHIONHA = 1;
    private static final Integer UPDATED_DK_NGHIONHA = 2;

    private static final Integer DEFAULT_DK_SUYGIAMLD = 1;
    private static final Integer UPDATED_DK_SUYGIAMLD = 2;

    private static final Integer DEFAULT_DK_XACSYT = 1;
    private static final Integer UPDATED_DK_XACSYT = 2;

    private static final Integer DEFAULT_DK_3_CA = 1;
    private static final Integer UPDATED_DK_3_CA = 2;

    private static final ZonedDateTime DEFAULT_NGAY_1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_1 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_TEN_CON = "AAAAAAAAAA";
    private static final String UPDATED_TEN_CON = "BBBBBBBBBB";

    private static final Integer DEFAULT_STT = 1;
    private static final Integer UPDATED_STT = 2;

    private static final String DEFAULT_MA_QL = "AAAAAAAAAA";
    private static final String UPDATED_MA_QL = "BBBBBBBBBB";

    private static final String DEFAULT_MA_TINH = "AAAAAAAAAA";
    private static final String UPDATED_MA_TINH = "BBBBBBBBBB";

    private static final Integer DEFAULT_LOAIDC = 1;
    private static final Integer UPDATED_LOAIDC = 2;

    private static final String DEFAULT_LYDODC = "AAAAAAAAAA";
    private static final String UPDATED_LYDODC = "BBBBBBBBBB";

    private static final String DEFAULT_GHICHUDC = "AAAAAAAAAA";
    private static final String UPDATED_GHICHUDC = "BBBBBBBBBB";

    private static final String DEFAULT_LOAI_BENH = "AAAAAAAAAA";
    private static final String UPDATED_LOAI_BENH = "BBBBBBBBBB";

    private static final String DEFAULT_TUYEN_BV = "AAAAAAAAAA";
    private static final String UPDATED_TUYEN_BV = "BBBBBBBBBB";

    private static final Float DEFAULT_LBQHS = 1F;
    private static final Float UPDATED_LBQHS = 2F;

    private static final Float DEFAULT_LBQML = 1F;
    private static final Float UPDATED_LBQML = 2F;

    private static final Float DEFAULT_TRO_CAP = 1F;
    private static final Float UPDATED_TRO_CAP = 2F;

    private static final Float DEFAULT_TRO_CAP_BH = 1F;
    private static final Float UPDATED_TRO_CAP_BH = 2F;

    private static final Integer DEFAULT_DK_3 = 1;
    private static final Integer UPDATED_DK_3 = 2;

    private static final Integer DEFAULT_DK_4 = 1;
    private static final Integer UPDATED_DK_4 = 2;

    private static final Float DEFAULT_TYLEH = 1F;
    private static final Float UPDATED_TYLEH = 2F;

    private static final ZonedDateTime DEFAULT_NGAY_NUOI = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_NGAY_NUOI = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_SO_NGAY_CD = 1;
    private static final Integer UPDATED_SO_NGAY_CD = 2;

    private static final String DEFAULT_NGAY_NGHI_LIST = "AAAAAAAAAA";
    private static final String UPDATED_NGAY_NGHI_LIST = "BBBBBBBBBB";

    private static final String DEFAULT_VSSID = "AAAAAAAAAA";
    private static final String UPDATED_VSSID = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_TU_NGAY_BS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TU_NGAY_BS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_DEN_NGAY_BS = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEN_NGAY_BS = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Integer DEFAULT_CHK_1 = 1;
    private static final Integer UPDATED_CHK_1 = 2;

    private static final Integer DEFAULT_CHK_2 = 1;
    private static final Integer UPDATED_CHK_2 = 2;

    private static final Integer DEFAULT_CHK_3 = 1;
    private static final Integer UPDATED_CHK_3 = 2;

    @Autowired
    private CtHuongRepository ctHuongRepository;

    @Autowired
    private CtHuongService ctHuongService;

    @Autowired
    private CtHuongSearchRepository ctHuongSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCtHuongMockMvc;

    private CtHuong ctHuong;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        CtHuongResource ctHuongResource = new CtHuongResource(ctHuongService);
        this.restCtHuongMockMvc = MockMvcBuilders.standaloneSetup(ctHuongResource)
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
    public static CtHuong createEntity(EntityManager em) {
        CtHuong ctHuong = new CtHuong()
            .keysl(DEFAULT_KEYSL)
            .keyYp(DEFAULT_KEY_YP)
            .maDv(DEFAULT_MA_DV)
            .soBhxh(DEFAULT_SO_BHXH)
            .maNv(DEFAULT_MA_NV)
            .hoTen(DEFAULT_HO_TEN)
            .mucLuong(DEFAULT_MUC_LUONG)
            .ngayDuyet(DEFAULT_NGAY_DUYET)
            .nguoiDuyet(DEFAULT_NGUOI_DUYET)
            .loaiDt(DEFAULT_LOAI_DT)
            .maNt(DEFAULT_MA_NT)
            .mlTt(DEFAULT_ML_TT)
            .maCd(DEFAULT_MA_CD)
            .maNh(DEFAULT_MA_NH)
            .tuNgay(DEFAULT_TU_NGAY)
            .denNgay(DEFAULT_DEN_NGAY)
            .tuNgayH(DEFAULT_TU_NGAY_H)
            .denNgayH(DEFAULT_DEN_NGAY_H)
            .soNgay(DEFAULT_SO_NGAY)
            .soNgayH(DEFAULT_SO_NGAY_H)
            .soNgayLk(DEFAULT_SO_NGAY_LK)
            .soTien(DEFAULT_SO_TIEN)
            .soTienH(DEFAULT_SO_TIEN_H)
            .soNamBh(DEFAULT_SO_NAM_BH)
            .soThangBh(DEFAULT_SO_THANG_BH)
            .soNgayLkdv(DEFAULT_SO_NGAY_LKDV)
            .ghiChu(DEFAULT_GHI_CHU)
            .dk1(DEFAULT_DK_1)
            .dk2(DEFAULT_DK_2)
            .dk5(DEFAULT_DK_5)
            .dk6(DEFAULT_DK_6)
            .tuoiCon(DEFAULT_TUOI_CON)
            .sttCon(DEFAULT_STT_CON)
            .gioiTinh(DEFAULT_GIOI_TINH)
            .maQt(DEFAULT_MA_QT)
            .dkBenhDai(DEFAULT_DK_BENH_DAI)
            .dkPhauthuat(DEFAULT_DK_PHAUTHUAT)
            .dkNghionha(DEFAULT_DK_NGHIONHA)
            .dkSuygiamld(DEFAULT_DK_SUYGIAMLD)
            .dkXacsyt(DEFAULT_DK_XACSYT)
            .dk3Ca(DEFAULT_DK_3_CA)
            .ngay1(DEFAULT_NGAY_1)
            .tenCon(DEFAULT_TEN_CON)
            .stt(DEFAULT_STT)
            .maQl(DEFAULT_MA_QL)
            .maTinh(DEFAULT_MA_TINH)
            .loaidc(DEFAULT_LOAIDC)
            .lydodc(DEFAULT_LYDODC)
            .ghichudc(DEFAULT_GHICHUDC)
            .loaiBenh(DEFAULT_LOAI_BENH)
            .tuyenBv(DEFAULT_TUYEN_BV)
            .lbqhs(DEFAULT_LBQHS)
            .lbqml(DEFAULT_LBQML)
            .troCap(DEFAULT_TRO_CAP)
            .troCapBh(DEFAULT_TRO_CAP_BH)
            .dk3(DEFAULT_DK_3)
            .dk4(DEFAULT_DK_4)
            .tyleh(DEFAULT_TYLEH)
            .ngayNuoi(DEFAULT_NGAY_NUOI)
            .soNgayCd(DEFAULT_SO_NGAY_CD)
            .ngayNghiList(DEFAULT_NGAY_NGHI_LIST)
            .vssid(DEFAULT_VSSID)
            .tuNgayBs(DEFAULT_TU_NGAY_BS)
            .denNgayBs(DEFAULT_DEN_NGAY_BS)
            .chk1(DEFAULT_CHK_1)
            .chk2(DEFAULT_CHK_2)
            .chk3(DEFAULT_CHK_3);
        return ctHuong;
    }

    @Before
    public void initTest() {
        ctHuongSearchRepository.deleteAll();
        ctHuong = createEntity(em);
    }

    @Test
    @Transactional
    public void createCtHuong() throws Exception {
        int databaseSizeBeforeCreate = ctHuongRepository.findAll().size();

        // Create the CtHuong
        restCtHuongMockMvc.perform(post("/api/ct-huongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ctHuong)))
            .andExpect(status().isCreated());

        // Validate the CtHuong in the database
        List<CtHuong> ctHuongList = ctHuongRepository.findAll();
        assertThat(ctHuongList).hasSize(databaseSizeBeforeCreate + 1);
        CtHuong testCtHuong = ctHuongList.get(ctHuongList.size() - 1);
        assertThat(testCtHuong.getKeysl()).isEqualTo(DEFAULT_KEYSL);
        assertThat(testCtHuong.getKeyYp()).isEqualTo(DEFAULT_KEY_YP);
        assertThat(testCtHuong.getMaDv()).isEqualTo(DEFAULT_MA_DV);
        assertThat(testCtHuong.getSoBhxh()).isEqualTo(DEFAULT_SO_BHXH);
        assertThat(testCtHuong.getMaNv()).isEqualTo(DEFAULT_MA_NV);
        assertThat(testCtHuong.getHoTen()).isEqualTo(DEFAULT_HO_TEN);
        assertThat(testCtHuong.getMucLuong()).isEqualTo(DEFAULT_MUC_LUONG);
        assertThat(testCtHuong.getNgayDuyet()).isEqualTo(DEFAULT_NGAY_DUYET);
        assertThat(testCtHuong.getNguoiDuyet()).isEqualTo(DEFAULT_NGUOI_DUYET);
        assertThat(testCtHuong.getLoaiDt()).isEqualTo(DEFAULT_LOAI_DT);
        assertThat(testCtHuong.getMaNt()).isEqualTo(DEFAULT_MA_NT);
        assertThat(testCtHuong.getMlTt()).isEqualTo(DEFAULT_ML_TT);
        assertThat(testCtHuong.getMaCd()).isEqualTo(DEFAULT_MA_CD);
        assertThat(testCtHuong.getMaNh()).isEqualTo(DEFAULT_MA_NH);
        assertThat(testCtHuong.getTuNgay()).isEqualTo(DEFAULT_TU_NGAY);
        assertThat(testCtHuong.getDenNgay()).isEqualTo(DEFAULT_DEN_NGAY);
        assertThat(testCtHuong.getTuNgayH()).isEqualTo(DEFAULT_TU_NGAY_H);
        assertThat(testCtHuong.getDenNgayH()).isEqualTo(DEFAULT_DEN_NGAY_H);
        assertThat(testCtHuong.getSoNgay()).isEqualTo(DEFAULT_SO_NGAY);
        assertThat(testCtHuong.getSoNgayH()).isEqualTo(DEFAULT_SO_NGAY_H);
        assertThat(testCtHuong.getSoNgayLk()).isEqualTo(DEFAULT_SO_NGAY_LK);
        assertThat(testCtHuong.getSoTien()).isEqualTo(DEFAULT_SO_TIEN);
        assertThat(testCtHuong.getSoTienH()).isEqualTo(DEFAULT_SO_TIEN_H);
        assertThat(testCtHuong.getSoNamBh()).isEqualTo(DEFAULT_SO_NAM_BH);
        assertThat(testCtHuong.getSoThangBh()).isEqualTo(DEFAULT_SO_THANG_BH);
        assertThat(testCtHuong.getSoNgayLkdv()).isEqualTo(DEFAULT_SO_NGAY_LKDV);
        assertThat(testCtHuong.getGhiChu()).isEqualTo(DEFAULT_GHI_CHU);
        assertThat(testCtHuong.getDk1()).isEqualTo(DEFAULT_DK_1);
        assertThat(testCtHuong.getDk2()).isEqualTo(DEFAULT_DK_2);
        assertThat(testCtHuong.getDk5()).isEqualTo(DEFAULT_DK_5);
        assertThat(testCtHuong.getDk6()).isEqualTo(DEFAULT_DK_6);
        assertThat(testCtHuong.getTuoiCon()).isEqualTo(DEFAULT_TUOI_CON);
        assertThat(testCtHuong.getSttCon()).isEqualTo(DEFAULT_STT_CON);
        assertThat(testCtHuong.getGioiTinh()).isEqualTo(DEFAULT_GIOI_TINH);
        assertThat(testCtHuong.getMaQt()).isEqualTo(DEFAULT_MA_QT);
        assertThat(testCtHuong.getDkBenhDai()).isEqualTo(DEFAULT_DK_BENH_DAI);
        assertThat(testCtHuong.getDkPhauthuat()).isEqualTo(DEFAULT_DK_PHAUTHUAT);
        assertThat(testCtHuong.getDkNghionha()).isEqualTo(DEFAULT_DK_NGHIONHA);
        assertThat(testCtHuong.getDkSuygiamld()).isEqualTo(DEFAULT_DK_SUYGIAMLD);
        assertThat(testCtHuong.getDkXacsyt()).isEqualTo(DEFAULT_DK_XACSYT);
        assertThat(testCtHuong.getDk3Ca()).isEqualTo(DEFAULT_DK_3_CA);
        assertThat(testCtHuong.getNgay1()).isEqualTo(DEFAULT_NGAY_1);
        assertThat(testCtHuong.getTenCon()).isEqualTo(DEFAULT_TEN_CON);
        assertThat(testCtHuong.getStt()).isEqualTo(DEFAULT_STT);
        assertThat(testCtHuong.getMaQl()).isEqualTo(DEFAULT_MA_QL);
        assertThat(testCtHuong.getMaTinh()).isEqualTo(DEFAULT_MA_TINH);
        assertThat(testCtHuong.getLoaidc()).isEqualTo(DEFAULT_LOAIDC);
        assertThat(testCtHuong.getLydodc()).isEqualTo(DEFAULT_LYDODC);
        assertThat(testCtHuong.getGhichudc()).isEqualTo(DEFAULT_GHICHUDC);
        assertThat(testCtHuong.getLoaiBenh()).isEqualTo(DEFAULT_LOAI_BENH);
        assertThat(testCtHuong.getTuyenBv()).isEqualTo(DEFAULT_TUYEN_BV);
        assertThat(testCtHuong.getLbqhs()).isEqualTo(DEFAULT_LBQHS);
        assertThat(testCtHuong.getLbqml()).isEqualTo(DEFAULT_LBQML);
        assertThat(testCtHuong.getTroCap()).isEqualTo(DEFAULT_TRO_CAP);
        assertThat(testCtHuong.getTroCapBh()).isEqualTo(DEFAULT_TRO_CAP_BH);
        assertThat(testCtHuong.getDk3()).isEqualTo(DEFAULT_DK_3);
        assertThat(testCtHuong.getDk4()).isEqualTo(DEFAULT_DK_4);
        assertThat(testCtHuong.getTyleh()).isEqualTo(DEFAULT_TYLEH);
        assertThat(testCtHuong.getNgayNuoi()).isEqualTo(DEFAULT_NGAY_NUOI);
        assertThat(testCtHuong.getSoNgayCd()).isEqualTo(DEFAULT_SO_NGAY_CD);
        assertThat(testCtHuong.getNgayNghiList()).isEqualTo(DEFAULT_NGAY_NGHI_LIST);
        assertThat(testCtHuong.getVssid()).isEqualTo(DEFAULT_VSSID);
        assertThat(testCtHuong.getTuNgayBs()).isEqualTo(DEFAULT_TU_NGAY_BS);
        assertThat(testCtHuong.getDenNgayBs()).isEqualTo(DEFAULT_DEN_NGAY_BS);
        assertThat(testCtHuong.getChk1()).isEqualTo(DEFAULT_CHK_1);
        assertThat(testCtHuong.getChk2()).isEqualTo(DEFAULT_CHK_2);
        assertThat(testCtHuong.getChk3()).isEqualTo(DEFAULT_CHK_3);

        // Validate the CtHuong in Elasticsearch
        CtHuong ctHuongEs = ctHuongSearchRepository.findOne(testCtHuong.getId());
        assertThat(ctHuongEs).isEqualToComparingFieldByField(testCtHuong);
    }

    @Test
    @Transactional
    public void createCtHuongWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ctHuongRepository.findAll().size();

        // Create the CtHuong with an existing ID
        ctHuong.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCtHuongMockMvc.perform(post("/api/ct-huongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ctHuong)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<CtHuong> ctHuongList = ctHuongRepository.findAll();
        assertThat(ctHuongList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCtHuongs() throws Exception {
        // Initialize the database
        ctHuongRepository.saveAndFlush(ctHuong);

        // Get all the ctHuongList
        restCtHuongMockMvc.perform(get("/api/ct-huongs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ctHuong.getId().intValue())))
            .andExpect(jsonPath("$.[*].keysl").value(hasItem(DEFAULT_KEYSL.toString())))
            .andExpect(jsonPath("$.[*].keyYp").value(hasItem(DEFAULT_KEY_YP.toString())))
            .andExpect(jsonPath("$.[*].maDv").value(hasItem(DEFAULT_MA_DV.toString())))
            .andExpect(jsonPath("$.[*].soBhxh").value(hasItem(DEFAULT_SO_BHXH.toString())))
            .andExpect(jsonPath("$.[*].maNv").value(hasItem(DEFAULT_MA_NV.toString())))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN.toString())))
            .andExpect(jsonPath("$.[*].mucLuong").value(hasItem(DEFAULT_MUC_LUONG.doubleValue())))
            .andExpect(jsonPath("$.[*].ngayDuyet").value(hasItem(sameInstant(DEFAULT_NGAY_DUYET))))
            .andExpect(jsonPath("$.[*].nguoiDuyet").value(hasItem(DEFAULT_NGUOI_DUYET.toString())))
            .andExpect(jsonPath("$.[*].loaiDt").value(hasItem(DEFAULT_LOAI_DT.toString())))
            .andExpect(jsonPath("$.[*].maNt").value(hasItem(DEFAULT_MA_NT.toString())))
            .andExpect(jsonPath("$.[*].mlTt").value(hasItem(DEFAULT_ML_TT.doubleValue())))
            .andExpect(jsonPath("$.[*].maCd").value(hasItem(DEFAULT_MA_CD.toString())))
            .andExpect(jsonPath("$.[*].maNh").value(hasItem(DEFAULT_MA_NH.toString())))
            .andExpect(jsonPath("$.[*].tuNgay").value(hasItem(sameInstant(DEFAULT_TU_NGAY))))
            .andExpect(jsonPath("$.[*].denNgay").value(hasItem(sameInstant(DEFAULT_DEN_NGAY))))
            .andExpect(jsonPath("$.[*].tuNgayH").value(hasItem(sameInstant(DEFAULT_TU_NGAY_H))))
            .andExpect(jsonPath("$.[*].denNgayH").value(hasItem(sameInstant(DEFAULT_DEN_NGAY_H))))
            .andExpect(jsonPath("$.[*].soNgay").value(hasItem(DEFAULT_SO_NGAY)))
            .andExpect(jsonPath("$.[*].soNgayH").value(hasItem(DEFAULT_SO_NGAY_H)))
            .andExpect(jsonPath("$.[*].soNgayLk").value(hasItem(DEFAULT_SO_NGAY_LK)))
            .andExpect(jsonPath("$.[*].soTien").value(hasItem(DEFAULT_SO_TIEN.doubleValue())))
            .andExpect(jsonPath("$.[*].soTienH").value(hasItem(DEFAULT_SO_TIEN_H.doubleValue())))
            .andExpect(jsonPath("$.[*].soNamBh").value(hasItem(DEFAULT_SO_NAM_BH)))
            .andExpect(jsonPath("$.[*].soThangBh").value(hasItem(DEFAULT_SO_THANG_BH)))
            .andExpect(jsonPath("$.[*].soNgayLkdv").value(hasItem(DEFAULT_SO_NGAY_LKDV)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].dk1").value(hasItem(DEFAULT_DK_1)))
            .andExpect(jsonPath("$.[*].dk2").value(hasItem(DEFAULT_DK_2)))
            .andExpect(jsonPath("$.[*].dk5").value(hasItem(DEFAULT_DK_5)))
            .andExpect(jsonPath("$.[*].dk6").value(hasItem(DEFAULT_DK_6)))
            .andExpect(jsonPath("$.[*].tuoiCon").value(hasItem(DEFAULT_TUOI_CON)))
            .andExpect(jsonPath("$.[*].sttCon").value(hasItem(DEFAULT_STT_CON)))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH)))
            .andExpect(jsonPath("$.[*].maQt").value(hasItem(DEFAULT_MA_QT.toString())))
            .andExpect(jsonPath("$.[*].dkBenhDai").value(hasItem(DEFAULT_DK_BENH_DAI.intValue())))
            .andExpect(jsonPath("$.[*].dkPhauthuat").value(hasItem(DEFAULT_DK_PHAUTHUAT)))
            .andExpect(jsonPath("$.[*].dkNghionha").value(hasItem(DEFAULT_DK_NGHIONHA)))
            .andExpect(jsonPath("$.[*].dkSuygiamld").value(hasItem(DEFAULT_DK_SUYGIAMLD)))
            .andExpect(jsonPath("$.[*].dkXacsyt").value(hasItem(DEFAULT_DK_XACSYT)))
            .andExpect(jsonPath("$.[*].dk3Ca").value(hasItem(DEFAULT_DK_3_CA)))
            .andExpect(jsonPath("$.[*].ngay1").value(hasItem(sameInstant(DEFAULT_NGAY_1))))
            .andExpect(jsonPath("$.[*].tenCon").value(hasItem(DEFAULT_TEN_CON.toString())))
            .andExpect(jsonPath("$.[*].stt").value(hasItem(DEFAULT_STT)))
            .andExpect(jsonPath("$.[*].maQl").value(hasItem(DEFAULT_MA_QL.toString())))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(DEFAULT_MA_TINH.toString())))
            .andExpect(jsonPath("$.[*].loaidc").value(hasItem(DEFAULT_LOAIDC)))
            .andExpect(jsonPath("$.[*].lydodc").value(hasItem(DEFAULT_LYDODC.toString())))
            .andExpect(jsonPath("$.[*].ghichudc").value(hasItem(DEFAULT_GHICHUDC.toString())))
            .andExpect(jsonPath("$.[*].loaiBenh").value(hasItem(DEFAULT_LOAI_BENH.toString())))
            .andExpect(jsonPath("$.[*].tuyenBv").value(hasItem(DEFAULT_TUYEN_BV.toString())))
            .andExpect(jsonPath("$.[*].lbqhs").value(hasItem(DEFAULT_LBQHS.doubleValue())))
            .andExpect(jsonPath("$.[*].lbqml").value(hasItem(DEFAULT_LBQML.doubleValue())))
            .andExpect(jsonPath("$.[*].troCap").value(hasItem(DEFAULT_TRO_CAP.doubleValue())))
            .andExpect(jsonPath("$.[*].troCapBh").value(hasItem(DEFAULT_TRO_CAP_BH.doubleValue())))
            .andExpect(jsonPath("$.[*].dk3").value(hasItem(DEFAULT_DK_3)))
            .andExpect(jsonPath("$.[*].dk4").value(hasItem(DEFAULT_DK_4)))
            .andExpect(jsonPath("$.[*].tyleh").value(hasItem(DEFAULT_TYLEH.doubleValue())))
            .andExpect(jsonPath("$.[*].ngayNuoi").value(hasItem(sameInstant(DEFAULT_NGAY_NUOI))))
            .andExpect(jsonPath("$.[*].soNgayCd").value(hasItem(DEFAULT_SO_NGAY_CD)))
            .andExpect(jsonPath("$.[*].ngayNghiList").value(hasItem(DEFAULT_NGAY_NGHI_LIST.toString())))
            .andExpect(jsonPath("$.[*].vssid").value(hasItem(DEFAULT_VSSID.toString())))
            .andExpect(jsonPath("$.[*].tuNgayBs").value(hasItem(sameInstant(DEFAULT_TU_NGAY_BS))))
            .andExpect(jsonPath("$.[*].denNgayBs").value(hasItem(sameInstant(DEFAULT_DEN_NGAY_BS))))
            .andExpect(jsonPath("$.[*].chk1").value(hasItem(DEFAULT_CHK_1)))
            .andExpect(jsonPath("$.[*].chk2").value(hasItem(DEFAULT_CHK_2)))
            .andExpect(jsonPath("$.[*].chk3").value(hasItem(DEFAULT_CHK_3)));
    }

    @Test
    @Transactional
    public void getCtHuong() throws Exception {
        // Initialize the database
        ctHuongRepository.saveAndFlush(ctHuong);

        // Get the ctHuong
        restCtHuongMockMvc.perform(get("/api/ct-huongs/{id}", ctHuong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ctHuong.getId().intValue()))
            .andExpect(jsonPath("$.keysl").value(DEFAULT_KEYSL.toString()))
            .andExpect(jsonPath("$.keyYp").value(DEFAULT_KEY_YP.toString()))
            .andExpect(jsonPath("$.maDv").value(DEFAULT_MA_DV.toString()))
            .andExpect(jsonPath("$.soBhxh").value(DEFAULT_SO_BHXH.toString()))
            .andExpect(jsonPath("$.maNv").value(DEFAULT_MA_NV.toString()))
            .andExpect(jsonPath("$.hoTen").value(DEFAULT_HO_TEN.toString()))
            .andExpect(jsonPath("$.mucLuong").value(DEFAULT_MUC_LUONG.doubleValue()))
            .andExpect(jsonPath("$.ngayDuyet").value(sameInstant(DEFAULT_NGAY_DUYET)))
            .andExpect(jsonPath("$.nguoiDuyet").value(DEFAULT_NGUOI_DUYET.toString()))
            .andExpect(jsonPath("$.loaiDt").value(DEFAULT_LOAI_DT.toString()))
            .andExpect(jsonPath("$.maNt").value(DEFAULT_MA_NT.toString()))
            .andExpect(jsonPath("$.mlTt").value(DEFAULT_ML_TT.doubleValue()))
            .andExpect(jsonPath("$.maCd").value(DEFAULT_MA_CD.toString()))
            .andExpect(jsonPath("$.maNh").value(DEFAULT_MA_NH.toString()))
            .andExpect(jsonPath("$.tuNgay").value(sameInstant(DEFAULT_TU_NGAY)))
            .andExpect(jsonPath("$.denNgay").value(sameInstant(DEFAULT_DEN_NGAY)))
            .andExpect(jsonPath("$.tuNgayH").value(sameInstant(DEFAULT_TU_NGAY_H)))
            .andExpect(jsonPath("$.denNgayH").value(sameInstant(DEFAULT_DEN_NGAY_H)))
            .andExpect(jsonPath("$.soNgay").value(DEFAULT_SO_NGAY))
            .andExpect(jsonPath("$.soNgayH").value(DEFAULT_SO_NGAY_H))
            .andExpect(jsonPath("$.soNgayLk").value(DEFAULT_SO_NGAY_LK))
            .andExpect(jsonPath("$.soTien").value(DEFAULT_SO_TIEN.doubleValue()))
            .andExpect(jsonPath("$.soTienH").value(DEFAULT_SO_TIEN_H.doubleValue()))
            .andExpect(jsonPath("$.soNamBh").value(DEFAULT_SO_NAM_BH))
            .andExpect(jsonPath("$.soThangBh").value(DEFAULT_SO_THANG_BH))
            .andExpect(jsonPath("$.soNgayLkdv").value(DEFAULT_SO_NGAY_LKDV))
            .andExpect(jsonPath("$.ghiChu").value(DEFAULT_GHI_CHU.toString()))
            .andExpect(jsonPath("$.dk1").value(DEFAULT_DK_1))
            .andExpect(jsonPath("$.dk2").value(DEFAULT_DK_2))
            .andExpect(jsonPath("$.dk5").value(DEFAULT_DK_5))
            .andExpect(jsonPath("$.dk6").value(DEFAULT_DK_6))
            .andExpect(jsonPath("$.tuoiCon").value(DEFAULT_TUOI_CON))
            .andExpect(jsonPath("$.sttCon").value(DEFAULT_STT_CON))
            .andExpect(jsonPath("$.gioiTinh").value(DEFAULT_GIOI_TINH))
            .andExpect(jsonPath("$.maQt").value(DEFAULT_MA_QT.toString()))
            .andExpect(jsonPath("$.dkBenhDai").value(DEFAULT_DK_BENH_DAI.intValue()))
            .andExpect(jsonPath("$.dkPhauthuat").value(DEFAULT_DK_PHAUTHUAT))
            .andExpect(jsonPath("$.dkNghionha").value(DEFAULT_DK_NGHIONHA))
            .andExpect(jsonPath("$.dkSuygiamld").value(DEFAULT_DK_SUYGIAMLD))
            .andExpect(jsonPath("$.dkXacsyt").value(DEFAULT_DK_XACSYT))
            .andExpect(jsonPath("$.dk3Ca").value(DEFAULT_DK_3_CA))
            .andExpect(jsonPath("$.ngay1").value(sameInstant(DEFAULT_NGAY_1)))
            .andExpect(jsonPath("$.tenCon").value(DEFAULT_TEN_CON.toString()))
            .andExpect(jsonPath("$.stt").value(DEFAULT_STT))
            .andExpect(jsonPath("$.maQl").value(DEFAULT_MA_QL.toString()))
            .andExpect(jsonPath("$.maTinh").value(DEFAULT_MA_TINH.toString()))
            .andExpect(jsonPath("$.loaidc").value(DEFAULT_LOAIDC))
            .andExpect(jsonPath("$.lydodc").value(DEFAULT_LYDODC.toString()))
            .andExpect(jsonPath("$.ghichudc").value(DEFAULT_GHICHUDC.toString()))
            .andExpect(jsonPath("$.loaiBenh").value(DEFAULT_LOAI_BENH.toString()))
            .andExpect(jsonPath("$.tuyenBv").value(DEFAULT_TUYEN_BV.toString()))
            .andExpect(jsonPath("$.lbqhs").value(DEFAULT_LBQHS.doubleValue()))
            .andExpect(jsonPath("$.lbqml").value(DEFAULT_LBQML.doubleValue()))
            .andExpect(jsonPath("$.troCap").value(DEFAULT_TRO_CAP.doubleValue()))
            .andExpect(jsonPath("$.troCapBh").value(DEFAULT_TRO_CAP_BH.doubleValue()))
            .andExpect(jsonPath("$.dk3").value(DEFAULT_DK_3))
            .andExpect(jsonPath("$.dk4").value(DEFAULT_DK_4))
            .andExpect(jsonPath("$.tyleh").value(DEFAULT_TYLEH.doubleValue()))
            .andExpect(jsonPath("$.ngayNuoi").value(sameInstant(DEFAULT_NGAY_NUOI)))
            .andExpect(jsonPath("$.soNgayCd").value(DEFAULT_SO_NGAY_CD))
            .andExpect(jsonPath("$.ngayNghiList").value(DEFAULT_NGAY_NGHI_LIST.toString()))
            .andExpect(jsonPath("$.vssid").value(DEFAULT_VSSID.toString()))
            .andExpect(jsonPath("$.tuNgayBs").value(sameInstant(DEFAULT_TU_NGAY_BS)))
            .andExpect(jsonPath("$.denNgayBs").value(sameInstant(DEFAULT_DEN_NGAY_BS)))
            .andExpect(jsonPath("$.chk1").value(DEFAULT_CHK_1))
            .andExpect(jsonPath("$.chk2").value(DEFAULT_CHK_2))
            .andExpect(jsonPath("$.chk3").value(DEFAULT_CHK_3));
    }

    @Test
    @Transactional
    public void getNonExistingCtHuong() throws Exception {
        // Get the ctHuong
        restCtHuongMockMvc.perform(get("/api/ct-huongs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCtHuong() throws Exception {
        // Initialize the database
        ctHuongService.save(ctHuong);

        int databaseSizeBeforeUpdate = ctHuongRepository.findAll().size();

        // Update the ctHuong
        CtHuong updatedCtHuong = ctHuongRepository.findOne(ctHuong.getId());
        updatedCtHuong
            .keysl(UPDATED_KEYSL)
            .keyYp(UPDATED_KEY_YP)
            .maDv(UPDATED_MA_DV)
            .soBhxh(UPDATED_SO_BHXH)
            .maNv(UPDATED_MA_NV)
            .hoTen(UPDATED_HO_TEN)
            .mucLuong(UPDATED_MUC_LUONG)
            .ngayDuyet(UPDATED_NGAY_DUYET)
            .nguoiDuyet(UPDATED_NGUOI_DUYET)
            .loaiDt(UPDATED_LOAI_DT)
            .maNt(UPDATED_MA_NT)
            .mlTt(UPDATED_ML_TT)
            .maCd(UPDATED_MA_CD)
            .maNh(UPDATED_MA_NH)
            .tuNgay(UPDATED_TU_NGAY)
            .denNgay(UPDATED_DEN_NGAY)
            .tuNgayH(UPDATED_TU_NGAY_H)
            .denNgayH(UPDATED_DEN_NGAY_H)
            .soNgay(UPDATED_SO_NGAY)
            .soNgayH(UPDATED_SO_NGAY_H)
            .soNgayLk(UPDATED_SO_NGAY_LK)
            .soTien(UPDATED_SO_TIEN)
            .soTienH(UPDATED_SO_TIEN_H)
            .soNamBh(UPDATED_SO_NAM_BH)
            .soThangBh(UPDATED_SO_THANG_BH)
            .soNgayLkdv(UPDATED_SO_NGAY_LKDV)
            .ghiChu(UPDATED_GHI_CHU)
            .dk1(UPDATED_DK_1)
            .dk2(UPDATED_DK_2)
            .dk5(UPDATED_DK_5)
            .dk6(UPDATED_DK_6)
            .tuoiCon(UPDATED_TUOI_CON)
            .sttCon(UPDATED_STT_CON)
            .gioiTinh(UPDATED_GIOI_TINH)
            .maQt(UPDATED_MA_QT)
            .dkBenhDai(UPDATED_DK_BENH_DAI)
            .dkPhauthuat(UPDATED_DK_PHAUTHUAT)
            .dkNghionha(UPDATED_DK_NGHIONHA)
            .dkSuygiamld(UPDATED_DK_SUYGIAMLD)
            .dkXacsyt(UPDATED_DK_XACSYT)
            .dk3Ca(UPDATED_DK_3_CA)
            .ngay1(UPDATED_NGAY_1)
            .tenCon(UPDATED_TEN_CON)
            .stt(UPDATED_STT)
            .maQl(UPDATED_MA_QL)
            .maTinh(UPDATED_MA_TINH)
            .loaidc(UPDATED_LOAIDC)
            .lydodc(UPDATED_LYDODC)
            .ghichudc(UPDATED_GHICHUDC)
            .loaiBenh(UPDATED_LOAI_BENH)
            .tuyenBv(UPDATED_TUYEN_BV)
            .lbqhs(UPDATED_LBQHS)
            .lbqml(UPDATED_LBQML)
            .troCap(UPDATED_TRO_CAP)
            .troCapBh(UPDATED_TRO_CAP_BH)
            .dk3(UPDATED_DK_3)
            .dk4(UPDATED_DK_4)
            .tyleh(UPDATED_TYLEH)
            .ngayNuoi(UPDATED_NGAY_NUOI)
            .soNgayCd(UPDATED_SO_NGAY_CD)
            .ngayNghiList(UPDATED_NGAY_NGHI_LIST)
            .vssid(UPDATED_VSSID)
            .tuNgayBs(UPDATED_TU_NGAY_BS)
            .denNgayBs(UPDATED_DEN_NGAY_BS)
            .chk1(UPDATED_CHK_1)
            .chk2(UPDATED_CHK_2)
            .chk3(UPDATED_CHK_3);

        restCtHuongMockMvc.perform(put("/api/ct-huongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCtHuong)))
            .andExpect(status().isOk());

        // Validate the CtHuong in the database
        List<CtHuong> ctHuongList = ctHuongRepository.findAll();
        assertThat(ctHuongList).hasSize(databaseSizeBeforeUpdate);
        CtHuong testCtHuong = ctHuongList.get(ctHuongList.size() - 1);
        assertThat(testCtHuong.getKeysl()).isEqualTo(UPDATED_KEYSL);
        assertThat(testCtHuong.getKeyYp()).isEqualTo(UPDATED_KEY_YP);
        assertThat(testCtHuong.getMaDv()).isEqualTo(UPDATED_MA_DV);
        assertThat(testCtHuong.getSoBhxh()).isEqualTo(UPDATED_SO_BHXH);
        assertThat(testCtHuong.getMaNv()).isEqualTo(UPDATED_MA_NV);
        assertThat(testCtHuong.getHoTen()).isEqualTo(UPDATED_HO_TEN);
        assertThat(testCtHuong.getMucLuong()).isEqualTo(UPDATED_MUC_LUONG);
        assertThat(testCtHuong.getNgayDuyet()).isEqualTo(UPDATED_NGAY_DUYET);
        assertThat(testCtHuong.getNguoiDuyet()).isEqualTo(UPDATED_NGUOI_DUYET);
        assertThat(testCtHuong.getLoaiDt()).isEqualTo(UPDATED_LOAI_DT);
        assertThat(testCtHuong.getMaNt()).isEqualTo(UPDATED_MA_NT);
        assertThat(testCtHuong.getMlTt()).isEqualTo(UPDATED_ML_TT);
        assertThat(testCtHuong.getMaCd()).isEqualTo(UPDATED_MA_CD);
        assertThat(testCtHuong.getMaNh()).isEqualTo(UPDATED_MA_NH);
        assertThat(testCtHuong.getTuNgay()).isEqualTo(UPDATED_TU_NGAY);
        assertThat(testCtHuong.getDenNgay()).isEqualTo(UPDATED_DEN_NGAY);
        assertThat(testCtHuong.getTuNgayH()).isEqualTo(UPDATED_TU_NGAY_H);
        assertThat(testCtHuong.getDenNgayH()).isEqualTo(UPDATED_DEN_NGAY_H);
        assertThat(testCtHuong.getSoNgay()).isEqualTo(UPDATED_SO_NGAY);
        assertThat(testCtHuong.getSoNgayH()).isEqualTo(UPDATED_SO_NGAY_H);
        assertThat(testCtHuong.getSoNgayLk()).isEqualTo(UPDATED_SO_NGAY_LK);
        assertThat(testCtHuong.getSoTien()).isEqualTo(UPDATED_SO_TIEN);
        assertThat(testCtHuong.getSoTienH()).isEqualTo(UPDATED_SO_TIEN_H);
        assertThat(testCtHuong.getSoNamBh()).isEqualTo(UPDATED_SO_NAM_BH);
        assertThat(testCtHuong.getSoThangBh()).isEqualTo(UPDATED_SO_THANG_BH);
        assertThat(testCtHuong.getSoNgayLkdv()).isEqualTo(UPDATED_SO_NGAY_LKDV);
        assertThat(testCtHuong.getGhiChu()).isEqualTo(UPDATED_GHI_CHU);
        assertThat(testCtHuong.getDk1()).isEqualTo(UPDATED_DK_1);
        assertThat(testCtHuong.getDk2()).isEqualTo(UPDATED_DK_2);
        assertThat(testCtHuong.getDk5()).isEqualTo(UPDATED_DK_5);
        assertThat(testCtHuong.getDk6()).isEqualTo(UPDATED_DK_6);
        assertThat(testCtHuong.getTuoiCon()).isEqualTo(UPDATED_TUOI_CON);
        assertThat(testCtHuong.getSttCon()).isEqualTo(UPDATED_STT_CON);
        assertThat(testCtHuong.getGioiTinh()).isEqualTo(UPDATED_GIOI_TINH);
        assertThat(testCtHuong.getMaQt()).isEqualTo(UPDATED_MA_QT);
        assertThat(testCtHuong.getDkBenhDai()).isEqualTo(UPDATED_DK_BENH_DAI);
        assertThat(testCtHuong.getDkPhauthuat()).isEqualTo(UPDATED_DK_PHAUTHUAT);
        assertThat(testCtHuong.getDkNghionha()).isEqualTo(UPDATED_DK_NGHIONHA);
        assertThat(testCtHuong.getDkSuygiamld()).isEqualTo(UPDATED_DK_SUYGIAMLD);
        assertThat(testCtHuong.getDkXacsyt()).isEqualTo(UPDATED_DK_XACSYT);
        assertThat(testCtHuong.getDk3Ca()).isEqualTo(UPDATED_DK_3_CA);
        assertThat(testCtHuong.getNgay1()).isEqualTo(UPDATED_NGAY_1);
        assertThat(testCtHuong.getTenCon()).isEqualTo(UPDATED_TEN_CON);
        assertThat(testCtHuong.getStt()).isEqualTo(UPDATED_STT);
        assertThat(testCtHuong.getMaQl()).isEqualTo(UPDATED_MA_QL);
        assertThat(testCtHuong.getMaTinh()).isEqualTo(UPDATED_MA_TINH);
        assertThat(testCtHuong.getLoaidc()).isEqualTo(UPDATED_LOAIDC);
        assertThat(testCtHuong.getLydodc()).isEqualTo(UPDATED_LYDODC);
        assertThat(testCtHuong.getGhichudc()).isEqualTo(UPDATED_GHICHUDC);
        assertThat(testCtHuong.getLoaiBenh()).isEqualTo(UPDATED_LOAI_BENH);
        assertThat(testCtHuong.getTuyenBv()).isEqualTo(UPDATED_TUYEN_BV);
        assertThat(testCtHuong.getLbqhs()).isEqualTo(UPDATED_LBQHS);
        assertThat(testCtHuong.getLbqml()).isEqualTo(UPDATED_LBQML);
        assertThat(testCtHuong.getTroCap()).isEqualTo(UPDATED_TRO_CAP);
        assertThat(testCtHuong.getTroCapBh()).isEqualTo(UPDATED_TRO_CAP_BH);
        assertThat(testCtHuong.getDk3()).isEqualTo(UPDATED_DK_3);
        assertThat(testCtHuong.getDk4()).isEqualTo(UPDATED_DK_4);
        assertThat(testCtHuong.getTyleh()).isEqualTo(UPDATED_TYLEH);
        assertThat(testCtHuong.getNgayNuoi()).isEqualTo(UPDATED_NGAY_NUOI);
        assertThat(testCtHuong.getSoNgayCd()).isEqualTo(UPDATED_SO_NGAY_CD);
        assertThat(testCtHuong.getNgayNghiList()).isEqualTo(UPDATED_NGAY_NGHI_LIST);
        assertThat(testCtHuong.getVssid()).isEqualTo(UPDATED_VSSID);
        assertThat(testCtHuong.getTuNgayBs()).isEqualTo(UPDATED_TU_NGAY_BS);
        assertThat(testCtHuong.getDenNgayBs()).isEqualTo(UPDATED_DEN_NGAY_BS);
        assertThat(testCtHuong.getChk1()).isEqualTo(UPDATED_CHK_1);
        assertThat(testCtHuong.getChk2()).isEqualTo(UPDATED_CHK_2);
        assertThat(testCtHuong.getChk3()).isEqualTo(UPDATED_CHK_3);

        // Validate the CtHuong in Elasticsearch
        CtHuong ctHuongEs = ctHuongSearchRepository.findOne(testCtHuong.getId());
        assertThat(ctHuongEs).isEqualToComparingFieldByField(testCtHuong);
    }

    @Test
    @Transactional
    public void updateNonExistingCtHuong() throws Exception {
        int databaseSizeBeforeUpdate = ctHuongRepository.findAll().size();

        // Create the CtHuong

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCtHuongMockMvc.perform(put("/api/ct-huongs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ctHuong)))
            .andExpect(status().isCreated());

        // Validate the CtHuong in the database
        List<CtHuong> ctHuongList = ctHuongRepository.findAll();
        assertThat(ctHuongList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCtHuong() throws Exception {
        // Initialize the database
        ctHuongService.save(ctHuong);

        int databaseSizeBeforeDelete = ctHuongRepository.findAll().size();

        // Get the ctHuong
        restCtHuongMockMvc.perform(delete("/api/ct-huongs/{id}", ctHuong.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean ctHuongExistsInEs = ctHuongSearchRepository.exists(ctHuong.getId());
        assertThat(ctHuongExistsInEs).isFalse();

        // Validate the database is empty
        List<CtHuong> ctHuongList = ctHuongRepository.findAll();
        assertThat(ctHuongList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCtHuong() throws Exception {
        // Initialize the database
        ctHuongService.save(ctHuong);

        // Search the ctHuong
        restCtHuongMockMvc.perform(get("/api/_search/ct-huongs?query=id:" + ctHuong.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ctHuong.getId().intValue())))
            .andExpect(jsonPath("$.[*].keysl").value(hasItem(DEFAULT_KEYSL.toString())))
            .andExpect(jsonPath("$.[*].keyYp").value(hasItem(DEFAULT_KEY_YP.toString())))
            .andExpect(jsonPath("$.[*].maDv").value(hasItem(DEFAULT_MA_DV.toString())))
            .andExpect(jsonPath("$.[*].soBhxh").value(hasItem(DEFAULT_SO_BHXH.toString())))
            .andExpect(jsonPath("$.[*].maNv").value(hasItem(DEFAULT_MA_NV.toString())))
            .andExpect(jsonPath("$.[*].hoTen").value(hasItem(DEFAULT_HO_TEN.toString())))
            .andExpect(jsonPath("$.[*].mucLuong").value(hasItem(DEFAULT_MUC_LUONG.doubleValue())))
            .andExpect(jsonPath("$.[*].ngayDuyet").value(hasItem(sameInstant(DEFAULT_NGAY_DUYET))))
            .andExpect(jsonPath("$.[*].nguoiDuyet").value(hasItem(DEFAULT_NGUOI_DUYET.toString())))
            .andExpect(jsonPath("$.[*].loaiDt").value(hasItem(DEFAULT_LOAI_DT.toString())))
            .andExpect(jsonPath("$.[*].maNt").value(hasItem(DEFAULT_MA_NT.toString())))
            .andExpect(jsonPath("$.[*].mlTt").value(hasItem(DEFAULT_ML_TT.doubleValue())))
            .andExpect(jsonPath("$.[*].maCd").value(hasItem(DEFAULT_MA_CD.toString())))
            .andExpect(jsonPath("$.[*].maNh").value(hasItem(DEFAULT_MA_NH.toString())))
            .andExpect(jsonPath("$.[*].tuNgay").value(hasItem(sameInstant(DEFAULT_TU_NGAY))))
            .andExpect(jsonPath("$.[*].denNgay").value(hasItem(sameInstant(DEFAULT_DEN_NGAY))))
            .andExpect(jsonPath("$.[*].tuNgayH").value(hasItem(sameInstant(DEFAULT_TU_NGAY_H))))
            .andExpect(jsonPath("$.[*].denNgayH").value(hasItem(sameInstant(DEFAULT_DEN_NGAY_H))))
            .andExpect(jsonPath("$.[*].soNgay").value(hasItem(DEFAULT_SO_NGAY)))
            .andExpect(jsonPath("$.[*].soNgayH").value(hasItem(DEFAULT_SO_NGAY_H)))
            .andExpect(jsonPath("$.[*].soNgayLk").value(hasItem(DEFAULT_SO_NGAY_LK)))
            .andExpect(jsonPath("$.[*].soTien").value(hasItem(DEFAULT_SO_TIEN.doubleValue())))
            .andExpect(jsonPath("$.[*].soTienH").value(hasItem(DEFAULT_SO_TIEN_H.doubleValue())))
            .andExpect(jsonPath("$.[*].soNamBh").value(hasItem(DEFAULT_SO_NAM_BH)))
            .andExpect(jsonPath("$.[*].soThangBh").value(hasItem(DEFAULT_SO_THANG_BH)))
            .andExpect(jsonPath("$.[*].soNgayLkdv").value(hasItem(DEFAULT_SO_NGAY_LKDV)))
            .andExpect(jsonPath("$.[*].ghiChu").value(hasItem(DEFAULT_GHI_CHU.toString())))
            .andExpect(jsonPath("$.[*].dk1").value(hasItem(DEFAULT_DK_1)))
            .andExpect(jsonPath("$.[*].dk2").value(hasItem(DEFAULT_DK_2)))
            .andExpect(jsonPath("$.[*].dk5").value(hasItem(DEFAULT_DK_5)))
            .andExpect(jsonPath("$.[*].dk6").value(hasItem(DEFAULT_DK_6)))
            .andExpect(jsonPath("$.[*].tuoiCon").value(hasItem(DEFAULT_TUOI_CON)))
            .andExpect(jsonPath("$.[*].sttCon").value(hasItem(DEFAULT_STT_CON)))
            .andExpect(jsonPath("$.[*].gioiTinh").value(hasItem(DEFAULT_GIOI_TINH)))
            .andExpect(jsonPath("$.[*].maQt").value(hasItem(DEFAULT_MA_QT.toString())))
            .andExpect(jsonPath("$.[*].dkBenhDai").value(hasItem(DEFAULT_DK_BENH_DAI.intValue())))
            .andExpect(jsonPath("$.[*].dkPhauthuat").value(hasItem(DEFAULT_DK_PHAUTHUAT)))
            .andExpect(jsonPath("$.[*].dkNghionha").value(hasItem(DEFAULT_DK_NGHIONHA)))
            .andExpect(jsonPath("$.[*].dkSuygiamld").value(hasItem(DEFAULT_DK_SUYGIAMLD)))
            .andExpect(jsonPath("$.[*].dkXacsyt").value(hasItem(DEFAULT_DK_XACSYT)))
            .andExpect(jsonPath("$.[*].dk3Ca").value(hasItem(DEFAULT_DK_3_CA)))
            .andExpect(jsonPath("$.[*].ngay1").value(hasItem(sameInstant(DEFAULT_NGAY_1))))
            .andExpect(jsonPath("$.[*].tenCon").value(hasItem(DEFAULT_TEN_CON.toString())))
            .andExpect(jsonPath("$.[*].stt").value(hasItem(DEFAULT_STT)))
            .andExpect(jsonPath("$.[*].maQl").value(hasItem(DEFAULT_MA_QL.toString())))
            .andExpect(jsonPath("$.[*].maTinh").value(hasItem(DEFAULT_MA_TINH.toString())))
            .andExpect(jsonPath("$.[*].loaidc").value(hasItem(DEFAULT_LOAIDC)))
            .andExpect(jsonPath("$.[*].lydodc").value(hasItem(DEFAULT_LYDODC.toString())))
            .andExpect(jsonPath("$.[*].ghichudc").value(hasItem(DEFAULT_GHICHUDC.toString())))
            .andExpect(jsonPath("$.[*].loaiBenh").value(hasItem(DEFAULT_LOAI_BENH.toString())))
            .andExpect(jsonPath("$.[*].tuyenBv").value(hasItem(DEFAULT_TUYEN_BV.toString())))
            .andExpect(jsonPath("$.[*].lbqhs").value(hasItem(DEFAULT_LBQHS.doubleValue())))
            .andExpect(jsonPath("$.[*].lbqml").value(hasItem(DEFAULT_LBQML.doubleValue())))
            .andExpect(jsonPath("$.[*].troCap").value(hasItem(DEFAULT_TRO_CAP.doubleValue())))
            .andExpect(jsonPath("$.[*].troCapBh").value(hasItem(DEFAULT_TRO_CAP_BH.doubleValue())))
            .andExpect(jsonPath("$.[*].dk3").value(hasItem(DEFAULT_DK_3)))
            .andExpect(jsonPath("$.[*].dk4").value(hasItem(DEFAULT_DK_4)))
            .andExpect(jsonPath("$.[*].tyleh").value(hasItem(DEFAULT_TYLEH.doubleValue())))
            .andExpect(jsonPath("$.[*].ngayNuoi").value(hasItem(sameInstant(DEFAULT_NGAY_NUOI))))
            .andExpect(jsonPath("$.[*].soNgayCd").value(hasItem(DEFAULT_SO_NGAY_CD)))
            .andExpect(jsonPath("$.[*].ngayNghiList").value(hasItem(DEFAULT_NGAY_NGHI_LIST.toString())))
            .andExpect(jsonPath("$.[*].vssid").value(hasItem(DEFAULT_VSSID.toString())))
            .andExpect(jsonPath("$.[*].tuNgayBs").value(hasItem(sameInstant(DEFAULT_TU_NGAY_BS))))
            .andExpect(jsonPath("$.[*].denNgayBs").value(hasItem(sameInstant(DEFAULT_DEN_NGAY_BS))))
            .andExpect(jsonPath("$.[*].chk1").value(hasItem(DEFAULT_CHK_1)))
            .andExpect(jsonPath("$.[*].chk2").value(hasItem(DEFAULT_CHK_2)))
            .andExpect(jsonPath("$.[*].chk3").value(hasItem(DEFAULT_CHK_3)));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CtHuong.class);
        CtHuong ctHuong1 = new CtHuong();
        ctHuong1.setId(1L);
        CtHuong ctHuong2 = new CtHuong();
        ctHuong2.setId(ctHuong1.getId());
        assertThat(ctHuong1).isEqualTo(ctHuong2);
        ctHuong2.setId(2L);
        assertThat(ctHuong1).isNotEqualTo(ctHuong2);
        ctHuong1.setId(null);
        assertThat(ctHuong1).isNotEqualTo(ctHuong2);
    }
}
