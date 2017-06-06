import java.awt.BorderLayout;
import java.sql.*;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Cursor;




public class Registro extends JDialog {
	Connection connection= conexionSQL.dbConector();
	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textDNI;
	private JTextField textUser;
	private JTextField textPass;
	private JTextField textPass_2;
	private JTextField textEmail;
	String dni="";
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the dialog.
	 */
	public Registro() {
		setTitle("Registro");
		setBounds(100, 100, 450, 489);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setForeground(Color.WHITE);
		contentPanel.setBackground(new Color(135, 206, 250));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel txtrNombre = new JLabel();
			txtrNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtrNombre.setForeground(new Color(0, 0, 139));
			txtrNombre.setBackground(SystemColor.menu);
			txtrNombre.setText("Nombre:");
			txtrNombre.setBounds(26, 109, 108, 22);
			contentPanel.add(txtrNombre);
		}
		{
			JLabel txtrApellidos = new JLabel();
			txtrApellidos.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtrApellidos.setForeground(new Color(0, 0, 139));
			txtrApellidos.setBackground(SystemColor.menu);
			txtrApellidos.setText("Apellidos:");
			txtrApellidos.setBounds(130, 109, 108, 22);
			contentPanel.add(txtrApellidos);
		}
		{
			JLabel txtrDni = new JLabel();
			txtrDni.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtrDni.setForeground(new Color(0, 0, 139));
			txtrDni.setBackground(SystemColor.menu);
			txtrDni.setText("DNI:");
			txtrDni.setBounds(26, 168, 108, 22);
			contentPanel.add(txtrDni);
		}
		{
			JLabel txtrUser = new JLabel();
			txtrUser.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtrUser.setForeground(new Color(0, 0, 139));
			txtrUser.setBackground(SystemColor.menu);
			txtrUser.setText("User:");
			txtrUser.setBounds(26, 203, 108, 22);
			contentPanel.add(txtrUser);
		}
		{
			JLabel txtrContrasea = new JLabel();
			txtrContrasea.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtrContrasea.setForeground(new Color(0, 0, 139));
			txtrContrasea.setBackground(SystemColor.menu);
			txtrContrasea.setText("Contrase\u00F1a:");
			txtrContrasea.setBounds(26, 238, 108, 22);
			contentPanel.add(txtrContrasea);
		}
		{
			JLabel txtrEmail = new JLabel();
			txtrEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtrEmail.setForeground(new Color(0, 0, 139));
			txtrEmail.setBackground(SystemColor.menu);
			txtrEmail.setText("Confirmar contrase\u00F1a:");
			txtrEmail.setBounds(168, 238, 172, 22);
			contentPanel.add(txtrEmail);
		}
		{
			JLabel txtrContrase = new JLabel();
			txtrContrase.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtrContrase.setForeground(new Color(0, 0, 139));
			txtrContrase.setBackground(SystemColor.menu);
			txtrContrase.setText("E-Mail:");
			txtrContrase.setBounds(26, 297, 108, 22);
			contentPanel.add(txtrContrase);
		}
		{
			textNombre = new JTextField();
			textNombre.setBounds(26, 133, 92, 22);
			contentPanel.add(textNombre);
			textNombre.setColumns(10);
		}
		{
			textApellido = new JTextField();
			textApellido.setColumns(10);
			textApellido.setBounds(130, 133, 172, 22);
			contentPanel.add(textApellido);
		}
		{
			textDNI = new JTextField();
			textDNI.setColumns(10);
			textDNI.setBounds(76, 170, 172, 22);
			contentPanel.add(textDNI);
		}
		{
			textUser = new JTextField();
			textUser.setColumns(10);
			textUser.setBounds(76, 203, 172, 22);
			contentPanel.add(textUser);
		}
		{
			textPass = new JTextField();
			textPass.setColumns(10);
			textPass.setBounds(26, 262, 134, 22);
			contentPanel.add(textPass);
		}
		{
			textPass_2 = new JTextField();
			textPass_2.setColumns(10);
			textPass_2.setBounds(168, 262, 172, 22);
			contentPanel.add(textPass_2);
		}
		{
			textEmail = new JTextField();
			textEmail.setColumns(10);
			textEmail.setBounds(93, 299, 172, 22);
			contentPanel.add(textEmail);
		}
		{
			JLabel txtrRegistro = new JLabel();
			txtrRegistro.setForeground(new Color(255, 255, 255));
			txtrRegistro.setFont(new Font("LCDMono2", Font.BOLD, 53));
			txtrRegistro.setBackground(SystemColor.menu);
			txtrRegistro.setText("REGISTRO");
			txtrRegistro.setBounds(26, 36, 243, 60);
			contentPanel.add(txtrRegistro);
		}
		{
			JButton btnAyudaRegistro = new JButton();
			btnAyudaRegistro.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			ImageIcon helpPls = new ImageIcon(getClass().getResource("Imagen5-1.png"));
			ImageIcon helpPls1 = new ImageIcon(helpPls.getImage().getScaledInstance(40, 40, 0));
			
			btnAyudaRegistro.setIcon(helpPls1);
			
			btnAyudaRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					JOptionPane.showMessageDialog(null, "En esta ventana se deberán rellenar todo los campos para realizar el registro.");
					
				}
			});
			
			btnAyudaRegistro.setOpaque(false);
			btnAyudaRegistro.setContentAreaFilled(false);
			btnAyudaRegistro.setBorderPainted(false);
			btnAyudaRegistro.setBounds(367, 30, 53, 49);
			contentPanel.add(btnAyudaRegistro);
		}
		{
			JLabel lblAyuda = new JLabel("Ayuda");
			lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblAyuda.setForeground(Color.WHITE);
			lblAyuda.setBounds(369, 0, 56, 40);
			contentPanel.add(lblAyuda);
		}
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon("C:\\Users\\AFM-ASUS-i7\\Desktop\\Informática\\Ingeniería del Software II\\Practicas\\GothamCurlingClubV3F\\Fotos Inso\\Imagenes Inso\\onetry.png"));
			lblNewLabel.setBounds(304, 297, 118, 122);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(135, 206, 250));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Confirmar registro");
				okButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				okButton.setBackground(Color.BLACK);
				okButton.setForeground(Color.WHITE);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						
						int count=0;
							try{
	
								String query="SELECT * FROM Administrador WHERE Usuario='"+textUser.getText()+"'";
								
								PreparedStatement pst=connection.prepareStatement(query);
								ResultSet rs=pst.executeQuery();
									while(rs.next()){
										count++;
									}
									
								query="SELECT * FROM Cliente WHERE Usuario='"+textUser.getText()+"'";
								pst=connection.prepareStatement(query);
								rs=pst.executeQuery();
								
								while(rs.next()){
									count++;
								}
		
									rs.close();
									pst.close();
							}catch(Exception e1){
								JOptionPane.showMessageDialog(null, e1);
							}
									dni= textDNI.getText();
							if(textUser.getText().length()==0||textPass.getText().length()==0||textPass_2.getText().length()==0||textDNI.getText().length()==0){
								JOptionPane.showMessageDialog(null, "No puedes dejar campos vacios");
								textPass.setText("");
								textPass_2.setText("");
							}else if(!textPass.getText().equals( textPass_2.getText())){
								JOptionPane.showMessageDialog(null, "Las contraseñas tienen que ser las mismas");
								textPass.setText("");
								textPass_2.setText("");
							}else if(validarDNI()==false){
								JOptionPane.showMessageDialog(null, "DNI no válido");
							}else if(count!=0){

								JOptionPane.showMessageDialog(null, "El usuario ya existe");
							}else{
								try{
									String query="insert into Cliente (Usuario, Contraseña, Nombre, Apellidos, DNI, Correo) values (?,?,?,?,?,?)";
									PreparedStatement pst=connection.prepareStatement(query);
									pst.setString(1, textUser.getText());
									pst.setString(2, textPass.getText());
									pst.setString(3, textNombre.getText());
									pst.setString(4, textApellido.getText());
									pst.setString(5, textDNI.getText());
									pst.setString(6, textEmail.getText());
									
									pst.execute();
									
									pst.close();
									JOptionPane.showMessageDialog(null, "Registo completado correctamente.");
									dispose();
									
								}catch(Exception e1){
									e1.printStackTrace();
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
	public boolean validarDNI() {

		String letra = "";
		if(dni.length() != 9 || Character.isLetter(this.dni.charAt(8)) == false){
		return false; //Cambiar esto por el mensaje de aviso de error
		}

		letra = (this.dni.substring(8)).toUpperCase();
		if(soloNumeros() == true && letraDNI().equals(letra)){
		return true;
		}
		else{
		return false;
		}
		}


		private boolean soloNumeros(){

		int i = 0;
		int j = 0;
		String numero = "";
		String miDNI = "";
		String [] numeros = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

		for( i = 0; i < this.dni.length() - 1; i++){
		numero = this.dni.substring(i, i+1);
		for( j = 0; j < numeros.length; j++){
		if (numero.equals(numeros[j])) {
		miDNI += numeros[j];
		}
		}
		}

		if (miDNI.length() != 8){
		return false;
		}
		else{
		return true;
		}
		}




		private String letraDNI() {

		int miDNI = Integer.parseInt(this.dni.substring(0,8));
		int resto = 0;
		String miLetra = "";
		String [] asignacionLetra = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};

		resto = miDNI % 23;

		miLetra = asignacionLetra[resto];

		return miLetra;
		}
	

}
