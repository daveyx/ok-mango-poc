package ok.mango.poc.security;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
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

		final Facebook facebook = (Facebook) connection.getApi();
		final String[] fields = { "id", "email", "first_name", "last_name" };
		final User userProfile = facebook.fetchObject("me", User.class, fields);

		final String profileEmail = userProfile.getEmail();
		final PUser user = new PUser();
		user.setEmail(profileEmail);
		user.setFirstName(userProfile.getFirstName());
		user.setLastName(userProfile.getLastName());
		user.setPassword(randomAlphabetic(8));
		userRepository.save(user);
		return connection.getDisplayName();
	}

}
