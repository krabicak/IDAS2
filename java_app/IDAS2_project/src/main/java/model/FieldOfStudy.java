package model;

import javax.persistence.*;
import java.util.List;

@NamedNativeQuery(
        name = "get_all_fields_of_study",
        query = "{ ? = call get_all_fields_of_study ( ) }",
        resultClass = FieldOfStudy.class,
        hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
)
@Entity
@Table(name = "view_field_of_study")
public class FieldOfStudy {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pracoviste")
    private Workplace pracoviste;
    @Column(name = "nazev")
    private String nazev;
    @Column(name = "zkratka")
    private String zkratka;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "FORMY",
            joinColumns = {@JoinColumn(name = "studijni_obor_id")},
            inverseJoinColumns = {@JoinColumn(name = "forma_vyuky_id")}
    )
    private List<FormOfStudy> forma;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Workplace getPracoviste() {
        return pracoviste;
    }

    public void setPracoviste(Workplace pracoviste) {
        this.pracoviste = pracoviste;
    }


    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }


    public String getZkratka() {
        return zkratka;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }


    public List<FormOfStudy> getForma() {
        return forma;
    }

    public void setForma(List<FormOfStudy> forma) {
        this.forma = forma;
    }

    @Override
    public String toString() {
        return nazev + " " + zkratka;
    }
}
