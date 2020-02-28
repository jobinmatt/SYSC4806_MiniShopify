package springboot.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

public class ShopDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("desc")
    private String description;

    @JsonProperty("tags")
    private List<String> tags;

    @JsonProperty("userId")
    private Long userId;

    @JsonProperty("products")
    private List<Long> productIds;

    public ShopDTO() {

        tags = new ArrayList<String>();
        productIds = new ArrayList<Long>();
    }

    public ShopDTO(String name, String description, List<String> tags, Long owner, List<Long> products) {

        this.name = name;
        this.description = description;
        this.tags = tags;
        this.userId = owner;
        this.productIds = products;
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

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", owner=" + userId +
                ", products=" + productIds +
                '}';
    }
}
