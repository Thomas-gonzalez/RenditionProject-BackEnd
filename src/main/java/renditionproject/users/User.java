package renditionproject.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import renditionproject.areas.Area;
import renditionproject.companies.Company;

@Entity
public class User {
	
	@Id
	@NotNull
	private String username;
	@Column(nullable = false)
	private String dni;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String passwordHash;
	@Column(nullable = false)
	private boolean deactivated;
	
	private String token;
			
	@ManyToOne
	private Company company;
	
	public User() {}
	public User(@NotNull String username, @NotNull String dni, @NotNull String name, @NotNull String email, @NotNull String passwordHash,
			 Company company, Area area, String token) {
		super();
		this.username = username;
		this.dni = dni;
		this.name = name;
		this.passwordHash = passwordHash;
		this.deactivated = false;
		this.company = company;
		this.email = email;
		this.token = token;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public boolean isDeactivated() {
		return deactivated;
	}
	public void setDeactivated(boolean deactivated) {
		this.deactivated = deactivated;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}

	
}
