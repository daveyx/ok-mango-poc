package ok.mango.poc.mangopay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mangopay.MangoPayApi;
import com.mangopay.core.OAuthToken;

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

	public void authMangoPay() {

		final MangoPayApi api = new MangoPayApi();

		// configuration
		api.Config.ClientId = mangoClientId;
		api.Config.ClientPassword = mangoClientPw;
		api.Config.BaseUrl = mangoApi;

		// call some API methods...
		try {
//			List<User> users = api.Users.getAll();
			final OAuthToken token = api.AuthenticationManager.createToken();
			System.out.println("token=" + token.access_token + ", type=" + token.token_type + ", expires=" + token.expires_in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getUser() {
		
	}

}
