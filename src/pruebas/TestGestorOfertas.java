package pruebas;

import java.util.GregorianCalendar;
import java.util.Vector;
import java.sql.Date;
import java.sql.SQLException;

import casarural.GestorOfertas;
import casarural.Oferta;
import junit.framework.TestCase;

/**
 * @author  kdreamer
 */
public class TestGestorOfertas extends TestCase {
	
	/**
	 * @uml.property  name="gestorOfertas"
	 * @uml.associationEnd  
	 */
	private GestorOfertas gestorOfertas;
	
	public TestGestorOfertas(String nombre) {
		super(nombre);
	}

	public void setUp() {
		try {
			this.gestorOfertas = GestorOfertas.getInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tearDown() {
		this.gestorOfertas = null;
	}
	/**
	 * En esta prueba se concatenan 2 ofertas 
	 */
	public void test_Concatenar_2_Ofertas(){
		
		//Entrada
		Vector entrada = new Vector();

		Oferta o1 = new Oferta();
		o1.setNumCasa(1);
		o1.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o1.setDiaFin(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o1.setPrecio(300);
		
		Oferta o2 = new Oferta();
		o2.setNumCasa(1);
		o2.setDiaIni(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o2.setDiaFin(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o2.setPrecio(200);
		
		entrada.add(o1);
		entrada.add(o2);

		//Resultado Esperado
		Vector esperado = (Vector)entrada.clone();
		Oferta o3 = new Oferta();
		o3.setNumCasa(1);
		o3.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o3.setDiaFin(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o3.setPrecio(500);		
		esperado.add(o3);
		
		//Ejecutar test
		Vector obtenido = (Vector)gestorOfertas.concatenarOfertas(entrada);
		assertEquals("Error en la concatenacion!", esperado, obtenido);	
	}
	/**
	 * En esta prueba se concatenan 3 ofertas 
	 */
	public void test_Concatenar_3_Ofertas(){
		
		//Entrada
		Vector entrada = new Vector();
		
		Oferta o1 = new Oferta();
		o1.setNumCasa(1);
		o1.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o1.setDiaFin(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o1.setPrecio(300);
		
		Oferta o2 = new Oferta();
		o2.setNumCasa(1);
		o2.setDiaIni(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o2.setDiaFin(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o2.setPrecio(200);
		
		Oferta o3 = new Oferta();
		o3.setNumCasa(1);
		o3.setDiaIni(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o3.setDiaFin(new Date((new GregorianCalendar(2007, 2, 8)).getTime().getTime()));
		o3.setPrecio(50);
		
		entrada.add(o1);
		entrada.add(o2);
		entrada.add(o3);

		//Vector Esperado
		Vector esperado = (Vector)entrada.clone();
		Oferta o4 = new Oferta();
		o4.setNumCasa(1);
		o4.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o4.setDiaFin(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o4.setPrecio(500);		
		esperado.add(o4);
		
		Oferta o5 = new Oferta();
		o5.setNumCasa(1);
		o5.setDiaIni(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o5.setDiaFin(new Date((new GregorianCalendar(2007, 2, 8)).getTime().getTime()));
		o5.setPrecio(250);		
		esperado.add(o5);
		
		Oferta o6 = new Oferta();
		o6.setNumCasa(1);
		o6.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o6.setDiaFin(new Date((new GregorianCalendar(2007, 2, 8)).getTime().getTime()));
		o6.setPrecio(550);		
		esperado.add(o6);
		
		//Ejecutar test
		Vector obtenido = (Vector)gestorOfertas.concatenarOfertas(entrada);
		assertEquals("Error en la concatenacion!", esperado, obtenido);	
	}
	/**
	 * En esta prueba NO se concatenan 2 ofertas porque las fechas estan solapadas
	 */
	public void test_No_Concatenar_2_Ofertas_Fecha(){
		
		//Entrada
		Vector entrada = new Vector();
		
		Oferta o1 = new Oferta();
		o1.setNumCasa(1);
		o1.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o1.setDiaFin(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o1.setPrecio(300);
		
		Oferta o2 = new Oferta();
		o2.setNumCasa(1);
		o2.setDiaIni(new Date((new GregorianCalendar(2007, 2, 2)).getTime().getTime()));
		o2.setDiaFin(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o2.setPrecio(200);
		
		entrada.add(o1);
		entrada.add(o2);
		
		//Vector Esperado
		Vector esperado = (Vector)entrada.clone();
		
		//Ejecutar test
		Vector obtenido = (Vector)gestorOfertas.concatenarOfertas(entrada);
		assertEquals("Error en la concatenacion!", esperado, obtenido);
	}
	/**
	 * En esta prueba NO se concatenan 2 ofertas porque pertenecen a diferentes casas 
	 */
	public void test_No_Concatenar_2_Ofertas_NumCasa(){
		
		//Entrada
		Vector entrada = new Vector();

		Oferta o1 = new Oferta();
		o1.setNumCasa(1);
		o1.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o1.setDiaFin(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o1.setPrecio(300);
		
		Oferta o2 = new Oferta();
		o2.setNumCasa(2);
		o2.setDiaIni(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o2.setDiaFin(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o2.setPrecio(200);
		
		entrada.add(o1);
		entrada.add(o2);

		//Resultado Esperado
		Vector esperado = (Vector)entrada.clone();
		
		//Ejecutar test
		Vector obtenido = (Vector)gestorOfertas.concatenarOfertas(entrada);
		assertEquals("Error en la concatenacion!", esperado, obtenido);	
	}
	
	/**
	 * En esta prueba se prueba el comportamiento de la concatenacion de una sola oferta 
	 */
	public void test_ConcatenarOfertas_1_Oferta(){
		
		//Entrada
		Vector entrada = new Vector();
		
		Oferta o1 = new Oferta();
		o1.setNumCasa(1);
		o1.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o1.setDiaFin(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o1.setPrecio(300);
		
		entrada.add(o1);

		//Vector Esperado
		Vector esperado = (Vector)entrada.clone();

		//Ejecutar test
		Vector obtenido = (Vector)gestorOfertas.concatenarOfertas(entrada);
		assertEquals("Error en la concatenacion!", esperado, obtenido);	
	}
	/**
	 * En esta prueba se comprueba la ordenacion de ofertas por duracion descendente en dias de las mismas
	 */
	public void test_QuickSort_Duracion_Oferta(){
		//Entrada
		Vector entrada = new Vector();
		
		//2 noches
		Oferta o1 = new Oferta();
		o1.setNumCasa(1);
		o1.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o1.setDiaFin(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o1.setPrecio(300);
		
		//4 noches
		Oferta o2 = new Oferta();
		o2.setNumCasa(1);
		o2.setDiaIni(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o2.setDiaFin(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o2.setPrecio(200);
		
		//1 noche
		Oferta o3 = new Oferta();
		o3.setNumCasa(1);
		o3.setDiaIni(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o3.setDiaFin(new Date((new GregorianCalendar(2007, 2, 8)).getTime().getTime()));
		o3.setPrecio(50);
		
		//6 noches
		Oferta o4 = new Oferta();
		o4.setNumCasa(1);
		o4.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o4.setDiaFin(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o4.setPrecio(500);		
		
		//5 noches
		Oferta o5 = new Oferta();
		o5.setNumCasa(1);
		o5.setDiaIni(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o5.setDiaFin(new Date((new GregorianCalendar(2007, 2, 8)).getTime().getTime()));
		o5.setPrecio(250);		
		
		//7 noches
		Oferta o6 = new Oferta();
		o6.setNumCasa(1);
		o6.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o6.setDiaFin(new Date((new GregorianCalendar(2007, 2, 8)).getTime().getTime()));
		o6.setPrecio(550);		
		
		entrada.add(o1);
		entrada.add(o2);
		entrada.add(o3);
		entrada.add(o4);
		entrada.add(o5);
		entrada.add(o6);
		
		//Esperado
		Vector esperado = new Vector();
		esperado.add(o6);
		esperado.add(o4);
		esperado.add(o5);
		esperado.add(o2);
		esperado.add(o1);
		esperado.add(o3);
		
		//Ejecutar test
		Vector obtenido = (Vector)gestorOfertas.quickSort_Dias(0, entrada.size() -1, entrada);
		assertEquals("Error en el orden!", esperado, obtenido);
	}
	/**
	 * En esta prueba se comprueba la ordenacion de ofertas por precio ascendente de las mismas
	 */
	public void test_QuickSort_Precio_Oferta(){
		//Entrada
		Vector entrada = new Vector();
		
		//200€
		Oferta o1 = new Oferta();
		o1.setNumCasa(1);
		o1.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o1.setDiaFin(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o1.setPrecio(200);
		
		//400€
		Oferta o2 = new Oferta();
		o2.setNumCasa(1);
		o2.setDiaIni(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o2.setDiaFin(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o2.setPrecio(400);
		
		//100€
		Oferta o3 = new Oferta();
		o3.setNumCasa(1);
		o3.setDiaIni(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o3.setDiaFin(new Date((new GregorianCalendar(2007, 2, 8)).getTime().getTime()));
		o3.setPrecio(100);
		
		//600€
		Oferta o4 = new Oferta();
		o4.setNumCasa(1);
		o4.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o4.setDiaFin(new Date((new GregorianCalendar(2007, 2, 7)).getTime().getTime()));
		o4.setPrecio(600);		
		
		//500€ noches
		Oferta o5 = new Oferta();
		o5.setNumCasa(1);
		o5.setDiaIni(new Date((new GregorianCalendar(2007, 2, 3)).getTime().getTime()));
		o5.setDiaFin(new Date((new GregorianCalendar(2007, 2, 8)).getTime().getTime()));
		o5.setPrecio(500);		
		
		//700€
		Oferta o6 = new Oferta();
		o6.setNumCasa(1);
		o6.setDiaIni(new Date((new GregorianCalendar(2007, 2, 1)).getTime().getTime()));
		o6.setDiaFin(new Date((new GregorianCalendar(2007, 2, 8)).getTime().getTime()));
		o6.setPrecio(700);		
		
		entrada.add(o1);
		entrada.add(o2);
		entrada.add(o3);
		entrada.add(o4);
		entrada.add(o5);
		entrada.add(o6);
		
		//Esperado
		Vector esperado = new Vector();
		esperado.add(o3);
		esperado.add(o1);
		esperado.add(o2);
		esperado.add(o5);
		esperado.add(o4);
		esperado.add(o6);
		
		//Ejecutar test
		Vector obtenido = (Vector)gestorOfertas.quickSort_Precio(0, entrada.size() - 1, entrada);
		assertEquals("Error en el orden!", esperado, obtenido);
	}
	/**
	 * En esta prueba se comprueba la integracion entre los metodos dentro del metodo buscar ofertas
	 * de manera que se consultan las ofertas de casas no reservadas entre el 01-02-2005 y el 01-02-2006
	 * y con un numero de habitaciones de 5 ordenadas por precio
	 */
	public void test_Buscar_Ofertas_Por_Precio(){
//		 Entrada
		Vector esperado = new Vector();
		Oferta of;
//		 Resultado Esperado
					
		of = new Oferta();
		of.setPrecio(45);
		of.setDiaFin(fecha(2006,4,16));
		of.setDiaIni(fecha(2006,4,10));
		of.setNumCasa(7);
		of.setNumOferta("107");
		esperado.add(of);
		
		// Ejecutar test
		Vector obtenido = (Vector) gestorOfertas.buscarOfertas(fecha(2005,5,15), fecha(2006,4,16),0, 0, 5, 0, true);
		assertEquals("Error en la busqueda de ofertas por precio", esperado, obtenido);
	}
	/**
	 * En esta prueba se comprueba la integracion entre los metodos dentro del metodo buscar ofertas
	 * de manera que se consultan las ofertas de casas no reservadas entre el 01-02-2005 y el 01-02-2006
	 * y con un numero de habitaciones de 5 ordenadas por duracion
	 */
	public void test_Buscar_Ofertas_Por_Duracion(){
		//Entrada
		
		Vector esperado = new Vector();
		Oferta of;
		//Resultado Esperado	
		
		of = new Oferta();
		of.setPrecio(45);
		of.setDiaFin(fecha(2006,4,16));
		of.setDiaIni(fecha(2006,4,10));
		of.setNumCasa(7);
		of.setNumOferta("107");
		esperado.add(of);
		
				
		// Ejecutar test
		//Vector obtenido = (Vector) gestorOfertas.buscarOfertas( fecha(2005,5,15), fecha(2006,4,16),0, -1,5, -1, false);
		Vector obtenido = (Vector) gestorOfertas.buscarOfertas(fecha(2005,5,15), fecha(2006,4,16), 0, 0, 5, 0, false);
		assertEquals("Error en la busqueda de ofertas por duracion", esperado, obtenido);
	}
	private java.sql.Date fecha(int year,int month,int day){
		return new java.sql.Date((new GregorianCalendar(year, month-1, day)).getTime().getTime());
	}
}
