package sysappTest;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import testUtils.baseutilspkg;

public class TestLeadFlow {

    WebDriver driver;
    String leadRefId;

    @BeforeClass
    public void setup() {
        baseutilspkg obj = new baseutilspkg();
        driver = obj.startUp("ch");
    }

    @Test(priority = 1)
    public void addLeadTest() throws InterruptedException {
        Addlead addLead = new Addlead();
        leadRefId = addLead.addLead(driver);
    }

    @Test(priority = 2, dependsOnMethods = "addLeadTest")
    public void searchLeadTest() {
        Leadsearch leadSearch = new Leadsearch();
        leadSearch.search(driver,leadRefId);
    }

    @Test(priority = 3, dependsOnMethods = "searchLeadTest")
    public void editLeadTest() {
        LeadEdit editLead = new LeadEdit();
        editLead.editLeadmthd(driver,leadRefId);
    }
        
        
        @Test(priority = 4, dependsOnMethods = "editLeadTest")
        public void advehicle() {	
        	AddVehicle vehicle=new AddVehicle();
        	vehicle.addleadvehicle(driver);
        }
        
        @Test(priority = 5, dependsOnMethods = "advehicle")
        public void cretquot() throws InterruptedException {	
        	CreateQuote vehicle=new CreateQuote();
        	vehicle.quotecreate(driver);
    }
}

