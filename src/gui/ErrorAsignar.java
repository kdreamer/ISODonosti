package gui;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ErrorAsignar extends JFrame 
{
  private JLabel jLabel1 = new JLabel();
  private JButton jButton1 = new JButton();

  public ErrorAsignar()
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
    this.setSize(new Dimension(298, 167));
    this.setTitle("Error asignar disponibilidad");
    jLabel1.setText("No se puede asignar, existe oferta reservada.");
    jLabel1.setBounds(new Rectangle(20, 25, 260, 25));
    jButton1.setText("Aceptar");
    jButton1.setBounds(new Rectangle(80, 80, 130, 30));
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jLabel1, null);
  }

  private void jButton1_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }
}