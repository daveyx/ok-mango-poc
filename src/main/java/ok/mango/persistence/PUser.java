package ok.mango.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author daveyx
 *
 */

@Data
@Entity
@NoArgsConstructor
public class PUser {
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("PUser [id=").append(id).append(", username=").append(username).append(", password=").append(password).append("]");
        return builder.toString();
    }

}
