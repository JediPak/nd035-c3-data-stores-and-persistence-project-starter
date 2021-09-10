package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//   @Query(
//           "FROM Employee e" +
//                   " where :numberOfSkills = ( " +
//                   "    select count(distinct e2.skills) from Employee e2 inner join e2.skills " +
//                   "    where e2.id = e.id and e2.skills IN ( :skills ) " +
//                   " )"

//           "select count(distinct e2.skills) from Employee e2 inner join e2.skills " +
//           "    where e2.skills IN ( :skills ) "

//           "from Employee e " +
//           "where :numberOfSkills = (" +
//                       "select count(e.id)" +
//                       "from Employee e2" +
//                       "inner join e2.skills skills" +
//                       "where bookSkill.id in :setOfTagsIds" +
//                       "and book2.id = book.id" +
//                                    ")"
//           "from Employee e" +
//                   "where not exists (" +
//                   "select tag.id from Tag tag" +
//                   "where tag.id in :setOfTagsIds" +
//                   "and tag.id not in (" +
//                   "select bookTag.id" +
//                   "from Book book2" +
//                   "inner join book2.tags bookSkill" +
//                   "where book2.id = book.id))"

//           select book from Book book
//           where not exists (
//           select tag.id from Tag tag
//           where tag.id in :setOfTagsIds
//           and tag.id not in (
//           select bookTag.id
//           from Book book2
//           inner join book2.tags bookSkill
//           where book2.id = book.id))

//           "select book from Book book
//           where :numberOfTags = (
//           select count(tag.id)
//           from Book book2
//           inner join book2.tags bookTag
//           where bookSkill.id in :setOfTagsIds
//           and book2.id = book.id)"
//   )
//   List<Employee> findEmployeesForServiceEmployeeSkill(Set <EmployeeSkill> skills, Long numberOfSkills);
//
//   List<Employee> findEmployeesForServiceEmployeeSkill(Set <EmployeeSkill> skills, Long numberOfSkills);
   List<Employee> findAllByDaysAvailableContaining(DayOfWeek day);


//   select b from Book b
//   where :numberOfTags = (
//      select count(distinct tag.tagName) from Book b2
//      inner join b2.tags tag where b2.id = b.id and tag.tagName IN (:tagNames)
//   )
}
