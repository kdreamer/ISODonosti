package configuracion;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * @author  kdreamer
 */
public class Config {

	/**
	 * @uml.property  name="userBD"
	 */
	private String userBD;

	/**
	 * @uml.property  name="passBD"
	 */
	private String passBD;

	/**
	 * @uml.property  name="fuenteDatos"
	 */
	private String fuenteDatos;

	/**
	 * @uml.property  name="protocoloBD"
	 */
	private String protocoloBD;

	/**
	 * @uml.property  name="puertoBD"
	 */
	private String puertoBD;

	/**
	 * @uml.property  name="driverBD"
	 */
	private String driverBD;

	/**
	 * @uml.property  name="serverBD"
	 */
	private String serverBD;

	/**
	 * @uml.property  name="urlBD"
	 */
	private String urlBD;

	/**
	 * @uml.property  name="serverRMI"
	 */
	private String serverRMI;

	/**
	 * @uml.property  name="puertoRMI"
	 */
	private String puertoRMI;

	/**
	 * @uml.property  name="servicioRMI"
	 */
	private String servicioRMI;

	private String urlRMI;

	/**
	 * @uml.property  name="ficheroReservas"
	 */
	private String ficheroReservas;

	private final String USER_BD = "userBD";

	private final String PASS_BD = "passBD";

	private final String FUENTE_DATOS = "fuenteDatos";

	private final String PROTOCLO_BD = "protocoloBD";

	private final String PUERTO_BD = "puertoBD";

	private final String DRIVER_BD = "driverBD";

	private final String SERVER_BD = "serverBD";

	private final String SERVER_RMI = "serverRMI";

	private final String PUERTO_RMI = "puertoRMI";

	private final String SERVICIO_RMI = "servicioRMI";

	private final String FICHERO_RESERVAS = "ficheroReservas";



	/**
	 * @uml.property  name="instancia"
	 * @uml.associationEnd  
	 */
	private static Config instancia = new Config();

	private static final String FICHERO = "src/configuracion/config.xml";
	private static final String SCHEMA = "src/configuracion/config.xsd";

	private Config() {
		
		/*
		fuenteDatos= "BDCasasRurales";
		protocoloBD = "jdbc:odbc";
		puertoBD  ="3306";
		driverBD ="sun.jdbc.odbc.JdbcOdbcDriver";
		serverBD = "localhost";
		serverRMI = "localhost";
		puertoRMI = "1099";
		servicioRMI = "casaRural2010";
		ficheroReservas= "numeroReserva.txt";

	urlBD = protocoloBD + "://" + serverBD + ":" + puertoBD + "/" + fuenteDatos;
		urlBD= protocoloBD+ ":" + fuenteDatos;
urlRMI = "rmi://" + serverRMI + ":" + puertoRMI + "/" + servicioRMI;
		*/
		try {
			if (!validarXML()) {
				System.out.println("Error en la configuracion de la aplicacion.");
				System.exit(-3);
			}
			SAXBuilder builder = new SAXBuilder();
			Document doc = builder.build(FICHERO);
			Element raiz = doc.getRootElement();
			userBD = raiz.getChild(USER_BD).getText();
			passBD = raiz.getChild(PASS_BD).getText();
			serverBD = raiz.getChild(SERVER_BD).getText();
			fuenteDatos = raiz.getChild(FUENTE_DATOS).getText();
			driverBD = raiz.getChild(DRIVER_BD).getText();
			protocoloBD = raiz.getChild(PROTOCLO_BD).getText();
			puertoBD = raiz.getChild(PUERTO_BD).getText();
			serverRMI = raiz.getChild(SERVER_RMI).getText();
			puertoRMI = raiz.getChild(PUERTO_RMI).getText();
			servicioRMI = raiz.getChild(SERVICIO_RMI).getText();
			ficheroReservas = raiz.getChild(FICHERO_RESERVAS).getText();
			urlBD = protocoloBD + ":"+ fuenteDatos;
			urlRMI = "rmi://" + serverRMI + ":" + puertoRMI + "/" + servicioRMI;
			
			
		} catch (JDOMException e1) {
			System.out.println("Error al leer el fichero de configuracion.\n");
			System.out.println(e1.getMessage());
			System.exit(-1);
		} catch (IOException e1) {
			System.out
					.println("No se ha podido leer el fichero de configuracion.\n");
			System.exit(-2);
			
		
		}
		
	}

	private boolean validarXML() {
		boolean correcto = false;
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		try {
			Schema schemaGrammar = schemaFactory.newSchema(new File(SCHEMA));
			Validator schemaValidator = schemaGrammar.newValidator();
			schemaValidator.validate(new StreamSource(FICHERO));
			correcto = true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return correcto;
	}

	public static Config getInstance() {
		return instancia;
	}

	/**
	 * @return
	 * @uml.property  name="userBD"
	 */
	public String getUserBD() {
		return userBD;
	}

	/**
	 * @return
	 * @uml.property  name="passBD"
	 */
	public String getPassBD() {
		return passBD;
	}

	/**
	 * @return
	 * @uml.property  name="driverBD"
	 */
	public String getDriverBD() {
		return driverBD;
	}

	/**
	 * @return
	 * @uml.property  name="fuenteDatos"
	 */
	public String getFuenteDatos() {
		return fuenteDatos;
	}

	/**
	 * @return
	 * @uml.property  name="protocoloBD"
	 */
	public String getProtocoloBD() {
		return protocoloBD;
	}

	/**
	 * @return
	 * @uml.property  name="puertoBD"
	 */
	public String getPuertoBD() {
		return puertoBD;
	}

	/**
	 * @return
	 * @uml.property  name="serverBD"
	 */
	public String getServerBD() {
		return serverBD;
	}

	/**
	 * @return
	 * @uml.property  name="urlBD"
	 */
	public String getUrlBD() {
		return urlBD;
	}

	/**
	 * @return
	 * @uml.property  name="serverRMI"
	 */
	public String getServerRMI() {
		return serverRMI;
	}

	/**
	 * @return
	 * @uml.property  name="puertoRMI"
	 */
	public String getPuertoRMI() {
		return puertoRMI;
	}

	/**
	 * @return
	 * @uml.property  name="servicioRMI"
	 */
	public String getServicioRMI() {
		System.out.println("FEO "+ servicioRMI);
		return servicioRMI;
	}

	/**
	 * @return
	 * @uml.property  name="ficheroReservas"
	 */
	public String getFicheroReservas() {
		return ficheroReservas;
	}

	public String getUrlRmi() {
		return urlRMI;
	}

	public String toString() {
		return urlBD + "\nUser: " + userBD + "\nPass: " + passBD + "\nDriver: "
				+ driverBD + "\nRMI: " + urlRMI + "\nFichero reservas: " + ficheroReservas;
	}

}
