package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sem")
public class Sem {

  @Id
  @Column(name = "semestr_id")
  private String semestrId;
  @Column(name = "predmet_id")
  private String predmetId;


  public String getSemestrId() {
    return semestrId;
  }

  public void setSemestrId(String semestrId) {
    this.semestrId = semestrId;
  }


  public String getPredmetId() {
    return predmetId;
  }

  public void setPredmetId(String predmetId) {
    this.predmetId = predmetId;
  }

}
