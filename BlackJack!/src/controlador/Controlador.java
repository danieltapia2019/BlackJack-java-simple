/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Stream.Stream;
import blackjack.JuegoBeta;
import graficos.VentanaPrincipal;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author DanielTapia
 */
public class Controlador implements Serializable {

    static int posUltimaCartaJug = 100;
    static JLabel puntajeJug;
    static JLabel puntajeCasa;
    static JLabel carta1 = new JLabel();
    static JLabel carta2 = new JLabel();
    static JLabel carta3 = new JLabel();
    static JLabel carta4 = new JLabel();

    public static VentanaPrincipal vista;
    public static JuegoBeta juego;
    public static boolean jugar = false;

    public Controlador(VentanaPrincipal vistaa) {
        vista = vistaa;
        juego = vista.blackjack;
        vista.panelJuego.info.setText(obtenerInfo());

        vista.panelJuego.salir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Stream.jugadores.get(0) == juego.player) {
                    System.out.println("Apuntan al mismo objeto");
                }

                String[] opciones = {"Si", "No"};

                int seleccion = JOptionPane.showOptionDialog(null, "¿Está seguro de que desea salir?", "Alerta", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
                if (seleccion == 0) {
                    posUltimaCartaJug = 100;

                    juego.player.mano.clear();
                    juego.player.bJ = false;
                    VentanaPrincipal.p1 = true;
                    try {
                        Stream.guardarDatos();
                    } catch (Exception ex) {
                        System.out.println("Error");
                    }
                    Stream.jugadores.clear();
                    juego.stop();
                    vista.dispose();
                    VentanaPrincipal v = new VentanaPrincipal();
                }
            }
        });

