package springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CartItem {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Item itemId;
    private int quantity;

    @ManyToOne
    private Cart cart;

    public CartItem() {
    }

    public CartItem(Item itemId, int quantity) {

        this.itemId = itemId;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Item getItemId() {
        return itemId;
    }

    public void setItemId(Item itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", quantity=" + quantity +
                ", cart=" + cart.getId() +
                '}';
    }
}
