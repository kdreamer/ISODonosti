package pruebas;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.sql.Date;

import casarural.GestorOfertas;
import casarural.GestorReservas;

import accesoDatos.GestorBD;



import junit.framework.TestCase;

/**
 * @author  Iraitz
 */
public class TestAnularReserva extends TestCase {

	/**
	 * @uml.property  name="gestorBD"
	 * @uml.associationEnd  
	 */
	private GestorBD gestorBD;
	/**
	 * @uml.property  name="gestorReservas"
	 * @uml.associationEnd  
	 */
	private GestorReservas gestorReservas;
	/**
	 * @uml.property  name="gestorOfertas"
	 * @uml.associationEnd  
	 */
	private GestorOfertas gestorOfertas;

	/**
	 * @param name
	 */
	public TestAnularReserva(String name) {
		super(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		gestorBD = GestorBD.getInstance();
		gestorReservas= GestorReservas.getInstance();
		gestorOfertas= GestorOfertas.getInstance();
		gestorBD.reiniciarBD();
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		this.gestorBD = null;
		this.gestorReservas=null;
		this.gestorOfertas=null;
		
	}
	private java.sql.Date fecha(int year,int month,int day){
		return new java.sql.Date((new GregorianCalendar(year, month-1, day)).getTime().getTime());
	}
	
	
	//GestorBD
	/**
	 * En esta prueba se verifica que el precio total de la Reserva con NumReserva=2
	 * sea igual a 150€.
	 */
	public void test_obtenerPrecioTotal(){
		int numReserva = 2;
		float esperado = 150;
		//Ejecutar test
		float obtenido;
		try {
			obtenido = gestorBD.obtenPrecioTotal(numReserva);
			assertEquals("No coincide el precio!",esperado,obtenido);
		} catch (SQLException e) {
			fail("No existe la reserva, pero el método funciona bien");	
		}
	}
	
	/**
	 * En esta prueba ,que se realiza sobre una Reserva que no existe,
	 * salta una Exception que indica que la reserva indicada no existe.
	 */
	public void test_obtenerPrecioTotal_ReservaNoExiste(){
		int numReserva = 9;
		float esperado = 0;
		//Ejecutar test
		float obtenido;
		try {
			obtenido = gestorBD.obtenPrecioTotal(numReserva);
			fail("Error en la obtencion de los datos de la BD!");
		} catch (SQLException e) {
			
			assertTrue(true);
		}
	
	}
	
	/**
	 * En esta prueba se verifica que, para la Reserva con NumReserva=2,
	 * devuelva 2, que es el numero de ofertas asociadas a dicha reserva.
	 */		
	public void test_actualizarOfertas(){
		
		int numReserva =2;
		int esperado = 2;
		//Ejecutar Test
		try{
			int obtenido = gestorBD.actualizarOfertas(numReserva);
			assertEquals("Error en la Actualización de la BD!",esperado,obtenido);
		}
		catch (SQLException e) {
			fail("No existe la reserva, pero el método funciona bien");
			
			
		}
		
		
	}
	/**
	 * En esta prueba se verifica que, para la Reserva con NumReserva=14,
	 * que no existe, salte una Exception que indica que esa Reserva no tiene 
	 * ninguna oferta asociada.
	 */	
	public void test_actualizarOfertas_ReservaSinOferta(){
		
		int numReserva =14;
		//GregorianCalendar fecha  =  new GregorianCalendar(2008, 4, 23);
		
		int esperado =0;
		//Ejecutar Test
		try{
			int obtenido = gestorBD.actualizarOfertas(numReserva);
			assertEquals("Error en la Actualización de la BD!",esperado,obtenido);
		}
		catch (SQLException e) {
			fail(e.getMessage());
			
			
		}
		
		
	}
	/**
	 * En esta prueba se verifica que, para la Reserva con NumReserva=3,
	 * devuelva 1, que es el número de filas afectadas en la BD.
	 */	
	public void test_ActualizarReservas(){
		int numReserva =3;
		int esperado = 1;
		//Ejecutar Test
		try{
			int obtenido = gestorBD.actualizarReservas(numReserva);
			assertEquals("Error al Actualizar la BD!",esperado,obtenido);
		}
		catch (SQLException e) {
			fail("No existe la reserva, pero el método funciona bien");
			}
	}
	
	/**
	 * En esta prueba se verifica que, para la Reserva con NumReserva=9,
	 * que no existe, salte una Exception que indica que esa reserva no existe.
	 */	
	public void test_ActualizarReservas_ReservaNoexiste(){
		int numReserva =9;
		int esperado = 0;
		//Ejecutar Test
		try{
			int obtenido = gestorBD.actualizarReservas(numReserva);
			assertEquals("Error al Actualizar la BD!",esperado,obtenido);
		}
		catch (SQLException e) {
			fail("No existe la reserva, pero el método funciona bien");
			}
	}
	
	/**
	 * En esta prueba se verifica que, para la Reserva con NumReserva=4,
	 * devuelva la fecha (2008, 5, 16), que es la correspondiente al dia de inicio
	 * de la oferta que antes empieza de las asociadas a esa reserva.
	 */	
	public void test_obtenDiaIniMinBD(){
		int numReserva=4;
		GregorianCalendar esperado  =  new GregorianCalendar(2008, 4, 12);
		//Ejecutar Test
		GregorianCalendar obtenido;
		try {
			obtenido = gestorBD.obtenDiaIniMinBD(numReserva);
			assertEquals("Error en la obtencion de los datos de la BD!",esperado,obtenido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
		}
		
		
	}
	/**
	 * En esta prueba se verifica que, para la Reserva con NumReserva=9,
	 * que no existe, salte una Exception que indica que esa reserva no existe.
	 */	
	public void test_obtenDiaIniMinBD_ReservaNoExiste(){
		int numReserva=9;
		GregorianCalendar esperado  =  new GregorianCalendar(0, 0, 0);
		//Ejecutar Test

		try{
			GregorianCalendar obtenido = gestorBD.obtenDiaIniMinBD(numReserva);
			fail("No existe la reserva, pero el método funciona bien");
		}
		catch (SQLException e) {
			assertTrue(true);
			}
	}


	//Gestor Ofertas
	/**
	 * En esta prueba se verifica que, para la Reserva con NumReserva=5,
	 * devuelva la fecha (2008, 5, 5), que es la correspondiente al dia de inicio
	 * de la oferta que antes empieza de las asociadas a esa reserva.
	 */	
	public void test_ObtenDiaIniMin(){
		int numReserva=5;
		GregorianCalendar esperado  =  new GregorianCalendar(2008, 4, 5);
		//Ejecutar Test
		GregorianCalendar obtenido;
		try {
			obtenido = gestorOfertas.obtenDiaIniMin(numReserva);
			assertEquals("La fecha no coincide!",esperado,obtenido);
		} catch (Exception e) {
			
			fail(e.getMessage());
		}
		
	}
	
	/**
	 * En esta prueba se verifica que, para la Reserva con NumReserva=9,
	 * que no existe, salte una Exception que indica que esa reserva no existe.
	 */	
	public void test_ObtenDiaIniMin_ReservaNoExiste(){
		int numReserva=25;
		GregorianCalendar esperado  =  new GregorianCalendar(0, 0, 0);
		//Ejecutar Test
		GregorianCalendar obtenido;
		try {
			obtenido = gestorOfertas.obtenDiaIniMin(numReserva);
			fail("La reserva existe en BD!");
		} catch (Exception e) {
			
			assertTrue(true);
		}
		
	}
	
	//Gestor Reservas
	
	/**
	 * En esta prueba se verifica que, para la Reserva con NumReserva=5, se anule.
	 * Para ello se compara el estado anterior y posterior de la BD.
	 * 
	 */
	public void test_AnularReserva(){
		int numReserva=5;
		try {
				String consultaobt = "SELECT COUNT(*) FROM reserva WHERE Anulado=1";
				ResultSet rsobt = gestorBD.hacerConsulta(consultaobt);
				rsobt.next();
				int esperado = rsobt.getInt(1);
				gestorReservas.anularReserva(numReserva);
				String consultaesp = "SELECT COUNT(*) FROM reserva WHERE Anulado=1";
				ResultSet rsesp = gestorBD.hacerConsulta(consultaesp);
				rsesp.next();
				int obtenido = rsesp.getInt(1);
			    assertEquals("No se ha anulado la Reserva",esperado, obtenido-1);
		} catch (Exception e) {
			
			fail(e.getMessage());
		}
		
		
	}
	
	/**
	 * En esta prueba se verifica que, el estado anterior y posterior de la BD después
	 *	de Anular Reserva de una Reserva que no existe, son iguales.
	 * 
	 */
	public void test_AnularReservaNoExiste(){
		int numReserva =25;
		try {
			String consultaobt = "SELECT COUNT(*) FROM reserva WHERE Anulado=1";
			ResultSet rsobt = gestorBD.hacerConsulta(consultaobt);
			rsobt.next();
			int esperado = rsobt.getInt(1);
			gestorReservas.anularReserva(numReserva);
			String consultaesp = "SELECT COUNT(*) FROM reserva WHERE Anulado=1";
			ResultSet rsesp = gestorBD.hacerConsulta(consultaesp);
			rsesp.next();
			int obtenido = rsesp.getInt(1);
		    fail("La reserva no existe!");
	} catch (Exception e) {
		
		assertTrue(true);
	}
	
	
}
		
}
