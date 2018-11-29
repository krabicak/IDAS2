package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "semestr")
public class Semester {

  @Id
  @Column(name = "id")
  private String id;
  @Column(name = "nazev_semestru")
  private String nazevSemestru;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getNazevSemestru() {
    return nazevSemestru;
  }

  public void setNazevSemestru(String nazevSemestru) {
    this.nazevSemestru = nazevSemestru;
  }

}
