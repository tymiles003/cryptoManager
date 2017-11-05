package nl.kolkos.cryptoManager;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/deposit") // This means URL's start with /demo (after Application path)
public class DepositController {
	@Autowired
	@Qualifier(value = "depositRepository")
	private DepositRepository depositRepository;
	
	@Autowired
	@Qualifier(value = "walletRepository")
	private WalletRepository walletRepository;
	
	
	@GetMapping("/")
    public String forwardDepositForm(Model model) {
        model.addAttribute("deposit", new Deposit());
        return "deposit_form";
    }
	
	@GetMapping("/add")
    public String depositForm(Model model) {
        model.addAttribute("deposit", new Deposit());
        model.addAttribute("wallet", new Wallet());
        
        
        return "deposit_form";
    }
	
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody String addNewDeposit (
			@RequestParam Date depositDate,
			@RequestParam int walletId,
			@RequestParam double amount,
			@RequestParam double purchaseValue,
			@RequestParam String remarks) {

				
		Deposit deposit = new Deposit();
		deposit.setDepositDate(depositDate);
		deposit.setWalletId(walletId);
		deposit.setAmount(amount);
		deposit.setPurchaseValue(purchaseValue);
		deposit.setRemarks(remarks);
		
		depositRepository.save(deposit);
		
		
		return "Deposit saved";
	}

	@GetMapping(path="/list")
	public @ResponseBody Iterable<Deposit> getAllDeposits() {
		// This returns a JSON or XML with the users
		return depositRepository.findAll();
	}
}
