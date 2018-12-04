package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zpusob_vyuky")
public class MethodOfLearning {

  @Id
  @Column(name = "ZKRATA")
  private String zkrata;
  @Column(name = "NAZEV_ZPUSOBU")
  private String nazevZpusobu;


  public String getZkrata() {
    return zkrata;
  }

  public void setZkrata(String zkrata) {
    this.zkrata = zkrata;
  }


  public String getNazevZpusobu() {
    return nazevZpusobu;
  }

  public void setNazevZpusobu(String nazevZpusobu) {
    this.nazevZpusobu = nazevZpusobu;
  }

    @Override
    public String toString() {
        return nazevZpusobu;
    }
}
