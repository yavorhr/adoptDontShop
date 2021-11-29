package softuni.adoptdontshop.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.adoptdontshop.Model.Entity.Comment;
import softuni.adoptdontshop.Model.Entity.Dog;
import softuni.adoptdontshop.Model.Entity.MedicalRecord;
import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Enum.CoatLengthEnum;
import softuni.adoptdontshop.Model.Enum.GenderEnum;
import softuni.adoptdontshop.Model.Enum.MedicalRecordEnum;
import softuni.adoptdontshop.Model.Enum.SizeEnum;
import softuni.adoptdontshop.Repository.CommentRepository;
import softuni.adoptdontshop.Repository.DogRepository;
import softuni.adoptdontshop.Repository.MedicalRecordRepository;
import softuni.adoptdontshop.Repository.UserRepository;


import java.time.Instant;
import java.util.List;

@WithMockUser("testUser")
@SpringBootTest
@AutoConfigureMockMvc
public class CommentsRestControllerTest {

    private static final String COMMENT_1 = "hey Spring is cool!";
    private static final String COMMENT_2 = "Well... it is a bit trick sometimes... :(";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();

        testUser.setUsername("testUser")
                .setFirstName("testUser")
                .setLastName("testUser")
                .setPassword("test")
                .setEmail("test@test.com")
                .setDescription("some test description")
                .setAge(33);

        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        dogRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {
        long dogId = initDog();

        mockMvc.perform(get("/api/" + dogId + "/comments")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$.[0].message", is(COMMENT_1))).
                andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    private Long initDog() {
        Dog testDog = new Dog();
        testDog
                .setName("testDog")
                .setAge(30)
                .setColour("test")
                .setDescription("test description")
                .setMedicalNotes("test medical notes")
                .setGender(GenderEnum.FEMALE)
                .setCoatLength(CoatLengthEnum.MEDIUM)
                .setGetAlongWith("test test test")
                .setHouseTrained(false)
                .setAdopted(false)
                .setSize(SizeEnum.MEDIUM)
                .setWeight(30);

        dogRepository.save(testDog);

        Comment comment1 = new Comment();
        comment1.setCreated(Instant.now());
        comment1.setUser(testUser);
        comment1.setTextContent(COMMENT_1);
        comment1.setApproved(true);
        comment1.setDog(testDog);

        Comment comment2 = new Comment();
        comment2.setCreated(Instant.now());
        comment2.setUser(testUser);
        comment2.setTextContent(COMMENT_2);
        comment2.setApproved(true);
        comment2.setDog(testDog);

        testDog.setComments(List.of(comment1,comment2));

        return dogRepository.save(testDog).getId();
    }

}
