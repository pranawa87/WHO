package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class Browser_setup {

    public static WebDriver driver;

    ResourceBundle launch = ResourceBundle.getBundle("browser_setup");

    public void browser() throws MalformedURLException {
        URL server = new URL(launch.getString("hub"));
        if (launch.getString("browser").equalsIgnoreCase("chrome")) {
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability("Version", "");
            cap.setCapability("platform", "LINUX");
            driver = new RemoteWebDriver(server, cap);
            System.out.println("Chrome Browser Launch");
            /*System.setProperty("webdriver.chrome.driver", "../../../Drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
*/
        } else if (launch.getString("browser").equalsIgnoreCase("firefox")) {

        } else {
            System.out.println("Setup browser properly from this option: chrome or firefox");
        }
    }

}
