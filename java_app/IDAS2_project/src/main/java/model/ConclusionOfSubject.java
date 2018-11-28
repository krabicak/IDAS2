package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zakonceni_predmetu")
public class ConclusionOfSubject {

  @Id
  @Column(name = "zkratka")
  private String zkratka;
  @Column(name = "nazev_zakonceni")
  private String nazevZakonceni;


  public String getZkratka() {
    return zkratka;
  }

  public void setZkratka(String zkratka) {
    this.zkratka = zkratka;
  }


  public String getNazevZakonceni() {
    return nazevZakonceni;
  }

  public void setNazevZakonceni(String nazevZakonceni) {
    this.nazevZakonceni = nazevZakonceni;
  }

}
