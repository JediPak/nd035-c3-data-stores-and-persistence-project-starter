package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

   @Autowired
   EmployeeRepository employeeRepository;

   public Employee saveEmployee(Employee employee){
      return employeeRepository.save(employee);
   }

   public List<Employee> getAllEmployees(){
      return employeeRepository.findAll();
   }

   public Employee getEmployee(Long employeeId) {
      return employeeRepository.findById(employeeId).get();
   }
   public Employee setAvailability(Set <DayOfWeek> daysAvailable, Long employeeId){
      Employee employee = employeeRepository.findById(employeeId).get();
      employee.setDaysAvailable(daysAvailable);
      return employeeRepository.save(employee);
   }
   public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date) {
      DayOfWeek day = date.getDayOfWeek();
      List<Employee> employees;
      if (date != null){
         employees = employeeRepository.findAllByDaysAvailableContaining(day);
      } else {
         employees = employeeRepository.findAll();
      }
//              employeeRepository.findEmployeesForServiceEmployeeSkill(skills, Integer.toUnsignedLong(skills.size()));
      //skill set and date
      if (skills != null & !skills.isEmpty()){
         employees.removeIf(e -> !e.getSkills().containsAll(skills));
//         for ( Employee e : employeeSkill_filtered ) {
//            if (!e.getDaysAvailable().contains(day)){
//               employeeSkill_filtered.remove(e);
//            }
//         }
      }
      return employees;//employeeSkill_filtered;
   }






}
