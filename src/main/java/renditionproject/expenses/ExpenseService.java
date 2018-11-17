package renditionproject.expenses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import renditionproject.renditions.Rendition;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	
	public List<Expense> getExpensesByRendition(long renditionId) {
		List<Expense> expenses = new ArrayList<>();
		expenseRepository.findByRenditionId(renditionId).forEach(expenses::add);
		return expenses;
	}
	
	public void addExpense(Expense expense, Rendition rendition) {
		expense.setRendition(rendition);
		expenseRepository.save(expense);
	}
	public Expense getExpense(long id) {
		return expenseRepository.findById(id).get();
	}
}
