package gui;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class DetallesMejorOferta extends JFrame 
{
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
private boolean hayOfertas;
private float precio;
private int tamano;
private int numCasa;
private Date inicio;
private Date fin;
public DetallesMejorOferta()
{try{jbInit();
  this.jLabel1.setText("Lo sentimos, no hay ofertas");
   this.jButton2.setText("Salir");
   this.jButton1.setVisible(false);
}catch(Exception ex){ex.printStackTrace();}
}
  public DetallesMejorOferta(int numCasa,float precio,int tamano,Date inicio,Date fin)
  {
    try
    {this.hayOfertas=hayOfertas;
    this.precio=precio;
    this.tamano=tamano;
    this.numCasa=numCasa;
    this.inicio=inicio;
    this.fin=fin;
      jbInit();
      //a partir de aqui no estaba
      this.jLabel1.setText("La mejor oferta es para la casa:");
    this.jLabel2.setText("Su precio es:");
    this.jLabel6.setText("Su tamaño es:");
    this.jLabel3.setText("¿Deseas hacer la reserva?");
      this.jLabel4.setText(""+this.numCasa+"");
      this.jLabel5.setText(this.precio+" euros");
      this.jLabel7.setText(this.tamano+" camas");
      this.jButton1.setText("Sí");
      this.jButton2.setText("No");
      
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
    jLabel1.setBounds(new Rectangle(10, 35, 195, 25));
    jLabel2.setBounds(new Rectangle(10, 75, 120, 30));
    jLabel3.setBounds(new Rectangle(45, 155, 160, 35));
    jButton1.setBounds(new Rectangle(95, 210, 85, 30));
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    jButton2.setBounds(new Rectangle(220, 210, 90, 30));
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton2_actionPerformed(e);
        }
      });
    jLabel4.setBounds(new Rectangle(230, 40, 75, 20));
    jLabel5.setBounds(new Rectangle(130, 80, 80, 25));
    jLabel6.setBounds(new Rectangle(10, 120, 85, 25));
    jLabel7.setBounds(new Rectangle(125, 120, 80, 25));
    this.getContentPane().add(jLabel7, null);
    this.getContentPane().add(jLabel6, null);
    this.getContentPane().add(jLabel5, null);
    this.getContentPane().add(jLabel4, null);
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jLabel1, null);
 /*   if(this.hayOfertas)
    {this.jLabel1.setText("La mejor oferta es para la casa:");
    this.jLabel2.setText("Su precio es:");
    this.jLabel6.setText("Su tamaño es:");
    this.jLabel3.setText("¿Deseas hacer la reserva?");
      this.jLabel4.setText(""+this.numCasa+"");
      this.jLabel5.setText(this.precio+" euros");
      this.jLabel7.setText(this.tamano+" camas");
      this.jButton1.setText("Sí");
      this.jButton2.setText("No");
    }
    else
    {
      this.jLabel1.setText("Lo sentimos, no hay ofertas");
   this.jButton2.setText("Salir");
   this.jButton1.setVisible(false);
   
    }*/
  }

  private void jButton2_actionPerformed(ActionEvent e)
  {//System.exit(0);
  this.setVisible(false);
  //try{
  //this.finalize();}catch(Exception ex){System.out.println("error al cerrar");};
  }


  private void jButton1_actionPerformed(ActionEvent e)
  {ReservarCasa rc=new ReservarCasa(numCasa,inicio,fin);
  rc.setVisible(true);
  }
}