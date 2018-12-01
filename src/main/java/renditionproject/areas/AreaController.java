package renditionproject.areas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AreaController {
	
	@Autowired
	private AreaService areaService;

	@RequestMapping("/areas")
	public List<Area> getAllAreas() {
		return areaService.getAllAreas();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/areas")
	public void addArea(@RequestBody Area area) {
		areaService.addArea(area);
	}
}
