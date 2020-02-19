package springboot.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;
    private String lastName;

    private String email;
    private String password;

    @OneToMany
    private List<Shop> ownedShop;

    @OneToOne
    private Cart personalCart;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Shop> getOwnedShop() {
        return ownedShop;
    }

    public void setOwnedShop(List<Shop> ownedShop) {
        this.ownedShop = ownedShop;
    }

    public Cart getPersonalCart() {
        return personalCart;
    }

    public void setPersonalCart(Cart personalCart) {
        this.personalCart = personalCart;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ownedShop=" + ownedShop +
                ", personalCart=" + personalCart +
                '}';
    }
}
