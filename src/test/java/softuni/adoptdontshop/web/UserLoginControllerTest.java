package softuni.adoptdontshop.web;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Entity.UserRoleEntity;
import softuni.adoptdontshop.Model.Enum.UserRoleEnum;
import softuni.adoptdontshop.Repository.RoleRepository;
import softuni.adoptdontshop.Repository.UserRepository;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserLoginControllerTest {

    private static final String TEST_USER_USERNAME = "testUser";
    private static final String TEST_USER_PASSWORD = "test111";

    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserEntity testUser;
    private UserRoleEntity testRole;

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();
        testRole = initUserRole();

        testUser.setUsername("testUser")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("userTest@none.com")
                .setDescription("some test description")
                .setAge(33)
                .setRoles(Set.of(testRole));
        initMockMvc();

        testUser.setPassword(passwordEncoder.encode(TEST_USER_PASSWORD));
        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testOpenUserLogin() throws Exception {
        mockMvc.perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-login"));
    }

    @Test
    void testLoginUser() throws Exception {
        mockMvc.perform(post("/users/login")
                .param("username", TEST_USER_USERNAME)
                .param("password", TEST_USER_PASSWORD)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(authenticated())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void testLoginUserNotSuccessful() throws Exception {
        mockMvc.perform(post("/users/login")
                .param("username", "wrong_user")
                .param("password", "test1111")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(forwardedUrl("/users/login-error"));
    }

    private UserRoleEntity initUserRole() {
        return roleRepository.save(new UserRoleEntity()
                .setRole(UserRoleEnum.USER));
    }

    private void initMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .apply(springSecurity())
                .build();
    }
}
