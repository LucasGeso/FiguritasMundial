package tp.integrador;

import java.util.Arrays;
import java.util.Scanner;

public class Qatar2022 {
	
	private static final int ACTUALIZAR_DATOS_FIGURITA = 1, COMPRAR_SOBRE = 2, VISUALIZAR_FIGURITAS_ACTUALES = 3, CALCULAR_PORCENTAJE_DE_COMPLETADO = 4, VISUALIZAR_FIGURITAS_REPETIDAS = 5,INTERCAMBIAR_FIGURITA =6, CALCULAR_PORCENTAJE_FIGURITAS_REPETIDAS =7,	 SALIR = 9;
	
	public static void main(String[] args) {

	
		
		Album actual = new Album();
		Album.inicializarCodigosDeFiguritas();
		
		int opcionIngresada;
		Scanner teclado = new Scanner(System.in);

		System.out.println("Bienvenido album de figuritas Panini Virtual");

		do {
			mostrarMenu();
			opcionIngresada = teclado.nextInt();
			actual = determinarAccionARealizar(actual, opcionIngresada, teclado);
		}while (!actual.elAlbumEstaCompleto());
	}

	private static void mostrarMenu() {
		System.out.println("************************");
		System.out.println("Menu de opciones\n");
		System.out.println(ACTUALIZAR_DATOS_FIGURITA + " - Actualizar datos de la figurita");
		System.out.println(COMPRAR_SOBRE + " - Comprar sobre ");
		System.out.println(VISUALIZAR_FIGURITAS_ACTUALES + " - Visualizar figuritas actuales");
		System.out.println(CALCULAR_PORCENTAJE_DE_COMPLETADO + " - Calcular el porcentaje del album completado");
		System.out.println(VISUALIZAR_FIGURITAS_REPETIDAS + " - Visualizar figuritas repetidas");
		System.out.println(INTERCAMBIAR_FIGURITA + " - Intercambiar una de nuestras figuritas repetidas");
		System.out.println(CALCULAR_PORCENTAJE_FIGURITAS_REPETIDAS + " - Calcular el porcentaje de figuritas repetidas");
		System.out.println(SALIR + " - Salir");
		System.out.println("************************");
	}

	private static Album determinarAccionARealizar(Album actual, int opcionIngresada, Scanner teclado) {

		switch(opcionIngresada) {
		case ACTUALIZAR_DATOS_FIGURITA:
			actualizardatosFigurita(teclado, actual);
			break;
		case COMPRAR_SOBRE:
			comprarSobre(teclado, actual);
			break;
		case VISUALIZAR_FIGURITAS_ACTUALES:
			visualizarFiguritasActuales(teclado, actual);
			break;
		case CALCULAR_PORCENTAJE_DE_COMPLETADO:
			calcularElPorcentajeDeCompletado(teclado, actual);
			break;
		case VISUALIZAR_FIGURITAS_REPETIDAS:
			visualizarFiguritasRepetidas(teclado, actual);
			break;
		case INTERCAMBIAR_FIGURITA:
			intercambiarFiguritas(teclado, actual);
			break;
		case CALCULAR_PORCENTAJE_FIGURITAS_REPETIDAS:
			calcularPorcentajeRepetidas(teclado, actual);
		case SALIR:
			break;
		}
		return actual;
	}

	private static void actualizardatosFigurita(Scanner teclado, Album actual) {
		String codigo;
		char grupo;
		String seleccion;
		String nombreJugador;
		double valor;
		Figurita aActualizar;

		System.out.println("***************");
		System.out.println("Actualizar datos de figurita");
		System.out.println("***************"); 

		System.out.println("Ingrese el codigo a actualizar");
		codigo = teclado.next();
		aActualizar = Album.getFigurita(codigo);
		
		System.out.println("Selecci�n: " + aActualizar.getSeleccion());
		
		System.out.println("Ingrese el grupo al que pertenece la selecci�n");
		grupo = teclado.next().charAt(0);
		System.out.println("Ingrese el nombre del jugador");
		nombreJugador = teclado.next();
		System.out.println("Ingrese el valor");
		valor = teclado.nextDouble();

		Album.actualizarDatosFigurita(codigo, grupo, nombreJugador, valor);
	}

	private static void comprarSobre(Scanner teclado, Album actual) {
		Figurita compradas[] = actual.comprarSobre();
		
		System.out.println("Las figuritas son");

		/*
		 * Mostrar las figuritas obtenidas
		 */
		System.out.println(Arrays.toString(compradas));
		/* 
		 * Por cada figuria comprada, agregarlas al album actual actual.agregarFigurita(compradas[i]);
		 */
		for(int i=0; i<compradas.length; i++) {
			actual.agregarFigurita(compradas[i]);
		}

	}
	
