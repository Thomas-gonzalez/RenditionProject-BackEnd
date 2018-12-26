package renditionproject.users;

import java.util.List;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import renditionproject.profiles.ProfileService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
	@RequestMapping(method = RequestMethod.POST, value = "/users/{username}")
	public User updateUser(@PathVariable String username, @RequestBody User user) {
		return userService.updateUser(user, username);
	}
	@RequestMapping("/users/{username}")
	public User getUser(@PathVariable String username) {
		return userService.getUser(username);
	}
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{username}")
	public void deleteUser(@PathVariable String username) throws ServletException {
		if (profileService.getBossProfile(username) != null) throw new ServletException("Usuario tiene perfil de jefe, debe reemplazar jefe antes de eliminarlo.");
		userService.deleteUser(username);
	}
	
	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	@CrossOrigin(origins = "http://localhost:4200")
	public User login(@RequestBody User login) throws ServletException {

		String jwtToken = "";

		if (login.getUsername() == null || login.getPasswordHash() == null) {
			throw new ServletException("Please fill in username and password");
		}

		String username = login.getUsername();
		String password = login.getPasswordHash();

		User user = userService.getUser(username);
		
		if (user == null || user.isDeactivated()) {
			throw new ServletException("User name not found.");
		}

		String encodedPassword = user.getPasswordHash();
		String usr = user.getUsername();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(password, encodedPassword) || !username.equals(usr)) {
			throw new ServletException("Invalid login. Please check your name and password.");
		}

		jwtToken = Jwts.builder().setSubject(username).claim("roles", "user").setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		
		user.setToken(jwtToken);
		return user;
	}
}
