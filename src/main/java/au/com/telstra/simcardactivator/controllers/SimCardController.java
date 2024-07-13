package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.models.ActivateRequest;
import au.com.telstra.simcardactivator.models.ActuateRequest;
import au.com.telstra.simcardactivator.models.ActuateResponse;
import au.com.telstra.simcardactivator.proxies.SimActuatorProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimCardController {

    private final SimActuatorProxy simActuator;

    @Autowired
    public SimCardController(SimActuatorProxy simActuator) {
        this.simActuator = simActuator;
    }

    @PostMapping("/activate")
    public void activateSim(ActivateRequest activateRequest) {
        ActuateRequest actuateRequest = new ActuateRequest();
        actuateRequest.setIccid(activateRequest.getIccid());
        ActuateResponse actuateResponse = simActuator.actuateSim(actuateRequest);
    }
}
