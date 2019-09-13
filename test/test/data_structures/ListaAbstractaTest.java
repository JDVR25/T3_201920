package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.IEstructura;

/**
 * Clase de prueba con los m�todos necesarios para probar cualquier tipo de lista.
 * Esta clase de pruebas no tiene en cuenta las particularidades de cada tipo de lista.
 *
 */
public abstract class ListaAbstractaTest 
{
	/**
	 * Lista sobre la que se realizar�n las pruebas.
	 */
	protected IEstructura<Integer> lista;
	
	/**
	 * Arreglo con los elementos del escenario 2 (sirve para realizar pruebas exhaustivas).
	 */
	protected static final int[] ARREGLO_ESCENARIO_2 = {350, 383, 105, 233, 140, 266, 356, 236, 80, 360, 221, 241, 130, 244, 352, 446, 18, 98, 97, 396};
	
	/**
	 * Crea el escenario 1. Un escenario vac�o.
	 */
	@Before
	public abstract void setupEscenario1();
	
	/**
	 * Crea el escenario 2 (sin n�meros repetidos) agregando los siguientes n�meros (en este orden):
	 * 
	 * 350, 383, 105, 233, 140, 266, 356, 236, 80, 360, 221, 241, 130, 244, 352, 446, 18, 98, 97, 396
	 */
	public void setupEscenario2()
	{
		for(int actual: ARREGLO_ESCENARIO_2)
		{
			lista.addLast(actual);
		}
	}
	
	/**
	 * Prueba que revisa el m�todo size.
	 */
	@Test
	public void testSize()
	{
		//Prueba la lista vac�a.
		assertEquals("El tama�o de la lista vac�a no es correcto", 0, lista.size());
		
		//Prueba la lista con dos elementos
		
		lista.addLast(5);
		lista.addLast(30);
		
		assertEquals("El tama�o de la lista con dos elementos no es correcto", 2, lista.size());
		
		//Prueba vaciando la lista
		lista.clear();
		assertEquals("El tama�o de la lista vac�a no es correcto", 0, lista.size());
		
		//Prueba la lista con 20 elementos
		setupEscenario2();
		
		assertEquals("El tama�o de la lista con 20 elementos no es correcto", ARREGLO_ESCENARIO_2.length, lista.size());
		
		//Agrega dos elementos m�s y prueba
		
		lista.addLast(5);
		lista.addLast(30);
		
		assertEquals("El tama�o de la lista con 22 elementos no es correcto", ARREGLO_ESCENARIO_2.length + 2, lista.size());
	}
	
	/**
	 * Prueba que revisa el m�todo isEmpty
	 */
	@Test
	public void testIsEmpty()
	{
		//Prueba la lista vac�a.
		assertTrue("Al inicio la lista deber�a estar vac�a", lista.isEmpty());
		
		//Prueba la lista con dos elementos.
		lista.addLast(5);
		lista.addLast(30);
		
		assertFalse("Al agregar elementos la lista no deber�a estar vac�a", lista.isEmpty());
		
		//Prueba la lista despu�s de vaciarla.
		lista.clear();
		
		assertTrue("Al remover todos los elementos de la lista deber�a estar vac�a", lista.isEmpty());
		
		//Prueba la lista con 20 elementos.
		setupEscenario2();
		
		assertFalse("Al agregar 20 elementos, la lista no deber�a estar vac�a", lista.isEmpty());
	}
	
	/**
	 * Prueba que revisa el m�todo contains
	 */
	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void testContains()
	{
		//Prueba con la lista vac�a.
		assertFalse("Al inicio no deber�a encontrar ning�n elemento", lista.contains(80));
		assertFalse("Al inicio no deber�a encontrar ning�n elemento", lista.contains(new Double(80)));
		
		//Prueba con dos elementos
		lista.addLast(5);
		lista.addLast(30);
		
		assertTrue("Deber�a contener el elemento", lista.contains(5));
		assertTrue("Deber�a contener el elemento", lista.contains(30));
		
		//Vac�a la lista y prueba.
		lista.clear();
		
		assertFalse("No deber�a encontrar ning�n elemento", lista.contains(30));
		assertFalse("No deber�a encontrar ning�n elemento", lista.contains(new Double(5)));
		
		// Prueba con 20 elementos.
		setupEscenario2();
		
		for(int actual: ARREGLO_ESCENARIO_2)
		{
			assertTrue("El elemento "+ actual +" deber�a encontrarse en la lista", lista.contains(actual));
		}
		
	}
	
