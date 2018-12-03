package renditionproject.users;

import renditionproject.usertypes.UserType;
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
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping("/users/{username}/type")
	public UserType getUserType(@PathVariable String username) {
		User user= userService.getUser(username);
		return user.getUserType();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public void addUser(@RequestBody User user) {
		userService.addUser(user);
	}
	@RequestMapping("/users/{username}")
	public User getUser(@PathVariable String username) {
		return userService.getUser(username);
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
		
		if (user == null) {
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
