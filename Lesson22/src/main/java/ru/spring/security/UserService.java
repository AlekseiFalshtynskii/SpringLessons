package ru.spring.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.spring.model.User;
import ru.spring.repository.UserRepository;

import static java.util.stream.Collectors.toList;

@Service("userDetailsService")
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findUserByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                user.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(toList())
        );
    }
}
