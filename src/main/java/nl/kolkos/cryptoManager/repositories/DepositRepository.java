package nl.kolkos.cryptoManager.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import nl.kolkos.cryptoManager.Deposit;
import nl.kolkos.cryptoManager.Wallet;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

public interface DepositRepository extends CrudRepository<Deposit, Long> {
	List<Deposit> findByWallet(Wallet wallet);
	
	List<Deposit> findAllByOrderByDepositDateAsc();
	
	@Query(value="SELECT COALESCE((SELECT SUM(amount) FROM deposit WHERE wallet_id = ?1), 0) AS aantal", nativeQuery = true)
	double getSumOfAmountForWalletId(long walletId);
	
	@Query(value="SELECT COALESCE((SELECT SUM(purchase_value) FROM deposit WHERE wallet_id = ?1), 0) AS aantal", nativeQuery = true)
	double getSumOfPurchaseValueForWalletId(long walletId);
	
	
	@Query(value="SELECT * FROM deposit WHERE id IN( " + 
			"    SELECT deposit.id FROM deposit, wallet, portfolio, coin " + 
			"	WHERE deposit.wallet_id = wallet.id " + 
			"	AND wallet.coin_id = coin.id " + 
			"	AND wallet.portfolio_id = portfolio.id " + 
			"	AND coin.id LIKE ?1 " + 
			"	AND wallet.id LIKE ?2 " + 
			"	AND portfolio.id LIKE ?3)", nativeQuery = true)
	List<Deposit> filterResults(String coinId, String walletId, String PortfolioId);
	
	
}
