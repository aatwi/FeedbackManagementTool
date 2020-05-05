package features;

import com.google.gson.Gson;
import features.data.UserFeature;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import static features.UrlBuilder.USER_CREATION_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserManagementStepDefsTest {

    @Autowired
    private RestTemplate restTemplate;
    private ResponseEntity<String> userCreatedResponseEntity;
    private UserFeature userToBeCreated;
    private UserFeature createdUser;

    @Given("The user is on the Create New Account page")
    public void theUserIsOnTheCreateNewAccountPage() {
    }

    @When("The user inputs the required information {string} {string} {string}")
    public void theUserInputsTheRequiredInformation(String name, String email, String password) {
        userToBeCreated = new UserFeature(email, name, password);
        try {
            userCreatedResponseEntity = restTemplate.postForEntity(USER_CREATION_URL, new HttpEntity<>(userToBeCreated), String.class);
        } catch (RestClientResponseException exp) {
            userCreatedResponseEntity = ResponseEntity.status(exp.getRawStatusCode()).body(exp.getResponseBodyAsString());
        }
    }

    @Then("The system should try to create an account")
    public void theSystemShouldTryToCreateAnAccount() {
        Gson gson = new Gson();
        createdUser = gson.fromJson(userCreatedResponseEntity.getBody(), UserFeature.class);
    }

    @And("The user should receive a notification with response {string}")
    public void theUserShouldReceiveANotificationWithResponse(String expectedResponse) {
        HttpStatus statusCode = userCreatedResponseEntity.getStatusCode();
        assertEquals(expectedResponse, statusCode.getReasonPhrase());

        if (statusCode.equals(HttpStatus.CREATED)) {
            assertUserObject();
        }
    }

    private void assertUserObject() {
        assertEquals(userToBeCreated.getName(), createdUser.getName());
        assertEquals(userToBeCreated.getEmail(), createdUser.getEmail());
        assertEquals(userToBeCreated.getPassword(), createdUser.getPassword());
    }
}
