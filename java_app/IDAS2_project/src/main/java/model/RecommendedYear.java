package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doporuceny_rocnik")
public class RecommendedYear {

  @Id
  @Column(name = "cislo_rocniku")
  private String cisloRocniku;

  public String getCisloRocniku() {
    return cisloRocniku;
  }

  public void setCisloRocniku(String cisloRocniku) {
    this.cisloRocniku = cisloRocniku;
  }

}
