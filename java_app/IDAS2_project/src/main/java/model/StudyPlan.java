package model;

import javax.persistence.*;
import java.io.Serializable;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "get_all_study_plans",
                query = "{ ? = call get_all_study_plans () }",
                resultClass = StudyPlan.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        ),
        @NamedNativeQuery(
                name = "get_all_study_plan_by_field_of_study",
                query = "{ ? = call GET_STUDY_PLANS_BY_FIELD_OF_STUDY ( ? ) }",
                resultClass = StudyPlan.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )})
@Entity
@Table(name = "studini_plan")
public class StudyPlan implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDIJNI_OBOR_ID")
    private FieldOfStudy studijniObor;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PREDMET_ID")
    private Subject predmet;

    public StudyPlan(Subject predmet) {
        this.predmet = predmet;
    }

    public StudyPlan() {
    }

    public FieldOfStudy getStudijniObor() {
        return studijniObor;
    }

    public void setStudijniObor(FieldOfStudy studijniOborId) {
        this.studijniObor = studijniOborId;
    }

    public Subject getPredmet() {
        return predmet;
    }

    public void setPredmet(Subject predmetId) {
        this.predmet = predmetId;
    }


}
