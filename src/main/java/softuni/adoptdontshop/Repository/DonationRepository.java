package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.Donation;

@Repository
public interface DonationRepository extends JpaRepository<Donation,Long> {
}
