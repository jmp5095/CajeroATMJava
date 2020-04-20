/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocajeroatm;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


/**
 *
 * @author juan
 */
public class MySQL {
    
    private Connection conexion;
    private Statement comando;
    private ResultSet consulta;
    
    public MySQL(){}

    
    
    public Connection MySQLConnect() {
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            String servidor="jdbc:mysql://localhost:3306/cajero_atm_db";
            
            String usuario="root";
            String pass="juan";
            //inicia la conexion
            conexion=DriverManager.getConnection(servidor,usuario,pass);
            return conexion;  
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,e,"Error 1:"+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            return conexion=null;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e,"Error 2:"+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            return conexion=null;
        }catch(HeadlessException e){
            JOptionPane.showMessageDialog(null,e,"Error 3:"+e.getMessage(),JOptionPane.ERROR_MESSAGE);
            return conexion=null;
        }
    }
    

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public Statement getComando() {
        return comando;
    }

    public void setComando(Statement comando) {
        this.comando = comando;
    }

    public ResultSet getConsulta() {
        return consulta;
    }

    public void setConsulta(ResultSet consulta) {
        this.consulta = consulta;
    }
    
    
    
}
