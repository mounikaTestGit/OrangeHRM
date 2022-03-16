package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.WaitHelper;

import java.util.List;

public class SearchCustomerPage {

    public WebDriver ldriver;
    WaitHelper waitHelper;

    public SearchCustomerPage(WebDriver rdriver)
    {
        ldriver = rdriver;
        PageFactory.initElements(ldriver,this);
         waitHelper = new WaitHelper(ldriver);
    }

    @FindBy(how = How.ID, using = "SearchEmail")
    @CacheLookup
    WebElement txtEmail;

    @FindBy(how = How.ID, using = "SearchFirstName")
    @CacheLookup
    WebElement txtFirstName;

    @FindBy(how = How.ID, using = "SearchLastName")
    @CacheLookup
    WebElement txtLastName;

    @FindBy(how = How.ID, using = "SearchMonthOfBirth")
    @CacheLookup
    WebElement drpDOBMonth;

    @FindBy(how = How.ID, using = "SearchDayOfBirth")
    @CacheLookup
    WebElement drpDOBDay;

    @FindBy(how = How.ID, using = "SearchCompany")
    @CacheLookup
    WebElement txtCompanyName;

    @FindBy(how = How.ID, using = "SearchIpAddress")
    @CacheLookup
    WebElement txtIpAddress;

    @FindBy(how =How.XPATH,using = "//input[@role='listbox']")
    @CacheLookup
    WebElement txtCustomerRoles;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Administrators')]")
    @CacheLookup
    WebElement lstitemAdmintrators;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Forum Moderators')]")
    @CacheLookup
    WebElement lstitemForumModerators;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Guests')]")
    @CacheLookup
    WebElement lstitemGuests;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Registered')]")
    @CacheLookup
    WebElement lstitemRegistered;

    @FindBy(how = How.XPATH, using = "//li[contains(text(),'Vendors')]")
    @CacheLookup
    WebElement lstitemVendors;

    @FindBy(how =How.XPATH, using = "//button[@id='search-customers']")
    @CacheLookup
    WebElement btnSearch;

    @FindBy(how = How.XPATH , using = "(//table[@role='grid'])[1]")
    @CacheLookup
    WebElement tblSearchResults;

    @FindBy(how = How.XPATH , using = "//table[@id='customers-grid']")
    @CacheLookup
    WebElement table;

    @FindBy(how =How.XPATH , using = "//table[@id='customers-grid']//tbody//tr")
    @CacheLookup
    List<WebElement> tableRows;

    @FindBy(how = How.XPATH, using = "//table[@id='customers-grid']//tbody//tr//td")
    @CacheLookup
    List<WebElement> tableCols;

    public void setTxtEmail(String email) {
        waitHelper.waitForElement(txtEmail,10);
        txtEmail.clear();
        txtEmail.sendKeys(email);
    }
    public void setFirstName(String F_Name) {
        waitHelper.waitForElement(txtFirstName,10);
        txtFirstName.clear();
        txtFirstName.sendKeys(F_Name);
    }
    public void setLastName(String L_Name) {
        waitHelper.waitForElement(txtLastName,10);
        txtLastName.clear();
        txtLastName.sendKeys(L_Name);
    }
    public void clickBtnSearch()
    {
        waitHelper.waitForElement(btnSearch,30);
        btnSearch.click();
        waitHelper.waitForElement(btnSearch,30);
    }

    public int getNoOfRows()
    {
        return (tableRows.size());
    }

    public int getNoOfCols(){
        return (tableCols.size());
    }

    public boolean searchCustomerByEmail(String email)
    {
        boolean flag = false;
        for (int i = 1 ; i<=getNoOfRows() ; i++)
        {
            String EId = table.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr["+i+"]//td[2]")).getText();
            if (email.equals(EId)) {
                System.out.println("###### Result #####: ---> \n" + EId);
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean searchCustomerByName(String F_Name , String L_Name)
    {
        boolean flag = false;
        String A_Name = F_Name+" "+L_Name;
        for (int i = 1 ; i<=getNoOfRows() ; i++)
        {
            String E_Name = table.findElement(By.xpath("//table[@id='customers-grid']//tbody//tr["+i+"]//td[3]")).getText();
            if (A_Name.equalsIgnoreCase(E_Name)) {
                System.out.println(E_Name);
                flag = true;
                break;
            }
        }
        return flag;
    }

}
