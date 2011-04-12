package pruebas;

import configuracion.Config;
import junit.framework.TestCase;

/**
 * @author  kdreamer
 */
public class TestConfiguracion extends TestCase {

	/**
	 * @uml.property  name="config"
	 * @uml.associationEnd  
	 */
	private Config config;

	public TestConfiguracion(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		config = Config.getInstance();
	}

	public void testGetUserBD() {
		assertEquals("Error en la carga del usuario de la BD", "iso", config.getUserBD());
	}

	public void testGetPassBD() {
		assertEquals("Error en la carga del password de la BD", "iso", config.getPassBD());
	}

	public void testGetServerBD() {
		assertEquals("Error en la carga del servidor de la BD", "localhost", config.getServerBD());
	}

	public void testGetPuertoBD() {
		assertEquals("Error en la carga del puerto de la BD", "3306", config.getPuertoBD());
	}

	public void testGetProtocoloBD() {
		assertEquals("Error en la carga del protocolo de la BD", "jdbc:mysql", config.getProtocoloBD());
	}

	public void testGetDriverBD() {
		assertEquals("Error en la carga del driver de la BD", "com.mysql.jdbc.Driver", config.getDriverBD());
	}

	public void testGetFuenteDatos() {
		assertEquals("Error en la carga de la fuente de datos", "casarural", config.getFuenteDatos());
	}

	public void testGetServerRMI() {
		assertEquals("Error en la carga del servidor RMI", "localhost", config.getServerRMI());
	}

	public void testGetPuertoRMI() {
		assertEquals("Error en la carga del puerto RMI", "1099", config.getPuertoRMI());
	}

	public void testGetficheroReservasServicioRMI() {
		assertEquals("Error en la carga del servicio RMI", "CasaRural", config.getServicioRMI());
	}

	public void testGetficheroReservas() {
		assertEquals("Error en la carga del fichero de reservas", "numReservas.txt", config.getFicheroReservas());
	}

	public void testGetURLDB() {
		assertEquals("Error en la url de la BD", "jdbc:mysql://localhost:3306/casarural", config.getUrlBD());
	}

	public void testGetURLRMI() {
		assertEquals("Error en la url de la BD", "rmi://localhost:1099/CasaRural", config.getUrlRmi());
	}
}
