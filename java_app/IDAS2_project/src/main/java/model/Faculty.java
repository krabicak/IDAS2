package model;

import javax.persistence.*;

@NamedNativeQuery(
        name = "get_all_faculties",
        query = "{ ? = call get_all_faculties ( ) }",
        resultClass = Faculty.class,
        hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
)
@Entity
@Table(name = "FAKULTA")
public class Faculty {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private String id;
  @Column(name = "nazev")
  private String nazev;
  @Column(name = "zkratka")
  private String zkratka;


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

  @Override
  public String toString() {
    return zkratka;
  }
}
