package AssertVerify;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SoftAsserts {
	
	SoftAssert softAssert = new SoftAssert();
	
	@Test
	public void test1() {
		int i = 100;
		int j = 200;
		System.out.println("first assertion: ");
		softAssert.assertEquals(i, j);
		
		System.out.println("second assertion: ");
		softAssert.assertNotEquals(i, j);
		
		System.out.println("third assertion: ");
		softAssert.assertTrue(i > j);
		
		softAssert.assertAll();
	}

}
