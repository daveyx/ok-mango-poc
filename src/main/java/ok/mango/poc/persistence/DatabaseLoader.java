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
		pUser.setUsername("d.rehle@gmail.com");
		pUser.setPassword("asdf");
		this.users.save(pUser);
	}
}
