package casarural;
import java.io.*;
public class Oferta implements Serializable
{
  private String numOferta="";
  private java.sql.Date diaIni;
  private java.sql.Date diaFin;
  private float precio;
  private String numReserva;
  private int tamano;
  private int numCasa;
  public Oferta()
  {
  }
  /**Obtiene el numero de la casa que se oferta
   * @param Ninguno
   * @return Numero de la casa que se oferta
   */
   public int getNumCasa()
   {
     return this.numCasa;
   }
   /**Establece el numero de la casa ofertada
    * @param Numero
    * @return Ninguno
    */
    public void setNumCasa(int numCasa)
    {
      this.numCasa=numCasa;
    }
  /**Obtiene el tamaño de la casa que se oferta
   * @param Ninguno
   * @return Tamaño de la casa que se oferta
   */
   public int getTamano()
   {
     return this.tamano;
   }
   /**Establece el tamano de la casa ofertada
    * @param Tamaño
    * @return Ninguno
    */
    public void setTamano(int tamano)
    {
      this.tamano=tamano;
    }
/**Obtiene el Numero de Oferta
  *@param Ninguno
  *@return Numero de Oferta
  */
  public String getNumOferta()
  {return this.numOferta;}
/**Establece el Numero de Oferta
  *@param Numero de Oferta
  *@return Ninguno
  */
  public void setNumOferta(String numOfe)
  {this.numOferta=numOfe;}
/**Obtener dia de inicio
  *@param Ninguno
  *@return Dia de inicio
  */
  public java.sql.Date getDiaIni()
  {return this.diaIni;}
/**Establecer dia de inicio
  *@param Dia de inicio
  *@return Ninguno
  */
  public void setDiaIni(java.sql.Date diaIn)
  {this.diaIni=diaIn;}
/**Obtener dia de fin
  *@param Ninguno
  *@return Dia de fin
  */
  public java.sql.Date getDiaFin()
  {return this.diaFin;}
/**Establecer dia de fin
  *@param Dia de fin
  *@return Ninguno
  */
  public void setDiaFin(java.sql.Date diaFi)
  {this.diaFin=diaFi;}
/**Obtener el precio
  *@param Ninguno
  *@return Precio
  */
  public float getPrecio()
  {return this.precio;}
/**Establecer el precio
  *@param Precio
  *@return Ninguno
  */
  public void setPrecio(float prec)
  {this.precio=prec;}
/**Obtener numero de reserva
  *@param Ninguno
  *@return Numero de reserva
  */
  public String getNumReserva()
  {return this.numReserva;}
 /**Establecer numero de reserva
  *@param Numero de reserva
  *@return Ninguno
  */
  public void setNumReserva(String numRes)
  {this.numReserva=numRes;}
  
  
  /**
   * Método que compara dos objetos para comprobar si son o no iguales.
   * En este caso, este método representa la redefinición del método equals
   * que heredan todas las clases java por la clase Object.
   * Esta redefinición ha sido realizada para adaptarse lo más fielmente posible
   * a los objetos de la clase Oferta.
   * 
   * @param obj objeto para comprobar
   * 
   * @return true sii el objeto es realmente la oferta
   */
  public boolean equals(Object obj){
	 boolean res = false;	 
	 if(obj instanceof Oferta){
		Oferta o = (Oferta)obj;
		res = 	(this.getNumCasa() == o.getNumCasa())&
				(this.getNumOferta().equals(o.getNumOferta()))&
				(this.getPrecio() == o.getPrecio())&
				(this.getNumReserva() == o.getNumReserva())&
				(this.getTamano() == o.getTamano())&
				(this.getDiaIni().getTime() == o.getDiaIni().getTime())&
				(this.getDiaFin().getTime() == o.getDiaFin().getTime());
	 }	 
	 return res;
  }
}