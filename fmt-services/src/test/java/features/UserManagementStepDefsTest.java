package features;

import com.google.gson.Gson;
import features.data.UserFeature;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static features.UrlBuilder.USER_CREATION_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.CREATED;

public class UserManagementStepDefsTest {

    @Autowired
    private RestTemplate restTemplate;
    private ResponseEntity<String> userCreatedResponseEntity;
    private UserFeature userToBeCreated;

    @Given("A user sends a request to create a new account in the system")
    public void aUserSendsARequestToCreateANewAccountInTheSystem() {
    }

    @When("The user inputs the required information {string} {word} {word}")
    public void theUserInputsTheRequiredInformationNameEmailPassword(String name, String email, String password) {
        userToBeCreated = new UserFeature(email, name, password);
        userCreatedResponseEntity = restTemplate.postForEntity(USER_CREATION_URL, new HttpEntity<>(userToBeCreated), String.class);
    }

    @Then("An account should be created in the system")
    public void anAccountShouldBeCreatedInTheSystem() {
        Gson gson = new Gson();
        UserFeature userFeature = gson.fromJson(userCreatedResponseEntity.getBody(), UserFeature.class);

        Assertions.assertEquals(userToBeCreated.getName(), userFeature.getName());
        Assertions.assertEquals(userToBeCreated.getEmail(), userFeature.getEmail());
        Assertions.assertEquals(userToBeCreated.getPassword(), userFeature.getPassword());
    }

    @And("The user should receive a Success notification")
    public void theUserShouldReceiveASuccessNotification() {
        assertEquals(CREATED, userCreatedResponseEntity.getStatusCode());
    }
}
