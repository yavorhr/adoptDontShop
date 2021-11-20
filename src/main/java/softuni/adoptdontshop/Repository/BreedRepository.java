package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.Breed;

import java.util.List;
import java.util.Optional;

@Repository
public interface BreedRepository extends JpaRepository<Breed,Long> {

    Optional<Breed> findByName(String name);

    @Query("SELECT b FROM Breed b ORDER BY b.name ASC")
    List<Breed> findAllBreedsSortedByNameAsc();
}
