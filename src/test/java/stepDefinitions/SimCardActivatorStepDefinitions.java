package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.models.ActivateRequest;
import au.com.telstra.simcardactivator.models.ActuateRequest;
import au.com.telstra.simcardactivator.models.ActuateResponse;
import au.com.telstra.simcardactivator.models.SimCard;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.apache.coyote.Response;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${activator.test.url}")
    private String serviceURL;
    private ActivateRequest request;
    private SimCard sim;

    @Given("SIM card ICCID is {string}")
    public void simCardICCIDIs(String arg0) {
        ActivateRequest request = new ActivateRequest();
        request.setCustomerEmail("billyjoel@hmail.com");
        request.setIccid(arg0);
    }

    @When("client makes call to POST activate")
    public void clientMakesCallToPOSTActivate() {
        String uri = serviceURL + "/activate";

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<ActivateRequest> requestEntity = new HttpEntity<>(request, headers);

        restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
    }

    @When("client makes call to GET sim?id={int}")
    public void clientMakesCallToGETSimId(int arg0) {
        String uri = serviceURL + "/sim?id=" + arg0;

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<SimCard> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, SimCard.class);

        sim = response.getBody();
    }

    @Then("SIM card activation must be {string}")
    public void simCardActivationMustBe(String arg0) {
        boolean desiredResult = arg0.equals("true");

        Assert.assertEquals(sim.isActive(), desiredResult);
    }
}