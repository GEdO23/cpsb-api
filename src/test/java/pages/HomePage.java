package pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    public final By title = By.id("home-title");

    public void isOnHomePage() {
        isVisible(title);
    }
}
