package casarural;
import java.util.Vector;
import java.rmi.*;
import java.sql.SQLException;
import java.util.Enumeration;

import accesoDatos.GestorBD;
/**
 * @author  kdreamer
 */
public final class GestorCasasRurales
{
  /**
 * @uml.property  name="gbd"
 * @uml.associationEnd  
 */
GestorBD gbd;
  /**
 * @uml.property  name="elGestorCasasRurales"
 * @uml.associationEnd  
 */
private static GestorCasasRurales elGestorCasasRurales;
  private GestorCasasRurales() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
  {
    gbd = GestorBD.getInstance();
  }
  /**Devuelve una instancia de la clase GestorCasasRurales
  *@param Ninguno
  *@return El gestor de casas rurales
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IllegalAccessException 
 * @throws InstantiationException 
  */
  public static GestorCasasRurales getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
  {
	  if (elGestorCasasRurales == null) {
		  elGestorCasasRurales =new GestorCasasRurales();
	  }
    return elGestorCasasRurales;
  }
  /**Obtener el numero de cuenta corriente a partir de una casa
  *@param Numero de casa
  *@return Numero de cuenta corriente
  */ 
  public String getNumCuentaCorriente (int numCasa)
  {
  try{
 
  return gbd.seleccionarPropietario(numCasa).getNumCuentaCorriente();
  }catch(Exception e){System.out.println("Error accediendo al Gestor de BD: "+e.toString());
                      return null;}
  }
/**Obtiene el codigo de las casas vinculadas a una cuenta
  *@param Numero de cuenta
  *@return Vector de codigos de casa
  */ 
  public Vector getCodigosCasas(String cuentaSistema)
  {
  Vector v2=new Vector();
 try{
    v2=gbd.seleccionarCasas(cuentaSistema);
  }
  catch(Exception e){System.out.println("Error accediendo al Gestor de BD: "+e.toString());}
  Enumeration e2=v2.elements();
  Casa ic;
  Vector v3=new Vector();
  while(e2.hasMoreElements())
  {
  ic=(Casa)e2.nextElement();
  v3.addElement(new Integer(ic.getNumCasa()));
  }
  
  return v3;
}

  public Vector getCodigosCasas()
  {
	  Vector v2=new Vector();
	  try{
		  v2=gbd.seleccionarCasas();
	  }
	  catch(Exception e){System.out.println("Error accediendo al Gestor de BD: "+e.toString());}
	  Enumeration e2=v2.elements();
	  Casa ic;
	  Vector v3=new Vector();
	  while(e2.hasMoreElements())
	  {
		  ic=(Casa)e2.nextElement();
		  v3.addElement(new Integer(ic.getNumCasa()));
	  }
  
	  return v3;
  }
}