	/**
	 * Prueba que verifica el iterador que recibe.
	 */
	@Test
	public void testIterator()
	{
		//Prueba el iterador de la lista vac�a.
		Iterator<Integer> iterador = lista.iterator();
		assertNotNull("Con la lista vac�a retorna un iterador vac�o", iterador);
		assertFalse("Con la lista vac�a dice que puede avanzar al siguiente elemento", iterador.hasNext());
		try
		{
			iterador.next();
			fail("Si no tiene elementos, no deber�a avanzar.");
		}
		catch(NoSuchElementException e)
		{
			//No avanz� porque no hay elementos.
		}
		
		//Prueba el iterador con dos elementos.
		lista.addLast(5);
		lista.addLast(30);
		
		iterador = lista.iterator();
		assertNotNull("No deber�a retornar un iterador vac�o", iterador);
		assertTrue("Con elementos el la lista dice que no puede avanzar al siguiente elemento", iterador.hasNext());
		try
		{
			Integer siguiente = iterador.next();
			assertTrue("Estando en el primer elemento de la lista, dice que no puede avanzar al segundo", iterador.hasNext());
			assertNotNull(siguiente);
			siguiente = iterador.next();
		}
		catch(NoSuchElementException e)
		{
			fail("No avanz� el iterador, dice que no hay m�s elementos.");
		}
		
		assertFalse("Estando en el �ltimo elemento de la lista dice que no puede avanzar.", iterador.hasNext());
		
		try
		{
			iterador.next();
			fail("Si est� en el �ltimo elemento, no deber�a avanzar.");
		}
		catch(NoSuchElementException e)
		{
			//No avanz� proque no hay m�s elementos.
		}
		
		//Prueba el iterador con 20 elementos
		
		lista.clear();
		
		setupEscenario2();
		
		iterador = lista.iterator();
		assertNotNull("No deber�a retornar un iterador vac�o", iterador);
		assertTrue("Con elementos el la lista dice que no puede avanzar al siguiente elemento", iterador.hasNext());
		
		int indiceActual = 0;
		while(iterador.hasNext())
		{
			try
			{
				Integer actual = iterador.next();
				assertNotNull(actual);
				indiceActual++;
			}
			catch(NoSuchElementException e)
			{
				fail("Indica que puede avanzar, pero lanza excepci�n al avanzar.");
			}
		}
		
		assertEquals("El iterador no recorri� toda la lista", indiceActual, lista.size());
		
	}
	
	/**
	 * M�todo que prueba el primer toarray
	 */
	@Test
	public void testToArray()
	{
		//Prueba con la lista vac�a.
		Object[] arreglo = lista.toArray();
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El arreglo con la lista vac�a no est� vac�o", 0, arreglo.length);
		
		//Prueba la lista con dos elementos.
		lista.addLast(5);
		lista.addLast(30);
		arreglo = lista.toArray();
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El arreglo con la lista vac�a no est� vac�o", 2, arreglo.length);
		
		//Prueba la lista con 20 elementos.
		lista.clear();
		setupEscenario2();
		arreglo = lista.toArray();
		assertNotNull("El arreglo no puede ser null", arreglo);
		assertEquals("El tama�o del arreglo no es el esperado", ARREGLO_ESCENARIO_2.length, arreglo.length);	
	}
	
	/**
	 * Prueba que revisa que se agreguen los elementos sin tener en cuenta repetidos, ni el orden.
	 */
	@Test
	public void testAdd()
	{
		//Prueba la lista vac�a.
		assertTrue("Al principio la lista est� vac�a", lista.isEmpty());
		assertEquals("El tama�o de la lista al principio no es 0", 0, lista.size());

		//Agrega dos elementos.
		assertTrue("No agrega el elemento", lista.addLast(5));
		assertTrue("No agrega el elemento", lista.addLast(30));
		assertFalse("La lista no deber�a estar vac�a", lista.isEmpty());
		assertEquals("La lista deber�a tener 2 elementos", 2, lista.size());

		assertTrue("La lista no contiene 5", lista.contains(5));
		assertTrue("La lista no contiene 30", lista.contains(30));
		
		//Agrega 20 elementos.
		lista.clear();
		
		setupEscenario2();
		assertFalse("La lista no deber�a estar vac�a", lista.isEmpty());
		assertEquals("La lista deber�a tener 20 elementos", ARREGLO_ESCENARIO_2.length, lista.size());
	}
	
