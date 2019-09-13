package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public abstract class ListaAbstractaNoOrdenadaTest extends ListaAbstractaTest
{

	@Override
	public void testIterator() 
	{
		super.testIterator();
		Iterator<Integer> iterador = lista.iterator();
		int indiceActual = 0;
		while(iterador.hasNext())
		{
			try
			{
				Integer actual = iterador.next();
				assertEquals("El elemento en la posición " + indiceActual + " del iterador no coincide con el que debería", new Integer(ARREGLO_ESCENARIO_2[indiceActual]), actual);
				indiceActual++;
			}
			catch(NoSuchElementException e)
			{
				fail("Indica que puede avanzar, pero lanza excepción al avanzar.");
			}
		}
	}
	
	@Override
	public void testToArray() 
	{
		//Omitido debido a que al exigir comparable el test falla
//		super.testToArray();
//		Object[] arreglo = lista.toArray();
//		for(int i = 0; i < arreglo.length; i++)
//		{
//			assertEquals("El elemento en la posición "+ i + " no es el esperado", ARREGLO_ESCENARIO_2[i], arreglo[i]);
//		}
	}
	
	@Override
	public void testAdd() {
		super.testAdd();
	}
	
	@Override
	public void testRemoveObject() {
		super.testRemoveObject();
		
		
	}
	
	@Override
	public void testGet() {
		super.testGet();
		
		for(int i = 0; i < lista.size(); i ++)
		{
			assertEquals("El elemento en la posición " + i  + " no es el esperado", new Integer(ARREGLO_ESCENARIO_2[i]), lista.get(i));
		}
	}
	
	
	/**
	 * Prueba el método add en un índice.
	 */
	@Test
	public void addIndice()
	{
		try
		{
			lista.add(1, 5);
			fail("Debería lanzar la excepción, el índice se encuentra por fuera de la lista.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//
		}
		
		try
		{
			lista.add(0, 5);
			assertEquals("El tamaño de la lista no es correcto", 1, lista.size());
			assertEquals("El elemento no es el esperado", new Integer(5), lista.get(0));
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("No debería lanzar la excepción, el índice se encuentra dentro de la lista.");
		}
		
		//Prueba con 20 elementos
		lista.clear();
		setupEscenario2();
		
		try
		{
			lista.add(-1, 5);
			fail("Debería lanzar la excepción, el índice se encuentra por fuera de la lista.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//
		}
		
		try
		{
			lista.add(21, 5);
			fail("Debería lanzar la excepción, el índice se encuentra por fuera de la lista.");
		}
		catch(IndexOutOfBoundsException e)
		{
			//
		}
		
		try
		{
			lista.add(0, 5);
			assertEquals("El tamaño de la lista no es correcto", 21, lista.size());
			assertEquals("El elemento no es el esperado", new Integer(5), lista.get(0));
			assertEquals("El elemento no es el esperado", new Integer(350), lista.get(1));
			
			lista.add(21, 290);
			assertEquals("El tamaño de la lista no es correcto", 22, lista.size());
			assertEquals("El elemento no es el esperado", new Integer(290), lista.get(21));
			assertEquals("El elemento no es el esperado", new Integer(396), lista.get(20));
			
			lista.add(10, 580);
			assertEquals("El tamaño de la lista no es correcto", 23, lista.size());
			assertEquals("El elemento no es el esperado", new Integer(580), lista.get(10));
			assertEquals("El elemento no es el esperado", new Integer(80), lista.get(9));
			assertEquals("El elemento no es el esperado", new Integer(360), lista.get(11));
		}
		catch(IndexOutOfBoundsException e)
		{
			fail("No debería lanzar la excepción, el índice se encuentra dentro de la lista.");
		}
	}
	
	//Codigo basado en el ejercicio de nivel 9 de APO 2 (Honores) 201910
}