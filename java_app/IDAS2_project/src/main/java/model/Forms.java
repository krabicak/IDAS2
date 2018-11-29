package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "formy")
public class Forms implements Serializable {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STUDIJNI_OBOR_ID")
    private FieldOfStudy studijniOborId;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FORMA_VYUKY_ID")
    private FormsOfStudy formaVyukyId;


    public FieldOfStudy getStudijniOborId() {
        return studijniOborId;
    }

    public void setStudijniOborId(FieldOfStudy studijniOborId) {
        this.studijniOborId = studijniOborId;
    }


    public FormsOfStudy getFormaVyukyId() {
        return formaVyukyId;
    }

    public void setFormaVyukyId(FormsOfStudy formaVyukyId) {
        this.formaVyukyId = formaVyukyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forms forms = (Forms) o;
        return Objects.equals(studijniOborId, forms.studijniOborId) &&
                Objects.equals(formaVyukyId, forms.formaVyukyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studijniOborId, formaVyukyId);
    }
}
