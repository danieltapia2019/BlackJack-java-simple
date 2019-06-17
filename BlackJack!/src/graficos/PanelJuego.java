/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import Stream.Stream;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author DanielTapia
 */
public class PanelJuego extends JPanel {
    public  JPanel cartasJug;
    public  JPanel cartasCasa;

    public JLabel imagenFondo;
    public JLabel quienGana;
    public JButton quedarse;
    public JButton pedir;
    public JButton doblar;
    public JButton abrir;
    public JButton jugar;
    public JButton asegurar;
    public JButton aumentar;
    public JButton restar;
    public JButton salir;
    public JLabel apuestas;
    public JTextArea info;
    public VentanaPrincipal ventanaPadre;

    public PanelJuego(VentanaPrincipal ventanaPadre) {
        this.ventanaPadre = ventanaPadre;
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setLayout(null);
        setSize(VentanaPrincipal.ANCHO, VentanaPrincipal.ALTO);
        setLayout(null);
        setBackground(Color.red);
        jugar = new JButton("Jugar");
        quedarse = new JButton("Quedarse");
        pedir = new JButton("Pedir");
        doblar = new JButton("Doblar");
        abrir = new JButton("Abrir");
        asegurar = new JButton("Seguro");
        salir = new JButton("Salir");
        quienGana = new JLabel();
        aumentar = new JButton("+50");
        restar = new JButton("-50");
        info = new JTextArea();
        cartasJug=new JPanel();
        cartasCasa=new JPanel();
        cartasJug.setBounds(200,200,350,50);
        cartasCasa.setBounds(200,100,350,50);
        cartasCasa.setLayout(null);
        cartasJug.setLayout(null);

        quedarse.setBounds(VentanaPrincipal.ANCHO - 310, VentanaPrincipal.ALTO - 90, 100, 20);
        pedir.setBounds(VentanaPrincipal.ANCHO - 210, VentanaPrincipal.ALTO - 90, 100, 20);
        doblar.setBounds(VentanaPrincipal.ANCHO - 110, VentanaPrincipal.ALTO - 90, 100, 20);
        jugar.setBounds(VentanaPrincipal.ANCHO - 310, VentanaPrincipal.ALTO - 70, 100, 20);
        abrir.setBounds(VentanaPrincipal.ANCHO - 210, VentanaPrincipal.ALTO - 70, 100, 20);
        asegurar.setBounds(VentanaPrincipal.ANCHO - 110, VentanaPrincipal.ALTO - 70, 100, 20);
        salir.setBounds(VentanaPrincipal.ANCHO - 110, 40, 100, 20);
        aumentar.setBounds(VentanaPrincipal.ANCHO - 450, VentanaPrincipal.ALTO - 90, 100, 20);
        restar.setBounds(VentanaPrincipal.ANCHO - 450, VentanaPrincipal.ALTO - 70, 100, 20);
        quienGana.setBounds(0, 0, VentanaPrincipal.ANCHO, 20);
        quienGana.setOpaque(true);
        quienGana.setBackground(Color.YELLOW);
        quienGana.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quienGana.setText("Juego creado por Daniel Tapia");
        info.setBounds(5, VentanaPrincipal.ALTO - 170, 160, 140);
        info.setOpaque(true);
        info.setBackground(Color.green);
        info.setOpaque(true);
        quedarse.setEnabled(false);
        pedir.setEnabled(false);
        doblar.setEnabled(false);
        jugar.setEnabled(false);
        abrir.setEnabled(false);
        asegurar.setEnabled(false);

        add(quedarse);
        add(pedir);
        add(doblar);
        add(abrir);
        add(asegurar);
        add(jugar);
        add(salir);
        add(quienGana);
        add(aumentar);
        add(restar);
        add(info);
        add(cartasJug);
        add(cartasCasa);
        
         salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] opciones = {"Si", "No"};
    
               int seleccion = JOptionPane.showOptionDialog(null, "¿Está seguro de que desea salir?", "Alerta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                    if (seleccion == 0) {
                        try{
                            Stream.guardarDatos(Stream.jugadores);
                        }catch(Exception ex){
                            System.out.println("Error");
                        }
                        VentanaPrincipal.p1=true;
                        ventanaPadre.dispose();
                        VentanaPrincipal v=new VentanaPrincipal();
                    }
            }
        });

    }
}
