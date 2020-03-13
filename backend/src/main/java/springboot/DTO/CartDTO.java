package springboot.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import springboot.model.CartItem;

import java.util.List;

public class CartDTO {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("owner")
    private String owner;

    @JsonProperty("items")
    @JsonIgnoreProperties(value = { "items" })
    private List<ItemDTO> items;

    public CartDTO() {

    }

    public CartDTO(Long id, String owner, List<ItemDTO> items) {
        this.id = id;
        this.owner = owner;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "CartDTO{" +
                "id=" + id +
                ", owner='" + owner + '\'' +
                ", items=" + items +
                '}';
    }
}
