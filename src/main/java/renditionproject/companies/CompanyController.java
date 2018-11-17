package renditionproject.companies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	
	@RequestMapping("/companies")
	public List<Company> getAllCompanies() {
		return companyService.getAllCompanies();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/companies")
	public void addCompany(@RequestBody Company company) {
		companyService.addCompany(company);
	}
}
