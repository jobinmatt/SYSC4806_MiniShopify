package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.model.Owner;
import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    List<Owner> findByLastName(String lastName);
    Owner findByEmail (String email);
}
