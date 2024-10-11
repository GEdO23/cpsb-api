package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    public final By title = By.id("home-title");
    public final By btnExit = By.id("btn-exit");

    public void isOnHomePage() {
        isVisible(title);
    }
}
