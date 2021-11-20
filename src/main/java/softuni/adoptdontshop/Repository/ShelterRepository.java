package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.Shelter;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter,Long> {
}
