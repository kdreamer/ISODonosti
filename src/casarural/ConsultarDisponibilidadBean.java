package casarural;

import java.rmi.*;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.Vector;
import java.lang.*;

public class ConsultarDisponibilidadBean {
	InterfazFachada logNeg;

	private int numCasa;

	private String diaIni;

	private Date diaIniDate;

	private String diaFin;

	private Date diaFinDate;

	private int numNoches;

	private long numNochesM;

	public ConsultarDisponibilidadBean() {

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
	 * Asigna el numero de noches
	 * 
	 * @param El
	 *            numero de noches
	 * @return Ninguno
	 */
	public void setNumNoches(int s) {
		numNoches = s;
		numNochesM = 1000 * 60 * 60 * 24 * s;
	}

	/**
	 * Devuelve el numero de noches
	 * 
	 * @param Ninguno
	 * @return El numero de noches
	 */
	public int getNumNoches() {
		return numNoches;
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
	 * Devuelve un vector con el dia y el estado de la oferta
	 * 
	 * @param Ninguno
	 * @return El vector de ofertas
	 */
	public Vector getResultado() {
		// Numero de Casa
		// Dia inicio
		final long diams = 1000 * 60 * 60 * 24;
		diaFinDate = new Date(diaIniDate.getTime() + (diams * numNoches));
		Vector res = new Vector();

		try {
			OfertasEnMemoriaPrincipal omp = logNeg.obtenerOfertas(numCasa,
					diaIniDate, diaFinDate);
			Vector ompVectorStrict = omp.obtenerOfertasIncluidasEntre(
					diaIniDate, diaFinDate);
			Vector ompVector = omp.obtenerOfertasEntre(diaIniDate, diaFinDate);
			Date dia = new Date(diaIniDate.getTime());
			dia = Date.valueOf(dia.toString());
			String estadoCasaRural;
			String estadoCasaRuralStrict;

			while (dia.getTime() <= diaFinDate.getTime())// hasta el ultimo
															// dia
			{
				estadoCasaRural = omp.estadoCasaRural(dia, ompVector);
				estadoCasaRuralStrict = omp.estadoCasaRural(dia,
						ompVectorStrict);
				if (estadoCasaRural == estadoCasaRuralStrict) {

				} else if (estadoCasaRural != estadoCasaRuralStrict) {

					Date DiaIniAux = ((Oferta) omp
							.obtenerOfertasEntre(dia, dia).elementAt(0))
							.getDiaIni();
					Date DiaFinAux = ((Oferta) omp
							.obtenerOfertasEntre(dia, dia).elementAt(0))
							.getDiaFin();
					estadoCasaRural = "Libre pero oferta no completa\n  * Disponible del "
							+ DiaIniAux.toString()
							+ " al "
							+ DiaFinAux.toString();
				}
				res.addElement(dia.toString() + ":  " + estadoCasaRural + "\n");
				dia.setTime(dia.getTime() + diams);
			}
			return res;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			e.printStackTrace();
		}
		return res;
	}

}
