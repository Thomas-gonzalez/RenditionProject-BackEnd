package renditionproject.currencies;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

	@Autowired
	private CurrencyService currencyService;
	
	@RequestMapping("/currencies")
	public List<Currency> getAllCurrencies() {
		return currencyService.getAllCurrencies();
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/currencies")
	public void addCurrency(@RequestBody Currency currency) {
		currencyService.addCurrency(currency);
	}
}
