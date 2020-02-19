package springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Shop shop;

    private String name;
    private String description;
    private int stockQuantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", shop=" + shop +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
