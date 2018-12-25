package renditionproject.profiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	//asigna rol de jefe a un usuario, requiere en el JSON el valor de "area".
	@RequestMapping(method = RequestMethod.POST, value = "profiles/{username}/boss")
	public BossProfile AddBossProfile(@RequestBody BossProfile bossProfile, @PathVariable String username) {
		return profileService.AddBossProfile(bossProfile, username);
	}
	
	//asigna rol de empleado a un usuario con jefe explicito, requiere en el JSON el valor de "area".
	@RequestMapping(method = RequestMethod.POST, value = "profiles/{username}/employee/{bossUsername}")
	public EmployeeProfile AddEmployeeProfile(@RequestBody EmployeeProfile employeeProfile, @PathVariable String username, @PathVariable String bossUsername) {
		return profileService.AddEmployeeProfile(employeeProfile, username, bossUsername);
	}
	
	//asigna rol de empleado a un usuario con jefe implicito(en base al area), requiere en el JSON el valor de "area".
	@RequestMapping(method = RequestMethod.POST, value = "profiles/{username}/employee")
	public EmployeeProfile AddEmployeeProfileIB(@RequestBody EmployeeProfile employeeProfile, @PathVariable String username) {
		return profileService.AddEmployeeProfileIB(employeeProfile, username);
	}
	
	//asigna rol de encargado a un usuario, requiere en el JSON el valor de "area".
	@RequestMapping(method = RequestMethod.POST, value = "profiles/{username}/manager")
	public ManagerProfile AddManagerProfile(@RequestBody ManagerProfile managerProfile, @PathVariable String username) {
		return profileService.AddManagerProfile(managerProfile, username);
	}
	
	//asigna rol de admin a un usuario, recibe un JSON vacio
	@RequestMapping(method = RequestMethod.POST, value = "profiles/{username}/admin")
	public AdminProfile AddAdminProfile(@RequestBody AdminProfile adminProfile, @PathVariable String username) {
		return profileService.AddAdminProfile(adminProfile, username);
	}
	
	@RequestMapping("profiles/bosses")
	public List<BossProfile> getAllBossProfiles() {
		return profileService.getAllBossProfiles();
	}
	
	@RequestMapping("profiles/{username}/boss")
	public BossProfile getBossProfile(@PathVariable String username) {
		return profileService.getBossProfile(username);
	}
	
	@RequestMapping("profiles/{username}/employee")
	public EmployeeProfile getEmployeeProfile(@PathVariable String username) {
		return profileService.getEmployeeProfile(username);
	}
	
	@RequestMapping("profiles/{username}/manager")
	public ManagerProfile getManagerProfile(@PathVariable String username) {
		return profileService.getManagerProfile(username);
	}
	
	@RequestMapping("profiles/{username}/admin") 
	public AdminProfile getAdminProfile(@PathVariable String username) {
		return profileService.getAdminProfile(username);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "profiles/{username}/admin/delete")
	public void deleteAdminProfile(@PathVariable String username) {
		profileService.deleteAdminProfile(username);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "profiles/{username}/manager/delete")
	public void deleteManagerProfile(@PathVariable String username) {
		profileService.deleteManagerProfile(username);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "profiles/{username}/employee/delete")
	public void deleteEmployeeProfile(@PathVariable String username) {
		profileService.deleteEmployeeProfile(username);
	}
	
	//cambia de jefe mediante el username del nuevo jefe, recibe JSON vacio
	@RequestMapping(method = RequestMethod.POST, value = "profiles/{oldBossUsername}/boss/{newBossUsername}")
	public BossProfile reAssignBossProfile(@PathVariable String oldBossUsername, @PathVariable String newBossUsername) {
		return profileService.reAssignBossProfile(oldBossUsername, newBossUsername);
	}

}
