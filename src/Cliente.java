import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Cursor;

public class Cliente extends JFrame {

	private JPanel contentPane;
	Connection connection=conexionSQL.dbConector();
	JTable tablaReservas;
	String Usuario;
	
	public Cliente(String USUARIO) {
		Usuario=USUARIO;
		setTitle("Ventana cliente");
		setBounds(100, 100, 973, 576);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(panel, BorderLayout.CENTER);
		
		JButton btnRealizarReserva = new JButton("Realizar reserva");
		btnRealizarReserva.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRealizarReserva.setBounds(56, 459, 162, 25);
		btnRealizarReserva.setBackground(Color.BLACK);
		btnRealizarReserva.setForeground(Color.WHITE);
		btnRealizarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRealizarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reserva res=new Reserva(Usuario);
				res.setModal(true);
				res.setVisible(true);
				tablaReservas.setModel(DbUtils.resultSetToTableModel(verMisReservas()));
			}
		});
		
		JButton btnGestionarReserva = new JButton("Gestionar reserva");
		btnGestionarReserva.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGestionarReserva.setBounds(733, 459, 162, 25);
		btnGestionarReserva.setBackground(Color.BLACK);
		btnGestionarReserva.setForeground(Color.WHITE);
		btnGestionarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGestionarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row;
		
				String idRes,fecha,nPista,hora;
				try{
					row=tablaReservas.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "Seleccione la reserva a modificar.","Advertencia",JOptionPane.WARNING_MESSAGE);
					}else{
						idRes=tablaReservas.getValueAt(row, 0).toString();
						fecha=tablaReservas.getValueAt(row,2).toString();
						nPista=tablaReservas.getValueAt(row,3).toString();
						hora=tablaReservas.getValueAt(row,4).toString();
						
						ModificarReserva res=new ModificarReserva(idRes,fecha,nPista,hora);
						res.setModal(true);
						res.setVisible(true);
						tablaReservas.setModel(DbUtils.resultSetToTableModel(verMisReservas()));
					}
					
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnEliminarReserva = new JButton("Eliminar reserva");
		btnEliminarReserva.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEliminarReserva.setBounds(382, 459, 172, 25);
		btnEliminarReserva.setBackground(Color.BLACK);
		btnEliminarReserva.setForeground(Color.WHITE);
		btnEliminarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row;
				int resp;
				String idRes;
				try{
					row=tablaReservas.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "Seleccione la reserva a eliminar.","Advertencia",JOptionPane.WARNING_MESSAGE);
					}else{
						resp=JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar la reserva?","Eliminar",JOptionPane.YES_NO_OPTION);
						if(resp==JOptionPane.YES_OPTION){
							idRes=tablaReservas.getValueAt(row,0).toString();
							eliminarReserva(idRes);
							tablaReservas.setModel(DbUtils.resultSetToTableModel(verMisReservas()));
						}
					}
					
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
		
		JLabel textArea = new JLabel();
		textArea.setBounds(12, 160, 323, 32);
		textArea.setForeground(Color.WHITE);
		textArea.setText("Lista de reservas");
		textArea.setFont(new Font("Arial", Font.BOLD, 25));
		textArea.setBackground(SystemColor.menu);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 205, 919, 218);
		
		tablaReservas = new JTable();
		scrollPane.setViewportView(tablaReservas);
		tablaReservas.setModel(DbUtils.resultSetToTableModel(verMisReservas()));
		
		JButton btnAyudaCliente = new JButton();
		btnAyudaCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAyudaCliente.setBounds(871, 31, 48, 54);
		
		ImageIcon helperino = new ImageIcon(getClass().getResource("Imagen5-1.png"));
		ImageIcon helperino1 = new ImageIcon(helperino.getImage().getScaledInstance(40, 40, 0));
		
		btnAyudaCliente.setIcon(helperino1);
		
		btnAyudaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "En esta ventanan el cliente tendrá acceso"
						+ " a todas las reservas realizadas por él, \npodrá visualizarlas, realizar una reserva,"
						+ " \neliminar sus reservas y gestionar las mismas,"
						+ "\ncambiando horario y pista sorbre el mismo día."
						+ "Para realizar la reserva, se deberá escoger el día que se desea realizar, "
						+ "el horario(ya sea Mañana o Tarde) y por último el numero de pista que deseas reservar.");
			}
		});
		btnAyudaCliente.setOpaque(false);
		btnAyudaCliente.setContentAreaFilled(false);
		btnAyudaCliente.setBorderPainted(false);
		panel.setLayout(null);
		
		JLabel lblAyuda = new JLabel("Ayuda");
		lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAyuda.setForeground(Color.WHITE);
		lblAyuda.setBounds(871, 11, 60, 25);
		panel.add(lblAyuda);
		panel.add(textArea);
		panel.add(btnAyudaCliente);
		panel.add(scrollPane);
		panel.add(btnRealizarReserva);
		panel.add(btnEliminarReserva);
		panel.add(btnGestionarReserva);
		
		JButton botonCliente = new JButton();
		
		ImageIcon helped = new ImageIcon(getClass().getResource("cli.png"));
		ImageIcon helped1 = new ImageIcon(helped.getImage().getScaledInstance(440, 115, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\AFM-ASUS-i7\\Desktop\\Informática\\Ingeniería del Software II\\Practicas\\GothamCurlingClubV3F\\Fotos Inso\\cooltext228680600699825.png"));
		lblNewLabel.setBounds(12, 24, 269, 106);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\AFM-ASUS-i7\\Desktop\\Informática\\Ingeniería del Software II\\Practicas\\GothamCurlingClubV3F\\Fotos Inso\\Imagenes Inso\\Imagen344.png"));
		lblNewLabel_1.setBounds(253, 13, 221, 134);
		panel.add(lblNewLabel_1);
		
		
		
		setLocationRelativeTo(null);
	}
	
	public ResultSet verMisReservas(){

		try{
			String query="SELECT * FROM Reservas where Usuario='"+Usuario+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			
			return rs;
		}catch(Exception e){
			e.printStackTrace();

			
			return null;
		}
	}
	
	public void eliminarReserva(String idRes){

		try{
			String query="delete from Reservas where idReserva='"+idRes+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.execute();
			
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
