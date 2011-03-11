package casarural;

import java.util.Vector;
import java.sql.Date;
import java.util.Enumeration;
import java.io.*;

import excepciones.NoSePuedeReservarException;

public class OfertasEnMemoriaPrincipal implements Serializable {
	private Vector ofertas = new Vector();

	public OfertasEnMemoriaPrincipal() {
	}

	/**
	 * Añade una reserva al objeto OfertasEnMemoriaPrincipal
	 * 
	 * @param dia
	 *            de inicio, dia de fin, numero de oferta y precio
	 * @return Nada
	 */
	public void anadirReserva(java.sql.Date diaIni, java.sql.Date diaFin,
			String numOferta, float precio) {
		try {
			Oferta ofert = new Oferta();
			ofert.setDiaIni(diaIni);
			ofert.setDiaFin(diaFin);
			ofert.setNumOferta(numOferta);
			ofert.setPrecio(precio);
			this.ofertas.addElement(ofert);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Añade una reserva al objeto OfertasEnMemoriaPrincipal
	 * 
	 * @param dia
	 *            de inicio, dia de fin, numero de oferta, precio y numero de
	 *            reserva
	 * @return Nada
	 */
	public void anadirReserva(java.sql.Date diaIni, java.sql.Date diaFin,
			String numOferta, float precio, String numReserva) {
		try {
			Oferta ofert = new Oferta();
			ofert.setDiaIni(diaIni);
			ofert.setDiaFin(diaFin);
			ofert.setNumOferta(numOferta);
			ofert.setPrecio(precio);
			ofert.setNumReserva(numReserva);
			this.ofertas.addElement(ofert);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene el conjunto de ofertas que se encuentran entre diaIni y diaFin
	 * 
	 * @param dia
	 *            de inicio y dia de fin
	 * @return Vector de objetos de la clase Oferta
	 */
	public Vector obtenerOfertasIncluidasEntre(java.sql.Date diaIni,
			java.sql.Date diaFin) {
		Vector vector = new Vector();
		try {
			java.sql.Date dateAuxIni;
			java.sql.Date dateAuxFin;
			String numReserva;
			Oferta oferta;
			Enumeration e = this.ofertas.elements();
			while (e.hasMoreElements()) {
				oferta = (Oferta) e.nextElement();
				dateAuxIni = oferta.getDiaIni();
				dateAuxFin = oferta.getDiaFin();
				numReserva = oferta.getNumReserva();

				if ((dateAuxIni.after(diaIni) || dateAuxIni.equals(diaIni))
						&& (dateAuxFin.before(diaFin) || dateAuxFin
								.equals(diaFin)) && (numReserva == null)) {
					Oferta ofert = new Oferta();
					ofert.setNumOferta(oferta.getNumOferta());
					ofert.setDiaIni(oferta.getDiaIni());
					ofert.setDiaFin(oferta.getDiaFin());
					ofert.setNumReserva(oferta.getNumReserva());
					ofert.setPrecio(oferta.getPrecio());
					vector.addElement(ofert);
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return vector;
	}

	/**
	 * Obtiene la oferta que incluye el diaIni en su periodo de oferta
	 * 
	 * @param dia
	 *            de inicio y dia de fin
	 * @return el numero de oferta
	 */
	public String obtenerOfertaAnteriorAYSolapadaCon(java.sql.Date diaIni,
			java.sql.Date diaFin) {
		try {
			java.sql.Date dateAuxIni;
			java.sql.Date dateAuxFin;
			Oferta oferta;
			Enumeration e = this.ofertas.elements();
			while (e.hasMoreElements()) {
				oferta = (Oferta) e.nextElement();
				dateAuxIni = oferta.getDiaIni();
				dateAuxFin = oferta.getDiaFin();
				if ((dateAuxIni.before(diaIni) || dateAuxIni.equals(diaIni))
						&& (dateAuxFin.after(diaIni) || dateAuxFin
								.equals(diaIni))) {
					return oferta.getNumOferta();
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return "cero";
	}

	/**
	 * Obtiene la oferta que incluye el diaFin en su periodo de oferta
	 * 
	 * @param dia
	 *            de inicio y dia de fin
	 * @return el numero de oferta
	 */
	public String obtenerOfertaPosteriorAYSolapadaCon(java.sql.Date diaIni,
			java.sql.Date diaFin) {
		try {
			java.sql.Date dateAuxIni;
			java.sql.Date dateAuxFin;
			Oferta oferta;
			Enumeration e = this.ofertas.elements();
			while (e.hasMoreElements()) {
				oferta = (Oferta) e.nextElement();
				dateAuxIni = oferta.getDiaIni();
				dateAuxFin = oferta.getDiaFin();
				if ((dateAuxIni.before(diaFin) || dateAuxIni.equals(diaFin))
						&& (dateAuxFin.after(diaFin) || dateAuxFin
								.equals(diaFin))) {
					return oferta.getNumOferta();
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return "cero";
	}

	/**
	 * Nos dice si tiene almacenada alguna oferta que esta reservada
	 * 
	 * @param Nada
	 * @return ¿Hay alguna oferta reservada?
	 */
	public boolean existeOfertaReservada() {
		Oferta oferta;
		Enumeration e = this.ofertas.elements();
		while (e.hasMoreElements()) {
			oferta = (Oferta) e.nextElement();
			if (!(oferta.getNumReserva() == null))
				return true;
		}
		return false;
	}

	/**
	 * Obtiene la el conjunto de ofertas que componen la reserva completa
	 * 
	 * @param dia
	 *            de inicio y dia de fin
	 * @return Vector de objetos de la clase ReservaCompleta
	 */
	public Vector obtenerReservaCompleta(java.sql.Date diaIni,
			java.sql.Date diaFin) throws NoSePuedeReservarException {
		Vector vector = new Vector();
		java.sql.Date dateAuxIni;
		java.sql.Date dateAuxFin;
		String numReserva;
		Oferta oferta;
		java.sql.Date diaActual = new java.sql.Date(System.currentTimeMillis());
		int anoActual = diaActual.getYear() + 1900;
		Integer anoActualAux = new Integer(anoActual + 10);
		java.sql.Date minDiaIni = java.sql.Date.valueOf(anoActualAux.toString()
				+ "-12-31");
		java.sql.Date maxDiaFin = new java.sql.Date(
				System.currentTimeMillis() - 1000 * 3600 * 24);
		Enumeration e = this.ofertas.elements();
		while (e.hasMoreElements()) {
			oferta = (Oferta) e.nextElement();
			dateAuxIni = oferta.getDiaIni();
			dateAuxFin = oferta.getDiaFin();
			numReserva = oferta.getNumReserva();
			if ((dateAuxFin.after(diaIni) || dateAuxFin.equals(diaIni))
					&& (dateAuxIni.before(diaFin) || dateAuxIni.equals(diaFin))
					&& (numReserva == null)) {
				if (dateAuxFin.after(maxDiaFin))
					maxDiaFin = (java.sql.Date) dateAuxFin.clone();
				if (dateAuxIni.before(minDiaIni))
					minDiaIni = (java.sql.Date) dateAuxIni.clone();

				ReservaCompleta resComp = new ReservaCompleta();
				resComp.setNumOferta(oferta.getNumOferta());
				resComp.setPrecio(oferta.getPrecio());
				vector.addElement(resComp);
			}
		}
		if (!vector.elements().hasMoreElements())
			throw new NoSePuedeReservarException(
					"La casa no se encuentra disponible estos dias");
		if (minDiaIni.after(diaIni))
			throw new NoSePuedeReservarException(
					"La casa solo esta disponible a partir del dia "
							+ minDiaIni);
		if (maxDiaFin.before(diaFin))
			throw new NoSePuedeReservarException(
					"La casa solo esta disponible hasta el dia " + maxDiaFin);
		return vector;

	}

	/**
	 * Obtiene el conjunto de ofertas que desde la que incluye a diaIni hasta la
	 * que incluye a diaFin
	 * 
	 * @param dia
	 *            de inicio y dia de fin
	 * @return Vector de objetos de la clase Oferta
	 */
	public Vector obtenerOfertasEntre(java.sql.Date diaIni, java.sql.Date diaFin) {
		Vector vector = new Vector();
		try {
			java.sql.Date dateAuxIni;
			java.sql.Date dateAuxFin;
			String numReserva;
			Oferta oferta;
			Enumeration e = this.ofertas.elements();
			while (e.hasMoreElements()) {
				oferta = (Oferta) e.nextElement();
				dateAuxIni = oferta.getDiaIni();
				dateAuxFin = oferta.getDiaFin();
				numReserva = oferta.getNumReserva();

				if ((diaIni.after(dateAuxIni) || diaIni.equals(dateAuxIni))
						&& (diaIni.before(dateAuxFin) || diaIni
								.equals(dateAuxFin)) && (numReserva == null)) {
					Oferta ofert = new Oferta();
					ofert.setNumOferta(oferta.getNumOferta());
					ofert.setDiaIni(oferta.getDiaIni());
					ofert.setDiaFin(oferta.getDiaFin());
					ofert.setNumReserva(oferta.getNumReserva());
					ofert.setPrecio(oferta.getPrecio());
					vector.addElement(ofert);
				} else if ((dateAuxIni.after(diaIni) || dateAuxIni
						.equals(diaIni))
						&& (dateAuxFin.before(diaFin) || dateAuxFin
								.equals(diaFin)) && (numReserva == null)) {
					Oferta ofert = new Oferta();
					ofert.setNumOferta(oferta.getNumOferta());
					ofert.setDiaIni(oferta.getDiaIni());
					ofert.setDiaFin(oferta.getDiaFin());
					ofert.setNumReserva(oferta.getNumReserva());
					ofert.setPrecio(oferta.getPrecio());
					vector.addElement(ofert);
				} else if ((diaFin.after(dateAuxIni) || diaFin
						.equals(dateAuxIni))
						&& (diaFin.before(dateAuxFin) || diaFin
								.equals(dateAuxFin)) && (numReserva == null)) {
					Oferta ofert = new Oferta();
					ofert.setNumOferta(oferta.getNumOferta());
					ofert.setDiaIni(oferta.getDiaIni());
					ofert.setDiaFin(oferta.getDiaFin());
					ofert.setNumReserva(oferta.getNumReserva());
					ofert.setPrecio(oferta.getPrecio());
					vector.addElement(ofert);
				}

			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		return vector;
	}

	/**
	 * Devuelve el estado de la casa rural en un dia concreto
	 * 
	 * @param dia
	 * @return String con el estado en el que se encuentra la casa
	 */
	public String estadoCasaRural(java.sql.Date dia, Vector ompVector) {
		// ///////////////////////////////////////////////////////////////
		// /////////////se mira si el dia concreto se encuentra dentro de los
		// plazos para la reserva//
		Vector ompVectorAux = new Vector();
		ompVectorAux = ompVector;
		try {
			java.sql.Date dateAuxIni;
			java.sql.Date dateAuxFin;
			Oferta oferta;
			Enumeration e = ompVectorAux.elements();
			while (e.hasMoreElements()) {
				oferta = (Oferta) e.nextElement();
				dateAuxIni = oferta.getDiaIni();
				dateAuxFin = oferta.getDiaFin();

				if ((dateAuxIni.before(dia) || dateAuxIni.equals(dia))
						&& (dateAuxFin.after(dia) || dateAuxFin.equals(dia))) {// ///////si
					// el
					// dia
					// esta
					// dentro
					// del
					// plazo
					// posible
					// para
					// la
					// reserva,
					// devolvemos
					// estado
					// libre///
					return "libre";
				}
			}
		} catch (Exception exc) {
			exc.printStackTrace();
		}

		// /////al salir del bucle no estara libre y devolveremos reservada////
		return "reservada";
	}

	/**
	 * Devuelve todas las ofertas
	 * 
	 * @return Las ofertas
	 */
	public Vector getOfertas() {
		return ofertas;
	}

}