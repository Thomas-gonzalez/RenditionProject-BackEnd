package renditionproject.renditions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import renditionproject.users.User;
import renditionproject.users.UserRepository;
import renditionproject.usertypes.UserType;

@Service
public class RenditionService {

	private RenditionRepository renditionRepository;
	private UserRepository userRepository;
	
	public Rendition getRendition(Long id) {
		return renditionRepository.findById(id).get();
	}

	public List<Rendition> getAllRenditions() {
		List<Rendition> renditions = new ArrayList<>();
		renditionRepository.findAll().forEach(renditions::add);
		return renditions;
	}

	public List<Rendition> getRenditionsByEmployeeUsername(String employeeUsername) {
		List<Rendition> renditions = new ArrayList<>();
		renditionRepository.findByEmployeeUsername(employeeUsername).forEach(renditions::add);
		return renditions;
	}

	public void addRendition(Rendition rendition, String employeeUsername) {
		User employee = userRepository.findById(employeeUsername).get();
		UserType userType = employee.getUserType();
		if (!userType.getName().equals("employee")) {
			return; //error tratando de crear una rendicion para un usuario no empleado
		}
		rendition.setArea(employee.getArea());
		rendition.setEmployee(employee);
		rendition.setState(1);
		renditionRepository.save(rendition);
		
	}

	public void updateRendition(Rendition rendition, String employeeUsername) {
		User employee = userRepository.findById(employeeUsername).get();
		UserType userType = employee.getUserType();
		if (!userType.getName().equals("employee")) {
			return; //error tratando de crear una rendicion para un usuario no empleado
		}
		rendition.setArea(employee.getArea());
		rendition.setEmployee(employee);
		rendition.setState(1);
		renditionRepository.save(rendition);		
		
	}

	public Rendition getRendition(long id) {
		return renditionRepository.findById(id).get();
	}

	public void sendRendition(long id) {
		Rendition rendition = getRendition(id);
		rendition.setState(2); //jefe se le muestran rendiciones en este estado
		//implementar notificacion
	}
	public List<Rendition> getRenditionsByAreaId(int areaId) {
		List<Rendition> renditions = new ArrayList<>();
		renditionRepository.findByAreaId(areaId).forEach(renditions::add);
		return renditions;
	}

	public List<Rendition> getBossRenditionInbox(String bossUsername) {
		int areaId = userRepository.findById(bossUsername).get().getArea().getId();
		List<Rendition> renditions;
		return renditions = getRenditionsByAreaId(areaId);
		
	}
}
