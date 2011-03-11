package casarural;

import java.sql.*;
import java.rmi.*;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

public class MejorOfertaBean {
	InterfazFachada logNeg;

	private int numCasa;

	private String diaIni;

	private Date diaIniDate;

	private String diaFin;

	private Date diaFinDate;

	private int habitaciones;

	private int banos;

	private String criterio;

	public MejorOfertaBean() {
		final String IPMAQUINA = "localhost";
		// final String DIRECTORIOCLASES= "/ISOServidor/";
		//
		// System.setProperty ("java.rmi.server.codebase",
		// "http://"+IPMAQUINA+DIRECTORIOCLASES);
		// Aqui realizamos las operaciones necesarias para trabajar
		// mediante RMI
		try {
			// Nombre servicio remoto
			String servicio = "/CasaRural";
			System.setSecurityManager(new RMISecurityManager());
			// Numero puerto servidor RMI
			int numPuerto = InterfazFachada.numPuerto;
			// IP maquina servidor RMI
			String maquina = IPMAQUINA;
			logNeg = (InterfazFachada) Naming.lookup("rmi://" + maquina + ":"
					+ numPuerto + servicio);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Asigna el codigo de la casa al de la instancia actual
	 * 
	 * @param El
	 *            codigo de la casa
	 * @return Ninguno
	 */
	public void setNumCasa(int numCasa) {
		this.numCasa = numCasa;
	}

	/**
	 * Devuelve el codigo de la casa
	 * 
	 * @param Ninguno
	 * @return El codigo de la casa
	 */
	public int getNumCasa() {
		return numCasa;
	}

	/**
	 * Asigna el dia de inicio de la reserva al de la instancia actual
	 * 
	 * @param El
	 *            dia de inicio de la reserva
	 * @return Ninguno
	 */
	public void setDiaIni(String i) {
		diaIni = i;
		StringTokenizer st = new StringTokenizer(i, "/");
		int dia = Integer.parseInt(st.nextToken());
		int mes = Integer.parseInt(st.nextToken()) - 1;
		int anio = Integer.parseInt(st.nextToken());
		GregorianCalendar gc = new GregorianCalendar(anio, mes, dia);
		diaIniDate = new Date(gc.getTime().getTime());
	}

	/**
	 * Devuelve el dia de inicio de la reserva
	 * 
	 * @param Ninguno
	 * @return El dia de inicio de la reserva
	 */
	public String getDiaIni() {
		return diaIni;
	}

	/**
	 * Asigna el dia final de la reserva al de la instancia actual
	 * 
	 * @param El
	 *            dia de fin de la reserva
	 * @return Ninguno
	 */
	public void setDiaFin(String i) {
		diaFin = i;
		StringTokenizer st = new StringTokenizer(i, "/");
		int dia = Integer.parseInt(st.nextToken());
		int mes = Integer.parseInt(st.nextToken()) - 1;
		int anio = Integer.parseInt(st.nextToken());
		GregorianCalendar gc = new GregorianCalendar(anio, mes, dia);
		diaFinDate = new Date(gc.getTime().getTime());
	}

	/**
	 * Devuelve el dia de fin de la reserva
	 * 
	 * @param Ninguno
	 * @return El dia de fin de la reserva
	 */
	public String getDiaFin() {
		return diaFin;
	}

	/**
	 * Devuelve el dia de inicio en formato Date
	 * 
	 * @param Ninguno
	 * @return El dia de inicio
	 */
	public Date getDiaIniAsDate() {
		return (this.diaIniDate);
	}

	/**
	 * Devuelve el dia de salida en formato Date
	 * 
	 * @param Ninguno
	 * @return El dia de salida
	 */
	public Date getDiaFinAsDate() {
		return (this.diaFinDate);
	}

	/**
	 * Asigna el numero de habitaciones a la instancia actual
	 * 
	 * @param numero
	 *            de habitaciones
	 * @return Ninguno
	 */
	public void setHabitaciones(int numHabs) {
		this.habitaciones = numHabs;
	}

	/**
	 * Devuelve el numero de habitaciones
	 * 
	 * @param Ninguno
	 * @return El numero de habitaciones
	 */
	public int getHabitaciones() {
		return habitaciones;
	}

	/**
	 * Asigna el numero de baños a la instancia actual
	 * 
	 * @param numero
	 *            de baños
	 * @return Ninguno
	 */
	public void setBanos(int numBanos) {
		this.banos = numBanos;
	}

	/**
	 * Devuelve el numero de baños
	 * 
	 * @param Ninguno
	 * @return El numero de baños
	 */
	public int getBanos() {
		return banos;
	}

	/**
	 * Asigna el criterio
	 * 
	 * @param Criterio
	 * @return
	 */
	public void setCriterio(String criterio) {
		this.criterio = criterio;
	}

	/**
	 * Devuelve el criterio
	 * 
	 * @param Ninguno
	 * @return El criterio
	 */
	public String getCriterio() {
		return this.criterio;
	}

	public Vector getResultado() {
		try {

			Oferta of = logNeg.obtenerMejorOferta(diaIniDate, diaFinDate,
					habitaciones, banos, criterio);
			Vector v = new Vector();
			v.add(new Integer(of.getNumCasa()));
			v.add(new Float(of.getPrecio()));
			v.add(new Integer(of.getTamano()));
			this.setNumCasa(of.getNumCasa());
			return v;
		} catch (Exception ex) {
			return null;
		}

	}
}