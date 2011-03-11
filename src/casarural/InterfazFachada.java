package casarural;
/**
 * @author Grupo 2
 * 
 */

import java.rmi.*;
import java.util.Vector;
import java.sql.Date;
import java.sql.SQLException;

import excepciones.NoSePuedeReservarException;
public interface InterfazFachada extends Remote
{
  public static final int numPuerto=1099;
 
  //Float calcular(Float op1,Float op2) throws RemoteException;
 /**Establece la configuracion
  *@param La configuracion a establecer
  *@return ninguno
  */ 
 //void setConfiguracion(InterfazConfiguracion inConf) throws RemoteException;
 /**Selecciona las reservas incluidas entre las fechas indicadas para el número de casa dado
  *@param Dia de Inicio, dia de fin y numero de casa
  *@return vector de objetos de la clase Oferta
  */  
 //Vector seleccionarReservas(java.sql.Date diaIni,java.sql.Date diaFin,int numCasa) throws RemoteException,NoSePuedeReservarException;
 /**Realizar la transaccion de reservas
  *@param Vector de Strings números de oferta, numero de reserva, numero de telefono, precio total
  *@return Ninguno
  */ 
 //void transaccionDeReserva(Vector reservasTotales,String numReserva,String numTfnoReserva,float PrecioTotal) throws RemoteException;
 /**Selecciona el propietario a partir de una casa
  *@param Numero de casa
  *@return Propietario 
  */ 
 //Propietario seleccionarPropietario(int numCasa) throws RemoteException;
 
 /**Selecciona las casas a partir de una cuenta
  *@param Numero de cuenta
  *@return Vector de objetos de la clase Casa
  */ 
 //Vector seleccionarCasas(String cuentaSistema) throws RemoteException;
 
 /**Selecciona las ofertas entre las fechas indicadas para un numero de casa
  *@param Fecha de inicio, fecha de fin y numero de casa
  *@return Vector de objetos de la clase Oferta
  */
 //Vector seleccionarOfertas(java.sql.Date diaIni,java.sql.Date diaFin,int numCasa) throws RemoteException;
 /**Realiza una transaccion con la ofertas
  *@param Vector de Strings números de oferta, dia de inicio, dia de fin, primera oferta, ultima oferta, precio y numero de casa
  *@return  Ninguno
  */  
 //void transaccionDeOfertas(Vector todasLasOfertasIncluidas,java.sql.Date diaIni,java.sql.Date diaFin,String numOfePrimera,String numOfeUltima,float precio,int numCasa) throws RemoteException;
 /**Establece el tanto por ciento a pagar
  *@param el porcentaje
  *@return Ninguno
  */ 
 //void setTantoPorCientoAPagar(int porcentaje) throws RemoteException;
 /**Obtiene el tanto por ciento
  *@param Ninguno
  *@return el porcentaje
  */ 
 //int getTantoPorCientoAPagar() throws RemoteException;
 /**Establece el numero del dia a pagar
  *@param Numero de dias
  *@return Ninguno
  */ 
 //void setNumDiasAPagar(int numDias) throws RemoteException;
 /**Obtiene el numero de dia a pagar
  *@param Ninguno
  *@return Numero de dias
  */ 
 //int getNumDiasAPagar() throws RemoteException;
 /**Obtiene la configuracion
  *@param Ninguno
  *@return La configuracion
  */  
 //public InterfazConfiguracion getConfiguracion() throws RemoteException;
 
  /**Obtiene el codigo de las casas vinculadas a una cuenta
   *@param Numero de cuenta
   *@return Vector de codigos de casa
   */ 
 Vector getCodigosCasas(String cuentaSistema) throws RemoteException;

  /**Obtiene la mejor oferta siguiendo el criterio indicado
   *@param Dia inicio, dia final, numero de habitaciones, numero de baños, criterio
   * @return Nada
   */
   Oferta obtenerMejorOferta(Date diaIni, Date diaFin, int habitaciones, int banos, String criterio) throws RemoteException,Exception;
 
 /**Asigna una oferta con el numero de casa, fecha de inicio, fecha de fin y precio
  *@param Numero de casa, fecha de inicio, fecha de fin y precio
  *@return Ninguno
  */  

  void asignarOferta(int numCasa,java.sql.Date diaIni,java.sql.Date diaFin,float precio) throws RemoteException,Exception;
 
 /**Realiza la reserva
  *@param Dia inicio, dia fin, numero de casa, telefono
  *@return la reserva
  */  
 Reserva reservar(java.sql.Date diaIni,java.sql.Date diaFin,int numCasa,String numTfnoReserva) throws RemoteException,NoSePuedeReservarException;
 
 /**Obtener el numero de cuenta corriente a partir de una casa
  *@param Numero de casa
  *@return Numero de cuenta corriente
  */ 
 String getNumCuentaCorriente(int numCasa) throws RemoteException;
 

 /**Obtiene las ofertas entre las fechas indicadas para un numero de casa
  *@param Numero de casa, Fecha de inicio y fecha de fin  
  *@return Vector de objetos de la clase Oferta
  */
 OfertasEnMemoriaPrincipal obtenerOfertas (int numCasa,java.sql.Date diaIni,java.sql.Date diaFin) throws RemoteException,Exception;

 Vector buscarOfertas(java.sql.Date diaIni, java.sql.Date diaFin, Float precioMax, int diasMin, int dormitorios, int banos, boolean orden) throws RemoteException, Exception;
 /**
  * Como la clase fachada implementa ésta interfaz, sólo contiene la cabecera.
  * 
  * @param numReserva
  * @throws RemoteException, SQLException
  */
 float anularReserva(Integer numReserva)throws RemoteException, SQLException;
 
}