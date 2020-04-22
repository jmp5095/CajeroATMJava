/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor..
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

/**
 *
 * @author juan
 */
public class RetiroGui extends JFrame implements ActionListener {

    Container contenedor = getContentPane();

    JLabel mensaje;

    JButton veinte;
    JButton cuarenta;
    JButton sesenta;
    JButton cien;
    JButton docientos;

    JButton cancelar;

    JPanel botones;

    GridLayout esquema;

    public RetiroGui() {
        super("Retiro");
        contenedor.setLayout(new FlowLayout());

        mensaje = new JLabel("Elija la Cantidad a Retirar");

        veinte = new JButton("20.000");
        cuarenta = new JButton("40.000");
        sesenta = new JButton("60.000");
        cien = new JButton("100.000");
        docientos = new JButton("200.000");

        cancelar = new JButton("Cancelar");

        botones = new JPanel();
        esquema = new GridLayout(5, 1, 3, 1);
        botones.setLayout(esquema);

        botones.add(veinte);
        botones.add(cuarenta);
        botones.add(sesenta);
        botones.add(cien);
        botones.add(docientos);
        //adicionando escuchas
        agregarListenerBotones();

        contenedor.add(mensaje);
        contenedor.add(botones);
        contenedor.add(cancelar);
        contenedor.setLayout(null);
        //posicion y tamaño de los elementos
        mensaje.setBounds(new Rectangle(40, 10, 260, 40));
        botones.setBounds(new Rectangle(100, 70, 120, 150));
        cancelar.setBounds(new Rectangle(100, 240, 120, 50));

        //estilo
        Font numerosf = new Font("Arial", Font.BOLD, 14);
        mensaje.setFont(new Font(numerosf.getFontName(), numerosf.getStyle(), 18));
        veinte.setFont(new Font(numerosf.getFontName(), numerosf.getStyle(), 16));
        cuarenta.setFont(new Font(numerosf.getFontName(), numerosf.getStyle(), 16));
        sesenta.setFont(new Font(numerosf.getFontName(), numerosf.getStyle(), 16));
        cien.setFont(new Font(numerosf.getFontName(), numerosf.getStyle(), 16));
        docientos.setFont(new Font(numerosf.getFontName(), numerosf.getStyle(), 16));
        cancelar.setFont(new Font(numerosf.getFontName(), numerosf.getStyle(), 15));

        //colores
        contenedor.setBackground(Color.WHITE);
        veinte.setBackground(Color.WHITE);
        veinte.setForeground(new java.awt.Color(54, 22, 181));
        cuarenta.setBackground(Color.WHITE);
        cuarenta.setForeground(new java.awt.Color(54, 22, 181));
        sesenta.setBackground(Color.WHITE);
        sesenta.setForeground(new java.awt.Color(54, 22, 181));
        cien.setBackground(Color.WHITE);
        cien.setForeground(new java.awt.Color(54, 22, 181));
        docientos.setBackground(Color.WHITE);
        docientos.setForeground(new java.awt.Color(54, 22, 181));
        cancelar.setBackground(new java.awt.Color(245, 35, 20));
        cancelar.setForeground(Color.WHITE);

        this.setSize(333, 370);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(veinte)) {
            this.restarMonto(20000);
        }
        if (e.getSource().equals(cuarenta)) {
            this.restarMonto(40000);
        }
        if (e.getSource().equals(sesenta)) {
            this.restarMonto(60000);
        }
        if (e.getSource().equals(cien)) {
            this.restarMonto(100000);
        }
        if (e.getSource().equals(docientos)) {
            this.restarMonto(200000);
        }
        if (e.getSource().equals(cancelar)) {
            accionCancelar();
        }
    }

    public void restarMonto(double cantidad) {
        String m;
        m = CajeroGui.cuenta.retirarMonto(cantidad);
        if (m.equals("Retiro exitoso.")) {
            m = m + "\n\n";
            CajeroGui.cuenta.consultarCuenta();

            LoginGui.cajero.salida.setText(m);
            LoginGui.cajero.concatenaSalida("Saldo Actual:\n" + CajeroGui.cuenta.getMonto());
            LoginGui.cajero.concatenaSalida("Saldo Anterior:\n" + (CajeroGui.cuenta.getMonto() + cantidad));
            LoginGui.cajero.concatenaSalida("Cantidad Retirada:\n" + cantidad + "\n\n");
            LoginGui.cajero.concatenaSalida("No olvide retirar \nsu dinero");
            LoginGui.cajero.dispensador.setText(cantidad + "");

        } else {

            LoginGui.cajero.salida.setText(m);

        }
        this.setVisible(false);
    }

    public void accionCancelar() {
        this.setVisible(false);
    }

    public void agregarListenerBotones() {
        this.veinte.addActionListener(this);
        this.cuarenta.addActionListener(this);
        this.sesenta.addActionListener(this);
        this.cien.addActionListener(this);
        this.docientos.addActionListener(this);

        this.cancelar.addActionListener(this);
    }
}
