package renditionproject.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import renditionproject.areas.Area;
import renditionproject.companies.Company;
import renditionproject.usertypes.UserType;

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
	private String passwordHash;
	@Column(nullable = false)
	private boolean deactivated;
	
	@ManyToOne
	private Company company;
	@ManyToOne
	private Area area;
	@ManyToOne(optional=false)
	private UserType userType;
	
	public User() {}
	public User(@NotNull String username, @NotNull String dni, @NotNull String name, @NotNull String passwordHash,
			 Company company, Area area, UserType userType) {
		super();
		this.username = username;
		this.dni = dni;
		this.name = name;
		this.passwordHash = passwordHash;
		this.deactivated = false;
		this.company = company;
		this.area = area;
		this.userType = userType;
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
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
}
