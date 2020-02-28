package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.model.Item;
import springboot.model.Shop;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
