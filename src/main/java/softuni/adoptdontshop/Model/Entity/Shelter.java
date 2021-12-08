package softuni.adoptdontshop.Model.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "shelter")
public class Shelter extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer capacity;

    public Shelter() {
    }

    public String getName() {
        return name;
    }

    public Shelter setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Shelter setCapacity(Integer capacity) {
        this.capacity = capacity;
        return this;
    }
}
