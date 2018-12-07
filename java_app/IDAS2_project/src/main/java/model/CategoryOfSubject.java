package model;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "get_all_categories_of_subject",
                query = "{ ? = call get_all_categories_of_subject () }",
                resultClass = CategoryOfSubject.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )})
@Entity
@Table(name = "kategorie_predmetu")
public class CategoryOfSubject {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "zkratka")
  private String zkratka;
  @Column(name = "nazev_kategorie")
  private String nazevKategorie;

  public String getZkratka() {
    return zkratka;
  }

  public void setZkratka(String zkratka) {
    this.zkratka = zkratka;
  }


  public String getNazevKategorie() {
    return nazevKategorie;
  }

  public void setNazevKategorie(String nazevKategorie) {
    this.nazevKategorie = nazevKategorie;
  }

  @Override
  public String toString() {
    return nazevKategorie;
  }
}
