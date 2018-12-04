package renditionproject.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import renditionproject.areas.Area;
import renditionproject.areas.AreaRepository;
import renditionproject.usertypes.UserType;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	AreaRepository areaRepository;
	
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public User addUser(User user) {
		user.setDeactivated(false);
		BCryptPasswordEncoder encoder =  new BCryptPasswordEncoder();
		user.setPasswordHash(encoder.encode(user.getPasswordHash()));
		user = userRepository.save(user);
		UserType userType = user.getUserType();
		if (userType.getName() == "boss") {
			Area area = user.getArea();
			area.setBoss(user);
			areaRepository.save(area);
		}
		return user;
	
	}

	public User getUser(String username) {
		return userRepository.findById(username).get();
	}
	
}
