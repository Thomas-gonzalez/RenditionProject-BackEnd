package renditionproject.renditions;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.UpdateTimestamp;

import renditionproject.users.User;

@Entity
public class Rendition {

	@Id
	@GeneratedValue
	private long id;
	@NotBlank
	private int state; //valores pre definidos
	//1 : inicializado
	//2 : creado-pendiente-enviar
	//3 : enviado-pendiente-revisar
	//4 : aprobado-jefe
	//5 : rechazado-jefe
	//6 : aprobado-encargado
	//7 : rechazado-encargado
	//8 : cerrado
	private Timestamp sentDatetime;
	private boolean prettyCash; //caja chica
	private String declineDescription;
	@UpdateTimestamp
	private Timestamp lastUpdateDatetime;
	
	@ManyToOne
	private User employee;
	@ManyToOne
	private User boss;
	@ManyToOne
	private User manager;
	
	public Rendition() {}
	public Rendition(long id, @NotBlank int state, Timestamp sentDatetime, boolean prettyCash,
			String declineDescription, Timestamp lastUpdateDatetime, User employee, User boss, User manager) {
		super();
		this.id = id;
		this.state = state;
		this.sentDatetime = sentDatetime;
		this.prettyCash = prettyCash;
		this.declineDescription = declineDescription;
		this.lastUpdateDatetime = lastUpdateDatetime;
		this.employee = employee;
		this.boss = boss;
		this.manager = manager;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Timestamp getSentDatetime() {
		return sentDatetime;
	}
	public void setSentDatetime(Timestamp sentDatetime) {
		this.sentDatetime = sentDatetime;
	}
	public boolean isPrettyCash() {
		return prettyCash;
	}
	public void setPrettyCash(boolean prettyCash) {
		this.prettyCash = prettyCash;
	}
	public String getDeclineDescription() {
		return declineDescription;
	}
	public void setDeclineDescription(String declineDescription) {
		this.declineDescription = declineDescription;
	}
	public Timestamp getLastUpdateDatetime() {
		return lastUpdateDatetime;
	}
	public void setLastUpdateDatetime(Timestamp lastUpdateDatetime) {
		this.lastUpdateDatetime = lastUpdateDatetime;
	}
	public User getEmployee() {
		return employee;
	}
	public void setEmployee(User employee) {
		this.employee = employee;
	}
	public User getBoss() {
		return boss;
	}
	public void setBoss(User boss) {
		this.boss = boss;
	}
	public User getManager() {
		return manager;
	}
	public void setManager(User manager) {
		this.manager = manager;
	}
	
	
	
	
}
