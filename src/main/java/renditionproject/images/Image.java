package renditionproject.images;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import renditionproject.expenses.Expense;

@Entity
public class Image {
	
	@Id
	@GeneratedValue
	@Column(nullable = false)
	private long id;
	@NotNull
	@Lob
	private String imageData;
	
	@ManyToOne(optional = false)
	private Expense expense;
	
	public Image() {}
	public Image(long id, String imageData, Expense expense) {
		super();
		this.id = id;
		this.imageData = imageData;
		this.expense = expense;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getImageData() {
		return imageData;
	}
	public void setImageData(String address) {
		this.imageData = address;
	}
	
	public Expense getExpense() {
		return expense;
	}
	public void setExpense(Expense expense) {
		this.expense = expense;
	}
	
}
