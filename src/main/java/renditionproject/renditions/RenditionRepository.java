package renditionproject.renditions;

import org.springframework.data.repository.CrudRepository;

public interface RenditionRepository extends CrudRepository<Rendition, Long> {
	
	public Iterable<Rendition> findByEmployeeUsername(String employeeUsername);
	public Iterable<Rendition> findByAreaId(int id);
}
