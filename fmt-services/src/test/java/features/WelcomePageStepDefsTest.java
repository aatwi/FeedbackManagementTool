package features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.nio.charset.Charset;

import static features.UrlBuilder.HOME_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WelcomePageStepDefsTest {

    @Autowired
    RestTemplate restTemplate;

    String welcomeResponse;

    @Given("The system is up and running")
    public void theSystemIsUpAndRunning() {
    }

    @When("A user navigates to the home page")
    public void aUserNavigatesToTheHomePage() {
        welcomeResponse = restTemplate.execute(HOME_PAGE, HttpMethod.GET, null, clientHttpResponse -> {
            InputStream responseStream = clientHttpResponse.getBody();
            return IOUtils.toString(responseStream, Charset.defaultCharset());
        });
    }

    @Then("The user receives the welcome message {string}")
    public void theUserReceivesTheWelcomeMessage(String message) {
        assertEquals(message, welcomeResponse);
    }
}
