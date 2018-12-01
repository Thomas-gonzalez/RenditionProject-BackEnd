package renditionproject.renditions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import renditionproject.users.User;
import renditionproject.users.UserRepository;
import renditionproject.usertypes.UserType;

@Service
public class RenditionService {

	@Autowired
	private RenditionRepository renditionRepository;
	@Autowired
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

	public Rendition addRendition(Rendition rendition, String employeeUsername) {
		User employee = userRepository.findById(employeeUsername).get();
		UserType userType = employee.getUserType();
		if (!userType.getName().equals("employee")) {
			return null; //error tratando de crear una rendicion para un usuario no empleado
		}
		rendition.setArea(employee.getArea());
		rendition.setEmployee(employee);
		rendition.setState(1);
		return renditionRepository.save(rendition);
		
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
		rendition.setValueTotal(0);
		renditionRepository.save(rendition);		
		
	}

	public Rendition getRendition(long id) {
		return renditionRepository.findById(id).get();
	}

	public Rendition sendRendition(long id) {
		Rendition rendition = getRendition(id);
		if (rendition.getState() < 2) rendition.setState(2); //proteccion ante que se llame este metodo al estar ya rechazado/aprobado
		renditionRepository.save(rendition);//jefe se le muestran rendiciones en este estado
		//implementar notificacion
		rendition = renditionRepository.save(rendition);
		rendition.setSentDatetime(rendition.getLastUpdateDatetime());// investigar como obtener tiempo para no hacerlo asi.
		return rendition;
	}
	public List<Rendition> getRenditionsByAreaId(int areaId) {
		List<Rendition> renditions = new ArrayList<>();
		renditionRepository.findByAreaId(areaId).forEach(renditions::add);
		return renditions;
	}

	public List<Rendition> getBossRenditionInbox(String bossUsername) {
		int areaId = userRepository.findById(bossUsername).get().getArea().getId();
		List<Rendition> renditions;
		//filtrando por rendiciones enviadas, y por rendiciones no cerradas.
		return renditions = getRenditionsByAreaId(areaId).stream().filter(r -> r.getState() == 2 && !r.isClosed()).
				collect(Collectors.toList());	
	}
	public List<Rendition> getManagerRenditionInbox() {
		List<Rendition> openrenditions = getAllRenditions().stream().filter(r -> !r.isClosed() && (r.getState() == 4 || r.getState() == 6))
				.collect(Collectors.toList());
		return openrenditions;
	}
	public Rendition renditionBossApproval(long id) {
		Rendition rendition = getRendition(id);
		rendition.setState(4);
		return renditionRepository.save(rendition);
	}
	public Rendition renditionBossDecline(long id, Rendition renditionDES) {
		Rendition rendition = getRendition(id);
		rendition.setState(3);
		rendition.setDeclineDescription(renditionDES.getDeclineDescription());
		return renditionRepository.save(rendition);
	}
	public Rendition renditionManagerApproval(long id) {
		Rendition rendition = getRendition(id);
		rendition.setState(6);
		return renditionRepository.save(rendition);
	}
	public Rendition renditionManagerDecline(long id, Rendition renditionDES) {
		Rendition rendition = getRendition(id);
		rendition.setState(5);
		rendition.setDeclineDescription(renditionDES.getDeclineDescription());
		return renditionRepository.save(rendition);
	}

} 
