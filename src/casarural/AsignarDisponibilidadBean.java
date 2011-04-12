package casarural;
import java.rmi.*;
import java.sql.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 * @author  kdreamer
 */
public class AsignarDisponibilidadBean 
{
  /**
 * @uml.property  name="logNeg"
 * @uml.associationEnd  
 */
InterfazFachada logNeg;
  /**
 * @uml.property  name="numCasa"
 */
private int numCasa;
  
  /**
 * @uml.property  name="diaIni"
 */
private String diaIni;
  private Date diaIniDate;
  /**
 * @uml.property  name="diaFin"
 */
private String diaFin;
  private Date diaFinDate;
  private float precio;
  /**
 * @uml.property  name="precioString"
 */
private String precioString;
  /**
 * @uml.property  name="codPropietario"
 */
private String codPropietario;
  
  /**Constructor de la clase AsignarDisponibilidadBean
  *@param Ninguno
  *@return Constructor
  */
  public AsignarDisponibilidadBean()
  {
    try
    {
      logNeg = ((InterfazFachada)Naming.lookup("rmi://localhost:1099/CasaRural"));
    }
    catch(Exception e) {System.out.println("Error al conseguir la logica del negocio: "+e.toString());}
  }
  
  
  /**
 * Asigna el codigo de la casa al de la instancia actual
 * @param El  codigo de la casa
 * @return  Ninguno
 * @uml.property  name="numCasa"
 */
  public void setNumCasa(int numCasa)
  {
    this.numCasa = numCasa;
  }
  
  /**
 * Devuelve el codigo de la casa
 * @param Ninguno
 * @return  El codigo de la casa
 * @uml.property  name="numCasa"
 */
   public int getNumCasa()
  {
    return numCasa;
  }
  
 /**
 * Asigna el precio de la casa al de la instancia actual
 * @param El  precio de la casa
 * @return  Ninguno
 * @uml.property  name="precioString"
 */
 public void setPrecioString(String i)
  {
    this.precioString = i;
    this.precio= Float.parseFloat(i);
  }
  
  /**
 * Devuelve el precio de la casa
 * @param Ninguno
 * @return  El precio de la casa
 * @uml.property  name="precioString"
 */
   public float getPrecioString()
  {
    return precio;
  }
  
  /**
 * Asigna el dia de inicio de la reserva al de la instancia actual
 * @param El  dia de inicio de la reserva
 * @return  Ninguno
 * @uml.property  name="diaIni"
 */
 public void setDiaIni(String i)
  {
    diaIni=i;
    StringTokenizer st = new StringTokenizer (i,"/");
    int dia = Integer.parseInt(st.nextToken());
    int mes = Integer.parseInt(st.nextToken()) - 1;
    int anio = Integer.parseInt(st.nextToken());
    GregorianCalendar gc = new GregorianCalendar(anio,mes,dia);
    diaIniDate = new Date(gc.getTime().getTime());
  }
  
  /**
 * Devuelve el dia de inicio de la reserva
 * @param Ninguno
 * @return  El dia de inicio de la reserva
 * @uml.property  name="diaIni"
 */
   public String getDiaIni()
  {
    return diaIni;
  }
  
  /**
 * Asigna el dia de fin de la reserva al de la instancia actual
 * @param El  dia de fin de la reserva
 * @return  Ninguno
 * @uml.property  name="diaFin"
 */
   public void setDiaFin(String i)
  {
    diaFin=i;
    StringTokenizer st = new StringTokenizer (i,"/");
    int dia = Integer.parseInt(st.nextToken());
    int mes = Integer.parseInt(st.nextToken()) - 1;
    int anio = Integer.parseInt(st.nextToken());
    GregorianCalendar gc = new GregorianCalendar(anio,mes,dia);
    diaFinDate = new Date(gc.getTime().getTime());
  }
  
  /**
 * Devuelve el dia de fin de la reserva
 * @param Ninguno
 * @return  El dia de fin de la reserva
 * @uml.property  name="diaFin"
 */
   public String getDiaFin()
  {
    return diaFin;
  }
  
  /**
 * Asigna el codigo de propietario al de la instancia actual
 * @param El  codigo de propietario
 * @return  Ninguno
 * @uml.property  name="codPropietario"
 */
   public void setCodPropietario(String codPropietario)
  {
    this.codPropietario = codPropietario;
  }
  
  /**
 * Devuelve el codigo de propietario
 * @param Ninguno
 * @return  El codigo de propietario
 * @uml.property  name="codPropietario"
 */
  public String getCodPropietario()
  {
    return codPropietario;
  }
  
  /**Devuelve el dia de inicio de la oferta en formato Date
  *@param Ninguno
  *@return El dia de inicio de la oferta
  */
  public Date getDiaIniAsDate()
  {
    return (this.diaIniDate);
  }
  
  /**Devuelve el dia de fin de la oferta en formato Date
  *@param Ninguno
  *@return El dia de fin de la oferta
  */
  public Date getDiaFinAsDate()
  {
    return (this.diaFinDate);
  }
  
  /**Devuelve un error si no se realiza la asignacion de la oferta
  *@param Ninguno
  *@return El error
  */
   public String setResultado()
  {
    String error="";
     try
    {
      logNeg.asignarOferta(numCasa, diaIniDate, diaFinDate, precio);
    } catch(Exception e) {error="Error: "+e.toString();
                          e.printStackTrace();
                          return error;}
    return error;
  }
  
  /**Devuelve un vector con los codigos de las casas del propietario
  *@param Ninguno
  *@return El vector de codigos
  */
  public Vector getResultado()
  {
    Vector resultado = null;
    try
    {
      resultado = logNeg.getCodigosCasas(codPropietario);
      return resultado;
    }
    catch(Exception e) {System.out.println("Error: "+e.toString());
                        e.printStackTrace();}
    return resultado;
  }
}
