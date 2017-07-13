package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A CtHuong.
 */
@Entity
@Table(name = "ct_huong")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "cthuong")
public class CtHuong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "keysl")
    private String keysl;

    @Column(name = "key_yp")
    private String keyYp;

    @Column(name = "ma_dv")
    private String maDv;

    @Column(name = "so_bhxh")
    private String soBhxh;

    @Column(name = "ma_nv")
    private String maNv;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "muc_luong")
    private Float mucLuong;

    @Column(name = "ngay_duyet")
    private ZonedDateTime ngayDuyet;

    @Column(name = "nguoi_duyet")
    private String nguoiDuyet;

    @Column(name = "loai_dt")
    private String loaiDt;

    @Column(name = "ma_nt")
    private String maNt;

    @Column(name = "ml_tt")
    private Float mlTt;

    @Column(name = "ma_cd")
    private String maCd;

    @Column(name = "ma_nh")
    private String maNh;

    @Column(name = "tu_ngay")
    private ZonedDateTime tuNgay;

    @Column(name = "den_ngay")
    private ZonedDateTime denNgay;

    @Column(name = "tu_ngay_h")
    private ZonedDateTime tuNgayH;

    @Column(name = "den_ngay_h")
    private ZonedDateTime denNgayH;

    @Column(name = "so_ngay")
    private Integer soNgay;

    @Column(name = "so_ngay_h")
    private Integer soNgayH;

    @Column(name = "so_ngay_lk")
    private Integer soNgayLk;

    @Column(name = "so_tien")
    private Float soTien;

    @Column(name = "so_tien_h")
    private Float soTienH;

    @Column(name = "so_nam_bh")
    private Integer soNamBh;

    @Column(name = "so_thang_bh")
    private Integer soThangBh;

    @Column(name = "so_ngay_lkdv")
    private Integer soNgayLkdv;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "dk_1")
    private Integer dk1;

    @Column(name = "dk_2")
    private Integer dk2;

    @Column(name = "dk_5")
    private Integer dk5;

    @Column(name = "dk_6")
    private Integer dk6;

    @Column(name = "tuoi_con")
    private Integer tuoiCon;

    @Column(name = "stt_con")
    private Integer sttCon;

    @Column(name = "gioi_tinh")
    private Integer gioiTinh;

    @Column(name = "ma_qt")
    private String maQt;

    @Column(name = "dk_benh_dai")
    private Long dkBenhDai;

    @Column(name = "dk_phauthuat")
    private Integer dkPhauthuat;

    @Column(name = "dk_nghionha")
    private Integer dkNghionha;

    @Column(name = "dk_suygiamld")
    private Integer dkSuygiamld;

    @Column(name = "dk_xacsyt")
    private Integer dkXacsyt;

    @Column(name = "dk_3_ca")
    private Integer dk3Ca;

    @Column(name = "ngay_1")
    private ZonedDateTime ngay1;

    @Column(name = "ten_con")
    private String tenCon;

    @Column(name = "stt")
    private Integer stt;

    @Column(name = "ma_ql")
    private String maQl;

    @Column(name = "ma_tinh")
    private String maTinh;

    @Column(name = "loaidc")
    private Integer loaidc;

    @Column(name = "lydodc")
    private String lydodc;

    @Column(name = "ghichudc")
    private String ghichudc;

    @Column(name = "loai_benh")
    private String loaiBenh;

    @Column(name = "tuyen_bv")
    private String tuyenBv;

    @Column(name = "lbqhs")
    private Float lbqhs;

    @Column(name = "lbqml")
    private Float lbqml;

    @Column(name = "tro_cap")
    private Float troCap;

    @Column(name = "tro_cap_bh")
    private Float troCapBh;

    @Column(name = "dk_3")
    private Integer dk3;

    @Column(name = "dk_4")
    private Integer dk4;

    @Column(name = "tyleh")
    private Float tyleh;

    @Column(name = "ngay_nuoi")
    private ZonedDateTime ngayNuoi;

    @Column(name = "so_ngay_cd")
    private Integer soNgayCd;

    @Column(name = "ngay_nghi_list")
    private String ngayNghiList;

    @Column(name = "vssid")
    private String vssid;

    @Column(name = "tu_ngay_bs")
    private ZonedDateTime tuNgayBs;

    @Column(name = "den_ngay_bs")
    private ZonedDateTime denNgayBs;

    @Column(name = "chk_1")
    private Integer chk1;

    @Column(name = "chk_2")
    private Integer chk2;

    @Column(name = "chk_3")
    private Integer chk3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeysl() {
        return keysl;
    }

    public CtHuong keysl(String keysl) {
        this.keysl = keysl;
        return this;
    }

    public void setKeysl(String keysl) {
        this.keysl = keysl;
    }

    public String getKeyYp() {
        return keyYp;
    }

    public CtHuong keyYp(String keyYp) {
        this.keyYp = keyYp;
        return this;
    }

    public void setKeyYp(String keyYp) {
        this.keyYp = keyYp;
    }

    public String getMaDv() {
        return maDv;
    }

    public CtHuong maDv(String maDv) {
        this.maDv = maDv;
        return this;
    }

    public void setMaDv(String maDv) {
        this.maDv = maDv;
    }

    public String getSoBhxh() {
        return soBhxh;
    }

    public CtHuong soBhxh(String soBhxh) {
        this.soBhxh = soBhxh;
        return this;
    }

    public void setSoBhxh(String soBhxh) {
        this.soBhxh = soBhxh;
    }

    public String getMaNv() {
        return maNv;
    }

    public CtHuong maNv(String maNv) {
        this.maNv = maNv;
        return this;
    }

    public void setMaNv(String maNv) {
        this.maNv = maNv;
    }

    public String getHoTen() {
        return hoTen;
    }

    public CtHuong hoTen(String hoTen) {
        this.hoTen = hoTen;
        return this;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Float getMucLuong() {
        return mucLuong;
    }

    public CtHuong mucLuong(Float mucLuong) {
        this.mucLuong = mucLuong;
        return this;
    }

    public void setMucLuong(Float mucLuong) {
        this.mucLuong = mucLuong;
    }

    public ZonedDateTime getNgayDuyet() {
        return ngayDuyet;
    }

    public CtHuong ngayDuyet(ZonedDateTime ngayDuyet) {
        this.ngayDuyet = ngayDuyet;
        return this;
    }

    public void setNgayDuyet(ZonedDateTime ngayDuyet) {
        this.ngayDuyet = ngayDuyet;
    }

    public String getNguoiDuyet() {
        return nguoiDuyet;
    }

    public CtHuong nguoiDuyet(String nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
        return this;
    }

    public void setNguoiDuyet(String nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
    }

    public String getLoaiDt() {
        return loaiDt;
    }

    public CtHuong loaiDt(String loaiDt) {
        this.loaiDt = loaiDt;
        return this;
    }

    public void setLoaiDt(String loaiDt) {
        this.loaiDt = loaiDt;
    }

    public String getMaNt() {
        return maNt;
    }

    public CtHuong maNt(String maNt) {
        this.maNt = maNt;
        return this;
    }

    public void setMaNt(String maNt) {
        this.maNt = maNt;
    }

    public Float getMlTt() {
        return mlTt;
    }

    public CtHuong mlTt(Float mlTt) {
        this.mlTt = mlTt;
        return this;
    }

    public void setMlTt(Float mlTt) {
        this.mlTt = mlTt;
    }

    public String getMaCd() {
        return maCd;
    }

    public CtHuong maCd(String maCd) {
        this.maCd = maCd;
        return this;
    }

    public void setMaCd(String maCd) {
        this.maCd = maCd;
    }

    public String getMaNh() {
        return maNh;
    }

    public CtHuong maNh(String maNh) {
        this.maNh = maNh;
        return this;
    }

    public void setMaNh(String maNh) {
        this.maNh = maNh;
    }

    public ZonedDateTime getTuNgay() {
        return tuNgay;
    }

    public CtHuong tuNgay(ZonedDateTime tuNgay) {
        this.tuNgay = tuNgay;
        return this;
    }

    public void setTuNgay(ZonedDateTime tuNgay) {
        this.tuNgay = tuNgay;
    }

    public ZonedDateTime getDenNgay() {
        return denNgay;
    }

    public CtHuong denNgay(ZonedDateTime denNgay) {
        this.denNgay = denNgay;
        return this;
    }

    public void setDenNgay(ZonedDateTime denNgay) {
        this.denNgay = denNgay;
    }

    public ZonedDateTime getTuNgayH() {
        return tuNgayH;
    }

    public CtHuong tuNgayH(ZonedDateTime tuNgayH) {
        this.tuNgayH = tuNgayH;
        return this;
    }

    public void setTuNgayH(ZonedDateTime tuNgayH) {
        this.tuNgayH = tuNgayH;
    }

    public ZonedDateTime getDenNgayH() {
        return denNgayH;
    }

    public CtHuong denNgayH(ZonedDateTime denNgayH) {
        this.denNgayH = denNgayH;
        return this;
    }

    public void setDenNgayH(ZonedDateTime denNgayH) {
        this.denNgayH = denNgayH;
    }

    public Integer getSoNgay() {
        return soNgay;
    }

    public CtHuong soNgay(Integer soNgay) {
        this.soNgay = soNgay;
        return this;
    }

    public void setSoNgay(Integer soNgay) {
        this.soNgay = soNgay;
    }

    public Integer getSoNgayH() {
        return soNgayH;
    }

    public CtHuong soNgayH(Integer soNgayH) {
        this.soNgayH = soNgayH;
        return this;
    }

    public void setSoNgayH(Integer soNgayH) {
        this.soNgayH = soNgayH;
    }

    public Integer getSoNgayLk() {
        return soNgayLk;
    }

    public CtHuong soNgayLk(Integer soNgayLk) {
        this.soNgayLk = soNgayLk;
        return this;
    }

    public void setSoNgayLk(Integer soNgayLk) {
        this.soNgayLk = soNgayLk;
    }

    public Float getSoTien() {
        return soTien;
    }

    public CtHuong soTien(Float soTien) {
        this.soTien = soTien;
        return this;
    }

    public void setSoTien(Float soTien) {
        this.soTien = soTien;
    }

    public Float getSoTienH() {
        return soTienH;
    }

    public CtHuong soTienH(Float soTienH) {
        this.soTienH = soTienH;
        return this;
    }

    public void setSoTienH(Float soTienH) {
        this.soTienH = soTienH;
    }

    public Integer getSoNamBh() {
        return soNamBh;
    }

    public CtHuong soNamBh(Integer soNamBh) {
        this.soNamBh = soNamBh;
        return this;
    }

    public void setSoNamBh(Integer soNamBh) {
        this.soNamBh = soNamBh;
    }

    public Integer getSoThangBh() {
        return soThangBh;
    }

    public CtHuong soThangBh(Integer soThangBh) {
        this.soThangBh = soThangBh;
        return this;
    }

    public void setSoThangBh(Integer soThangBh) {
        this.soThangBh = soThangBh;
    }

    public Integer getSoNgayLkdv() {
        return soNgayLkdv;
    }

    public CtHuong soNgayLkdv(Integer soNgayLkdv) {
        this.soNgayLkdv = soNgayLkdv;
        return this;
    }

    public void setSoNgayLkdv(Integer soNgayLkdv) {
        this.soNgayLkdv = soNgayLkdv;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public CtHuong ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Integer getDk1() {
        return dk1;
    }

    public CtHuong dk1(Integer dk1) {
        this.dk1 = dk1;
        return this;
    }

    public void setDk1(Integer dk1) {
        this.dk1 = dk1;
    }

    public Integer getDk2() {
        return dk2;
    }

    public CtHuong dk2(Integer dk2) {
        this.dk2 = dk2;
        return this;
    }

    public void setDk2(Integer dk2) {
        this.dk2 = dk2;
    }

    public Integer getDk5() {
        return dk5;
    }

    public CtHuong dk5(Integer dk5) {
        this.dk5 = dk5;
        return this;
    }

    public void setDk5(Integer dk5) {
        this.dk5 = dk5;
    }

    public Integer getDk6() {
        return dk6;
    }

    public CtHuong dk6(Integer dk6) {
        this.dk6 = dk6;
        return this;
    }

    public void setDk6(Integer dk6) {
        this.dk6 = dk6;
    }

    public Integer getTuoiCon() {
        return tuoiCon;
    }

    public CtHuong tuoiCon(Integer tuoiCon) {
        this.tuoiCon = tuoiCon;
        return this;
    }

    public void setTuoiCon(Integer tuoiCon) {
        this.tuoiCon = tuoiCon;
    }

    public Integer getSttCon() {
        return sttCon;
    }

    public CtHuong sttCon(Integer sttCon) {
        this.sttCon = sttCon;
        return this;
    }

    public void setSttCon(Integer sttCon) {
        this.sttCon = sttCon;
    }

    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public CtHuong gioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMaQt() {
        return maQt;
    }

    public CtHuong maQt(String maQt) {
        this.maQt = maQt;
        return this;
    }

    public void setMaQt(String maQt) {
        this.maQt = maQt;
    }

    public Long getDkBenhDai() {
        return dkBenhDai;
    }

    public CtHuong dkBenhDai(Long dkBenhDai) {
        this.dkBenhDai = dkBenhDai;
        return this;
    }

    public void setDkBenhDai(Long dkBenhDai) {
        this.dkBenhDai = dkBenhDai;
    }

    public Integer getDkPhauthuat() {
        return dkPhauthuat;
    }

    public CtHuong dkPhauthuat(Integer dkPhauthuat) {
        this.dkPhauthuat = dkPhauthuat;
        return this;
    }

    public void setDkPhauthuat(Integer dkPhauthuat) {
        this.dkPhauthuat = dkPhauthuat;
    }

    public Integer getDkNghionha() {
        return dkNghionha;
    }

    public CtHuong dkNghionha(Integer dkNghionha) {
        this.dkNghionha = dkNghionha;
        return this;
    }

    public void setDkNghionha(Integer dkNghionha) {
        this.dkNghionha = dkNghionha;
    }

    public Integer getDkSuygiamld() {
        return dkSuygiamld;
    }

    public CtHuong dkSuygiamld(Integer dkSuygiamld) {
        this.dkSuygiamld = dkSuygiamld;
        return this;
    }

    public void setDkSuygiamld(Integer dkSuygiamld) {
        this.dkSuygiamld = dkSuygiamld;
    }

    public Integer getDkXacsyt() {
        return dkXacsyt;
    }

    public CtHuong dkXacsyt(Integer dkXacsyt) {
        this.dkXacsyt = dkXacsyt;
        return this;
    }

    public void setDkXacsyt(Integer dkXacsyt) {
        this.dkXacsyt = dkXacsyt;
    }

    public Integer getDk3Ca() {
        return dk3Ca;
    }

    public CtHuong dk3Ca(Integer dk3Ca) {
        this.dk3Ca = dk3Ca;
        return this;
    }

    public void setDk3Ca(Integer dk3Ca) {
        this.dk3Ca = dk3Ca;
    }

    public ZonedDateTime getNgay1() {
        return ngay1;
    }

    public CtHuong ngay1(ZonedDateTime ngay1) {
        this.ngay1 = ngay1;
        return this;
    }

    public void setNgay1(ZonedDateTime ngay1) {
        this.ngay1 = ngay1;
    }

    public String getTenCon() {
        return tenCon;
    }

    public CtHuong tenCon(String tenCon) {
        this.tenCon = tenCon;
        return this;
    }

    public void setTenCon(String tenCon) {
        this.tenCon = tenCon;
    }

    public Integer getStt() {
        return stt;
    }

    public CtHuong stt(Integer stt) {
        this.stt = stt;
        return this;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public String getMaQl() {
        return maQl;
    }

    public CtHuong maQl(String maQl) {
        this.maQl = maQl;
        return this;
    }

    public void setMaQl(String maQl) {
        this.maQl = maQl;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public CtHuong maTinh(String maTinh) {
        this.maTinh = maTinh;
        return this;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public Integer getLoaidc() {
        return loaidc;
    }

    public CtHuong loaidc(Integer loaidc) {
        this.loaidc = loaidc;
        return this;
    }

    public void setLoaidc(Integer loaidc) {
        this.loaidc = loaidc;
    }

    public String getLydodc() {
        return lydodc;
    }

    public CtHuong lydodc(String lydodc) {
        this.lydodc = lydodc;
        return this;
    }

    public void setLydodc(String lydodc) {
        this.lydodc = lydodc;
    }

    public String getGhichudc() {
        return ghichudc;
    }

    public CtHuong ghichudc(String ghichudc) {
        this.ghichudc = ghichudc;
        return this;
    }

    public void setGhichudc(String ghichudc) {
        this.ghichudc = ghichudc;
    }

    public String getLoaiBenh() {
        return loaiBenh;
    }

    public CtHuong loaiBenh(String loaiBenh) {
        this.loaiBenh = loaiBenh;
        return this;
    }

    public void setLoaiBenh(String loaiBenh) {
        this.loaiBenh = loaiBenh;
    }

    public String getTuyenBv() {
        return tuyenBv;
    }

    public CtHuong tuyenBv(String tuyenBv) {
        this.tuyenBv = tuyenBv;
        return this;
    }

    public void setTuyenBv(String tuyenBv) {
        this.tuyenBv = tuyenBv;
    }

    public Float getLbqhs() {
        return lbqhs;
    }

    public CtHuong lbqhs(Float lbqhs) {
        this.lbqhs = lbqhs;
        return this;
    }

    public void setLbqhs(Float lbqhs) {
        this.lbqhs = lbqhs;
    }

    public Float getLbqml() {
        return lbqml;
    }

    public CtHuong lbqml(Float lbqml) {
        this.lbqml = lbqml;
        return this;
    }

    public void setLbqml(Float lbqml) {
        this.lbqml = lbqml;
    }

    public Float getTroCap() {
        return troCap;
    }

    public CtHuong troCap(Float troCap) {
        this.troCap = troCap;
        return this;
    }

    public void setTroCap(Float troCap) {
        this.troCap = troCap;
    }

    public Float getTroCapBh() {
        return troCapBh;
    }

    public CtHuong troCapBh(Float troCapBh) {
        this.troCapBh = troCapBh;
        return this;
    }

    public void setTroCapBh(Float troCapBh) {
        this.troCapBh = troCapBh;
    }

    public Integer getDk3() {
        return dk3;
    }

    public CtHuong dk3(Integer dk3) {
        this.dk3 = dk3;
        return this;
    }

    public void setDk3(Integer dk3) {
        this.dk3 = dk3;
    }

    public Integer getDk4() {
        return dk4;
    }

    public CtHuong dk4(Integer dk4) {
        this.dk4 = dk4;
        return this;
    }

    public void setDk4(Integer dk4) {
        this.dk4 = dk4;
    }

    public Float getTyleh() {
        return tyleh;
    }

    public CtHuong tyleh(Float tyleh) {
        this.tyleh = tyleh;
        return this;
    }

    public void setTyleh(Float tyleh) {
        this.tyleh = tyleh;
    }

    public ZonedDateTime getNgayNuoi() {
        return ngayNuoi;
    }

    public CtHuong ngayNuoi(ZonedDateTime ngayNuoi) {
        this.ngayNuoi = ngayNuoi;
        return this;
    }

    public void setNgayNuoi(ZonedDateTime ngayNuoi) {
        this.ngayNuoi = ngayNuoi;
    }

    public Integer getSoNgayCd() {
        return soNgayCd;
    }

    public CtHuong soNgayCd(Integer soNgayCd) {
        this.soNgayCd = soNgayCd;
        return this;
    }

    public void setSoNgayCd(Integer soNgayCd) {
        this.soNgayCd = soNgayCd;
    }

    public String getNgayNghiList() {
        return ngayNghiList;
    }

    public CtHuong ngayNghiList(String ngayNghiList) {
        this.ngayNghiList = ngayNghiList;
        return this;
    }

    public void setNgayNghiList(String ngayNghiList) {
        this.ngayNghiList = ngayNghiList;
    }

    public String getVssid() {
        return vssid;
    }

    public CtHuong vssid(String vssid) {
        this.vssid = vssid;
        return this;
    }

    public void setVssid(String vssid) {
        this.vssid = vssid;
    }

    public ZonedDateTime getTuNgayBs() {
        return tuNgayBs;
    }

    public CtHuong tuNgayBs(ZonedDateTime tuNgayBs) {
        this.tuNgayBs = tuNgayBs;
        return this;
    }

    public void setTuNgayBs(ZonedDateTime tuNgayBs) {
        this.tuNgayBs = tuNgayBs;
    }

    public ZonedDateTime getDenNgayBs() {
        return denNgayBs;
    }

    public CtHuong denNgayBs(ZonedDateTime denNgayBs) {
        this.denNgayBs = denNgayBs;
        return this;
    }

    public void setDenNgayBs(ZonedDateTime denNgayBs) {
        this.denNgayBs = denNgayBs;
    }

    public Integer getChk1() {
        return chk1;
    }

    public CtHuong chk1(Integer chk1) {
        this.chk1 = chk1;
        return this;
    }

    public void setChk1(Integer chk1) {
        this.chk1 = chk1;
    }

    public Integer getChk2() {
        return chk2;
    }

    public CtHuong chk2(Integer chk2) {
        this.chk2 = chk2;
        return this;
    }

    public void setChk2(Integer chk2) {
        this.chk2 = chk2;
    }

    public Integer getChk3() {
        return chk3;
    }

    public CtHuong chk3(Integer chk3) {
        this.chk3 = chk3;
        return this;
    }

    public void setChk3(Integer chk3) {
        this.chk3 = chk3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CtHuong ctHuong = (CtHuong) o;
        if (ctHuong.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), ctHuong.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CtHuong{" +
            "id=" + getId() +
            ", keysl='" + getKeysl() + "'" +
            ", keyYp='" + getKeyYp() + "'" +
            ", maDv='" + getMaDv() + "'" +
            ", soBhxh='" + getSoBhxh() + "'" +
            ", maNv='" + getMaNv() + "'" +
            ", hoTen='" + getHoTen() + "'" +
            ", mucLuong='" + getMucLuong() + "'" +
            ", ngayDuyet='" + getNgayDuyet() + "'" +
            ", nguoiDuyet='" + getNguoiDuyet() + "'" +
            ", loaiDt='" + getLoaiDt() + "'" +
            ", maNt='" + getMaNt() + "'" +
            ", mlTt='" + getMlTt() + "'" +
            ", maCd='" + getMaCd() + "'" +
            ", maNh='" + getMaNh() + "'" +
            ", tuNgay='" + getTuNgay() + "'" +
            ", denNgay='" + getDenNgay() + "'" +
            ", tuNgayH='" + getTuNgayH() + "'" +
            ", denNgayH='" + getDenNgayH() + "'" +
            ", soNgay='" + getSoNgay() + "'" +
            ", soNgayH='" + getSoNgayH() + "'" +
            ", soNgayLk='" + getSoNgayLk() + "'" +
            ", soTien='" + getSoTien() + "'" +
            ", soTienH='" + getSoTienH() + "'" +
            ", soNamBh='" + getSoNamBh() + "'" +
            ", soThangBh='" + getSoThangBh() + "'" +
            ", soNgayLkdv='" + getSoNgayLkdv() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", dk1='" + getDk1() + "'" +
            ", dk2='" + getDk2() + "'" +
            ", dk5='" + getDk5() + "'" +
            ", dk6='" + getDk6() + "'" +
            ", tuoiCon='" + getTuoiCon() + "'" +
            ", sttCon='" + getSttCon() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", maQt='" + getMaQt() + "'" +
            ", dkBenhDai='" + getDkBenhDai() + "'" +
            ", dkPhauthuat='" + getDkPhauthuat() + "'" +
            ", dkNghionha='" + getDkNghionha() + "'" +
            ", dkSuygiamld='" + getDkSuygiamld() + "'" +
            ", dkXacsyt='" + getDkXacsyt() + "'" +
            ", dk3Ca='" + getDk3Ca() + "'" +
            ", ngay1='" + getNgay1() + "'" +
            ", tenCon='" + getTenCon() + "'" +
            ", stt='" + getStt() + "'" +
            ", maQl='" + getMaQl() + "'" +
            ", maTinh='" + getMaTinh() + "'" +
            ", loaidc='" + getLoaidc() + "'" +
            ", lydodc='" + getLydodc() + "'" +
            ", ghichudc='" + getGhichudc() + "'" +
            ", loaiBenh='" + getLoaiBenh() + "'" +
            ", tuyenBv='" + getTuyenBv() + "'" +
            ", lbqhs='" + getLbqhs() + "'" +
            ", lbqml='" + getLbqml() + "'" +
            ", troCap='" + getTroCap() + "'" +
            ", troCapBh='" + getTroCapBh() + "'" +
            ", dk3='" + getDk3() + "'" +
            ", dk4='" + getDk4() + "'" +
            ", tyleh='" + getTyleh() + "'" +
            ", ngayNuoi='" + getNgayNuoi() + "'" +
            ", soNgayCd='" + getSoNgayCd() + "'" +
            ", ngayNghiList='" + getNgayNghiList() + "'" +
            ", vssid='" + getVssid() + "'" +
            ", tuNgayBs='" + getTuNgayBs() + "'" +
            ", denNgayBs='" + getDenNgayBs() + "'" +
            ", chk1='" + getChk1() + "'" +
            ", chk2='" + getChk2() + "'" +
            ", chk3='" + getChk3() + "'" +
            "}";
    }
}