	/**
	 * Prueba el m�todo remove que recibe como par�metro un objeto.
	 */
	@Test
	public void testRemoveObject()
	{
		//Prueba con una lista vac�a.
		assertFalse("No deber�a eliminar elementos porque la lista est� vac�a", lista.remove(new Integer(40)));
		
		//Prueba con una lista de 3 elementos.
		lista.addLast(3);
		lista.addLast(40);
		lista.addLast(30);
		assertEquals("El tama�o del arreglo no es el esperado", 3, lista.size());
		
		assertTrue("Deber�a poder eliminar el elemento de la lista", lista.remove(new Integer(3)));
		assertFalse("No se elimin� el elemento", lista.contains(new Integer(3)));
		assertEquals("El tama�o de la lista no es el esperado", 2, lista.size());
		
		assertFalse("No deber�a poder eliminar un elemento que no est� en la lista", lista.remove(new Integer(323)));
		
		assertTrue("Deber�a poder eliminar el elemento de la lista", lista.remove(new Integer(30)));
		assertFalse("No se elimin� el elemento", lista.contains(new Integer(30)));
		assertEquals("El tama�o de la lista no es el esperado", 1, lista.size());
		
		assertTrue("Deber�a poder eliminar el elemento de la lista", lista.remove(new Integer(40)));
		assertFalse("No se elimin� el elemento", lista.contains(new Integer(3)));
		assertEquals("El tama�o de la lista no es el esperado", 0, lista.size());
		
		//Prueba con una lista de 20 elementos.
		setupEscenario2();

		
		assertFalse("No deber�a poder eliminar un elemento que no est� en la lista", lista.remove(new Integer(40)));
		
		assertTrue("Deber�a poder eliminar el elemento de la lista", lista.remove(new Integer(350)));
		assertFalse("No se elimin� el elemento", lista.contains(new Integer(350)));
		assertEquals("El tama�o de la lista no es el esperado", 19, lista.size());
		
		assertTrue("Deber�a poder eliminar el elemento de la lista", lista.remove(new Integer(396)));
		assertFalse("No se elimin� el elemento", lista.contains(new Integer(396)));
		assertEquals("El tama�o de la lista no es el esperado", 18, lista.size());
		
		assertTrue("Deber�a poder eliminar el elemento de la lista", lista.remove(new Integer(18)));
		assertFalse("No se elimin� el elemento", lista.contains(new Integer(18)));
		assertEquals("El tama�o de la lista no es el esperado", 17, lista.size());
		
		assertTrue("Deber�a poder eliminar el elemento de la lista", lista.remove(new Integer(446)));
		assertFalse("No se elimin� el elemento", lista.contains(new Integer(446)));
		assertEquals("El tama�o de la lista no es el esperado", 16, lista.size());
		
		assertFalse("La lista no deber�a tener elementos repetidos", hayRepetidos());
	}
	
	/**
	 * Prueba que revisa el m�todo clear.
	 */
	@Test
	public void testClear()
	{
		//Prueba la lista vac�a.
		lista.clear();
		assertEquals("La lista sigue vac�a", 0, lista.size());
		
		//Prueba con 20 elementos
		setupEscenario2();
		assertEquals("La lista no tiene 20 elementos, hay que revisar el m�todo add", 20, lista.size());
		lista.clear();
		assertEquals("La lista no qued� vac�a", 0, lista.size());
		try
		{
			lista.get(0);
			fail("No deber�a poder recuperar el primer elemento de la lista");
		}
		catch (IndexOutOfBoundsException e) 
		{
			//Deber�a lanzar la excepci�n.
		}
	}
	
