package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Luong.
 */
@Entity
@Table(name = "luong")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "luong")
public class Luong implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "muc_luong")
    private String mucLuong;

    @Column(name = "thang")
    private Integer thang;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMucLuong() {
        return mucLuong;
    }

    public Luong mucLuong(String mucLuong) {
        this.mucLuong = mucLuong;
        return this;
    }

    public void setMucLuong(String mucLuong) {
        this.mucLuong = mucLuong;
    }

    public Integer getThang() {
        return thang;
    }

    public Luong thang(Integer thang) {
        this.thang = thang;
        return this;
    }

    public void setThang(Integer thang) {
        this.thang = thang;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Luong luong = (Luong) o;
        if (luong.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), luong.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Luong{" +
            "id=" + getId() +
            ", mucLuong='" + getMucLuong() + "'" +
            ", thang='" + getThang() + "'" +
            "}";
    }
}
