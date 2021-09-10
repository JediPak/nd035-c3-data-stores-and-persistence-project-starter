package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends User{
//   private long id;
//   private String name;
   private String phoneNumber;
   private String notes;
   @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
   private List<Pet> pets = new ArrayList <>();
//   private List <Long> petIds;

//   public long getId() {
//      return id;
//   }
//
//   public void setId(long id) {
//      this.id = id;
//   }
//
//   public String getName() {
//      return name;
//   }
//
//   public void setName(String name) {
//      this.name = name;
//   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public String getNotes() {
      return notes;
   }

   public void setNotes(String notes) {
      this.notes = notes;
   }

   public List <Pet> getPets() {
      return pets;
   }

   public void setPets(List <Pet> pets) {
      this.pets = pets;
   }
}
