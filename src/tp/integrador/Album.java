package tp.integrador;

public class Album {
	
	private static String selecciones[] = {"Qatar", "Ecuador", "Senegal", "Paises Bajos", "Inglaterra", "Irán", "EEUU", "Gales", "Argentina", "Arabia Saudita", "México", "Polonia", "Francia", "Australia", "Dinamarca", "Tunez", "España", "Costa Rica", "Alemania", "Japón", "Bélgica", "Canadá", "Marruecos", "Croacia", "Brasil", "Serbia", "Suiza", "Camerún", "Portugal", "Ghana", "Uruguay", "Corea del Sur"};
	private final static int MAXIMO_DE_FIGURITAS = 704;
	private static Figurita figuritasDisponibles[] = new Figurita[MAXIMO_DE_FIGURITAS];
	private Figurita figuritasActuales[];
	private final static int MAXIMA_CANTIDAD_DE_FIGURITAS_POR_PAIS= 22;
	private Figurita figuritasRepetidas[];

	
	public Album() {
		this.figuritasActuales = new Figurita[MAXIMO_DE_FIGURITAS];
		this.figuritasRepetidas = new Figurita[MAXIMO_DE_FIGURITAS];

	}
	
	public static void inicializarCodigosDeFiguritas() {
		String codigoLetra;
		String concatenacion;
		int posicion =0;
		
		for(int i=0;i<selecciones.length;i++){
			for(int j=0; j<MAXIMA_CANTIDAD_DE_FIGURITAS_POR_PAIS;j++){
					codigoLetra = selecciones[i].substring(0,3).toUpperCase();
					concatenacion = codigoLetra + (j+1);
					if(figuritasDisponibles[posicion]==null) {
					figuritasDisponibles[posicion]= new Figurita(concatenacion);
					figuritasDisponibles[posicion].setSeleccion(selecciones[i]);
					posicion++;
					}
			}
		}
	}
	
	public Figurita[] getFiguritasActuales() {
		return figuritasActuales;
	}
	
	public Figurita[] getFiguritasRepetidas() {
		return figuritasRepetidas;
	}

	/***
	 * En funci�n del c�digo de figurita, se deben actualizar los datos de la misma en figuritasDisponibles 
	 */
	public static void actualizarDatosFigurita(String codigo, char grupo, String nombreJugador, double valor) {
		
		for(int i=0; i<figuritasDisponibles.length; i++) {
			if(figuritasDisponibles[i]!=null && figuritasDisponibles[i].getCodigo().equals(codigo)) {
				figuritasDisponibles[i].setGrupo(grupo);
				figuritasDisponibles[i].setNombreJugador(nombreJugador);
				figuritasDisponibles[i].setValor(valor);
				break;
			}
		}
	}
	
	/***
	 * En funci�n del c�digo de figurita, devuelve la figurita de figuritasDisponibles
	 */
	public static Figurita getFigurita(String codigo) {
		Figurita figuritaBuscada = null;
		
		for(int i=0; i<figuritasDisponibles.length; i++) {
			if(figuritasDisponibles[i]!=null && figuritasDisponibles[i].getCodigo().equals(codigo)) {
				figuritaBuscada = figuritasDisponibles[i];
				return figuritaBuscada;
			}	
		}
		return figuritaBuscada;
	}
	
	/***
	 * Se debe calcular y devolver 5 c�digos de figurita de manera aleatoria.
	 */
	public Figurita[] comprarSobre() {
		Figurita sobre[] = new Figurita[5];
		int codigo = 0;
		
		for(int i=0; i<sobre.length; i++) {
			if(sobre[i]==null) {
				codigo = (int)(Math.random()*704);
				sobre[i]=figuritasDisponibles[codigo];
			}
		}
		return sobre;
	}
	
	/***
	 * Agrega una nueva figurita al array figuritasActuales o al array figuritasRepetidas si cumple esa condición
	 */
	public void agregarFigurita(Figurita nueva) {
		for(int i=0; i<figuritasActuales.length; i++) {
			if(figuritasActuales[i]==null && !esRepetida(nueva)) {
				figuritasActuales[i] = nueva;
				break;
			} if(esRepetida(nueva)){
				agregarFiguritaRepetida(nueva);
			}
		}

	}
	
