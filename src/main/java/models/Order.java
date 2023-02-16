package models;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table( name = "order", schema = "public")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, updatable = false, nullable = false)
    private Long id;


    @Basic
    @Column(name = "adress", nullable = false)
    private String adress;

//    @ManyToMany(cascade = {CascadeType.ALL})
////    @JoinTable( name = "medicament_order",
////     joinColumns = { @JoinColumn(name = "order_id")},
////    inverseJoinColumns = { @JoinColumn( name = "medicament_id")})
////    Set<Medicament> medicaments = new HashSet<>()

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn( name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "order")
    private List<Medicament> medicaments;


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Medicament> getMedicament() {
        return medicaments;
    }

    public void setMedicaments(List<Medicament> medicaments) {
        this.medicaments = medicaments;
    }
}
