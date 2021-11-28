package softuni.adoptdontshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableScheduling
@SpringBootApplication
public class AdoptDontShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdoptDontShopApplication.class, args);
    }

}
