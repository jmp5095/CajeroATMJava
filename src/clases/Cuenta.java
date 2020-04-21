/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import Guis.CajeroGui;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import proyectocajeroatm.MySQL;

/**
 *
 * @author juan
 */
public class Cuenta {
    private String numeroDeCuenta;
    private String tipo ;
    private double monto;
    private Cliente c;
    
    public Cuenta(){}
    
    public Cuenta(String numeroDeCuenta, String tipo, double monto, Cliente c) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.tipo = tipo;
        this.monto = monto;
        this.c = c;
    }
    public String retirarMonto(double cantidad){
        this.consultarCuenta();
        if (CajeroGui.efectivo>cantidad) {
            if (this.monto>=cantidad) {
                try{
                    CajeroGui.setEfectivo(CajeroGui.getEfectivo()-cantidad); 
                    double aux=this.getMonto()-cantidad;
                //variables objeto db
                    Connection conexion;
                    Statement comando;
                //creo objeto conexion
                    MySQL bd=new MySQL();
                    bd.MySQLConnect();

                    comando=bd.getComando();
                    conexion=bd.getConexion();

                    String Query="update "+this.nombreClase()+" set cue_monto="+aux+" where cue_numero_de_cuenta ="+"'"+this.getNumeroDeCuenta()+"'";

                    comando= conexion.createStatement();
                    comando.execute(Query);
                    return "Retiro exitoso.";
                }catch(SQLException e){
                    return "ocurrio un error inesperado";
                }
            }else{
                return "Lo sentimos no tiene \nsaldo suficiente.";
            }
        }else{
             return "Lo sentimos, \nel cajero no cuenta \ncon el monto solicitado";
        }
        
        
    }
    public String restarMonto(double cantidad){
        this.consultarCuenta();
            if (this.monto>=cantidad) {
                try{
                    CajeroGui.setEfectivo(CajeroGui.getEfectivo()-cantidad); 
                    double aux=this.getMonto()-cantidad;
                //variables objeto db
                    Connection conexion;
                    Statement comando;
                //creo objeto conexion
                    MySQL bd=new MySQL();
                    bd.MySQLConnect();

                    comando=bd.getComando();
                    conexion=bd.getConexion();

                    String Query="update "+this.nombreClase()+" set cue_monto="+aux+" where cue_numero_de_cuenta ="+"'"+this.getNumeroDeCuenta()+"'";

                    comando= conexion.createStatement();
                    comando.execute(Query);
                    return "Retiro exitoso.";
                }catch(SQLException e){
                    return "ocurrio un error inesperado";
                }
            }else{
                return "Lo sentimos no tiene \nsaldo suficiente.";
            }
        
        
    }
    public String abonarMonto(double cantidad){
        this.consultarCuenta();
                try{
                    
                    double aux=this.getMonto()+cantidad;
                //variables objeto db
                    Connection conexion;
                    Statement comando;
                //creo objeto conexion
                    MySQL bd=new MySQL();
                    bd.MySQLConnect();

                    comando=bd.getComando();
                    conexion=bd.getConexion();

                    String Query="update "+this.nombreClase()+" set cue_monto="+aux+" where cue_numero_de_cuenta ="+"'"+this.getNumeroDeCuenta()+"'";

                    comando= conexion.createStatement();
                    comando.execute(Query);
                    return "Abono exitoso.";
                }catch(SQLException e){
                    System.out.println(e.toString());
                    return "ocurrio un error inesperado";
                }
        
    }
    public void consultarCuenta(){
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
            this.c.consultarCliente();
            
            String Query="select * from "+this.nombreClase()+" where tbl_cli_cedula="+"'"+this.c.getCedula()+"'";
            
            comando= conexion.createStatement();
            consulta= comando.executeQuery(Query);
            
            while(consulta.next()){
                        this.numeroDeCuenta=consulta.getString("cue_numero_de_cuenta");
                        this.tipo=consulta.getString("cue_tipo_de_cuenta");
                        this.c.setCedula(consulta.getString("tbl_cli_cedula"));
                        this.monto=Double.parseDouble(consulta.getString("cue_monto"));
            }
            
            this.setC(c);
        }catch(SQLException e){
            System.out.println(e.toString()+"<-este fue");
            
        }
        
    }
    public void consultarCuentaId(){
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
            
            String Query="select * from "+this.nombreClase()+" where cue_numero_de_cuenta="+this.numeroDeCuenta;
            
            comando= conexion.createStatement();
            consulta= comando.executeQuery(Query);
            
            while(consulta.next()){
                        this.numeroDeCuenta=consulta.getString("cue_numero_de_cuenta");
                        this.tipo=consulta.getString("cue_tipo_de_cuenta");
                        this.monto=Double.parseDouble(consulta.getString("cue_monto"));
            }
            
        }catch(SQLException e){
            System.out.println(e.toString()+"<-este fue");
            
        }
        
    }
    public void actualizarCuenta(){
        Transaccion t =new Transaccion();
        double sumT=t.sumarTransacciones(this.getNumeroDeCuenta());
            System.out.println(sumT);
            this.abonarMonto(sumT);
            t.actualizarTransacciones();
            Transaccion.transacciones.clear();
    }
    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
    }
    
    public String nombreClase(){
        return this.getClass().getSimpleName();
    }
}
