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
import org.springframework.test.web.servlet.ResultActions;
import softuni.adoptdontshop.Model.Entity.*;
import softuni.adoptdontshop.Model.Enum.*;
import softuni.adoptdontshop.Repository.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username = "admin", roles = {"USER", "ADMIN"})
@SpringBootTest
@AutoConfigureMockMvc
public class DogsControllerTest {

    private static final String TEST_DOG_NAME = "test_dog";
    private static final String TEST_DOG_BREED = "test_breed";
    private static final int TEST_DOG_AGE = 3;
    private static final String TEST_DOG_DESCRIPTION = "some long test description about test_dog";
    private static final String TEST_DOG_SIZE = String.valueOf(SizeEnum.MEDIUM);
    private static final int TEST_DOG_WEIGHT = 30;
    private static final String TEST_DOG_COLOR = "test_color";
    private static final GenderEnum TEST_DOG_GENDER = GenderEnum.FEMALE;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private BreedRepository breedRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    private UserRoleEntity adminRole;
    private Dog testDog;
    private UserEntity testUser;
    private MedicalRecord medicalRecordTest;


    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        dogRepository.deleteAll();
        medicalRecordRepository.deleteAll();
        pictureRepository.deleteAll();
        breedRepository.deleteAll();

        adminRole = initUserRole();
        testUser = initTestUser();
        testDog = initDogAndBreed();
        medicalRecordTest = initMedicalRecord();
    }


    @AfterEach
    void tearDown() {
        dogRepository.deleteAll();
        userRepository.deleteAll();
        pictureRepository.deleteAll();
        dogRepository.deleteAll();
        breedRepository.deleteAll();
    }

    @Test
    void testOpenAllDogs() throws Exception {
        mockMvc.perform(get("/dogs/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("all-dogs"))
                .andExpect(model().attributeExists("allDogs"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = "ADMIN")
    void getAddDogPage() throws Exception {
        mockMvc
                .perform(get("/dogs/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-dog"));
    }



    @Test
    @WithMockUser(username = "testUser", roles = "ADMIN")
    void addDog() throws Exception {

        mockMvc.perform(post("/dogs/add")
                .param("name", TEST_DOG_NAME)
                .param("age", String.valueOf(TEST_DOG_AGE))
                .param("breed", TEST_DOG_BREED)
                .param("description", TEST_DOG_DESCRIPTION)
                .param("medicalNotes", "some long test description about test_dog medical notes")
                .param("imageUrl", "https://res.cloudinary.com/yavorhr/image/upload/v1638621386/f7dfvslmlrwdonvvxq3d.jpg")
                .param("gender", String.valueOf(TEST_DOG_GENDER))
                .param("size", TEST_DOG_SIZE)
                .param("weight", String.valueOf(TEST_DOG_WEIGHT))
                .param("colour", TEST_DOG_COLOR)
                .param("coatLength", String.valueOf(CoatLengthEnum.CURLY))
                .param("houseTrained", String.valueOf(false))
                .param("getAlongWith", "some test get a long with text")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        );

        Assertions.assertEquals(1, dogRepository.count());
        Optional<Dog> newlyAddedDogOpt = dogRepository.findByNameAndAge("test_dog", 3);
        Assertions.assertTrue(newlyAddedDogOpt.isPresent());

        Dog newlyAddedDog = newlyAddedDogOpt.get();
        Assertions.assertEquals(TEST_DOG_NAME, newlyAddedDog.getName());
        Assertions.assertEquals(TEST_DOG_AGE, newlyAddedDog.getAge());
        Assertions.assertEquals(TEST_DOG_BREED, newlyAddedDog.getBreed().getName());
        Assertions.assertEquals(TEST_DOG_COLOR, newlyAddedDog.getColour());
        Assertions.assertEquals(TEST_DOG_DESCRIPTION, newlyAddedDog.getDescription());
        Assertions.assertEquals(TEST_DOG_SIZE, newlyAddedDog.getSize().name());
        Assertions.assertEquals(TEST_DOG_WEIGHT, newlyAddedDog.getWeight());
        Assertions.assertEquals(TEST_DOG_GENDER, newlyAddedDog.getGender());
    }

    @Test
    @WithMockUser(username = "testUser", roles = "ADMIN")
    void testAddInvalidDog() throws Exception {
        mockMvc
                .perform(post("/dogs/add")
                        .param("name", TEST_DOG_NAME)
                        .param("breed", TEST_DOG_BREED)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("add"));
    }

    @Test
    void testGetDogProfile() throws Exception {
        mockMvc
                .perform(get("/dogs/" + testDog.getId() + "/details"))
                .andExpect(status().isOk())
                .andExpect(view().name("dog-profile"))
                .andExpect(model().attributeExists("dog"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = "ADMIN")
    void getDogUpdateForm() throws Exception {
        mockMvc
                .perform(get("/dogs/" + testDog.getId() + "/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("update-dog"))
                .andExpect(model().attributeExists("allBreedsNames"))
                .andExpect(model().attributeExists("dogUpdateBindingModel"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = "ADMIN")
    void testEditDog() throws Exception {
        ResultActions resultActions = mockMvc.perform(patch("/dogs/" + testDog.getId() + "/edit")
                .param("name", TEST_DOG_NAME)
                .param("age", String.valueOf(TEST_DOG_AGE))
                .param("breed", TEST_DOG_BREED)
                .param("description", TEST_DOG_DESCRIPTION)
                .param("medicalNotes", "some long test description about test_dog medical notes")
                .param("gender", String.valueOf(TEST_DOG_GENDER))
                .param("size", TEST_DOG_SIZE)
                .param("weight", String.valueOf(TEST_DOG_WEIGHT))
                .param("colour", TEST_DOG_COLOR)
                .param("coatLength", String.valueOf(CoatLengthEnum.CURLY))
                .param("houseTrained", String.valueOf(false))
                .param("getAlongWith", "some test get a long with text")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        );

        Assertions.assertEquals(1, dogRepository.count());
        Optional<Dog> updateDogOpt = dogRepository.findByNameAndAge("test_dog", 3);
        Assertions.assertTrue(updateDogOpt.isPresent());

        Dog updatedDog = updateDogOpt.get();

        Assertions.assertEquals(TEST_DOG_NAME, updatedDog.getName());
        Assertions.assertEquals(TEST_DOG_AGE, updatedDog.getAge());
        Assertions.assertEquals(TEST_DOG_BREED, updatedDog.getBreed().getName());
        Assertions.assertEquals(TEST_DOG_COLOR, updatedDog.getColour());
        Assertions.assertEquals(TEST_DOG_DESCRIPTION, updatedDog.getDescription());
        Assertions.assertEquals(TEST_DOG_SIZE, updatedDog.getSize().name());
        Assertions.assertEquals(TEST_DOG_WEIGHT, updatedDog.getWeight());
        Assertions.assertEquals(TEST_DOG_GENDER, updatedDog.getGender());

        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dogs/" + testDog.getId() + "/edit/errors"));
    }


    @Test
    @WithMockUser(username = "testUser", roles = "ADMIN")
    void testEditDogWithInvalidParam() throws Exception {
        mockMvc
                .perform(patch("/dogs/" + testDog.getId() + "/edit")
                        .param("name", TEST_DOG_NAME)
                        .param("breed", TEST_DOG_BREED)
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dogs/" + testDog.getId() + "/edit/errors"));
    }

    @Test
    @WithMockUser(username = "testUser", roles = "ADMIN")
    void testDeleteDog() throws Exception {

        mockMvc
                .perform(delete("/dogs/" + testDog.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dogs/all"));

        Assertions.assertEquals(0,dogRepository.count());

    }



    private Dog initDogAndBreed() {
        Breed breed = new Breed();

        breed
                .setName("test_breed")
                .setDescription("some long breed description")
                .setAffectionLevel(30)
                .setEnergyLevel(30)
                .setFriendlinessToOtherPets(30)
                .setFriendlinessToStrangers(30)
                .setGroomingRequirements(30)
                .setPlayfulness(30)
                .setEaseOfTraining(30)
                .setSize(SizeEnum.MEDIUM)
                .setWatchfulness(30)
                .setImageUrl("https://res.cloudinary.com/yavorhr/image/upload/v1638621386/f7dfvslmlrwdonvvxq3d.jpg");

        breedRepository.save(breed);

        Dog dog = new Dog();

        dog.setName("test_dog")
                .setAge(3)
                .setBreed(breed)
                .setDescription("some long test description about test_dog")
                .setMedicalNotes("some long test description about test_dog medical notes ")
                .setGender(TEST_DOG_GENDER)
                .setSize(SizeEnum.MEDIUM)
                .setWeight(30)
                .setColour(TEST_DOG_COLOR)
                .setCoatLength(CoatLengthEnum.MEDIUM)
                .setHouseTrained(true)
                .setGetAlongWith("everybody");

        Picture testPicture = new Picture();
        testPicture.setUrl(breed.getImageUrl());
        testPicture.setTitle("some random title");

        testPicture.setDog(dog);
        dog.setPictures(List.of(testPicture));

        dogRepository.save(dog);
        pictureRepository.save(testPicture);

        return dog;
    }

    @Test
    void testGetAllBreedsPage() throws Exception {
        mockMvc.perform(get("/breeds/all"))
                .andExpect(status().isOk())
                .andExpect(view().name("breeds"))
                .andExpect(model().attributeExists("allBreeds"));
    }

    @Test
    void testGetBreedProfile() throws Exception {
        Long test_breed = breedRepository.findByName("test_breed").get().getId();

        mockMvc
                .perform(get("/breeds/" + test_breed + "/details"))
                .andExpect(status().isOk())
                .andExpect(view().name("breed-details"))
                .andExpect(model().attributeExists("breed"))
                .andExpect(model().attributeExists("dogsFromThisBreed"));
    }

    private UserEntity initTestUser() {
        return userRepository.save(new UserEntity()
                .setFirstName("testUser")
                .setLastName("testUser")
                .setUsername("testUser")
                .setEmail("testUser@abv.bg")
                .setPassword("11111")
                .setRoles(Set.of(adminRole)));
    }

    private UserRoleEntity initUserRole() {
        return roleRepository.save(new UserRoleEntity()
                .setRole(UserRoleEnum.ADMIN));
    }

    private MedicalRecord initMedicalRecord() {
        return medicalRecordRepository.save(new MedicalRecord()
                .setName(MedicalRecordEnum.RABIES));
    }

}




