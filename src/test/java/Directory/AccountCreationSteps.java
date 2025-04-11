package Directory;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertTrue;

public class AccountCreationSteps {
    WebDriver driver;
    WebDriverWait wait;

    private void waitForCheckAndClick(String labelID){
        By labelLocator = By.cssSelector("label[for='"+labelID+"']");
        wait.until(ExpectedConditions.elementToBeClickable(labelLocator));
        WebElement label = driver.findElement(labelLocator);
        label.click();
        /*The code below is here as a safety net in the case of a checkbox not being clickable on the first try because
        //error messages or other web side information that take president over the selenium program*/
        WebElement check = driver.findElement(By.id(labelID));
        if (!check.isSelected()){
            label.click();
        }
    }

    @Given("I am at the create an account page using {string}")
    //here the program chooses browser based on which browser is chosen in the scenario examples
    public void iAmAtTheCreateAnAccountPageUsing(String browser) {
        if (browser.equals("chrome")) driver = new ChromeDriver();
        if (browser.equals("firefox")) driver = new FirefoxDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
    }

    @When("I create an account i have to fill in {string}")
    //this code types out the chosen Date of Birth, it doesn't use the webpages calendar
    public void iCreateAnAccountIHaveToFillIn(String DoB) {
        WebElement field = driver.findElement(By.id("dp"));
        field.sendKeys(DoB);
    }

    @And("Fill in my {string} and {string}")
    //made the filling in off the users first and last name the same step to make it more efficient
    public void fillInMyAnd(String fName, String lName) {
        WebElement field1 = driver.findElement(By.name("Forename"));
        field1.sendKeys(fName);
        WebElement field2 = driver.findElement(By.name("Surname"));
        field2.sendKeys(lName);

    }

    @And("Fill in my {string} and confirm it")
    /*as none of the tests we were asked to make asked for a test that checked for an error regarding the email address
    fields, I decided to let the program fill in the same data in both of the fields. It is relatively easy to change it
    to make such a testcase be viable*/
    public void fillInMyAndConfirmIt(String email) {
        WebElement field1 = driver.findElement(By.name("EmailAddress"));
        field1.sendKeys(email);
        WebElement field2 = driver.findElement(By.name("ConfirmEmailAddress"));
        field2.sendKeys(email);
    }

    @And("Create a {string} and re-enter it {string}")
    /*needed two different names for the password since it needed to be different for a test case, so decided passcode
    was a good and easily understood choice for the name */
    public void createAAndReEnterIt(String password, String passcode) {
        WebElement field1 = driver.findElement(By.name("Password"));
        field1.sendKeys(password);
        WebElement field2 = driver.findElement(By.name("ConfirmPassword"));
        field2.sendKeys(passcode);
    }

    @And("Agree to the terms and conditions {string}")
    public void agreeToTheTermsAndConditions(String agree) {
        if (agree.equals("true")){
            /*the wait here is crucial for the program to actually click on any of the boxes that is asked to click on,
            don't know why though as it works with as little time as just 1 millisecond */
            wait = new WebDriverWait(driver, Duration.ofMillis(1));
            waitForCheckAndClick("sign_up_25");
            waitForCheckAndClick("sign_up_26");
            waitForCheckAndClick("fanmembersignup_agreetocodeofethicsandconduct");
        }
    }

    @And("Click the confirm and join button")
    //this code simply clicks on the join button
    public void clickTheConfirmAndJoinButton() {
        WebElement join = driver.findElement(By.name("join"));
        join.click();
    }

    @Then("The account is successfully created {string}")
    public void theAccountIsSuccessfullyCreated(String outcome) {
        /*I wanted all the test scenarios to be passable and decided to add a new column on the scenario example that
        told the user if the test should or shouldn't be able to make an account. This set up should catch if any of the
        tests would create an account without all the obligated information has been given/agreed to*/
        if (outcome.equals("yes")){
            boolean accountMade = driver.getPageSource().contains("THANK YOU FOR CREATING AN ACCOUNT WITH BASKETBALL ENGLAND");
            assertTrue(accountMade);
        } else {
            boolean accountFailed = driver.getPageSource().contains("CREATE AN ACCOUNT");
            assertTrue(accountFailed);
        }
    }
}
