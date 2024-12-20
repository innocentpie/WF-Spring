package com.uniprojects.wfspring.data.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "szoba")
public class SzobaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "szobaszam")
    private Integer szobaszam;

    @Column(name = "maxferohely")
    private Integer maxFerohely;

    @Column(name = "ftperejszaka")
    private Integer ftPerEjszaka;

    @OneToMany(mappedBy = "szoba")
    private List<FoglalasEntity> foglalasok;

    public SzobaEntity() {
    }

    public SzobaEntity(long id, Integer szobaszam, Integer maxFerohely, Integer ftPerEjszaka) {
        this.id = id;
        this.szobaszam = szobaszam;
        this.maxFerohely = maxFerohely;
        this.ftPerEjszaka = ftPerEjszaka;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getSzobaszam() {
        return szobaszam;
    }

    public void setSzobaszam(Integer szobaszam) {
        this.szobaszam = szobaszam;
    }

    public Integer getMaxFerohely() {
        return maxFerohely;
    }

    public void setMaxFerohely(Integer maxFerohely) {
        this.maxFerohely = maxFerohely;
    }

    public Integer getFtPerEjszaka() {
        return ftPerEjszaka;
    }

    public void setFtPerEjszaka(Integer ftPerEjszaka) {
        this.ftPerEjszaka = ftPerEjszaka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SzobaEntity that = (SzobaEntity) o;
        return id == that.id && Objects.equals(szobaszam, that.szobaszam) && Objects.equals(maxFerohely, that.maxFerohely) && Objects.equals(ftPerEjszaka, that.ftPerEjszaka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, szobaszam, maxFerohely, ftPerEjszaka);
    }
}
