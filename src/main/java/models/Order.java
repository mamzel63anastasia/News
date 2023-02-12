package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table( name = "order", schema = "public")
public class Order {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private UUID id;

    @Basic
    @Column(name = "adress", nullable = false)
    private String adress;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "medicament_order",
     joinColumns = @JoinColumn(name = "medicament_id"),
    inverseJoinColumns = @JoinColumn( name = "order_id"))



    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
