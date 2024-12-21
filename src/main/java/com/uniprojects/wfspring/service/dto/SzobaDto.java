package com.uniprojects.wfspring.service.dto;

import java.util.Objects;

public class SzobaDto {
    private Long id;
    private Integer szobaszam;
    private Integer maxFerohely;
    private Integer ftPerEjszaka;

    public SzobaDto() {
    }

    public SzobaDto(Long id, Integer szobaszam, Integer maxFerohely, Integer ftPerEjszaka) {
        this.id = id;
        this.szobaszam = szobaszam;
        this.maxFerohely = maxFerohely;
        this.ftPerEjszaka = ftPerEjszaka;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        SzobaDto szobaDto = (SzobaDto) o;
        return Objects.equals(id, szobaDto.id) && Objects.equals(szobaszam, szobaDto.szobaszam) && Objects.equals(maxFerohely, szobaDto.maxFerohely) && Objects.equals(ftPerEjszaka, szobaDto.ftPerEjszaka);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, szobaszam, maxFerohely, ftPerEjszaka);
    }
}
