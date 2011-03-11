package casarural;
/**
 * @author Grupo 2
 */
import java.util.*;
import java.sql.Date;
import java.sql.SQLException;
import java.io.*;
import java.rmi.*;
import configuracion.Config;


import excepciones.NoSePuedeReservarException;
import gui.IUDevolverDinero;
public final class GestorReservas
{
  private static Config conf = Config.getInstance();
  protected static int numReserva = 0;
  accesoDatos.GestorBD gbd;
  GestorOfertas gof;
  private static String ficheroNumReserva =conf.getFicheroReservas();
    
  private static GestorReservas elGestorReservas; 
  
  private GestorReservas() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
  {
    gbd = accesoDatos.GestorBD.getInstance();
    gof = GestorOfertas.getInstance();
    GestorReservas.inicializar();
   
  }
  /**Devuelve una instancia del gestor de reservas
  *@param Ninguno
  *@return El gestor de reservas
 * @throws SQLException 
 * @throws ClassNotFoundException 
 * @throws IllegalAccessException 
 * @throws InstantiationException 
  */	
  public static GestorReservas getInstance() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
  {
	  if (elGestorReservas == null)
		  elGestorReservas =new GestorReservas();
      return elGestorReservas;
  }
  /**Inicializa las reservas con el numero de reserva
  *@param Ninguno
  *@return Ninguno
  */  
  protected static void inicializar()
  {
    try{GestorReservas.numReserva=GestorReservas.cargarNumReserva();}
    catch(Exception e){System.out.println("Error cargando el n�mero de reserva: "+e.toString());}
  }
  /**Accede al fichero que contiene el numero de reserva
  *@param Ninguno
  *@return El numero de reserva
  */  
  protected static int cargarNumReserva() throws FileNotFoundException,IOException
  {
    BufferedReader entrada=new BufferedReader(new FileReader(ficheroNumReserva));
    int numero=Integer.parseInt(entrada.readLine());
    entrada.close();
    return numero;
  }
  /**Guarda en el fichero el numero de reserva actual
  *@param Ninguno
  *@return Ninguno
  */  
  protected static void salvarNumReserva() throws FileNotFoundException,IOException
  {
        PrintWriter numero=new PrintWriter(new FileWriter(ficheroNumReserva));
        Integer num=new Integer(GestorReservas.numReserva);
        numero.print(num.toString());
        numero.close();
  }
  //crea el siguiente numero para el siguiente reserva
  private int crearNumReserva(){
    GestorReservas.numReserva = GestorReservas.numReserva+1;
    return GestorReservas.numReserva;
  }
/**Indica que el objeto se puede descargar de la memoria
  *@param Ninguno
  *@return Ninguno
  */
  protected void finalize()
  {
    try{GestorReservas.salvarNumReserva();}catch(Exception e){;}
  }
  /**Hace la reserva en los dias que se le facilitan
  *@param Dia de Inicio, Dia de Fin, numero de casa y telefono
  *@return Una reserva
  */  
  public Reserva reservar(java.sql.Date diaIni, java.sql.Date diaFin, int numCasa, String numTfnoReserva)throws NoSePuedeReservarException
  {

    //preguntar el vector de las ofertas dentro de las fechas (diaIni-diaFin)
    //si no existen catch la exception
    Vector ofertas = new Vector();
    OfertasEnMemoriaPrincipal oferts=new OfertasEnMemoriaPrincipal();
try{  
        ofertas =gbd.seleccionarReservas(diaIni,diaFin,numCasa);
  }
catch(NoSePuedeReservarException ex){
  throw ex;
  }
  catch(Exception exc){exc.printStackTrace();}

    //a�adir todas las ofertas en la memoria principal
    Iterator iter1 = ofertas.iterator();
    while(iter1.hasNext()){
      Oferta next = (Oferta)iter1.next();
      String numOferta = next.getNumOferta();
      java.sql.Date diaIniOtro = next.getDiaIni();
      java.sql.Date diaFinOtro = next.getDiaFin();
      float precio = next.getPrecio();
      oferts.anadirReserva(diaIniOtro,diaFinOtro,numOferta,precio);
    }

    //preguntar de volver el vector de la reservaCompleta
    //sino existe catch la excepcion
    Vector resCompleta = new Vector();
try{ resCompleta = oferts.obtenerReservaCompleta(diaIni,diaFin);}
catch(NoSePuedeReservarException ex){throw ex;}

    // c�lculo del precio total
    Iterator iter2 = resCompleta.iterator();
    float precio = 0;
    Vector reservasTotales= new Vector();
    while(iter2.hasNext()){
      ReservaCompleta next = (ReservaCompleta)iter2.next();
      reservasTotales.addElement(next.getNumOferta());
      precio = precio+next.getPrecio();
    }
    // generacion del numReserva
     this.crearNumReserva();

    Integer otro=new Integer(GestorReservas.numReserva);
    //ejecutar la transaccion
    try{gbd.transaccionDeReserva(reservasTotales,otro.toString(),numTfnoReserva,precio);}
    catch(Exception e){;}

    Reserva res=new Reserva(otro.toString(),numCasa,precio);
    //volver numReserv
    try{GestorReservas.salvarNumReserva();}catch(Exception e){;}
    return res;
    

    }

  /**
   * El gestor reserva, con anular reserva va a GBD , obtiene el precio total de la reserva, gbd.preciototal(numreserva).
	luego llama al gestor ofertas en el q tiene q haber un metodo que llame a la BD para obtener la 
	fecha minima de la reserva(entre todas las ofertas de la reserva) .
	devuelve la fecha inicio si han pasado menos de 10 dias entonces nueva IU devolver dinero
	sino nada llamando al GBD se actualiza oferta y actualizar reserva
   * @param numReserva
   * @return 20% del Precio Total
   */
	public float anularReserva(Integer numReserva) throws SQLException {
		
		float precioTotal;
		float devolucion=0;
		
			java.util.GregorianCalendar diaIniMin = gof.obtenDiaIniMin(numReserva);
			// comparar fechas boolean b = comparacion
			java.util.GregorianCalendar hoy = new java.util.GregorianCalendar();
			hoy.add(hoy.DATE, 10);
			boolean b = (diaIniMin.after(hoy) || diaIniMin.equals(hoy));
			if (b) {
				// devolver dinero
				int pagado=gbd.estaPagado(numReserva);
				if (pagado==1){
					precioTotal = gbd.obtenPrecioTotal(numReserva);
					devolucion=((precioTotal * 20) / 100);
				}
				
			}

			gbd.actualizarOfertas(numReserva);
			gbd.actualizarReservas(numReserva);
			return devolucion;

	}
}