package com.uniprojects.wfspring.service.dto;


import java.util.Date;
import java.util.Objects;

public class FoglalasDto {
    private long id;
    private Long felhasznaloId;
    private Long szobaId;
    private Date elfoglalasDatum;
    private Date elhagyasDatum;

    private FelhasznaloDto felhasznalo;
    private SzobaDto szoba;

    public FoglalasDto() {
    }

    public FoglalasDto(Date elhagyasDatum, Date elfoglalasDatum, Long szobaId, Long felhasznaloId, long id) {
        this.elhagyasDatum = elhagyasDatum;
        this.elfoglalasDatum = elfoglalasDatum;
        this.szobaId = szobaId;
        this.felhasznaloId = felhasznaloId;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getFelhasznaloId() {
        return felhasznaloId;
    }

    public void setFelhasznaloId(Long felhasznaloId) {
        this.felhasznaloId = felhasznaloId;
    }

    public Long getSzobaId() {
        return szobaId;
    }

    public void setSzobaId(Long szobaId) {
        this.szobaId = szobaId;
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

    public FelhasznaloDto getFelhasznalo() {
        return felhasznalo;
    }

    public void setFelhasznalo(FelhasznaloDto felhasznalo) {
        this.felhasznalo = felhasznalo;
    }

    public SzobaDto getSzoba() {
        return szoba;
    }

    public void setSzoba(SzobaDto szoba) {
        this.szoba = szoba;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoglalasDto that = (FoglalasDto) o;
        return id == that.id && Objects.equals(felhasznaloId, that.felhasznaloId) && Objects.equals(szobaId, that.szobaId) && Objects.equals(elfoglalasDatum, that.elfoglalasDatum) && Objects.equals(elhagyasDatum, that.elhagyasDatum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, felhasznaloId, szobaId, elfoglalasDatum, elhagyasDatum);
    }
}
