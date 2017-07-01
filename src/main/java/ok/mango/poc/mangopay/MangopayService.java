package ok.mango.poc.mangopay;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mangopay.MangoPayApi;
import com.mangopay.core.OAuthToken;
import com.mangopay.core.enumerations.CountryIso;
import com.mangopay.entities.User;
import com.mangopay.entities.UserNatural;

/**
 *
 * @author daveyx
 *
 */

@Service
public class MangopayService implements IMangopayService {

	@Value("${ok.mango.api}")
	private String mangoApi;

	@Value("${ok.mango.clientid}")
	private String mangoClientId;

	@Value("${ok.mango.clientpw}")
	private String mangoClientPw;

	private final MangoPayApi api;

	private String authToken;

	private String tokenType;

	private DateTime expiration;

	public MangopayService() {
		api = new MangoPayApi();
		api.getConfig().setClientId(mangoClientId);
		api.getConfig().setClientPassword(mangoClientPw);
		api.getConfig().setBaseUrl(mangoApi);
		authToken = null;
		tokenType = null;
		expiration = new DateTime();
	}

	public void authMangoPay() {
		try {
			// List<User> users = api.Users.getAll();
			final OAuthToken token = api.getAuthenticationManager().createToken();
			System.out.println(
					"token=" + token.getAccessToken() + ", type=" + token.getTokenType() + ", expires=" + token.getExpiresIn());
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public User createUserDaveyx() {
		final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		final DateTime birthday = formatter.parseDateTime("09/09/1980");

		final UserNatural user = new UserNatural();
		user.setFirstName("David");
		user.setLastName("Rehle");
		user.setEmail("d.rehle@gmail.com");
		user.setBirthday(birthday.getMillis() / 1000);
		user.setNationality(CountryIso.DE);
		user.setCountryOfResidence(CountryIso.DE);

		try {
			api.getUserApi().create(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
