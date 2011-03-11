package casarural;

import java.rmi.*;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.Vector;

public class BuscarOfertasBean {

	InterfazFachada logNeg;

	private String diaIni;

	private String diaFin;

	private String precio;

	private String diasMin;

	private String minDormitorios;

	private String minWC;

	private String orden;

	private Date diaIniDate;

	private Date diaFinDate;

	private float precioFloat;

	private int diasMinInt;

	private int minDormitoriosInt;

	private int minWCInt;

	private boolean ordenBoolean;

	public BuscarOfertasBean() {

		final String IPMAQUINA = "localhost";
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
	 * Devuelve el dia de inicio del periodo en el cual buscar ofertas
	 * 
	 * @param Ninguno
	 * @return El dia de inicio
	 */
	public String getDiaIni() {
		return diaIni;
	}

	/**
	 * Asigna el dia de inicio del periodo en el cual buscar ofertas
	 * 
	 * @param El
	 *            dia de inicio
	 * @return Ninguno
	 */
	public void setDiaIni(String diaIni) {
		this.diaIni = diaIni;
		StringTokenizer st = new StringTokenizer(diaIni, "/");
		int dia = Integer.parseInt(st.nextToken());
		int mes = Integer.parseInt(st.nextToken()) - 1;
		int anio = Integer.parseInt(st.nextToken());
		GregorianCalendar gc = new GregorianCalendar(anio, mes, dia);
		diaIniDate = new Date(gc.getTime().getTime());
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
	 * Devuelve el dia de fin del periodo en el cual buscar ofertas
	 * 
	 * @param Ninguno
	 * @return El dia de fin
	 */
	public String getDiaFin() {
		return diaFin;
	}

	/**
	 * Asigna el dia de fin del periodo en el cual buscar ofertas
	 * 
	 * @param El
	 *            dia de fin
	 * @return Ninguno
	 */
	public void setDiaFin(String diaFin) {
		this.diaFin = diaFin;
		StringTokenizer st = new StringTokenizer(diaFin, "/");
		int dia = Integer.parseInt(st.nextToken());
		int mes = Integer.parseInt(st.nextToken()) - 1;
		int anio = Integer.parseInt(st.nextToken());
		GregorianCalendar gc = new GregorianCalendar(anio, mes, dia);
		diaFinDate = new Date(gc.getTime().getTime());
	}

	/**
	 * Devuelve el dia de fin en formato Date
	 * 
	 * @param Ninguno
	 * @return El dia de inicio
	 */
	public Date getDiaFinAsDate() {
		return (this.diaFinDate);
	}

	/**
	 * Devuelve el precio maximo de las ofertas a buscar
	 * 
	 * @param Ninguno
	 * @return El precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * Asigna el precio maximo de las ofertas a buscar
	 * 
	 * @param El
	 *            precio
	 * @return Ninguno
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
		try {
			this.precioFloat = Float.parseFloat(precio);
		} catch (Exception e) {
			this.precioFloat = 0;
		}
	}

	/**
	 * Devuelve el precio maximo de las ofertas a buscar en formato Float
	 * 
	 * @param Ninguno
	 * @return El precio
	 */
	public float getPrecioAsFloat() {
		return precioFloat;
	}

	/**
	 * Devuelve el numero minimo de dias que tienen que tener las ofertas a
	 * buscar
	 * 
	 * @param Ninguno
	 * @return El numero de dias minimo
	 */
	public String getDiasMin() {
		return diasMin;
	}

	/**
	 * Asigna el numero minimo de dias que tienen que tener las ofertas a buscar
	 * 
	 * @param El
	 *            numero de dias minimo
	 * @return Ninguno
	 */
	public void setDiasMin(String diasMin) {
		this.diasMin = diasMin;
		try {
			this.diasMinInt = Integer.parseInt(diasMin);
		} catch (Exception e) {
			this.diasMinInt = 0;
		}
	
	}

	/**
	 * Devuelve el numero minimo de dias que tienen que tener las ofertas a
	 * buscar en formato int
	 * 
	 * @param Ninguno
	 * @return El numero de dias minimo
	 */
	public int getDiasMinAsInt() {
		return diasMinInt;
	}

	/**
	 * Devuelve el numero minimo de dormitorios que tienen que tener las casas
	 * rurales de las ofertas a buscar
	 * 
	 * @param Ninguno
	 * @return El numero de dormitorios minimo
	 */
	public String getMinDormitorios() {
		return minDormitorios;
	}

	/**
	 * Asigna el numero minimo de dormitorios que tienen que tener las casas
	 * rurales de las ofertas a buscar
	 * 
	 * @param El
	 *            numero de dormitorios minimo
	 * @return Ninguno
	 */
	public void setMinDormitorios(String minDormitorios) {
		this.minDormitorios = minDormitorios;
		try {
			this.minDormitoriosInt = Integer.parseInt(minDormitorios);
		} catch (Exception e) {
			this.minDormitoriosInt = 0;
		}
	}

	/**
	 * Devuelve el numero minimo de dormitorios que tienen que tener las casas
	 * rurales de las ofertas a buscar en formato int
	 * 
	 * @param Ninguno
	 * @return El numero de dormitorios minimo
	 */
	public int getMinDormitoriosAsInt() {
		return minDormitoriosInt;
	}

	/**
	 * Devuelve el numero minimo de baños que tienen que tener las casas rurales
	 * de las ofertas a buscar
	 * 
	 * @param Ninguno
	 * @return El numero de baños minimo
	 */
	public String getMinWC() {
		return minWC;
	}

	/**
	 * Asigna el numero minimo de baños que tienen que tener las casas rurales
	 * de las ofertas a buscar
	 * 
	 * @param El
	 *            numero de baños minimo
	 * @return Ninguno
	 */
	public void setMinWC(String minWC) {
		this.minWC = minWC;
		try {
			this.minWCInt = Integer.parseInt(minWC);
		} catch (Exception e) {
			this.minWCInt = 0;
		}
	}

	/**
	 * Devuelve el numero minimo de baños que tienen que tener las casas rurales
	 * de las ofertas a buscar en formato int
	 * 
	 * @param Ninguno
	 * @return El numero de baños minimo
	 */
	public int getMinWCAsInt() {
		return minWCInt;
	}

	/**
	 * Devuelve el criterio de ordenacion de las ofertas a buscar: - 1 -->
	 * Precio - 2 --> Numero de dias
	 * 
	 * @param Ninguno
	 * @return El criterio de ordenacion
	 */
	public String getOrden() {
		return orden;
	}

	/**
	 * Asigna el criterio de ordenacion de las ofertas a buscar: - 1/True -->
	 * Precio - 2/False --> Numero de dias
	 * 
	 * @param El
	 *            criterio de ordenacion
	 * @return Ninguno
	 */
	public void setOrden(String orden) {
		this.orden = orden;
		switch (Integer.parseInt(orden)) {
		case 1:
			this.ordenBoolean = true;
			break;
		case 2:
			this.ordenBoolean = false;
			break;
		default:
			this.ordenBoolean = true;
			break;
		}
	}

	/**
	 * Devuelve el criterio de ordenacion de las ofertas a buscar: - True -->
	 * Precio - False --> Numero de dias
	 * 
	 * @param Ninguno
	 * @return El criterio de ordenacion
	 */
	public boolean getOrdenAsBoolean() {
		return ordenBoolean;
	}

	/**
	 * Devuelve las ofertas encontradas segun los criterios seleccionados
	 * 
	 * @param Ninguno
	 * @return Las ofertas
	 */
	public Vector getOfertas() {

		Vector res = new Vector();

		try {
			res = logNeg.buscarOfertas(diaIniDate, diaFinDate, precioFloat,
					diasMinInt, minDormitoriosInt, minWCInt, ordenBoolean);
			return res;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			e.printStackTrace();
		}
		return res;
	}

}
