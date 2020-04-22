/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import proyectocajeroatm.MySQL;

/**
 *
 * @author juan
 */
public class Cliente {
    private String cedula;
    private String nombre;
    private String apellido;
    private String direccion;
    private String contraseña;
    private String ciudad;

    public Cliente(){}
    
    public Cliente(String cedula,String contraseña){
        this.cedula=cedula;
        this.contraseña=contraseña;
    }
    
    public Cliente(String cedula, String nombre, String contraseña) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.contraseña = contraseña;
    }
    
    public Cliente(String cedula, String nombre, String apellido, String direccion, String contraseña, String ciudad) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.contraseña = contraseña;
        this.ciudad = ciudad;
    }
    
     public boolean validarCliente(){
         
         try{
             
            boolean salida=false;
            //variables objeto db
            Connection conexion;
            Statement comando;
            ResultSet consulta;
            //variables objeto cliente
            String ce="";
            String no="";
            String co="";
            
            //creo objeto conexion
            MySQL bd=new MySQL();
            bd.MySQLConnect();
            
            conexion=bd.getConexion();
            comando=bd.getComando();
            consulta=bd.getConsulta();
            
            String Query="select * from "+this.nombreClase()+" where cli_cedula="+"'"+this.cedula+"'" 
                        +"and cli_contraseña="+"'"+this.contraseña+"'";
            
            comando= conexion.createStatement();
            consulta= comando.executeQuery(Query);
            
            while(consulta.next()){
                        ce=consulta.getString("cli_cedula");
                        no=consulta.getString("cli_nombre");
            }
            
             if (!ce.equals("")) {
                 salida=true;
                 this.setNombre(no);
             }else{
                 salida=false;
             }
            return salida;
            
        }catch(SQLException e){
            System.out.println(e.toString()+"<-este fue");
            return false;
        }  
    }

     public void consultarCliente(){
         
         try{
            //variables objeto db
            Connection conexion;
            Statement comando;
            ResultSet consulta;
            
            //creo objeto conexion
            MySQL bd=new MySQL();
            bd.MySQLConnect();
            
            conexion=bd.getConexion();
            comando=bd.getComando();
            consulta=bd.getConsulta();
            
            String Query="select * from "+this.nombreClase()+","+"Ciudad"+" where cli_cedula="+"'"+this.cedula+"'"
                   + " and tbl_ciu_id_ciudad =ciu_id_ciudad";
            
            
            comando= conexion.createStatement();
            consulta= comando.executeQuery(Query);
            
            while(consulta.next()){
                        this.cedula=consulta.getString("cli_cedula");
                        this.nombre=consulta.getString("cli_nombre");
                        this.apellido=consulta.getString("cli_apellido");
                        this.direccion=consulta.getString("cli_direccion");
                        this.contraseña=consulta.getString("cli_contraseña");
                        this.ciudad=consulta.getString("ciu_nombre");
            }
            
            
        }catch(SQLException e){
            System.out.println(e.toString()+"<-este fue");
        }  
    }
    
    public String nombreClase(){
        return this.getClass().getSimpleName();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    
}
