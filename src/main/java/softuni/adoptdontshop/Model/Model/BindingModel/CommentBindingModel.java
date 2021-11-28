package softuni.adoptdontshop.Model.Model.BindingModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentBindingModel {

    @NotBlank
    @Size(min=10)
    private String message;

    public CommentBindingModel() {
    }

    public String getMessage() {
        return message;
    }

    public CommentBindingModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
