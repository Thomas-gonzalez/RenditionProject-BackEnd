package renditionproject.profiles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import renditionproject.users.User;
import renditionproject.users.UserRepository;

@Service
public class ProfileService {
	
	@Autowired
	private BossProfileRepository bossProfileRepository;
	@Autowired
	private EmployeeProfileRepository employeeProfileRepository;
	@Autowired
	private ManagerProfileRepository managerProfileRepository;
	@Autowired
	private AdminProfileRepository adminProfileRepository;
	@Autowired
	private UserRepository userRepository;
	
	public BossProfile AddBossProfile(BossProfile bossProfile, String username) {
		User user = userRepository.findById(username).get();
		bossProfile.setUser(user);
		return bossProfileRepository.save(bossProfile);
	}
	
	public EmployeeProfile AddEmployeeProfile(EmployeeProfile employeeProfile, String username, String bossUsername) {
		User user = userRepository.findById(username).get();
		employeeProfile.setUser(user);
		employeeProfile.setBoss(bossProfileRepository.findByUserUsername(bossUsername).get());
		return employeeProfileRepository.save(employeeProfile);
	}
	
	//agrega un rol de empleado asumiendo quien sera el jefe en funcion del area del empleado (implicit boss)
	public EmployeeProfile AddEmployeeProfileIB(EmployeeProfile employeeProfile, String username) {
		User user = userRepository.findById(username).get();
		employeeProfile.setUser(user);
		employeeProfile.setBoss(bossProfileRepository.findByAreaId(employeeProfile.getArea().getId()).get());
		return employeeProfileRepository.save(employeeProfile);
	}
	
	public ManagerProfile AddManagerProfile(ManagerProfile managerProfile, String username) {
		User user = userRepository.findById(username).get();
		managerProfile.setUser(user);
		return managerProfileRepository.save(managerProfile);
	}
	
	public AdminProfile AddAdminProfile(AdminProfile adminProfile, String username) {
		User user = userRepository.findById(username).get();
		adminProfile.setUser(user);
		return adminProfileRepository.save(adminProfile);
	}
	
	public List<BossProfile> getAllBossProfiles() {
		List<BossProfile> bossProfiles = new ArrayList<>();
		bossProfileRepository.findAll().forEach(bossProfiles::add);
		return bossProfiles;
	}
	
	public BossProfile getBossProfile(String username) {
		return bossProfileRepository.findByUserUsername(username).get();
	}
	
	public EmployeeProfile getEmployeeProfile(String username) {
		return employeeProfileRepository.findByUserUsername(username).get();
	}
	
	public ManagerProfile getManagerProfile(String username) {
		return managerProfileRepository.findByUserUsername(username).get();
	}
	
	public AdminProfile getAdminProfile(String username) {
		return adminProfileRepository.findByUserUsername(username).get();
	}
	
}
