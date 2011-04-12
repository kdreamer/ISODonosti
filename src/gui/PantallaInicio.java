package gui;
/**
 * @author Grupo 2
 */
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import configuracion.Config;

import casarural.InterfazFachada;


import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;

public class PantallaInicio extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	private JButton boton1 = null;

	private JButton boton2 = null;

	private JPanel pReservar = null;

	private JPanel pAsignar = null;

	private JPanel pConsultar = null;

	private JButton boton3 = null;

	private JPanel pMejor = null;

	private JButton boton4 = null;

	private JPanel pSalir = null;

	private JButton boton5 = null;

	private JPanel pBuscar = null;

	private JButton boton6 = null;
	
	private JPanel pAnular = null;
	
	private JButton boton7 = null;
	
	private JPanel pLoginAdmin = null;
	
	private JButton boton8 = null;
	
	public static InterfazFachada interfazfachada;

	/**
	 * This is the default constructor
	 */
	public PantallaInicio() {
		super();
		initialize();
		this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 350);
		this.setContentPane(getJContentPane());
		this.setTitle("Casas Rurales");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
			jContentPane.add(getPReservar(), null);
			jContentPane.add(getPAsignar(), null);
			jContentPane.add(getPConsultar(), null);
			jContentPane.add(getPMejor(), null);
			jContentPane.add(getPBuscar(), null);
			jContentPane.add(getPAnular(),null);
			jContentPane.add(getPLoginAdmin(), null);
			jContentPane.add(getPSalir(), null);

		}
		return jContentPane;
	}

	/**
	 * This method initializes boton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton1() {
		if (boton1 == null) {
			boton1 = new JButton();
			boton1.setText("Reservar Casa Rural");
			boton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//C?digo cedido por la univerdad
					JFrame a = new ReservarCasa();
				    a.setVisible(true);
				}
			});
		}
		return boton1;
	}

	/**
	 * This method initializes boton2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton2() {
		if (boton2 == null) {
			boton2 = new JButton();
			boton2.setText("Asignar Disponibilidad");
			boton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//C?digo cedido por la universidad
				    JFrame a = new AsignarDisponibilidad();
				    a.setVisible(true);
				}
			});
		}
		return boton2;
	}

	/**
	 * This method initializes pReservar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPReservar() {
		if (pReservar == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.NONE;
			gridBagConstraints2.gridwidth = 1;
			gridBagConstraints2.ipadx = 40;
			gridBagConstraints2.insets = new Insets(8, 0, 5, 0);
			pReservar = new JPanel();
			pReservar.setLayout(new GridBagLayout());
			pReservar.add(getBoton1(), gridBagConstraints2);
		}
		return pReservar;
	}

	/**
	 * This method initializes pAsignar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPAsignar() {
		if (pAsignar == null) {
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints3.ipadx = 31;
			pAsignar = new JPanel();
			pAsignar.setLayout(new GridBagLayout());
			pAsignar.add(getBoton2(), gridBagConstraints3);
		}
		return pAsignar;
	}

	/**
	 * This method initializes pConsultar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPConsultar() {
		if (pConsultar == null) {
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints4.ipadx = 20;
			pConsultar = new JPanel();
			pConsultar.setLayout(new GridBagLayout());
			pConsultar.add(getBoton3(), gridBagConstraints4);
		}
		return pConsultar;
	}

	/**
	 * This method initializes boton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton3() {
		if (boton3 == null) {
			boton3 = new JButton();
			boton3.setText("Consultar Disponibilidad");
			boton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//C?digo cedido por la universidad
					JFrame a = new ConsultarDisponibilidad();
				    a.setVisible(true);
				}
			});
		}
		return boton3;
	}

	/**
	 * This method initializes pMejor	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPMejor() {
		if (pMejor == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints5.ipadx = 11;
			pMejor = new JPanel();
			pMejor.setLayout(new GridBagLayout());
			pMejor.add(getBoton4(), gridBagConstraints5);
		}
		return pMejor;
	}

	/**
	 * This method initializes boton4	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton4() {
		if (boton4 == null) {
			boton4 = new JButton();
			boton4.setText("Encontrar Mejor Reservar");
			boton4.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//C?digo cedido por la universidad.
					encontrarMejorReserva emr=new encontrarMejorReserva();
					  emr.setVisible(true);
				}
			});
		}
		return boton4;
	}

	/**
	 * This method initializes pSalir	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPSalir() {
		if (pSalir == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = -1;
			gridBagConstraints1.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints1.gridy = -1;
			pSalir = new JPanel();
			pSalir.setLayout(new GridBagLayout());
			pSalir.add(getBoton6(), gridBagConstraints1);
		}
		return pSalir;
	}

	/**
	 * This method initializes boton5	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton5() {
		if (boton5 == null) {
			boton5 = new JButton();
			boton5.setText("Buscar Ofertas");
			boton5.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//Llamar a ventana de Joel.
					BuscarOfertas ventanaBuscarOfertas = new BuscarOfertas();
					ventanaBuscarOfertas.setVisible(true);
				}
			});
		}
		return boton5;
	}

	/**
	 * This method initializes pBuscar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPBuscar() {
		if (pBuscar == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = -1;
			gridBagConstraints.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints.ipadx = 70;
			gridBagConstraints.gridy = -1;
			pBuscar = new JPanel();
			pBuscar.setLayout(new GridBagLayout());
			pBuscar.add(getBoton5(), gridBagConstraints);
		}
		return pBuscar;
	}

	/**
	 * This method initializes boton6	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton6() {
		if (boton6 == null) {
			boton6 = new JButton();
			boton6.setText("Salir");
			//Funci?n para salir, cuando se pulsa el bot?n.
			boton6.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return boton6;
	}
	
	/**
	 * Este método inicializa el Panel pAnular
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPAnular() {
		if (pAnular == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = -1;
			gridBagConstraints.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints.ipadx = 70;
			gridBagConstraints.gridy = -1;
			pAnular = new JPanel();
			pAnular.setLayout(new GridBagLayout());
			pAnular.add(getBoton7(), gridBagConstraints);
		}
		return pAnular;
	}

	/**
	 * Con este botón se abre la interfaz para anular las reservas.
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton7() {
		if (boton7 == null) {
			boton7 = new JButton();
			boton7.setText("Anular Reserva");
			boton7.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				    IUAnularReserva an = new IUAnularReserva();
				    an.setVisible(true);
				}
			});
		}
		return boton7;
	}
	
	/**
	 * Este método inicializa el Panel pAnular
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPLoginAdmin() {
		if (pLoginAdmin == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = -1;
			gridBagConstraints.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints.ipadx = 42;
			gridBagConstraints.gridy = -1;
			pLoginAdmin = new JPanel();
			pLoginAdmin.setLayout(new GridBagLayout());
			pLoginAdmin.add(getBoton8(), gridBagConstraints);
		}
		return pLoginAdmin;
	}

	/**
	 * Con este botón se abre la interfaz de login del administrador.
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBoton8() {
		if (boton8 == null) {
			boton8 = new JButton();
			boton8.setText("Login Administrador");
			boton8.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				    LoginAdministrador la = new LoginAdministrador();
				    la.setVisible(true);
				}
			});
		}
		return boton8;
	}
	
	  public static void main(String[] args)
	  {
	  	final String IPMAQUINA = "localhost";
	  	final String DIRECTORIOCLASES= "/ISOServidor/";
	  	//
	  	System.setProperty ("java.rmi.server.codebase", "http://"+IPMAQUINA+DIRECTORIOCLASES);
	    // Aqui realizamos las operaciones necesarias para trabajar
	    // mediante RMI
	   try {
				
			
	      Config conf = Config.getInstance();
	  	// Nombre servicio remoto
	      String servicio =conf.getServicioRMI(); //modifiar esto para que lo pille desde el xml
		System.setSecurityManager(new RMISecurityManager());
		// Numero puerto servidor RMI
	      int numPuerto = InterfazFachada.numPuerto;//No es mejor coger los datos del xml?????
	      // IP maquina servidor RMI
				String maquina = conf.getServerRMI();
				//System.out.println("rmi://" + maquina + ":" + numPuerto + servicio);
				interfazfachada = (InterfazFachada) Naming.lookup("rmi://" + maquina + ":" + numPuerto + "/" + servicio);
				

			}

			catch (Exception e) {
				System.out.println("aki es donde falla en pantallaInicio");
				e.printStackTrace();

				System.out.println(e.toString());
			}
	    JFrame a = new PantallaInicio();
	    a.setVisible(true);
	  }

}  //  @jve:decl-index=0:visual-constraint="0,0"
