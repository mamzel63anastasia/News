package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
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

//    @ManyToMany(cascade = {CascadeType.ALL})
////    @JoinTable( name = "medicament_order",
////     joinColumns = { @JoinColumn(name = "order_id")},
////    inverseJoinColumns = { @JoinColumn( name = "medicament_id")})
////    Set<Medicament> medicaments = new HashSet<>();

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn( name = "user_id")
    private User user;




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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
