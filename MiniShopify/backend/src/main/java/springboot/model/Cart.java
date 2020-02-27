package springboot.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private User owner;

    @OneToMany
    private List<CartItem> items;

    public Cart() {

        items = new ArrayList<CartItem>();
    }

    public Cart(User owner) {

        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem item) {

        this.items.add(item);
    }

    public void removeItem(CartItem item) {

        this.items.remove(item);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", owner=" + owner +
                ", items=" + items +
                '}';
    }
}
