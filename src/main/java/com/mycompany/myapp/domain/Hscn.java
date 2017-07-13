package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Hscn.
 */
@Entity
@Table(name = "hscn")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "hscn")
public class Hscn implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "so_bhxh", nullable = false)
    private String soBhxh;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "ngay_sinh")
    private Long ngaySinh;

    @Column(name = "so_cmnd")
    private String soCmnd;

    @Column(name = "dia_chi_lh")
    private String diaChiLh;

    @Column(name = "ngay_cap")
    private Long ngayCap;

    @Column(name = "noi_cap")
    private String noiCap;

    @Column(name = "dan_toc")
    private String dan_toc;

    @Column(name = "quoc_tich")
    private String quoc_tich;

    @Column(name = "noi_khai")
    private String noi_khai;

    @Column(name = "diachi_hk")
    private String diachiHK;

    @Column(name = "dien_thoai")
    private String dien_thoai;

    @Column(name = "email")
    private String email;

    @Column(name = "chuc_vu")
    private String chuc_vu;

    @Column(name = "ma_pb")
    private String maPB;

    @Column(name = "phong_ban")
    private String phong_ban;

    @Column(name = "ma_cv")
    private String maCV;

    @Column(name = "ma_nv")
    private String maNV;

    @Column(name = "noicap_bhxh")
    private String noicapBHXH;

    @Column(name = "ma_dt")
    private String maDT;

    @Column(name = "ma_bv")
    private String maBV;

    @Column(name = "ghi_chu")
    private String ghi_chu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSoBhxh() {
        return soBhxh;
    }

    public Hscn soBhxh(String soBhxh) {
        this.soBhxh = soBhxh;
        return this;
    }

    public void setSoBhxh(String soBhxh) {
        this.soBhxh = soBhxh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public Hscn hoTen(String hoTen) {
        this.hoTen = hoTen;
        return this;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public Hscn gioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
        return this;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Long getNgaySinh() {
        return ngaySinh;
    }

    public Hscn ngaySinh(Long ngaySinh) {
        this.ngaySinh = ngaySinh;
        return this;
    }

    public void setNgaySinh(Long ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSoCmnd() {
        return soCmnd;
    }

    public Hscn soCmnd(String soCmnd) {
        this.soCmnd = soCmnd;
        return this;
    }

    public void setSoCmnd(String soCmnd) {
        this.soCmnd = soCmnd;
    }

    public String getDiaChiLh() {
        return diaChiLh;
    }

    public Hscn diaChiLh(String diaChiLh) {
        this.diaChiLh = diaChiLh;
        return this;
    }

    public void setDiaChiLh(String diaChiLh) {
        this.diaChiLh = diaChiLh;
    }

    public Long getNgayCap() {
        return ngayCap;
    }

    public Hscn ngayCap(Long ngayCap) {
        this.ngayCap = ngayCap;
        return this;
    }

    public void setNgayCap(Long ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getNoiCap() {
        return noiCap;
    }

    public Hscn noiCap(String noiCap) {
        this.noiCap = noiCap;
        return this;
    }

    public void setNoiCap(String noiCap) {
        this.noiCap = noiCap;
    }

    public String getDan_toc() {
        return dan_toc;
    }

    public Hscn dan_toc(String dan_toc) {
        this.dan_toc = dan_toc;
        return this;
    }

    public void setDan_toc(String dan_toc) {
        this.dan_toc = dan_toc;
    }

    public String getQuoc_tich() {
        return quoc_tich;
    }

    public Hscn quoc_tich(String quoc_tich) {
        this.quoc_tich = quoc_tich;
        return this;
    }

    public void setQuoc_tich(String quoc_tich) {
        this.quoc_tich = quoc_tich;
    }

    public String getNoi_khai() {
        return noi_khai;
    }

    public Hscn noi_khai(String noi_khai) {
        this.noi_khai = noi_khai;
        return this;
    }

    public void setNoi_khai(String noi_khai) {
        this.noi_khai = noi_khai;
    }

    public String getDiachiHK() {
        return diachiHK;
    }

    public Hscn diachiHK(String diachiHK) {
        this.diachiHK = diachiHK;
        return this;
    }

    public void setDiachiHK(String diachiHK) {
        this.diachiHK = diachiHK;
    }

    public String getDien_thoai() {
        return dien_thoai;
    }

    public Hscn dien_thoai(String dien_thoai) {
        this.dien_thoai = dien_thoai;
        return this;
    }

    public void setDien_thoai(String dien_thoai) {
        this.dien_thoai = dien_thoai;
    }

    public String getEmail() {
        return email;
    }

    public Hscn email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChuc_vu() {
        return chuc_vu;
    }

    public Hscn chuc_vu(String chuc_vu) {
        this.chuc_vu = chuc_vu;
        return this;
    }

    public void setChuc_vu(String chuc_vu) {
        this.chuc_vu = chuc_vu;
    }

    public String getMaPB() {
        return maPB;
    }

    public Hscn maPB(String maPB) {
        this.maPB = maPB;
        return this;
    }

    public void setMaPB(String maPB) {
        this.maPB = maPB;
    }

    public String getPhong_ban() {
        return phong_ban;
    }

    public Hscn phong_ban(String phong_ban) {
        this.phong_ban = phong_ban;
        return this;
    }

    public void setPhong_ban(String phong_ban) {
        this.phong_ban = phong_ban;
    }

    public String getMaCV() {
        return maCV;
    }

    public Hscn maCV(String maCV) {
        this.maCV = maCV;
        return this;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }

    public String getMaNV() {
        return maNV;
    }

    public Hscn maNV(String maNV) {
        this.maNV = maNV;
        return this;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getNoicapBHXH() {
        return noicapBHXH;
    }

    public Hscn noicapBHXH(String noicapBHXH) {
        this.noicapBHXH = noicapBHXH;
        return this;
    }

    public void setNoicapBHXH(String noicapBHXH) {
        this.noicapBHXH = noicapBHXH;
    }

    public String getMaDT() {
        return maDT;
    }

    public Hscn maDT(String maDT) {
        this.maDT = maDT;
        return this;
    }

    public void setMaDT(String maDT) {
        this.maDT = maDT;
    }

    public String getMaBV() {
        return maBV;
    }

    public Hscn maBV(String maBV) {
        this.maBV = maBV;
        return this;
    }

    public void setMaBV(String maBV) {
        this.maBV = maBV;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public Hscn ghi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
        return this;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hscn hscn = (Hscn) o;
        if (hscn.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), hscn.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Hscn{" +
            "id=" + getId() +
            ", soBhxh='" + getSoBhxh() + "'" +
            ", hoTen='" + getHoTen() + "'" +
            ", gioiTinh='" + getGioiTinh() + "'" +
            ", ngaySinh='" + getNgaySinh() + "'" +
            ", soCmnd='" + getSoCmnd() + "'" +
            ", diaChiLh='" + getDiaChiLh() + "'" +
            ", ngayCap='" + getNgayCap() + "'" +
            ", noiCap='" + getNoiCap() + "'" +
            ", dan_toc='" + getDan_toc() + "'" +
            ", quoc_tich='" + getQuoc_tich() + "'" +
            ", noi_khai='" + getNoi_khai() + "'" +
            ", diachiHK='" + getDiachiHK() + "'" +
            ", dien_thoai='" + getDien_thoai() + "'" +
            ", email='" + getEmail() + "'" +
            ", chuc_vu='" + getChuc_vu() + "'" +
            ", maPB='" + getMaPB() + "'" +
            ", phong_ban='" + getPhong_ban() + "'" +
            ", maCV='" + getMaCV() + "'" +
            ", maNV='" + getMaNV() + "'" +
            ", noicapBHXH='" + getNoicapBHXH() + "'" +
            ", maDT='" + getMaDT() + "'" +
            ", maBV='" + getMaBV() + "'" +
            ", ghi_chu='" + getGhi_chu() + "'" +
            "}";
    }
}
