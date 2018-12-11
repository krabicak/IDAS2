package model;

import javax.persistence.*;
import java.sql.Blob;

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "get_photo_by_id",
                query = "{ ? = call get_photo_by_id ( ? ) }",
                resultClass = Photo.class,
                hints = @javax.persistence.QueryHint(name = "org.hibernate.callable", value = "true")
        )})
@Entity
@Table(name = "FOTKA")
public class Photo {

    @Id
    @Column(name = "PRACOVNICI_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Lob
    @Column(name = "obrazek", columnDefinition = "BLOB")
    private byte[] obrazek;
    @Column(name = "info")
    private String info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public byte[] getObrazek() {
        return obrazek;
    }

    public void setObrazek(byte[] obrazek) {
        this.obrazek = obrazek;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
