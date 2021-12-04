package softuni.adoptdontshop.web;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.adoptdontshop.Model.Entity.Dog;
import softuni.adoptdontshop.Model.Entity.Picture;
import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Enum.CoatLengthEnum;
import softuni.adoptdontshop.Model.Enum.GenderEnum;
import softuni.adoptdontshop.Model.Enum.MedicalRecordEnum;
import softuni.adoptdontshop.Model.Enum.SizeEnum;
import softuni.adoptdontshop.Repository.DogRepository;
import softuni.adoptdontshop.Repository.PictureRepository;
import softuni.adoptdontshop.Repository.UserRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@SpringBootTest
@AutoConfigureMockMvc
public class DogsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PictureRepository pictureRepository;


    private Picture testPicture;


    @BeforeEach
    void setUp() {



    }

    @AfterEach
    void tearDown() {
        dogRepository.deleteAll();
        userRepository.deleteAll();
        pictureRepository.deleteAll();
        dogRepository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc.perform(get("/dogs/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-dogs"));
    }

    @Test
    void addDog() throws Exception {

        mockMvc.perform(post("/dogs/add")
                .param("name", "test_dog")
                .param("age", String.valueOf(3))
                .param("breed", "test_breed")
                .param("description", "some long test description about test_dog")
                .param("medicalNotes", "some long test description about test_dog medical notes")
                .param("imageUrl", "https://res.cloudinary.com/yavorhr/image/upload/v1638621386/f7dfvslmlrwdonvvxq3d.jpg")
                .param("gender", String.valueOf(GenderEnum.FEMALE))
                .param("size", String.valueOf(SizeEnum.MEDIUM))
                .param("weight", String.valueOf(15))
                .param("colour", "test_color")
                .param("coatLength", String.valueOf(CoatLengthEnum.CURLY))
                .param("houseTrained", String.valueOf(false))
                .param("getAlongWith", "some test get a long with text")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
            //TODO?

                .andExpect(status().is3xxRedirection());



        Assertions.assertEquals(1, dogRepository.count());


//        Optional<UserEntity> newlyCreatedUserOpt = userRepository.findByEmail(TEST_USER_EMAIL);
//
//        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());
//        UserEntity newlyCreatedUser = newlyCreatedUserOpt.get();
//        Assertions.assertEquals(TEST_USER_AGE, newlyCreatedUser.getAge());
    }
}




