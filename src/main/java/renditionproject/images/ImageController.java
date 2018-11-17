package renditionproject.images;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import renditionproject.expenses.Expense;
import renditionproject.expenses.ExpenseService;

@RestController
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@RequestMapping("/expenses/{expenseId}/images")
	public List<Image> getImagesByExpense(@PathVariable long expenseId) {
		List<Image> images = new ArrayList<>();
		imageService.getImagesByExpense(expenseId).forEach(images::add);
		return images;
	}
	@RequestMapping(method = RequestMethod.POST, value = "/expenses/{expenseId}/images")
	public void addImage(@RequestBody Image image, @PathVariable long expenseId) {
		Expense expense = expenseService.getExpense(expenseId);
		image.setExpense(expense);
		imageService.addImage(image, expense);
	}
}
