package pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    public final String validUserName = "Gabriel";
    public final String validUserPassword = "12345";

    public final By etUserName = By.id("username");
    public final By etUserPassword = By.id("password");
    public final By btnLogin = By.id("btn-login");

    public final By tvLoginError = By.id("alert-invalid-user");
    public final By tvLogoutSuccess = By.id("alert-logout-success");

    public void fillUserName() {
        fillInput(etUserName, validUserName);
    }

    public void fillUserPassword() {
        fillInput(etUserPassword, validUserPassword);
    }

    public void clickBtnSignIn() {
        click(btnLogin);
    }

    public void isOnLoginPage() {
        driver.get("http://localhost:8080/login");
        isVisible(btnLogin);
    }

}
