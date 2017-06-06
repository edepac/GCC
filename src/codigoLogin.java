import javax.swing.JOptionPane;
import java.sql.*;

public class codigoLogin {


	public int validar_ingreso(String usuario,String clavedef){

		int resultado=0;
		Connection connection=conexionSQL.dbConector();
		

		try{

			String query="SELECT * FROM Administrador WHERE Usuario='"+usuario+"' AND Contraseña='"+clavedef+"'";
			
			PreparedStatement pst=connection.prepareStatement(query);
			
			ResultSet rs=pst.executeQuery();
			int count=0;
				while(rs.next()){
					count++;
				}
				
				if(count==1){
					JOptionPane.showMessageDialog(null, "Se ha validado correctamente.");
					resultado=1;
					count++;
				}else{
					query="SELECT * FROM Cliente WHERE Usuario='"+usuario+"' AND Contraseña='"+clavedef+"'";
					pst=connection.prepareStatement(query);
					rs=pst.executeQuery();
					while(rs.next()){
						count++;
					}
				}
				
				if(count==1){
					resultado=2;
				}else if(count==0){
					JOptionPane.showMessageDialog(null, "Usuario o contraseña son erroneos");
				}
				
				rs.close();
				pst.close();
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		
		return resultado;
		
	}
	
	public String dameDNI(String usuario){
		Connection connection=conexionSQL.dbConector();
			String devolver=null;
		try{

			String query="SELECT * FROM Cliente WHERE Usuario='"+usuario+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			devolver=rs.getString("DNI");
			rs.close();
			pst.close();
			return devolver;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return devolver;
	}
	
	public String dameIDAdmin(String usuario){
		Connection connection=conexionSQL.dbConector();
			String devolver=null;
		try{

			String query="SELECT * FROM Administrador WHERE Usuario='"+usuario+"'";
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			devolver=rs.getString("IDAdministrador");
			rs.close();
			pst.close();
			return devolver;
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, e);
		}
		return devolver;
	}

}
