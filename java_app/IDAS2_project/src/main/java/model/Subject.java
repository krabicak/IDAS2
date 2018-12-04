package model;

import javax.persistence.*;
import java.util.List;


@NamedNativeQuery(
        name = "get_all_subjects",
        query = "{ ? = call get_all_subjects ( ) }",
        resultClass = Subject.class,
        hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
)
@Entity
@Table(name = "VIEW_SUBJECTS")
public class Subject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id",nullable = false)
  private String id;
  @Column(name = "nazev")
  private String nazev;
  @Column(name = "zkratka")
  private String zkratka;
  @Column(name = "rozsah_hodin")
  private String rozsahHodin;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "kategorie")
  private CategoryOfSubject kategorie;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "zpusob_zakonceni")
  private ConclusionOfSubject zpusobZakonceni;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "garant")
  private Teacher garant;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "doporuceny_rocnik")
  private RecommendedYear doporucenyRocnik;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "SEM",
            joinColumns = {@JoinColumn(name = "predmet_id")},
            inverseJoinColumns = {@JoinColumn(name = "semestr_id")}
    )
  private List<Semester> semestr;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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


  public String getRozsahHodin() {
    return rozsahHodin;
  }

  public void setRozsahHodin(String rozsahHodin) {
    this.rozsahHodin = rozsahHodin;
  }


  public CategoryOfSubject getKategorie() {
    return kategorie;
  }

  public void setKategorie(CategoryOfSubject kategorie) {
    this.kategorie = kategorie;
  }


  public ConclusionOfSubject getZpusobZakonceni() {
    return zpusobZakonceni;
  }

  public void setZpusobZakonceni(ConclusionOfSubject zpusobZakonceni) {
    this.zpusobZakonceni = zpusobZakonceni;
  }


  public Teacher getGarant() {
    return garant;
  }

  public void setGarant(Teacher garant) {
    this.garant = garant;
  }


  public RecommendedYear getDoporucenyRocnik() { return doporucenyRocnik; }

  public void setDoporucenyRocnik(RecommendedYear doporucenyRocnik) { this.doporucenyRocnik = doporucenyRocnik; }

  public List<Semester> getSemestr() {
    return semestr;
  }

  public void setSemestr(List<Semester> semestr) {
    this.semestr = semestr;
  }

    @Override
    public String toString() {
        return zkratka;
    }
}
