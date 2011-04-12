package casarural;
/**
 * @author Grupo 2
 */
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import excepciones.NoSePuedeReservarException;

/**
 * @author  kdreamer
 */
public class ClaseFachada extends UnicastRemoteObject implements InterfazFachada {
	
	/**
	 * @uml.property  name="elGestorCasasRurales"
	 * @uml.associationEnd  
	 */
	GestorCasasRurales elGestorCasasRurales=GestorCasasRurales.getInstance();
	/**
	 * @uml.property  name="elGestorReservas"
	 * @uml.associationEnd  
	 */
	GestorReservas elGestorReservas;
	/**
	 * @uml.property  name="elGestorOfertas"
	 * @uml.associationEnd  
	 */
	GestorOfertas elGestorOfertas;
	
	Login elGestorLogin;
	
	public ClaseFachada() throws RemoteException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
  {
		 elGestorReservas=GestorReservas.getInstance();
		 elGestorOfertas=GestorOfertas.getInstance();
		 elGestorLogin= Login.getInstance(); 
	}
	/*
	public Float calcular(Float op1, Float op2) throws RemoteException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public void setConfiguracion(InterfazConfiguracion inConf) throws RemoteException {
		// TODO Apéndice de método generado automáticamente
		
	}

	public Vector seleccionarReservas(Date diaIni, Date diaFin, int numCasa) throws RemoteException, NoSePuedeReservarException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public void transaccionDeReserva(Vector reservasTotales, String numReserva, String numTfnoReserva, float PrecioTotal) throws RemoteException {
		// TODO Apéndice de método generado automáticamente
		
	}

	public Propietario seleccionarPropietario(int numCasa) throws RemoteException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public Vector getCodigosCasas(String cuentaSistema) throws RemoteException {
		return elGestorCasasRurales.getCodigosCasas(cuentaSistema);
	}

	public Vector seleccionarCasas(String cuentaSistema) throws RemoteException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public Vector seleccionarOfertas(Date diaIni, Date diaFin, int numCasa) throws RemoteException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}

	public void transaccionDeOfertas(Vector todasLasOfertasIncluidas, Date diaIni, Date diaFin, String numOfePrimera, String numOfeUltima, float precio, int numCasa) throws RemoteException {
		// TODO Apéndice de método generado automáticamente
	}

	public void setTantoPorCientoAPagar(int porcentaje) throws RemoteException {
		// TODO Apéndice de método generado automáticamente
	}

	public int getTantoPorCientoAPagar() throws RemoteException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	public void setNumDiasAPagar(int numDias) throws RemoteException {
		// TODO Apéndice de método generado automáticamente
	}

	public int getNumDiasAPagar() throws RemoteException {
		// TODO Apéndice de método generado automáticamente
		return 0;
	}

	public InterfazConfiguracion getConfiguracion() throws RemoteException {
		// TODO Apéndice de método generado automáticamente
		return null;
	}
	*/
	
	/**Obtiene el codigo de las casas vinculadas a una cuenta
	  *@param Numero de cuenta
	  *@return Vector de codigos de casa
	  */ 
    
	public Vector getCodigosCasas(String cuentaSistema) throws RemoteException {
		return elGestorCasasRurales.getCodigosCasas(cuentaSistema);
	}

  
	 /**Obtiene las ofertas entre las fechas indicadas para un numero de casa
	  *@param Numero de casa, Fecha de inicio y fecha de fin  
	  *@return Vector de objetos de la clase Oferta
    */
    
    public Oferta obtenerMejorOferta (Date diaIni,Date diaFin, int habitaciones, int banos, String criterio) throws RemoteException, Exception
    {
  
    return elGestorOfertas.obtenerMejorOferta(diaIni, diaFin, habitaciones, banos, criterio);
  
    }
	/**Asigna la oferta a la casa determinada
	  *@param El codigo de la casa, dia de inicio, dia de fin y el precio
	  *@return Ninguno
	  */ 
    public void asignarOferta(int numCasa, Date diaIni, Date diaFin, float precio) throws RemoteException, Exception {
		elGestorOfertas.asignarOferta(numCasa, diaIni, diaFin, precio);
	}
	
	/**Devuelve la reserva vinculada a los parametros de entrada
	  *@param El codigo de la casa, dia de inicio, dia de fin y el numero de telefono
	  *@return La reserva
	  */ 
	public Reserva reservar(Date diaIni, Date diaFin, int numCasa, String numTfnoReserva) throws RemoteException, NoSePuedeReservarException {
		
		return elGestorReservas.reservar(diaIni, diaFin, numCasa, numTfnoReserva);
	}
	
	/**Devuelve el numero de cuenta corriente asociada a la casa
	  *@param El codigo de la casa
	  *@return El numero de cuenta
	  */ 
	public String getNumCuentaCorriente(int numCasa) throws RemoteException {
		return elGestorCasasRurales.getNumCuentaCorriente(numCasa);
	}
	
	//////////////////////////////////////////////////////////////////////////////
	// Añadido por nosotros
	
	/**Obtiene las ofertas entre las fechas indicadas para un numero de casa
	  *@param Numero de casa, Fecha de inicio y fecha de fin  
	  *@return Vector de objetos de la clase Oferta
	*/
	 public OfertasEnMemoriaPrincipal obtenerOfertas (int numCasa,java.sql.Date diaIni,java.sql.Date diaFin) throws RemoteException,Exception {
	 	return elGestorOfertas.obtenerOfertas(numCasa, diaIni, diaFin);
	 }
	 /**
	 	Método que anula una reserva y con ella todas las ofertas relacionadas, además de devolver el Precio total.
	 * 
	 * @param Numero
	 *            de Reserva.
	 * @return PrecioTotal
	 */
	 public float anularReserva(Integer numReserva)throws RemoteException, SQLException{
		 return elGestorReservas.anularReserva(numReserva);
	 }
	 
	 
	

	public Vector buscarOfertas(Date diaIni, Date diaFin, Float precioMax, int diasMin, int dormitorios, int banos, boolean orden) throws RemoteException, Exception {
		 return elGestorOfertas.buscarOfertas(diaIni, diaFin, precioMax,
				 	diasMin, dormitorios, banos, orden);
	}

	public Boolean hacerLogin(String pAlias, String pPassword)throws RemoteException,Exception {
		return elGestorLogin.identificarse(pAlias, pPassword);
	}

}