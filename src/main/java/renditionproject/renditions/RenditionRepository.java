package renditionproject.renditions;

import org.springframework.data.repository.CrudRepository;

import renditionproject.users.User;

public interface RenditionRepository extends CrudRepository<Rendition, Long> {
	
	public Iterable<Rendition> findByEmployeeUsername(String employeeUsername);	
}
