package softuni.adoptdontshop.web;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Repository.UserRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser("userTest@none.com")
@SpringBootTest
@AutoConfigureMockMvc

public class UserLoginControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();

        testUser.setUsername("testUser")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setEmail("userTest@none.com")
                .setDescription("some test description")
                .setAge(33);

        testUser.setPassword(passwordEncoder.encode("test"));

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

    private static final String TEST_USER_EMAIL = "userTest@none.com";
    private static final String TEST_USER_PASSWORD = "test";

    @Test
    void testLoginUser() throws Exception {
        mockMvc.perform(post("/users/login")
                .param("email", TEST_USER_EMAIL)
                .param("password", TEST_USER_PASSWORD)
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testLoginUserNotSuccessful() throws Exception {
        mockMvc.perform(post("/users/login")
                .param("email", "wrong@email.com")
                .param("password", "test1111")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().is2xxSuccessful());
    }
}
