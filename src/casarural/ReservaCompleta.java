package casarural;
import java.io.*;
/**
 * @author  kdreamer
 */
public class ReservaCompleta implements Serializable
{
  /**
 * @uml.property  name="numOferta"
 */
private String numOferta="";
  /**
 * @uml.property  name="precio"
 */
private float precio;
  public ReservaCompleta()
  {
  }
/**
 * Obtener numero de oferta
 * @param Ninguno
 * @return  Numero de Oferta
 * @uml.property  name="numOferta"
 */
  public String getNumOferta()
  {return this.numOferta;}
/**
 * Establecer numero de oferta
 * @param Numero  de oferta
 * @return  Ninguno
 * @uml.property  name="numOferta"
 */
  public void setNumOferta(String numOfe)
  {this.numOferta=numOfe;}
/**
 * Obtener Precio
 * @param Ninguno
 * @return  Precio
 * @uml.property  name="precio"
 */
  public float getPrecio()
  {return this.precio;}
/**
 * Establecer Precio
 * @param Precio
 * @return  Ninguno
 * @uml.property  name="precio"
 */
  public void setPrecio(float prec)
  {this.precio=prec;}
  
}