package pom.login;

import com.thoughtworks.selenium.Selenium;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class Login_page_pom {
    private WebDriver driver;
   private ResourceBundle bundle = ResourceBundle.getBundle("login_page");


    public Login_page_pom(WebDriver driver) {
        this.driver = driver;

    }

    public void loginLink() {
        System.out.println("driver value is = " + driver);
        driver.findElement(By.xpath(bundle.getString("loginLink_xpath"))).click();

    }

    public void userName(String user_name) {
        driver.manage().timeouts().implicitlyWait(25000, TimeUnit.SECONDS);

        System.out.println("User Name is : " + user_name);

        // driver.findElement(By.id("user_email")).clear();
        driver.findElement(By.cssSelector("#user_email")).sendKeys(user_name);
    }

    public void password(String password) {

        driver.findElement(By.cssSelector("#user_password")).clear();
        driver.findElement(By.cssSelector("#user_password")).sendKeys(password);
        
    }

   /*   public void submitButton() {
      System.out.println("driver=" + driver);
        driver.findElement(By.cssSelector(bundle.getString("login_page_submit_button"))).click();

    }*/

}
