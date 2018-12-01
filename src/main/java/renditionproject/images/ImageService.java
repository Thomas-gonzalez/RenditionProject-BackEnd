package renditionproject.images;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import renditionproject.expenses.Expense;

@Service
public class ImageService {

	@Autowired
	private ImageRepository imageRepository;
	
	public List<Image> getImagesByExpense(long expenseId) {
		List<Image> images = new ArrayList<>();
		imageRepository.findByExpenseId(expenseId).forEach(images::add);
		return images;
	}
	
	//solo guarda los datos de la imagen (direccion de archivo y llave foranea)
	public Image addImage(Image image, Expense expense) {
		image.setExpense(expense);
		return imageRepository.save(image);
	}
	
	public Image getImage(long id) {
		return imageRepository.findById(id).get();
	}
	
}
