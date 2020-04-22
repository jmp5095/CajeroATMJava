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
import java.util.ArrayList;
import proyectocajeroatm.MySQL;

/**
 *
 * @author juan
 */
public class Transaccion {

    public static ArrayList<Transaccion> transacciones = new ArrayList<>();
    private int id;
    private double monto;
    private double fecha;
    private boolean aprovacion;
    private boolean realizada;
    private String cuentaDepositante;
    private String cuentaDeposito;

    public Transaccion() {
    }

    public Transaccion(double monto, boolean aprovacion, boolean realizada, String cuentaDepositante, String cuentaDeposito) {
        this.monto = monto;
        this.aprovacion = aprovacion;
        this.realizada = realizada;
        this.cuentaDepositante = cuentaDepositante;
        this.cuentaDeposito = cuentaDeposito;
    }

    public boolean registrar() {
        try {
            //variables objeto db
            Connection conexion;
            Statement comando;
            //creo objeto conexion
            MySQL bd = new MySQL();
            bd.MySQLConnect();

            comando = bd.getComando();
            conexion = bd.getConexion();

            String Query = "insert into Transaccion" + "(tra_monto,tra_fecha,tra_aprobacion,tra_realizada,tbl_cue_depositante,tbl_cue_deposito)"
                    + "values(" + this.monto + "," + "now()" + "," + this.isAprovacion() + "," + this.isRealizada() + "," + "'" + this.cuentaDepositante + "'" + "," + "'" + this.cuentaDeposito + "'" + ")";
            
            comando = conexion.createStatement();

            comando.execute(Query);
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
            

        }
    }

    public double sumarTransacciones(String numeroDeCuenta) {
        double total = 0;
        try {
            Transaccion t = new Transaccion();
            //variables objeto db
            Connection conexion;
            Statement comando;
            ResultSet consulta;
            //creo objeto conexion
            MySQL bd = new MySQL();
            bd.MySQLConnect();

            conexion = bd.getConexion();
            comando = bd.getComando();
            consulta = bd.getConsulta();

            String Query = "select * from Transaccion where tra_aprobacion=true "
                    + " and tra_realizada=false"
                    + " and tbl_cue_deposito=" + numeroDeCuenta;

            comando = conexion.createStatement();
            consulta = comando.executeQuery(Query);

            while (consulta.next()) {
                Transaccion mit = new Transaccion();
                mit.id = Integer.parseInt(consulta.getString("tra_id_transaccion"));
                mit.monto = Double.parseDouble(consulta.getString("tra_monto"));
                transacciones.add(mit);
                total += mit.monto;
            }
            return total;
        } catch (SQLException e) {
            System.out.println(e.toString() + "<-este fue");
            return total;
        }
    }

    public void actualizarTransacciones() {
        try {
            //variables objeto db
            Connection conexion;
            Statement comando;
            //creo objeto conexion
            MySQL bd = new MySQL();
            bd.MySQLConnect();

            comando = bd.getComando();
            conexion = bd.getConexion();
            for (Transaccion t : transacciones) {
                String Query = "update Transaccion set tra_realizada=1 where tra_id_transaccion=" + t.id;
                System.out.println("se ejecuto");
                comando = conexion.createStatement();

                comando.execute(Query);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());

        }

    }

    public static ArrayList<Transaccion> getTransacciones() {
        return transacciones;
    }

    public static void setTransacciones(ArrayList<Transaccion> transacciones) {
        Transaccion.transacciones = transacciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getFecha() {
        return fecha;
    }

    public void setFecha(double fecha) {
        this.fecha = fecha;
    }

    public boolean isAprovacion() {
        return aprovacion;
    }

    public void setAprovacion(boolean aprovacion) {
        this.aprovacion = aprovacion;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public void setRealizada(boolean realizada) {
        this.realizada = realizada;
    }

    public String getCuentaDepositante() {
        return cuentaDepositante;
    }

    public void setCuentaDepositante(String cuentaDepositante) {
        this.cuentaDepositante = cuentaDepositante;
    }

    public String getCuentaDeposito() {
        return cuentaDeposito;
    }

    public void setCuentaDeposito(String cuentaDeposito) {
        this.cuentaDeposito = cuentaDeposito;
    }

    public String nombreClase() {
        return this.getClass().getSimpleName();
    }
}
