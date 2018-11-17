package renditionproject.usertypes;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTypeService {
	
	@Autowired
	private UserTypeRepository userTypeRepository;

	public void addUserType(UserType userType) {
		userTypeRepository.save(userType);
	}

	public List<UserType> getAllUserTypes() {
		List<UserType> userTypes = new ArrayList<>();
		userTypeRepository.findAll().forEach(userTypes::add);
		return userTypes;
	}
}
