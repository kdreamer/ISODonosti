package gui;
import casarural.Reserva;

import com.toedter.calendar.*;

import excepciones.NoSePuedeReservarException;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.rmi.RemoteException;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.Color;
import java.util.GregorianCalendar;
public class ReservarCasa extends JFrame
{
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private JLabel jLabel1 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private JButton jButton2 = new JButton();
  private JButton jButton3 = new JButton();
  private JCheckBox checkServicioRecogida;
  
  // Codigo para el JCalendar
  private JCalendar jCalendar1 = new JCalendar();
  private Calendar calendarMio = null;
  private JLabel jLabel5 = new JLabel();
  
  //Para inicializar la página con mis propios datos
  private boolean deOferta = false;
  private int codigo;
  private Date diaIni;
  private int numNomches;
  

public ReservarCasa()
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
  public ReservarCasa(int numCasa,Date inicio,Date fin)
  {
    try
    {
      jbInit();
      
 jTextField1.setText(Integer.toString(numCasa));
 long numNoches=(fin.getTime()-inicio.getTime())/(1000*60*60*24);
 jTextField3.setText(Long.toString(numNoches));
 DateFormat dateformat1 = DateFormat.getDateInstance(1);

 Date diaIni= new Date((long)(inicio.getTime()));
  jTextField2.setText(dateformat1.format(diaIni));
GregorianCalendar cal=new GregorianCalendar();
cal.setTime(diaIni);
int anio=cal.get(Calendar.YEAR);
int mes=cal.get(Calendar.MONTH);
int dia=cal.get(Calendar.DAY_OF_MONTH);




  JYearChooser yc=jCalendar1.getYearChooser();
    JMonthChooser mc=   jCalendar1.getMonthChooser();
    JDayChooser dc= jCalendar1.getDayChooser();
  
    
    yc.setYear(anio);
    mc.setMonth(mes);
    dc.setDay(dia);

    


    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception
  {
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(410, 460));
    this.setTitle("Reservar casa rural");
    jLabel1.setText("Codigo de la casa:");
    jLabel1.setBounds(new Rectangle(15, 10, 115, 20));
    jTextField1.setBounds(new Rectangle(155, 10, 140, 20));
    jTextField1.setToolTipText("null");
    jTextField1.setText("0");
    jTextField1.addFocusListener(new FocusListener()
      {
          public void focusGained(FocusEvent e)
          {
          }
  
          public void focusLost(FocusEvent e)
          {
            jTextField1_focusLost();
          }
      });
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
    jTextField4.addFocusListener(new FocusListener()
      {
          public void focusGained(FocusEvent e)
          {
          }
  
          public void focusLost(FocusEvent e)
          {
            jTextField4_focusLost();
          }
      });
    jLabel2.setText("Dia de entrada:");
    jLabel2.setBounds(new Rectangle(15, 40, 115, 20));
    jLabel3.setText("Numero de noches:");
    jLabel3.setBounds(new Rectangle(15, 240, 115, 20));
    jLabel4.setText("Telefono de contacto:");
    jLabel4.setBounds(new Rectangle(15, 270, 140, 20));
    jTextField2.setBounds(new Rectangle(155, 205, 140, 20));
    jTextField2.setEditable(false);
    jTextField3.setBounds(new Rectangle(155, 240, 140, 20));
    jTextField3.setText("0");
    jTextField4.setBounds(new Rectangle(155, 270, 140, 20));
    jTextField4.setText("0");
    jButton2.setText("Aceptar");
    jButton2.setBounds(new Rectangle(50, 385, 130, 30));
    jButton2.setSize(new Dimension(130, 30));
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
        	// Codigo a ejecutar cuando se clickee sobre el boton aceptar
        	// Codigo de la casa
        	int numCasa=Integer.parseInt(jTextField1.getText());
        	// Dia entrada
        	Date diaIni= new Date(jCalendar1.getCalendar().getTime().getTime());
        	//Eliminamos la parte de hora:minuto:segundo:ms de la fecha para normalizarla con lo que aparece en la BD
          	diaIni=Date.valueOf(diaIni.toString());
        	// Dia fin
        	// Numero de dias expresado en milisegundos
        	long numnoches=1000*60*60*24* Integer.parseInt(jTextField3.getText());
        	Date diaFin= new Date((long)(diaIni.getTime()+numnoches));
        	// Telefono de contacto
        	String numTfnoReserva=jTextField4.getText();
        	try {
				// Llamada al metodo remoto Reservar
				Reserva reserva=PantallaInicio.interfazfachada.reservar(diaIni, diaFin, numCasa, numTfnoReserva);
				// Si llega a ejecutarse lo siguiente es que se puede reservar
				ConfirmarReserva confirmarreserva=new ConfirmarReserva(reserva.getNumCasa(), reserva.getPrecioTotal(), reserva.getNumReserva());
				confirmarreserva.setVisible(true);
			} catch (RemoteException e1) {
	
				e1.printStackTrace();
			} catch (NoSePuedeReservarException e1) {
				//INCLUDES Consultar disponibilidad
				jLabel5.setText("Error: No se puede reservar");
				JFrame a = new ConsultarDisponibilidad();
			    a.setVisible(true);
			}
        }
      });
    jButton3.setText("Cancelar");
    jButton3.setBounds(new Rectangle(220, 385, 130, 30));
    jButton3.setSize(new Dimension(130, 30));
    jButton3.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton3_actionPerformed(e);
        }
      });
    jLabel5.setBounds(new Rectangle(50, 354, 300, 20));
    jLabel5.setForeground(Color.red);
    jCalendar1.setBounds(new Rectangle(155, 50, 235, 145));
    this.getContentPane().add(jCalendar1, null);
    this.getContentPane().add(jLabel5, null);
    this.getContentPane().add(jButton3, null);
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jTextField4, null);
    this.getContentPane().add(jTextField3, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(jLabel4, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jLabel1, null);
    
    checkServicioRecogida = new JCheckBox("Reservar servicio de recogida");
    checkServicioRecogida.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		if (checkServicioRecogida.isSelected()) {
    			ReservarCasa.this.setSize(new Dimension(410, 600));
    			jButton2.setBounds(50, 525, 130, 30);
    			jButton3.setBounds(220, 525, 130, 30);
    		}
    		else {
    			ReservarCasa.this.setSize(new Dimension(410, 460));
    			jButton2.setBounds(50, 385, 130, 30);
    			jButton3.setBounds(220, 385, 130, 30);
    		}
    	}
    });
    checkServicioRecogida.setBounds(15, 324, 209, 23);
    getContentPane().add(checkServicioRecogida);
    
    // Codigo para el JCalendar
    this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
          DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField2.setText(dateformat.format(calendarMio.getTime()));
          
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarMio = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField2.setText(dateformat1.format(calendarMio.getTime()));
          jCalendar1.setCalendar(calendarMio);
        }
      } 
    });
  }
  
  public static void main (String[] args)
  {
    new ReservarCasa();
  }

  private void jButton3_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }

 private void jTextField1_focusLost()
 {
   try
  {
    new Integer (jTextField1.getText());
    jLabel5.setText("");
  }
  catch (NumberFormatException ex)
  {
    jLabel5.setText("Error: Introduzca un numero");
  }
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
 
 private void jTextField4_focusLost()
 {
   try
  {
    new Integer (jTextField4.getText());
    jLabel5.setText("");
  }
  catch (NumberFormatException ex)
  {
    jLabel5.setText("Error: Introduzca un numero");
  }
 }
}