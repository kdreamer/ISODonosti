package gui;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JTable;
import java.awt.Rectangle;
import javax.swing.table.TableColumnModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Disponibilidad extends JFrame 
{
  private TableColumnModel tableColumnModel1 = new javax.swing.table.DefaultTableColumnModel();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JTextArea jTextArea1 = new JTextArea();
  private JTextArea jTextArea2 = new JTextArea();
  private JButton jButton1 = new JButton();

  public Disponibilidad()
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
    this.setSize(new Dimension(400, 300));
    this.setTitle("Disponibilidad");
    jLabel1.setText("Dia:");
    jLabel1.setBounds(new Rectangle(25, 10, 50, 20));
    jLabel2.setText("Estado:");
    jLabel2.setBounds(new Rectangle(135, 10, 60, 20));
    jTextArea1.setBounds(new Rectangle(20, 35, 90, 160));
    jTextArea1.setEditable(false);
    jTextArea2.setBounds(new Rectangle(130, 35, 245, 160));
    jTextArea2.setEditable(false);
    jButton1.setText("Aceptar");
    jButton1.setBounds(new Rectangle(135, 220, 125, 30));
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jTextArea2, null);
    this.getContentPane().add(jTextArea1, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jLabel1, null);
  }

  private void jButton1_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }
}