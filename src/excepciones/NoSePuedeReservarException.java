package excepciones;
public class NoSePuedeReservarException extends Exception
{
  public NoSePuedeReservarException()
  {
    super();
  }
  /**Excepcion que salta si no de puede reservar una oferta
  *@param String
  *@return Ninguno
  */
  public NoSePuedeReservarException(String s)
  {
    super(s);
  }
}