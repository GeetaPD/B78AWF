package script;

import org.testng.Reporter;
import org.testng.annotations.Test;

import generic.BaseTest;
import generic.Excel;
import page.DemoPage;

public class test1 extends BaseTest{

	@Test
	public void test()
	{
		String un=Excel.getCellData(XL_PATH,"test1",1,0);
		Reporter.log(un,true);
		Reporter.log("test1...",true);
		DemoPage d=new DemoPage(driver);
		d.setUserName(un);
	}
}
