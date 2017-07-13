package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DmDonVi.
 */
@Entity
@Table(name = "dm_don_vi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "dmdonvi")
public class DmDonVi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "ma", nullable = false)
    private String ma;

    @Column(name = "name")
    private String name;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "ghi_chu")
    private String ghiChu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public DmDonVi ma(String ma) {
        this.ma = ma;
        return this;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getName() {
        return name;
    }

    public DmDonVi name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public DmDonVi diaChi(String diaChi) {
        this.diaChi = diaChi;
        return this;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public DmDonVi ghiChu(String ghiChu) {
        this.ghiChu = ghiChu;
        return this;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DmDonVi dmDonVi = (DmDonVi) o;
        if (dmDonVi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), dmDonVi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DmDonVi{" +
            "id=" + getId() +
            ", ma='" + getMa() + "'" +
            ", name='" + getName() + "'" +
            ", diaChi='" + getDiaChi() + "'" +
            ", ghiChu='" + getGhiChu() + "'" +
            "}";
    }
}
