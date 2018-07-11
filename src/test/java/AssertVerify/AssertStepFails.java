package AssertVerify;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AssertStepFails {

	@Test
	public void test1() {
		int n = 10;
		int k = 5;
		Assert.assertEquals(n, k); //
		System.out.println("next step");
		Assert.assertTrue(n > k);
	}

	@Test
	public void test2() {
		int n = 10;
		int k = 5;
		Assert.assertTrue(n > k);
	}
}
