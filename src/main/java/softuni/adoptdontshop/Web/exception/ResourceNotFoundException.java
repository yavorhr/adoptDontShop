package softuni.adoptdontshop.Web.exception;

public class ResourceNotFoundException extends RuntimeException {

    //ExceptionHandler Dogs and Breeds Controllers 500

    private final Long resourceId;

    public ResourceNotFoundException(Long resourceId) {
        super("Cannot find resource with id " + resourceId);
        this.resourceId = resourceId;
    }

    public Long getResourceId() {
        return resourceId;
    }
}
