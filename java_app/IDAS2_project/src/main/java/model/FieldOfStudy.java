package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "view_field_of_study")
public class FieldOfStudy {

  @Id
  @Column(name = "id")
  private String id;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "pracoviste")
  private Workplace pracoviste;
  @Column(name = "nazev")
  private String nazev;
  @Column(name = "zkratka")
  private String zkratka;
  @OneToMany(mappedBy = "formaVyukyId")
  private List<Forms> forma;


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


  public List<Forms> getForma() {
    return forma;
  }

  public void setForma(List<Forms> forma) {
    this.forma = forma;
  }

}
