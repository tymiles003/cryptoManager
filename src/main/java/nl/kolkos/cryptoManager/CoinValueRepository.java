package nl.kolkos.cryptoManager;

import org.springframework.data.repository.CrudRepository;

import nl.kolkos.cryptoManager.CoinValue;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

public interface CoinValueRepository extends CrudRepository<CoinValue, Long> {

}