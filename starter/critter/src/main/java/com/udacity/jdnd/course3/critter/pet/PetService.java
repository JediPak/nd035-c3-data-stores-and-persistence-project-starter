package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PetService {

   @Autowired
   PetRepository petRepository;

   @Autowired
   CustomerRepository customerRepository;

   public Pet savePet(Pet pet){
      Pet savedPet =  petRepository.save(pet);
      Customer customer = savedPet.getOwner();
      customer.getPets().add(savedPet);
      customerRepository.save(customer);

      return savedPet;
   }

   public Pet getPet(Long petId) {
      return petRepository.findById(petId).get();
   }
   public List <Pet> getPets(){
      return petRepository.findAll();
   }
   public List<Pet> getPetsByOwner(Long ownerId){
      return petRepository.getPetsByOwner(ownerId);
   }
}
