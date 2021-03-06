package model;

import javax.persistence.*;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "get_learning_actions_by_teacher",
                query = "{ ? = call get_learning_actions_by_teacher ( ? ) }",
                resultClass = LearningAction.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        ),
        @NamedNativeQuery(
                name = "get_all_learning_actions",
                query = "{ ? = call get_all_learning_actions ( ) }",
                resultClass = LearningAction.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        ),
        @NamedNativeQuery(
                name = "get_all_learning_actions_by_room",
                query = "{ ? = call get_all_learning_actions_by_room ( ? ) }",
                resultClass = LearningAction.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )
})
@Entity
@Table(name = "VIEW_LEARNING_ACTION")
public class LearningAction {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "kapacita")
    private String kapacita;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zpusob_vyuky")
    private MethodOfLearning zpusobVyuky;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vyucujici")
    private Teacher vyucujici;
    @Column(name = "pocatek")
    private String pocatek;
    @Column(name = "konec")
    private String konec;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "den")
    private Day den;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "predmet")
    private Subject predmet;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ucebna")
    private Room ucebna;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKapacita() {
        return kapacita;
    }

    public void setKapacita(String kapacita) {
        this.kapacita = kapacita;
    }

    public MethodOfLearning getZpusobVyuky() {
        return zpusobVyuky;
    }

    public void setZpusobVyuky(MethodOfLearning zpusobVyuky) {
        this.zpusobVyuky = zpusobVyuky;
    }

    public Teacher getVyucujici() {
        return vyucujici;
    }

    public void setVyucujici(Teacher vyucujici) {
        this.vyucujici = vyucujici;
    }

    public String getPocatek() {
        return pocatek;
    }

    public void setPocatek(String pocatek) {
        this.pocatek = pocatek;
    }

    public String getKonec() {
        return konec;
    }

    public void setKonec(String konec) {
        this.konec = konec;
    }

    public Day getDen() {
        return den;
    }

    public void setDen(Day den) {
        this.den = den;
    }

    public Subject getPredmet() {
        return predmet;
    }

    public void setPredmet(Subject predmet) {
        this.predmet = predmet;
    }

    public Room getUcebna() {
        return ucebna;
    }

    public void setUcebna(Room ucebna) {
        this.ucebna = ucebna;
    }

    @Override
    public String toString() {
        return (den + " " + ((pocatek != null && konec != null) ? (pocatek + '-' + konec + ' ') : "") + predmet);
    }
}
