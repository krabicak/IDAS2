package model;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @author hotov
 */
@SqlResultSetMapping(
        name = "days_of_week",
        entities = {
                @EntityResult(
                        entityClass = DayOfWeek.class,
                        fields = {
                                @FieldResult(
                                        name = "shortcut",
                                        column = "shortcut"
                                )
                                ,
                                @FieldResult(
                                        name = "fullname",
                                        column = "fullname"
                                ),}
                )
        }
)
@NamedNativeQuery(
        name = "get_days_of_week",
        query = "{ ? = call get_days_of_week ( ) }",
        resultSetMapping = "days_of_week",
        hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
)
@Entity
public class DayOfWeek implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String shortcut;
    private String fullname;

    public DayOfWeek() {
    }

    public DayOfWeek(String shortcut, String fullname) {
        this.shortcut = shortcut;
        this.fullname = fullname;
    }

    public String getShortcut() {
        return shortcut;
    }

    public void setShortcut(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shortcut != null ? shortcut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DayOfWeek)) {
            return false;
        }
        DayOfWeek other = (DayOfWeek) object;
        if ((this.shortcut == null && other.shortcut != null) || (this.shortcut != null && !this.shortcut.equals(other.shortcut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Day[ id=" + shortcut + " ]";
    }

}
