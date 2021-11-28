package softuni.adoptdontshop.Model.Model.ViewModel;

import java.time.Instant;

public class CommentViewModel {

    private Long id;
    private String message;
    private String firstName;
    private String lastName;
    private Instant created;
    private boolean canApprove;
    private boolean canDelete;

    public CommentViewModel() {
    }

    public Long getId() {
        return id;
    }

    public CommentViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommentViewModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public CommentViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public CommentViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public CommentViewModel setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public boolean isCanApprove() {
        return canApprove;
    }

    public CommentViewModel setCanApprove(boolean canApprove) {
        this.canApprove = canApprove;
        return this;
    }

    public boolean isCanDelete() {
        return canDelete;
    }

    public CommentViewModel setCanDelete(boolean canDelete) {
        this.canDelete = canDelete;
        return this;
    }
}
