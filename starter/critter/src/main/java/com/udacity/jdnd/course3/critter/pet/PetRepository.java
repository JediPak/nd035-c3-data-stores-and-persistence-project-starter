package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {

   @Query("FROM Pet p WHERE p.owner.id = :ownerId")
   public List<Pet> getPetsByOwner(long ownerId);

//   EXAMPLE OF USING QUERY
//   List<Delivery> deliveryList = new ArrayList <>();
//      TypedQuery<Delivery> query =
//              entityManager.createNamedQuery("Delivery.findAllDeliveryByName", Delivery.class);
//      query.setParameter("name", name);
//      return query.getResultList();

   @Query("SELECT p.owner FROM Pet p WHERE p.id = :petId")
   public Long getOwnerByPet(Long petId);
}
