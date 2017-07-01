package ok.mango.poc.mangopay;

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
}
