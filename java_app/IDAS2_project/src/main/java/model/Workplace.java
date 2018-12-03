package model;

import javax.persistence.*;

@Entity
@Table(name = "view_workplace")
public class Workplace {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private String id;
  @Column(name = "nazev")
  private String nazev;
  @Column(name = "zkratka")
  private String zkratka;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="fakulta")
  private Faculty fakulta;

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

  public Faculty getFakulta() {
    return fakulta;
  }

  public void setFakulta(Faculty fakulta) {
    this.fakulta = fakulta;
  }

  @Override
  public String toString() {
    return zkratka;
  }
}
