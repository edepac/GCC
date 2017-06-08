import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;

import org.omg.CORBA.portable.InputStream;

import com.itextpdf.text.DocumentException;

import java.awt.Cursor;
import java.awt.Color;




public class Admin extends JFrame {

	private JPanel contentPane;

	Connection connection=conexionSQL.dbConector();
	JTable tablaRes;
	JTable tablaCli;
	
	public Admin() {
		setTitle("Ventana Admin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1285, 595);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.textHighlight);
		contentPane.setForeground(SystemColor.textHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRealizarReserva = new JButton("Realizar reserva");
		btnRealizarReserva.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnRealizarReserva.setForeground(Color.WHITE);
		btnRealizarReserva.setBackground(Color.BLACK);
		btnRealizarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRealizarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserva res=new Reserva();
				res.setModal(true);
				res.setVisible(true);
				tablaRes.setModel(DbUtils.resultSetToTableModel(consultaReservas()));
				
			}
		});
		btnRealizarReserva.setBounds(689, 458, 152, 25);
		contentPane.add(btnRealizarReserva);
		
		JButton btnGestionarReserva = new JButton("Gestionar reserva");
		btnGestionarReserva.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnGestionarReserva.setForeground(Color.WHITE);
		btnGestionarReserva.setBackground(Color.BLACK);
		btnGestionarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGestionarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row;
				String idRes,fecha,nPista,hora;
				try{
					row=tablaRes.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "Tienes que seleccionar la reserva a modificar.","Advertencia",JOptionPane.WARNING_MESSAGE);
					}else{
						idRes=tablaRes.getValueAt(row, 0).toString();
						fecha=tablaRes.getValueAt(row,2).toString();
						nPista=tablaRes.getValueAt(row,3).toString();
						hora=tablaRes.getValueAt(row,4).toString();
						
						ModificarReserva res=new ModificarReserva(idRes,fecha,nPista,hora);
						res.setModal(true);
						res.setVisible(true);
						tablaRes.setModel(DbUtils.resultSetToTableModel(consultaReservas()));
						
					}
					
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
			}
		});
		btnGestionarReserva.setBounds(1095, 458, 152, 25);
		contentPane.add(btnGestionarReserva);
		
		JButton btnEliminarReserva = new JButton("Eliminar reserva");
		btnEliminarReserva.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEliminarReserva.setBackground(Color.BLACK);
		btnEliminarReserva.setForeground(Color.WHITE);
		btnEliminarReserva.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarReserva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row;
				int resp;
				String idRes;
				try{
					row=tablaRes.getSelectedRow();
					if(row==-1){
						JOptionPane.showMessageDialog(null, "Debe seleccionar la reserva a eliminar.","Advertencia",JOptionPane.WARNING_MESSAGE);
					}else{
						resp=JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta reserva?","Eliminar reserva",JOptionPane.YES_NO_OPTION);
						if(resp==JOptionPane.YES_OPTION){
							idRes=tablaRes.getValueAt(row,0).toString();
							eliminarReserva(idRes);
							tablaRes.setModel(DbUtils.resultSetToTableModel(consultaReservas()));
						}
					}
					
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnEliminarReserva.setBounds(896, 458, 152, 25);
		contentPane.add(btnEliminarReserva);
		
		JLabel txtrListaDeReservas = new JLabel();
		txtrListaDeReservas.setForeground(SystemColor.text);
		txtrListaDeReservas.setBackground(SystemColor.menu);
		txtrListaDeReservas.setFont(new Font("Monospaced", Font.BOLD, 25));
		txtrListaDeReservas.setText("Lista de reservas");
		txtrListaDeReservas.setBounds(678, 138, 342, 32);
		contentPane.add(txtrListaDeReservas);
		
		JLabel txtrListaDeClientes = new JLabel();
		txtrListaDeClientes.setForeground(SystemColor.text);
		txtrListaDeClientes.setText("Lista de clientes");
		txtrListaDeClientes.setFont(new Font("Monospaced", Font.BOLD, 25));
		txtrListaDeClientes.setBackground(SystemColor.menu);
		txtrListaDeClientes.setBounds(23, 138, 359, 32);
		contentPane.add(txtrListaDeClientes);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		scrollPane.setBounds(678, 182, 589, 241);
		contentPane.add(scrollPane);
		tablaRes = new JTable();
		scrollPane.setViewportView(tablaRes);
		tablaRes.setModel(DbUtils.resultSetToTableModel(consultaReservas()));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		scrollPane_1.setBounds(23, 181, 614, 241);
		contentPane.add(scrollPane_1);
		tablaCli = new JTable();
		scrollPane_1.setViewportView(tablaCli);
		tablaCli.setModel(DbUtils.resultSetToTableModel(consultaClientes()));
		
		JButton btnEliminarCliente = new JButton("Eliminar cliente");
		btnEliminarCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEliminarCliente.setBackground(Color.BLACK);
		btnEliminarCliente.setForeground(Color.WHITE);
		btnEliminarCliente.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				{
					int row;
					int resp;
					String idClie;
					String usuario;
					try{
						row=tablaCli.getSelectedRow();
						if(row==-1){
							JOptionPane.showMessageDialog(null, "Debe seleccionar el cliente a eliminar.","Advertencia",JOptionPane.WARNING_MESSAGE);
						}else{
							resp=JOptionPane.showConfirmDialog(null, "¿Está seguro de eliminar esta Cliente?","Eliminar cliente",JOptionPane.YES_NO_OPTION);
							if(resp==JOptionPane.YES_OPTION){
								idClie=tablaCli.getValueAt(row,0).toString();
								usuario=tablaCli.getValueAt(row, 1).toString();
								eliminarCliente(idClie, usuario);
								tablaRes.setModel(DbUtils.resultSetToTableModel(consultaReservas()));
								tablaCli.setModel(DbUtils.resultSetToTableModel(consultaClientes()));
								
								
							}
						}
						
						
					}catch(Exception e1){
						e1.printStackTrace();
					}
				}
				
				
			}
		});
		btnEliminarCliente.setBounds(197, 458, 172, 25);
		contentPane.add(btnEliminarCliente);
		
		JButton btnAyudaAdministrador = new JButton();
		btnAyudaAdministrador.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		ImageIcon helperino = new ImageIcon(getClass().getResource("Imagen5-1.png"));
		ImageIcon helperino1 = new ImageIcon(helperino.getImage().getScaledInstance(40, 40, 0));
		
		btnAyudaAdministrador.setIcon(helperino1);
		
		btnAyudaAdministrador.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null, "En esta ventanan el administrador tendrá acceso"
						+ " a todas las reservas y todos \nlos clientes, con la posibilidad de realizar"
						+ " reservas seleccionando \nun usuario , eliminar reservas indiviualmente, modificar"
						+ " estas \ny eliminar clientes, de forma que si un cliente es eliminado \ntambién lo"
						+ " serán sus reservas.");
				
			}
		});
		
		btnAyudaAdministrador.setOpaque(false);
		btnAyudaAdministrador.setContentAreaFilled(false);
		btnAyudaAdministrador.setBorderPainted(false);
		btnAyudaAdministrador.setBounds(1210, 34, 65, 71);
		//btnAyudaAdministrador.setBounds(509, 18, 162, 25);
		contentPane.add(btnAyudaAdministrador);
		
		JLabel lblAyuda = new JLabel("Ayuda");
		lblAyuda.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAyuda.setForeground(Color.WHITE);
		lblAyuda.setBounds(1216, 14, 58, 38);
		contentPane.add(lblAyuda);
		
		JButton btnbackup = new JButton("");
		btnbackup.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		ImageIcon helperoncio = new ImageIcon(getClass().getResource("Backup.png"));
		ImageIcon helperoncio1 = new ImageIcon(helperoncio.getImage().getScaledInstance(40, 40, 0));
		
		btnbackup.setIcon(helperoncio1);
		
		btnbackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser fichero_guardar = new JFileChooser();
				// Abrimos la ventana y guardamos la opcion seleccionada por el usuario
				fichero_guardar.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int seleccion = fichero_guardar.showSaveDialog(null);
				// Si el usuario pincha en aceptar
				if(seleccion == JFileChooser.APPROVE_OPTION) {
					// Seleccionamos el fichero
					File fichero = fichero_guardar.getSelectedFile();
					fichero.toString();
					
					
					String origen = "C:\\Users\\AFM-ASUS-i7\\Desktop\\Informática\\Ingeniería del Software II\\Practicas\\GothamCurlingClubV3F\\GCC.sqlite";
			        String destino = fichero.toString()+"\\Gcc.sqlite";
			        
			        CopiarArchivo.getInstance().copiar(origen, destino);
			        
			        JOptionPane.showMessageDialog(null, "Copia de seguridad realizada correctamente."+ destino);
					
				}
			}
		});
		btnbackup.setActionCommand("Backup");
		btnbackup.setOpaque(false);
		btnbackup.setContentAreaFilled(false);
		btnbackup.setBorderPainted(false);
		btnbackup.setBounds(1143, 34, 57, 66);
		contentPane.add(btnbackup);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\AFM-ASUS-i7\\Desktop\\Informática\\Ingeniería del Software II\\Practicas\\GothamCurlingClubV3F\\Fotos Inso\\cooltext228680583960543.png"));
		lblNewLabel.setBounds(23, 26, 484, 99);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\AFM-ASUS-i7\\Desktop\\Informática\\Ingeniería del Software II\\Practicas\\GothamCurlingClubV3F\\Fotos Inso\\Administrador.png"));
		lblNewLabel_1.setBounds(495, 14, 118, 122);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblBackup = new JLabel("Back Up --->");
		lblBackup.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblBackup.setBounds(1030, 50, 118, 35);
		contentPane.add(lblBackup);
		
		JButton btnPrevisionIngresos = new JButton("Prevision Ingresos");
		btnPrevisionIngresos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//GenerarPdf pdf = new GenerarPdf();
				JFrame frame = new JFrame();
				JFrame frame1 = new JFrame();
				String panelcito = JOptionPane.showInputDialog(frame, "Mes:");
				String panelcito2 = JOptionPane.showInputDialog(frame1, "Año:");
				
				int mes = Integer.parseInt(panelcito);
				int año = Integer.parseInt(panelcito2);
				
				int pistas=0, entrenadores=0, cascos=0, sliders=0, escobas=0;
				
				JTable tablaReservas = new JTable();
				tablaReservas.setModel(DbUtils.resultSetToTableModel(consultaReservas()));
				
				String fecha1=null;
				for(int j=1; j<32;j++){
				for (int i = 0; i < tablaReservas.getRowCount(); i++) {
					fecha1=j+"/"+mes+"/"+año;
					String fechaDB= (String) tablaReservas.getValueAt(i, 2);
					if(fecha1.equals(fechaDB)){
						pistas=pistas+ 1;
						entrenadores = entrenadores + (int) tablaReservas.getValueAt(i, 7);
						cascos = cascos + (int) tablaReservas.getValueAt(i, 8);
						sliders = sliders + (int) tablaReservas.getValueAt(i, 9);
						escobas = escobas + (int) tablaReservas.getValueAt(i, 10);
					}
				}
				}
				
				
				try {
					GenerarPdf.hazPDF(mes, año, pistas, entrenadores, cascos, sliders, escobas);
				} catch (DocumentException | IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnPrevisionIngresos.setBounds(782, 59, 89, 23);
		contentPane.add(btnPrevisionIngresos);
		setLocationRelativeTo(null);
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
	
	public ResultSet consultaClientes(){

		try{
			String query="SELECT * FROM Cliente";
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
	public void eliminarCliente(String idClie,String usuario){

		try{
			String query="delete from Cliente where IDCliente='"+idClie+"'";
			
			String query1="delete from Reservas where Usuario='"+usuario+"'";
			
			
			
			
			
			PreparedStatement pst=connection.prepareStatement(query);
			PreparedStatement pst1=connection.prepareStatement(query1);
			pst1.execute();
			pst.execute();
			pst1.close();
			pst.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
}

