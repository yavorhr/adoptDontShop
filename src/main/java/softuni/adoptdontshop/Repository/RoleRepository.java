package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.UserRoleEntity;
import softuni.adoptdontshop.Model.Enum.UserRoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<UserRoleEntity,Long> {

    UserRoleEntity findByRole(UserRoleEnum role);
}
