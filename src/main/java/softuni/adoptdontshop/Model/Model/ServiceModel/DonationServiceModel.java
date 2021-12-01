package softuni.adoptdontshop.Model.Model.ServiceModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DonationServiceModel {

    private String firstName;
    private String lastName;
    private BigDecimal sum;
    private String phoneNumber;
    private String email;
    private String text;
    private LocalDate contributedOn;

    public DonationServiceModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public DonationServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public DonationServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public LocalDate getContributedOn() {
        return contributedOn;
    }

    public DonationServiceModel setContributedOn(LocalDate contributedOn) {
        this.contributedOn = contributedOn;
        return this;
    }

    public DonationServiceModel setSum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public DonationServiceModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public DonationServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getText() {
        return text;
    }

    public DonationServiceModel setText(String text) {
        this.text = text;
        return this;
    }
}
