/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guis;

import clases.Cuenta;
import clases.Transaccion;
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
public class CajeroGui extends JFrame implements ActionListener{
    public static Cuenta cuenta=new Cuenta();
    public static double efectivo=1000000;
    public static TransaccionGui l;
     String aux="";
    Container contenedor=getContentPane();
    
    //planeo que sean un par de imagener que representan esto
    JLabel dispensadorl;
    JTextField dispensador;
    JLabel entradal;
    
    JTextArea salida;
    JTextField entrada;
    
    JButton uno;
    JButton dos;
    JButton tres;
    JButton cuatro;
    JButton cinco;
    JButton seis;
    JButton siete;
    JButton ocho;
    JButton nueve;
    JButton cero;
    
    JButton saldo;
    JButton retirar;
    JButton depositar;
    JButton cancelar;
    
    JPanel numeros;
    JPanel acciones;
    JPanel botones;
    
    GridLayout esqueman;
    GridLayout esquemaa;
    GridLayout esquema;
    
    public CajeroGui(){ super("Cajero ATM");
     
        contenedor.setLayout(new FlowLayout());

        this.instanciaCuenta();
        
        
        salida=new JTextArea();
        salida.setEditable(false);
        
        uno=new JButton("1");
        dos=new JButton("2");
        tres=new JButton("3");
        cuatro=new JButton("4");
        cinco=new JButton("5");
        seis=new JButton("6");
        siete=new JButton("7");
        ocho=new JButton("8");
        nueve=new JButton("9");
        cero=new JButton("0");
        
        saldo=new JButton("Saldo");
        retirar=new JButton("Retiro");
        depositar=new JButton("Abono");
        cancelar=new JButton("Cancelar");
        
        entradal=new JLabel("Deposito");
        entrada=new JTextField(15);
        entrada.setText(" ");
        dispensadorl=new JLabel("Dispensador");
        dispensador=new JTextField("");
        dispensador.setEditable(false);
        
        esqueman=new GridLayout(3,3,1,1);
        numeros=new JPanel();
        numeros.setLayout(esqueman);
        numeros.add(uno);
        numeros.add(dos);
        numeros.add(tres);
        numeros.add(cuatro);
        numeros.add(cinco);
        numeros.add(seis);
        numeros.add(siete);
        numeros.add(ocho);
        numeros.add(nueve);
        
        
        acciones=new JPanel();
        esquemaa=new GridLayout(3,1,1,1);
        acciones.setLayout(esquemaa);
        acciones.add(saldo);
        acciones.add(retirar);
        acciones.add(depositar);
        
        botones=new JPanel();
        esquema=new GridLayout(1,2,1,1);
        botones.setLayout(esquema);
        botones.add(numeros);
        botones.add(acciones);
        
        //posicion y tamaño de los elementos
        salida.setBounds(new Rectangle(50,50,230,180));
        numeros.setBounds(new Rectangle(50,250,150,100));
        acciones.setBounds(new Rectangle(205,250,80,100));
        cancelar.setBounds(new Rectangle(85,370,160,30));
        entradal.setBounds(new Rectangle(50,420,235,20));
        entrada.setBounds(new Rectangle(50,450,235,25));
        dispensadorl.setBounds(new Rectangle(50,480,235,20));
        dispensador.setBounds(new Rectangle(50,510,235,25));
        
        //estilo 
        Font auxFont=salida.getFont();
        Font numerosf = new Font("Arial", Font.BOLD, 14);
        salida.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),16));
        
        estiloNumeros(numerosf,15);
        estiloAcciones(numerosf,12);
        
        cancelar.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),15));
        
        entradal.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),15));
        entrada.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),16));
        dispensadorl.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),15));
        dispensador.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),16));
        
         //colores
        contenedor.setBackground(Color.WHITE);
        salida.setBackground(new java.awt.Color(54,22,181));
        salida.setForeground(new java.awt.Color(255,255,255));
        colorNumeros();
        colorAcciones();
        cancelar.setBackground(new java.awt.Color(245,35,20));
        cancelar.setForeground(Color.WHITE);
        
        //agregar escuchas
        agregarListenerBotones();
       
        
        
        contenedor.add(salida);
        contenedor.add(numeros);
        contenedor.add(acciones);
        contenedor.add(cancelar);
        contenedor.add(entradal);
        contenedor.add(entrada);
        contenedor.add(dispensadorl);
        contenedor.add(dispensador);
        contenedor.setLayout(null);
        
        
        this.setSize(333,630);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        
        
       
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(saldo)) {
            accionSaldo();
        }
        if (e.getSource().equals(retirar)) {
            accionRetirar();
        }
        if (e.getSource().equals(depositar)) {
            accionDepositar();
        }
        if (e.getSource().equals(cancelar)) {
            accionCancelar();
        }
        
        accionNumeros(e);
    
    }

    public static double getEfectivo() {
        return efectivo;
    }

    public static void setEfectivo(double efectivo) {
        CajeroGui.efectivo = efectivo;
    }
    
    public void accionSaldo(){
        this.entrada.setText(" ");
        this.dispensador.setText(" ");
        cuenta.consultarCuenta();
        cuenta.actualizarCuenta();
        cuenta.consultarCuenta();
        aux="Su saldo es: \n"+cuenta.getMonto();
        salida.setText(aux);
    }
    public void accionRetirar(){
        this.entrada.setText(" ");
        this.entrada.setText(" ");
        RetiroGui rgui=new RetiroGui();
        rgui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void accionDepositar(){
        this.dispensador.setText(" ");
        aux=entrada.getText();
        if (!aux.equals(" ")) {
            if (esNumerico(aux)) {
                
                 l=new TransaccionGui();
                l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
            }else{
                salida.setText("Lo ingresado\nEn el deposito \n"
                             + "No es dinero!");
            }
        }else{
            salida.setText("Ingrese la cantidad \nque desea depositar\n"
                         + "en la ranura \ninferior del atm \n"
                         + "destinada al deposito.");
        }
    }
    public void depositarEnCuenta(String deposito){
        if (esValido(aux)) {
            
                    double mon=Double.parseDouble(aux);
                    Transaccion t= new Transaccion();
                    t.setMonto(mon);
                    t.setAprovacion(false);
                    t.setRealizada(false);
                    t.setCuentaDepositante(cuenta.getNumeroDeCuenta());
                    t.setCuentaDeposito(deposito);
                    
                    t.registrar();
                    this.salida.setText(" ");
                    LoginGui.cajero.concatenaSalida("Apreciado usuario:\n"
                            + "tenga en cuenta que\n"
                            + "la cantidad abonada\n"
                            + "sera verificada y aprobada\n"
                            + "por nuestro personal.\n");
                  
                }else{
                    salida.setText("la minima cantidad \nque puede abonar\n es 1000");
                }
        this.entrada.setText(" ");
    }
    public void depositarMiCuenta(){
        if (esValido(aux)) {
            
                    double mon=Double.parseDouble(aux);
                    Transaccion t= new Transaccion();
                    t.setMonto(mon);
                    t.setAprovacion(false);
                    t.setRealizada(false);
                    t.setCuentaDepositante(cuenta.getNumeroDeCuenta());
                    t.setCuentaDeposito(cuenta.getNumeroDeCuenta());
                    
                    t.registrar();
                    this.salida.setText(" ");
                    LoginGui.cajero.concatenaSalida("Apreciado usuario:\n"
                            + "tenga en cuenta que\n"
                            + "la cantidad abonada\n"
                            + "sera verificada y aprobada\n"
                            + "por nuestro personal.\n");
                  
                }else{
                    salida.setText("la minima cantidad \nque puede abonar\n es 1000");
                }
        this.entrada.setText(" ");
    }

    public void estiloNumeros(Font auxFont,int t){
        uno.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        dos.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        tres.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        cuatro.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        cinco.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        seis.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        siete.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        ocho.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        nueve.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        cero.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
    }
    
    public boolean esValido(String n){
        double d=Double.parseDouble(n);
        return d>=1000;
    }
    public Boolean esNumerico(String n){
        try{
            double num=Double.parseDouble(n);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
 
    public void accionCancelar(){
        this.setVisible(false);
        mostrarLoginGui();
    }
    
    
    public void concatenaEntrada(String nv){
        entrada.setText(entrada.getText()+nv);
    }
    public void concatenaSalida(String nv){
        salida.setText(salida.getText()+nv+"\n");
    }
    public void reiniciaSalida(){
        salida.setText("");
    }
    public void agregarListenerBotones(){
        this.uno.addActionListener(this);
        this.dos.addActionListener(this);
        this.tres.addActionListener(this);
        this.cuatro.addActionListener(this);
        this.cinco.addActionListener(this);
        this.seis.addActionListener(this);
        this.siete.addActionListener(this);
        this.ocho.addActionListener(this);
        this.nueve.addActionListener(this);
        this.cero.addActionListener(this);
        
        this.saldo.addActionListener(this);
        this.retirar.addActionListener(this);
        this.depositar.addActionListener(this);
        //this.enter.addActionListener(this);
        this.cancelar.addActionListener(this);
    }
    public void mostrarLoginGui(){
        LoginGui login=new LoginGui();
            login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void instanciaCuenta(){
        cuenta.setC(LoginGui.cliLogueado);
        
        cuenta.consultarCuenta();
        cuenta.actualizarCuenta();
        cuenta.consultarCuenta();
        
        System.out.println(cuenta.getNumeroDeCuenta());
        System.out.println(cuenta.getTipo());
        System.out.println(cuenta.getMonto());
        System.out.println(cuenta.getC().getCedula());
        System.out.println(cuenta.getC().getNombre());
                
    }
    
    public void accionNumeros(ActionEvent e){
        if (e.getSource().equals(uno)) {
            concatenaEntrada("1");
        }
        if (e.getSource().equals(dos)) {
            concatenaEntrada("2");
        }
        if (e.getSource().equals(tres)) {
            concatenaEntrada("3");
        }
        if (e.getSource().equals(cuatro)) {
            concatenaEntrada("4");
        }
        if (e.getSource().equals(cinco)) {
            concatenaEntrada("5");
        }
        if (e.getSource().equals(seis)) {
            concatenaEntrada("6");
        }
        if (e.getSource().equals(siete)) {
            concatenaEntrada("7");
        }
        if (e.getSource().equals(ocho)) {
            concatenaEntrada("8");
        }
        if (e.getSource().equals(nueve)) {
            concatenaEntrada("9");
        }
        if (e.getSource().equals(cero)) {
            concatenaEntrada("0");
        }
    }
    public void misEstilos(){
        saldo.setForeground(Color.red);
        retirar.setForeground(new java.awt.Color(0,50,200));
        depositar.setBackground(java.awt.Color.LIGHT_GRAY);
    }
    public JTextArea getSalida(){
        return this.salida;
    }
    
    public void colorNumeros(){
        int p=150;
        uno.setBackground(new java.awt.Color(p,p,p));
        dos.setBackground(new java.awt.Color(p,p,p));
        tres.setBackground(new java.awt.Color(p,p,p));
        cuatro.setBackground(new java.awt.Color(p,p,p));
        cinco.setBackground(new java.awt.Color(p,p,p));
        seis.setBackground(new java.awt.Color(p,p,p));
        siete.setBackground(new java.awt.Color(p,p,p));
        ocho.setBackground(new java.awt.Color(p,p,p));
        nueve.setBackground(new java.awt.Color(p,p,p));
        cero.setBackground(new java.awt.Color(p,p,p));
        
        uno.setForeground(Color.WHITE);
        dos.setForeground(Color.WHITE);
        tres.setForeground(Color.WHITE);
        cuatro.setForeground(Color.WHITE);
        cinco.setForeground(Color.WHITE);
        seis.setForeground(Color.WHITE);
        siete.setForeground(Color.WHITE);
        ocho.setForeground(Color.WHITE);
        nueve.setForeground(Color.WHITE);
        cero.setForeground(Color.WHITE);
    }
    public void estiloAcciones(Font auxFont,int t){
        saldo.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        retirar.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
        depositar.setFont(new Font(auxFont.getFontName(),auxFont.getStyle(),t));
    }
    
    public void colorAcciones(){
        int p=2;
        saldo.setBackground(new java.awt.Color(203,213,53));
        retirar.setBackground(new java.awt.Color(27,144,55));
        depositar.setBackground(new java.awt.Color(37,55,144));
        
        
        saldo.setForeground(Color.WHITE);
        retirar.setForeground(Color.WHITE);
        depositar.setForeground(Color.WHITE);
    }
}
