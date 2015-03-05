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
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.DuplicatedInstanceException;

/**
 * @author �lvaro Ruiz Molledo
 * @author Victor Perez Esteban
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
		assertEquals("El patr�n Singleton est� mal implementado.", pool1, pool2);
	}

	@Test(expected = NotFreeInstanceException.class)
	public void testAcquireReusableException() throws NotFreeInstanceException,DuplicatedInstanceException{
		ReusablePool pool = ReusablePool.getInstance();
		Reusable reusable1 = pool.acquireReusable();
		Reusable reusable2 = pool.acquireReusable();
		//Reusable reusable3 = pool.acquireReusable();
		
		try{
			pool.acquireReusable();
			//pool.acquireReusable();
			//pool.acquireReusable();
		}finally{
			pool.releaseReusable(reusable1);
			pool.releaseReusable(reusable2);
			//pool.releaseReusable(reusable3);
		}
	}
	
	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() throws NotFreeInstanceException,DuplicatedInstanceException {
		ReusablePool pool = ReusablePool.getInstance();
		
		Reusable reusable1 = pool.acquireReusable();
		
		assertNotNull(reusable1);
		
		pool.releaseReusable(reusable1);
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() throws NotFreeInstanceException,DuplicatedInstanceException{
		ReusablePool pool = ReusablePool.getInstance();
		
		Reusable reusable1 = pool.acquireReusable();
		Reusable reusable2 = pool.acquireReusable();
		
		pool.releaseReusable(reusable2);
		
		Reusable reusable3 = pool.acquireReusable();
		
		assertEquals(reusable2, reusable3);
		
		pool.releaseReusable(reusable1);
		pool.releaseReusable(reusable3);
	}
	
	@Test (expected = DuplicatedInstanceException.class)
	public void testReleaseReusableDuplicatedException() throws NotFreeInstanceException,DuplicatedInstanceException{
		ReusablePool pool = ReusablePool.getInstance();
		Reusable reusable1;
		
		reusable1 = pool.acquireReusable();
		pool.releaseReusable(reusable1);
		pool.releaseReusable(reusable1);
		
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.Reusable#util()}.
	 */
	@Test
	public void testReusableUtil() throws NotFreeInstanceException,DuplicatedInstanceException {
		ReusablePool pool = ReusablePool.getInstance();
		
		Reusable reusable1 = pool.acquireReusable();
		
		assertEquals(reusable1.util(), reusable1.hashCode() + "  :Uso del objeto Reutilizable" );
		
		pool.releaseReusable(reusable1);
	}
}
