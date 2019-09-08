package controller;

import java.util.Scanner;

import model.data_structures.ListaSencillamenteEncadenada;
import model.data_structures.Nodo;
import model.logic.MVCModelo;
import model.logic.UBERTrip;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo*/
	private MVCModelo modelo;

	/* Instancia de la Vista*/
	private MVCView view;

	private ListaSencillamenteEncadenada<UBERTrip> viajesConsultados;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new MVCView();
		modelo = new MVCModelo();
		viajesConsultados = new ListaSencillamenteEncadenada<>();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				System.out.println("--------- \nSe cargaran los datos: ");
				modelo = new MVCModelo(); 
				modelo.cargarDatos();
				System.out.println("Datos cargados");
				System.out.println("Numero de viajes cargados: " + modelo.darNumViajes() );
				UBERTrip primero = modelo.darPrimerViaje(); 
				UBERTrip ultimo = modelo.darUltimoViaje();
				System.out.println("Datos primer viaje: " + primero.darIDOrigen() + ", " + primero.darIdDestino() + ", " + primero.darHoraOMesODia() + ", " + primero.darTiempoViaje());
				System.out.println("Datos primer viaje: " + ultimo.darIDOrigen() + ", " + ultimo.darIdDestino() + ", " + ultimo.darHoraOMesODia() + ", " + ultimo.darTiempoViaje());
				break;

			case 2:
				System.out.println("--------- \nInserte una hora a consultar: ");
				dato = lector.next();
				try
				{
					int hour = Integer.parseInt(dato);
					if(hour >= 0 && hour <= 24)
					{
						viajesConsultados = modelo.consultarViajesSegunHora(hour);
						System.out.println("Viajes consultados, numero de resultados: " + viajesConsultados.size());
					}
					else
					{
						System.out.println("Hora invalida");
					}
				}
				catch(NumberFormatException e)
				{
					System.out.println("Debe ingresar la hora como un numero");
				}
				break;

			case 3:
				System.out.println("--------- \nSe ordenaran los viajes usando ShellSort: ");
				if(viajesConsultados.isEmpty())
				{
					System.out.println("Debe realizar primero una consulta de viajes segun la hora");
				}
				else
				{
					double tiempo = modelo.ordenarShellSort(viajesConsultados);
					System.out.println("Viajes ordenados, duracion: " + tiempo);
					Nodo<UBERTrip> primeros = viajesConsultados.darNodo(0);
					for(int i = 0; i < 10 && i< viajesConsultados.size(); i++)
					{
						System.out.println(primeros.darElemento().darIDOrigen() + ", " + primeros.darElemento().darIdDestino() + ", " + primeros.darElemento().darHoraOMesODia() + ", " + primeros.darElemento().darTiempoViaje());
						primeros = primeros.darSiguiente();
					}
					try
					{
						Nodo<UBERTrip> ultimos = viajesConsultados.darNodo(viajesConsultados.size() - 10);
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						for(int i = 0; i < 10 && i< viajesConsultados.size(); i++)
						{
							System.out.println(ultimos.darElemento().darIDOrigen() + ", " + ultimos.darElemento().darIdDestino() + ", " + ultimos.darElemento().darHoraOMesODia() + ", " + ultimos.darElemento().darTiempoViaje());
							ultimos = ultimos.darSiguiente();
						}
					}
					catch(IndexOutOfBoundsException e)
					{

					}

				}
				break;

			case 4: 
				System.out.println("--------- \nSe ordenaran los viajes usando MergeSort: ");
				if(viajesConsultados.isEmpty())
				{
					System.out.println("Debe realizar primero una consulta de viajes segun la hora");
				}
				else
				{
					double tiempo = modelo.ordenarMergeSort(viajesConsultados);
					System.out.println("Viajes ordenados, duracion: " + tiempo);
					Nodo<UBERTrip> primeros = viajesConsultados.darNodo(0);
					for(int i = 0; i < 10 && i< viajesConsultados.size(); i++)
					{
						System.out.println(primeros.darElemento().darIDOrigen() + ", " + primeros.darElemento().darIdDestino() + ", " + primeros.darElemento().darHoraOMesODia() + ", " + primeros.darElemento().darTiempoViaje());
						primeros = primeros.darSiguiente();
					}
					try
					{
						Nodo<UBERTrip> ultimos = viajesConsultados.darNodo(viajesConsultados.size() - 10);
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						for(int i = 0; i < 10 && i< viajesConsultados.size(); i++)
						{
							System.out.println(ultimos.darElemento().darIDOrigen() + ", " + ultimos.darElemento().darIdDestino() + ", " + ultimos.darElemento().darHoraOMesODia() + ", " + ultimos.darElemento().darTiempoViaje());
							ultimos = ultimos.darSiguiente();
						}
					}
					catch(IndexOutOfBoundsException e)
					{

					}

				}
				break;

			case 5: 
				System.out.println("--------- \nSe ordenaran los viajes usando QuickSort: ");
				if(viajesConsultados.isEmpty())
				{
					System.out.println("Debe realizar primero una consulta de viajes segun la hora");
				}
				else
				{
					double tiempo = modelo.ordenarQuickSort(viajesConsultados);
					System.out.println("Viajes ordenados, duracion: " + tiempo);
					Nodo<UBERTrip> primeros = viajesConsultados.darNodo(0);
					for(int i = 0; i < 10 && i< viajesConsultados.size(); i++)
					{
						System.out.println(primeros.darElemento().darIDOrigen() + ", " + primeros.darElemento().darIdDestino() + ", " + primeros.darElemento().darHoraOMesODia() + ", " + primeros.darElemento().darTiempoViaje());
						primeros = primeros.darSiguiente();
					}
					try
					{
						Nodo<UBERTrip> ultimos = viajesConsultados.darNodo(viajesConsultados.size() - 10);
						System.out.println(".");
						System.out.println(".");
						System.out.println(".");
						for(int i = 0; i < 10 && i< viajesConsultados.size(); i++)
						{
							System.out.println(ultimos.darElemento().darIDOrigen() + ", " + ultimos.darElemento().darIdDestino() + ", " + ultimos.darElemento().darHoraOMesODia() + ", " + ultimos.darElemento().darTiempoViaje());
							ultimos = ultimos.darSiguiente();
						}
					}
					catch(IndexOutOfBoundsException e)
					{

					}

				}
				break;

			case 6: 
				System.out.println("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break; 

			default: 
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
