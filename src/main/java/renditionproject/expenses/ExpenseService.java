package renditionproject.expenses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import renditionproject.images.ImageRepository;
import renditionproject.renditions.Rendition;
import renditionproject.renditions.RenditionRepository;

@Service
public class ExpenseService {
	
	@Autowired
	private ExpenseRepository expenseRepository;
	@Autowired
	private RenditionRepository renditionRepository;
	@Autowired
	private ImageRepository imageRepository;
	
	public List<Expense> getExpensesByRendition(long renditionId) {
		List<Expense> expenses = new ArrayList<>();
		expenseRepository.findByRenditionId(renditionId).forEach(expenses::add);
		return expenses;
	}
	
	public Expense addExpense(Expense expense, Rendition rendition) {
		expense.setRendition(rendition);
		float valueTotal = rendition.getValueTotal() + expense.getValue();
		rendition.setValueTotal(valueTotal);
		expense = expenseRepository.save(expense);
		renditionRepository.save(rendition);
		return expense;
	}
	public Expense getExpense(long id) {
		return expenseRepository.findById(id).get();
	}
	
	public void deleteExpense(long id) {
		imageRepository.deleteAll(imageRepository.findByExpenseId(id));
		expenseRepository.deleteById(id);
	}
	
	public void deleteExpenseByEntity(Expense expense) {
		imageRepository.deleteAll(imageRepository.findByExpenseId(expense.getId()));
		expenseRepository.delete(expense);
	}
}
