package springboot.model;

import javax.persistence.ElementCollection;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ShopDTO {

    @NotNull
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    private List<String> tags;

    @NotNull
    private User owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
