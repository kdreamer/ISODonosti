package casarural;
import java.io.*;
/**
 * @author  kdreamer
 */
public class Reserva implements Serializable
{
  /**
 * @uml.property  name="numReserva"
 */
private String numReserva;
  /**
 * @uml.property  name="numCasa"
 */
private int numCasa;
  /**
 * @uml.property  name="precioTotal"
 */
private float precioTotal;
  public Reserva()
  {
  }
  public Reserva(String res,int casa,float precio)
  {
  this.numReserva=res;
  this.numCasa=casa;
  this.precioTotal=precio;
  }
  /**
 * Establece el Numero de reserva
 * @param Numero  de reserva
 * @return  Ninguno
 * @uml.property  name="numReserva"
 */
  public void setNumReserva(String res)
  {
    this.numReserva=res;
  }
  /**
 * Obtiene el Numero de reserva
 * @param Ninguno
 * @return  Numero de reserva
 * @uml.property  name="numReserva"
 */
  public String getNumReserva()
  {
    return this.numReserva;
  }
  /**
 * Establece el numero de casa
 * @param Numero  de casa
 * @return  Ninguno
 * @uml.property  name="numCasa"
 */  
  public void setNumCasa(int casa)
  {
    this.numCasa=casa;
  }
  /**
 * Obtiene el numero de casa
 * @param Ninguno
 * @return  Numero de casa
 * @uml.property  name="numCasa"
 */
  public int getNumCasa()
  {
    return this.numCasa;
  }
  /**
 * Establecer Precio total
 * @param Precio  total
 * @return  Ninguno
 * @uml.property  name="precioTotal"
 */
  public void setPrecioTotal(float precio)
  {
    this.precioTotal=precio;
  }
  /**
 * Obtener Precio total
 * @param Ninguno
 * @return  Precio total
 * @uml.property  name="precioTotal"
 */
  public float getPrecioTotal()
  {
    return this.precioTotal;
  }
}