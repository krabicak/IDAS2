package model;

import javax.persistence.*;

@Entity
@Table(name = "sem")
public class Sem {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEMESTR_ID")
    private Semester semestrId;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PREDMET_ID")
    private Subject predmetId;


    public Semester getSemestrId() {
        return semestrId;
    }

    public void setSemestrId(Semester semestrId) {
        this.semestrId = semestrId;
    }


    public Subject getPredmetId() {
        return predmetId;
    }

    public void setPredmetId(Subject predmetId) {
        this.predmetId = predmetId;
    }

}
