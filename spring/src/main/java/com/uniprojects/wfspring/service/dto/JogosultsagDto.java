package com.uniprojects.wfspring.service.dto;

import java.util.Objects;

public class JogosultsagDto {
    private long id;
    private String nev;

    public JogosultsagDto() {
    }

    public JogosultsagDto(long id, String nev) {
        this.id = id;
        this.nev = nev;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JogosultsagDto that = (JogosultsagDto) o;
        return id == that.id && Objects.equals(nev, that.nev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nev);
    }
}
