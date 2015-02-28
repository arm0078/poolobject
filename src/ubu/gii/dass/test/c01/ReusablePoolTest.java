/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.ReusablePool;
import ubu.gii.dass.c01.NotFreeInstanceException;

/**
 * @author Álvaro Ruiz Molledo
 *
 */
public class ReusablePoolTest {

	ReusablePool pool = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool pool1 = ReusablePool.getInstance();
		assertNotNull("El objeto pool es null.", pool1);
		
		ReusablePool pool2 = ReusablePool.getInstance();
		assertEquals("El patrón Singleton está mal implementado.", pool1, pool2);
	}

	@Test(expected = NotFreeInstanceException.class)
	public void testAcquireReusableException() throws NotFreeInstanceException{
		ReusablePool pool = ReusablePool.getInstance();
		pool.acquireReusable();
		pool.acquireReusable();
		pool.acquireReusable();
	}
	
	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() throws NotFreeInstanceException{
		fail("Not yet implemented");
	}

}
