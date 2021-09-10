package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
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

   @Autowired
   PetRepository petRepository;

   @Autowired
   EmployeeRepository employeeRepository;

   @Autowired
   CustomerRepository customerRepository;

   public Schedule saveSchedule(Schedule schedule){
      return scheduleRepository.save(schedule);
   }

   public List<Schedule> getAllSchedules(){
      return scheduleRepository.findAll();
   }

   List<Schedule> getScheduleForPet(long petId){
      List<Schedule> schedules = scheduleRepository.findAll();

      Pet pet = petRepository.getOne(petId);

      schedules.removeIf(s -> !s.getPets().contains(pet));

      return schedules;
   }
   public List<Schedule> getScheduleForEmployee(long employeeId){
      List<Schedule> schedules = scheduleRepository.findAll();

      Employee employee = employeeRepository.getOne(employeeId);

      schedules.removeIf(s -> !s.getEmployees().contains(employee));

      return schedules;
   }

   List<Schedule> getScheduleForCustomer(long customerId) {
//      List<Schedule> schedules = scheduleRepository.findAll();
//
//      Customer customer = customerRepository.getOne(customerId);
//      for (Schedule schedule : schedules) {
//         if (!schedule.getPets()..contains(customer))
//            schedules.remove(schedule);
//      }
//      return schedules;
      return scheduleRepository.getScheduleForCustomer(customerId);
   }

}
