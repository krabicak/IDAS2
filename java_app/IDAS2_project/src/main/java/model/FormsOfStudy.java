package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "forma_vyuky")
public class FormsOfStudy {

  @Id
  @Column(name = "ID")
  private String id;
  @Column(name = "NAZEV_FORMY")
  private String nazevFormy;
  @ManyToMany(mappedBy = "forma")
  private List<FieldOfStudy> studijniObory;


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
