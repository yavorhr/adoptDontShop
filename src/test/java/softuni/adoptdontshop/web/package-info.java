package softuni.adoptdontshop.web;



/*

CommentsRestController - Integration test for REST Controller

1. application.yaml create
2. virtual DB pom.xml

dependency>
   <groupId>org.hsqldb</groupId>
   <artifactId>sqltool-j5</artifactId>
   <version>2.2.4</version>
</dependency>

3. Import real repositories

     @Autowired
   private MockMvc mockMvc;

    @Autowired
    private DogRepository dogRepository;

    @Autowired
    private CommentRepository commentRepository;

4. @BeforeEach

Creating user and save it to the actual userRepository, before each test

    @BeforeEach
    void setUp() {
        testUser = new UserEntity();

        testUser.setUsername("testUser")
                .setFirstName("testUser")
                .setLastName("testUser")
                .setPassword("test");

        testUser = userRepository.save(testUser);
    }

5. AfterEach

Deleting the saved user and dog

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        dogRepository.deleteAll();
    }

 */