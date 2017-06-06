import java.sql.*;

import javax.swing.ImageIcon;

public class main {
	
	public static void main(String[] args) {
		Connection conexion= conexionSQL.dbConector();
		
		Logging log= new Logging();
		log.setVisible(true);

		
		//String origen = "C:\\Users\\PFV-ASUS-13\\Desktop\\GCC.sqlite";
        //String destino = "C:\\Users\\PFV-ASUS-13\\Desktop\\Prueba\\GCC.sqlite";
        //System.out.println("Proceso de copiar archivo: " + CopiarArchivo.getInstance().copiar(origen, destino));
	}

}
