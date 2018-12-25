package renditionproject.profiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		Optional<BossProfile> bossProfile = bossProfileRepository.findByUserUsername(username);
		if (!bossProfile.isPresent()) {
			return null;
		}
		else return bossProfile.get();
	}
	
	public EmployeeProfile getEmployeeProfile(String username) {
		Optional<EmployeeProfile> employeeProfile =  employeeProfileRepository.findByUserUsername(username);
		if (!employeeProfile.isPresent()) {
			return null;
		}
		else return employeeProfile.get();
	}
	
	public ManagerProfile getManagerProfile(String username) {
		Optional<ManagerProfile> managerProfile = managerProfileRepository.findByUserUsername(username);
		if (!managerProfile.isPresent()) return null;
		else return managerProfile.get();
	}
	
	public AdminProfile getAdminProfile(String username) {
		Optional<AdminProfile> adminProfile = adminProfileRepository.findByUserUsername(username);
		if (!adminProfile.isPresent()) return null;
		else return adminProfile.get();
	}
	//deletes
	public void deleteAdminProfile(String username) {
		Optional<AdminProfile> adminProfile = adminProfileRepository.findByUserUsername(username);
		if (!adminProfile.isPresent()) return;
		else adminProfileRepository.delete(adminProfile.get());	
	}
	
	public void deleteManagerProfile(String username) {
		Optional<ManagerProfile> managerProfile = managerProfileRepository.findByUserUsername(username);
		if (!managerProfile.isPresent()) return;
		else managerProfileRepository.delete(managerProfile.get());
	}
	
	public void deleteEmployeeProfile(String username) {
		Optional<EmployeeProfile> employeeProfile = employeeProfileRepository.findByUserUsername(username);
		if (!employeeProfile.isPresent()) return;
		else employeeProfileRepository.delete(employeeProfile.get());
	}
	
	//se reasigna el perfil de jefe a un nuevo usuario.
	public BossProfile reAssignBossProfile(String oldBossUsername, String newBossUsername) {
		Optional<BossProfile> bossProfile = bossProfileRepository.findByUserUsername(oldBossUsername);
		Optional<User> newBoss = userRepository.findById(newBossUsername);
		if (!bossProfile.isPresent() || !newBoss.isPresent()) return null;
		BossProfile bossP = bossProfile.get();
		bossP.setUser(newBoss.get());
		return bossProfileRepository.save(bossP);
		
	}
	
}
