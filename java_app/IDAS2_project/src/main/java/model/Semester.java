package model;

import javax.persistence.*;
import java.util.List;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "get_all_semesters",
                query = "{ ? = call get_all_semesters () }",
                resultClass = Semester.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )})
@Entity
@Table(name = "semestr")
public class Semester {

  @Id
  @Column(name = "id")
  private String id;
  @Column(name = "nazev_semestru")
  private String nazevSemestru;
  @ManyToMany(mappedBy = "semestr")
  private List<Subject> subjects;

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

  @Override
  public String toString() {
    return nazevSemestru;
  }
}
