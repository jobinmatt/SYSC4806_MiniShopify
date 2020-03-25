package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.model.Shop;
import java.util.List;
@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>{

    List<Shop> findByName(@Param("name") String name);
    List<Shop> findByDescription(@Param("description")String description);
    List<Shop> findByNameAndDescription(@Param("name") String name, @Param("description")String description);
//    List<Shop> findByTags(@Param("tags") List<String> tags);

    Shop findById (long id);
}
