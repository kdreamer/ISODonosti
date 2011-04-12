package casarural;
import java.rmi.*;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

/**
 * @author  kdreamer
 */
public class ReservarCasaBean 
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
  private String diaFin;
  private Date diaFinDate;
  /**
 * @uml.property  name="numNoches"
 */
private int numNoches;
  private long numNochesM;
  /**
 * @uml.property  name="numTfnoReserva"
 */
private String numTfnoReserva;
  
 /**Constructor de la clase ReservarCasaBean
  *@param Ninguno
  *@return Constructor
  */
  public ReservarCasaBean()
  {
    try
    {
      logNeg = ((InterfazFachada)Naming.lookup("rmi://localhost:1099/CasaRural"));
    }
    catch(Exception e) {System.out.println("Error al conseguir la logica del negocio: "+e.toString());}
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
 * Asigna el codigo de la casa al de la instancia actual
 * @param El  codigo de la casa
 * @return  Ninguno
 * @uml.property  name="numCasa"
 */
   public void setNumCasa(int n)
  {
    numCasa=n;
    System.out.println("numCasa set to "+ n);
  }
  /**
 * Devuelve el dia de inicio de la reserva
 * @param Ninguno
 * @return  El dia de inicio de la reserva
 * @uml.property  name="diaIni"
 */
  public String getDiaIni ()
  {
    return diaIni;
  }
  /**
 * Asigna el dia de fin de la reserva al de la instancia actual
 * @param El  dia de fin de la reserva
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
    
    System.out.println("diaIni set to "+ diaIni);
    System.out.println("diaIniDate set to "+ diaIniDate.toString());
  }
  /**
 * Devuelve el numero de noches
 * @param Ninguno
 * @return  El numero de noches
 * @uml.property  name="numNoches"
 */
   public int getNumNoches()
  {
    return numNoches;
  }
  /**
 * Asigna el numero de noches
 * @param El  numero de noches
 * @return  Ninguno
 * @uml.property  name="numNoches"
 */
  public void setNumNoches(int s)
  {
    numNoches=s;
    numNochesM = 1000*60*60*24*s;
    System.out.println("numNoches set to "+ numNoches);
    System.out.println("numNochesM set to "+ numNochesM);
  }
  /**
 * Devuelve el numero de telefono
 * @param Ninguno
 * @return  El numero de telefono
 * @uml.property  name="numTfnoReserva"
 */
  public String getNumTfnoReserva()
  {
    return numTfnoReserva;
  }
  /**
 * Asigna el numero de telefono
 * @param El  numero de telefono
 * @return  Ninguno
 * @uml.property  name="numTfnoReserva"
 */
  public void setNumTfnoReserva(String t)
  {
    numTfnoReserva=t;
    System.out.println("numTfnoReserva set to "+ numTfnoReserva);
  }  
  
 /**Devuelve una reserva realizada con los datos introducidos
  *@param Ninguno
  *@return La reserva
  */
  public Reserva getResultado()
  {
    Reserva res = null;
    try
    {
      diaFinDate = new Date(diaIniDate.getTime()+numNochesM);
      res = logNeg.reservar(diaIniDate, diaFinDate, numCasa, numTfnoReserva);
    }
    catch (Exception e) {System.out.println("Error: "+e.toString());
                         e.printStackTrace();}
    return res;
  }
 
}