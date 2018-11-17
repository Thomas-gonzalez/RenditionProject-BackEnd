package renditionproject.currencies;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {
	
	@Autowired
	private CurrencyRepository currencyRepository;

	public List<Currency> getAllCurrencies() {
		List<Currency> currencies = new ArrayList<>();
		currencyRepository.findAll().forEach(currencies::add);
		return currencies;
	}
	
	public void addCurrency(Currency currency) {
		currencyRepository.save(currency);
	}
}
