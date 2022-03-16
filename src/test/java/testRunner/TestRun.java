package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features = "./Features/Customers.feature",
        features = {"./Features"},
        glue =  "stepDefinitions",
        dryRun = false,
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-reports"},
        tags= "@sanity"
        //tags ={"@sanity","@regression"}--both sanity and regression combination executes(and operator)
        //tags = {"@sanity,@regression"}--or operator
)

public class TestRun {
}
