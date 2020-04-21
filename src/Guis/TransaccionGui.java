/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guis;

import clases.Cliente;
import clases.Cuenta;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author juan
 */
public class TransaccionGui extends JFrame implements ActionListener{
    Container contenedor=getContentPane();
    JTextArea salida;
    JLabel numeroDeCuental;
    JLabel cantidadl;
    JButton realizar;
    JButton cancelar;
    
    JPanel botones;
    GridLayout esquemab;
    JPanel entrada;
    GridLayout esquemae;
    
    JTextField numeroDeCuenta;
    JTextField cantidad;
    public TransaccionGui(){
        contenedor.setLayout(new FlowLayout());
        salida= new JTextArea();
        salida.setEditable(false);
        
        realizar=new JButton("Realizar transaccion");
        cancelar=new JButton("Cancelar");
        
        numeroDeCuental=new JLabel("Número de cuenta");
        numeroDeCuenta= new JTextField(" ");
        
        cantidadl=new JLabel("Monto a abonar");
        cantidad=new JTextField(" ");
        
        botones=new JPanel();
        esquemab=new GridLayout(2,1,1,2);
        botones.setLayout(esquemab);
        
        botones.add(realizar);
        botones.add(cancelar);
        
        entrada=new JPanel();
        esquemae=new GridLayout(4,1,1,2);
        entrada.setLayout(esquemae);
        
        entrada.add(numeroDeCuental);
        entrada.add(numeroDeCuenta);
        entrada.add(cantidadl);
        entrada.add(cantidad);
        
        //posicion y tamaño de los elementos
        salida.setBounds(new Rectangle(50,25,230,60));
        botones.setBounds(new Rectangle(50,110,230,80));
        entrada.setBounds(new Rectangle(50,210,230,95));
        
        //estilo 
        Font auxFont=salida.getFont();
        salida.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),16));
        contenedor.add(salida);
        contenedor.add(botones);
        contenedor.add(entrada);
        contenedor.setLayout(null);
        
        //colores
        contenedor.setBackground(Color.WHITE);
        salida.setBackground(new java.awt.Color(54,22,181));
        salida.setForeground(new java.awt.Color(255,255,255));
        realizar.setBackground(Color.WHITE);
        realizar.setForeground(new java.awt.Color(54,22,181));
        cancelar.setBackground(new java.awt.Color(245,35,20));
        cancelar.setForeground(Color.WHITE);
        entrada.setBackground(Color.white);
        
        //add listeners
        this.realizar.addActionListener(this);
        this.cancelar.addActionListener(this);
        
        this.setSize(333,370);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(realizar)) {
            accionTransaccion();
        }
    }
    
    public void accionTransaccion(){
        String numCuenta=numeroDeCuenta.getText();
        String can=cantidad.getText();
        if (!numCuenta.equals(" ")) {
            if (!can.equals(" ")) {
                if (LoginGui.cajero.esNumerico(numCuenta)) {
                    
                    if (LoginGui.cajero.esNumerico(can)) {
                       
                       
                        Cuenta c=new Cuenta();
                        Cliente cli=new Cliente();
                        c.setNumeroDeCuenta(numCuenta);
                        c.setC(cli);
                        c.consultarCuentaId();
                        
                        if (c.getTipo()!=null) {
                             Double mon=Double.parseDouble(can);
                             String r= CajeroGui.cuenta.restarMonto(mon);
                             if (r.equals("Retiro exitoso.")) {
                               r = c.abonarMonto(mon);
                               r=r+"\nMonto transferido:"
                                 + "\n"+mon+"\n"
                                 + "Cuenta depositada:\n"
                                 + c.getNumeroDeCuenta()+"\n"
                                 + "Transferencia desde:\n"
                                 + CajeroGui.cuenta.getNumeroDeCuenta();
                               LoginGui.cajero.salida.setText(r);
                               
                               this.setVisible(false);
                            }else{
                               LoginGui.cajero.salida.setText(r);
                               this.setVisible(false);
                            }
                        }else{
                            salida.setText("El numero de cuenta ingresado"
                                    + "\nno corresponde a ninguna"
                                    + "\ncuenta registrada");
                        }
                       
                        
                    }else{
                        salida.setText("Lo ingresado no es"
                             + "\nun numero."
                        + "\nMonto erroneo.");
                    }
                    
                }else{
                     salida.setText("Lo ingresado no es"
                             + "\nun numero."
                        + "\nCuenta erronea.");
                }
            }else{
                salida.setText("Ingrese la cantidad que"
                             + "\ndesea abonar en la parte"
                             + "\ninferior de la pantalla.");
            }
            
        }else{
            salida.setText("Ingrese el numero de cuenta"
                    + "\nen la parte inferior"
                    + "\nde la pantalla.");
        }
    }
}
