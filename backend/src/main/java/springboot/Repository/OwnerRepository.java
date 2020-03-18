package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import springboot.model.Owner;
import springboot.model.Shop;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findByFirstName(@Param("firstName") String firstName);
    List<Owner> findByLastName(@Param("lastName") String lastName);
    Owner findByEmail(@Param("email") String email);
    List<Owner> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
    Owner findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
}
