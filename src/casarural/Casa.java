package casarural;
import java.io.*;
/**
 * @author  kdreamer
 */
public class Casa implements Serializable
{
/**
 * @uml.property  name="numCasa"
 */
private int numCasa;
  public Casa()
  {
  }
/**
 * Obtiene el numero de casa
 * @param Ninguno
 * @return  El numero de casa
 * @uml.property  name="numCasa"
 */
  public int getNumCasa()
  {return this.numCasa;}
/**
 * Establecer el numero de casa
 * @param numero  de casa a fijar
 * @return  Ninguno
 * @uml.property  name="numCasa"
 */
  public void setNumCasa(int numCas)
  {this.numCasa=numCas;}
}