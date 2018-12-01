package renditionproject.expenses;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import renditionproject.renditions.Rendition;
import renditionproject.renditions.RenditionService;
import org.springframework.web.bind.annotation.CrossOrigin;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private RenditionService renditionService;
	

	@RequestMapping("renditions/{renditionId}/expenses")
	public List<Expense> getExpensesByRendition(@PathVariable Long renditionId) {
		return expenseService.getExpensesByRendition(renditionId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/renditions/{renditionId}/expenses")
	public Expense addExpense(@RequestBody Expense expense, @PathVariable long renditionId) {
		Expense e1 = new Expense();
		e1.setValue(expense.getValue());
		e1.setComment(expense.getComment());
		e1.setCurrency(expense.getCurrency());
		e1.setDate(expense.getDate());
		Rendition rendition = renditionService.getRendition(renditionId);
		return expenseService.addExpense(e1, rendition);
	}
	@RequestMapping("/renditions/{renditionId}/expenses/{id}")
	public Expense getExpense(@PathVariable long id) {
		return expenseService.getExpense(id);
	}
}
