package softuni.adoptdontshop.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Validator.UniqueEmail;
import softuni.adoptdontshop.Model.Validator.UniqueUsername;
import softuni.adoptdontshop.Repository.UserRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserRegistrationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth-register"));
    }

    private static final String TEST_USER_EMAIL = "testUserEmail@test.com";
    private static final String TEST_USER_USERNAME = "pesho123";
    private static final int TEST_USER_AGE = 31;

    @Test
    void testRegisterUser() throws Exception {
        mockMvc.perform(post("/users/register")
                .param("username", TEST_USER_USERNAME)
                .param("email", TEST_USER_EMAIL)
                .param("firstName", "Pesho")
                .param("lastName", "Petrov")
                .param("age", String.valueOf(TEST_USER_AGE))
                .param("password", "12345")
                .param("confirmPassword", "12345")
                .param("description", "some random description")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(4, userRepository.count());
        Optional<UserEntity> newlyCreatedUserOpt = userRepository.findByEmail(TEST_USER_EMAIL);

        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());
        UserEntity newlyCreatedUser = newlyCreatedUserOpt.get();
        Assertions.assertEquals(TEST_USER_AGE, newlyCreatedUser.getAge());
    }


}