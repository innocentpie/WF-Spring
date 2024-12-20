package com.uniprojects.wfspring.data.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "foglalas")
public class FoglalasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "felhasznalo_id", referencedColumnName = "id")
    private FelhasznaloEntity felhasznalo;

    @ManyToOne
    @JoinColumn(name = "szoba_id", referencedColumnName = "id")
    private SzobaEntity szoba;

    @Column(name = "elfoglalasdatum")
    private Date elfoglalasDatum;

    @Column(name = "elhagyasdatum")
    private Date elhagyasDatum;

    public FoglalasEntity() {
    }

    public FoglalasEntity(long id, FelhasznaloEntity felhasznalo, SzobaEntity szoba, Date elfoglalasDatum, Date elhagyasDatum) {
        this.id = id;
        this.felhasznalo = felhasznalo;
        this.szoba = szoba;
        this.elfoglalasDatum = elfoglalasDatum;
        this.elhagyasDatum = elhagyasDatum;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public FelhasznaloEntity getFelhasznalo() {
        return felhasznalo;
    }

    public void setFelhasznalo(FelhasznaloEntity felhasznalo) {
        this.felhasznalo = felhasznalo;
    }

    public SzobaEntity getSzoba() {
        return szoba;
    }

    public void setSzoba(SzobaEntity szoba) {
        this.szoba = szoba;
    }

    public Date getElfoglalasDatum() {
        return elfoglalasDatum;
    }

    public void setElfoglalasDatum(Date elfoglalasDatum) {
        this.elfoglalasDatum = elfoglalasDatum;
    }

    public Date getElhagyasDatum() {
        return elhagyasDatum;
    }

    public void setElhagyasDatum(Date elhagyasDatum) {
        this.elhagyasDatum = elhagyasDatum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoglalasEntity that = (FoglalasEntity) o;
        return id == that.id && Objects.equals(felhasznalo, that.felhasznalo) && Objects.equals(szoba, that.szoba) && Objects.equals(elfoglalasDatum, that.elfoglalasDatum) && Objects.equals(elhagyasDatum, that.elhagyasDatum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, felhasznalo, szoba, elfoglalasDatum, elhagyasDatum);
    }
}
