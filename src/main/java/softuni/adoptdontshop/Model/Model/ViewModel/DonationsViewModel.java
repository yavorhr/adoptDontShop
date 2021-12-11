package softuni.adoptdontshop.Model.Model.ViewModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public class DonationsViewModel {

    private String firstName;
    private String lastName;
    private BigDecimal sum;
    private String phoneNumber;
    private String email;
    private String text;
    private LocalDate contributedOn;

    public DonationsViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public DonationsViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public DonationsViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public DonationsViewModel setSum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DonationsViewModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DonationsViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getText() {
        return text;
    }

    public DonationsViewModel setText(String text) {
        this.text = text;
        return this;
    }

    public LocalDate getContributedOn() {
        return contributedOn;
    }

    public DonationsViewModel setContributedOn(LocalDate contributedOn) {
        this.contributedOn = contributedOn;
        return this;
    }
}
