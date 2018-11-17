package renditionproject.renditions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RenditionService {

	private RenditionRepository renditionRepository;
	
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
}
