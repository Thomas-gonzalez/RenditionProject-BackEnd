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


@RestController
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	@Autowired
	private RenditionService renditionService;
	

	@RequestMapping("renditions/{renditionId}/expenses")
	public List<Expense> getExpensesByRendition(@PathVariable Long renditionId) {
		List<Expense> expenses = new ArrayList<>();
		expenseService.getExpensesByRendition(renditionId).forEach(expenses::add);
		return expenses;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/renditions/{renditionId}/expenses")
	public void addExpense(@RequestBody Expense expense, @PathVariable long renditionId) {
		Rendition rendition = renditionService.getRendition(renditionId);
		expenseService.addExpense(expense, rendition);
	}
	@RequestMapping("/renditions/{renditionId}/expenses/{id}")
	public Expense getExpense(@PathVariable long id) {
		return expenseService.getExpense(id);
	}
}
