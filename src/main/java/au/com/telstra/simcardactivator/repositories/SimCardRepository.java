package au.com.telstra.simcardactivator.repositories;

import au.com.telstra.simcardactivator.models.SimCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimCardRepository extends CrudRepository<SimCard, Long> {

}
