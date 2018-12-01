package renditionproject.images;

import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Long>{

	public Iterable<Image> findByExpenseId(long expenseId);
}
