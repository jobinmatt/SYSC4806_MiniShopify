package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.model.User;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByLastName(String lastName);
    User findByEmail (String email);
}
