package softuni.adoptdontshop.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "donations")
public class Donation extends BaseEntity {

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private BigDecimal sum;
    private String phoneNumber;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(columnDefinition = "LONGTEXT")
    private String text;

    public Donation() {
    }

    public String getFirstName() {
        return firstName;
    }

    public Donation setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Donation setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public Donation setSum(BigDecimal sum) {
        this.sum = sum;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Donation setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Donation setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getText() {
        return text;
    }

    public Donation setText(String text) {
        this.text = text;
        return this;
    }
}
