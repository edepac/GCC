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

public class Reserva extends JDialog {

	private final JPanel contentPanel = new JPanel();


	private final JLabel label = DefaultComponentFactory.getInstance().createTitle("New JGoodies title");
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JComboBox<String> comboBox, comboBoxPista;
	JRadioButton rdbtnPista, rdbtnPista_1, rdbtnPista_2, rdbtnPista_3, rdbtnPista_4, rdbtnPista_5;
	
	
	Connection connection=conexionSQL.dbConector();
	String Usuario=null;
	
	public Reserva(String USUARIO) {

		Usuario=USUARIO;
		iniciar();
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
				
				JOptionPane.showMessageDialog(null, "En esta ventana se deberán seleccionar todo los campos \n"
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
		lblPista.setBounds(328, 36, 46, 14);
		contentPanel.add(lblPista);
	}

	public void iniciar(){

		
		contentPanel.setBackground(new Color(135, 206, 250));
		
		setTitle("Reserva");
		setBounds (100, 100, 624, 527);
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
		rdbtnManana.setBounds(40, 326, 127, 25);
		contentPanel.add(rdbtnManana);
		{
			JRadioButton rdbtnTarde = new JRadioButton("Tarde");
			rdbtnTarde.setFont(new Font("Tahoma", Font.PLAIN, 15));
			rdbtnTarde.setBackground(new Color(135, 206, 250));
			buttonGroup.add(rdbtnTarde);
			rdbtnTarde.setBounds(198, 326, 127, 25);
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
		rdbtnPista.setBounds(8, 356, 127, 25);
		contentPanel.add(rdbtnPista);
		
		rdbtnPista_1 = new JRadioButton("Calle 2");
		rdbtnPista_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista_1.setBackground(new Color(135, 206, 250));
		buttonGroup_1.add(rdbtnPista_1);
		rdbtnPista_1.setBounds(8, 388, 127, 25);
		contentPanel.add(rdbtnPista_1);
		
		rdbtnPista_2 = new JRadioButton("Calle 3");
		rdbtnPista_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista_2.setBackground(new Color(135, 206, 250));
		buttonGroup_1.add(rdbtnPista_2);
		rdbtnPista_2.setBounds(139, 356, 127, 25);
		contentPanel.add(rdbtnPista_2);
		
		rdbtnPista_3 = new JRadioButton("Calle 4");
		rdbtnPista_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista_3.setBackground(new Color(135, 206, 250));
		buttonGroup_1.add(rdbtnPista_3);
		rdbtnPista_3.setBounds(139, 388, 127, 25);
		contentPanel.add(rdbtnPista_3);
		
		rdbtnPista_4 = new JRadioButton("Calle 5");
		rdbtnPista_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista_4.setBackground(new Color(135, 206, 250));
		buttonGroup_1.add(rdbtnPista_4);
		rdbtnPista_4.setBounds(270, 356, 127, 25);
		contentPanel.add(rdbtnPista_4);
		
		rdbtnPista_5 = new JRadioButton("Calle 6");
		rdbtnPista_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnPista_5.setBackground(new Color(135, 206, 250));
		buttonGroup_1.add(rdbtnPista_5);
		rdbtnPista_5.setBounds(270, 388, 127, 25);
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
								horarioSelec="Mañana";
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
							int numPista;
							
							numPista = comboBoxPista.getSelectedIndex();
							
													
							int day=calendar.getCalendar().get(Calendar.DAY_OF_MONTH);
							int month=calendar.getCalendar().get(Calendar.MONTH);
							month=month+1;
							int year=calendar.getCalendar().get(Calendar.YEAR);
							
							//HAcer aqui el JText para mostrar disponibilidad de pistas
							
							String fecha=day+"/"+month+"/"+year;
							if(!validarReserva(fecha, numeroCalle, horarioSelec)){
								JOptionPane.showMessageDialog(null, "La pista en ese momento se encuentra reservada");
							}else if(year<=Integer.parseInt(agno)  & month<=mes2 & day<=Integer.parseInt(dia)){
								JOptionPane.showMessageDialog(null, "Fecha no válida");
							}
							else {
								
									Reservar(fecha,numeroCalle,horarioSelec, numPista);
								
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
		}
	}
	
	public void Reservar(String fecha, String numC, String horario, int numP){
		
		String user;
		if(Usuario==null){
			
			user=comboBox.getSelectedItem().toString();
		}else{
			user=Usuario;
		}
		
		try{
			String query="insert into Reservas (Usuario, Fecha, NumeroCalle, Horario, IdPista) values (?,?,?,?,?)";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1, user);
			pst.setString(2, fecha);
			pst.setString(3, numC);
			pst.setString(4, horario);
			pst.setInt(5, numP+1);
			

			pst.execute();
			
			pst.close();
			
				JOptionPane.showMessageDialog(null, "Gracias, su reserva se ha realizado correctamente");
			
		}catch(Exception e1){
			e1.printStackTrace();
		}
	}
	
	public boolean validarReserva(String fecha, String numP, String horario){
		boolean libre=true;
		
		try{
			String query="SELECT * FROM Reservas where Fecha='"+fecha+"' and NumeroCalle='"+numP+"' and Horario='"+horario+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()){
				libre=false;
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		
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
	
	
	
	//SALVAVIDAS
	
}
