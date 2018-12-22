package renditionproject.profiles;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ManagerProfileRepository extends CrudRepository<ManagerProfile, Integer>{

	public Optional<ManagerProfile> findByUserUsername(String username);
}
