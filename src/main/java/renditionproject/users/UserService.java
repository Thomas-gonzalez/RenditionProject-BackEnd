package renditionproject.users;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import renditionproject.areas.AreaRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AreaRepository areaRepository;
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		users = users.stream().filter(r -> !r.isDeactivated()).collect(Collectors.toList());
		return users;
	}
	
	public User addUser(User user) {
		user.setDeactivated(false);
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		user.setPasswordHash(encoder.encode(user.getPasswordHash()));
		user = userRepository.save(user);
		return user;
	}

	public User getUser(String username) {
		return userRepository.findById(username).get();
	}
	
	public User updateUser(User user, String username) {
		User existingUser = userRepository.findById(username).get();
		existingUser.setArea(user.getArea());
		existingUser.setCompany(user.getCompany());
		existingUser.setDni(user.getDni());
		existingUser.setEmail(user.getEmail());
		existingUser.setName(user.getName());
		existingUser = userRepository.save(existingUser);
		return existingUser;
	}

	public void deleteUser(String username) {
		User user = userRepository.findById(username).get();
		user.setDeactivated(true);
		userRepository.save(user);
	}
	
}
