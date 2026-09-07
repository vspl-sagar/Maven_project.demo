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
    public void editLeadTest() throws InterruptedException {
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
       @Test(priority = 6)
       public void chstatus() throws InterruptedException {
    	   ChangeStatus chst = new ChangeStatus();
    	   
    	   chst.statusschange(driver);
       }
     	@Test(priority=7)
      public void reports() throws InterruptedException	{
    	salespiplineRepoirt repo = new salespiplineRepoirt();
    	repo.salesrepo(driver,leadRefId);
    	
    }
     @Test(priority=8)
    public void mypipreport() throws InterruptedException {
    	MypipelineReport mreport = new MypipelineReport();
    	mreport.mypipeline(driver, leadRefId);
    }
     @Test(priority=9)
     public void paymereport() throws InterruptedException {
    	 PaymentStatus statupay = new PaymentStatus();
    	 statupay.paymentst(driver, leadRefId);
     }
     @Test(priority=10) 
    public void taskmy() {
    	MyTask mytaskcr = new MyTask();
    	mytaskcr.mytaskmet(driver,leadRefId);
    }
    @Test(priority=11)
    public void importfunct() {
    	ImportCSV imprt=new ImportCSV();
    	imprt.impcsv(driver,leadRefId);
    	
    	
    }
    
    @Test(priority=12)
    public void reasslead() {
    	LeadReassign leadre=new LeadReassign();
    	leadre.leadreassmeth(driver,leadRefId);
    }
    	
}



