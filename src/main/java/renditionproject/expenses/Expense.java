package renditionproject.expenses;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import renditionproject.currencies.Currency;
import renditionproject.renditions.Rendition;

@Entity
public class Expense {
	
	@Id
	@GeneratedValue
	private long id;
	private Date date;
	private Float value;
	private String comment;
	
	@ManyToOne(optional = false)
	private Currency currency;
	@ManyToOne(optional = false)
	private Rendition rendition;
	
	public Expense() {}


	public Expense(long id, Date date, Float value, String comment, Currency currency, Rendition rendition) {
		super();
		this.id = id;
		this.date = date;
		this.value = value;
		this.comment = comment;
		this.currency = currency;
		this.rendition = rendition;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Float getValue() {
		return value;
	}


	public void setValue(Float value) {
		this.value = value;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Currency getCurrency() {
		return currency;
	}


	public void setCurrency(Currency currency) {
		this.currency = currency;
	}


	public Rendition getRendition() {
		return rendition;
	}


	public void setRendition(Rendition rendition) {
		this.rendition = rendition;
	}



	
	
	
	
	
	
}
