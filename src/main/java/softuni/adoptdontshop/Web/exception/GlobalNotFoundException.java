package softuni.adoptdontshop.Web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Resource not found")
public class GlobalNotFoundException extends RuntimeException {

    public GlobalNotFoundException() {
        super("Resource not found");
    }
}


