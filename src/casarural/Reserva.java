package casarural;
import java.io.*;
public class Reserva implements Serializable
{
  private String numReserva;
  private int numCasa;
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
  /**Establece el Numero de reserva
  *@param Numero de reserva
  *@return Ninguno
  */
  public void setNumReserva(String res)
  {
    this.numReserva=res;
  }
  /**Obtiene el Numero de reserva
  *@param Ninguno
  *@return Numero de reserva
  */
  public String getNumReserva()
  {
    return this.numReserva;
  }
  /**Establece el numero de casa
  *@param Numero de casa
  *@return Ninguno
  */  
  public void setNumCasa(int casa)
  {
    this.numCasa=casa;
  }
  /**Obtiene el numero de casa
  *@param Ninguno
  *@return Numero de casa
  */
  public int getNumCasa()
  {
    return this.numCasa;
  }
  /**Establecer Precio total
  *@param Precio total
  *@return Ninguno
  */
  public void setPrecioTotal(float precio)
  {
    this.precioTotal=precio;
  }
  /**Obtener Precio total
  *@param Ninguno
  *@return Precio total
  */
  public float getPrecioTotal()
  {
    return this.precioTotal;
  }
}