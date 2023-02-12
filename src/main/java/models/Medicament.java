package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table( name = " medicament", schema = "public")
public class Medicament {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column (name = "id", unique = true, updatable = false, nullable = false)
    private UUID id;

    @Basic
    @Column(name = "name", nullable = false)
    private  String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn( name = "substance_id")
    private Substance substance;

    @Basic
    @Column(name = "dose", nullable = false)
    private  int dose;

    @Basic
    @Column(name = "number", nullable = false)
    private int number;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDose() {
        return dose;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public Substance getSubstance() {
        return substance;
    }

    public void setSubstance(Substance substance) {
        this.substance = substance;
    }
}
