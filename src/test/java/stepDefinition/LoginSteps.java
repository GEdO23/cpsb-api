package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;

public class LoginSteps extends BasePage {

    LoginPage login = new LoginPage();
    HomePage home = new HomePage();

    @Given("i am in login screen")
    public void iAmInLoginScreen() {
        login.isOnLoginPage();
    }

    @When("enter with valid credentials")
    public void enterWithValidCredentials() {
        login.fillUserName();
        login.fillUserPassword();
    }

    @And("click submit button")
    public void clickSubmitButton() {
        login.clickBtnSignIn();
    }

    @Then("view home screen")
    public void viewHomeScreen() {
        home.isOnHomePage();
    }

    @When("enter with invalid username")
    public void enterWithInvalidUsername() {
        fillInput(login.etUserName, "invalid user");
        login.fillUserPassword();
    }

    @When("enter with invalid password")
    public void enterWithInvalidPassword() {
        login.fillUserName();
        fillInput(login.etUserPassword, "invalid password");
    }

    @Then("view login error message")
    public void viewLoginErrorMessage() {
        isVisible(login.tvLoginError);
    }

    @Then("view logout success message")
    public void viewLogoutSuccessMessage() {
        isVisible(login.tvLogoutSuccess);
    }

    @When("click sign off button")
    public void clickSignOffButton() {
        isVisible(home.btnExit);
        click(home.btnExit);
    }
}
