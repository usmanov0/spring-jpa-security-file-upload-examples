package com.example.hibernateexample.repository;

import com.example.hibernateexample.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

//    //types of query
//    //1
//    @Query("select s from Student s where s.name = :name and s.surname = :surname")
//    List<Student> findAllByNameAndSurname(@Param("name") String name,
//                                          @Param("surname") String surname);
//
//    //2
//    List<Student> findAllByNameAAndSurname(String name, String surname);
//
//    //3
//    @Query(value = "select * from student s where s.name = :name and s.surname = :surname", nativeQuery = true)
//    List<Student> findAllByNameandSurname(@Param("name") String name, @Param("surname") String surname);
//
//    //Distinct   select distinct table_name where s.name = ?1 and s.surname = ?2
//    Student findDistinctByName(String name);
//
//
//    //Or findByLastnameOrFirstname     // where s.name = ?1 or s.surname = ?2
//    Student findByNameOrSurname(String name, String surname);
//
//
//    // Is, Equals   findByFirstname,findByFirstnameIs,findByFirstnameEquals     //… where x.firstname = ?1
//    Student findByNameEquals(String name);
//
//    // OrderBy          // where s.age = ?1 order by s.name asc
//    Student findByAgeOrderByNameAsc(int age);
//
//
//    //Between   findByAgeBetween    / where s.age between ?1 and ?2
//    Student findByAgeBetween(int age1, int age2);
//
//
//    // LessThan  // findByAgeLessThan  // … where s.age <= ?1
//    Student findByAgeLessThanEqual(int age);
//
//
//    // GreaterThan  // findByAgeGreaterThan  // … where s.age >= ?1
//    Student findByAgeGreaterThanEqual(int age);
//
//
//    // In // findByAgeIn(Collection<Age> ages)  // … where x.age in ?1
//    Student findByAgeIn(Collection<Integer> age);
//
//
//    // Not // findByAgeNot  // … where x.lastname <> ?1
//    Student findByNameNot(String name);
//
//
//    //Like findByFirstnameLike      … where s.name like ?1
//    Student findByNameLike(String name);
//
//    //StartingWith //findByNameStartingWith    … where s.name like ?1 (parameter bound with appended %)
//    Student findByNameStartingWith(String name);
//
//    // EndingWith //findByNameEndingWith  .. where s.name like ?1
//    Student findByNameEndingWith(String name);
}
