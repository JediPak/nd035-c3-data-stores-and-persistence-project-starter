package com.udacity.jdnd.course3.critter.pet;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    PetService petService;

    @Autowired
    CustomerService customerService;

    @GetMapping("/example")
    public PetDTO getExamplePet() {
        PetDTO petDTO = new PetDTO();
        petDTO.setName("name");
        petDTO.setNotes("notes");
        petDTO.setType(PetType.LIZARD);
        petDTO.setBirthDate(LocalDate.now());
        petDTO.setOwnerId(102L);
        return petDTO;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
//        throw new UnsupportedOperationException();
        Pet pet = convertDTO2Entity(petDTO);
        Pet petSaved = petService.savePet(pet);
        return convertEntity2DTO(petSaved);

    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable Long petId) {
//        throw new UnsupportedOperationException();
        Pet pet = petService.getPet(petId);
        return convertEntity2DTO(pet);
    }

    @GetMapping
    public List<PetDTO> getPets(){
//        throw new UnsupportedOperationException();
        List<PetDTO> petDTOList = new ArrayList <>();
        for(Pet pet : petService.getPets()){
            petDTOList.add(convertEntity2DTO(pet));
        }
        return petDTOList;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable Long ownerId) {
//        throw new UnsupportedOperationException();
        List<PetDTO> petDTOList = new ArrayList <>();
        for(Pet pet : petService.getPetsByOwner(ownerId)){
            petDTOList.add(convertEntity2DTO(pet));
        }
        return petDTOList;
    }

    private PetDTO convertEntity2DTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet, petDTO, "owner");
        if (pet.getOwner() != null)
            petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }

    private Pet convertDTO2Entity(PetDTO petDTO) {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet, "ownerId");
        if (petDTO.getOwnerId() != null) {
            Customer customer = customerService.findById(petDTO.getOwnerId());
            pet.setOwner(customer);
        }
        return pet;
    }
}
