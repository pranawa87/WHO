package sd.Home_sd;


import base.Browser_setup;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;
import pom.login.Login_page_pom;



public class Home_sd extends Browser_setup {
    Login_page_pom login = new Login_page_pom(driver);

    @And("^User click at login$")
    public void userClickAtLogin() {

        login.loginLink();

    }

    @Then("^user should navigate to Login page$")
    public void userShouldNavigateToLoginPage() {
    }

    @And("^user enter user \"([^\"]*)\"$")
    public void userEnterUserName(String credentials)  {

        login.userName(credentials);

    }

    @And("^user enter pass \"([^\"]*)\"$")
    public void userEnterPassword(String credentials)  {

        login.password(credentials);


    }

    @And("^user navigate back$")
    public void userNavigateBack() {
        driver.navigate().back();

    }

    @And("^user select at BMW radio button$")
    public void userSelectAtBMWRadioButton() throws InterruptedException {
        driver.findElement(By.id("bmwradio")).click();
        Thread.sleep(3000);
        driver.quit();
    }


}
