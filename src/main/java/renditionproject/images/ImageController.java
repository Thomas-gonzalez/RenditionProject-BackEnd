package renditionproject.images;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import renditionproject.expenses.Expense;
import renditionproject.expenses.ExpenseService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ImageController {

	@Autowired
	private ImageService imageService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@RequestMapping("/expenses/{expenseId}/images")
	public List<Image> getImagesByExpense(@PathVariable long expenseId) {
		return imageService.getImagesByExpense(expenseId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/expenses/{expenseId}/images")
	public Image addImage(@RequestBody Image image, @PathVariable long expenseId) {
		Image i1 = new Image();
		i1.setAddress(image.getAddress());
		Expense expense = expenseService.getExpense(expenseId);
		return imageService.addImage(i1, expense);
	}
}
