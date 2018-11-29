package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
        return "Role{" +
                "zkratka='" + zkratka + '\'' +
                '}';
    }
}
