package model;

import javax.persistence.*;

@Entity
@Table(name = "FAKULTA")
public class Faculty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private String id;
  @Column(name = "nazev")
  private String nazev;
  @Column(name = "zkratka")
  private String zkratka;


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

  @Override
  public String toString() {
    return "Faculty{" +
            "id='" + id + '\'' +
            ", nazev='" + nazev + '\'' +
            ", zkratka='" + zkratka + '\'' +
            '}';
  }
}
