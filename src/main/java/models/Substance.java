package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "substance", schema = "public")
public class Substance {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private UUID id;

    @Basic
    @Column(name = "mnn", nullable = false)
    private  String mnn;

    @Basic
    @Column(name = "farmgroup", nullable = false)
    private  String  farmgroup;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }


    public String getMnn() {
        return mnn;
    }

    public void setMnn(String mnn) {
        this.mnn = mnn;
    }

    public String getFarmgroup() {
        return farmgroup;
    }

    public void setFarmgroup(String farmgroup) {
        this.farmgroup = farmgroup;
    }
}