//        
        vista.panelJuego.aumentar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (juego.player.dinero >= 50) {
                    juego.player.apuesta += 50;
                    juego.player.dinero -= 50;
                    vista.panelJuego.info.setText(obtenerInfo());
                }

            }
        });
        vista.panelJuego.restar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (juego.player.apuesta >= 50) {
                    juego.player.apuesta -= 50;
                    juego.player.dinero += 50;
                    vista.panelJuego.info.setText(obtenerInfo());
                }

            }
        });
        vista.panelJuego.jugar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (juego.primeraMano) {
                    restart();

                }
                vista.panelJuego.aumentar.setEnabled(false);
                vista.panelJuego.restar.setEnabled(false);

                pintarCartas();
                juego.chequearMano();
                juego.jugar = true;
                vista.panelJuego.jugar.setEnabled(false);

            }
        });
        vista.panelJuego.pedir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                juego.repartirUna(juego.player);
                pintarUltima();
                juego.chequearMano();

            }
        });
        vista.panelJuego.quedarse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                juego.jugListo = true;
                juego.pedir = false;
                juego.quedarse = false;

            }
        });
        iniciar();

    }

    private void iniciar() {
        juego.start();
    }

    public static String obtenerInfo() {
        String info = "";
        info += juego.player.nombre + "\nDinero: " + juego.player.dinero + "\nApuesta: " + juego.player.apuesta + "\nTotal: " + juego.player.total;
        return info;
    }

    public void pintarCartas() {
        carta1 = new JLabel();
        carta2 = new JLabel();
        carta3 = new JLabel();
        carta4 = new JLabel();
        juego.repartirCartas();
        puntajeJug = new JLabel();
        carta1.setBounds(0, 0, 50, 50);
        carta2.setBounds(50, 0, 50, 50);
        carta3.setBounds(0, 0, 50, 50);
        carta4.setBounds(50, 0, 50, 50);
        puntajeJug.setBounds(270, 270, 100, 20);
        carta1.setOpaque(true);
        carta2.setOpaque(true);
        carta3.setOpaque(true);
        carta4.setOpaque(true);
        carta1.setBackground(Color.BLACK);
        carta2.setBackground(Color.CYAN);
        carta3.setBackground(Color.BLACK);
        carta4.setBackground(Color.CYAN);
        carta1.setText(juego.player.mano.get(0).nombre + "");
        carta2.setText(juego.player.mano.get(1).nombre + "");
        carta3.setText(juego.casa.mano.get(0).nombre + "");

        puntajeJug.setText("Puntos: " + juego.player.calcularPuntaje());
        vista.panelJuego.cartasJug.add(carta1);
        vista.panelJuego.cartasJug.add(carta2);
        vista.panelJuego.cartasCasa.add(carta3);
        vista.panelJuego.cartasCasa.add(carta4);
        vista.panelJuego.add(puntajeJug);
        vista.panelJuego.revalidate();
        vista.panelJuego.repaint();

    }

    public static void pintarUltima() {
        JLabel carta = new JLabel();
        vista.panelJuego.remove(puntajeJug);
        puntajeJug = new JLabel();
        carta.setBounds(posUltimaCartaJug, 0, 50, 50);
        puntajeJug.setBounds(270, 270, 100, 20);
        carta.setOpaque(true);
        carta.setBackground(Color.gray);
        carta.setText(juego.player.mano.get(juego.player.mano.size() - 1).nombre + "");
        puntajeJug.setText("Puntos: " + juego.player.calcularPuntaje());
        vista.panelJuego.add(puntajeJug);
        vista.panelJuego.cartasJug.add(carta);
        vista.panelJuego.revalidate();
        vista.panelJuego.repaint();
        posUltimaCartaJug += 50;
    }

    public static void pintarUltimaCroupier() {

        JLabel carta = new JLabel();
        vista.panelJuego.remove(puntajeCasa);
        puntajeCasa = new JLabel();
        carta.setBounds(posUltimaCartaJug, 0, 50, 50);
        puntajeCasa.setBounds(270, 170, 100, 20);
        carta.setOpaque(true);
        carta.setBackground(Color.gray);
        carta.setText(juego.casa.mano.get(juego.casa.mano.size() - 1).nombre + "");
        puntajeCasa.setText("Puntos: " + juego.casa.calcularPuntaje());
        vista.panelJuego.add(puntajeCasa);
        vista.panelJuego.cartasCasa.add(carta);
        vista.panelJuego.revalidate();
        vista.panelJuego.repaint();
        posUltimaCartaJug += 50;
    }

    public static void accionesCasa() {
        posUltimaCartaJug = 100;
        puntajeCasa = new JLabel();
        puntajeCasa.setBounds(270, 170, 100, 20);
        puntajeCasa.setText("Puntos: " + juego.casa.calcularPuntaje());
        vista.panelJuego.cartasCasa.remove(carta4);
        carta4 = new JLabel();
        carta4.setBackground(Color.CYAN);
        carta4.setOpaque(true);
        carta4.setBounds(50, 0, 50, 50);
        carta4.setText(juego.casa.mano.get(1).nombre + "");
        vista.panelJuego.cartasCasa.add(carta4);
        vista.panelJuego.add(puntajeCasa);
        vista.panelJuego.revalidate();

        vista.panelJuego.repaint();
        juego.chequearManoCroupier();

        while (juego.casa.pedirCarta == true) {

            juego.repartirUna(juego.casa);
            pintarUltimaCroupier();
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            juego.chequearManoCroupier();
        }

    }

    public static void restart() {
        vista.panelJuego.quienGana.setText("Juego creado por Daniel Tapia");
        posUltimaCartaJug = 100;
        vista.panelJuego.cartasJug.removeAll();
        vista.panelJuego.cartasCasa.removeAll();
        vista.panelJuego.remove(puntajeJug);
        if (puntajeCasa != null) {
            vista.panelJuego.remove(puntajeCasa);
        }
        vista.panelJuego.revalidate();
        vista.panelJuego.repaint();

        juego.player.descartarMano(juego.descarte);
        juego.casa.descartarMano(juego.descarte);
        juego.player.bJ = false;
        juego.casa.bJ = false;

    }

}
