package gui;
/**
 * @author Grupo 2
 */

//import acm.io.*;

import java.awt.Frame;

import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.rmi.*;
import java.sql.SQLException;

public class IUAnularReserva extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel jLabel = null;

	private JTextField jTextField = null;

	private JButton jButton = null;

	//private IODialog dialogo = new IODialog();
	
	float devolucion;

	/**
	 * En este label es en el que se introduce el número de la reserva
	 * que queremos anular.	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(24, 87, 203, 40));
		}
		return jTextField;
	}

	/**
	 * Este botón es el que realiza la llamada a la Pantalla Inicio 
	 * y llama al método anular Reserva pasándole el número de la reserva
	 * que queremos anular.
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(131, 167, 167, 47));
			jButton.setText("Anular Reserva");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					try {
						devolucion=PantallaInicio.interfazfachada.anularReserva(Integer
								.parseInt(jTextField.getText()));
						
						IUDevolverDinero devolverDinero = new IUDevolverDinero(devolucion);
						devolverDinero.setVisible(true);
						//dialogo.println("Reserva Anulada Correctamente.");
						setVisible(false);
					}catch (NumberFormatException e2){
						//dialogo.showErrorMessage(e2.getMessage());
					
					} catch (Exception e1) {
						//dialogo.showErrorMessage(e1.getMessage());
					}
				}
			});
		}
		return jButton;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * This is the default constructor
	 */
	public IUAnularReserva() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		jLabel = new JLabel();
		jLabel.setBounds(new Rectangle(22, 42, 205, 35));
		jLabel.setText("Introduzca el número de reserva: ");
		this.setLayout(null);
		this.setSize(345, 300);
		this.setTitle("Anular Reserva");

		this.add(jLabel, null);
		this.add(getJTextField(), null);
		this.add(getJButton(), null);

	}
} // @jve:decl-index=0:visual-constraint="10,10"
