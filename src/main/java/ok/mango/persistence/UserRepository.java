package ok.mango.persistence;

/**
 *
 * @author daveyx
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<PUser, Long> {

    PUser findByUsername(final String username);

}