	private static void visualizarFiguritasActuales(Scanner teclado, Album actual) {
		/*
		 * Se deben mostrar las figuritas que posee el usuario de manera ordenada.
		 */
		actual.ordenarFiguritasActuales();

		for(int i=0; i<actual.getFiguritasActuales().length; i++) {
			if(actual.getFiguritasActuales()[i]!=null) {
				System.out.print(actual.getFiguritasActuales()[i]);
				System.out.print(" - ");
			}
		}
		
		System.out.println(" ");
	}
	
	private static void calcularElPorcentajeDeCompletado(Scanner teclado, Album actual) {
		System.out.println("El album est� completo en un " + actual.calcularPorcentajeCompletado() + "%");
	}
	
	private static void visualizarFiguritasRepetidas(Scanner teclado, Album actual) {
		
		for(int i=0; i<actual.getFiguritasRepetidas().length; i++) {
			if(actual.getFiguritasRepetidas()[i]!=null) {
				System.out.print(actual.getFiguritasRepetidas()[i]);
				System.out.print(" - ");
			}
		}
		System.out.println(" ");
	}
	
	private static void intercambiarFiguritas(Scanner teclado, Album actual) {
		System.out.println("Tus figuritas repetidas son:");
		visualizarFiguritasRepetidas(teclado, actual);
		System.out.println(" ");
		String codigoFiguritaParaIntercambiar;
		String figuritaElegida;
		boolean hacerIntercambio = false;
		String respuestaIntercambio =" ";
		
		do {
			System.out.println("Ingrese el código de la figurita que desea cambiar: ");
			codigoFiguritaParaIntercambiar = teclado.next();
		}while(actual.buscarFiguritaRepetida(codigoFiguritaParaIntercambiar)==null);

		actual.buscarFiguritaRepetida(codigoFiguritaParaIntercambiar);
		System.out.println(" ");
		System.out.println("Figuritas Actuales: ");
		visualizarFiguritasActuales(teclado, actual);
		System.out.println(" ");
		
		System.out.println("Opciones para elegir: ");
		Figurita intercambio[] = actual.figuritasParaIntercambiar();
		System.out.println(Arrays.toString(intercambio));
		System.out.println(" ");
		System.out.println("Las figuritas que no tenés son: ");
		chequearFiguritasFaltantes(intercambio, actual.getFiguritasActuales());
		System.out.println(" ");
		mostrarFiguritasQueTeFaltan(intercambio);
		System.out.println(" ");
		
		
		System.out.println("Te interesa intercambiar alguna figurita de estas figuritas? ");
		respuestaIntercambio = teclado.next().toUpperCase();
		if(respuestaIntercambio.equals("SI")) {
			hacerIntercambio = true;
		}
		
		if(hacerIntercambio==true) {
			do {
				System.out.println("Seleccionar una de las siguientes figuritas");
				figuritaElegida = teclado.next();
			}while(!chequearFiguritaElegida(figuritaElegida, intercambio));
			
			Figurita intercambiada = Album.getFigurita(figuritaElegida);
			actual.agregarFigurita(intercambiada);
			actual.borrarFiguritaRepetida(codigoFiguritaParaIntercambiar);
			
			System.out.println("Intercambio exitoso");
			System.out.println("Tu album quedó asi:");
			visualizarFiguritasActuales(teclado, actual);
			System.out.println(" ");
			
		} else {
			System.out.println("Intercambio cancelado");
			System.out.println(" ");
		}

		
	}

	private static void mostrarFiguritasQueTeFaltan(Figurita[] figuritasFaltantes) {
		for(int i=0; i<figuritasFaltantes.length;i++) {
			if(figuritasFaltantes[i]!=null) {
				System.out.print(figuritasFaltantes[i] + " ");
			}
		}
	}
	
	private static void calcularPorcentajeRepetidas(Scanner teclado, Album actual) {
		System.out.println("El porcentaje de figuritas repetidas con relación al album es: " + actual.calcularPorcentajeRepetidas() + "%");
	}
	
	//COMPRUEBA SI EL CÓDIGO SELECCIONADO PARA EL INTERCAMBIO, SEA REALMENTE UNA  DE LAS FIGURITAS OFRECIDAS
	private static boolean chequearFiguritaElegida(String codigo, Figurita[] intercambio) {
		for(int i=0; i<intercambio.length; i++) {
			if(intercambio[i]!=null && intercambio[i].getCodigo().equals(codigo)) {
				return true;
			}
		}
		return false;
	}
	
	//ESTE MÉTODO ELIMINA LAS FIGURITAS QUE TE OFRECIERON PARA INTERCAMBIAR QUE YA ESTÁN EN TU ALBUM Y SOLO DEJA LAS QUE NO TENEMOS
	private static void chequearFiguritasFaltantes(Figurita intercambio[], Figurita figuritasActuales[]){
		
		for(int i=0; i<intercambio.length; i++) {
			for(int j=0; j<figuritasActuales.length; j++) {
				if(intercambio[i]!=null && figuritasActuales[j]!=null && intercambio[i].getCodigo().equals(figuritasActuales[j].getCodigo())){
					intercambio[i]=null;
				}
	
			}
			
		}
		
	}
}
