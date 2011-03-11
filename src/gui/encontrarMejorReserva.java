package gui;

import javax.swing.JFrame;
import java.awt.Dimension;

import casarural.Oferta;

import com.toedter.calendar.JCalendar;
import java.awt.Rectangle;
import java.util.Calendar;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.*;
public class encontrarMejorReserva extends JFrame 
{
  private JCalendar jCalendar1 = new JCalendar();
  private JCalendar jCalendar2 = new JCalendar();
  private Calendar calendarMio = null;
  private Calendar calendarMio2 = null;
  private JTextField jTextField1 = new JTextField();
  private JTextField jTextField2 = new JTextField();
  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private String[] lista={"precio","tamaño"};
  private JComboBox jComboBox1 = new JComboBox(lista);
  private JLabel jLabel3 = new JLabel();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private JLabel jLabel9 = new JLabel();
  private JLabel jLabel10 = new JLabel();

  public encontrarMejorReserva()
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
    this.setSize(new Dimension(528, 379));
    this.setTitle("IUencontrarMejorReserva");
    jCalendar1.setBounds(new Rectangle(5, 40, 225, 150));
    jCalendar2.setBounds(new Rectangle(260, 40, 225, 150));
    jTextField1.setBounds(new Rectangle(35, 190, 155, 30));
    jTextField1.setEditable(false);
    jTextField2.setBounds(new Rectangle(290, 190, 155, 30));
    jTextField2.setEditable(false);
    jLabel1.setText("Habitaciones");
    jLabel1.setBounds(new Rectangle(10, 255, 105, 25));
    jLabel2.setText("Baños");
    jLabel2.setBounds(new Rectangle(10, 300, 80, 25));
    jTextField3.setBounds(new Rectangle(110, 255, 55, 25));
    jTextField4.setBounds(new Rectangle(110, 295, 55, 25));
    jComboBox1.setBounds(new Rectangle(345, 255, 110, 20));
    jLabel3.setText("Criterio");
    jLabel3.setBounds(new Rectangle(245, 255, 80, 20));
    jButton1.setText("Buscar");
    jButton1.setBounds(new Rectangle(240, 305, 85, 30));
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    jButton2.setText("Cancelar");
    jButton2.setBounds(new Rectangle(350, 305, 85, 30));
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton2_actionPerformed(e);
        }
      });
    jLabel9.setText("Fecha de inicio");
    jLabel9.setBounds(new Rectangle(15, 15, 145, 15));
    jLabel10.setText("Fecha de fin");
    jLabel10.setBounds(new Rectangle(265, 10, 145, 20));
    this.getContentPane().add(jLabel10, null);
    this.getContentPane().add(jLabel9, null);
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jComboBox1, null);
    this.getContentPane().add(jTextField4, null);
    this.getContentPane().add(jTextField3, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jCalendar2, null);
    this.getContentPane().add(jCalendar1, null);

 jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
          DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField1.setText(dateformat.format(calendarMio.getTime()));
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarMio = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField1.setText(dateformat1.format(calendarMio.getTime()));
          jCalendar1.setCalendar(calendarMio);
        }
      }}); 

jCalendar2.addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar2.setLocale((Locale) propertychangeevent.getNewValue());
          DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar2.getLocale());
          jTextField2.setText(dateformat.format(calendarMio2.getTime()));
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarMio2 = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar2.getLocale());
          jTextField2.setText(dateformat1.format(calendarMio2.getTime()));
          jCalendar2.setCalendar(calendarMio2);
        }
      }}); 

    
  }

  private void jButton2_actionPerformed(ActionEvent e)
  {System.exit(0);
  }

  private void jButton1_actionPerformed(ActionEvent e)
  {	Date diaIni=new Date(jCalendar1.getCalendar().getTime().getTime());
 		diaIni=Date.valueOf(diaIni.toString());
    Date diaFin=new Date(jCalendar2.getCalendar().getTime().getTime());
 		diaFin=Date.valueOf(diaFin.toString());
    String criterio=(String)jComboBox1.getSelectedItem();
    int habitaciones=Integer.parseInt(jTextField3.getText());
    int banos=Integer.parseInt(jTextField4.getText());
    try 
  {
  Oferta of=(Oferta)PantallaInicio.interfazfachada.obtenerMejorOferta(diaIni,diaFin,habitaciones,banos,criterio);
  if(of.getTamano()!=0){
  //dmo.inicializarPantalla(true,laMejorOferta.getNumCasa(),laMejorOferta.getPrecio(),laMejorOferta.getTamano());
DetallesMejorOferta dmo=new DetallesMejorOferta(of.getNumCasa(),of.getPrecio(),of.getTamano(),diaIni,diaFin);
  dmo.setVisible(true);

  }
  else 
  {
    DetallesMejorOferta dmo=new DetallesMejorOferta();
    dmo.setVisible(true);
  }
  }catch (Exception ex){ex.printStackTrace();
  }//catch (RemoteException re){re.printStackTrace();};
  }
}
