package accesoDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Vector;

import configuracion.Config;

import casarural.Casa;
import casarural.Oferta;
import casarural.Propietario;
import casarural.Recorrido;
import casarural.Servicio;
import excepciones.NoSePuedeReservarException;

// TODO: Auto-generated Javadoc
/**
 * The Class GestorBD.
 *
 * @author  kdreamer
 */
public final class GestorBD {

	/** The conf. @uml.property  name="conf" @uml.associationEnd */
	private static Config conf = Config.getInstance();
	
	/** The Constant FUENTE_DATOS. */
	public static final String FUENTE_DATOS = conf.getFuenteDatos();
	
	/** The Constant SERVER. */
	public static final String SERVER = conf.getServerBD();
	
	/** The Constant PROTOCOLO. */
	public static final String PROTOCOLO = conf.getProtocoloBD();
	
	/** The Constant DRIVER. */
	public static final String DRIVER = conf.getDriverBD();
	
	/** The Constant SERVERRMI. */
	public static final String	SERVERRMI= conf.getServerRMI();
	//private static final String URL = PROTOCOLO + "://" + SERVER + "/" + FUENTE_DATOS;
	/** The Constant URL. */
	private static final String URL = PROTOCOLO + ":"+ "//" +SERVER +"/" + FUENTE_DATOS;
	
	/** The Constant USER. */
	private static final String USER = conf.getUserBD();
	
	/** The Constant PASS. */
	private static final String PASS = conf.getPassBD();
	
	/** The el gestor bd. @uml.property  name="elGestorBD" @uml.associationEnd */
	private static GestorBD elGestorBD;

	/** The c. */
	private Connection c;
	
	/** The s. */
	private Statement s;

