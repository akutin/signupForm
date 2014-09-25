package name.alexey.register.persistence;


import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import name.alexey.register.persistence.validation.PasswordComposition;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames={"username"}, name = "UK_username"),
})
public class Account implements java.io.Serializable {
    private static final long serialVersionUID = 2748488086849083378L;

	public static final String FIND_BY_USERNAME = "Account.findByUsername";

    @JsonIgnore
	private Long id;

    @NotEmpty( message = "{required.message}")
    @Size( min=5, message = "{minLength.message}")
    @Pattern( regexp = "[a-zA-Z0-9]+", message = "{alphanum.message}")
	private String username;

    @NotEmpty( message = "{required.message}")
    @Email( message = "{email.message}")
    private String email;

    @NotEmpty( message = "{required.message}")
    @Size( min=8, message = "{minLength.message}")
    @PasswordComposition
	private String password;

    public Account() {
    }

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false, updatable = false)
	public Long getId() {
		return id;
	}

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="email", nullable = true, length = 128)
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    @Column(name="passwd", nullable = false, length = 128)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    @Column(name = "username", unique = true, nullable = false, length = 128, updatable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
