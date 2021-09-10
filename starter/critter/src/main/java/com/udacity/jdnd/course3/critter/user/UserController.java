package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.*;
import com.udacity.jdnd.course3.critter.schedule.Schedule;
import com.udacity.jdnd.course3.critter.schedule.ScheduleDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    CustomerService customerService;

    @Autowired
    PetService petService;

    @GetMapping("/example-customer")
    public CustomerDTO getExampleCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
//        customerDTO.setId(101L);
        customerDTO.setName("name");
        customerDTO.setNotes("notes");
        customerDTO.setPhoneNumber("111-222-3333");
        customerDTO.setPetIds(asList(1L, 2L, 3L));
        return customerDTO;
    }

    @GetMapping("/example-employee")
    public EmployeeDTO getExampleEmployee() {
        Set<EmployeeSkill> skillSet = new HashSet <>();
        skillSet.add(EmployeeSkill.PETTING);
        skillSet.add(EmployeeSkill.SHAVING);
        skillSet.add(EmployeeSkill.FEEDING);

        Set<DayOfWeek> dayOfWeekSet = new HashSet <>();
        dayOfWeekSet.add(DayOfWeek.MONDAY);
        dayOfWeekSet.add(DayOfWeek.WEDNESDAY);
        dayOfWeekSet.add(DayOfWeek.FRIDAY);

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setName("name");
        employeeDTO.setSkills(skillSet);
        employeeDTO.setDaysAvailable(dayOfWeekSet);
        return employeeDTO;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
//        throw new UnsupportedOperationException();
        Customer customer = convertDTO2Entity_customer(customerDTO);
        Customer customerSaved = customerService.saveCustomer(customer);

        return convertEntity2DTO_customer(customerSaved);
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
//        throw new UnsupportedOperationException();
        List<CustomerDTO> customerDTOList = new ArrayList <>();
        for(Customer customer : customerService.getAllCustomers()){
            customerDTOList.add(convertEntity2DTO_customer(customer));
        }
        return customerDTOList;
    }

    @GetMapping("/employee")
    public List<EmployeeDTO> getAllEmployees(){
//        throw new UnsupportedOperationException();
        List<EmployeeDTO> employeeDTOList = new ArrayList <>();
        for(Employee employee : employeeService.getAllEmployees()){
            employeeDTOList.add(convertEntity2DTO_employee(employee));
        }
        return employeeDTOList;

    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable Long petId){
//        throw new UnsupportedOperationException();
        Customer customer = customerService.getOwnerByPet(petId);
        return convertEntity2DTO_customer(customer);

    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
//        throw new UnsupportedOperationException();
        Employee employee =
                employeeService.saveEmployee(convertDTO2Entity_employee(employeeDTO));
        return convertEntity2DTO_employee(employee);
    }

//    @PostMapping
    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable Long employeeId) {
//        throw new UnsupportedOperationException();
        Employee employee = employeeService.getEmployee(employeeId);
        return convertEntity2DTO_employee(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public EmployeeDTO setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable Long employeeId) {
//        throw new UnsupportedOperationException();
        Employee employee = employeeService.setAvailability(daysAvailable, employeeId);
        return convertEntity2DTO_employee(employee);
    }

//    @GetMapping
    @PostMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
//        throw new UnsupportedOperationException();
        List<Employee> employeeList = employeeService.findEmployeesForService(employeeDTO.getSkills(), employeeDTO.getDate());

        List<EmployeeDTO> employeeDTOList = new ArrayList <>();
        for(Employee employee : employeeList){
            employeeDTOList.add(convertEntity2DTO_employee(employee));
        }
        return employeeDTOList;
    }

    private EmployeeDTO convertEntity2DTO_employee(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private Employee convertDTO2Entity_employee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private CustomerDTO convertEntity2DTO_customer(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO, "owner");

        // finding all pets associated with customer and setting pet ids
        List<Pet> petList = petService.getPetsByOwner(customer.getId());
        List<Long> petIds = new ArrayList <>();
        for (Pet pet : petList){
            petIds.add(pet.getId());
        }
        customerDTO.setPetIds(petIds);
        return customerDTO;
    }

    private Customer convertDTO2Entity_customer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        // no need to add in pets for entity,
        // since the only place this method is used is for [findEmployeesForService]
        return customer;
    }

}
