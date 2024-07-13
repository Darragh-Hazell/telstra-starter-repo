package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.models.ActivateRequest;
import au.com.telstra.simcardactivator.models.ActuateRequest;
import au.com.telstra.simcardactivator.models.ActuateResponse;
import au.com.telstra.simcardactivator.models.SimCard;
import au.com.telstra.simcardactivator.proxies.SimActuatorProxy;
import au.com.telstra.simcardactivator.services.SimCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SimCardController {

    private final SimActuatorProxy simActuator;
    private final SimCardService simCardService;

    @Autowired
    public SimCardController(SimActuatorProxy simActuator, SimCardService simCardService) {
        this.simActuator = simActuator;
        this.simCardService = simCardService;
    }

    @PostMapping("/activate")
    public void activateSim(@RequestBody ActivateRequest activateRequest) {
        ActuateRequest actuateRequest = new ActuateRequest();
        actuateRequest.setIccid(activateRequest.getIccid());
        ActuateResponse actuateResponse = simActuator.actuateSim(actuateRequest);

        SimCard sim = new SimCard(
                activateRequest.getIccid(),
                activateRequest.getCustomerEmail(),
                actuateResponse.isSuccess()
        );

        simCardService.saveSimCard(sim);
    }

    @GetMapping("/sim")
    @ResponseBody
    public SimCard getSim(@RequestParam Long id) {
        return simCardService.getSimCard(id);
    }
}
