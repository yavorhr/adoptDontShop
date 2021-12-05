package softuni.adoptdontshop.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Entity.UserRoleEntity;
import softuni.adoptdontshop.Model.Enum.UserRoleEnum;
import softuni.adoptdontshop.Repository.RoleRepository;
import softuni.adoptdontshop.Repository.UserRepository;
import java.util.Set;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserProfileControllerTest {

    private MockMvc mockMvc;
    private UserRoleEntity testRole;
    private UserEntity testUser;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;



    @Autowired
    PasswordEncoder passwordEncoder;


    @BeforeEach
    void setUp() {
        testUser = new UserEntity();
        testRole = new UserRoleEntity();
        testRole.setRole(UserRoleEnum.USER);
        roleRepository.save(testRole);

        testUser.setUsername("test")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("userTest@none.com")
                .setDescription("some test description")
                .setAge(33);
        testUser.setPassword(passwordEncoder.encode("test"));
        testUser.setRoles(Set.of(testRole));

        initMockMvc();

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @WithMockUser(username = "test")
    void testGetProfilePage() throws Exception {
        mockMvc
                .perform(get("/users/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("user-profile"))
                .andExpect(model().attributeExists("currentUser"));
    }


    private void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }
}
