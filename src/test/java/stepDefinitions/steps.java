package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class steps extends BaseClass{

    @Before
    public void setup() throws IOException {
        //Reading properties
        configProp = new Properties();
        FileInputStream configPropfile = new FileInputStream("./config.properties");
        configProp.load(configPropfile);

        //Logger Configuration
        logger = Logger.getLogger("nopCommerce"); // Added logger
        PropertyConfigurator.configure("log4j.properties"); // Added logger
        //System.setProperty("webdriver.chrome.driver","./Drivers/chromedriver.exe");
        String browser = configProp.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", configProp.getProperty("chromepath"));
            driver = new ChromeDriver();
        }
       else if (browser.equalsIgnoreCase("Edge"))
        {
            System.setProperty("webdriver.edge.driver",configProp.getProperty("edgepath"));
            driver = new EdgeDriver();
        }
    }

    @Given("User Launch Chrome Browser")
    public void user_launch_chrome_browser() {

        logger.info("******* Launching Browser ******* ");
        loginPage = new LoginPage(driver);

    }
    @When("User Opens URL {string}")
    public void user_opens_url(String url) {
        logger.info("******* Opening URL ******* ");
        driver.get(url);
    }
    @When("User enters Email as {string} and password as {string}")
    public void user_enters_email_as_and_password_as(String email, String password) {
        logger.info("******** Enter User Info ********");
    loginPage.setUserName(email);
    loginPage.setPassword(password);
    }

    @When("Click on Login")
    public void click_on_login() {
        logger.info("********Started login ****************");
        loginPage.clickLogin();
    }
    @Then("Page Title should be {string}")
    public void page_title_should_be(String expectedtitle) {
        if (driver.getPageSource().contains("Login was unsuccessful."))
        {
            driver.close();
            logger.info("*********** Login failed *************");
            Assert.assertTrue(false);
        }
        else
        {
            logger.info("****** Login Success **********");
            Assert.assertEquals(expectedtitle, driver.getTitle());
        }
   /* String actualTitle = driver.getTitle();
    if (expectedtitle.equals(actualTitle))
    {
        System.out.println("Expected and actual titles are same "+actualTitle+" and expected "+expectedtitle);
    }*/
    }
    @When("User click on log out link")
    public void user_click_on_log_out_link() throws InterruptedException {

        logger.info("****** click on Logout ********");
        loginPage.clickLogout();
        Thread.sleep(4000);
    }

    @Then("close browser")
    public void close_browser() {
        logger.info("****** close the driver ********");
        driver.close();
    }

    // Customer feature  Steps.................................................

    //Scenario : Add new Customer

    @Then("User can view Dashboard")
    public void user_can_view_dashboard() {
        addCustomerPage = new AddCustomerPage(driver);
        Assert.assertEquals("Dashboard / nopCommerce administration",addCustomerPage.getPageTitle());
    }

    @When("User clicks on Customers Menu")
    public void user_clicks_on_customers_menu() throws InterruptedException {
        Thread.sleep(3000);
        addCustomerPage.clickOnCustomers_menu();
    }

    @When("click on Customers Menu Item")
    public void click_on_customers_menu_item() throws InterruptedException {
        Thread.sleep(2000);
        addCustomerPage.clickOnCustomers_menuitem();
    }

    @When("click on Add new Button")
    public void click_on_add_new_button() throws InterruptedException {
        Thread.sleep(2000);
        addCustomerPage.clickOnAddNew();
    }

    @Then("User can view Add new Customer Page")
    public void user_can_view_add_new_customer_page() {
        Assert.assertEquals("Add a new customer / nopCommerce administration",addCustomerPage.getPageTitle());
    }

    @When("User enter customer info")
    public void user_enter_customer_info() throws InterruptedException {

        // set Email
        logger.info("****** Adding New Customer info *********");
        String email = randomString()+"@gmail.com";
        addCustomerPage.setTxtEmail(email);
        addCustomerPage.setTxtPassword("test123");
        Thread.sleep(5000);
        addCustomerPage.setTxtFirstName("Saanvi");
        addCustomerPage.setTxtLastName("ch");
        addCustomerPage.setGender("Female");
        addCustomerPage.setTxtDOB("1/1/2022");
        addCustomerPage.setTxtCompanyName("CGI");
        addCustomerPage.setAdminContent("This is for QA testing ...........");

        addCustomerPage.setCustomerRoles("Administrators");
        Thread.sleep(3000);

      //  addCstomerPage.setDmanagerOfVendor("Vendor 2");



    }

    @When("click on save button")
    public void click_on_save_button() {
        logger.info("*********** Saving New Customer Data ********* ");
        addCustomerPage.clickBtnSave();
    }

    @Then("User can view confirmation message {string}")
    public void user_can_view_confirmation_message(String msg) throws InterruptedException {
        Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("The new customer has been added successfully."));

        Thread.sleep(4000);
    }

    // Scenario: Search Customer by EmailId...................

    @When("Enter customer Email")
    public void enter_customer_email() {

        logger.info("*********** Searching Customer By Email Id *************");
        searchCustomerPage = new SearchCustomerPage(driver);
        searchCustomerPage.setTxtEmail("victoria_victoria@nopCommerce.com");
    }
    @When("Click on search Button")
    public void click_on_search_button() throws InterruptedException {
        searchCustomerPage.clickBtnSearch();
        Thread.sleep(3000);
    }
    @Then("User should found Email in the search table")
    public void user_should_found_email_in_the_search_table() {
        //Assert.assertEquals("an@gmail.com",driver.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr//td[2]")).getText());
    Assert.assertTrue(searchCustomerPage.searchCustomerByEmail("victoria_victoria@nopCommerce.com"));
    }

    // Search based on FirstName and LastName

    @When("Enter customer FirstName")
    public void enter_customer_first_name() {

        logger.info("*********** Searching Customer By Name ************");
        searchCustomerPage = new SearchCustomerPage(driver);
        searchCustomerPage.setFirstName("Victoria");
    }
    @When("Enter customer LastName")
    public void enter_customer_last_name() {
        searchCustomerPage.setLastName("Terces");

    }
    @Then("User should found Name in the search table")
    public void user_should_found_name_in_the_search_table() {
        Assert.assertEquals(true,searchCustomerPage.searchCustomerByName("Victoria","Terces"));

    }

}
