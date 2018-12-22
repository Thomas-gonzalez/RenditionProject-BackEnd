package renditionproject.renditions;

import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import renditionproject.profiles.ProfileService;

import org.springframework.web.bind.annotation.CrossOrigin;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RenditionController {

	@Autowired
	private RenditionService renditionService;
	@Autowired
	private ProfileService profileService;

	//para encargado
	@RequestMapping("/renditions")
	public List<Rendition> getAllRenditions() {
		return renditionService.getAllRenditions();
	}
	//acceder a las rendiciones de un empleado en particular.
	//debe ser accedido solo por empleado en bandeja
	@RequestMapping("/{employeeUsername}/renditions")
	public List<Rendition> getRenditionsByEmployeeUsername(@PathVariable String employeeUsername) {
		return renditionService.getRenditionsByEmployeeUsername(employeeUsername);
	}
	//guardar una rendicion (pre-envio), *solo para empleado*
	@RequestMapping(method = RequestMethod.POST, value = "/{employeeUsername}/renditions")
	public Rendition addRendition(@RequestBody Rendition rendition, @PathVariable String employeeUsername) throws ServletException {
		if (profileService.getEmployeeProfile(employeeUsername) == null) {
			throw new ServletException("Este usuario no tiene perfil de empleado");
		}
		return renditionService.addRendition(rendition, employeeUsername);
	}
	//rendicion por id
	@RequestMapping("/{employeeUsername}/renditions/{id}")
	public Rendition getRendition( @PathVariable long id) {
		return renditionService.getRendition(id);
	}
	//rendicion cambia a estado enviada y por revisar y se muestra en la bandeja de el jefe (falta notificar)
	@RequestMapping(method = RequestMethod.GET, value = "/{employeeUsername}/renditions/{id}/send")
	public Rendition sendRendition(@PathVariable long id) {
		return renditionService.sendRendition(id);
	}
	
	//acceder a rendiciones para jefe
	@RequestMapping("/boss/{bossUsername}/renditions")
	public List<Rendition> getBossRenditionInbox(@PathVariable String bossUsername) {
		return renditionService.getBossRenditionInbox(bossUsername);
	}
	
	//acceder a rendiciones para encargados
	@RequestMapping("/manager/{managerUsername}/renditions")
	public List<Rendition> getManagerRenditionInbox() {
		return renditionService.getManagerRenditionInbox();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "boss/{bossUsername}/renditions/{id}/approve")
	public Rendition renditionBossApproval(@PathVariable long id) {
		return renditionService.renditionBossApproval(id);
	}
	//implement rejection
	@RequestMapping(method = RequestMethod.POST, value ="boss/{bossUsername}/renditions/{id}/decline")
	public Rendition renditionBossDecline(@PathVariable long id, @RequestBody Rendition rendition) {
		return renditionService.renditionBossDecline(id, rendition);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "manager/{managerUsername}/renditions/{id}/approve")
	public Rendition renditionManagerApproval(@PathVariable long id) {
		return renditionService.renditionManagerApproval(id);
	}
	//implement rejection
	@RequestMapping(method = RequestMethod.POST, value ="manager/{managerUsername}/renditions/{id}/decline")
	public Rendition renditionManagerDecline(@PathVariable long id, @RequestBody Rendition rendition) {
		return renditionService.renditionManagerDecline(id, rendition);
	}
	//borra la rendicion y sus gastos y las imagenes de cada gasto
	@RequestMapping(method = RequestMethod.DELETE, value = "/{employeeUsername}/renditions/{id}")
	public void deleteRendition(@PathVariable long id) {
		renditionService.deleteRendition(id);
	}
	@RequestMapping("{employeeUsername}/renditions/{id}/close")
	public Rendition closeRendition(@PathVariable long id) {
		return renditionService.closeRendition(id);
	}
	
}
