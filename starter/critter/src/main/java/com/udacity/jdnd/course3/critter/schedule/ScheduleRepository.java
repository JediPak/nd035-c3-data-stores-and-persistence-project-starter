package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


//   List<Schedule> getScheduleForPet(long petId){
//   }
//
//   List<Schedule> getScheduleForEmployee(long employeeId){
//
//   }
//
//   List<Schedule> getScheduleForCustomer(long customerId){
//
//   }
}
