package softuni.adoptdontshop.web;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import softuni.adoptdontshop.Repository.DonationRepository;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class DonationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DonationRepository donationRepository;

    @AfterEach
    void tearDown() {
        donationRepository.deleteAll();
    }

    @Test
    void testOpenDonationForm() throws Exception {
        mockMvc.perform(get("/donations"))
                .andExpect(status().isOk())
                .andExpect(view().name("donation-form"));
    }

    private static final String TEST_USER_EMAIL = "testUserEmail@test.com";
    private static final String TEST_USER_FIRST_NAME = "Ivan";
    private static final String TEST_USER_LAST_NAME = "Ivanov";
    private static final int SUM = 31;

    @Test
    void testDonation() throws Exception {
        mockMvc.perform(post("/donations")
                .param("firstName", TEST_USER_FIRST_NAME)
                .param("lastName", TEST_USER_LAST_NAME)
                .param("sum", String.valueOf(SUM))
                .param("phoneNumber", "+35988888888")
                .param("email", TEST_USER_EMAIL)
                .param("text", "some random text")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().is3xxRedirection());

        Assertions.assertEquals(16, donationRepository.count());

//        Optional<UserEntity> newlyCreatedUserOpt = donationRepository.findTEST_USER_EMAIL);
//
//        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());
//        UserEntity newlyCreatedUser = newlyCreatedUserOpt.get();
//        Assertions.assertEquals(TEST_USER_AGE, newlyCreatedUser.getAge());
    }

    @Test
    void testDonationsFail() throws Exception {
        mockMvc.perform(post("/donations")
                .param("firstName", TEST_USER_FIRST_NAME)
                .param("lastName", TEST_USER_LAST_NAME)
                .param("sum", String.valueOf(-30))
                .param("phoneNumber", "+35988888888")
                .param("email", "test")
                .param("creditCardNumber", "111111111111")
                .param("cardHolderName", "some random name")
                .param("expiration", "03/26")
                .param("text", "some random text")
                .param("cvv", "333")
                .with(csrf())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
                .andExpect(status().is3xxRedirection());



//        Optional<UserEntity> newlyCreatedUserOpt = donationRepository.findTEST_USER_EMAIL);
//
//        Assertions.assertTrue(newlyCreatedUserOpt.isPresent());
//        UserEntity newlyCreatedUser = newlyCreatedUserOpt.get();
//        Assertions.assertEquals(TEST_USER_AGE, newlyCreatedUser.getAge());
    }
}
