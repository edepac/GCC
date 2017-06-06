import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Button;
import javax.swing.JLabel;
import java.awt.Cursor;

public class Logging extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	JLabel txtrUsuario;
	JLabel txtrContrasea;
	codigoLogin log=new codigoLogin();
	private JPasswordField textField_1;
	
	private JButton btnAyudaLogin;
	private JButton btnNewButton;
	private JButton botonLogo;

	
	public Logging() {
		
	//private Imagen imagen =new ImageIcon(getClass().getResource("Logo GCC.png")).getImage();
		
		ImageIcon imagen = new ImageIcon("Logo GCC.png");
		
		setTitle("Login");;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnReg = new JButton("\u00BFDesea registrarse en la aplicaci\u00F3n?");
		btnReg.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnReg.setForeground(new Color(255, 255, 255));
		btnReg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnReg.setBackground(new Color(0, 0, 0));
		
		btnReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registro reg=new Registro();
				reg.setVisible(true);
			}
		});
		btnReg.setBounds(12, 215, 408, 25);
		contentPane.add(btnReg);
		
		JButton btnRegistrarse = new JButton("Login");
		btnRegistrarse.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRegistrarse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegistrarse.setForeground(new Color(255, 255, 255));
		btnRegistrarse.setBackground(new Color(0, 0, 0));
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				
				int tipoUsuario = log.validar_ingreso(textField.getText(), textField_1.getText());
				if(tipoUsuario==1){
					Admin vista=new Admin();
					vista.setVisible(true);
					dispose();
				}else if(tipoUsuario==2){
					Cliente vista=new Cliente(textField.getText());
					vista.setVisible(true);
					dispose();
				}
				textField.setText("");
				textField_1.setText("");
				
		}}
		);
		btnRegistrarse.setBounds(158, 163, 97, 25);
		contentPane.add(btnRegistrarse);
		
		textField = new JTextField();
		textField.setBounds(114, 90, 158, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		txtrUsuario = new JLabel();
		txtrUsuario.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtrUsuario.setForeground(new Color(0, 0, 139));
		txtrUsuario.setBackground(SystemColor.textHighlight);
		txtrUsuario.setText("Usuario:");
		txtrUsuario.setBounds(22, 90, 68, 22);
		contentPane.add(txtrUsuario);
		
		txtrContrasea = new JLabel();
		txtrContrasea.setFont(new Font("Tahoma", Font.BOLD, 15));
		txtrContrasea.setForeground(new Color(0, 0, 139));
		txtrContrasea.setBackground(SystemColor.textHighlight);
		txtrContrasea.setText("Contrase\u00F1a:");
		txtrContrasea.setBounds(22, 125, 91, 22);
		contentPane.add(txtrContrasea);
		
		JLabel txtrGothamCurlingClub = new JLabel();
		txtrGothamCurlingClub.setForeground(SystemColor.text);
		txtrGothamCurlingClub.setFont(new Font("Lucida Calligraphy", Font.BOLD, 19));
		txtrGothamCurlingClub.setBackground(new Color(255, 255, 255));
		txtrGothamCurlingClub.setText("Gotham Curling Club");
		txtrGothamCurlingClub.setBounds(33, 13, 239, 52);
		contentPane.add(txtrGothamCurlingClub);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(124, 125, 148, 25);
		contentPane.add(textField_1);
		
		btnAyudaLogin = new JButton();
		btnAyudaLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		
		ImageIcon help = new ImageIcon(getClass().getResource("Imagen5-1.png"));
		ImageIcon help1 = new ImageIcon(help.getImage().getScaledInstance(40, 40, 0));
		
		btnAyudaLogin.setIcon(help1);
		
		
		
		btnAyudaLogin.addActionListener(new ActionListener() {
			
			
			
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "En esta ventana el usuario o el administrador deberán "
						+ "introducir sus datos y pulsar el botón Login.\nSi el usuario no está registrado "
						+ "tendrá que pulsar el botón de la parte inferior.");
			}
		});
		btnAyudaLogin.setOpaque(false);
		btnAyudaLogin.setContentAreaFilled(false);
		btnAyudaLogin.setBorderPainted(false);
		btnAyudaLogin.setBounds(332, 151, 68, 62);
		contentPane.add(btnAyudaLogin);
		
		JLabel lblAyuda = new JLabel("Ayuda");
		lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblAyuda.setForeground(Color.WHITE);
		lblAyuda.setBounds(345, 129, 57, 38);
		contentPane.add(lblAyuda);
		
		botonLogo = new JButton();
		botonLogo.setBackground(SystemColor.textHighlight);
		
		ImageIcon helperino = new ImageIcon(getClass().getResource("Logo GCC.png"));
		ImageIcon helperino1 = new ImageIcon(helperino.getImage().getScaledInstance(95, 95, 0));
		
		botonLogo.setIcon(helperino1);
		botonLogo.setContentAreaFilled(false);
		botonLogo.setBorderPainted(false);
		botonLogo.setBounds(292, 35, 136, 125);
		
		botonLogo.setBounds(313, 13, 107, 99);
		contentPane.add(botonLogo);
		
		
		
		
		setLocationRelativeTo(null);
	}
	}

