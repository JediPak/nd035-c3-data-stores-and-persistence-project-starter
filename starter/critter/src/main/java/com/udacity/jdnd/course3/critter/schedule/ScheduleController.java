package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetDTO;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

import static java.util.Arrays.asList;


/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    SchedulerService schedulerService;

    @Autowired
    PetService petService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/example")
    public ScheduleDTO getExampleSchedule() {
        Set<EmployeeSkill> skillSet = new HashSet <>();
        skillSet.add(EmployeeSkill.FEEDING);
        skillSet.add(EmployeeSkill.MEDICATING);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate(LocalDate.now());
        scheduleDTO.setActivities(skillSet);
        scheduleDTO.setEmployeeIds(asList(1L, 2L, 3L));
        scheduleDTO.setPetIds(asList(4L, 5L, 6L));

        return scheduleDTO;
    }

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
//        throw new UnsupportedOperationException();
        Schedule schedule = convertDTO2Entity(scheduleDTO);
        return convertEntity2DTO(schedulerService.saveSchedule(schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
//        throw new UnsupportedOperationException();
        List<ScheduleDTO> scheduleDTOList = new ArrayList <>();
        for(Schedule schedule : schedulerService.getAllSchedules()){
            scheduleDTOList.add(convertEntity2DTO(schedule));
        }
        return scheduleDTOList;
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable Long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable Long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable Long customerId) {
        throw new UnsupportedOperationException();
    }

    private ScheduleDTO convertEntity2DTO(Schedule schedule){
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        BeanUtils.copyProperties(schedule, scheduleDTO);

        List<Long> petIdList = new ArrayList<>();
        for(Pet pet : schedule.getPets()){
            petIdList.add(pet.getId());
        }
        scheduleDTO.setPetIds(petIdList);

        List<Long> employeeIdList = new ArrayList<>();
        for(Employee employee : schedule.getEmployees()){
            employeeIdList.add(employee.getId());
        }
        scheduleDTO.setEmployeeIds(employeeIdList);

        return scheduleDTO;
    }

    private Schedule convertDTO2Entity(ScheduleDTO scheduleDTO){
        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule, "employees", "pets");

        List<Pet> petList = new ArrayList<>();
        for(Long petId : scheduleDTO.getPetIds()){
            petList.add(petService.getPet(petId));
        }
        schedule.setPets(petList);

        List<Employee> employeeList = new ArrayList<>();
        for(Long employeeId : scheduleDTO.getEmployeeIds()){
            employeeList.add(employeeService.getEmployee(employeeId));
        }
        schedule.setEmployees(employeeList);

        return schedule;
    }
}
