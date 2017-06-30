package ok.mango.poc.security;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import ok.mango.poc.persistence.PUser;
import ok.mango.poc.persistence.UserRepository;

/**
 *
 * @author daveyx
 *
 */

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(final Connection<?> connection) {
        System.out.println("signup === ");
        final PUser user = new PUser();
        user.setUsername(connection.getDisplayName());
        user.setPassword(randomAlphabetic(8));
        userRepository.save(user);
        return user.getUsername();
    }

}
