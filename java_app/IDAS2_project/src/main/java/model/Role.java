package model;

import javax.persistence.*;


@NamedNativeQueries({
        @NamedNativeQuery(
                name = "get_all_role",
                query = "{ ? = call get_all_role() }",
                resultClass = Role.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )})
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "zkratka")
    private String zkratka;

    public String getZkratka() {
        return zkratka;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    @Override
    public String toString() {
        return zkratka;
    }
}
