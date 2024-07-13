package au.com.telstra.simcardactivator.proxies;

import au.com.telstra.simcardactivator.models.ActuateRequest;
import au.com.telstra.simcardactivator.models.ActuateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SimActuatorProxy {

    private final RestTemplate restTemplate;

    @Value("${actuator.service.url}")
    private String actuatorServiceUrl;

    @Autowired
    public SimActuatorProxy(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ActuateResponse actuateSim(ActuateRequest request) {
        String uri = actuatorServiceUrl + "/actuate";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ActuateRequest> requestEntity = new HttpEntity<>(request, headers);

        ResponseEntity<ActuateResponse> response =
                restTemplate.exchange(uri, HttpMethod.POST, requestEntity, ActuateResponse.class);

        return response.getBody();
    }
}
