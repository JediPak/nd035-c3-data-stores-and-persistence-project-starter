package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService {

   @Autowired
   CustomerRepository customerRepository;

   @Autowired
   PetRepository petRepository;

   public Customer saveCustomer(Customer customer){
      return customerRepository.save(customer);
   }

   public Customer findById(Long customerId){
      return customerRepository.findById(customerId).get();
   }

   public List <Customer> getAllCustomers(){
      return customerRepository.findAll();
   }

   public Customer getOwnerByPet(Long petId){
      return petRepository.findById(petId).get().getOwner();
//      Long ownerId = petRepository.getOwnerByPet(petId);
//      Optional <Customer> customerOptional = customerRepository.findById(ownerId);
//      if(customerOptional.isPresent())
//         return customerOptional.get();
//      else
//         return null;
   }
}
