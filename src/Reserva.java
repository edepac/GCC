import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JCalendar;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class Reserva extends JDialog {

	private final JPanel contentPanel = new JPanel();


	private final JLabel label = DefaultComponentFactory.getInstance().createTitle("New JGoodies title");
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JComboBox<String> comboBox, comboBoxPista;
	JRadioButton rdbtnPista, rdbtnPista_1, rdbtnPista_2, rdbtnPista_3, rdbtnPista_4, rdbtnPista_5;
	JTextArea textArea;
	
	JScrollPane scrollPane;
	
	JCheckBox chckbxEntrenador, chckbxPartido;
	JSpinner spinner, spinner_1, spinner_2;
	
	
	
	Connection connection=conexionSQL.dbConector();
	String Usuario=null;
	
	public Reserva(String USUARIO) {

		Usuario=USUARIO;
		iniciar();		
		comboBoxPista = new JComboBox<String>();
		comboBoxPista.setBounds(371, 33, 145, 20);
		contentPanel.add(comboBoxPista);	
		
		comboBoxPista.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				JTable tablaPista = new JTable();
				tablaPista.setModel(DbUtils.resultSetToTableModel(consultaPista()));
				int row;
				int numCalles;
				
				row = comboBoxPista.getSelectedIndex();
				numCalles = (int) tablaPista.getValueAt(row, 1);
				mostrarCalles(numCalles);
				
			}
		});
		
		comboPista();
		
		
		JButton btnAyudaReserva = new JButton();
		btnAyudaReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		ImageIcon helpPls = new ImageIcon(getClass().getResource("Imagen5-1.png"));
		ImageIcon helpPls1 = new ImageIcon(helpPls.getImage().getScaledInstance(40, 40, 0));
		
		btnAyudaReserva.setIcon(helpPls1);
		
		btnAyudaReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "En esta ventana se deber�n seleccionar todo los campos \n"
													+"para realizar la reserva en la fecha y lugar deseados.");
				
			}
		});
		
		btnAyudaReserva.setOpaque(false);
		btnAyudaReserva.setContentAreaFilled(false);
		btnAyudaReserva.setBorderPainted(false);
		btnAyudaReserva.setBounds(550, 39, 46, 39);
		contentPanel.add(btnAyudaReserva);
		
		JLabel lblAyuda = new JLabel("Ayuda");
		lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAyuda.setForeground(Color.WHITE);
		lblAyuda.setBounds(552, 11, 56, 28);
		contentPanel.add(lblAyuda);
		
		
		JLabel lblPista = new JLabel("Pista");
		lblPista.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPista.setForeground(new Color(0, 0, 139));
		lblPista.setBounds(328, 36, 46, 14);
		contentPanel.add(lblPista);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(402, 110, 192, 200);
		contentPanel.add(scrollPane);
		
		JLabel lblParaEstaFecha = new JLabel("Campos reservados:");
		lblParaEstaFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblParaEstaFecha.setForeground(new Color(0, 0, 139));
		lblParaEstaFecha.setBounds(402, 92, 150, 14);
		contentPanel.add(lblParaEstaFecha);
		
		chckbxEntrenador = new JCheckBox("Entrenador");
		chckbxEntrenador.setBounds(8, 457, 97, 23);
		chckbxEntrenador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxEntrenador.setBackground(new Color(135, 206, 250));
		contentPanel.add(chckbxEntrenador);
		
		chckbxPartido = new JCheckBox("Partido");
		chckbxPartido.setBounds(139, 457, 97, 23);
		chckbxPartido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxPartido.setBackground(new Color(135, 206, 250));
		contentPanel.add(chckbxPartido);
		
		JLabel lblAlquiler = new JLabel("ALQUILER:");
		lblAlquiler.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAlquiler.setBounds(443, 337, 97, 25);
		contentPanel.add(lblAlquiler);
		
		JLabel label_1 = new JLabel("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_1.setBounds(0, 436, 628, 14);
		contentPanel.add(label_1);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner.setBounds(402, 371, 29, 20);
		contentPanel.add(spinner);
		
		JLabel lblCasco = new JLabel("Casco");
		lblCasco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCasco.setBounds(433, 369, 54, 20);
		contentPanel.add(lblCasco);
		
		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner_1.setBounds(402, 403, 29, 20);
		contentPanel.add(spinner_1);
		
		JLabel lblEscoba = new JLabel("Escoba");
		lblEscoba.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEscoba.setBounds(433, 406, 46, 14);
		contentPanel.add(lblEscoba);
		
		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner_2.setBounds(519, 371, 29, 20);
		contentPanel.add(spinner_2);
		
		JLabel lblSlider = new JLabel("Slider");
		lblSlider.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSlider.setBounds(550, 372, 58, 14);
		contentPanel.add(lblSlider);
		
		JButton btnPrecios = new JButton("Precios");
		btnPrecios.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPrecios.setBackground(Color.BLACK);
		btnPrecios.setForeground(Color.WHITE);
		btnPrecios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPrecios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null, "Los precios de las reservas son: \n"
						+ "-------------------------------------- \n"
						+ "Reservar una calle: 100,00 � \n"
						+ "Reservar un entrenador: 50,00 � \n"
						+ "Reservar casco: 10,00 �/unidad \n"
						+ "Reservar slider: 14,00 �/unidad \n"
						+ "Reservar escoba: 15,00 �/unidad ");
				
			}
		});
		btnPrecios.setBounds(426, 459, 182, 23);
		contentPanel.add(btnPrecios);
		
	}
	
	public Reserva(){
		
		iniciar();
		comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(135, 206, 250));
		comboBox.setBounds(104, 32, 162, 22);
		setResizable(false);
		
		comboBoxPista = new JComboBox<String>();
		comboBoxPista.setBounds(371, 33, 145, 20);
		contentPanel.add(comboBoxPista);	
		
		comboBoxPista.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				JTable tablaPista = new JTable();
				tablaPista.setModel(DbUtils.resultSetToTableModel(consultaPista()));
				int row;
				int numCalles;
				
				row = comboBoxPista.getSelectedIndex();
				numCalles = (int) tablaPista.getValueAt(row, 1);
				mostrarCalles(numCalles);
				
			}
		});
		
		comboUsuarios();
		comboPista();
			
		
		
		contentPanel.setBackground(new Color(135, 206, 250));
		contentPanel.add(comboBox);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUsuario.setForeground(new Color(0, 0, 139));
		lblUsuario.setBounds(27, 34, 108, 16);
		contentPanel.add(lblUsuario);
		
		JButton btnAyudaReserva = new JButton();
		btnAyudaReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		ImageIcon helpPls = new ImageIcon(getClass().getResource("Imagen5-1.png"));
		ImageIcon helpPls1 = new ImageIcon(helpPls.getImage().getScaledInstance(40, 40, 0));
		
		btnAyudaReserva.setIcon(helpPls1);
		
		btnAyudaReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "En esta ventana se deber�n seleccionar todo los campos \n"
													+"para realizar la reserva en la fecha y lugar deseados.");
				
			}
		});
		
		btnAyudaReserva.setOpaque(false);
		btnAyudaReserva.setContentAreaFilled(false);
		btnAyudaReserva.setBorderPainted(false);
		btnAyudaReserva.setBounds(550, 39, 46, 39);
		contentPanel.add(btnAyudaReserva);
		
		JLabel lblAyuda = new JLabel("Ayuda");
		lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAyuda.setForeground(Color.WHITE);
		lblAyuda.setBounds(552, 11, 56, 28);
		contentPanel.add(lblAyuda);
		
		
		JLabel lblPista = new JLabel("Pista");
		lblPista.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPista.setForeground(new Color(0, 0, 139));
		lblPista.setBounds(328, 36, 46, 14);
		contentPanel.add(lblPista);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(402, 110, 192, 200);
		contentPanel.add(scrollPane);
		
		JLabel lblParaEstaFecha = new JLabel("Campos reservados:");
		lblParaEstaFecha.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblParaEstaFecha.setForeground(new Color(0, 0, 139));
		lblParaEstaFecha.setBounds(402, 89, 150, 18);
		contentPanel.add(lblParaEstaFecha);
		
		chckbxEntrenador = new JCheckBox("Entrenador");
		chckbxEntrenador.setBounds(8, 457, 97, 23);
		chckbxEntrenador.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxEntrenador.setBackground(new Color(135, 206, 250));
		contentPanel.add(chckbxEntrenador);
		
		chckbxPartido = new JCheckBox("Partido");
		chckbxPartido.setBounds(139, 457, 97, 23);
		chckbxPartido.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckbxPartido.setBackground(new Color(135, 206, 250));
		contentPanel.add(chckbxPartido);
		
		JLabel lblAlquiler = new JLabel("ALQUILER:");
		lblAlquiler.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAlquiler.setBounds(443, 337, 97, 25);
		contentPanel.add(lblAlquiler);
		
		JLabel label_1 = new JLabel("-----------------------------------------------------------------------------------------------------------------------------------------------------------");
		label_1.setBounds(0, 436, 628, 14);
		contentPanel.add(label_1);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner.setBounds(402, 371, 29, 20);
		contentPanel.add(spinner);
		
		JLabel lblCasco = new JLabel("Casco");
		lblCasco.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCasco.setBounds(433, 369, 54, 20);
		contentPanel.add(lblCasco);
		
		spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner_1.setBounds(402, 403, 29, 20);
		contentPanel.add(spinner_1);
		
		JLabel lblEscoba = new JLabel("Escoba");
		lblEscoba.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEscoba.setBounds(433, 406, 46, 14);
		contentPanel.add(lblEscoba);
		
		spinner_2 = new JSpinner();
		spinner_2.setModel(new SpinnerNumberModel(0, 0, 5, 1));
		spinner_2.setBounds(519, 371, 29, 20);
		contentPanel.add(spinner_2);
		
		JLabel lblSlider = new JLabel("Slider");
		lblSlider.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSlider.setBounds(550, 372, 58, 14);
		contentPanel.add(lblSlider);
		
		JButton btnPrecios = new JButton("Precios");
		btnPrecios.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnPrecios.setBackground(Color.BLACK);
		btnPrecios.setForeground(Color.WHITE);
		btnPrecios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPrecios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null, "Los precios de las reservas son: \n"
						+ "-------------------------------------- \n"
						+ "Reservar una calle: 100,00 � \n"
						+ "Reservar un entrenador: 35,00 � \n"
						+ "Reservar casco: 8,00 �/unidad \n"
						+ "Reservar slider: 12,00 �/unidad \n"
						+ "Reservar escoba: 14,00 �/unidad ");
				
			}
		});
		btnPrecios.setBounds(426, 459, 182, 23);
		contentPanel.add(btnPrecios);
		
		
	}

	public void iniciar(){

		
		contentPanel.setBackground(new Color(135, 206, 250));
		
		setTitle("Reserva");
		setBounds (100, 100, 624, 565);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		JRadioButton rdbtnManana = new JRadioButton("Ma\u00F1ana");
		rdbtnManana.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnManana.setBackground(new Color(135, 206, 250));
		rdbtnManana.setSelected(true);
		buttonGroup.add(rdbtnManana);
		rdbtnManana.setBounds(40, 337, 127, 25);
		contentPanel.add(rdbtnManana);
		{
			JRadioButton rdbtnTarde = new JRadioButton("Tarde");
			rdbtnTarde.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnTarde.setBackground(new Color(135, 206, 250));
			buttonGroup.add(rdbtnTarde);
			rdbtnTarde.setBounds(198, 337, 127, 25);
			contentPanel.add(rdbtnTarde);
		}
		{
			JLabel txtrDa = new JLabel();
			txtrDa.setFont(new Font("Tahoma", Font.BOLD, 17));
			txtrDa.setForeground(new Color(0, 0, 139));
			txtrDa.setText("D\u00EDa:");
			txtrDa.setBounds(30, 85, 54, 22);
			contentPanel.add(txtrDa);
		}
		
		rdbtnPista = new JRadioButton("Calle 1");
		rdbtnPista.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista.setBackground(new Color(135, 206, 250));
		rdbtnPista.setSelected(true);
		buttonGroup_1.add(rdbtnPista);
		rdbtnPista.setBounds(8, 367, 127, 25);
		contentPanel.add(rdbtnPista);
		
		rdbtnPista_1 = new JRadioButton("Calle 2");
		rdbtnPista_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista_1.setBackground(new Color(135, 206, 250));
		buttonGroup_1.add(rdbtnPista_1);
		rdbtnPista_1.setBounds(8, 399, 127, 25);
		contentPanel.add(rdbtnPista_1);
		
		rdbtnPista_2 = new JRadioButton("Calle 3");
		rdbtnPista_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista_2.setBackground(new Color(135, 206, 250));
		buttonGroup_1.add(rdbtnPista_2);
		rdbtnPista_2.setBounds(139, 367, 127, 25);
		contentPanel.add(rdbtnPista_2);
		
		rdbtnPista_3 = new JRadioButton("Calle 4");
		rdbtnPista_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista_3.setBackground(new Color(135, 206, 250));
		buttonGroup_1.add(rdbtnPista_3);
		rdbtnPista_3.setBounds(139, 399, 127, 25);
		contentPanel.add(rdbtnPista_3);
		
		rdbtnPista_4 = new JRadioButton("Calle 5");
		rdbtnPista_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista_4.setBackground(new Color(135, 206, 250));
		buttonGroup_1.add(rdbtnPista_4);
		rdbtnPista_4.setBounds(270, 367, 127, 25);
		contentPanel.add(rdbtnPista_4);
		
		rdbtnPista_5 = new JRadioButton("Calle 6");
		rdbtnPista_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista_5.setBackground(new Color(135, 206, 250));
		buttonGroup_1.add(rdbtnPista_5);
		rdbtnPista_5.setBounds(270, 399, 127, 25);
		contentPanel.add(rdbtnPista_5);
		
		JCalendar calendar = new JCalendar();
		calendar.getDayChooser().setBackground(new Color(135, 206, 250));
		calendar.setBounds(30, 110, 345, 200);
		contentPanel.add(calendar);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(135, 206, 250));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			{
				JButton okButton = new JButton("Reservar");
				okButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				okButton.setBackground(Color.BLACK);
				okButton.setForeground(Color.WHITE);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(calendar.getDayChooser()==null){
							JOptionPane.showMessageDialog(null, "Debe escoger una fecha para la reserva");
						}else{
							String numeroCalle=null;
							String horarioSelec;
							if(rdbtnPista.isSelected()){
								numeroCalle="1";
							}else if(rdbtnPista_1.isSelected()){
								numeroCalle="2";
							}else if(rdbtnPista_2.isSelected()){
								numeroCalle="3";
							}else if(rdbtnPista_3.isSelected()){
								numeroCalle="4";	
							}else if(rdbtnPista_4.isSelected()){
								numeroCalle="5";	
							}else if(rdbtnPista_5.isSelected()){
								numeroCalle="6";	
							}
							
							
							if(rdbtnManana.isSelected()){
								horarioSelec="Ma�ana";
							}else{
								horarioSelec="Tarde";
							}
							java.util.Date fecha1 = new Date();
							String fecha2= fecha1.toString();
							
							String dia=fecha2.substring(8,10);
							String mes=fecha2.substring(4,7);
							String agno=fecha2.substring(fecha2.length()-4,fecha2.length());
							int mes2=0;
							if(mes.equals("Jan")){
								mes2=1;
							}else if(mes.equals("Feb")){
								mes2=2;
							}else if(mes.equals("Mar")){
								mes2=3;
							}else if(mes.equals("Apr")){
								mes2=4;
							}else if(mes.equals("May")){
								mes2=5;
							}else if(mes.equals("Jun")){
								mes2=6;
							}else if(mes.equals("Jul")){
								mes2=7;
							}else if(mes.equals("Aug")){
								mes2=8;
							}else if(mes.equals("Sep")){
								mes2=9;
							}else if(mes.equals("Oct")){
								mes2=10;
							}else if(mes.equals("Nov")){
								mes2=11;
							}else if(mes.equals("Dec")){
								mes2=12;
							}
						
							JTable tablaPista = new JTable();
							tablaPista.setModel(DbUtils.resultSetToTableModel(consultaPista()));
							int numPista2;
							
							numPista2 = comboBoxPista.getSelectedIndex()+1;
							String numPista = String.valueOf(numPista2);
													
							int day=calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
							int month=calendar.getCalendar().get(Calendar.MONTH);
							month=month+1;
							int year=calendar.getCalendar().get(Calendar.YEAR);
							
							
							int entrenadorSel=0, partidoSel=0, cascos, sliders, escobas;
							//HAcer aqui el JText para mostrar disponibilidad de pistas
							if(chckbxEntrenador.isSelected()){
								entrenadorSel=1;
							}
							
							if(chckbxPartido.isSelected()){
								partidoSel=1;
							}
							
							cascos  = (int) spinner.getValue();
							escobas = (int) spinner_1.getValue();
							sliders = (int) spinner_2.getValue();
							
							
						
							String fecha=day+"/"+month+"/"+year;
							if(!validarReserva(fecha, numeroCalle, horarioSelec, numPista)){
								JOptionPane.showMessageDialog(null, "La pista en ese momento se encuentra reservada");
							}else if(year<=Integer.parseInt(agno)  & month<=mes2 & day<=Integer.parseInt(dia)){
								JOptionPane.showMessageDialog(null, "Fecha no v�lida");
							}
							else {
								
									Reservar(fecha,numeroCalle,horarioSelec, numPista, entrenadorSel, partidoSel, cascos, escobas, sliders);
								
								dispose();
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				cancelButton.setForeground(Color.WHITE);
				cancelButton.setBackground(Color.BLACK);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
							dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			calendar.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent arg0) {
					int day = calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
					int month = calendar.getCalendar().get(Calendar.MONTH)+1;
					int year = calendar.getCalendar().get(Calendar.YEAR);
					
					String fechaComp=day+"/"+month+"/"+year;
					
					JTable tablaReservas = new JTable();
					tablaReservas.setModel(DbUtils.resultSetToTableModel(consultaReservas()));
					
					JTable tablaPista2 = new JTable();
					tablaPista2.setModel(DbUtils.resultSetToTableModel(consultaPista()));
					
					String fechaDB;
					String pistaDBS = null;
					
					textArea.setText("");
					
					for (int i = 0; i < tablaReservas.getRowCount(); i++) {
							fechaDB= (String) tablaReservas.getValueAt(i, 2);
							if(fechaComp.equals(fechaDB)){
								
								int numeroCalleDB = (int) tablaReservas.getValueAt(i, 3);
								String horaDB = (String) tablaReservas.getValueAt(i, 4);
								int pistaDB= (int) tablaReservas.getValueAt(i, 5);
								
								for (int j = 0; j < tablaPista2.getRowCount(); j++) {
									int indicePista = (int) tablaPista2.getValueAt(j, 0);
									if(pistaDB==indicePista){
										pistaDBS= (String) tablaPista2.getValueAt(j, 2);
									}
								}
								
								textArea.setText(textArea.getText()+"Pista: "+pistaDBS+"\n"
										+ "Horario: "+ horaDB+"\n"
												+ "Numero de calle: "+ numeroCalleDB+"\n\n");
							}
						
					}
				}
			});
		}
	}
	
	public void Reservar(String fecha, String numC, String horario, String numP, int mister, int partidillo, int casco, int escoba, int slider){
		
		String user;
		if(Usuario==null){
			
			user=comboBox.getSelectedItem().toString();
		}else{
			user=Usuario;
		}
		
		try{
			String query="insert into Reservas (Usuario, Fecha, NumeroCalle, Horario, IdPista, Entrenador, IdPArtido, Casco, Slider, Escoba) values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1, user);
			pst.setString(2, fecha);
			pst.setString(3, numC);
			pst.setString(4, horario);
			pst.setString(5, numP);
			pst.setInt(6, mister);
			pst.setInt(7, partidillo);
			pst.setInt(8, casco);
			pst.setInt(9, slider);
			pst.setInt(10, escoba);
			

			pst.execute();
			
			pst.close();
			
				JOptionPane.showMessageDialog(null, "Gracias, su reserva se ha realizado correctamente");
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
	public boolean validarReserva(String fecha, String numC, String horario, String numP){
		boolean libre=true;
		
		try{
			String query="SELECT * FROM Reservas WHERE Fecha='"+fecha+"' AND NumeroCalle='"+numC+"' AND Horario='"+horario+"' AND IdPista='"+numP+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				libre=false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		//Salvavidas
		//Salvavidas
		
		return libre;
	}
	
	public void comboUsuarios(){
		
		comboBox.setVisible(true);
		
		try{
			String query="SELECT * FROM Cliente";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				comboBox.addItem(rs.getString("Usuario"));
			}

			rs.close();
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		setLocationRelativeTo(null);
	}
	
	public void comboPista(){
		
		comboBoxPista.setVisible(true);
		
		try{
			String query="SELECT * FROM Pista";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				comboBoxPista.addItem(rs.getString("Nombre"));
			}

			rs.close();
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		setLocationRelativeTo(null);
	}
	
	
	
	
	public void mostrarCalles(int numCalles){
		
		rdbtnPista.setVisible(false);
		rdbtnPista_1.setVisible(false);
		rdbtnPista_2.setVisible(false);
		rdbtnPista_3.setVisible(false);
		rdbtnPista_4.setVisible(false);
		rdbtnPista_5.setVisible(false);
	
		for (int i = 0; i < numCalles; i++) {
			
			if(i == 0){
				rdbtnPista.setVisible(true);
			}else
			if(i == 1){
				rdbtnPista_1.setVisible(true);
			}else
			if(i == 2){
				rdbtnPista_2.setVisible(true);
			}else
			if(i == 3){
				rdbtnPista_3.setVisible(true);
			}else
			if(i == 4){
				rdbtnPista_4.setVisible(true);
			}else
			if(i == 5){
				rdbtnPista_5.setVisible(true);
			}
		
		}
	}
	
	public ResultSet consultaPista(){

		try{
			String query="SELECT * FROM Pista";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();

			
			return rs;
		}catch(Exception e){
			e.printStackTrace();

			
			return null;
		}
	}
	
	public ResultSet consultaReservas(){

		try{
			String query="SELECT * FROM Reservas";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();

			
			return rs;
		}catch(Exception e){
			e.printStackTrace();

			
			return null;
		}
	}
}
