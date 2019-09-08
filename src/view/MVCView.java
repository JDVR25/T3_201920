package view;

import model.logic.MVCModelo;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar los viajes de UBER");
			System.out.println("2. Consultar viajes segun hora");
			System.out.println("3. Ordenar los viajes consultados con ShellSort");
			System.out.println("4. Ordenar los viajes consultados con MergeSort");
			System.out.println("5. Ordenar los viajes consultados con QuickSort");
			System.out.println("6. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(MVCModelo modelo)
		{
			// TODO implementar
		}
}
