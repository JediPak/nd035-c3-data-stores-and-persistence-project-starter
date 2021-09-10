package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SchedulerService {
   @Autowired
   ScheduleRepository scheduleRepository;

   public Schedule saveSchedule(Schedule schedule){
      return scheduleRepository.save(schedule);
   }

   public List<Schedule> getAllSchedules(){
      return scheduleRepository.findAll();
   }

//   List<Schedule> getScheduleForPet(long petId){
//      return scheduleRepository.getScheduleForPet(petId);
//   }
//   public List<Schedule> getScheduleForEmployee(long employeeId){
//      return scheduleRepository.getScheduleForEmployee(employeeId);
//   }
//   List<Schedule> getScheduleForCustomer(long customerId) {
//      return scheduleRepository.getScheduleForCustomer(customerId);
//   }

}
