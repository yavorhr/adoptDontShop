package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.Breed;
import softuni.adoptdontshop.Model.Entity.Dog;

import java.lang.annotation.Native;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {

    @Query(value = "SELECT * FROM dogs d WHERE d.is_adopted=false ORDER BY d.id DESC LIMIT 8", nativeQuery = true)
    List<Dog> findDogsHomePage();

    Optional<Dog> findByNameAndAge(String name, Integer age);

//    @Query("SELECT d.breed.name FROM Dog d ORDER BY d.breed.name ASC ")
//    List<String> findAllBreedNames();
//
//    Integer findByAdoptedOnBetween(LocalDate adoptedOn, LocalDate adoptedOn2);

    @Query(
            value = "SELECT Count(*) FROM dogs d WHERE d.adopted_on BETWEEN '2021-01-01 04:00:00' AND '2021-03-31 04:00:00'",
            nativeQuery = true)
    Integer findAdoptedDogsFirstQuarter();

    @Query(
            value = "SELECT Count(*) FROM dogs d WHERE d.adopted_on BETWEEN '2021-04-01 04:00:00' AND '2021-06-30 04:00:00'",
            nativeQuery = true)
    Integer findAdoptedDogsSecondQuarter();


    @Query(
            value = "SELECT Count(*) FROM dogs d WHERE d.adopted_on BETWEEN '2021-07-01 04:00:00' AND '2021-09-30 04:00:00'",
            nativeQuery = true)
    Integer findAdoptedDogsThirdQuarter();


    @Query(
            value = "SELECT Count(*) FROM dogs d WHERE d.adopted_on BETWEEN '2021-10-01 04:00:00' AND '2021-12-31 04:00:00'",
            nativeQuery = true)
    Integer findAdoptedDogsFourthQuarter();
}
