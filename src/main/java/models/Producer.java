package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

 @Entity
 @Table( name = "producer", schema = "public")
public class Producer {
     @Id
     @GeneratedValue (generator = "UUID")
     @GenericGenerator(
             name =  "UUID",
             strategy = "org.hibernate.id.UUIDGeneration"
     )
     @Column(name = "id", unique = true, updatable = false, nullable = false)
    private UUID id;

     @Basic
     @ Column(name = "producer_name", nullable = false)
    private  String name;

     @Basic
     @Column(name = "country", nullable =  false)
    private String country;

     public Producer(String name, String country) {
         this.name = name;
         this.country = country;
     }


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

     public String getCountry() {
         return country;
     }

     public void setCountry(String country) {
         this.country = country;
     }
 }
