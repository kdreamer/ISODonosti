package gui;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorReservar extends JFrame 
{
  private JLabel jLabel1 = new JLabel();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();

  public ErrorReservar()
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
    this.setSize(new Dimension(494, 153));
    this.setTitle("Error reservar casa rural");
    jLabel1.setText("No se puede reservar la oferta.");
    jLabel1.setBounds(new Rectangle(25, 20, 185, 30));
    jButton1.setText("Aceptar");
    jButton1.setBounds(new Rectangle(170, 75, 130, 30));
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    jButton2.setText("Consultar Disponibilidad");
    jButton2.setBounds(new Rectangle(260, 20, 185, 30));
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton2_actionPerformed(e);
        }
      });
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jLabel1, null);
  }

  private void jButton2_actionPerformed(ActionEvent e)
  {
     JFrame c = new ConsultarDisponibilidad();
     c.setVisible(true);
  }

  private void jButton1_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }
}