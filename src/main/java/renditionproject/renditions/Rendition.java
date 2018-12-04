package renditionproject.renditions;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.UpdateTimestamp;

import renditionproject.areas.Area;
import renditionproject.users.User;

@Entity
public class Rendition {

	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false)
	private int state; //valores pre definidos
	//1 : creada-pendiente-enviar
	//2 : enviada-pendiente-revisar
	//3 : rechazada-jefe
	//4 : aprobada-jefe
	//5 : rechazada-encargado
	//6 : aprobada-encargado
	private boolean closed;
	private Timestamp sentDatetime;
	private boolean pettyCash; //caja chica
	private String declineDescription;
	@UpdateTimestamp
	private Timestamp lastUpdateDatetime;
	private double valueTotal;
	
	@ManyToOne
	private User employee;
	@ManyToOne
	private User boss;
	@ManyToOne
	private Area area;
	
	public Rendition() {}
	public Rendition(long id, int state, Timestamp sentDatetime, boolean pettyCash,
			String declineDescription, Timestamp lastUpdateDatetime, User employee, User boss, Area area) {
		super();
		this.id = id;
		this.state = state;
		this.sentDatetime = sentDatetime;
		this.pettyCash = pettyCash;
		this.declineDescription = declineDescription;
		this.lastUpdateDatetime = lastUpdateDatetime;
		this.employee = employee;
		this.boss = boss;
		this.area = area;
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

	public boolean isClosed() {
		return closed;
	}
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public boolean isPettyCash() {
		return pettyCash;
	}
	public void setPettyCash(boolean pettyCash) {
		this.pettyCash = pettyCash;
	}
	public double getValueTotal() {
		return valueTotal;
	}
	public void setValueTotal(double valueTotal) {
		this.valueTotal = valueTotal;
	}
	
	
	
	
}
