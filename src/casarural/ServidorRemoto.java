package casarural;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;

/**
 * Activa el servidor remoto
 * 
 * @param Ninguno
 * @return Ninguno
 */
// ddd
public class ServidorRemoto {
	public static void main(String[] args) {
		System.setSecurityManager(new RMISecurityManager());
		try {
			java.rmi.registry.LocateRegistry.createRegistry(ClaseFachada.numPuerto);
			// Crear RMIREGISTRY
		} catch (Exception e) {
			System.out.println(e.toString() + "Rmiregistry estaba lanzado.");
		}

		try {
			ClaseFachada servidor = new ClaseFachada();
			String servicio="CasaRural2011";
			//String servicio = "//localhost:" + InterfazFachada.numPuerto
					//+ "/CasaRural2011";
			// Resgistrar el servicio remoto
			Naming.rebind(servicio, servidor);
			System.out.println("HOLA HOLA Servicio lanzado en:\n\t" + servicio);
		} catch (Exception e) {
			System.out.println("Errorrrrrrrr1" +e.toString());
		}
	}
}
