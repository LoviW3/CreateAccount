import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginStepDefs {
    private WebDriver driver;
    @Given("I fill in my <DoB>")
    public void iFillInMyDoB(String DoB) {
        driver = new FirefoxDriver();
        driver.get("https://membership.basketballengland.co.uk/NewSupporterAccount");
        WebElement field = driver.findElement(By.id("dp"));
        field.sendKeys(DoB);
    }

    @And("I fill in my <firstName> and <lastName>")
    public void iFillInMyFirstNameAndLastName(String firstName, String lastName) {
        WebElement firstField = driver.findElement(By.id("member_firstname"));
        firstField.sendKeys(firstName);
        WebElement lastField = driver.findElement(By.id("member_lastname"));
        lastField.sendKeys(lastName);
    }

    @And("I fill in my <email> and then confirm it")
    public void iFillInMyEmailAndThenConfirmIt(String email) {
        WebElement firstField = driver.findElement(By.id("member_emailaddress"));
        firstField.sendKeys(email);
        WebElement secondField = driver.findElement(By.id("member_confirmemailaddress"));
        secondField.sendKeys(email);
    }

    @And("I create a <password> and then re-enter it <passAgain>")
    public void iCreateAPasswordAndThenReEnterItPassAgain(String password, String passAgain) {
        WebElement firstField = driver.findElement(By.id("signupunlicenced_password"));
        firstField.sendKeys(password);
        WebElement secondField = driver.findElement(By.id("signuplicenced_confirmpassword"));
        secondField.sendKeys(passAgain);
    }

    @When("I agree to the terms and conditions")
    public void iAgreeToTheTermsAndConditions() {
        WebElement boxOne = driver.findElement(By.cssSelector("sign_up_25"));
        boxOne.click();
        WebElement boxTwo = driver.findElement(By.cssSelector("sign_up_26"));
        boxTwo.click();
        WebElement boxThree = driver.findElement(By.cssSelector("fanmembersignup_agreetocodeofethicsandconduct"));
        boxThree.click();
    }

    @And("I confirm the information")
    public void iConfirmTheInformation() {
    }

    @Then("I get an account confirmation")
    public void iGetAnAccountConfirmation() {
        WebElement button = driver.findElement(By.name("join"));
        button.click();
    }
}
