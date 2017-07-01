package ok.mango.poc.mangopay;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mangopay.MangoPayApi;
import com.mangopay.core.Configuration;
import com.mangopay.core.OAuthToken;
import com.mangopay.core.enumerations.CountryIso;
import com.mangopay.core.enumerations.CurrencyIso;
import com.mangopay.entities.User;
import com.mangopay.entities.UserNatural;
import com.mangopay.entities.Wallet;

/**
 *
 * @author daveyx
 *
 */

@Service
public class MangopayService implements IMangopayService {

	private final MangoPayApi api;

	@SuppressWarnings("unused")
	private String authToken;

	@SuppressWarnings("unused")
	private String tokenType;

	@SuppressWarnings("unused")
	private DateTime expiration;

	public MangopayService(@Value("${ok.mango.api}") final String mangoApi,
			@Value("${ok.mango.clientid}") final String mangoClientId,
			@Value("${ok.mango.clientpw}") final String mangoClientPw) {
		final Configuration config = new Configuration();
		config.setClientId(mangoClientId);
		config.setClientPassword(mangoClientPw);
		config.setBaseUrl(mangoApi);

		api = new MangoPayApi();
		api.setConfig(config);
		authToken = null;
		tokenType = null;
		expiration = new DateTime();
	}

	public void authMangoPay() {
		try {
			final OAuthToken token = api.getAuthenticationManager().createToken();
			System.out.println("token=" + token.getAccessToken() + ", type=" + token.getTokenType() + ", expires="
					+ token.getExpiresIn());
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public User getUserByEmail(final String email) {
		final List<User> users;
		try {
			users = api.getUserApi().getAll();
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
		if (users != null && !users.isEmpty()) {
			for (final User user : users) {
				if (user.getEmail().equals(email)) {
					return user;
				}
			}
		}
		return null;
	}

	public User createUserDaveyx() {
		final User user = getUserDaveyx(false);
		try {
			final User userCreated = api.getUserApi().create(user);
			// System.out.println("userCreated: " + userCreated); //id=28519399
			return userCreated;
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Wallet createWalletDaveyx() {
		final User user = getUserDaveyx(true);
		final Wallet wallet = new Wallet();
		wallet.setOwners(new ArrayList<String>());
		wallet.getOwners().add(user.getId());

		wallet.setCurrency(CurrencyIso.EUR);
		wallet.setDescription("Wallet of " + user.getEmail());

		try {
			final Wallet walletCreated = api.getWalletApi().create(wallet);
			// System.out.println("wallet=" + walletCreated);
			return walletCreated;
		} catch (final Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	//
	// ---> private
	//

	private User getUserDaveyx(boolean setId) {
		final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		final DateTime birthday = formatter.parseDateTime("09/09/1980");

		final UserNatural user = new UserNatural();
		user.setFirstName("David");
		user.setLastName("Rehle");
		user.setEmail("d.rehle@gmail.com");
		user.setBirthday(birthday.getMillis() / 1000);
		user.setNationality(CountryIso.DE);
		user.setCountryOfResidence(CountryIso.DE);

		if (setId) {
			user.setId("28519399"); // id of daveyx 170701
		}

		return user;
	}
}
