package ubu.gii.dass.c01.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.ReusablePool;

public class PoolObjectTest {

	@Test
	public void testAdquirirPool() {
		ReusablePool pool = ReusablePool.getInstance();
		assertNotNull(pool);
	}

	@Test(expected = NotFreeInstanceException.class)
	public void testAcquireReusableException() throws NotFreeInstanceException{
		ReusablePool pool = ReusablePool.getInstance();
		pool.acquireReusable();
		pool.acquireReusable();
		pool.acquireReusable();
	}

}