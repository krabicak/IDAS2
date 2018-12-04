package model;

import javax.persistence.*;

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
