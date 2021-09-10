package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

   @Query(
           "SELECT e FROM Employee e" +
                   " where :numberOfSkills = (" +
                   "    select count(distinct e2.skills) from Employee e2" +
                   "    where e2.id = e.id and e2.skills IN ( :skills )" +
                   " )"
   )
   public List <Employee> findEmployeesForService_EmployeeSkill(int numberOfSkills, Set <EmployeeSkill> skills);
//   select b from Book b
//   where :numberOfTags = (
//      select count(distinct tag.tagName) from Book b2
//      inner join b2.tags tag where b2.id = b.id and tag.tagName IN (:tagNames)
//   )
}
