package softuni.adoptdontshop.Init;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.adoptdontshop.Service.UserService;

@Component
public class DBInit implements CommandLineRunner {

    private final UserService userService;

    public DBInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userService.initializeUsersAndRoles();
    }
}
