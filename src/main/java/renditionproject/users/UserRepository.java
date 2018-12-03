package renditionproject.users;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {
	public Iterable<User> findByAreaId(int id);
}
