package model;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "get_all_obligations",
                query = "{ ? = call get_all_obligations () }",
                resultClass = Obligation.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )})
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
    return typ;
  }
}
