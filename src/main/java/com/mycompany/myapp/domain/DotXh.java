package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DotXh.
 */
@Entity
@Table(name = "dot_xh")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "dotxh")
public class DotXh implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_dv")
    private String maDv;

    @Column(name = "ma_qt")
    private String maQt;

    @Column(name = "loai_dt")
    private String loaiDt;

    @Column(name = "ma_nt")
    private String maNt;

    @Column(name = "ty_gia")
    private Integer tyGia;

    @Column(name = "ngay_ct")
    private ZonedDateTime ngayCt;

    @Column(name = "so_phieu")
    private String soPhieu;

    @Column(name = "so_ld")
    private Integer soLd;

    @Column(name = "tql")
    private Float tql;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "last_code")
    private ZonedDateTime lastCode;

    @Column(name = "sld_nu")
    private Integer sldNu;

    @Column(name = "ma_ql")
    private String maQl;

    @Column(name = "ma_tinh")
    private String maTinh;

    @Column(name = "list_ngaynghi")
    private String listNgaynghi;

    @Column(name = "khoa_so")
    private Boolean khoaSo;

    @Column(name = "ngay_khoa")
    private ZonedDateTime ngayKhoa;

    @Column(name = "thong_tin_1")
    private String thongTin1;

    @Column(name = "thong_tin_2")
    private String thongTin2;

    @Column(name = "thong_tin_3")
    private String thongTin3;

    @Column(name = "ngay_1")
    private ZonedDateTime ngay1;

    @Column(name = "ngay_2")
    private ZonedDateTime ngay2;

    @Column(name = "ngay_3")
    private ZonedDateTime ngay3;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "ngay_duyet")
    private ZonedDateTime ngayDuyet;

    @Column(name = "nguoi_duyet")
    private String nguoiDuyet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDv() {
        return maDv;
    }

    public DotXh maDv(String maDv) {
        this.maDv = maDv;
        return this;
    }

    public void setMaDv(String maDv) {
        this.maDv = maDv;
    }

    public String getMaQt() {
        return maQt;
    }

    public DotXh maQt(String maQt) {
        this.maQt = maQt;
        return this;
    }

    public void setMaQt(String maQt) {
        this.maQt = maQt;
    }

    public String getLoaiDt() {
        return loaiDt;
    }

    public DotXh loaiDt(String loaiDt) {
        this.loaiDt = loaiDt;
        return this;
    }

    public void setLoaiDt(String loaiDt) {
        this.loaiDt = loaiDt;
    }

    public String getMaNt() {
        return maNt;
    }

    public DotXh maNt(String maNt) {
        this.maNt = maNt;
        return this;
    }

    public void setMaNt(String maNt) {
        this.maNt = maNt;
    }

    public Integer getTyGia() {
        return tyGia;
    }

    public DotXh tyGia(Integer tyGia) {
        this.tyGia = tyGia;
        return this;
    }

    public void setTyGia(Integer tyGia) {
        this.tyGia = tyGia;
    }

    public ZonedDateTime getNgayCt() {
        return ngayCt;
    }

    public DotXh ngayCt(ZonedDateTime ngayCt) {
        this.ngayCt = ngayCt;
        return this;
    }

    public void setNgayCt(ZonedDateTime ngayCt) {
        this.ngayCt = ngayCt;
    }

    public String getSoPhieu() {
        return soPhieu;
    }

    public DotXh soPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
        return this;
    }

    public void setSoPhieu(String soPhieu) {
        this.soPhieu = soPhieu;
    }

    public Integer getSoLd() {
        return soLd;
    }

    public DotXh soLd(Integer soLd) {
        this.soLd = soLd;
        return this;
    }

    public void setSoLd(Integer soLd) {
        this.soLd = soLd;
    }

    public Float getTql() {
        return tql;
    }

    public DotXh tql(Float tql) {
        this.tql = tql;
        return this;
    }

    public void setTql(Float tql) {
        this.tql = tql;
    }

    public String getUserCode() {
        return userCode;
    }

    public DotXh userCode(String userCode) {
        this.userCode = userCode;
        return this;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public ZonedDateTime getLastCode() {
        return lastCode;
    }

    public DotXh lastCode(ZonedDateTime lastCode) {
        this.lastCode = lastCode;
        return this;
    }

    public void setLastCode(ZonedDateTime lastCode) {
        this.lastCode = lastCode;
    }

    public Integer getSldNu() {
        return sldNu;
    }

    public DotXh sldNu(Integer sldNu) {
        this.sldNu = sldNu;
        return this;
    }

    public void setSldNu(Integer sldNu) {
        this.sldNu = sldNu;
    }

    public String getMaQl() {
        return maQl;
    }

    public DotXh maQl(String maQl) {
        this.maQl = maQl;
        return this;
    }

    public void setMaQl(String maQl) {
        this.maQl = maQl;
    }

    public String getMaTinh() {
        return maTinh;
    }

    public DotXh maTinh(String maTinh) {
        this.maTinh = maTinh;
        return this;
    }

    public void setMaTinh(String maTinh) {
        this.maTinh = maTinh;
    }

    public String getListNgaynghi() {
        return listNgaynghi;
    }

    public DotXh listNgaynghi(String listNgaynghi) {
        this.listNgaynghi = listNgaynghi;
        return this;
    }

    public void setListNgaynghi(String listNgaynghi) {
        this.listNgaynghi = listNgaynghi;
    }

    public Boolean isKhoaSo() {
        return khoaSo;
    }

    public DotXh khoaSo(Boolean khoaSo) {
        this.khoaSo = khoaSo;
        return this;
    }

    public void setKhoaSo(Boolean khoaSo) {
        this.khoaSo = khoaSo;
    }

    public ZonedDateTime getNgayKhoa() {
        return ngayKhoa;
    }

    public DotXh ngayKhoa(ZonedDateTime ngayKhoa) {
        this.ngayKhoa = ngayKhoa;
        return this;
    }

    public void setNgayKhoa(ZonedDateTime ngayKhoa) {
        this.ngayKhoa = ngayKhoa;
    }

    public String getThongTin1() {
        return thongTin1;
    }

    public DotXh thongTin1(String thongTin1) {
        this.thongTin1 = thongTin1;
        return this;
    }

    public void setThongTin1(String thongTin1) {
        this.thongTin1 = thongTin1;
    }

    public String getThongTin2() {
        return thongTin2;
    }

    public DotXh thongTin2(String thongTin2) {
        this.thongTin2 = thongTin2;
        return this;
    }

    public void setThongTin2(String thongTin2) {
        this.thongTin2 = thongTin2;
    }

    public String getThongTin3() {
        return thongTin3;
    }

    public DotXh thongTin3(String thongTin3) {
        this.thongTin3 = thongTin3;
        return this;
    }

    public void setThongTin3(String thongTin3) {
        this.thongTin3 = thongTin3;
    }

    public ZonedDateTime getNgay1() {
        return ngay1;
    }

    public DotXh ngay1(ZonedDateTime ngay1) {
        this.ngay1 = ngay1;
        return this;
    }

    public void setNgay1(ZonedDateTime ngay1) {
        this.ngay1 = ngay1;
    }

    public ZonedDateTime getNgay2() {
        return ngay2;
    }

    public DotXh ngay2(ZonedDateTime ngay2) {
        this.ngay2 = ngay2;
        return this;
    }

    public void setNgay2(ZonedDateTime ngay2) {
        this.ngay2 = ngay2;
    }

    public ZonedDateTime getNgay3() {
        return ngay3;
    }

    public DotXh ngay3(ZonedDateTime ngay3) {
        this.ngay3 = ngay3;
        return this;
    }

    public void setNgay3(ZonedDateTime ngay3) {
        this.ngay3 = ngay3;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public DotXh ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public ZonedDateTime getNgayDuyet() {
        return ngayDuyet;
    }

    public DotXh ngayDuyet(ZonedDateTime ngayDuyet) {
        this.ngayDuyet = ngayDuyet;
        return this;
    }

    public void setNgayDuyet(ZonedDateTime ngayDuyet) {
        this.ngayDuyet = ngayDuyet;
    }

    public String getNguoiDuyet() {
        return nguoiDuyet;
    }

    public DotXh nguoiDuyet(String nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
        return this;
    }

    public void setNguoiDuyet(String nguoiDuyet) {
        this.nguoiDuyet = nguoiDuyet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DotXh dotXh = (DotXh) o;
        if (dotXh.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dotXh.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DotXh{" +
            "id=" + getId() +
            ", maDv='" + getMaDv() + "'" +
            ", maQt='" + getMaQt() + "'" +
            ", loaiDt='" + getLoaiDt() + "'" +
            ", maNt='" + getMaNt() + "'" +
            ", tyGia='" + getTyGia() + "'" +
            ", ngayCt='" + getNgayCt() + "'" +
            ", soPhieu='" + getSoPhieu() + "'" +
            ", soLd='" + getSoLd() + "'" +
            ", tql='" + getTql() + "'" +
            ", userCode='" + getUserCode() + "'" +
            ", lastCode='" + getLastCode() + "'" +
            ", sldNu='" + getSldNu() + "'" +
            ", maQl='" + getMaQl() + "'" +
            ", maTinh='" + getMaTinh() + "'" +
            ", listNgaynghi='" + getListNgaynghi() + "'" +
            ", khoaSo='" + isKhoaSo() + "'" +
            ", ngayKhoa='" + getNgayKhoa() + "'" +
            ", thongTin1='" + getThongTin1() + "'" +
            ", thongTin2='" + getThongTin2() + "'" +
            ", thongTin3='" + getThongTin3() + "'" +
            ", ngay1='" + getNgay1() + "'" +
            ", ngay2='" + getNgay2() + "'" +
            ", ngay3='" + getNgay3() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            ", ngayDuyet='" + getNgayDuyet() + "'" +
            ", nguoiDuyet='" + getNguoiDuyet() + "'" +
            "}";
    }
}
