package runner;


import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.CucumberOptions;


//@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/Feature Files/Firstwebsite.feature"}, glue = {"sd"},
        format = {"json:target/cucumber.json", "html:/target/site/cucumber-pretty"},
        plugin = {"pretty",
                "html:target/site/cucumber-pretty",
                "json:target/site/cucumber.json"})

public class Test extends AbstractTestNGCucumberTests {

}
