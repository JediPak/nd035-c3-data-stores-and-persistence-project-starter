package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

   @Query(
//           "Select s " +
//                   "from Schedule s inner join Pet p " +
//                   "WHERE p.id = :customerId"

           "Select s " +
           "from Schedule s inner join s.pets p " +
               "inner join p.owner c " +
//               "ON p.customer_id = c.id " +
           "WHERE c.id = :customerId"
   )
   List<Schedule> getScheduleForCustomer(long customerId);
}
