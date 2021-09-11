package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Pet {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Enumerated(EnumType.STRING)
   private PetType type;
   @Nationalized // should use @Nationalized instead of @Type=nstring
   private String name;
   @ManyToOne(cascade = CascadeType.PERSIST) //or should i do persist as the cascadetype?
   private Customer owner = null;
//   private long ownerId;
   private LocalDate birthDate;
   private String notes;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public PetType getType() {
      return type;
   }

   public void setType(PetType type) {
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Customer getOwner() {
      return owner;
   }

   public void setOwner(Customer owner) {
      this.owner = owner;
   }

   public LocalDate getBirthDate() {
      return birthDate;
   }

   public void setBirthDate(LocalDate birthDate) {
      this.birthDate = birthDate;
   }

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }


}
