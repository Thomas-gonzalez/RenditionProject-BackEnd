package renditionproject.profiles;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


public interface BossProfileRepository extends CrudRepository<BossProfile, Integer>{
	public Optional<BossProfile> findByAreaId(int id);
	public Optional<BossProfile> findByUserUsername(String username);
}
