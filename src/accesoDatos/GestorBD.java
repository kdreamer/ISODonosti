package accesoDatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Vector;

import configuracion.Config;

import casarural.Casa;
import casarural.Oferta;
import casarural.Propietario;
import excepciones.NoSePuedeReservarException;

public final class GestorBD {

	private static Config conf = Config.getInstance();
	public static final String FUENTE_DATOS = conf.getFuenteDatos();
	public static final String SERVER = conf.getServerBD();
	public static final String PROTOCOLO = conf.getProtocoloBD();
	public static final String DRIVER = conf.getDriverBD();
	public static final String	SERVERRMI= conf.getServerRMI();
	//private static final String URL = PROTOCOLO + "://" + SERVER + "/" + FUENTE_DATOS;
	private static final String URL = PROTOCOLO + ":"+ FUENTE_DATOS;
	private static final String USER = conf.getUserBD();
	private static final String PASS = conf.getPassBD();
	private static GestorBD elGestorBD;

	private Connection c;
	private Statement s;

	/**
	 * Constructor
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
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
	 * Devuelve una instancia del gestor de la BD
	 *
	 * @return GestorBD
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static GestorBD getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		if (elGestorBD == null)
			elGestorBD = new GestorBD();
		return elGestorBD;
	}

	/**
	 * Selecciona reservas entre las fechas que se le indican
	 *
	 * @param Dia
	 *            de inicio, dia de fin y numero de casas
	 * @return Vector de objetos Oferta
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
	 * Dado un numero de casa selecciona al propietario de esa casa
	 *
	 * @param Numero
	 *            de casa
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
	 * Devuelve las casas dado un numero de cuenta de propietario
	 *
	 * @param Numero
	 *            de cuenta de propietario
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
	 * Devuelve las todas las casas
	 *
	 * @param Ninguno
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
	 * @param numDormitorios,
	 *            mínimo número de dormitorios.
	 * @param numBaños,
	 *            mínimo número de baños.
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
	 * @param year,
	 *            entero que hace referencia al año
	 * @param month,
	 *            entero que hace referencia al mes
	 * @param day,
	 *            entero que hace referencia al día
	 * @return Date, la misma fecha en formato fecha (clase Date).
	 */
	private static java.sql.Date fecha(int year, int month, int day) {
		return new java.sql.Date((new GregorianCalendar(year, month - 1, day))
				.getTime().getTime());
	}

	/**
	 * Selecciona las ofertas entre las fechas dadas para un numero de casa
	 *
	 * @param Diade
	 *            inicio, dia de fin y numero de casa
	 *
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

	/**
	 * Realiza la reserva en forma de transaccion
	 *
	 * @param Vector
	 *            de Strings numeros de oferta, numero de reserva, telefono y
	 *            precio
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
	 * Realiza una trasaccion de ofertas
	 *
	 * @param Vector
	 *            de Strings numeros de oferta, dia de inicio, dia de fin,
	 *            numero de la primera oferta, numero de la ultima oferta,precio
	 *            y numero de casa
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
	 * la casa
	 *
	 * @param numCasa,
	 *            id de la casa a consultar
	 * @return vector que contiene por un lado, el número de habitaciones, y
	 *         por el otro el número de baños de dicha casa
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
	 * @param numCasa,
	 *            identificador de la casa
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
	 * @param numReserva,
	 * 			identificador de la reserva
	 * 
	 * @return GregorianCalendar,
	 * 			fecha de la primera oferta de la reserva pasada por parametro
	 *
	 * @throws SQLException,
	 * 			Excepcion que se produce si no encuentra ninguna oferta que tenga asociada esa reserva o se produce 
	 * 			un error al realizar la consulta a la base de datos. 
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
	 * @param numReserva,
	 * 			identificador de la reserva
	 * 
	 * @return int,
	 * 			numero de tuplas actualizadas en la base de datos
	 * 
	 * @throws SQLException,
	 * 			Excepcion que se produce cuando ocurre un error al realizar la consulta a la base de datos.
	 */

	public int actualizarReservas(int numReserva) throws SQLException {
		String upd = "UPDATE Reserva SET Anulado = 1 WHERE NumReserva = ?";
		PreparedStatement ps = c.prepareStatement(upd);

		ps.setInt(1, numReserva);

		return ps.executeUpdate();
	}
	
	/**
	 * Metodo que obtiene el precio de la reserva que se le pasa por parametro
	 *
	 * @param numReserva,
	 * 			identificador de la reserva
	 * 
	 * @return float,
	 * 			precio de la reserva que se le pasa por parametro
	 * 
	 * @throws SQLException,
	 * 			Excepcion que se produce cuando ocurre un error al realizar la consulta a la base de datos.
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
	 * @param numReserva,
	 * 			identificador de la reserva
	 * 
	 * @return int,
	 * 			numero de tuplas actualizadas en la base de datos
	 * 
	 * @throws SQLException,
	 * 			Excepcion que se produce cuando ocurre un error al realizar la consulta a la base de datos.
	 * 
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
	 * @param numReserva,
	 * 			identificador de la reserva
	 * 
	 * @return int,
	 * 			valor del campo pagado de la reserva que se le pasa por parametro en la base de datos. 
	 * 			0 indica que la reserva no ha sido pagada, y 1 que si.
	 * 
	 * @throws SQLException,
	 * 			Excepcion que se produce cuando ocurre un error al realizar la consulta a la base de datos.
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
	public ResultSet hacerConsulta(String sql) throws SQLException{
		
		return  s.executeQuery(sql);
		
	}
	
	/**
	 * Metodo que reinicia la base de datos. 
	 * 
	 * @return int,
	 * 			valor del campo pagado de la reserva que se le pasa por parametro en la base de datos. 
	 * 			0 indica que la reserva no ha sido pagada, y 1 que si.
	 * 
	 * @throws SQLException,
	 * 			Excepcion que se produce cuando ocurre un error al realizar la consulta a la base de datos.
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
}

