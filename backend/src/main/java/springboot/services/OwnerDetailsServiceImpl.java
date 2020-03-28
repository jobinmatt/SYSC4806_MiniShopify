package springboot.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springboot.Repository.OwnerRepository;
import springboot.model.Owner;

import java.util.Collections;
@Service
public class OwnerDetailsServiceImpl implements UserDetailsService {
    private OwnerRepository ownerRepository;

    public OwnerDetailsServiceImpl(OwnerRepository applicationUserRepository) {
        this.ownerRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner applicationUser = ownerRepository.findByEmail(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(applicationUser.getEmail(), applicationUser.getPassword(), Collections.emptyList());
    }
}
