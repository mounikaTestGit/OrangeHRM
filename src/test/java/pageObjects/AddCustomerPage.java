package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.WaitHelper;

public class AddCustomerPage {

    public WebDriver ldriver;
    public WaitHelper waitHelper;

    public AddCustomerPage(WebDriver rdriver)
    {
        ldriver = rdriver;
        PageFactory.initElements(ldriver, this);
         waitHelper = new WaitHelper(ldriver);
    }


 //   By btnLogin = By.xpath("//button[@type='submit']");

   // By lnkList = By.xpath("//a[@id='nopSideBarPusher']");

    By lnkCustomers_menu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
    By lnkCustomers_menuitem = By.xpath("//a[@href='/Admin/Customer/List']//p");
    By btnAddNew = By.xpath("//a[@class='btn btn-primary']");

    By txtEmail = By.xpath("//input[@id='Email']");
    By txtPassword = By.xpath("//input[@id='Password']");

    By txtFirstName = By.xpath("//input[@id='FirstName']");
    By txtLastName = By.xpath("//input[@id='LastName']");

    By rdMaleGender = By.id("Gender_Male");
    By rdFemaleGender = By.id("Gender_Female");

    By txtDOB = By.xpath("//input[@id='DateOfBirth']");
    By txtCompanyName = By.xpath("//input[@id='Company']");


    By txtCustomerRoles = By.xpath("//div[@class='input-group-append input-group-required']//div[@role='listbox']");

    By lstitemAdministrators = By.xpath("//li[contains(text(),'Administrators')]");
    By lstitemModerators = By.xpath("//li[contains(text(),'Forum Moderators')]");
    By lstitemGuests = By.xpath("Guests");
    By lstitemRegistered = By.xpath("//li[contains(text(),'Registered')]");
    By lstitemVendors = By.xpath("//li[contains(text(),'Vendors')]");

    By drpmgrOfVendor = By.xpath("//select[@id='VendorId']");

    By txtAreaAdminContent = By.xpath("//textarea[@id='AdminComment']");

    By btnSave = By.xpath("//button[@name='save']");

    //Action Mthods

    public String getPageTitle()
    {
       String title = ldriver.getTitle();
       return title;
    }

    public void clickOnCustomers_menu()
    {
        ldriver.findElement(lnkCustomers_menu).click();
    }

    public void clickOnCustomers_menuitem()
    {
        ldriver.findElement(lnkCustomers_menuitem).click();
    }

    public void clickOnAddNew()
    {
        ldriver.findElement(btnAddNew).click();
    }

    public void setTxtEmail(String email) {

        waitHelper.waitForElement(ldriver.findElement(txtEmail),10);
        ldriver.findElement(By.xpath("//button[@class='btn btn-tool']")).click();
        ldriver.findElement(txtEmail).sendKeys(email);
    }

    public void setTxtPassword(String pwd)
    {
        waitHelper.waitForElement( ldriver.findElement(txtPassword),30);
        ldriver.findElement(txtPassword).sendKeys(pwd);
    }

    public void setTxtFirstName(String F_Name)
    {
        waitHelper.waitForElement(ldriver.findElement(txtFirstName),40);
        ldriver.findElement(txtFirstName).sendKeys(F_Name);
    }

    public void setTxtLastName(String L_Name)
    {
        waitHelper.waitForElement(ldriver.findElement(txtLastName),30);
        ldriver.findElement(txtLastName).sendKeys(L_Name);
    }

    public void setGender(String gender)
    {
        if (gender.equalsIgnoreCase("Male"))
            ldriver.findElement(rdMaleGender).click();
        else if (gender.equalsIgnoreCase("Female"))
            ldriver.findElement(rdFemaleGender).click();
        else
        ldriver.findElement(rdMaleGender).click();
    }

    public void setTxtDOB(String dob)
    {
        waitHelper.waitForElement(ldriver.findElement(txtDOB),30);
        ldriver.findElement(txtDOB).sendKeys(dob);
    }

    public void setTxtCompanyName(String cmp)
    {
        ldriver.findElement(txtCompanyName).sendKeys(cmp);
    }

    public void setCustomerRoles(String role) throws InterruptedException {
        waitHelper.waitForElement(ldriver.findElement(txtCustomerRoles),30);
       ldriver.findElement(txtCustomerRoles).click();

        WebElement listItem;

        if (role.equalsIgnoreCase("Administrators"))
        {
            listItem = ldriver.findElement(lstitemAdministrators);
        }
        else if(role.equalsIgnoreCase("Forum Moderators"))
        {
            listItem = ldriver.findElement(lstitemModerators);
        }
        else if (role.equalsIgnoreCase("Guests"))
            listItem = ldriver.findElement(lstitemGuests);
        else if (role.equalsIgnoreCase("Registered"))
        {
            listItem = ldriver.findElement(lstitemRegistered);
        }
        else
            listItem = ldriver.findElement(lstitemVendors);

       // listItem.click();
        Thread.sleep(4000);

        JavascriptExecutor js = (JavascriptExecutor)ldriver;
        js.executeScript("arguments[0].click();",listItem);
    }

  /*  public void setDmanagerOfVendor(String value)
    {
        Select drp = new Select(ldriver.findElement(drpmgrOfVendor));
        drp.selectByVisibleText(value);
    }*/

    public void setAdminContent(String data)
    {
        ldriver.findElement(txtAreaAdminContent).sendKeys(data);
    }

    public void clickBtnSave()
    {
        ldriver.findElement(btnSave).click();
    }
}
