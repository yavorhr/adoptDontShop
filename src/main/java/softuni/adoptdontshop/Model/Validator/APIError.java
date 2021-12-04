package softuni.adoptdontshop.Model.Validator;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class APIError {

    private final HttpStatus status;
    private List<String> fieldWithErrors = new ArrayList<>();

    public APIError(HttpStatus status) {
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public List<String> getFieldWithErrors() {
        return fieldWithErrors;
    }

    public APIError setFieldWithErrors(List<String> fieldWithErrors) {
        this.fieldWithErrors = fieldWithErrors;
        return this;
    }

    public void addFieldWithError(String error) {
        fieldWithErrors.add(error);
    }
}
