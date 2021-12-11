package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.Shelter;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {

    @Query(value = "SELECT Count(*) FROM dogs d WHERE d.is_adopted=false"
            ,nativeQuery = true)
    Integer findAllAdoptedDogs();
}
