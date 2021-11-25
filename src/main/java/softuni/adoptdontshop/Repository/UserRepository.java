package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.UserEntity;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findUserById(Long id);

    Optional<UserEntity> findByUsernameIgnoreCase(String username);
}
