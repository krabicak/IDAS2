package model;

import javax.persistence.*;

@NamedNativeQuery(
        name = "get_all_methods_of_learning",
        query = "{ ? = call get_all_method_of_learning ( ) }",
        resultClass = MethodOfLearning.class,
        hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
)
@Entity
@Table(name = "zpusob_vyuky")
public class MethodOfLearning {

    @Id
    @Column(name = "ZKRATA")
    private String zkrata;
    @Column(name = "NAZEV_ZPUSOBU")
    private String nazevZpusobu;


    public String getZkrata() {
        return zkrata;
    }

    public void setZkrata(String zkrata) {
        this.zkrata = zkrata;
    }


    public String getNazevZpusobu() {
        return nazevZpusobu;
    }

    public void setNazevZpusobu(String nazevZpusobu) {
        this.nazevZpusobu = nazevZpusobu;
    }

    @Override
    public String toString() {
        return nazevZpusobu;
    }
}
