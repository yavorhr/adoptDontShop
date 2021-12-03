package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.PictureUser;

@Repository
public interface PictureUserRepositorty extends JpaRepository<PictureUser,Long> {
}
