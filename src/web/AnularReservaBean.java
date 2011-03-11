package web;

import java.rmi.*;

import configuracion.Config;
import casarural.InterfazFachada;

public class AnularReservaBean { 

	public static InterfazFachada logNeg;
	private String reserva;

	/**
	 * Constructor. Conexion con la logica de negocio a traves de RMI.
	 */
	public AnularReservaBean() {
		final Config conf = Config.getInstance();
		final String ipMaquina = conf.getServerRMI();
		final String urlRMI = conf.getUrlRmi();
		
		//final String ipMaquina = "10.227.131.226";
		//final String urlRMI = "rmi://10.227.131.226:1099/CasaRural";
		
		System.setProperty ("java.rmi.server.codebase", "http://"+ipMaquina);
		// Aqui realizamos las operaciones necesarias para trabajar
		// mediante RMI
		try {
			// Nombre servicio remoto
			System.setSecurityManager(new RMISecurityManager());
			
			// IP maquina servidor RMI
			logNeg = (InterfazFachada) Naming.lookup(urlRMI);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	/**
	 * Asignamos el numero de reserva.
	 * @param numReserva
	 */
	public void setReserva(String numReserva) {
		this.reserva = numReserva;
	}

	/**
	 * Anulamos la reserva.
	 * @return String Resultado de anular reserva. Dinero a devolver.
	 */
	public String getReserva()	{
		try {
			String res;
			float devolucion = logNeg.anularReserva(Integer.valueOf(this.reserva));
			
			if(devolucion == 0)
				res = "Reserva anulada. No le corresponde devolucion de dinero.";
			else
				res = "Reserva anulada. Se devolvera un importe de "+devolucion+" Û";
			
			return res;
		} catch (NumberFormatException e1) {
			return this.reserva+" NO es un numero.";
		} catch (Exception e2) {
			return e2.getMessage();
		}
	}
}