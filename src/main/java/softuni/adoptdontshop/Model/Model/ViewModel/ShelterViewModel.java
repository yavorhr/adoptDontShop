package softuni.adoptdontshop.Model.Model.ViewModel;

public class ShelterViewModel {

    private Integer capacity;
    private Integer adopted;
    private Integer freeSlots;

    public ShelterViewModel() {
    }

    public Integer getCapacity() {
        return capacity;
    }

    public ShelterViewModel setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }

    public Integer getAdopted() {
        return adopted;
    }

    public ShelterViewModel setAdopted(Integer adopted) {
        this.adopted = adopted;
        return this;
    }

    public Integer getFreeSlots() {
        return freeSlots;
    }

    public ShelterViewModel setFreeSlots(Integer freeSlots) {
        this.freeSlots = freeSlots;
        return this;
    }
}
