package com.uniprojects.wfspring.service.dto;


import java.util.List;
import java.util.Objects;

public class FelhasznaloDto {
    private long id;
    private String nev;
    private String email;
    private String telefonszam;

    private List<JogosultsagDto> jogosultsagok;

    public FelhasznaloDto() {
    }

    public FelhasznaloDto(long id, String nev, String email, String telefonszam) {
        this.id = id;
        this.nev = nev;
        this.email = email;
        this.telefonszam = telefonszam;
    }

    public FelhasznaloDto(long id, String nev, String email, String telefonszam, List<JogosultsagDto> jogosultsagok) {
        this.id = id;
        this.nev = nev;
        this.email = email;
        this.telefonszam = telefonszam;
        this.jogosultsagok = jogosultsagok;
    }

    public List<JogosultsagDto> getJogosultsagok() {
        return jogosultsagok;
    }

    public void setJogosultsagok(List<JogosultsagDto> jogosultsagok) {
        this.jogosultsagok = jogosultsagok;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FelhasznaloDto that = (FelhasznaloDto) o;
        return id == that.id && Objects.equals(nev, that.nev) && Objects.equals(email, that.email) && Objects.equals(telefonszam, that.telefonszam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nev, email, telefonszam);
    }
}
