package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Chedo.
 */
@Entity
@Table(name = "chedo")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "chedo")
public class Chedo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_cd")
    private String maCD;

    @Column(name = "ten_cd")
    private String tenCD;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaCD() {
        return maCD;
    }

    public Chedo maCD(String maCD) {
        this.maCD = maCD;
        return this;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public String getTenCD() {
        return tenCD;
    }

    public Chedo tenCD(String tenCD) {
        this.tenCD = tenCD;
        return this;
    }

    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Chedo chedo = (Chedo) o;
        if (chedo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), chedo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Chedo{" +
            "id=" + getId() +
            ", maCD='" + getMaCD() + "'" +
            ", tenCD='" + getTenCD() + "'" +
            "}";
    }
}
