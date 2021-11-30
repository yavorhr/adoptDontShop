package softuni.adoptdontshop.Model.Model.ServiceModel;

public class CommentServiceModel {

    private Long dogId;
    private String message;
    private String creator;

    public CommentServiceModel() {
    }

    public Long getDogId() {
        return dogId;
    }

    public CommentServiceModel setDogId(Long dogId) {
        this.dogId = dogId;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentServiceModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public CommentServiceModel setCreator(String creator) {
        this.creator = creator;
        return this;
    }
}
