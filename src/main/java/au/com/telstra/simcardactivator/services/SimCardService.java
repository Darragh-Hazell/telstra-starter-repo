package au.com.telstra.simcardactivator.services;

import au.com.telstra.simcardactivator.models.SimCard;
import au.com.telstra.simcardactivator.repositories.SimCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimCardService {

    private final SimCardRepository simCardRepository;

    @Autowired
    public SimCardService(SimCardRepository simCardRepository) {
        this.simCardRepository = simCardRepository;
    }

    public void saveSimCard(SimCard simCard) {
        simCardRepository.save(simCard);
    }

    public SimCard getSimCard(Long id) {
        Optional<SimCard> result = simCardRepository.findById(id);
        return result.orElse(null);
    }
}
