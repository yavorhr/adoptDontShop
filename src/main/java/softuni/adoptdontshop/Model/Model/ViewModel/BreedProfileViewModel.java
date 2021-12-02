package softuni.adoptdontshop.Model.Model.ViewModel;
import softuni.adoptdontshop.Model.Enum.SizeEnum;
public class BreedProfileViewModel {

    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private SizeEnum size;
    private Integer energyLevel;
    private Integer playfulness;
    private Integer affectionLevel;
    private Integer groomingRequirements;
    private Integer friendlinessToOtherPets;
    private Integer friendlinessToStrangers;
    private Integer watchfulness;
    private Integer easeOfTraining;

    public BreedProfileViewModel() {
    }

    public String getName() {
        return name;
    }

    public BreedProfileViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BreedProfileViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public BreedProfileViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public BreedProfileViewModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public SizeEnum getSize() {
        return size;
    }

    public BreedProfileViewModel setSize(SizeEnum size) {
        this.size = size;
        return this;
    }

    public Integer getEnergyLevel() {
        return energyLevel;
    }

    public BreedProfileViewModel setEnergyLevel(Integer energyLevel) {
        this.energyLevel = energyLevel;
        return this;
    }

    public Integer getPlayfulness() {
        return playfulness;
    }

    public BreedProfileViewModel setPlayfulness(Integer playfulness) {
        this.playfulness = playfulness;
        return this;
    }

    public Integer getAffectionLevel() {
        return affectionLevel;
    }

    public BreedProfileViewModel setAffectionLevel(Integer affectionLevel) {
        this.affectionLevel = affectionLevel;
        return this;
    }

    public Integer getGroomingRequirements() {
        return groomingRequirements;
    }

    public BreedProfileViewModel setGroomingRequirements(Integer groomingRequirements) {
        this.groomingRequirements = groomingRequirements;
        return this;
    }

    public Integer getFriendlinessToOtherPets() {
        return friendlinessToOtherPets;
    }

    public BreedProfileViewModel setFriendlinessToOtherPets(Integer friendlinessToOtherPets) {
        this.friendlinessToOtherPets = friendlinessToOtherPets;
        return this;
    }

    public Integer getFriendlinessToStrangers() {
        return friendlinessToStrangers;
    }

    public BreedProfileViewModel setFriendlinessToStrangers(Integer friendlinessToStrangers) {
        this.friendlinessToStrangers = friendlinessToStrangers;
        return this;
    }

    public Integer getWatchfulness() {
        return watchfulness;
    }

    public BreedProfileViewModel setWatchfulness(Integer watchfulness) {
        this.watchfulness = watchfulness;
        return this;
    }

    public Integer getEaseOfTraining() {
        return easeOfTraining;
    }

    public BreedProfileViewModel setEaseOfTraining(Integer easeOfTraining) {
        this.easeOfTraining = easeOfTraining;
        return this;
    }
}
