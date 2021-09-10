package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeSkill;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Schedule {
   @Id
   @GeneratedValue
   private Long id;

   @OneToMany()//(mappedBy = "employee", fetch = FetchType.LAZY)
   private List<Employee> employees = new ArrayList<>();
//   private List <Long> employeeIds;

   @OneToMany//(mappedBy = "pets", fetch = FetchType.LAZY)
   private List<Pet> pets = new ArrayList <>();
//   private List<Long> petIds;

   private LocalDate date;
   @ElementCollection(fetch = FetchType.EAGER)
   @Enumerated(EnumType.STRING)
   private Set <EmployeeSkill> activities;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public List <Employee> getEmployees() {
      return employees;
   }

   public void setEmployees(List <Employee> employees) {
      this.employees = employees;
   }

   public List <Pet> getPets() {
      return pets;
   }

   public void setPets(List <Pet> pets) {
      this.pets = pets;
   }

   public LocalDate getDate() {
      return date;
   }

   public void setDate(LocalDate date) {
      this.date = date;
   }

   public Set <EmployeeSkill> getActivities() {
      return activities;
   }

   public void setActivities(Set <EmployeeSkill> activities) {
      this.activities = activities;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Schedule)) return false;
      Schedule schedule = (Schedule) o;
      return id == schedule.id && Objects.equals(employees, schedule.employees) && Objects.equals(pets, schedule.pets) && Objects.equals(date, schedule.date) && Objects.equals(activities, schedule.activities);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, employees, pets, date, activities);
   }

   @Override
   public String toString() {
      return "Schedule{" +
              "id=" + id +
              ", employees=" + employees +
              ", pets=" + pets +
              ", date=" + date +
              ", activities=" + activities +
              '}';
   }
}
