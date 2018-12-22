package renditionproject.profiles;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AdminProfileRepository extends CrudRepository<AdminProfile, Integer>{
	public Optional<AdminProfile> findByUserUsername(String username);
}
