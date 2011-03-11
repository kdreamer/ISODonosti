package casarural;
import java.io.*;
public class Propietario implements Serializable
{
  private String numCuentaCorriente="";
  public Propietario()
  {
  }
/**Obtiene el numero de la cuenta corriente
  *@param Ninguno
  *@return El numero de la cuenta corriente
  */
  public String getNumCuentaCorriente()
  {return this.numCuentaCorriente;}
/**Establecer el numero de cuenta corriente
  *@param El numero de la cuenta corriente
  *@return Ninguno
  */
  public void setNumCuentaCorriente(String numCuenta)
  {this.numCuentaCorriente=numCuenta;}
}