package softuni.adoptdontshop.Web.exception;

public class ResourceNotFoundException extends RuntimeException {

    private final Long resourceId;

    public ResourceNotFoundException(Long resourceId) {
        super("Cannot find resource with id " + resourceId);
        this.resourceId = resourceId;
    }

    public Long getResourceId() {
        return resourceId;
    }
}
