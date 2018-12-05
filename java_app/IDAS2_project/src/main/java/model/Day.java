package model;

import javax.persistence.*;

@NamedNativeQuery(
        name = "get_all_days",
        query = "{ ? = call get_all_days ( ) }",
        resultClass = Day.class,
        hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
)
@Entity
@Table(name = "dny")
public class Day {

    @Id
    @Column(name = "ZKRATKA")
    private String shortcut;
    @Column(name = "DETAIL")
    private String fullname;

    public Day() {
    }

    public Day(String shortcut, String fullname) {
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
