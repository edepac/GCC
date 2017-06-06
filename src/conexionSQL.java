
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

import javax.swing.*;

public class conexionSQL {
    Connection connection=null;
  
    
    public static Connection dbConector(){
        
        try{
            Class.forName("org.sqlite.JDBC");
           
            Connection connection=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\AFM-ASUS-i7\\Desktop\\Informática\\Ingeniería del Software II\\Practicas\\GothamCurlingClubV3F\\GCC.sqlite");
           
            
            return connection;
            
            
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }    

}