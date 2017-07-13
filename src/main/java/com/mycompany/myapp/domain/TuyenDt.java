package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A TuyenDt.
 */
@Entity
@Table(name = "tuyen_dt")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "tuyendt")
public class TuyenDt implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "so_ngay")
    private Integer soNgay;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "hscn_id")
    private Long hscnId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public TuyenDt ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public TuyenDt ten(String ten) {
        this.ten = ten;
        return this;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getSoNgay() {
        return soNgay;
    }

    public TuyenDt soNgay(Integer soNgay) {
        this.soNgay = soNgay;
        return this;
    }

    public void setSoNgay(Integer soNgay) {
        this.soNgay = soNgay;
    }

    public Boolean isActive() {
        return active;
    }

    public TuyenDt active(Boolean active) {
        this.active = active;
        return this;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getHscnId() {
        return hscnId;
    }

    public TuyenDt hscnId(Long hscnId) {
        this.hscnId = hscnId;
        return this;
    }

    public void setHscnId(Long hscnId) {
        this.hscnId = hscnId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TuyenDt tuyenDt = (TuyenDt) o;
        if (tuyenDt.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tuyenDt.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TuyenDt{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", ten='" + getTen() + "'" +
            ", soNgay='" + getSoNgay() + "'" +
            ", active='" + isActive() + "'" +
            ", hscnId='" + getHscnId() + "'" +
            "}";
    }
}
