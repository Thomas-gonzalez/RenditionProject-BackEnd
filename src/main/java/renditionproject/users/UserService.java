package renditionproject.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public void addUser(User user) {
		user.setDeactivated(false);
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		user.setPasswordHash(encoder.encode(user.getPasswordHash()));
		userRepository.save(user);
	}

	public User getUser(String username) {
		return userRepository.findById(username).get();
	}
	
}
