package ok.mango.poc.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ok.mango.poc.Constants;
import ok.mango.poc.persistence.PUser;
import ok.mango.poc.persistence.UserRepository;

/**
 *
 * @author daveyx
 *
 */

@Service
public class OKUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public OKUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(final String username) {
        final PUser user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(username, user.getPassword(), true, true, true, true, Arrays.asList(new SimpleGrantedAuthority(Constants.ROLE_USER)));
    }
}
