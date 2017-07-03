package ok.mango.poc.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 
 * @author daveyx
 * 
 */

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final UserRepository users;

	@Autowired
	public DatabaseLoader(final UserRepository users) {
		this.users = users;
	}

	@Override
	public void run(String... strings) throws Exception {
		final PUser pUser = new PUser();
		pUser.setEmail("d.rehle@gmail.com");
		pUser.setFirstName("David");
		pUser.setLastName("Rehle");
		pUser.setPassword(XXX);
		this.users.save(pUser);

		for (int i = 0; i < 10; i++) {
			final PUser loopUser = new PUser();
			loopUser.setEmail("d.rehle+" + i + "@gmail.com");
			loopUser.setFirstName("David" + i);
			loopUser.setLastName("Rehle" + i);
			loopUser.setPassword(XXX);
			this.users.save(loopUser);
		}
	}

}
