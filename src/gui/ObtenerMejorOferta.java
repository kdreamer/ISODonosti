package gui;
import javax.swing.JApplet;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import casarural.InterfazFachada;

import java.rmi.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;

import java.sql.*;
import java.sql.Date;
public class ObtenerMejorOferta extends JApplet 
{
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private String[] lista={"precio","tamaño"};
  private JComboBox jComboBox1 = new JComboBox(lista);
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
InterfazFachada ifach;
  public ObtenerMejorOferta()
  {
  }

  public void init()
  {
    try
    {
      jbInit();
      String maquina=this.getCodeBase().getHost();
      ifach=(InterfazFachada)Naming.lookup("rmi://"+maquina+":1099/CasaRural");
      
    }catch(Exception e)
    {System.out.println("Error al cargar la logica de negocio");
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {
    this.getContentPane().setLayout(null);
    jLabel1.setText("Fecha de inicio(dd/mm/aaaa)");
    jLabel1.setBounds(new Rectangle(20, 45, 145, 25));
    jLabel2.setText("Fecha final(dd/mm/aaaa)");
    jLabel2.setBounds(new Rectangle(20, 80, 140, 20));
    jLabel3.setText("Nº de habitaciones");
    jLabel3.setBounds(new Rectangle(20, 110, 135, 20));
    jLabel4.setText("Nº de baños");
    jLabel4.setBounds(new Rectangle(20, 140, 130, 25));
    jLabel5.setText("Criterio");
    jLabel5.setBounds(new Rectangle(20, 170, 70, 20));
    jTextField1.setBounds(new Rectangle(175, 45, 90, 20));
    jTextField2.setBounds(new Rectangle(175, 80, 90, 20));
    jTextField3.setBounds(new Rectangle(175, 110, 55, 20));
    jTextField4.setBounds(new Rectangle(175, 140, 55, 20));
    jComboBox1.setBounds(new Rectangle(175, 170, 75, 20));
    jButton1.setText("Aceptar");
    jButton1.setBounds(new Rectangle(95, 220, 85, 30));
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    jButton2.setText("Salir");
    jButton2.setBounds(new Rectangle(205, 220, 85, 30));
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton2_actionPerformed(e);
        }
      });
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jComboBox1, null);
    this.getContentPane().add(jTextField4, null);
    this.getContentPane().add(jTextField3, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jLabel5, null);
    this.getContentPane().add(jLabel4, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jLabel1, null);
  }

  static  
  {
    try
    {
      // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      // UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch(Exception e)
    {
    }

  }

  private void jButton1_actionPerformed(ActionEvent e)
  {String diaIni=this.jTextField1.getText();
  String diaFin=this.jTextField2.getText();
 
    StringTokenizer st = new StringTokenizer (diaIni,"/");
    int dia = Integer.parseInt(st.nextToken());
    int mes = Integer.parseInt(st.nextToken()) - 1;
    int anio = Integer.parseInt(st.nextToken());
    GregorianCalendar gc = new GregorianCalendar(anio,mes,dia);
    java.sql.Date diaIniDate = new java.sql.Date(gc.getTime().getTime());
      StringTokenizer st2 = new StringTokenizer (diaFin,"/");
    int dia2 = Integer.parseInt(st2.nextToken());
    int mes2 = Integer.parseInt(st2.nextToken()) - 1;
    int anio2 = Integer.parseInt(st2.nextToken());
    GregorianCalendar gc2 = new GregorianCalendar(anio2,mes2,dia2);
    java.sql.Date diaFinDate = new java.sql.Date(gc2.getTime().getTime());

String criterio=(String)this.jComboBox1.getSelectedItem();
try{
  ifach.obtenerMejorOferta(diaIniDate,diaFinDate,Integer.parseInt(this.jTextField3.getText()),Integer.parseInt(this.jTextField4.getText()),criterio);
  }catch(Exception ex){System.out.println("error en applet");}
  }

  private void jButton2_actionPerformed(ActionEvent e)
  {System.exit(0);
  }
}