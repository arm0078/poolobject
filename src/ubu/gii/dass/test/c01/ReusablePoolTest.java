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
import ubu.gii.dass.c01.Client;

/**
 * @author �lvaro Ruiz Molledo
 * @author Victor Perez Esteban
 *
 */
public class ReusablePoolTest {

	private ReusablePool pool = null;
	private Reusable reusable1 = null;
	private Reusable reusable2 = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		/*
		pool = ReusablePool.getInstance();
		
		reusable1 = pool.acquireReusable();
		reusable2 = pool.acquireReusable();
		
		pool.releaseReusable(reusable1);
		pool.releaseReusable(reusable2);
		*/
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Comprueba que al pedir ina instancia del pool
	 * devuelve un objeto no nulo, y que al volver a 
	 * pedir otra instancia devuelve el mismo objeto
	 * 
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		ReusablePool pool1 = ReusablePool.getInstance();
		assertNotNull("El objeto pool es null.", pool1);
		
		ReusablePool pool2 = ReusablePool.getInstance();
		assertEquals("El patrogleton esta mal implementado.", pool1, pool2);
	}
	
	/**
	 * Prueba que cuando se supera el máximo número de
	 * peticiones de reusables al pool, lanza la excepción
	 * NotFreeInstanceException
	 * 
	 * @throws NotFreeInstanceException
	 * @throws DuplicatedInstanceException
	 */
	@Test(expected = NotFreeInstanceException.class)
	public void testAcquireReusableException() throws NotFreeInstanceException,DuplicatedInstanceException{
		pool = ReusablePool.getInstance();
		
		reusable1 = pool.acquireReusable();
		reusable2 = pool.acquireReusable();
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
	 * Prueba que al pedir un resusable cuando hay resusables
	 * disponibles, el pool devuelve un objeto no nulo
	 * 
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 */
	@Test
	public void testAcquireReusable() throws NotFreeInstanceException,DuplicatedInstanceException {
		pool = ReusablePool.getInstance();
		
		reusable1 = pool.acquireReusable();
		
		assertNotNull(reusable1);
		
		pool.releaseReusable(reusable1);
	}

	/**
	 * Comprueba que la liberar un reusable al pool y volver
	 * a pedir otro reusable, devuelve el mismo objeto
	 * 
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 */
	@Test
	public void testReleaseReusable() throws NotFreeInstanceException,DuplicatedInstanceException{
		pool = ReusablePool.getInstance();
		
		reusable1 = pool.acquireReusable();
		reusable2 = pool.acquireReusable();
		
		pool.releaseReusable(reusable2);
		
		Reusable reusable3 = pool.acquireReusable();
		
		assertEquals(reusable2, reusable3);
		
		pool.releaseReusable(reusable1);
		pool.releaseReusable(reusable3);
	}
	
	/**
	 * Prueba que cuando se intenta liberar al pool
	 * dos veces el mismo objeto, lanza la excepción
	 * DuplicatedInstanceException
	 * 
	 * @throws NotFreeInstanceException
	 * @throws DuplicatedInstanceException
	 */
	@Test (expected = DuplicatedInstanceException.class)
	public void testReleaseReusableDuplicatedException() throws NotFreeInstanceException,DuplicatedInstanceException{
		pool = ReusablePool.getInstance();
		
		reusable1 = pool.acquireReusable();
		pool.releaseReusable(reusable1);
		pool.releaseReusable(reusable1);
		
	}
	
	/**
	 * Prueba que no se pueda añadir un reusable nulo
	 * al pool
	 * 
	 * @throws NotFreeInstanceException
	 * @throws DuplicatedInstanceException
	 */
	@Test 
	public void testReleaseReusableNoNulo() throws NotFreeInstanceException,DuplicatedInstanceException{
		pool = ReusablePool.getInstance();
		Reusable reusable_test1 = null;
		//Reusable reusable_test2 = new Reusable();
		
		pool.releaseReusable(reusable_test1);
		//pool.releaseReusable(reusable_test2);
		
		assertNotNull (pool.acquireReusable());
		assertNotNull (pool.acquireReusable());
	}

	/**
	 * Prueba que al usar la función de hash de un reusable
	 * devuelve el código hash del objeto más el mensaje
	 * "  :Uso del objeto Reutilizable"
	 * 
	 * Test method for {@link ubu.gii.dass.c01.Reusable#util()}.
	 */
	@Test
	public void testReusableUtil() throws NotFreeInstanceException,DuplicatedInstanceException {
		ReusablePool pool = ReusablePool.getInstance();
		
		Reusable reusable1 = pool.acquireReusable();
		
		assertEquals(reusable1.util(), reusable1.hashCode() + "  :Uso del objeto Reutilizable" );
		
		pool.releaseReusable(reusable1);
	}
	
	/**
	 * Test method for {@link ubu.gii.dass.c01.Client#main()}.
	 */
	/*
	@Test
	public void testClient() throws NotFreeInstanceException,DuplicatedInstanceException {
		ReusablePool pool = ReusablePool.getInstance();
		
		reusable1 = pool.acquireReusable();
		reusable2 = pool.acquireReusable();
		pool.releaseReusable(reusable1);
		pool.releaseReusable(reusable2);
		
		Client cliente = new Client();
		
		cliente.main(new String[2]);
		
		pool.releaseReusable(reusable1);
		pool.releaseReusable(reusable2);
	
	}
	*/
}
