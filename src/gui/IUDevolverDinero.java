package gui;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;

public class IUDevolverDinero extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jDevolucion = null;
	private JButton jAceptar = null;

	public IUDevolverDinero() throws HeadlessException {
		// TODO Auto-generated constructor stub
		super();
		initialize();
	}

	public IUDevolverDinero(float precioTotal) {
		super();
		initialize();
		if (precioTotal ==0)
			jDevolucion.setText("No le corresponde devolución de dinero");
		else
			jDevolucion.setText("Se le devolverá un importe de: "+String.valueOf(precioTotal)+" €");
	}

	public IUDevolverDinero(String title) throws HeadlessException {
		super(title);
		// TODO Auto-generated constructor stub
		initialize();
	}

	public IUDevolverDinero(String title, GraphicsConfiguration gc) {
		super(title, gc);
		// TODO Auto-generated constructor stub
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(300, 200);
		this.setContentPane(getJContentPane());
		this.setTitle("Devolución Fianza");
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jDevolucion = new JLabel();
			jDevolucion.setBounds(new Rectangle(27, 28, 227, 23));
			jDevolucion.setText("JLabel");
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(jDevolucion, null);
			jContentPane.add(getJAceptar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes jAceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJAceptar() {
		if (jAceptar == null) {
			jAceptar = new JButton();
			jAceptar.setBounds(new Rectangle(89, 90, 102, 20));
			jAceptar.setText("Aceptar");
			jAceptar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					setVisible(false);
				}
			});
		}
		return jAceptar;
	}

}
