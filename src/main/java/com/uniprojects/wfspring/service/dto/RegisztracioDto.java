package com.uniprojects.wfspring.service.dto;

import java.util.Objects;

public class RegisztracioDto {

    private String nev;
    private String jelszo;
    private String email;
    private String telefonszam;

    public RegisztracioDto() {
    }

    public RegisztracioDto(String nev, String jelszo, String email, String telefonszam) {
        this.nev = nev;
        this.jelszo = jelszo;
        this.email = email;
        this.telefonszam = telefonszam;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
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
        RegisztracioDto that = (RegisztracioDto) o;
        return Objects.equals(nev, that.nev) && Objects.equals(jelszo, that.jelszo) && Objects.equals(email, that.email) && Objects.equals(telefonszam, that.telefonszam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nev, jelszo, email, telefonszam);
    }
}
