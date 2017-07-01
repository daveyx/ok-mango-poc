package ok.mango.poc.persistence;

/**
 *
 * @author daveyx
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<PUser, Long> {

    PUser findByUsername(final String username);
    PUser findByEmail(final String email);
}
