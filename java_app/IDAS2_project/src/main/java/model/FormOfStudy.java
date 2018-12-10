package model;

import javax.persistence.*;
import java.util.List;

@NamedNativeQuery(
        name = "get_all_forms_of_study",
        query = "{ ? = call get_all_forms_of_study ( ) }",
        resultClass = FormOfStudy.class,
        hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
)
@Entity
@Table(name = "forma_vyuky")
public class FormOfStudy {

    @Id
    @Column(name = "ID")
    private String id;
    @Column(name = "NAZEV_FORMY")
    private String nazevFormy;
    @ManyToMany(mappedBy = "forma")
    private List<FieldOfStudy> studijniObory;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getNazevFormy() {
        return nazevFormy;
    }

    public void setNazevFormy(String nazevFormy) {
        this.nazevFormy = nazevFormy;
    }

    public List<FieldOfStudy> getStudijniObory() {
        return studijniObory;
    }

    public void setStudijniObory(List<FieldOfStudy> studijniObory) {
        this.studijniObory = studijniObory;
    }

    @Override
    public String toString() {
        return nazevFormy;
    }
}
