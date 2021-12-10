package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.Breed;
import softuni.adoptdontshop.Model.Entity.Dog;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogRepository extends JpaRepository<Dog,Long> {

    @Query(value = "SELECT * FROM dogs d WHERE d.is_adopted=false ORDER BY d.id DESC LIMIT 8", nativeQuery = true)
    List<Dog> findDogsHomePage();

    Optional<Dog> findByNameAndAge(String name, Integer age);

    @Query("SELECT d.breed.name FROM Dog d ORDER BY d.breed.name ASC ")
    List<String> findAllBreedNames();
}
