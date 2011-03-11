package pruebas;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTestsAnular {

	public static Test suite (){
		TestSuite suite = new TestSuite("Test for AnularReserva");
		suite.addTest(new TestAnularReserva("test_obtenerPrecioTotal"));
		suite.addTest(new TestAnularReserva("test_obtenerPrecioTotal_ReservaNoExiste"));
		suite.addTest(new TestAnularReserva("test_actualizarOfertas"));
		suite.addTest(new TestAnularReserva("test_actualizarOfertas_ReservaSinOferta"));
		suite.addTest(new TestAnularReserva("test_ActualizarReservas"));
		suite.addTest(new TestAnularReserva("test_ActualizarReservas_ReservaNoexiste"));
		suite.addTest(new TestAnularReserva("test_obtenDiaIniMinBD"));
		suite.addTest(new TestAnularReserva("test_obtenDiaIniMinBD_ReservaNoExiste"));
		suite.addTest(new TestAnularReserva("test_ObtenDiaIniMin"));
		suite.addTest(new TestAnularReserva("test_ObtenDiaIniMin_ReservaNoExiste"));
		suite.addTest(new TestAnularReserva("test_AnularReserva"));
		suite.addTest(new TestAnularReserva("test_AnularReservaNoExiste"));
		return(suite);
		}
}

