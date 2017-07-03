package ok.mango.poc.security;

import java.util.Arrays;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import ok.mango.poc.Constants;

/**
 *
 * @author daveyx
 *
 */

@Service
public class FacebookSignInAdapter implements SignInAdapter {

	@Override
	public String signIn(final String localUserId, final Connection<?> connection, final NativeWebRequest request) {
		System.out.println(" ====== Sign In adapter");

		final User user = new User(localUserId, "null", true, true, true, true,
				Arrays.asList(new SimpleGrantedAuthority(Constants.ROLE_FACEBOOK_USER)));

		// SecurityContextHolder.getContext().setAuthentication(new
		// UsernamePasswordAuthenticationToken(
		// user, null, Arrays.asList(new
		// SimpleGrantedAuthority(Constants.ROLE_FACEBOOK_USER))));
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, null,
				Arrays.asList(new SimpleGrantedAuthority(Constants.ROLE_FACEBOOK_USER))));
		return null;
	}
}
