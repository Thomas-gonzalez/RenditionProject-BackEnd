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

	@RequestMapping("/renditions")
	public List<Rendition> getAllRenditions() {
		return renditionService.getAllRenditions();
	}
	//acceder a las rendiciones de un empleado en particular.
	//debe ser accedido solo por empleado en bandeja o jefe/encargado por filtros.
	@RequestMapping("/{employeeUsername}/renditions")
	public List<Rendition> getRenditionsByEmployeeUsername(@PathVariable String employeeUsername) {
		return renditionService.getRenditionsByEmployeeUsername(employeeUsername);
	}
}
