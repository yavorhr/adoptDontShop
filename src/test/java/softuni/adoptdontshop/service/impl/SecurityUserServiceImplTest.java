package softuni.adoptdontshop.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Entity.UserRoleEntity;
import softuni.adoptdontshop.Model.Enum.UserRoleEnum;
import softuni.adoptdontshop.Repository.UserRepository;
import softuni.adoptdontshop.Service.Impl.SecurityUserServiceImpl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class SecurityUserServiceImplTest {

    private UserEntity testUser;
    private SecurityUserServiceImpl serviceToTest;
    private UserRoleEntity adminRole;
    private UserRoleEntity userRole;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {

        // ARRANGE
        adminRole = new UserRoleEntity();
        adminRole.setRole(UserRoleEnum.ADMIN);

        userRole = new UserRoleEntity();
        userRole.setRole(UserRoleEnum.USER);

        serviceToTest = new SecurityUserServiceImpl(mockUserRepository);
        testUser = new UserEntity()
                .setUsername("testUser")
                .setEmail("testUser@gmail.com")
                .setPassword("secret")
                .setRoles(Set.of(adminRole, userRole));
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("invalid_user_email@non_existing.com"));

    }

    @Test
    void testUserFound() {
        //ARRANGE
        Mockito.when(mockUserRepository.findByEmail(testUser.getEmail())).thenReturn(Optional.of(testUser));

        //ACT
        var actual = serviceToTest.loadUserByUsername(testUser.getEmail());

        //ASSERT
        Assertions.assertEquals(actual.getUsername(), testUser.getUsername());

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        String actualRoles =
                actual
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(", "));


        Assertions.assertEquals(expectedRoles, actualRoles);
    }

}
