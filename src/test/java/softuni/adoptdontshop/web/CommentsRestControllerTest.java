package softuni.adoptdontshop.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import softuni.adoptdontshop.Model.Entity.Comment;
import softuni.adoptdontshop.Model.Entity.Dog;
import softuni.adoptdontshop.Model.Entity.UserEntity;
import softuni.adoptdontshop.Model.Enum.CoatLengthEnum;
import softuni.adoptdontshop.Model.Enum.GenderEnum;
import softuni.adoptdontshop.Model.Enum.SizeEnum;
import softuni.adoptdontshop.Model.Model.BindingModel.CommentBindingModel;
import softuni.adoptdontshop.Repository.DogRepository;
import softuni.adoptdontshop.Repository.UserRepository;


import java.time.Instant;
import java.util.List;

@WithMockUser("userTest")
@SpringBootTest
@AutoConfigureMockMvc
public class CommentsRestControllerTest {

    private static final String COMMENT_1 = "some test comment about Spring";
    private static final String COMMENT_2 = "some test comment about Spring again";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private UserRepository userRepository;

    private UserEntity testUser;

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();

        testUser.setUsername("userTest")
                .setFirstName("firstName")
                .setLastName("lastName")
                .setPassword("test")
                .setEmail("userTest@none.com")
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
        var dog = initComments(initDog());

        mockMvc.perform(get("/api/" + dog.getId() + "/comments")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$.[0].message", is(COMMENT_1))).
                andExpect(jsonPath("$.[1].message", is(COMMENT_2)));
    }

    @Test
    void testCreateComments() throws Exception {

        CommentBindingModel commentBindingModel = new CommentBindingModel()
                .setMessage(COMMENT_1);

        var emptyDog = initDog();

        mockMvc.perform(
                post("/api/" + emptyDog.getId() + "/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(commentBindingModel))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/" + emptyDog.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_1)));
    }

    private Dog initDog() {

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

        return dogRepository.save(testDog);
    }

    private Dog initComments(Dog testDog) {

        Comment comment1 = new Comment();
        comment1.setCreated(Instant.now());
        comment1.setUser(testUser);
        comment1.setTextContent(COMMENT_1);
        comment1.setApproved(true);

        Comment comment2 = new Comment();
        comment2.setCreated(Instant.now());
        comment2.setUser(testUser);
        comment2.setTextContent(COMMENT_2);
        comment2.setApproved(true);

        comment1.setDog(testDog);
        comment2.setDog(testDog);
        testDog.setComments(List.of(comment1, comment2));

        return dogRepository.save(testDog);
    }
}
