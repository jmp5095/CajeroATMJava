/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guis;

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
    JButton miCuenta;
    JButton otraCuenta;
    JButton cancelar;
    
    JPanel botones;
    GridLayout esquemab;
    JPanel entrada;
    GridLayout esquemae;
    
    JTextField numeroDeCuenta;
    
    
    public TransaccionGui(){
        contenedor.setLayout(new FlowLayout());
        salida= new JTextArea();
        salida.setEditable(false);
        
        miCuenta=new JButton("abonar a mi cuenta");
        otraCuenta= new JButton("abonar a otra cuenta");
        cancelar=new JButton("Cancelar");
        
        numeroDeCuental=new JLabel("Número de cuenta");
        numeroDeCuenta= new JTextField(15);
        
        botones=new JPanel();
        esquemab=new GridLayout(3,1,1,2);
        botones.setLayout(esquemab);
        
        botones.add(miCuenta);
        botones.add(otraCuenta);
        botones.add(cancelar);
        
        entrada=new JPanel();
        esquemae=new GridLayout(3,1,1,2);
        entrada.setLayout(esquemae);
        
        entrada.add(numeroDeCuental);
        entrada.add(numeroDeCuenta);
        
        
        //posicion y tamaño de los elementos
        salida.setBounds(new Rectangle(50,30,230,60));
        botones.setBounds(new Rectangle(50,110,230,100));
        entrada.setBounds(new Rectangle(50,220,230,75));
        
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
        miCuenta.setBackground(Color.WHITE);
        miCuenta.setForeground(new java.awt.Color(54,22,181));
        otraCuenta.setBackground(Color.WHITE);
        otraCuenta.setForeground(new java.awt.Color(54,22,181));
        cancelar.setBackground(new java.awt.Color(245,35,20));
        cancelar.setForeground(Color.WHITE);
        entrada.setBackground(Color.white);
        
        //add listeners
        this.miCuenta.addActionListener(this);
        this.otraCuenta.addActionListener(this);
        this.cancelar.addActionListener(this);
        
        this.setSize(333,370);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(miCuenta)) {
            LoginGui.cajero.depositarEnCuenta(CajeroGui.cuenta.getNumeroDeCuenta());
            this.setVisible(false);
        }
        if (e.getSource().equals(otraCuenta)) {
            String en=numeroDeCuenta.getText();
            if (!en.equals("")) {
                if (LoginGui.cajero.esNumerico(en)) {
                    LoginGui.cajero.depositarEnCuenta(en);
                    this.setVisible(false);
                }else{
                   salida.setText("Lo ingresado no es un "
                           + "\nnumero");
                }
            }else{
                salida.setText("Ingrese por favor el \nnumero de cuenta en la "
                        + "\nparte inferior");
            }
        }
        if (e.getSource().equals(cancelar)) {
            this.setVisible(false);
        }
    }

    public String getNumeroDeCuenta() {
        return this.numeroDeCuenta.getText();
    }

    
    
}
