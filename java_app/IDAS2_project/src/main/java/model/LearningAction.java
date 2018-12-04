package model;

import javax.persistence.*;

@Entity
@Table(name = "VIEW_LEARNING_ACTION")
public class LearningAction {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "KAPACITA")
    private String kapacita;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ZPUSOB_VYUKY")
    private MethodOfLearning zpusobVyuky;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VYUCUJICI")
    private Teacher vyucujici;
    @Column(name = "POCATEK")
    private String pocatek;
    @Column(name = "KONEC")
    private String konec;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEN")
    private DayOfWeek den;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PREDMET")
    private Subject predmet;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UCEBNA")
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

    public DayOfWeek getDen() {
        return den;
    }

    public void setDen(DayOfWeek den) {
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
}
