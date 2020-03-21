package features;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UserManagementStepDefsTest extends SpringIntegrationTest {

    String url = "http://localhost:8082/";

    @Given("A user sends a request to create a new account in the system")
    public void aUserSendsARequestToCreateANewAccountInTheSystem() {
        throwPendingException();
    }

    @When("The user inputs the required information {string} {word} {word}")
    public void theUserInputsTheRequiredInformationNameEmailPassword(String name, String email, String password) {
        throwPendingException();
    }

    @Then("An account should be created in the system")
    public void anAccountShouldBeCreatedInTheSystem() {
        throwPendingException();
    }

    @And("The user should receive a Success notification")
    public void theUserShouldReceiveASuccessNotification() {
        throwPendingException();
    }

    private void throwPendingException() {
        throw new PendingException();
    }
}
