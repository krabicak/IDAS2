package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "uvazek")
public class Obligation {

  @Id
  @Column(name = "typ")
  private String typ;

  public String getTyp() {
    return typ;
  }

  public void setTyp(String typ) {
    this.typ = typ;
  }

  @Override
  public String toString() {
    return "Obligation{" +
            "typ='" + typ + '\'' +
            '}';
  }
}
