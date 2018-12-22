package renditionproject.profiles;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import renditionproject.areas.Area;
import renditionproject.users.User;

@Entity
public class EmployeeProfile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	@JoinColumn(unique=true, nullable=false)
	private User user;
	@ManyToOne
	private BossProfile profileOfBoss; //para poder cambiar el jefe de un area sin tener que modificar cada perfil de empleado
	@ManyToOne
	private Area area;
	
	public EmployeeProfile() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BossProfile getProfileOfBoss() {
		return profileOfBoss;
	}

	public void setBoss(BossProfile profileOfBoss) {
		this.profileOfBoss = profileOfBoss;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	

	
}

