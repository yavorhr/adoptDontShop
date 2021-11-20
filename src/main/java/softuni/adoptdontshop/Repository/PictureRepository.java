package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {
}
