import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Button;
import java.awt.Font;
import java.awt.Cursor;

public class ModificarReserva extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JRadioButton dbtnPista_5;
	JRadioButton dbtnPista_4;
	JRadioButton dbtnPista_3;
	JRadioButton dbtnPista_2;
	JRadioButton dbtnPista_1;
	JRadioButton dbtnPista;
	JRadioButton dbtnManana;
	
	JRadioButton dbtnTarde;
	
	
	
	Connection connection=conexionSQL.dbConector();
	private JTextField textFechaDeLaReserva;
	String idRes;
	String fecha;
	String NumeroCalle;
	String Horario;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel lblAyuda;

	
	public ModificarReserva(String r,String f, String NP, String H) {
		idRes=r;
		fecha=f;
		NumeroCalle=NP;
		Horario=H;
		setTitle("Modificar reserva");
		setBounds(100, 100, 450, 300);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(135, 206, 250));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel textArea = new JLabel();
			textArea.setFont(new Font("Tahoma", Font.BOLD, 15));
			textArea.setText("D\u00EDa:");
			textArea.setBounds(50, 26, 54, 22);
			contentPanel.add(textArea);
		}
		{
			dbtnManana = new JRadioButton("Ma\u00F1ana");
			dbtnManana.setFont(new Font("Tahoma", Font.PLAIN, 15));
			dbtnManana.setBackground(new Color(135, 206, 250));
			buttonGroup.add(dbtnManana);
			dbtnManana.setBounds(50, 72, 127, 25);
			if(Horario.toLowerCase().equals("mañana")){
				dbtnManana.setSelected(true);
			}
			contentPanel.add(dbtnManana);
		}
		{
			dbtnTarde = new JRadioButton("Tarde");
			dbtnTarde.setFont(new Font("Tahoma", Font.PLAIN, 15));
			dbtnTarde.setBackground(new Color(135, 206, 250));
			buttonGroup.add(dbtnTarde);
			dbtnTarde.setBounds(208, 72, 127, 25);
			if(Horario.toLowerCase().equals("tarde")){
				dbtnTarde.setSelected(true);
			}
			contentPanel.add(dbtnTarde);
		}
		{
			dbtnPista = new JRadioButton("Calle 1");
			dbtnPista.setFont(new Font("Tahoma", Font.PLAIN, 15));
			dbtnPista.setBackground(new Color(135, 206, 250));
			buttonGroup_1.add(dbtnPista);
			dbtnPista.setBounds(23, 139, 127, 25);
			if(NumeroCalle.toLowerCase().equals("1")){
				dbtnPista.setSelected(true);
			}
			contentPanel.add(dbtnPista);
		}
		{
			dbtnPista_1 = new JRadioButton("Calle 2");
			dbtnPista_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
			dbtnPista_1.setBackground(new Color(135, 206, 250));
			buttonGroup_1.add(dbtnPista_1);
			dbtnPista_1.setBounds(23, 171, 127, 25);
			if(NumeroCalle.toLowerCase().equals("2")){
				dbtnPista_1.setSelected(true);
			}
			contentPanel.add(dbtnPista_1);
		}
		{
			dbtnPista_2 = new JRadioButton("Calle 3");
			dbtnPista_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			dbtnPista_2.setBackground(new Color(135, 206, 250));
			buttonGroup_1.add(dbtnPista_2);
			dbtnPista_2.setBounds(154, 139, 127, 25);
			if(NumeroCalle.toLowerCase().equals("3")){
				dbtnPista_2.setSelected(true);
			}
			contentPanel.add(dbtnPista_2);
		}
		{
			dbtnPista_3 = new JRadioButton("Calle 4");
			dbtnPista_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
			dbtnPista_3.setBackground(new Color(135, 206, 250));
			buttonGroup_1.add(dbtnPista_3);
			dbtnPista_3.setBounds(154, 171, 127, 25);
			if(NumeroCalle.toLowerCase().equals("4")){
				dbtnPista_3.setSelected(true);
			}
			contentPanel.add(dbtnPista_3);
		}
		{
			dbtnPista_4 = new JRadioButton("Calle 5");
			dbtnPista_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
			dbtnPista_4.setBackground(new Color(135, 206, 250));
			buttonGroup_1.add(dbtnPista_4);
			dbtnPista_4.setBounds(285, 139, 127, 25);
			if(NumeroCalle.toLowerCase().equals("5")){
				dbtnPista_4.setSelected(true);
			}
			contentPanel.add(dbtnPista_4);
		}
		{
			dbtnPista_5 = new JRadioButton("Calle 6");
			dbtnPista_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
			dbtnPista_5.setBackground(new Color(135, 206, 250));
			buttonGroup_1.add(dbtnPista_5);
			dbtnPista_5.setBounds(285, 171, 127, 25);
			if(NumeroCalle.toLowerCase().equals("6")){
				dbtnPista_5.setSelected(true);
			}
			contentPanel.add(dbtnPista_5);
		}
		
		textFechaDeLaReserva = new JTextField();
		textFechaDeLaReserva.setBackground(new Color(135, 206, 250));
		textFechaDeLaReserva.setText(fecha);
		textFechaDeLaReserva.setEditable(false);
		textFechaDeLaReserva.setBounds(125, 26, 116, 22);
		contentPanel.add(textFechaDeLaReserva);
		textFechaDeLaReserva.setColumns(10);
		
		JButton botonAyudaModif = new JButton();
		botonAyudaModif.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		ImageIcon helpPls = new ImageIcon(getClass().getResource("Imagen5-1.png"));
		ImageIcon helpPls1 = new ImageIcon(helpPls.getImage().getScaledInstance(40, 40, 0));
		
		botonAyudaModif.setIcon(helpPls1);
		
		botonAyudaModif.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JOptionPane.showMessageDialog(null, "En esta ventana se deberán seleccionar los campos con la nueva\n"
						+                           "información para modificar la reserva con los cambios deseados.");
				
				
			}
		});
		
		botonAyudaModif.setOpaque(false);
		botonAyudaModif.setContentAreaFilled(false);
		botonAyudaModif.setBorderPainted(false);
		botonAyudaModif.setBounds(378, 38, 47, 42);
		contentPanel.add(botonAyudaModif);
		{
			lblAyuda = new JLabel("Ayuda");
			lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblAyuda.setForeground(Color.WHITE);
			lblAyuda.setBounds(377, 10, 55, 29);
			contentPanel.add(lblAyuda);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(135, 206, 250));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Modificar reserva");
				okButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				okButton.setBackground(Color.BLACK);
				okButton.setForeground(Color.WHITE);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String numeroCalle=null;
						String horarioSelec;
						if(dbtnPista.isSelected()){
							numeroCalle="1";
						}else if(dbtnPista_1.isSelected()){
							numeroCalle="2";
						}else if(dbtnPista_2.isSelected()){
							numeroCalle="3";
						}else if(dbtnPista_3.isSelected()){
							numeroCalle="4";	
						}else if(dbtnPista_4.isSelected()){
							numeroCalle="5";	
						}else if(dbtnPista_5.isSelected()){
							numeroCalle="6";	
						}
						
						
						if(dbtnManana.isSelected()){
							horarioSelec="Mañana";
						}else{
							horarioSelec="Tarde";
						}
						
						if(!validarReserva(fecha, numeroCalle, horarioSelec)){
							JOptionPane.showMessageDialog(null, "La pista en ese momento se encuentra reservada");
						}else{
							
								modificarRes(numeroCalle,horarioSelec);
							
							dispose();
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
				cancelButton.setBackground(Color.BLACK);
				cancelButton.setForeground(Color.WHITE);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(null);
	}
	
	public void modificarRes(String numP, String hor){
		try{
			String query="update Reservas set NumeroCalle='"+numP+"' ,Horario='"+hor+"' where idReserva='"+idRes+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			
			
			pst.execute();
			
			pst.close();
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
}
