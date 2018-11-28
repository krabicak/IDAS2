package model;

import javax.persistence.*;

@Entity
@Table(name = "kategorie_predmetu")
public class CategoryOfSubject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "zkratka")
  private String zkratka;
  @Column(name = "nazev_kategorie")
  private String nazevKategorie;

  public String getZkratka() {
    return zkratka;
  }

  public void setZkratka(String zkratka) {
    this.zkratka = zkratka;
  }


  public String getNazevKategorie() {
    return nazevKategorie;
  }

  public void setNazevKategorie(String nazevKategorie) {
    this.nazevKategorie = nazevKategorie;
  }

}
