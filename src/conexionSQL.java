
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
        	String hostName="gcc";
        	String dbName ="GCC";
        	String user = "gccadmin";
        	String password = "pcZujne7";
        	String connectionUrl = String.format("jdbc:sqlserver://%s.database.windows.net;database=%s;user=%s;password=%s;", hostName, dbName, user, password);
        	
           
            connection = DriverManager.getConnection(connectionUrl);
           
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