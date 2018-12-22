package renditionproject.areas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AreaService {
	
	@Autowired
	private AreaRepository areaRepository;
	
	public List<Area> getAllAreas() {
		List<Area> areas = new ArrayList<>();
		areaRepository.findAll().forEach(areas::add);
		return areas;
	}

	public Area addArea(Area area) {
		return areaRepository.save(area);
	}
}
