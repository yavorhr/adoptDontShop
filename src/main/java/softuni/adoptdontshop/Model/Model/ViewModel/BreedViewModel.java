package softuni.adoptdontshop.Model.Model.ViewModel;

public class BreedViewModel {

    private Long id;
    private String name;

    public BreedViewModel() {
    }

    public Long getId() {
        return id;
    }

    public BreedViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BreedViewModel setName(String name) {
        this.name = name;
        return this;
    }
}
