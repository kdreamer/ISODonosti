package casarural;
/**
 * @author Grupo 2
 */
import java.rmi.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import java.util.Enumeration;

import excepciones.NoSePuedeReservarException;

public final class GestorOfertas {
	accesoDatos.GestorBD gbd;

	// ///// LO PONGO YO
	GestorCasasRurales gcr;

	// ////////

	private static GestorOfertas elGestorOfertas;

	private GestorOfertas() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		gbd = accesoDatos.GestorBD.getInstance();

		// //////// LO PONGO YO
		gcr = GestorCasasRurales.getInstance();
		// ////////////
	}

	/**
	 * Devuelve una instancia del gestor de ofertas
	 * 
	 * @param Ninguno
	 * @return el gestor de ofertas
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static GestorOfertas getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (elGestorOfertas == null)
			elGestorOfertas = new GestorOfertas();
		return elGestorOfertas;
	}

	/**
	 * Asigna una oferta con el numero de casa, fecha de inicio, fecha de fin y
	 * precio
	 * 
	 * @param Numero
	 *            de casa, fecha de inicio, fecha de fin y precio
	 * @return Ninguno
	 */
	public void asignarOferta(int numCasa, java.sql.Date diaIni,
			java.sql.Date diaFin, float precio) throws Exception {
		OfertasEnMemoriaPrincipal mem = null;
		try {
			Vector v = new Vector();
			v = gbd.seleccionarOfertas(diaIni, diaFin, numCasa);
			Enumeration e2 = v.elements();
			mem = new OfertasEnMemoriaPrincipal();
			Oferta of;
			while (e2.hasMoreElements()) {
				of = (Oferta) e2.nextElement();
				mem.anadirReserva(of.getDiaIni(), of.getDiaFin(), of
						.getNumOferta(), of.getPrecio(), of.getNumReserva());
			}
		} catch (Exception ex) {
			System.out.println("gestor de ofertas");
			ex.printStackTrace();
		}
		if (!mem.existeOfertaReservada()) {
			try {
				Enumeration e = mem
						.obtenerOfertasIncluidasEntre(diaIni, diaFin)
						.elements();
				Oferta of1;
				Vector v2 = new Vector();
				while (e.hasMoreElements()) {
					of1 = (Oferta) e.nextElement();
					v2.addElement(of1.getNumOferta());
				}
				gbd
						.transaccionDeOfertas(v2, diaIni, diaFin, mem
								.obtenerOfertaAnteriorAYSolapadaCon(diaIni,
										diaFin), mem
								.obtenerOfertaPosteriorAYSolapadaCon(diaIni,
										diaFin), precio, numCasa);
			} catch (Exception ex) {
				System.out.println("gestor de ofertas");
				ex.printStackTrace();
			}
		}

		else {
			NoSePuedeReservarException exc = new NoSePuedeReservarException(
					"Error, no se puede crear esta oferta ya que esta casa se encuentra "
							+ "reservada estos dias");
			throw exc;
		}
	}

	public Oferta obtenerMejorOferta(Date diaIni, Date diaFin, int dormitorios,
			int banos, String criterio) throws RemoteException {
		boolean hayMejor = false;
		Enumeration codigos = gcr.getCodigosCasas().elements();
		int numCasa;
		Enumeration ofertas, ofertas2;
		Vector vecofertas;
		OfertasEnMemoriaPrincipal mem = null;
		Oferta of;
		Oferta laMejorOferta = new Oferta();
		laMejorOferta.setTamano(0);
		laMejorOferta.setPrecio(9999999);
		laMejorOferta.setNumCasa(0);
		while (codigos.hasMoreElements()) {
			mem = new OfertasEnMemoriaPrincipal();
			numCasa = Integer.parseInt(codigos.nextElement().toString());
			boolean hayOfertas = false;
			ofertas = gbd.seleccionarOfertas(diaIni, diaFin, numCasa)
					.elements();

			vecofertas = new Vector();
			while (ofertas.hasMoreElements()) {
				hayOfertas = true;
				of = (Oferta) ofertas.nextElement();
				vecofertas.addElement(of);
				mem.anadirReserva(of.getDiaIni(), of.getDiaFin(), of
						.getNumOferta(), of.getPrecio(), of.getNumReserva());
			}
			if ((!mem.existeOfertaReservada()) && (hayOfertas)) {
				Vector habsYBans = new Vector();
				habsYBans = gbd.numHabitacionesnumBanos(numCasa);

				int camas = 0;

				camas = gbd.camas(numCasa);

				if ((Integer.parseInt(habsYBans.firstElement().toString()) >= dormitorios)
						&& (Integer
								.parseInt(habsYBans.lastElement().toString()) >= banos)) {
					ofertas2 = vecofertas.elements();
					while (ofertas2.hasMoreElements()) {
						of = (Oferta) ofertas2.nextElement();
						of.setTamano(camas);
						of.setNumCasa(numCasa);
						if (((criterio.compareTo("tamaño") == 0) && (of
								.getTamano() > laMejorOferta.getTamano()))
								|| ((criterio.compareTo("precio") == 0) && (of
										.getPrecio() < laMejorOferta
										.getPrecio()))) {
							hayMejor = true;
							laMejorOferta.setDiaFin(of.getDiaFin());
							laMejorOferta.setDiaIni(of.getDiaIni());
							laMejorOferta.setTamano(of.getTamano());
							laMejorOferta.setNumOferta(of.getNumOferta());
							laMejorOferta.setPrecio(of.getPrecio());
							laMejorOferta.setNumCasa(of.getNumCasa());

						}// if
					}// while

				}

			}

			mem = null;

		}

		/*
		 * if(hayMejor){
		 * //dmo.inicializarPantalla(true,laMejorOferta.getNumCasa(),laMejorOferta.getPrecio(),laMejorOferta.getTamano());
		 * DetallesMejorOferta dmo=new
		 * DetallesMejorOferta(true,laMejorOferta.getNumCasa(),laMejorOferta.getPrecio(),laMejorOferta.getTamano());
		 * dmo.setVisible(true);
		 *  } else { DetallesMejorOferta dmo=new
		 * DetallesMejorOferta(false,0,0,0); dmo.setVisible(true); }
		 */
		// else dmo.inicializarPantalla(false,0,0,0);
		// System.out.println("el precio de la mejor oferta
		// es"+laMejorOferta.getPrecio()+"");
		// System.out.println("el tamano de la mejor oferta
		// es"+laMejorOferta.getTamano()+"");

		return laMejorOferta;

	}

	/**
	 * Método que obtiene ofertas en función de las restricciones impuestas
	 * por el usuario
	 * 
	 * @param numCasa, número de la casa de la cual se buscan ofertas
	 * @param diaIni, día de inicio del rango de fechas para la búsqueda de ofertas
	 * @param diaFin, día de fin del rango de fechas para la búsqueda de ofertas
	 * @return un buffer (Clase OfertasEnMemoriaPrincipal) con las ofertas encontras
	 */
	public OfertasEnMemoriaPrincipal obtenerOfertas(int numCasa,
			java.sql.Date diaIni, java.sql.Date diaFin){
		//Si hay problemas, he borrado throws exception del método, porque las excepciones
		//son capturadas ya en el gestor y en la clase de OfertasEnMemoriaPpal.

		// preguntar el vector de las ofertas dentro de las fechas
		// (diaIni-diaFin)
		// si no existen catch la exception
		Vector ofertas = new Vector();// vector donde se guarda el resultado
										// de la consulta sql

		OfertasEnMemoriaPrincipal oferts = new OfertasEnMemoriaPrincipal();

		ofertas = gbd.seleccionarOfertas(diaIni, diaFin, numCasa);
		// se hace la consulta sql c1 y se guarda el resultado en ofertas
		//guardamos la consulta en mem principal
		Iterator iter1 = ofertas.iterator();// el iterador para recorrer la
											// consulta SQL
		while (iter1.hasNext()) {
			Oferta next = (Oferta) iter1.next();
			String numOferta = next.getNumOferta();
			java.sql.Date diaIniOtro = next.getDiaIni();
			java.sql.Date diaFinOtro = next.getDiaFin();
			float precio = next.getPrecio();
			String numReserva = next.getNumReserva();
			oferts.anadirReserva(diaIniOtro, diaFinOtro, numOferta, precio,
					numReserva);
		}
		// ////////////al salir de aqui tiene las ofertas guardadas en
		// //////////
		// //////////// oferts /////////////////////////////////////////////////

		// //////////////////////////////////////////////////////////
		// ////////////aniadirDiaIniyFin(diaIni,DiaFin)//////////////
		// ////////////vamos a usar la funcion obtenerOfertasIncluidasEntre
		// ofertas1 = oferts.obtenerOfertasIncluidasEntre(diaIni,diaFin);
		// //////devuelve las ofertas incluidas entre diaIni y diaFin/////
		return oferts;
	}
	/**
	 * Método para ordenar un vector de ofertas por días
	 * 
	 * @param izq, extremo izquierdo del vector
	 * @param der, extremo derecho del vector
	 * @param a, vector de ofertas
	 * @return vector de ofertas ordenadas
	 */
	public Vector quickSort_Dias(int izq, int der, Vector a) {
		//ordena de mayor a menor
		if (a == null || a.isEmpty())
			return a;
		int i = izq;
		int j = der;
		Oferta of = (Oferta) a.elementAt((izq + der) / 2);
		long pivote = of.getDiaFin().getTime() -  of.getDiaIni().getTime();
		do {
			while (((Oferta)a.elementAt(i)).getDiaFin().getTime() - (((Oferta) a.elementAt(i)).getDiaIni().getTime()) > pivote)
				i++;
			while (((Oferta) a.elementAt(j)).getDiaFin().getTime() - (((Oferta) a.elementAt(j)).getDiaIni().getTime()) < pivote)
				j--;
			if (i <= j) {
				Oferta aux = (Oferta) a.elementAt(i);
				a.setElementAt(a.elementAt(j), i);
				a.setElementAt(aux, j);
				i++;
				j--;
			}
		} while (i <= j);
		if (izq < j)
			quickSort_Dias(izq, j, a);
		if (i < der)
			quickSort_Dias(i, der, a);
		return a;
	}

	/**
	 * Método para ordenar un vector de ofertas por precio
	 * 
	 * @param izq, extremo izquierdo del vector
	 * @param der, extremo derecho del vector
	 * @param a, vector de ofertas
	 * @return vector de ofertas ordenadas
	 */
	public Vector quickSort_Precio(int izq, int der, Vector a) {
//		ordena de menor a maYOR
		if (a == null || a.isEmpty())
			return a;
		int i = izq;
		int j = der;
		Oferta of = (Oferta) a.elementAt((izq + der) / 2);
		float pivote = of.getPrecio();
		do {
			while (((Oferta) a.elementAt(i)).getPrecio() < pivote)
				i++;
			while (((Oferta) a.elementAt(j)).getPrecio() > pivote)
				j--;
			if (i <= j) {
				Oferta aux = (Oferta) a.elementAt(i);
				a.setElementAt(a.elementAt(j), i);
				a.setElementAt(aux, j);
				i++;
				j--;
			}
		} while (i <= j);
		if (izq < j)
			quickSort_Precio(izq, j, a);
		if (i < der)
			quickSort_Precio(i, der, a);
		return a;
	}
	/**
	 * Devuelve el resultado de las ofertas que interesan, según las
	 * restricciones de fechas, precio y demás, que opcionalmente pueda haber
	 * introducido.
	 * 
	 * Primero se realiza la obtencion de datos desde la base de datos, una vez obtenido el vector de ofertas
	 * se realiza la ordenacion del vector segun el criterio de precio o duracion en dias.
	 * Una vez ordenado el vector, se realiza la posible concatenacion de ofertas entre si, 
	 * incluyendo en una mayores las mas pequeñas 
	 * 
	 * @param numDormitorios,
	 *            mínimo número de dormitorios.
	 * @param numBanos mínimo número de baños.
	 * @param diaIni,
	 *            fecha de inicio de para la búsqueda de ofertas.
	 * @param diaIni,
	 *            fecha de finalización de para la búsqueda de ofertas.
	 * @param precio,
	 *            precio máximo de las ofertas a priori.
	 * @param numMinDias,
	 *            número mínimo de días para la estancia.
	 * @param orden,
	 *            ordenados por precio o por número de días.
	 * @return Vector, un vector con las ofertas interesantes para el usuario.
	 */
	public Vector buscarOfertas(java.sql.Date diaIni, java.sql.Date diaFin, float precio,		
			int numMinDias, int numDormitorios, int numBanos,boolean orden) {
		Vector ofertas = (Vector)gbd.seleccionarCasasDorWC(numDormitorios, numBanos,
				diaIni, diaFin, precio, numMinDias, orden);
		
		if(!ofertas.isEmpty()){
			if (orden){
				ofertas = quickSort_Precio(ofertas.indexOf(ofertas.firstElement()), ofertas
						.indexOf(ofertas.lastElement()), ofertas);
			}else{
				ofertas = quickSort_Dias(ofertas.indexOf(ofertas.firstElement()), ofertas
					.indexOf(ofertas.lastElement()), ofertas);		
			}
			ofertas = concatenarOfertas(ofertas);
		}//if
		return ofertas;
	}
	/**
	 * Se realiza la posible concatenacion de ofertas entre si, 
	 * incluyendo en una mayores las mas pequeñas, es decir si hay 2 ofertas consecutivas en fechas
	 * se crea automaticamente otra oferta mayor que incluye a las otras 2 de manera que existen 3 ofertas
	 * 
	 * @param Vector,
	 *            vector de ofertas simples con opcion a ser concatenadas
	 * @return Vector, un vector con las ofertas concatenadas.
	 */
	public Vector concatenarOfertas(Vector ofertas) {
		// suponemos que ofertas viene ordenado por dia inicio
		if (ofertas == null || ofertas.isEmpty())
			return ofertas;
		Vector resultado = (Vector) ofertas.clone();
		Vector aux;
		while (!ofertas.isEmpty()) {
			Oferta of1 = (Oferta) ofertas.elementAt(0);
			aux = new Vector();
			for (int i = 0; i < ofertas.size(); i++) {
				Oferta of2 = (Oferta) ofertas.elementAt(i);

				// concatenacion of1 - of2
				if (of1.getDiaFin().equals(of2.getDiaIni())
						&& of1.getNumCasa() == of2.getNumCasa()) {
					Oferta oferta = new Oferta();
					oferta.setNumCasa(of1.getNumCasa());
					oferta.setDiaIni(of1.getDiaIni());
					oferta.setDiaFin(of2.getDiaFin());
					oferta.setPrecio(of1.getPrecio() + of2.getPrecio());
					aux.add(oferta);

					// concatenacion of2 - of1
				} else if (of2.getDiaFin().equals(of1.getDiaIni())
						&& of1.getNumCasa() == of2.getNumCasa()) {
					Oferta oferta = new Oferta();
					oferta.setNumCasa(of1.getNumCasa());
					oferta.setDiaIni(of2.getDiaIni());
					oferta.setDiaFin(of1.getDiaFin());
					oferta.setPrecio(of1.getPrecio() + of2.getPrecio());
					aux.add(oferta);

				}// if
			}// for
			ofertas.removeElementAt(0);
			ofertas.addAll((Collection) aux);
			resultado.addAll((Collection) aux);
			ofertas.trimToSize();

		}// while
		return resultado;
	}
	/**
	 * Método encargado de realizar la llamada a la base de datos para obtener
	 * entre todas las ofertas de la reserva con código numReserva, 
	 * la fecha de inicio más antigua.
	 * 
	 * @param numReserva
	 * @return Gregorian Calendar
	 */
	  public java.util.GregorianCalendar obtenDiaIniMin (Integer numReserva) throws SQLException{
			return gbd.obtenDiaIniMinBD(numReserva);
		  }  
}