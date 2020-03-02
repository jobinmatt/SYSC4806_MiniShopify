package springboot.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class ShopDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name; //req

    @JsonProperty("desc")
    private String description; //req

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("userId")
    private Long userId; //req

    @JsonProperty("products")
    private List<ItemDTO> products;

    public ShopDTO() {

        tags = new ArrayList<String>();
        products = new ArrayList<ItemDTO>();
    }

    public ShopDTO(Long id, String name, String description, List<String> tags, Long owner, List<ItemDTO> products) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.userId = owner;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<ItemDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ItemDTO> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", owner=" + userId +
                ", products=" + products +
                '}';
    }
}