	/**
	 * Constructor.
	 *
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	private GestorBD() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		try {
			Class.forName(DRIVER).newInstance();
			c = DriverManager.getConnection(URL, USER, PASS);
			c.setAutoCommit(true);
			s = c.createStatement();
	} catch (Exception ex) {
			System.out
					.println("Error realizando la conexion con la base de datos: "
							+ FUENTE_DATOS + "\n\t" + ex.toString());
			
		}
	}

	/**
	 * Devuelve una instancia del gestor de la BD.
	 *
	 * @return GestorBD
	 * @throws InstantiationException the instantiation exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ClassNotFoundException the class not found exception
	 * @throws SQLException the sQL exception
	 */
	public static GestorBD getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (elGestorBD == null)
			elGestorBD = new GestorBD();
		return elGestorBD;
	}

	/**
	 * Selecciona reservas entre las fechas que se le indican.
	 *
	 * @param diaIni the dia ini
	 * @param diaFin the dia fin
	 * @param numCasa the num casa
	 * @return Vector de objetos Oferta
	 * @throws NoSePuedeReservarException the no se puede reservar exception
	 */
	public Vector seleccionarReservas(java.sql.Date diaIni,
			java.sql.Date diaFin, int numCasa)
			throws NoSePuedeReservarException

	{
		Vector vectorReservas = new Vector();
		// try{String consulta="SELECT NumOferta,DiaIni,DiaFin,Precio FROM
		// Oferta WHERE NumReserva is null AND NumCasa = "+numCasa+" AND DiaFin
		// >=#"+diaIni+"# AND DiaIni <= #"+diaFin+"#";
		try {
			String consulta = "SELECT NumOferta,DiaIni,DiaFin,Precio FROM oferta WHERE NumReserva is null AND  NumCasa = "
					+ numCasa
					+ " AND DiaFin >='"
					+ diaIni
					+ "' AND DiaIni <= '" + diaFin + "'";
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) {
				String numeroOferta = rs.getString("NumOferta");
				java.sql.Date diaFinal = rs.getDate("DiaFin");
				java.sql.Date diaInicio = rs.getDate("DiaIni");
				float precio = rs.getFloat("Precio");
				Oferta res = new Oferta();
				res.setNumOferta(numeroOferta);
				res.setDiaIni(diaInicio);
				res.setDiaFin(diaFinal);
				res.setPrecio(precio);
				vectorReservas.addElement(res);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!(vectorReservas.elements()).hasMoreElements())
			throw new NoSePuedeReservarException(
					"La casa no se encuentra disponible estos dias");
		return vectorReservas;
	}

	/**
	 * Dado un numero de casa selecciona al propietario de esa casa.
	 *
	 * @param numCasa the num casa
	 * @return El propietario
	 */
	public Propietario seleccionarPropietario(int numCasa)

	{
		Propietario p = new Propietario();
		try {
			// String consulta = "SELECT P.NumCuentaCorriente FROM Propietario
			// P, CasaRural C WHERE C.Propietario=P.CuentaSistema AND C.NumCasa
			// ="+numCasa;
			String consulta = "SELECT P.NumCuentaCorriente FROM propietario P, casarural C WHERE C.Propietario=P.CuentaSistema AND C.NumCasa ="
					+ numCasa;
			ResultSet rs = s.executeQuery(consulta);
			if (rs.next()) {
				String str = new String(rs.getString("NumCuentaCorriente"));
				p.setNumCuentaCorriente(str);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return p;
	}

	/**
	 * Devuelve las casas dado un numero de cuenta de propietario.
	 *
	 * @param cuentaSistema the cuenta sistema
	 * @return Vector de objetos de la clase Casa
	 */
	public Vector seleccionarCasas(String cuentaSistema)

	{
		Vector vectorCasas = new Vector();
		try {

			// String consulta="SELECT NumCasa FROM CasaRural WHERE
			// Propietario='"+cuentaSistema+"'";
			String consulta = "SELECT NumCasa FROM casarural WHERE Propietario='"
					+ cuentaSistema + "'";
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) {
				int nc = rs.getInt("NumCasa");
				Casa ca = new Casa();
				ca.setNumCasa(nc);
				vectorCasas.addElement(ca);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return vectorCasas;
	}

	/**
	 * Devuelve  todas las casas.
	 *
	 * @return Vector de objetos de la clase Casa
	 */
	public Vector seleccionarCasas()

	{
		Vector vectorCasas = new Vector();
		try {

			// String consulta="SELECT NumCasa FROM CasaRural";
			String consulta = "SELECT NumCasa FROM casarural";
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) {
				int nc = rs.getInt("NumCasa");
				Casa ca = new Casa();
				ca.setNumCasa(nc);
				vectorCasas.addElement(ca);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return vectorCasas;
	}

	/**
	 * Devuelve el resultado de las ofertas que interesan, según las
	 * restricciones de fechas, precio y demás, que opcionalmente pueda haber
	 * introducido.
	 * 
	 * Para la realización de la consulta, se ha decidido generarla a medida
	 * que se descubren los parámetros introducidos por el usuario. De esta
	 * manera, puede capturarse cualquiera de las posibilidades introducidas por
	 * el usuario, de una vez en una sola consulta.
	 *
	 * @param numDormitorios the num dormitorios
	 * @param numBanos the num banos
	 * @param diaIni the dia ini
	 * @param diaFin the dia fin
	 * @param precio the precio
	 * @param numMinDias the num min dias
	 * @param orden the orden
	 * @return Vector, un vector con las ofertas interesantes para el usuario.
	 */
	public Vector seleccionarCasasDorWC(int numDormitorios, int numBanos,
			java.sql.Date diaIni, java.sql.Date diaFin, float precio,
			int numMinDias, boolean orden)

	{

		String banos = "";
		String dormi = "";
		String dias = "";
		String sOrden = "";
		String precioMax = "";

		/*
		 * En caso de que el usuario haya añadido algún número mínimo de
		 * dormitorios, dicha restricción se añade a la consulta. Para ello,
		 * creamos la restricción.
		 */
		if (numDormitorios != 0) {
			dormi = " AND NumDormitorios >= " + numDormitorios;
		}
		// float Fdias = ((diaFin.getTime() - diaIni.getTime())/(1000*3600*24));
		// int numDias = (int) Fdias;

		/*
		 * En caso de que el usuario haya añadido algún número mínimo de
		 * baños, dicha restricción se añade a la consulta. Para ello,
		 * creamos la restricción.
		 */
		if (numBanos != 0) {
			banos = " AND NumBaños >=" + numBanos;
		}
		/*
		 * En caso de que el usuario haya añadido un precio máximo, dicha
		 * restricción se añade a la consulta. Para ello, creamos la
		 * restricción.
		 */
		if (precio != 0) {
			precioMax = " AND Precio <=" + precio;
		}

		/*
		 * Se calcula el número de días, para comparar la duración de la
		 * estancia dentro de la misma consulta.
		 */
		if (numMinDias != 0) {
			dias = " AND ((DiaFin - DiaIni)/1000000) >=" + numMinDias;
		}

		/*
		 * En función de lo que escoja el usuario, se ordenará el resultado de
		 * la consulta.
		 */
		if (orden) {
			sOrden = " ORDER BY Precio ASC";
		} else {
			sOrden = " ORDER BY numDias DESC";
		}
		// precio,diafin,diaini,numcasa,numoferta
		String consulta = "SELECT *, ((DiaFin - DiaIni)/1000000) as numDias FROM casarural "
				+ " natural join oferta WHERE numreserva is null "
				+ banos
				+ dormi
				+ " AND DiaIni >= '"
				+ diaIni
				+ "' AND Diafin <= '"
				+ diaFin + "' " + precioMax + dias;
		Vector ofertas = new Vector();
		// Creamos la consulta resultante de la unión de todas las piezas.
		try {
			consulta = consulta + sOrden;
			System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);
			// Se crea la oferta resultado, y se pasa al vector de ofertas.
			while (rs.next()) {
				Oferta of = new Oferta();
				of.setPrecio(rs.getFloat("precio"));
				of.setDiaFin(rs.getDate("diafin"));
				of.setDiaIni(rs.getDate("diaini"));
				of.setNumCasa(rs.getInt("NumCasa"));
				Integer i = new Integer(rs.getInt("Numoferta"));
				of.setNumOferta(i.toString());
				ofertas.add(of);
			}

		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return ofertas;
	}

	/**
	 * Fecha devuelve una fecha tomada de formato tres datos en formato
	 * numérico.
	 *
	 * @param year the year
	 * @param month the month
	 * @param day the day
	 * @return Date, la misma fecha en formato fecha (clase Date).
	 */
	private static java.sql.Date fecha(int year, int month, int day) {
		return new java.sql.Date((new GregorianCalendar(year, month - 1, day))
				.getTime().getTime());
	}

	/**
	 * Selecciona las ofertas entre las fechas dadas para un numero de casa.
	 *
	 * @param diaIni the dia ini
	 * @param diaFin the dia fin
	 * @param numCasa the num casa
	 * @return Vector de objetos de la clase Oferta
	 */
	public Vector seleccionarOfertas(java.sql.Date diaIni,
			java.sql.Date diaFin, int numCasa)

	{
		Vector vectorOfertas = new Vector();
		try {
			// String consulta ="SELECT
			// NumOferta,DiaIni,DiaFin,Precio,NumReserva FROM OFERTA WHERE
			// DiaFin >=#"+diaIni+"# AND DiaIni <= #"+diaFin+"# AND NumCasa =
			// "+numCasa;
			// String consulta ="SELECT
			// NumOferta,DiaIni,DiaFin,Precio,NumReserva FROM OFERTA WHERE
			// DiaFin >=#"+diaFin+"# AND DiaIni <= #"+diaIni+"# AND DiaFin
			// >=#"+diaIni+"# AND DiaIni <= #"+diaFin+"# AND NumCasa =
			// "+numCasa;
			// String consulta ="SELECT
			// NumOferta,DiaIni,DiaFin,Precio,NumReserva FROM OFERTA WHERE
			// DiaFin =#"+diaFin+"# AND DiaIni = #"+diaIni+"# AND NumCasa =
			// "+numCasa;

			String consulta = "SELECT NumOferta,DiaIni,DiaFin,Precio,NumReserva FROM oferta WHERE DiaFin >='"
					+ diaFin
					+ "' AND DiaIni <= '"
					+ diaIni
					+ "' AND DiaFin >='"
					+ diaIni
					+ "' AND DiaIni <= '"
					+ diaFin + "' AND NumCasa = " + numCasa;
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) {
				String nOferta = rs.getString("NumOferta");
				java.sql.Date dIni = rs.getDate("DiaIni");
				java.sql.Date dFin = rs.getDate("DiaFin");
				float pr = rs.getFloat("Precio");
				String nRes = rs.getString("NumReserva");
				Oferta of = new Oferta();
				of.setDiaFin(dFin);
				of.setDiaIni(dIni);
				of.setNumOferta(nOferta);
				of.setNumReserva(nRes);
				of.setPrecio(pr);
				vectorOfertas.addElement(of);

			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}

		return vectorOfertas;
	}

	
	
	////////////////////////////////////
	/**
	 * Realiza la reserva en forma de transaccion.
	 *
	 * @param reservasTotales the reservas totales
	 * @param numReserva the num reserva
	 * @param numTfnoReserva the num tfno reserva
	 * @param precioTotal the precio total
	 * @return Ninguno
	 */
	public void transaccionDeReserva(Vector reservasTotales, String numReserva,
			String numTfnoReserva, float precioTotal) {
		try {

			// Connection
			// d=DriverManager.getConnection("jdbc:odbc:BDCasasRurales");
			//Connection d = DriverManager.getConnection(URL, USER, PASS);
			c.setAutoCommit(false);
			Statement f = c.createStatement();
			java.sql.Date diadehoy = new java.sql.Date(System
					.currentTimeMillis());
			// String consulta1 ="INSERT INTO
			// RESERVA(NumReserva,Pagado,Fecha,NumTfno,PrecioTotal) VALUES
			// ('"+numReserva+"',no,#"+diadehoy+"#,'"+numTfnoReserva+"',"+precioTotal+")";
			String consulta1 = "INSERT INTO reserva(NumReserva,Pagado,Fecha,NumTfno,PrecioTotal) VALUES ('"
					+ numReserva
					+ "',no,'"
					+ diadehoy
					+ "','"
					+ numTfnoReserva
					+ "'," + precioTotal + ")";
			f.executeUpdate(consulta1);
			Enumeration enumera = reservasTotales.elements();
			while (enumera.hasMoreElements()) {
				String nOferta = (String) enumera.nextElement();
				// String consulta = "UPDATE OFERTA SET NumReserva
				// ='"+numReserva+"' WHERE NumOferta
				// ="+Integer.valueOf(nOferta).intValue();
				String consulta = "UPDATE oferta SET NumReserva ='"
						+ numReserva + "' WHERE NumOferta ="
						+ Integer.valueOf(nOferta).intValue();
				s.executeUpdate(consulta);
			}
			c.commit();
			c.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}

	/**
	 * Realiza una trasaccion de ofertas.
	 *
	 * @param todasLasOfertasIncluidas the todas las ofertas incluidas
	 * @param diaIni the dia ini
	 * @param diaFin the dia fin
	 * @param numOfePrimera the num ofe primera
	 * @param numOfeUltima the num ofe ultima
	 * @param precio the precio
	 * @param numCasa the num casa
	 * @return Ninguno
	 */
	public void transaccionDeOfertas(Vector todasLasOfertasIncluidas,
			java.sql.Date diaIni, java.sql.Date diaFin, String numOfePrimera,
			String numOfeUltima, float precio, int numCasa) {
		try {
			// Connection
			// d=DriverManager.getConnection("jdbc:odbc:BDCasasRurales");
//			Connection d = DriverManager.getConnection(URL, USER, PASS);
			c.setAutoCommit(false);
			Statement f = c.createStatement();
			String consulta;
			Enumeration enumera = todasLasOfertasIncluidas.elements();
			if (!numOfePrimera.equals(numOfeUltima)) {
				if (!numOfePrimera.equals("cero")) {
					// String consulta1="SELECT Precio,DiaIni,DiaFin FROM OFERTA
					// WHERE
					// NumOferta="+Integer.valueOf(numOfePrimera).intValue();
					String consulta1 = "SELECT Precio,DiaIni,DiaFin FROM oferta WHERE NumOferta="
							+ Integer.valueOf(numOfePrimera).intValue();
					ResultSet rs1 = f.executeQuery(consulta1);
					rs1.next();
					float prPrim = rs1.getFloat("Precio");
					java.sql.Date dIniPrim = rs1.getDate("DiaIni");
					java.sql.Date dFinPrim = rs1.getDate("DiaFin");
					int numeroDiasPrim = (int) ((dFinPrim.getTime() - dIniPrim
							.getTime()) / (1000 * 3600 * 24)) + 1;
					float precioPorDiaPrim = prPrim / (int) numeroDiasPrim;
					int numDiasAntes = (int) ((diaIni.getTime() - dIniPrim
							.getTime()) / (1000 * 3600 * 24));
					int nuevoPrecioPrim = (int) (numDiasAntes * precioPorDiaPrim);
					java.sql.Date diaIniNuevo = new java.sql.Date(diaIni
							.getTime() - 1000 * 3600 * 24);
					if (diaIniNuevo.getTime() >= dIniPrim.getTime()) {
						// String consultaA1 = "UPDATE OFERTA SET DiaFin =
						// #"+diaIniNuevo+"#, Precio = "+nuevoPrecioPrim+" WHERE
						// NumOferta
						// ="+Integer.valueOf(numOfePrimera).intValue();
						String consultaA1 = "UPDATE oferta SET DiaFin = '"
								+ diaIniNuevo + "', Precio = "
								+ nuevoPrecioPrim + " WHERE NumOferta ="
								+ Integer.valueOf(numOfePrimera).intValue();
						f.executeUpdate(consultaA1);
					} else {
						// String consultaA3 = "DELETE FROM OFERTA WHERE
						// NumOferta
						// ="+Integer.valueOf(numOfePrimera).intValue();
						String consultaA3 = "DELETE FROM oferta WHERE NumOferta ="
								+ Integer.valueOf(numOfePrimera).intValue();
						f.executeUpdate(consultaA3);
					}
				}
				if (!numOfeUltima.equals("cero")) {
					// String consulta2="SELECT Precio,DiaIni,DiaFin FROM OFERTA
					// WHERE
					// NumOferta="+Integer.valueOf(numOfeUltima).intValue();
					String consulta2 = "SELECT Precio,DiaIni,DiaFin FROM oferta WHERE NumOferta="
							+ Integer.valueOf(numOfeUltima).intValue();
					ResultSet rs2 = f.executeQuery(consulta2);
					rs2.next();
					float prUlti = rs2.getFloat("Precio");
					java.sql.Date dIniUlti = rs2.getDate("DiaIni");
					java.sql.Date dFinUlti = rs2.getDate("DiaFin");
					int numeroDiasUlti = (int) ((dFinUlti.getTime() - dIniUlti
							.getTime()) / (1000 * 3600 * 24)) + 1;
					float precioPorDiaUlti = prUlti / (int) numeroDiasUlti;
					int numDiasDespues = (int) ((dFinUlti.getTime() - diaFin
							.getTime()) / (1000 * 3600 * 24));
					int nuevoPrecioUlti = (int) (numDiasDespues * precioPorDiaUlti);
					java.sql.Date diaFinNuevo = new java.sql.Date(diaFin
							.getTime() + 1000 * 3600 * 24);
					if (dFinUlti.getTime() >= diaFinNuevo.getTime()) {
						// String consultaA2 = "UPDATE OFERTA SET DiaIni =
						// #"+diaFinNuevo+"#, Precio = '"+nuevoPrecioUlti+"'
						// WHERE NumOferta
						// ="+Integer.valueOf(numOfeUltima).intValue();
						String consultaA2 = "UPDATE oferta SET DiaIni = '"
								+ diaFinNuevo + "', Precio = '"
								+ nuevoPrecioUlti + "' WHERE NumOferta ="
								+ Integer.valueOf(numOfeUltima).intValue();
						f.executeUpdate(consultaA2);
					} else {
						// String consultaA4 = "DELETE FROM OFERTA WHERE
						// NumOferta
						// ="+Integer.valueOf(numOfeUltima).intValue();
						String consultaA4 = "DELETE FROM oferta WHERE NumOferta ="
								+ Integer.valueOf(numOfeUltima).intValue();
						f.executeUpdate(consultaA4);
					}
				}
			} else if (!numOfePrimera.equals("cero")) {
				// String consulta1="SELECT Precio,DiaIni,DiaFin FROM OFERTA
				// WHERE NumOferta="+Integer.valueOf(numOfePrimera).intValue();
				String consulta1 = "SELECT Precio,DiaIni,DiaFin FROM oferta WHERE NumOferta="
						+ Integer.valueOf(numOfePrimera).intValue();
				ResultSet rs1 = f.executeQuery(consulta1);
				rs1.next();
				float prPrim = rs1.getFloat("Precio");
				java.sql.Date dIniPrim = rs1.getDate("DiaIni");
				java.sql.Date dFinPrim = rs1.getDate("DiaFin");
				int numeroDiasPrim = (int) ((dFinPrim.getTime() - dIniPrim
						.getTime()) / (1000 * 3600 * 24)) + 1;
				float precioPorDiaPrim = prPrim / (int) numeroDiasPrim;
				int numDiasAntes = (int) ((diaIni.getTime() - dIniPrim
						.getTime()) / (1000 * 3600 * 24));
				int numDiasDespues = (int) ((dFinPrim.getTime() - diaFin
						.getTime()) / (1000 * 3600 * 24));
				int nuevoPrecioPrim = (int) (numDiasAntes * precioPorDiaPrim);
				int nuevoPrecioUlti = (int) (numDiasDespues * precioPorDiaPrim);
				java.sql.Date diaIniNuevo = new java.sql.Date(
						diaIni.getTime() - 1000 * 3600 * 24);
				java.sql.Date diaFinNuevo = new java.sql.Date(
						diaFin.getTime() + 1000 * 3600 * 24);
				if (diaIniNuevo.getTime() >= dIniPrim.getTime()) {
					// String consultaA5 = "UPDATE OFERTA SET DiaFin =
					// #"+diaIniNuevo+"#, Precio = "+nuevoPrecioPrim+" WHERE
					// NumOferta ="+Integer.valueOf(numOfePrimera).intValue();
					String consultaA5 = "UPDATE oferta SET DiaFin = '"
							+ diaIniNuevo + "', Precio = " + nuevoPrecioPrim
							+ " WHERE NumOferta ="
							+ Integer.valueOf(numOfePrimera).intValue();
					f.executeUpdate(consultaA5);
				} else {
					// String consultaA6 = "DELETE FROM OFERTA WHERE NumOferta
					// ="+Integer.valueOf(numOfePrimera).intValue();
					String consultaA6 = "DELETE FROM oferta WHERE NumOferta ="
							+ Integer.valueOf(numOfePrimera).intValue();
					f.executeUpdate(consultaA6);
				}
				if (dFinPrim.getTime() >= diaFinNuevo.getTime()) {
					// String consulta2="INSERT INTO OFERTA
					// (DiaIni,DiaFin,Precio,NumCasa)"+"VALUES(#"+diaFinNuevo+"#,#"+dFinPrim+"#,"+nuevoPrecioUlti+","+numCasa+")";
					String consulta2 = "INSERT INTO oferta (DiaIni,DiaFin,Precio,NumCasa)"
							+ "VALUES('"
							+ diaFinNuevo
							+ "','"
							+ dFinPrim
							+ "'," + nuevoPrecioUlti + "," + numCasa + ")";
					f.executeUpdate(consulta2);
				}
			}
			String nOferta;
			while (enumera.hasMoreElements()) {
				nOferta = (String) enumera.nextElement();
				// consulta = "DELETE FROM OFERTA WHERE NumOferta
				// ="+Integer.valueOf(nOferta).intValue();
				consulta = "DELETE FROM oferta WHERE NumOferta ="
						+ Integer.valueOf(nOferta).intValue();
				f.executeUpdate(consulta);
			}
			// String consultaI1 = "INSERT INTO OFERTA
			// (DiaIni,DiaFin,Precio,NumCasa)"+"VALUES(#"+diaIni+"#,#"+diaFin+"#,"+precio+","+numCasa+")";
			String consultaI1 = "INSERT INTO oferta (DiaIni,DiaFin,Precio,NumCasa)"
					+ "VALUES('"
					+ diaIni
					+ "','"
					+ diaFin
					+ "',"
					+ precio
					+ "," + numCasa + ")";
			f.executeUpdate(consultaI1);
			c.commit();
			//.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
			ex.printStackTrace();
		}
	}

	/**
	 * Método que obtiene el número de habitaciones y de baños dado el id de
	 * la casa.
	 *
	 * @param numCasa the num casa
	 * @return vector que contiene por un lado, el número de habitaciones, y
	 * por el otro el número de baños de dicha casa
	 */
	public Vector numHabitacionesnumBanos(int numCasa) {
		Vector v = new Vector();
		try {// String consulta="SELECT numDormitorios,numBa�os FROM
			// CasaRural WHERE numCasa="+numCasa;
			String consulta = "SELECT numDormitorios,numBaños FROM casarural WHERE numCasa="
					+ numCasa;

			ResultSet rs = s.executeQuery(consulta);

			if (rs.next()) {
				int dormitorios = rs.getInt(1);

				int banos = rs.getInt(2);

				v.addElement(String.valueOf(dormitorios));

				v.addElement(String.valueOf(banos));
			}

		} catch (Exception ex) {
			System.out
					.println("error al contar habitaciones y baños(GestorBD)");
		}
		;
		return v;
	}

	/**
	 * Método que obtiene el número de camas de una casa. Las camas dobles
	 * cuentan por dos. El método devolverá un único número con el total de
	 * camas de dicha casa.
	 *
	 * @param numCasa the num casa
	 * @return el número de camas de la casa
	 */
	public int camas(int numCasa) {

		int numcamas = 0;
		try
		//
		{// String consulta="SELECT
			// SUM(NumCamasSencillas)+SUM(NumCamasDobles)*2 as tamano FROM
			// Dormitorio WHERE NumCasa="+numCasa+" GROUP BY NumCasa";
			String consulta = "SELECT SUM(NumCamasSencillas)+SUM(NumCamasDobles)*2 as tamano FROM dormitorio WHERE NumCasa="
					+ numCasa + " GROUP BY NumCasa";

			ResultSet rs = s.executeQuery(consulta);

			if (rs.next())
				numcamas = rs.getInt(1);

		} catch (Exception ex) {
			System.out.println("error en camas (GestorBD)");
		}
		;
		return numcamas;
	}
	
	/**
	 * Metodo que devuelve el dia de la primera oferta de la reserva que se le pasa por parametro.
	 *
	 * @param numReserva the num reserva
	 * @return GregorianCalendar,
	 * fecha de la primera oferta de la reserva pasada por parametro
	 * @throws SQLException the sQL exception
	 */
	

	public GregorianCalendar obtenDiaIniMinBD(int numReserva) throws SQLException {

		Date fechaIni;
		GregorianCalendar fechaGreg = new GregorianCalendar();

		String sql = "SELECT DiaIni FROM Oferta WHERE NumReserva = ? ORDER BY DiaIni ASC LIMIT 0,1";
		PreparedStatement ps = c.prepareStatement(sql);

		ps.setInt(1, numReserva);
		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
			fechaIni = rs.getDate(1);
			fechaGreg.setTime(fechaIni);
			return fechaGreg;
		} else {
			throw new SQLException("No se ha encontrado ofertas asociadas a la reserva con ID = " + numReserva);
		}

	}
	
	/**
	 * Metodo que anula la reserva que se le pasa por parametro.
	 *
	 * @param numReserva the num reserva
	 * @return int,
	 * numero de tuplas actualizadas en la base de datos
	 * @throws SQLException the sQL exception
	 */

	public int actualizarReservas(int numReserva) throws SQLException {
		String upd = "UPDATE Reserva SET Anulado = 1 WHERE NumReserva = ?";
		PreparedStatement ps = c.prepareStatement(upd);

		ps.setInt(1, numReserva);

		return ps.executeUpdate();
	}
	
	/**
	 * Metodo que obtiene el precio de la reserva que se le pasa por parametro.
	 *
	 * @param numReserva the num reserva
	 * @return float,
	 * precio de la reserva que se le pasa por parametro
	 * @throws SQLException the sQL exception
	 */

	public float obtenPrecioTotal(int numReserva) throws SQLException {
		float precio;
		String sql = "SELECT PrecioTotal FROM Reserva WHERE NumReserva = ?";

		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, numReserva);

		ResultSet rs = ps.executeQuery();

		if(rs.next()) {
			precio = rs.getFloat(1);
			return precio;
		} else {
			throw new SQLException("No se ha encontrado la reserva con ID = " + numReserva);
		}
	}

	/**
	 * Metodo que actualiza las oferta que tienen asociado el numero de reserva que se le pasa por parametro.
	 * Las ofertas en cuestion ya no estaran asociadas a ninguna reserva.
	 *
	 * @param numReserva the num reserva
	 * @return int,
	 * numero de tuplas actualizadas en la base de datos
	 * @throws SQLException the sQL exception
	 */
	public int actualizarOfertas(int numReserva) throws SQLException {
		String upd = "UPDATE Oferta SET NumReserva = 0 WHERE NumReserva = ?";

		PreparedStatement ps = c.prepareStatement(upd);
		ps.setInt(1, numReserva);

		return ps.executeUpdate();
	}
	
	/**
	 * Metodo que indica si la reserva que se le pasa por parametro ya ha sido pagada.
	 *
	 * @param numReserva the num reserva
	 * @return int,
	 * valor del campo pagado de la reserva que se le pasa por parametro en la base de datos.
	 * 0 indica que la reserva no ha sido pagada, y 1 que si.
	 * @throws SQLException the sQL exception
	 */
	
	public int estaPagado(int numReserva) throws SQLException{
		
		String consulta = "SELECT Pagado from reserva where NumReserva=?";
		int pagado;

		PreparedStatement ps = c.prepareStatement(consulta);
		ps.setInt(1, numReserva);
		
		ResultSet rs = ps.executeQuery();

		if (rs.next())
			pagado = rs.getInt(1);
		else
			throw new SQLException("No se ha encontrado la reserva con ID = " + numReserva);

		return pagado;
}
	
	/**
	 * Hacer consulta.
	 *
	 * @param sql the sql
	 * @return the result set
	 * @throws SQLException the sQL exception
	 */
	public ResultSet hacerConsulta(String sql) throws SQLException{
		
		return  s.executeQuery(sql);
		
	}
	
	/**
	 * Metodo que reinicia la base de datos.
	 *
	 * @return int,
	 * valor del campo pagado de la reserva que se le pasa por parametro en la base de datos.
	 * 0 indica que la reserva no ha sido pagada, y 1 que si.
	 * @throws SQLException the sQL exception
	 */
	
public void reiniciarBD() throws SQLException{
				
	String consulta = "UPDATE reserva SET Anulado =0 where NumReserva=5";
	s.executeUpdate(consulta);
	consulta ="UPDATE oferta SET NumReserva=2 where Precio=75.00";
	s.executeUpdate(consulta);
	consulta ="UPDATE oferta SET NumReserva=5 where NumOferta=3";
	s.executeUpdate(consulta);
	consulta ="DELETE FROM reserva  where NumReserva=25";
	s.executeUpdate(consulta);	
		
	}


////////////////////////////
//NUEVO URKO
//
/////////////////////

/**
 * Da de alta un nuevo servicio.
 *
 * @param pHorario             variable de tipo DateTime, corresponde al horario del servicio
 * @param pPuntoRecogida variable string define donde empieza el servicio
 * @param pNumPlazas the num plazas
 * @param d the d
 * @param i the i
 * @return Ninguno
 */
public void transaccionInsertServicio(Time pHorario,String  pPuntoRecogida, 
		int pNumPlazas, double d,int i)
{
	//para el horario
	/*
	 * java.util.Date date = new java.util.Date();
	 * new  java.sql.Time(date.getTime())
	 * 
	 */
	String consulta = "INSERT INTO cararural2011.Servicio (horario,puntoRecogida,numPlazas,precio,idRecorrido)"
		+ "VALUES('"
		+ pHorario
		+ "','"
		+ pPuntoRecogida
		+ "', " +  pNumPlazas + "," + d + "," + i+ ")";
	System.out.println(consulta);
	try
	{
		c.setAutoCommit(false);
		Statement f = c.createStatement();
		f.executeUpdate(consulta);
	}
	catch (Exception ex) {
	System.out.println(ex.toString());
	}
}

/**
 * Da de alta un nuevo recorrido.
 *
 * @param pInicio variable de tipo String, determina donde (localidad o estacion) comienza el recorrido)
 * @param pFin variable string define la �ltima parada  del servicio
 * @return Ninguno
 */
public void transaccionInsertRecorrido(String pInicio,String pFin)
{
	
	String consulta = "INSERT INTO cararural2011.Recorrido (inicio,fin)"
		+ "VALUES('"
		+ pInicio
		+ "','"
		+ pFin
		+ "' )";
	System.out.println(consulta);
	try
	{
		c.setAutoCommit(false);
		Statement f = c.createStatement();
		f.executeUpdate(consulta);
	}
	catch (Exception ex) {
	System.out.println(ex.toString());
	}
}

/**
 * A�ade una casaRural a un recorrido.
 *
 * @param pNumCasa variable de tipo int, identificador de la casaRural
 * @param pIdRecorrido variable int correspondiente al identificador del Recorrido
 * @return Ninguno
 */
public void transaccionAddCasaRecorrido(int pNumCasa,int pIdRecorrido)
{
	
	String consulta = "INSERT INTO cararural2011.casaRecorrido (numCasa,idRecorrido)"
		+ "VALUES("
		+ pNumCasa
		+ ","
		+ pIdRecorrido
		+ " )";
	System.out.println(consulta);
	try
	{
		c.setAutoCommit(false);
		Statement f = c.createStatement();
		f.executeUpdate(consulta);
	}
	catch (Exception ex) {
	System.out.println(ex.toString());
	}
}

	/*
	 * Devuelve  todas los Recorridos
	 *
	 * @param Ninguno
	 * @return Vector de objetos de la clase Recorrido
	 */
	/**
	 * Gets the lista recorridos.
	 *
	 * @return the lista recorridos
	 */
	public Vector getListaRecorridos()

	{
		Vector vectorRecorridos = new Vector();
		try {

			// String consulta="SELECT * FROM CasaRural";
			String consulta = "SELECT * FROM Recorrido";
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) {
				int idRecorrido = rs.getInt("idRecorrido");
				String Inicio= rs.getString("Inicio");
				String Fin = rs.getString("Fin");
				Recorrido re = new Recorrido(idRecorrido,Inicio,Fin);
				vectorRecorridos.addElement(re);
			}
			
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return vectorRecorridos;
	}
	
	/**
	 * Devuelve un propietario concreto sabiendo su alias
	 *
	 * @param pAlias el alias
	 * @return el propietario de alias pAlias
	 * @throws SQLException 
	 */
	public Propietario getPropietarioConcreto(String pAlias) throws SQLException
	{

		Propietario pro = new Propietario();
		try {

			String consulta = "SELECT * FROM Propietario where CuentaSistema='"+pAlias+"'";
			//System.out.println(consulta);
			ResultSet rs = s.executeQuery(consulta);
		
			 
			  
				 while (rs.next()) {
					 pro.setAlias(rs.getString("CuentaSistema"));
					pro.setPassword(rs.getString("Password"));
					pro.setEsAdmin(rs.getBoolean("esAdmin"));
				}
				 rs.last(); //me coloco en la ultima pos, pq rs.next te deja al principio otra vez
				 if ( rs.getRow()== 0 )  //no existe ese propietario
				 { 
						throw new SQLException("No se ha encontrado un usuario con alias = " + pAlias);
				 } 
		} catch (Exception ex) {
			System.out.println(ex.toString());

		}
		return pro;	
	}
	/*
* Devuelve  todas los Servicios
	 *
	 * @param Ninguno
	 * @return Vector de objetos de la clase Servicio
	 */
	/**
	 * Gets the lista servicios.
	 *
	 * @return the lista servicios
	 */
	public Vector getListaServicios()

	{
		Vector vectorServicios = new Vector();
		try {

			// String consulta="SELECT * FROM CasaRural";
			String consulta = "SELECT * FROM Servicio";
			ResultSet rs = s.executeQuery(consulta);
			while (rs.next()) {
				int idServicio = rs.getInt("idServicio");
				Time horario = rs.getTime("horario");
				String puntoRecogida = rs.getString("puntoRecogida");
				int numPlazas = rs.getInt("numPlazas");
				float precio = rs.getFloat("precio");
				int idRecorrido= rs.getInt("idRecorrido");
				
									
				Servicio se = new Servicio(idServicio,horario,puntoRecogida,numPlazas,numPlazas, precio,idRecorrido);
				vectorServicios.addElement(se);
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return vectorServicios;
		
	}
			
	/*
* Devuelve  todas los Origenes
	 *
	 * @param Ninguno
	 * @return Vector de String Origen
	 */
/**
	 * Gets the lista origenes.
	 *
	 * @return the lista origenes
	 */
	public Vector getListaOrigenes()
{
	Vector vectorOrigenes = new Vector();
	try {
		// String consulta="SELECT * FROM CasaRural";
		String consulta = "SELECT inicio FROM Recorrido";
		ResultSet rs = s.executeQuery(consulta);
		while (rs.next()) {
			String pInicio = rs.getString("inicio");
			vectorOrigenes.addElement(pInicio);
		}
	}
	catch(Exception ex) {
		System.out.println(ex.toString());
	}
		return vectorOrigenes;
}

			
/*
 * Resta una unidad al num de plazas libres 
			 *
			 * @param idServicio
			 * 			idServicio correspondiente al servicio que queremos disminuir en una unidad
			 * @return null
			 */			
/**
 * Restar unidad servicio.
 *
 * @param idServicio the id servicio
 * @param plazas the plazas
 * @throws SQLException 
 */
public void restarUnidadServicio(int idServicio, int plazas) throws SQLException
{
	
	try{
		 s.executeUpdate("UPDATE Servicio SET plazasLibres= plazasLibres - " + plazas + "  WHERE idServicio="+idServicio );
	}
	catch (Exception ex)
	{
		throw new SQLException("No se ha encontrado un idServicio con idServicio= "+ idServicio );

	}
}
	
/**
 * Adds the casa a recorrido.
 *
 * @param pNumCasa the num casa
 * @param pIdRecorrido the id recorrido
 */
public void addCasaARecorrido(int pNumCasa,int pIdRecorrido)
{
	try
	{
		 String consulta = "insert into CasaRecorrido(idRecorrido, numCasa) values(?, ?)";

		  PreparedStatement   pstmt = c.prepareStatement(consulta); // create a statement
	      pstmt.setInt(1, pIdRecorrido); // set input parameter 1
	      pstmt.setInt(2, pNumCasa); // set input parameter 2
	      pstmt.executeUpdate(); // execute insert statement
	}
	catch(Exception ex) {
		System.out.println(ex.toString());
		
	}
}

}
















