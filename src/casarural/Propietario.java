package casarural;
import java.io.*;
// TODO: Auto-generated Javadoc

/**
 * The Class Propietario.
 *
 * @author  kdreamer
 */
public class Propietario implements Serializable
{
	/** The num cuenta corriente. @uml.property  name="numCuentaCorriente" */
	private String numCuentaCorriente=null;

	/** The alias. */
	private String alias= null;

	/** The password. */
	private String password= null;

	/** The es admin. */
	private Boolean esAdmin=null;
	
 


  /**
   * Instantiates a new propietario.
   */
  public Propietario()
  {
  }

/**
 * Obtiene el numero de la cuenta corriente.
 *
 * @return  El numero de la cuenta corriente
 * @uml.property  name="numCuentaCorriente"
 */
  public String getNumCuentaCorriente()
  {return this.numCuentaCorriente;}

/**
 * Establecer el numero de cuenta corriente.
 *
 * @param numCuenta the new num cuenta corriente
 * @return  Ninguno
 * @uml.property  name="numCuentaCorriente"
 */
  public void setNumCuentaCorriente(String numCuenta)
  {this.numCuentaCorriente=numCuenta;}
  
  
  /**
   * Asinga la variable alias.
   *
   * @param pAlias el nuevo alias
   */
  public void setAlias(String pAlias)
  {
	  this.alias=pAlias;
  }
  
  /**
   * Asigna el password.
   *
   * @param pPassword el nuevo password
   */
  public void setPassword(String pPassword){
	  this.password=pPassword;
  }
  
  /**
   * Asigna el atributo esAdmin
   *
   * @param pEsAdmin el nuevo valor de esAdmin
   */
  public void setEsAdmin(Boolean pEsAdmin){
	  this.esAdmin =pEsAdmin;
  }
  public String getAlias() {
		return alias;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getEsAdmin() {
		return esAdmin;
	}

}