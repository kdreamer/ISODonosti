package gui;
import casarural.Oferta;
import casarural.OfertasEnMemoriaPrincipal;

import com.toedter.calendar.JCalendar;


import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JTextField;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextArea;

public class ConsultarDisponibilidad extends JFrame 
{

  private JLabel jLabel1 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField2 = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JTextField jTextField3 = new JTextField();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  
  // Codigo para el JCalendar
  private JCalendar jCalendar1 = new JCalendar();
  private Calendar calendarMio = null;
  private JLabel jLabel4 = new JLabel();
  private JTextArea jTextArea1 = new JTextArea();
  private JScrollPane scrollPane = new JScrollPane();


  public ConsultarDisponibilidad()
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
    this.setSize(new Dimension(433, 548));
    this.setTitle("Consultar Disponibilidad");
    jLabel1.setText("Codigo de la casa:");
    jLabel1.setBounds(new Rectangle(40, 20, 105, 25));
    jTextField1.setBounds(new Rectangle(190, 20, 155, 25));
    jTextField1.setText("0");
    jLabel2.setText("Dia de inicio:");
    jLabel2.setBounds(new Rectangle(40, 55, 75, 25));
    jTextField2.setBounds(new Rectangle(190, 210, 155, 25));
    jTextField2.setEditable(false);
    jLabel3.setText("Numero de noches:");
    jLabel3.setBounds(new Rectangle(40, 250, 115, 25));
    jTextField3.setBounds(new Rectangle(190, 250, 155, 25));
    jTextField3.setText("0");
    jButton1.setText("Aceptar");
    jButton1.setBounds(new Rectangle(55, 455, 130, 30));
    jButton1.addActionListener(new ActionListener()
    	      {
    	        public void actionPerformed(ActionEvent e)
    	        {
    	          jButton1_actionPerformed(e);
    	        }
    	      });
    jButton2.setText("Cancelar");
    jButton2.setBounds(new Rectangle(230, 455, 130, 30));
    
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
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton2_actionPerformed(e);
        }
      });
    jLabel4.setBounds(new Rectangle(55, 300, 305, 30));
    jLabel4.setForeground(Color.red);
    jTextArea1.setEditable(false);
    jCalendar1.setBounds(new Rectangle(190, 60, 225, 150));
    scrollPane.setBounds(new Rectangle(45, 305, 320, 135));
    jTextArea1.setText("");
    scrollPane.getViewport().add(jTextArea1, null);
    
    this.getContentPane().add(scrollPane, null);
    this.getContentPane().add(jCalendar1, null);
    this.getContentPane().add(jLabel4, null);
    this.getContentPane().add(jButton2, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jTextField3, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jLabel1, null);
    
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

  private void jButton2_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }
  
  private void jTextField1_focusLost()
 {
   try
  {
    new Integer (jTextField1.getText());
    jLabel4.setText("");
  }
  catch (NumberFormatException ex)
  {
    jLabel4.setText("Error: Introduzca un numero");
  }
 }
 private void jTextField3_focusLost()
 {
   try
  {
    new Integer (jTextField3.getText());
    jLabel4.setText("");
  }
  catch (NumberFormatException ex)
  {
    jLabel4.setText("Error: Introduzca un numero");
  }
 }

 private void jButton1_actionPerformed(ActionEvent e)
 {		
 		// Numero de Casa
 		int numCasa=Integer.parseInt(jTextField1.getText());
 		// Dia inicio
 		Date diaIni=new Date(jCalendar1.getCalendar().getTime().getTime());
 		diaIni=Date.valueOf(diaIni.toString());
 		final long diams=1000*60*60*24;
 		long numnoches= diams * Integer.parseInt(jTextField3.getText());
    	Date diaFin= new Date((long)(diaIni.getTime()+numnoches));
    	jTextArea1.setText("");
    	try {
			OfertasEnMemoriaPrincipal omp=PantallaInicio.interfazfachada.obtenerOfertas(numCasa,diaIni, diaFin);
			Vector ompVectorStrict=omp.obtenerOfertasIncluidasEntre(diaIni,diaFin);
			Vector ompVector=omp.obtenerOfertasEntre(diaIni,diaFin);
		    Date dia=new Date(diaIni.getTime());
		    dia=Date.valueOf(dia.toString());
		    String estadoCasaRural;
		    String estadoCasaRuralStrict;
		    Vector res;

		    while (dia.getTime()<=diaFin.getTime())//hasta el ultimo dia
		    {
		    	estadoCasaRural=omp.estadoCasaRural(dia, ompVector);
		    	estadoCasaRuralStrict=omp.estadoCasaRural(dia, ompVectorStrict);
		    	if (estadoCasaRural==estadoCasaRuralStrict){
		    		
		    	} else if (estadoCasaRural!=estadoCasaRuralStrict) {
		    		
		    		Date DiaIniAux=((Oferta)omp.obtenerOfertasEntre(dia,dia).elementAt(0)).getDiaIni();
		    		Date DiaFinAux=((Oferta)omp.obtenerOfertasEntre(dia,dia).elementAt(0)).getDiaFin();
		    		estadoCasaRural="Libre pero oferta no completa\n  * Disponible del "+DiaIniAux.toString()+" al "+DiaFinAux.toString();
		    	}
		    	jTextArea1.append(dia.toString() + ":  " + estadoCasaRural+"\n");
				dia.setTime(dia.getTime()+diams);
		    	
		    }
		  
		} catch (RemoteException e1) {

			e1.printStackTrace();
		} catch (Exception e1) {

			e1.printStackTrace();
		}
 }
}