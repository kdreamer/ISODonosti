package gui;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JComboBox;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;


import com.toedter.calendar.JCalendar;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;

public class RellenarDisponibilidad extends JFrame  
{
  private JComboBox jComboBox1;
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JTextField jTextField2 = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JTextField jTextField3 = new JTextField();
  private JButton jButton1 = new JButton();
  
  // Codigo para el JCalendar
  private JCalendar jCalendar1 = new JCalendar();
  private JCalendar jCalendar2 = new JCalendar();
  private Calendar calendarInicio = null;
  private Calendar calendarFin = null;
  private JButton jButton2 = new JButton();
  private JLabel jLabel5 = new JLabel();
  
  public RellenarDisponibilidad(Vector v)
  {
    try
    {
      jbInit(v);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private void jbInit(Vector v) throws Exception
  {
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(513, 433));
    this.setTitle("Rellenar Disponibilidad");
    jComboBox1 = new JComboBox(v);
    jComboBox1.setBounds(new Rectangle(115, 30, 115, 20));
    jLabel1.setText("Lista de casas:");
    jLabel1.setBounds(new Rectangle(25, 30, 95, 20));
    jLabel2.setText("Dia Inicio :");
    jLabel2.setBounds(new Rectangle(25, 75, 85, 25));
    jTextField1.setBounds(new Rectangle(25, 265, 220, 25));
    jTextField1.setEditable(false);
    jLabel3.setText("Dia Fin :");
    jLabel3.setBounds(new Rectangle(260, 75, 75, 25));
    jTextField2.setBounds(new Rectangle(260, 265, 220, 25));
    jTextField2.setEditable(false);
    jLabel4.setText("Precio:");
    jLabel4.setBounds(new Rectangle(260, 30, 75, 20));
    jTextField3.setBounds(new Rectangle(350, 30, 115, 20));
    jTextField3.setText("0");
    jButton1.setText("Aceptar");
    jButton1.setBounds(new Rectangle(100, 360, 130, 30));
    jTextField3.addFocusListener(new FocusListener()
      {
          public void focusGained(FocusEvent e)
          {
          }
  
          public void focusLost(FocusEvent e)
          {
            jTextField3_focusLost();
          }
      });
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    jButton2.setText("Cancelar");
    jButton2.setBounds(new Rectangle(270, 360, 130, 30));
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton2_actionPerformed(e);
        }
      });
    jLabel5.setBounds(new Rectangle(100, 320, 300, 20));
    jLabel5.setForeground(Color.red);
    jLabel5.setSize(new Dimension(305, 20));
    jCalendar1.setBounds(new Rectangle(25, 100, 220, 165));
    jCalendar2.setBounds(new Rectangle(260, 100, 220, 165));
    
    // Codigo para el JCalendar
    this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
          DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField1.setText(dateformat.format(calendarInicio.getTime()));
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarInicio = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField1.setText(dateformat1.format(calendarInicio.getTime()));
          jCalendar1.setCalendar(calendarInicio);
        }
      } 
    });
    
    this.jCalendar2.addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar2.setLocale((Locale) propertychangeevent.getNewValue());
          DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar2.getLocale());
          jTextField2.setText(dateformat.format(calendarFin.getTime()));
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarFin = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar2.getLocale());
          jTextField2.setText(dateformat1.format(calendarFin.getTime()));
          jCalendar2.setCalendar(calendarFin);
        }
      } 
    });
    
    
    this.getContentPane().add(jCalendar2, null);
    this.getContentPane().add(jCalendar1, null);
    this.getContentPane().add(jLabel5, null);
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jTextField3, null);
    this.getContentPane().add(jLabel4, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(jComboBox1, null);
  }

  private void jButton1_actionPerformed(ActionEvent e)
  {
  	int numCasa=((Integer)jComboBox1.getSelectedItem()).intValue();
  	Date diaIni=new Date(jCalendar1.getCalendar().getTime().getTime());
    //Eliminamos la parte de hora:minuto:segundo:ms de la fecha para normalizarla con lo que aparece en la BD
  	diaIni=Date.valueOf(diaIni.toString());
  	Date diaFin=new Date(jCalendar2.getCalendar().getTime().getTime());
  	//Eliminamos la parte de hora:minuto:segundo:ms de la fecha para normalizarla con lo que aparece en la BD
  	diaFin=Date.valueOf(diaFin.toString());
  	//Puede que lance una excepcion en caso de que no se introduzca un numero
  	float precio= Float.parseFloat(jTextField3.getText());
  	try {
  		PantallaInicio.interfazfachada.asignarOferta(numCasa, diaIni, diaFin, precio);
		this.setVisible(false);
	} catch (Exception e1) {

		e1.printStackTrace();
	}
  }
  private void jButton2_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }
  
 private void jTextField3_focusLost()
 {
   try
  {
    new Integer (jTextField3.getText());
    jLabel5.setText("");
  }
  catch (NumberFormatException ex)
  {
    jLabel5.setText("Error: Introduzca un numero");
  }
 }
}