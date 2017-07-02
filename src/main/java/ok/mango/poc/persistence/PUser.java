package ok.mango.poc.persistence;

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
	private String email;

	@Column(nullable = true, unique = false)
	private String firstName;

	@Column(nullable = true, unique = false)
	private String lastName;

	private String password;

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("PUser [id=")
				.append(id)
				.append(", email=")
				.append(email)
				.append(", firstName=")
				.append(firstName)
				.append(", lastName=")
				.append(lastName)
				.append(", password=")
				.append(password)
				.append("]");
		return builder.toString();
	}

}
