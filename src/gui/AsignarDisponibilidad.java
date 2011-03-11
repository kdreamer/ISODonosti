package gui;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.Vector;

public class AsignarDisponibilidad extends JFrame 
{
  private JLabel jLabel1 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  
  // String con el Nombre de la Cuenta del Sistema
  String cuentasistema=null;

  public AsignarDisponibilidad()
  {
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(400, 179));
    this.setTitle("Asignar Disponibilidad");
    jLabel1.setText("Codigo del propietario:");
    jLabel1.setBounds(new Rectangle(40, 25, 130, 25));
    jTextField1.setBounds(new Rectangle(210, 25, 130, 25));
    
    jTextField1.addCaretListener(new CaretListener()
    	      {
    	        public void caretUpdate(CaretEvent e)
    	        {
    	          jTextField1_caretUpdate(e);
    	        }
    	      });
    
    jButton1.setBounds(new Rectangle(40, 85, 130, 30));
    jButton1.setText("Aceptar");
    jButton1.setEnabled(false);
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    jButton2.setText("Cancelar");
    jButton2.setBounds(new Rectangle(210, 85, 130, 30));
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton2_actionPerformed(e);
        }
      });
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jLabel1, null);
  }

  private void jButton2_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }

  private void jButton1_actionPerformed(ActionEvent e)
  {			
  			Vector listacasas=null;
  			try {
				listacasas=PantallaInicio.interfazfachada.getCodigosCasas(jTextField1.getText());
			
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			if (listacasas.isEmpty()!=true) {
				JFrame a = new RellenarDisponibilidad(listacasas);
				a.setVisible(true);
			} else if (listacasas.isEmpty()==true){
				System.out.print("No existe usuario o usuario sin casas");
			} 		
  }

  private void jTextField1_caretUpdate(CaretEvent e)
  {
  cuentasistema=jTextField1.getText();
	if (cuentasistema.length()!=0){
		jButton1.setEnabled(true);
	} else if (cuentasistema.length()==0) {
		jButton1.setEnabled(false);
	 }
  }
}