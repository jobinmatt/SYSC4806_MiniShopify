package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface User extends JpaRepository<User, Long> {

    List<User> findByLastName(String lastName);
    User findByEmail (String email);
}
