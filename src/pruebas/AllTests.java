package pruebas;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for casarural");
		
		TestSuite suiteA = new TestSuite("Tests GestorOfertas");
		
		TestSuite suiteAA = new TestSuite("Concatenar Ofertas");
		
		suiteAA.addTest(new TestGestorOfertas("test_Concatenar_2_Ofertas"));
		suiteAA.addTest(new TestGestorOfertas("test_Concatenar_3_Ofertas"));
		suiteAA.addTest(new TestGestorOfertas("test_ConcatenarOfertas_1_Oferta"));
		suiteAA.addTest(new TestGestorOfertas("test_No_Concatenar_2_Ofertas_Fecha"));
		suiteAA.addTest(new TestGestorOfertas("test_No_Concatenar_2_Ofertas_NumCasa"));
		
		TestSuite suiteAB = new TestSuite("Ordenar Ofertas");
		suiteAB.addTest(new TestGestorOfertas("test_QuickSort_Duracion_Oferta"));
		suiteAB.addTest(new TestGestorOfertas("test_QuickSort_Precio_Oferta"));
		
		TestSuite suiteAC = new TestSuite("Buscar Ofertas");
		suiteAC.addTest(new TestGestorOfertas("test_Buscar_Ofertas_Por_Precio"));
		suiteAC.addTest(new TestGestorOfertas("test_Buscar_Ofertas_Por_Duracion"));
		
		suiteA.addTest(suiteAA);
		suiteA.addTest(suiteAB);
		suiteA.addTest(suiteAC);


		TestSuite suiteB = new TestSuite("Tests GestorBD");
		suiteB.addTest(new TestGestorBD("test_seleccionarCasasDorWC_BD_Ord_Precio"));
		suiteB.addTest(new TestGestorBD("test_seleccionarCasasDorWC_BD_Fechas_Numdias_Ord_Precio"));
		suiteB.addTest(new TestGestorBD("test_seleccionarCasasDorWC_BD_Fechas_Ord_Precio"));
		suiteB.addTest(new TestGestorBD("test_seleccionarCasasDorWC_BD_Fechas_Precio_Ord_Precio"));
		
		suite.addTest(suiteA);
		suite.addTest(suiteB);

		return suite;
	}

}
