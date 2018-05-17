package pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ResourceBundle;

public class Login_page {

    WebDriver driver;
    ResourceBundle bundle = ResourceBundle.getBundle("Properties file/login_page");

    public Login_page(WebDriver driver){

        this.driver = driver;

    }
    public void userName(String user_name )
    {
		 /*driver.findElement(By.cssSelector(bundle.getString("login_page_username_txt_box_css"))).clear();
		 driver.findElement(By.cssSelector(bundle.getString("login_page_username_txt_box_css"))).sendKeys(user_name); //Enter User name
*/	 }

    public void password (String password )
    {
        //	System.out.println(driver.getCurrentUrl());
		 /*driver.findElement(By.cssSelector(bundle.getString("login_page_password_txt_box_css"))).clear();
		 driver.findElement(By.cssSelector(bundle.getString("login_page_password_txt_box_css"))).sendKeys("PEMBROKE"); //Enter password
*/	 }

    public void submitButton ()
    {
        System.out.println("driver=" + driver);
        driver.findElement(By.cssSelector(bundle.getString("login_page_submit_button"))).click();

    }
}
