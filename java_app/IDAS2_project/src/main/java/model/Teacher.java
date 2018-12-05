package model;

import javax.persistence.*;

/*@NamedStoredProcedureQuery(
        name="add_teacher",
        procedureName="add_teacher",
        parameters={
                @StoredProcedureParameter(name="admin_mail", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="admin_heslo", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_jmeno", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_prijmeni", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_titul_pred", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_titul_za", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_telefon", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_mobil", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_email", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_pracoviste_id", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_heslo", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_role_zkratka", type=String.class, mode=ParameterMode.IN),
                @StoredProcedureParameter(name="p_uvazek_typ", type=String.class, mode=ParameterMode.IN)
        }
)*/
@NamedNativeQueries({
        /*@NamedNativeQuery(
                name = "add_teacher",
                query = "{ call add_teacher ( ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? )}",
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        ),*/
        @NamedNativeQuery(
                name = "login_user",
                query = "{ ? = call login_usr ( ? , ? ) }",
                resultClass = Teacher.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        ),

        @NamedNativeQuery(
                name = "get_all_teachers",
                query = "{ ? = call get_all_teachers ( ) }",
                resultClass = Teacher.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )})
@Entity
@Table(name = "VIEW_TEACHERS")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private String id;
    @Column(name = "titul_pred")
    private String titulPred;
    @Column(name = "jmeno", nullable = false)
    private String jmeno;
    @Column(name = "prijmeni", nullable = false)
    private String prijmeni;
    @Column(name = "titul_za")
    private String titulZa;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pracoviste")
    private Workplace pracoviste;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uvazek")
    private Obligation uvazek;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "telefon")
    private String telefon;
    @Column(name = "mobil")
    private String mobil;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role", nullable = false)
    private Role role;
    @Transient
    private String heslo;

    public Teacher(String titulPred, String jmeno, String prijmeni, String titulZa, Workplace pracoviste, Obligation uvazek, String email, String telefon, String mobil, Role role, String heslo) {
        this.titulPred = titulPred;
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.titulZa = titulZa;
        this.pracoviste = pracoviste;
        this.uvazek = uvazek;
        this.email = email;
        this.telefon = telefon;
        this.mobil = mobil;
        this.role = role;
        this.heslo = heslo;
    }

    public Teacher() {
    }

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

    public String getHeslo() { return heslo; }

    public void setHeslo(String heslo) { this.heslo = heslo; }

    @Override
    public String toString() {
        return ((titulPred != null) ? titulPred + ' ' : "") + jmeno + ' ' + prijmeni + ' ' + ((titulZa != null) ? titulZa : "");
    }
}