	//MÉTODO TP EXTRA
	public boolean esRepetida(Figurita nueva) {
		for (int i = 0; i < figuritasActuales.length; i++) {
			if (figuritasActuales[i]!= null && figuritasActuales[i].getCodigo().equals(nueva.getCodigo())) {
				return true;
			}
		}
		return false;
	}
	
	public void agregarFiguritaRepetida(Figurita nueva) {

	    for (int i = 0; i < figuritasRepetidas.length; i++) {
	        if (figuritasRepetidas[i] != null && figuritasRepetidas[i].getCodigo().equals(nueva.getCodigo())) {
	            return;
	        }
	    } 
	    for (int i = 0; i < figuritasRepetidas.length; i++) {
	        if (figuritasRepetidas[i] == null) {
	            figuritasRepetidas[i] = nueva;
	            return;
	        }
	    }
	}
	
	/***
	 * Debe ordenar el array figuritasActuales
	 */
	public void ordenarFiguritasActuales() {
		Figurita auxiliar = null;
		
		for(int i=1; i<=figuritasActuales.length; i++) {
			for(int j=0; j<figuritasActuales.length-1; j++) {
				if(figuritasActuales[j]!=null && figuritasActuales[j+1]!=null && figuritasActuales[j].getCodigo().compareToIgnoreCase(figuritasActuales[j+1].getCodigo())>0) {
					auxiliar = figuritasActuales[j];
					figuritasActuales[j] = figuritasActuales[j+1];
					figuritasActuales[j+1]= auxiliar;
				}
			}
		}

	}
	
	/***
	 * Debe verificar que todas las figuritas disponibles est�n presentes al menos una vez en las figuritas actuales 
	 */		
	public boolean elAlbumEstaCompleto() {
	
		for(int i=0; i<figuritasActuales.length; i++) {
			if(figuritasActuales[i]==null ) {
				return false;
			}
		}
		return true;
	}
	
	/***
	 * Debe calcular el porcentaje de figuritas del album que est� completo. 
	 * Para esto se debe basar en la informaci�n de las figuritasDisponibles en relaci�n a las figuritasActuales que se tiene en el album.
	 */
	public double calcularPorcentajeCompletado() {
		int acumuladorFiguritas =0;
		for(int i=0; i<figuritasActuales.length; i++) {
			if(figuritasActuales[i]!=null) {
				acumuladorFiguritas++;
			}
		}
		
		double porcentaje = (double)(acumuladorFiguritas * 100)/MAXIMO_DE_FIGURITAS;
		
		return porcentaje;
	}
	
	public Figurita buscarFiguritaRepetida(String codigo) {
		Figurita figuritaBuscada = new Figurita(codigo);
		
		for(int i=0; i<figuritasRepetidas.length; i++) {
			if(figuritasRepetidas[i]!=null && figuritaBuscada.getCodigo().equals(figuritasRepetidas[i].getCodigo())) {
				return figuritaBuscada;
			}
		}
		return null;
	}
	
	//NOS MUESTRA 5 FIGURITAS DE LAS CUALES PODEMOS SELECCIONAR 1 PARA HACER UN INTERCAMBIO
	public Figurita[] figuritasParaIntercambiar() {
		Figurita figuritasParaIntercambio[] = new Figurita[5];
		int codigo=0; 
		
		for(int i=0; i<figuritasParaIntercambio.length; i++) {
			if(figuritasParaIntercambio[i]==null) {
				codigo = (int)(Math.random()*704);
				figuritasParaIntercambio[i]= figuritasDisponibles[codigo];
			}
		}
		
		return figuritasParaIntercambio;
	}
	
	//ELIMINA LA FIGURITA QUE DIMOS PARA REALIZAR UN INTERCAMBIO
	public void borrarFiguritaRepetida(String codigo) {
		for(int i=0; i<figuritasRepetidas.length; i++) {
			if(figuritasRepetidas[i]!=null && figuritasRepetidas[i].getCodigo().equals(codigo)) {
				figuritasRepetidas[i] = null;
				return;
			}
		}
	}


	public double calcularPorcentajeRepetidas() {
		int acumuladorRepetidas =0;
		for(int i=0; i<figuritasRepetidas.length; i++) {
			if(figuritasRepetidas[i]!=null) {
				acumuladorRepetidas++;
			}
		}
		
		double porcentaje = (double)(acumuladorRepetidas * 100)/MAXIMO_DE_FIGURITAS;
		
		return porcentaje;
	}	
}
