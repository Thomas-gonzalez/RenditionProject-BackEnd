package renditionproject.renditions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RenditionController {

	@Autowired
	private RenditionService renditionService;

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
	public void addRendition(@RequestBody Rendition rendition, @PathVariable String employeeUsername) {
		renditionService.addRendition(rendition, employeeUsername);
	}
	//actualizar una rendicion (pre-envio), *solo para empleado*	
	@RequestMapping(method = RequestMethod.PUT, value = "/{employeeUsername}/renditions")
	public void updateRendition(@RequestBody Rendition rendition, @PathVariable String employeeUsername) {
		renditionService.updateRendition(rendition, employeeUsername);
	}
	//rendicion por id
	@RequestMapping("/{employeeUsername}/renditions/{id}")
	public Rendition getRendition( @PathVariable long id) {
		return renditionService.getRendition(id);
	}
	//rendicion cambia a estado enviada y por revisar y se muestra en la bandeja de el jefe (falta notificar)
	@RequestMapping(method = RequestMethod.PUT, value = "/{employeeUsername}/renditions/{id}")
	public void sendRendition(@PathVariable long id) {
		renditionService.sendRendition(id);
	}
	
	//acceder a rendiciones para jefe
	@RequestMapping("/boss/{bossUserame}/renditions")
	public List<Rendition> getBossRenditionInbox(@PathVariable String bossUsername) {
		return renditionService.getBossRenditionInbox(bossUsername);
	}
	
}