	/**
	 * Prueba que revisa el m�todo get.
	 */
	@Test
	public void testGet()
	{
		//Revisa la lista vac�a.
		try
		{
			lista.get(0);
			fail("Deber�a lanzar excepci�n porque la lista est� vac�a.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deber�a lanzar la excepci�n
		}
		
		try
		{
			lista.get(-1);
			fail("Deber�a lanzar excepci�n porque el �ndice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deber�a lanzar la excepci�n
		}
		
		try
		{
			lista.get(-50);
			fail("Deber�a lanzar excepci�n porque el �ndice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deber�a lanzar la excepci�n
		}
		
		//Revisa la lista con 20 elementos.
		setupEscenario2();
		
		try
		{
			lista.get(-1);
			fail("Deber�a lanzar excepci�n porque el �ndice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deber�a lanzar la excepci�n
		}
		
		try
		{
			lista.get(-50);
			fail("Deber�a lanzar excepci�n porque el �ndice no existe");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deber�a lanzarla
		}
		
		try
		{
			lista.get(500);
			fail("Deber�a lanzar excepci�n porque el �ndice est� por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deber�a lanzarla
		}
		
		try
		{
			lista.get(lista.size());
			fail("Deber�a lanzar excepci�n porque el �ndice est� por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			//Deber�a lanzarla
		}
		
		for(int i = 0; i < ARREGLO_ESCENARIO_2.length; i++)
		{
			try
			{
				Integer elemento = lista.get(i);
				assertNotNull("Los elementos recuperados no pueden ser nulos", elemento);
			}
			catch(IndexOutOfBoundsException e)
			{
				fail("El elemento se encuentra dentro del rango, no deber�a lanzar excepci�n");
			}
		}
	}
	
	/**
	 * Prueba del m�todo remove
	 */
	@Test
	public void testRemoveIndex()
	{
		//Prueba con la lista vac�a.
		try
		{
			lista.remove(0);
			fail("No deber�a dejar eliminar elementos porque la lista est� vac�a");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		try
		{
			lista.remove(-1);
			fail("No deber�a eliminar porque el �ndice es negativo");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		//Prueba con 20 elementos
		setupEscenario2();
		try
		{
			lista.remove(-1);
			fail("No deber�a dejar eliminar porque el �ndice es negativo");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		try
		{
			lista.remove(lista.size());
			fail("No deber�a dejar eliminar porque el �ndice est� por fuera de la lista");
		}
		catch(IndexOutOfBoundsException e)
		{
			
		}
		
		
		try
		{
			Integer paraEliminar = lista.get(0);
			assertEquals("No elimin� el elemento esperado", paraEliminar, lista.remove(0));
			assertEquals("La lista no tiene el tama�o esperado", 19, lista.size());
			assertFalse("No elimin� el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(lista.size() - 1);
			assertEquals("No elimin� el elemento esperado", paraEliminar, lista.remove(lista.size() - 1));
			assertEquals("La lista no tiene el tama�o esperado", 18, lista.size());
			assertFalse("No elimin� el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(3);
			assertEquals("No elimin� el elemento esperado", paraEliminar, lista.remove(3));
			assertEquals("La lista no tiene el tama�o esperado", 17, lista.size());
			assertFalse("No elimin� el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(15);
			assertEquals("No elimin� el elemento esperado", paraEliminar, lista.remove(15));
			assertEquals("La lista no tiene el tama�o esperado", 16, lista.size());
			assertFalse("No elimin� el elemento", lista.contains(paraEliminar));
			
			paraEliminar = lista.get(10);
			assertEquals("No elimin� el elemento esperado", paraEliminar, lista.remove(10));
			assertEquals("La lista no tiene el tama�o esperado", 15, lista.size());
			assertFalse("No elimin� el elemento", lista.contains(paraEliminar));
			
			assertFalse("La lista no deber�a tener elementos repetidos", hayRepetidos());
			
			
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("Deber�a dejar realizar la operaci�n porque el �ndice se encuentra en el rango");
		}
	}

	/**
	 * Revisa si hay n�meros repetidos en la lista.
	 * @return True si est� repetido, false en caso contrario.
	 */
	protected boolean hayRepetidos()
	{
		for(int i = 0; i < lista.size(); i++)
		{
			for(int j = i + 1; j < lista.size(); j++)
			{
				if(lista.get(i).intValue() == lista.get(j).intValue())
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Revisa si un n�mero est� en el arreglo del escenario.
	 * @param numero N�mero que se quiere buscar
	 * @return True si se encuentra, false de lo contrario.
	 */
	protected boolean estaEnArreglo(int numero)
	{
		for(int actual: ARREGLO_ESCENARIO_2)
		{
			if(actual == numero)
				return true;
		}
		return false;
	}
	
	//Codigo basado en el ejercicio de nivel 9 de APO 2 (Honores) 201910
}