package softuni.adoptdontshop.Model.Model.ViewModel;


public class UserProfileViewModel {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String description;

    public UserProfileViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserProfileViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public UserProfileViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public UserProfileViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserProfileViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
