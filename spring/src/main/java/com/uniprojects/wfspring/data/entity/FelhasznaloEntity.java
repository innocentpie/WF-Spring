package com.uniprojects.wfspring.data.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "felhasznalo")
public class FelhasznaloEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nev")
    private String nev;

    @Column(name = "jelszo")
    private String jelszo;

    @Column(name = "email")
    private String email;

    @Column(name = "telefonszam")
    private String telefonszam;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "felhasznalo_jogosultsagok",
            joinColumns = {@JoinColumn(name = "felhasznalo_id")},
            inverseJoinColumns = {@JoinColumn(name = "jogosultsag_id")}
    )
    private List<JogosultsagEntity> jogosultsagok;

    @OneToMany(mappedBy = "felhasznalo")
    private List<FoglalasEntity> foglalasok;


    public FelhasznaloEntity(long id, String nev, String jelszo, String email, String telefonszam) {
        this.id = id;
        this.nev = nev;
        this.jelszo = jelszo;
        this.email = email;
        this.telefonszam = telefonszam;
    }

    public FelhasznaloEntity() {
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


    public List<JogosultsagEntity> getJogosultsagok() {
        return jogosultsagok;
    }

    public void setJogosultsagok(List<JogosultsagEntity> jogosultsagok) {
        this.jogosultsagok = jogosultsagok;
    }

    public List<FoglalasEntity> getFoglalasok() {
        return foglalasok;
    }

    public void setFoglalasok(List<FoglalasEntity> foglalasok) {
        this.foglalasok = foglalasok;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FelhasznaloEntity that = (FelhasznaloEntity) o;
        return id == that.id && Objects.equals(nev, that.nev) && Objects.equals(jelszo, that.jelszo) && Objects.equals(email, that.email) && Objects.equals(telefonszam, that.telefonszam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nev, jelszo, email, telefonszam);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return jogosultsagok;
    }

    @Override
    public String getPassword() {
        return jelszo;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
