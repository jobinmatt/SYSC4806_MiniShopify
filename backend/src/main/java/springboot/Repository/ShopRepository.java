package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import springboot.model.Shop;
import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long>{

    List<Shop> findByName(@Param("name") String name);
    List<Shop> findByDescription(@Param("description")String description);
    List<Shop> findByNameAndDescription(@Param("name") String name, @Param("description")String description);
//    List<Shop> findByTags(@Param("tags") List<String> tags);

    Shop findById (long id);
}
