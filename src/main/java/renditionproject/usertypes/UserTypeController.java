package renditionproject.usertypes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTypeController {
	
	@Autowired
	private UserTypeService userTypeService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/user-types")
	private void addUserType(@RequestBody UserType userType) {
		userTypeService.addUserType(userType);
	}
	
	@RequestMapping("/user-types")
	private List<UserType> getAllUserTypes() {
		return userTypeService.getAllUserTypes();
	}
}
