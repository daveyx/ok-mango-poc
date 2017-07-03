package ok.mango.poc.mangopay;

import java.util.List;

import com.mangopay.entities.User;
import com.mangopay.entities.Wallet;

/**
 *
 * @author daveyx
 *
 */

public interface IMangopayService {
	void authMangoPay();
	User createUserDaveyx();
	Wallet createWalletDaveyx();
	User getUserByEmail(final String email);
	String createPayInDaveyx();
}
