package gui;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.GridBagLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagConstraints;
import java.util.Vector;
import java.awt.Insets;
import javax.swing.BoxLayout;

import casarural.Oferta;

/**
 * @author  kdreamer
 */
public class kisama extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	/**
	 * @uml.property  name="scroll"
	 */
	private JScrollPane scroll = null;

	/**
	 * @uml.property  name="tabla"
	 */
	private JTable tabla = null;

	private JPanel pBoton = null;

	/**
	 * @uml.property  name="boton"
	 */
	private JButton boton = null;
	
	private DefaultTableModel model;
	
	private Vector ofertas = new Vector();  //  @jve:decl-index=0:

	/**
	 * This is the default constructor
	 */
	public kisama() {
		super();
		initialize();
		model = new DefaultTableModel();
		tabla = new JTable(model);
	}
	
	/**
	 * Constructora con vector de ofertas
	 */
	public kisama(Vector ofers) {
		super();
		this.ofertas = ofers;
		initialize();	
		tabla = new JTable(model);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(400, 600);
		this.setContentPane(getJContentPane());
		this.setTitle("JFrame");
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
			jContentPane.add(getScroll(), null);
			jContentPane.add(getPBoton(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes scroll	
	 * @return  javax.swing.JScrollPane
	 * @uml.property  name="scroll"
	 */
	private JScrollPane getScroll() {
		if (scroll == null) {
			scroll = new JScrollPane();
			scroll.setPreferredSize(new Dimension(400, 600));
			scroll.setSize(400, 600);
			scroll.setViewportView(getTabla());
			scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		}
		return scroll;
	}

	/**
	 * This method initializes tabla	
	 * @return  javax.swing.JTable
	 * @uml.property  name="tabla"
	 */
	private JTable getTabla() {
		if (tabla == null) {
			model = new DefaultTableModel();
			model.addColumn("Fecha Inicio");
			model.addColumn("Fecha Fin");
			model.addColumn("Precio");
			for(int i =0;i<ofertas.size();i++){
				Oferta o = (Oferta)ofertas.elementAt(i);
				Object[] fila = {o.getDiaIni().toString(),o.getDiaFin().toString(),
						o.getPrecio()};
				model.addRow(fila);
			}//for
			tabla = new JTable(model);
			tabla.setPreferredSize(new Dimension(400, 600));
			tabla.setPreferredScrollableViewportSize(new Dimension(200,100));
			tabla.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
			tabla.setRowSelectionAllowed(true);
			
		}
		return tabla;
	}

	/**
	 * This method initializes pBoton	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPBoton() {
		if (pBoton == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.insets = new Insets(8, 0, 8, 0);
			pBoton = new JPanel();
			pBoton.setLayout(new GridBagLayout());
			pBoton.add(getBoton(), gridBagConstraints);
		}
		return pBoton;
	}

	/**
	 * This method initializes boton	
	 * @return  javax.swing.JButton
	 * @uml.property  name="boton"
	 */
	private JButton getBoton() {
		if (boton == null) {
			boton = new JButton();
			boton.setText("Reservar");
			boton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {

					int select = ((JTable)((JViewport)scroll.getComponent(0)).getComponent(0)).getSelectedRow();
					
					try{
						Oferta oferSel = (Oferta)ofertas.elementAt(select);
					
						ReservarCasa reser = new ReservarCasa(oferSel.getNumCasa(), oferSel.getDiaIni(), oferSel.getDiaFin());
						reser.setVisible(true);
					}catch(Exception ex){
						JFrame alerta = new JFrame();
						try{
							JOptionPane.showMessageDialog(alerta, "No se ha seleccionado ninguna oferta!","Alerta",JOptionPane.WARNING_MESSAGE);
						}catch(Exception a){
							System.out.println("error mostrando dialogo");
							a.printStackTrace();
						}
					}
				}
			});
		}
		return boton;
	}
	
}
