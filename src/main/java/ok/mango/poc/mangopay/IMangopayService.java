package ok.mango.poc.mangopay;

import com.mangopay.entities.PayIn;
import com.mangopay.entities.Transfer;
import com.mangopay.entities.User;
import com.mangopay.entities.Wallet;

/**
 *
 * @author daveyx
 *
 */

public interface IMangopayService {
	void authMangoPay();
	User getUserByEmail(final String email);
	String createPayIn(final String email);
	PayIn getPayInInfo(final String payInId);
	
	User createUserDaveyx();
	Wallet createWalletDaveyx();
	Transfer transferToDaveyx(final PayIn payIn);
	String createPayInDaveyx();
}
