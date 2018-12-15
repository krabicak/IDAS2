package model;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "get_all_rooms",
                query = "{ ? = call get_all_rooms ( ) }",
                resultClass = Room.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )
})
@Entity
@Table(name = "UCEBNA")
public class Room {

    @Column(name = "POPIS")
    private String popis;
    @Column(name = "OZNACENI")
    private String oznaceni;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    public Room() {
    }

    public Room(String popis, String oznaceni) {
        this.popis = popis;
        this.oznaceni = oznaceni;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }


    public String getOznaceni() {
        return oznaceni;
    }

    public void setOznaceni(String oznaceni) {
        this.oznaceni = oznaceni;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return oznaceni;
    }
}
