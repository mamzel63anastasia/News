package models;

import javax.persistence.*;

@Entity
@Table(name = "substance", schema = "public")
public class Substance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;

    @Basic
    @Column(name = "mnn", nullable = false)
    private  String mnn;

    @Basic
    @Column(name = "farmGroup", nullable = false)
    private  String farmGroup;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getMnn() {
        return mnn;
    }

    public void setMnn(String mnn) {
        this.mnn = mnn;
    }

    public String getFarmGroup() {
        return farmGroup;
    }

    public void setFarmGroup(String farmGroup) {
        this.farmGroup = farmGroup;
    }
}
