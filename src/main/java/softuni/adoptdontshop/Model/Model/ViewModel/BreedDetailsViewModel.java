package softuni.adoptdontshop.Model.Model.ViewModel;

import softuni.adoptdontshop.Model.Enum.SizeEnum;

public class BreedDetailsViewModel {

    private Long id;
    private String name;
    private String imageUrl;
    private SizeEnum size;

    public BreedDetailsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public BreedDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public BreedDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BreedDetailsViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public SizeEnum getSize() {
        return size;
    }

    public BreedDetailsViewModel setSize(SizeEnum size) {
        this.size = size;
        return this;
    }
}
