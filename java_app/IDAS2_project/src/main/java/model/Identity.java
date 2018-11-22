package model;

import javax.persistence.*;
import java.io.Serializable;

@NamedNativeQuery(
        name = "login_user",
        query = "{ ? = call login_user ( ? , ? ) }",
        resultClass = Identity.class,
        hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
)
@Entity
@Table(name = "Pracovnici", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "email")})
public class Identity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Column(name = "jmeno")
    private String jmeno;
    @Column(name = "prijmeni")
    private String prijmeni;
    @Column(name = "titul_pred")
    private String titulPred;
    @Column(name = "titul_za")
    private String titulZa;
    @Column(name = "telefon")
    private String telefon;
    @Column(name = "mobil")
    private String mobil;
    @Column(name = "email")
    private String email;
    @Column(name = "pracoviste_id")
    private String pracovisteId;
    @Column(name = "heslo")
    private String heslo;
    @Column(name = "role_zkratka")
    private String roleZkratka;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }


    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }


    public String getTitulPred() {
        return titulPred;
    }

    public void setTitulPred(String titulPred) {
        this.titulPred = titulPred;
    }


    public String getTitulZa() {
        return titulZa;
    }

    public void setTitulZa(String titulZa) {
        this.titulZa = titulZa;
    }


    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }


    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPracovisteId() {
        return pracovisteId;
    }

    public void setPracovisteId(String pracovisteId) {
        this.pracovisteId = pracovisteId;
    }


    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }


    public String getRoleZkratka() {
        return roleZkratka;
    }

    public void setRoleZkratka(String roleZkratka) {
        this.roleZkratka = roleZkratka;
    }
}
