package script;

import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.DemoPage;

public class test2 extends BaseTest {
  @Test
  public void test() {
	  String un = Excel.getCellData(XL_PATH, "test1", 1, 0);
	  String pwd = Excel.getCellData(XL_PATH, "test1", 1, 1);
	  Reporter.log(un+" "+pwd,true);
	  Reporter.log("test2....",true);
	  
	  DemoPage dp = new DemoPage(driver);
	  dp.setUserName(un);
	  dp.setPassword(pwd); 
  }
}
