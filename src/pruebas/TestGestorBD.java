/**
 *
 */
package pruebas;

import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.sql.Date;

import accesoDatos.GestorBD;
import casarural.Oferta;

import junit.framework.TestCase;

/**
 * @author JORGE
 *
 */
public class TestGestorBD extends TestCase {

	private GestorBD gestorBD;

	/**
	 * @param name
	 */
	public TestGestorBD(String name) {
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
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		this.gestorBD = null;
	}
	private java.sql.Date fecha(int year,int month,int day){
		return new java.sql.Date((new GregorianCalendar(year, month-1, day)).getTime().getTime());
	}

	/**
	 * En esta prueba se verifica que salen todas las ofertas no reservadas sin ningun parametro
	 *	y ordenadas por precio.
	 * Esta prueba debe realizarse con la base de datos inicial
	 */
	public void test_seleccionarCasasDorWC_BD_Ord_Precio() {

		// Entrada
		int numDormitorios = 0 , numBanos = 0,numMinDias = 0;
		boolean orden = true;
		java.sql.Date diaIni =  new java.sql.Date((new GregorianCalendar(2004, 1, 1)).getTime().getTime());
		java.sql.Date diaFin  = new java.sql.Date((new GregorianCalendar(2007, 1, 1)).getTime().getTime());
		float precio = 0;

		Vector esperado = new Vector();
		Oferta of;
//		 Resultado Esperado
		of = new Oferta();
		of.setPrecio(3);
		of.setDiaFin(fecha(2005,5,15));
		of.setDiaIni(fecha(2005,5,9));
		of.setNumCasa(5);
		of.setNumOferta("102");
		esperado.add(of);

		of = new Oferta();
		of.setPrecio(25);
		of.setDiaFin(fecha(2006,4,16));
		of.setDiaIni(fecha(2006,4,10));
		of.setNumCasa(4);
		of.setNumOferta("108");
		esperado.add(of);

		of = new Oferta();
		of.setPrecio(33);
		of.setDiaFin(fecha(2005,5,20));
		of.setDiaIni(fecha(2005,5,15));
		of.setNumCasa(4);
		of.setNumOferta("103");
		esperado.add(of);

		of = new Oferta();
		of.setPrecio(34);
		of.setDiaFin(fecha(2006,4,25));
		of.setDiaIni(fecha(2006,4,23));
		of.setNumCasa(4);
		of.setNumOferta("106");
		esperado.add(of);

		of = new Oferta();
		of.setPrecio(45);
		of.setDiaFin(fecha(2006,4,16));
		of.setDiaIni(fecha(2006,4,10));
		of.setNumCasa(7);
		of.setNumOferta("107");
		esperado.add(of);

		of = new Oferta();
		of.setPrecio(80);
		of.setDiaFin(fecha(2006,5,24));
		of.setDiaIni(fecha(2006,5,21));
		of.setNumCasa(4);
		of.setNumOferta("110");
		esperado.add(of);

		of = new Oferta();
		of.setPrecio(110);
		of.setDiaFin(fecha(2006,5,25));
		of.setDiaIni(fecha(2006,5,20));
		of.setNumCasa(7);
		of.setNumOferta("109");
		esperado.add(of);

		// Ejecutar test
		Vector obtenido = (Vector) gestorBD.seleccionarCasasDorWC(numDormitorios, numBanos, diaIni, diaFin, precio, numMinDias, orden);
		assertEquals("Error en la obtencion de los datos BD!", esperado, obtenido);
	}
	/**
	 * En esta prueba se verifica que salen todas las ofertas no reservadas entre el 01-02-2005 y el 01-02-2006
	 *	y ordenadas por precio.
	 * Esta prueba debe realizarse con la base de datos inicial
	 */
	public void test_seleccionarCasasDorWC_BD_Fechas_Ord_Precio() {

		// Entrada
		int numDormitorios = 0, numBanos = 0,numMinDias = 0;
		boolean orden = true;
		java.sql.Date diaIni =  new java.sql.Date((new GregorianCalendar(2005, 1, 1)).getTime().getTime());
		java.sql.Date diaFin  = new java.sql.Date((new GregorianCalendar(2006, 1, 1)).getTime().getTime());
		float precio = 0;

		Vector esperado = new Vector();
		Oferta of;
//		 Resultado Esperado
		of = new Oferta();
		of.setPrecio(3);
		of.setDiaFin(fecha(2005,5,15));
		of.setDiaIni(fecha(2005,5,9));
		of.setNumCasa(5);
		of.setNumOferta("102");
		esperado.add(of);

		of = new Oferta();
		of.setPrecio(33);
		of.setDiaFin(fecha(2005,5,20));
		of.setDiaIni(fecha(2005,5,15));
		of.setNumCasa(4);
		of.setNumOferta("103");
		esperado.add(of);

		// Ejecutar test
		Vector obtenido = (Vector) gestorBD.seleccionarCasasDorWC(numDormitorios, numBanos, diaIni, diaFin, precio, numMinDias, orden);
		assertEquals("Error en la obtencion de los datos BD!", esperado, obtenido);
	}
	/**
	 * En esta prueba se verifica que salen todas las ofertas no reservadas entre el 01-02-2005 y el 01-02-2006
	 * y con un numero de dias minimo igual a 6 y ordenadas por precio.
	 * Esta prueba debe realizarse con la base de datos inicial
	 */
	public void test_seleccionarCasasDorWC_BD_Fechas_Numdias_Ord_Precio() {

		// Entrada
		int numDormitorios = 0 , numBanos = 0,numMinDias = 6;
		boolean orden = true;
		java.sql.Date diaIni =  new java.sql.Date((new GregorianCalendar(2005, 1, 1)).getTime().getTime());
		java.sql.Date diaFin  = new java.sql.Date((new GregorianCalendar(2006, 1, 1)).getTime().getTime());
		float precio = 0;

		Vector esperado = new Vector();
		Oferta of;
//		 Resultado Esperado
		of = new Oferta();
		of.setPrecio(3);
		of.setDiaFin(fecha(2005,5,15));
		of.setDiaIni(fecha(2005,5,9));
		of.setNumCasa(5);
		of.setNumOferta("102");
		esperado.add(of);

		// Ejecutar test
		Vector obtenido = (Vector) gestorBD.seleccionarCasasDorWC(numDormitorios, numBanos, diaIni, diaFin, precio, numMinDias, orden);
		assertEquals("Error en la obtencion de los datos BD!", esperado, obtenido);
	}
	/**
	 * En esta prueba se verifica que salen todas las ofertas no reservadas entre el 01-02-2005 y el 01-02-2006
	 * y con un precio maximo de 4 y ordenadas por precio.
	 * Esta prueba debe realizarse con la base de datos inicial
	 */
	public void test_seleccionarCasasDorWC_BD_Fechas_Precio_Ord_Precio() {

		// Entrada
		int numDormitorios = 0 , numBanos = 0,numMinDias = 0;
		boolean orden = true;
		java.sql.Date diaIni =  new java.sql.Date((new GregorianCalendar(2005, 1, 1)).getTime().getTime());
		java.sql.Date diaFin  = new java.sql.Date((new GregorianCalendar(2006, 1, 1)).getTime().getTime());
		float precio = 4;

		Vector esperado = new Vector();
		Oferta of;
//		 Resultado Esperado
		of = new Oferta();
		of.setPrecio(3);
		of.setDiaFin(fecha(2005,5,15));
		of.setDiaIni(fecha(2005,5,9));
		of.setNumCasa(5);
		of.setNumOferta("102");
		esperado.add(of);


		// Ejecutar test
		Vector obtenido = (Vector) gestorBD.seleccionarCasasDorWC(numDormitorios,numBanos,diaIni,diaFin,precio,numMinDias,orden);
		assertEquals("Error en la obtencion de los datos BD!", esperado, obtenido);
	}
}
