package casarural;
import java.io.*;
public class ReservaCompleta implements Serializable
{
  private String numOferta="";
  private float precio;
  public ReservaCompleta()
  {
  }
/**Obtener numero de oferta
  *@param Ninguno
  *@return Numero de Oferta
  */
  public String getNumOferta()
  {return this.numOferta;}
/**Establecer numero de oferta
  *@param Numero de oferta
  *@return Ninguno
  */
  public void setNumOferta(String numOfe)
  {this.numOferta=numOfe;}
/**Obtener Precio
  *@param Ninguno
  *@return Precio
  */
  public float getPrecio()
  {return this.precio;}
/**Establecer Precio
  *@param Precio
  *@return Ninguno
  */
  public void setPrecio(float prec)
  {this.precio=prec;}
  
}