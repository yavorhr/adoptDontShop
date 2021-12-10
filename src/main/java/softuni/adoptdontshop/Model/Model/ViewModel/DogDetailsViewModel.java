package softuni.adoptdontshop.Model.Model.ViewModel;

import softuni.adoptdontshop.Model.Entity.MedicalRecord;
import softuni.adoptdontshop.Model.Entity.Picture;
import softuni.adoptdontshop.Model.Enum.CoatLengthEnum;
import softuni.adoptdontshop.Model.Enum.GenderEnum;
import softuni.adoptdontshop.Model.Enum.SizeEnum;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DogDetailsViewModel {

    private Long id;
    private String name;
    private Integer age;
    private LocalDate lastModified;
    private LocalDate addedOn;
    private String description;
    private String medicalNotes;
    private String Breed;

    private GenderEnum gender;
    private String colour;
    private CoatLengthEnum coatLength;
    private boolean houseTrained;
    private String getAlongWith;
    private Integer weight;
    private SizeEnum size;

    private List<Picture> pictures;
    private List<MedicalRecord> medicalRecord = new LinkedList<>();

    public DogDetailsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public DogDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public DogDetailsViewModel setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    public String getBreed() {
        return Breed;
    }

    public DogDetailsViewModel setBreed(String breed) {
        Breed = breed;
        return this;
    }

    public String getName() {
        return name;
    }

    public DogDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public DogDetailsViewModel setAge(Integer age) {
        this.age = age;
        return this;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public DogDetailsViewModel setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public DogDetailsViewModel setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DogDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getMedicalNotes() {
        return medicalNotes;
    }

    public DogDetailsViewModel setMedicalNotes(String medicalNotes) {
        this.medicalNotes = medicalNotes;
        return this;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public DogDetailsViewModel setGender(GenderEnum gender) {
        this.gender = gender;
        return this;
    }

    public String getColour() {
        return colour;
    }

    public DogDetailsViewModel setColour(String colour) {
        this.colour = colour;
        return this;
    }

    public CoatLengthEnum getCoatLength() {
        return coatLength;
    }

    public DogDetailsViewModel setCoatLength(CoatLengthEnum coatLength) {
        this.coatLength = coatLength;
        return this;
    }

    public boolean isHouseTrained() {
        return houseTrained;
    }

    public DogDetailsViewModel setHouseTrained(boolean houseTrained) {
        this.houseTrained = houseTrained;
        return this;
    }

    public String getGetAlongWith() {
        return getAlongWith;
    }

    public DogDetailsViewModel setGetAlongWith(String getAlongWith) {
        this.getAlongWith = getAlongWith;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public DogDetailsViewModel setWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public SizeEnum getSize() {
        return size;
    }

    public DogDetailsViewModel setSize(SizeEnum size) {
        this.size = size;
        return this;
    }

    public List<MedicalRecord> getMedicalRecord() {
        return medicalRecord;
    }

    public DogDetailsViewModel setMedicalRecord(List<MedicalRecord> medicalRecord) {
        this.medicalRecord = medicalRecord;
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
