package com.udacity.jdnd.course3.critter.user;

import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   @Nationalized // should use @Nationalized instead of @Type=nstring
   private String name;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
