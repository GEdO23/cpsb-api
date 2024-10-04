package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Setup;

import java.time.Duration;

public class BasePage extends Setup {

    protected WebElement waitElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected boolean isVisible(By locator) {
        waitElement(locator);
        return driver.findElement(locator).isDisplayed();
    }

    protected void fillInput(By locator, String text) {
        isVisible(locator);
        driver.findElement(locator).sendKeys(text);
    }

    protected void click(By locator) {
        isVisible(locator);
        driver.findElement(locator).click();
    }
}
