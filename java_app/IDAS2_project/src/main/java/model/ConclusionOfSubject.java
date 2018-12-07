package model;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "get_all_conclusions_of_subject",
                query = "{ ? = call get_all_conclusions_of_subject () }",
                resultClass = ConclusionOfSubject.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )})
@Entity
@Table(name = "zakonceni_predmetu")
public class ConclusionOfSubject {

  @Id
  @Column(name = "zkratka")
  private String zkratka;
  @Column(name = "nazev_zakonceni")
  private String nazevZakonceni;


  public String getZkratka() {
    return zkratka;
  }

  public void setZkratka(String zkratka) {
    this.zkratka = zkratka;
  }


  public String getNazevZakonceni() {
    return nazevZakonceni;
  }

  public void setNazevZakonceni(String nazevZakonceni) {
    this.nazevZakonceni = nazevZakonceni;
  }

  @Override
  public String toString() {
    return nazevZakonceni;
  }
}
