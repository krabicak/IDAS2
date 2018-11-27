package model;

import javax.persistence.*;

@NamedNativeQuery(
        name = "login_user",
        query = "{ ? = call login_usr ( ? , ? ) }",
        resultClass = Teacher.class,
        hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
)

@Entity
@Table(name = "VIEW_TEACHERS")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Column(name = "titul_pred")
    private String titulPred;
    @Column(name = "jmeno")
    private String jmeno;
    @Column(name = "prijmeni")
    private String prijmeni;
    @Column(name = "titul_za")
    private String titulZa;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pracoviste")
    private Workplace pracoviste;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="uvazek")
    private Obligation uvazek;
    @Column(name = "email")
    private String email;
    @Column(name = "telefon")
    private String telefon;
    @Column(name = "mobil")
    private String mobil;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="role")
    private Role role;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulPred() {
        return titulPred;
    }

    public void setTitulPred(String titulPred) {
        this.titulPred = titulPred;
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


    public String getTitulZa() {
        return titulZa;
    }

    public void setTitulZa(String titulZa) {
        this.titulZa = titulZa;
    }


    public Workplace getPracoviste() {
        return pracoviste;
    }

    public void setPracoviste(Workplace pracoviste) {
        this.pracoviste = pracoviste;
    }

    public Obligation getUvazek() {
        return uvazek;
    }

    public void setUvazek(Obligation uvazek) {
        this.uvazek = uvazek;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id='" + id + '\'' +
                ", titulPred='" + titulPred + '\'' +
                ", jmeno='" + jmeno + '\'' +
                ", prijmeni='" + prijmeni + '\'' +
                ", titulZa='" + titulZa + '\'' +
                ", pracoviste=" + pracoviste +
                ", uvazek=" + uvazek +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", mobil='" + mobil + '\'' +
                ", role=" + role +
                '}';
    }
}
