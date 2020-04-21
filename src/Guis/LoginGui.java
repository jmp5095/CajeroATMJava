/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guis;
import clases.Cliente;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author juan
 */
public class LoginGui extends JFrame implements ActionListener{
    Container contenedor=getContentPane();
    public static Cliente cliLogueado=new Cliente();
    public static CajeroGui cajero;
    
    JLabel titulo;
    JLabel espacio;
    JLabel cedula;
    JTextField campocedula;
    JLabel pass;
    JPasswordField campopass;
    
    GridLayout esquema2;
    JPanel panel;
    
    JPanel botones;
    GridLayout botonese;
    
    JButton boton1;
    JButton boton2;
    
    public LoginGui(){
        contenedor.setLayout(new FlowLayout());
        
        titulo=new JLabel("Inicio de Sesión");
        espacio=new JLabel("                                              ");
        cedula= new JLabel("Cédula");
        campocedula= new JTextField(15);
        pass=new JLabel("Contraseña");
        campopass=new JPasswordField(15);
        
        esquema2= new GridLayout(4,1,3,3);
        panel=new JPanel();
        panel.setLayout(esquema2);
        
        panel.add(cedula);
        panel.add(campocedula);
        panel.add(pass);
        panel.add(campopass);
        
        botones=new JPanel();
        botonese=new GridLayout(1,2,10,1);
        botones.setLayout(botonese);
        
        boton1= new JButton("Aceptar");
        boton2= new JButton("Cancelar");
        
        boton1.addActionListener(this);
        boton2.addActionListener(this);
        
        botones.add(boton1);
        botones.add(boton2);
        
        //estilo 
        Font auxFont=titulo.getFont();
        titulo.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),40));
        cedula.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),20));
        campocedula.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),16));
        pass.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),20));
        campopass.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),16));
        boton1.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),15));
        boton2.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),15));
        
        //posicionamiento
        titulo.setBounds(new Rectangle(37,35,360,50));
        panel.setBounds(new Rectangle(90,110,250,140));
        botones.setBounds(new Rectangle(60,290,300,30));
        //colores
        contenedor.setBackground(Color.WHITE);
        titulo.setForeground(new java.awt.Color(70,70,70));
        cedula.setForeground(new java.awt.Color(105,105,105));
        campocedula.setForeground(new java.awt.Color(70,70,70));
        pass.setForeground(new java.awt.Color(105,105,105));
        campopass.setForeground(new java.awt.Color(70,70,70));
        panel.setBackground(Color.WHITE);
        boton1.setBackground(new java.awt.Color(33,139,40));
        boton2.setBackground(new java.awt.Color(245,35,20));
        boton1.setForeground(Color.WHITE);
        boton2.setForeground(Color.WHITE);
        
            
            contenedor.add(titulo);
            contenedor.add(panel);
            contenedor.add(botones);
            contenedor.setLayout(null);
            
            this.setSize(425,425);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(boton1)) {
            accionAceptar();
        }
        if (e.getSource().equals(boton2)) {
            accionCancelar();
        }
        
    }
    
    public void accionAceptar(){
        
        String ce=campocedula.getText();
        String co=campopass.getText();
        Cliente cliente;
        cliente = new Cliente(ce,co);
        
        
        if ((cliente.validarCliente())) {
            LoginGui.cliLogueado=cliente;
            JOptionPane.showMessageDialog(null, "Bienvenido "+cliLogueado.getNombre()+"!");
            this.setVisible(false);
            mostrarCajeroGui();
        }else{
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas ");
        }
    }
    
    public void accionCancelar(){
        System.exit(0);
    }
    
    public void mostrarCajeroGui(){
            LoginGui.cajero=new CajeroGui();
            LoginGui.cajero.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public Cliente getCliente(){
        return LoginGui.cliLogueado;
    }
    
    public CajeroGui getCajero(){
        return LoginGui.cajero;
    }
    
}
