package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "sem")
public class Sem implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sem sem = (Sem) o;
        return Objects.equals(semestrId, sem.semestrId) &&
                Objects.equals(predmetId, sem.predmetId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(semestrId, predmetId);
    }
}
