package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.model.Shop;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long>{

    List<Shop> findByName(String name);
    Shop findById (long id);
}
