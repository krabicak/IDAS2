package model;

import javax.persistence.*;

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
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "semestr",nullable = true)
  private Sem semestr;

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
}
