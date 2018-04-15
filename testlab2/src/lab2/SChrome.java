package lab2;


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.util.Iterator; 

import org.apache.poi.xssf.usermodel.XSSFCell;  
import org.apache.poi.xssf.usermodel.XSSFRow;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class SChrome {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty("webdriver.chrome.driver", "C:/Users/Administrator/AppData/Local/Google/Chrome/Application/chromedriver.exe");
	driver = new ChromeDriver();
    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testUntitledTestCase1() throws Exception {
	  InputStream ExcelFileToRead = new FileInputStream("H:/input.xlsx");  
	  XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);  
	  String username,password,adress;
	  for(int sheet=0;sheet<wb.getNumberOfSheets();sheet++){
		  XSSFSheet xs=wb.getSheetAt(sheet);
		  if(xs!=null){
			  for(int row=1;row<=xs.getLastRowNum();row++){
			          XSSFRow xf = xs.getRow(row);
					  xf.getCell(0).setCellType(XSSFCell.CELL_TYPE_STRING);
				    	username = xf.getCell(0).getStringCellValue();
				    	password = username.substring(4);
				    	adress = xf.getCell(1).toString();
				    	if(adress.equals(""))continue;
				    	 driver.get("https://psych.liebes.top/st");
				    	    driver.findElement(By.id("username")).click();
				    	    driver.findElement(By.id("username")).clear();
				    	    driver.findElement(By.id("username")).sendKeys(username);
				    	    driver.findElement(By.id("password")).click();
				    	    driver.findElement(By.id("password")).clear();
				    	    driver.findElement(By.id("password")).sendKeys(password);
				    	    driver.findElement(By.id("submitButton")).click();
				    	    String adress2 = driver.findElement(By.xpath("//p")).getText();
				    	    assertTrue(equalsadress(adress, adress2));
			  }
					  
				  }
			  }
		  
  }
  public boolean equalsadress(String adress1,String adress2){
	  if(adress1.equals(adress2)||(adress1+"/").equals(adress2)){
		  return true;
	  }
  return false;
}
  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

