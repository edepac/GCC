
import java.sql.*;

import javax.swing.*;

public class conexionSQL {
	private static conexionSQL instance = null;
    private Connection conexion=null;
  
    private conexionSQL(){}
    
    public static conexionSQL getInstance(){
    	if(instance==null){
    		instance=new conexionSQL();
    	}
    	return instance;
    }
    
    public Connection getConnection(){
    	if(conexion==null){
    		conexion=dbConector();
    	}
    	return conexion;
    }
    
    static Connection dbConector(){
    	
    	Connection connection = null;
        
        try{
        	
        	
           
            connection = DriverManager.getConnection("jdbc:sqlserver://gcc.database.windows.net;database=GCC;user=gccadmin;password=pcZujne7;encrypt=true;trustServerCertificate=true;integratedSecurity=true;loginTimeout=30;");
            //jdbc:sqlserver://%s.database.windows.net:1433;database=%s;user=%s;password=%s;encrypt=true;hostNameInCertificate=*.database.windows.net;loginTimeout=30;
            //connection = DriverManager.getConnection("jdbc:sqlserver://51.141.8.11;database=GCC;user=gccadmin;password=pcZujne7;encrypt=true;trustServerCertificate=true;integratedSecurity=true;hostNameInCerftificate=ukwest1-a.control.database.windows.net;loginTimeout=30;");
            
            //Nuestro certificado es ukwest1-a.control.database.windows.net
           
            if(connection !=null){
            	System.out.println("Conexión realizada con exito");
            }   
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
        
        return connection;
    }    

}

//SALVAVIDAS
//SALVAVIDAS