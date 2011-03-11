package gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmarReserva extends JFrame 
{
  private JLabel jLabel1 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField2 = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JButton jButton1 = new JButton();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();

  public ConfirmarReserva(int NumCasa, float PrecioTotal, String NumReserva)
  {
    try
    {
      jbInit(NumCasa, PrecioTotal, NumReserva);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit(int NumCasa, float PrecioTotal, String NumReserva) throws Exception
  {
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(416, 316));
    this.setTitle("Confirmar Reserva");
    this.setResizable(false);
    jLabel1.setText("Numero de cuenta del propietario:");
    jLabel1.setBounds(new Rectangle(20, 20, 200, 25));
    jTextField1.setBounds(new Rectangle(225, 20, 165, 25));
    jTextField1.setEditable(false);
    // Añadimos Numero cuenta corriente
    jTextField1.setText(PantallaInicio.interfazfachada.getNumCuentaCorriente(NumCasa));
    
    jLabel2.setText("Numero de la reserva:");
    jLabel2.setBounds(new Rectangle(20, 60, 130, 25));
    jTextField2.setBounds(new Rectangle(225, 60, 165, 25));
    jTextField2.setEditable(false);
    //añadimos numero de reserva
    jTextField2.setText(NumReserva);
    
    jLabel3.setText("Debe ingresar el 20% del importe total antes del plazo de 3 dias.");
    jLabel3.setBounds(new Rectangle(20, 105, 370, 25));
    jButton1.setText("Aceptar");
    jButton1.setBounds(new Rectangle(135, 235, 130, 30));
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    jLabel4.setText("Total:");
    jLabel4.setBounds(new Rectangle(70, 140, 85, 25));
    jLabel5.setText("Total a Ingresar:");
    jLabel5.setBounds(new Rectangle(70, 175, 100, 25));
    jTextField3.setBounds(new Rectangle(180, 140, 115, 25));
    jTextField3.setEditable(false);
    // Fijamos el precio
    jTextField3.setText(Float.toString(PrecioTotal) + " €");
    jTextField4.setBounds(new Rectangle(180, 175, 115, 25));
    jTextField4.setEditable(false);
    jTextField4.setText(Float.toString(PrecioTotal*(float)0.2) + " €");
    this.getContentPane().add(jTextField4, null);
    this.getContentPane().add(jTextField3, null);
    this.getContentPane().add(jLabel5, null);
    this.getContentPane().add(jLabel4, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jLabel1, null);
  }

  private void jButton1_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }
}