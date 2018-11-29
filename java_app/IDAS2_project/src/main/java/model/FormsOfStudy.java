package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forma_vyuky")
public class FormsOfStudy {

  @Id
  @Column(name = "ID")
  private String id;
  @Column(name = "NAZEV_FORMY")
  private String nazevFormy;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getNazevFormy() {
    return nazevFormy;
  }

  public void setNazevFormy(String nazevFormy) {
    this.nazevFormy = nazevFormy;
  }

}
