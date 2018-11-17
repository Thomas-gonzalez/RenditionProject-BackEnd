package renditionproject.companies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;

	public List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<>();
		companyRepository.findAll().forEach(companies::add);
		return companies;
	}
	
	public void addCompany(Company company) {
		companyRepository.save(company);
	}
}
