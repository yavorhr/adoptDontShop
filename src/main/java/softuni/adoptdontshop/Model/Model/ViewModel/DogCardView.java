package softuni.adoptdontshop.Model.Model.ViewModel;

import softuni.adoptdontshop.Model.Enum.GenderEnum;
import softuni.adoptdontshop.Model.Enum.SizeEnum;

public class DogCardView {

    private Long id;
    private String name;
    private String imageUrl;
    private String breed;
    private String colour;
    private GenderEnum gender;
    private SizeEnum size;
    private Integer age;


    public DogCardView() {
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public DogCardView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getName() {
        return name;
    }

    public DogCardView setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public DogCardView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public DogCardView setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public DogCardView setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public SizeEnum getSize() {
        return size;
    }

    public DogCardView setSize(SizeEnum size) {
        this.size = size;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public DogCardView setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getBreed() {
        return breed;
    }

    public DogCardView setBreed(String breed) {
        this.breed = breed;
        return this;
    }


    public String ageCalc(Integer age) {
        this.age = age;

        if (age < 12) {
            return String.format("%d months old", age);
        }

        //13 % 12 == 1
        int months = age % 12;
        //13 / 12 == 1
        int year = age / 12;

        if (year == 1) {
            return String.format("%d year and %d months", year, months);
        }
        return String.format("%d years and %d months", year, months);
    }
}
