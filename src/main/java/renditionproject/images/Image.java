package renditionproject.images;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import renditionproject.expenses.Expense;

@Entity
public class Image {
	
	@Id
	@GeneratedValue
	@Column(nullable = false)
	private long id;
	@NotBlank
	private String address;
	
	@ManyToOne(optional = false)
	private Expense expense;
	
	public Image() {}
	public Image(long id, String address) {
		super();
		this.id = id;
		this.address = address;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	
}
