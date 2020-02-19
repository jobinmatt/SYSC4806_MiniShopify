package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>{

    Shop findByName(String name);
    Shop findById (long id);
}
