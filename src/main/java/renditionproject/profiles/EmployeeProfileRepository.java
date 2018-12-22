package renditionproject.profiles;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeProfileRepository extends CrudRepository<EmployeeProfile, Integer>{
	
	public Optional<EmployeeProfile> findByUserUsername(String username);
}
