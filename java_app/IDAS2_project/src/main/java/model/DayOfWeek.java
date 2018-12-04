package model;

import javax.persistence.*;

@NamedNativeQuery(
        name = "get_days_of_week",
        query = "{ ? = call get_days_of_week ( ) }",
        resultClass = DayOfWeek.class,
        hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
)
@Entity
@Table(name = "dny")
public class DayOfWeek {

    @Id
    @Column(name = "ZKRATKA")
    private String shortcut;
    @Column(name = "DETAIL")
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
    public String toString() {
        return fullname;
    }

}
