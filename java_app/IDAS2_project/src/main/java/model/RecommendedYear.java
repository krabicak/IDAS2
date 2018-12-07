package model;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "get_all_recommended_years",
                query = "{ ? = call get_all_recommended_years () }",
                resultClass = RecommendedYear.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )})
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

  @Override
  public String toString() {
    return cisloRocniku;
  }
}
