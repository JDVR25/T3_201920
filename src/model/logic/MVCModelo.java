package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import model.data_structures.IEstructura;
import model.data_structures.ListaSencillamenteEncadenada;
import model.data_structures.Nodo;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private ListaSencillamenteEncadenada<UBERTrip> horas;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public MVCModelo()
	{
		horas = new ListaSencillamenteEncadenada<UBERTrip>();
	}

	public void cargarDatos()
	{
		CSVReader reader = null;
		try 
		{
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-All-HourlyAggregate.csv"));
			for(String[] param : reader)
			{
				try
				{
					UBERTrip nuevo = new UBERTrip(Integer.parseInt(param[0]), Integer.parseInt(param[1]), 
							Integer.parseInt(param[2]), Double.parseDouble(param[3]), Double.parseDouble(param[4]),
							Double.parseDouble(param[5]), Double.parseDouble(param[6]));
					horas.addLast(nuevo);
				}
				catch(NumberFormatException e)
				{

				}
			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}

		}
	}

	public int darNumViajes()
	{
		return horas.size();
	}

	public UBERTrip darPrimerViaje()
	{
		return horas.getFirst();
	}

	public UBERTrip darUltimoViaje()
	{
		return horas.getLast();
	}

	public ListaSencillamenteEncadenada<UBERTrip> consultarViajesSegunHora(int hour)
	{
		ListaSencillamenteEncadenada<UBERTrip> respuesta = new ListaSencillamenteEncadenada<UBERTrip>();

		for(UBERTrip temp: horas)
		{
			if(temp.darHoraOMesODia() == hour)
			{
				respuesta.addLast(temp);
			}
		}

		return respuesta;
	}

	public double ordenarShellSort(ListaSencillamenteEncadenada<UBERTrip> lista)
	{
		double tiempo = 0;
		//Medicion del tiempo
		long tInicial = System.currentTimeMillis();

		//Aqui poner el ordenamiento
		int cantElementos = lista.size();
		int n = 1;
		while(n > cantElementos/3)
			n = (3*n) +1;
		while(n >= 1)
		{
			for(int i = n; i < cantElementos; i++)
			{
				boolean listo = false;
				for(int j = i; j >= 0 && !listo; j -=n)
				{
					Nodo<UBERTrip> nodo1 = lista.darNodo(j);
					Nodo<UBERTrip> nodo2 = lista.darNodo(j-n);
					if(nodo1.darElemento().compareTo(nodo2.darElemento()) < 0)
					{
						UBERTrip temp = nodo1.darElemento();
						nodo1.cambiarElemento(nodo2.darElemento());
						nodo2.cambiarElemento(temp);
					}
					else
					{
						listo = true;
					}
				}
			}
			n = (n-1)/3;
		}

		long tFinal = System.currentTimeMillis();
		tiempo = tFinal - tInicial;
		return tiempo;
	}

	public double ordenarMergeSort(ListaSencillamenteEncadenada<UBERTrip> lista)
	{
		double tiempo = 0;
		//Medicion del tiempo
		long tInicial = System.currentTimeMillis();

		//Aqui poner el ordenamiento
		mergeSort(lista, 0, lista.size() - 1);

		long tFinal = System.currentTimeMillis();
		tiempo = tFinal - tInicial;
		return tiempo;
	}
	
	public void mergeSort(ListaSencillamenteEncadenada<UBERTrip> lista, int inicio, int last)
	{
		if(inicio < last)
		{
			int mitad = inicio + ((last - inicio) / 2);
			mergeSort(lista, inicio, mitad);
			mergeSort(lista, mitad + 1, last);
			merge(lista, inicio, mitad, last);
		}
	}
	
	public void merge(ListaSencillamenteEncadenada<UBERTrip> lista, int inicio, int midle, int last)
	{
		ListaSencillamenteEncadenada<UBERTrip> aux = new ListaSencillamenteEncadenada<UBERTrip>();
		Nodo<UBERTrip> previous = null;
		Nodo<UBERTrip> firstPart = null;
		if(inicio != 0)
		{
			previous = lista.darNodo(inicio - 1);
			firstPart = previous.darSiguiente();
		}
		else
		{
			firstPart = lista.darNodo(inicio);
		}
		Nodo<UBERTrip> secondPart = lista.darNodo(midle + 1);
		int i = inicio;
		int m = midle + 1;
		while(i <= midle && m <= last)
		{
			if(firstPart.darElemento().compareTo(secondPart.darElemento()) < 0)
			{
				aux.addLast(firstPart.darElemento());
				firstPart = firstPart.darSiguiente();
				i++;
			}
			else
			{
				aux.addLast(secondPart.darElemento());
				secondPart = secondPart.darSiguiente();
				m++;
			}
		}
		if(i <= midle)
		{
			while(i <= midle)
			{
				aux.addLast(firstPart.darElemento());
				firstPart = firstPart.darSiguiente();
				i++;
			}
		}
		if(m <= last)
		{
			while(m <= last)
			{
				aux.addLast(secondPart.darElemento());
				secondPart = secondPart.darSiguiente();
				m++;
			}
		}
		if(inicio == 0)
		{
			lista.cambiarPrimero(aux.darNodo(0));
		}
		else
		{
			previous.cambiarSiguiente(aux.darNodo(0));
		}
		if(last == lista.size()-1)
		{
			lista.cambiarUltimo(aux.darNodo(aux.size()-1));
		}
		else
		{
			aux.darNodo(aux.size() - 1).cambiarSiguiente(secondPart.darSiguiente());
		}
	}

	public double ordenarQuickSort(ListaSencillamenteEncadenada<UBERTrip> lista)
	{
		double tiempo = 0;
		//Medicion del tiempo
		long tInicial = System.currentTimeMillis();

		//Aqui poner el ordenamiento

		long tFinal = System.currentTimeMillis();
		tiempo = tFinal - tInicial;
		return tiempo;
	}
}
