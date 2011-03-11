package casarural;
import java.io.*;
public class Casa implements Serializable
{
private int numCasa;
  public Casa()
  {
  }
/**Obtiene el numero de casa
  *@param Ninguno
  *@return El numero de casa
  */
  public int getNumCasa()
  {return this.numCasa;}
/**Establecer el numero de casa
  *@param numero de casa a fijar
  *@return Ninguno
  */
  public void setNumCasa(int numCas)
  {this.numCasa=numCas;}
}