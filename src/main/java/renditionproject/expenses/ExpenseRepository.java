package renditionproject.expenses;

import org.springframework.data.repository.CrudRepository;

public interface ExpenseRepository extends CrudRepository<Expense, Long> {

	public Iterable<Expense> findByRenditionId(long renditionId);
	
}
